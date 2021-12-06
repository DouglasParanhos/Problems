'''
--- Part Two ---

Suppose the lanternfish live forever and have unlimited food and space. Would they take over the entire ocean?

After 256 days in the example above, there would be a total of 26984457539 lanternfish!

How many lanternfish would there be after 256 days?
R: 1639643057051

'''

#I couldn't scape, had to do the clever solution:/

import time
import tracemalloc

start_time = time.time()
tracemalloc.start()

lanternfishes = {}

def initLanternfishes():
    global lanternfishes

    for i in range(9):
        lanternfishes[i] = 0

def getLanternfishesInitial(parts):
    global lanternfishes

    for i in parts:
        lanternfishes[int(i)] += 1

def decrementLanternFishes():
    global lanternfishes

    temp = lanternfishes[0]
    for i in range(8):
        lanternfishes[i] = lanternfishes[i + 1]
    
    lanternfishes[6] += temp
    lanternfishes[8] = temp

def getTotalLanternfishes():
    global lanternfishes

    sum = 0
    for i in lanternfishes.keys():
        sum += lanternfishes[i]

    return sum

f = open("lanternfishes.txt", "r")
for line in f:
    line = line.replace("\n","")

    if not line:
        continue

    parts = line.split(",")

    initLanternfishes()
    getLanternfishesInitial(parts)

else:
    for i in range(256):
        decrementLanternFishes()


print(getTotalLanternfishes())
print("--- %s miliseconds ---" % ((time.time() - start_time)*1000))
print("--- %s MB used ---" % (tracemalloc.get_traced_memory()[1]/1000))

tracemalloc.stop()