import turtle

def initialize():
    turtle.up()
    turtle.left(90)
    turtle.pensize(2)

def draw_border():
    turtle.down()
    turtle.right(90)
    turtle.circle(100)
    turtle.up()
    turtle.left(90)

def draw_eye():
    turtle.down()
    turtle.circle(15)
    turtle.up()
    
def draw_eyes():
    turtle.forward(100)
    turtle.right(90)
    turtle.backward(50)
    draw_eye()
    turtle.forward(100)
    draw_eye()
    turtle.backward(50)
    turtle.left(90)
    turtle.backward(100)

def draw_nose():
    turtle.forward(70)
    turtle.down()
    turtle.right(90)
    turtle.forward(15)
    turtle.left(120)
    turtle.forward(30)
    turtle.left(120)
    turtle.forward(30)
    turtle.left(120)
    turtle.forward(15)
    turtle.left(90)
    turtle.up()
    turtle.backward(70)

def main():
    initialize()
    draw_border()
    draw_eyes()
    draw_nose()

main()
    
