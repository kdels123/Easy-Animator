package cs5004.animator.view;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.ShapeImpl;
import cs5004.animator.model.ShapeType;

public class AnimationViewTest {

  private View view = new AnimationView(2);
  private AnimationModel animationAnimationModel = new AnimationModel();
  private AnimationModel emptyAnimationAnimationModel = new AnimationModel();

  /**
   * Creates examples of SVGText to test.
   */
  @Before
  public void setup() {

    animationAnimationModel.addShape(new ShapeImpl("R", Color.red, ShapeType.RECTANGLE,
            new Point2D.Double(200, 200), 50, 100, new Integer[]{1, 100}));
    animationAnimationModel.addShape(new ShapeImpl("C", Color.white, ShapeType.OVAL,
            new Point2D.Double(500, 100), 60, 30, new Integer[]{4, 100}));

    view.setData(animationAnimationModel.getShapes(),
            animationAnimationModel.getActions());

  }

  // tests whether the animation is empty
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyOutput() {
    view.setData(emptyAnimationAnimationModel.getShapes(),
            emptyAnimationAnimationModel.getActions());
  }

  // NOTE: Function made public to test and hard coded "timerCounter" to 60 in order to prevent
  // errors in the tests
  // tests whether the method outputDescription() returns the correct formatted String

  // tests whether method setColor returns an interpolated color
  //  @Test
  //  public void testSetColor() {
  //    ChangeColor colorAction  = new ChangeColor("C", 10, 80,
  //            Color.WHITE, Color.YELLOW);
  //
  //    Color newColor = new Color(255, 255, 72);
  //    assertEquals(newColor, ((AnimationView) animationView).setColor(colorAction));
  //  }
  //
  //  // tests whether method setPosition returns an interpolated position
  //  @Test
  //  public void testSetPosition() {
  //    ChangePosition positionAction  = new ChangePosition("C", 10, 80,
  //            new Point2D.Double(500, 100), new Point2D.Double(500, 150));
  //
  //    Point2D newPosition = new Point2D.Double(500, 135.71428571428572);
  //    assertEquals(newPosition, ((AnimationView) animationView).setPosition(positionAction));
  //  }
  //
  //  // tests whether method setScale returns an interpolated size
  //  @Test
  //  public void testSetScale() {
  //    ChangeScale scaleAction  = new ChangeScale("C", 10, 80,
  //            new int[] {60, 30}, new int[] {30, 30});
  //
  //    double[] newScale = new double [] {38.57142857142857, 30.0};
  //    assertArrayEquals(newScale, ((AnimationView) animationView).setScale(scaleAction), 0.001);
  //  }

}