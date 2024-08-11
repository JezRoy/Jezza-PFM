package application;

import java.util.Date;

public class Transaction {
	
	private int id;
	private Date date;
	private double amount;
	private String description;
	private String category;
	
	public Transaction(int id, Date date, double amount, String description, String category) {
		super();
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.description = description;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
