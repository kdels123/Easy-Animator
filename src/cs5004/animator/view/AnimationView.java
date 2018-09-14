package cs5004.animator.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.BorderFactory;

import cs5004.animator.model.Action;
import cs5004.animator.model.Shape;


/**
 * This class represents an AnimationView. An AnimationView is a View. An AnimationView class
 * generates an output of the animation as a JFrame.
 */
public class AnimationView extends JFrame implements View {

  private java.util.List<Shape> shapeList;
  private java.util.List<Action> actionList;
  private int ticks;

  private JPanel animationPanel;

  private JTextField fileName;

  private AnimationCanvas animationCanvas;

  /**
   * Constructs an instance of an AnimationView and initializes the following parameters.
   * @param ticks the speed of the Animation.
   */
  public AnimationView(int ticks) {

    this.ticks = ticks;


    // sets the sizeof the AnimationView
    setSize(600, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel buttonPanel = new JPanel();
    animationPanel = new JPanel();
    JPanel textPanel = new JPanel();

    getContentPane().setLayout(new BorderLayout());
    add(animationPanel, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.NORTH);
    add(textPanel, BorderLayout.SOUTH);

    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.X_AXIS));

    JScrollPane scrollPane = new JScrollPane(animationPanel);
    add(scrollPane);

    // button - play button
    JButton playButton = new JButton("Play");
    playButton.setActionCommand("play button");
    playButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        animationPanel.add(animationCanvas);
        validate();
        animationCanvas.startAnimation();
      }
    });
    buttonPanel.add(playButton);

    // button - pause
    JButton pauseButton = new JButton("Pause/Resume");
    pauseButton.setActionCommand("pause button");
    pauseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        animationCanvas.pauseResumeAnimation();
      }
    });
    buttonPanel.add(pauseButton);

    // button - restart button
    JButton restartButton = new JButton("Restart");
    restartButton.setActionCommand("restart button");
    restartButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        animationCanvas.play = true;
        animationCanvas.restartAnimation();
      }
    });
    buttonPanel.add(restartButton);

    // button - increase speed button
    JButton increaseButton = new JButton("Increase Speed");
    increaseButton.setActionCommand("increase button");
    increaseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        animationCanvas.increaseSpeed();
      }
    });
    buttonPanel.add(increaseButton);

    // button - decrease speed button
    JButton decreaseButton = new JButton("Decrease Speed");
    decreaseButton.setActionCommand("decrease button");
    decreaseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        animationCanvas.decreaseSpeed();
      }
    });
    buttonPanel.add(decreaseButton);

    // text field - file name
    fileName = new JTextField(40);
    fileName.setBorder(BorderFactory.createTitledBorder("Enter File Path: (src/Example.svg)"));
    textPanel.add(fileName);

    // button - save button
    JButton saveButton = new JButton("Save");
    saveButton.setActionCommand("save button");
    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Appendable appendableFileName = System.out;
        String fileNameString = fileName.getText();
        fileName.setText("");
        try {
          appendableFileName = new PrintStream(fileNameString);
        } catch (FileNotFoundException e1) {
          e1.printStackTrace();
        }

        View newSVG = new SVGText(ticks, appendableFileName);
        newSVG.setData(shapeList, actionList);
        newSVG.outputDescription();
      }
    });
    textPanel.add(saveButton);
  }

  @Override
  public void outputDescription() {
    animationCanvas = new AnimationCanvas(shapeList, actionList, ticks);
    validate();
    setVisible(true);
    validate();
  }

  @Override
  public void setData(java.util.List<cs5004.animator.model.Shape> shapeList,
                      java.util.List<Action> actionList) throws IllegalArgumentException {
    if (shapeList.isEmpty()) {
      throw new IllegalArgumentException("Animation is empty");
    }
    this.shapeList = shapeList;
    this.actionList = actionList;
  }


}
