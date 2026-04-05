package proj.ig.entity;

public class UserProfileResponse {
    private String userEmail;
    private Integer lastInvoiceNumber;
    // You can add 'businessName', 'taxId' here later too
    
    public UserProfileResponse(String email, Integer lastNum) {
        this.userEmail = email;
        this.lastInvoiceNumber = lastNum;
    }
    // Getters...

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Integer getLastInvoiceNumber() {
		return lastInvoiceNumber;
	}

	public void setLastInvoiceNumber(Integer lastInvoiceNumber) {
		this.lastInvoiceNumber = lastInvoiceNumber;
	}
}