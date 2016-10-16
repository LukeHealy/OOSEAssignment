# <center> Object Oriented Software Engineering </center>
# <center> Assignment 1 - Semester 2</center>
___
## Polymorphism
The following parts of my solution use polymorphism.

* **Property.** The `Property` class implements the Template Method pattern. It forces a `Property` to implement methods such as `calcProfit()`, `isBusinessUnit()` and `isCompany()`. An advantage of using polymorphism for `Company`, `BusinessUnit` and `BankAccount`, in the form of a `Property` is so that a Company can maintain a container of `Property` objects, and call the required functionality on each, such as `calcProfit()` without caring about which type of `Property` each is. Another advantage is when buying or selling a `Property`; again we don't need to know nor care about whether it's a `Company` or a `BusinessUnit`.
* **Eventful.** The `Eventful` interface implements the Strategy pattern. The purpose of this interface is to define a family of event behaviours, and assign one to a given `Event` at runtime, as they are read from the file. The Strategy pattern was chosen for this reason, because we only know at runtime, the required/desired event behaviour. The `Simulation` can simply retain a list of `Event` objects (which implements `Eventful`), and can call `doEvent()` when appropriate, without caring about what the event actually does.
* **WageEvent.** The `WageEvent` is a special type of event.
* **Transaction.**
* **Parser.**


## Testability

Stuff.

## Alternative Design Choices

1. 1
2. 2