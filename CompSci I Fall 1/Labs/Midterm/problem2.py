"""
Nick Salvemini
Midterm Problem 2
Draws a pattern of squares iteratively
"""

import turtle as t

def init():
    t.pensize(2)
    t.speed(0)
    t.down()
    t.color('#009900')

def drawSquare(side):
    sides = 0
    while sides < 4:
        t.forward(side)
        t.left(90)
        sides = sides + 1

def squaresIter(side, num):
    perimeter = 0
    while num > 0:
        if side >= 0:
            drawSquare(side)
            perimeter = perimeter + (side * 4)
            side = side - 10
            num = num - 1
        else:
            print('ERROR: Side length cannot become lower than 0.')
            num = 0
    return perimeter

def main():
    init()
    side = int(input("Enter the side length of the biggest square: "))
    num = int(input("Enter the number of squares: "))
    print('Total perimeter: ', squaresIter(side, num))
    print('Click X to close the window.')
    t.done()

if __name__ == '__main__':
    main()