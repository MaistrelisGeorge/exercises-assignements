# Airport Control Simulation Program

## Overview

This Python program simulates air traffic control at a **small airport** with a single airway. The simulation manages **landing** and **takeoff** requests from planes using custom made **FIFO queues** to handle priorities.

Key features:
- Handles **four types of flight requests**:
  - Extreme Emergency Landings
  - Emergency Landings
  - Normal Landings
  - Takeoffs
- Prioritizes **landings over takeoffs** to minimize air traffic waiting time.
- **Extreme emergencies** are handled first, followed by **emergencies**, **normal landings**, and finally **takeoffs**.
- Simulates a **full operational day** (24 airway clearances), with:
  - **Random request generation** every 2-5 seconds.
  - **Airway clearances** occurring every 9 seconds.

## How to Use the Program

1. **Ensure you have Python installed** (version 3.x).
   
2. **Run the program**:
   
    Change directory to match the folder the .py is located, then:

    ```bash
    python AirwayPriority_FinalwBetterComments.py
    ```
    
    Alternatively you can **double-click the .py file from your file explorer**.

3. **Follow the prompt**:
   - Enter `y` to start a **new simulation day**.
   - The simulation will:
     - Generate **random flight requests** (landings/takeoffs) every 2-5 seconds.
     - Perform **airway clearances** based on priority every 9 seconds.
   - After **24 clearances**, the day ends, and you'll be asked if you want to start another.

4. **Exit the program**:
   - Enter `n` when prompted to **exit**.

## Simulation Details

- **Flight Requests**:
  - Randomly generated flight numbers (e.g., `AB123`).
  - Operations:
    - **Takeoff**
    - **Normal Landing**
    - **Emergency Landing**
    - **Extreme Emergency Landing**
  - Requests are generated **every 2-5 seconds**.

- **Airway Clearance**:
  - Happens **every 9 seconds**, regardless of request timing.
  - Follows this **priority order**:
    1. Extreme Emergency Landings
    2. Emergency Landings
    3. Normal Landings
    4. Takeoffs (only if no landings are pending)

- **Simulation Day**:
  - A full day includes **24 airway clearances**.
  - After a day finishes, you're prompted to **start a new day** or **exit**.

## File Structure

```
AirwayPriority_FinalwBetterComments.py
README.md
```

- **AirwayPriority_FinalwBetterComments.py**: Main Python script containing the simulation code.
- **README.md**: Documentation explaining the program.

## Example Output

```
Welcome to the Airport Simulation Program!

Start a new day of simulation? (y/n): y
Flight AB123 requests takeoff.
Flight XY789 requests NORMAL landing.
Flight CD456 requests EXTREME EMERGENCY landing.
CONTROL: Flight CD456 is cleared for landing (EXTREME EMERGENCY).
...
Simulation day completed.
```

## Notes

- The program uses **only Python standard libraries** (`random`, `string`, `time`).
- No additional installations or dependencies are required, thus no requirements.txt file.

## Author

- **Student Name**: [Maistrelis Georgios Efstratios]
- **Student ID**: [2331873]
- **Program Title**: [Data Structure and Algorithms]
