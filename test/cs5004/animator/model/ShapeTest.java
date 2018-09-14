
package cs5004.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.Arrays;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

public class ShapeTest {
  private Shape rectangle;
  private Shape square;
  private Shape circle;
  private Shape oval;

  /**
   * Generates examples of Shapes to test.
   */
  @Before
  public void setup() {
    rectangle = new ShapeImpl("R", Color.red, ShapeType.RECTANGLE,
            new Double(100.0D, 300.0D), 10, 20, new Integer[]{10, 100});
    square = new ShapeImpl("S", Color.red, ShapeType.SQUARE,
            new Double(100.0D, 300.0D), 10, 10, new Integer[]{10, 100});
    circle = new ShapeImpl("C", Color.red, ShapeType.CIRCLE,
            new Double(100.0D, 300.0D), 10, 10, new Integer[]{10, 100});
    oval = new ShapeImpl("O", Color.red, ShapeType.OVAL,
            new Double(100.0D, 300.0D), 10, 20, new Integer[]{10, 100});

  }

  // tests whether shapeImpl() only accepts valid parameters - specifically Shape name
  @Test(expected = IllegalArgumentException.class)
  public void testShapeExceptionI() {
    new ShapeImpl("", Color.red, ShapeType.RECTANGLE, new Point2D.Double(200, 200),
            50, 100, new Integer[]{1, 100});
  }

  // tests whether shapeImpl() only accepts valid parameters - specifically Shape position
  @Test(expected = IllegalArgumentException.class)
  public void testShapeExceptionII() {
    new ShapeImpl("R", Color.red, ShapeType.RECTANGLE, new Point2D.Double(-200, 200),
            50, 100, new Integer[]{1, 100});
  }

  // tests whether shapeImpl() only accepts valid parameters - specifically Shape length
  @Test(expected = IllegalArgumentException.class)
  public void testShapeExceptionIII() {
    new ShapeImpl("R", Color.red, ShapeType.RECTANGLE, new Point2D.Double(200, 200),
            -50, 100, new Integer[]{1, 100});
  }

  // tests whether shapeImpl() only accepts valid parameters - specifically negative time
  @Test(expected = IllegalArgumentException.class)
  public void testShapeExceptionIV() {
    new ShapeImpl("R", Color.red, ShapeType.RECTANGLE, new Point2D.Double(200, 200),
            50, 100, new Integer[]{1, -100});
  }

  // tests whether shapeImpl() only accepts valid parameters - specifically the order of time
  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeExceptionV() {
    new ShapeImpl("R", Color.red, ShapeType.RECTANGLE, new Point2D.Double(200, 200),
            50, 100, new Integer[]{100, 1});
  }

  // tests method getName() returns a Shape's name
  @Test
  public void testGetName() {
    Assert.assertEquals("R", rectangle.getName());
  }

  // tests method getColor() returns a Shape's color
  @Test
  public void testGetColor() {
    Assert.assertEquals(Color.red, rectangle.getColor());
  }

  // tests method getShapeType() returns a Shape's ShapeType
  @Test
  public void testGetShapeType() {
    Assert.assertEquals(ShapeType.RECTANGLE, this.rectangle.getShapeType());
    Assert.assertEquals(ShapeType.SQUARE, this.square.getShapeType());
    Assert.assertEquals(ShapeType.CIRCLE, this.circle.getShapeType());
    Assert.assertEquals(ShapeType.OVAL, this.oval.getShapeType());
  }

  // tests method getPosition() returns a Shape's position
  @Test
  public void testGetPosition() {
    Assert.assertEquals(new Double(100.0D, 300.0D), rectangle.getPosition());
  }

  // tests method getLength1() returns a Shape's length1
  @Test
  public void testGetLength1() {
    Assert.assertEquals(10, rectangle.getLength1());
  }

  // tests method getLength2() returns a Shape's length2
  @Test
  public void testGetLength2() {
    Assert.assertEquals(20, rectangle.getLength2());
  }

  // tests method getAppearDisappear() returns a Shape's time in animation
  @Test
  public void testGetAppearDisappear() {
    Integer[] appearDisappear = new Integer[]{10, 100};
    Assert.assertEquals(Arrays.toString(appearDisappear),
            Arrays.toString(rectangle.getAppearDisappear()));
  }


}