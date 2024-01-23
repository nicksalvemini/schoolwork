"""
Nick Salvemini
Lab 03
This program draws a series of inscribed polygons and circles.
"""

#   Import turtle and math modules
import turtle as t
import math as m

def init():
    """
    Initializes the turtle at an optimal location to fit the drawing
    onto the canvas.
    preconditions: None
    postconditions: Turtle down, facing east, pensize 2, fastest speed, and turtle
                    has moved down from initial starting position 150 pixels.
    """
    t.up()
    t.right(90)
    t.forward(150)
    t.left(90)
    t.pensize(2)
    t.speed(0)
    t.down()

def colorChange(sides):
    """
    Changes the color of the turtle. Uses the mod function
    to determine which color to switch to, as each level
    will cycle the result of the mod function from
    2 - 1 - 0 - 2 ...
    preconditions: None
    postconditions: Turtle is the correct non-black color
    :param sides: Sides of the polygon are passed to determine color
                    based on which level of depth the polygon is
    """
    if sides % 3 == 0:
        t.color("red")
    elif sides % 3 == 1:
        t.color("blue")
    elif sides % 3 == 2:
        t.color("green")

def calcIntAngle(sides):
    """
    Calculates the interior angle of a regular polygon based on its number
    of sides
    :param sides: Number of sides of the polygon
    :return: Interior angle measure of the polygon
    """
    return (sides - 2) * 180 / sides

def nextRadius(sides, radius):
    """
    Uses trigonometry to calculate the apothem of the current
    polygon, which will be the radius of the next circle
    preconditions: None
    postconditions: None
    :param sides: Number of sides of the last polygon drawn
    :param radius: Length of radius of last circle drawn
    :return: Apothem of last polygon drawn, or radius of next circle
                to be drawn
    """
    return radius * (m.sin(m.radians(calcIntAngle(sides) / 2)))

def calcLength(sides, radius):
    """
    Uses the law of cosines to calculate the side length
    of the polygon using the radius of the circle it is
    inscribed in and its number of sides
    preconditions: None
    postconditions: None
    :param sides: Number of sides of polygon to be drawn
    :param radius: Radius of circle polygon is to be inscribed in
    :return: Length of one side in the polygon to be drawn
    """
    return radius * ((2 - 2 * m.cos(m.radians(360 / sides))) ** (1 / 2))

def inscribePolygon(sides, radius):
    """
    Inscribes a polygon in the last drawn circle. Calls the
    colorChange function to cycle the color of each polygon drawn
    preconditions: Turtle is on the last drawn circle pointing towards its center
    postconditions: Turtle is at midpoint of one of the sides of the polygon pointing
                    towards its center
    :param sides: Number of sides of the polygon
    :param radius: Radius of the circle the polygon is being inscribed in
    :return: Returns the total perimeter of the polygon just drawn
    """
    colorChange(sides)
    intAngle = calcIntAngle(sides)
    length = calcLength(sides, radius)
    t.right(intAngle / 2)
    perimeter = length * sides
    while sides > 0:
        t.forward(length)
        t.left(180 - intAngle)
        sides = sides - 1
    t.forward(length / 2)
    return perimeter

def drawPolygons(sides, radius):
    """
    Completes the drawing of the shape recursively. Draws a black
    circle each time it is called and then calls the inscribePolygon
    function to draw the polygon within it. Accumulates the total
    perimeter of all drawn polygons
    preconditions: Turtle is initialized
    postconditions: Turtle ends at midpoint of inner triangle
                    facing along the triangle's side
    :param sides: Number of sides for the initial inscribed polygon
    :param radius: Radius for the initial circle
    :return: Total perimeter of all polygons drawn
    """
    accum = 0
    if sides < 3:
        print("ERROR: A polygon must have at least three sides.")
    else:
        t.color("black")
        t.circle(radius)
        t.left(90)
        accum = accum + inscribePolygon(sides, radius)
        if sides > 3:
            accum = accum + drawPolygons(sides - 1, nextRadius(sides, radius))
    return accum

def main():
    """
    Takes user input for the number of sides of the polygon
    to be inscribed in a circle of radius 200, then completes
    the shape. Once shape is complete, prompts user for another
    number of sides to draw shape again. If user enters 0,
    total perimeter of all polygons (not circles) is printed
    along with an exit message.
    preconditions: None
    postconditions: None
    """
    init()
    length = 0
    while True:
        userInput = int(input("Enter the number of sides for the initial polygon, or 0 to quit: "))
        if userInput != 0:
            length = length + drawPolygons(userInput, 200)
        else:
            break
    print("Total drawing length of polygons: ", length)
    print("Click X in the corner to close the program.")
    t.done()

main()
