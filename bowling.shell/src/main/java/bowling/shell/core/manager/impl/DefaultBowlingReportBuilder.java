package bowling.shell.core.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import bowling.shell.core.manager.BowlingReportBuilder;
import bowling.shell.exception.BowlingException;
import bowling.shell.model.Frame;
import bowling.shell.model.Sheet;
import bowling.shell.util.BowlingUtil;

@Component
public class DefaultBowlingReportBuilder implements BowlingReportBuilder {

	private final int LABEL_COLUMN_WIDTH = 10;
	private final int FRAME_COLUMN_WIDTH = 9;
	private final int BALL_COLUMN_WIDTH = 3;

	/**
	 * This method builds the report
	 * 
	 * @param bowlingSheet
	 * @return
	 * @throws BowlingException
	 */
	public List<String> buildReport(Sheet bowlingSheet) throws BowlingException {

		List<String> scoreReport = new ArrayList<>();

		String frameHeaderLine = buildFrameHeaderLine();
		scoreReport.add(frameHeaderLine);

		for (String name : bowlingSheet.keySet()) {

			String player = StringUtils.rightPad(" " + name, 100);
			scoreReport.add(player);

			Frame[] frameArray = bowlingSheet.get(name).getFrameArray();

			String framePinfallsLine = StringUtils.rightPad("  Pinfalls", LABEL_COLUMN_WIDTH);
			String frameScoreLine = StringUtils.rightPad("  Score", LABEL_COLUMN_WIDTH);

			for (Frame frame : frameArray) {

				if (Objects.isNull(frame)) {
					continue;
				}
				framePinfallsLine += StringUtils.leftPad(buildBallPinfallsLine(frame), FRAME_COLUMN_WIDTH);
				frameScoreLine += StringUtils.leftPad("" + (frame.getScore()), FRAME_COLUMN_WIDTH);
			}

			scoreReport.add(framePinfallsLine);
			scoreReport.add(frameScoreLine);
			scoreReport.add("");
		}
		return scoreReport;
	}

	/**
	 * This method build each ball info
	 * 
	 * @param frame
	 * @return
	 */
	private String buildBallPinfallsLine(Frame frame) {

		String ballPinfallsLine = "";
		String score = "";

		if (!Objects.isNull(frame.getBall01())) {

			score = frame.getBall01().getScore();
			if (Objects.equals("10", score)) {
				score = "X";
			}
			ballPinfallsLine += StringUtils.leftPad(score, BALL_COLUMN_WIDTH);
		}

		if (!Objects.isNull(frame.getBall02())) {

			score = frame.getBall02().getScore();
			if (BowlingUtil.isSpare(frame)) {
				score = "/";
			}
			ballPinfallsLine += StringUtils.leftPad(score, BALL_COLUMN_WIDTH);
		}

		if (!Objects.isNull(frame.getBall03())) {

			score = frame.getBall03().getScore();
			ballPinfallsLine += StringUtils.leftPad(score, BALL_COLUMN_WIDTH);
		}

		return ballPinfallsLine;
	}

	/**
	 * This method builds the Frame Header Line
	 * 
	 * @return
	 */
	private String buildFrameHeaderLine() {

		String frameHeaderLine = StringUtils.rightPad("Frame", LABEL_COLUMN_WIDTH);
		for (int i = 0; i < 10; i++) {
			frameHeaderLine += StringUtils.leftPad("" + (i + 1), FRAME_COLUMN_WIDTH);
		}

		return frameHeaderLine;
	}
}
