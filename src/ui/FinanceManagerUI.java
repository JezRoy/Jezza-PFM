package ui;

import java.util.Date;

import application.Expense;
import application.FinanceManager;
import application.Income;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
		
        // Example Components
        Label incomeLabel = new Label("Income:");
        TextField incomeField = new TextField();
        Button addIncomeButton = new Button("Add Income");
        
        Label expenseLabel = new Label("Expense:");
        TextField expenseField = new TextField();
        Button addExpenseButton = new Button("Add Expense");
        
        Label budgetLabel = new Label("Budget:");
        TextField budgetField = new TextField();
        Button setBudget = new Button("Set Budget");
        
        // HBox to hold buttons for operations like viewing reports or calculating totals
        HBox buttonBox = new HBox(10); // 10 is the spacing between buttons
        Button viewReportButton = new Button("View Report");
        Button calculateTotalButton = new Button("Calculate Totals");
        buttonBox.getChildren().addAll(viewReportButton, calculateTotalButton);
        
        // ComboBoxes for category selection
        Label incomeCategoryLabel = new Label("Income Category:");
        ComboBox<String> incomeCategoryBox = new ComboBox<>();
        incomeCategoryBox.getItems().addAll("Salary", "Freelance", "Investments", "Other");

        Label expenseCategoryLabel = new Label("Expense Category:");
        ComboBox<String> expenseCategoryBox = new ComboBox<>();
        expenseCategoryBox.getItems().addAll("Food", "Rent", "Utilities", "Entertainment", "Subscription", "Gift", "General", "Transport", "Other");
        
        Label incomeDescriptionLabel = new Label("Description:");
        TextArea incomeDescriptionArea = new TextArea();
        incomeDescriptionArea.setPrefRowCount(2);
        
        Label expenseDescriptionLabel = new Label("Description:");
        TextArea expenseDescriptionArea = new TextArea();
        expenseDescriptionArea.setPrefRowCount(2);
        
        // Add a button to the gridPane for showing the chart
        Button showChartButton = new Button("Show Summary Chart");
        
        // Add components to the grid (colIndex, rowIndex)
        gridPane.add(incomeLabel, 0, 0);
        gridPane.add(incomeField, 1, 0);
        gridPane.add(incomeDescriptionLabel, 0, 1);
        gridPane.add(incomeDescriptionArea, 1, 1);
        gridPane.add(incomeCategoryLabel, 0, 2);
        gridPane.add(incomeCategoryBox, 1, 2);
        gridPane.add(addIncomeButton, 2, 2);

        gridPane.add(expenseLabel, 0, 3);
        gridPane.add(expenseField, 1, 3);
        gridPane.add(expenseDescriptionLabel, 0, 4);
        gridPane.add(expenseDescriptionArea, 1, 4);
        gridPane.add(expenseCategoryLabel, 0, 5);
        gridPane.add(expenseCategoryBox, 1, 5);
        gridPane.add(addExpenseButton, 2, 5);
        
        gridPane.add(budgetLabel, 0, 6);
        gridPane.add(budgetField, 1, 6);
        gridPane.add(setBudget, 2, 6);
        
        // Add the button box to the grid
        gridPane.add(buttonBox, 1, 8, 2, 1); // Span 2 columns
        gridPane.add(showChartButton, 0, 9);
        // Example event handling
        addIncomeButton.setOnAction(e -> handleAddIncome(incomeField, incomeDescriptionArea, incomeCategoryBox));
        addExpenseButton.setOnAction(e -> handleAddExpense(expenseField, expenseDescriptionArea, expenseCategoryBox));
        viewReportButton.setOnAction(e -> handleViewReport());
        showChartButton.setOnAction(e -> showIncomeExpenseChart());
	}

	// Method to handle adding income
	private void handleAddIncome(TextField incomeField, TextArea incomeDescriptionArea, ComboBox<String> incomeCategoryBox) {
        try {
            double amount = Double.parseDouble(incomeField.getText());
            String description = incomeDescriptionArea.getText();
            String category = incomeCategoryBox.getValue();

            if (category == null) {
                showAlert(AlertType.WARNING, "Warning", "Please select a category.");
                return;
            }

            if (description.isEmpty()) {
                showAlert(AlertType.WARNING, "Warning", "Please enter a description.");
                return;
            }

            // Add an Income transaction
            financeManager.addIncome(new Income(1, new Date(), amount, description, category, "Bank Transfer"));
            showAlert(AlertType.INFORMATION, "Success", "Income added: " + amount + " in category " + category);
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Error", "Invalid income amount.");
        }
    }

    // Method to handle adding expense
	private void handleAddExpense(TextField expenseField, TextArea expenseDescriptionArea, ComboBox<String> expenseCategoryBox) {
        try {
            double amount = Double.parseDouble(expenseField.getText());
            String description = expenseDescriptionArea.getText();
            String category = expenseCategoryBox.getValue();

            if (category == null) {
                showAlert(AlertType.WARNING, "Warning", "Please select a category.");
                return;
            }

            if (description.isEmpty()) {
                showAlert(AlertType.WARNING, "Warning", "Please enter a description.");
                return;
            }

            // Add an Expense transaction
            financeManager.addExpense(new Expense(1, new Date(), amount, description, category, "Credit Card"));
            showAlert(AlertType.INFORMATION, "Success", "Expense added: " + amount + " in category " + category);
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
    public GridPane getUI() {
        return gridPane;
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
