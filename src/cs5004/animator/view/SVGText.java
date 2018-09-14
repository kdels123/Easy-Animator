package cs5004.animator.view;

import cs5004.animator.model.Action;
import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.ChangePosition;
import cs5004.animator.model.ChangeScale;
import cs5004.animator.model.ShapeType;
import cs5004.animator.model.Shape;

import java.io.IOException;
import java.util.List;

/**
 * This class represents a SVGText. A SVGText is a View. A SVGText class generates an output
 * description of the animation in a SVG format.
 */
public class SVGText implements View {

  private List<Shape> shapeList;
  private List<Action> actionList;
  private int ticks;
  private Appendable outputFile;

  /**
   * Constructs a SVGText object that has the provided ticks and name of the output file.
   * @param ticks the speed of the animation
   * @param outputFile the name of the output file
   */
  public SVGText(int ticks, Appendable outputFile) {
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
   * Generates a description of the animation in a SVG format that includes the Shapes that are in
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
   * Generates a description of the animation in a String format that includes the Shapes that are
   * in the animation and the Actions of each Shape.
   *
   * @return svgText a text of the animation in an SVG format
   */
  private String outputDescriptionString() {

    String outputDescription = "";
    String shapeStr;

    // set variables used to determine size of Animation screen so shapes are not cut off

    for (Shape shape : shapeList) {
      String actionStr = "";
      shapeStr = shapeSVGText(shape);

      for (Action action : actionList) {

        if (action.getName().equals(shape.getName())) {
          actionStr = actionStr + actionSVGText(action, shape.getShapeType());
        }
      }

      if (shape.getShapeType() == ShapeType.RECTANGLE || shape.getShapeType()
              == ShapeType.SQUARE) {
        outputDescription = outputDescription + "<rect " + shapeStr + " >\n"
                + actionStr + "\n</rect>\n";
      } else if (shape.getShapeType() == ShapeType.OVAL || shape.getShapeType()
              == ShapeType.CIRCLE) {
        outputDescription = outputDescription + "<ellipse " + shapeStr + " >\n"
                + actionStr + "\n</ellipse>\n";
      }
    }

    // determine size of Animation window so no shapes will be cut off
    int animationWidth = windowWidth();
    int animationHeight = windowHeight();

    String svgFirstLine = String.format("<svg width=\"%d\" height=\"%d\" version=\"1.1\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n", animationWidth, animationHeight);
    String svgLastLine = "\n</svg>\n";

    String svgText = svgFirstLine + outputDescription + svgLastLine;
    return svgText;
  }

  /**
   * HELPER FUNCTION: outputs the SVG text description of the shape.
   *
   * @param shape a Shape
   * @return outputDescription a description of a Shape in the animation
   */
  private String shapeSVGText(Shape shape) throws IllegalArgumentException {
    String outputDescription = "";
    String posX = "";
    String posY = "";
    String length1 = "";
    String length2 = "";

    if (shape.getShapeType() != ShapeType.RECTANGLE
            && shape.getShapeType() != ShapeType.SQUARE
            && shape.getShapeType() != ShapeType.CIRCLE
            && shape.getShapeType() != ShapeType.OVAL) {
      throw new IllegalArgumentException("Shape type does not exist in animation");
    } else if (shape.getShapeType() == ShapeType.RECTANGLE || shape.getShapeType()
            == ShapeType.SQUARE) {
      posX = "x";
      posY = "y";
      length1 = "width";
      length2 = "height";
    } else if (shape.getShapeType() == ShapeType.OVAL || shape.getShapeType() == ShapeType.CIRCLE) {
      posX = "cx";
      posY = "cy";
      length1 = "rx";
      length2 = "ry";
    }

    outputDescription = String.format("id=\"%s\" %s=\"%d\" %s=\"%d\" %s=\"%d\" %s=\"%d\" "
                    + "fill=\"rgb(%d,%d,%d)\" visibility=\"visible\"", shape.getName(), posX,
            (int) shape.getPosition().getX(), posY, (int) shape.getPosition().getY(), length1,
            shape.getLength1(), length2, shape.getLength2(), shape.getColor().getRed(),
            shape.getColor().getGreen(), shape.getColor().getBlue());

    return outputDescription;
  }

  /**
   * HELPER FUNCTION: outputs the SVG text description of the Shape's actions.
   *
   * @param action shape's action
   * @return outputDescription a description of a Shape's actions
   */
  private String actionSVGText(Action action, ShapeType shapeType) throws IllegalArgumentException {
    String outputDescription = "";
    String attribute = "";
    String initialState = "";
    String finalState = "";
    int duration;
    int startTime;

    if (!(action instanceof ChangeColor)
            && !(action instanceof ChangePosition)
            && !(action instanceof ChangeScale)) {
      throw new IllegalArgumentException("Action type does not exist in animation");
    }

    if (action instanceof ChangeColor) {
      attribute = "fill";
      initialState = String.format("rgb(%d,%d,%d)",
              ((ChangeColor) action).getInitialCondition().getRed(),
              ((ChangeColor) action).getInitialCondition().getGreen(),
              ((ChangeColor) action).getInitialCondition().getBlue());
      finalState = String.format("rgb(%d,%d,%d)",
              ((ChangeColor) action).getFinalCondition().getRed(),
              ((ChangeColor) action).getFinalCondition().getGreen(),
              ((ChangeColor) action).getFinalCondition().getBlue());

      duration = ((action.getFinalTime() - action.getInitialTime()) * 1000) / ticks;
      startTime = (action.getInitialTime() * 1000) / ticks;
      outputDescription = outputDescription + String.format("     <animate attributeType="
                      + "\"xml\" begin=\"%dms\" dur=\"%dms\" "
                      + "attributeName=\"%s\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n",
              startTime, duration, attribute, initialState, finalState);

    } else if (action instanceof ChangePosition) {

      double x1 = ((ChangePosition) action).getInitialCondition().getX();
      double y1 = ((ChangePosition) action).getInitialCondition().getY();

      double x2 = ((ChangePosition) action).getFinalCondition().getX();
      double y2 = ((ChangePosition) action).getFinalCondition().getY();

      duration = ((action.getFinalTime() - action.getInitialTime()) * 1000) / ticks;
      startTime = (action.getInitialTime() * 1000) / ticks;

      if (x1 != x2) {
        if (shapeType == ShapeType.RECTANGLE || shapeType == ShapeType.SQUARE) {
          attribute = "x";
        } else if (shapeType == ShapeType.OVAL || shapeType == ShapeType.CIRCLE) {
          attribute = "cx";
        }

        initialState = Double.toString(x1);
        finalState = Double.toString(x2);

        outputDescription = outputDescription + String.format("     <animate attributeType=\""
                        + "xml\" begin=\"%dms\" dur=\"%dms\" "
                        + "attributeName=\"%s\" from=\"%s\" to=\"%s\" fill=\"freeze\" /> \n",
                startTime, duration, attribute, initialState, finalState);
      }

      if (y1 != y2) {
        if (shapeType == ShapeType.RECTANGLE || shapeType == ShapeType.SQUARE) {
          attribute = "y";
        } else if (shapeType == ShapeType.OVAL || shapeType == ShapeType.CIRCLE) {
          attribute = "cy";
        }

        initialState = Double.toString(y1);
        finalState = Double.toString(y2);

        outputDescription = outputDescription + String.format("     <animate attributeType=\""
                        + "xml\" begin=\"%dms\" dur=\"%dms\" "
                        + "attributeName=\"%s\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n",
                startTime, duration, attribute, initialState, finalState);
      }
    } else if (action instanceof ChangeScale) {
      int initLength1 = ((ChangeScale) action).getInitialCondition()[0];
      int initLength2 = ((ChangeScale) action).getInitialCondition()[1];

      int finalLength1 = ((ChangeScale) action).getFinalCondition()[0];
      int finalLength2 = ((ChangeScale) action).getFinalCondition()[1];

      duration = ((action.getFinalTime() - action.getInitialTime()) * 1000) / ticks;
      startTime = (action.getInitialTime() * 1000) / ticks;

      if (initLength1 != finalLength1) {
        if (shapeType == ShapeType.RECTANGLE || shapeType == ShapeType.SQUARE) {
          attribute = "width";
        } else if (shapeType == ShapeType.OVAL || shapeType == ShapeType.CIRCLE) {
          attribute = "rx";
        }

        initialState = Integer.toString(initLength1);
        finalState = Integer.toString(finalLength1);

        outputDescription = outputDescription + String.format("     <animate attributeType=\""
                        + "xml\" begin=\"%dms\" dur=\"%dms\" "
                        + "attributeName=\"%s\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n",
                startTime, duration, attribute, initialState, finalState);
      }
      if (initLength2 != finalLength2) {
        if (shapeType == ShapeType.RECTANGLE || shapeType == ShapeType.SQUARE) {
          attribute = "height";
        } else if (shapeType == ShapeType.OVAL || shapeType == ShapeType.CIRCLE) {
          attribute = "ry";
        }
        initialState = Integer.toString(initLength2);
        finalState = Integer.toString(finalLength2);

        outputDescription = outputDescription + String.format("     <animate attributeType=\""
                        + "xml\" begin=\"%dms\" dur=\"%dms\" "
                        + "attributeName=\"%s\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n",
                startTime, duration, attribute, initialState, finalState);
      }
    }

    return outputDescription;
  }

  /**
   * Returns the width needed for the Animation to prevent shapes from being cut off.
   *
   * @return returns the width of the Animation window.
   */
  private int windowWidth() {
    double minX = 0;

    for (Shape shape : shapeList) {
      if (shape.getLength1() + shape.getPosition().getX() > minX) {
        minX = shape.getLength1() + shape.getPosition().getX();
      }
      for (Action action : actionList) {
        if (action instanceof ChangePosition) {
          if (((ChangePosition) action).getFinalCondition().getX() + shape.getLength1() > minX) {
            minX = ((ChangePosition) action).getFinalCondition().getX() + shape.getLength1();
          }
        }
        if (action instanceof ChangeScale) {
          if (((ChangeScale) action).getFinalCondition()[0] + shape.getLength1() > minX) {
            minX = ((ChangeScale) action).getFinalCondition()[0] + shape.getPosition().getX();
          }
        }
      }

    }

    return (int) Math.ceil(minX);

  }

  /**
   * Returns the height needed for the Animation to prevent shapes from being cut off.
   *
   * @return returns the width of the Animation window.
   */
  private int windowHeight() {
    double minY = 0;

    for (Shape shape : shapeList) {
      if (shape.getLength2() + shape.getPosition().getY() > minY) {
        minY = shape.getLength2() + shape.getPosition().getY();
      }
      for (Action action : actionList) {
        if (action instanceof ChangePosition) {
          if (((ChangePosition) action).getFinalCondition().getY() + shape.getLength2() > minY) {
            minY = ((ChangePosition) action).getFinalCondition().getY() + shape.getLength2();
          }
        }
        if (action instanceof ChangeScale) {
          if (((ChangeScale) action).getFinalCondition()[1] + shape.getPosition().getY() > minY) {
            minY = ((ChangeScale) action).getFinalCondition()[1] + shape.getPosition().getY();
          }
        }
      }
    }
    return (int) Math.ceil(minY);
  }

}
