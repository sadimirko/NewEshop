package Dao;
import Beens.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class UsersDao {
	private List<User> zoznamUzivatelov = new ArrayList<>();

	public UsersDao(){}
	
	public UsersDao(List<User> zoznamUzivatelov) {
		this.zoznamUzivatelov = zoznamUzivatelov;
	}
		
	public void pridajUzivatela(User uzivatel){
		zoznamUzivatelov.add(uzivatel);
	}
	public void zmazUzivatela(User uzivatel){
		zoznamUzivatelov.remove(uzivatel);
	}
	public void zmazUzivatela(int cislo){
		zoznamUzivatelov.remove(cislo);
	}
	
	public int returnSize(){
		return zoznamUzivatelov.size();
	}
	
	private boolean isPass(String pass){
		for (User user : zoznamUzivatelov) {
			if (pass.equals(user.getHeslo())) {
				return true;
			} 	
		}
		return false;
	}
	
	private boolean isLogin(String login){
		for (User user : zoznamUzivatelov) {
			if (login.equals(user.getLogin())) {
				return true;
			} 	
		}
		return false;
	}
	
	private boolean isU(String login, String pass){
		for (User user : zoznamUzivatelov) {
			if (pass.equals(user.getHeslo()) && login.equals(user.getLogin())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isUser(String login, String pass){
		if (isLogin(login) && isPass(pass) && isU(login, pass)){
			return true;
		}
		return false;
	}
	
	public User returnUser(String login, String pass){
		for (User user : zoznamUzivatelov) {
			if (pass.equals(user.getHeslo()) && login.equals(user.getLogin())) {
				return user;
			}
		}
		return null;
	}
	
	public void setAdmin(int i){
		zoznamUzivatelov.get(i).setAdmin(true);
	}
	
	@Override
	public String toString() {
		return "Users: " + zoznamUzivatelov + "";
	} 
	
	public void insertUserJDBC(int i, String name, String lastname, boolean admin, String login , String password){
		Connection conn = null;
		Statement stmt = null;
		int a;
		if (admin) a = 1; else a = 0;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/eshop?user=root&password=");
			stmt = conn.createStatement();
			String sql = "INSERT INTO user (id,name, last_name, admin, login, password)" +
			"VALUES ('" + i + "','" + name + "','" + lastname + "'," + a + ",'" + login + "','" + password + "')";
			stmt.executeUpdate(sql);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Registracia prebehla uspesne !");
	}
}
