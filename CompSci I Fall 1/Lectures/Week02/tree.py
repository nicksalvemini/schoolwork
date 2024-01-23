"""
Draw a tree with user input
"""

import turtle as t

def init():
    t.left(90)
    t.speed(0)

def drawTree0(s):
    return 0

def drawTree1(s):
    t.forward(s)
    t.forward(-s)

def drawTree2(s):
    t.forward(s)
    t.left(45)
    drawTree1(s/2)
    t.right(90)
    drawTree1(s/2)
    t.left(45)
    t.backward(s)

def drawTree3(s):
    t.forward(s)
    t.left(45)
    drawTree2(s/2)
    t.right(90)
    drawTree2(s/2)
    t.left(45)
    t.backward(s)

def drawTree4(s):
    t.forward(s)
    t.left(45)
    drawTree3(s/2)
    t.right(90)
    drawTree3(s/2)
    t.left(45)
    t.backward(s)

def drawTree5(s):
    t.forward(s)
    t.left(45)
    drawTree4(s/2)
    t.right(90)
    drawTree4(s/2)
    t.left(45)
    t.backward(s)

def main():
    size = int(input("Size for tree: "))
    init()
    drawTree5(size)
    t.done()

main()