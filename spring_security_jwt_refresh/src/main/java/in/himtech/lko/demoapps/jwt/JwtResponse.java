package in.himtech.lko.demoapps.jwt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class JwtResponse {

	private final String accessToken;
	
	private final String refreshToken;

	public JwtResponse(String accessToken) {
		this.accessToken = accessToken;
		this.refreshToken = null;
	}
	
	public JwtResponse(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	

	public String getAccessToken() {
		return this.accessToken;
	}
	
	public String getRefreshToken() {
		return refreshToken;
	}
}
