"""
Nick Salvemini
HW 03
triangles.py
This program draws a series of triangles using a recursive function.
"""

import turtle as t

def init():
    """
    Initialize the turtle with a pensize of 1 pixel
    and the maximum speed.
    Turtle orientation is 60 degrees above east.
    """
    t.left(60)
    t.pensize(1)
    t.speed(0)

def drawTriangles(depth, size):
    """
    Recursive function used to draw multiple different tiers of triangles.
    :param depth: Determines how many levels of triangles will be drawn
    :param size: Determines the side length of the largest triangle
    :postcondition: Turtle must end the current iteration in the same location it began.
    """
    if depth <= 0:
        pass
    else:
        t.forward(size)
        drawTriangles(depth - 1, size / 2)
        t.left(120)
        t.forward(size)
        t.right(120)
        drawTriangles(depth - 1, size / 2)
        t.right(120)
        t.forward(size)
        t.left(120)

def main():
    """
    Takes user input for depth and size of initial triangle and calls
    the drawTriangles function to complete the drawing.
    """
    init()
    drawTriangles(int(input("Enter the depth: ")), int(input("Enter the size: ")))
    print("Click X in the top right of the window to close the program.")
    t.done()

main()