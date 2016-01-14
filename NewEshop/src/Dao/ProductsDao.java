package Dao;
import Beens.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ProductsDao {
	public static List<Product> zoznamProduktov = new ArrayList<>();
	private static final String CONNECT = "jdbc:mysql://localhost/eshop?user=root&password=" ;
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	public ProductsDao(){
		
	}
	public ProductsDao(List<Product> zoznamProduktov) {
		ProductsDao.zoznamProduktov = zoznamProduktov;
	}
	
	public void pridajProdukt(Product produkt){
		zoznamProduktov.add(produkt);
	}
	public void zmazProdukt(Product produkt){
		zoznamProduktov.remove(produkt);
	}
	public void zmazProdukt(int cislo){
		zoznamProduktov.remove(cislo);
	}
	public Product vyberProdukt(int i){
		return zoznamProduktov.get(i);
	}
	
	public int sizeProducts(){
		return zoznamProduktov.size();
	}

	@Override
	public String toString() {
		return "Product: " + zoznamProduktov + "";
	}
	
	public void insertProductJDBC(int id, String name, String description, double price){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONNECT);
			stmt = conn.createStatement();
			String sql = "INSERT INTO product (id, name, description, price)" +
			"VALUES ('" + id + "','" + name + "','" + description + "'," + price + "')";
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
		System.out.println("Product was add !");
	}
	
	public void deleteProductJDBC(int i){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONNECT);
			stmt = conn.createStatement();
			String sql = "DELETE from product WHERE id='"+i+"'";
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
	
	public String listProductJDBC(int i)  {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONNECT);
			stmt = conn.createStatement();
			String sql = "SELECT id, name, description, price FROM product";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String des = rs.getString("description");
				double price = rs.getDouble("price");
				if (id == i) {
					return  "Id: "+id +"Name: "+ name +"\nDescription: " + des + "Price: "+price;
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
	public void listProductsJDBC()  {
		Connection conn = null;
		Statement stmt = null;
		String s = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONNECT);
			stmt = conn.createStatement();
			String sql = "SELECT id,name, description, price FROM product";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String price = rs.getString("price");
				s =  "Id: "+id +"Name: "+ name +"\nDescription: " + description + "\n Price: "+price;
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
	
	
	
	public void loadProductsJDBC()  {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONNECT);
			stmt = conn.createStatement();
			String sql = "SELECT id,name, description, price FROM product";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String des = rs.getString("description");
				double price = rs.getDouble("price");
				Product p = new Product(id, name, des, price);
				zoznamProduktov.add(p);
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
		System.out.println("Import products was OK");
	}
	public void saveProductJDBC(){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONNECT);
			stmt = conn.createStatement();
			for (Product product : zoznamProduktov) {
			String sql = "INSERT INTO product (id,name, description, price)" +
			"VALUES ('" + product.getCisloProduktu() + "','" + product.getNazov() + "','" + product.getPopis() + "'," + product.getCena() 
			+ "')";
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
		System.out.println("Save OK !");
	}
}
