package bowling.shell.model;

public class Frame {

	private Ball ball01;
	private Ball ball02;
	private Ball ball03;

	private Integer score;

	public Frame() {
		this(null, null);
	}

	public Frame(Ball ball01) {
		this(ball01, null);
	}

	public Frame(Ball ball01, Ball ball02) {
		this.ball01 = ball01;
		this.ball02 = ball02;
	}

	public Frame(Ball ball01, Ball ball02, Ball ball03) {
		this.ball01 = ball01;
		this.ball02 = ball02;
		this.ball03 = ball03;
	}

	public Ball getBall01() {
		return ball01;
	}

	public void setBall01(Ball ball01) {
		this.ball01 = ball01;
	}

	public Ball getBall02() {
		return ball02;
	}

	public void setBall02(Ball ball02) {
		this.ball02 = ball02;
	}

	public Ball getBall03() {
		return ball03;
	}

	public void setBall03(Ball ball03) {
		this.ball03 = ball03;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
