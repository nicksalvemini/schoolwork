"""
Nick Salvemini
Lab 04
This program obfuscates and clarifies text using a ROT11 Caesar Cipher
"""

def rotationKey():
    """
    Sets a rotation key for the muddling
    so that to change the encryption, only this
    line needs to be changed
    :return: Encryption rotation key
    """
    return 1

def muddleLetter(letter):
    """
    Muddle an individual letter and return it
    :param: The letter to be rotated forward
    """
    if ord(letter) + rotationKey() < 127:
        return chr(ord(letter) + rotationKey())
    else:
        return chr(ord(letter) + rotationKey() - 95)

def clarifyLetter(letter):
    """
    Clarify an individual letter and return it
    :param: The letter to be rotated backward
    """
    if ord(letter) - rotationKey() >= 32:
        return chr(ord(letter) - rotationKey())
    else:
        return chr(ord(letter) - rotationKey() + 95)

def clarify(file):
    """
    Read the file and clarify every line,
    printing each line as it is clarified
    :param: The path to the file containing muddled message
    """
    build = ""
    for line in open(file):
        tempStr = ""
        for ch in line:
            tempStr = tempStr + clarifyLetter(ch)
        build = build + tempStr + "\n"
    return build.strip()

def muddle(file):
    """
    Read the file and muddle every line,
    printing each line as it is muddled
    :param: The path to the file containing message
    """
    build = ""
    for line in open(file):
        tempStr = ""
        for ch in line:
            tempStr = tempStr + muddleLetter(ch)
        build = build + tempStr + "\n"
    return build.strip()

def promptFile(file):
    """
    Offers user a choice to write results of obfuscation/clarification
    to a new file with the same name but a different suffix. If user declines,
    results are simply printed. If invalid file is passed, prints an error message
    :param file: Path to the file to be obfuscated/clarified
    """
    newFile = file[:-4]
    prompt = "Enter 'Y' to write results to file "
    if ".mud" in file:
        userIn = input(prompt + newFile + "2.txt: ")[0]
        if userIn == "Y" or userIn == "y":
            newFile = newFile + "2.txt"
            build = open(newFile, "w")
            build.write(clarify(file))
            build.close()
        else:
            print(clarify(file))
    elif ".txt" in file:
        userIn = input(prompt + newFile + ".mud: ")[0]
        if userIn == "Y" or userIn == "y":
            newFile = newFile + ".mud"
            build = open(newFile, "w")
            build.write(muddle(file))
            build.close()
        else:
            print(muddle(file))
    else:
        print("File name must have '.mud' or '.txt' suffix.")

def checkSuffix(file):
    """
    Check suffix of file name to determine whether the file will
    be obfuscated or clarified
    :param file: File name
    """
    if ".mud" in file or ".txt" in file:
        promptFile(file)
    else:
        print("File name must have '.mud' or '.txt' suffix.")

def process(file):
    """
    Temporarily unnecessary but allows for future functions to be called
    for the file, for example if it needed to be both muddled and then
    clarified and printed both times
    :param file: Path to the file
    """
    checkSuffix(file)

def main():
    while True:
        userIn = input("Enter file name, or an empty string to quit: ")
        if userIn == "":
            print("Program quit successfully.")
            break
        else:
            process(userIn)

if __name__ == '__main__':
    main()