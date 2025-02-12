from multiprocessing import Pool, freeze_support, RLock, current_process
from tqdm import tqdm
from pathlib import Path
import subprocess as sp
import xml.etree.ElementTree as ET
from APKReader import APKReader
from TemplateGen import TemplateGen
import shutil
import os
import time

def split_chunk(full, n):
    (chunk_size, mod_size) = divmod(len(full), n) 
    chunk_sizes = [chunk_size for i in range(n-1)] + [chunk_size+mod_size]
    i = 0
    for k_size in chunk_sizes:
            yield full[i: i + k_size]
            i = i + k_size

def gen_CFG():
    java_proj = Path("temp_20230813_163815/java_projects").glob("*")  
    for file in (pbar:=tqdm(list(java_proj))):
        pbar.set_postfix_str(file.name)
        apk_name = file.name
        gen = TemplateGen()

        gen.reset_dot()
        start = time.time()
        gen.runActivities(str(file.absolute()))
        exe_time = time.time() - start
       
        gen.get_dot(apk_name)
        with open('./data/result_1308.csv', 'a') as file:
            line = apk_name+ ', ' + ','.join([str(x) for x in gen.stat]) + ', ' + str(exe_time) + '\n'
            file.write(line)

if __name__ == "__main__": 
   gen_CFG()




