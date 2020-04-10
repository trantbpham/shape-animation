package cs5004.animator.view;

//import java.awt.;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.event.ActionListener;

//import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cs5004.animator.model.AnimationModelImpl;

/**
 * This class creates the a visual animation view using JFrame, JPanel and Graphics2D objects.
 *     This view also supports panels which repaint themselves for every tick of the model.
 *     JPanel is extended to update the paint method and create the animation itself.
 */
public class PlaybackAnimationView extends JFrame implements PlaybackInterface{


  private AnimationModelImpl myModel;
  private int leftBoundOffset;
  private int topBoundOffset;


  /**
   * Constructor for VisualAnimationView, creates the JFrame and updates the animation
   *     continuously.
   *
   * @param model - the model that this view pulls information to display from
   * @param speed - the speed of the animation
   */
  public PlaybackAnimationView(AnimationModelImpl model, int speed) {
    MyPanel panel;

    this.myModel = model;

    leftBoundOffset = myModel.getLeftBound();
    topBoundOffset = myModel.getTopBound();

    this.setSize(myModel.getWidth() + 50, myModel.getHeight() + 50);
    this.setLocation(leftBoundOffset, topBoundOffset);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new FlowLayout());

    panel = new MyPanel();
    panel.setPreferredSize(new Dimension(myModel.getWidth(), myModel.getHeight()));
    this.add(panel);

    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setPreferredSize(new Dimension(myModel.getWidth(), myModel.getHeight()));
    this.add(scrollPane);

    this.setVisible(true);

    for (int i = 0; i < myModel.getLongestLife(); i++) {
      panel.nextTime();
      panel.repaint();
      try {
        Thread.sleep(1000 / speed);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }


  }

  @Override
  public void setListener(ActionListener listener) {

  }

  @Override
  public void rewind() {

  }

  @Override
  public void pause() {

  }

  @Override
  public void play() {

  }

  @Override
  public void enableLoop() {

  }

  @Override
  public void disableLoop() {

  }

  @Override
  public void increaseSpeed() {

  }

  @Override
  public void decreaseSpeed() {

  }

  /**
   * myPanel is just JPanel with overridden paintComponent method to correctly paint the shapes from
   *     the model.
   */
  public class MyPanel extends JPanel {
    int time = 0;

    /**
     * nextTime is a public method to myPanel which will move the timer one tick forward. This gets
     *     called in VisualAnimationView to move the animation forward.
     */
    public void nextTime() {
      this.time += 1;
      //if(time > myModel.)
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
              RenderingHints.VALUE_ANTIALIAS_ON);

      for (String currentShape : myModel.getShapeParameters(time)) {
        String[] parseString = currentShape.split("\\|");
        String shapeType = parseString[0];

        g2d.setColor(new Color((int) Double.parseDouble(parseString[5])));

        if (shapeType.equals("rectangle") && Boolean.valueOf(parseString[6])) {
          g2d.fillRect((int) Double.parseDouble(parseString[1]) - leftBoundOffset,
                  (int) Double.parseDouble(parseString[2]) - topBoundOffset,
                  (int) Double.parseDouble(parseString[3]),
                  (int) Double.parseDouble(parseString[4]));

        } else if (shapeType.equals("ellipse") && Boolean.valueOf(parseString[6])) {
          g2d.fillOval((int) Double.parseDouble(parseString[1]) - leftBoundOffset,
                  (int) Double.parseDouble(parseString[2]) - topBoundOffset,
                  (int) Double.parseDouble(parseString[3]),
                  (int) Double.parseDouble(parseString[4]));
        }
      }


    }

  }

}
