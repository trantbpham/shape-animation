package cs5004.animator.view;

/**
 * This class creates the a visual animation view using JFrame, JPanel and Graphics2D objects.
 *     This view also supports commands during the animation from a controller class.
 *     The visual output will repaint itself for every tick of the model. JPanel is extended to
 *     update the paint method and create the animation itself.
 */
public interface PlaybackInterface {

  public void rewind();

  public void pause();

  public void play();

  public void enableLoop();

  public void disableLoop();

  public void increaseSpeed();

  public void decreaseSpeed();


}











