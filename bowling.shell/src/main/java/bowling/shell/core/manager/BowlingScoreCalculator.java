package bowling.shell.core.manager;

import bowling.shell.exception.BowlingException;
import bowling.shell.model.Sheet;

public interface BowlingScoreCalculator {

	void calculateScore(Sheet bowlingSheet) throws BowlingException;
}
