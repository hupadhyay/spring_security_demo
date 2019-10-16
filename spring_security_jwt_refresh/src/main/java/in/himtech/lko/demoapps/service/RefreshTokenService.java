package in.himtech.lko.demoapps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import in.himtech.lko.demoapps.dao.RefreshTokenDao;
import in.himtech.lko.demoapps.jwt.JwtTokenUtil;
import in.himtech.lko.demoapps.model.RefreshToken;

@Service
public class RefreshTokenService {

	@Autowired
	private RefreshTokenDao refTokenDao;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;


	public String validateAndGetAccessToken(String refToken) {
		RefreshToken refreshToken = refTokenDao.findRefreshToken(refToken);
		
		if(refreshToken !=null) {
			final UserDetails userDetails = userDetailsService.loadUserByUsername(refreshToken.getUserName());

			return jwtTokenUtil.generateToken(userDetails);

		}
		return null;
	}
}
