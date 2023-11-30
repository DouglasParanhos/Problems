'''
--- Day 5: Hydrothermal Venture ---

You come across a field of hydrothermal vents on the ocean floor! These vents constantly produce large, opaque clouds, so it would be best to avoid them if possible.

They tend to form in lines; the submarine helpfully produces a list of nearby lines of vents (your puzzle input) for you to review. For example:

0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2

Each line of vents is given as a line segment in the format x1,y1 -> x2,y2 where x1,y1 are the coordinates of one end the line segment and x2,y2 are the coordinates of the other end. These line segments include the points at both ends. In other words:

    An entry like 1,1 -> 1,3 covers points 1,1, 1,2, and 1,3.
    An entry like 9,7 -> 7,7 covers points 9,7, 8,7, and 7,7.

For now, only consider horizontal and vertical lines: lines where either x1 = x2 or y1 = y2.

So, the horizontal and vertical lines from the above list would produce the following diagram:

.......1..
..1....1..
..1....1..
.......1..
.112111211
..........
..........
..........
..........
222111....

In this diagram, the top left corner is 0,0 and the bottom right corner is 9,9. Each position is shown as the number of lines which cover that point or . if no line covers that point. The top-left pair of 1s, for example, comes from 2,2 -> 2,1; the very bottom row is formed by the overlapping lines 0,9 -> 5,9 and 0,9 -> 2,9.

To avoid the most dangerous areas, you need to determine the number of points where at least two lines overlap. In the above example, this is anywhere in the diagram with a 2 or larger - a total of 5 points.

Consider only horizontal and vertical lines. At how many points do at least two lines overlap?
R: 5698
'''

import time
import tracemalloc

start_time = time.time()
tracemalloc.start()

#####################################################################

import numpy as np

winds = np.zeros(shape=(1000,1000))

def defineWinds(x0, y0, x1, y1):
    global winds

    if x0 == x1:
        for i in range(min(y0, y1), max(y0,y1) + 1):
            winds[x0][i] += 1
    elif y0 == y1:
        for i in range(min(x0, x1), max(x0,x1) + 1):
            winds[i][y0] += 1


def getOverlaps():
    global winds

    values = 0
    for i in range(1000):
        for j in range(1000):
            values += 1 if winds[i][j] > 1 else 0

    return values

f = open("winds.txt", "r")
for line in f:
    line = line.replace("\n","")

    if not line:
        continue

    parts = line.split(" -> ")
    part0 = parts[0].split(",")
    part1 = parts[1].split(",")
    defineWinds(int(part0[0]), int(part0[1]), int(part1[0]), int(part1[1]))

else:
    print(getOverlaps())

#####################################################################

print("--- %s miliseconds ---" % ((time.time() - start_time)*1000))
print("--- %s MB used ---" % (tracemalloc.get_traced_memory()[1]/1000000))

tracemalloc.stop()