"""
CSCI-141: Homework 11
file: encryption.py
language: python3
author: mkp, mkpvcs@rit.edu
description: functions for encryption, decryption and XOR gate
"""

def xor_gate( list1, list2):
    """
    Create a list of outputs of a XOR gate performed on elements
    of list1 and list2.
    :param list1: list of zeros/ones
    :param list2: list of zeros/ones
    :return: list of zeros/ones
    """
    newList = []
    if len(list1) != len(list2):
        raise ValueError('Lists are not the same length')
    else:
        for i in range(0, len(list1)):
            newList.append(list1[i] ^ list2[i])
    return newList

def shiftL(vector, n):
    """
    Shifts the bits n positions to the left.
    Example: shiftL(1 1 0 1 1 0 1 0 , 2) -> 1 0 1 1 0 1 0 1.
    :param vector: list
    :param n: int
    :return: list shifted by n positions to the left
    """
    return vector[n:] + vector[:n]

def shiftR(vector, n):
    """
    Shifts the bits n positions to the right.
    Example: shiftR( 1 0 1 1 0 1 0 1, 2) -> 1 1 0 1 1 0 1 0.
    :param vector: list
    :param n: int
    :return: list shifted by n positions to the right
    """
    length = len(vector)
    return vector[length - n:] + vector[:length - n]

def encryption( vector, key):
    """
    Encrypts vector using secret key.
    :param vector: list of zeros/ones
    :param key: list of zeros/ones
    :return: list of zeros/ones (encrypted block)
    """
    if len(vector ) != len(key):
        raise Exception("vector and key must be of the same length")
    for i in range(4):
        vector = shiftL(vector, 10)
        vector = xor_gate(vector, key)
    return vector

def decryption(vector, key):
    """
    Decrypts vector using secret key.
    :param vector: list of zeros/ones
    :param key: list of zeros/ones
    :return: list of zeros/ones (decrypted block)
    """
    if len(vector) != len(key):
        raise Exception("vector and key must be of the same length")
    for i in range(4):
        vector = xor_gate(vector, key)
        vector = shiftR(vector,10)
    return vector


        