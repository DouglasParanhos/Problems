'''
--- Day 4: Giant Squid ---

You're already almost 1.5km (almost a mile) below the surface of the ocean, already so deep that you can't see any sunlight. What you can see, however, is a giant squid that has attached itself to the outside of your submarine.

Maybe it wants to play bingo?

Bingo is played on a set of boards each consisting of a 5x5 grid of numbers. Numbers are chosen at random, and the chosen number is marked on all boards on which it appears. (Numbers may not appear on all boards.) If all numbers in any row or any column of a board are marked, that board wins. (Diagonals don't count.)

The submarine has a bingo subsystem to help passengers (currently, you and the giant squid) pass the time. It automatically generates a random order in which to draw numbers and a random set of boards (your puzzle input). For example:

7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1

22 13 17 11  0
 8  2 23  4 24
21  9 14 16  7
 6 10  3 18  5
 1 12 20 15 19

 3 15  0  2 22
 9 18 13 17  5
19  8  7 25 23
20 11 10 24  4
14 21 16 12  6

14 21 17 24  4
10 16 15  9 19
18  8 23 26 20
22 11 13  6  5
 2  0 12  3  7

After the first five numbers are drawn (7, 4, 9, 5, and 11), there are no winners, but the boards are marked as follows (shown here adjacent to each other to save space):

22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
 8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
 6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
 1 12 20 15 19        14 21 16 12  6         2  0 12  3  7

After the next six numbers are drawn (17, 23, 2, 0, 14, and 21), there are still no winners:

22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
 8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
 6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
 1 12 20 15 19        14 21 16 12  6         2  0 12  3  7

Finally, 24 is drawn:

22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
 8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
 6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
 1 12 20 15 19        14 21 16 12  6         2  0 12  3  7

At this point, the third board wins because it has at least one complete row or column of marked numbers (in this case, the entire top row is marked: 14 21 17 24 4).

The score of the winning board can now be calculated. Start by finding the sum of all unmarked numbers on that board; in this case, the sum is 188. Then, multiply that sum by the number that was just called when the board won, 24, to get the final score, 188 * 24 = 4512.

To guarantee victory against the giant squid, figure out which board will win first. What will your final score be if you choose that board?
R: 16674
'''

import time
import tracemalloc

start_time = time.time()
tracemalloc.start()

#####################################################################

boards = []
numbers = []
MATRIX_SIZE = 5 #it's squared, we need only one value

class Element:

    def __init__(self, value):
        self.value = value
        self.drawn = False

    def setDrawn(self):
        self.drawn = True

class Board(object):

    def __init__(self, values):
        self.matrix = [[ 0 for i in range(MATRIX_SIZE)] for j in range(MATRIX_SIZE)]
        for i in range(MATRIX_SIZE):
            for j in range(MATRIX_SIZE):
                self.matrix[i][j] = Element(int(values[i][j]))

    def bingo(self):
        return self.bingoHorizontally() or self.bingoVertically()

    def getSumAllValuesNotDrawn(self):
        sum = 0
        for i in range(MATRIX_SIZE):
            for j in range(MATRIX_SIZE):
                sum += self.matrix[i][j].value if not self.matrix[i][j].drawn else 0

        return sum

    def bingoHorizontally(self):
        for i in range(MATRIX_SIZE):
            bingo = 0
            for j in range(MATRIX_SIZE):
                bingo += 1 if self.matrix[i][j].drawn else 0
            
            if bingo == MATRIX_SIZE:
                return True

        return False

    def bingoVertically(self):
        for i in range(MATRIX_SIZE):
            bingo = 0
            for j in range(MATRIX_SIZE):
                bingo += 1 if self.matrix[j][i].drawn else 0
            
            if bingo == MATRIX_SIZE:
                return True

        return False

    def draw(self, numberDrawn):
        for i in range(MATRIX_SIZE):
            for j in range(MATRIX_SIZE):
                if self.matrix[i][j].value == numberDrawn:
                    self.matrix[i][j].setDrawn()
 
def draw(numberDrawn):
    for board in boards:
        board.draw(numberDrawn)

def anyBingo():
    for board in boards:
        if board.bingo():
            return board

    return False

def startDrawn():
    for i in numbers:
        number = int(i)
        draw(number)
        bingoBoard = anyBingo()
        if bingoBoard:
            return bingoBoard.getSumAllValuesNotDrawn() * number

values = []
lineNumber = 0
f = open("bingo.txt", "r")
for line in f:
    line = line.replace("\n","")

    if not numbers:
        numbers = line.split(",")
        continue

    if line:
        line = line.split(" ")
        line = [i for i in line if i]
        values.append(line)
        lineNumber += 1
        if lineNumber == MATRIX_SIZE:
            boards.append(Board(values))
            values = []
            lineNumber = 0

else:
    print(startDrawn())

#####################################################################

print("--- %s miliseconds ---" % ((time.time() - start_time)*1000))
print("--- %s MB used ---" % (tracemalloc.get_traced_memory()[1]/1000000))

tracemalloc.stop()