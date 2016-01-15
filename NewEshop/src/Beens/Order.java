package Beens;

import java.io.Serializable;
import java.util.*;

public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int cisloObjednavky;
	private List<Product> zoznamProduktov = new ArrayList<>();
	private double celkovaCena;
	private boolean zaplatena;
	private User zakaznik ;
	
	public Order(int cisloObjednavky, List<Product> zoznamProduktov, 
			User zakaznik, boolean zaplatena) {
		this.cisloObjednavky = cisloObjednavky;
		this.zoznamProduktov = zoznamProduktov;
		this.zaplatena = zaplatena;
		this.zakaznik = zakaznik;
		double cena = 0.0;
		for (Product product : zoznamProduktov) {
			 cena +=  product.getCena();
		}
		this.celkovaCena = cena; 
	}

	public int getCisloObjednavky() {
		return cisloObjednavky;
	}

	public void setCisloObjednavky(int cisloObjednavky) {
		this.cisloObjednavky = cisloObjednavky;
	}

	public List<Product> getZoznamProduktov() {
		return zoznamProduktov;
	}

	public void setZoznamProduktov(List<Product> zoznamProduktov) {
		this.zoznamProduktov = zoznamProduktov;
	}

	public double getCelkovaCena() {
		return celkovaCena;
	}

	public void setCelkovaCena(double celkovaCena) {
		this.celkovaCena = celkovaCena;
	}

	public boolean isZaplatena() {
		return zaplatena;
	}

	public void setZaplatena(boolean zaplatena) {
		this.zaplatena = zaplatena;
	}

	public User getZakaznik() {
		return zakaznik;
	}

	public void setZakaznik(User zakaznik) {
		this.zakaznik = zakaznik;
	}

	@Override
	public String toString() {
		return "\n cislo=" + cisloObjednavky + ",\n zoznamProduktov=" + zoznamProduktov
				+ ",\n celkovaCena=" + celkovaCena + " , zaplatena=" + zaplatena + ", zakaznik=" + zakaznik.getLogin()+" id:"+zakaznik.getCisloUzivatela()+ "";
	}
}
