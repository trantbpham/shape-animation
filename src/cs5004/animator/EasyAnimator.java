package cs5004.animator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.PlaybackAnimationView;
import cs5004.animator.view.SvgAnimationView;
import cs5004.animator.view.TextAnimationView;
import cs5004.animator.view.VisualAnimationView;


/**
 * This class implements the main for the views and allows the user to select the view, set the
 *     speed, input files, and output files for the animation.
 */
public final class EasyAnimator {

  /**
   * Parses command line arguments from user input, calls on builder to create the model from input
   *     files and calls views to build themselves using. Uses swtich statements to determine which
   *     views to active.
   *
   * @param args command line arguments for main to parse
   */
  public static void main(String[] args) throws IOException {
    FileReader inputStream = null;
    FileWriter outputStream = null;

    String inFile = "default";
    String view = "default";
    String outFile = "System.out";
    int speed = 1;
    int arguments = args.length;
    int i;

    if (arguments % 2 != 0) {
      JOptionPane.showMessageDialog(null, "Arguments must be paired");
      return;
    }

    for (i = 0; i < arguments; i += 2) {
      switch (args[i]) {
        case "-in":
          if (i + 1 == arguments) {
            JOptionPane.showMessageDialog(null, "Missing -in argument");
          }
          inFile = args[i + 1];
          break;
        case "-view":
          view = args[i + 1];
          break;
        case "-speed":
          speed = Integer.parseInt(args[i + 1]);
          break;
        case "-out":
          outFile = args[i + 1];
          break;
        default:
          JOptionPane.showMessageDialog(null, "Incorrect keyword usage, " +
                  "please only use -in, -view, -out, " +
                  "and -speed followed by their respective arguments.");
          return;
      }
    }

    if (inFile.equals("default") || view.equals("default")) {
      JOptionPane.showMessageDialog(null, "Must have inputs for" +
              " -view and -in.");
    }

    try {
      inputStream = new FileReader(inFile);
      AnimationModelImpl.AnimationModelBuilder builder =
              new AnimationModelImpl.AnimationModelBuilder();
      AnimationModelImpl model = AnimationReader.parseFile(inputStream, builder);

      switch (view) {
        case "text":
          TextAnimationView textView = new TextAnimationView(model);
          System.out.print(textView.animationHistoryToString());
          File outputFile = new File(outFile);
          outputStream = new FileWriter(outputFile);
          outputStream.write(textView.animationHistoryToString().toString());
          break;
        case "svg":
          SvgAnimationView svgView = new SvgAnimationView(model, speed);
          System.out.print(svgView.printSGV());
          File outputFileSGV = new File(outFile);
          outputStream = new FileWriter(outputFileSGV);
          outputStream.write(svgView.printSGV().toString());
          break;
        case "visual":
          VisualAnimationView visualView = new VisualAnimationView(model, speed);
          break;
        case "playback":
          PlaybackAnimationView playbackView = new PlaybackAnimationView(model, speed);
          break;
        default:
          JOptionPane.showMessageDialog(null, "Invalid view selected, " +
                  "please only use text, svg, or visual.");
          return;
      }
    } catch (FileNotFoundException e) {
      JOptionPane.showMessageDialog(null, "FileNotFoundException: " +
              "File name could not be found, double check filename input and " +
              "make sure file exists");
      return;
    } finally {
      if (inputStream != null) {
        inputStream.close();
      }
      if (outputStream != null) {
        outputStream.flush();
        outputStream.close();

      }
    }
  }
}