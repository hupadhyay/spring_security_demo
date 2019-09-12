package in.himtech.lko.demoapps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecurityDemoApplication implements CommandLineRunner{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PasswordEncoder pwdEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SecurityDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String credential = pwdEncoder.encode("himserver");
		
		String sql = "INSERT INTO users (username, password, enabled) VALUES ('himanshu', '" + credential +"', true)";
		int value = jdbcTemplate.update(sql);
		if(value > 0) {
			System.out.println("User Credential inserted successfully!");
		}
		
		String sql1 = "INSERT INTO users (username, password, enabled) VALUES ('hrishik', '" + credential +"', true)";
		value = jdbcTemplate.update(sql1);
		if(value > 0) {
			System.out.println("User Credential inserted successfully!");
		}
		
		String sql2 = "INSERT INTO authorities (username, authority) VALUES ('himanshu', 'ROLE_USER')";
		value = jdbcTemplate.update(sql2);
		if(value > 0) {
			System.out.println("User Credential inserted successfully!");
		}
		
		String sql3 = "INSERT INTO authorities (username, authority) VALUES ('himanshu', 'ROLE_USER')";
		value = jdbcTemplate.update(sql3);
		if(value > 0) {
			System.out.println("User Credential inserted successfully!");
		}
	}

}
