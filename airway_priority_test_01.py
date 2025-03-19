class MaxHeap:
    def __init__(self):
        self.data = []

    def add(self, value):
        self.data.append(value)
        self.heapify_up()

    def get(self):
        if self.is_empty():
            return None 
        maximum = self.data[0]
        self.data[0] = self.data[-1]
        self.data.pop(-1)
        self.heapify_down() 
        return maximum

    def is_empty(self):
        return len(self.data) == 0

    def get_parent(self, pos):
        return (pos - 1) // 2
    
    def get_left_child(self, pos):
        return pos * 2 + 1
    
    def get_right_child(self, pos):
        return pos * 2 + 2

    def heapify_up(self):
        pos = len(self.data) - 1
        while pos > 0:
            parent = self.get_parent(pos)
            if self.data[pos][0] > self.data[parent][0]:  #compare priority
                self.data[pos], self.data[parent] = self.data[parent], self.data[pos] 
                pos = parent
            else:
                break

    def in_heap_list(self, pos):
        return pos < len(self.data)

    def get_max_child(self, pos):
        left = self.get_left_child(pos)
        right = self.get_right_child(pos)
        if self.in_heap_list(left) and self.in_heap_list(right):
            return left if self.data[left][0] > self.data[right][0] else right
        elif self.in_heap_list(left):
            return left 
        else:
            return None

    def heapify_down(self):
        pos = 0
        max_child = self.get_max_child(pos) 
        while max_child is not None:
            if self.data[pos][0] < self.data[max_child][0]:
                self.data[pos], self.data[max_child] = self.data[max_child], self.data[pos]
                pos = max_child
                max_child = self.get_max_child(pos)
            else:
                break


class AirwayPrioritySystem:  
    def __init__(self):
        self.extreme_emergency_landings = MaxHeap()
        self.emergency_landings = MaxHeap()
        self.normal_landings = MaxHeap()
        self.takeoffs = MaxHeap()
        self.priority_map = {"extreme emergency": 3, "emergency": 2, "normal": 1, "takeoff": 0}
    
    def request_airway(self, flight_number, operation, priority=None): #function to sort flights into the 4 heaps
        if operation == "landing":
            if priority == "extreme emergency":
                self.extreme_emergency_landings.add((3, flight_number))
            elif priority == "emergency":
                self.emergency_landings.add((2, flight_number))
            elif priority == "normal":
                self.normal_landings.add((1, flight_number))
            else:
                print("Invalid priority for landing.")
        elif operation == "takeoff":
            self.takeoffs.add((0, flight_number))
        else:
            print("Invalid operation.")

    def clear_airway(self): #function to clear flights from the heaps in priority
        if not self.extreme_emergency_landings.is_empty():
            _, flight = self.extreme_emergency_landings.get()
            print(f"Flight {flight} is cleared for landing (EXTREME EMERGENCY).")
        elif not self.emergency_landings.is_empty():
            _, flight = self.emergency_landings.get()
            print(f"Flight {flight} is cleared for landing (EMERGENCY).")
        elif not self.normal_landings.is_empty():
            _, flight = self.normal_landings.get()
            print(f"Flight {flight} is cleared for landing (NORMAL).")
        elif not self.takeoffs.is_empty():
            _, flight = self.takeoffs.get()
            print(f"Flight {flight} is cleared for takeoff.")
        else:
            print("No flights waiting.")


if __name__ == "__main__":
    airway_system = AirwayPrioritySystem()
    
    #adding requests to test
    airway_system.request_airway("AA101", "takeoff")
    airway_system.request_airway("BB202", "landing", "normal")
    airway_system.request_airway("CC303", "landing", "emergency")
    airway_system.request_airway("DD404", "landing", "extreme emergency")
    airway_system.request_airway("EE505", "takeoff")
    airway_system.request_airway("FF606", "landing", "emergency")
    
    #processing requests (must find a way to add automatically)
    for _ in range(6):
        airway_system.clear_airway()
