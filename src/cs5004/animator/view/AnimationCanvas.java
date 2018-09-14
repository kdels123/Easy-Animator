package cs5004.animator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

import java.awt.geom.Point2D;

import java.util.LinkedHashMap;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import cs5004.animator.model.Action;
import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.ChangePosition;
import cs5004.animator.model.ChangeScale;
import cs5004.animator.model.Shape;
import cs5004.animator.model.ShapeImpl;
import cs5004.animator.model.ShapeType;

/**
 * This class represents an AnimationCanvas. An AnimationCanvas is a JPanel. An AnimationCanvas
 * class generates an output animation.
 */
public class AnimationCanvas extends JPanel implements ActionListener {
  private List<Shape> shapeList;
  private List<Action> actionList;

  private LinkedHashMap<String, Shape> shapeMap;

  private Timer timer;
  private int timeCounter = 0;
  private int ticks;
  Boolean play = true;

  /**
   * Constructs an Animation object that has the provided ticks and also initializes the timer of
   * the animation.
   *
   * @param ticks the speed of the animation
   */
  public AnimationCanvas(List<Shape> shapeList, List<Action> actionList, int ticks) {

    this.shapeList = shapeList;
    this.actionList = actionList;
    this.ticks = ticks;
    this.shapeMap = returnShapeMap();

  }

  @Override
  public void actionPerformed(ActionEvent evt) {
    atTick();
  }

  /**
   * Starts the AnimationView.
   */
  public void startAnimation() {
    timer = new Timer(1000 / ticks, this);
    timer.start();
  }

  /**
   * Pause animation.
   */
  public void pauseResumeAnimation() {
    play = !play;
  }

  /**
   * Restarts the AnimationView.
   */
  public void restartAnimation() {
    timer = new Timer(1000 / ticks, this);
    timer.restart();
    timeCounter = 0;
  }

  /**
   * Increases the speed of the AnimationView.
   */
  public void increaseSpeed() {
    ticks = ticks + 10;
    timer.setDelay(1000 / ticks);
  }

  /**
   * Decreases the speed of the AnimationView.
   */
  public void decreaseSpeed() {
    if (ticks > 10) {
      ticks = ticks - 10;
    } else if (ticks > 1) {
      ticks = ticks - 1;
    }
    timer.setDelay(1000 / ticks);
  }

  /**
   * Increases the time counter and repaints the animation at every time interval.
   */
  private void atTick() {
    if (play.equals(true)) {
      repaint();
      timeCounter++;
    }
  }

  /**
   * Adds the Shapes and Shape Actions to the animation based on their initial start time.
   *
   * @param g graphics component
   */
  public void paint(Graphics g) {
    super.paintComponent(g);

    //1.) loop through actions
    //2.) compare t to initTime and finalTime
    //3.) if true, call appropriate helper function to perform action
    //      - use equation to determine new condition
    //      - helper function returns shape with new condition

    // loops through the action list and determines if the action is present given a certain time
    for (Action action : actionList) {
      if (timeCounter >= action.getInitialTime() && timeCounter <= action.getFinalTime()) {
        // constructs a new Shape based on the specific action
        if (action instanceof ChangeColor) {
          ChangeColor colorAction = (ChangeColor) action;
          Shape originalShape = shapeMap.get(action.getName());
          Shape newShape = new ShapeImpl(originalShape.getName(),
                  setColor(colorAction),
                  originalShape.getShapeType(),
                  originalShape.getPosition(),
                  originalShape.getLength1(), originalShape.getLength2(),
                  originalShape.getAppearDisappear());
          shapeMap.put(action.getName(), newShape);
        } else if (action instanceof ChangePosition) {
          ChangePosition positionAction = (ChangePosition) action;
          Point2D newPosition = setPosition(positionAction);
          Shape originalShape = shapeMap.get(action.getName());
          Shape newShape = new ShapeImpl(originalShape.getName(),
                  originalShape.getColor(),
                  originalShape.getShapeType(),
                  newPosition,
                  originalShape.getLength1(), originalShape.getLength2(),
                  originalShape.getAppearDisappear());
          shapeMap.put(action.getName(), newShape);
        } else {
          ChangeScale scaleAction = (ChangeScale) action;
          double[] newScale = setScale(scaleAction);
          Shape originalShape = shapeMap.get(action.getName());
          Shape newShape = new ShapeImpl(originalShape.getName(),
                  originalShape.getColor(),
                  originalShape.getShapeType(),
                  originalShape.getPosition(),
                  (int) newScale[0], (int) newScale[1],
                  originalShape.getAppearDisappear());
          shapeMap.put(action.getName(), newShape);
        }
      }
    }

    // adds the Shape to the animation
    for (Shape shape : shapeMap.values()) {
      if (timeCounter >= shape.getAppearDisappear()[0] && timeCounter
              <= shape.getAppearDisappear()[1]) {
        g.setColor(shape.getColor());
        if (shape.getShapeType() == ShapeType.RECTANGLE
                || shape.getShapeType() == ShapeType.SQUARE) {
          g.fillRect((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                  shape.getLength1(), shape.getLength2());
        } else {
          g.fillOval((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                  shape.getLength1(), shape.getLength2());
        }
      }
    }
  }

  /**
   * HELPER FUNCTION: returns the new color based on a given time using interpolation.
   *
   * @param colorAction the action that specifies the initial and final conditions and times
   * @return color the new color based on a given time
   */
  private Color setColor(ChangeColor colorAction) {

    // initialize/declare values to use in interpolation helper function
    Color initialColor = colorAction.getInitialCondition();
    Color finalColor = colorAction.getFinalCondition();
    double initialTime = colorAction.getInitialTime();
    double finalTime = colorAction.getFinalTime();

    // generates new color values using interpolation
    double newR = interpolate(initialColor.getRed(), finalColor.getRed(), initialTime, finalTime);
    double newG = interpolate(initialColor.getGreen(), finalColor.getGreen(), initialTime,
            finalTime);
    double newB = interpolate(initialColor.getBlue(), finalColor.getBlue(), initialTime, finalTime);

    return new Color((int) newR, (int) newG, (int) newB);

  }

  /**
   * HELPER FUNCTION: returns the new position based on a given time using interpolation.
   *
   * @param positionAction the action that specifies the initial and final conditions and times
   * @return position the new position (Point2D) based on a given time
   */
  private Point2D setPosition(ChangePosition positionAction) {

    // initialize/declare values to use in interpolation helper function
    double initialPositionX = positionAction.getInitialCondition().getX();
    double initialPositionY = positionAction.getInitialCondition().getY();
    double finalPositionX = positionAction.getFinalCondition().getX();
    double finalPositionY = positionAction.getFinalCondition().getY();
    double initialTime = positionAction.getInitialTime();
    double finalTime = positionAction.getFinalTime();

    // generates new position values using interpolation
    double newPositionX = interpolate(initialPositionX, finalPositionX, initialTime, finalTime);
    double newPositionY = interpolate(initialPositionY, finalPositionY, initialTime, finalTime);

    return new Point2D.Double(newPositionX, newPositionY);

  }

  /**
   * HELPER FUNCTION: returns the new length and width based on a given time using interpolation.
   *
   * @param scaleAction the action that specifies the initial and final conditions and times
   * @return length the new length and height based on a given time
   */
  private double[] setScale(ChangeScale scaleAction) {

    double initialLength1 = scaleAction.getInitialCondition()[0];
    double initialLength2 = scaleAction.getInitialCondition()[1];
    double finalLength1 = scaleAction.getFinalCondition()[0];
    double finalLength2 = scaleAction.getFinalCondition()[1];
    double initialTime = scaleAction.getInitialTime();
    double finalTime = scaleAction.getFinalTime();

    double newLength1 = interpolate(initialLength1, finalLength1, initialTime, finalTime);
    double newLength2 = interpolate(initialLength2, finalLength2, initialTime, finalTime);

    return new double[]{newLength1, newLength2};
  }

  /**
   * HELPER FUNCTION: returns an interpolated value based on a given time.
   *
   * @param initialCond the initial condition of the Shape before the Action
   * @param finalCond   the final condition of the Shape after the Action
   * @param initialTime the time the Action starts
   * @param finalTime   the time the Action is completed
   */
  private double interpolate(double initialCond, double finalCond, double initialTime,
                             double finalTime) {

    double a = (initialCond * (finalTime - timeCounter) / (finalTime - initialTime));
    double b = (finalCond * (timeCounter - initialTime) / (finalTime - initialTime));

    return a + b;

  }

  /**
   * HELPER FUNCTION: Loops through the function and a HashMap.
   *
   * @return shapeMap a HashMap of Shapes
   */
  private LinkedHashMap<String, Shape> returnShapeMap() {
    LinkedHashMap<String, Shape> shapeMap = new LinkedHashMap<>();
    for (Shape shape : shapeList) {
      shapeMap.put(shape.getName(), shape);
    }
    return shapeMap;
  }

  /**
   * Sets the size of the animation.
   *
   * @return dimension the dimension of the AnimationCanvas
   */
  public Dimension getPreferredSize() {
    return new Dimension(800, 800);
  }


}