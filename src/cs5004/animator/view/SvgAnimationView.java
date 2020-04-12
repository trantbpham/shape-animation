package cs5004.animator.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import cs5004.animator.model.AbstractShape;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.ShapeType;

/**
 * SVG view of the animation model. Main will be using printSVG function to generate the SVG content
 * for the file.
 */
public class SvgAnimationView {
  private AnimationModelImpl animationModel;
  private AbstractShape shape;
  private double timescale;

  /**
   * SVG constructor with the field of animation model.
   */
  public SvgAnimationView() {
    this.animationModel = new AnimationModelImpl();
  }

  /**
   * SVG takes in the animationModel to generate view.
   */
  public SvgAnimationView(AnimationModelImpl newAnimationView, int speed) {
    this.animationModel = newAnimationView;
    timescale = 1000 / (double) speed;
  }

  /**
   * Use string builder to create SVG file by accessing the hashmap of the shapes and get its info.
   */
  public StringBuilder printSGV() {
    HashMap<String, AbstractShape> map = animationModel.getShape();
    ArrayList<AbstractShape> shapeList = new ArrayList<>();
    for (String name : map.keySet()) {
      shapeList.add(map.get(name));
    }

    StringBuilder returnString = new StringBuilder("<svg ");

    returnString.append("width=\"" + animationModel.getWidth() + "\" ");
    returnString.append("height=\"" + animationModel.getHeight() + "\" ");
    returnString.append("viewBox=\"" + animationModel.getLeftBound() + " "
            + animationModel.getTopBound() + " "
            + animationModel.getWidth() + " " + animationModel.getHeight() + "\" ");
    returnString.append("version=\"1.1\" ");
    returnString.append("xmlns=\"http://www.w3.org/2000/svg\">\n\n");

    for (AbstractShape currentShape : shapeList) {
      if (currentShape.getShapeType() == ShapeType.Rectangle) {
        returnString.append("<rect ");
        returnString.append("id=\"" + currentShape.getName() + "\" ");
        returnString.append("x=\"" + currentShape.getX(0) + "\" ");
        returnString.append("y=\"" + currentShape.getY(0) + "\" ");
        returnString.append("width=\"" + currentShape.getWidth(0) + "\" ");
        returnString.append("height=\"" + currentShape.getHeight(0) + "\" ");
        returnString.append("fill=\"" + colorConvert(currentShape.getColor(0)) + "\" ");
        returnString.append("visibility=\"visible\" >\n\n");

      }

      if (currentShape.getShapeType() == ShapeType.Ellipse) {
        returnString.append("<ellipse ");
        returnString.append("id=\"" + currentShape.getName() + "\" ");
        returnString.append("cx=\"" + currentShape.getX(0) + "\" ");
        returnString.append("cy=\"" + currentShape.getY(0) + "\" ");
        returnString.append("rx=\"" + currentShape.getWidth(0) + "\" ");
        returnString.append("ry=\"" + currentShape.getHeight(0) + "\" ");
        returnString.append("fill=\"" + colorConvert(currentShape.getColor(0)) + "\" ");
        returnString.append("visibility=\"visible\" >\n\n");
      }

      for (int i = 0; i < currentShape.getFromXDestinationSize(); i++) {
        if ((currentShape.getFromXDestination(i) < currentShape.getMoveXDestination(i))
                || (currentShape.getFromXDestination(i) > currentShape.getMoveXDestination(i))) {
          returnString.append("        <animate attributeType=\"xml\" ");
          returnString.append("begin=\""
                  + currentShape.getMotionMoveStartTime(i) * timescale + "ms\" ");
          returnString.append("dur=\""
                  + currentShape.getMotionMoveEndTime(i) * timescale + "ms\" ");
          returnString.append("attributeName=\"x\" ");
          returnString.append("from=\"" + currentShape.getFromXDestination(i) + "\" ");
          returnString.append("to=\"" + currentShape.getMoveXDestination(i) + "\" ");
          returnString.append("fill=\"freeze\" />\n");

        }
      }

      for (int j = 0; j < currentShape.getMoveYDestinationSize(); j++) {
        if ((currentShape.getFromYDestination(j) < currentShape.getMoveYDestination(j))
                || (currentShape.getFromYDestination(j) > currentShape.getMoveXDestination(j))) {
          returnString.append("        <animate attributeType=\"xml\" ");
          returnString.append("begin=\""
                  + currentShape.getMotionMoveStartTime(j) * timescale + "ms\" ");
          returnString.append("dur=\""
                  + currentShape.getMotionMoveEndTime(j) * timescale + "ms\" ");
          returnString.append("attributeName=\"y\" ");
          returnString.append("from=\"" + currentShape.getFromYDestination(j) + "\" ");
          returnString.append("to=\"" + currentShape.getMoveYDestination(j) + "\" ");
          returnString.append("fill=\"freeze\" />\n");
        }
      }

      for (int k = 0; k < currentShape.getWidthDestinationSize(); k++) {
        if ((currentShape.getWidthDestination(k) < currentShape.getFromWidthDestination(k))
                || (currentShape.getWidthDestination(k)
                > currentShape.getFromWidthDestination(k))) {

          System.out.println(currentShape.getMotionResizeStartTime(0));
          returnString.append("        <animate attributeType=\"xml\" ");
          returnString.append("begin=\""
                  + currentShape.getMotionResizeStartTime(k) * timescale + "ms\" ");
          returnString.append("dur=\""
                  + currentShape.getMotionResizeEndtime(k) * timescale + "ms\" ");
          returnString.append("attributeName=\"width\" ");
          returnString.append("from=\"" + currentShape.getFromWidthDestination(k) + "\" ");
          returnString.append("to=\"" + currentShape.getWidthDestination(k) + "\" ");
          returnString.append("fill=\"freeze\" />\n");
        }
      }
      for (int l = 0; l < currentShape.getHeightDestinationSize(); l++) {
        if ((currentShape.getHeightDestination(l) < currentShape.getFromHeightDestination(l))
                || (currentShape.getHeightDestination(l)
                > currentShape.getFromHeightDestination(l))) {
          returnString.append("        <animate attributeType=\"xml\" ");
          returnString.append("begin=\""
                  + currentShape.getMotionResizeStartTime(l) * timescale + "ms\" ");
          returnString.append("dur=\""
                  + currentShape.getMotionResizeEndtime(l) * timescale + "ms\" ");
          returnString.append("attributeName=\"height\" ");
          returnString.append("from=\"" + currentShape.getFromHeightDestination(l) + "\" ");
          returnString.append("to=\"" + currentShape.getHeightDestination(l) + "\" ");
          returnString.append("fill=\"freeze\" />\n");
        }
      }
      for (int m = 0; m < currentShape.getColorSize(); m++) {
        if (((currentShape.getFromRColor(m) < currentShape.getRColorDestination(m))
                || (currentShape.getFromRColor(m) < currentShape.getRColorDestination(m)))
                && ((currentShape.getFromBColor(m) < currentShape.getBColorDestination(m))
                || (currentShape.getFromBColor(m) > currentShape.getBColorDestination(m)))
                && ((currentShape.getFromGColor(m) < currentShape.getGColorDestination(m))
                || (currentShape.getFromGColor(m) > currentShape.getGColorDestination(m)))) {
          returnString.append("        <animate attributeType=\"xml\" ");
          returnString.append("begin=\"" + currentShape.getColorStartTime(m) * timescale + "ms\" ");
          returnString.append("dur=\"" + currentShape.getColorEndTime(m) * timescale + "ms\" ");
          returnString.append("attributeName=\"color\"");
          returnString.append("from=\"rgb(" + currentShape.getFromRColor(m) + ","
                  + currentShape.getFromGColor(m) + "," + currentShape.getFromBColor(m) + ")\" ");
          returnString.append("to=\"rgb(" + currentShape.getRColorDestination(m) + ","
                  + currentShape.getGColorDestination(m) + ","
                  + currentShape.getBColorDestination(m) + ")\" ");
          returnString.append("fill=\"freeze\" />\n");
        }
      }
      if (currentShape.getShapeType() == ShapeType.Rectangle) {
        returnString.append("</rect>\n\n");
      } else if (currentShape.getShapeType() == ShapeType.Ellipse) {
        returnString.append("</ellipse>\n\n");

      }
    }

    returnString.append("</svg>");
    return returnString;
  }

  /**
   * Convert color class to proper SVG color format string.
   */
  private String colorConvert(Color color) {
    String convertedString = "rgb(" + color.getRed() + "," + color.getGreen() + ","
            + color.getBlue() + ")";
    return convertedString;
  }

}
