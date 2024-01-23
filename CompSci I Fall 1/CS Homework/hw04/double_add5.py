"""
Nick Salvemini
HW 04
double_add5.py
Prints a sequence of numbers such that n = (n-1) * 2 + 5
If a goal and a number of steps is given, returns the minimum start value
"""

def nextNumber(number):
    """
    Returns the next number in the sequence
    :param number: Term that the following term will be based on
    :return: Next number in sequence
    """
    return number * 2 + 5

def printSequenceRec(start, count):
    """
    Uses recursion to print a sequence of numbers
    :param start: Initial value in the sequence
    :param count: Number of values in the sequence following
                    the initial value
    """
    print(start, end=" ")
    if count == 0:
        print()
    else:
        printSequenceRec(nextNumber(start), count - 1)

def printSequenceIter(start, count):
    """
    Uses iteration/loop to print a sequence of numbers
    :param start: Initial value in the sequence
    :param count: Number of values in the sequence following
                    the initial value
    """
    print(start, end=" ")
    temp = start
    while count > 0:
        print(nextNumber(temp), end=" ")
        temp = nextNumber(temp)
        count = count - 1
    print()

def findStartForward(goal, count):
    """
    Returns the minimum start value to reach the goal
    in the specified number of steps
    :param goal: Sequence must reach this value or greater
    :param count: How many steps until goal must be met or exceeded
    :return: Minimum start value to meet criteria
    """
    start = 0
    steps = count
    while True:
        temp = start
        while steps > 0:
            temp = nextNumber(temp)
            steps = steps - 1
        if temp >= goal:
            return start
            break
        else:
            start = start + 1
            steps = count

def main():
    """
    Runs multiple test cases
    """
    printSequenceRec(3, 4)
    printSequenceRec(5, 7)
    printSequenceRec(0, 19)
    printSequenceRec(-2, 6)
    printSequenceRec(50, 0)
    printSequenceRec(10, 10)
    print()
    printSequenceIter(3, 4)
    printSequenceIter(6, 4)
    printSequenceIter(0, 8)
    printSequenceIter(-2, 7)
    printSequenceIter(-3, 3)
    printSequenceIter(14, 4)
    print()
    print("Start: ", findStartForward(1000, 3))
    print("Start: ", findStartForward(543, 7))
    print("Start: ", findStartForward(67, 0))
    print("Start: ", findStartForward(186, 4))
    print("Start: ", findStartForward(200, 5))
    print("Start: ", findStartForward(322, 6))
    print()
    print(findStartForward(20, 2) == 2)
    print(findStartForward(50, 3) == 2)
    print(findStartForward(243, 0) == 243)

main()