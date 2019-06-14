import random

suits = ('Hearts', 'Diamonds', 'Spades', 'Clubs')
ranks = ('Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight', 'Nine', 'Ten', 'Jack', 'Queen', 'King', 'Ace')
values = {'Two': 2, 'Three': 3, 'Four': 4, 'Five': 5, 'Six': 6, 'Seven': 7, 'Eight': 8, 'Nine': 9,
          'Ten': 10, "Jack": 10, 'Queen': 10, 'King': 10, 'Ace': 11}

playing = True
first_game = True


class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank

    def __str__(self):
        return self.rank + " of " + self.suit


class Deck:
    def __init__(self):
        self.deck = []
        for suit in suits:
            for rank in ranks:
                self.deck.append(Card(suit, rank))

    def __str__(self):
        deck_comp = ""
        for card in self.deck:
            deck_comp += "\n" + card.__str__()
        return "The deck has:" + deck_comp

    def shuffle(self):
        random.shuffle(self.deck)

    def deal(self):
        single_card = self.deck.pop()
        return single_card


class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0
        self.aces = 0

    def add_card(self, card):
        # from Deck.deal()
        self.cards.append(card)
        self.value += values[card.rank]

        if card.rank == "Ace":
            self.aces += 1

    def adjust_for_ace(self):

        while self.value > 21 and self.aces > 0:
            self.value -= 10
            self.aces -= 1


class Chips:
    def __init__(self):
        self.total = 100
        self.bet = 0

    def win_bet(self):
        self.total += self.bet

    def lose_bet(self):
        self.total -= self.bet


# #Player 1
# test_player = Hand()
# #Deal one card from the test_deck
# pulled_card = test_deck.deal()
# print(pulled_card)
# test_player.add_card(pulled_card)
# print(test_player.value)
#
# test_player.add_card(test_deck.deal())


def take_bet(chips):
    while True:
        try:
            chips.bet = int(input("How many chips would you like to bet?"))

        except:
            print('Please provide an integer.')

        else:
            if chips.bet > chips.total:
                print(f"You don't have enough chips.  You have {chips.total} chips.")

            else:
                break


def hit(deck, hand):
    single_card = deck.deal()
    hand.add_card(single_card)
    hand.adjust_for_ace()


def hit_or_stand(deck, hand):
    global playing

    while True:
        x = input("Hit or stand?  Enter h or s.\n").lower()
        if x[0] == 'h':
            hit(deck, hand)

        elif x[0] == 's':
            print("Player stands.  Dealer's turn.")
            playing = False

        else:
            print("Please enter only an 'h' or 's'.")
            continue
        break


def show_some(player, dealer):
    print("\nDEALER'S HAND:")
    print("one card hidden!")
    print(dealer.cards[1])
    print('\n')
    print("\nPLAYER'S HAND:")
    for card in player.cards:
        print(card)


def show_all(player, dealer):
    print("\nDEALER'S HAND:")
    for card in dealer.cards:
        print(card)
    print("\n")
    print("\nPLAYER'S HAND:")
    for card in player.cards:
        print(card)


def player_busts(player, dealer, chips):
    if player_hand.value > 21:
        print("\nPlayer busts: hand is over 21.  Dealer wins.")
        chips.lose_bet()


def player_wins(player, dealer, chips):
    print("\nPlayer WINS!")
    chips.win_bet()


def dealer_busts(player, dealer, chips):
    print("\nDealer busts. Player WINS!")
    chips.win_bet()


def dealer_wins(player, dealer, chips):
    print("\nDealer WINS!")
    chips.lose_bet()


def push(player, dealer, chips):
    print("\nDealer and Player tie!")


while True:
    # Create and shuffle the deck.
    game_deck = Deck()
    game_deck.shuffle()

    # Create player hand. Deal 2 cards to player and dealer.
    player_hand = Hand()
    player_hand.add_card(game_deck.deal())
    player_hand.add_card(game_deck.deal())

    dealer_hand = Hand()
    dealer_hand.add_card(game_deck.deal())
    dealer_hand.add_card(game_deck.deal())

    # set up player's chips
    # player_chips = Chips()
    while first_game:
        # print an opening statement
        print("Welcome to BLACKJACK!")
        print("You get 100 chips free to start!")
        player_name = input("What is your name?")
        player_chips = Chips()
        break

    while not first_game:
        player_chips = player_chips
        break

    # prompt player for their bet
    take_bet(player_chips)

    # show cards but keep one dealer card hidden
    show_some(player_hand, dealer_hand)

    while playing:
        # prompt player to hit or stand
        hit_or_stand(game_deck, player_hand)

        # show some cards
        show_some(player_hand, dealer_hand)

        if player_hand.value > 21:
            player_busts(player_hand, dealer_hand, player_chips)

            break

    # if player hasn't busted, play dealer hand until 17
    if player_hand.value <= 21:
        while dealer_hand.value < player_hand.value:
            hit(game_deck, dealer_hand)

        # show all cards
        show_all(player_hand, dealer_hand)

        # run different winning outcomes
        if dealer_hand.value > 21:
            dealer_busts(player_hand, dealer_hand, player_chips)
        elif dealer_hand.value > player_hand.value:
            dealer_wins(player_hand, dealer_hand, player_chips)
        elif dealer_hand.value < player_hand.value:
            player_wins(player_hand, dealer_hand, player_chips)
        else:
            push(player_hand, dealer_hand, player_chips)

    # update player chips total, refresh if zero
    print(f"\nPlayer current total chips: {player_chips.total}")

    # ask to play again
    new_game = input(f"Would you like to play again {player_name}?  Enter 'y' or 'n'").lower()
    if new_game[0] == 'y':
        playing = True
        first_game = False
        if player_chips.total == 0:
            player_chips.total = int(input("You are out of chips.\n"
                                            "Please add more to continue: "))
        continue
    else:
        print("Thanks for playing {}!".format(player_name))
        break