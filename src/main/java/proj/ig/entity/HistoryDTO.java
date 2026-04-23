package proj.ig.entity;

import java.time.LocalDateTime;

public class HistoryDTO {
    private Long pdfId;
    private String pdfName;
    private LocalDateTime createdAt;
    private String clientName;
    private boolean paid;
    
    public HistoryDTO(Long pdfId, String pdfName, LocalDateTime createdAt, String clientName, boolean paid) {
        this.pdfId = pdfId;
        this.pdfName = pdfName;
        this.createdAt = createdAt;
        this.clientName = clientName;
        this.paid = paid;
    }

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public Long getPdfId() {
		return pdfId;
	}

	public void setPdfId(Long pdfId) {
		this.pdfId = pdfId;
	}

	public String getPdfName() {
		return pdfName;
	}

	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    
    
}