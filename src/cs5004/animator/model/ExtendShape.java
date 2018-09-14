package cs5004.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * This class extends Shape and represents an ExtendShape. An ExtendShape is a Shape. An ExtendShape
 * has a name, color, shapeType, position, length1, length2, and an actionList.
 */
public class ExtendShape extends ShapeImpl {
  private List<Action> actionList;

  /**
   * Constructs an ExtendShape object that has the provided name, color, shapeType, position,
   * length1, length2, and actionList.
   *
   * @param name      the name of the Shape
   * @param color     the name of the Color
   * @param shapeType the tye pof Shape
   * @param position  the position of the Shape
   * @param length1   the length1 of the Shape
   * @param length2   the length2 of the Shape
   */
  public ExtendShape(String name, Color color, ShapeType shapeType, Point2D position,
                     int length1, int length2, Integer[] appearDisappear) {
    super(name, color, shapeType, position, length1, length2, appearDisappear);

    actionList = new ArrayList<>();
  }

  /**
   * Adds an Action to a List of Actions.
   *
   * @param newAction the action the Shape is performing
   * @throws IllegalArgumentException if action already exists during a given time
   */
  protected void addAction(Action newAction) throws  IllegalArgumentException {

    for (Action action : getList()) {
      // checks that the actions are the same type
      if (newAction.getClass().equals(action.getClass())) {
        // checks an action of the same type isn't already occurring during the proposed time
        if (newAction.getInitialTime() > action.getInitialTime()
                && newAction.getInitialTime() < action.getFinalTime()
                || newAction.getFinalTime() > action.getInitialTime()
                && newAction.getFinalTime() < action.getFinalTime()) {
          throw new IllegalArgumentException("An Action already occurs during this time");
        }
      }
    }
    actionList.add(newAction);
  }

  /**
   * Returns a List of Actions of a Shape.
   *
   * @return List of Actions of a Shape
   */
  protected List<? extends Action> getList() {
    return this.actionList;
  }

}
