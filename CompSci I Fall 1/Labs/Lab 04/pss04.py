"""
Nick Salvemini
PSS04
Muddles and Demuddles a string.
"""

def muddle(file):
        for line in open(file):
            print(line[::-1].strip())

def checkSuffix(file):
    if ".mud" in file or ".txt" in file:
        muddle(file)
    else:
        print("ERROR: Wrong file type.")

def recursive(str):
    if len(str) == 0:
        return ""
    else:
        return str[-1] + recursive(str[:len(str)-1])


def main():
    checkSuffix(input("Enter file name: "))
    for line in (open(input("Enter file name: "))):
        print(recursive(line).strip())

main()