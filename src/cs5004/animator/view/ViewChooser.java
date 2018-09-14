package cs5004.animator.view;

/**
 * This class represents a ViewChooser. A ViewChooser constructs the appropriate View according to
 * inputs provided by the Controller.
 */
public class ViewChooser {

  /**
   * Returns the correct View based on the following parameters provided by the  Controller.
   *
   * @param viewType   the type of view for the output
   * @param ticks      the number of ticks of the animation
   * @param outputFile the name of the output file
   * @return view the type of view to output
   * @throws IllegalArgumentException if the output name is invalid
   */
  public static View createView(String viewType, int ticks, Appendable outputFile)
          throws IllegalArgumentException {
    View view;

    if (viewType.equals("text")) {
      return view = new Text(ticks, outputFile);
    } else if (viewType.equals("svg")) {
      return view = new SVGText(ticks, outputFile);
    } else if (viewType.equals("visual")) {
      return view = new AnimationView(ticks);
    }
    else {
      throw new IllegalArgumentException("This animation does not contain inputted view type");
    }
  }

}
