package in.himtech.lko.demoapps.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.himtech.lko.demoapps.model.RefreshToken;

@Repository
public class RefreshTokenDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean saveRefreshToken(RefreshToken token) {
		String sqlStmt = "insert into ref_token (token, username, millies) values (?, ?, ?)";
		try {
			int val = jdbcTemplate.update(sqlStmt,
					new Object[] { token.getToken(), token.getUserName(), token.getDate() });

			return (val == 1);
		} catch (DataAccessException exp) {
			return false;
		}
	}

	public RefreshToken findRefreshToken(String token) {
		String sqlStmt = "select token, username, millies from ref_token where token =  ?";
		
		try {
			return jdbcTemplate.queryForObject(sqlStmt, new Object[] {token}, (rs, rowNum) ->{
				RefreshToken refToken = new RefreshToken();
				refToken.setToken(token);
				refToken.setUserName(rs.getString("username"));
				refToken.setDate(rs.getLong("millies"));
				return refToken;
			});
		} catch (DataAccessException exp) {
			return null;
		}
	}
}
