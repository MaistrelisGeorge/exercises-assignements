#added a prev attribute to store the reference to previous node
#added a tail attribute to store the last node of the list
#modified add to update the prev attribute
#display_backward method made to display the list backward
#remove method modified to also modify the prev attribute

class DoublyLinkedList:
    def __init__(self):
        self.head = None
        self.tail = None

    def add(self, value):
        new_node = ListNode(value)
        if self.head is None:  # If the list is empty
            self.head = new_node
            self.tail = new_node
        else:
            new_node.next = self.head
            self.head.prev = new_node
            self.head = new_node

    def display_forward(self):
        iter = self.head
        while iter is not None:
            print(iter.value, end=' ')
            iter = iter.next
        print()

    def display_backward(self):
        iter = self.tail
        while iter is not None:
            print(iter.value, end=' ')
            iter = iter.prev
        print()

    def contains(self, value):
        iter = self.head
        while iter is not None:
            if iter.value == value:
                return True
            iter = iter.next
        return False

    def remove(self, value):
        iter = self.head
        while iter is not None:
            if iter.value == value:
                if iter == self.head:  # If the node to be removed is the head
                    self.head = iter.next
                    if self.head is not None:  # If the list is not empty after removal
                        self.head.prev = None
                    else:  # If the list becomes empty
                        self.tail = None
                elif iter == self.tail:  # If the node to be removed is the tail
                    self.tail = iter.prev
                    self.tail.next = None
                else:  # If the node to be removed is in the middle
                    iter.prev.next = iter.next
                    iter.next.prev = iter.prev
                print(value, "deleted")
                return True
            iter = iter.next
        print(value, "not found")
        return False


class ListNode:
    def __init__(self, value):
        self.value = value
        self.next = None
        self.prev = None

    def __str__(self):
        return str(self.value)


# Test the Doubly Linked List
data = DoublyLinkedList()
data.add(35)
data.add(28)
data.add(49)
data.display_forward()  # Output: 49 28 35
data.display_backward()  # Output: 35 28 49

print(data.contains(49))  # Output: True
print(data.contains(56))  # Output: False

data.remove(55)  # Output: 55 not found
data.remove(35)  # Output: 35 deleted
data.display_forward()  # Output: 49 28
data.remove(49)  # Output: 49 deleted
data.display_forward()  # Output: 28