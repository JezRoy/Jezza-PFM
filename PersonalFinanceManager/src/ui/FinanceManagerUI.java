package ui;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import application.Budget;
import application.Expense;
import application.FinanceManager;
import application.Income;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FinanceManagerUI {
	
	private FinanceManager financeManager;
	private GridPane gridPane;
	
	public FinanceManagerUI(FinanceManager financeManager) {
		this.financeManager = financeManager;
		initializeUI();
	}
	
	private void initializeUI() {
		
		// Create and configure the GridPane
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10)); // Padding around the grid
		gridPane.setHgap(10); // Horizontal gap between columns
        gridPane.setVgap(10); // Vertical gap between rows
        
        Label incomeMonthlyLabel = new Label("Last Month's Income:");
        Label expenseMonthlyLabel = new Label("Last Month's Expenses:");
        Label incomeMonthlyValue = new Label();
        Label expenseMonthlyValue = new Label();
        // Run every hour
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> {
            	incomeMonthlyValue.setText(String.format("%.2f", financeManager.calculateMonthlyIncome()));
            	expenseMonthlyValue.setText(String.format("%.2f", financeManager.calculateMonthlyExpenses()));
            });
        }, 0, 1, TimeUnit.HOURS); // Update every hour

        // Headings
        Label mainHeading = new Label("Jezza's Personal Finance Manager");
        mainHeading.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        
        Label incomeSectionHeading = new Label("Income Section");
        incomeSectionHeading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        Label expenseSectionHeading = new Label("Expense Section");
        expenseSectionHeading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        Label budgetSectionHeading = new Label("Budget Section");
        budgetSectionHeading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        Label savingsSectionHeading = new Label("Savings Section");
        savingsSectionHeading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        Label investmentSectionHeading = new Label("Investments Section");
        investmentSectionHeading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
		
        // Example Components
        Label incomeLabel = new Label("Income Amount:");
        TextField incomeField = new TextField();
        Button addIncomeButton = new Button("Add Income");
        // ComboBoxes for category selection
        Label incomeCategoryLabel = new Label("Income Category:");
        ComboBox<String> incomeCategoryBox = new ComboBox<>();
        incomeCategoryBox.getItems().addAll("Salary", "Freelance", "Investments", "Other");
        Label incomeSourceLabel = new Label("Expense Category:");
        ComboBox<String> incomeSourceBox = new ComboBox<>();
        incomeSourceBox.getItems().addAll("Bank Transfer", "Cash", "Credit", "Debit");
        Label incomeDescriptionLabel = new Label("Description:");
        TextArea incomeDescriptionArea = new TextArea();
        incomeDescriptionArea.setPrefRowCount(2);
        
        Label expenseLabel = new Label("Expense Amount:");
        TextField expenseField = new TextField();
        Button addExpenseButton = new Button("Add Expense");
        Label expenseCategoryLabel = new Label("Expense Category:");
        ComboBox<String> expenseCategoryBox = new ComboBox<>();
        expenseCategoryBox.getItems().addAll("Food", "Rent", "Utilities", "Entertainment", "Subscription", "Gift", "General", "Transport", "Other");
        Label expenseSourceLabel = new Label("Payment Method:");
        ComboBox<String> expenseSourceBox = new ComboBox<>();
        expenseSourceBox.getItems().addAll("Bank Transfer", "Cash", "Credit", "Debit");
        Label expenseDescriptionLabel = new Label("Description:");
        TextArea expenseDescriptionArea = new TextArea();
        expenseDescriptionArea.setPrefRowCount(2);
        
        Label incomeGoalLabel = new Label("Income Goal:");
        TextField incomeGoalField = new TextField();
        Label expenseLimitLabel = new Label("Expense Limit:");
        TextField expenseLimitField = new TextField();
        Label savingsGoalLabel = new Label("Savings Goal:");
        TextField savingsGoalField = new TextField();
        Label investmentGoalLabel = new Label("Investment Goal:");
        TextField investmentGoalField = new TextField();
        Button setBudgetButton = new Button("Set Budget");
        
        // HBox to hold buttons for operations like viewing reports or calculating totals
        HBox buttonBox = new HBox(10); // 10 is the spacing between buttons
        Button viewReportButton = new Button("View Report");
        Button calculateTotalButton = new Button("Calculate Totals");
        buttonBox.getChildren().addAll(viewReportButton, calculateTotalButton);
        
        // Add a button to the gridPane for showing the chart
        Button showChartButton = new Button("Show Summary Chart");
        
        gridPane.add(mainHeading, 1, 0, 2, 1); // Spanning across 2 columns
        gridPane.add(incomeSectionHeading, 0, 1, 2, 1);
        // Add components to the grid (colIndex, rowIndex)
        int incomeRowIndex = 1;
        gridPane.add(incomeLabel, 0, incomeRowIndex + 1);
        gridPane.add(incomeField, 1, incomeRowIndex + 1);
        gridPane.add(incomeDescriptionLabel, 0, incomeRowIndex + 2);
        gridPane.add(incomeDescriptionArea, 1, incomeRowIndex + 2);
        gridPane.add(incomeCategoryLabel, 0, incomeRowIndex + 3);
        gridPane.add(incomeCategoryBox, 1, incomeRowIndex + 3);
        gridPane.add(incomeSourceLabel, 0, incomeRowIndex + 4);
        gridPane.add(incomeSourceBox, 1, incomeRowIndex + 4);
        gridPane.add(addIncomeButton, 2, incomeRowIndex + 4);
        
        int expenseRowIndex = incomeRowIndex + 5;
        gridPane.add(expenseSectionHeading, 0, expenseRowIndex, 2, 1);
        gridPane.add(expenseLabel, 0, expenseRowIndex + 1);
        gridPane.add(expenseField, 1, expenseRowIndex + 1);
        gridPane.add(expenseDescriptionLabel, 0, expenseRowIndex + 2);
        gridPane.add(expenseDescriptionArea, 1, expenseRowIndex + 2);
        gridPane.add(expenseCategoryLabel, 0, expenseRowIndex + 3);
        gridPane.add(expenseCategoryBox, 1, expenseRowIndex + 3);
        gridPane.add(expenseSourceLabel, 0, expenseRowIndex + 4);
        gridPane.add(expenseSourceBox, 1, expenseRowIndex + 4);
        gridPane.add(addExpenseButton, 2, expenseRowIndex + 4);
        
        int budgetRowIndex = expenseRowIndex + 5;
        gridPane.add(budgetSectionHeading, 0, budgetRowIndex, 2, 1);
        gridPane.add(incomeMonthlyLabel, 2, budgetRowIndex + 1);
        gridPane.add(incomeMonthlyValue, 3, budgetRowIndex + 1);
        gridPane.add(expenseMonthlyLabel, 2, budgetRowIndex + 2);
        gridPane.add(expenseMonthlyValue, 3, budgetRowIndex + 2);
        gridPane.add(incomeGoalLabel, 0, budgetRowIndex + 1);
        gridPane.add(incomeGoalField, 1, budgetRowIndex + 1);
        gridPane.add(expenseLimitLabel, 0, budgetRowIndex + 2);
        gridPane.add(expenseLimitField, 1, budgetRowIndex + 2);
        gridPane.add(savingsGoalLabel, 0, budgetRowIndex + 3);
        gridPane.add(savingsGoalField, 1, budgetRowIndex + 3);
        gridPane.add(investmentGoalLabel, 0, budgetRowIndex + 4);
        gridPane.add(investmentGoalField, 1, budgetRowIndex + 4);
        gridPane.add(setBudgetButton, 1, budgetRowIndex + 5);
        
        // Add the button box to the grid
        int miscButtonRowIndex = budgetRowIndex + 6;
        gridPane.add(buttonBox, 1, miscButtonRowIndex, 2, 1); // Span 2 columns
        gridPane.add(showChartButton, 0, miscButtonRowIndex + 1);
        
        // Event handling
        addIncomeButton.setOnAction(e -> handleAddIncome(incomeField, incomeDescriptionArea, incomeCategoryBox, incomeSourceBox));
        addExpenseButton.setOnAction(e -> handleAddExpense(expenseField, expenseDescriptionArea, expenseCategoryBox, expenseSourceBox));
        
        setBudgetButton.setOnAction(e -> {
            double incomeGoal = Double.parseDouble(incomeGoalField.getText());
            double expenseLimit = Double.parseDouble(expenseLimitField.getText());
            double savingsGoal = Double.parseDouble(savingsGoalField.getText());
            double investmentGoal = Double.parseDouble(investmentGoalField.getText());

            Budget budget = new Budget(incomeGoal, expenseLimit, savingsGoal, investmentGoal);
            financeManager.setBudget(budget);

            // Optionally, update UI or notify the user
            showAlert(AlertType.INFORMATION, "Budget Saved", "Your budget has been updated.");
        });
        
        viewReportButton.setOnAction(e -> handleViewReport());
        showChartButton.setOnAction(e -> showIncomeExpenseChart());
        
	}

	// Method to handle adding income
	private void handleAddIncome(TextField incomeField, TextArea incomeDescriptionArea, ComboBox<String> incomeCategoryBox, ComboBox<String> incomeSourceBox) {
        try {
            double amount = Double.parseDouble(incomeField.getText());
            String description = incomeDescriptionArea.getText();
            String category = incomeCategoryBox.getValue();
            String source = incomeSourceBox.getValue();

            if (category == null) {
                showAlert(AlertType.WARNING, "Warning", "Please select a category.");
                return;
            }

            if (description.isEmpty()) {
                showAlert(AlertType.WARNING, "Warning", "Please enter a description.");
                return;
            }
            
            if (source.isEmpty()) {
                showAlert(AlertType.WARNING, "Warning", "Please enter an income source.");
                return;
            }

            // Add an Income transaction
            financeManager.addIncome(new Income(1, new Date(), amount, description, category, source));
            showAlert(AlertType.INFORMATION, "Success", "Income added: " + amount + " in category " + category + ", through " + source);
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Error", "Invalid income amount.");
        }
    }

    // Method to handle adding expense
	private void handleAddExpense(TextField expenseField, TextArea expenseDescriptionArea, ComboBox<String> expenseCategoryBox, ComboBox<String> expenseSourceBox) {
        try {
            double amount = Double.parseDouble(expenseField.getText());
            String description = expenseDescriptionArea.getText();
            String category = expenseCategoryBox.getValue();
            String payMethod = expenseSourceBox.getValue();

            if (category == null) {
                showAlert(AlertType.WARNING, "Warning", "Please select a category.");
                return;
            }

            if (description.isEmpty()) {
                showAlert(AlertType.WARNING, "Warning", "Please enter a description.");
                return;
            }
            
            if (payMethod.isEmpty()) {
                showAlert(AlertType.WARNING, "Warning", "Please enter a payment method.");
                return;
            }

            // Add an Expense transaction
            financeManager.addExpense(new Expense(1, new Date(), amount, description, category, payMethod));
            showAlert(AlertType.INFORMATION, "Success", "Expense added: " + amount + " in category " + category + ", through " + payMethod);
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Error", "Invalid expense amount.");
        }
    }
    
    // Method to handle viewing report
	private void handleViewReport() {
        String report = financeManager.generateReport();
        showAlert(AlertType.INFORMATION, "Financial Report", report);
    }
	
	private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to return the UI for use in the scene
    public ScrollPane getUI() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);  // gridPane is the existing layout
        scrollPane.setFitToWidth(true);   // This will ensure the content fits the width of the ScrollPane
        return scrollPane;
    }
    
    private void showIncomeExpenseChart() {
        PieChart pieChart = new PieChart();
        
        double totalIncome = financeManager.calculateTotalIncome();
        double totalExpenses = financeManager.calculateTotalExpenses();
        double savings = financeManager.calculateTotalSavings();
        double investments = financeManager.calculateTotalInvestments();
        
        PieChart.Data incomeData = new PieChart.Data("Income", totalIncome);
        PieChart.Data expenseData = new PieChart.Data("Expenses", totalExpenses);
        PieChart.Data savingsData = new PieChart.Data("Savings", savings);
        PieChart.Data investmentsData = new PieChart.Data("Investments", investments);
        
        pieChart.getData().addAll(incomeData, expenseData, savingsData, investmentsData);
        
        // You can add this pie chart to your existing grid or display it in a new window
        Scene chartScene = new Scene(new VBox(pieChart), 800, 600);
        Stage chartStage = new Stage();
        chartStage.setTitle("Income vs Expenses Chart");
        chartStage.setScene(chartScene);
        chartStage.show();
    }
}
