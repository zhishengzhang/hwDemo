package zhang.util.exception;

@SuppressWarnings("serial")
public class DemoException extends Exception {

	public DemoException(String message) {
		super(message);
	}

	public DemoException(String type, String cause) {
		super(type, new Throwable(cause));
	}
	
}
