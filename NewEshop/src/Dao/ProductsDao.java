package Dao;
import Beens.*;
import java.util.*;

public class ProductsDao {
	public static List<Product> zoznamProduktov = new ArrayList<>();

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
		
}
