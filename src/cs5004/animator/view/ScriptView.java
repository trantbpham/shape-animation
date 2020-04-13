package cs5004.animator.view;

import java.awt.Color;

/**
 * ScriptView is the interface for text based output views such as TextAnimationView and SVG
 *     AnimationView. The methods they share are colorConvert and printScript which returns a
 *     textual output of the model's information.
 */
public interface ScriptView {

  /**
   * Convert color class to proper SVG color format string.
   */
  static String colorConvert(Color color) {
    String convertedString = "rgb(" + color.getRed() + "," + color.getGreen() + ","
            + color.getBlue() + ")";
    return convertedString;
  }

  /**
   * Use string builder to create text file by accessing the hashmap of the shapes and get its
   * info.
   */
  StringBuilder printScript();
}
