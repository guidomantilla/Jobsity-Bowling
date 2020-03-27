package bowling.shell.core.manager;

import java.util.HashMap;
import java.util.Map;

import bowling.shell.model.FrameChance;

public class FrameManager {

	private Map<String, Integer> bowlerFrameCounter;

	public FrameManager() {
		bowlerFrameCounter = new HashMap<>();
	}

	public void frameChance(FrameChance frameChance) {

		if (!bowlerFrameCounter.containsKey(frameChance.getName())) {

			bowlerFrameCounter.put(frameChance.getName(), 1);

		} else if (bowlerFrameCounter.get(frameChance.getName()) == 1) {

			bowlerFrameCounter.put(frameChance.getName(), 2);

		} else {
// paila
		}
	}
}
