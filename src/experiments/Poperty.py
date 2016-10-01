###
# PURPOSE: This file contains all property classes and their behaviours.
# AUTHOR: Luke Healy
# DATE: 30/09/16
#
#!/bin/python


##
# Property is the parent property class, from which the rest inherit.
class Poperty:

    monetaryValue = 0
    profit = 0
    owner = None # A company
    sellable = False

    def __init__(self, monetaryValue, owner):
        self.monetaryValue = monetaryValue
        self.owner = owner

    def calcProfit(self):
        raise NotImplementedError

##
# BankAccount is a non-sellable property. Every company has exactly one.
class BankAccount(Poperty):

    # If monetaryValue is negative, this will just subtract 5%.
    def calcProfit(self):
        self.profit = 0.05 * self.monetaryValue

##
# BusinessUnit can be sold. Every BusinessUnit has the same wage. 
class BusinessUnit(Poperty):

    revenue = 0
    wage = 0

    self.sellable = True

    def calcProfit(self):
        self.profit = self.revenue - self.wages

##
# Company is a property that contains other properties.
class Company(Poperty):

    # Forced single instance of BankAccount.
    bank = BankAccount(0, self)

    # Container of owned properties.
    properties = []

    sellable = True

    def calcProfit(self):
        totalPropertyProfits = 0

        for p in properties:
            totalPropertyProfits = totalPropertyProfits + p.profit

        if totalPropertyProfits <= 0:
            bank.monetaryValue = bank.monetaryValue - totalPropertyProfits
        else
            bank.monetaryValue = bank.monetaryValue + (0.5 * totalPropertyProfits)

    # TODO: Ownership check
