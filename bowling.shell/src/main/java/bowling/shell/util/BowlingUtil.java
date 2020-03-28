package bowling.shell.util;

import java.util.Objects;

import bowling.shell.model.Ball;
import bowling.shell.model.Frame;

public class BowlingUtil {

	public static boolean existsCurrentFrame(Frame frame) {
		return !Objects.isNull(frame);
	}

	public static boolean isBallScoreValid(Ball ball) {
		return !Objects.isNull(ball) && !Objects.isNull(ball.getScore());
	}

	public static boolean isSpare(Frame frame) {
		return retrieveScoreInt(frame.getBall01()) != 10
				&& retrieveScoreInt(frame.getBall01()) + retrieveScoreInt(frame.getBall02()) == 10;
	}

	public static boolean isStrike(Frame frame) {

		return retrieveScoreInt(frame.getBall01()) == 10 && retrieveScoreInt(frame.getBall02()) == 0;
	}

	public static boolean isFrameValid(Frame frame) {

		int a = retrieveScoreInt(frame.getBall01());
		int b = retrieveScoreInt(frame.getBall02());

		if (a + b <= 10) {
			return true;
		}

		return false;
	}

	public static boolean canHaveExtraBall(Frame frame) {

		int a = retrieveScoreInt(frame.getBall01());
		int b = retrieveScoreInt(frame.getBall02());

		if (a == 10) {
			return true;
		}

		if (a + b == 10) {
			return true;
		}

		return false;
	}

	public static int retrieveScoreInt(Ball ball) {

		try {

			return Integer.parseInt(ball.getScore());

		} catch (NullPointerException | NumberFormatException e) {

			return 0;
		}
	}
}
