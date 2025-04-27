import random
import string
import time

# FIFO Queue Class
class Queue:
    def __init__(self):
        self.data = []

    def enqueue(self, value):
        # Adds a new item to the end of the queue
        self.data.append(value)

    def dequeue(self):
        # Removes and returns the item at the front of the queue (FIFO)
        value = self.next()
        if value is not None:
            self.data.pop(0)
        return value

    def next(self):
        # Returns the front item without removing it
        if self.is_empty():
            return None
        return self.data[0]

    def is_empty(self):
        # Checks if the queue is empty
        return len(self.data) == 0

# Airway Priority System Class
class AirwayPrioritySystem:
    def __init__(self):
        # Initialize four queues for different operations and priorities
        self.extreme_emergency_landings = Queue()
        self.emergency_landings = Queue()
        self.normal_landings = Queue()
        self.takeoffs = Queue()

    def request_airway(self, flight_number, operation, priority=None): # operation and priority could be merged, but this way visual clarity and expandability is better
        # Adds a flight request to the appropriate queue
        if operation == "landing":
            if priority == "extreme emergency":
                self.extreme_emergency_landings.enqueue(flight_number)
                print(f"Flight {flight_number} requests EXTREME EMERGENCY landing.")
            elif priority == "emergency":
                self.emergency_landings.enqueue(flight_number)
                print(f"Flight {flight_number} requests EMERGENCY landing.")
            elif priority == "normal":
                self.normal_landings.enqueue(flight_number)
                print(f"Flight {flight_number} requests NORMAL landing.")
            else:
                print(f"Invalid priority for landing request by flight {flight_number}.")
        elif operation == "takeoff":
            self.takeoffs.enqueue(flight_number)
            print(f"Flight {flight_number} requests takeoff.")
        else:
            print(f"Invalid operation request by flight {flight_number}.")

    def clear_airway(self):
        # Clears the airway based on priority: extreme emergencies first, then emergencies, then normal landings, takeoffs last
        if not self.extreme_emergency_landings.is_empty():
            flight = self.extreme_emergency_landings.dequeue()
            print(f"CONTROL: Flight {flight} is cleared for landing (EXTREME EMERGENCY).")
        elif not self.emergency_landings.is_empty():
            flight = self.emergency_landings.dequeue()
            print(f"CONTROL: Flight {flight} is cleared for landing (EMERGENCY).")
        elif not self.normal_landings.is_empty():
            flight = self.normal_landings.dequeue()
            print(f"CONTROL: Flight {flight} is cleared for landing (NORMAL).")
        elif not self.takeoffs.is_empty():
            flight = self.takeoffs.dequeue()
            print(f"CONTROL: Flight {flight} is cleared for takeoff.")
        else:
            print("CONTROL: No flights waiting.")

# Generates a random flight number (both uppercase letters and digits)
def generate_flight_number():
    letters = ''.join(random.choices(string.ascii_uppercase, k=2))
    numbers = ''.join(random.choices(string.digits, k=3))
    return letters + numbers

# Generates a random flight request and adds it to the system
def generate_random_request(system):
    flight_number = generate_flight_number()
    operation_roll = random.randint(1, 4) # 1=Takeoff, 2=Normal Landing, 3=Emergency Landing, 4=Extreme Emergency Landing

    if operation_roll == 1:
        system.request_airway(flight_number, "takeoff")
    elif operation_roll == 2:
        system.request_airway(flight_number, "landing", "normal")
    elif operation_roll == 3:
        system.request_airway(flight_number, "landing", "emergency")
    elif operation_roll == 4:
        system.request_airway(flight_number, "landing", "extreme emergency")

# Simulates a full day of airport operations (24 airway clearances)
def simulate_day():
    system = AirwayPrioritySystem()  
    clearance_cycles = 0 # Counter for airway clearances
    last_clearance_time = time.time() # Track the last time the airway was cleared

    while clearance_cycles < 24:
        # Wait between 3-6 seconds before generating the next request
        request_interval = random.randint(3, 6)
        time.sleep(request_interval)
        generate_random_request(system) # Generate and add a random request

        # Check if 9 seconds have passed since the last airway clearance
        if time.time() - last_clearance_time >= 9:
            system.clear_airway() # Clear the airway
            clearance_cycles += 1 # +1 to clearance counter
            last_clearance_time = time.time() # Reset clearance timer

# Main program
if __name__ == "__main__":
    print("Welcome to the Airport Simulation Program!")

    while True:
        # Prompt the user whether to start a new simulation day
        user_input = input("\nStart a new day of simulation? (y/n): ").strip().lower()
        if user_input == 'y':
            simulate_day() # Run the simulation
            print("\nSimulation day completed.")
        elif user_input == 'n':
            print("Exiting the program.")
            break
        else:
            print("Invalid input. Please enter 'y' or 'n'.")
