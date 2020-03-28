package bowling.shell.manager;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import bowling.shell.core.manager.BowlingBallFileParser;
import bowling.shell.core.manager.BowlingReportBuilder;
import bowling.shell.core.manager.BowlingScoreCalculator;
import bowling.shell.core.manager.BowlingSheetCreator;
import bowling.shell.core.manager.impl.DefaultBowlingBallFileParser;
import bowling.shell.core.manager.impl.DefaultBowlingReportBuilder;
import bowling.shell.core.manager.impl.DefaultBowlingScoreCalculator;
import bowling.shell.core.manager.impl.DefaultBowlingSheetCreator;
import bowling.shell.exception.BowlingException;
import bowling.shell.model.Ball;
import bowling.shell.model.Sheet;

@RunWith(SpringRunner.class)
public class BowlingReportBuilderTest {

	@TestConfiguration
	static class TestContextConfiguration {

		@Bean
		public BowlingReportBuilder bowlingReportBuilder() {
			return new DefaultBowlingReportBuilder();
		}

		@Bean
		public BowlingScoreCalculator bowlingScoreCalculator() {
			return new DefaultBowlingScoreCalculator();
		}

		@Bean
		public BowlingSheetCreator bowlingSheetCreator() {
			return new DefaultBowlingSheetCreator();
		}

		@Bean
		public BowlingBallFileParser bowlingBallFileParser() {
			return new DefaultBowlingBallFileParser();
		}
	}

	@Autowired
	private BowlingReportBuilder bowlingReportBuilder;

	@Autowired
	private BowlingBallFileParser bowlingBallFileParser;

	@Autowired
	private BowlingSheetCreator bowlingSheetCreator;

	@Autowired
	private BowlingScoreCalculator bowlingScoreCalculator;

	@Test
	public void whenFileIsOK_then_True() throws BowlingException, IOException {

		String fileName = "UnitTestCaseOK01.txt";

		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		List<Ball> ballList = bowlingBallFileParser.parseFile(file.getAbsolutePath());

		Sheet sheet = bowlingSheetCreator.createSheet(ballList);

		bowlingScoreCalculator.calculateScore(sheet);

		List<String> scoreReport = bowlingReportBuilder.buildReport(sheet);

		assertTrue(!scoreReport.isEmpty());
	}
}
