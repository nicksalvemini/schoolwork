"""
Nick Salvemini
Lab 05
Calls functions in other modules to calculate
optimal location for store and returns time information.
Doe snot sort list, uses quickSelect algorithm
"""

import tools
import time

def quickSelect(list, k):
    """
    Finds the (k+1))th smallest value in an unsorted list of data
    Precondition: k is in range 0 to len(list) - 1
    :param list: Data list
    :param k: Determines which value we are looking for
    :return: (k+1)th smallest value
    """
    pivot = list[len(list) // 2]
    smallerList = []
    largerList = []
    count = 0
    for el in list:
        if el < pivot:
            smallerList += [el]
        elif el == pivot:
            count += 1
        elif el > pivot:
            largerList += [el]
    m = len(smallerList)
    if k >= m and k < m + count:
        return pivot
    if m > k:
        return quickSelect(smallerList, k)
    else:
        return quickSelect(largerList, k - m - count)

def displayResults(optimal, total, elapsed):
    """
    Prints the optimal location and sum of
    distances to other buildings neatly
    :param optimal: Location of new store
    :param total: Total distance to other buildings
    """
    print('Optimal new store location:', optimal,
          '\nSum of distances to the new store:', total,
          '\nElapsed time:', elapsed, 'sec')

def main():
    """
    Prompts user for a file containing
    building locations, then times execution
    for placing building and calculating distance
    to all other buildings using the quickSelect
    algorithm
    """
    locations = tools.readFile(input("Enter file containing building locations: "))
    k = len(locations) // 2
    start = time.perf_counter()
    if len(locations) % 2 == 1:
        optimal = quickSelect(locations, k)
    else:
        optimal = (quickSelect(locations, k - 1) + quickSelect(locations, k)) / 2
    totDist = tools.calcDist(locations, optimal)
    elapsed = time.perf_counter()
    exeTime = elapsed - start

    displayResults(optimal, totDist, exeTime)

if __name__ == '__main__':
    main()