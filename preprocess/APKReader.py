from multiprocessing import Pool, freeze_support, RLock, current_process
from tqdm import tqdm
from pathlib import Path
import subprocess as sp
import shutil
import sys
import traceback
import xml.etree.ElementTree as ET
import zipfile
import pickle
import time
import re
import logging
from CHA import generate_dot

logger = logging.getLogger(__name__)
file_handler = logging.FileHandler(filename="logfile_" + time.strftime("%Y%m%d_%H%M%S") + ".log",mode = "w")
handler = logging.StreamHandler(stream=sys.stdout) 
logger.addHandler(file_handler)
logger.setLevel(logging.INFO)

class APKReader:
    ROOT_DIR = str(Path().resolve().parent)
    
    PROJ_DIR = ROOT_DIR + "/HybridSE"
    DEX2JAR_DIR = ROOT_DIR + "/dex2jar/dex-tools/build/distributions/dex2jar-0.0.9.15"
    DEX2JAR_DIR_2 = ROOT_DIR + "/dex2jar/dex-tools/build/distributions/dex-tools-2.1-SNAPSHOT"
    
    stamp = time.strftime("%Y%m%d_%H%M%S")
    BASE_PROJECT= PROJ_DIR + "/data/base_project"
    RESULT_FOLDER= ROOT_DIR + "/hybridSE_output/result_" + stamp
    
    APKTOOL_FDR= RESULT_FOLDER + "/apktool_folder"
    DEX2JAR_INP= RESULT_FOLDER + "/dex2jar_input"
    DEX2JAR_OUT= RESULT_FOLDER + "/dex2jar_output"
    JAVA_PROJECT= RESULT_FOLDER 
    
    CSV_FILE= "{}/apk_{}.csv".format(RESULT_FOLDER, stamp)
    APK_NAME = ""
    no_debug = False
    
    def __init__(self) -> None:
        Path(self.RESULT_FOLDER).mkdir(parents=True, exist_ok=True)
        Path(self.APKTOOL_FDR).mkdir(parents=True, exist_ok=True)
        Path(self.DEX2JAR_INP).mkdir(parents=True, exist_ok=True)
        Path(self.DEX2JAR_OUT).mkdir(parents=True, exist_ok=True)

    def run_apktool(self, apk_fpath, clean=True):
        """
        Run preprocessing tools: apktool + dex2jar + javap 
        :param apk_fpath - path to apk file
        :param clean - remove jar file after preprocessing
        """
        file_path = Path(apk_fpath)
        apk_name = file_path.stem
        self.APK_NAME = apk_name
        #family_name = str(file_path.parent).split('/')[-2]

        if Path(self.JAVA_PROJECT + '/' +apk_name).exists():
            logger.debug(msg="Already processed!")
            return
        try:
            # Check if exist
            if not file_path.exists():
                print("No APK file found.")
                return
            # APKTOOL
            decode_cmd = "apktool decode {} -f -o {}/{}.out".format(file_path.resolve(), self.APKTOOL_FDR, apk_name)
            sp.run(decode_cmd, shell=True, capture_output=self.no_debug, text=self.no_debug)
            
            rebuild_cmd = "apktool build --debug -f {}/{}.out -o {}/{}".format(self.APKTOOL_FDR, apk_name, self.DEX2JAR_INP, apk_name)
            sp.run(rebuild_cmd, shell=True, capture_output=self.no_debug, text=self.no_debug)
            
            # Check if MultiDEX
            dex_files = list(Path("{}/{}.out".format(self.APKTOOL_FDR, apk_name)).rglob("*.dex"))
            dex_count = len(dex_files)
            
            # DEX2JAR
            # d2j_cmd = "{}/d2j-dex2jar.sh -f -d {}/{} -o {}/{}.jar" \
            #             .format(self.DEX2JAR_DIR, self.DEX2JAR_INP, apk_name, self.DEX2JAR_OUT, apk_name)
            #sp.run(d2j_cmd,  shell=True, capture_output=self.no_debug, text=self.no_debug)
            #logger.debug(msg=d2j_cmd)

            # retry w/ DEX2JAR 2.1
            if not Path("{}/{}.jar".format(self.DEX2JAR_OUT, apk_name)).exists():
                d2j_cmd = "{}/d2j-dex2jar.sh -f -d {}/{} -o {}/{}.jar" \
                        .format(self.DEX2JAR_DIR_2, self.DEX2JAR_INP, apk_name, self.DEX2JAR_OUT, apk_name)
                sp.run(d2j_cmd,  shell=True, capture_output=self.no_debug, text=self.no_debug)
                logger.debug(msg=d2j_cmd)
                print('TRY WITH DEX2JAR-2.1.')

            findnative_cmd = "jar tf {}/{}.jar | grep '.class$' | tr / . | sed 's/\.class$//'| xargs javap -protected -cp {}/{}.jar | grep ' native '" \
                        .format(self.DEX2JAR_OUT, apk_name, self.DEX2JAR_OUT, apk_name)
            findnative_p = sp.run(findnative_cmd, shell=True, capture_output=True, text=True)
            native_count = findnative_p.stdout.strip().count(' native ')
            logger.debug(msg="Native functions in " + apk_name + " " + str(native_count))

            # Count stub
            findstub_cmd = "jar tf {}/{}.jar | grep '.class$' | tr / . | sed 's/\.class$//' | grep 'StubApp'" \
                        .format(self.DEX2JAR_OUT, apk_name, self.DEX2JAR_OUT, apk_name)
            findstub_p = sp.run(findstub_cmd, shell=True, capture_output=True, text=True)
            stub_count = findstub_p.stdout.strip().count('stub')
            logger.debug(msg="Stub application " + apk_name + " " + str(stub_count))

            # Count .so files, only support armeabi-v7a and armeabi
            arm_v7_path = self.APKTOOL_FDR+'/'+apk_name+'.out/lib/armeabi-v7a'
            arm_eabi_path = self.APKTOOL_FDR+'/'+apk_name+'.out/lib/armeabi'
            libpath = Path(arm_v7_path) if Path(arm_v7_path).exists() \
                else (Path(arm_eabi_path) if Path(arm_eabi_path).exists() else None)
            so_files = list(libpath.rglob("*.so")) if libpath is not None else []
            so_file_string = '[' + '; '.join([str(x.stem) for x in so_files]) + ']'

            # Find .so file in assets
            assets_path = "{}/{}.out/assets".format(self.APKTOOL_FDR, apk_name)
            
            # Save result line to write CSV 
            with open(self.CSV_FILE, 'a') as file:
                in_lib = 'yes' if Path(self.APKTOOL_FDR+'/'+apk_name+'.out/lib/').exists() else 'no'
                apk_success = 'yes' if Path("{}/{}.out".format(self.APKTOOL_FDR, apk_name)).exists() else 'no'
                d2j_success = 'yes' if Path("{}/{}.jar".format(self.DEX2JAR_OUT, apk_name)).exists() else 'no'
                so_in_assets = 'yes' if len(list(Path(assets_path).rglob("*.so"))) > 0 else 'no'

            #line = apk_name+ ', ' + str(native_count) + ', ' + in_lib + ', ' + family_name + '\n'
                line = apk_name+ ', ' + str(native_count) + ', ' + in_lib + ', ' + str(dex_count) + ', ' + \
                    str(stub_count) + ', ' + so_in_assets + ', ' + apk_success + ', ' + d2j_success + ', ' + so_file_string + '\n'
                file.write(line)

            # Copy base project when /lib folder exists
            if native_count > 0 and Path(self.APKTOOL_FDR+'/'+apk_name+'.out/lib/').exists():
            #if Path(self.APKTOOL_FDR+'/'+apk_name+'.out/lib/').exists():
              
                target_project = self.JAVA_PROJECT+'/' + apk_name
        
                with zipfile.ZipFile(self.DEX2JAR_OUT + '/' + apk_name + '.jar', 'r') as zip_ref:
                    zip_ref.extractall(target_project + '/src')
               
                shutil.copy(self.APKTOOL_FDR + '/' + apk_name + '.out/AndroidManifest.xml', target_project)
                shutil.copytree(self.APKTOOL_FDR +'/'+ apk_name + '.out/lib/', target_project + "/lib", dirs_exist_ok=True)
                if so_in_assets == 'yes':
                    shutil.copytree(self.APKTOOL_FDR +'/'+ apk_name + '.out/assets/', target_project + "/assets", dirs_exist_ok=True)
                
                # Write all method name
                classes={}
                findclass_cmd = "jar tf {}/{}.jar | grep '.class$' | tr / . | sed 's/\.class$//'".format(self.DEX2JAR_OUT, apk_name)
                
                find_cmd = "jar tf {}/{}.jar | grep '.class$' | tr / . | sed 's/\.class$//'| xargs javap -protected -cp {}/{}.jar" \
                        .format(self.DEX2JAR_OUT, apk_name, self.DEX2JAR_OUT, apk_name)
                find_p = sp.run(find_cmd, shell=True, capture_output=True, text=True)
                find_out = find_p.stdout.split('}')
                class_contents = [x + "}" for x in find_out]
                classes = self.__parse_javap_output(class_contents)

                with open(target_project + '/method_dict.pkl', 'wb') as handle:
                    pickle.dump(classes, handle)
                print('DONE UNPACKING APK.')
            else:
                
                target_project = self.JAVA_PROJECT+'/' + apk_name
        
                with zipfile.ZipFile(self.DEX2JAR_OUT + '/' + apk_name + '.jar', 'r') as zip_ref:
                    zip_ref.extractall(target_project + '/src')
            
                shutil.copy(self.APKTOOL_FDR + '/' + apk_name + '.out/AndroidManifest.xml', target_project)
                if so_in_assets == 'yes':
                    shutil.copytree(self.APKTOOL_FDR +'/'+ apk_name + '.out/assets/', target_project + "/assets", dirs_exist_ok=True)
                
                # Write all method name
                classes={}
                findclass_cmd = "jar tf {}/{}.jar | grep '.class$' | tr / . | sed 's/\.class$//'".format(self.DEX2JAR_OUT, apk_name)
                find_cmd = "jar tf {}/{}.jar | grep '.class$' | tr / . | sed 's/\.class$//'| xargs javap -protected -cp {}/{}.jar" \
                        .format(self.DEX2JAR_OUT, apk_name, self.DEX2JAR_OUT, apk_name)
                find_p = sp.run(find_cmd, shell=True, capture_output=True, text=True)
                find_out = find_p.stdout.split('}')
                class_contents = [x + "}" for x in find_out]
                classes = self.__parse_javap_output(class_contents)

                with open(target_project + '/method_dict.pkl', 'wb') as handle:
                    pickle.dump(classes, handle)
                print('DONE UNPACKING APK.')

            # CHA
            jarfile = self.DEX2JAR_OUT + "/" + apk_name + ".jar"
            dotfile = self.JAVA_PROJECT + "/" + apk_name + '/callgraph.dot'
            txt_file = self.JAVA_PROJECT + "/" + apk_name + "/callgraph.txt"
            cha_cmd = "java -jar lib/javacg-0.1-SNAPSHOT-static.jar {} > {}" \
                                    .format(jarfile, txt_file)
            sp.run(cha_cmd, shell=True, capture_output=True, text=True)
            generate_dot(txt_file, dotfile)
            print('DONE Class Hierarchy Analysis.')

            # Count native functions
            # RESULT_FOLDER 2025/01/04 Commented out for testing
            shutil.rmtree(self.APKTOOL_FDR + '/' + apk_name + '.out',  ignore_errors=True)
            Path(self.DEX2JAR_INP + '/' + apk_name).unlink(missing_ok=True)
            shutil.rmtree(self.JAVA_PROJECT + '/' + apk_name + '/src', ignore_errors=True)
            shutil.rmtree(self.JAVA_PROJECT + '/' + apk_name + '/lib', ignore_errors=True)
        
            if clean:    
                Path(self.DEX2JAR_OUT + '/' + apk_name + '.jar').unlink(missing_ok=True)
                
        except Exception :
            print(traceback.format_exc())

    def __parse_javap_output(self, class_contents_in):
        class_out = {}
        classes = {}         
        classes['asyncTask'] = []
        classes['Thread'] = []
        classes['Runnable'] = []

        for cls in class_contents_in:
            # Regex for Java classname
            m = re.search("((?<=\n)|(?<=\A))?(?:public\s)?(?:final\s)?(class|interface|enum)\s([^\n\s]*)", cls.strip())
            if m is not None:
                class_name = m.group(3)
                mth_content = cls.strip()[cls.strip().index('{', 0) + 1 : -1].strip()
                classes[class_name] = []
       
                if 'activity' in cls.lower().strip() or ' native ' in cls.strip():
                    for line in mth_content.splitlines():
                        if ' native ' in line:
                            classes[class_name].append(line.strip())
                        if 'public' in line or 'protected' in line:
                            # filter public methods
                            if len(line.split()) > 2:
                                classes[class_name].append(line.strip())
                elif 'java.lang.Thread' in cls.strip():
                    classes['Thread'].append(class_name)

                elif 'android.os.AsyncTask' in cls.strip():
                    classes['asyncTask'].append(class_name)
                
                elif 'java.lang.Runnable' in cls.strip():
                    classes['Runnable'].append(class_name)
                else:
                    for line in mth_content.splitlines():
                        if 'public' in line or 'protected' in line:
                            # filter public methods
                            if len(line.split()) > 2:
                                classes[class_name].append(line.strip())
        for cls in classes:
            #if not len(classes[cls]) == 0 or cls == 'asyncTask':
                class_out[cls] = classes[cls]
        return class_out
    ## 
    # Util functions
    ##
    def __check_lib(self, apk_fpath):
        file_path = Path(apk_fpath)
        apk_name = file_path.stem
        count = 0
        print(file_path)

        if Path(self.JAVA_PROJECT + '/' +apk_name).exists(): 
            print("Already processed!")
            count = 1
        try:
            decode_cmd = "apktool decode {} -f -o {}/{}.out".format(file_path.resolve(), self.APKTOOL_FDR, apk_name)
            sp.run(decode_cmd, shell=True)
            rebuild_cmd = "apktool build --debug -f {}/{}.out -o {}/{}".format(self.APKTOOL_FDR, apk_name, self.DEX2JAR_INP, apk_name)
            sp.run(rebuild_cmd, shell=True)
            
            if Path(self.APKTOOL_FDR+'/'+apk_name+'.out/lib/armeabi').exists():
                count = 1
        except Exception :
            print(Exception)

        if count > 0:
            with open(self.CSV_FILE, 'a') as file:
                line = apk_name+ '\n'
                file.write(line)
        shutil.rmtree(self.APKTOOL_FDR + '/' + apk_name + '.out',  ignore_errors=True)
        return count

    def count_lib(self, dir_path):
        count = 0
        path = Path(dir_path).rglob("*/*")
        for file in (pbar:=tqdm(list(path))):
            count = count + self.__check_lib(file)
        print(count)

    def func(self, batch, tqdm_index):
        with tqdm(batch, position=tqdm_index) as progress:
            for file in progress:
                progress.set_postfix_str(file.stem)
                self.run_apktool(file)

    def split_chunk(full, n):
        (chunk_size, mod_size) = divmod(len(full), n) 
        chunk_sizes = [chunk_size for i in range(n-1)] + [chunk_size+mod_size]
        i = 0
        for k_size in chunk_sizes:
            yield full[i: i + k_size]
            i = i + k_size

    ##
    # Iterate functions
    ##
    def analyse_file(self, file_path, is_clean=True):
        path = Path(file_path)
        self.run_apktool(path, is_clean)
        return self.JAVA_PROJECT + '/' + self.APK_NAME
    
    def analyse_dir_multiproc(self, dir_path):
        n_processes = 5
        freeze_support()
        file_list = list(Path(dir_path).rglob("*/*.apk"))
        file_chunks = APKReader.split_chunk(file_list, n_processes)
        pool = Pool(n_processes, initializer=tqdm.set_lock, initargs=(tqdm.get_lock(),))
        
        results = pool.starmap(self.func, [(batch, idx) for idx, batch in enumerate(file_chunks)])
        print("DONE.")
        return self.JAVA_PROJECT

    def analyse_dir(self, dir_path):
        file_list = list(Path(dir_path).rglob("*.apk"))
        for file in (pbar:=tqdm(file_list)):
            self.run_apktool(file)
        print("DONE.")
        return self.JAVA_PROJECT

if __name__ == "__main__": 
    args = sys.argv[1:]
    APKReader().analyse_dir(args[0])  #args[0] is path to an APK file