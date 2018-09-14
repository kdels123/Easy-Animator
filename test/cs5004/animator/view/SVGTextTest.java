package cs5004.animator.view;

import org.junit.Before;
import org.junit.Test;


import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.PrintStream;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.ChangePosition;
import cs5004.animator.model.ChangeScale;
import cs5004.animator.model.ShapeImpl;
import cs5004.animator.model.ShapeType;


/**
 * This class tests the SVGText class.
 */
public class SVGTextTest {

  private SVGText svgText = new SVGText(2, new PrintStream(System.out));
  private AnimationModel animationAnimationModel = new AnimationModel();
  private AnimationModel emptyAnimationAnimationModel = new AnimationModel();

  /**
   * Creates examples of SVGText to test.
   */
  @Before
  public void setup() {

    animationAnimationModel.addShape(new ShapeImpl("R", Color.red, ShapeType.RECTANGLE,
            new Point2D.Double(200, 200), 50, 100, new Integer[]{1, 100}));
    animationAnimationModel.addShape(new ShapeImpl("C", Color.blue, ShapeType.OVAL,
            new Point2D.Double(500, 100), 60, 30, new Integer[]{4, 100}));

    animationAnimationModel.addAction(new ChangePosition("R", 1, 5,
            new Point2D.Double(200, 200), new Point2D.Double(300, 300)));
    animationAnimationModel.addAction(new ChangePosition("C", 5, 7,
            new Point2D.Double(500, 100), new Point2D.Double(500, 400)));
    animationAnimationModel.addAction(new ChangeColor("C", 6, 9,
            Color.blue, Color.green));
    animationAnimationModel.addAction(new ChangeScale("R", 2, 10,
            new int[]{50, 100}, new int[]{25, 100}));

    svgText.setData(animationAnimationModel.getShapes(), animationAnimationModel.getActions());


  }

  // tests whether the animation is empty
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyOutput() {
    svgText.setData(emptyAnimationAnimationModel.getShapes(),
            emptyAnimationAnimationModel.getActions());
  }


  //  // NOTE: Function made public to test
  //  // tests whether the method outputDescription() returns the correct formatted String
  //  @Test
  //  public void testOutputDescription() {
  //    String stringOutput = "<svg width=\"560\" height=\"500\" version=\"1.1\"\n"
  //            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
  //            + "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" fill=\""
  //            + "rgb(255,0,0)\" visibility=\"visible\" >\n"
  //            + "     <animate attributeType=\"xml\" begin=\"500ms\" dur=\"2000ms\" "
  //            + "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" /> \n"
  //            + "     <animate attributeType=\"xml\" begin=\"500ms\" dur=\"2000ms\" "
  //            + "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
  //            + "     <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" "
  //            + "attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
  //            + "\n"
  //            + "</rect>\n"
  //            + "<ellipse id=\"C\" cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" fill=\"rgb(0,0,255)\"
  //            + ""visibility=\"visible\" >\n"
  //            + "     <animate attributeType=\"xml\" begin=\"2500ms\" dur=\"1000ms\" "
  //            + "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
  //            + "     <animate attributeType=\"xml\" begin=\"3000ms\" dur=\"1500ms\" "
  //            + "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,255,0)\" fill=\""
  //            + "freeze\" />\n"
  //            + "\n"
  //            + "</ellipse>\n"
  //            + "\n"
  //            + "</svg>\n";
  //    assertEquals(stringOutput, svgText.outputDescriptionString());
  //  }


}