"""
Nick Salvemini
Lab 02
squares.py
This program draws a series of squares using a recursive function.
"""

import turtle as t

def init():
    """
    Initialize the turtle with a pensize of 2 pixels
    and the maximum speed.
    """
    t.pensize(1)
    t.speed(0)

def changeColor(depth):
    """
    Pen is set to blue for even depths and orange for odd depths
    :param depth: Represents which level of squares is being drawn to
                    to allow for proper color changing
    """
    if depth % 2 == 0:
        t.color("blue")
    else:
        t.color("orange")

def drawSquares(depth, size):
    """
    Recursive function used to draw multiple different tiers of squares.
    The colorChange function is called after each iteration of the next
    tier is called to reset the pen color to the correct color for that
    tier/iteration.
    :param depth: Determines how many levels of squares will be drawn
    :param size: Determines the side length of the largest square
    :postcondition: Turtle must end the current iteration in the same location it began.
    """
    if depth <= 0:
        pass
    else:
        changeColor(depth)
        t.forward(size)
        t.left(90)
        t.forward(size)
        t.right(90)
        drawSquares(depth - 1, size / 3)
        changeColor(depth)
        t.back(size)
        t.up()
        t.back(size / 3)
        t.down()
        drawSquares(depth - 1, size / 3)
        changeColor(depth)
        t.up()
        t.forward(size / 3)
        t.down()
        t.right(90)
        t.forward(size)
        t.left(90)

def main():
    """
    Takes user input for depth and size of initial square and calls
    the drawSquares function to complete the drawing.
    """
    init()
    drawSquares(int(input("Enter the depth: ")), int(input("Enter the size: ")))
    print("Click X in the top right of the window to close the program.")
    t.done()

main()