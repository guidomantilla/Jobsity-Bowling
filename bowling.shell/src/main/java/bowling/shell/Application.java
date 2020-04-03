package bowling.shell;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import bowling.shell.core.BowlingService;

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = { "bowling.shell" })
public class Application implements CommandLineRunner {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private BowlingService bowlingService;

	@Override
	public void run(String... args) throws Exception {

		String message = "";

		if (args.length != 1) {

			message = "ERROR: Too many parameters as input.";

		} else {

			String originPath = args[0];
			if (validatePath(originPath)) {

				message = bowlingService.score(originPath);

			} else {
				message = "ERROR: The path parameters may not be valid.";
			}
		}
		System.out.println(message);
	}

	private boolean validatePath(String path) {
		Path filePath = Paths.get(path);
		return !Files.isDirectory(filePath) && Files.exists(filePath);
	}
}
