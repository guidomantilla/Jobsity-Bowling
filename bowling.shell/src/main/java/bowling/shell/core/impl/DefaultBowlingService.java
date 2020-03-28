package bowling.shell.core.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bowling.shell.core.BowlingService;
import bowling.shell.core.manager.BowlingBallFileParser;
import bowling.shell.core.manager.BowlingReportBuilder;
import bowling.shell.core.manager.BowlingScoreCalculator;
import bowling.shell.core.manager.BowlingSheetCreator;
import bowling.shell.exception.BowlingException;
import bowling.shell.model.Ball;
import bowling.shell.model.Sheet;

@Service
public class DefaultBowlingService implements BowlingService {

	private static Logger LOGGER = LoggerFactory.getLogger(DefaultBowlingService.class);

	@Autowired
	private BowlingReportBuilder bowlingReportBuilder;

	@Autowired
	private BowlingScoreCalculator bowlingScoreCalculator;

	@Autowired
	private BowlingSheetCreator bowlingSheetCreator;

	@Autowired
	private BowlingBallFileParser bowlingBallFileParser;

	@Override
	public String score(String fileInPath) {

		String message = "";
		try {

			List<Ball> ballList = bowlingBallFileParser.parseFile(fileInPath);

			Sheet bowlingSheet = bowlingSheetCreator.createSheet(ballList);

			bowlingScoreCalculator.calculateScore(bowlingSheet);

			List<String> scoreReport = bowlingReportBuilder.buildReport(bowlingSheet);
			scoreReport.forEach(line -> System.out.println(line));

			message = "Bowling Score Displayed";

		} catch (IOException | BowlingException e) {
			message = e.getMessage();
			LOGGER.error(message, e);
		}

		return message;
	}
}
