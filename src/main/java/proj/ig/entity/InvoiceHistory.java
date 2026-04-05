package proj.ig.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoice_history")
public class InvoiceHistory {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pdfId; // The handle Hibernate was looking for

    @Column(name="user_email")
    private String userEmail;
    
    private String pdfName;

    @Column(columnDefinition = "BYTEA") // PostgreSQL specific for binary
    private byte[] pdfData;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters...
    public Long getPdfId() { return pdfId; }
    public void setPdfId(Long pdfId) { this.pdfId = pdfId; }
    
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getPdfName() { return pdfName; }
    public void setPdfName(String pdfName) { this.pdfName = pdfName; }

    public byte[] getPdfData() { return pdfData; }
    public void setPdfData(byte[] pdfData) { this.pdfData = pdfData; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}