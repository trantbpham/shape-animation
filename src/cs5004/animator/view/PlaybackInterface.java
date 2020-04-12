package cs5004.animator.view;

/**
 * This class creates the a visual animation view using JFrame, JPanel and Graphics2D objects. This
 * view also supports commands during the animation from a controller class calling methods within
 * the view itself. The visual output will repaint itself for every tick of the model. JPanel is
 * extended to update the paintComponent method and create the animation itself.
 */
public interface PlaybackInterface {

  /**
   * Begins running the animation by starting a while(true) loop which checks the state of the
   * various internal values for play, pause, rewind etc. and determines how to behave. Also
   * maintains an internal timer using the sleep method.
   */
  void runAnimation();

  /**
   * Returns the boolean value of the pause variable (mostly used for testing).
   *
   * @return boolean - the value of the pause variable in the view.
   */
  boolean getPause();

  /**
   * Returns the boolean value of the play variable (mostly used for testing).
   *
   * @return boolean - the value of the play variable in the view.
   */
  boolean getPlay();

  /**
   * Returns the boolean value of the rewind variable (mostly used for testing).
   *
   * @return boolean - the value of the rewind variable in the view.
   */
  boolean getRewind();

  /**
   * Returns the boolean value of the loop variable (mostly used for testing).
   *
   * @return boolean - the value of the loop variable in the view.
   */
  boolean getLoop();

  /**
   * Returns the value of the frame delay of the animation
   *
   * @return double - the time in milliseconds between frames.
   */
  double getFrameDelay();

  /**
   * Changes the play, rewind, and pause variables such that the animation runs in reverse.
   */
  void rewind();

  /**
   * Changes the play, rewind, and pause variables such that the animation pauses.
   */
  void pause();

  /**
   * Changes the play, rewind, and pause variables such that the animation runs normally.
   */
  void play();

  /**
   * Enables the loop variable (enabled by default) to allow for looping the animation.
   */
  void enableLoop();

  /**
   * Disables the loop variable (enabled by default) to stop the looping of the animation. Animation
   * will stop on the final frame.
   */
  void disableLoop();

  /**
   * Increases the speed of the animation by decreasing the time between frames by 10%.
   */
  void increaseSpeed();

  /**
   * Decreases the speed of the animation by increasing the time between frames by 10%.
   */
  void decreaseSpeed();

  /**
   * Create pop up message indicating shape not found.
   */
  void popupErrorMessageDialogue();

  /**
   * Add dialogue pop up for user to enter shape's name.
   *
   * @return a String with shape's name
   */
  String addJDialogue();
}











