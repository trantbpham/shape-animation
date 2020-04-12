package cs5004.animator.view;

import java.awt.*;

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
