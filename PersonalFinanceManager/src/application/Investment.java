package application;

public class Investment {
	
	private String name;
	private String type;
	private double amount;
	private double returnRate;
	private String maturityDate;
	
	public Investment(String name, String type, double amount, double returnRate, String maturityDate) {
		super();
		this.name = name;
		this.type = type;
		this.amount = amount;
		this.returnRate = returnRate;
		this.maturityDate = maturityDate;
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
	
	

}
