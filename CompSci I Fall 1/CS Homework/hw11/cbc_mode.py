"""
Nick Salvemini
HW 11
Encrypts and Decrypts linked lists according to
an initialization vector and a key
"""

import encryption as e
import linked_list_type as llt
import mutable_list as mlist

def cbc_mode_encryption(plaintext, IV, key):
    """
    Encrypts a plaintext in the form of a linked list
    of 1s and 0s such that the resultant ciphertext is
    equal to each node of the plaintext being XORed
    with the previous resultant ciphertext, with the
    first ciphertext being the initialization vector IV.
    After being XORed, the resultant text is passed to the
    encryption function with the key to create the first node
    in the ciphertext linked list
    Parameters:
        plaintext: Linked List, value of each node is a list of
                    1s and 0s representing plaintext
        IV: Initialization vector used for the XOR with the first node
            because there is no previous ciphertext to use yet
        key: List to be used in the final encryption function
    Returns:
        Returns a linked list containing ciphertext, each node is
        the ciphertext of the node in that position in the plaintext
    """
    ciphertext = llt.make_empty_list()
    node = plaintext.head
    for i in range(plaintext.size):
        step1 = e.xor_gate(node.value, IV)
        cipher = e.encryption(step1, key)
        mlist.append(ciphertext, cipher)
        IV = cipher
        node = node.next
    return ciphertext

def cbc_mode_decryption(ciphertext, IV, key):
    """
        Decrypts a ciphertext in the form of a linked list
        of 1s and 0s such that the resultant plaintext is
        equal to each node of the ciphertext being passed
        to the decryption function with the key, then the
        result being XORed with the previous ciphertext,
        with the first XOR value being the initialization
        vector IV
        Parameters:
            ciphertext: Linked List, value of each node is a list of
                        1s and 0s representing ciphertext
            IV: Initialization vector used for the XOR with the first node
                because there is no previous ciphertext to use yet
            key: List to be used in the final decryption function
        Returns:
            Returns a linked list containing plaintext, each node is
            the plaintext of the node in that position in the ciphertext
        """
    plaintext = llt.make_empty_list()
    node = ciphertext.head
    for i in range(ciphertext.size):
        nextXor = node.value
        step1 = e.decryption(node.value, key)
        plain = e.xor_gate(step1, IV)
        mlist.append(plaintext, plain)
        IV = nextXor
        node = node.next
    return plaintext