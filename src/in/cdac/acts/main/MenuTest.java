package in.cdac.acts.main;

import in.cdac.acts.utils.*;
import in.cdac.acts.domain.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MenuTest {
	private static Scanner sc = new Scanner(System.in);
	private static Cart cart = new Cart();
	private static Order order;
	private static int addQuantities;
	private static int removeQuantities;
	private static List<Item> items = new ArrayList<>();

	private static void getMenu() {
		items.add(new Item("Pasta", 150.0, 10));
		items.add(new Item("Biryani", 250.0, 20));
		items.add(new Item("Butter Chicken", 300.0, 15));
		items.add(new Item("Idli Sambhar", 70.0, 12));
		items.add(new Item("Masala Dosa", 50.0, 19));
		items.add(new Item("Vada Pav", 18.0, 16));
		items.add(new Item("Samosa", 20.0, 32));
		items.add(new Item("Chole Bhature", 80.0, 10));
		items.add(new Item("Chicken Roll ", 100.0, 8));
		items.add(new Item("Noodles", 60.0, 21));

	}

	private static void showMenu(Comparator<Item> comparator) {
		System.out.println("Menu:");
		System.out.println(String.format("%-25s%-15s%10s", "Name", "Price(in Rs)", "Available Quantity"));
		System.out.println("------------------------------------------------------------------------");
		items.sort(comparator);
		for (Item item : items) {
			System.out.println(item);
		}
		System.out.println("------------------------------------------------------------------------");

	}

	private static void addItem() {
		System.out.print("Enter item name	:	");
		String itemName = sc.nextLine();
		System.out.print("Enter item price	:	");
		double itemPrice = sc.nextDouble();
		System.out.print("Enter item quantity	:	");
		int itemQuantity = sc.nextInt();
		items.add(new Item(itemName, itemPrice, itemQuantity));
		System.out.println("Item added  successfully");
	}

	private static void addToCart() {
		System.out.print("Enter item name	:	");
		String addName = sc.nextLine();
		System.out.print("No. of quantity to add	:	");
		addQuantities = sc.nextInt();
		Item item = null;
		for (Item i : items) {
			if (i.getName().equalsIgnoreCase(addName)) {
				i.setbillQuantity(addQuantities);
				item = i;
				break;
			}
		}
		if (item == null) {
			System.out.println("Invalid item name.");
			return;
		}
		item.setQuantity(item.getQuantity() - addQuantities);
		if (item.getQuantity() < 0) {
			System.out.println("Not enough stock");
			item.setQuantity(item.getQuantity() + addQuantities);
			return;
		}
		cart.addItem(item);
		System.out.println("Item added to cart	:	"
				+ String.format("%-15s%-15.2f%-15d", item.getName(), item.getPrice(), addQuantities));
	}

	private static void removeFromCart() {
		System.out.print("Enter item name	:	");
		String removeName = sc.nextLine();
		System.out.print("No. of quantity to remove	:	");
		removeQuantities = sc.nextInt();
		Item item = null;
		if (removeQuantities > addQuantities) {
			System.out.println("Not enough quantity of this item in the cart");
			return;
		}

		for (Item i : cart.getItems()) {
			if (i.getName().equalsIgnoreCase(removeName)) {
				item = i;
				break;
			}
		}
		if (item == null) {
			System.out.println("Invalid item name.");
			return;
		}
		item.setQuantity(item.getQuantity() + removeQuantities);
		item.setbillQuantity(addQuantities - removeQuantities);
		if (removeQuantities == addQuantities)
			cart.removeItem(item);
		System.out.println("Item removed from cart	:	"
				+ String.format("%-15s%-15.2f%-15d", item.getName(), item.getPrice(), removeQuantities));
	}

	private static void viewCart() {
		if (cart.getItems().isEmpty()) {
			System.out.println("Your cart is empty.");
		} else {
			System.out.println();
			cart.displayCart();
		}
	}

	private static void checkout() {
		if (cart.getItems().isEmpty()) {
			System.out.println("Your cart is empty.");
			return;
		}
		order = new Order();

		for (Item item : cart.getItems()) {
			order.addItem(item);
		}

		order.calculateTotal();

		System.out.println();
		order.displayOrder();

		System.out.print("Confirm your order (y/n)	:	");
		String confirm = sc.next();
		if (confirm.equalsIgnoreCase("y")) {
			System.out.println("Your order has been placed successfully.");
			System.out.println("Thanks for shopping");
			order = null;
			cart.clearCart();
		} else {
			System.out.println("Your order has been cancelled.");
			order = null;
			cart.clearCart();

		}

	}

	private static int menuList() {
		System.out.println();
		System.out.println("Welcome to my shop!");
		System.out.println("0. Exit");
		System.out.println("1. Add Item");
		System.out.println("2. Show Menu");
		System.out.println("3. Add to Cart");
		System.out.println("4. Remove from Cart");
		System.out.println("5. View Cart");
		System.out.println("6. Checkout");
		System.out.print("Enter your choice	:	");
		try {
			int choice = sc.nextInt();
			return choice;
		} catch (Exception e) {
			System.out.println("Please enter a valid integer.");
			sc.nextLine(); 
			return menuList();
		}

	}

	private static int subMenuList() {
		System.out.println();
		System.out.println("0. Exit");
		System.out.println("1. Sort by name ");
		System.out.println("2. Sort by price ");
		System.out.println("3. Sort by quantity ");
		System.out.print("Enter your choice	:	");
		try {
			int choice = sc.nextInt();
			return choice;
		} catch (Exception e) {
			System.out.println("Please enter a valid integer.");
			sc.nextLine();
			return subMenuList();
		}

	}

	public static void main(String[] args) {
		int choice;
		MenuTest.getMenu();

		while ((choice = MenuTest.menuList()) != 0) {
			switch (choice) {

			case 1:
				sc.nextLine();
				MenuTest.addItem();
				break;
			case 2:
				int option;
				while ((option = MenuTest.subMenuList()) != 0) {
					Comparator<Item> comparator = null;
					switch (option) {
					case 1:
						comparator = new NameComparator();
						break;
					case 2:
						comparator = new PriceComparator();
						break;
					case 3:
						comparator = new QuantityComparator();
						break;

					}
					sc.nextLine();
					MenuTest.showMenu(comparator);

				}
				break;
			case 3:
				sc.nextLine();
				MenuTest.addToCart();
				break;
			case 4:
				sc.nextLine();
				MenuTest.removeFromCart();
				break;
			case 5:
				sc.nextLine();
				MenuTest.viewCart();
				break;
			case 6:
				sc.nextLine();
				MenuTest.checkout();
				break;

			}
			System.out.println();

		}
		System.out.println("Thanks for visiting!");

	}

}