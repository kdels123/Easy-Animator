package cs5004.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import cs5004.animator.util.TweenModelBuilder;

/**
 * This class represents an AnimationModel. An AnimationModel is a ModelInterface.  An
 * AnimationModel represents an animation of Shapes.  An AnimationModel has a shapeMap that
 * contains the Shapes in the animation model and a list of Actions performed by the Shape.
 */
public final class AnimationModel implements ModelInterface {

  private LinkedHashMap<String, Shape> shapeMap;

  /**
   * Constructs an AnimationModel object that has the provided name shapeMap.
   */
  public AnimationModel() {
    this.shapeMap = new LinkedHashMap<>();
  }

  /**
   * Adds a Shape to the shapeMap HashMap.
   *
   * @param newShape the new Shape to be added to the Animation
   */
  public void addShape(Shape newShape) {

    shapeMap.put(newShape.getName(), newShape);
  }

  /**
   * Adds an Action to a Shape's list of Actions.  The Shape is then replaced on the shapeMap, with
   * the updated Shape.
   *
   * @param newAction the new action to be added to a Shape and Animation
   */
  public void addAction(Action newAction) {

    // extracts the name of the Shape the newAction is being added to
    String shapeName = newAction.getName();

    // extracts the Shape to add the new Action to
    Shape oldShape = shapeMap.get(shapeName);

    // check that Shape has "appeared" and not "disappeared" during time of action
    if (newAction.getInitialTime() < oldShape.getAppearDisappear()[0]
            || newAction.getFinalTime() > oldShape.getAppearDisappear()[1]) {
      throw new IllegalArgumentException("Shape is not present");
    }

    if (oldShape instanceof ExtendShape) {

      // if Shape is an ExtendShape add the Action to the list of actions in the ExtendShape
      ((ExtendShape) oldShape).addAction(newAction);
      shapeMap.put(shapeName, oldShape);
    } else {
      // initializes the newExtendedShape for the Action to be added to
      ExtendShape newExtendShape = new ExtendShape(oldShape.getName(), oldShape.getColor(),
              oldShape.getShapeType(), oldShape.getPosition(), oldShape.getLength1(),
              oldShape.getLength2(), oldShape.getAppearDisappear());

      newExtendShape.addAction(newAction);
      shapeMap.put(shapeName, newExtendShape);
    }

  }

  /**
   * Returns a list of Shapes in the AnimationModel.
   *
   * @return listOfShapes a list of Shapes in the AnimationModel
   */
  public List<Shape> getShapes() {

    // initializes listOfShapes to be returned
    List<Shape> listOfShapes = new ArrayList<>();

    // loops through AnimationModel HashMap to add each Shape to the list
    for (Shape shape : shapeMap.values()) {
      listOfShapes.add(shape);
    }

    return listOfShapes;
  }

  /**
   * Returns a list of all the Actions in the AnimationModel sorted by the initial start time .
   *
   * @return listOfActions a list of Actions of all the Shapes sorted by initial start time
   */
  public List<Action> getActions() {

    // initializes listOfActions to be sorted later
    List<Action> listOfActions = new ArrayList<>();

    // loops through AnimationModel HashMap to add each action to list
    for (Shape shape : shapeMap.values()) {
      if (shape instanceof ExtendShape) {
        for (Action action : ((ExtendShape) shape).getList()) {
          listOfActions.add(action);
        }
      }
    }

    // sorts a list of Actions based on initial time
    Collections.sort(listOfActions);

    return listOfActions;

  }

  /**
   * This class represents a Builder.
   * It is to help build a AnimationModel using the methods in the TweenModelBuilder interface.
   */
  public static final class Builder implements TweenModelBuilder<ModelInterface> {

    private ModelInterface newModel = new AnimationModel();


    //TweenModelBuilder<ModelInterface> builder = new Builder();


    @Override
    public TweenModelBuilder<ModelInterface> addOval(
            String name,
            float cx, float cy,
            float xRadius, float yRadius,
            float red, float green, float blue,
            int startOfLife, int endOfLife) {


      Shape newShape = new ShapeImpl(name, new Color(red, green, blue), ShapeType.OVAL,
              new Point2D.Double(cx, cy), (int) xRadius, (int) yRadius,
              new Integer[]{startOfLife, endOfLife});

      newModel.addShape(newShape);

      return this;
    }

    @Override
    public TweenModelBuilder<ModelInterface> addRectangle(
            String name,
            float lx, float ly,
            float width, float height,
            float red, float green, float blue,
            int startOfLife, int endOfLife) {

      Shape newShape = new ShapeImpl(name, new Color(red, green, blue), ShapeType.RECTANGLE,
              new Point2D.Double(lx, ly), (int) width, (int) height,
              new Integer[]{startOfLife, endOfLife});

      newModel.addShape(newShape);

      return this;
    }

    @Override
    public TweenModelBuilder<ModelInterface> addMove(
            String name,
            float moveFromX, float moveFromY, float moveToX, float moveToY,
            int startTime, int endTime) {

      Action newAction = new ChangePosition(name, startTime, endTime,
              new Point2D.Double(moveFromX, moveFromY), new Point2D.Double(moveToX, moveToY));

      newModel.addAction(newAction);

      return this;
    }

    @Override
    public TweenModelBuilder<ModelInterface> addColorChange(String name, float oldR, float oldG,
                                                            float oldB, float newR, float newG,
                                                            float newB, int startTime,
                                                            int endTime) {

      Action newAction = new ChangeColor(name, startTime, endTime, new Color(oldR, oldG, oldB),
              new Color(newR, newG, newB));

      newModel.addAction(newAction);

      return this;
    }

    @Override
    public TweenModelBuilder<ModelInterface> addScaleToChange(String name,
                                                              float fromSx, float fromSy,
                                                              float toSx, float toSy,
                                                              int startTime, int endTime) {
      Action newAction = new ChangeScale(name, startTime, endTime,
              new int[]{(int) fromSx, (int) fromSy}, new int[]{(int) toSx, (int) toSy});

      newModel.addAction(newAction);

      return this;
    }

    @Override
    public ModelInterface build() {
      return newModel;
    }


  }


}
