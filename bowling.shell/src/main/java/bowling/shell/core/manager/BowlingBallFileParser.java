package bowling.shell.core.manager;

import static bowling.shell.util.StringUtil.validateStringDomainChars;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import bowling.shell.exception.BowlingException;
import bowling.shell.model.Ball;

@Component
public class BowlingBallFileParser {

	private final String NAME_CHARS_ACCEPTED = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private final String PIN_NUMBER_CHARS_ACCEPTED = "0123456789F";

	/**
	 * This method parses the file
	 * 
	 * @param fileInPath
	 * @return FrameChance list
	 * @throws IOException
	 * @throws BowlingException
	 */
	public List<Ball> parseFile(String fileInPath) throws IOException, BowlingException {

		List<Ball> chanceList = new ArrayList<>();

		List<String> lineList = Files.readAllLines(Paths.get(fileInPath));

		int lineNumber = 1;
		for (String line : lineList) {

			if (!validateFrameChanceFormat(line)) {
				throw new BowlingException(String.format("Invalid Format -  Line %d", lineNumber)
						+ ": TAB separator does not exist or exists more than one.");
			}

			String[] lineParsed = line.split("\t");
			if (!validateFrameChanceFields(lineParsed)) {
				throw new BowlingException(String.format("Invalid Format -  Line %d", lineNumber)
						+ ": Fields NAME y SCORE have invalid characters.");
			}

			if (!validateFrameChanceScore(lineParsed)) {
				throw new BowlingException(
						String.format("Invalid Format -  Line %d", lineNumber) + ": Field SCORE has an invalid value.");
			}

			chanceList.add(new Ball(lineParsed[0], lineParsed[1]));
			lineNumber++;
		}

		return chanceList;
	}

	/**
	 * This method validates that the field with the score is valid
	 * 
	 * @param lineParsed
	 * @return
	 */
	private boolean validateFrameChanceScore(String[] lineParsed) {

		try {

			if (Integer.parseInt(lineParsed[1]) > 10) {
				return false;
			}

		} catch (NumberFormatException e) {

			if (lineParsed[1].length() > 1) {
				return false;
			}
		}

		return true;
	}

	/**
	 * This method validates that each field in the file, conforms to it's
	 * expectations.
	 * 
	 * @param lineParsed
	 * @return
	 */
	private boolean validateFrameChanceFields(String[] lineParsed) {

		return validateStringDomainChars(lineParsed[0], NAME_CHARS_ACCEPTED)
				&& validateStringDomainChars(lineParsed[1], PIN_NUMBER_CHARS_ACCEPTED);
	}

	/**
	 * This method validates that each line has only one tab separator.
	 * 
	 * @param line
	 * @return
	 */
	private boolean validateFrameChanceFormat(String line) {

		return line.contains("\t") && line.lastIndexOf("\t") == line.indexOf("\t");
	}
}
