package cs5004.animator.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.view.PlaybackAnimationView;

public class Controller implements ActionListener, KeyListener {

  private PlaybackAnimationView view;
  private AnimationModelImpl model;
  private MouseListenerControl mouse;

  public Controller(PlaybackAnimationView view, AnimationModelImpl model) {
    this.view = view;
    this.model = model;

  }
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {

      case "Play Button":
        view.play();
      case "Pause Button":
        view.pause();
      case "Rewind Button":
        view.rewind();
      case "Increase Speed Button":
        view.increaseSpeed();
      case "Decrease Speed Button":
        view.decreaseSpeed();
      case "Loop Enabler Button":
        view.enableLoop();
      case "Loop Disabler Button":
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
