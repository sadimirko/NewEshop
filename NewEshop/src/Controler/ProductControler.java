package Controler;
import Dao.*;
import java.util.Scanner;
import Beens.*;

public class ProductControler {
	private  ProductsDao zoznamProduktov = new ProductsDao();
	private static final Scanner sc = new Scanner(System.in);
	
	public void inic(){
		//zoznamProduktov.pridajProdukt(new Product(1, "Lopata", "toto je lopata", 14.5));
		//zoznamProduktov.pridajProdukt(new Product(2, "Motyka", "toto je motyka", 16.0));
		//zoznamProduktov.pridajProdukt(new Product(3, "Hrable", "toto su hrable", 17.0));
		//zoznamProduktov.insertProductJDBC(1, "Vedro", "toto je vedro", 4.5);
		//zoznamProduktov.saveProductJDBC();
		zoznamProduktov.loadProductsJDBC();
	}
	
	public void pridajPr() {
		String nazov;
		String popis;
		double cena;
		System.out.println("Zadaj nazov produktu");
		nazov = sc.nextLine();
		System.out.println("Zadaj popis produktu");
		popis = sc.nextLine();
		System.out.println("Zadaj cenu produktu");
		cena = sc.nextDouble();
		Product product = new Product(zoznamProduktov.sizeProducts()+1, nazov, popis, cena);
		zoznamProduktov.pridajProdukt(product);
		zoznamProduktov.insertProductJDBC(product.getCisloProduktu(), product.getNazov(), product.getPopis(), product.getCena());
	}
	public void zmazPr() {
		int znak;
		System.out.println("*---Vyber produkt ktory chces zmazat---* ");
		System.out.println();
		System.out.println(zoznamProduktov.toString());
		System.out.println("Zadaj cislo produktu ktory chces zmazat");
		znak = sc.nextInt();
		zoznamProduktov.zmazProdukt(znak-1);
		zoznamProduktov.deleteProductJDBC(znak);
		System.out.println("Produkt cislo "+znak+"bol zmazany");
	}
	public String listProducts(){
		return zoznamProduktov.toString();
	}

}
