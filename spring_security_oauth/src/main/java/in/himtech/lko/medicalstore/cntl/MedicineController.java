package in.himtech.lko.medicalstore.cntl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicineController {
	
	@GetMapping("/health")
	public String getHealthInfo() {
		return "You are healthy!";
	}
	
	@GetMapping("/medicine")
	public String getMedicineInfo() {
		return "Good Medicine";
	}

}
