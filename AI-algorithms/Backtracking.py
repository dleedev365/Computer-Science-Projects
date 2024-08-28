# Author: Daniel

from functions import *
from csp import *
import time


class InstruCSP(CSP):

    def __init__(self, variables, domains, neighbors, constraints):
        super().__init__(variables, domains, neighbors, constraints)
        self.count = 0


    def unassign(self, var, assignment):
        super().unassign(var, assignment)
        self.count += 1

def make_instru(csp):
    return InstruCSP(csp.variables, csp.domains, csp.neighbors, csp.constraints)


def run_q3():

    for run in range(5):
        n = 30
        graphs = [rand_graph(n, 0.1), rand_graph(n, 0.2), rand_graph(n, 0.3), rand_graph(n, 0.4), rand_graph(n, 0.5)]
        colours = []
        itr = 0

        for g in graphs:
            variables = list(g.keys())
            colours.clear()
            itr += 1
            print("============================ [",run+1,"] Run / Backtracking Graph [", itr,"] ===============================")
            for i in range(len(variables)):
                colours.append(variables[i])
                # csp = MapColoringCSP(colours, g)
                csp = make_instru(MapColoringCSP(colours, g))

                start = time.time()
                bts = backtracking_search(csp, select_unassigned_variable=mrv, order_domain_values=lcv, inference=mac)   
                end = time.time()

                if bts != None:
                    print("Result:",bts)
                    print("Teams:", len(set(list(bts.values()))))
                    total_people_count = 0

                    for teams in set(list(bts.values())):
                        members_count = list(bts.values()).count(teams)
                        total_people_count += members_count 
                        print("Team [", teams,"] has ",  members_count, " members")     

                    print("Total # of people assigned to teams:", total_people_count)
                    print("Valid teams?:", check_teams(g,bts))
                    print("Variables Assigned:",csp.nassigns,", Variables Unassigned:",csp.count)
                    print("Total Time:", end-start)
                    break
                else:
                    print("Not enough colours:", colours)
                    # print("Total Time:", end-start)

run_q3()