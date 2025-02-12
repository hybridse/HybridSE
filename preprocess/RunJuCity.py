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
import pandas
import io
import time
import logging

logger = logging.getLogger()
handler = logging.StreamHandler(stream=sys.stdout) 
logger.addHandler(handler)
logger.setLevel(logging.DEBUG)

class APKReader:
    DEX2JAR_DIR = "~/git/dex2jar/dex-tools/build/distributions/dex2jar-0.0.9.15"
    #DEX2JAR_DIR = "~/git/dex2jar/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT"
    TEMP="./temp_" + time.strftime("%Y%m%d_%H%M%S")
    APKTOOL_FDR= TEMP+ "/apktool_folder"
    DEX2JAR_INP= TEMP+ "/dex2jar_input"
    DEX2JAR_OUT= TEMP+ "/dex2jar_output"
    JAVA_PROJECT=TEMP+ "/java_projects" 
    BASE_PROJECT="./data/base_project"
    CSV_FILE="transfer_0507.csv"

    ##
    # Iterate functions
    ##
    def analyse_file(self, file_path):
        path = Path(file_path)
        self.run_apktool(path)
        return self.JAVA_PROJECT
    
    def analyse_dir_multiproc(self, dir_path):
        n_processes = 5
        freeze_support()
        file_list = list(Path(dir_path).rglob("*/*.apk"))
        file_chunks = APKReader.split_chunk(file_list, n_processes)

        pool = Pool(n_processes, initializer=tqdm.set_lock, initargs=(tqdm.get_lock(),))
        
        results = pool.starmap(self.func, [(batch, idx) for idx, batch in enumerate(file_chunks)])
        print("DONE.")
        return self.JAVA_PROJECT 

    def analyse_jucity(self, dir_path):
        with_native = []
        csv_path = "/home/va/Projects/JuCity-env/AMD_dataset.csv"
        df = pandas.read_csv(csv_path)
        for index, row in df.iterrows():
            if row[2] == 'yes':
                with_native.append(row[0])
        

        file_list = list(Path(dir_path).rglob("*.apk"))
        for file in (pbar:=tqdm(file_list)):
                if file.stem in with_native:
                    with open('./jucify_out/out_' + file.stem +'.txt', 'a') as f:
                        start = time.time()
                        f.write("#" + file.name + '\n')
                        cmd = "cd /home/va/git/JuCify/scripts/ && ./main.sh -f {} -p /home/va/Android/Sdk/platforms".format(file)
                        p = sp.run(cmd, shell=True, capture_output=True, text=True)
                        
                        result_str = p.stdout
                        result_str[result_str.find("Results:"):]
                        for line in result_str.split('\n'):
                            if line.strip().startswith('-'):
                                f.write(line + '\n')
                        f.write("T:" + str(time.time()-start) + '\n')  
                        f.close()
                    #print(p.stderr)
              
                

if __name__ == "__main__": 
    args = sys.argv[1:]
    #APKReader().analyse_dir(args[0])  #args[0] is path to an APK file
    APKReader().analyse_jucity(args[0])