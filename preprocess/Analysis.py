from jinja2 import Template
from pathlib import Path
from tqdm import tqdm
import xml.etree.ElementTree as ET
import subprocess as sp
import pandas as pd
import pickle
import random
import string
import time
import logging
import shutil
import sys
from deprecated import deprecated
import traceback
import CHA as cha

logger = logging.getLogger()
file_handler = logging.FileHandler(filename="logfile_" + time.strftime("%Y%m%d_%H%M%S") + ".log",mode = "w")
handler = logging.StreamHandler(stream=sys.stdout) 
logger.addHandler(file_handler)
logger.setLevel(logging.INFO)

class Analysis:
    ROOT_DIR = str(Path().resolve().parent)
    PROJ_DIR = ROOT_DIR + "/HybridSE"
    ACTIVITY_TEMPLATE = PROJ_DIR + '/data/ActivityWrapper.template'
    HYBRIDSE_DIR = PROJ_DIR + '/src'
    ENTRY = {'onCreate':['bd'], 'buttonClicked':['view'], 'onStart':['intent'], \
             'onStartCommand':['intent'],\
             'onRequestPermissionsResult':['1', 'strArray', 'intArray']}
    RESULT_DIR = PROJ_DIR + '/temp/result'
    FAMILY_CSV="./data/sha256_family.csv"
    SOURCE_FILE = PROJ_DIR + "/lib/sources.txt"
    SINK_FILE = PROJ_DIR + "/lib/sinks.txt"
    TIMEOUT=100

    def __init__(self) -> None:
        self.root_package = None
        self.activities = {}    # Activity and package name from AndroidManifest.xml
        self.entrypoints = {}   
        self.native_path = None
        self.classes = {}       # All classes from classes.dex
        self.services = {}      # Service from AndroidManifest.xml  
        self.applications = {}  # Application from AndroidManifest.xml
        self.threads = {}       # Thread from source code
        self.asyncTasks = {}    # AsyncTask from source code
        self.runnables = {}     # java.lang.Runnable from source code
        self.stat = (0, 0, 0)
        self.callgraph = ([],[])     # Tuple of nodes and edges callgraph from CHA
        self.api_list = []      # API list get from CHA

    def getPermissionsFromXML(self, sample_dir):
        """ Get application permission from Android Manifest.xml """
        xmlfile = sample_dir + '/AndroidManifest.xml'
        permissions = []
        try:
            tree = ET.parse(xmlfile)
    
            # get root element
            root = tree.getroot()
            for item in root.findall('.//uses-permission'):
                name_tag = '{http://schemas.android.com/apk/res/android}name'
                if name_tag in item.attrib:
                    android_name = item.attrib[name_tag]
                    permissions.append(android_name)
            # print permissions
            print("=== Extracting APK permissions ===")
            with open(sample_dir + '/permissions.txt', 'w') as f:
                f.writelines('\n'.join(permissions))
            print("Permission info saved in {}".format(sample_dir + '/permissions.txt'))
        except:
            print("FAIL to parse AndroidManifest.xml")
        return permissions                

    def parseXML(self, sample_dir):
        """ 
        Parse XML and source code to get Activities, Services, ...
        Parse Call Graph to get class hierachy analysis
        """
        xmlfile = sample_dir + '/AndroidManifest.xml'
        try:
            tree = ET.parse(xmlfile)
    
            # get root element
            root = tree.getroot()

            # create empty list for news items
            activities_dict = {}
            services_dict = {}
            application_dict = {}
            root_package = root.attrib['package']
            apis = []
            call_nodes, call_edges = [], []
            
            for item in root.findall('.//activity'):
                name_tag = '{http://schemas.android.com/apk/res/android}name'
                if name_tag in item.attrib:
                    android_name = item.attrib[name_tag]
                    if android_name.startswith('.'):
                        class_name=android_name.replace('.','')
                        activities_dict[class_name] = root_package
                    elif android_name.startswith(root_package) or android_name.startswith('com'):
                        paths = [x for x in android_name.split('.') if x]
                        class_name = paths[len(paths) - 1]
                        activities_dict[class_name] = '.'.join(paths[:-1]) + '.'
                    else:
                        paths = [x for x in android_name.split('.') if x]
                        class_name = paths[len(paths) - 1]
                        package_name = root_package + '.' + '.'.join(paths[:-1])
                        activities_dict[class_name] = root_package + '.' + '.'.join(paths[:-1])
    
            for item in root.findall('.//service'):
                # android_name = item.attrib['{http://schemas.android.com/apk/res/android}name']
                name_tag = '{http://schemas.android.com/apk/res/android}name'
                if name_tag in item.attrib:
                    android_name = item.attrib[name_tag]
                    if android_name.startswith('.'):
                        class_name=android_name.replace('.','')
                        services_dict[class_name] = root_package
                    elif android_name.startswith(root_package) or android_name.startswith('com'):
                        paths = [x for x in android_name.split('.') if x]
                        class_name = paths[len(paths) - 1]
                        services_dict[class_name] = '.'.join(paths[:-1])
                    else:
                        paths = [x for x in android_name.split('.') if x]
                        class_name = paths[len(paths) - 1]
                        package_name = root_package + '.' + '.'.join(paths[:-1])
                        services_dict[class_name] = package_name
            
            for item in root.findall('.//application'):
                name_tag = '{http://schemas.android.com/apk/res/android}name'
                if name_tag in item.attrib:
                    android_name = item.attrib[name_tag]
                    if android_name.startswith('com'):
                        paths = [x for x in android_name.split('.') if x]
                        class_name = paths[len(paths) - 1].strip()
                        application_dict[class_name] = '.'.join(paths[:-1]).strip()
        except Exception:
            print("FAIL to parse AndroidManifest.xml")
    
        # Classes are loaded in method_dict.pkl
        with open(sample_dir + '/method_dict.pkl', 'rb') as handle:
                pkl_dict = pickle.load(handle)
                async_dict = {}
                for task in pkl_dict['asyncTask']:
                    class_name = task.split('.')[-1]
                    package_name = task.replace('.'+class_name, '')
                    async_dict[class_name] = package_name
                self.asyncTasks = async_dict
                pkl_dict.pop('asyncTask')
                
                thread_dict = {}
                for th in pkl_dict['Thread']:
                    class_name = th.split('.')[-1]
                    package_name = th.replace('.'+class_name, '')
                    thread_dict[class_name] = package_name
                self.threads = thread_dict
                pkl_dict.pop('Thread')

                runnable_dict = {}
                for r in pkl_dict['Runnable']:
                    class_name = r.split('.')[-1]
                    package_name = r.replace('.'+class_name, '')
                    runnable_dict[class_name] = package_name
                self.runnables = runnable_dict
                pkl_dict.pop('Runnable')

                self.classes = pkl_dict

        # Call graph is saved in callgraph.txt
        callfile = sample_dir + '/callgraph.txt'
        call_nodes, call_edges, apis = cha.parse_java_callgraph(callfile)
        
        self.native_path = self.__find_native(sample_dir)
        self.root_package = root_package
        self.activities = self.__clear_path(activities_dict)
        self.services = self.__clear_path(services_dict)
        self.applications = self.__clear_path(application_dict)
        self.callgraph = (call_nodes, call_edges)
        self.api_list = apis

    def startChaBySources(self, sample_dir:str):
        sources = []
        with open(self.SOURCE_FILE, "r", encoding="utf-8") as file:
            for line in file.readlines():
                line = line.replace('\n', '').strip()
                sources.append(line)

        sinks = []
        with open(self.SINK_FILE, "r", encoding="utf-8") as file:
            for line in file.readlines():
                line = line.replace('\n', '').strip()
                sinks.append(line)
        
        call_nodes, call_edges, apis = cha.parse_java_callgraph(sample_dir + "/callgraph.txt")

        searched_nodes, searched_edges = [], []
        print("=== Search from Call graph ===")
        for source in sources:
            if len(cha.get_node_by_name(call_nodes, source)) > 0:
                    source_node = cha.get_node_by_name(call_nodes, source)[0]
                    sub_nodes, sub_edges = cha.get_subgraph_by_class(call_nodes,call_edges, source)
                    potential_sink = [b for a in sinks for b in sub_nodes if a in b]
                    if len(potential_sink) > 0:
                        for edge in call_edges:
                            if edge[1] == source_node:
                                print("ENTRYPOINT: " + edge[0])
                                print("+ SOURCE:" + edge[1])
                                print("+ SINK: " + potential_sink[0])
                    searched_edges.append(sub_edges)
                    searched_nodes.append(sub_nodes)
                    
        searched_dot = cha.create_dot_from_graph(searched_nodes, searched_edges, True)
        with open(sample_dir + '/sub_callgraph.dot', "w") as dotfile:
            dotfile.writelines(searched_dot)
    
    def startDseByEntryPoints(self, sample_dir:str):
        """
        Explore all entry points
        :param str sample_dir: java_projects folder path
        """
        outputs=[]
        self.parseXML(sample_dir)
        
        apk_name = Path(sample_dir).stem
        class_name = ''
        
        print('=== RUN ACTIVITIES ===')
        match_count = 0 # number of activity match between android manifest and .class
        for act in self.activities:
            entries = self.__get_entry_in_activity(activity_name=act) 
            match_count = match_count + 1 if entries[0] is True else 0
            for entry in entries[1]:
                self.run_actitivy(sample_dir, act, self.activities[act], entry, args_count=1, args_types=None)

        print('=== RUN SERVICES ===')
        for ser in self.services:
            entries = self.__get_entry_in_service(service_name=ser)
            self.run_service(sample_dir, ser, self.services[ser], 'onStart', args_count=0, args_types=None)
            self.run_service(sample_dir, ser, self.services[ser], 'onCreate', args_count=0, args_types=None)
        
        for task in self.asyncTasks:
            self.run_async(sample_dir, task, self.asyncTasks[task], 'doInBackground', args_count=0, args_types=None)
        
        print('=== RUN APPLICATION ===')
        for cls in self.applications:
            if 'StubApp' in cls:
                #Run StupApp
                self.run_application(sample_dir, cls, self.applications[cls], 'onCreate', args_count=0, args_types=None)
            else:
                entries = self.__get_entry_in_application(application_name=cls)
                if entries[0]:
                    for entry in entries[1]:
                        self.run_application(sample_dir, cls, self.applications[cls], entry, args_count=0, args_types=None)
        
        #JNI_OnLoad       
        print('JNI_ONLOAD')
        package_name = ""
        for natv in self.__find_natives(sample_dir):
            self.native_path = natv
            print(natv)
            self.run_corana_direct(sample_dir, class_name, package_name, 'JNI_OnLoad', args_types=None, args_count=None)

        logger.debug(msg=self.stat)

        # Add Comment if want to keep java files for debug
        shutil.rmtree(sample_dir+'/src', ignore_errors=True)

    def __genAsync(self, async_name, async_path, entry_point, args_count=None, args_types=None):
        args_str = (',').join(Analysis.__random(args_types)) if args_types is not None else ''
        logger.debug(msg=async_name+'.'+entry_point+'('+args_str+')')
        with open('./data/AsyncTaskWrapper.template') as file_:
            struct_template = Template(file_.read(), trim_blocks=True)
            return struct_template.render(package_name=async_path,class_name=async_name,\
                                          entry_point=entry_point, args=args_str)

    def __genService(self, activity_name, activity_path, entry_point, args_count=None, args_types=None):
        args_str = (',').join(Analysis.__random(args_types)) if args_types is not None else ''
        logger.debug(msg=activity_name+'.'+entry_point+'('+args_str+')')
        with open('./data/ServiceWrapper.template') as file_:
            struct_template = Template(file_.read(), trim_blocks=True)
            return struct_template.render(package_name=activity_path,class_name=activity_name,\
                                          entry_point=entry_point, args=args_str)

    def __genActivity(self, activity_name, activity_path, entry_point, args_count=None, args_types=None):
        args_str = (',').join(Analysis.__random(args_types)) if args_types is not None else ''
        logger.debug(msg=activity_name+'.'+entry_point+'('+args_str+')')
        with open('./data/ActivityWrapper.template') as file_:
            struct_template = Template(file_.read(), trim_blocks=True)
            return struct_template.render(package_name=activity_path,class_name=activity_name,\
                                          entry_point=entry_point, args=args_str)
    
    def __genApplication(self, application_name, activity_path, entry_point, args_count=None, args_types=None):
        args_str = (',').join(Analysis.__random(args_types)) if args_types is not None else ''
        logger.debug(msg=application_name+'.'+entry_point+'('+args_str+')')
        with open('./data/ApplicationWrapper.template') as file_:
            struct_template = Template(file_.read(), trim_blocks=True)
            return struct_template.render(package_name=activity_path,class_name=application_name,\
                                          entry_point=entry_point, args=args_str)

    def __genJpfFile(self, activity_name, activity_path, entry_point, args_count=None, args_types=None):
        args_count = args_count if args_count is not None else \
            (len(args_types) if args_types is not None else 0)
        args_str = (',').join(Analysis.__random_sym(args_types)) if args_types is not None else \
            (',').join(['sym']*args_count)
        base_path = self.PROJ_DIR + '/data/base_project/base.jar'
        with open('./data/JPF.template') as file_:
            template = Template(file_.read(), trim_blocks=True)
            return template.render(package_name=activity_path,class_name=activity_name,base_android=base_path,\
                                    native_lib=self.native_path, entry_point=entry_point, sym_str=args_str)
    
    def __genThread(self, thread_name, thread_path, entry_point, args_count=None, args_types=None):
        args_str=""
        logger.debug(msg=thread_name+'.'+entry_point+'('+args_str+')')
        with open('./data/ActivityWrapper.template') as file_:
            struct_template = Template(file_.read(), trim_blocks=True)
            return struct_template.render(package_name=thread_path,class_name=thread_name,\
                                          entry_point=entry_point, args=args_str)

    def __stat(self, output:str) -> tuple:
        return (output.count('\n-'), output.count('\n->'), output.count("=== Call to library function"))

    def __sum_stat(self, output:str) -> tuple:
        a = self.__stat(output)
        self.stat = tuple(map(sum, zip(self.stat, a)))    

    def build():
        build_cmd = "bash {}/build.sh {}".format(Analysis.HYBRIDSE_DIR, Analysis.HYBRIDSE_DIR)
        build_run = sp.run(build_cmd, shell=True, capture_output=True, text=True)
        logger.debug(msg=build_run.stdout)

    def run_actitivy(self, sample_dir, class_name, package_name, entry, args_count=None, args_types=None):
        try:    
            f_out = open(sample_dir+'/output_'+class_name+'_'+entry+'.out', "w")
            try:
                base_project = self.PROJ_DIR + '/data/base_project/base.jar'
                print('ENTRYPOINT: ' + class_name + '.' + entry)
                java_file=sample_dir + '/src/' + package_name.replace('.', '/') + '/' + class_name + 'Wrapper.java'
                f_wrapper = open(java_file, "w") 
                wrapper_content = self.__genActivity(class_name, package_name, entry, args_count=args_count, args_types=args_types)
                
                f_wrapper.write(wrapper_content)
                f_wrapper.close()
                
                f_jpf = open(sample_dir + '/'+ class_name+'_'+entry+'.jpf', "w")
                jpf_content = self.__genJpfFile(class_name, package_name, entry, args_count=args_count, args_types=args_types)
                f_jpf.write(jpf_content)
                f_jpf.close()
                
                compile_cmd = "cd {} && javac -g -cp {}:. {}".format(sample_dir+'/src', base_project, java_file.replace(sample_dir +'/src/', ''))
                
                sp.run(compile_cmd, shell=True, capture_output=True, text=True)
                
                run_cmd = "bash {}/run.sh {} {}".format(self.HYBRIDSE_DIR, self.HYBRIDSE_DIR, Path(f_jpf.name).absolute())
                logger.debug(msg=run_cmd)
                
                se_run = sp.run(run_cmd, shell=True, timeout=self.TIMEOUT, capture_output=True, text=True)
                logger.debug(msg=se_run.stdout)
                se_out = se_run.stdout
                
                f_out.write(se_out)
                self.__sum_stat(se_out)
                logger.error(msg=se_run.stderr)
                
                # delete temporary file
                Path(f_wrapper.name).unlink()
                Path(f_jpf.name).unlink()
                Path(sample_dir+'/output.txt').unlink()  
                return se_out 
            except sp.TimeoutExpired as timeErr:
                err_out = timeErr.stdout.decode()
                #errs = timeErr.stderr.decode()
                f_out.write(err_out)
            f_out.close()
        except FileNotFoundError:
            print('FAIL ' + class_name + '.' + entry + '\n')

    def run_application(self, sample_dir, class_name, package_name, entry, args_count=None, args_types = None):
        try:
            f_out = open(sample_dir+'/output_'+class_name+'_'+entry+'.out', "w")
            try:
                base_project = self.PROJ_DIR + '/data/base_project/base.jar'
                
                java_file=sample_dir + '/src/' + package_name.replace('.', '/') + '/' + class_name + 'Wrapper.java'
                f_wrapper = open(java_file, "w") 
                wrapper_content = self.__genApplication(class_name, package_name, entry, args_count=args_count, args_types=args_types)
            
                f_wrapper.write(wrapper_content)
                f_wrapper.close()
                
                f_jpf = open(sample_dir + '/'+ class_name+'_'+entry+'.jpf', "w")
                jpf_content = self.__genJpfFile(class_name, package_name, entry, args_count=args_count, args_types=args_types)
                f_jpf.write(jpf_content)
                f_jpf.close()
                
                compile_cmd = "cd {} && javac -g -cp {}:. {}".format(sample_dir+'/src', base_project, java_file.replace(sample_dir +'/src/', ''))
                #compile_cmd = "cd {} && javac -g {}".format(sample_dir+'/src', java_file.replace(sample_dir +'/src/', ''))
                sp.run(compile_cmd, shell=True, capture_output=True, text=True)
                
                run_cmd = "bash {}/run.sh {} {}".format(self.HYBRIDSE_DIR, self.HYBRIDSE_DIR, Path(f_jpf.name).absolute())
                logger.debug(msg=run_cmd)

                se_run = sp.run(run_cmd, shell=True, timeout=self.TIMEOUT, capture_output=True, text=True)
                logger.debug(msg=se_run.stdout)
                se_out = se_run.stdout
                f_out.write(se_out)
              
                self.__sum_stat(se_out)
                logger.error(msg=se_run.stderr)
                # delete temperary file
                
                #Path(f_wrapper.name).unlink()
                #Path(f_jpf.name).unlink()
                Path(sample_dir+'/output.txt').unlink()
                return se_out 
        
            except sp.TimeoutExpired as timeErr:
                err_out = timeErr.stdout.decode()
                #errs = timeErr.stderr.decode()
                f_out.write(err_out)
            f_out.close()
        except FileNotFoundError:
            print('FAIL ' + class_name + '.' + entry )

    def run_corana_direct(self, sample_dir, class_name, package_name, entry, args_count=None, args_types=None):
        try:
            f_out = open(sample_dir+'/output_'+class_name+'_'+entry.strip()+'.out', "a")
            try:
                native_lib = str(Path(sample_dir).absolute()) + '/' + self.native_path
                run_cmd = "bash {}/run_direct.sh {} {} {}".format(self.HYBRIDSE_DIR, self.HYBRIDSE_DIR, native_lib, entry)
             
                se_run = sp.run(run_cmd, shell=True, timeout=self.TIMEOUT, capture_output=True, text=True)
                se_out = se_run.stdout
            
                f_out.write(se_out)
              
                self.__sum_stat(se_out)
                # delete temperary file
                return se_out   
            except sp.TimeoutExpired as timeErr:
                err_out = timeErr.stdout.decode()
                #errs = timeErr.stderr.decode()
                f_out.write(err_out)
            f_out.close()
        except FileNotFoundError:
            print('FAIL ' + class_name + '.' + entry )

    def run_service(self, sample_dir, class_name, package_name, entry, args_count=None, args_types=None):
        try:
            f_out = open(sample_dir+'/output_'+class_name+'_'+entry+'.out', "w")
            try:
                base_project = self.PROJ_DIR + '/data/base_project/base.jar'
                
                java_file=sample_dir + '/src/' + package_name.replace('.', '/') + '/' + class_name + 'Wrapper.java'
                f_wrapper = open(java_file, "w")    
                wrapper_content = self.__genService(class_name, package_name, entry, args_count=args_count, args_types=args_types)
                f_wrapper.write(wrapper_content)
                f_wrapper.close()
                
                f_jpf = open(sample_dir + '/'+class_name+'_'+entry+'.jpf', "w")
                jpf_content = self.__genJpfFile(class_name, package_name, entry, args_count=args_count, args_types=args_types)
                f_jpf.write(jpf_content)
                f_jpf.close()
                compile_cmd = "cd {} && javac -g -cp {}:. {}".format(sample_dir+'/src', base_project, java_file.replace(sample_dir +'/src/', ''))
                sp.run(compile_cmd, shell=True,  capture_output=True, text=True)

                run_cmd = "bash {}/run.sh {} {}".format(self.HYBRIDSE_DIR, self.HYBRIDSE_DIR, Path(f_jpf.name).absolute())
                se_run = sp.run(run_cmd, shell=True, timeout=self.TIMEOUT, capture_output=True, text=True)
                logger.debug(msg=se_run.stdout)
                se_out = se_run.stdout
                
                f_out.write(se_out)
                
                self.__sum_stat(se_out)
                #logger.error(msg=se_run.stderr)
                # delete temperary file
                Path(f_wrapper.name).unlink()
                Path(f_jpf.name).unlink()
                Path(sample_dir+'/output.txt').unlink()
                return se_out
            except sp.TimeoutExpired as timeErr:
                err_out = timeErr.stdout.decode()
                #errs = timeErr.stderr.decode()
                f_out.write(err_out)
            f_out.close()
        except FileNotFoundError:
            print('FAIL ' + class_name + '.' + entry )
        
    def run_thread(self, sample_dir, class_name, package_name, entry, args_count=None, args_types=None):
        try:
            f_out = open(sample_dir+'/output_'+class_name+'_'+entry+'.out', "w")
            try:
                base_project = self.PROJ_DIR + '/data/base_project/base.jar'
                
                java_file=sample_dir + '/src/' + package_name.replace('.', '/') + '/' + class_name + 'Wrapper.java'
            
                f_wrapper = open(java_file, "w")
                wrapper_content = self.__genThread(class_name, package_name, entry, args_count=args_count, args_types=args_types)
               
                f_wrapper.write(wrapper_content)
                f_wrapper.close()
                
                f_jpf = open(sample_dir + '/'+class_name+'_'+entry+'.jpf', "w")
                jpf_content = self.__genJpfFile(class_name, package_name, entry, args_count=args_count, args_types=args_types)
                
                f_jpf.write(jpf_content)
                f_jpf.close()
                
                compile_cmd = "cd {} && javac -g -cp {}:. {}".format(sample_dir+'/src', base_project, java_file.replace(sample_dir +'/src/', ''))
                
                sp.run(compile_cmd, shell=True,  capture_output=True, text=True)
                
                run_cmd = "bash {}/run.sh {} {}".format(self.HYBRIDSE_DIR, self.HYBRIDSE_DIR, Path(f_jpf.name).absolute())

                se_run = sp.run(run_cmd, shell=True, capture_output=True, text=True)
                logger.debug(msg=se_run.stdout)
                se_out = se_run.stdout
     
                f_out.write(se_out)


                self.__sum_stat(se_out)
           
                # delete temperary file
                Path(f_wrapper.name).unlink()
                Path(f_jpf.name).unlink()
                Path(sample_dir+'/output.txt').unlink()
                return se_out  
            except sp.TimeoutExpired as timeErr:
                err_out = timeErr.stdout.decode()
                #errs = timeErr.stderr.decode()
                f_out.write(err_out)
            f_out.close()
        except FileNotFoundError:
            print('FAIL ' + class_name + '.' + entry )

    def run_async(self, sample_dir, class_name, package_name, entry, args_count=None, args_types=None):
        try:
            f_out = open(sample_dir+'/output_'+class_name+'_'+entry+'.out', "w")
            try:
                base_project = self.PROJ_DIR + '/data/base_project/base.jar'
                
                java_file=sample_dir + '/src/' + package_name.replace('.', '/') + '/' + class_name + 'Wrapper.java'
               
                f_wrapper = open(java_file, "w")
                wrapper_content = self.__genAsync(class_name, package_name, entry, args_count=args_count, args_types=args_types)
                f_wrapper.write(wrapper_content)
                f_wrapper.close()
                
                f_jpf = open(sample_dir + '/'+class_name+'_'+entry+'.jpf', "w")
                jpf_content = self.__genJpfFile(class_name, package_name, entry, args_count=args_count, args_types=args_types)
    
                f_jpf.write(jpf_content)
                f_jpf.close()
                
                compile_cmd = "cd {} && javac -g -cp {}:. {}".format(sample_dir+'/src', base_project, java_file.replace(sample_dir +'/src/', ''))
                #compile_cmd = "cd {} && javac -g {}".format(sample_dir+'/src', java_file.replace(sample_dir +'/src/', ''))
                sp.run(compile_cmd, shell=True,  capture_output=True, text=True)
                
                run_cmd = "bash {}/run.sh {} {}".format(self.HYBRIDSE_DIR, self.HYBRIDSE_DIR, Path(f_jpf.name).absolute())
                se_run = sp.run(run_cmd, shell=True, capture_output=True, text=True)
                logger.debug(msg=se_run.stdout)
                se_out = se_run.stdout          
                
                f_out.write(se_out)
            
                self.__sum_stat(se_out)
                #logger.error(msg=se_run.stderr)
                # delete temperary file
                Path(f_wrapper.name).unlink()
                Path(f_jpf.name).unlink()
                Path(sample_dir+'/output.txt').unlink()
                return se_out  
            except sp.TimeoutExpired as timeErr:
                err_out = timeErr.stdout.decode()
                #errs = timeErr.stderr.decode()
                f_out.write(err_out)
            f_out.close()
        except FileNotFoundError:
            print('FAIL ' + class_name + '.' + entry )
    
    """
    Utility methods
    """    
    def __get_entry_in_application(self, application_name):
        package_name = self.applications[application_name]
        entry_points = []
        application_full = package_name + '.' + application_name
        mth_class = application_full
        if mth_class not in self.classes.keys(): return (False, entry_points)
        
        for mth_sig in self.classes[mth_class]:
                param_list = mth_sig[mth_sig.find('(')+1:mth_sig.rfind(')')].strip().split(',')
                param_list = param_list.remove('') if '' in param_list else param_list #param list

                method_name = mth_sig.split('(')[0].split()[-1].replace(';','')[:mth_sig.find('(')+1].replace('(','').replace(')','') #entry point
                return_type = mth_sig.split()[-2]
                class_name = mth_class.split('.')[-1]  #activity class
                package_name = mth_class[:mth_class.rfind('.')]
                
                if method_name in self.ENTRY.keys() and method_name not in entry_points:
                    entry_points.append(method_name)
        if 'onCreate' not in entry_points: entry_points.append('onCreate')
        return (True, entry_points)

    def __get_entry_in_service(self, service_name):
        package_name = self.services[service_name]
        entry_points = []
        service_full = package_name + '.' + service_name
        mth_class = service_full
        if mth_class not in self.classes.keys(): return (False, entry_points)
        
        for mth_sig in self.classes[mth_class]:
                param_list = mth_sig[mth_sig.find('(')+1:mth_sig.rfind(')')].strip().split(',')
                param_list = param_list.remove('') if '' in param_list else param_list #param list

                method_name = mth_sig.split('(')[0].split()[-1].replace(';','')[:mth_sig.find('(')+1].replace('(','').replace(')','') #entry point
                return_type = mth_sig.split()[-2]
                class_name = mth_class.split('.')[-1] 
                package_name = mth_class[:mth_class.rfind('.')]
                
                #if method_name in self.ENTRY.keys() and method_name not in entry_points:
                if method_name not in entry_points:
                    entry_points.append(method_name)
        if 'onCreate' not in entry_points: entry_points.append('onCreate')
        return (True, entry_points)
    
    def __get_entry_in_activity(self, activity_name):
        """ Return if found activity_name in .class, if True return list of possible entry points"""
        activity_full = self.activities[activity_name] + '.' + activity_name
        entry_points = []
        mth_class = activity_full
        if mth_class not in self.classes.keys(): return (False, entry_points)
        
        for mth_sig in self.classes[mth_class]:
                param_list = mth_sig[mth_sig.find('(')+1:mth_sig.rfind(')')].strip().split(',')
                param_list = param_list.remove('') if '' in param_list else param_list #param list

                method_name = mth_sig.split('(')[0].split()[-1].replace(';','')[:mth_sig.find('(')+1].replace('(','').replace(')','') #entry point
                return_type = mth_sig.split()[-2]
                class_name = mth_class.split('.')[-1]  #activity class
                package_name = mth_class[:mth_class.rfind('.')]
                
                if method_name in self.ENTRY.keys() and method_name not in entry_points:
                    entry_points.append(method_name)
        if 'onCreate' not in entry_points: entry_points.append('onCreate')
        if 'onStart' not in entry_points: entry_points.append('onStart')

        return (True, entry_points)

    def reset_dot(self):
        for file in Path(self.HYBRIDSE_DIR).glob("*.dot"):
            file.unlink()

    def get_dot(self, apk_name):
        for file in Path(self.HYBRIDSE_DIR).glob("*.dot"):
            Path(self.RESULT_DIR + '/' + apk_name).mkdir(parents=True, exist_ok=True)
            shutil.copy(file, self.RESULT_DIR + '/' + apk_name)

    def remove_file(self):  
        path = Path('./temp/java_projects/').glob('*')
        
        for file in (pbar:=tqdm(list(path))):
            Path(str(file) + '/src/android.jar').unlink(missing_ok=True)

    def __clear_path(self, classdict):
        for k in classdict:
            classdict[k] = (classdict[k][:-1] if classdict[k][-1] =='.' else classdict[k]).replace("..",".")
        return classdict
    
    @deprecated(reason="unused")
    def __find_native(self, folder) -> str:
        for native_file in Path(folder).rglob("*.so"):
            if native_file.parent.name == 'armeabi' or native_file.parent.name == 'armeabi_v7a':
                return 'lib/' + native_file.parent.name + '/' + native_file.name
        return ''
    
    def __find_natives(self, folder) -> list:
        so_list = []
        for native_file in Path(folder).rglob("*.so"):
            if native_file.parent.name == 'assets':
                if 'x86' not in native_file.name.lower() and 'mips' not in native_file.name.lower():
                    so_list.append(native_file.parent.name + '/' + native_file.name)
            elif native_file.parent.name == 'armeabi' or native_file.parent.name == 'armeabi_v7a':
                if 'x86' not in native_file.name.lower() and 'mips' not in native_file.name.lower():
                    if len(so_list) == 0:
                        so_list.append('lib/' + native_file.parent.name + '/' + native_file.name)
        return so_list
    
    def __random(type_list) -> list:
        res = []
        for t in type_list:
            if 'String' in t:
                res.append("\""+''.join(random.choices(string.ascii_lowercase, k=10))+"\"")
            elif 'FileDescriptor' in t:
                res.append("new FileDescriptor()")
            elif 'Integer' in t:
                res.append(str(random.randint(0, 100)))
            elif 'int' in t and '[]' not in t:
                res.append(str(random.randint(0, 100)))
            elif 'int' in t and '[]' in t:
                res.append('intArray')
            else:
                res.append('null')        
        return res
    
    def __random_sym(type_list) -> list:
        res = []
        for t in type_list:
            if 'String' in t:
                res.append("sym")
            elif 'Integer' in t:
                res.append("#sym")
            elif 'int' in t:
                res.append("sym")
            else:
                res.append("sym") 
        return res

if __name__ == "__main__":            
    gen = Analysis()
    Analysis.build()

    df = pd.read_csv(Analysis.FAMILY_CSV, delimiter=',',usecols=['sha256','family'])
    df = df.reindex(columns = ['sha256','family', 'bytecode','arm','API','time'])
    sample_dir = './temp/java_projects_01'
    save_file = './data/result_180523.csv'
    path = Path(sample_dir).glob("*")

    df_save = pd.read_csv(save_file, delimiter=',',usecols=['sha256', 'java', 'native', 'lib', 'time'])

    for file in (pbar:=tqdm(list(path))):
        pbar.set_postfix_str(file.name)
        apk_name = file.name
        gen = Analysis()
        if apk_name in df_save.sha256:
            print("Already processed")
            continue

        gen.reset_dot()
        start = time.time()
        gen.startAtEntryPoints(str(file.absolute()))
        exe_time = time.time() - start
        df.loc[df.sha256 == apk_name, ['bytecode','arm','API']] = list(gen.stat)
        df.loc[df.sha256 == apk_name, 'time'] = exe_time
        logger.info(msg=gen.stat)
        logger.info(msg=df.loc[df.sha256 == apk_name, 'time'])
        gen.get_dot(apk_name)
        with open('./data/result.csv', 'a') as file:
            line = apk_name+ ', ' + ','.join([str(x) for x in gen.stat]) + ', ' + str(exe_time) + '\n'
            file.write(line)
    df.to_csv('./run_stat.csv')
