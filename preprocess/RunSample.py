import pickle
from multiprocessing import Pool, freeze_support, RLock, current_process
from pathlib import Path
from tqdm import tqdm
import re 
from TemplateGen import TemplateGen
from CreateDot import todot, find_leak, todot_native
import subprocess as sp
from APKReader import APKReader
import sys
import time
import argparse
import pandas as pd
import shutil

class RunSample:
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

    def run_SE_dir(self, project_path):
        java_proj = Path(project_path).glob("*")  
        
        for file in list(java_proj):
            apk_name = file.name
            print(apk_name)
            gen = TemplateGen()
            #gen.parseXML(str(file.absolute()))
            gen.runEntryPoints(str(file.absolute()))
            
    
    def single_proc(self, batch, tqdm_index, stamp=None):
        id = '_' + stamp if stamp is not None else ''
    
        with tqdm(batch, position=tqdm_index) as progress:
            for file in progress:
                progress.set_postfix_str(file.stem)
                start = time.time()
                apk_proj_path = APKReader().analyse_file(file)
                
                print('DONE DEX2JAR.')
                self.run_SE_file(apk_proj_path)
                exe_time = time.time() - start
                print('DONE CFG in', exe_time , '(s)')
                self.analyse_output(apk_proj_path)

                with open(APKReader.TEMP + '/time' + id + '.csv', 'a') as csv_file:
                    line = file.stem + ', ' + str(exe_time) + '\n'
                    csv_file.write(line)
    
    def run_SE_file(self, project_path):
        file = Path(project_path)
        if file.exists():   
            gen = TemplateGen()
            gen.runEntryPoints(str(file.absolute()))

    def __split_chunk(self, full, n):
        (chunk_size, mod_size) = divmod(len(full), n) 
        chunk_sizes = [chunk_size for i in range(n-1)] + [chunk_size+mod_size]
        i = 0
        for k_size in chunk_sizes:
            yield full[i: i + k_size]
            i = i + k_size

    def exclude_list(self, csv_file):
        df = pd.read_csv(csv_file, delimiter=',',usecols=['APK','family'])
    
    def run(self, apk_path, proc_no = 2, stamp=None, exclude=None):
        n_processes = proc_no
        file_list = list(Path(apk_path).rglob('*.apk')) if Path(apk_path).is_dir() else [Path(apk_path)]  

        if (len(file_list) > n_processes):
            freeze_support()
            file_chunks = self.__split_chunk(file_list, n_processes)
            pool = Pool(n_processes, initializer=tqdm.set_lock, initargs=(tqdm.get_lock(),))
            results = pool.starmap(self.single_proc, [(batch, idx, stamp) for idx, batch in enumerate(file_chunks)])
            print("DONE.")
        else:
            self.single_proc(file_list, 0, stamp)
        
        #TEMPORARY 2025/01/04
        #shutil.rmtree(APKReader.APKTOOL_FDR, ignore_errors=True)
        #shutil.rmtree(APKReader.DEX2JAR_INP, ignore_errors=True)
        #shutil.rmtree(APKReader.DEX2JAR_OUT, ignore_errors=True)

    def analyse_output(self, output_path):
        apk = Path(output_path).stem
        graph_folder = output_path + '/graphs'
        leak = 0
        leak_report_file = output_path + '/taint_summary.txt'
        Path(graph_folder).mkdir(parents=True, exist_ok=True)
        for output in Path(output_path).rglob("*.out"):
            if not output.stem == 'output_' + apk + '.out':
                    if 'StubApp' in output.stem:
                        # Case of stub app
                        todot(output, apk, graph_folder + '/' + output.stem.replace('output_', '') + '_stub')
                    elif 'JNI' in output.stem or '_so' in output.stem:
                        todot_native(output, apk,  graph_folder + '/' + output.stem.replace('output_', '') + '_native')
                    else:
                        todot(output, apk, graph_folder + '/' + output.stem.replace('output_', '') + '_nostub')
            leak_result = find_leak(output, apk, leak_report_file)
            if leak_result[0]:
                    leak = leak + leak_result[1]
        print("LEAKS: " + str(leak))

if __name__ == "__main__":
    args = sys.argv[1:]
    parser = argparse.ArgumentParser(description='Optional description')
    
    parser.add_argument('path', help='Path to apk file or directory')
    
    # Optional argument
    parser.add_argument('-n', '--nproc', type=int, default=5,
                        help='Number of paralel processes')
    
    parser.add_argument('-s', '--stamp', help='Name the output folder')

    parser.add_argument('-e', '--exclude', help='Previous history CSV file')
    args = parser.parse_args()
    RunSample().run(args.path, args.nproc, args.stamp, args.exclude)

