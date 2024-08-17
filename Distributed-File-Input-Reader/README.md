# Chord-DHT
A simple chord distributed hash table programmed in Python 

# Description
<pre>
This program reads inputs from a txt.file formatted as follows:

S // Hash space [0 ... 2^S-1] 
N // Number of joined nodes
M // Number of keys
id1, id2, ... , idN // Hashed node ids, joined in order
k1,k2, ... ,kM // Hashed keys, joined in order
k,id // key, query node id
k,id // key, query node id
...
-1,-1 // end

The output indicates the successor table of the lst joined node. Then, the query path for the given pair of key and query node id.

# Example
Assume a hash space [0...7]
1. Node n1 joins
2. Node n2 joins
3. Nodes n0, n6 join
4. keys f7,f1

the input will be 
3
4
2
1,2,0,6
7,1
7,1
-1,-1

the out will be 
0 7 0
1 0 0
2 2 2
1 6 0
</pre>
# For more info
https://en.wikipedia.org/wiki/Chord_(peer-to-peer)
