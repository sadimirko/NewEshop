package Controler;
import java.util.Scanner;

import Beens.User;
import Dao.UsersDao;

public class UserControler {
	private static final Scanner sc = new Scanner(System.in);
	private  UsersDao zoznamUzivatelov = new UsersDao();
	
	
	
	public void inic(){
		zoznamUzivatelov.pridajUzivatela(new User(1, "admin", "admin", true, "admin", "pass"));
		zoznamUzivatelov.pridajUzivatela(new User(2, "user", "user", false, "user", "pass"));
	}
	
	@Override
	public String toString() {
		return "Users [" + zoznamUzivatelov + "]";
	}
	
	public void addUser(User uzivatel){
		zoznamUzivatelov.pridajUzivatela(uzivatel);
	}
	
	public void deleteUser(int index){
		zoznamUzivatelov.zmazUzivatela(index);
	}
	public void deleteUser(User user){
		zoznamUzivatelov.zmazUzivatela(user);
	}
		
	public String listUsers(){
		return zoznamUzivatelov.toString();
	}
	
	public int returnSizeUsers(){
		return zoznamUzivatelov.returnSize();
	}
	public boolean isUser(String login, String pass){
		if (zoznamUzivatelov.isUser(login, pass)) return true;
		return false;
	}
	
	public User returnUser(String login, String pass){
		
		return zoznamUzivatelov.returnUser(login, pass);
		
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
		User neww = new User(returnSizeUsers() + 1, name, lastname, false, login, pass);
		addUser(neww);
		System.out.println("User added ...");
		zoznamUzivatelov.insertUserJDBC(returnSizeUsers() + 1,name, lastname, false, login, pass);
	}
	
	public void setAdmin(){
		System.out.println("***  Select number of new Admin  ***");
		listUsers();
		int i = sc.nextInt();
		zoznamUzivatelov.setAdmin(i);
	}
			
}
