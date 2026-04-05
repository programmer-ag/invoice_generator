package proj.ig.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proj.ig.entity.ProfileDTO;
import proj.ig.entity.User;
import proj.ig.repos.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // 1. Fetch Profile (Prefill logic)
    @GetMapping("/info")
    public ResponseEntity<ProfileDTO> getProfile(Principal principal) {
        User user = userRepository.findByUserEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        ProfileDTO dto = new ProfileDTO();
        dto.setUserEmail(user.getUserEmail());
        dto.setCompanyName(user.getCompanyName());
        dto.setSenderName(user.getFullName());
        dto.setSenderContact(user.getContactNo());
        dto.setTaxId(user.getTaxId());
        dto.setWebsite(user.getWebsite());
        dto.setSenderAddress(user.getAddress());
        dto.setLogoBase64(user.getLogoBase64());
        dto.setSignatureBase64(user.getSignatureBase64());
        dto.setQrCodeBase64(user.getQrCodeBase64());
        return ResponseEntity.ok(dto);
    }

    // 2. Save Changes
    @PutMapping("/info")
    public ResponseEntity<?> updateProfile(@RequestBody ProfileDTO profileUpdate, Principal principal) {
        User user = userRepository.findByUserEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update only allowed fields (ignore email from request body)
        user.setCompanyName(profileUpdate.getCompanyName());
        user.setFullName(profileUpdate.getSenderName());
        user.setContactNo(profileUpdate.getSenderContact());
        user.setTaxId(profileUpdate.getTaxId());
        user.setWebsite(profileUpdate.getWebsite());
        user.setAddress(profileUpdate.getSenderAddress());
        user.setLogoBase64(profileUpdate.getLogoBase64());
        user.setSignatureBase64(profileUpdate.getSignatureBase64());
        user.setQrCodeBase64(profileUpdate.getQrCodeBase64());
        userRepository.save(user);
        
        return ResponseEntity.ok("Profile updated successfully");
    }
    
    @DeleteMapping("/info")
    public ResponseEntity<?> deleteUserAccount(Principal principal) {
        // 1. Find the user
        User user = userRepository.findByUserEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Delete the user record
        // Note: If you later add an Invoices table, ensure you use CascadeType.ALL 
        // or manually delete related records first.
        userRepository.delete(user);

        return ResponseEntity.ok("Account and all associated data deleted successfully.");
    }
}
