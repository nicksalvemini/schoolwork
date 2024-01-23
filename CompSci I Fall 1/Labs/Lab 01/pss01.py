"""
Nick Salvemini
Lab 01 PSS
pss01.py
This program draws a house by drawing a triangle on top of a square,
and then draws a tree by drawing a circle on top of a thin rectangle.

"""

import turtle

def init():
    """
    Initialize the turtle with the fastest speed.
    :preconditions: None
    :postconditions: The turtle is up, facing east, with the fastest speed.
    """
    turtle.pensize(1)
    turtle.speed(0)
    turtle.up()

def drawSquare():
    """
    Make the turtle draw a square of side length 100.
    :preconditions: None
    :postconditions: The turtle ends in the exact same state it began in.
    """
    turtle.down()
    turtle.forward(100)
    turtle.left(90)
    turtle.forward(100)
    turtle.left(90)
    turtle.forward(100)
    turtle.left(90)
    turtle.forward(100)
    turtle.left(90)
    turtle.up()

def drawTriangle():
    """
    Make the turtle draw a triangle of side length 60.
    :preconditions: None
    :postconditions: The turtle ends in the exact same state it began in.
    """
    turtle.down()
    turtle.forward(100)
    turtle.left(120)
    turtle.forward(100)
    turtle.left(120)
    turtle.forward(100)
    turtle.left(120)
    turtle.up()

def drawHouse():
    """
    Make the turtle draw a house by drawing a square with a triangle on top.
    :preconditions: Turtle is facing east.
    :postconditions: Turtle ends in the exact same state it began in.
    """
    drawSquare()
    turtle.left(90)
    turtle.forward(100)
    turtle.right(90)
    drawTriangle()
    turtle.left(90)
    turtle.backward(100)
    turtle.right(90)

def drawTrunk():
    """
    Make the turtle draw a tree trunk.
    :preconditions: None
    :postconditions: Turtle ends in the exact same state it began in.
    """
    turtle.down()
    turtle.forward(20)
    turtle.left(90)
    turtle.forward(50)
    turtle.left(90)
    turtle.forward(20)
    turtle.left(90)
    turtle.forward(50)
    turtle.left(90)
    turtle.up()

def drawLeaves():
    """
    Make the turtle draw the leaves of the tree.
    :preconditions: None
    :postconditions: Turtle ends in the exact same state it began in.
    """
    turtle.down()
    turtle.circle(35)
    turtle.up()

def drawTree():
    """
    Make the turtle draw a tree.
    :preconditions: None
    :postconditions: Turtle ends in the exact same state it began in.
    """
    drawTrunk()
    turtle.forward(10)
    turtle.left(90)
    turtle.forward(50)
    turtle.right(90)
    drawLeaves()
    turtle.right(90)
    turtle.forward(50)
    turtle.left(90)
    turtle.backward(10)

def main():
    """
    Draw the Picture
    :preconditions: None
    :postconditions: Turtle stops and waits.
    """
    drawHouse()
    turtle.forward(150)
    drawTree()
    turtle.done()

main()
