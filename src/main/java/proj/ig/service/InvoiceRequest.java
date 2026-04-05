package proj.ig.service;


import java.util.List;


public class InvoiceRequest {
    private String invoiceNumber;
    private String date;
    private String senderName;
    private String clientName;
    private List<Item> items;
    public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	private double taxRate;
    private String currency="₹";
    private String senderAddress; // New: Physical address
    private String templateName;
    public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public boolean isShowLogo() {
		return showLogo;
	}

	public void setShowLogo(boolean showLogo) {
		this.showLogo = showLogo;
	}

	public boolean isShowAddress() {
		return showAddress;
	}

	public void setShowAddress(boolean showAddress) {
		this.showAddress = showAddress;
	}

	private String logoBase64; // The image data from React
    private String companyName;

    
    // Sender Details
    private String senderEmail;
    public String getLogoBase64() {
		return logoBase64;
	}

	public void setLogoBase64(String logoBase64) {
		this.logoBase64 = logoBase64;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
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

	public boolean isShowEmail() {
		return showEmail;
	}

	public void setShowEmail(boolean showEmail) {
		this.showEmail = showEmail;
	}

	public boolean isShowContact() {
		return showContact;
	}

	public void setShowContact(boolean showContact) {
		this.showContact = showContact;
	}

	public boolean isShowTaxId() {
		return showTaxId;
	}

	public void setShowTaxId(boolean showTaxId) {
		this.showTaxId = showTaxId;
	}

	public boolean isTaxEnabled() {
		return taxEnabled;
	}

	public void setTaxEnabled(boolean taxEnabled) {
		this.taxEnabled = taxEnabled;
	}

	private String senderContact;
    private String taxId; // e.g., GST Number
    
    // Checkbox Flags (Conditional Rendering)
    private boolean showEmail;
    private boolean showContact;
    private boolean showTaxId;
    private boolean taxEnabled; // The master switch for tax calculations
    private boolean showLogo;
    private boolean showAddress;
    
    public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public static class Item {
        private String description;
        private int quantity;
        private double price;
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
        
    }
    
    // Logic for the template
    public double getTotal() {
        double subtotal = items.stream().mapToDouble(i -> i.quantity * i.price).sum();
        return subtotal + (subtotal * (taxRate / 100));
    }
    
   
    
    // In your getter, handle the potential null
   public double getTax()
   {
	   double subtotal = items.stream().mapToDouble(i -> i.quantity * i.price).sum();
	   return (subtotal * (taxRate / 100));
   }
    
    public String getInvoiceNumber() {
        return this.invoiceNumber;
        
        
    }

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	private boolean showSignature;
    private String signatureBase64;
    private boolean showQrCode;
    private String qrCodeBase64;
    private boolean showWorkingHrs;
    private String workingHrs;
    private boolean showWebsite;
    private String clientWebsite;
    
    
	public boolean isShowSignature() {
		return showSignature;
	}

	public void setShowSignature(boolean showSignature) {
		this.showSignature = showSignature;
	}

	public String getSignatureBase64() {
		return signatureBase64;
	}

	public void setSignatureBase64(String signatureBase64) {
		this.signatureBase64 = signatureBase64;
	}

	public boolean isShowQrCode() {
		return showQrCode;
	}

	public void setShowQrCode(boolean showQrCode) {
		this.showQrCode = showQrCode;
	}

	public String getQrCodeBase64() {
		return qrCodeBase64;
	}

	public void setQrCodeBase64(String qrCodeBase64) {
		this.qrCodeBase64 = qrCodeBase64;
	}

	public boolean isShowWorkingHrs() {
		return showWorkingHrs;
	}

	public void setShowWorkingHrs(boolean showWorkingHrs) {
		this.showWorkingHrs = showWorkingHrs;
	}

	public boolean isShowNotes() {
		return showNotes;
	}

	public void setShowNotes(boolean showNotes) {
		this.showNotes = showNotes;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getWorkingHrs() {
		return workingHrs;
	}

	public void setWorkingHrs(String workingHrs) {
		this.workingHrs = workingHrs;
	}

	public boolean isShowWebsite() {
		return showWebsite;
	}

	public void setShowWebsite(boolean showWebsite) {
		this.showWebsite = showWebsite;
	}

	public String getClientWebsite() {
		return clientWebsite;
	}

	public void setClientWebsite(String clientWebsite) {
		this.clientWebsite = clientWebsite;
	}
	
	private boolean showNotes;
	private String notes;
}
