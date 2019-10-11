package in.himtech.lko.demoapps.model;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailInfo implements UserDetails{
	
	private UserInfo userInfo;
	
	public UserDetailInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		GrantedAuthority ga = new SimpleGrantedAuthority(userInfo.getRole());
		return Arrays.asList(ga);
	}

	@Override
	public String getPassword() {
		return userInfo.getPassword();
	}

	@Override
	public String getUsername() {
		return userInfo.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return userInfo.getEnabled()>0;
	}

}
