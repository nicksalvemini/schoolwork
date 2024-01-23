"""
Nick Salvemini
Lab 01
scenery.py
This program draws two trees, a sun, and a house with windows, a door, and a roof.
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
    turtle.color("gray")
    turtle.begin_fill()
    turtle.forward(100)
    turtle.left(90)
    turtle.forward(100)
    turtle.left(90)
    turtle.forward(100)
    turtle.left(90)
    turtle.forward(100)
    turtle.left(90)
    turtle.end_fill()
    turtle.up()

def drawTriangle():
    """
    Make the turtle draw a triangle of side length 60.
    :preconditions: None
    :postconditions: The turtle ends in the exact same state it began in.
    """
    turtle.down()
    turtle.color("purple")
    turtle.begin_fill()
    turtle.forward(100)
    turtle.left(120)
    turtle.forward(100)
    turtle.left(120)
    turtle.forward(100)
    turtle.left(120)
    turtle.end_fill()
    turtle.up()

def drawWindow():
    """
    Make the turtle draw a window.
    :preconditions: None
    :postconditions: Turtle ends in the exact same state it began in.
    """
    turtle.down()
    turtle.color("blue")
    turtle.begin_fill()
    turtle.forward(20)
    turtle.left(90)
    turtle.forward(20)
    turtle.left(90)
    turtle.forward(20)
    turtle.left(90)
    turtle.forward(20)
    turtle.left(90)
    turtle.end_fill()
    turtle.up()

def drawDoor():
        """
        Make the turtle draw a window.
        :preconditions: None
        :postconditions: Turtle ends in the exact same state it began in.
        """
        turtle.down()
        turtle.color("beige")
        turtle.begin_fill()
        turtle.forward(20)
        turtle.left(90)
        turtle.forward(40)
        turtle.left(90)
        turtle.forward(20)
        turtle.left(90)
        turtle.forward(40)
        turtle.left(90)
        turtle.end_fill()
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
    turtle.forward(40)
    drawDoor()
    turtle.backward(30)
    turtle.left(90)
    turtle.forward(20)
    turtle.right(90)
    drawWindow()
    turtle.left(90)
    turtle.forward(40)
    turtle.right(90)
    drawWindow()
    turtle.forward(30)
    drawWindow()
    turtle.forward(30)
    drawWindow()
    turtle.right(90)
    turtle.forward(40)
    turtle.left(90)
    drawWindow()
    turtle.left(90)
    turtle.backward(20)
    turtle.right(90)
    turtle.backward(70)
    turtle.color("black")



def drawTrunk():
    """
    Make the turtle draw a tree trunk.
    :preconditions: None
    :postconditions: Turtle ends in the exact same state it began in.
    """
    turtle.down()
    turtle.color("brown")
    turtle.begin_fill()
    turtle.forward(20)
    turtle.left(90)
    turtle.forward(50)
    turtle.left(90)
    turtle.forward(20)
    turtle.left(90)
    turtle.forward(50)
    turtle.left(90)
    turtle.end_fill()
    turtle.up()

def drawLeaves():
    """
    Make the turtle draw the leaves of the tree.
    :preconditions: None
    :postconditions: Turtle ends in the exact same state it began in.
    """
    turtle.down()
    turtle.color("green")
    turtle.begin_fill()
    turtle.circle(35)
    turtle.end_fill()
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

def drawSun():
    """
    Make the turtle draw a yellow sun.
    :preconditions: None
    :postconditions:
    """
    turtle.down()
    turtle.color("yellow")
    turtle.begin_fill()
    turtle.circle(40)
    turtle.end_fill()
    turtle.up()

def drawScenery():
    """
    Make the turtle draw the outside features.
    :preconditions: The turtle starts at the same place it started when
                    drawing the house.
    :postconditions: Turtle ends in the exact same state it began in.
    """
    turtle.forward(140)
    drawTree()
    turtle.backward(200)
    drawTree()
    turtle.forward(25)
    turtle.left(90)
    turtle.forward(150)
    turtle.right(90)
    drawSun()
    turtle.right(90)
    turtle.forward(150)
    turtle.left(90)
    turtle.forward(35)
    turtle.color("black")

def main():
    """
    Make the turtle draw the Picture.
    :preconditions: None
    :postconditions: Turtle stops and waits.
    """
    init()
    drawHouse()
    drawScenery()
    turtle.done()

main()