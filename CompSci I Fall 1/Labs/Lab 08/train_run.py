"""
Nick Salvemini
PSS 08
Runs a train yard simulation.
"""

import linked_list_type as llt
import mutable_list as mlist
from dataclasses import dataclass

@dataclass(frozen=True)
class TrainCar:
    """
    A class representing an individual train car
    cargo: String telling what the car holds
    dest: String telling where the car is going
    mile: Floating point number telling how far
            the destination is
    """
    cargo: str
    dest: str
    mile: float

@dataclass
class Train:
    """
    A class representing an entire train
    speed: Floating point representing the train
            speed in mph
    cars: A linked list representing the train cars
            that make up the train
    size: An int representing the number of cars
            attached to the train
    """
    speed: float
    cars: llt.LinkedList
    size: int

def getEndCar(train):
    """
    Takes a Train class instance as an argument
    and looks through it to find the last car
    :param train: Instance of Train class
    :return: Car at end of Train
    """
    if train.cars.head is None:
        return 'END'
    else:
        endCar = train.cars.head
        while endCar.next is not None:
            endCar = endCar.next
        return endCar.value

def set_speed(train, speed):
    """
    Set the speed of the train
    :param train: Train to change speed of
    :param speed: Desired speed
    """
    train.speed = speed

def add_car(train, content, station, distance):
    """
    Adds a new car to a train in the following order:
    Car closest to the front has to travel the furthest,
    Car closest to the back has the closest destination
    :param train: Train to add new car to
    :param content: Cargo of new car
    :param station: Destination of new car
    :param distance: Distance of destination of new car
    """
    car = TrainCar(content, station, distance)
    if train.cars.size != 0:
        newDest = car.mile
        newPlace = train.cars.head
        for i in range(train_size(train)):
            if newDest > newPlace.value.mile:
                mlist.insert_before_index(train.cars, car, i)
                break
            elif newPlace.next is not None:
                newPlace = newPlace.next
            else:
                mlist.append(train.cars, car)
                break
        train.size += 1
    else:
        mlist.append(train.cars, car)
        train.size += 1

def train_size(train):
    """
    Returns size of train
    :param train: Train being looked at
    :return: Number of cars attached to train
    """
    return train.size

def show_train(train):
    """
    Shows the engine speed as well as all
    the cars attached to the train
    :param train: Train being looked at
    """
    trainList = []
    car = train.cars.head
    for i in range(train.size):
        trainList.append(car.value)
        if i < train.size - 1:
            car = car.next
    backwardsList = trainList[::-1]
    print('engine(', train.speed, end=' ) ')
    print(backwardsList)

def start(train):
    """
    Executes the train simulation. Moves train
    to each individual destination, dropping
    off train cars at each one until all cars
    have been dropped off, then ends simulation
    :param train: Train in simulation
    """
    if train.speed is None or train.speed == 0:
        print('Train speed is either 0 or has not been set.')
    else:
        endCar = getEndCar(train)
        dest = endCar.dest
        lastDist = 0
        dist = endCar.mile - lastDist
        duration = 0

        while train.size != 0:
            print('Moving on to ' + dest)
            while endCar.dest == dest:
                mlist.remove_value(train.cars, endCar)
                train.size -= 1
                print('Unloading ' + endCar.cargo + ' in ' + dest)
                endCar = getEndCar(train)
                if endCar == 'END':
                    break
            print('Half an hour taken to separate cars.')
            duration += .5
            print('This segment took', ("%0.2f" % (dist / train.speed)), 'hours to travel.')
            duration += dist / train.speed
            if endCar != 'END':
                lastDist = dist
                dest = endCar.dest
                dist = endCar.mile - lastDist
        print('Total duration of trip was', ("%0.2f" % duration), 'hours.')

def help():
    """
    Prints list of available commands
    """
    commands = {'set_speed <speed>': 'Set train speed.',
                'add_car <content> <station> <distance>': 'Add a new car to the train.',
                'train_size': 'Show number of cars on train.',
                'show_train': 'Show train speed and all cars on train in order.',
                'start': 'Begin path of train, stopping at each destination to drop off cars.',
                'help': 'Print list of available commands.',
                'quit': 'Terminate program.'}
    print('Available Commands\n---------------------')
    for key in commands:
        print(key + ': ' + commands[key])
    print('---------------------')

def process_commands(train):
    """
    Takes user input and splits it up into processable
    functions and arguments, then calls the respective functions
    to ultimately execute the train yard simulation
    :param train: Train for commands to be executed on
    """
    userIn = ''
    formError = 'Illegal use or form for this command.' \
                '\nType "help" for a list of available commands.'
    while userIn != 'quit':
        userIn = input('Enter command: ')
        splitIn = userIn.split()
        if splitIn[0] == 'set_speed':
            if len(splitIn) == 2:
                set_speed(train, int(splitIn[1]))
                print('Train speed set to', train.speed)
            else:
                print(formError)
        elif splitIn[0] == 'add_car':
            if len(splitIn) == 4:
                add_car(train, splitIn[1], splitIn[2], float(splitIn[3]))
                print('Car added.')
            else:
                print(formError)
        elif splitIn[0] == 'train_size':
            if userIn == 'train_size':
                print('Number of cars attached to the train is', train_size(train))
            else:
                print(formError)
        elif splitIn[0] == 'show_train':
            if userIn == 'show_train':
                show_train(train)
            else:
                print(formError)
        elif splitIn[0] == 'start':
            if userIn == 'start':
                start(train)
                userIn = 'quit'
            else:
                print(formError)
        elif splitIn[0] == 'help':
            if userIn == 'help':
                help()
            else:
                print(formError)
        elif userIn == 'quit':
            pass
        else:
            print('Illegal command name.')

def main():
    """
    Prints welcome message, runs simulation, then
    prints closing message
    """
    print('Welcome to the train yard.')
    help()
    train = Train(0, mlist.make_empty_list(), 0)
    process_commands(train)
    print('Leaving the train yard.')

if __name__ == '__main__':
    main()