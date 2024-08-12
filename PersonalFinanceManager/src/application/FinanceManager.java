package application;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class FinanceManager {
	
	private List<Transaction> transactions;
    private Budget budget;
    private List<Savings> savings;
    private List<Investment> investments;
    
	public FinanceManager() {
		this.transactions = new ArrayList<Transaction>();
		this.savings = new ArrayList<Savings>();
		this.investments = new ArrayList<Investment>();
	}
    
	public void addIncome(Income income) {
		transactions.add(income);
	}
	
	public void addExpense(Expense expense) {
        transactions.add(expense);
    }
    
	// Calculate monthly income from the most recent month
	public double calculateMonthlyIncome() {
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.MONTH, -1); // Move to the previous month
	    int lastMonth = cal.get(Calendar.MONTH);

	    return transactions.stream()
	            .filter(t -> t instanceof Income && getMonthFromDate(t.getDate()) == lastMonth)
	            .mapToDouble(Transaction::getAmount)
	            .sum();
	}
    
    // Calculate monthly expenses from the most recent month
	public double calculateMonthlyExpenses() {
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.MONTH, -1); // Move to the previous month
	    int lastMonth = cal.get(Calendar.MONTH);

	    return transactions.stream()
	            .filter(t -> t instanceof Expense && getMonthFromDate(t.getDate()) == lastMonth)
	            .mapToDouble(Transaction::getAmount)
	            .sum();
	}
    
    private int getMonthFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }
	
	public double calculateTotalIncome() {
        return transactions.stream()
                .filter(t -> t instanceof Income)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double calculateTotalExpenses() {
        return transactions.stream()
                .filter(t -> t instanceof Expense)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
    
    // Calculate total savings
    public double calculateTotalSavings() {
        return savings.stream()
                .filter(t -> t instanceof Savings)
                .mapToDouble(Savings::getCurrentAmount)
                .sum();
    }

    // Calculate total investments
    public double calculateTotalInvestments() {
        return investments.stream()
                .filter(t -> t instanceof Investment)
                .mapToDouble(Investment::getAmount)
                .sum();
    }
    
    public double calculateRemainingBalance() {
        return calculateTotalIncome() - calculateTotalExpenses();
    }
    
    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public Budget getBudget() {
        return budget;
    }
    
    public void addSavings(Savings saving) {
        savings.add(saving);
    }

    public void addInvestment(Investment investment) {
        investments.add(investment);
    }

    // Removing incomes
    public void removeAllIncomes() {
        Iterator<Transaction> iterator = transactions.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof Income) {
                iterator.remove();
            }
        }
    }

    // Removing expenses
    public void removeAllExpenses() {
        Iterator<Transaction> iterator = transactions.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof Expense) {
                iterator.remove();
            }
        }
    }

    // Removing savings
    public void removeAllSavings() {
        Iterator<Savings> iterator = savings.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof Savings) {
                iterator.remove();
            }
        }
    }

    // Removing investments
    public void removeAllInvestments() {        
    	Iterator<Investment> iterator = investments.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof Investment) {
                iterator.remove();
            }
        }
    }

    // Filtering by category
    public List<Transaction> filterByCategory(String category) {
        return transactions.stream()
                .filter(t -> t.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Method to remove a transaction by ID
    public boolean removeTransactionById(int id) {
        Iterator<Transaction> iterator = transactions.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                return true; // Return true if a transaction was removed
            }
        }
        return false; // Return false if no transaction was found with the given ID
    }
    
    // Method to remove a transaction by description
    public boolean removeTransactionByDescription(String description) {
        Iterator<Transaction> iterator = transactions.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getDescription().equalsIgnoreCase(description)) {
                iterator.remove();
                return true; // Return true if a transaction was removed
            }
        }
        return false; // Return false if no transaction was found with the given description
    }
    
    // Method to remove a transaction by category
    public boolean removeTransactionByCategory(String category) {
        Iterator<Transaction> iterator = transactions.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getCategory().equalsIgnoreCase(category)) {
                iterator.remove();
                return true; // Return true if a transaction was removed
            }
        }
        return false; // Return false if no transaction was found with the given category
    }
    // Sort transactions by amount in ascending order
    public List<Transaction> sortTransactionsByAmountAsc() {
        return transactions.stream()
                .sorted(Comparator.comparingDouble(Transaction::getAmount))
                .collect(Collectors.toList());
    }
    // Sort transactions by amount in descending order
    public List<Transaction> sortTransactionsByAmountDesc() {
        return transactions.stream()
                .sorted((t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount()))
                .collect(Collectors.toList());
    }
    // Sort transactions by category in alphabetical order
    public List<Transaction> sortTransactionsByCategoryAsc() {
        return transactions.stream()
                .sorted(Comparator.comparing(Transaction::getCategory))
                .collect(Collectors.toList());
    }
    // Sort transactions by category in reverse alphabetical order
    public List<Transaction> sortTransactionsByCategoryDesc() {
        return transactions.stream()
                .sorted((t1, t2) -> t2.getCategory().compareTo(t1.getCategory()))
                .collect(Collectors.toList());
    }
    
    // Method to generate a report of all transactions
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Financial Report:\n");

        double totalIncome = calculateTotalIncome();
        double totalExpenses = calculateTotalExpenses();
        double totalSavings = calculateTotalSavings();
        double totalInvestments = calculateTotalInvestments();

        report.append("Total Income: ").append(totalIncome).append("\n");
        report.append("Total Expenses: ").append(totalExpenses).append("\n");
        report.append("Total Savings: ").append(totalSavings).append("\n");
        report.append("Total Investments: ").append(totalInvestments).append("\n");

        return report.toString();
    }
    
    public boolean updateTransaction(int id, Transaction newTransaction) {
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getId() == id) {
                // Check the type of the existing transaction
                Transaction existingTransaction = transactions.get(i);
                if (existingTransaction.getClass().equals(newTransaction.getClass())) {
                    // Update only if the new transaction type matches the existing one
                    transactions.set(i, newTransaction);
                    return true;
                } else {
                    System.out.println("Transaction type mismatch. Update failed.");
                    return false;
                }
            }
        }
        return false; // Transaction with given id not found
    }
}
