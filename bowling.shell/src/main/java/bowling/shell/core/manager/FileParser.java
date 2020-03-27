package bowling.shell.core.manager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import bowling.shell.exception.BowlingException;
import bowling.shell.model.FrameChance;
import bowling.shell.util.StringUtil;

@Component
public class FileParser {

	private final String NAME_CHARS_ACCEPTED = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private final String PIN_NUMBER_CHARS_ACCEPTED = "0123456789F";

	public List<FrameChance> parseFile(String fileInPath) throws IOException, BowlingException {

		List<FrameChance> frameChanceList = new ArrayList<>();

		List<String> lineList = Files.readAllLines(Paths.get(fileInPath));

		int lineNumber = 1;
		for (String line : lineList) {

			if (!validateFrameChanceFormat(line)) {
				throw new BowlingException(
						String.format("Formato Invalido -  Linea %d", lineNumber) + ": No existe separador TAB.");
			}

			String[] lineParsed = line.split("\t");
			if (!validateFrameChanceFields(lineParsed)) {
				throw new BowlingException(String.format("Formato Invalido -  Linea %d", lineNumber)
						+ ": Campos NAME y SCORE contienen caracteres invalidos.");
			}

			if (!validateFrameChanceScore(lineParsed)) {
				throw new BowlingException(String.format("Formato Invalido -  Linea %d", lineNumber)
						+ ": Campo SCORE tiene un valor invalido.");
			}

			frameChanceList.add(new FrameChance(lineParsed[0], lineParsed[1]));
			lineNumber++;
		}

		return frameChanceList;
	}

	private boolean validateFrameChanceScore(String[] lineParsed) {

		try {

			Integer.parseInt(lineParsed[1]);

		} catch (NumberFormatException e) {

			if (lineParsed[1].length() > 1) {
				return false;
			}
		}

		return true;
	}

	private boolean validateFrameChanceFields(String[] lineParsed) {

		return StringUtil.validateStringDomainChars(lineParsed[0], NAME_CHARS_ACCEPTED)
				&& StringUtil.validateStringDomainChars(lineParsed[1], PIN_NUMBER_CHARS_ACCEPTED);
	}

	private boolean validateFrameChanceFormat(String line) {

		return line.contains("\t") && line.lastIndexOf("\t") == line.indexOf("\t");
	}
}
