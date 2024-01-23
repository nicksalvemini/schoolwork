"""
Nick Salvemini
HW 09
Uses a dictionary and dataclass to process a large dataset
"""

from dataclasses import dataclass

@dataclass(frozen=True)
class Birthday:
    """
    Birthday represents a date
        month is the month of the date
        day is the day of the date
        year is the year of the date
    frozen=True in the decorator makes
        each instance of the dataclass
        immutable so that a Birthday can
        be used as a key in a dictionary
    """
    month: str
    day: int
    year: int

def buildDictionary(filename):
    """
    Reads a file and builds a dictionary
    with each unique birthday being a key,
    and each birthday's number of occurrences being
    that Birthday key's respective value
    :param filename: Path to the file containing
                        all of the birthdays in
                        MMM DD YYYY format
    :return: Returns a dictionary with each key
                being a unique and immutable instance
                of the Birthday dataclass, and each value
                being the number of occurrences of its
                respective Birthday key
    """
    birthdays = {}
    for line in open(filename):
        m, d, y = line.split()
        birthday = Birthday(m, d, y)

        if birthday not in birthdays:
            birthdays[birthday] = 1
        else:
            birthdays[birthday] += 1
    return birthdays

def birthdaysAtLeast(bdCounts, minCount):
    """
    Takes a dictionary with values being ints
    and builds a list containing only the keys
    corresponding to values higher than a certain
    minimum value
    :param bdCounts: Dictionary with keys as Birthdays
                        and values as ints
    :param minCount: Minimum number a value can be for
                        its key to be added to the list
    :return: Returns the list of keys that meet the
                value requirement
    """
    bdsAtLeast = []
    for key in bdCounts:
        if bdCounts[key] >= minCount:
            bdsAtLeast += [key]
    return bdsAtLeast

def toStrings(birthdayLst):
    """
    Takes a list where each element is
    a Birthday, and creates a new list full
    of neater strings that presents the same
    information in a cleaner way
    :param birthdayLst: List of Birthdays
    :return: Returns a neater list with each
                element in the format MM/DD/YYYY
    """
    monDct = {'JAN': 1, 'FEB': 2, 'MAR': 3,
              'APR': 4, 'MAY': 5, 'JUN': 6,
              'JUL': 7, 'AUG': 8, 'SEP': 9,
              'OCT': 10, 'NOV': 11, 'DEC': 12}
    strLst = []
    for el in birthdayLst:
        m = el.month
        d = el.day
        y = el.year
        bdayStr = str(monDct[m]) + '/' + str(d) + '/' + str(y)
        strLst += [bdayStr]
    return strLst

def printInfo(birthdays):
    """
    Prints the list of birthdays that
    occurred enough times. Prints both
    the original version and the neat
    version
    :param birthdays: List of Birthdays that had
                        enough occurrences
    """
    print(birthdays)
    print()
    print(toStrings(birthdays))

def main():
    """
    Prompts user for file name containing all the birthdays
    and prompts for a minimum number of occurrences. Calls
    functions to build a full dictionary and a smaller list
    containing only certain birthdays, based on whatever value
    was input for the minCount. Calls a function to print the data
    """
    birthdaysDct = buildDictionary(input('Enter the file name containing the birthdays: '))
    minCount = int(input('Enter a minimum count: '))
    bdsAtLeast = birthdaysAtLeast(birthdaysDct, minCount)
    print('\nBirthdays that occurred at least ' + str(minCount) + ' times:\n')
    printInfo(bdsAtLeast)

if __name__ == '__main__':
    main()