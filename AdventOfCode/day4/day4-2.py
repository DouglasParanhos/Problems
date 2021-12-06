'''
--- Part Two ---

On the other hand, it might be wise to try a different strategy: let the giant squid win.

You aren't sure how many bingo boards a giant squid could play at once, so rather than waste time counting its arms, the safe thing to do is to figure out which board will win last and choose that one. That way, no matter which boards it picks, it will win for sure.

In the above example, the second board is the last to win, which happens after 13 is eventually called and its middle column is completely marked. If you were to keep playing until this point, the second board would have a sum of unmarked numbers equal to 148 for a final score of 148 * 13 = 1924.

Figure out which board will win last. Once it wins, what would its final score be?
R: 7075
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

    def __init__(self, id, values):
        self.id = id
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
    bingoBoards = []
    for board in boards:
        if board.bingo():
            bingoBoards.append(board)

    return bingoBoards

def startDrawn():
    for i in numbers:
        number = int(i)
        draw(number)
        bingoBoards = anyBingo()
        if bingoBoards:
            if len(boards) == 1:
                
                return bingoBoards[0].getSumAllValuesNotDrawn() * number
            else:
                for board in bingoBoards:
                    boards.remove(board)

values = []
lineNumber = 0
id = 0
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
            id += 1
            boards.append(Board(id, values))
            values = []
            lineNumber = 0

else:
    print(startDrawn())

#####################################################################

print("--- %s miliseconds ---" % ((time.time() - start_time)*1000))
print("--- %s MB used ---" % (tracemalloc.get_traced_memory()[1]/1000000))

tracemalloc.stop()