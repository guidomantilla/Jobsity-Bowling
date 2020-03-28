package bowling.shell.model;

public class Bowler {

	private String name;

	private Frame[] frameArray;

	private int frameIndex;

	public Bowler() {
		this(null, null, 0);
	}

	public Bowler(String name) {
		this(name, new Frame[13], 1);
	}

	public Bowler(String name, Frame[] frameArray, int frameIndex) {
		this.name = name;
		this.frameArray = frameArray;
		this.frameIndex = frameIndex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Frame[] getFrameArray() {
		return frameArray;
	}

	public void setFrameArray(Frame[] frameArray) {
		this.frameArray = frameArray;
	}

	public int getFrameIndex() {
		return frameIndex;
	}

	public void setFrameIndex(int frameIndex) {
		this.frameIndex = frameIndex;
	}
}
