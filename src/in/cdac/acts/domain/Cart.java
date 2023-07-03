package in.cdac.acts.domain;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<Item> items;

	public Cart() {
		this.items = new ArrayList<>();
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

	public void removeItem(Item item) {
		this.items.remove(item);
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void clearCart() {
		this.items.clear();
	}

	public void displayCart() {
		System.out.println("Item(s) in Cart: ");
		System.out.println(String.format("%-15s%-15s", "Name", "Quantity"));
		System.out.println("-------------------------------");
		for (Item item : this.items) {
			System.out.println(String.format("%-15s%-15d", item.getName(), item.getbillQuantity()));
		}
		System.out.println("-------------------------------");
	}
}
