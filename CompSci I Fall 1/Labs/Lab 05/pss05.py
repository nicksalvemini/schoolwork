"""
Nick Salvemini
PSS 05
"""

def buildList(file):
    list = []
    for line in open(file):
        list += [int(line)]
    return list

def placeBuilding(list):
    list.sort()
    print(list)
    if len(list) % 2 == 1:
        return list[len(list) // 2]
    else:
        return (list[len(list) // 2] + list[len(list) // 2 - 1]) / 2

def calcDist(list, loc):
    sum = 0
    for el in list:
        sum += abs(el - loc)
    return sum

def main():
    file = input("Enter file name containing building locations"
                 " (For PSS, file is buildings.txt): ")
    list = buildList(file)
    loc = placeBuilding(list)
    totDistance = calcDist(list, loc)
    print('Donut shop will best be placed at', loc,
          ', total distance to all offices is', totDistance)

if __name__ == '__main__':
    main()