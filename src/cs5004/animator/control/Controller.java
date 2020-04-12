package cs5004.animator.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.view.PlaybackAnimationView;

import static cs5004.animator.view.PlaybackAnimationView.DECREASE_SPEED;
import static cs5004.animator.view.PlaybackAnimationView.DISABLE_LOOP;
import static cs5004.animator.view.PlaybackAnimationView.ENABLE_LOOP;
import static cs5004.animator.view.PlaybackAnimationView.INCREASE_SPEED;
import static cs5004.animator.view.PlaybackAnimationView.PAUSE;
import static cs5004.animator.view.PlaybackAnimationView.PLAY;
import static cs5004.animator.view.PlaybackAnimationView.REWIND;

public class Controller implements ActionListener {

  private PlaybackAnimationView view;
  private AnimationModelImpl model;


  public Controller(PlaybackAnimationView view, AnimationModelImpl model) {
    this.view = view;
    this.model = model;
    view.setListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
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
      case "Delete Shape":
        String getValue = view.getJTextField().getText();
        view.deleteShape(getValue);
    }

  }

}