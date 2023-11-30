'''
--- Part Two ---

Next, you need to find the largest basins so you know what areas are most important to avoid.

A basin is all locations that eventually flow downward to a single low point. Therefore, every low point has a basin, although some basins are very small. Locations of height 9 do not count as being in any basin, and all other locations will always be part of exactly one basin.

The size of a basin is the number of locations within the basin, including the low point. The example above has four basins.

The top-left basin, size 3:

2199943210
3987894921
9856789892
8767896789
9899965678

The top-right basin, size 9:

2199943210
3987894921
9856789892
8767896789
9899965678

The middle basin, size 14:

2199943210
3987894921
9856789892
8767896789
9899965678

The bottom-right basin, size 9:

2199943210
3987894921
9856789892
8767896789
9899965678

Find the three largest basins and multiply their sizes together. In the above example, this is 9 * 14 * 9 = 1134.

What do you get if you multiply together the sizes of the three largest basins?
R: 1417248
'''

import time
import tracemalloc

start_time = time.time()
tracemalloc.start()

#####################################################################
import numpy as np

ARRAY_SIZE = 100

smokeMap = np.zeros(shape=(ARRAY_SIZE,ARRAY_SIZE))
lowPoints = []
size = 0

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
    global lowPoints

    for i in range(ARRAY_SIZE):
        for j in range(ARRAY_SIZE):
            if isLowPoint(i,j):
                lowPoints.append([i,j])

def getPointsSubBasin(x, y):
    global smokeMap
    global size

    if smokeMap[x][y] != -1:
        size += 1
        smokeMap[x][y] = -1

    if exist(x - 1, y) and \
       smokeMap[x - 1][y] != 9 and \
       smokeMap[x - 1][y] != -1:
        getPointsSubBasin(x - 1, y)
    
    if exist(x + 1, y) and \
       smokeMap[x + 1][y] != 9 and \
       smokeMap[x + 1][y] != -1:
        getPointsSubBasin(x + 1, y)
    
    if exist(x, y - 1) and \
       smokeMap[x][y - 1] != 9 and \
       smokeMap[x][y - 1] != -1:
        getPointsSubBasin(x, y - 1)

    if exist(x, y + 1) and \
       smokeMap[x][y + 1] != 9 and \
       smokeMap[x][y + 1] != -1:
        getPointsSubBasin(x, y + 1)

def maxBasinsSizes():

    global lowPoints
    global size
    getLowPoints()

    basinsSizes = []

    for lowPoint in lowPoints:
        size = 0
        getPointsSubBasin(lowPoint[0], lowPoint[1])
        basinsSizes.append(size)

    return sorted(basinsSizes)[-3:]

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
    finalValue = 1
    for x in maxBasinsSizes():
        finalValue *= x

    print(finalValue)



#####################################################################

print("--- %s miliseconds ---" % ((time.time() - start_time)*1000))
print("--- %s MB used ---" % (tracemalloc.get_traced_memory()[1]/1000000))

tracemalloc.stop()