package io.mesosphere.codechallenge.tim

import scala.collection.immutable.SortedSet
import io.mesosphere.codechallange.tim.MyElevatorControlSystem


object Main {
  def main(args: Array[String]): Unit = {
		  try {
        println("How many floors? (integer value greater than 0)");
        val line = scala.io.StdIn.readLine()
      	val numberOfFloors = line.toInt;
        println("How many elevators? (integer value greater than 0)");
        val line2 = scala.io.StdIn.readLine()
      	val numberOfElevators = line2.toInt;
        val elevatorSystem = new MyElevatorControlSystem(numberOfFloors, numberOfElevators);
        var quit = false;
        while (!quit){
          println(s"Status: ${ elevatorSystem.status()}");
          println("Where to Pickup? (h for help, q for quit, s for step)");
          scala.io.StdIn.readLine() match {
            case "h" => printHelp();
            case "q" => quit = true;
            case "s" => elevatorSystem.step();
            case line3 =>  val splittedLines = line3.split(" ");
                          if (splittedLines.size == 2){              
                          	splittedLines(1).toInt match {
                          	case 1 => 
                          	  elevatorSystem.pickup(splittedLines(0).toInt, Constants.DIRECTIONUP)
                          	case -1 => 
                          	  elevatorSystem.pickup(splittedLines(0).toInt, Constants.DIRECTIONDOWN)
                          	case default => printHelp();
                          	}
                          }
                          else if (splittedLines.size == 4){
                            elevatorSystem.update(splittedLines(1).toInt, splittedLines(2).toInt, splittedLines(3).toInt)
                          }                            
                          else {
                        	  println("Wrong input...");
                        	  printHelp;
                          }
          }
        }
      } catch {
        case e: Exception =>
          e.printStackTrace()
          println("Good bye, I don't want to play with you");
      }
  }
  
  def printHelp(){
    println("""Write Pickup like "12 -1" where 12 stands for the floor and -1 for down or 1 for up""");
    println("""Write Update like "u 1 12 5" where 1 stands for the elevator id, 12 for the new floor and 5 for the new goalfloor""");
  }
}