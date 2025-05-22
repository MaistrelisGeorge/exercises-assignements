
# Singapore Metro Route Planner

## Overview

This Python program simulates the Singapore MRT network using a graph data structure. It supports user interaction through a command-line interface where users can:
- Load real MRT station data
- View all available lines and stations
- Calculate:
  - The **fastest route** (by travel time) using **Dijkstra’s algorithm**
  - The **shortest route** (by fewest stops) using **Breadth-First Search (BFS)**

The program constructs a weighted, undirected graph using real station data from a `.csv` file and calculates optimal paths based on the user’s input.

---

## How to Use the Program

1. **Ensure Python 3 is installed** on your system.

2. Place the **stations.csv** and **metro_map.py** files in the same folder.

3. **Install the required external library:**

```bash
pip install pandas
```

4. **Run the program from your terminal or command prompt:**

```bash
python metro_map_v2.0_minheap.py
```

5. **Follow the prompts**:
- Enter the **station codes** for your start and destination (e.g., `TE20`, `NS12`).
- Choose:
  - `1` for the fastest route (Dijkstra)
  - `2` for the least stops (BFS)

6. The result will display the chosen route and relevant metrics.

---

## Libraries Used

### Python Standard Library
- `random` – To simulate random travel times
- `re` – For parsing station codes
- `collections.defaultdict` – For mapping and station grouping

### External Library
- `pandas` – Used to load and manipulate the MRT station dataset

---

## Files Included

```
metro_map_v1.9.py                    # Main Python program
stations.csv                         # Station data source (real MRT station list)
SingaporeMetro_README.md             # Program usage documentation
SingaporeMetro_requirements.txt      # Package requirements (pandas)
```

---

## Author
- **Student Name**: Maistrelis Georgios Efstratios  
- **Student ID**: 2331873  
- **Program Title**: Data Structures and Algorithms
