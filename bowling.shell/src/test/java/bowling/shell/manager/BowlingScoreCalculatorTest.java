package bowling.shell.manager;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import bowling.shell.core.manager.BowlingBallFileParser;
import bowling.shell.core.manager.BowlingScoreCalculator;
import bowling.shell.core.manager.BowlingSheetCreator;
import bowling.shell.core.manager.impl.DefaultBowlingBallFileParser;
import bowling.shell.core.manager.impl.DefaultBowlingScoreCalculator;
import bowling.shell.core.manager.impl.DefaultBowlingSheetCreator;
import bowling.shell.exception.BowlingException;
import bowling.shell.model.Ball;
import bowling.shell.model.Frame;
import bowling.shell.model.Sheet;

@RunWith(SpringRunner.class)
public class BowlingScoreCalculatorTest {

	@TestConfiguration
	static class TestContextConfiguration {

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

		for (String name : sheet.keySet()) {

			Frame[] frameArray = sheet.get(name).getFrameArray();
			for (int i = 0; i < frameArray.length; i++) {

				if (Objects.isNull(frameArray[i])) {
					continue;
				}

				Frame frame = frameArray[i];
				assertNotNull(frame.getScore());
				break;
			}
		}
	}
}
