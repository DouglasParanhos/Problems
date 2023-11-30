'''
--- Part Two ---

Considering every single measurement isn't as useful as you expected: there's just too much noise in the data.

Instead, consider sums of a three-measurement sliding window. Again considering the above example:

199  A      
200  A B    
208  A B C  
210    B C D
200  E   C D
207  E F   D
240  E F G  
269    F G H
260      G H
263        H

Start by comparing the first and second three-measurement windows. The measurements in the first window are marked A (199, 200, 208); their sum is 199 + 200 + 208 = 607. The second window is marked B (200, 208, 210); its sum is 618. The sum of measurements in the second window is larger than the sum of the first, so this first comparison increased.

Your goal now is to count the number of times the sum of measurements in this sliding window increases from the previous sum. So, compare A with B, then compare B with C, then C with D, and so on. Stop when there aren't enough measurements left to create a new three-measurement sum.

In the above example, the sum of each three-measurement window is as follows:

A: 607 (N/A - no previous sum)
B: 618 (increased)
C: 618 (no change)
D: 617 (decreased)
E: 647 (increased)
F: 716 (increased)
G: 769 (increased)
H: 792 (increased)

In this example, there are 5 sums that are larger than the previous sum.

Consider sums of a three-measurement sliding window. How many sums are larger than the previous sum?
R: 1262
'''

import time
import tracemalloc

start_time = time.time()
tracemalloc.start()
#####################################################################

import queue

q1 = queue.Queue(3)
previousSum = 0
depthIncreases = 0

#Need a smarter way to sum: without removing them from queue and adding again
def sumQueue(queue):
    val1 = q1.get()
    val2 = q1.get()
    val3 = q1.get()
    q1.put(val1)
    q1.put(val2)
    q1.put(val3)

    return val1 + val2 + val3

f = open("depths.txt", "r")
for line in f:
    try:
        actualNum = int(line.replace("\n",""))
    except:
        break

    if q1.full() and previousSum > 0:
        q1.get()
        q1.put(actualNum)
        sumQ1 = sumQueue(q1)

        if sumQ1 > previousSum:
            depthIncreases = depthIncreases + 1
        
        previousSum = sumQ1

    elif q1.full() and previousSum == 0:
        previousSum = sumQueue(q1)
        q1.get()
        q1.put(actualNum)
        

    else:
        q1.put(actualNum)

print(depthIncreases)

#####################################################################

print("--- %s miliseconds ---" % ((time.time() - start_time)*1000))
print("--- %s MB used ---" % (tracemalloc.get_traced_memory()[1]/1000000))

tracemalloc.stop()