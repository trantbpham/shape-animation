package cs5004.animator.view;

//import java.awt.;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import cs5004.animator.control.Controller;
import cs5004.animator.model.AnimationModelImpl;

//import javax.swing.*;

/**
 * This class creates the a visual animation view using JFrame, JPanel and Graphics2D objects. This
 * view also supports panels which repaint themselves for every tick of the model. JPanel is
 * extended to update the paint method and create the animation itself.
 */
public class PlaybackAnimationView extends JFrame implements PlaybackInterface {

  public static final String ENABLE_LOOP = "Enable Loop";
  public static final String PLAY = "Play";
  public static final String PAUSE = "Pause";
  public static final String REWIND = "Rewind";
  public static final String DISABLE_LOOP = "Disable Loop";
  public static final String INCREASE_SPEED = "Increase Speed";
  public static final String DECREASE_SPEED = "Decrease Speed";
  /*
  private JButton playButton, pauseButton, rewindButton, increaseSpeedButton,
          decreaseSpeedButton, enableLoopButton,
          disableLoopButton;
          */

  private AnimationModelImpl myModel;
  private int leftBoundOffset;
  private int topBoundOffset;
  int animationLength;
  private double frameDelay;
  private boolean loop;
  private boolean play;
  private boolean reverse;
  private boolean pause;
  private InputMap inputMap;
  private ActionMap actionMap;


  /**
   * Constructor for VisualAnimationView, creates the JFrame and updates the animation
   * continuously.
   *
   * @param model - the model that this view pulls information to display from
   * @param speed - the speed of the animation
   */
  public PlaybackAnimationView(AnimationModelImpl model, int speed) {
    MyPanel animationPanel;
    animationLength = model.getLongestLife();
    frameDelay = 1000 / speed;
    loop = true;
    play = true;
    reverse = false;
    pause = false;
    Controller controller = new Controller(this, model);
    this.myModel = model;

    leftBoundOffset = myModel.getLeftBound();
    topBoundOffset = myModel.getTopBound();

    this.setSize(myModel.getWidth() + 50, myModel.getHeight() + 50);
    this.setLocation(leftBoundOffset, topBoundOffset);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());

    animationPanel = new MyPanel();
    animationPanel.setPreferredSize(new Dimension(myModel.getWidth(), myModel.getHeight()));
    this.add(animationPanel, BorderLayout.CENTER);

    //Adding scroll pane if animation extends beyond pane view
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setPreferredSize(new Dimension(myModel.getWidth(), myModel.getHeight()));
    this.add(scrollPane);

    //button panel
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    this.add(buttonPanel, BorderLayout.SOUTH);

    //Adding labels for instructions and information
    JLabel textbox = new JLabel(" ");
    textbox.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonPanel.add(textbox);
    textbox = new JLabel("First Line of instructions");
    textbox.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonPanel.add(textbox);
    textbox = new JLabel("Second line of instructions");
    textbox.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonPanel.add(textbox);
    textbox = new JLabel("Third line of instructions");
    textbox.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonPanel.add(textbox);
    textbox = new JLabel(" ");
    textbox.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonPanel.add(textbox);

    //buttons
    JButton playButton = new JButton(PLAY);
    playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    playButton.addActionListener(controller);
    playButton.setActionCommand(playButton.getText());
    buttonPanel.add(playButton);

    JButton pauseButton = new JButton(PAUSE);
    pauseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    pauseButton.addActionListener(controller);
    pauseButton.setActionCommand(pauseButton.getText());

    buttonPanel.add(pauseButton);

    JButton rewindButton = new JButton(REWIND);
    rewindButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    rewindButton.addActionListener(controller);
    rewindButton.setActionCommand(rewindButton.getText());
    buttonPanel.add(rewindButton);

    JButton enableLoopButton = new JButton(ENABLE_LOOP);
    enableLoopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    enableLoopButton.addActionListener(controller);
    enableLoopButton.setActionCommand(enableLoopButton.getText());
    buttonPanel.add(enableLoopButton);

    JButton disableLoopButton = new JButton(DISABLE_LOOP);
    disableLoopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    disableLoopButton.addActionListener(controller);
    disableLoopButton.setActionCommand(disableLoopButton.getText());
    buttonPanel.add(disableLoopButton);

    JButton increaseSpeedButton = new JButton(INCREASE_SPEED);
    increaseSpeedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    increaseSpeedButton.addActionListener(controller);
    increaseSpeedButton.setActionCommand(increaseSpeedButton.getText());
    buttonPanel.add(increaseSpeedButton);

    JButton decreaseSpeedButton = new JButton(DECREASE_SPEED);
    decreaseSpeedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    decreaseSpeedButton.addActionListener(controller);
    decreaseSpeedButton.setActionCommand(decreaseSpeedButton.getText());
    buttonPanel.add(decreaseSpeedButton);

    //Spacer at the bottom of the button panel
    textbox = new JLabel(" ");
    textbox.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonPanel.add(textbox);

    this.pack();
    this.setVisible(true);

    while (true) {
      System.out.println("this.play is " + this.play);
      System.out.println("this.pause is " + this.pause);
      System.out.println("this.reverse is " + this.reverse);

      if (this.play && animationPanel.getTime() < animationLength) {
        System.out.println("if 1");
        animationPanel.nextTime();
        animationPanel.repaint();

        try {
          Thread.sleep((long) this.frameDelay);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        continue;
      }

      if (this.reverse && animationPanel.getTime() <=
              animationLength && animationPanel.getTime() > 0 && !this.pause) {
        System.out.println("if 2: this.reverse is " + this.reverse);
        animationPanel.previousTime();
        animationPanel.repaint();

        try {
          Thread.sleep((long) this.frameDelay);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        continue;
      }

      if (this.play && this.loop) {
        System.out.println("if 3");
        animationPanel.resetTime();
        animationPanel.repaint();

        try {
          Thread.sleep((long) frameDelay);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        continue;
      }

      if (this.reverse && this.loop) {
        System.out.println("if 3");
        animationPanel.resetEndTime();
        animationPanel.repaint();

        try {
          Thread.sleep((long) frameDelay);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        continue;
      }

      if (this.play && !this.loop) {
        System.out.println("if 5");
        this.pause();
        continue;
      }

      if (this.pause) {
        System.out.println("if 6");
        try {
          Thread.sleep((long) this.frameDelay);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        continue;
      }

    }
  }


  @Override
  public void setListener(ActionListener listener) {

  }

  public void rewind() {
    this.reverse = true;
    this.play = false;
    this.pause = false;
  }

  @Override
  public void pause() {
    this.pause = true;
    this.play = false;
  }

  @Override
  public void play() {
    this.play = true;
    this.reverse = false;
    this.pause = false;
  }

  @Override
  public void enableLoop() {
    this.play = true;
    this.loop = true;
  }

  @Override
  public void disableLoop() {
    this.loop = false;
  }

  @Override
  public void increaseSpeed() {
    this.frameDelay = this.frameDelay * .9;
  }

  @Override
  public void decreaseSpeed() {
    this.frameDelay = this.frameDelay * 1.1;

  }

  /**
   * myPanel is just JPanel with overridden paintComponent method to correctly paint the
   * shapes from  the model.
   */
  private class MyPanel extends JPanel {
    int time = 0;

    /**
     * nextTime is a public method to myPanel which will move the timer one tick forward. This gets
     * called in VisualAnimationView to move the animation forward.
     */
    public void nextTime() {
      this.time += 1;
      //if(time > myModel.)
    }

    public void previousTime() {
      this.time -= 1;
      //if(time > myModel.)
    }

    public void resetTime() {
      this.time = 0;
    }

    public void resetEndTime() {
      this.time = animationLength;
    }

    public int getTime() {
      return this.time;
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
