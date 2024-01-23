"""
Nick Salvemini
Midterm Problem 1
Draws a pattern of squares recursively
"""

import turtle as t

def init(size):
    t.reset()
    t.setup(600, 600)
    t.setworldcoordinates(-2*size, -2*size, 2*size, 2*size)
    t.down()
    t.pensize(2)
    t.speed(0)
    t.left(45)

def drawSquare(size):
    sides = 0
    while sides < 4:
        t.forward(size)
        t.left(90)
        sides = sides + 1

def drawPattern(size, levels):
    if levels <= 0:
        pass
    else:
        drawSquare(size)
        t.left(180)
        drawSquare(size)
        drawPattern(size / 2, levels - 1)

def main():
    size = int(input("Enter the side length for the square on the largest level: "))
    levels = int(input("Enter the number of levels: "))
    init(size)
    drawPattern(size, levels)
    print('Click X to close the window.')
    t.done()

if __name__ == '__main__':
    main()