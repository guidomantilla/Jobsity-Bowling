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
import bowling.shell.core.manager.impl.DefaultBowlingBallFileParser;
import bowling.shell.exception.BowlingException;
import bowling.shell.model.Ball;

@RunWith(SpringRunner.class)
public class BowlingBallFileParserTest {

	@TestConfiguration
	static class TestContextConfiguration {

		@Bean
		public BowlingBallFileParser bowlingBallFileParser() {
			return new DefaultBowlingBallFileParser();
		}
	}

	@Autowired
	private BowlingBallFileParser bowlingBallFileParser;

	@Test
	public void whenFileIsOK_then_True() throws IOException, BowlingException {

		String fileName = "UnitTestCaseOK01.txt";

		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		List<Ball> ballList = bowlingBallFileParser.parseFile(file.getAbsolutePath());

		assertTrue(!ballList.isEmpty());
	}

	@Test(expected = BowlingException.class)
	public void whenFileIsBAD_then_Exception() throws IOException, BowlingException {

		String fileName = "UnitTestCaseBAD01.txt";

		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		bowlingBallFileParser.parseFile(file.getAbsolutePath());
	}

}
