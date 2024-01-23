"""
Nick Salvemini
Computer Science 1
HW02
intercepts.py

This program takes input as some coefficients m and b
and computes the x and y intercepts of the line.

"""

def noXIntercept(m,b):
    """
    Returns a value of true if the line has no x-intercepts.
    :param m represents the slope of the line
    :param b represents the y-intercept of the line
    """
    return m == 0 and b != 0

def xIntercept(m,b):
    """
    Calculates the x-intercept of the line, or returns "None" if the line
    has no x-intercept, or returns "Entire x-axis" if it is a horizontal
    line at y=0.
    :param m represents the slope of the line
    :param b represents the y-intercept of the line
    """
    if noXIntercept(m,b):
        return
    elif m == b and m == 0:
        return "Entire x-axis"
    else:
        return -b/m

def yIntercept(m,b):
    """
    Purely for symmetry. As b already represents the y-intercept,
    this function simply returns b as the y-intercept.
    :param b represents the y-intercept
    """
    return b

def printPoint(x,y):
    """
    Prints the point in standard (x,y) Cartesian form.
    Specific if statements allow for proper formatting if there
    is no x-intercept, and the value is not numeric.
    :param x represents the x-intercept of the line
    :param y represents the y-intercept of the line
    """
    if x == "None" or x == "Entire x-axis":
        print("(", x, ",", (format(y, ".3f")), end=") ")
    else:
        print("(", (format(x, ".3f")),",", (format(y, ".3f")), end=") ")

def testCase(m,b):
    """
    Takes the input and calls functions to compute intercepts
    and neatly display the points next to the equation
    :param m represents the slope of the line
    :param b represents the y-intercept of the line
    """
    print("Equation: y = ", m, "x + ", b, end=" Intercepts: ")
    if noXIntercept(m,b):
        print(end="(NONE) ")
        printPoint(0, b)
    else:
        printPoint(xIntercept(m,b),0)
        printPoint(0,yIntercept(m,b))
    print()

def main():
    """
    Calls the testCase function multiple times with
    different values for m and b to ensure the program
    returns the correct values for multiple different lines.
    """
    testCase(3,5)
    testCase(0,2)
    testCase(0,0)
    testCase(4,0)
    testCase(7,2)
    testCase(-2,3)
    testCase(-5,0)
    testCase(0,-3)
    testCase(2,-4)
    testCase(10,10)

main()