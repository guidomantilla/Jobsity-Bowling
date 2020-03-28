package bowling.shell.core.manager.impl;

import static bowling.shell.util.BowlingUtil.isSpare;
import static bowling.shell.util.BowlingUtil.isStrike;
import static bowling.shell.util.BowlingUtil.retrieveScoreInt;

import java.util.Objects;

import org.springframework.stereotype.Component;

import bowling.shell.core.manager.BowlingScoreCalculator;
import bowling.shell.exception.BowlingException;
import bowling.shell.model.Frame;
import bowling.shell.model.Sheet;

@Component
public class DefaultBowlingScoreCalculator implements BowlingScoreCalculator {

	/**
	 * This method calculate the score
	 * 
	 * @param ballList
	 * @return
	 * @throws BowlingException
	 */
	@Override
	public void calculateScore(Sheet bowlingSheet) throws BowlingException {

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
	}
}
