"""
Nick Salvemini
HW 06
Testing and debugging
Test module for the is_palindrome() function in the palindrome module
"""

import palindrome

def test_is_palindrome(name, word, expected):
    """
    A single test of the is_palindrome() function
    :param name: A string with the function and arguments being tested
    :param word: Word to be checked / argument of is_palindrome function
    :param expected: Expected result
    """
    result = palindrome.is_palindrome(word)
    if result == expected:
        print(name, 'passed')
    else:
        print(name, 'failed: expected', expected, 'but got', result)

def runTests():
    """
    Test cases for is_palindrome()
    """
    test_is_palindrome('is_palindrome(ab)', 'ab', False)
    test_is_palindrome('is_palindrome(aa)', 'aa', True)
    test_is_palindrome('is_palindrome(abba)', 'abba', True)
    test_is_palindrome('is_palindrome(abbab)', 'abbab', False)
    test_is_palindrome('is_palindrome(baaabb)', 'baaabb', False)
    test_is_palindrome('is_palindrome(aaaabbbb)', 'aaaabbbb', False)
    test_is_palindrome('is_palindrome(aaaabaaaaa)', 'aaaabaaaaa', False)
    test_is_palindrome('is_palindrome(bbabbabb)', 'bbabbabb', True)
    test_is_palindrome('is_palindrome(aabaaa)', 'aabaaa', False)
    print()
    test_is_palindrome('is_palindrome(racecar)', 'racecar', True)
    test_is_palindrome('is_palindrome(tacocat)', 'tacocat', True)
    test_is_palindrome('is_palindrome(princess)', 'princess', False)
    test_is_palindrome('is_palindrome(hi)', 'hi', False)
    test_is_palindrome('is_palindrome(chumbucket)', 'chumbucket', False)
    test_is_palindrome('is_palindrome(level)', 'level', True)
    test_is_palindrome('is_palindrome(wasitacaroracatisaw)', 'wasitacaroracatisaw', True)

if __name__ == '__main__':
    runTests()