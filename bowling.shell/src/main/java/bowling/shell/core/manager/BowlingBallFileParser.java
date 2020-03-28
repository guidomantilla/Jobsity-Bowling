package bowling.shell.core.manager;

import java.io.IOException;
import java.util.List;

import bowling.shell.exception.BowlingException;
import bowling.shell.model.Ball;

public interface BowlingBallFileParser {

	List<Ball> parseFile(String fileInPath) throws IOException, BowlingException;
}
