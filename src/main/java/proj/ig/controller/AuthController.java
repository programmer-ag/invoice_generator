package proj.ig.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proj.ig.entity.LoginRequest;
import proj.ig.entity.User;
import proj.ig.repos.UserRepository;
import proj.ig.security.utils.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder encoder;
    @Autowired private JwtUtils jwtUtils;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if(userRepository.existsById(user.getUserEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        // Encrypt the password!
        user.setPassword(encoder.encode(user.getPassword()));
        user.setUserId(UUID.randomUUID());
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        User user = userRepository.findById(req.getUserEmail()).orElse(null);
        
        if (user != null && encoder.matches(req.getPassword(), user.getPassword())) {
            // Generate the Key!
            String token = jwtUtils.generateToken(user.getUserEmail());
            
            // Return a map or a DTO with the token
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("email", user.getUserEmail());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}