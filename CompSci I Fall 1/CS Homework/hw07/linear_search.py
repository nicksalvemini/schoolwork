"""
Nick Salvemini
HW 07
Searches a list for the index of a certain element linearly.
"""

def linearSearch(data, target, start, end):
    """
    Perform a linear search for a target value between start and end indices.
    Parameters:
        data - List of sorted data
        target - Target value to search for
        start - Starting index in data that is part of this search
        end - Ending index in data that is part of this search
    Returns:
        Index of target in data if present; otherwise None
    """

    check = start
    while check <= end:
        if data[check] == target:
            return check
        else:
            check += 1
    return None

def getIndex(data, target):
    """
    Returns the index of target in data or None if not target found.
    Parameters:
        data - List of sorted data
        target - Target value to search for
    Returns:
        Index of the target element in data if it is present,
        otherwise None.
    """

    return linearSearch(data, target, 0, len(data)-1)

def main():
    """
    Opens an alphabetized list of words based on user parameters and
    allows the user to 'linear search' for values
    """

    file = input("Enter file name containing data: ")
    data = []
    for line in open(file):
        data += [line.strip()]

    print("Data: ", data)
    print("\nNumber of elements: ", len(data))

    target = input("\nEnter target value to search for: ")

    print("\nGetting index of target value in data...")
    index = getIndex(data, target)
    print()
    if index != None:
        print(target, "found at index", index, ".")
    else:
        print(target, "not found.")

if __name__ == '__main__':
    main()