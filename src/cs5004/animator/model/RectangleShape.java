package cs5004.animator.model;

/**
 * Rectangle Shape Class: inherits Abstract Shape methods & overriding methods to fit Rectangle
 * needs.
 */
public class RectangleShape extends AbstractShape {
  protected ShapeType shapeType;

  /**
   * Construct a Rectangle object - including initializing all of its parameters such as position,
   * width, height, color, startTime, endTime. Angle defaults to 0 and may be changed by
   * controller.
   *
   * @param name      - name of the rectangle
   * @param startX    - X position of this rectangle's origin
   * @param startY    - Y position of this rectangle's origin
   * @param width     - this rectangle's width (X dimension)
   * @param height    - this rectangle's height (Y dimension
   * @param r         - this rectangle's red color (0.0-1.0)
   * @param g         - this rectangle's green color (0.0-1.0)
   * @param b         - this rectangle's blue color (0.0-1.0)
   * @param startTime - this rectangle's start time (appears in animation)
   * @param endTime   - this rectangle's end time (disappears in animation)
   * @throws IllegalArgumentException - if any of the inputs are invalid, throw error and inform
   *                                  user
   */
  public RectangleShape(String name, double startX, double startY, double width,
                        double height, float r, float g, float b, int startTime, int endTime) {

    super(name, startX, startY, width, height, r, g, b, startTime, endTime);
    this.shapeType = ShapeType.Rectangle;

  }

  /**
   * Getter method to identify the shape type.
   *
   * @return shapeType Rectangle
   */
  @Override
  public ShapeType getShapeType() {
    return this.shapeType;
  }


  @Override
  public String selfDescription() {

    String returnString = "";

    returnString += "Name: " + this.name + "\n";
    returnString += "Type: " + this.shapeType + "\n";
    returnString += String.format("Lower Left corner: (%.1f,%.1f), ",
            this.getX(0), this.getY(0));
    returnString += String.format("Width: %.1f, ", this.getWidth(0));
    returnString += String.format("Height: %.1f, ", this.getHeight(0));
    returnString += String.format("Color: (%.1f,%.1f,%.1f)", this.color[0].getRed() / 256.0,
            this.color[0].getGreen() / 256.0, this.color[0].getBlue() / 256.0) + "\n";
    returnString += String.format("Appears at t = %d", this.shapeStartTime) + "\n";
    returnString += String.format("Disappears at t = %d", this.shapeEndTime - 1);


    return returnString;
  }
}
