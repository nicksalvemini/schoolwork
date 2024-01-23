"""
Nick Salvemini
HW 07
Compares the execution time of two list search algorithms
"""

import time as t
import binary_search as bs
import linear_search as ls

def readFile(file):
    """
    Reads a file and builds a list of strings with each
    element of the list being a line from the file.
    Creates a second list containing all the same words
    plus some phonies that are not in the original list to be
    used as test values for analysis
    :param file: File containing sorted word list
    :return: Returns both lists, one containing the words from the
                original file, the other containing all those words and
                phonies
    """
    testWords = []
    data = []
    i = 0
    for line in open(file):
        data += [line.strip()]
        testWords += [line.strip()]
        if i % 10 == 0:
            testWords += [line[::-1].strip()]
        i += 1
    testWords.sort()
    return data, testWords

def compare(data, target):
    """
    Uses the timer to find execution times for both
    the binary and linear search methods for a given data
    list and target value
    :param data: Data list
    :param target: Value to be found in data list
    :return: Returns time taken in msec for each search method
                to find the index of the target value
    """
    start = t.perf_counter()
    bs.getIndex(data, target)
    elapsedBinary = t.perf_counter() - start

    start = t.perf_counter()
    ls.getIndex(data, target)
    elapsedLinear = t.perf_counter() - start

    return (elapsedBinary * 1000), (elapsedLinear * 1000)

def analyze(data, targets):
    """
    Creates lists for all the execution times in order
    to calculate the average execution time and displays the
    efficiency of each method
    :param data: Data list
    :param targets: List of words to be used as targets, large to
                    have more accurate average execution times for
                    each method
    :return: Returns average execution time for binary search and
                linear search
    """
    linTimes = []
    binTimes = []

    for el in targets:
        binaryTime, linearTime = compare(data, el)
        binTimes += [binaryTime]
        linTimes += [linearTime]

    binTot = 0
    linTot = 0

    for i in range(0, len(binTimes)):
        binTot += binTimes[i]
        linTot += linTimes[i]
    return (binTot / len(binTimes)), (linTot / len(linTimes))

def main():
    """
    Calls function to read contents of file and prints information
    to tell the user about efficiency of search methods
    """

    data, testWords = readFile(input('Enter file name containing data: '))

    print('Data set size: ', len(data))
    print('Number of test words (including phonies): ', len(testWords))
    print('Analyzing time difference...')

    binAvg, linAvg = analyze(data, testWords)
    print('Binary search average time: ', binAvg, 'msec')
    print('Linary search average time: ', linAvg, 'msec')
    print('Binary search is', linAvg / binAvg, 'times faster than the linear '
                                               'search for this data set.')

if __name__ == '__main__':
    main()