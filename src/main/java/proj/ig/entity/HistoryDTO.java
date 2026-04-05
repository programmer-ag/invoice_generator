package proj.ig.entity;

import java.time.LocalDateTime;

public class HistoryDTO {
    private Long pdfId;
    private String pdfName;
    private LocalDateTime createdAt;

    public HistoryDTO(Long pdfId, String pdfName, LocalDateTime createdAt) {
        this.pdfId = pdfId;
        this.pdfName = pdfName;
        this.createdAt = createdAt;
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