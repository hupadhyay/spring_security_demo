package in.himtech.lko.demoapps.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.himtech.lko.demoapps.model.UserInfo;

@Repository
public class UserInfoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public UserInfo getUserDetailsByUsername(String username) {
		String sql = "SELECT u.username, u.password, u.enabled, a.authority FROM users u JOIN authorities a ON u.username=a.username and u.username=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {username}, (rs, rowNum) -> {
			UserInfo userInfo = new UserInfo();
			userInfo.setUsername(rs.getString("username"));
			userInfo.setPassword(rs.getString("password"));
			userInfo.setEnabled(rs.getInt("enabled"));
			userInfo.setRole(rs.getString("authority"));
			return userInfo;
		});
	}
	
}
