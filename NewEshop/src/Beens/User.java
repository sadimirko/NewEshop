package Beens;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int cislo;
	private String meno;
	private String priezvisko;
	private boolean admin = false;
	private String login;
	private String heslo;
	
	public User(){
	}
	public User(int cisloUzivatela,String meno, String priezvisko, boolean admin) {
		this.cislo = cisloUzivatela;
		this.meno = meno;
		this.priezvisko = priezvisko;
		this.admin = admin;
	}

	public User(int cisloUzivatela, String meno, String priezvisko, boolean admin, String login, String heslo) {
		this.cislo = cisloUzivatela;
		this.meno = meno;
		this.priezvisko = priezvisko;
		this.admin = admin;
		this.login = login;
		this.heslo = heslo;
	}
	public int getCisloUzivatela() {
		return cislo;
	}
	public void setCisloUzivatela(int cisloUzivatela) {
		this.cislo = cisloUzivatela;
	}
	public String getMeno() {
		return meno;
	}
	public void setMeno(String meno) {
		this.meno = meno;
	}
	public String getPriezvisko() {
		return priezvisko;
	}
	public void setPriezvisko(String priezvisko) {
		this.priezvisko = priezvisko;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getHeslo() {
		return heslo;
	}
	public void setHeslo(String heslo) {
		this.heslo = heslo;
	}
	@Override
	public String toString() {
		return "\n[ id=" + cislo + ", name=" + meno + ", lastname=" + priezvisko + ", admin=" + admin
				+ ", login=" + login + ", pass=" + heslo + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (admin ? 1231 : 1237);
		result = prime * result + cislo;
		result = prime * result + ((heslo == null) ? 0 : heslo.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((meno == null) ? 0 : meno.hashCode());
		result = prime * result + ((priezvisko == null) ? 0 : priezvisko.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (admin != other.admin)
			return false;
		if (cislo != other.cislo)
			return false;
		if (heslo == null) {
			if (other.heslo != null)
				return false;
		} else if (!heslo.equals(other.heslo))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (meno == null) {
			if (other.meno != null)
				return false;
		} else if (!meno.equals(other.meno))
			return false;
		if (priezvisko == null) {
			if (other.priezvisko != null)
				return false;
		} else if (!priezvisko.equals(other.priezvisko))
			return false;
		return true;
	}
	
	
}
