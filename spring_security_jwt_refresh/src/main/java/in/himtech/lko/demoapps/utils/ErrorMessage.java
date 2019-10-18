package in.himtech.lko.demoapps.utils;

public class ErrorMessage {
	private String message;
	private String uri;
	private String status;
	public ErrorMessage(String message2, String description, String status) {
		this.message = message2;
		this.uri = description;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return String.format("ErrorMessage [message=%s, uri=%s, status=%s]", message, uri, status);
	}

}
