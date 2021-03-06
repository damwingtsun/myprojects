import random


class Attacker():
    num_of_attacks = 0

    def __init__(self, style="", position=""):
        self.style = style
        self.position = position
        self.leftFist = [0, 0]
        self.rightFist = [0, 0]
        self.weapons = []
        self.leftArmWeapons = ['Left Fist']
        self.rightArmWeapons = ['Right Fist']
        self.weapons.extend(self.leftArmWeapons)
        self.weapons.extend(self.rightArmWeapons)
        self.force = ['light', 'medium', 'hard']

        Attacker.num_of_attacks += 1

    def __str__(self):
        return "Attacker: = %s, position: = %s" % (self.style, self.position)

    def distance(self, arms_length, kicking):
        self.arms_length = arms_length
        self.kicking = kicking

    def fists_home(self):
        self.leftFist = [4, -3]
        self.rightFist = [3, 3]
        return "Fists home position:  (left)%s,(right)%s" % (self.leftFist, self.rightFist)

    def arm_strike(self):
        print(Attacker.fists_home(self))
        self.value = random.choice(self.weapons)

        if self.value == 'Left Fist':
            self.leftFist = [(random.randint(0, 10) * -1), random.randint(0, 10)]
            return ("%s:%s Strike Force= %s" % (self.value, self.leftFist, (random.choice(self.force))))

        elif self.value == 'Right Fist':
            self.rightFist = [(random.randint(0, 10)), random.randint(0, 10)]
            return ("%s:%s Strike Force= %s" % (self.value, self.rightFist, (random.choice(self.force))))

opponent1 = Attacker("MMA", "Left Facing")
opponent2 = Attacker("Boxer", "Right Facing")
print(opponent1)
print(opponent1.arm_strike())
print(opponent1.arm_strike())
print(opponent1.arm_strike())
print(opponent1.num_of_attacks)

MMA = Attacker()
print(MMA.arm_strike())

