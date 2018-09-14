package cs5004.animator.model;

/**
 * This class represents a ChangeScale, which is a change in Shape's size. A ChanceScale is an
 * Action. A change in size has a name of the Shape performing the Action, an Action start time,
 * and Action end time, the Shape's initial size, and the Shape's final size.
 */
public class ChangeScale implements Action {
  private String name;
  private int timeInitial;
  private int timeFinal;
  private int[] initialSize;
  private int[] finalSize;

  /**
   * Constructs a ChangeScale object that has the provided name, timeInitial, timeFinal,
   * initialSize, finalPosition.
   *
   * @param name        the name of Shape performing the action
   * @param timeInitial the time the Action starts
   * @param timeFinal   the time the Action stops
   * @param initialSize the initial size of the Shape
   * @param finalSize   the final size of the Shape
   * @throws IllegalArgumentException if input parameters are not valid
   */
  public ChangeScale(String name, int timeInitial, int timeFinal, int[] initialSize,
                     int[] finalSize) throws IllegalArgumentException {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("Shape name not provided");
    }
    if (timeInitial <= 0 || timeFinal <= 0) {
      throw new IllegalArgumentException("time must be greater than 0");
    }
    if (timeInitial > timeFinal) {
      throw new IllegalArgumentException("input time must be in chronological order");
    }
    this.name = name;
    this.timeInitial = timeInitial;
    this.timeFinal = timeFinal;
    this.initialSize = initialSize;
    this.finalSize = finalSize;
  }

  /**
   * Returns the name of the Shape performing the Action.
   *
   * @return the name of the Shape performing the Action.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the start time of the Action performed by a Shape.
   *
   * @return time the initial time of the Action
   */
  public int getInitialTime() {
    return this.timeInitial;
  }

  /**
   * Returns the completion time of the Action performed by a Shape.
   *
   * @return time the final time of the Action
   */
  public int getFinalTime() {
    return this.timeFinal;
  }

  /**
   * Returns the initial size of the Shape performing the Action at the initial time.
   *
   * @return initialCondition the initialCondition of the Shape performing the Action
   */
  public  int[] getInitialCondition() {
    return this.initialSize;
  }

  /**
   * Returns the final size of the Shape performing the Action at the time of completion.
   *
   * @return finalCondition the initialCondition of the Shape performing the Action
   */
  public int[] getFinalCondition() {
    return this.finalSize;
  }

  /**
   * Compares the initial start time of ChangePosition, to other Actions' initial start times.
   * @param compareAction the ChangePosition action to be compared to
   * @return int -1 (if this < other), 0 (if this == other), 1 (if this > other)
   */
  @Override
  public int compareTo(Action compareAction) {
    int compareInitialTime = compareAction.getInitialTime();

    return this.timeInitial - compareInitialTime;
  }


}
