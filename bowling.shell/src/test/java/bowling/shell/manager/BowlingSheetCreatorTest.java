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
import bowling.shell.core.manager.BowlingSheetCreator;
import bowling.shell.core.manager.impl.DefaultBowlingBallFileParser;
import bowling.shell.core.manager.impl.DefaultBowlingSheetCreator;
import bowling.shell.exception.BowlingException;
import bowling.shell.model.Ball;
import bowling.shell.model.Sheet;

@RunWith(SpringRunner.class)
public class BowlingSheetCreatorTest {

	@TestConfiguration
	static class TestContextConfiguration {

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

	@Test
	public void whenFileIsOK_then_True() throws BowlingException, IOException {

		String fileName = "UnitTestCaseOK01.txt";

		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		List<Ball> ballList = bowlingBallFileParser.parseFile(file.getAbsolutePath());

		Sheet sheet = bowlingSheetCreator.createSheet(ballList);

		assertTrue(sheet.keySet().size() == 2);
	}

	@Test(expected = BowlingException.class)
	public void whenFileIsBAD_then_Exception() throws BowlingException, IOException {

		String fileName = "UnitTestCaseBAD02.txt";

		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		List<Ball> ballList = bowlingBallFileParser.parseFile(file.getAbsolutePath());

		bowlingSheetCreator.createSheet(ballList);
	}
}
