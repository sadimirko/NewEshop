package Controler;
import Dao.*;
import Beens.*;
import java.util.*;

public class OrderControler {
	private static final Scanner sc = new Scanner(System.in);
	private OrdersDao zoznamObjednavok = new OrdersDao();
	//private  ProductsDao zoznamProduktov = new ProductsDao();
	//private User zakaznik ; 
	
	public void pridajOb(User loged) {
	
		List <Product> objednane = new ArrayList<Product>();		
		System.out.println();
		Integer znak = 99;
		while (znak != (0)){
			System.out.println("*---Vyber produkt ktory chces pridat do objednavky---* ");
			System.out.println();
			//System.out.println(zoznamProduktov.toString());
			System.out.print("Proeducts: ");
			System.out.println(ProductsDao.zoznamProduktov.toString());
			System.out.println("Zadaj cislo produktu");
			znak = sc.nextInt();
			objednane.add(ProductsDao.zoznamProduktov.get(znak-1));
			System.out.println("chces pridat dalsi  ak nie stlac 0");
			znak = sc.nextInt();
		}
		Order nova = new Order(zoznamObjednavok.sizeOrders()+1, objednane, loged, false);
		zoznamObjednavok.pridajObjednavku(nova);
	}
	public String  listOrders(){
		return zoznamObjednavok.toString();
	}
	
	public String  listOrders(User user){
		return zoznamObjednavok.userOrders(user);
	}
	
	public void deleteOrder(){
		System.out.println("select number for delete ");
		listOrders();
		int i =  sc.nextInt();
		zoznamObjednavok.zmazObjednavku(i);
		System.out.println("Order "+i+" was deleted.");
	}
	public void payOrder(User user){
		System.out.println("Select number of order for pay ");
		System.out.println(listOrders(user));
		int i =  sc.nextInt();
		zoznamObjednavok.payOrder(i-1);
		System.out.println("Order "+i+" was payed.");
	}
}
