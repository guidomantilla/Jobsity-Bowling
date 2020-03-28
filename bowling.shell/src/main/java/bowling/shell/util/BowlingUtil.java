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

	public static boolean isStrike(Frame frame) {
		return isBallScoreValid(frame.getBall01()) && Objects.equals("10", frame.getBall01().getScore());
	}

	public static boolean isSpare(Frame frame) {

		int a = retrieveScoreInt(frame.getBall01());
		int b = retrieveScoreInt(frame.getBall02());

		if (a + b <= 10) {
			return true;
		}

		return false;
	}

	public static boolean canHaveExtraBall(Frame frame) {

		if (isStrike(frame)) {
			return true;
		}

		int a = retrieveScoreInt(frame.getBall01());
		int b = retrieveScoreInt(frame.getBall02());

		if (a + b == 10) {
			return true;
		}

		return false;
	}

	public static int retrieveScoreInt(Ball ball) {

		try {

			return Integer.parseInt(ball.getScore());

		} catch (NumberFormatException e) {

			return 0;
		}
	}
}
