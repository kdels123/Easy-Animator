package cs5004.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D.Double;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * This class represents an extend shape.
 */
public class ExtendShapeTest {
  private ExtendShape extShape;


  /**
   * Creates examples of ExtendShape to test.
   */
  @Before
  public void setUp() {
    extShape = new ExtendShape("R", Color.RED, ShapeType.RECTANGLE,
            new Double(200.0D, 200.0D), 50, 100, new Integer[]{1, 100});
  }

  // tests the method addAction()
  @Test
  public void testAddAction() {
    String checkAction1Description = "Shape R moves from (200.0,200.0) to (300.0,300.0) from "
            + "t=10 to t=50\n";
    String checkAction2Description = "Shape R moves from (300.0,300.0) to (200.0,200.0) from "
            + "t=70 to t=100\n";
    String checkAction3Description = "Shape R scales from NAME1: 50.0, NAME2: 100.0 to "
            + "NAME1: 25.0, NAME2: 100.0 from t=51 to t=70\n";

    Action action1 = new ChangePosition("R", 10, 50,
            new Double(200.0D, 200.0D), new Double(300.0D, 300.0D));
    Action action2 = new ChangePosition("R", 70, 100,
            new Double(300.0D, 300.0D), new Double(200.0D, 200.0D));
    Action action3 = new ChangeScale("R", 51, 70,
            new int[]{50, 100}, new int[]{25, 100});

    extShape.addAction(action1);
    extShape.addAction(action2);
    extShape.addAction(action3);

    assertEquals(action1, extShape.getList().get(0));
    assertEquals(action2, extShape.getList().get(1));
    assertEquals(action3, extShape.getList().get(2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddActionException() {
    Action action1 = new ChangePosition("R", 10, 50,
            new Double(200.0D, 200.0D), new Double(300.0D, 300.0D));
    Action action2 = new ChangePosition("R", 20, 70,
            new Double(200.0D, 200.0D), new Double(300.0D, 300.0D));

    this.extShape.addAction(action1);
    this.extShape.addAction(action2);
  }


}