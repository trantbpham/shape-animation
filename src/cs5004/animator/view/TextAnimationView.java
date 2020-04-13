package cs5004.animator.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import cs5004.animator.model.AbstractShape;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.ShapeType;

/**
 * Text view of the animation model. Main will be using animationHistoryToString function to
 * generate the text content for the file.
 */
public class TextAnimationView implements ScriptView {
  private AnimationModelImpl animationModel;
  private AbstractShape shape;


  /**
   * Text constructor with the field of animation model.
   */
  public TextAnimationView() {
    this.animationModel = new AnimationModelImpl();

  }

  /**
   * Text takes in the animationModel to generate view.
   */
  public TextAnimationView(AnimationModelImpl newAnimation) {
    this.animationModel = newAnimation;
  }


  @Override
  public StringBuilder printScript() {

    HashMap<String, AbstractShape> map = animationModel.getShape();
    ArrayList<AbstractShape> shapeList = new ArrayList<>();
    List<String> keyList = new ArrayList<String>(map.keySet());
    Collections.sort(keyList);
    for (String name : keyList) {
      shapeList.add(map.get(name));
    }

    StringBuilder returnString = new StringBuilder("");

    for (AbstractShape currentShape : shapeList) {
      if (currentShape.getShapeType
              () == ShapeType.Rectangle) {
        returnString.append("Create rectangle " + currentShape.getName() + " with color "
                + ScriptView.colorConvert(currentShape.getColor(0))
                + " and corner at (" + currentShape.getX(0) + ", "
                + currentShape.getY(0) + "), width: "
                + currentShape.getWidth(0) + " and height: "
                + currentShape.getHeight(0) + "\n");
      }

      if (currentShape.getShapeType() == ShapeType.Ellipse) {
        returnString.append("Ellipse " + currentShape.getName());
        returnString.append("Create ellipse " + currentShape.getName() + " with color "
                + ScriptView.colorConvert(currentShape.getColor(0))
                + " and corner at (" + currentShape.getX(0) + ", "
                + currentShape.getY(0) + "), width: "
                + currentShape.getWidth(0) + " and height: "
                + currentShape.getHeight(0) + "\n");
      }

      for (int i = 0; i < currentShape.getFromXDestinationSize(); i++) {
        returnString.append(currentShape.getName() + " moves from ("
                + currentShape.getFromXDestination(i) + ", " + currentShape.getFromYDestination(i)
                + ") to (" + currentShape.getMoveXDestination(i) + ", "
                + currentShape.getMoveYDestination(i) + ") from time t = "
                + currentShape.getMotionMoveStartTime(i) + " to t = "
                + currentShape.getMotionMoveEndTime(i)
                + "\n");

      }
      for (int i = 0; i < currentShape.getFromYDestinationSize(); i++) {
        returnString.append(currentShape.getName() + " moves from ("
                + currentShape.getFromXDestination(i) + ", " + currentShape.getFromYDestination(i)
                + ") to (" + currentShape.getMoveXDestination(i) + ", "
                + currentShape.getMoveYDestination(i) + ") from time t = "
                + currentShape.getMotionMoveStartTime(i) + " to t = "
                + currentShape.getMotionMoveEndTime(i)
                + "\n");
      }

      for (int k = 0; k < currentShape.getWidthDestinationSize(); k++) {
        returnString.append(currentShape.getName() + " resizes from Width: "
                + currentShape.getFromWidthDestination(k) + ", Height: "
                + currentShape.getFromHeightDestination(k)
                + " to Width: " + currentShape.getWidthDestination(k) + ", Height: "
                + currentShape.getHeightDestination(k) + ") from time t = "
                + currentShape.getMotionResizeStartTime(k) + " to t = "
                + currentShape.getMotionResizeEndtime(k)
                + "\n");
      }

      for (int l = 0; l < currentShape.getHeightDestinationSize(); l++) {
        returnString.append(currentShape.getName() + " resizes from Width: "
                + currentShape.getFromWidthDestination(l) + ", Height: "
                + currentShape.getFromHeightDestination(l)
                + " to Width: " + currentShape.getWidthDestination(l) + ", Height: "
                + currentShape.getHeightDestination(l) + ") from time t = "
                + currentShape.getMotionResizeStartTime(l) + " to t = "
                + currentShape.getMotionResizeEndtime(l)
                + "\n");
      }
      for (int m = 0; m < currentShape.getColorSize(); m++) {
        if (((currentShape.getFromRColor(m) < currentShape.getRColorDestination(m))
                || (currentShape.getFromRColor(m) < currentShape.getRColorDestination(m)))
                && ((currentShape.getFromBColor(m) < currentShape.getBColorDestination(m))
                || (currentShape.getFromBColor(m) > currentShape.getBColorDestination(m)))
                && ((currentShape.getFromGColor(m) < currentShape.getGColorDestination(m))
                || (currentShape.getFromGColor(m) > currentShape.getGColorDestination(m)))) {
          returnString.append(currentShape.getName() + " changes color from ( "
                  + currentShape.getFromRColor(m) + ", " + currentShape.getFromGColor(m) + ", "
                  + currentShape.getFromBColor(m)
                  + " to (" + currentShape.getRColorDestination(m) + ", "
                  + currentShape.getGColorDestination(m) + ", "
                  + currentShape.getBColorDestination(m) + ") from time t = "
                  + currentShape.getColorStartTime(m) + " to t = "
                  + currentShape.getColorEndTime(m)
                  + "\n");
        }


      }


    }

    returnString.setLength(returnString.length()-1);
    return returnString;
}

}