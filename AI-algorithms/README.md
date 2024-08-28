# aima_python_practices
N-Queens Problem, CSP problem, Minisat

The following files are to explore the Ice Breaker Problem: given a group of n people, what's the minimum number of teams they can be partitioned into such that
no team has 2 (or more) people who are friends. 

# Premise
n people are named 0, 1, 2, …, n−1, and that we have what we will call a friendship graph, i.e. a graph with n nodes labeled 0 to n−1 (each node is a person), where nodes i and j are connected by an edge just when i and j are friends. 
Friendship is symmetric: if i is a friend of j, then j is a friend of i. Plus a person cannot be friends with themselves.

# N-queens with SAT
use minisat to solve some constraint satisfaction problems.

> make_queen_sat(N) generates a SAT sentence (as a Python string) that, when satisfied, will be a solution to the N-queens problem.
> draw_queen_sat_sol(sol) takes the output of minsat as a string and draws the resulting N-queens solution on the screen (using print). 
> make_ice_breaker_sat(graph, k) takes a friendship graph as input (see assignment 2) and a positive integer k representing the number of possible teams (i.e. each node can be one of k colors). It returns, as a Python string, an encoding as a SAT problem that can be used as input to minisat.
> find_min_teams(graph) uses minisat to find the (exact) minimum number of teams the people can be divided into such that no team has any friends (teams of 1 are permitted). find_min_teams(graph) returns a single integer: the (exact) minimum number of teams.
