package bowling.shell;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;

import bowling.shell.core.BowlingService;

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = { "bowling.shell" })
@ShellComponent
public class Application {

	public static void main(String[] args) throws IOException {

		String[] disabledCommands = { "--spring.shell.command.quit.enabled=false" };
		String[] fullArgs = StringUtils.concatenateStringArrays(args, disabledCommands);
		SpringApplication.run(Application.class, fullArgs);
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 */

	@Autowired
	private BowlingService bowlingService;

	@ShellMethod(value = "This method bowl based on a list from a file.", key = "bowl")
	public String bowl(

			@ShellOption(value = { "-i",
					"--impl" }, defaultValue = "default", help = "This parameter defines the implementation type for the Job") String implementation,

			@ShellOption(value = { "-op",
					"--origin-path" }, help = "This parameter defines the origin path") String originPath,

			@ShellOption(value = { "-dp",
					"--destination-path" }, help = "This parameter defines the destination path") String destinationPath) {

		String message = "";
		if (validatePath(originPath) && validatePath(destinationPath)) {

			switch (implementation) {
			case "default":
				message = bowlingService.score(originPath, destinationPath);
				break;

			case "streams":
				// new StreamsWebCrawlerService().crawl(patternList, originPath,
				// destinationPath);
				break;

			default:
				message = "ERROR: The impl parameter may not be valid.";
				break;
			}

		} else {
			message = "ERROR: The path parameters may not be valid.";
		}

		return message;
	}

	@ShellMethod(value = "Force quit from the application", key = "quit")
	public String quit() {
		System.exit(0);
		return "";
	}

	private boolean validatePath(String path) {
		Path filePath = Paths.get(path);
		return !Files.isDirectory(filePath) && Files.exists(filePath);
	}
}
