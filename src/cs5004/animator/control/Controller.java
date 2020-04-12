package cs5004.animator.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.view.PlaybackAnimationView;

import static cs5004.animator.view.PlaybackAnimationView.DECREASE_SPEED;
import static cs5004.animator.view.PlaybackAnimationView.DELETE_SHAPE;
import static cs5004.animator.view.PlaybackAnimationView.DISABLE_LOOP;
import static cs5004.animator.view.PlaybackAnimationView.ENABLE_LOOP;
import static cs5004.animator.view.PlaybackAnimationView.INCREASE_SPEED;
import static cs5004.animator.view.PlaybackAnimationView.PAUSE;
import static cs5004.animator.view.PlaybackAnimationView.PLAY;
import static cs5004.animator.view.PlaybackAnimationView.REWIND;

/**
 * Controller implements ActionListener and IController interface. This controller takes in
 * model and view, in attempt to allow users interacting from the view and control the animation.
 */
public class Controller implements ActionListener, IController {

  private PlaybackAnimationView view;
  private AnimationModelImpl model;

  /**
   * Controller interacts with elements from model and view by taking in model and view.
   * @param view take in the PlayBackView
   * @param model take in the AnimationModelImpl model
   */
  public Controller(PlaybackAnimationView view, AnimationModelImpl model) {
    this.view = view;
    this.model = model;
  }

  public void goAnimation() {
    this.view.runAnimation();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String action = e.getActionCommand();
    switch (action) {
      default:
        break;
      case PLAY:
        view.play();
        break;
      case PAUSE:
        view.pause();
        break;
      case REWIND:
        view.rewind();
        break;
      case INCREASE_SPEED:
        view.increaseSpeed();
        break;
      case DECREASE_SPEED:
        view.decreaseSpeed();
        break;
      case ENABLE_LOOP:
        view.enableLoop();
        break;
      case DISABLE_LOOP:
        view.disableLoop();
        break;
      case DELETE_SHAPE:
        try {
          model.removeShape(view.addJDialogue());
        } catch (IllegalArgumentException i) {
          view.popupErrorMessageDialogue();
        }
    }
  }

}