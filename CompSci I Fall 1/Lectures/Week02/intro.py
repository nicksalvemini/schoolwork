"""
Functions and Parameters
"""

import turtle as t

def init():
    t.pensize(2)
    t.up()

def square(s):
    t.down()
    t.forward(s)
    t.left(90)
    t.forward(s)
    t.left(90)
    t.forward(s)
    t.left(90)
    t.forward(s)
    t.left(90)
    t.up()

def shape(length, sides):
    angle = (180 * (sides - 2)) / sides
    t.down()
    t.forward(length)
    t.left(angle)
    t.forward(length)
    t.left(angle)
    t.forward(length)
    t.left(angle)
    t.forward(length)
    t.left(angle)
    t.forward(length)
    t.left(angle)
    t.forward(length)
    t.left(angle)
    t.up()

def main():
    init()
    # length = int(input ("Enter the side length: "))
    # square(length)
    square(int(input ("Enter the side length: ")))
    shape(int(input ("Enter the side length: ")), int(input ("Enter the number of sides: ")))
    t.done()

main()