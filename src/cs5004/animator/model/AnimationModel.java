package cs5004.animator.model;

import java.util.List;

/**
 * This interface represents all the methods that the Animation Model will be able to perform when
 * requested by the controller.
 */
public interface AnimationModel {

  /**
   * Returns the left bound location of the model for the purposes of drawing the view.
   *
   * @returns - Left bound of the model
   */
  int getLeftBound();

  /**
   * Returns the top bound location of the model for the purposes of drawing the view.
   *
   * @return - Left bound of the model
   */
  int getTopBound();

  /**
   * Returns the Width of the model's area for the purposes of drawing the view.
   *
   * @return - Width of the model
   */
  int getWidth();

  /**
   * Returns the Height of the model's area for the purposes of drawing the view.
   *
   * @return - Height of the model
   */
  int getHeight();

  /**
   * Returns the Longest life of a shape in this model for use in terminating the views.
   *
   * @return - Longest lifetime of a shape in the model in arbitrary time t.
   */
  int getLongestLife();

  /**
   * Adds a shape to the animation model, this shape will be stored in a Hashtable (dictionary)
   * where the key of the Hashtable will be the shape's name and the value of the key will be the
   * shape object itself.
   *
   * @param name      - name of the shape
   * @param shapeType - what kind of shape to draw (rectangle or oval)
   * @param startX    - X position of this shape's origin
   * @param startY    - Y position of this shape's origin
   * @param width     - this shape's width (X dimension)
   * @param height    - this shape's height (Y dimension
   * @param red       - this shape's red color
   * @param green     - this shape's green color
   * @param blue      - this shape's blue color
   * @param startTime - this shape's start time (appears in animation)
   * @param endTime   - this shape's end time (disappears in animation)
   * @throws IllegalArgumentException - if any of the inputs are invalid, throw error and inform
   *                                  user
   */
  void addShape(String name, String shapeType, double startX, double startY, double width,
                double height, double red, double green, double blue, int startTime, int endTime)
          throws IllegalArgumentException;

  /**
   * Adds a move to a shape, which will move the shape from it's current location to the endX, endY
   * over the time period from startTime to endTime.
   *
   * @param name      - name of the shape
   * @param x1        - start X position
   * @param y2        - start Y position
   * @param endX      - final X position of move
   * @param endY      - final Y position of move
   * @param startTime - when the shape should start its move
   * @param endTime   - when the shape should end its move
   * @throws IllegalArgumentException - if any of the inputs are invalid, throw error and inform
   *                                  user
   */
  void addMove(String name, double x1, double y2, double endX, double endY,
               int startTime, int endTime)
          throws IllegalArgumentException;

  /**
   * Adds a sizechange to a shape, which will change the shape's size parameters from its current
   * values to its new values over the stated time period.
   *
   * @param name      - name of the shape
   * @param w1        - start width
   * @param h1        - start height
   * @param startTime - when the shape should start its size change
   * @param endTime   - when the shape should end its size change
   * @param endWidth  - final width of shape
   * @param endHeight - final height of shape
   * @throws IllegalArgumentException - if any of the inputs are invalid, throw error and inform
   *                                  user
   */
  void addSizeChange(String name, double w1, double h1, int startTime, int endTime,
                     double endWidth, double endHeight)
          throws IllegalArgumentException;


  /**
   * Adds a color change to a shape, which will change the shape's color from it's current color to
   * the new color values.
   *
   * @param name      - name of the shape
   * @param endR      - final Red value of the shape's color
   * @param endG      - final Green value of the shape's color
   * @param endB      - final Blue value of the shape's color
   * @param startTime - when the shape should start its move
   * @param endTime   - when the shape should end its move
   * @throws IllegalArgumentException - if any of the inputs are invalid, throw error and inform
   *                                  user
   */
  void addColorChange(String name, double r1, double g1, double b1, double endR, double endG,
                      double endB, int startTime,
                      int endTime) throws IllegalArgumentException;

  /**
   * Return all parameters of all shapes in the current model for a given time value.
   *
   * @param t time value for which to return parameters
   * @return returns shape parameters (currently as string array, but may change)
   */
  List<String> getShapeParameters(int t) throws IllegalArgumentException;

  /**
   * Return a string describing all animations and shapes in this model.
   *
   * @return returns animation in text format
   */
  String getTextAnimation() throws IllegalArgumentException;

  /**
   * Return a string list of the shape name in the animation model.
   * @return string builder with animation shapes' names
   */
  StringBuilder getShapesNameInAnimation();

  /**
   * Using the shape's key to remove its key & value from all the lists and hash map in this model.
   *
   * @param name take in a String for shape name's
   * @throws IllegalArgumentException if shape key doesn't exist in lists and hash map.
   */
  void removeShape(String name) throws IllegalArgumentException;
}
