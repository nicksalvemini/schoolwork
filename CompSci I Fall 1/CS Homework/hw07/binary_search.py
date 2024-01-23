"""
file: binary_search.py
purpose: Lecture demonstrates the recursive binary search algorithm.
author: RIT CS Dept.
"""

def binarySearch(data, target, start, end):
    """
    Perform a binary search for a target value between start and end indices.
    Parameters:
        data - a list of sorted data
        target - the target value to search for
        start - the starting index in data that is part of this search
        end - the ending index in data that is part of this search
    Returns:
        index of target in data, if present; otherwise None.
    """

    # base condition - terminate when start passes end index
    if start > end:
        return None

    # find the middle value between start and end indices
    midIndex = (start + end) // 2
    midValue = data[midIndex]

    """# debug statement prints the data list
    print("Searching for", target, ":", data[start:mid_index],
    "*" + str(mid_value) + "*",
    data[mid_index + 1:end + 1])
    """

    if target == midValue:
        return midIndex
    elif target.lower() < midValue.lower():
        return binarySearch(data, target, start, midIndex - 1)
    else:
        return binarySearch(data, target, midIndex + 1, end)

def getIndex(data, target):
    """
    Returns the index of target in data or None if not target found.
    Parameters:
        data - List of sorted data
        target - Target value to search for
    Returns:
        The index of the target element in data if it is present,
        otherwise None.
    """

    # search for the target across all elements in data
    return binarySearch(data, target, 0, len(data) - 1)


def main():
    """
    Opens an alphabetized list of words based on user parameters and
    allows the user to 'binary search' for values
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

if __name__ == "__main__":
    main()