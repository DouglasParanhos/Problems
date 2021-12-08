'''
--- Part Two ---

Through a little deduction, you should now be able to determine the remaining digits. Consider again the first example above:

acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab |
cdfeb fcadb cdfeb cdbaf

After some careful analysis, the mapping between signal wires and segments only make sense in the following configuration:

 dddd
e    a
e    a
 ffff
g    b
g    b
 cccc

So, the unique signal patterns would correspond to the following digits:

    acedgfb: 8
    cdfbe: 5
    gcdfa: 2
    fbcad: 3
    dab: 7
    cefabd: 9
    cdfgeb: 6
    eafb: 4
    cagedb: 0
    ab: 1

Then, the four digits of the output value can be decoded:

    cdfeb: 5
    fcadb: 3
    cdfeb: 5
    cdbaf: 3

Therefore, the output value for this entry is 5353.

Following this same process for each entry in the second, larger example above, the output value of each entry can be determined:

    fdgacbe cefdb cefbgd gcbe: 8394
    fcgedb cgb dgebacf gc: 9781
    cg cg fdcagb cbg: 1197
    efabcd cedba gadfec cb: 9361
    gecf egdcabf bgf bfgea: 4873
    gebdcfa ecba ca fadegcb: 8418
    cefg dcbef fcge gbcadfe: 4548
    ed bcgafe cdgba cbgef: 1625
    gbdfcae bgc cg cgb: 8717
    fgae cfgab fg bagce: 4315

Adding all of the output values in this larger example produces 61229.

For each entry, determine all of the wire/segment connections and decode the four-digit output values. What do you get if you add up all of the output values?
R: 1074888
'''

import time
import tracemalloc

start_time = time.time()
tracemalloc.start()

#####################################################################
import numpy as np
import itertools as it

'''
    000
   1   2
   1   2
    333
   4   5
   4   5
    666
'''

def decodeSingleDigit(letterSegmentMap, number):
    segments = ''

    for i in number:
        segments += str(letterSegmentMap[i])

    return segments


def decodeFinalNumber(possibleLettersPerSegment, digits):
    finalNumber = ''

    translationDigitSegment = {
        '012456': 0,
        '25': 1,
        '02346': 2,
        '02356': 3,
        '1235': 4,
        '01356': 5,
        '013456': 6,
        '025': 7,
        '0123456': 8,
        '012356': 9
    }

    for number in digits.split(" "):
        digits = str(decodeSingleDigit(possibleLettersPerSegment, number))

        for permut in list(it.permutations(digits)):
            try:
                finalNumber += str(translationDigitSegment[''.join(list(permut))])
            except KeyError:
                continue

    if finalNumber == '':
        print(digits)
    
    return int(finalNumber)

def decode(parts):

    mapLens = {}
    possibleLettersPerSegment = {}
    letters = ['a','b','c','d','e','f','g']
    lettersAppeard = []

    for input in parts[0].split(" "):
        try:
            mapLens[len(input)].append(input)
        except KeyError:
            mapLens[len(input)] = []
            mapLens[len(input)].append(input)

    #2 segments first, to determine which letters segments 2 and 5 can be
    possibleLettersPerSegment[2] = list(mapLens[2][0])
    possibleLettersPerSegment[5] = list(mapLens[2][0])
    lettersAppeard.extend(possibleLettersPerSegment[2])

    #Now segment 0 can be only one letter
    possibleLettersPerSegment[0] = list(np.setdiff1d(list(mapLens[3][0]), list(mapLens[2][0])))
    lettersAppeard.extend(possibleLettersPerSegment[0])

    #4 segments, to determine which letters segments 1 and 3 can be
    possibleLettersPerSegment[1] = list(np.setdiff1d(list(mapLens[4][0]), possibleLettersPerSegment[2]))
    possibleLettersPerSegment[3] = list(np.setdiff1d(list(mapLens[4][0]), possibleLettersPerSegment[2]))
    lettersAppeard.extend(possibleLettersPerSegment[1])

    #Determine the missing letters to be in segments 4 and 6
    possibleLettersPerSegment[4] = list(np.setdiff1d(letters, lettersAppeard))
    possibleLettersPerSegment[6] = list(np.setdiff1d(letters, lettersAppeard))

    #Get segments with len = 5 to determine segments 1, 3, 4, 6
    for element in mapLens[5]:
        letters = list(element)

        if set(possibleLettersPerSegment[2]).issubset(set(letters)):
            letters = list(np.setdiff1d(letters, possibleLettersPerSegment[2]))
            letters.extend(np.setdiff1d(letters, possibleLettersPerSegment[0]))
            possibleLettersPerSegment[3] = list(set(possibleLettersPerSegment[3]).intersection(letters))
            possibleLettersPerSegment[1] = np.setdiff1d(possibleLettersPerSegment[1], possibleLettersPerSegment[3])
            possibleLettersPerSegment[6] = list(set(possibleLettersPerSegment[6]).intersection(letters))
            possibleLettersPerSegment[4] = np.setdiff1d(possibleLettersPerSegment[4], list(possibleLettersPerSegment[6]))
            
            break

    #Get segments with len = 6 to determine segments 2, 5
    for element in mapLens[6]:
        letters = list(element)
        if not set(possibleLettersPerSegment[2]).issubset(set(letters)):
            possibleLettersPerSegment[2] = np.setdiff1d(possibleLettersPerSegment[2], list(element))
            possibleLettersPerSegment[5] = np.setdiff1d(possibleLettersPerSegment[5], possibleLettersPerSegment[2])

    #There's no need to have lists in values anymore, i already know all chars
    for i in possibleLettersPerSegment.keys():
        possibleLettersPerSegment[i] = possibleLettersPerSegment[i][0]

    return decodeFinalNumber({v: k for k, v in possibleLettersPerSegment.items()}, parts[1])

sum = 0
contador = 0
f = open("lights.txt", "r")
for line in f:
    line = line.replace("\n","")

    if not line:
        continue

    part = line.split(" | ")
    contador += 1
    sum += decode(part)

else:
    print(sum)


#####################################################################

print("--- %s miliseconds ---" % ((time.time() - start_time)*1000))
print("--- %s MB used ---" % (tracemalloc.get_traced_memory()[1]/1000000))

tracemalloc.stop()