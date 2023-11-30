'''
--- Part Two ---

Unfortunately, considering only horizontal and vertical lines doesn't give you the full picture; you need to also consider diagonal lines.

Because of the limits of the hydrothermal vent mapping system, the lines in your list will only ever be horizontal, vertical, or a diagonal line at exactly 45 degrees. In other words:

    An entry like 1,1 -> 3,3 covers points 1,1, 2,2, and 3,3.
    An entry like 9,7 -> 7,9 covers points 9,7, 8,8, and 7,9.

Considering all lines from the above example would now produce the following diagram:

1.1....11.
.111...2..
..2.1.111.
...1.2.2..
.112313211
...1.2....
..1...1...
.1.....1..
1.......1.
222111....

You still need to determine the number of points where at least two lines overlap. In the above example, this is still anywhere in the diagram with a 2 or larger - now a total of 12 points.

Consider all of the lines. At how many points do at least two lines overlap?
R: 15463
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
    elif max(x0, x1) - min(x0, x1) == max(y0, y1) - min(y0, y1):
        for i in range((max(x0, x1) - min(x0, x1)) + 1):
            if x1 > x0 and y1 > y0:
                winds[x0 + i][y0 + i] += 1
            elif x1 > x0 and y1 < y0:
                winds[x0 + i][y0 - i] += 1
            elif x1 < x0 and y1 > y0:
                winds[x0 - i][y0 + i] += 1
            else:
                winds[x0 - i][y0 - i] += 1

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