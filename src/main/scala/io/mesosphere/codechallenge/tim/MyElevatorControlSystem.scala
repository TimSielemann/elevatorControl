package io.mesosphere.codechallenge.tim
/**
 * Implementation of the ElevatorControlSystem Trait.
 * 
 * Manages Elevators in a list and handles elevator scheduling with the NextCar Algorithm
 * 
 * @param numberOfFloors - the total number of Floors. Must be greater than 0
 * @param numberOfElevators - the total number of Elevators. Must be greater than 0
 */
class MyElevatorControlSystem(numberOfFloors: Int, numberOfElevators: Int) extends ElevatorControlSystem{
  if (numberOfElevators <= 0)
    throw new IllegalArgumentException("Number of Elevators must be greater than 0");
  if (numberOfFloors <= 0)
    throw new IllegalArgumentException("Number of Floors must be greater than 0");
  
  val elevators = 0.until(numberOfElevators).map { i => new Elevator(i) };
  
  override def status(): Seq[(Int, Int, Set[Int])] = {
    return elevators.map { x => (x.id, x.floor, x.goalfloors) };
  }
  override def update(id: Int, floor: Int, goalFloor: Int) = {
    if (floor > numberOfFloors || goalFloor > numberOfFloors)
      throw new IllegalArgumentException(s"The building just has $numberOfFloors Floors");
    if (id >= numberOfElevators)
      throw new IllegalArgumentException(s"The building just has $numberOfElevators elevators");
    val elevator = elevators.find { x => x.id == id }
    if (elevator.isDefined){
      elevator.get.setNewState(floor, goalFloor);
    }
  }
  
  override def pickup(floor: Int, direction: Int) = {
    //If the requested floornumber is bigger than the overall floorcount it is not reachable 
    if (floor > numberOfFloors)
      throw new IllegalArgumentException(s"The building just has $numberOfFloors Floors");
    val elevator = this.getBestElevator(floor, direction)
    elevator.addGoalFloor(floor);
    println(s"$elevator is picking up at $floor floor")
  }
  
  override def step() = {
    elevators.foreach { x => x.move() }
  }
  
  /**
   * Implementation of the NextCar Algorithm
   * 
   * Searches for the best fitting elevator and returns it
   * 
   * @param floor - The floor of the Pickup Request
   * @param direction - The direction of the Pickup Request
   * 
   * @return - The Elevator which is suitable for the requested pickup
   * 
   */
  private def getBestElevator(floor: Int, direction: Int) : Elevator = {
    //Remember the best fitting elevator
    var bestFs : (Int, Elevator) = (0, null);
    for (elevator <- elevators){
      //Figure of Suitability(FS)
    	var fs = 1;
      val distance = Math.abs(elevator.floor - floor);
      var add = 1;
      //If the requested direction matches with the elevator direction the elevator is seen as better fitting
      if (direction == elevator.direction) 
        add = 2;
      //The FS is only calculated if the direction and the actual floor of the elevator matches to the requested floor
      elevator.direction match {
        case Constants.DIRECTIONIDLE => fs = numberOfFloors + add - distance;
        case Constants.DIRECTIONDOWN if elevator.floor >= floor => fs = numberOfFloors + add - distance;
        case Constants.DIRECTIONUP if elevator.floor <= floor => fs = numberOfFloors + add - distance;
        case default => ;
      }
      if (fs > bestFs._1){
        bestFs = (fs, elevator);
      }
    }
    return bestFs._2;
  }
}