package in.himtech.lko.demoapps.model;

public class UserInfo {
	
	private String username;
	
	private String password;
	
	private String role;
	
	private int enabled;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return String.format("UserInfo [username=%s, password=%s, enabled=%s]", username, password, enabled);
	} 

}
