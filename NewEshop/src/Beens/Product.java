package Beens;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int productId;
	private String name;
	private String description;
	private double price = 0;
	
	public Product(){}
	
	public Product(int cisloProduktu,String nazov,String popis,double cena){
		this.productId = cisloProduktu;
		this.name = nazov;
		this.description = popis;
		this.price = cena;
	}
	
	public int getCisloProduktu() {
		return productId;
	}

	public void setCisloProduktu(int cisloProduktu) {
		this.productId = cisloProduktu;
	}

	public String getNazov() {
		return name;
	}
	public void setNazov(String nazov) {
		this.name = nazov;
	}
	public String getPopis() {
		return description;
	}
	public void setPopis(String popis) {
		this.description = popis;
	}
	public double getCena() {
		return price;
	}
	public void setCena(double cena) {
		this.price = cena;
	}

	@Override
	public String toString() {
		return "\n[id:" + productId + ", nazov=" + name + ", popis=" + description + ", cena=" + price
				+ "]";
	}
	
}
