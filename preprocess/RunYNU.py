from multiprocessing import Pool, freeze_support, RLock
from tqdm import tqdm
from pathlib import Path
import subprocess as sp
import xml.etree.ElementTree as ET
from APKReader import APKReader
from TemplateGen import TemplateGen
import shutil
import time

def split_chunk(full, n):
    (chunk_size, mod_size) = divmod(len(full), n) 
    chunk_sizes = [chunk_size for i in range(n-1)] + [chunk_size+mod_size]
    i = 0
    for k_size in chunk_sizes:
            yield full[i: i + k_size]
            i = i + k_size

def run_apk_tool():
    #args = sys.argv[1:]
    #amd_dataset = "/home/va/Projects/transfer_5552318"
    amd_dataset = "/home/va/Projects/amd_data/"
    
    filtered = "/home/va/Projects/ynu_small"
    Path(filtered).mkdir(parents=True, exist_ok=True)
    
    sample_list = []
    for family in Path(amd_dataset).glob("*/variety*"):
        for apk in Path(family).glob("*.apk"):
              sample_list.append(apk)
              shutil.copy(apk, filtered)
              break
    # for apk in Path(amd_dataset).rglob("*.apk"):
    #     sample_list.append(apk)
    
    n_processes = 4
    freeze_support()
    apkReader = APKReader()        
    file_chunks = APKReader.split_chunk(sample_list, n_processes)

    pool = Pool(n_processes, initializer=tqdm.set_lock, initargs=(tqdm.get_lock(),))
        
    results = pool.starmap(apkReader.func, [(batch, idx) for idx, batch in enumerate(file_chunks)])
    print(apkReader.JAVA_PROJECT)

def gen_CFG():
    java_proj = Path("temp_20230813_162225/java_projects").glob("*") 
    for file in (pbar:=tqdm(list(java_proj))):
        pbar.set_postfix_str(file.name)
        apk_name = file.name
        if apk_name == '4c81a3ba4e434d248a8fad9c3b4bc333':
            gen = TemplateGen()

            gen.reset_dot()
            start = time.time()
            gen.runEntryPoints(str(file.absolute()))
            exe_time = time.time() - start
        
            gen.get_dot(apk_name)
            with open('./data/result_1308.csv', 'a') as file:
                line = apk_name+ ', ' + ','.join([str(x) for x in gen.stat]) + ', ' + str(exe_time) + '\n'
                file.write(line)

def run_nativebench():
    java_proj = Path("temp_20231116_142929/java_projects").glob("*") 
    for file in (pbar:=tqdm(list(java_proj))):
        pbar.set_postfix_str(file.name)
        apk_name = file.name
        if apk_name == 'native_set_field_from_native':
            gen = TemplateGen()

            gen.reset_dot()
            start = time.time()
            
            gen.runEntryPoints(str(file.absolute()))
            exe_time = time.time() - start
        
            gen.get_dot(apk_name)
            
if __name__ == "__main__": 
   #gen_CFG()
   run_nativebench()
