package Dao;

import Beens.*;
import java.sql.*;
import java.util.*;

public class OrdersDao {
	private List<Order> zoznamObjednavok = new ArrayList<>();
	private static final String CONNECT = "jdbc:mysql://localhost/eshop?user=root&password=";
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	public OrdersDao(List<Order> zoznamObjednavok) {
		this.zoznamObjednavok = zoznamObjednavok;
	}

	public OrdersDao() {

	}

	public void pridajObjednavku(Order objednavka) {
		zoznamObjednavok.add(objednavka);
	}

	public void zmazObjednavku(Order objednavka) {
		zoznamObjednavok.remove(objednavka);
	}

	public void zmazObjednavku(int cislo) {
		zoznamObjednavok.remove(cislo);
	}

	public void payOrder(int i) {
		zoznamObjednavok.get(i).setZaplatena(true);
	}

	@Override
	public String toString() {
		return "Objednavky: " + zoznamObjednavok + "";
	}

	public String userOrders(User user) {
		String s = "My orders:";
		for (Order order : zoznamObjednavok) {
			if (order.getZakaznik().equals(user)) {
				s += order.toString();
			}
		}
		return s;
	}

	public int sizeOrders() {
		return zoznamObjednavok.size();
	}

	public void insertOrderJDBC(int id, List<Product> ordered, boolean payed, User user) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONNECT);
			stmt = conn.createStatement();
			int id_product;
			for (Product product : ordered) {
				id_product = product.getCisloProduktu();
				String sql = "INSERT INTO orderproduct (id_order, id_user, id_product, payed)" + "VALUES ('" + id
						+ "','" + user.getCisloUzivatela() + "','" + id_product + "','" + 0 + "')";
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
		System.out.println("Product was add !");
	}

	public void deleteOrderJDBC(int i) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONNECT);
			stmt = conn.createStatement();
			String sql = "DELETE from orderproduct WHERE order_id='" + i + "'";
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

	public String listOrdersJDBC() {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONNECT);
			stmt = conn.createStatement();
			String sql = "SELECT id,order_id, id_user, id_product, payed FROM orderproduct";
			ResultSet rs = stmt.executeQuery(sql);
			String s = "";
			while (rs.next()) {
				int id = rs.getInt("id");
				int id_order = rs.getInt("id_order");
				int id_user = rs.getInt("id_user");
				int id_product = rs.getInt("id_product");
				int payed = rs.getInt("payed");
				s += "\n" + "Id: " + id + "Id_order:" + id_order + "User: " + id_user + "\nId_Product: " + id_product
						+ "Payed: " + payed;
				return s;
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

	public void saveOrdersJDBC() {
		Connection conn = null;
		Statement stmt = null;
		int id = 0;
		int payed = 0;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONNECT);
			stmt = conn.createStatement();
			for (Order order : zoznamObjednavok) {
				for (Product suborder : order.getZoznamProduktov()) {
					if (order.isZaplatena())
						payed = 1;
					else
						payed = 0;
					String sql = "INSERT INTO orderproduct id,(id_order, id_user, id_product, payed)" + "VALUES ('"
							+ ++id + "','" + order.getCisloObjednavky() + "'," + suborder.getCisloProduktu() + "',"
							+ order.getZakaznik().getCisloUzivatela() + "'," + payed + "')";
					stmt.executeUpdate(sql);
				}
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
		System.out.println("Product was add !");
	}

	public void loadOrdersJDBC() {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONNECT);
			stmt = conn.createStatement();
			String sql = "SELECT id, id_order, id_user, id_product, payed FROM orderproduct";
			ResultSet rs = stmt.executeQuery(sql);
			// ****************************************************************************************************************************************
			boolean pay = false;
			int thisOrder = 1;
			List<Product> zoznam = new ArrayList<Product>();
			while (rs.next()) {
				
				int id = rs.getInt("id");
				int id_order = rs.getInt("id_order");
				int id_user = rs.getInt("id_user");
				int id_product = rs.getInt("id_product");
				int payed = rs.getInt("payed");
				if (payed == 1)
					pay = true;
				else
					pay = false;
				if (id_order == thisOrder){
					zoznam.add(ProductsDao.class.newInstance().vyberProdukt(id_product - 1));
				}
				if (id_order > thisOrder) {
					Order nova = new Order(zoznamObjednavok.size()+1, zoznam, UsersDao.class.newInstance().returnUser(id_user), pay);
					zoznamObjednavok.add(nova);
					zoznam = new ArrayList<Product>();
					zoznam.add(ProductsDao.class.newInstance().vyberProdukt(id_product - 1));
				}
				
				thisOrder = id_order;
				if (rs.isLast()){
					Order nova = new Order(zoznamObjednavok.size()+1, zoznam, UsersDao.class.newInstance().returnUser(id_user), pay);
					zoznamObjednavok.add(nova);
				}
				//Order nova = new Order(id_order, zoznam, UsersDao.class.newInstance().returnUser(id_user), pay);
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
		System.out.println("Nothing found...");
	}
}
