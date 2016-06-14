package io.mesosphere.codechallenge.tim

import scala.collection.immutable.SortedSet
/**
 * An Elevator is characterized by an Id, a floor and the floors to which the elevator is going
 * 
 * @param id - The id of the elevator;
 */
class Elevator(val id: Int) {
  /**
   * The actual floor of the elevator
   */
   var floor: Int = 0;
   /**
    * The direction of the Elevator @see Constants
    */
   var direction: Int = Constants.DIRECTIONIDLE;
   /**
    * Goalfloors are hold in a Sorted Set because it automatically sorts the new pickup Requests and there are no duplicates
    */
   var goalfloors: Set[Int] = SortedSet[Int]();
   /**
    * internal value for the current goalfloor
    */
   private var currentGoalFloor: Option[Int] = Option.empty
   
   /**
    * Moves the elevator 1 floor in the current direction 
    * 
    * If a goalfloor is reached, it is deleted from the Set. If there are no more goalfloors, the elevator remains in idling state
    */
   def move() = {
     floor += direction;     
     if (currentGoalFloor.isDefined && currentGoalFloor.get == floor){
    	 this.removeGoalfloor(floor);
    	 currentGoalFloor = this.getNextGoalfloor()
    	 this.setDirection();
     }  
   }
   
   /**
    * Adds a Goalfloor to the Set
    * @param goalFloor - the new goalfloor
    */
   def addGoalFloor(goalFloor: Int){
     if (floor != goalFloor){
    	 goalfloors = goalfloors + goalFloor;       
     }
     currentGoalFloor = this.getNextGoalfloor();
     this.setDirection()
     
   }
   
   /**
    * Sets the new direction by checking the new goalfloor
    */
   private def setDirection() = {
     if (currentGoalFloor.isEmpty){
        this.direction = Constants.DIRECTIONIDLE;
     }
     else {
    	 if (floor < currentGoalFloor.get)
    		 this.direction = Constants.DIRECTIONUP;
    	 else if (floor > currentGoalFloor.get)
    		 this.direction = Constants.DIRECTIONDOWN;       
     }
   }
   
   /**
    * Removes a Goalfloor from the Set
    * 
    * @param floor - the floor to remove
    */
   private def removeGoalfloor(floor: Int){
     goalfloors = goalfloors.-(floor);
   }
   
   /**
    * Sets the floor of the elevator to the given floor, delete the current goalfloors and add the given goalfloor
    * 
    * @param floor - the new floor of the elevator
    * @param goalFloor - the new goalfloor of the elevator
    */
   def setNewState(floor : Int, goalFloor : Int){
     this.floor = floor;
     this.goalfloors = SortedSet(goalFloor);
     if (floor < goalFloor)
       this.direction = Constants.DIRECTIONUP;
     else if (floor > goalFloor)
       this.direction = Constants.DIRECTIONDOWN;
     else {
    	 this.direction = Constants.DIRECTIONIDLE;
       this.removeGoalfloor(goalFloor)
     }
   }
   
   override def toString : String = {
     return s"Elevator $id";
   }
   
  /**
   * Searches for the next GoalFloor
   */
  private def getNextGoalfloor() : Option[Int] = {
     this.direction match {
       case Constants.DIRECTIONUP => 
         val foundFloor = this.goalfloors.find { x => x >= floor };
         if (foundFloor.isEmpty)
           this.goalfloors.toSeq.reverse.find { x => x <= floor }
         else
           return foundFloor;
       case default => val foundFloor = this.goalfloors.toSeq.reverse.find { x => x <= floor }
         if (foundFloor.isEmpty)
             this.goalfloors.find { x => x >= floor };
           else
             return foundFloor;
     }
     
   }
}