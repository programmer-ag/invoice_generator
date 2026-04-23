package proj.ig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proj.ig.entity.Client;
import proj.ig.repos.ClientRepository;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    // Fetch all clients for the logged-in user
    @GetMapping
    public List<Client> getMyClients(Principal principal) {
        return clientRepository.findByUserEmail(principal.getName());
    }

    // Save or Update a client
    @PostMapping
    public ResponseEntity<?> saveClient(@RequestBody Client client, Principal principal) {
        client.setUserEmail(principal.getName());
        
        // Prevent duplicate names for the same user
        if (clientRepository.existsByUserEmailAndClientName(principal.getName(), client.getClientName())) {
            return ResponseEntity.badRequest().body("Client name already exists.");
        }
        
        clientRepository.save(client);
        return ResponseEntity.ok("Client saved successfully");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody Client updatedClient, Principal principal) {
        Client existing = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // Security check: Ensure the client belongs to the logged-in user
        if (!existing.getUserEmail().equals(principal.getName())) {
            return ResponseEntity.status(403).body("Unauthorized");
        }

        existing.setClientName(updatedClient.getClientName());
        existing.setAddress(updatedClient.getAddress());
        
        clientRepository.save(existing);
        return ResponseEntity.ok("Client updated successfully");
    }

    // Delete a client
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return ResponseEntity.ok("Client removed");
    }
}