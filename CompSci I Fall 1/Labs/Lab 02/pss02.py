"""
Nick Salvemini
Lab 02 PSS
pss02.py
This program draws a series of squares using a recursive function.
"""

import turtle as t

def init():
    t.pensize(1)
    t.speed(0)

def drawSquares(depth, size):
    if depth <= 0:
        pass
    else:
        t.forward(size)
        t.left(90)
        t.forward(size)
        t.right(90)
        drawSquares(depth - 1, size / 3)
        t.back(size)
        t.up()
        t.back(size / 3)
        t.down()
        drawSquares(depth - 1, size / 3)
        t.up()
        t.forward(size / 3)
        t.down()
        t.right(90)
        t.forward(size)
        t.left(90)

def main():
    init()
    drawSquares(int(input("Enter the depth: ")), int(input("Enter the size: ")))
    t.done()

main()