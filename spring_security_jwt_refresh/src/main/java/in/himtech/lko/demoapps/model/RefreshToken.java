package in.himtech.lko.demoapps.model;

public class RefreshToken {

	private String token;
	
	private String userName;
	
	private long date;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return String.format("RefreshToken [token=%s, userName=%s, date=%s]", token, userName, date);
	}
	
	
}
