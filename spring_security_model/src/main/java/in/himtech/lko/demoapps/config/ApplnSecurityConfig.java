package in.himtech.lko.demoapps.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class ApplnSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder pwdEncoder;

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * -- Method No 1 --
		 * JDBC Authentication: the below code will use schema.sql to create schema
		 * It uses method "usersByUsernameQuery" and "authoritiesByUsernameQuery" 
		 * to query the user credetinal and authorities.
		 */
		auth.jdbcAuthentication().passwordEncoder(pwdEncoder).dataSource(dataSource)
				.usersByUsernameQuery("select username, password, enabled from users where username =  ?")
				.authoritiesByUsernameQuery("select username, authority from authorities where username = ?");
	}

	private String getEncodedPwd(String string) {
		String pwd = "himserver";
		return pwdEncoder.encode(pwd);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin")
				.hasRole("ADMIN")
			.antMatchers("/user")
				.hasAnyRole("ADMIN", "USER")
			.antMatchers("/all")
				.permitAll()
			.antMatchers("/**")
				.permitAll()
			.and()
			.formLogin();
	}
}
