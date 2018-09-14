package cs5004.animator.model;

import java.awt.geom.Point2D;

/**
 * This class represents a ChangePosition, which is a change in a Shape's position. A ChancePosition
 * is an Action. A change in position has a name of the Shape performing the Action, an Action start
 * time, and Action end time, the Shape's initial position, and the Shape's final position.
 */
public class ChangePosition implements Action {
  private String name;
  private int timeInitial;
  private int timeFinal;
  private Point2D initialPosition;
  private Point2D finalPosition;

  /**
   * Constructs a ChangePosition object that has the provided name, timeInitial, timeFinal,
   * initialPosition, finalPosition.
   *
   * @param name            the name of the Shape performing the action
   * @param timeInitial     the time the Action starts
   * @param timeFinal       the time the Action stops
   * @param initialPosition the initial position of the Shape
   * @param finalPosition   the final position of the Shape
   * @throws IllegalArgumentException if input paremetes are not valid
   */
  public ChangePosition(String name, int timeInitial, int timeFinal, Point2D initialPosition,
                        Point2D finalPosition) throws IllegalArgumentException {
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
    this.initialPosition = initialPosition;
    this.finalPosition = finalPosition;
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
   * Returns the initial position of the Shape performing the Action at the initial time.
   *
   * @return initialCondition the initialCondition of the Shape performing the Action
   */
  public Point2D getInitialCondition() {
    return this.initialPosition;
  }

  /**
   * Returns the final position of the Shape performing the Action at the time of completion.
   *
   * @return finalCondition the initialCondition of the Shape performing the Action
   */
  public Point2D getFinalCondition() {
    return this.finalPosition;
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
