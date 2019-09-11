package in.himtech.lko.medicalstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplnConfig {

	@Bean
	public PasswordEncoder getPasswordEncoder() {
//		return new BCryptPasswordEncoder(8);
		return NoOpPasswordEncoder.getInstance();
	}
}
