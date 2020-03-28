package bowling.shell.core.manager;

import java.util.List;

import bowling.shell.exception.BowlingException;
import bowling.shell.model.Ball;
import bowling.shell.model.Sheet;

public interface BowlingSheetCreator {

	Sheet createSheet(List<Ball> ballList) throws BowlingException;
}
