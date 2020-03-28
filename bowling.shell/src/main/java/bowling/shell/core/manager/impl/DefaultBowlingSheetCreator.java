package bowling.shell.core.manager.impl;

import static bowling.shell.util.BowlingUtil.canHaveExtraBall;
import static bowling.shell.util.BowlingUtil.existsCurrentFrame;
import static bowling.shell.util.BowlingUtil.isFrameValid;
import static bowling.shell.util.BowlingUtil.isSpare;
import static bowling.shell.util.BowlingUtil.isStrike;
import static bowling.shell.util.BowlingUtil.retrieveScoreInt;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import bowling.shell.core.manager.BowlingSheetCreator;
import bowling.shell.exception.BowlingException;
import bowling.shell.model.Ball;
import bowling.shell.model.Bowler;
import bowling.shell.model.Frame;
import bowling.shell.model.Sheet;

@Component
public class DefaultBowlingSheetCreator implements BowlingSheetCreator {
	/**
	 * This method create the bowling score sheet
	 * 
	 * @param ballList
	 * @return
	 * @throws BowlingException
	 */
	public Sheet calculateScore(List<Ball> ballList) throws BowlingException {

		Sheet bowlingSheet = createSheet(ballList);

		for (String name : bowlingSheet.keySet()) {

			int acum = 0;
			Frame[] frameArray = bowlingSheet.get(name).getFrameArray();
			for (int i = 0; i < frameArray.length; i++) {

				if (Objects.isNull(frameArray[i])) {
					continue;
				}

				Frame frame = frameArray[i];
				int score = acum + retrieveScoreInt(frame.getBall01()) + retrieveScoreInt(frame.getBall02());

				if (i < 10) {

					Frame nextFrame = frameArray[i + 1];

					if (isStrike(frame)) {

						if (isStrike(nextFrame)) {

							Frame nextFramePlusOne = frameArray[i + 2];
							score += retrieveScoreInt(nextFrame.getBall01())
									+ retrieveScoreInt(nextFramePlusOne.getBall01());
						} else {

							score += retrieveScoreInt(nextFrame.getBall01()) + retrieveScoreInt(nextFrame.getBall02());
						}
					}

					if (isSpare(frame)) {
						score += retrieveScoreInt(nextFrame.getBall01());
					}

				}

				if (i == 10) {
					score += retrieveScoreInt(frame.getBall03());
				}

				frame.setScore(score);
				acum = score;
			}
		}

		return bowlingSheet;
	}

	/**
	 * 
	 * @param ballList
	 * @return
	 * @throws BowlingException
	 */
	private Sheet createSheet(List<Ball> ballList) throws BowlingException {

		Sheet bowlingSheet = new Sheet();

		for (Ball currentBall : ballList) {

			validateScore(currentBall);

			Bowler bowler = addBowler(currentBall.getName(), bowlingSheet);

			int currentFrameIndex = bowler.getFrameIndex();
			Frame[] frameArray = bowler.getFrameArray();

			Frame frame = frameArray[currentFrameIndex];
			if (!existsCurrentFrame(frame)) {

				frame = new Frame(currentBall);

				if (currentFrameIndex < 10 && isStrike(frame)) {
					bowler.setFrameIndex(currentFrameIndex + 1);
				}

				frameArray[currentFrameIndex] = frame;

			} else {

				if (Objects.isNull(frame.getBall02())) {

					frame.setBall02(currentBall);

					if (currentFrameIndex < 10) {
						validateScore(frame);
						bowler.setFrameIndex(currentFrameIndex + 1);
					}
				}

				if (currentFrameIndex == 10 && canHaveExtraBall(frame)) {

					frame.setBall03(currentBall);
				}
			}
		}

		return bowlingSheet;
	}

	/**
	 * 
	 * @param ball
	 * @throws BowlingException
	 */
	private void validateScore(Ball ball) throws BowlingException {

		if (retrieveScoreInt(ball) > 10) {
			throw new BowlingException(String.format("Invalid Sheet -  Bowler %s", ball.getName())
					+ ": The bowler ball have more than 10 pins knockdown.");
		}
	}

	/**
	 * 
	 * @param frame
	 * @throws BowlingException
	 */
	private void validateScore(Frame frame) throws BowlingException {

		if (!isFrameValid(frame)) {
			throw new BowlingException(String.format("Invalid Sheet -  Bowler %s", frame.getBall01().getName())
					+ ": The bowler frame have more than 10 pins knockdown.");
		}
	}

	/**
	 * This method adds a new Bowler to the sheet
	 * 
	 * @param name
	 * @param bowlingSheet
	 * @return
	 */
	private Bowler addBowler(String name, Sheet bowlingSheet) {

		if (!bowlingSheet.containsKey(name)) {

			bowlingSheet.put(name, new Bowler(name));
		}

		return bowlingSheet.get(name);
	}
}
