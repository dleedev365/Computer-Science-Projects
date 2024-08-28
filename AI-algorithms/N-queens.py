# Author: Daniel Lee


from csp import *
import os
import math
import threading



str_array = []

def make_nqueen_array(n):
    nq_array = []
    number = 0

    for row in range(n):
        new_row = []
        for numbers in range(n) :
            number += 1
            new_row.append(number)
        nq_array.append(new_row) 

    return nq_array

# input: integer, array 
def row_constraints(n,a):
    xi = 0
    temp_str = ""

    for rows in a:
        for entries in rows:
            temp_str += str(entries) + " "

        temp_str += "0"
        str_array.append(temp_str)
        temp_str = ""
        # print contraints
        for x in range(n-1):
            for y in range(x+1, n):
                print(str(-a[xi][x]) + " " + str(-a[xi][y]) + " 0")

        xi += 1

# input: integer, array 
def col_constraints(n,a):
    # print("c <Column Contraints>")
    str_array.append("c <Column Contraints>")
    # print entries + 0
    yi = 0
    temp_str = ""

    for cols in range(n):
        for rows in range(n):
            # print(a[rows][cols], end=" ")
            temp_str += str(a[rows][cols]) + " "
        # print('0')
        temp_str += "0"
        str_array.append(temp_str)
        temp_str = ""

    # print contraints
        for xi in range(n-1):
            for x in range(xi+1, n):
                # print(str(-a[xi][yi]) + " " + str(-a[x][yi]) + " 0")
                str_array.append(str(-a[xi][yi]) + " " + str(-a[x][yi]) + " 0")
        yi += 1

# input: integer, array
def diag_contraints(n,a):
    # print("c <Diagnoal Contraints(\)>")
    str_array.append("c <Diagnoal Contraints(skewed to left)>")
    temp_array = []
    
    # first half based on Y
    for y in range(n-1):
        x = 0
        yx = y
        while x < n-y:
            # print(a[x][yx], end=" ")
            temp_array.append(a[x][yx])
            x += 1
            yx = x + y

        for i in range(len(temp_array)):
            for j in range(i+1, len(temp_array)):
                # print(str(-temp_array[i]) + " " + str(-temp_array[j]) + " 0")
                str_array.append(str(-temp_array[i]) + " " + str(-temp_array[j]) + " 0")
        temp_array = []
        # print("0")

    temp_array = []
    # second half based on X
    for x in range(1,n-1):
        y = 0
        xx = x
        while y < n-x:
            # print(a[xx][y], end=" ")
            temp_array.append(a[xx][y])
            y += 1
            xx = y + x

        for i in range(len(temp_array)):
            for j in range(i+1, len(temp_array)):
                # print(str(-temp_array[i]) + " " + str(-temp_array[j]) + " 0" )
                str_array.append(str(-temp_array[i]) + " " + str(-temp_array[j]) + " 0")
        temp_array = []
        # print("0")

    # print("c <Diagnoal Contraints(/)>")
    str_array.append("c <Diagnoal Contraints(skewed to right)>")

    temp_array = []
    # first half based on Y
    for y in range(1, n):
        x = 0
        yx = y
        while x <= y:
            # print(a[x][yx],end=" ")
            temp_array.append(a[x][yx])
            x += 1
            yx -= 1

        for i in range(len(temp_array)):
            for j in range(i+1, len(temp_array)):
                # print(str(-temp_array[i]) + " " + str(-temp_array[j]) + " 0" )
                str_array.append(str(-temp_array[i]) + " " + str(-temp_array[j]) + " 0")
        temp_array = []
        # print("0")

    temp_array = []
    # second half based on X
    for x in range(1, n-1):
        y = n-1
        xx = x
        while y >= x:
            # print(a[xx][y], end=" ")
            temp_array.append(a[xx][y])
            y -= 1
            xx += 1

        for i in range(len(temp_array)):
            for j in range(i+1, len(temp_array)):
                # print(str(-temp_array[i]) + " " + str(-temp_array[j]) + " 0" )
                str_array.append(str(-temp_array[i]) + " " + str(-temp_array[j]) + " 0")
        temp_array = []
        # print("0")

#----------------- PART 1 -------------------------------------
# generate a SAT sentence 
def make_queen_sat(N):
    # create an array to contain N-queen entries
    nq_array = make_nqueen_array(N)
    sat_sentence = ""
    # create a SAT sentence
    row_constraints(N, nq_array)
    col_constraints(N, nq_array)
    diag_contraints(N, nq_array)

    # write to a file
    from_path = './' + str(N) + '_queen.txt'
    
    try:
        with open(from_path, 'w') as f:
            for lines in str_array:
                f.write(lines)
                sat_sentence += lines + "\n"
                f.write("\n")
            f.close()

        with open(from_path, 'r') as f:
            contents = f.readlines()
            clause_count = 0
            # count clauses
            for lines in contents:
                # print("Looking at: ", lines)
                if lines[0] != 'c':
                    clause_count += 1

            # print("Number of clauses:", clause_count)
            contents.insert(1, "p cnf " + str(N*N) + " " + str(clause_count) + "\n")
            f.close()

        with open(from_path, 'w') as f:
            contents = "".join(contents)
            f.write(contents)
            f.close()
        
        # with open(path, 'r') as f:
        #     contents = f.readlines()
        #     for line in contents:
        #         # print(line)
        #     f.close()

    except FileNotFoundError:
        print("File not Found")


    # run minisat to generate result
    to_path = './out_' + str(N)
    os.system('minisat '+ str(from_path) + " " + str(to_path))

    str_array.clear()

    return sat_sentence

# sol: string
def draw_queen_sat_sol(sol):

    if "UNSAT" in sol:
        print("no solution")

    else:
        sol = sol.strip() # remove a space at the end
        sol = sol[:-1] # remove 0
        num = ""
        num_array = []
        for ch in sol:
            if ch == ' ':
                # print("Number:", int(num))
                num_array.append(int(num))
                num = "" 
            num += ch

        # print("Numbers:", num_array)

        n = math.sqrt(abs(num_array[-1]))
        # print("n:",n)
        index = 0

        for numbers in num_array:
            index += 1
            
            if numbers < 0:
                print('.', end=" ")
            else:
                print('Q', end=" ")

            if (index % n == 0):
                print('\n')


def generate_multiple_SAT_files(start,end):
    for i in range(start,end):
        make_queen_sat(i)


def read_minisat_result_of_size(N):

    try:
        path = './out_' + str(N)
        with open(path, 'r') as f:
            contents = f.readlines()
            for line in contents:
                # print(line)
                if not ("SAT" in line and "UNSAT" not in line):
                    draw_queen_sat_sol(line)
            
            f.close()
    except FileNotFoundError:
        print("Minisat Output File not Found")


# 142 entries -> 5 threads
# thread 1 : 2 ~ 90
# thread 2: 91 ~ 110
# thread 3: 111 ~ 130
# thread 4: 131 ~ 144

# t1 = threading.Thread(target=generate_SAT_files(2,91))
# t2 = threading.Thread(target=generate_SAT_files(91,111))
# t3 = threading.Thread(target=generate_SAT_files(111,131))
# t4 = threading.Thread(target=generate_SAT_files(131,145))

# t1.start()
# t2.start()
# t3.start()
# t4.start()

