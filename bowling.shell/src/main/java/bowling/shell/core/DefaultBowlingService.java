package bowling.shell.core;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bowling.shell.core.manager.FileParser;
import bowling.shell.exception.BowlingException;
import bowling.shell.model.FrameChance;

@Service
public class DefaultBowlingService implements BowlingService {

	private static Logger LOGGER = LoggerFactory.getLogger(DefaultBowlingService.class);

	@Autowired
	private FileParser fileParser;

	@Override
	public String bowl(String fileInPath, String fileOutPath) {

		System.out.println(Calendar.getInstance().getTime() + " Starting bowling");

		String message = "";
		try {

			List<FrameChance> frameChanceList = fileParser.parseFile(fileInPath);
			for (FrameChance frameChance : frameChanceList) {
				System.out.println(frameChance.getName() + " " + frameChance.getScore());
			}

		} catch (IOException | BowlingException e) {
			message = e.getMessage();
			LOGGER.error(message, e);
		}

		System.out.println(Calendar.getInstance().getTime() + " Done Bowling");

		return message;
	}
}
