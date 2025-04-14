import graphviz
import networkx as nx
from networkx.drawing.nx_agraph import write_dot
from pathlib import Path
import pandas as pd
from tqdm.auto import tqdm 

#bytecode graph

def get_time(csv_file):
    df_time = pd.read_csv(csv_file, names=['apk', 'time'])

def todot(out_file, apk, dot_file):
    file1 = open(out_file, 'r')
    Lines = file1.readlines()

    dot = nx.Graph(strict=True) 
    count = 0
    prev = Path(out_file).stem
    c = 'blue'
    dot.add_node(prev, color=c)
    for line in Lines:
        count += 1
        line = line.strip()
        com = line.split(' ')
        if '[WARNING]' in line and len(com) > 2: # bytecode node
            node = com[1] + "_" + com[2]
            dot.add_node(node, color=c)
            dot.add_edge(prev, node)
            prev = node
        else:
            if 'Executing' in line and len(com) >= 5:
                node = com[2]+ "_" + com[4]
                dot.add_node(node,color='red')
                dot.add_edge(prev, node)
                prev = node
            if "Call to library function"in line:
                node =  "CALL_" + com[-1]
                dot.add_node(node,color='red',  style='filled')
                dot.add_edge(prev, node)
                prev = node
    graph = dot
    write_dot(graph, dot_file + '.dot')

def find_leak(out_file, apk, leak_file):
    hasSource, hasSink = False, False
    file1 = open(out_file, 'r')
    Lines = file1.readlines()
    report_file = open(leak_file, "a")
    
    report = []
    report_file.write('ENTRY POINT: ' + out_file.stem.replace('output_', '') + '\n')
    leak = 0
    current= ""
    
    for line in Lines:
        line = line.strip()

        if 'WARNING' in line and 'SINK' not in line and 'SOURCE' not in line: 
            current = line.split(' ')[1]
        elif 'Executing' in line: 
            current = line.split(' ')[2]

        if 'SOURCE' in line: # bytecode node
            hasSource = True 
            report.append('LOC ' + current + " " + line)
        elif 'SINK' in line and '<clinit>' not in line:
            if current.strip() != "-1" and ('LOC ' + current + " "  + line not in report[-1]):
                hasSink = True
                report.append('LOC ' + current + " "  + line)
                leak = leak + 1
            elif current.strip() == "-1": 
                hasSink = True
                report.append('LOC ' + current + " "  + line)
                leak = leak + 1
                    
    for r_line in report:
        report_file.write(r_line + '\n')
    report_file.write("Leaks: " + str(leak) + '\n')
    report_file.close()
    return (hasSource and hasSink, leak)

def todot_native(out_file, apk, dot_file):
    file1 = open(out_file, 'r')
    Lines = file1.readlines()

    dot = nx.Graph(strict=True) 
    count = 0
    count = 0
    prev = Path(out_file).stem
    c = 'red'
    dot.add_node(prev, color=c)
    for line in Lines:
        count += 1
        #print("Line{}: {}".format(count, line.strip()))
        #if line.startswith('-') :
        line = line.strip()
        com = line.split(' ')
        if 'Executing' in line:
                node = com[2]+ "_" + com[4]
                dot.add_node(node,color='red')
                dot.add_edge(prev, node)
                prev = node
        if "Call to library function"in line:
                node =  "CALL_" + com[-1]
                dot.add_node(node,color='red',  style='filled')
                dot.add_edge(prev, node)
                prev = node
    write_dot(dot, dot_file + '.dot')
    
def get_packagename(jpf_file):
    file1 = open(jpf_file, 'r')
    Lines = file1.readlines()
    package_name = ''
    for line in Lines:
        if 'symbolic.method' in line:
            package_name = line.strip().split('=')[-1]
    return package_name

def tocalldot(out_file):
    call = graphviz.Digraph('callG') 
    c = 'blue'
    prev = 'start'
    Lines = []
    for line in Lines:
        count += 1
        line = line.strip()
        com = line.split(' ')
        if 'enter' in line:
            node = com[1] + "_" + com[2]
            call.node(node, color=c)    
            call.edge(prev, node)
            prev = node
        elif 'invoke' in line:
            node = com[1] + "_" + com[2]
            call.node(node, color=c)    
            call.edge(prev, node)
            prev = node
        elif 'Exploring' in line:
            node = "native_" + com[1]
            call.node(node, color='red')    
            call.edge(prev, node)
            prev = node

   
if __name__ == "__main__": 
    year = "2018"
    count_stub = 0
    count = 0
    leak = 0
    
    with open('input_2018', 'r') as f:
        for line in f.readlines():
            line = line.strip()
            stamp = "2018"
            output_path = line + "/java_projects"
            print(line)
            #output_path = "/home/va/out/temp_" + stamp + "/java_projects"
            file_list = list(Path(output_path).glob("*"))
            for file in tqdm(file_list, total= len(file_list), position=0, leave=True):
                apk = file.stem
                count = count+1
                if Path(year + "/" + apk).exists():
                    pass
                else:
                    Path(year + "/" + apk).mkdir(parents=True, exist_ok=True)
                    leak_report_file = year +'/' + apk + '/taint_report.txt'
                    for output in file.rglob("*.out"):
                        if not output.stem == 'output_' + apk + '.out':
                            if 'StubApp' in output.stem:
                                # Case of stub app
                                count_stub = count_stub + 1
                                todot(output, apk, year +'/' + apk + '/' + output.stem + '_stub')
                            elif 'JNI' in output.stem or '_so' in output.stem:
                                todot_native(output, apk, year + '/' + apk + '/' + output.stem + '_native')
                            else: 
                                todot(output, apk, year + '/' + apk + '/' + output.stem + '_nostub')
                        if find_leak(output, apk, leak_report_file):
                            leak = leak + 1
            print("LEAKS: " + str(leak))