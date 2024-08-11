package application;

import java.util.Date;

public class Expense extends Transaction {
	
	private String paymentMethod;
	
	public Expense(int id, Date date, double amount, String description, String category, String paymentMethod) {
        super(id, date, amount, description, category);
        this.paymentMethod = paymentMethod;
    }

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	
	
}
