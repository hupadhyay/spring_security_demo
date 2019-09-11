package in.himtech.lko.medicalstore.config;

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
		 * This commentted code is used for in-memory authentication with the given user
		 * credentials.
		 */
//		auth.inMemoryAuthentication()
//			.passwordEncoder(pwdEncoder)
//				.withUser("himanshu").password(getEncodedPwd("himanshu")).roles("USER")
//			.and()
//				.withUser("hrishik").password(getEncodedPwd("hrishik")).roles("ADMIN"); 

		/*
		 * JDBC Authentication: the below code will create default schema and insert two
		 * user details.
		 */
//		auth.jdbcAuthentication()
//			.passwordEncoder(pwdEncoder)
//			.dataSource(dataSource).withDefaultSchema()
//				.withUser(User.withUsername("himanshu").password("himserver").roles("USER"))
//				.withUser(User.withUsername("hrishik").password("himserver").roles("ADMIN"));
		
		/*
		 * JDBC Authentication: the below code will use schema.sql to create schema and data.sql to insert users details. and insert two
		 * user details.
		 */
		auth.jdbcAuthentication()
			.passwordEncoder(pwdEncoder)
			.dataSource(dataSource)
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
			.antMatchers("/health")
				.hasRole("ADMIN")
			.antMatchers("/medicine")
				.hasAnyRole("ADMIN", "USER")
			.anyRequest().permitAll()
			.antMatchers("/**")
				.permitAll()
			.and()
			.formLogin();
	}
}
