package application;

import java.time.LocalDate;

public class Savings {

	private String name;
	private String status; // Failed, In Progress, Completed
	private double goal;
	private double currentAmount;
	private String targetDate;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Savings(String name, double goal, double currentAmount, String targetDate) {
		super();
		this.name = name;
		this.status = "In Progress";
		this.goal = goal;
		this.currentAmount = currentAmount;
		this.targetDate = targetDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public double calculateProgress() {
		return (currentAmount / goal) * 100;
	}
	
	public void updateStatus() {
		if (currentAmount >= goal) {
			status = "Completed";
		} else if (currentAmount < goal && targetDatePassed()) {
	        status = "Failed";
	    } else {
	        status = "In Progress";
	    }
	}
	
	private boolean targetDatePassed() {
		LocalDate targetDateObj = LocalDate.parse(targetDate);
	    return LocalDate.now().isAfter(targetDateObj);
	}
	
}
