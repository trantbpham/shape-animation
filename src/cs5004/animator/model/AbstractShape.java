package cs5004.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This abstract shape class implements Shape interface, will be utilized by both Rectangle and Oval
 * shape class.
 */
public abstract class AbstractShape implements Shape {

  protected String name;
  protected double[] width;
  protected double[] height;
  protected double[] x;
  protected double[] y;
  protected Color[] color;
  protected int shapeStartTime;
  protected int shapeEndTime;
  protected boolean[] visible;
  protected boolean[] moveTimeSlots;
  protected boolean[] colorTimeSlots;
  protected boolean[] sizeTimeSlots;
  protected boolean[] initialized = new boolean[3];
  protected ColorName[] colorNameList;

  //time lists for 3 types: X/Y ; width/height ; colors
  protected List<Integer> motionMoveStartTime;
  protected List<Integer> motionMoveEndTime;
  protected List<Integer> motionResizeStartTime;
  protected List<Integer> motionResizeEndTime;
  protected List<Integer> motionColorStartTime;
  protected List<Integer> motionColorEndTime;

  //from lists for 3 types: X/Y ; width/height ; colors
  protected List<Double> fromXDestination;
  protected List<Double> fromYDestination;
  protected List<Double> fromWidthDestination;
  protected List<Double> fromHeightDestination;
  protected List<Double> fromRColor;
  protected List<Double> fromGColor;
  protected List<Double> fromBColor;


  //to lists for 3 types: X/Y ; width/height ; colors
  protected List<Double> moveXDestination;
  protected List<Double> moveYDestination;
  protected List<Double> widthDestination;
  protected List<Double> heightDestination;
  protected List<Double> rDestination;
  protected List<Double> gDestination;
  protected List<Double> bDestination;

  /**
   * Construct an AbstractShape object - including initializing all of its parameters such as
   * position, width, height, color, startTime, endTime. Angle defaults to 0 and may be changed by
   * controller.
   *
   * @param name      - name of the shape
   * @param startX    - X position of this shape's origin
   * @param startY    - Y position of this shape's origin
   * @param width     - this shape's width (X dimension)
   * @param height    - this shape's height (Y dimension
   * @param r         - this shape's red color (0.0-1.0)
   * @param g         - this shape's green color (0.0-1.0)
   * @param b         - this shape's blue color (0.0-1.0)
   * @param startTime - this shape's start time (appears in animation)
   * @param endTime   - this shape's end time (disappears in animation)
   * @throws IllegalArgumentException - if any of the inputs are invalid, throw error and inform
   *                                  user
   */
  public AbstractShape(String name, double startX, double startY, double width, double height,
                       float r, float g, float b, int startTime, int endTime)
          throws IllegalArgumentException {

    if (r < 0.0 || r > 1.0 || g < 0.0 || g > 1.0 || b < 0.0 || b > 1.0) {
      throw new IllegalArgumentException("Color values must be between 0.0 and 1.0.");
    }

    if (startTime < 0 || endTime < 0) {
      throw new IllegalArgumentException("time cannot be negative");
    }

    if (startTime >= endTime) {
      throw new IllegalArgumentException("endTime must be greater than startTime.");
    }

    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("width and height must both be greater than 0.");
    }


    //endTime + 1 since we're indexing from 0, that way if we call position at time 100 we still
    // have a value at that time
    this.shapeEndTime = endTime + 1;
    this.shapeStartTime = 0;


    this.motionMoveStartTime = new ArrayList();
    this.motionMoveEndTime = new ArrayList();
    this.motionResizeStartTime = new ArrayList();
    this.motionResizeEndTime = new ArrayList();
    this.motionColorStartTime = new ArrayList();
    this.motionColorEndTime = new ArrayList();

    this.moveXDestination = new ArrayList();
    this.moveYDestination = new ArrayList();
    this.widthDestination = new ArrayList();
    this.heightDestination = new ArrayList();
    this.fromRColor = new ArrayList();
    this.fromGColor = new ArrayList();
    this.fromBColor = new ArrayList();


    this.fromXDestination = new ArrayList();
    this.fromYDestination = new ArrayList();
    this.fromWidthDestination = new ArrayList();
    this.fromHeightDestination = new ArrayList();
    this.rDestination = new ArrayList();
    this.gDestination = new ArrayList();
    this.bDestination = new ArrayList();

    this.width = new double[shapeEndTime];
    this.height = new double[shapeEndTime];
    this.x = new double[shapeEndTime];
    this.y = new double[shapeEndTime];
    this.color = new Color[shapeEndTime];
    this.visible = new boolean[shapeEndTime];
    this.moveTimeSlots = new boolean[shapeEndTime];
    this.sizeTimeSlots = new boolean[shapeEndTime];
    this.colorTimeSlots = new boolean[shapeEndTime];
    int i;

    this.name = name;

    this.colorNameList = new ColorName[shapeEndTime];

    Arrays.fill(this.visible, false);
    Arrays.fill(this.width, width);
    Arrays.fill(this.height, height);
    Arrays.fill(this.x, startX);
    Arrays.fill(this.y, startY);
    Arrays.fill(this.color, new Color(r, g, b));
    Arrays.fill(this.moveTimeSlots, true);
    Arrays.fill(this.colorTimeSlots, true);
    Arrays.fill(this.sizeTimeSlots, true);

    for (i = 0; i < startTime; i++) {
      this.visible[i] = false;
    }
    initialized[0] = true;
    initialized[1] = true;
    initialized[2] = true;
  }


  @Override
  public void setName(String newName) {
    this.name = newName;
  }

  public double getMoveXDestination(int i) {
    return this.moveXDestination.get(i);
  }

  public double getFromXDestination(int i) {
    return this.fromXDestination.get(i);
  }

  public int getFromXDestinationSize() {
    return fromXDestination.size();
  }

  public double getFromYDestination(int i) {
    return this.fromYDestination.get(i);
  }

  public int getFromYDestinationSize() {
    return fromYDestination.size();
  }

  public double getMoveYDestination(int i) {
    return this.moveYDestination.get(i);
  }

  public int getMoveYDestinationSize() {
    return moveYDestination.size();
  }

  public double getWidthDestination(int i) {
    return this.widthDestination.get(i);
  }

  public double getFromWidthDestination(int i) {
    return this.fromWidthDestination.get(i);
  }

  public double getWidthDestinationSize() {
    return widthDestination.size();
  }

  public double getHeightDestination(int i) {
    return this.heightDestination.get(i);
  }

  public int getHeightDestinationSize() {
    return heightDestination.size();
  }

  public double getFromHeightDestination(int i) {
    return fromHeightDestination.get(i);
  }

  public int getMotionMoveStartTime(int i) {
    return this.motionMoveStartTime.get(i);
  }

  public int getMotionMoveEndTime(int i) {
    return this.motionMoveEndTime.get(i);
  }

  public int getMotionResizeStartTime(int i) {
    return this.motionResizeStartTime.get(i);
  }

  public int getMotionResizeEndtime(int i) {
    return this.motionResizeEndTime.get(i);
  }


  public int getColorStartTime(int i) {
    return this.motionColorStartTime.get(i);
  }

  public int getColorEndTime(int i) {
    return this.motionColorEndTime.get(i);
  }

  public double getFromRColor(int i) {
    return this.fromRColor.get(i);
  }

  public double getFromGColor(int i) {
    return this.fromGColor.get(i);
  }

  public double getFromBColor(int i) {
    return this.fromBColor.get(i);
  }

  public double getRColorDestination(int i) {
    return this.rDestination.get(i);
  }

  public double getGColorDestination(int i) {
    return this.gDestination.get(i);
  }

  public double getBColorDestination(int i) {
    return this.bDestination.get(i);
  }

  public int getColorSize() {
    return this.motionColorEndTime.size();
  }


  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public double getX(int t) throws IllegalArgumentException {

    if (t < 0 || t >= this.shapeEndTime) {
      throw new IllegalArgumentException("Time must be greater than 0"
              + " and less than this shape's lifetime.");
    }

    return this.x[t];
  }


  @Override
  public double getY(int t) throws IllegalArgumentException {

    if (t < 0 || t >= this.shapeEndTime) {
      throw new IllegalArgumentException("Time must be greater than 0"
              + " and less than this shape's lifetime.");
    }

    return this.y[t];
  }


  @Override
  public Color getColor(int t) throws IllegalArgumentException {

    if (t < 0 || t >= this.shapeEndTime) {
      throw new IllegalArgumentException("Time must be greater than 0"
              + " and less than this shape's lifetime.");
    }

    return this.color[t];
  }


  @Override
  public double getHeight(int t) throws IllegalArgumentException {

    if (t < 0 || t >= this.shapeEndTime) {
      throw new IllegalArgumentException("Time must be greater than 0"
              + " and less than this shape's lifetime.");
    }

    return this.height[t];
  }


  @Override
  public double getWidth(int t) throws IllegalArgumentException {

    if (t < 0 || t >= this.shapeEndTime) {
      throw new IllegalArgumentException("Time must be greater than 0"
              + " and less than this shape's lifetime.");
    }

    return this.width[t];
  }

  /**
   * Convert color RGB to appropriate name using Color Enum.
   *
   * @return the ColorList
   */
  public ColorName[] colorConvert() {
    int i;
    float[] colorComponent = new float[3];
    for (i = shapeStartTime; i < shapeEndTime; i++) {
      for (Color each : color) {
        each.getColorComponents(colorComponent);
        //     for (float individual : colorComponent) {
        if ((colorComponent[0] > colorComponent[1]) && (colorComponent[0] > colorComponent[2])) {
          this.colorNameList[i + 1] = ColorName.RED;
        }
        if ((colorComponent[1] > colorComponent[0]) && (colorComponent[1] > colorComponent[2])) {
          this.colorNameList[i + 1] = ColorName.GREEN;
        }
        if ((colorComponent[2] > colorComponent[0]) && (colorComponent[2] > colorComponent[1])) {
          this.colorNameList[i + 1] = ColorName.BLUE;
        }
        if (colorComponent[0] == 0 && colorComponent[1] == 0 && colorComponent[2] == 0) {
          this.colorNameList[i + 1] = ColorName.BLACK;
        }
        if (colorComponent[0] == 1 && colorComponent[1] == 1 && colorComponent[2] == 1) {
          this.colorNameList[i + 1] = ColorName.WHITE;
        }
      }
    }
    return colorNameList;
  }


  @Override
  public boolean getVisibility(int t) throws IllegalArgumentException {

    if (t < 0 || t >= this.shapeEndTime) {
      throw new IllegalArgumentException("Time must be greater than 0"
              + " and less than this shape's lifetime.");
    }

    return this.visible[t];
  }

  @Override
  public void changeVisibility(int startTime) {

    if (startTime < 0) {
      throw new IllegalArgumentException("Motion startTime must be >=0.");
    }

    Arrays.fill(this.visible, startTime, this.visible.length, true);

  }

  public boolean[] getVisibilityArray() {
    return this.visible;
  }

  public boolean[] getMoveTimeSlotsArray() {
    return this.moveTimeSlots;
  }


  @Override
  public void move(double endX, double endY, int startTime, int endTime)
          throws IllegalArgumentException {

    int i;


    if (startTime < 0 || endTime < 0) {
      throw new IllegalArgumentException("time cannot be negative.");
    }

    if (startTime >= endTime) {
      throw new IllegalArgumentException("endTime must be greater than startTime.");
    }

    if (endTime >= this.shapeEndTime) {
      throw new IllegalArgumentException("endTime cannot be larger than shape's lifetime.");
    }

    //Checks if a move has already been made in this time slot
    for (i = startTime; i < endTime; i++) {
      if (!this.moveTimeSlots[i]) {
        throw new IllegalArgumentException("There is already a move in this time slot");
      }
      this.moveTimeSlots[i] = false;
    }

    double stepX = (endX - this.x[startTime]) / (endTime - startTime);
    double stepY = (endY - this.y[startTime]) / (endTime - startTime);


    for (i = startTime; i < endTime; i++) {

      this.x[i + 1] = this.x[i] + stepX;
      this.y[i + 1] = this.y[i] + stepY;

    }

    Arrays.fill(x, endTime, this.shapeEndTime, endX);
    Arrays.fill(y, endTime, this.shapeEndTime, endY);

  }

  @Override
  public void changeColor(float endRed, float endGreen, float endBlue, int startTime,
                          int endTime) throws IllegalArgumentException {

    int i;
    Color nextColor;
    float nextRed;
    float nextGreen;
    float nextBlue;

    if (endRed < 0.0 || endRed > 256.0 || endGreen < 0.0
            || endGreen > 256.0 || endBlue < 0.0 || endBlue > 256.0) {
      throw new IllegalArgumentException("Color values must be between 0 and 256.");
    }

    if (startTime < 0 || endTime < 0) {
      throw new IllegalArgumentException("time cannot be negative.");
    }

    if (startTime >= endTime) {
      throw new IllegalArgumentException("endTime must be greater than startTime.");
    }

    if (endTime >= this.shapeEndTime) {
      throw new IllegalArgumentException("endTime cannot be larger than shape's lifetime.");
    }
    //Checks if a color change has already been made in this time slot
    for (i = startTime; i < endTime; i++) {
      if (!this.colorTimeSlots[i]) {
        throw new IllegalArgumentException("There is already a color change in this time slot");
      }
      this.colorTimeSlots[i] = false;
    }

    float stepRed = (float) ((endRed - (this.color[startTime].getRed()))
            / (endTime - startTime));

    float stepGreen = (float) (endGreen - (this.color[startTime].getGreen()))
            / (endTime - startTime);

    float stepBlue = (float) (endBlue - (this.color[startTime].getBlue()))
            / (endTime - startTime);


    for (i = startTime; i < endTime; i++) {
      nextRed = (float) (this.color[i].getRed() + stepRed);
      nextGreen = (float) (this.color[i].getGreen() + stepGreen);
      nextBlue = (float) (this.color[i].getBlue() + stepBlue);

      //Handle marginally negative cases such as -.00625 due to computer math
      if (nextRed < 0) {
        nextRed = 0;
      }
      if (nextBlue < 0) {
        nextBlue = 0;
      }
      if (nextGreen < 0) {
        nextGreen = 0;
      }

      nextColor = new Color((int) nextRed, (int) nextGreen, (int) nextBlue);

      this.color[i + 1] = nextColor;


    }
    Arrays.fill(color, endTime, this.shapeEndTime, new Color((int) endRed,
            (int) endGreen, (int) endBlue));
  }

  @Override
  public void changeSize(double endWidth, double endHeight, int startTime, int endTime)
          throws IllegalArgumentException {

    int i;

    if (startTime < 0 || endTime < 0) {
      throw new IllegalArgumentException("time cannot be negative.");
    }


    if (endWidth <= 0 || endHeight <= 0) {
      throw new IllegalArgumentException("width and height must both be greater than 0.");
    }


    if (endTime >= this.shapeEndTime) {
      throw new IllegalArgumentException("endTime cannot be larger than shape's lifetime.");
    }

    //Checks if a size change has already been made in this time slot
    for (i = startTime; i < endTime; i++) {
      if (!this.sizeTimeSlots[i]) {
        throw new IllegalArgumentException("There is already a size change in this time slot");
      }
      this.sizeTimeSlots[i] = false;
    }

    double sizeChangeWidth = (endWidth - this.width[startTime]) / (endTime - startTime);
    double sizeChangeHeight = (endHeight - this.height[startTime]) / (endTime - startTime);

    for (i = startTime; i < endTime; i++) {
      if (endWidth != this.width[startTime]) {
        this.width[i + 1] = this.width[i] + sizeChangeWidth;
      }

      for (int j = startTime; j < endTime; j++) {
        if (endHeight != this.height[startTime]) {
          this.height[j + 1] = this.height[j] + sizeChangeHeight;

        }

      }

    }

    Arrays.fill(this.width, endTime, this.shapeEndTime, endWidth);
    Arrays.fill(this.height, endTime, this.shapeEndTime, endHeight);

  }

  @Override
  public String createShapeCaption() {
    return "Create " + this.getShapeType() + " " + this.getName() + " with color "
            + this.getColor(0)
            + ") and corner at (" + this.getX(0) + ", " + this.getY(0) + "), width: "
            + this.getWidth(0) + " and height: " + this.getHeight(0) + "\n";
  }

  @Override
  public String captionColorChange(float r, float g, float b, int startTime, int endTime) {

    float redConverted = (float) (this.getColor(startTime).getRed());
    float greenConverted = (float) ((this.getColor(startTime).getGreen()));
    float blueConverted = (float) ((this.getColor(startTime).getBlue()));

    return this.getName() + " changes color from (" + redConverted + ", " + greenConverted
            + ", " + blueConverted + ") to (" + r + ", " + g + ", " + b + ") from time t = "
            + startTime + " to t = " + endTime + "\n";
  }

  @Override
  public String captionMove(double endX, double endY, int startTime, int endTime) {
    return this.getName() + " moves from (" + this.getX(startTime) + ", " + this.getY(startTime)
            + ") to (" + endX + ", " + endY + ") from time t = " + startTime + " to t = " + endTime
            + "\n";
  }

  @Override
  public String captionResize(double endWidth, double endHeight, int startTime, int endTime) {

    return this.getName() + " scales from Width: " + this.getWidth(startTime)
            + ", Height: " + this.getHeight(startTime) + " to Width: " + endWidth + ", Height: "
            + endHeight + " from time t = " + startTime + " to t = " + endTime + "\n";
  }

  @Override
  public String toString() {

    String returnString = "";

    returnString += "Name: " + this.name + "\n";
    returnString += "Xpositions: " + Arrays.toString(this.x) + "\n";
    returnString += "Ypositions: " + Arrays.toString(this.y) + "\n";
    returnString += "moveTimeSlots: " + Arrays.toString(this.moveTimeSlots) + "\n";
    returnString += "width: " + Arrays.toString(this.width) + "\n";
    returnString += "height: " + Arrays.toString(this.height) + "\n";
    returnString += "resizeTimeSlots: " + Arrays.toString(this.sizeTimeSlots) + "\n";
    returnString += "colors: " + Arrays.toString(this.color) + "\n";
    returnString += "colorTimeSlots: " + Arrays.toString(this.colorTimeSlots) + "\n";
    returnString += "visibility: " + Arrays.toString(this.visible) + "\n";


    return returnString;
  }


}
