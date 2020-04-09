import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.SvgAnimationView;

import static org.junit.Assert.assertEquals;

/**
 * This class represents the testing class for SVG view. All methods, constructors, and illegal
 * method calls are tested in this class.
 */
public class SvgViewTest {


  AnimationModelImpl testAnimationModel;
  FileReader inputStream;

  /**
   * Test for SVG to see if it's print out properly.
   */

  @Test
  public void testView() throws FileNotFoundException {

    FileReader inputStream = new FileReader("toh-3.txt");
    AnimationModelImpl.AnimationModelBuilder builder =
            new AnimationModelImpl.AnimationModelBuilder();
    AnimationModelImpl testAnimationModel = AnimationReader.parseFile(inputStream, builder);
    SvgAnimationView sgvView = new SvgAnimationView(testAnimationModel, 10);

    assertEquals("<svg width=\"410\" height=\"220\" viewBox=\"145 50 410 220\" " +
            "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "\n" +
            "<rect id=\"disk3\" x=\"145.0\" y=\"240.0\" width=\"110.0\" height=\"30.0\" " +
            "fill=\"rgb(11,45,175)\" visibility=\"visible\" >\n" +
            "\n" +
            "        <animate attributeType=\"xml\" begin=\"12100.0ms\" dur=\"13100.0ms\" " +
            "attributeName=\"x\" from=\"145.0\" to=\"445.0\" fill=\"freeze\" />\n" +
            "        <animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"12100.0ms\" " +
            "attributeName=\"y\" from=\"240.0\" to=\"240.0\" fill=\"freeze\" />\n" +
            "        <animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"12100.0ms\" " +
            "attributeName=\"y\" from=\"240.0\" to=\"50.0\" fill=\"freeze\" />\n" +
            "        <animate attributeType=\"xml\" begin=\"13100.0ms\" dur=\"13200.0ms\" " +
            "attributeName=\"y\" from=\"50.0\" to=\"240.0\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "\n" +
            "<rect id=\"disk1\" x=\"190.0\" y=\"180.0\" width=\"20.0\" height=\"30.0\" " +
            "fill=\"rgb(0,49,90)\" visibility=\"visible\" >\n" +
            "\n" +
            "        <animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"3500.0ms\" " +
            "attributeName=\"x\" from=\"190.0\" to=\"490.0\" fill=\"freeze\" />\n" +
            "        <animate attributeType=\"xml\" begin=\"4600.0ms\" dur=\"4700.0ms\" " +
            "attributeName=\"x\" from=\"490.0\" to=\"340.0\" fill=\"freeze\" />\n" +
            "        <animate attributeType=\"xml\" begin=\"8900.0ms\" dur=\"9900.0ms\" " +
            "attributeName=\"x\" from=\"340.0\" to=\"190.0\" fill=\"freeze\" />\n" +
            "        <animate attributeType=\"xml\" begin=\"11000.0ms\" dur=\"11100.0ms\" " +
            "attributeName=\"x\" from=\"190.0\" to=\"490.0\" fill=\"freeze\" />\n" +
            "        <animate attributeType=\"xml\" begin=\"3500.0ms\" dur=\"3600.0ms\" " +
            "attributeName=\"y\" from=\"50.0\" to=\"240.0\" fill=\"freeze\" />\n" +
            "        <animate attributeType=\"xml\" begin=\"4700.0ms\" dur=\"5700.0ms\" " +
            "attributeName=\"y\" from=\"50.0\" to=\"210.0\" fill=\"freeze\" />\n" +
            "        <animate attributeType=\"xml\" begin=\"9900.0ms\" dur=\"10000.0ms\" " +
            "attributeName=\"y\" from=\"50.0\" to=\"240.0\" fill=\"freeze\" />\n" +
            "        <animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"11000.0ms\" " +
            "attributeName=\"y\" from=\"240.0\" to=\"240.0\" fill=\"freeze\" />\n" +
            "        <animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"11000.0ms\" " +
            "attributeName=\"y\" from=\"240.0\" to=\"50.0\" fill=\"freeze\" />\n" +
            "        <animate attributeType=\"xml\" begin=\"11100.0ms\" dur=\"12100.0ms\" " +
            "attributeName=\"y\" from=\"50.0\" to=\"180.0\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "\n" +
            "<rect id=\"disk2\" x=\"167.0\" y=\"210.0\" width=\"65.0\" height=\"30.0\" " +
            "fill=\"rgb(6,247,41)\" visibility=\"visible\" >\n" +
            "\n" +
            "        <animate attributeType=\"xml\" begin=\"5700.0ms\" dur=\"6700.0ms\" " +
            "attributeName=\"x\" from=\"167.0\" to=\"317.0\" fill=\"freeze\" />\n" +
            "        <animate attributeType=\"xml\" begin=\"7800.0ms\" dur=\"7900.0ms\" " +
            "attributeName=\"x\" from=\"317.0\" to=\"467.0\" fill=\"freeze\" />\n" +
            "        <animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"5700.0ms\" " +
            "attributeName=\"y\" from=\"210.0\" to=\"210.0\" fill=\"freeze\" />\n" +
            "        <animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"5700.0ms\" " +
            "attributeName=\"y\" from=\"210.0\" to=\"50.0\" fill=\"freeze\" />\n" +
            "        <animate attributeType=\"xml\" begin=\"6700.0ms\" dur=\"6800.0ms\" " +
            "attributeName=\"y\" from=\"50.0\" to=\"240.0\" fill=\"freeze\" />\n" +
            "        <animate attributeType=\"xml\" begin=\"7900.0ms\" dur=\"8900.0ms\" " +
            "attributeName=\"y\" from=\"50.0\" to=\"210.0\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "\n" +
            "</svg>", sgvView.printSGV().toString());

  }


}
