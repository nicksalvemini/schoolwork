"""
Nick Salvemini
HW 06
Code for computing the Ackermann function
"""

def ack(m, n):
    """
    Calculates the result of the Ackermann function
    :param m: First integer
    :param n: Second integer
    :return: Result of Ackermann function
    """
    if m == 0:
        return 2*n
    elif m > 0 and n == 0:
        return 0
    if m > 0 and n == 1:
        return 2
    if m > 0 and n > 1:
        return ack(m - 1, ack(m, n - 1))

def getIntegers():
    """
    Takes user input for integers m and n
    :return: Integers m and n to be used as ack() parameters
    """
    m = int(input("Enter a first integer m: "))
    n = int(input("Enter a second integer n: "))
    return m, n

def main():
    """
    Prompts for user input and performs ack() function
    to print result neatly
    """
    m, n = getIntegers()
    print('ack(', m, ',', n, ') = ', ack(m, n))

if __name__ == '__main__':
    main()
