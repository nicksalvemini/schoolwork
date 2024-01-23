"""
Nick Salvemini
HW 08
Compares the efficiency of sorting algorithms
"""

import sys
import time
import random
import quick_sort as quick
import merge_sort as merge
import merge_quick_sort as hybrid

def createList(size):
    """
    Creates a list of integers from 0 to size - 1
    Parameters
        size: Size of the list to be created
    Returns
        Returns the built list of integers
    """
    list = []
    for i in range(0, size):
        list += [i]
    return list

def compareSorts(list, type):
    """
    Finds the execution time for each sorting method
    Parameters
        list: List to be sorted
        type: String telling whether the list is already sorted
                or if it is in a random order
    """
    start = time.perf_counter()
    quick.quick_sort(list)
    elapsed = time.perf_counter() - start
    print('Quick sort (', type, ') elapsed time: ', elapsed * 1000, 'msec')

    start = time.perf_counter()
    merge.merge_sort(list)
    elapsed = time.perf_counter() - start
    print('Merge sort (', type, ') elapsed time: ', elapsed * 1000, 'msec')

    start = time.perf_counter()
    hybrid.merge_quick_sort(list)
    elapsed = time.perf_counter() - start
    print('Hybrid sort (', type, ') elapsed time: ', elapsed * 1000, 'msec')

def main():
    """
    Prompts user for size of list to be built,
    then calls for list to be built and calls functions to
    compare the execution times for different sort methods
    both before and after randomizing the list
    """
    size = int(input('Enter list size between 0 and 10000: '))
    if size + 10 > sys.getrecursionlimit():
        sys.setrecursionlimit(size + 10)
    lst = createList(size)
    compareSorts(lst, 'sorted')
    random.shuffle(lst)
    print()
    compareSorts(lst, 'random')

if __name__ == '__main__':
    main()