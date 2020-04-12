package cs5004.animator.control;

import java.awt.event.ActionEvent;

/**
 * The controller interface for the animation program. The functions here have been designed to give
 * control to the controller, and the primary operation for the controller to function (process
 * buttons command).
 */

public interface IController {
  /**
   * Process a given string command and notify the view to follow the according actions.
   *
   * @param e the command given, including any parameters (e.g. "play")
   */
  void actionPerformed(ActionEvent e);

  /**
   * Start the program, i.e. give control to the controller
   */
  void goAnimation();
}
