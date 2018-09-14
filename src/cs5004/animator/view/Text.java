package cs5004.animator.view;

import java.io.IOException;
import java.util.List;

import cs5004.animator.model.Action;
import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.ChangePosition;
import cs5004.animator.model.ChangeScale;
import cs5004.animator.model.ShapeType;
import cs5004.animator.model.Shape;

/**
 * This class represents a Text. A Text is a View. A Text class generates an output
 * description of the animation in a text format.
 */
public class Text implements View {

  private List<Shape> shapeList;
  private List<Action> actionList;
  private int ticks;
  private Appendable outputFile;

  /**
   * Constructs a Text object that has the provided ticks and name of the output file.
   */
  public Text(int ticks, Appendable outputFile) {
    this.ticks = ticks;
    this.outputFile = outputFile;
  }

  /**
   * Method provides the View with input data from the Controller.
   *
   * @param shapeList  a list of Shapes in the animation
   * @param actionList a list of Actions performed by the Shapes
   * @throws IllegalArgumentException if shapeList is empty, therefore, an empty animation
   */
  public void setData(List<Shape> shapeList, List<Action> actionList) throws
          IllegalArgumentException {
    if (shapeList.isEmpty()) {
      throw new IllegalArgumentException("Animation is empty");
    }
    this.shapeList = shapeList;
    this.actionList = actionList;
  }

  /**
   * Generates a description of the animation in a Text format that includes the Shapes that are in
   * the animation and the Actions of each Shape.
   */
  public void outputDescription() {

    try {
      this.outputFile.append(outputDescriptionString());
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot be written to file.");
    }
  }

  /**
   * Generates a description of the animation in a Text format that includes the Shapes that are in
   * the animation and the Actions of each Shape.
   *
   * @return description of animation in a Text format
   */
  private String outputDescriptionString() {
    String outputDescription = "Shapes:";

    outputDescription = outputDescription + shapesDescription(shapeList);

    outputDescription = outputDescription + "\n";

    outputDescription = outputDescription + actionsDescription(actionList);
    return outputDescription;
  }

  /**
   * HELPER FUNCTION: returns a description of the Shapes that exist in the animation.
   *
   * @return shapesDescription a description of all the Shapes in the animation
   * @throws IllegalArgumentException if ShapeType does not exist in the animation
   */
  private String shapesDescription(List<Shape> shapeList) throws IllegalArgumentException {
    String outputDescription = "";
    String shapeDimensions;

    // loops through HashMap to print Shape descriptions
    for (Shape shape : shapeList) {

      if (shape.getShapeType() != ShapeType.RECTANGLE
              && shape.getShapeType() != ShapeType.SQUARE
              && shape.getShapeType() != ShapeType.CIRCLE
              && shape.getShapeType() != ShapeType.OVAL) {
        throw new IllegalArgumentException("Shape type does not exist in animation");
      }

      // Determines the type of Shape
      if (shape.getShapeType() == ShapeType.RECTANGLE || shape.getShapeType() == ShapeType.SQUARE) {
        shapeDimensions = String.format(
                "Min corner: (%.1f,%.1f), Width: %.1f, Height: %.1f, Color: (%.1f,%.1f,%.1f)\n",
                shape.getPosition().getX(), shape.getPosition().getY(),
                (float) shape.getLength1(), (float) shape.getLength2(),
                shape.getColor().getRed() / 255.0, shape.getColor().getGreen() / 255.0,
                shape.getColor().getBlue() / 255.0);
      } else if (shape.getShapeType() == ShapeType.OVAL || shape.getShapeType()
              == ShapeType.CIRCLE) {
        shapeDimensions = String.format(
                "Center: (%.1f,%.1f), X radius: %.1f, Y radius: %.1f, Color: (%.1f,%.1f,%.1f)\n",
                shape.getPosition().getX(), shape.getPosition().getY(),
                (float) shape.getLength1(), (float) shape.getLength2(),
                shape.getColor().getRed() / 255.0, shape.getColor().getGreen() / 255.0,
                shape.getColor().getBlue() / 255.0);
      } else {
        shapeDimensions = String.format(
                "Left corner: (%.1f,%.1f), Base: %.1f, Height: %.1f, Color: (%.1f,%.1f,%.1f)\n",
                shape.getPosition().getX(), shape.getPosition().getY(),
                (float) shape.getLength1(), (float) shape.getLength2(),
                shape.getColor().getRed() / 255.0, shape.getColor().getGreen() / 255.0,
                shape.getColor().getBlue() / 255.0);
      }

      // prints the characteristics of each Shape
      //String shapeText = String.format("\nName: %s\n")
      outputDescription = outputDescription + "\n"
              + "Name: " + shape.getName() + "\n"
              + "Type: " + shape.getShapeType().toString().toLowerCase() + "\n"
              + shapeDimensions
              + String.format("Appears at t=%.1fs\n",
              (double) shape.getAppearDisappear()[0] / ticks)
              + String.format("Disappears at t=%.1fs\n",
              (double) shape.getAppearDisappear()[1] / ticks);
    }
    return outputDescription;
  }

  /**
   * HELPER FUNCTION: Returns a formatted String of the actions' descriptions.
   *
   * @return actionsDescription a description of all the Actions
   * @throws IllegalArgumentException if action type does not exist in animation
   */
  private String actionsDescription(List<Action> actionList) throws IllegalArgumentException {

    String outputDescription = "";

    // loops through the sorted list of Actions to print Action description
    for (Action action : actionList) {

      if (!(action instanceof ChangeColor)
              && !(action instanceof ChangePosition)
              && !(action instanceof ChangeScale)) {
        throw new IllegalArgumentException("Action type does not exist in animation");
      } else {
        String actionDescription;

        // Determines Action type
        if (action instanceof ChangeColor) {
          actionDescription = String.format("Shape %s changes color from (%.1f,%.1f,%.1f) to "
                          + "(%.1f,%.1f,%.1f) from t=%.1fs to t=%.1fs\n",
                  action.getName(),
                  ((ChangeColor) action).getInitialCondition().getRed() / 255.0,
                  ((ChangeColor) action).getInitialCondition().getGreen() / 255.0,
                  ((ChangeColor) action).getInitialCondition().getBlue() / 255.0,
                  ((ChangeColor) action).getFinalCondition().getRed() / 255.0,
                  ((ChangeColor) action).getFinalCondition().getGreen() / 255.0,
                  ((ChangeColor) action).getFinalCondition().getBlue() / 255.0,
                  (double) action.getInitialTime() / ticks, (double) action.getFinalTime() / ticks);
        } else if (action instanceof ChangePosition) {
          actionDescription = String.format("Shape %s moves from (%.1f,%.1f) to "
                          + "(%.1f,%.1f) from t=%.1fs to t=%.1fs\n",
                  action.getName(),
                  ((ChangePosition) action).getInitialCondition().getX(),
                  ((ChangePosition) action).getInitialCondition().getY(),
                  ((ChangePosition) action).getFinalCondition().getX(),
                  ((ChangePosition) action).getFinalCondition().getY(),
                  (double) action.getInitialTime() / ticks, (double) action.getFinalTime() / ticks);
        } else {
          actionDescription = changeScaleDescription(action);
        }
        outputDescription = outputDescription + actionDescription;
      }
    }
    return outputDescription;
  }

  /**
   * HELPER FUNCTION: Returns a formatted String of a ChangeScale descriptions.
   *
   * @return actionsDescription a description of all the Actions
   */
  private String changeScaleDescription(Action action) {

    String actionDescription = String.format("Shape %S scales from NAME1: %.1f, NAME2: %.1f to "
                    + "NAME1: %.1f, NAME2: %.1f from " + "t=%.1fs to t=%.1fs\n",
            action.getName(),
            (float) ((ChangeScale) action).getInitialCondition()[0],
            (float) ((ChangeScale) action).getInitialCondition()[1],
            (float) ((ChangeScale) action).getFinalCondition()[0],
            (float) ((ChangeScale) action).getFinalCondition()[1],
            (double) action.getInitialTime() / ticks, (double) action.getFinalTime() / ticks);

    String name1 = " ";
    String name2 = " ";

    for (Shape shape : shapeList) {
      if (action.getName() == shape.getName()) {
        if (shape.getShapeType() == ShapeType.RECTANGLE
                || shape.getShapeType() == ShapeType.SQUARE) {
          name1 = "Width";
          name2 = "Height";
        } else if (shape.getShapeType() == ShapeType.OVAL
                || shape.getShapeType() == ShapeType.CIRCLE) {
          name1 = "X radius";
          name2 = "Y radius";
        }
      }
    }
    String newAction = actionDescription.replaceAll("NAME1", name1);
    return newAction.replaceAll("NAME2", name2);
  }

}
