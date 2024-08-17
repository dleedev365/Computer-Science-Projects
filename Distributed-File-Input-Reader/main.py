'''
    AUTHOR: DANIEL LEE
    LAST UPDATE ON: JAN 14, 2018
'''


import math
import string
import array
import os
import sys

f2read = open('input.txt', 'r')
f2write = open('output.txt', 'w')

# variables declaration
line_counter = int(0)
EMPTY = int(0)
FILLED = int(1)
S = int(0)  # hash space [0 ...2^S-1]
MAX = int(0)  # MAX = 2^S, modulo
HashSpace = []
HashNodeVacancy = []
Fingers = []
Fingers_copy = []
Successors = []
query_path = []
last_joined_node = int(0)
ptr = int(0)
hash_key = int(0)
key = int(0)
query_node_id = int(0)
finger_result = int(0)
loop_counter = int(0)
largest_node = int(0)
largest_node_index = int(0)
finger = int(0)


for line in f2read:
    line_counter = line_counter + 1

    # First line, define hash space S, [0 ... 2^S-1]
    if line_counter == 1:
        S = int(line)
        MAX = pow(2, S)
        # initialize arrays
        for size in range(0, MAX):
            HashSpace.insert(size, -1)  # default
            HashNodeVacancy.insert(size, EMPTY)
        for size in range(0, S):
            Fingers.insert(size, -1)  # default
            Successors.insert(size, -1)  # default

    # Fourth line, hash node ids to join in order
    if line_counter == 4:
        # break line into chars
        for value in line.split(','):
            node_id = int(value)
            # insert the node
            HashNodeVacancy.pop(node_id)
            HashNodeVacancy.insert(node_id, FILLED)

    # Fifth line, hashed keys to join in order
    if line_counter == 5 and line != '-1,-1':
        # break line into chars, process them one at a time
        for value in line.split(','):
            hash_key = int(value)
            # find the closest node in the hash table []
            for ptr in range(int(hash_key), int(hash_key + MAX)):
                # if found a node in the table
                if HashNodeVacancy[ptr % MAX] == FILLED:
                    # insert the key
                    HashSpace.pop(ptr % MAX)
                    HashSpace.insert(ptr % MAX, hash_key)
                    break

    # From Sixth line, line format [key, query node id]
    if line_counter >= 6 and line != '-1,-1':
        last_joined_node = line_counter
        # break lie into individual values
        for value in line.split(','):
            # In first loop is a key
            if loop_counter == 0:
                key = int(value)
            # In second loop is query node id
            if loop_counter == 1:
                query_node_id = int(value)

            loop_counter = loop_counter + 1
        # while key has not been found
        while HashSpace[query_node_id] != key:
            # First, find Fingers of current node
            for i in range(0, S):
                finger_result = (query_node_id + pow(2, i)) % MAX  # id+2^i
                Fingers.pop(i)
                Fingers.insert(i, finger_result)

                # Second, find the successor of each i of the current node
                for x in range(finger_result, query_node_id + MAX):
                    if HashNodeVacancy[x % MAX] == FILLED:
                        Successors.pop(i)
                        Successors.insert(i, x % MAX)
                        break

            # Third, find the largest node of the node that doesn't exceed query key
            row = int(0)
            while row < S:
                tmp_var = Fingers[row]
                if tmp_var <= key and largest_node <= tmp_var and largest_node <= key:
                    largest_node = tmp_var
                    largest_node_index = Fingers.index(largest_node)
                else:
                    largest_node = max(Fingers)
                    largest_node_index = Fingers.index(largest_node)
                row = row + 1

            query_path.append(query_node_id)

            # hence key was not found, move onto the next node
            query_node_id = Successors[largest_node_index]
        # found a key
        query_path.append(query_node_id)

    # Get the success table of the last node joined
    # First, find Fingers of the node
    for i in range(0, S):
        finger_result = (last_joined_node + pow(2, i)) % MAX  # id+2^i
        Fingers.pop(i)
        Fingers.insert(i, finger_result)

        # Second, find the successor of each i of the node
        for x in range(finger_result, last_joined_node + MAX):
            if HashNodeVacancy[x % MAX] == FILLED:
                Successors.pop(i)
                Successors.insert(i, x % MAX)
                break

# print successor table of the last joined
for i in range(0, S):
    f2write.write(str(i) + ' ' + str(Fingers[i]) + ' ' + str(Successors[i]) + '\n')
# print query path
for value in query_path:
    f2write.write(str(value) + ' ')

f2read.close()
f2write.close()
