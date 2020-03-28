package bowling.shell.core.manager;

import java.util.List;

import bowling.shell.exception.BowlingException;
import bowling.shell.model.Sheet;

public interface BowlingReportBuilder {

	List<String> buildReport(Sheet bowlingSheet) throws BowlingException;
}
