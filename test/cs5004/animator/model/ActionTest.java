package cs5004.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D.Double;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * This class represents ActionTest.
 */
public class ActionTest {
  private Action colorAction;
  private Action positionAction;
  private Action scaleAction;

  /**
   * Creates examples of Actions to test.
   */
  @Before
  public void setUp() {
    colorAction = new ChangeColor("R", 10, 20,
            Color.RED, Color.BLUE);
    positionAction = new ChangePosition("R", 1, 10,
            new Double(200.0D, 300.0D), new Double(300.0D, 300.0D));
    scaleAction = new ChangeScale("R", 5, 6,
            new int[]{50, 20}, new int[]{50, 75});
  }

  // tests whether ChangeColor() only accepts valid parameters - specifically Action name
  @Test(expected = IllegalArgumentException.class)
  public void testColorExceptionI() {
    new ChangeColor("", 10, 20, Color.RED, Color.BLUE);
  }

  // tests whether ChangeColor() only accepts valid parameters - specifically Action time
  @Test(expected = IllegalArgumentException.class)
  public void testColorExceptionII() {
    new ChangeColor("R", 10, -20, Color.RED, Color.BLUE);
  }

  // tests whether ChangeColor() only accepts valid parameters - specifically Action time order
  @Test(expected = IllegalArgumentException.class)
  public void testColorExceptionIII() {
    new ChangeColor("R", 20, 10, Color.RED, Color.BLUE);
  }

  // tests whether ChangePosition() only accepts valid parameters - specifically Action name
  @Test(expected = IllegalArgumentException.class)
  public void testPositionExceptionI() {
    new ChangePosition("", 1, 10,
            new Double(200.0D, 300.0D), new Double(300.0D, 300.0D));
  }

  // tests whether ChangePosition() only accepts valid parameters - specifically Action time
  @Test(expected = IllegalArgumentException.class)
  public void testPositionExceptionII() {
    new ChangePosition("R", 1, -10,
            new Double(200.0D, 300.0D), new Double(300.0D, 300.0D));
  }

  // tests whether ChangePosition() only accepts valid parameters - specifically Action time order
  @Test(expected = IllegalArgumentException.class)
  public void testPositionExceptionIII() {
    new ChangePosition("", 10, 1,
            new Double(200.0D, 300.0D), new Double(300.0D, 300.0D));
  }

  // tests whether ChangeScale() only accepts valid parameters - specifically Action name
  @Test(expected = IllegalArgumentException.class)
  public void testScaleExceptionI() {
    new ChangeScale("", 5, 6, new int[]{50, 20}, new int[]{50, 75});
  }

  // tests whether ChangeScale() only accepts valid parameters - specifically Action time
  @Test(expected = IllegalArgumentException.class)
  public void testScaleExceptionII() {
    new ChangeScale("R", -5, 6, new int[]{50, 20}, new int[]{50, 75});
  }

  // tests whether ChangeScale() only accepts valid parameters - specifically Action time order
  @Test(expected = IllegalArgumentException.class)
  public void testScaleExceptionIII() {
    new ChangeScale("R", 6, 5, new int[]{50, 20}, new int[]{50, 75});
  }

  // tests whether the method getName() returns an Action's name
  @Test
  public void testGetName() {
    assertEquals("R", colorAction.getName());
  }

  // tests whether the method getInitialTime() returns an Action's initial time
  @Test
  public void testGetInitialTime() {
    assertEquals(1, positionAction.getInitialTime());
  }

  // tests whether the method getFinalTime() returns an Action's final time
  @Test
  public void testFinalTime() {
    assertEquals(6, scaleAction.getFinalTime());
  }
}