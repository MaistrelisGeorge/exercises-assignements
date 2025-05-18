import pandas as pd
import re
import random
from collections import defaultdict

# GRAPH STRUCTURE:

class Vertex:
    def __init__(self, key):
        self.key = key
        self.data = None
        self.edges = []
        self.parent = None
        self.color = 'white'
        self.distance = float("inf")

    def display(self):
        print(self.key, self.data, self.distance, end=": [ ")
        for edge in self.edges:
            print(edge.connection.key, end=" ")
        print("]")

class Edge:
    def __init__(self, vertex):
        self.connection = vertex
        self.weight = 1

class Graph:
    def __init__(self):
        self.vertices = {}
        self.directed = False
        self.queue = []
        self.start = None

    # Resetting vertex attributes: needed for BFS initialization
    def init_bfs(self):
        for v in self.vertices.values():
            v.parent = None
            v.distance = float("inf")
            v.color = 'white'

    # Relaxation: needed for Dijkstra's algorithm
    def relax(self, va, vb, w):
        if va.distance + w < vb.distance:
            vb.distance = va.distance + w
            vb.parent = va

    # Dijkstra's algorithm for shortest path (weighted edges, shortest time path)
    def dijkstra(self, start):
        self.start = start
        self.init_bfs()
        if start not in self.vertices:
            return False
        v = self.vertices[start]
        v.distance = 0
        heap = []
        heap.append((0, v))
        visited = set()
        while heap:
            heap.sort(key=lambda x: x[0])
            dist_u, u = heap.pop(0)
            if u.key in visited:
                continue
            visited.add(u.key)
            for e in u.edges:
                self.relax(u, e.connection, e.weight)
                if e.connection.key not in visited:
                    heap.append((e.connection.distance, e.connection))

    # BFS for shortest path (unweighted edges, least stops path)
    def bfs(self, start):
        self.start = start
        self.init_bfs()
        if start not in self.vertices:
            return False
        v = self.vertices[start]
        v.distance = 0
        v.color = 'grey'
        self.queue.append(v)
        while self.queue:
            v = self.queue.pop(0)
            v.color = 'black'
            for e in v.edges:
                con = e.connection
                if con.color == 'white':
                    con.color = 'gray'
                    con.distance = v.distance + 1
                    con.parent = v
                    self.queue.append(con)
        return True

    # Print shortest path from BFS/Dijkstra
    def bfs_shortest_path(self, dest):
        if dest in self.vertices:
            v = self.vertices[dest]
            if v.parent is None:
                print("No path from", v.data)
                return False
            self.print_path(v)
            print()
            return True
        else:
            return False

    # Recursively print path
    def print_path(self, vertex):
        if vertex.parent is not None:
            self.print_path(vertex.parent)
        print(vertex.data, end=' -> ')

    # Display all vertices and edges
    def display(self):
        for v in self.vertices.values():
            v.display()

    # Add 1 vertex to the graph
    def add(self, key):
        if key in self.vertices:
            return None
        new_vertex = Vertex(key)
        self.vertices[key] = new_vertex
        return new_vertex

    # Connect 2 vertices with 1 edge
    def connect(self, key_a, key_b, **kwargs):
        if key_a not in self.vertices or key_b not in self.vertices:
            return False, "Key not found"
        weight = kwargs.get('weight', 1)
        vertex_a = self.vertices[key_a]
        vertex_b = self.vertices[key_b]
        edge_ab = Edge(vertex_b)
        edge_ab.weight = weight
        vertex_a.edges.append(edge_ab)
        if not self.directed:
            edge_ba = Edge(vertex_a)
            edge_ba.weight = weight
            vertex_b.edges.append(edge_ba)
        return True, "Vertices connected"

#LINE DISPLAY:

# Display all MRT lines and stations (for the passenger menu)
def display_all_lines_and_stations(graph):
    print("Available MRT Lines and Their Stations:")
    line_map = defaultdict(list)
    for vertex in graph.vertices.values():
        if vertex.data:
            try:
                name_part, code_part = vertex.data.split(" (")
                code = code_part.strip(")")
                line = ''.join(filter(str.isalpha, code))
                num = int(''.join(filter(str.isdigit, code)))
                line_map[line].append((num, name_part.strip(), code))
            except ValueError:
                continue
    for line in sorted(line_map.keys()):
        print(f"Line {line}:")
        for _, name, code in sorted(line_map[line]):
            print(f"  - {name} ({code})")
        print()

# METRO GRAPH BUILDER:

# Load Singapore MRT data from the stations.csv and build graph
def load_singapore_mrt_to_graph(filename="stations.csv"):
    data = pd.read_csv(filename)
    features = pd.DataFrame()
    features['name'] = data['STN_NAME']
    features['code'] = data['STN_NO']
    rows_list = features.values
    new_rows = []
    for row in rows_list:
        codes = row[1].split('/')
        if len(codes) > 1:
            row[1] = codes[0]
            for i in range(1, len(codes)):
                new_rows.append([row[0], codes[i]])
    rows = rows_list.tolist()
    for row in new_rows:
        rows.append(row)
    df = pd.DataFrame(rows)
    df.rename(columns={0: 'name', 1: 'code'}, inplace=True)

    # Extract line and station number from code
    def extract_digits(code):
        result = re.findall(r"[0-9]+", code)
        return int(result[0]) if result else None

    def extract_letters(code):
        result = re.findall(r"[A-Z]+", code)
        return result[0] if result else None

    df['line'] = df.apply(lambda x: extract_letters(x.code), axis=1)
    df['num'] = df.apply(lambda x: extract_digits(x.code), axis=1)
    df = df.dropna()
    df = df[~df.line.isin(['BP', 'SW', 'SE', 'PE', 'PW'])]  # Exclude unreachable LRT stations (I think they are all unreachable from MRT, future plans have them in a separate menu?)
    df['num'] = pd.to_numeric(df['num'], downcast='integer')
    df = df.sort_values(['line', 'num'])

    # Build metro dictionary with edges
    metro = {}
    for row in df.values:
        metro[row[1]] = {'name': row[0], 'line': row[2], 'num': row[3], 'edges': []}

    # Connect adjacent stations on the same line
    row_list = df.values
    for i in range(len(row_list)-1):
        current = row_list[i]
        next_row = row_list[i+1]
        if current[2] == next_row[2] and next_row[3] - current[3] == 1:
            metro[current[1]]['edges'].append(next_row[1])
            metro[next_row[1]]['edges'].append(current[1])

    # Connect interchange stations
    df = df.sort_values(['name'])
    processed = set()
    for row in df.values:
        name = row[0]
        if name not in processed:
            duplicates = df[df.name == name]
            if len(duplicates) > 1:
                codes = duplicates['code'].tolist()
                for i in range(len(codes)):
                    for j in range(i+1, len(codes)):
                        metro[codes[i]]['edges'].append(codes[j])
                        metro[codes[j]]['edges'].append(codes[i])
                processed.add(name)

    # Build graph
    graph = Graph()
    code_to_key = {}
    key_counter = 1
    for code, station in metro.items():
        v = graph.add(key_counter)
        if v:
            v.data = f"{station['name']} ({code})"
            code_to_key[code] = key_counter
            key_counter += 1
    for code, station in metro.items():
        for neighbor_code in station['edges']:
            if code != neighbor_code:
                a = code_to_key[code]
                b = code_to_key[neighbor_code]
                weight = 5 if station['name'] == metro[neighbor_code]['name'] else random.randint(2, 8)
                graph.connect(a, b, weight=weight)
    return graph

# ROUTING MENU:

# User menu for station input and routing
def run_passenger_menu(graph):
    # Map station codes to vertex keys
    code_to_key = {}
    for k, v in graph.vertices.items():
        if v.data:
            _, code_part = v.data.split(" (")
            code = code_part.strip(")")
            code_to_key[code] = k

    print()
    start_code = input("Enter current station code (e.g., TE20): ").strip().upper()
    end_code = input("Enter destination station code (e.g., SW5): ").strip().upper()

    if start_code not in code_to_key or end_code not in code_to_key:
        print("Error: Invalid station code. Please try again.")
        return

    start_key = code_to_key[start_code]
    end_key = code_to_key[end_code]

    print("\nHow would you like to travel?")
    print("1: Fastest route (based on travel time)")
    print("2: Route with fewest stops")
    choice = input("Enter 1 or 2: ").strip()

    print()
    if choice == "1":
        print("Fastest route:")
        graph.dijkstra(start_key)
        graph.bfs_shortest_path(end_key)
    elif choice == "2":
        print("Least stops route:")
        graph.bfs(start_key)
        graph.bfs_shortest_path(end_key)
    else:
        print("Error: Invalid choice.")

# MAIN:

if __name__ == "__main__":
    print("Singapore Metro Navigator")
    print("Loading MRT map from 'stations.csv'...\n")
    graph = load_singapore_mrt_to_graph()
    print("MRT map loaded successfully.\n")
    display_all_lines_and_stations(graph)
    run_passenger_menu(graph)