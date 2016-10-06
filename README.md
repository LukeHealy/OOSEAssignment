# OOSEAssignment
Object Oriented Software Engineering Assignment, 2016, Semester 2 Curtin University.

## Purpose

This program simulates 0 or more businnes decisions, made in a given economic context, for the purpose of training a company director.

The point of this assignment was to implement a system design, given a complex specification, and make good use of OO design patterns and general practices.

## Compile/Build

This project builds useing ant. From the root directory, execute `ant` and the project will compile and build. ant will create a directory called `dist`, which containes the executable "Simulation.jar".

## Execute

To run the program, from the root directory, simply type `./run.sh` followed by the three file names (in any order), and the start and end year.
For example:

<center>`./run.sh property.csv events.csv plans.csv 2013 2020`</center>

Alternatively, one may execute Simulation.jar, from inside the `dist` directory using the `java -jar` command. For example:

<center>`java -jar Simulation.jar property.csv events.csv plans.csv 2013 2020`</center>

## Input

This program takes no user input, except for the command line arguments discussed above.

##Output

The expected output for this program is a table of the year, each company's name and it's bank balance, for each year of the simulation. For example:

