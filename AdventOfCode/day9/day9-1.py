'''
--- Day 9: Smoke Basin ---

These caves seem to be lava tubes. Parts are even still volcanically active; small hydrothermal vents release smoke into the caves that slowly settles like rain.

If you can model how the smoke flows through the caves, you might be able to avoid it and be that much safer. The submarine generates a heightmap of the floor of the nearby caves for you (your puzzle input).

Smoke flows to the lowest point of the area it's in. For example, consider the following heightmap:

2199943210
3987894921
9856789892
8767896789
9899965678

Each number corresponds to the height of a particular location, where 9 is the highest and 0 is the lowest a location can be.

Your first goal is to find the low points - the locations that are lower than any of its adjacent locations. Most locations have four adjacent locations (up, down, left, and right); locations on the edge or corner of the map have three or two adjacent locations, respectively. (Diagonal locations do not count as adjacent.)

In the above example, there are four low points, all highlighted: two are in the first row (a 1 and a 0), one is in the third row (a 5), and one is in the bottom row (also a 5). All other locations on the heightmap have some lower adjacent location, and so are not low points.

The risk level of a low point is 1 plus its height. In the above example, the risk levels of the low points are 2, 1, 6, and 6. The sum of the risk levels of all low points in the heightmap is therefore 15.

Find all of the low points on your heightmap. What is the sum of the risk levels of all low points on your heightmap?
R: 448
'''

import time
import tracemalloc

start_time = time.time()
tracemalloc.start()

#####################################################################
import numpy as np

ARRAY_SIZE = 100

smokeMap = np.zeros(shape=(ARRAY_SIZE,ARRAY_SIZE))

def exist(x, y):
    global smokeMap

    if x >=0 and x < ARRAY_SIZE and y >= 0 and y < ARRAY_SIZE:
        return True

    return False

def isLowPoint(x,y):

    global smokeMap

    countTrues = 0

    if exist(x - 1, y):
        countTrues += 1 if smokeMap[x][y] < smokeMap[x - 1][y] else 0
    else:
        countTrues += 1
    
    if exist(x + 1, y):
        countTrues += 1 if smokeMap[x][y] < smokeMap[x + 1][y] else 0
    else:
        countTrues += 1
    
    if exist(x, y - 1):
        countTrues += 1 if smokeMap[x][y] < smokeMap[x][y - 1] else 0
    else:
        countTrues += 1

    if exist(x, y + 1):
        countTrues += 1 if smokeMap[x][y] < smokeMap[x][y + 1] else 0
    else:
        countTrues += 1

    return countTrues == 4

def getLowPoints():
    global smokeMap

    lowPoints = 0

    for i in range(ARRAY_SIZE):
        for j in range(ARRAY_SIZE):
            lowPoints += (smokeMap[i][j] + 1) if isLowPoint(i,j) else 0

    return int(lowPoints)


count = 0
f = open("smoke.txt", "r")
for line in f:
    line = line.replace("\n","")

    if not line:
        continue

    smokes = list(line)

    for i in range(len(smokes)):
        smokeMap[count][i] = smokes[i]

    count += 1

else:
    print(getLowPoints())


#####################################################################

print("--- %s miliseconds ---" % ((time.time() - start_time)*1000))
print("--- %s MB used ---" % (tracemalloc.get_traced_memory()[1]/1000000))

tracemalloc.stop()