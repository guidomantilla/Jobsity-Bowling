package bowling.shell.model;

public class Ball {

	private String name;
	private String score;

	public Ball() {
		this(null, null);
	}

	public Ball(String name, String score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

}
