package in.cdac.acts.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private List<Item> items;
	private double totalPrice;

	public Order() {
		items = new ArrayList<>();
	}

	public void addItem(Item item) {
		items.add(item);
		totalPrice += item.getPrice() * item.getbillQuantity();
	}

	public void removeItem(Item item) {
		items.remove(item);
		totalPrice -= item.getPrice() * item.getbillQuantity();
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public List<Item> getItems() {
		return items;
	}

	public double calculateTotal() {
		double total = 0;
		for (Item item : items) {
			total += item.getPrice();
		}
		return total;
	}

	public void displayOrder() {
		System.out.println("Order Details: ");
		for (Item item : items) {
			System.out.println(item.getName() + " - " + item.getPrice() + " X " + item.getbillQuantity());
		}
		System.out.println("Total Price:  Rs " + totalPrice);
	}
}
