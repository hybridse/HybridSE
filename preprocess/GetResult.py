from APKReader import APKReader
from TemplateGen import TemplateGen
from Stat import Stat
import time
import pandas as pd
from tqdm import tqdm
from pathlib import Path
import shutil
import networkx as nx

start = time.time()

df = pd.read_csv(TemplateGen.FAMILY_CSV, delimiter=',',usecols=['sha256','family'])
df = df.reindex(columns = ['sha256','family', 'bytecode','arm','API','time'])
sample_dir = './temp/result'

save_file = './data/result_180523.csv'
path = Path(sample_dir).glob("*")

df_save = pd.read_csv(save_file, delimiter=',',usecols=['sha256', 'java', 'native', 'lib', 'time'])
apk_list = df.sha256.values
#TemplateGen.build()

# for file in (pbar:=tqdm(list(path))):
#     pbar.set_postfix_str(file.name)
#     apk_name = file.name
#     output_name = 'output_' + apk_name + '.out'
    
#     if apk_name in apk_list:
#         print(apk_name)
       
DOT_DIR = "./temp/result_1"
graphs = []
name_apk = []
sample_list = []
for file in Path(DOT_DIR).glob("*"):
    sample = {}
    apk_name = file.stem
    methods = []
    G = None
    executed_node = []
    for dotfile in Path(DOT_DIR + '/' + apk_name).glob("*.dot"):
        short_func_name = dotfile.stem.split('.')[-1].replace('so_','')
        sofile_name = dotfile.stem.replace( '_' + short_func_name, '') 
        full_name = ''
        methods.append(short_func_name)
        
        H = nx.Graph(nx.nx_pydot.read_dot(str(dotfile)))
        if H is None: 
         
            Path(dotfile).unlink()
        if len(H.nodes) == 0:
             Path(dotfile).unlink()
    #Summarize results to graph_df
