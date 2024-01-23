"""
CSCI-141 Computer Science 1 Recitation Exercise
13 - Trees
Binary Tree Exercise - Valid Binary Search Tree

Given several binary trees, verify they are valid binary search trees
or not.
"""

from bst import *

def min_node_val(node):
    """
    Find the minimum (leftmost) value from a node in a binary tree.

    :param node: the current node
    :return: the minimum value
    """

    # TODO: Step 1

    # if there is no left node this node's value is the minimum
    if node.left is None:
        return node.value
    else:
        return min_node_val(node.left)

    # otherwise recurse with the left node


def max_node_val(node):
    """
    Find the maximum (rightmost) value from a node in a binary tree.

    :param node: a node in the binary tree
    :return: the minimum value
    """

    # TODO: Step 2

    # if there is no right node this node's value is the maximum
    if node.right is None:
        return node.value
    else:
        return max_node_val(node.right)

    # otherwise recurse with the right node


def verify_rec(node, min_val, max_val):
    """
    Helper recursive function to verify a node is part of a valid
    BST.

    :param node: the current node
    :param min_val: the minimum value from this node
    :param max_val: the maximum value from this node
    :return:
    """

    # TODO: Step 3

    # if we get to an empty node, everything was valid in that path
    # and true should be returned
    if node.left is None and node.right is None:
        return True

    # if the node's value is not between the min and max, it is invalid
    # and false should be returned
    if node.value not in range(min_val, max_val + 1):
        return False

    # recursively check and return that both of these are true:
    # 1. the left node is between min and node's value
    # 2. the right node is between node's value and max
    if verify_rec(node.left, min_val, max_val) and verify_rec(node.right, min_val, max_val):
        if node.left.value in range(min_val, node.value) and node.right.value in range(node.value, max_val + 1):
            return True
        else:
            return False
    else:
        return False

def verify(tree):
    """
    Public function for verifying whether a binary tree is valid BST or not.

    :param tree: a binary tree
    :return: whether the tree is valid BST or not
    """
    return verify_rec(tree, min_node_val(tree), max_node_val(tree))

def verify_bst(tree, letter):
    """
    A helper function for verifying a single BST.

    :param tree: a binary tree
    :param letter: it's unique letter
    """
    print('tree'+letter+':')
    print('\tmin:', min_node_val(tree))
    print('\tmax:', max_node_val(tree))
    print('\tverify:', verify(tree))

def verify_bsts():
    """
    The main test function verifies a collection of binary trees are valid
    or invalid BST's.  Tree's A, B and E are valid while C and D are not valid.
    """
    # treeA inorder = [50]
    treeA = mk_btnode_leaf(50)
    verify_bst(treeA, 'A')

    # treeB inorder: [30, 50, 60]
    treeB = mk_btnode(50,
                          mk_btnode_leaf(30),
                          mk_btnode_leaf(60))
    verify_bst(treeB, 'B')

    # treeC inorder: [10, 20, 5, 30, 40]
    treeC = mk_btnode(20,
                          mk_btnode_leaf(10),
                          mk_btnode(30,
                                    mk_btnode_leaf(5),
                                    mk_btnode_leaf(40)))
    verify_bst(treeC, 'C')

    # treeD inorder: [10, 30, 35, 40, 55, 50, 60, 80, 90]
    treeD = mk_btnode(50,
                          mk_btnode(30,
                                    mk_btnode_leaf(10),
                                    mk_btnode(40,
                                              mk_btnode_leaf(35),
                                              mk_btnode_leaf(55))),
                          mk_btnode(80,
                                    mk_btnode_leaf(60),
                                    mk_btnode_leaf(90)))
    verify_bst(treeD, 'D')

    # treeE inorder: [10, 30, 35, 40, 45, 50, 60, 80, 90]
    treeE = mk_btnode(50,
                          mk_btnode(30,
                                    mk_btnode_leaf(10),
                                    mk_btnode(40,
                                              mk_btnode_leaf(35),
                                              mk_btnode_leaf(45))),
                          mk_btnode(80,
                                    mk_btnode_leaf(60),
                                    mk_btnode_leaf(90)))
    verify_bst(treeE, 'E')

if __name__ == '__main__':
    verify_bsts()

"""
$ python3.7 verify_sol.py
treeA:
	min: 50
	max: 50
	verify: True
treeB:
	min: 30
	max: 60
	verify: True
treeC:
	min: 10
	max: 40
	verify: False
treeD:
	min: 10
	max: 90
	verify: False
treeE:
	min: 10
	max: 90
	verify: True
"""