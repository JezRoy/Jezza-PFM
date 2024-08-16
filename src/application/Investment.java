package application;

import java.time.LocalDate;

public class Investment {
	
	private String name;
	private String riskLevel; // Low, Medium, High
	private String status; // Active, Matured, Closed
	private String type;
	private double amount;
	private double returnRate;
	private String maturityDate;
	
	public Investment(String name, String riskLevel, String type, double amount, double returnRate, String maturityDate) {
		super();
		this.name = name;
		this.riskLevel = riskLevel;
		this.status = "Active";
		this.type = type;
		this.amount = amount;
		this.returnRate = returnRate;
		this.maturityDate = maturityDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getReturnRate() {
		return returnRate;
	}

	public void setReturnRate(double returnRate) {
		this.returnRate = returnRate;
	}

	public String getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
	
	public double calculatePotentialReturn() {
		return amount * (returnRate / 100);
	}
	
	public boolean hasMatured() {
		LocalDate maturityDateObj = LocalDate.parse(maturityDate);
	    return LocalDate.now().isAfter(maturityDateObj);
	}
	
	public void updateStatus() {
		if (hasMatured()) {
	        status = "Matured";
	    } else {
	        status = "Active";
	    }
	}

}
