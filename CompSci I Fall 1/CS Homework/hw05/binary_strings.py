"""
Nick Salvemini
HW 05
Converts text to its binary representation and back.
"""

def ascii7bitsToBinary(word):
    """
    Converts a phrase of normal text into a binary string using
    the mod function on the decimal number associated with the
    characters in the string and reversing the string
    preconditions: None
    postconditions: None
    :param word: Phrase passed to be converted into binary
    :return: Returns the concatenated string of binary code
                representing the normal-text phrase
    """
    binary = ""
    for ch in word:
        num = ord(ch)
        count = 7
        build = ""
        while count > 0:
            temp = str(num % 2)
            num = num // 2
            build = build + temp
            count = count - 1
        build = build[::-1]
        binary = binary + build
    return binary


def binaryToAscii7bits(binary):
    """
        Converts a binary string into a phrase of normal text by processing
        7 characters in the binary string at a time and converting them into
        a single character in the normal-text string
        preconditions: None
        postconditions: None
        :param binary: Binary string passed to be converted back to normal
                        text
        :return: Returns the string of normal text
        """
    count = 0
    phrase = ""
    binStr = str(binary)
    while count < len(binStr) / 7:
        build = 0
        index = 0
        while index < 7:
            build = build + (2 ** (6-index)) * int(binStr[((7*count) + index)])
            index = index + 1
        phrase = phrase + chr(build)
        count = count + 1
    return phrase

def processString(phrase):
    """
    Neatly outputs the string to be translated, its binary translation,
    and its translation back into normal text
    preconditions: None
    postconditions: None
    :param phrase: Normal-text phrase to be processed by the function
    """
    print("String to process: '", phrase, "'")
    print("Converting to binary: ")
    print(ascii7bitsToBinary(phrase))
    print("Converting to ASCII: ")
    print(binaryToAscii7bits(ascii7bitsToBinary(phrase)))

def main():
    """
    Takes user input for normal-text phrases to be converted into binary
    and automatically ends when the user enters 'QUIT'
    """
    while True:
        phrase = input("Enter a phrase to convert to binary, or 'QUIT' to quit: ")
        if phrase == "QUIT":
            break
        processString(phrase)
        print()

main()