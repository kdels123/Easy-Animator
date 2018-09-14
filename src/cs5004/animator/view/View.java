package cs5004.animator.view;

import java.util.List;

import cs5004.animator.model.Action;
import cs5004.animator.model.Shape;

/**
 * This interface represents a View. A View receives inputs from the Controller and generates an
 * output description of the animation that includes the Shapes in the animation and the Actions
 * performed by the Shapes.
 */
public interface View {

  /**
   * Method provides the View with input data from the Controller.
   * @param shapeList a list of Shapes in the animation
   * @param actionList a list of Actions performed by the Shapes
   * @throws IllegalArgumentException if shapeList is empty, therefore, an empty animation
   */
  void setData(List<Shape> shapeList, List<Action> actionList) throws IllegalArgumentException;

  /**
   * Generates a description of the animation that includes the Shapes that are in the animation
   * and the Actions of each Shape.
   */
  void outputDescription();

}
