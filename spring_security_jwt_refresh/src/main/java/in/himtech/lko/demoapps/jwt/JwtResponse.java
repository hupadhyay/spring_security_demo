package in.himtech.lko.demoapps.jwt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class JwtResponse {

	private final String jwttoken;
	
	private final String refreshToken;

	public JwtResponse(String accessToken) {
		this.jwttoken = accessToken;
		this.refreshToken = null;
	}
	
	public JwtResponse(String accessToken, String refreshToken) {
		this.jwttoken = accessToken;
		this.refreshToken = refreshToken;
	}
	

	public String getToken() {
		return this.jwttoken;
	}
	
	public String getRefreshToken() {
		return refreshToken;
	}
}
