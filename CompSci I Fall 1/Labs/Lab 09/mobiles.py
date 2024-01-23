"""
file: mobiles.py
language: python3
author: CS.RIT.EDU
author: ##################### PUT YOUR NAME AND LOGIN ID HERE #####
description: Build mobiles using a tree data structure.
date: 10/2015, 11/2019, 04/2021
purpose: starter code for the tree mobiles lab
"""

############################################################
#                                                          #
#    IMPLEMENT THE STRUCTURE DEFINITIONS PER REQUIREMENTS, # 
#    AND                                                   #
#    IMPLEMENT THE MOBILE CREATION AND ANALYSIS FUNCTIONS. #
#        See the 'define structure here' text below,       #
#        the 'Create mobiles from mobile files' text,      #
#        and the heading 'mobile analysis functions'.      #
#                                                          #
#    (See also the 'pass' statements to replace.)          #
#                                                          #
############################################################

from dataclasses import dataclass
from typing import Union


############################################################
# structure definitions
############################################################

@dataclass
class Ball:
    """
    class Ball represents a ball of some weight hanging from a cord.
    field description:
    cord: length of the hanging cord in inches
    weight: weight of the ball in ounces (diameter of ball in a drawing)
    """
    cord: float
    weight: float

@dataclass
class Rod:
    """
    class Rod represents a horizontal rod part of a mobile with
    a left-side mobile on the end of a left arm of some length,
    and a right-side mobile on the end of a right arm of some length.
    In the middle between the two arms is a cord of some length
    from which the rod instance hangs.
    field description:
    leftmobile: subordinate mobile is a mobile type.
    leftarm: length of the right arm in inches
    cord: length of the hanging cord in inches
    rightarm: length of the right arm in inches
    rightmobile: subordinate mobile is a mobile type.

    An assembled mobile has valid left and right subordinate mobiles;
    an unassembled mobile does not have valid subordinate mobiles.
    """
    leftmobile: Union["Ball", "Rod"]
    leftarm: float
    cord: float
    rightarm: float
    rightmobile: Union["Ball", "Rod"]


#########################################################
# Create mobiles from mobile files
#########################################################

def read_mobile( file ):
    """
    read_mobile : OpenFileObject ->List( strings representing mobile parts )
    read_mobile reads the open file's content and
    Builds a mobile 'parts list' from the specification in the file,
    for example:
    Rod B1 30 20 30 B2
    B1 100 30
    B2 100 30

    returns parts=["Rod B1 30 20 30 B2", "B1 100 30", "B2 100 30"]

    If there is an error in the mobile specification, then
    report invalid lines.
    # blank lines and '#' comment lines are permitted.
    """
    parts = []
    for line in file:
        if line[0] == 'R' or line[0] == 'B':
            parts.append(line.strip())
    return parts


def construct_mobile( parts , x=0) :
    """
    construct_mobile : list -> Ball | Rod | NoneType

    construct_mobile reads the parts to put together the
    mobile's components and return a completed mobile object.

    The parts list has the components for assembling the mobile.

    If the parts list contains no recognizable mobile specification,
    or there is an error in the mobile specification, then 
    return None.
    """
    temp = parts[x].strip().split()

    if len(temp) == 3:
        return Ball(float(temp[1]), float(temp[2]))
    elif len(temp) == 6:
        tempRod = Rod(construct_mobile(parts, x + 1), float(temp[2]), float(temp[3]), float(temp[4]), Ball(0, 0))
        if parts[x+1][0] == 'B':
            tempRod.rightmobile = construct_mobile(parts, x + 2)
        else:
            expectedBalls = 2
            mobiles = 4
            for el in parts [x+2:]:
                if expectedBalls > 0:
                    if el[0] == 'B':
                        expectedBalls -= 1
                    else:
                        expectedBalls += 1
                        mobiles += 2
            tempRod.rightmobile = construct_mobile(parts, x + mobiles)
        return tempRod
    else:
        return None



############################################################
# mobile analysis functions
############################################################

def is_balanced( the_mobile ) :
    """
    is_balanced : Mobile -> Boolean

    is_balanced is trivially True if the_mobile is a simple ball. 

    Otherwise the_mobile is balanced if the product of the left side
    arm length and the left side is approximately equal to the 
    product of the right side arm length and the right side, AND
    both the right and left subordinate mobiles are also balanced.

    The approximation of balance is measured by checking
    that the absolute value of the difference between
    the two products is less than 1.0.

    If the_mobile is not valid, then produce an exception
    with the message 'Error: Not a valid mobile\n\t{mobile}',

    pre-conditions: the_mobile is a proper mobile instance.
    """
    if isinstance(the_mobile, Ball):
        return True
    elif isinstance(the_mobile, Rod):
        if is_balanced(the_mobile.leftmobile) and is_balanced(the_mobile.rightmobile):
            return abs((the_mobile.leftarm * weight(the_mobile.leftmobile)) -
                       (the_mobile.rightarm * weight(the_mobile.rightmobile))) < 1
        else:
            return False
    else:
        raise Exception( "Error: Not a valid mobile\n\t" + str( the_mobile ) )


def weight( the_mobile ) :
    """
    weight : Mobile -> Number
    weight of the the_mobile is the total weight of all its Balls.

    If the_mobile is not valid, then produce an exception
    with the message 'Error: Not a valid mobile\n\t{mobile}',

    pre-conditions: the_mobile is a proper mobile instance.
    """
    if isinstance(the_mobile, Ball):
        return the_mobile.weight
    elif isinstance(the_mobile, Rod):
        return weight(the_mobile.leftmobile) + weight(the_mobile.rightmobile)
    else:
        raise Exception( "Error: Not a valid mobile\n\t" + str( the_mobile ) )

 
def height( the_mobile ) :
    """
    height : the_mobile -> Number
    height of the the_mobile is the height of all tallest side.

    If the_mobile is not valid, then produce an exception
    with the message 'Error: Not a valid mobile\n\t{mobile}',

    pre-conditions: the_mobile is a proper mobile instance.
    """
    if isinstance(the_mobile, Ball):
        return the_mobile.cord + the_mobile.weight
    elif isinstance(the_mobile, Rod):
        lheight = height(the_mobile.leftmobile)
        rheight = height(the_mobile.rightmobile)
        return the_mobile.cord + max(lheight, rheight)
    else:
        raise Exception( "Error: Not a valid mobile\n\t" + str( the_mobile ) )