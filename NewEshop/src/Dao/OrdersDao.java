package Dao;
import Beens.*;
import java.util.*;
public class OrdersDao {
	private List<Order> zoznamObjednavok = new ArrayList<>();

	public OrdersDao(List<Order> zoznamObjednavok) {
		this.zoznamObjednavok = zoznamObjednavok;
	}
	public OrdersDao(){
		
	}
		
	public void pridajObjednavku(Order objednavka){
		zoznamObjednavok.add(objednavka);
	}
	public void zmazObjednavku(Order objednavka){
		zoznamObjednavok.remove(objednavka);
	}
	public void zmazObjednavku(int cislo){
		zoznamObjednavok.remove(cislo);
	}
	
	public void payOrder(int i){
		zoznamObjednavok.get(i).setZaplatena(true);
	}
		
	@Override
	public String toString() {
		return "Objednavky: " + zoznamObjednavok + "";
	}
	
	public String userOrders(User user){
		String s = "My orders:";
		for (Order order : zoznamObjednavok) {
			if (order.getZakaznik().equals(user)){
				s += order.toString(); 
			}
		}
		return s;
	}
	public int sizeOrders(){
		return zoznamObjednavok.size();
	}
}
