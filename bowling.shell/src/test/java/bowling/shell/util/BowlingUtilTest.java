package bowling.shell.util;

import static bowling.shell.util.BowlingUtil.canHaveExtraBall;
import static bowling.shell.util.BowlingUtil.existsCurrentFrame;
import static bowling.shell.util.BowlingUtil.isBallScoreValid;
import static bowling.shell.util.BowlingUtil.isFrameValid;
import static bowling.shell.util.BowlingUtil.isSpare;
import static bowling.shell.util.BowlingUtil.isStrike;
import static bowling.shell.util.BowlingUtil.retrieveScoreInt;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import bowling.shell.model.Ball;
import bowling.shell.model.Frame;

@RunWith(SpringRunner.class)
public class BowlingUtilTest {

	@Test
	public void whenBallAndScroreAreNotNull_then_True() {

		Ball ball = new Ball("Sample", "1");

		assertTrue(isBallScoreValid(ball));
	}

	@Test
	public void whenBallIsNotNullAndScroreIsNull_then_False() {

		Ball ball = new Ball();

		assertFalse(isBallScoreValid(ball));
	}

	@Test
	public void whenBallAndScroreAreNull_then_True() {

		assertFalse(isBallScoreValid(null));
	}

	@Test
	public void whenBallScroreIsNumber_then_True() {

		Ball ball = new Ball("Sample", "1");
		assertTrue(retrieveScoreInt(ball) == 1);
	}

	@Test
	public void whenBallScroreIsChar_then_False() {

		Ball ball = new Ball("Sample", "F");
		assertFalse(retrieveScoreInt(ball) == 'F');
	}

	@Test
	public void whenFrameIsNotNull_then_True() {

		assertTrue(existsCurrentFrame(new Frame()));
	}

	@Test
	public void whenFrameIsNull_then_False() {

		assertFalse(existsCurrentFrame(null));
	}

	@Test
	public void whenFrameBall01Is10AndBall02Is0_then_True() {

		assertTrue(isStrike(new Frame(new Ball("Sample", "10"), new Ball("Sample", "0"))));
	}

	@Test
	public void whenFrameBall01Is10AndBall02Is2_then_False() {

		assertFalse(isStrike(new Frame(new Ball("Sample", "10"), new Ball("Sample", "2"))));
	}

	@Test
	public void whenFrameBall01Is8AndBall02Is2_then_True() {

		assertTrue(isSpare(new Frame(new Ball("Sample", "8"), new Ball("Sample", "2"))));
	}

	@Test
	public void whenFrameBall01Is10AndBall02Is1_then_False() {

		assertFalse(isSpare(new Frame(new Ball("Sample", "10"), new Ball("Sample", "1"))));
	}

	@Test
	public void whenFrameBall01Is7AndBall02Is1_then_True() {

		assertTrue(isFrameValid(new Frame(new Ball("Sample", "7"), new Ball("Sample", "1"))));
	}

	@Test
	public void whenFrameBall01Is10AndBall02Is3_then_False() {

		assertFalse(isFrameValid(new Frame(new Ball("Sample", "10"), new Ball("Sample", "3"))));
	}

	@Test
	public void whenFrameBall01Is3AndBall02Is7_then_True() {

		assertTrue(canHaveExtraBall(new Frame(new Ball("Sample", "3"), new Ball("Sample", "7"))));
	}

	@Test
	public void whenFrameBall01Is1AndBall02Is3_then_False() {

		assertFalse(canHaveExtraBall(new Frame(new Ball("Sample", "1"), new Ball("Sample", "3"))));
	}
}
