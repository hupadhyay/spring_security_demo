package in.himtech.lko.demoapps.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;

	public String getUsernameFromToken(String jwtToken) {
		Claims claims = getClaimsFromToken(jwtToken);
		return claims.getSubject();
	}

	public Date getExpirationDateFromToken(String jwtToken) {
		Claims claims = getClaimsFromToken(jwtToken);
		return claims.getExpiration();
	}

	private Claims getClaimsFromToken(String jwtToken) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken).getBody();
	}

	public boolean validateToken(String jwtToken, UserDetails userDetails) {
		final String userName = getUsernameFromToken(jwtToken);
		return userName.equals(userDetails.getUsername()) && getExpirationDateFromToken(jwtToken).before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

}
