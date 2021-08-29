package main;

public class Item {
	private String description;
	private double price;
	private String category;
	private int prepTime;
	
	public Item(String description, double price, String category, int prepTime) {
		this.description = description;
		this.price = price;
		this.category = category;
		this.prepTime = prepTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(int prepTime) {
		this.prepTime = prepTime;
	}
	
	
	
}
