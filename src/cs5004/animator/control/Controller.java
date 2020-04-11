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

public class Controller implements ActionListener, KeyListener {

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
      case PAUSE:
        view.pause();
      case REWIND:
        view.rewind();
      case INCREASE_SPEED:
        view.increaseSpeed();
      case DECREASE_SPEED:
        view.decreaseSpeed();
      case ENABLE_LOOP:
        view.enableLoop();
      case DISABLE_LOOP:
        view.disableLoop();
    }

  }


  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
