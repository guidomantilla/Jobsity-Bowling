package bowling.shell.core;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import bowling.shell.core.impl.DefaultBowlingService;
import bowling.shell.core.manager.BowlingBallFileParser;
import bowling.shell.core.manager.BowlingReportBuilder;
import bowling.shell.core.manager.BowlingScoreCalculator;
import bowling.shell.core.manager.BowlingSheetCreator;
import bowling.shell.core.manager.impl.DefaultBowlingBallFileParser;
import bowling.shell.core.manager.impl.DefaultBowlingReportBuilder;
import bowling.shell.core.manager.impl.DefaultBowlingScoreCalculator;
import bowling.shell.core.manager.impl.DefaultBowlingSheetCreator;
import bowling.shell.exception.BowlingException;

@RunWith(SpringRunner.class)
public class BowlingServiceTest {

	@TestConfiguration
	static class TestContextConfiguration {

		@Bean
		public BowlingService bowlingService() {
			return new DefaultBowlingService();
		}

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
	private BowlingService bowlingService;

	@Test
	public void whenTestCase01_then_True() throws BowlingException, IOException {

		String fileName = "UnitTestCaseOK01.txt";

		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		String message = bowlingService.score(file.getAbsolutePath());

		assertTrue(Objects.equals("Bowling Score Displayed", message));
	}

	@Test
	public void whenTestCase02_then_True() throws BowlingException, IOException {

		String fileName = "UnitTestCaseOK02.txt";

		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		String message = bowlingService.score(file.getAbsolutePath());

		assertTrue(Objects.equals("Bowling Score Displayed", message));
	}

	@Test
	public void whenTestCase03_then_True() throws BowlingException, IOException {

		String fileName = "UnitTestCaseOK03.txt";

		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		String message = bowlingService.score(file.getAbsolutePath());

		assertTrue(Objects.equals("Bowling Score Displayed", message));
	}
}
