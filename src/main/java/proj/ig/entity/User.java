package proj.ig.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	public User() {;}
	
    public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	
	@Id
    private String userEmail; // Primary Key

    @Column(unique = true, nullable = false)
    private UUID userId; 

    @Column(nullable = false)
    private String password;

    // Profile fields for later
    @Column(name = "full_name")
    private String fullName;
    
    @Column(name = "company_name")
    private String companyName;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "tax_id")
    private String taxId;
    
    @Column(name = "last_invoice_number")
    private Integer lastInvoiceNumber = 0; // Default to 0

	public Integer getLastInvoiceNumber() {
		return lastInvoiceNumber;
	}

	public void setLastInvoiceNumber(Integer lastInvoiceNumber) {
		this.lastInvoiceNumber = lastInvoiceNumber;
	}
	
	@Column(name = "contact")
	private String contactNo;
	
	@Column(name = "website")
	private String website;

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	@Column(name="logo_img", columnDefinition = "TEXT") // Vital for large image strings
    private String logoBase64;

	
	@Column(name="sign_img", columnDefinition = "TEXT")
    private String signatureBase64;

	
	@Column(name="qr_img", columnDefinition = "TEXT")
    private String qrCodeBase64;

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

    
}
