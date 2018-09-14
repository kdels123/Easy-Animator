package cs5004.animator.view;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.PrintStream;

import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.ChangePosition;
import cs5004.animator.model.ChangeScale;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.ShapeImpl;
import cs5004.animator.model.ShapeType;


/**
 * This class tests the Text class.
 */
public class TextTest {

  private Text text;
  private AnimationModel emptyAnimationAnimationModel;



  /**
   * Creates examples of Text to test.
   */
  @Before
  public void setup() {
    AnimationModel animationAnimationModel;
    text = new Text(3, new PrintStream(System.out));
    animationAnimationModel = new AnimationModel();
    emptyAnimationAnimationModel = new AnimationModel();

    animationAnimationModel.addShape(new ShapeImpl("R", Color.red, ShapeType.RECTANGLE,
            new Point2D.Double(200, 200), 50, 100, new Integer[]{1, 100}));
    animationAnimationModel.addShape(new ShapeImpl("C", Color.blue, ShapeType.OVAL,
            new Point2D.Double(500, 100), 60, 30, new Integer[]{6, 100}));
    animationAnimationModel.addAction(new ChangePosition("R", 10, 50,
            new Point2D.Double(200, 200), new Point2D.Double(300, 300)));
    animationAnimationModel.addAction(new ChangePosition("C", 20, 70,
            new Point2D.Double(500, 100), new Point2D.Double(500, 400)));
    animationAnimationModel.addAction(new ChangeColor("C", 50, 80,
            Color.blue, Color.green));
    animationAnimationModel.addAction(new ChangeScale("R", 51, 70,
            new int[]{50, 100}, new int[]{25, 100}));
    text.setData(animationAnimationModel.getShapes(), animationAnimationModel.getActions());
  }

  // tests whether the animation is empty
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyOutput() {
    text.setData(emptyAnimationAnimationModel.getShapes(),
            emptyAnimationAnimationModel.getActions());
  }

  //  // NOTE: Function made public to test
  //  // tests whether the method outputDescriptionString() returns the correct formatted String
  //  @Test
  //  public void testOutputDescriptionString() {
  //    String stringOutput = "Shapes:\n" +
  //            "Name: R\n" +
  //            "Type: rectangle\n" +
  //            "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n" +
  //            "Appears at t=0.3s\nDisappears at t=33.3s\n\n" +
  //            "Name: C\n" +
  //            "Type: oval\n" +
  //            "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n" +
  //            "Appears at t=2.0s\nDisappears at t=33.3s\n\n" +
  //            "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=3.3s to t=16.7s\n" +
  //            "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=6.7s to t=23.3s\n" +
  //            "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=16.7s to t=26.7s"
  //            "\nShape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 from
  //            + "t=17.0s to t=23.3s\n";
  //    //assertEquals(stringOutput, text.outputDescriptionString());
  //  }
}
