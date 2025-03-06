#very simple queue example using Python lists

queue = []

queue.append(1)
queue.append(2)
queue.append(3)

print("Queue after enqueues:", queue)  #expected output: [1, 2, 3] since it uses Fifo and append here adds at the begining of the list