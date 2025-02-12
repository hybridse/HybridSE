from APKReader import APKReader
from TemplateGen import TemplateGen
from Stat import Stat
import time
import pandas as pd
from tqdm import tqdm
from pathlib import Path

start = time.time()

df = pd.read_csv(TemplateGen.FAMILY_CSV, delimiter=',',usecols=['sha256','family'])
df = df.reindex(columns = ['sha256','family', 'bytecode','arm','API','time'])
sample_dir = './temp/java_projects'
save_file = './data/result_180523.csv'
path = Path(sample_dir).glob("*")

df_save = pd.read_csv(save_file, delimiter=',',usecols=['sha256', 'java', 'native', 'lib', 'time'])
apk_list = df[df.family == "Adrd"].sha256.values
#TemplateGen.build()
for file in (pbar:=tqdm(list(path))):
    pbar.set_postfix_str(file.name)
    apk_name = file.name
    if apk_name in apk_list:
        print(apk_name)
        gen = TemplateGen()
        
        # if apk_name in df_save.sha256:
        #     print("Already processed")
        #     continue
        gen.reset_dot()
        start = time.time()
        gen.runActivities(str(file.absolute()))
        exe_time = time.time() - start
        # df.loc[df.sha256 == apk_name, ['bytecode','arm','API']] = list(gen.stat)
        # df.loc[df.sha256 == apk_name, 'time'] = exe_time
        gen.RESULT_DIR = "./temp/result_graph"
        gen.get_dot(apk_name)
        with open('./data/result_Adrd.csv', 'a') as file:
            line = apk_name+ ', ' + ','.join([str(x) for x in gen.stat]) + ', ' + str(exe_time) + '\n'
            file.write(line)