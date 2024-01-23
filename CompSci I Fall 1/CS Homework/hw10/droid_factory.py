"""
Nick Salvemini
HW 10
Builds an army of droids
"""

from dataclasses import dataclass
import cs_queue as q

@dataclass()
class Droid:
    """
    Creates a Class representing an
    individual droid, containing boolean
    variables for whether or not a corresponding
    part is present on a droid. Also contains serial
    number for each Droid
    """
    head: bool
    body: bool
    arms: bool
    legs: bool
    serial: int

def getParts(queue, filename):
    """
    Reads a file full of parts and enqueues
    each part as a string value
    :param queue: Preexisting queue to be filled with parts
    :param filename: Path to file containing parts
    """
    with open(filename) as f:
        for line in f:
            q.enqueue(queue, line.strip())

def buildQueue():
    """
    Calls a function in the cs_queue module
    to create an empty queue
    :return: An empty queue
    """
    return q.make_empty_queue()

def usePart(droid, belt, part):
    """
    Runs through a series of if statements
    to determine which field in the Droid
    class to check/change. If part is already
    on current droid, re-queues the part on the
    conveyor belt. If not, adds part to droid
    :param droid: Instance of the Droid class
    :param belt: Queue/Conveyor belt containing
                    droid parts
    :param part: String value representing which
                    part of the droid was just
                    pulled off the conveyor belt
    """
    if part == 'head':
        if droid.head:
            q.enqueue(belt, part)
            print('\tPlacing extra head back on conveyor belt...')
        else:
            droid.head = True
            print('\tAttaching head...')
    elif part == 'arms':
        if droid.arms:
            q.enqueue(belt, part)
            print('\tPlacing extra arms back on conveyor belt...')
        else:
            droid.arms = True
            print('\tAttaching arms...')
    elif part == 'body':
        if droid.body:
            q.enqueue(belt, part)
            print('\tPlacing extra body back on conveyor belt...')
        else:
            droid.body = True
            print('\tAttaching body...')
    elif part == 'legs':
        if droid.legs:
            q.enqueue(belt, part)
            print('\tPlacing extra legs back on conveyor belt...')
        else:
            droid.legs = True
            print('\tAttaching legs...')

def buildDroid(belt, serial):
    """
    Creates an instance of a droid with
    no parts to start and with a serial
    number passed down through function
    call. Continues through loop until
    droid is completely built
    :param belt: Queue/Conveyor belt containing
                    parts of droids
    :param serial: Serial number of current droid
    """
    droid = Droid(False, False, False, False, serial)
    while not (droid.head and droid.arms and droid.body and droid.legs):
        part = q.dequeue(belt)
        usePart(droid, belt, part)
    print('Droid', serial, 'has been assembled!')

def buildArmy(belt):
    """
    Builds droids until the conveyor belt runs
    out of parts. Each droid has a serial number,
    beginning with 10000 and increasing by 1 for
    each droid
    :param belt: Queue/Conveyor belt containing
                    parts of droids
    """
    serial = 10000
    print('Starting a shift at the droid factory!')
    while not q.is_empty(belt):
        print('Building a droid with serial number', serial)
        buildDroid(belt, serial)
        serial += 1

def main():
    """
    Prompts user for filename containing list of droid
    parts. Calls functions to create an empty belt, fill
    the belt with parts from a file, and build an army of
    droids.
    """
    filename = input('Enter filename containing parts: ')
    conBelt = buildQueue()
    getParts(conBelt, filename)
    buildArmy(conBelt)
    print('All of the droids have been assembled! Time to clock out and play Sabacc...')

if __name__ == '__main__':
    main()