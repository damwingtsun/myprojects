import random

# sides of each pip with bottom as -1 item
pip_1 = (2,3,4,5,6)
pip_2 = (1,3,4,6,5)
pip_3 = (1,2,5,6,4)
pip_4 = (1,2,5,6,3)
pip_5 = (1,3,4,6,2)
pip_6 = (2,3,4,5,1)

# Create dice side dictionary
die_side_dict = {1: pip_1, 2: pip_2, 3: pip_3, 4: pip_4, 5: pip_5, 6: pip_6}

# Find out the lowest pip set possible with only one side turn after roll complete.
# Iterates through the roll from the lowest position first.
# There might be no complete match.  'x' represents no side match.


# create pips array
def roll_dice(dice):
    pips = []
    for num in range(dice):
        side = random.randint(1, 6)
        pips.append(side)
    return pips


# go through array of pips rolled and find a uniform match
# from lowest pip up to highest pip showing
# return the lowest possible pip that has a complete match

def pips_set(my_roll):
    die_count = len(my_roll)
    result = []
    turn_totals = {}

    # assign showing die pip as a pip_check for each die
    for die in my_roll:
        pip_check = die
        print("\nPip check: {}".format(pip_check))
        dice_turn_count = 0

        # check to see if there is a complete match starting with 1st dice in roll
        for pip_up in my_roll:
            # have a match without showing, add it to the result, move on to next die
            if pip_up == pip_check:
                result.append(pip_up)
                pip_up += 1
            # not a showing match with pip up, then check each side or bottom side for match
            elif pip_up != pip_check:
                for key in die_side_dict:
                    if pip_up == key:
                        if pip_check in die_side_dict[key]:
                            result.append(pip_check)
                            if pip_check == die_side_dict[key][-1]:
                                print("Bottom match found with die {}".format(pip_up))
                                dice_turn_count += 2
                            else:
                                print("Side match found with die {}".format(pip_up))
                                dice_turn_count += 1

                        else:
                            print("No side match with die {}.".format(pip_up))
                            result.append('x')

        # end of inner loop for checking all dice against current pip_check
        print("Result ==> {}".format(result))

        # complete match possible and added to total list
        if 'x' not in result:
            print('Success!  Complete Pips Match!')
            print("Total dice turns for this pip check: {}".format(dice_turn_count))
            # populate total dictionary
            if pip_check not in turn_totals:
                turn_totals[pip_check] = dice_turn_count

        # no match possible with
        elif 'x' in result:
            print("Sorry.  No complete match possible.")

        # optional list to sort dictionary in case
        lst = list()
        for pip_check, dice_turn_count in turn_totals.items():
            lst.append((dice_turn_count, pip_check))
        lst.sort()


        # clear pip roll
        result = []

    # end of outer main loop with final total
    print("\n====== Summary Report ======")
    print("Roll ==> {}".format(my_roll))
    print("List of all possible pip match combos with turn totals"
          "\n(Pip Check #: Turn Total)\n{}".format(turn_totals))


    # totals evaluation
    if all(value == dice_turn_count for value in turn_totals.values()):
        print("All totals are equal.")

    else:
        pip_min = min(turn_totals.keys(), key=(lambda k: turn_totals[k]))
        print("Smallest pip check with least amount of side turns: {}".format(pip_min))
        print("\nList by turn total first: \n(Turn Total, Pip_Check #)")
        print(lst)

    return


# get user input
dice = int(input("How many dice do you have? \nPlease enter an amount; I will roll.==>"))

# get k or dice for roll
while dice <= 1:
    dice = int(input("Please enter a minimum of 2."))


# create variable for test run
my_roll = roll_dice(dice)
my_roll.sort()

print("\nTotal dice rolled: {}".format(dice))

pips_set(my_roll)

