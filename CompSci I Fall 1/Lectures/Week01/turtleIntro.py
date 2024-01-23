import turtle

def simple_drawing():
    turtle.forward(200)
    turtle.left(90)
    turtle.forward(50)
    turtle.up()
    turtle.forward(50)
    turtle.down()
    turtle.forward(50)

def main():
    simple_drawing()
    turtle.left(90)
    simple_drawing()
    turtle.done()

main()
