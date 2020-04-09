package cs5004.animator.model;

/**
 * Oval Shape Class: inherits Abstract Shape methods & overriding methods to fit Oval needs.
 */
public class EllipseShape extends AbstractShape {
  protected ShapeType shapeType;

  /**
   * Construct an Oval object - including initializing all of its parameters such as position,
   * width, height, color, startTime, endTime. Angle defaults to 0 and may be changed by
   * controller.
   *
   * @param name      - Oval
   * @param startX    - X position of this oval's origin
   * @param startY    - Y position of this oval's origin
   * @param width     - this oval's width (X dimension)
   * @param height    - this oval's height (Y dimension
   * @param r         - this oval's red color (0.0-1.0)
   * @param g         - this oval's green color (0.0-1.0)
   * @param b         - this oval's blue color (0.0-1.0)
   * @param startTime - this oval's start time (appears in animation)
   * @param endTime   - this oval's end time (disappears in animation)
   */
  public EllipseShape(String name, double startX, double startY, double width,
                      double height, float r, float g, float b, int startTime, int endTime) {

    super(name, startX, startY, width, height, r, g, b, startTime, endTime);
    this.shapeType = ShapeType.Ellipse;

  }

  /**
   * Getter method to identify the shape type.
   *
   * @return shapeType Oval
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
    returnString += String.format("Center: (%.1f,%.1f) ", this.getX(0), this.getY(0));
    returnString += String.format("X Radius: %.1f ", this.getWidth(0));
    returnString += String.format("Y Radius: %.1f ", this.getHeight(0));
    returnString += String.format("Color: (%.1f,%.1f,%.1f)", this.color[0].getRed() / 256.0,
            this.color[0].getGreen() / 256.0, this.color[0].getBlue() / 256.0) + "\n";
    returnString += String.format("Appears at t = %d", this.shapeStartTime) + "\n";
    returnString += String.format("Disappears at t = %d", this.shapeEndTime - 1);


    return returnString;
  }
}
