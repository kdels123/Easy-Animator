package cs5004.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * This interface represents a Shape. A ShapeInterface provides access to a Shape's fields.
 */
public interface Shape {
  /**
   * Method returns name of Shape.
   *
   * @return returns name of Shape as a string
   */
  String getName();

  /**
   * Method returns color of Shape.
   *
   * @return returns Color of Shape
   */
  Color getColor();

  /**
   * Method returns ShapeType of Shape.
   *
   * @return returns ShapeType of Shape as a ShapeType enumeration
   */
  ShapeType getShapeType();

  /**
   * Method returns position of Shape.
   *
   * @return returns position of Shape as a Point2D
   */
  Point2D getPosition();

  /**
   * Method returns length1 of Shape.
   *
   * @return returns length1 of Shape
   */
  int getLength1();

  /**
   * Method returns length2 of Shape.
   *
   * @return returns length2 of Shape
   */
  int getLength2();

  /**
   * Method returns appearDisappear of a Shape.
   *
   * @return returns length2 of Shape
   */
  Integer[] getAppearDisappear();
}
