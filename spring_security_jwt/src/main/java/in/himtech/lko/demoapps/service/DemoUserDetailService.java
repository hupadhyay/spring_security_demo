package in.himtech.lko.demoapps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.himtech.lko.demoapps.dao.UserInfoDao;
import in.himtech.lko.demoapps.model.UserDetailInfo;
import in.himtech.lko.demoapps.model.UserInfo;

@Service
public class DemoUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserInfoDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo =  userDao.getUserDetailsByUsername(username);
		
		UserDetails userDetail = new UserDetailInfo(userInfo);
		return userDetail;
	}

}
