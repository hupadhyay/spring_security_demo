package in.himtech.lko.demoapps.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import in.himtech.lko.demoapps.dao.RefreshTokenDao;
import in.himtech.lko.demoapps.jwt.JwtTokenUtil;
import in.himtech.lko.demoapps.model.RefreshToken;
import in.himtech.lko.demoapps.utils.TokenException;

@Service
public class RefreshTokenService {

	@Autowired
	private RefreshTokenDao refTokenDao;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Value("${jwt.token.refresh.timeout}")
	private long refreshTokenValidity;

	public String validateAndGetAccessToken(String refToken) {
		RefreshToken refreshToken = refTokenDao.findRefreshToken(refToken);

		if (refreshToken != null && refreshToken.getDate() > System.currentTimeMillis()) {
			final UserDetails userDetails = userDetailsService.loadUserByUsername(refreshToken.getUserName());

			return jwtTokenUtil.generateToken(userDetails);

		}
		throw new TokenException("Refresh Token Expire");
	}

	public String createAndPersistRefreshToken(String username) {
		String strUUID = UUID.randomUUID().toString();
		String strToken = Base64Utils.encodeToString(strUUID.getBytes());
		RefreshToken refToken = new RefreshToken();
		refToken.setDate(System.currentTimeMillis() + (refreshTokenValidity * 60 * 1000));
		refToken.setToken(strToken);
		refToken.setUserName(username);
		boolean bool = refTokenDao.saveRefreshToken(refToken);
		return bool ? strToken : null;
	}
}
