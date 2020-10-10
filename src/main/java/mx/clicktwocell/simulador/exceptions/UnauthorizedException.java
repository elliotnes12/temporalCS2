package mx.clicktwocell.simulador.exceptions;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = -8698555590596986199L;

	public UnauthorizedException() {
		super();
	}

	public UnauthorizedException(String message) {
		super(message);
	}

}
