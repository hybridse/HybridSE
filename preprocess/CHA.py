import subprocess as sp
from pathlib import Path
import networkx as nx
import difflib

SOURCE_FILE = "../lib/sources.txt"
SINK_FILE = "../lib/sinks.txt"

def get_subgraph_by_class(nodes, edges, targetclass):
    target_node = get_node_by_name(nodes, targetclass)[0]
    sub_nodes, sub_edges = get_subgraph(nodes, edges, target_node)
    return sub_nodes, sub_edges

def get_call_graph_and_api(jarfile, dotfile):
    txt_file = str(Path(dotfile).parent.resolve()) + "/callgraph.txt"
    cmd = "java -jar lib/javacg-0.1-SNAPSHOT-static.jar {} > {}" \
                            .format(jarfile, txt_file)
    p = sp.run(cmd, shell=True, capture_output=True, text=True)
    generate_dot(txt_file, dotfile)
    nodes, edges, api_list = parse_java_callgraph(txt_file)
    
    #Path(txt_file).unlink()
    return nodes, edges, api_list

def parse_java_callgraph(txt_path):
    """ Parses the Java call graph output (without using DOT) and builds a graph structure.
        Return Nodes list, Edges list, API lists
    """
    
    "M:class1:<method1>(arg_types) (typeofcall)class2:<method2>(arg_types)"
    "C:class1 class2"
    nodes = set()
    edges = []
    apis = set()
    with open(txt_path, "r") as f:
        for line in f:
            line = line.strip()
            
            if line.startswith("M:"):  # Method call
                parts = line.split(" ")
                if len(parts) == 2:
                    caller = parts[0].split(":")[1].split('(')[0]  # Extract class1
                    callee = parts[1].split(")")[1].split('(')[0].strip()  # Extract class2
                
                    nodes.add(caller)
                    nodes.add(callee)
                    edges.append((caller, callee))

                    method1 = parts[0].split(":")[1]
                    method2 = parts[1].split(")")[1].strip()+")"
                    apis.add(method1)
                    apis.add(method2)

            elif line.startswith("C:"):  # Class-level call
                parts = line.split(" ")
                if len(parts) == 2:
                    class1, class2 = parts[0][2:], parts[1]
                    nodes.add(class1)
                    nodes.add(class2)
                    edges.append((class1, class2))
    return nodes, edges, sorted(list(apis))   

def get_meaningful_str(api_list):
    """Return API Call that class name length > 10"""
    apis = [x for x in api_list if len(x.split(":")[0]) > 10]
    return sorted(apis)

def get_meaningful_classes(api_list):
    """Return Class name that length > 10"""
    classes = [x.split(":")[0] for x in api_list if len(x.split(":")[0]) > 10]
    return sorted(list(set(classes)))

def get_node_by_name(nodes, clzname):
    closest_match =  difflib.get_close_matches(clzname, nodes, n=1, cutoff=0.5)
    return closest_match
    
def get_callers(nodes, edges, clzname):
    target_node = get_node_by_name(nodes, clzname)
    callers = []
    for edge in edges:
        if edge[1] == target_node:
            callers.append(edge[0])
    return callers

def get_subgraph(nodes, edges, target_node, n=3):
    """Returns up to 'n' neighbors of a given node."""
    neighbors = []
    for edge in edges:
        if edge[0] == target_node:
            neighbors.append(edge[1])  # If target_node is the first in the edge, the second is a neighbor
        elif edge[1] == target_node:
            neighbors.append(edge[0])  # If target_node is the second in the edge, the first is a neighbor
    
    neighbors = set(neighbors)
    subgraph_edges = []
    
    for edge in edges:
        if edge[0] == target_node or edge[1] == target_node or edge[0] in neighbors or edge[1] in neighbors:
            subgraph_edges.append(edge)

    # Create the subgraph as a set of nodes and edges
    subgraph_nodes = {target_node} | neighbors  # Include target node and all neighbors
    for edge in subgraph_edges:
        subgraph_nodes.add(edge[0])
        subgraph_nodes.add(edge[1])
    subgraph_nodes = sorted(list(subgraph_nodes))
    return subgraph_nodes, subgraph_edges

def create_dot_from_graph(nodes, edges, directed=False):
    """
    Create a DOT format string from nodes and edges.
    
    :param nodes: List of nodes in the graph.
    :param edges: List of edges (tuples) where each edge is a pair of nodes.
    :param directed: If True, creates a directed graph, else an undirected graph.
    :return: A string in DOT format.
    """
    # Start the DOT graph representation
    graph_type = "digraph" if directed else "graph"
    dot_graph = f"{graph_type} {{\n"
    # Add edges to the DOT representation
    for edge in edges:
        if directed:
            dot_graph += f"    \"{edge[0]}\" -> \"{edge[1]}\";\n"
        else:
            dot_graph += f"    \"{edge[0]}\" -- '{edge[1]}\";\n"
    
    # Close the DOT graph representation
    dot_graph += "}\n"
    
    return dot_graph

def generate_dot(input_file, dot_file):
    """Parses Java Call Graph output and writes a DOT file efficiently."""
    with open(input_file, "r") as infile, open(dot_file, "w") as dotfile:
        dotfile.write("digraph CallGraph {\n")  # Start DOT file
        
        for line in infile:
            line = line.strip()
            
            if line.startswith("M:"):  # Method-level call
                parts = line.split(" ")
                if len(parts) == 2:
                    call_type = parts[0][2]  # Extract type (M, I, O, S, D)
                    caller, callee = parts[1].split(" (")[0], parts[1].split(")")[1].strip()
                    
                    color = {"M": "blue", "I": "green", "O": "red", "S": "purple", "D": "orange"}.get(call_type, "black")
                    dotfile.write(f'  "{caller}" -> "{callee}" [label="{call_type}", color="{color}"];\n')

            elif line.startswith("C:"):  # Class-level call
                parts = line.split(" ")
                if len(parts) == 2:
                    class1, class2 = parts[0][2:], parts[1]
                    dotfile.write(f'  "{class1}" -> "{class2}" [style="dashed", color="gray"];\n')

        dotfile.write("}\n")  # Close DOT file

def render_dot(dot_file, output_image):
    """Converts DOT file to PNG using Graphviz for faster rendering."""
    sp.run(["dot", "-Tpng", dot_file, "-o", output_image], check=True)
    print(f"Graph generated: {output_image}")

if __name__ == "__main__":
    # Test input file from javacg-static output
    JAR_FILE = "/home/va/git/out/result_20250402_132835/dex2jar_output/PhoneParentalbis.jar"
    DOT_FILE = "/home/va/git/out/result_20250402_132835/projects/PhoneParentalbis/callgraph.dot"
    nodes, edges, apis = get_call_graph_and_api(JAR_FILE, DOT_FILE)
    sub_nodes, sub_edges = get_subgraph_by_class(nodes, edges, "android.telephony.TelephoneManager.getDeviceId")
    #print(get_callers(nodes, edges, "android.telephony.TelephoneManager"))
    dot_representation = create_dot_from_graph(sub_nodes, sub_edges, directed=True)
    print(dot_representation)
    print("\n".join(sub_nodes))