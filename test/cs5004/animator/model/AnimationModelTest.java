package cs5004.animator.model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * This class represents a AnimationModel test.
 */
public class AnimationModelTest {
  private AnimationModel animationAnimationModelI;
  private AnimationModel animationAnimationModelII;

  /**
   * Provides AnimationModel examples to test.
   */
  @Before
  public void setup() {
    animationAnimationModelI = new AnimationModel();
    animationAnimationModelII = new AnimationModel();
    animationAnimationModelII.addShape(new ShapeImpl("R", Color.red, ShapeType.RECTANGLE,
            new Point2D.Double(200, 200), 50, 100, new Integer[] {1, 100}));
  }

  // tests whether addShape() method correctly adds Shapes to the AnimationModel
  @Test
  public void testAddShape() {
    Shape shape = new ShapeImpl("R", Color.red, ShapeType.RECTANGLE,
            new Point2D.Double(200, 200), 50, 100, new Integer[] {1, 100});

    animationAnimationModelI.addShape(shape);

    assertEquals(shape, animationAnimationModelI.getShapes().get(0));
  }

  // tests whether addAction() method correctly adds a ChangePosition Action to the AnimationModel
  @Test
  public void testAddActionPosition() {
    ChangePosition actionPosition =
            new ChangePosition("R", 10, 50,
                    new Point2D.Double(200, 200), new Point2D.Double(300, 300));

    animationAnimationModelII.addAction(actionPosition);

    assertEquals(actionPosition, animationAnimationModelII.getActions().get(0));
  }

  // tests whether addAction() method correctly adds a ChangeScale Action to the AnimationModel
  @Test
  public void testAddActionScale() {
    ChangeScale actionScale =
            new ChangeScale("R", 51, 70, new int[] {50, 100},
                    new int[] {50, 100});

    animationAnimationModelII.addAction(actionScale);

    assertEquals(actionScale, animationAnimationModelII.getActions().get(0));
  }

  // tests whether addAction() method correctly adds a ChangeColor Action to the AnimationModel
  @Test
  public void testAddActionColor() {
    ChangeColor actionColor =
            new ChangeColor("R", 50, 80, Color.blue, Color.green);

    animationAnimationModelII.addAction(actionColor);

    assertEquals(actionColor, animationAnimationModelII.getActions().get(0));
  }

  // tests the method getShapes() and that all Shapes added to the ModleAnimation are accounted for
  @Test
  public void testGetShapes() {
    Shape shapeI = new ShapeImpl("R", Color.red, ShapeType.RECTANGLE,
            new Point2D.Double(200, 200), 50, 100, new Integer[] {1, 100});
    Shape shapeII = new ShapeImpl("C", Color.blue, ShapeType.OVAL,
            new Point2D.Double(500, 100), 60, 30, new Integer[] {6, 100});

    animationAnimationModelI.addShape(shapeI);
    animationAnimationModelI.addShape(shapeII);

    assertEquals(shapeI, animationAnimationModelI.getShapes().get(0));
    assertEquals(shapeII, animationAnimationModelI.getShapes().get(1));
  }

  // tests the method getActions() and whether all the Actions added to Shapes and therefore
  // Animation are accounted for in the sorted order by initial start time
  @Test
  public void testGetActions() {
    ChangePosition actionPosition =
            new ChangePosition("R", 10, 50,
                    new Point2D.Double(200, 200), new Point2D.Double(300, 300));
    ChangeScale actionScale =
            new ChangeScale("R", 51, 70, new int[] {50, 100},
                    new int[] {50, 100});
    ChangeColor actionColor =
            new ChangeColor("R", 50, 80, Color.blue, Color.green);

    animationAnimationModelII.addAction(actionPosition);
    animationAnimationModelII.addAction(actionScale);
    animationAnimationModelII.addAction(actionColor);

    assertEquals(actionPosition, animationAnimationModelII.getActions().get(0));
    assertEquals(actionColor, animationAnimationModelII.getActions().get(1));
    assertEquals(actionScale, animationAnimationModelII.getActions().get(2));

  }


}