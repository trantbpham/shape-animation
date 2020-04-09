package cs5004.animator.model;

import java.awt.Color;

/**
 * This interface represents all the methods that all neu. Shape objects will be able to use.
 */
public interface Shape {

  /**
   * Returns the X coordinate of the shape at time t.
   *
   * @param t from which time value to get the X coordinate
   * @return the X coordinate of the shape
   * @throws IllegalArgumentException - if t is out of range for the shape
   */
  double getX(int t) throws IllegalArgumentException;


  String getName();

  /**
   * Returns the Y coordinate of the shape at time t.
   *
   * @param t from which time value to get the Y coordinate
   * @return the Y coordinate of the shape
   * @throws IllegalArgumentException - if t is out of range for the shape
   */
  double getY(int t) throws IllegalArgumentException;

  /**
   * Returns Java Color object describing this shape's color at time t.
   *
   * @param t from which time value to get the color
   * @return Java Color object describing this shape's color
   * @throws IllegalArgumentException - if t is out of range for the shape
   */
  Color getColor(int t) throws IllegalArgumentException;

  /**
   * Returns the height of the shape at time t.
   *
   * @param t from which time value to get the color
   * @return the height of the shape
   * @throws IllegalArgumentException - if t is out of range for the shape
   */
  double getHeight(int t) throws IllegalArgumentException;

  /**
   * Returns the width of the shape at time t.
   *
   * @param t from which time value to get the color
   * @return the width of the shape
   * @throws IllegalArgumentException - if t is out of range for the shape
   */
  double getWidth(int t) throws IllegalArgumentException;

  /**
   * Returns this shape's shapeType.
   *
   * @return this shape's shapeType.
   * @throws IllegalArgumentException - if t is out of range for the shape
   */
  ShapeType getShapeType();

  /**
   * Returns this shape's visibility at time t.
   *
   * @return this shape's visibility
   * @throws IllegalArgumentException - if t is out of range for the shape
   */
  boolean getVisibility(int t) throws IllegalArgumentException;

  /**
   * Change the object's visibility from false to true from startTime until animation completion.
   *
   * @param startTime - the start time of the move
   */
  void changeVisibility(int startTime);

  /**
   * Move the shape to the new location over the given time period.
   *
   * @param endX      - the X coordinate of the shape after the move is complete
   * @param endY      - the Y coordinate of the shape after the move is complete
   * @param startTime - the start time of the move
   * @param endTime   - the end time of the move
   * @throws IllegalArgumentException - if any of the inputs are invalid, throw error and inform
   *                                  user
   */
  void move(double endX, double endY, int startTime, int endTime) throws IllegalArgumentException;


  /**
   * Change the color of the shape.
   *
   * @param endRed    - the numerical (0.0-1.0) Red value of the color after the color change is
   *                  complete
   * @param endGreen  - the numerical (0.0-1.0) Green value of the color after the color change is
   *                  complete
   * @param endBlue   - the numerical (0.0-1.0) Blue value of the color after the color change is
   *                  complete
   * @param startTime - the start time of the color change
   * @param endTime   - the end time of the color change
   * @throws IllegalArgumentException - if any of the inputs are invalid, throw error and inform
   *                                  user
   */
  void changeColor(float endRed, float endGreen, float endBlue, int startTime, int endTime)
          throws IllegalArgumentException;

  /**
   * Change the size of the shape.
   *
   * @param endWidth  - the Width of the shape after the color change is complete
   * @param endHeight - the Height of the shape after the color change is complete
   * @param startTime - the start time of the color change
   * @param endTime   - the end time of the color change
   * @throws IllegalArgumentException - if any of the inputs are invalid, throw error and inform
   *                                  user
   */
  void changeSize(double endWidth, double endHeight, int startTime, int endTime)
          throws IllegalArgumentException;


  /**
   * Print out this shape's description for textual animation description. Formats and returns name
   * Type, starting location, starting width, starting height, starting color, starting time, ending
   * time.
   *
   * @return string of shape's description
   */
  String selfDescription();

  /**
   * Getter for shape move start time.
   */
  int getMotionMoveStartTime(int i);

  /**
   * Getter for shape move end time.
   */
  int getMotionMoveEndTime(int i);

  /**
   * Setter to assign shape's name.
   */
  void setName(String newName);

  /**
   * Getter for get shape X coordinate move's from.
   */
  double getMoveXDestination(int i);

  /**
   * Getter for get shape X coordinate move's destination.
   */
  double getFromXDestination(int i);

  /**
   * Getter for get shape X coordinate move's destination list.
   */
  int getFromXDestinationSize();

  /**
   * Getter for get shape Y coordinate move's from.
   */
  double getFromYDestination(int i);

  /**
   * Getter for get shape Y coordinate move's destination list.
   */
  int getFromYDestinationSize();

  /**
   * Getter for get shape Y coordinate move's destination.
   */
  double getMoveYDestination(int i);

  /**
   * Getter for get shape Y coordinate move's from list.
   */
  int getMoveYDestinationSize();

  /**
   * Getter for get shape width coordinate move's destination.
   */
  double getWidthDestination(int i);

  /**
   * Getter for get shape width coordinate move's from.
   */
  double getFromWidthDestination(int i);

  /**
   * Getter for get shape width coordinate change destination list.
   */
  double getWidthDestinationSize();

  /**
   * Getter for get shape height coordinate change destination.
   */
  double getHeightDestination(int i);

  /**
   * Getter for get shape height coordinate change destination list.
   */
  int getHeightDestinationSize();

  /**
   * Getter for get shape height coordinate move's from.
   */
  double getFromHeightDestination(int i);

  /**
   * Getter for get shape resize start time.
   */
  int getMotionResizeStartTime(int i);

  /**
   * Getter for get shape resize end time.
   */
  int getMotionResizeEndtime(int i);

  /**
   * Getter for get shape change color start time.
   */
  int getColorStartTime(int i);

  /**
   * Getter for get shape change color end time.
   */
  int getColorEndTime(int i);


  /**
   * Getter for get shape color R original color.
   */
  double getFromRColor(int i);

  /**
   * Getter for get shape color G original color.
   */
  double getFromGColor(int i);

  /**
   * Getter for get shape color B original color.
   */
  double getFromBColor(int i);

  /**
   * Getter for get shape color R target color.
   */
  double getRColorDestination(int i);

  /**
   * Getter for get shape color G target color.
   */
  double getGColorDestination(int i);

  /**
   * Getter for get shape color B target color.
   */
  double getBColorDestination(int i);

  /**
   * Getter for get shape color list.
   */
  int getColorSize();

  /**
   * Create caption for shape create.
   * @return a String caption for shape create
   */
  String createShapeCaption();

  /**
   * Create caption for color change.
   * @param r - Red
   * @param g - Green
   * @param b - Blue
   * @param startTime - start time to change color
   * @param endTime   - end time to change color
   * @return a String caption for color change
   */
  String captionColorChange(float r, float g, float b, int startTime, int endTime);

  /**
   * Create caption for shape move.
   * @param endX - X destination
   * @param endY - Y destination
   * @param startTime - start time to move
   * @param endTime - end time to move
   * @return a String caption for move
   */
  String captionMove(double endX, double endY, int startTime, int endTime);

  /**
   * Create caption for shape resize.
   * @param endWidth - width change
   * @param endHeight - height change
   * @param startTime - start time to resize
   * @param endTime - end time to resize
   * @return a String caption for resize
   */
  String captionResize(double endWidth, double endHeight, int startTime, int endTime);
}