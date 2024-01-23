"""
Nick Salvemini
Lab 05
Contains functions that will be shared by other program
modules
"""

def readFile(file):
    """
    Reads a file of buildings and their locations
    and returns a list of their locations
    :param file: Path to file containing building locations
    :return: Returns list of building locations
    """
    locations = []
    for line in open(file):
        i = 1
        while line[-i] != ' ':
            i += 1
        locations += [int(line[len(line) - i + 1:])]
    return locations

def calcDist(list, loc):
    """
    Calculates the total distance from the best
    location to each other location
    :param list: List of building locations
    :param loc: Best location for store
    :return: Returns the total distance from the
                best location to each other location
    """
    sum = 0
    for el in list:
        sum += abs(el - loc)
    return sum