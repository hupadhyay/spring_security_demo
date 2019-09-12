package in.himtech.lko.demoapps.cntl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	@GetMapping("/admin")
	public String greetAdmin() {
		return "Namaste Admin";
	}
	
	@GetMapping("/user")
	public String greetUser() {
		return "Good Morning User!";
	}
	
	@GetMapping("/all")
	public String greetAllUsers() {
		return "Good Morning to all Users!";
	}

}
