package cs5004.animator.model;

import java.awt.geom.Point2D;
import java.awt.Color;

/**
 * This class represents a Shape. A Shape is a ShapeInterface. A Shape has a name, color, shapeType,
 * position, length1, length2, and an appear disappear.
 */
public class ShapeImpl implements Shape {
  private  String name;
  private Color color;
  private ShapeType shapeType;
  private Point2D position;
  private int length1;
  private int length2;
  private Integer[] appearDisappear;

  /**
   * Constructs a Shape object that has the provided name, color, shapeType, position, length1,
   * length2, and appearDisappear.
   *
   * @param name      name of the Shape
   * @param color     color of the Shape
   * @param shapeType ShapeType of the Shape
   * @param position  position of the Shape
   * @param length1   length1 of the Shape
   * @param length2   length2 of the Shape
   * @param appearDisappear Integer array containing appear time and disappear time of the Shape
   * @throws IllegalArgumentException if parameters are not valid
   */
  public ShapeImpl(String name, Color color, ShapeType shapeType, Point2D position, int length1,
               int length2, Integer[] appearDisappear) throws IllegalArgumentException {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("Shape name not provided");
    }
    //    if (position.getX() < 0 || position.getY() < 0) {
    //      throw new IllegalArgumentException("Shape position must not be greater than 0");
    //    }
    if (length1 <= 0 || length2 <= 0) {
      throw new IllegalArgumentException("Shape lengths must be greater than 0");
    }
    if (appearDisappear[0] <= 0 || appearDisappear[1] <= 0) {
      throw new IllegalArgumentException("time must be greater than 0");
    }
    if (appearDisappear[0] > appearDisappear[1]) {
      throw new IllegalArgumentException("input time must be in chronological order");
    }
    this.name = name;
    this.color = color;
    this.shapeType = shapeType;
    this.position = position;
    this.length1 = length1;
    this.length2 = length2;
    this.appearDisappear = appearDisappear;
  }

  /**
   * Method returns name of Shape.
   *
   * @return returns name of Shape as a string
   */
  public String getName() {
    return this.name;
  }

  /**
   * Method returns color of Shape.
   *
   * @return returns Color of Shape
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * Method returns ShapeType of Shape.
   *
   * @return returns ShapeType of Shape as a ShapeType enumeration
   */
  public ShapeType getShapeType() {
    return this.shapeType;
  }

  /**
   * Method returns position of Shape.
   *
   * @return returns position of Shape as a Point2D
   */
  public Point2D getPosition() {
    return this.position;
  }

  /**
   * Method returns length1 of Shape.
   *
   * @return returns length1 of Shape
   */
  public int getLength1() {
    return this.length1;
  }

  /**
   * Method returns length2 of Shape.
   *
   * @return returns length2 of Shape
   */
  public int getLength2() {
    return this.length2;
  }

  /**
   * Method returns appearDisappear of a Shape.
   *
   * @return returns length2 of Shape
   */
  public Integer[] getAppearDisappear() {
    return this.appearDisappear;
  }

}
