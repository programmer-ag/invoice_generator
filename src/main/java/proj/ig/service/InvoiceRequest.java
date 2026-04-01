package proj.ig.service;


import java.util.List;


public class InvoiceRequest {
    private String invoiceNumber;
    private String date;
    private String senderName;
    private String clientName;
    private List<Item> items;
    private double taxRate;
    private String currency="₹";
    
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
}
