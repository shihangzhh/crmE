package xynu.shihang.exception;

public class OAException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

private String message;
	
	public OAException(String message) {
		super(message);
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
