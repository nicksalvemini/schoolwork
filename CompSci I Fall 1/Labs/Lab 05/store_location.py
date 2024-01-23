"""
Nick Salvemini
Lab 05
Calls functions in other modules to calculate
optimal location for store and returns time information
"""

import tools
import insertion_sort as sort
import time

def placeBuilding(list):
    """
    Sorts a list of buildings and finds
    the optimal location for the store
    :param list: Building locations
    :return: Optimal location for store
    """
    sort.insertion_sort(list)
    if len(list) % 2 == 1:
        return list[len(list) // 2]
    else:
        return (list[len(list) // 2] + list[len(list) // 2 - 1]) / 2

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
    to all other buildings using the insertion_sort
    algorithm and using the index of the sorted list
    to find the median
    """
    locations = tools.readFile(input("Enter file containing building locations: "))

    start = time.perf_counter()
    optimal = placeBuilding(locations)
    totDist = tools.calcDist(locations, optimal)
    elapsed = time.perf_counter()
    exeTime = elapsed - start

    displayResults(optimal, totDist, exeTime)

if __name__ == '__main__':
    main()