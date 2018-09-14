
package cs5004.animator.model;

/**
 * This interface represents an Action. An Action interface extends the Comparable Interface.
 * An action has a name, a start time, and end time. An ExtendShape has a list of Actions.
 */
public interface Action extends Comparable<Action> {

  /**
   * Method returns the name of the name of the Shape performing the Action.
   *
   * @return name the name of the Shape performing the Action as a String.
   */
  String getName();

  /**
   * Method returns the start time of the Action performed by the Shape.
   *
   * @return timeInitial the initial time of the Action as an int
   */
  int getInitialTime();

  /**
   * Method returns the completion time of the Action performed the Shape.
   *
   * @return timeFinal the final time of the Action as an int
   */
  int getFinalTime();

}
