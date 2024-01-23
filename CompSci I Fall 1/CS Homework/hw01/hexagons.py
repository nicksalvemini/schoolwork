"""
Nick Salvemini
Computer Science 1
hexagons.py

Here is a code that will draw a series of hexagons, connected together to form
a larger hexagons, and will then turn to the right and create the same shape to
ultimately create an interesting design.
"""

import turtle

def init():
    """
    Initialize the turtle by putting the pen down and setting the pensize to 2
    pixels.
    """
    turtle.down()
    turtle.pensize(2)

def draw_side():
    """
    Draw a single side of a hexagon and turn to prepare for drawing the next
    side.
    :precondition: Pen is down.
    :precondition: Turtle is already facing the direction in which the side
                    will be drawn.
    :postcondition: Turtle turns to prepare for the next side.
    """
    turtle.forward(60)
    turtle.left(60)

def reposition():
    """
    Repositions the turtle to draw the next hexagon,
    satisfying the preconditions for the drawHexagon() function.
    :precondition: Turtle has returned to its
                    starting point on the previous hexagon.
    """
    turtle.forward(60)
    turtle.right(60)
    
def draw_hexagon():
    """
    Make the turtle draw a hexagon 60 pixels on each side.
    :precondition: Pen is down.
    :precondition: Turtle is facing the direction in which the
                    first side will be drawn.
    :postcondition: Turtle’s state (location, direction, pen position)
                    is the same as when this function started.
    """
    draw_side()
    draw_side()
    draw_side()
    draw_side()
    draw_side()
    draw_side()
    
def draw_hexagons():
    """
    Make the turtle draw the set of six hexagons arranged in
    the shape of a larger hexagon.
    :precondition: Pen is down.
    :postcondition: Turtle’s state (location, direction, pen position)
                    is the same as when this function started.
    """
    draw_hexagon()
    reposition()
    draw_hexagon()
    reposition()
    draw_hexagon()
    reposition()
    draw_hexagon()
    reposition()
    draw_hexagon()
    reposition()
    draw_hexagon()
    reposition()

def main():
    """
    Make the turtle complete the drawing by creating
    one set of six hexagons, then turning and creating another set.
    The program also prints a message in the console telling the user
    how to close the window when the turtle has completed.
    :postcondition: The turtle will wait when it is finished and do nothing.
    """
    init()
    draw_hexagons()
    turtle.right(60)
    draw_hexagons()
    print("Click X in the top right of the window to close the program.")
    turtle.done()

main()
