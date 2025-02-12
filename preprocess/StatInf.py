import pickle
from pathlib import Path
from tqdm import tqdm
import re 
from TemplateGen import TemplateGen
import subprocess as sp
from APKReader import APKReader

class StatNative:
    arg_types = {}

    def __unpickle(self, sample_dir):
        with open(sample_dir + '/method_dict.pkl', 'rb') as handle:
            classes = pickle.load(handle)
            #print(classes)
            for ck in classes:
                for mthSignature in classes[ck]:
                    mth = mthSignature
                    args_search = re.search('\((.*)\)', mth)
                    if args_search is not None:
                        print(mth)
                        #valid method signature
                        args = args_search.group(1).split(',')
                        args = [a.strip() for a in args]
                        if len(args) == 0:
                            self.__count_type('void')
                        else:
                            [self.__count_type(s) for s in args]
            

    def find_async(self, sample_dir):
            pass
        
    def __count_type(self, t_name):
        if t_name in self.arg_types.keys():
            self.arg_types[t_name] += 1
        else:
            self.arg_types[t_name] = 1


    def run_SE(self, project_path):
        java_proj = Path(project_path).glob("*")  
        
        for file in list(java_proj):
            apk_name = file.name
            print(apk_name)
            gen = TemplateGen()
            #gen.parseXML(str(file.absolute()))
            gen.runEntryPoints(str(file.absolute()))
                
        # sorted_args = dict(sorted(self.arg_types.items(), key=lambda x:x[1], reverse=True))
        # print(sorted_args)
    
    def arg_stat(self):
        java_proj = Path('temp_20230821_170727/java_projects').glob("*")  
        for file in list(java_proj):
            apk_name = file.name
            if apk_name == 'towelroot':
                self.__unpickle(str(file))
                
        sorted_args = dict(sorted(self.arg_types.items(), key=lambda x:x[1], reverse=True))
        print(sorted_args)

#proj_path = APKReader().analyse_dir('/home/va/Projects/NativeFlowBench')
StatNative().run_SE("/home/va/git/DroiDSE/temp_20240207_135440/java_projects")
#StatNative().arg_stat() 