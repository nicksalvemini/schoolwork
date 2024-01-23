"""
CSCI-141: Homework 11
file: test_cbc.py
language: python3
author: mkp, mkpvcs@rit.edu
author: bks@cs.rit.edu
Test unit for the CBC encryption mode homework and its
cbc_mode_encryption/cbc_mode_decryption functions.
"""

import random
import linked_list_type as llist
from node_types import MutableNode
import cbc_mode as cbc
import mutable_list

def random_block(size = 64):
    """
    Generates random vector of zeros and ones of size 'size'.
    :param size: int
    :return: randomized list of zeros/ones
    """
    block = []
    for i in range(size):
        block.append(random.randrange(2))
    return block

def generate_plaintext(n):
    """
    Generates an n-block linked list of random plaintext blocks.
    Each block is a list of zeros/ones.
    :param n: int
    :return: linked list
    """
    plaintext = llist.make_empty_list()
    for i in range(n+1):
        plaintext.size += 1
        plaintext.head = MutableNode(random_block(), plaintext.head)
    return plaintext


def nice_print( lst ):
    """
    nice_print: LinkedList -> str
    Construct a string that shows the contents of a linked list.
    The elements are separated by commas and surrounded by brackets.
    :param lst: LinkedList whose contents will be printed
    """
    result = "Head\n\t-> " # result is the accumulator
    node = lst.head
    n = 1
    while node.next is not None:
        for el in node.value:
            result += str(el)
        result += "  [" + str(n) + "]\n\t-> "   # add block number suffix
        node = node.next
        n += 1             # to the next block
    result += "\tNone"
    return result

def random_test(blockcount, IV, key):
    """
    Run a random test of cbc_mode_encryption/cbc_mode_decryption.
    :param blockcount: integer count of plaintext blocks
    :param IV: list of zeros/ones
    :param key: list of zeros/ones
    :return: None
    """
    plaintext = generate_plaintext(blockcount)

    ciphertext = cbc.cbc_mode_encryption( plaintext, IV, key)

    decrypted_ciphertext = cbc.cbc_mode_decryption( ciphertext, IV, key)

    print("Plaintext: ", plaintext)
    print("Plaintext in nice format:\n", nice_print( plaintext ), sep="")
    print("Ciphertext: ", ciphertext)
    print("Ciphertext in nice format:\n", nice_print( ciphertext ), sep="")
    print("Decrypted ciphertext: ", decrypted_ciphertext)
    print("Decrypted ciphertext in nice format:\n",
        nice_print(decrypted_ciphertext), sep="")

    if plaintext == decrypted_ciphertext:
        print("\n\t", blockcount, "-block test Passed", sep="")
    else:
        print("\n\t", blockcount, "-block test Failed", sep="")

def run_n_tests(n):
    """
    Runs n tests of cbc_mode_encryption/cbc_mode_decryption.
    :param n: int
    :return: None
    """
    for i in range(1, n):
        print("Testing cbc mode on ", i, " blocks.")
        IV = random_block(64)
        key = random_block(64)
        random_test(i, IV, key)

def single_test(testid, plaintext, ciphertext, IV, key):
    """
    Single test of cbc_mode_encryption/cbc_mode_decryption.
    Expected results:
    encryption of plaintext matches ciphertext, and
    decryption of ciphertext matches plaintext.

    :param  testid: integer ID of test
    :plaintext: linked list
    :ciphertext: linked list
    :param IV: list of zeros/ones
    :param key: list of zeros/ones
    :return: None
    """

    cipher = cbc.cbc_mode_encryption( plaintext, IV, key)

    decrypted = cbc.cbc_mode_decryption( ciphertext, IV, key)

    if cipher == ciphertext:
        print("Encryption of ciphertext ",  testid, " is correct.")
        if decrypted == plaintext:
            print("Test",  testid, ": Passed")
        else:
            print("Decryption is incorrect.")
    else:
        print("Encryption of plaintext ",  testid, " is incorrect.")


def run_fixed_tests():
    """
    Generates 3 (plaintext, ciphertext) pairs and
    runs tests of cbc_mode_encryption/cbc_mode_decryption.
    """

    # key values were generated manually for testing
    key1 = [1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1]
    key2 = [1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1]
    key3 = [1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0]
    IV  = [0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1]

    # make plaintext 1 a 1-block message
    plaintext1 = llist.make_empty_list()
    mutable_list.append(plaintext1, [1 for i in range(64)])

    # make plaintext 2 a 2-block message
    plaintext2 = llist.make_empty_list()
    mutable_list.append(plaintext2, [1 for i in range(64)])
    mutable_list.insert_before_index( plaintext2, [0 for i in range(64)], 0)

    # make plaintext 3 a 3-block message
    plaintext3  = llist.make_empty_list()
    mutable_list.append(plaintext3, [1 for i in range(64)])
    mutable_list.append(plaintext3, [1 for i in range(64)])

    # ciphertext values were generated manually for testing
    ciphertext1 = llist.make_empty_list()
    mutable_list.append(ciphertext1, [0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1])

    ciphertext2 = llist.make_empty_list()
    mutable_list.append(ciphertext2, [0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0])
    mutable_list.append(ciphertext2, [0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0])

    ciphertext3 = llist.make_empty_list()
    mutable_list.append(ciphertext3, [1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1])
    mutable_list.append(ciphertext3, [0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0])

    single_test("1", plaintext1, ciphertext1, IV, key1)
    single_test("2", plaintext2, ciphertext2, IV, key2)
    single_test("3", plaintext3, ciphertext3, IV, key3)


if __name__ == "__main__":

    print("Running fixed tests...")
    run_fixed_tests()

    answer = input("Do you want to to run random tests? (y or n) ")
    if answer[0] == 'y':
        print("Running random tests...")
        run_n_tests(10)


