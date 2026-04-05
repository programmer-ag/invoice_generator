package proj.ig.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import proj.ig.entity.InvoiceHistory;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<InvoiceHistory, Long> {

    // Fetch list for the UI
    List<InvoiceHistory> findByUserEmail(String userEmail);

    // For the 10-day cleanup task
    @Transactional
    void deleteByCreatedAtBefore(LocalDateTime cutoff);
}