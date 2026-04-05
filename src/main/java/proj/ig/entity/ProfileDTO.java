package proj.ig.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileDTO {
    private String userEmail; // Read-only on frontend
    private String companyName;
    private String senderName;
    private String senderContact;
    private String taxId;
    private String website;
    private String senderAddress;
    
    @JsonProperty("logoBase64")
    private String logoBase64;
    
    @JsonProperty("signBase64")
    private String signatureBase64;
    
    @JsonProperty("qrBase64")
    private String qrCodeBase64;
    
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSenderContact() {
		return senderContact;
	}
	public void setSenderContact(String senderContact) {
		this.senderContact = senderContact;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getSenderAddress() {
		return senderAddress;
	}
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	
	public String getLogoBase64() {
		return logoBase64;
	}
	public void setLogoBase64(String logoBase64) {
		this.logoBase64 = logoBase64;
	}
	public String getSignatureBase64() {
		return signatureBase64;
	}
	public void setSignatureBase64(String signatureBase64) {
		this.signatureBase64 = signatureBase64;
	}
	public String getQrCodeBase64() {
		return qrCodeBase64;
	}
	public void setQrCodeBase64(String qrCodeBase64) {
		this.qrCodeBase64 = qrCodeBase64;
	}
	public ProfileDTO(String userEmail, String companyName, String senderName, String senderContact, String taxId,
			String website, String senderAddress) {
		super();
		this.userEmail = userEmail;
		this.companyName = companyName;
		this.senderName = senderName;
		this.senderContact = senderContact;
		this.taxId = taxId;
		this.website = website;
		this.senderAddress = senderAddress;
	}
	
	
	public ProfileDTO() {
		super();
	}

    
}