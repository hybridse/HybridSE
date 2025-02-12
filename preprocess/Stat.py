import pandas as pd
import time
from pathlib import Path
import sys
import pickle
import networkx as nx
import subprocess as sp
import collections
import numpy as np
from APKReader import APKReader
from TemplateGen import TemplateGen

class Stat:
    JAVA_PROJECT="./temp/java_projects"
    FAMILY_CSV="./data/sha256_family.csv"
    DOT_DIR = "./temp/result"

    def __init__(self, project_dir = None, dot_dir=None) -> None:
        self.DOT_DIR = dot_dir
        self.JAVA_PROJECT = project_dir
    
    def load_csv(csv_file):
        df = pd.read_csv(csv_file, delimiter=',',usecols=['sha256','family'])
        family_dict = dict(zip(df.sha256, df.family))
        print(family_dict)
        print(df['family'].unique())
        return family_dict

    def load_projects(self):
        df = pd.read_csv(self.FAMILY_CSV, delimiter=',',usecols=['sha256','family'])
        native_count = [0]*len(df)
        df['nativeN'] = native_count
        
        print(len(df))
        #native_df = pd.DataFrame(columns=['sha256','family','native_count'])
        print("All families: ", len(df.family.unique()))
        for file in list(Path(self.JAVA_PROJECT).glob("*")) + list(Path(self.JAVA_PROJECT + '_01').glob("*")):
            apk_name = file.name
            native_mth = None
            
            native_pkl = str(file) + '/method_dict.pkl'
            if Path(native_pkl).exists():
                with open(native_pkl, 'rb') as handle: 
                    native_classes = pickle.load(handle)
                    native_mth = [mth_sig for mth_sig in [native_classes[mth_class] for mth_class in native_classes]]
            
            native_count = len(native_mth) if native_mth is not None else 0
            df.loc[df.sha256 == apk_name, 'nativeN'] = native_count
            
        print('Project with native functions', len(df.loc[df.nativeN != 0]))
        print(len(df.loc[df.nativeN != 0].family.unique())) 
        native_families = df.loc[df.nativeN != 0].family.unique().tolist()
        for nativef in native_families:
            print(nativef, len(df[df.family == nativef]), len(df.loc[(df.family == nativef) & (df.nativeN != 0)]))
        # for nf in native_familes:
        #     print(df.loc[(df.family == nf) & (df.nativeN != 0)].iloc[0].sha256)
        df.to_csv('./data/sha256_native.csv')
            #family_df.loc[len(native_df)] = sample_stat
    
    def load_dot_drebin(self):
        df = pd.read_csv(self.FAMILY_CSV, delimiter=',',usecols=['sha256','family'])
        for file in Path(self.DOT_DIR).glob("*"):
            apk_name = file.stem
            print(apk_name)
            print(df[df.sha256 == apk_name].family)

            asm = []
            executed_node = []
            for dotfile in Path(self.DOT_DIR + '/' + apk_name).glob("*.dot"):
                short_func_name = dotfile.stem.split('.')[-1].replace('so_','')
                sofile_name = dotfile.stem.replace( '_' + short_func_name, '')
                full_name = ''
                sofile = self.JAVA_PROJECT + '/' + apk_name + '/armeabi/' + sofile_name
                symtbl_cmd= "objdump -t {} | grep \"{}\" | cut -f2".format(sofile, short_func_name)
                
                symp = sp.run(symtbl_cmd, shell=True, capture_output=True, text=True)
                
                full_name = symp.stdout.split()[-1] if len(symp.stdout.split()) > 0 else short_func_name
        
                run_cmd = "objdump -d {} | awk -v RS= '/^[[:xdigit:]]+ <{}>/' | cut -f1".\
                        format(sofile, full_name)
                se_run = sp.run(run_cmd, shell=True, capture_output=True, text=True)
                asm = asm + [x.strip() for x in se_run.stdout.replace(':', '').split('\n') if x != ''][1:]
                G = nx.Graph(nx.nx_pydot.read_dot(str(dotfile)))
                
                executed_node = executed_node + [x.split('_')[0] for x in G.nodes]
            
            c = collections.Counter(asm)
            c.subtract(executed_node)
            if (len(asm) > 0):
                print(1-c.total()/len(asm))
                #print(asm)
            #break
        # objdump -d libnative.so | awk -v RS= '/^[[:xdigit:]]+ <Java_uk_co_lilhermit_android_core_Native_runcmd>/' | cut -f1

    def load_dot(self, filepath):
        apk_name = Path(filepath).stem
        print(apk_name)

        asm = []
        executed_node = []
        for dotfile in Path(self.DOT_DIR + '/' + apk_name).glob("*.dot"):
            print(dotfile)
            short_func_name = dotfile.stem.split('.')[-1].replace('so_','')
            sofile_name = dotfile.stem.replace( '_' + short_func_name, '')
            full_name = ''
            sofile = self.JAVA_PROJECT + '/' + apk_name + '/armeabi/' + sofile_name
            symtbl_cmd= "nm -gD {} | grep \"{}\" | cut -f2".format(sofile, short_func_name)
            
            symp = sp.run(symtbl_cmd, shell=True, capture_output=True, text=True)
            
            full_name = symp.stdout.split()[-1] if len(symp.stdout.split()) > 0 else short_func_name

            #run_cmd = "objdump -d {} | awk -v RS= '/^[[:xdigit:]]+ <{}>/' | cut -f1".\
            #         format(sofile, full_name)
            run_cmd = "objdump -d  -j .text {}| cut -f1".\
                     format(sofile, full_name)
            se_run = sp.run(run_cmd, shell=True, capture_output=True, text=True)
            asm = asm + [x.strip() for x in se_run.stdout.replace(':', '').split('\n') if x != ''][1:]
            G = nx.Graph(nx.nx_pydot.read_dot(str(dotfile)))
            executed_node = executed_node + [x.split('_')[0] for x in G.nodes]
           
            print("Executed code percentage")    
            c = collections.Counter(asm)
            c.subtract(executed_node)

            if (len(asm) > 0):
                print((1-c.total()/len(asm))*100)
        
            
    def stat_result(self, result_file):
        df_res = pd.read_csv(result_file, delimiter=',',usecols=['sha256','java', 'native', 'lib', 'time'])
        family = np.nan*len(df_res)
        df_res['family'] = family
        
        df_family = pd.read_csv(self.FAMILY_CSV, delimiter=',',usecols=['sha256','family'])

        for i, row in df_res.iterrows():
            apk = df_res.at[i,'sha256'].strip()
            if not df_family[df_family['sha256'] == apk].empty:
                ifor_val = df_family[df_family['sha256'] == apk].iloc[0].family
                df_res.at[i,'family'] = ifor_val
        
        df_res.to_csv("./temp/res_family.csv")
        # for fi in df_res.family.unique().tolist():
        #     print(fi, len(df_res[df_res['family'] == fi]))

#Stat.load_csv("./data/sha256_family.csv")
#Stat().load_projects()
#Stat().load_dot("temp_6b4f02e7f177470d8587c8520bcb3c0b/results/towelroot")
#Stat().stat_result("./data/result.csv")

#nm -gD ./temp_6b4f02e7f177470d8587c8520bcb3c0b/java_projects/towelroot/armeabi/libexploit.so | grep "javaSucksAssReadTheKernelVersion" | cut -f2
if __name__ == "__main__":
    args = sys.argv[1:] 
    file_path = args[0]
    if not Path(file_path).exists:
        print("APK file does not exists!")
        exit
    start = time.time()
    apk_name = Path(file_path).stem
    out_folder = APKReader().analyse_file(file_path)
    gen = TemplateGen()
    TemplateGen.build()
    TemplateGen.HYBRIDSE_DIR='.'
    gen.runActivities(out_folder + '/' + apk_name)
    project_fld = out_folder + '/java_projects'
    print(gen.stat)
    print(time.time() - start)