# RocketMiles: HelloChange!
Author: **Kyle R. Almryde**

#### Preamble

Short and sweet little assignment for the good folks at RocketMiles. Written in [Kotlin]("https://kotlinlang.org/"), build with [Gradle]("https://gradle.org/"), and tested with [Spek]("http://spekframework.org/")  

### How do I get set up?
Grab your favorite IDE and import the project from source. This project uses ``Gradle`` for dependency 
management and for running unit tests, so make sure you have it installed. 

---

#### Running Application
Run the application in your IDE of choice via the main method found in 
``com.rocketmiles.krba.AppKT``. It is not recommended at this time to execute the ``gradle run`` task.  

#### How to play?
At the prompt, `>` enter a command. Available commands include:
* `show` - Show the current denominations and total amount of money in the register
* `put _ _ _ _ _` - "Put" money in the register. Arguments must be at 5 space separated numbers representing the number of that dollar you wish to add to the register  
* `take _ _ _ _ _` - "Take" money from the register. Arguments must be at 5 space separated numbers representing the number of that dollar you wish to add to the register
* `change _` - Make change for some amount of money. Argument must be an Integer. 
* `quit` - Quits the program

---

### Who to contact? 
* If you have any specific questions contact me via [kyle.almryde@gmail.com](mailto:kyle.almryde@gmail.com)