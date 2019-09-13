package in.himtech.lko.demoapps.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class ApplnSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder pwdEncoder;

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * -- Method No 1 --
		 * JDBC Authentication: the below code will use schema.sql to create schema
		 * It uses method "usersByUsernameQuery" and "authoritiesByUsernameQuery" 
		 * to query the user credential and authorities.
		 */
//		auth.jdbcAuthentication().passwordEncoder(pwdEncoder).dataSource(dataSource)
//				.usersByUsernameQuery("select username, password, enabled from users where username =  ?")
//				.authoritiesByUsernameQuery("select username, authority from authorities where username = ?");
		
		/*
		 * -- Method No 2 --
		 * JDBC Authentication: the below code will use UserDetail service to authenticate user. This method is 
		 * widely use in software industries to authenticate and authorize the the user.
		 */
		auth.userDetailsService(userDetailsService).passwordEncoder(pwdEncoder);
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
