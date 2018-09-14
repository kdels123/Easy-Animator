

import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs5004.animator.controller.Controller;
import cs5004.animator.controller.ControllerInterface;
import cs5004.animator.util.AnimationFileReader;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.ModelInterface;
import cs5004.animator.util.TweenModelBuilder;
import cs5004.animator.view.View;
import cs5004.animator.view.ViewChooser;

/**
 * This class represents an EasyAnimator.  An EasyAnimator receives the users input as an array
 * of Strings and runs the animation.  The EasyAnimator returns the animation as a text or file
 * based on the users input.
 */
public final class EasyAnimator {
  /**
   * Outputs a a text, .txt file, or a .svg file.
   *
   * @param args array of Strings
   * @throws IOException              if file exists
   * @throws IllegalArgumentException checks input is valid
   */
  public static void main(String[] args) throws IOException {

    JFrame frame = new JFrame("JOptionPane showMessageDialog example");

    /*
    a.) create model
          ModelInterface m = new AnimationModel();  ***
          TweenModelBuilder<ModelInterface> builder = new AnimationModel.Builder();
          m = AnimationFileReader(filename (from command line), TweenModelBuilder builder);

    b.) create view
          static view method("svg" or "text" input) -> appropriate view object

    c.) create animationController object- pass in model and view

    d.) call controller method go (c.go())
     */

    String fileName = "";
    Boolean fileNameDNE = true;
    String viewType = "";
    Boolean viewTypeDNE = true;
    Appendable outputFile = System.out;
    int speed = 1;

    for (int i = 0; i < args.length; i++) {
      // stores the file name
      if (args[i].equals("-if")) {
        fileName = args[i + 1];
        fileNameDNE = false;
      }
      // stores the viewType
      if (args[i].equals("-iv")) {
        viewType = args[i + 1];
        viewTypeDNE = false;
      }
      // stores the output file
      if (args[i].equals("-o")) {
        // check if input is valid (needs to be "out" or contain ".txt" or ".svg")
        if (!(args[i + 1].equals("out")) && !(args[i + 1].contains(".txt"))
                && !(args[i + 1].contains(".svg"))) {
          JOptionPane.showMessageDialog(frame, "Invalid output type");
          System.exit(0);
        }
        // if view is Text, the output cannot be a .svg file
        if (viewType.equals("text") && args[i + 1].contains(".svg")) {
          JOptionPane.showMessageDialog(frame, "Text view output cannot be a .svg file");
          System.exit(0);
        }
        // if the output is not "out", create PrintStream with given filename.
        // otherwise, it will be default "System.out"
        else if (!(args[i + 1].equals("out"))) {
          outputFile = new PrintStream(args[i + 1]);
        }
      }
      // stores the speed
      if (args[i].equals("-speed")) {
        speed = Integer.parseInt(args[i + 1]);
        if (speed <= 0 ) {
          JOptionPane.showMessageDialog(frame, "Animation speed not valid");
          System.exit(0);
        }
      }
    }

    if (fileNameDNE || viewTypeDNE) {
      JOptionPane.showMessageDialog(frame, "An input file and view type must be provided");
      System.exit(0);
    } else if (!(viewType.equals("text")
            || viewType.equals("svg")
            || viewType.equals("visual"))) {
      JOptionPane.showMessageDialog(frame, "View type not valid");
      System.exit(0);
    }

    // Create model
    TweenModelBuilder<ModelInterface> builder = new AnimationModel.Builder();
    AnimationFileReader animationReader = new AnimationFileReader();
    ModelInterface model = animationReader.readFile(fileName, builder);

    // Create view
    View view = new ViewChooser().createView(viewType, speed, outputFile);

    // Create controller
    ControllerInterface controller = new Controller(model, view);

    // Call go() method
    controller.goo();
  }

}


