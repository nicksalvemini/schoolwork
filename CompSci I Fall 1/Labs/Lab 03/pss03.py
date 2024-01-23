"""
Nick Salvemini
Lab 03 PSS
This program draws a series of inscribed polygons and circles.
"""

import turtle as t
import math as m

def init():
    t.pensize(1)
    t.speed(0)
    t.down()

def nextRadius(sides, radius):
    intAngle = (sides - 2) * 180 / sides
    return radius * (m.sin(m.radians(intAngle / 2)))

def inscribePolygon(sides, radius):
    intAngle = ((sides - 2) * 180) / sides
    length = radius * ((2 - 2 * m.cos(m.radians(360 / sides))) ** (1 / 2))
    t.right(intAngle / 2)
    while sides > 0:
        t.forward(length)
        t.left(180 - intAngle)
        sides = sides - 1
    t.forward(length / 2)

def drawPolygons(sides, radius):
    t.circle(radius)
    t.left(90)
    inscribePolygon(sides, radius)
    if sides > 3:
        drawPolygons(sides - 1, nextRadius(sides, radius))

def main():
    init()
    drawPolygons(int(input("Enter the number of sides for the initial polygon: ")),
                 int(input("Enter the initial radius: ")))
    t.done()

main()
