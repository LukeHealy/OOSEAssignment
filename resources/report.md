# <center> Object Oriented Software Engineering </center>
# <center> Assignment 1 - Semester 2</center>
___
## Polymorphism
The following parts of my solution use polymorphism.

* **Property.** The `Property` class implements the ***Template Method*** pattern. It forces a `Property` to implement methods such as `calcProfit()`, `isBusinessUnit()` and `isCompany()`. An advantage of using polymorphism for `Company`, `BusinessUnit` and `BankAccount`, in the form of a `Property` is so that a Company can maintain a container of `Property` objects, and call the required functionality on each, such as `calcProfit()` without caring about which type of `Property` each is. Another advantage is when buying or selling a `Property`; again we don't need to know nor care about whether it's a `Company` or a `BusinessUnit`. 
* **Event.** The `Event` class implements the ***Strategy*** pattern, through the `Eventful` interface. The purpose of this is to define a family of event behaviours, and assign one to a given `Event` at runtime, as they are read from the file. The Strategy pattern was chosen for this reason, because we only know at runtime, the required/desired event behaviour. The `Simulation` can simply retain a list of `Event` objects (which implements `Eventful`), and can call `doEvent()` when appropriate, without caring about what the event actually does.
* **Plan.** The `Plan` class implements the ***Strategy*** pattern through the `Transaction` interface. This was done so that a given transaction behaviour could be defined at runtime, as they are read in from the file. The `Simulation` can then retain a list of `Plan` objects, and call `doTransaction()` when appropriate, without caring about what type of transaction (buy or sell) it may be.
* **Parser.** `Parser` is an interface that is implemented by `EventFileParser`, `PlanFileParser` and `PropertyFileParser`. The purpose of this is so that the `FileIO` class can contain a ***Factory***; `makeParser()`, which, given a file header, can decide which type of `Parser` to make, and can return it for use. This means that to read a file, the program doesn't need to know what type of file it is, it simply gives the file to a `Parser`, which knows exatly what to do with it.
* Observer
* Composite
* 


## Testability

* **Parser Factory.** The use of a ***Factory*** for parsing the files means that only the `Parser` implementations needed to be tested. It wasn't necessary to test, or even implement any logic related to figuring out when to expect a certain file, or what to do when files are given in the wrong order. All of this is inherantly handled by the factory, which is a trivial `switch` statement.
* **Dependancy Injection.** Used it like innit?
* 

## Alternative Design Choices

1. 1
2. 2