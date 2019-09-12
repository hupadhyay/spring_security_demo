package in.himtech.lko.demoapps.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
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
		 * This commentted code is used for in-memory authentication with the given user
		 * credentials.
		 */
//		auth.inMemoryAuthentication()
//			.passwordEncoder(pwdEncoder)
//				.withUser("himanshu").password(getEncodedPwd("himserver")).roles("USER")
//			.and()
//				.withUser("hrishik").password(getEncodedPwd("himserver")).roles("ADMIN"); 

		/*
		 * JDBC Authentication: the below code will create default schema and insert two
		 * user details.
		 */
		auth.jdbcAuthentication()
			.passwordEncoder(pwdEncoder)
			.dataSource(dataSource).withDefaultSchema()
				.withUser(User.withUsername("himanshu").password(getEncodedPwd("himserver")).roles("USER"))
				.withUser(User.withUsername("hrishik").password(getEncodedPwd("himserver")).roles("ADMIN"));
		
	}

	private String getEncodedPwd(String pwd) {
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
