# Author: Daniel Lee

import random

HUMAN_SYMBOL = 'O'
COMPUTER_SYMBOL = 'X'

""" TEST CASES
a = [[1,'X',3],[4,'X','X'],['O','X','O']] column
a = [[1,'X',3],['X','X','X'],['O','X','O']] row
a = [[1,'O','O'],[4,'O','X'],['O','X','O']] diagnoal /
a = [['X',2,3],[4,'X','X'],['O','X','X']]  diangonal '\'
a = [['1','X','O'],['O','X','X'],['X','O','O']] continue
a = [['O','X','O'],['O','X','X'],['X','X','O']] tie
"""

def create_gameboard(n):
        t = []
        for i in range(n):
                r = []
                for j in range(n):
                        r.append(n*i+j+1)
                t.append(r)
        return t

def display_board(a):
        print()
        for i in range(len(a)):
                for e in range(len(a)):
                        print(a[i][e], end="\t")
                if i < len(a) - 1:
                        print()
        print()
        print()

def check_result(a,symbol):

        # print("CHECKING ROWS ...")
        symbol_count = 0

        for rows in range(len(a)):
                for cols in range(len(a)):
                        if a[rows][cols] == symbol:
                                symbol_count += 1
                if symbol_count == 3:
                        return "Win"
                
                symbol_count = 0


        # print("CHECKING COLUMNS ...")
        symbol_count = 0

        for cols in range(0,len(a)):
                for rows in range(0,len(a)):
                        if a[rows][cols] == symbol:
                                symbol_count += 1
                if symbol_count == 3:
                        return "Win"
                
                symbol_count = 0

        # print("CHECKING DIAGONALS / ...")
        symbol_count = 0

        for i in range(0, len(a)):
                # print(len(a)-i-1)
                if a[len(a)-i-1][i] == symbol:
                        symbol_count += 1

        if symbol_count == 3:
                return "Win"
        
        # print("CHECKING DIAGONALS \ ... ")
        symbol_count = 0
     
        for i in range(0, len(a)):
                if a[i][i] == symbol:
                        symbol_count += 1

        if symbol_count == 3:
                return "Win"
        
        # print("CHECKING TIE ... ")
        # check if it's a tie
        has_digit = False

        for rows in range(0,len(a)):
                for cols in range(0, len(a)):
                        if type(a[rows][cols]) == int:
                                has_digit = True
        
        if has_digit == False:
                return "Draw"
        else:
                return "Continue"

def valid_move(a,pos,symbol):
        if type(pos) == str:
                pos = int(pos)
        
        if pos % len(a) == 0:
                row_index = int(pos/len(a)) -1
        else:
                row_index = int(pos/len(a))
        
        col_index = int( (pos-1) % len(a) )
        # print(a[row_index][col_index], " is ", type(a[row_index][col_index]))

        if type(a[row_index][col_index]) == int and (a[row_index][col_index] > 0 and a[row_index][col_index] < len(a)*len(a)+1):
                return True
        
        return False

def get_location(a,pos):
        for rows in range(0,len(a)):
                for cols in range(0, len(a)):
                        if a[rows][cols] == pos:
                                return rows, cols

def make_move(a,pos,symbol):
        if type(pos) == str:
                pos = int(pos)

        row_index, col_index = get_location(a,pos)
        a[row_index][col_index] = symbol

def get_list_of_moves(a):
        slots = []
        for rows in range(len(a)):
                for e in a[rows]:
                        if type(e) == int:
                                slots.append(e)
        return slots

# ams = an array of available moves 
def random_playouts(ams, gameboard, num_playouts):
        stat_records = {} # {move:count}
        # for each move, do random playouts (randomly choos moves until it's over)
        for move in ams:
                stat_records.setdefault(move)

                # print("****** Running a simulation for move ", move," ******")   
                cpu_win_count = 0
                cpu_loss_count = 0 
                cpu_draw_count = 0
                playout_result = dict()

                for i in range(num_playouts):
                        playout_gameboard = list(map(list, gameboard))
                        temp_ams = list(ams)
                        iter = 0
                        while len(temp_ams) > 0:
                                # print("Available Options:", temp_ams)
                                # computer makes a move first
                                if iter == 0:
                                        computer_move = move 
                                else:
                                        computer_move = random.choice(temp_ams)
                                        
                                # print("<Computer Bot> chose ", computer_move)
                                temp_ams.remove(computer_move)
                                make_move(playout_gameboard, computer_move, COMPUTER_SYMBOL)
                                # display_board(playout_gameboard)

                                if check_result(playout_gameboard, COMPUTER_SYMBOL) == "Win":
                                        cpu_win_count += 1
                                        break

                                elif check_result(playout_gameboard, COMPUTER_SYMBOL) == "Draw" :
                                        cpu_draw_count += 1
                                        break

                                elif check_result(playout_gameboard, COMPUTER_SYMBOL) == "Continue":
                                        # human simulator randomly chooses a move
                                        human_bot_move = random.choice(temp_ams)                      
                                        # print("<Human Bot> chose ", human_bot_move)
                                        temp_ams.remove(human_bot_move)
                                        make_move(playout_gameboard, human_bot_move, HUMAN_SYMBOL)
                                        # display_board(playout_gameboard)

                                        if check_result(playout_gameboard, HUMAN_SYMBOL) == "Win":
                                                cpu_loss_count += 1
                                                break

                                        elif check_result(playout_gameboard, HUMAN_SYMBOL) == "Draw" :
                                                cpu_draw_count += 1
                                                break

                                        elif check_result(playout_gameboard, HUMAN_SYMBOL) == "Continue":
                                                iter += 1
                                                continue 
                                        else:
                                                # print("WIN COUNT ++")
                                                cpu_win_count += 1
                                                break
                                else:
                                        cpu_loss_count += 1
                                        break   
                                        
                        # print("Playout Result")
                        # display_board(playout_gameboard)

                playout_result["Win"] = cpu_win_count
                playout_result["Draw"] = cpu_draw_count
                playout_result["Loss"] = cpu_loss_count
                stat_records[move] = playout_result
                # print("PLAYOUT RESULT:", playout_result)
        
        print()
        # print("<STAT RECORDS>")
        # print("Format => Move: STAT RESULT")
        # print("------------------------------------------------------------")
        losses_array = []
        wins_array = []

        # get all the losses from the record
        for moves, stats in stat_records.items():
                # print(moves,":",stats)
                losses_array.append(stat_records[moves]['Loss'])
        
        print()
        la = sorted(losses_array)
        # print("Ascending Order of Losses:",la)
        least_losses = la[0]
        d_win = 0

        # for the same smallest number of losses, get their moves and choose the one with the highest win rate
        for moves,stats in stat_records.items():
                if stat_records[moves]['Loss'] == least_losses:
                        d_win = stat_records[moves]['Win']
                        wins_array.append(d_win)

        wa = sorted(wins_array)
        # print("Ascending Order of Wins for the least number of losses:",wa)       
        highest_wins = wa[len(wa)-1]
        # return the result
        for moves,stats in stat_records.items():
                if stat_records[moves]['Loss'] == least_losses and stat_records[moves]['Win'] == highest_wins:
                        return moves

def play_a_new_game():
        # Initialization
        n = 3
        playouts = 300
        gameboard = create_gameboard(n)

        print("********* [ WELCOME TO TIC-TAC-TOE GAME ] *********")
        print("*                                                 *")
        print("*          INFO: HUMAN (O), COMPUTER (X)          *")
        print("*                                                 *")
        print("*              < GAME TABLE FORMAT >              *")
        print("*                                                 *")
        print("*                    1  2  3                      *")
        print("*                    4  5  6                      *")
        print("*                    7  8  9                      *")
        print("*                                                 *")
        print("***************************************************")

        while True:
                # player makes a move
                pos = input("Please select a move:")

                while not valid_move(gameboard, pos, HUMAN_SYMBOL):
                        print("Invalid move!")
                        pos = input("Please select a move: ")

                make_move(gameboard,pos,HUMAN_SYMBOL)
                display_board(gameboard)

                if check_result(gameboard, HUMAN_SYMBOL) == "Win":
                        print("Human Won!")
                        break
                elif check_result(gameboard, HUMAN_SYMBOL) == "Draw":
                        print("Draw!")
                        break
                elif check_result(gameboard, HUMAN_SYMBOL) == "Continue":
                        computer_move = random_playouts(get_list_of_moves(gameboard), gameboard, playouts)
                        print("Computer chose: ", computer_move)
                        make_move(gameboard,computer_move,COMPUTER_SYMBOL)
                        display_board(gameboard)

                        if check_result(gameboard, COMPUTER_SYMBOL) == "Win":
                                print("Computer Won!")
                                break
                        elif check_result(gameboard, COMPUTER_SYMBOL) == "Draw":
                                print("Draw!")
                                break
                        elif check_result(gameboard, COMPUTER_SYMBOL) == "Continue":
                                continue
                        else:
                                print("Human Won!")
                                break
                else:
                        print("Computer Won!")
                        break


if __name__ == "__main__":
        play_a_new_game()
