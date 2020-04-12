import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.view.PlaybackAnimationView;

import static org.junit.Assert.assertEquals;


/**
 * This class represents the testing class for PlaybackAnimationView. This mostly consists of
 * checking the internal methods for changes to the view, such as pause, play, rewind, enable loop,
 * disable loop, increase speed, and decrease speed. These are mostly boolean or double changes and
 * don't have user input, so there is no need to check for invalid input.
 */
public class PlaybackAnimationViewTest {

  PlaybackAnimationView testPlayback;
  AnimationModelImpl testModel;

  @Before
  public void setup() {
    testModel = new AnimationModelImpl();
    testPlayback = new PlaybackAnimationView(testModel, 50);
  }

  @Test
  public void testConstructor() {

    assertEquals(true, testPlayback.getPause());
    assertEquals(false, testPlayback.getPlay());
    assertEquals(false, testPlayback.getRewind());
    assertEquals(true, testPlayback.getLoop());
    assertEquals(20.0, testPlayback.getFrameDelay(), .1);

  }

  @Test
  public void testPlay() {

    testPlayback.play();

    assertEquals(false, testPlayback.getPause());
    assertEquals(true, testPlayback.getPlay());
    assertEquals(false, testPlayback.getRewind());
    assertEquals(true, testPlayback.getLoop());
    assertEquals(20.0, testPlayback.getFrameDelay(), .1);

  }

  @Test
  public void testPause() {
    testPlayback.play();

    assertEquals(false, testPlayback.getPause());
    assertEquals(true, testPlayback.getPlay());
    assertEquals(false, testPlayback.getRewind());
    assertEquals(true, testPlayback.getLoop());
    assertEquals(20.0, testPlayback.getFrameDelay(), .1);

    testPlayback.pause();

    assertEquals(true, testPlayback.getPause());
    assertEquals(false, testPlayback.getPlay());
    assertEquals(false, testPlayback.getRewind());
    assertEquals(true, testPlayback.getLoop());
    assertEquals(20.0, testPlayback.getFrameDelay(), .1);

  }

  @Test
  public void testRewind() {
    testPlayback.play();

    assertEquals(false, testPlayback.getPause());
    assertEquals(true, testPlayback.getPlay());
    assertEquals(false, testPlayback.getRewind());
    assertEquals(true, testPlayback.getLoop());
    assertEquals(20.0, testPlayback.getFrameDelay(), .1);

    testPlayback.rewind();

    assertEquals(false, testPlayback.getPause());
    assertEquals(false, testPlayback.getPlay());
    assertEquals(true, testPlayback.getRewind());
    assertEquals(true, testPlayback.getLoop());
    assertEquals(20.0, testPlayback.getFrameDelay(), .1);

  }

  @Test
  public void testloop() {
    testPlayback.play();

    assertEquals(false, testPlayback.getPause());
    assertEquals(true, testPlayback.getPlay());
    assertEquals(false, testPlayback.getRewind());
    assertEquals(true, testPlayback.getLoop());
    assertEquals(20.0, testPlayback.getFrameDelay(), .1);

    testPlayback.disableLoop();

    assertEquals(false, testPlayback.getPause());
    assertEquals(true, testPlayback.getPlay());
    assertEquals(false, testPlayback.getRewind());
    assertEquals(false, testPlayback.getLoop());
    assertEquals(20.0, testPlayback.getFrameDelay(), .1);

    testPlayback.enableLoop();

    assertEquals(false, testPlayback.getPause());
    assertEquals(true, testPlayback.getPlay());
    assertEquals(false, testPlayback.getRewind());
    assertEquals(true, testPlayback.getLoop());
    assertEquals(20.0, testPlayback.getFrameDelay(), .1);

  }

  @Test
  public void testFrameDelay() {
    testPlayback.play();

    assertEquals(false, testPlayback.getPause());
    assertEquals(true, testPlayback.getPlay());
    assertEquals(false, testPlayback.getRewind());
    assertEquals(true, testPlayback.getLoop());
    assertEquals(20.0, testPlayback.getFrameDelay(), .1);

    testPlayback.increaseSpeed();

    assertEquals(false, testPlayback.getPause());
    assertEquals(true, testPlayback.getPlay());
    assertEquals(false, testPlayback.getRewind());
    assertEquals(true, testPlayback.getLoop());
    assertEquals(18.0, testPlayback.getFrameDelay(), .1);

    testPlayback.decreaseSpeed();

    assertEquals(false, testPlayback.getPause());
    assertEquals(true, testPlayback.getPlay());
    assertEquals(false, testPlayback.getRewind());
    assertEquals(true, testPlayback.getLoop());
    assertEquals(19.8, testPlayback.getFrameDelay(), .1);

  }

}
