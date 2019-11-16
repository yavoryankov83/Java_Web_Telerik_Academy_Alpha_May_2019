## OOP Workshop - Principles (Agency)

### General Description

Implement a journey and ticket tracking system for a famous travel agency called `Agency`. The application already accepts commands and outputs text for each submitted command, you just need to write the OOP. You can create different models (`Bus, Airplane, Train, Ticket, Journey`), as well as listing them. Make sure to follow all the good Object Orientated Programming practices and conventions that we have talked about during the lectures and don't let the length of this description intimidate you, read it carefully and start hacking!

### Architecture
Let's talk a bit about how the system works (you are already provided with all of this stuff, there is no need to implement it). There is an `EngineImpl` located in the `core` package that has a loop that cycles until the `Exit` command is submitted. With each cycle, it takes the input, passes it to the command parser that parses the parameters. Then there is `CommandFactoryImpl` that creates the command. All commands are located in the `commands` package. The commands themselves use the `AgencyFactoryImpl` located in the `core.factories` package to create the needed objects and `AgencyRepositoryImpl` to store them. After the command executes, it returns a result message to the `EngineImpl` that prints it to the console and then the cycle beings again.

In the `EngineImpl`, there is a try-catch block that catches every possible exception type and prints the exception's message to the console. If you want to debug the program and see where an exception is thrown, I suggest you comment out that try-catch block during the debugging process. Don't forget to **uncomment it** back when you finish, otherwise your program will crash upon invalid input!

Your focus should be on the `models` and `commands` packages, where you need to place the classes you create, using the provided interfaces in the `contracts` package or implement the commands which are not ready yet. The result from the execution of every command is printed on the console after each step, so please use the input one line at a time.

### Part 1 - Classes

Read the description `carefully` before you proceed to the code base. Read the code base `carefully` before you start implementing the down-mentioned requirements.

##### Validation Notes:

- All validation intervals are inclusive (closed).
- Try to stick to using only **IllegalArgumentException**.

##### Implement and validate the following:

- **Train** has: 
    - `PassangerCapacity` that is a number representing quantity in the interval of `[30, 150]`.
        - Exception message: `A train cannot have less than 30 passengers or more than 150 passengers.`
    - `Carts` that is a number representing quantity in the interval of `[1, 15]`.
        - Exception message: `A train cannot have less than 1 cart or more than 15 carts.`
    - `Type` that is a set of fixed values in the interval of `[Land, Air, Sea]`.
    - `PricePerKilometer` that is a number representing currency.
    - Should be convertable to **String** in the format:

```
Train ----
Passenger capacity: VALUE
Price per kilometer: VALUE
Vehicle type: VALUE
Carts amount: VALUE
```

- **Airplane** has: 
    - `PassangerCapacity` that is a number representing quantity.
    - `Type` that is a set of fixed values in the interval of `[Land, Air, Sea]`.
    - `HasFreeFood` that is a boolean.
    - `PricePerKilometer` that is a number representing currency.
    - Should be convertable to **String** in the format:

```
Airplane ----
Passenger capacity: VALUE
Price per kilometer: VALUE
Vehicle type: VALUE
Has free food: VALUE
```
    
- **Bus** has: 
    - `PassangerCapacity` that is a number representing quantity in the interval of `[10, 50]`.
        - Exception message: `A bus cannot have less than 10 passengers or more than 50 passengers.`
    - `PricePerKilometer` that is a number representing currency.
    - `Type` that is a set of fixed values in the interval of `[Land, Air, Sea]`.
    - Should be convertable to **String** in the format:

```
Bus ----
Passenger capacity: VALUE
Price per kilometer: VALUE
Vehicle type: VALUE
```

- **Journey** has: 
    - `StartLocation` that is a string with length in the interval of `[5, 25]`.
        - Exception message: `The StartingLocation's length cannot be less than 5 or more than 25 symbols long.`
    - `Destination` that is a string with length in the interval of `[5, 25]`.
        - Exception message: `The Destination's length cannot be less than 5 or more than 25 symbols long.`
    - `Distance` that is a number representing quantity in the interval of `[5, 5000]`.
        - Exception message: `The Distance cannot be less than 5 or more than 5000 kilometers.`
    - `Vehicle` that is the vehicle used in the journey.
    - `CalculateTravelCosts()` that returns a currency calculated by:
        - Multiplying the `Distance` by the `Vehicle`'s price per kilometer.
    - Should be convertable to **String** in the format:

```
Journey ----
Start location: VALUE
Destination: VALUE
Distance: VALUE
Vehicle type: VALUE
Travel costs: VALUE
```

- **Ticket** has: 
    - `Journey` that is the journey the ticket is sold for.
    - `AdministrativeCosts` that is a number representing currency.
    - `CalculatePrice()` that returns a currency calculated by:
        - Multiplying the `AdministrativeCosts` by the `Journey`'s travel costs.
    - Should be convertable to **String** in the format:

```
Ticket ----
Destination: VALUE
Price: VALUE
```

- **Factory** has:
    - **createBus(...)** that needs to be implemented.
    - **createAirplane(...)** that needs to be implemented.
    - **createTrain(...)** that needs to be implemented.
    - **createJourney(...)** that needs to be implemented.
    - **createTicket(...)** that needs to be implemented.

##### Additional validations

The laws of physics and finances dictate that:

- A vehicle with `less than 1 passenger` or more than `800 passengers` cannot exist!
    - Exception message: `A vehicle with less than 1 passengers or more than 800 passengers cannot exist!`
- A vehicle with a price per kilometer `lower than $0.10` or `higher than $2.50` cannot exist!
    - Exception message: `A vehicle with a price per kilometer lower than $0.10 or higher than $2.50 cannot exist!`

In your case, there is no such a vehicle, but think about these rules more generally. This system could be extended in the future to accommodate more vehicles.

### Part 2 - Commands

**All commands are case insensitive, except their parameters!** Each command is represented in the code base as a separate class, that is invoked by the CommandFactoryImpl. 

You are given a set of commands. The following are already implemented:
- **CreateBus** `[PassangerCapacity] [PricePerKilometer]` - Creates a new `Bus`.
- **CreateTrain** `[PassangerCapacity] [PricePerKilometer] [Carts]` - Creates a new `Train`.
- **CreateJourney** `[StartLocation] [Destination] [Distance] [VehicleID]` - Creates a new `Journey`.
- **ListJourneys** - Lists all stored journeys.
- **ListTickets** - Lists all stored tickets.

And these are the commands you need to implement yourself:
- **CreateAirplane** `[PassangerCapacity] [PricePerKilometer] [HasFreeFood]` - Creates a new `Airplane`.
- **CreateTicket** `[JourneyID] [AdministrativeCosts]` - Creates a new `Ticket`.
- **ListVehicles** - Lists all stored vehicles.

### Constraints
* You are allowed to create new and modify existing **classes, interfaces, enumerations and packages** in the `models` package.
* You are allowed to modify the **AgencyFactoryImpl** and **CommandFactoryImpl** in the `core.factories` package.
* You are allowed to create and modify existing classes in the `agency.commands` package. **(Careful with the names!)**
* You are allowed to add and remove usings from every file in the solution.
* **You are NOT allowed to modify any other existing interfaces!**
* **You are NOT allowed to modify any other existing classes, enumerations and packages!**

### Sample Input

```
createbus 10 0.7
createairplane 230 1 true
createtrain 80 0.44 3
listvehicles
createjourney Sofia Turnovo 300 0
createjourney Sofia Turnovo 33 1
listjourneys
createticket 0 30.2
createticket 1 100
listtickets
createticket pesho 100
createairplane 250 1 true
createticket 1 pipi
createairplane alabala 23 16
createjourney Sofia Turnovo 3000 0
createjourney SsdddddddddsdsssssssssSsdddddddddsdsssssssss Turnovo 3000 0
createjourney Sofia SsdddddddddsdsssssssssSsdddddddddsdsssssssss 3000 0
createtrain 80 0.08 3
createtrain 80 2.7 3
listtickets
createtrain 28 0.4 3
createtrain 152 0.4 3
listvehicles
exit
```

### Sample Output

```
Vehicle with ID 0 was created.
Vehicle with ID 1 was created.
Vehicle with ID 2 was created.
Bus ----
Passenger capacity: 10
Price per kilometer: 0.70
Vehicle type: Land
####################
Airplane ----
Passenger capacity: 230
Price per kilometer: 1.00
Vehicle type: Air
Has free food: true
####################
Train ----
Passenger capacity: 80
Price per kilometer: 0.44
Vehicle type: Land
Carts amount: 3
Journey with ID 0 was created.
Journey with ID 1 was created.
Journey ----
Start location: Sofia
Destination: Turnovo
Distance: 300
Vehicle type: Land
Travel costs: 210.00
####################
Journey ----
Start location: Sofia
Destination: Turnovo
Distance: 33
Vehicle type: Air
Travel costs: 33.00
Ticket with ID 0 was created.
Ticket with ID 1 was created.
Ticket ----
Destination: Turnovo
Price: 6342.00
####################
Ticket ----
Destination: Turnovo
Price: 3300.00
Failed to parse CreateTicket command parameters.
Vehicle with ID 3 was created.
Failed to parse CreateTicket command parameters.
Failed to parse CreateAirplane command parameters.
Journey with ID 2 was created.
The StartingLocation's length cannot be less than 5 or more than 25 symbols long.
The Destination's length cannot be less than 5 or more than 25 symbols long.
A vehicle with a price per kilometer lower than $0.10 or higher than $2.50 cannot exist!
A vehicle with a price per kilometer lower than $0.10 or higher than $2.50 cannot exist!
Ticket ----
Destination: Turnovo
Price: 6342.00
####################
Ticket ----
Destination: Turnovo
Price: 3300.00
A train cannot have less than 30 passengers or more than 150 passengers.
A train cannot have less than 30 passengers or more than 150 passengers.
Bus ----
Passenger capacity: 10
Price per kilometer: 0.70
Vehicle type: Land
####################
Airplane ----
Passenger capacity: 230
Price per kilometer: 1.00
Vehicle type: Air
Has free food: true
####################
Train ----
Passenger capacity: 80
Price per kilometer: 0.44
Vehicle type: Land
Carts amount: 3
####################
Airplane ----
Passenger capacity: 250
Price per kilometer: 1.00
Vehicle type: Air
Has free food: true
```