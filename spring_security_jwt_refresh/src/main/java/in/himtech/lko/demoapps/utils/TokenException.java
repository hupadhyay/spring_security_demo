package in.himtech.lko.demoapps.utils;

public class TokenException extends RuntimeException {

	private String message;
	
	public TokenException() {
		// TODO Auto-generated constructor stub
	}
	
	public TokenException(String message){
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}

	
}
