package io.mesosphere.codechallenge.tim

trait ElevatorControlSystem {
  /**
   * Retrieves the status of the Elevating System.
   * 
   * @return - The elevators as a sequence of triples. 
   * 
   * Triple.1 = Id of the Elevator
   * Triple.2 = Floor of the Elevator
   * Triple.3 = Sequence of Goal-Floors for the elevator
   */
  def status(): Seq[(Int, Int, Set[Int])];
  /**
   * Updates the state of the elevator with the given id. Directly moving him to the given floor and configuring the given goalfloor.
   * @param id - Id of the elevator
   * @param floor - The floor to which the elevator should be moved
   * @param goalFloor - The floor to which the elevator should move next
   */
  def update(id: Int, floor: Int, goalFloor: Int);
  /**
   * Defines a Pickup Request at the given floor in the given direction
   * @param floor - The floor of the Pickup Request
   * @param direction - The direction of the Pickup Request
   */
  def pickup(floor: Int, direction: Int);
  /**
   * Let one Timeunit elapse.
   * One Timeunit is defined as the amount of time that the elevators need to rise or fall one floor.
   */
  def step();
}