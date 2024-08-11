package ui;

import application.FinanceManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainApp extends Application{
	
	private FinanceManager financeManager;
	
	@Override
	public void start(Stage primaryStage) {
		
		// Create an instance of FinanceManager
		financeManager = new FinanceManager();
		
		try {
			// Set up the UI
			FinanceManagerUI ui = new FinanceManagerUI(financeManager);
			
            Scene scene = new Scene(ui.getUI(), 800, 600); // Set up a window
            primaryStage.setScene(scene);
            primaryStage.setTitle("Finance Manager");
            primaryStage.show();
            
		} catch (Exception e) {
			System.out.println("--->>> Error loading the Finance Manager UI:");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
        launch(args);
    }

}
