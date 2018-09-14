package cs5004.animator.model;

import java.awt.Color;

/**
 * This class represents a ChangeColor, which is  change in a Shape's color.  A ChangeColor is an
 * Action. A change in color has a name of the Shape performing the Action, an Action start time,
 * and Action end time, the Shape's initial color, and the Shape's final color.
 */
public class ChangeColor implements Action {
  private String name;
  private int timeInitial;
  private int timeFinal;
  private Color initialColor;
  private Color finalColor;

  /**
   * Constructs a ChangeColor object that has the provided name, timeInitial, timeFinal,
   * initialColor, and finalColor.
   *
   * @param name         the name of the shape performing the Action
   * @param timeInitial  the time the Action starts
   * @param timeFinal    the time the Action stops
   * @param initialColor the initial Color of the Shape
   * @param finalColor   the final Color of the Shape
   * @throws IllegalArgumentException if parameters are not valid
   */
  public ChangeColor(String name, int timeInitial, int timeFinal, Color initialColor,
                     Color finalColor) throws IllegalArgumentException {
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
    this.initialColor = initialColor;
    this.finalColor = finalColor;
  }

  /**
   * Returns the name of the Shape performing the Action.
   *
   * @return the name of the Shape performing the Action
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
   * Returns the initial color of the Shape performing the Action at the initial time.
   *
   * @return initialCondition the initialCondition of the Shape performing the Action
   */
  public Color getInitialCondition() {
    return this.initialColor;
  }

  /**
   * Returns the final color of the Shape performing the Action at the time of completion.
   *
   * @return finalCondition the initialCondition of the Shape performing the Action
   */
  public Color getFinalCondition() {
    return this.finalColor;
  }

  /**
   * Compares the initial start time of ChangeColor, to other Actions' initial start times.
   * @param compareAction the changeColor action to be compared to
   * @return int -1 (if this < other), 0 (if this == other), 1 (if this > other)
   */
  @Override
  public int compareTo(Action compareAction) {
    int compareInitialTime = compareAction.getInitialTime();

    return this.timeInitial - compareInitialTime;
  }

}
