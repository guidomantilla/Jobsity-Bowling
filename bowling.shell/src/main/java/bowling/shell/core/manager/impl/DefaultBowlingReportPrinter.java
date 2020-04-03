package bowling.shell.core.manager.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import bowling.shell.core.manager.BowlingReportPrinter;

@Component
public class DefaultBowlingReportPrinter implements BowlingReportPrinter {

	@Override
	public void printReport(List<String> scoreReport) {
		scoreReport.forEach(line -> System.out.println(line));
	}
}
