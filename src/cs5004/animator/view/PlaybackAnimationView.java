package cs5004.animator.view;

//import java.awt.;
import java.awt.*;
import java.awt.event.ActionListener;

//import javax.swing.*;
import javax.swing.*;

import cs5004.animator.control.Controller;
import cs5004.animator.model.AnimationModelImpl;

/**
 * This class creates the a visual animation view using JFrame, JPanel and Graphics2D objects.
 *     This view also supports panels which repaint themselves for every tick of the model.
 *     JPanel is extended to update the paint method and create the animation itself.
 */
public class PlaybackAnimationView extends JFrame implements PlaybackInterface{

  public static final String ENABLE_LOOP = "Enable Loop";
  private JButton playButton, pauseButton, rewindButton,increaseSpeedButton,decreaseSpeedButton,enableLoopButton,
                  disableLoopButton;
  private AnimationModelImpl myModel;
  private int leftBoundOffset;
  private int topBoundOffset;
  private boolean loop;
  private double frameDelay;
  private boolean play;
  private boolean reverse;
  private boolean pause;


  /**
   * Constructor for VisualAnimationView, creates the JFrame and updates the animation
   *     continuously.
   *
   * @param model - the model that this view pulls information to display from
   * @param speed - the speed of the animation
   */
  public PlaybackAnimationView(AnimationModelImpl model, int speed) {
    MyPanel animationPanel;
    int animationLength = model.getLongestLife();
    frameDelay = 1000/speed;
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
    buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
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
    JButton playButton = new JButton("Play");
    playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonPanel.add(playButton);

    JButton pauseButton = new JButton("Pause");
    pauseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonPanel.add(pauseButton);

    JButton rewindButton = new JButton("Rewind");
    rewindButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonPanel.add(rewindButton);

    enableLoopButton = new JButton("Enable Loop");
    enableLoopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    enableLoopButton.addActionListener(controller);
    enableLoopButton.setActionCommand(enableLoopButton.getText());
    buttonPanel.add(enableLoopButton);

    JButton disableLoopButton = new JButton("Disable Loop");
    disableLoopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonPanel.add(disableLoopButton);

    JButton increaseSpeedButton = new JButton("Increase Speed");
    increaseSpeedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonPanel.add(increaseSpeedButton);

    JButton decreaseSpeedButton = new JButton("Decrease Speed");
    decreaseSpeedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonPanel.add(decreaseSpeedButton);

    //Spacer at the bottom of the button panel
    textbox = new JLabel(" ");
    textbox.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonPanel.add(textbox);

    this.pack();
    this.setVisible(true);

    while(true) {
      if(this.pause){
        continue;
      }

      if(this.play && animationPanel.getTime() < animationLength){
        animationPanel.nextTime();
        animationPanel.repaint();

        try {
          Thread.sleep((long) this.frameDelay);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        continue;
      }

      if(this.reverse && animationPanel.getTime() < animationLength && animationPanel.getTime() > 0){
        animationPanel.previousTime();
        animationPanel.repaint();

        try {
          Thread.sleep((long) this.frameDelay);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        continue;
      }

      if(this.play && this.loop){
        animationPanel.resetTime();
        animationPanel.repaint();

        try {
          Thread.sleep((long) frameDelay);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        continue;
      }

      if(this.play && this.loop){
        animationPanel.resetTime();
        animationPanel.repaint();

        try {
          Thread.sleep((long) frameDelay);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        continue;
      }

      if(this.play && !this.loop){
        this.pause();
        continue;
      }

    }
  }


  @Override
  public void setListener(ActionListener listener) {

  }

  @Override
  public void rewind() {
    this.reverse = true;
    this.play = false;
  }

  @Override
  public void pause() {
    this.pause = true;

  }

  @Override
  public void play() {
    this.play = true;
    this.reverse = false;
  }

  @Override
  public void enableLoop() {
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
   * myPanel is just JPanel with overridden paintComponent method to correctly paint the shapes from
   *     the model.
   */
  private class MyPanel extends JPanel {
    int time = 0;

    /**
     * nextTime is a public method to myPanel which will move the timer one tick forward. This gets
     *     called in VisualAnimationView to move the animation forward.
     */
    public void nextTime() {
      this.time += 1;
      //if(time > myModel.)
    }

    public void previousTime() {
      this.time -= 1;
      //if(time > myModel.)
    }

    public void resetTime(){
      this.time = 0;
    }

    public int getTime(){
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
