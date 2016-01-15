package Dao;
import Beens.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class UsersDao {
	public static List<User> zoznamUzivatelov = new ArrayList<>();
	private static final String CONNECT = "jdbc:mysql://localhost/eshop?user=root&password=" ;
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	public UsersDao(){}
	
			
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
	public User returnUser(int i){
		for (User user : zoznamUzivatelov) {
			if (user.getCisloUzivatela() == i) return user;  
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
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONNECT);
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
		System.out.println("Insert was OK !");
	}
	
	public void deletetUserJDBC(int i){
		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONNECT);
			stmt = conn.createStatement();
			String sql = "DELETE from user WHERE id='"+i+"'";
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
		System.out.println("Delete OK");
	}
	
	public void updateAdminJDBC(int i){
		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONNECT);
			stmt = conn.createStatement();
			String sql = "Update user SET admin='1' WHERE user.id='"+i+"'";
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
		System.out.println("Update OK");
	}
	
	public String listUserJDBC(int i,String log)  {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONNECT);
			stmt = conn.createStatement();
			String sql = "SELECT id,name, last_name, admin, login, password FROM user";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String lastname = rs.getString("last_name");
				String login = rs.getString("login");
				String pass = rs.getString("passwrd");
				if (login.equals(log)&& (i == id)) {
					return  "Id: "+id +"\nName: "+ name +"Last name: " + lastname + "\nLogin : "+login
							+ "Password: "+pass;
				}
			}
			rs.close();
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
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} 
		}
		return "Nothing found...";
	}
	public void listUsersJDBC()  {
		Connection conn = null;
		Statement stmt = null;
		String s = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONNECT);
			stmt = conn.createStatement();
			String sql = "SELECT id,name, last_name, admin, login, password FROM user";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String lastname = rs.getString("last_name");
				String login = rs.getString("login");
				String pass = rs.getString("passwrd");
				s =  "Id: "+id +"\nName: "+ name +"Last name: " + lastname + "\nLogin : "+login
							+ "Password: "+pass;
				System.out.println(s);
			}
			rs.close();
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
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} 
		}
		if (s.equals(null)) s= "Nothing found... ";
	}
	
	public boolean loginUserJDBC(String log, String password)  {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONNECT);
			stmt = conn.createStatement();
			String sql = "SELECT login, password FROM user";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String login = rs.getString("login");
				String pass = rs.getString("passwrd");
				if (login.equals(log)&& (pass.equals(password))) {
					return true;
				}
			}
			rs.close();
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
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} 
		}
		return false;
	}
	
	public void loadtUsersJDBC()  {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONNECT);
			stmt = conn.createStatement();
			String sql = "SELECT id,name, last_name, admin, login, password FROM user";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String lastname = rs.getString("last_name");
				int a = rs.getInt("admin");
				boolean admin;
				if (a == 1) admin = true; else admin = false; 
				String login = rs.getString("login");
				String pass = rs.getString("password");
				User u = new User(id, name, lastname, admin, login, pass);
				zoznamUzivatelov.add(u);
			}
			rs.close();
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
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} 
		}
		System.out.println("Import users was OK");
	}
	
	public void saveUsersJDBC(){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONNECT);
			stmt = conn.createStatement();
			for (User user : zoznamUzivatelov) {
			String sql = "INSERT INTO user (id,name, last_name, admin, login, password)" +
			"VALUES ('" + user.getCisloUzivatela() + "','" + user.getMeno() + "','" + user.getPriezvisko() + "'," + user.isAdmin() 
			+ ",'" + user.getLogin() + "','" + user.getHeslo() + "')";
			stmt.executeUpdate(sql);
			}
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
		System.out.println("Insert was OK !");
	}
}
