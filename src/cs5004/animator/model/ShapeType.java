package cs5004.animator.model;

/**
 * Represent the shape type for all the shapes. This can be expand to store other shape types when
 * expanding to other shapes.
 */
public enum ShapeType {
  Rectangle("rectangle"),
  Ellipse("ellipse");

  private final String data;

  /**
   * Constructs the ShapeType enumerator object by input string "Rectangle" or "ShapeModel.Oval"
   *
   * @param val - input string for this enumerator, either "Rectangle" or "ShapeModel.Oval"
   */
  ShapeType(String val) {
    this.data = val;
  }

  @Override
  public String toString() {
    return this.data;
  }

}
