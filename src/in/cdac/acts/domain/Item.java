package in.cdac.acts.domain;

public class Item implements Comparable<Item>{
	private String name;
	private double price;
	private int quantity;
	private int  billQuantity;

	public Item(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.billQuantity = 0;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getbillQuantity() {
		return billQuantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setbillQuantity(int billQuantity) {
		this.billQuantity = billQuantity;
	}


	@Override
	public int compareTo(Item other) {
		return this.name.compareTo(other.name);
	}

	@Override
	public String toString() {
		return String.format("%-25s%-15.2f%10d", this.name, this.price, this.quantity);
	}

	
}
