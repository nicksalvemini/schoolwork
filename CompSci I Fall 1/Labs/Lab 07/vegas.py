"""
Nick Salvemini
PSS 07
Creates a deck of cards and plays a vegas game
where you must build a victory pile based on a
random card you draw from a deck
"""

import cs_queue as q
import cs_stack as stack
import random


def initDeck(numCards):
    """
    Initializes a deck of cards as
    a queue
    :param numCards: Number of cards to
                        be in the deck
    :return: Deck of cards
    """
    deck = q.make_empty_queue()
    for i in range(1, numCards + 1):
        q.enqueue(deck, i)
    return deck

def initDiscard():
    """
    Creates an empty stack
    :return: Empty stack
    """
    return stack.make_empty_stack()

def randomDraw(deck):
    """
    Cycles through the cards a random
    number of times, then pulls off top
    card to use in game
    :param deck: Deck of cards
    :return: Top card after shuffling
    """
    k = random.randint(1, deck.size)
    for i in range(k):
        q.enqueue(deck, q.dequeue(deck))
    return q.dequeue(deck)

def updateNextCard(victory):
    """
    Determines the value of the next card
    that can be placed in the victory pile
    :param victory: Current victory pile
    :return: Value of next expected card
                in victory pile
    """
    if stack.is_empty(victory):
        nextCard = 1
    else:
        nextCard = stack.top(victory) + 1
    return nextCard

def checkDiscards(victory, nextC, d1, d2):
    """
    Checks the top of each discard pile
    to make sure that the next expected
    card in the victory pile is not on
    top of either
    :param victory: Victory pile
    :param nextC: Next card that can be
                    played on victory pile
    :param d1: Discard pile 1
    :param d2: Discard pile 2
    """
    runAgain = False
    if not stack.is_empty(d1):
        if stack.top(d1) == nextC:
            stack.push(victory, stack.pop(d1))
            nextC = updateNextCard(victory)
            runAgain = True
    if not stack.is_empty(d2):
        if stack.top(d2) == nextC:
            stack.push(victory, stack.pop(d2))
            nextC = updateNextCard(victory)
            runAgain = True
    if runAgain:
        checkDiscards(victory, nextC, d1, d2)


def placeCard(card, victory, d1, d2):
    """
    Places the card in the correct pile based
    on the value of the card
    :param card: Card to be sorted into a pile
    :param victory: Empty stack for cards to
                        be placed into
    :param d1: First discard pile
    :param d2: Second discard pile
    """
    nextCard = updateNextCard(victory)

    # Card can go in victory pile
    if card == nextCard:
        stack.push(victory, card)
        nextCard = updateNextCard(victory)
        checkDiscards(victory, nextCard, d1, d2)
    # Discard 1 is empty
    elif stack.is_empty(d1):
        # Discard 1 and 2 are both empty
        if stack.is_empty(d2):
            stack.push(d1, card)
        # Discard 1 empty, Discard 2 has cards, Card
        # will block off Discard 2 if played there
        elif card > stack.top(d2):
            stack.push(d1, card)
        # Discard 1 empty, Discard 2 has cards, Card
        # will not block off Discard 2 if played there
        else:
            stack.push(d2, card)
    # Discard 1 not empty
    else:
        # Discard 1 has cards, Discard 2 empty
        if stack.is_empty(d2):
            # 1 has cards, 2 empty, playing card
            # in Discard 1 will not block
            if card < stack.top(d1):
                stack.push(d1, card)
            # 1 has cards, 2 empty, playing card
            # in Discard 1 will block
            else:
                stack.push(d2, card)
        # Discards 1 and 2 both have cards
        else:
            # Card will not block either pile,
            # Put card in pile with smaller card on top
            if card < stack.top(d1) and card < stack.top(d2):
                if stack.top(d1) < stack.top(d2):
                    stack.push(d2, card)
                else:
                    stack.push(d1, card)
            # Card will only block one pile,
            # Put card so it does not block
            elif card in range(stack.top(d1), stack.top(d2)):
                stack.push(d2, card)
            elif card in range(stack.top(d2), stack.top(d1)):
                stack.push(d1, card)
            # Card would block either pile
            # Put card so it blocks bigger card
            else:
                if stack.top(d1) < stack.top(d2):
                    stack.push(d2, card)
                else:
                    stack.push(d1, card)

def displayOutput(key, victory, d1, d2, gameNum, vicSizes, maxVic, minVic):
    """
    Neatly displays relevant information
    and program output
    :param key: Determines whether to output
                    single game info or total stats
    :param victory: Victory pile
    :param d1: Discard pile 1
    :param d2: Discard pile 2
    :param gameNum: Number of game being played
    :param vicSizes: List containing the size of
                        the victory pile at the end
                        of each playthrough
    :param maxVic: Size of the largest victory pile
    :param minVic: Size of the smallest victory pile
    """
    if key == 0:
        d1Lst, d2Lst, vicLst = [], [], []
        while not stack.is_empty(d1):
            d1Lst.append(stack.pop(d1))
        while not stack.is_empty(d2):
            d2Lst.append(stack.pop(d2))
        while not stack.is_empty(victory):
            vicLst.append(stack.pop(victory))

        print('Results of game number', gameNum)
        print('Money made (Size of victory pile):', len(vicLst))
        print('Victory pile:', vicLst[::-1])
        print('Discard pile 1: Bottom->', d1Lst[::-1], '<-Top')
        print('Discard pile 2: Bottom->', d2Lst[::-1], '<-Top')
        print('----------------------------------------')
    else:
        accum = 0
        for el in vicSizes:
            accum += el
        vicAvg = accum / len(vicSizes)
        print('Average size of victory pile:', vicAvg)
        print('Largest victory pile in all sims:', maxVic)
        print('Smallest victory pile in all sims:', minVic)

def playGame(numCards, plays):
    """
    Runs through the entire deck, calling
    the function to shuffle the cards and
    play the next card until the deck is empty
    :param numCards: Number of cards in the deck
    :param plays: Number of times the game should
                    be simulated
    """

    # Info to save
    maxVic, minVic = 0, numCards
    vicSizes = []
    for i in range(plays):
        deck = initDeck(numCards)
        discard1 = initDiscard()
        discard2 = initDiscard()
        win = initDiscard()
        while not q.is_empty(deck):
            card = randomDraw(deck)
            placeCard(card, win, discard1, discard2)
        vicSizes.append(stack.size(win))
        if stack.size(win) > maxVic:
            maxVic = stack.size(win)
        if stack.size(win) < minVic:
            minVic = stack.size(win)
        displayOutput(0, win, discard1, discard2, i + 1, None, None, None)
    displayOutput(1, None, None, None, None, vicSizes, maxVic, minVic)

def main():
    """
    Prompts user for number of cards in the deck
    and how many times the game should be played,
    then plays games
    """
    numCards = int(input('Enter number of cards in the deck: '))
    plays = int(input('Enter number of times game should be played: '))
    playGame(numCards, plays)

if __name__ == '__main__':
    main()