package application;

import java.io.Serializable;

public class Budget implements Serializable {

	private double monthlyIncome;
    private double monthlyExpenses;
    private double incomeGoal;
    private double expenseLimit;
    private double savingsGoal;
    private double investmentGoal;
    
	public Budget(double monthlyIncome, double monthlyExpenses, double incomeGoal, double expenseLimit,
			double savingsGoal, double investmentGoal) {
		super();
		this.monthlyIncome = monthlyIncome;
		this.monthlyExpenses = monthlyExpenses;
		this.incomeGoal = incomeGoal;
		this.expenseLimit = expenseLimit;
		this.savingsGoal = savingsGoal;
		this.investmentGoal = investmentGoal;
	}

	public double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public double getMonthlyExpenses() {
		return monthlyExpenses;
	}

	public void setMonthlyExpenses(double monthlyExpenses) {
		this.monthlyExpenses = monthlyExpenses;
	}

	public double getIncomeGoal() {
		return incomeGoal;
	}

	public void setIncomeGoal(double incomeGoal) {
		this.incomeGoal = incomeGoal;
	}

	public double getExpenseLimit() {
		return expenseLimit;
	}

	public void setExpenseLimit(double expenseLimit) {
		this.expenseLimit = expenseLimit;
	}

	public double getSavingsGoal() {
		return savingsGoal;
	}

	public void setSavingsGoal(double savingsGoal) {
		this.savingsGoal = savingsGoal;
	}

	public double getInvestmentGoal() {
		return investmentGoal;
	}

	public void setInvestmentGoal(double investmentGoal) {
		this.investmentGoal = investmentGoal;
	}
    
}
