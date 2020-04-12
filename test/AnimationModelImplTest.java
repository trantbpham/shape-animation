import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class represents the testing class for AnimationModelImplTest. All methods, constructors,
 * and illegal method calls are tested in this class.
 */
public class AnimationModelImplTest {

  AnimationModel testAnimationModel;


  @Before
  public void setup() {
    testAnimationModel = new AnimationModelImpl();
  }

  @Test
  public void testConstructor() {

    assertEquals("{}\n"
            + "[]", testAnimationModel.toString());
  }

  @Test
  public void testAddShape() {

    testAnimationModel.addShape("testRectangle", "rectangle", 10, 10,
            5, 5, .5, .5, .5, 3, 10);
    testAnimationModel.addShape("testRectangle2", "rectangle", 10,
            10, 5, 5, .5, .5, .5, 3, 10);

    assertEquals("{testRectangle2, testRectangle}\n" +
            "[Create rectangle testRectangle with color java.awt.Color[r=128,g=128,b=128]) " +
            "and corner at (10.0, 10.0), width: 5.0 and height: 5.0\n" +
            ", Create rectangle testRectangle2 with color java.awt.Color[r=128,g=128,b=128]) " +
            "and corner at (10.0, 10.0), width: 5.0 and height: 5.0\n" +
            "]", testAnimationModel.toString());

  }

  @Test
  public void testAddMove() {
    testAnimationModel.addShape("testRectangle", "rectangle", 10,
            10, 5, 5, .5, .5, .5, 3, 10);
    testAnimationModel.addMove("testRectangle", 10, 10,
            20, 20, 5, 8);

    assertEquals("{testRectangle}\n" +
                    "[Create rectangle testRectangle with" +
                    " color java.awt.Color[r=128,g=128,b=128])" +
                    " and corner at (10.0, 10.0), width: 5.0 and height: 5.0\n" +
                    ", testRectangle moves from (10.0, 10.0) to (20.0, 20.0) from time " +
                    "t = 5 to t = 8\n" +
                    "]",
            testAnimationModel.toString());
    testAnimationModel.addMove("testRectangle", 20, 20, 10,
            10, 9, 10);

    assertEquals("{testRectangle}\n" +
                    "[Create rectangle testRectangle with color " +
                    "java.awt.Color[r=128,g=128,b=128]) " +
                    "and corner at (10.0, 10.0), width: 5.0 and height: 5.0\n" +
                    ", testRectangle moves from (10.0, 10.0) to (20.0, 20.0) " +
                    "]from time t = 5 to t = 8\n" +
                    ", testRectangle moves from (20.0, 20.0) to (10.0, 10.0) " +
                    "from time t = 9 to t = 10\n" +
                    "]",
            testAnimationModel.toString());

  }

  @Test
  public void testIllegaladdMove() {

    boolean testvalue = false;

    testAnimationModel.addShape("testRectangle", "rectangle", 10,
            10, 5, 5, .5, .5, .5, 3, 10);

    try {
      testAnimationModel.addMove("nottestRectangle", 10, 10, 50,
              50, 4, 8);
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException: Invalid name, "
                      + "that shape doesn't exist in this model.It can be added with addShape().",
              i.toString());
      assertFalse(testvalue);
    }

    try {
      testAnimationModel.addMove("testRectangle", 10, 10, 50,
              50, -1, 8);
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException: "
              + "time cannot be negative.", i.toString());
      assertFalse(testvalue);
    }

    try {
      testAnimationModel.addMove("testRectangle", 10, 10,
              50, 50, 5, 2);
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException: endTime must be greater "
              + "than startTime.", i.toString());
      assertFalse(testvalue);
    }
  }

  @Test
  public void testAddSizeChange() {

    testAnimationModel.addShape("testRectangle", "rectangle", 10,
            10, 5.0, 5.0, .5, .5, .5, 3, 10);
    testAnimationModel.addSizeChange("testRectangle", 5.0, 5.0, 5,
            8, 20, 20);

    assertEquals("{testRectangle}\n" +
            "[Create rectangle testRectangle with color java.awt.Color[r=128,g=128,b=128]) " +
            "and corner at (10.0, 10.0), width: 5.0 and height: 5.0\n" +
            ", testRectangle scales from Width: 5.0, Height: 5.0 " +
            "to Width: 20.0, Height: 20.0 from time t = 5 to t = 8\n" +
            "]", testAnimationModel.toString());
    testAnimationModel.addSizeChange("testRectangle", 20.0, 20.0, 9,
            10, 5.6, 2.3);

    assertEquals("{testRectangle}\n" +
            "[Create rectangle testRectangle with color java.awt.Color[r=128,g=128,b=128]) and" +
            " corner at (10.0, 10.0), width: 5.0 and height: 5.0\n" +
            ", testRectangle scales from Width: 5.0, Height: 5.0 to Width: 20.0, Height: 20.0 " +
            "from time t = 5 to t = 8\n" +
            ", testRectangle scales from Width: 20.0, Height: 20.0 to Width: 5.6, Height: 2.3" +
            " from time t = 9 to t = 10\n" +
            "]", testAnimationModel.toString());

  }

  @Test
  public void testIllegalAddSizeChange() {

    boolean testvalue = false;

    testAnimationModel.addShape("testRectangle", "rectangle", 10, 10,
            5, 5, .5, .5, .5, 3, 10);

    try {
      testAnimationModel.addSizeChange("nottestRectangle", 5, 5, 4,
              8, -1, 50);
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException: Invalid name, "
                      + "that shape doesn't exist in this model.It can be added with addShape().",
              i.toString());
      assertFalse(testvalue);
    }

    //this error is accessed in AbstractShape, so if this test passes, then IllegalSizeChange from
    //shapeTest applies to the tests below and repeating them would be redunant.
    try {
      testAnimationModel.addSizeChange("testRectangle", 5, 5, 4,
              8, -1, 50);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException: width and height must both "
              + "be greater than 0.", i.toString());
      assertFalse(testvalue);
    }

  }

  @Test
  public void testAddColorChange() {

    testAnimationModel.addShape("testRectangle", "rectangle", 10,
            10, 5, 5, .5, .5, .5, 3, 10);
    testAnimationModel.addColorChange("testRectangle", .8, .2,
            .3, .8, .2, .3, 4, 10);

    assertEquals("{testRectangle}\n" +
            "[Create rectangle testRectangle with color java.awt.Color[r=128,g=128,b=128])" +
            " and corner at (10.0, 10.0), width: 5.0 and height: 5.0\n" +
            ", testRectangle changes color from (0.5, 0.5, 0.5) to" +
            " (0.8, 0.2, 0.3) from time t = 4 to t = 10\n" +
            "]", testAnimationModel.toString());
    testAnimationModel.addColorChange("testRectangle", 0.5, 0.5, 0.5,
            0.0, 0.0, 0.0, 1, 3);

    assertEquals("{testRectangle}\n" +
            "[Create rectangle testRectangle with color java.awt.Color[r=128,g=128,b=128])" +
            " and corner at (10.0, 10.0), width: 5.0 and height: 5.0\n" +
            ", testRectangle changes color from (0.5, 0.5, 0.5) " +
            "to (0.8, 0.2, 0.3) from time t = 4 to t = 10\n" +
            ", testRectangle changes color from (0.5, 0.5, 0.5)" +
            " to (0.0, 0.0, 0.0) from time t = 1 to t = 3\n" +
            "]", testAnimationModel.toString());

  }

  @Test
  public void testIllegalAddColorChange() {

    boolean testvalue = false;

    testAnimationModel.addShape("testRectangle", "rectangle", 10,
            10, 5, 5, .5, .5, .5, 3, 10);

    try {
      testAnimationModel.addColorChange("nottestRectangle", 0.5, 0.5, 0.5,
              0.0, 0.0, 0.0, 1, 3);
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException: Invalid name, "
                      + "that shape doesn't exist in this model.It can be added with addShape().",
              i.toString());
      assertFalse(testvalue);
    }

    //this error is accessed in AbstractShape, so if this test passes, then IllegalColorChange from
    //shapeTest applies to the tests below and repeating them would be redunant.
    try {
      testAnimationModel.addColorChange("testRectangle", 0.5, 0.5, 0.5,
              -.1, 0.0, 0.0, 1, 3);
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException: " +
              "Motion ending color value must be 0-255", i.toString());
      assertFalse(testvalue);
    }
  }

  @Test
  public void testGetShapeParameters() {

    assertEquals("[]", testAnimationModel.getShapeParameters(1).toString());

    testAnimationModel.addShape("testRectangle", "rectangle",
            10, 10, 5, 5, .5, .5, .5,
            3, 10);

    assertEquals("[rectangle|10.00|10.00|5.00|5.00|-8355712|false]",
            testAnimationModel.getShapeParameters(1).toString());

    testAnimationModel.addShape("testRectangle2", "rectangle",
            0, 10, 5, 5, .5, .5, .5,
            3, 20);

    assertEquals("[rectangle|10.00|10.00|5.00|5.00|-8355712|false," +
                    " rectangle|0.00|10.00|5.00|5.00|-8355712|false]",
            testAnimationModel.getShapeParameters(1).toString());

    assertEquals("[rectangle|0.00|10.00|5.00|5.00|-8355712|false]",
            testAnimationModel.getShapeParameters(11).toString());


  }

  @Test
  public void testIllegalGetShapeParameters() {

    assertEquals("[]", testAnimationModel.getShapeParameters(1).toString());

    boolean testvalue = false;

    testAnimationModel.addShape("testRectangle", "rectangle", 10,
            10, 5, 5, .5, .5, .5, 3, 10);

    try {
      assertEquals("[]", testAnimationModel.getShapeParameters(-1).toString());
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException: Time cannot be less than 0",
              i.toString());
      assertFalse(testvalue);
    }

  }

  @Test
  public void testGetTextAnimation() {

    assertEquals("", testAnimationModel.getTextAnimation());

    testAnimationModel.addShape("testRectangle", "rectangle",
            10, 10, 5, 5, .5,
            .5, .5, 3, 10);

    assertEquals("Name: testRectangle\n" +
            "Type: rectangle\n" +
            "Lower Left corner: (10.0,10.0), Width: 5.0, Height: 5.0, Color: (0.5,0.5,0.5)\n" +
            "Appears at t = 0\n" +
            "Disappears at t = 10\n" +
            "\n" +
            "\n" +
            "Create rectangle testRectangle with color java.awt.Color[r=128,g=128,b=128])" +
            " and corner at (10.0, 10.0), width: 5.0 and height: 5.0\n" +
            "\n", testAnimationModel.getTextAnimation());

    testAnimationModel.addMove("testRectangle", 10, 10, 20,
            20, 5, 8);

    assertEquals("Name: testRectangle\n" +
                    "Type: rectangle\n" +
                    "Lower Left corner: (10.0,10.0), Width: 5.0, " +
                    "Height: 5.0, Color: (0.5,0.5,0.5)\n" +
                    "Appears at t = 0\n" +
                    "Disappears at t = 10\n" +
                    "\n" +
                    "\n" +
                    "Create rectangle testRectangle with color " +
                    "java.awt.Color[r=128,g=128,b=128]) " +
                    "and corner at (10.0, 10.0), width: 5.0 and height: 5.0\n" +
                    "\n" +
                    "testRectangle moves from (10.0, 10.0) to " +
                    "(20.0, 20.0) from time t = 5 to t = 8\n" +
                    "\n",
            testAnimationModel.getTextAnimation());

    testAnimationModel.addShape("testEllipse", "ellipse",
            10, 10, 5, 5, .5,
            .5, .5, 3, 10);

    assertEquals("Name: testEllipse\n" +
                    "Type: ellipse\n" +
                    "Center: (10.0,10.0) X Radius: 5.0 Y Radius: 5.0 Color: (0.5,0.5,0.5)\n" +
                    "Appears at t = 0\n" +
                    "Disappears at t = 10\n" +
                    "\n" +
                    "Name: testRectangle\n" +
                    "Type: rectangle\n" +
                    "Lower Left corner: (10.0,10.0), Width: 5.0," +
                    " Height: 5.0, Color: (0.5,0.5,0.5)\n" +
                    "Appears at t = 0\n" +
                    "Disappears at t = 10\n" +
                    "\n" +
                    "\n" +
                    "Create rectangle testRectangle with color " +
                    "java.awt.Color[r=128,g=128,b=128]) and " +
                    "corner at (10.0, 10.0), width: 5.0 and height: 5.0\n" +
                    "\n" +
                    "testRectangle moves from (10.0, 10.0) to " +
                    "(20.0, 20.0) from time t = 5 to t = 8\n" +
                    "\n" +
                    "Create ellipse testEllipse with color " +
                    "java.awt.Color[r=128,g=128,b=128]) and " +
                    "corner at (10.0, 10.0), width: 5.0 and height: 5.0\n" +
                    "\n",
            testAnimationModel.getTextAnimation());

    testAnimationModel.addMove("testEllipse", 10, 10, 20,
            20, 5, 8);

    assertEquals("Name: testEllipse\n" +
                    "Type: ellipse\n" +
                    "Center: (10.0,10.0) X Radius: 5.0 Y Radius: 5.0 Color: (0.5,0.5,0.5)\n" +
                    "Appears at t = 0\n" +
                    "Disappears at t = 10\n" +
                    "\n" +
                    "Name: testRectangle\n" +
                    "Type: rectangle\n" +
                    "Lower Left corner: (10.0,10.0), Width: 5.0, Height: 5.0, Color: " +
                    "(0.5,0.5,0.5)\n" +
                    "Appears at t = 0\n" +
                    "Disappears at t = 10\n" +
                    "\n" +
                    "\n" +
                    "Create rectangle testRectangle with color " +
                    "java.awt.Color[r=128,g=128,b=128]) " +
                    "and corner at (10.0, 10.0), width: 5.0 and height: 5.0\n" +
                    "\n" +
                    "testRectangle moves from (10.0, 10.0) to (20.0, 20.0) from" +
                    " time t = 5 to t = 8\n" +
                    "\n" +
                    "Create ellipse testEllipse with color java.awt.Color[r=128,g=128,b=128]) " +
                    "and corner at (10.0, 10.0), width: 5.0 and height: 5.0\n" +
                    "\n" +
                    "testEllipse moves from (10.0, 10.0) to (20.0, 20.0) from time " +
                    "t = 5 to t = 8\n" +
                    "\n",
            testAnimationModel.getTextAnimation());

  }

  /**
   * Testing if shape is removed properly from the animation.
   */
  @Test
  public void testRemoveShape() {
    testAnimationModel.addShape("testRectangle", "rectangle",
            10, 10, 5, 5, .5,
            .5, .5, 3, 10);
    testAnimationModel.addShape("testEllipse", "ellipse",
            10, 10, 5, 5, .5,
            .5, .5, 3, 10);
    System.out.print("\nbefore remove\n");
    System.out.print(testAnimationModel.getShapesNamInAnimation().toString() + "\n");
    assertEquals("testEllipse, testRectangle",
            testAnimationModel.getShapesNamInAnimation().toString());

    testAnimationModel.removeShape("testRectangle");
    System.out.print("\nafter remove\n");
    System.out.print(testAnimationModel.getShapesNamInAnimation().toString() + "\n");
    assertEquals("testEllipse", testAnimationModel.getShapesNamInAnimation().toString());



  }
}
