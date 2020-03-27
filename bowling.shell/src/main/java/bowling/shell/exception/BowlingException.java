package bowling.shell.exception;

public class BowlingException extends Throwable {

	private static final long serialVersionUID = 1048181152821701231L;

	public BowlingException() {
		super();
	}

	public BowlingException(String message) {
		super(message);
	}

	public BowlingException(Throwable throwable) {
		super(throwable);
	}

	public BowlingException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
