package cs5004.animator.controller;

import cs5004.animator.model.ModelInterface;
import cs5004.animator.view.View;

/**
 * This class represents a Controller. A Controller is a ControllerInterface. A Controller
 * initializes ModelInterface and a View and then runs the View.
 */
public class Controller implements ControllerInterface {

  private ModelInterface model;
  private View view;

  /**
   * Constructs a Controller object that initializes a ModelInterface and a View.
   *
   * @param model the animation model
   * @param view the view
   */
  public Controller(ModelInterface model, View view) {
    this.model = model;
    this.view = view;
  }

  /**
   * Starts running the View of the animation program.
   */
  @Override
  public void goo() {
    view.setData(model.getShapes(), model.getActions());
    view.outputDescription();
  }

}
