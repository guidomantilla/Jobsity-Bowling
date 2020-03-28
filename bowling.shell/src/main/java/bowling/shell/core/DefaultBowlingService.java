package bowling.shell.core;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bowling.shell.core.manager.BowlingBallFileParser;
import bowling.shell.core.manager.BowlingSheetCreator;
import bowling.shell.exception.BowlingException;
import bowling.shell.model.Ball;
import bowling.shell.model.Bowler;
import bowling.shell.model.Frame;
import bowling.shell.model.Sheet;

@Service
public class DefaultBowlingService implements BowlingService {

	private static Logger LOGGER = LoggerFactory.getLogger(DefaultBowlingService.class);

	@Autowired
	private BowlingSheetCreator bowlingSheetCreator;

	@Autowired
	private BowlingBallFileParser bowlingBallFileParser;

	@Override
	public String score(String fileInPath, String fileOutPath) {

		String message = "";
		try {

			List<Ball> ballList = bowlingBallFileParser.parseFile(fileInPath);
			Sheet bowlingSheet = bowlingSheetCreator.createSheet(ballList);

			for (String name : bowlingSheet.keySet()) {
				System.out.println(name);

				Bowler bowler = bowlingSheet.get(name);

				for (Frame frame : bowler.getFrameArray()) {

					if (Objects.isNull(frame)) {
						continue;
					}

					String score1 = "";
					String score2 = "";
					String score3 = "";
					if (!Objects.isNull(frame.getBall01())) {
						score1 = frame.getBall01().getScore();
					}

					if (!Objects.isNull(frame.getBall02())) {
						score2 = frame.getBall02().getScore();
					}

					if (!Objects.isNull(frame.getBall03())) {
						score3 = frame.getBall03().getScore();
					}

					System.out.println("\t" + score1 + " " + score2 + " " + score3);
				}
			}

			for (String name : bowlingSheet.keySet()) {

			}

//			for (String name : bowlingSheet.keySet()) {
//
//				if (bowlingSheet.get(name).getFrameIndex() != 10) {
//					throw new BowlingException(
//							String.format("Invalid Sheet -  Bowler %s", name) + ": The bowler does not have 10 frames.");
//				}
//			}
		} catch (IOException | BowlingException e) {
			message = e.getMessage();
			LOGGER.error(message, e);
		}

		return message;
	}
}
