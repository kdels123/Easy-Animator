package cs5004.animator.model;

import java.util.List;

/**
 * This interface represents an animation model. A ModelInterface adds Shapes and Actions performed
 * by the Shapes to the animation. The interface returns all Shape and Actions to the Controller.
 */
public interface ModelInterface {

  /**
   * Adds a Shape to the shapeMap HashMap.
   *
   * @param newShape the new Shape to be added to the Animation
   */
  void addShape(Shape newShape);

  /**
   * Adds an Action to a Shape's list of Actions.  The Shape is then replaced on the shapeMap, with
   * the updated Shape.
   *
   * @param newAction the new action to be added to a Shape and Animation
   *
   */
  void addAction(Action newAction);

  /**
   * Returns a list of Shapes in the AnimationModel.
   *
   * @return listOfShapes a list of Shapes in the AnimationModel
   */
  List<Shape> getShapes();

  /**
   * Returns a list of all the Actions in the AnimationModel sorted by the initial start time .
   *
   * @return listOfActions a list of Actions of all the Shapes sorted by initial start time
   */
  List<Action> getActions();
}
