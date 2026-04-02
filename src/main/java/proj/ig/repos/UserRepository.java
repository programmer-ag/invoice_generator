package proj.ig.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proj.ig.entity.User; 
import java.util.UUID;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
    /**
     * Since userEmail is the @Id, we get findById(String email) for free.
     * We add this to find users by their internal UUID if needed.
     */
    Optional<User> findByUserId(UUID userId);
    
    /**
     * Used for registration checks.
     */
    boolean existsByUserEmail(String userEmail);
}