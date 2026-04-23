package proj.ig.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proj.ig.entity.Client;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByUserEmail(String userEmail);
    
    // Optional: Check if a client already exists by name for that user
    boolean existsByUserEmailAndClientName(String userEmail, String clientName);
}