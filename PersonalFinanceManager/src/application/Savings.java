package application;

public class Savings {
	
	private double goal;
	private double currentAmount;
	private String targetDate;
	
	public Savings(double goal, double currentAmount, String targetDate) {
		super();
		this.goal = goal;
		this.currentAmount = currentAmount;
		this.targetDate = targetDate;
	}

	public double getGoal() {
		return goal;
	}

	public void setGoal(double goal) {
		this.goal = goal;
	}

	public double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}

	public String getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}

	
}
