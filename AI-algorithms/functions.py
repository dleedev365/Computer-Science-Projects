# a2_q1.py

import random
import math
from csp import *

# Create a random graph of size n with probability, b
def rand_graph(n,p):
    variables = [x for x in range(n)] # a list of variables/people
    g = {var:[] for var in variables}

    for x in variables:
        for y in range(x+1,len(variables)):
            rp = random.random()
            if p >= rp:
                g[x].append(y)
                g[y].append(x)

    return g

# Teams of 1 person are permitted
def check_teams(graph, csp_sol):
    unique_teams = []
    variables_of_team = {}

    # 1. for each team in CSP solution, {var: val} = {var: team}
    for val in list(csp_sol.values()):
        # get unique teams
        if val not in unique_teams:
            unique_teams.append(val)

    for teams in unique_teams:
        variables_of_team[teams] = []
        
        # Find the vars of each team in csp_sol
        for var, team in csp_sol.items():
            if teams == team:
                variables_of_team[teams].append(var)
                
        
        for vars in variables_of_team[teams]:
            for variables in variables_of_team[teams]:

                if variables in list(graph[vars]):
                    return False

    return True
