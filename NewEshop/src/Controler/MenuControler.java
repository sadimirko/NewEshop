package Controler;

import java.util.Locale;
import java.util.Scanner;

import Beens.User;

public class MenuControler {
	private static final Scanner sc = new Scanner(System.in);
	private ProductControler products = new ProductControler();
	private UserControler users = new UserControler();
	private OrderControler orders = new OrderControler();
	private static User loged;

	public void run() {
		String znak = new String();
		orders.inic();
		if (loged.isAdmin())
			menuAdmin();
		else
			menuUser();

		while (!znak.equals("0")) {
			System.out.println("************************************************");
			System.out.println("Press number from menu!");
			
			znak = sc.next();
			switch (znak) {
			case "1":
				System.out.println("*--- List Products---*");
				System.out.println(products.listProducts());
				break;
			case "2":
				if (loged.isAdmin()) {
					System.out.println("Press 2 - List all orders");
					System.out.println(orders.listOrders());
				} else {
					System.out.println("Press 2 - List my orders");
					System.out.println(orders.listOrders(loged));
				}
				break;
			case "3":
				if (loged.isAdmin()) {
					System.out.println("Press 3 - Add product");
					products.pridajPr();
				} else {
					System.out.println("Press 3 - Add order");
					orders.pridajOb(loged);
				}
				break;
			case "4":
				if (loged.isAdmin()) {
					System.out.println("Press 4 - Delete product");
					products.zmazPr();
				} else {
					System.out.println("Press 4 - Pay order");
					orders.payOrder(loged);
				}
				break;
			case "5":
				if (loged.isAdmin()) {
					System.out.println("Press 5 - Add order");
					orders.pridajOb(loged);
				} else {
					System.out.println("Press 5 - Delete order");
					orders.deleteOrder();
				}
				break;
			case "6":
				if (loged.isAdmin()) {
					System.out.println("Press 6 - List users");
					System.out.println(users.listUsers());
				} else {
					System.out.println("Press 6 - List my info");
					System.out.println("User: "+loged.getLogin());
					System.out.println(loged.toString());
				}
				break;
			case "7":
				if (loged.isAdmin()) {
					System.out.println("Press 7 - Add user");
					users.createUser();
				} else {
					System.out.println("Press 7");
				}
				break;
			case "8":
				if (loged.isAdmin()) {
					System.out.println("Press 8 - Set user as Admin");
					users.setAdmin();
				} else {
					System.out.println("Press 8 ");
				}
				break;
			case "9":
				if (loged.isAdmin()) {
					menuAdmin();
				} else {
					menuUser();
				}
				break;
			}
		}
		System.out.println("User: "+loged.getLogin()+" was log out");
		loged = null;
	}

	public void menuAdmin() {
		System.out.println();
		System.out.println("*****************   MENU ADMIN  *********************");
		System.out.println("Press 1 - List products");
		System.out.println("Press 2 - List all orders");
		System.out.println("Press 3 - Add product");
		System.out.println("Press 4 - Delete product");
		System.out.println("Press 5 - Add order");
		System.out.println("Press 6 - List users");
		System.out.println("Press 7 - Add user");
		System.out.println("Press 8 - Set user as Admin");
		System.out.println("Press 9 - Admin Menu");
		System.out.println("Press 0 - For log out");
	}

	public void menuUser() {
		System.out.println();
		System.out.println("*****************   MENU USER  *********************");
		System.out.println("Press 1 - List products");
		System.out.println("Press 2 - List my orders");
		System.out.println("Press 3 - Add order");
		System.out.println("Press 4 - Pay order");
		System.out.println("Press 5 - Delete order");
		System.out.println("Press 6 - List my info");
		System.out.println("Press 7 - ");
		System.out.println("Press 8 - ");
		System.out.println("Press 9 - User Menu");
		System.out.println("Press 0 - For log out");

	}

	public void createUser() {
		System.out.println("***  Create new User  ***");
		System.out.println("Type name");
		String name = sc.next();
		System.out.println("Type lastname");
		String lastname = sc.next();
		System.out.println("Type login");
		String login = sc.next();
		System.out.println("Type password");
		String pass = sc.next();
		User neww = new User(users.returnSizeUsers() + 1, name, lastname, false, login, pass);
		users.addUser(neww);
		
	
		loged = neww;
		System.out.println("User: "+loged.getLogin()+" was log in...");
		run();
	}

	public void loginUser() {
		boolean a = false;
		String s = "";
		while (!s.equals("0")) {
			while (!a) {
				System.out.println("***  Login existing User  ***");
				System.out.println("Type login");
				String login = sc.next();
				System.out.println("Type password");
				String pass = sc.next();
				if (users.isUser(login, pass)) {
					a = true;
					loged = users.returnUser(login, pass);
					System.out.println("User: "+loged.getLogin()+" was log in...");
					run();
					
				} else
					System.out.println("Wrong login or password type again");
				System.out.println("For exit press 0");
				s = sc.next();
				if (s.equals("0")) a = true;
			}
		}
	}

	public void loging() {
		sc.useLocale(Locale.US);
		String s = "";
		System.out.println("Welcome in our Eshop");
		users.inic();
		products.inic();
		while (!s.equals("0")) {
			System.out.println("press 1 - For log in");
			System.out.println("press 2 - For create new user");
			System.out.println("press 0 - If you want exit ");
			s = sc.next();
			switch (s) {
			case "1":
				loginUser();
				break;
			case "2":
				createUser();
				break;
			}
		}
		System.out.println("*---Bye.---*");
	}

}
