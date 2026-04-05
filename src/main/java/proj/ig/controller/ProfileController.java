package proj.ig.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proj.ig.entity.User;
import proj.ig.entity.UserProfileResponse;
import proj.ig.repos.UserRepository;

@RestController
@RequestMapping("/api/user")
public class ProfileController {
	
	@Autowired UserRepository userRepository;
	
	
	@GetMapping("/profile")
	public ResponseEntity<?> getUserProfile(Principal principal) {
	    // Principal contains the email from your JWT
	    User user = userRepository.findByUserEmail(principal.getName())
	                  .orElseThrow(() -> new RuntimeException("User not found"));
	                  
	    return ResponseEntity.ok(new UserProfileResponse(
	        user.getUserEmail(), 
	        user.getLastInvoiceNumber()
	    ));
	}

}
