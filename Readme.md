# Readme

This is the elevator control system of Tim Sielemann. 

It implements the so called Nearest Car Algorithm for elevator scheduling. 

## Nearest Car Algorithm
The algorithm is in unlike the FCFS algorithm aware of the actual state of the elevators and select the elevator which goes in the right direction and is at minimum distance to the pickup request. This minimizes the times a elevator has to change directions. It also is aware of the fact that if there is a new pickup request on the way between his current position and goal he can handle this before the current pickup request. 

You can find more information to this algorithm [here](http://www.diva-portal.org/smash/get/diva2:811554/FULLTEXT01.pdf).

## Starting
You can start the application by using the sbt command `sbt run` while the working directory is the project folder.
The main class is io.mesosphere.codechallange.tim.Main.scala. There aren't any further dependencies, so you can start it with the normal scala command too.

After starting the application you can handle all functionality within stdInput. First you have to give in the number of floors and elevators of the simulated building. Afterwards you can put in pickup and update requests. 

___
### Example
```
How many floors? (integer value greater than 0)

12

How many elevators? (integer value greater than 0)

3

Status: Vector((0,0,TreeSet()), (1,0,TreeSet()), (2,0,TreeSet()))

Where to Pickup? (h for help, q for quit, s for step)

12 -1

Elevator 0 is picking up at 12 floor

Status: Vector((0,0,TreeSet(12)), (1,0,TreeSet()), (2,0,TreeSet()))

Where to Pickup? (h for help, q for quit, s for step)

s

Status: Vector((0,1,TreeSet(12)), (1,0,TreeSet()), (2,0,TreeSet()))

Where to Pickup? (h for help, q for quit, s for step)

s

Status: Vector((0,2,TreeSet(12)), (1,0,TreeSet()), (2,0,TreeSet()))

Where to Pickup? (h for help, q for quit, s for step)

s

Status: Vector((0,3,TreeSet(12)), (1,0,TreeSet()), (2,0,TreeSet()))

Where to Pickup? (h for help, q for quit, s for step)

u 1 5 3

Status: Vector((0,3,TreeSet(12)), (1,5,TreeSet(3)), (2,0,TreeSet()))

Where to Pickup? (h for help, q for quit, s for step)

s
Status: Vector((0,4,TreeSet(12)), (1,4,TreeSet(3)), (2,0,TreeSet()))

Where to Pickup? (h for help, q for quit, s for step)

s

Status: Vector((0,5,TreeSet(12)), (1,3,TreeSet(3)), (2,0,TreeSet()))

Where to Pickup? (h for help, q for quit, s for step)

q
```
___