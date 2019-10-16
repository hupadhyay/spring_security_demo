package in.himtech.lko.demoapps.cntl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import in.himtech.lko.demoapps.jwt.JwtRequest;
import in.himtech.lko.demoapps.jwt.JwtResponse;
import in.himtech.lko.demoapps.jwt.JwtTokenUtil;
import in.himtech.lko.demoapps.service.RefreshTokenService;

@RestController
@CrossOrigin
public class JWTSecurityController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private RefreshTokenService tokenService;

	@GetMapping("/greet/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String getAdminInfo() {
		return "You are an Admin!";
	}

	@GetMapping("/greet/user")
	@PreAuthorize("hasRole('USER')")
	public String getUserInfo() {
		return "You are an User";
	}
	
	@GetMapping("/greet/userAdmin")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public String getAdminUserInfo() {
		return "You are an User/Admin";
	}
	
	@GetMapping("/greet/anonymous")
	@PreAuthorize("hasRole('ANONYMOUS')")
	public String getAnonymousUser() {
		return "You are an Anonymous User";
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@PostMapping(value = "/token/refresh")
	public ResponseEntity<?> getAccessTokenFromRefresh(@RequestHeader HttpHeaders headers) throws Exception {
		String refToken = headers.get("ref_token").get(0);
		String token = tokenService.validateAndGetAccessToken(refToken);
		
		if(StringUtils.isNotBlank(token)) {
			return ResponseEntity.ok(new JwtResponse(token));
		}
			
		return ResponseEntity.badRequest().body("Refresh Token is not valid.");
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
