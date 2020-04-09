import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.util.AnimationReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class represents the testing class for AnimationModelImplTest. All methods, constructors,
 * and illegal method calls are tested in this class.
 */
public class AnimationModelImplBuilderTest {

  AnimationModel testAnimationModel;
  FileReader inputStream;

  @Test
  public void testBoundsAdded() throws FileNotFoundException {
    FileReader inputStream = new FileReader("testResources/testBounds1.txt");
    AnimationModelImpl.AnimationModelBuilder builder =
            new AnimationModelImpl.AnimationModelBuilder();
    AnimationModelImpl testAnimationModel = AnimationReader.parseFile(inputStream, builder);

    assertEquals(200, testAnimationModel.getLeftBound());
    assertEquals(70, testAnimationModel.getTopBound());
    assertEquals(360, testAnimationModel.getWidth());
    assertEquals(360, testAnimationModel.getHeight());

  }

  @Test
  public void testIllegalBoundsAdded() throws FileNotFoundException {
    AnimationModelImpl.AnimationModelBuilder builder
            = new AnimationModelImpl.AnimationModelBuilder();
    boolean testvalue = false;

    inputStream = new FileReader("testResources/testBoundsIllegal1.txt");

    try {
      AnimationModelImpl testAnimationModel = AnimationReader.parseFile(inputStream, builder);
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException: " +
              "Bounds must be positive integers", i.toString());
      assertFalse(testvalue);
    }

    inputStream = new FileReader("testResources/testBoundsIllegal2.txt");

    try {
      AnimationModelImpl testAnimationModel = AnimationReader.parseFile(inputStream, builder);
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException: " +
              "Bounds must be positive integers", i.toString());
      assertFalse(testvalue);
    }

    inputStream = new FileReader("testResources/testBoundsIllegal3.txt");

    try {
      AnimationModelImpl testAnimationModel = AnimationReader.parseFile(inputStream, builder);
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException: " +
              "Bounds must be positive integers", i.toString());
      assertFalse(testvalue);
    }

    inputStream = new FileReader("testResources/testBoundsIllegal4.txt");

    try {
      AnimationModelImpl testAnimationModel = AnimationReader.parseFile(inputStream, builder);
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException: " +
              "Bounds must be positive integers", i.toString());
      assertFalse(testvalue);
    }
  }

  @Test
  public void testDeclareShape() throws FileNotFoundException {
    inputStream = new FileReader("testResources/testDeclareShape.txt");
    AnimationModelImpl.AnimationModelBuilder builder
            = new AnimationModelImpl.AnimationModelBuilder();
    AnimationModelImpl testAnimationModel = AnimationReader.parseFile(inputStream, builder);

    assertEquals("{testrectangle, testellipse}\n" +
            "[]", testAnimationModel.toString());
    assertEquals("[rectangle|0.00|0.00|1.00|1.00|-16777216|false," +
                    " ellipse|0.00|0.00|1.00|1.00|-16777216|false]",
            testAnimationModel.getShapeParameters(0).toString());
    assertEquals("[rectangle|0.00|0.00|1.00|1.00|-16777216|false," +
                    " ellipse|0.00|0.00|1.00|1.00|-16777216|false]",
            testAnimationModel.getShapeParameters(10).toString());
    assertEquals("[rectangle|0.00|0.00|1.00|1.00|-16777216|false," +
                    " ellipse|0.00|0.00|1.00|1.00|-16777216|false]",
            testAnimationModel.getShapeParameters(10000).toString());
  }

  @Test
  public void testIllegalDeclareShape() throws FileNotFoundException {
    AnimationModelImpl.AnimationModelBuilder builder
            = new AnimationModelImpl.AnimationModelBuilder();
    boolean testvalue = false;

    inputStream = new FileReader("testResources/testIllegalDeclareShape.txt");

    try {
      AnimationModelImpl testAnimationModel = AnimationReader.parseFile(inputStream, builder);
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException: " +
              "Shape type must be rectangle or ellipse.", i.toString());
      assertFalse(testvalue);
    }

    inputStream = new FileReader("testResources/testIllegalDeclareShape2.txt");

    try {
      AnimationModelImpl testAnimationModel = AnimationReader.parseFile(inputStream, builder);
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException: " +
              "Shape type must be rectangle or ellipse.", i.toString());
      assertFalse(testvalue);
    }
  }

  @Test
  public void testAddMotion() throws FileNotFoundException {
    inputStream = new FileReader("testResources/testAddMotion.txt");
    AnimationModelImpl.AnimationModelBuilder builder
            = new AnimationModelImpl.AnimationModelBuilder();
    AnimationModelImpl testAnimationModel = AnimationReader.parseFile(inputStream, builder);

    //following tests check for motion, color, and scale changes
    assertEquals("[rectangle|200.00|200.00|50.00|100.00|-65536|true," +
                    " ellipse|440.00|70.00|120.00|60.00|-16776961|false]",
            testAnimationModel.getShapeParameters(1).toString());
    assertEquals("[rectangle|200.00|200.00|50.00|100.00|-65536|true," +
                    " ellipse|440.00|70.00|120.00|60.00|-16776961|false]",
            testAnimationModel.getShapeParameters(5).toString());
    assertEquals("[rectangle|200.00|200.00|50.00|100.00|-65536|true, " +
                    "ellipse|440.00|70.00|120.00|60.00|-16776961|true]",
            testAnimationModel.getShapeParameters(6).toString());
    assertEquals("[rectangle|250.00|250.00|50.00|100.00|-65536|true," +
                    " ellipse|393.33|146.67|120.00|60.00|-16776961|true]",
            testAnimationModel.getShapeParameters(30).toString());
    assertEquals("[rectangle|300.00|300.00|38.16|100.00|-65536|true, " +
                    "ellipse|370.00|335.00|120.00|60.00|-16756571|true]",
            testAnimationModel.getShapeParameters(60).toString());
    assertEquals("[rectangle|216.67|216.67|25.00|100.00|-65536|true, " +
                    "ellipse|440.00|370.00|120.00|60.00|-16711936|true]",
            testAnimationModel.getShapeParameters(95).toString());
  }

  @Test
  public void testIllegalAddMotion() throws FileNotFoundException {
    AnimationModelImpl.AnimationModelBuilder builder =
            new AnimationModelImpl.AnimationModelBuilder();
    boolean testvalue = false;

    inputStream = new FileReader("testResources/testIllegalAddMotion1.txt");

    try {
      AnimationModelImpl testAnimationModel = AnimationReader.parseFile(inputStream, builder);
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException:" +
              " Motion startTime must be >=0.", i.toString());
      assertFalse(testvalue);
    }

    inputStream = new FileReader("testResources/testIllegalAddMotion2.txt");

    try {
      AnimationModelImpl testAnimationModel = AnimationReader.parseFile(inputStream, builder);
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException:" +
              " Motion starting color value must be 0-255", i.toString());
      assertFalse(testvalue);
    }

    inputStream = new FileReader("testResources/testIllegalAddMotion4.txt");

    try {
      AnimationModelImpl testAnimationModel = AnimationReader.parseFile(inputStream, builder);
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException:" +
              " Motion starting color value must be 0-255", i.toString());
      assertFalse(testvalue);
    }

    inputStream = new FileReader("testResources/testIllegalAddMotion5.txt");

    try {
      AnimationModelImpl testAnimationModel = AnimationReader.parseFile(inputStream, builder);
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException:" +
              " Motion starting color value must be 0-255", i.toString());
      assertFalse(testvalue);
    }

    inputStream = new FileReader("testResources/testIllegalAddMotion3.txt");

    try {
      AnimationModelImpl testAnimationModel = AnimationReader.parseFile(inputStream, builder);
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException:" +
              " Motion ending color value must be 0-255", i.toString());
      assertFalse(testvalue);
    }

    inputStream = new FileReader("testResources/testIllegalAddMotion6.txt");

    try {
      AnimationModelImpl testAnimationModel = AnimationReader.parseFile(inputStream, builder);
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException:" +
              " Motion ending color value must be 0-255", i.toString());
      assertFalse(testvalue);
    }

    inputStream = new FileReader("testResources/testIllegalAddMotion7.txt");

    try {
      AnimationModelImpl testAnimationModel = AnimationReader.parseFile(inputStream, builder);
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException:" +
              " Motion ending color value must be 0-255", i.toString());
      assertFalse(testvalue);
    }

    inputStream = new FileReader("testResources/testIllegalAddMotion8.txt");

    try {
      AnimationModelImpl testAnimationModel = AnimationReader.parseFile(inputStream, builder);
      assertTrue(testvalue);
    } catch (IllegalArgumentException i) {
      assertEquals("java.lang.IllegalArgumentException:" +
              " starttime must be less than end time", i.toString());
      assertFalse(testvalue);
    }

  }


}
