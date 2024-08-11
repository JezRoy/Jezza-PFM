package application;

import java.util.Date;

public class Income extends Transaction {
	
	private String source;

	public Income(int id, Date date, double amount, String description, String category, String source) {
        super(id, date, amount, description, category);
        this.source = source;
    }

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	
}
