package cs5004.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cs5004.animator.util.AnimationBuilder;

/**
 * Implementing all methods in the AnimationModel Interface, represents the animation, storing all
 * shapes in a hashtable, utilizing the shape's name as the key.
 */
public class AnimationModelImpl implements AnimationModel {


  protected List<String> animationHistory;
  protected HashMap<String, AbstractShape> shapes;
  protected List<String> orderedKeys;
  protected int canvasLeftBound;
  protected int canvasTopBound;
  protected int canvasWidth;
  protected int canvasHeight;
  protected int longestLife = 0;


  /**
   * Constructor includes a hashtable and and array list of strings to store all animation history.
   */
  public AnimationModelImpl() {

    shapes = new HashMap<String, AbstractShape>();
    animationHistory = new ArrayList<>();
    orderedKeys = new ArrayList<>();

  }

  /**
   * Constructor includes a hashtable and and array list of strings to store all animation history.
   */
  public AnimationModelImpl getAnimationModel() {
    return this;
  }

  public Set<String> getKey() {
    return this.shapes.keySet();
  }

  public Shape getValue(String name) {
    return shapes.get(name);
  }

  /**
   * Returns the string of this model's animation history.
   *
   * @return String - text String of the animation history of the model
   */
  public String getAnimationHistory() {

    String returnString = "";
    Iterator<String> iter = animationHistory.iterator();
    while (iter.hasNext()) {
      returnString += iter.next() + "\n";
    }
    return returnString;
  }

  @Override
  public int getLeftBound() {
    return this.canvasLeftBound;
  }

  @Override
  public int getTopBound() {
    return this.canvasTopBound;
  }

  @Override
  public int getWidth() {
    return this.canvasWidth;
  }

  @Override
  public int getHeight() {
    return this.canvasHeight;
  }

  @Override
  public int getLongestLife() {
    return this.longestLife;
  }

  @Override
  public void addShape(String name, String shapeType, double startX, double startY, double width,
                       double height, double red, double green, double blue,
                       int startTime, int endTime) throws IllegalArgumentException {

    shapeType = shapeType.toLowerCase();

    //Check for invalid inputs
    if (red < 0.0 || red > 1.0 || green < 0.0 || green > 1.0 || blue < 0.0 || blue > 1.0) {
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

    if (!"rectangle,ellipse".contains(shapeType)) {
      throw new IllegalArgumentException("Invalid shape type,"
              + " please use rectangle or ellipse");
    }

    //check if this element exists in this model
    if (shapes.get(name) != null) {
      throw new IllegalArgumentException("That shape name already exists,"
              + " please choose a different one.");
    }

    if (shapeType.equals("rectangle")) {
      shapes.put(name, new RectangleShape(name, startX, startY, width, height,
              (float) red, (float) green, (float) blue, startTime, endTime));
      this.orderedKeys.add(name);
      this.animationHistory.add(this.shapes.get(name).createShapeCaption());
    } else if (shapeType.equals("ellipse")) {
      shapes.put(name, new EllipseShape(name, startX, startY, width, height,
              (float) red, (float) green, (float) blue, startTime, endTime));
      this.orderedKeys.add(name);
      this.animationHistory.add(this.shapes.get(name).createShapeCaption());
    }

  }

  @Override
  public void removeShape(String name) throws IllegalArgumentException {
    if (!shapes.containsKey(name)) {
      throw new IllegalArgumentException("Shape doesn't exist");
    }
    shapes.remove(name, shapes.get(name));

    for (String each : animationHistory) {
      if (each.equals(name)) {
        animationHistory.add(shapes.get(name).removeShapeCaption());
        animationHistory.remove(shapes.get(name));
      }
    }

    for (String each : orderedKeys) {
      if (each.equals(name)) {
        orderedKeys.remove(shapes.get(name));
      }
    }
  }

  @Override
  public void addMove(String name, double x1, double y1, double endX, double endY,
                      int startTime, int endTime) throws IllegalArgumentException {


    //check if this element exists in this model
    AbstractShape shape = this.shapes.get(name);


    if (shape != null) {
      if (!shape.initialized[0]) {
        Arrays.fill(shape.x, endX);
        Arrays.fill(shape.y, endY);
        shape.initialized[0] = true;
        shape.changeVisibility(startTime);
        return;
      }
      shape.move(endX, endY, startTime, endTime);
      this.animationHistory.add(this.shapes.get(name)
              .captionMove(endX, endY, startTime, endTime));
      shapes.put(name, shape);
      shape.motionMoveStartTime.add(startTime);
      shape.motionMoveEndTime.add(endTime);
      shape.moveXDestination.add(endX);
      shape.moveYDestination.add(endY);
      shape.fromXDestination.add(x1);
      shape.fromYDestination.add(y1);
    } else {
      throw new IllegalArgumentException("Invalid name, that shape doesn't exist in this model."
              + "It can be added with addShape().");
    }
  }

  @Override
  public void addSizeChange(String name, double w1, double h1, int startTime, int endTime,
                            double endWidth, double endHeight) throws IllegalArgumentException {

    //check if this element exists in this model
    AbstractShape shape = this.shapes.get(name);
    if (shape != null) {
      if (!shape.initialized[1]) {
        Arrays.fill(shape.width, endWidth);
        Arrays.fill(shape.height, endHeight);
        shape.initialized[1] = true;
        return;
      }
      shape.changeSize(endWidth, endHeight, startTime, endTime);
      this.animationHistory.add(this.shapes.get(name).captionResize(
              endWidth, endHeight, startTime, endTime));
      shape.motionResizeStartTime.add(startTime);
      shape.motionResizeEndTime.add(endTime);
      shape.heightDestination.add(endHeight);
      shape.widthDestination.add(endWidth);
      shape.fromWidthDestination.add(w1);
      shape.fromHeightDestination.add(h1);
      shapes.put(name, shape);
    } else {
      throw new IllegalArgumentException("Invalid name, that shape doesn't exist in this model."
              + "It can be added with addShape().");
    }
  }

  @Override
  public void addColorChange(String name, double r1, double g1, double b1, double endR,
                             double endG, double endB, int startTime,
                             int endTime) throws IllegalArgumentException {

    if (r1 < -.01 || r1 > 255.01 || b1 < -.01 || b1 > 255.01 || g1 < -.01 || g1 > 255.01) {
      throw new IllegalArgumentException("Motion starting color value must be 0-255");
    }

    if (endR < -.01 || endR > 255.01 || endG < -.01
            || endG > 255.01 || endB < -.01 || endB > 255.01) {
      throw new IllegalArgumentException("Motion ending color value must be 0-255");
    }

    //check if this element exists in this model
    AbstractShape shape = this.shapes.get(name);
    if (shape != null) {
      if (!shape.initialized[2]) {
        Arrays.fill(shape.color, new Color((int) endR, (int) endG, (int) endB));
        shape.initialized[2] = true;
        return;
      }

      shape.changeColor((float) endR, (float) endG, (float) endB, startTime, endTime);
      this.animationHistory.add(this.shapes.get(name).captionColorChange(
              (float) endR, (float) endG, (float) endB, startTime, endTime));
      shapes.put(name, shape);

      shape.motionColorStartTime.add(startTime);
      shape.motionColorEndTime.add(endTime);

      shape.rDestination.add(endR);
      shape.gDestination.add(endG);
      shape.bDestination.add(endB);

      shape.fromRColor.add(r1);
      shape.fromGColor.add(g1);
      shape.fromBColor.add(b1);
    } else {
      throw new IllegalArgumentException("Invalid name, that shape doesn't exist in this model."
              + "It can be added with addShape().");
    }
  }

  @Override
  public List<String> getShapeParameters(int t) throws IllegalArgumentException {

    if (t < 0) {
      throw new IllegalArgumentException("Time cannot be less than 0");
    }

    List parameterOutput = new LinkedList<String>();

    for (String currentElement : this.orderedKeys) {
      try {

        parameterOutput.add(String.format("%s|",
                shapes.get(currentElement).getShapeType().toString())
                + String.format("%.2f|", shapes.get(currentElement).getX(t))
                + String.format("%.2f|", shapes.get(currentElement).getY(t))
                + String.format("%.2f|", shapes.get(currentElement).getWidth(t))
                + String.format("%.2f|", shapes.get(currentElement).getHeight(t))
                + String.format("%s|", shapes.get(currentElement).getColor(t).getRGB())
                + String.format("%s", shapes.get(currentElement).getVisibility(t)));
      } catch (Exception e) {
        //This will skip over shapes if their lifetimes are expired
        continue;
      }
    }

    return parameterOutput;
  }

  @Override
  public StringBuilder getShapesNamInAnimation() {
    StringBuilder returnString = new StringBuilder("");
    if (shapes.isEmpty()) {
      return returnString.append("shape not found");
    }
    Iterator<String> iter = animationHistory.iterator();
    for (String each : shapes.keySet()) {
      returnString.append(each);
      returnString.append(", ");
    }
    returnString.setLength(returnString.length() - 2);
    return returnString;
  }

  @Override
  public String toString() {
    String returnString = "{";


    if (shapes.isEmpty()) {
      returnString = "{}\n";
      returnString += this.animationHistory.toString();
      return returnString;
    }

    for (String currentElement : shapes.keySet()) {
      returnString += currentElement + ", ";
    }
    returnString = returnString.substring(0, returnString.length() - 2);
    returnString += "}\n";

    returnString += this.animationHistory.toString();

    return returnString;
  }

  @Override
  public String getTextAnimation() {
    String returnString = "";

    if (shapes.isEmpty()) {
      return returnString;
    }

    for (String currentElement : shapes.keySet()) {
      returnString += shapes.get(currentElement).selfDescription() + "\n\n";
    }

    returnString += "\n";

    Iterator<String> iter = animationHistory.iterator();
    while (iter.hasNext()) {
      returnString += iter.next() + "\n";
    }

    return returnString;
  }

  public HashMap<String, AbstractShape> getShape() {
    return this.shapes;
  }


  /**
   * AnimationModelBuilder is the internal builder for AnimationModelImpl. It utilizes
   * AnimationModelReader to parse and input file and use that information to build the
   * AnimationModelImpl object with the correct shapes and animations.
   */
  public static final class AnimationModelBuilder implements AnimationBuilder<AnimationModelImpl> {
    private AnimationModelImpl animationModel = new AnimationModelImpl();
    private int canvasX;
    private int canvasY;
    private int canvasWidth;
    private int canvasHeight;

    /**
     * Default constructor for the AnimationModelBuilder, this simply initializes the values that
     * the builder will need to create the AnimationModelImpl object such as the object itself and
     * its bounds.
     */
    public AnimationModelBuilder() {
      this.animationModel = new AnimationModelImpl();
      this.canvasX = 0;
      this.canvasY = 0;
      this.canvasWidth = 0;
      this.canvasHeight = 0;
    }

    @Override
    public AnimationModelImpl build() {
      animationModel.canvasLeftBound = this.canvasX;
      animationModel.canvasTopBound = this.canvasY;
      animationModel.canvasHeight = this.canvasHeight;
      animationModel.canvasWidth = this.canvasWidth;
      return animationModel;
    }

    @Override
    public AnimationBuilder<AnimationModelImpl> setBounds(int x, int y, int width, int height)
            throws IllegalArgumentException {
      if (x < 0 || y < 0 || width < 0 || height < 0) {
        throw new IllegalArgumentException("Bounds must be positive integers");
      }
      this.canvasHeight = height;
      this.canvasWidth = width;
      this.canvasX = x;
      this.canvasY = y;
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModelImpl> declareShape(String name, String type)
            throws IllegalArgumentException {
      HashMap<String, AbstractShape> shapesList = this.animationModel.getShape();

      if (type.equals(ShapeType.Rectangle.toString())) {
        RectangleShape newShape = new RectangleShape(name, 0, 0, 1,
                1, 0, 0, 0, 0, 10000);
        newShape.initialized[0] = false;
        newShape.initialized[1] = false;
        newShape.initialized[2] = false;
        shapesList.put(name, newShape);
        this.animationModel.orderedKeys.add(name);
      } else if (type.equals(ShapeType.Ellipse.toString())) {
        EllipseShape newShape = new EllipseShape(name, 0, 0, 1,
                1, 0, 0, 0, 0, 10000);
        newShape.initialized[0] = false;
        newShape.initialized[1] = false;
        newShape.initialized[2] = false;
        shapesList.put(name, newShape);
        this.animationModel.orderedKeys.add(name);
      } else {
        throw new IllegalArgumentException("Shape type must be rectangle or ellipse.");
      }


      return this;
    }


    @Override
    public AnimationBuilder<AnimationModelImpl> addMotion(String name, int t1, int x1, int y1,
                                                          int w1, int h1, int r1, int g1, int b1,
                                                          int t2, int x2, int y2, int w2, int h2,
                                                          int r2, int g2, int b2)
            throws IllegalArgumentException {

      if (t2 > this.animationModel.longestLife) {
        this.animationModel.longestLife = t2;
      }

      if (t2 < t1) {
        throw new IllegalArgumentException("starttime must be less than end time");
      }

      animationModel.addMove(name, x1, y1, x2, y2, t1, t2);

      animationModel.addColorChange(name, r1, g1, b1, r2, g2, b2, t1, t2);

      animationModel.addSizeChange(name, w1, h1, t1, t2, w2, h2);

      return this;
    }
  }
}

