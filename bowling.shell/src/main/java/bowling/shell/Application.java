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

			@ShellOption(value = { "-op",
					"--origin-path" }, help = "This parameter defines the origin path") String originPath) {

		String message = "";
		if (validatePath(originPath)) {

			message = bowlingService.score(originPath);

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
