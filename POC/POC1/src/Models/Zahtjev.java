package Models;

import java.sql.Date;



public class Zahtjev {
	private long _id;
	private Klijent _klijent;
	private String _tipUredaja;
	private boolean _garancija;
	private String _komentar;
	private Date _datumZatvaranja;
	private Date _datumOtvaranja;
	private Zaposlenik _zaposlenik;
	private String _status;
	private int _prioritet;
	private double _cijena;
	
	public Zahtjev(){}
    
	public long getID() {
		return _id;
	}

	public void setID(long _id) {
		this._id = _id;
	}

	public Klijent getKlijent() {
		return _klijent;
	}

	public void setKlijent(Klijent _klijent) {
		this._klijent = _klijent;
	}

	public String getTipUredaja() {
		return _tipUredaja;
	}

	public void setTipUredaja(String _tipUredaja) {
		this._tipUredaja = _tipUredaja;
	}

	public boolean getGarancija() {
		return _garancija;
	}

	public void setGarancija(boolean _garancija) {
		this._garancija = _garancija;
	}

	public String getKomentar() {
		return _komentar;
	}

	public void setKomentar(String _komentar) {
		this._komentar = _komentar;
	}

	public Date getDatumZatvaranja() {
		return _datumZatvaranja;
	}

	public void setDatumZatvaranja(Date _datumZatvaranja) {
		this._datumZatvaranja = _datumZatvaranja;
	}

	public Date getDatumOtvaranja() {
		return _datumOtvaranja;
	}

	public void setDatumOtvaranja(Date _datumOtvaranja) {
		this._datumOtvaranja = _datumOtvaranja;
	}

	public Zaposlenik getZaposlenik() {
		return _zaposlenik;
	}

	public void setZaposlenik(Zaposlenik _zaposlenik) {
		this._zaposlenik = _zaposlenik;
	}

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public int getPrioritet() {
		return _prioritet;
	}

	public void setPrioritet(int _prioritet) {
		this._prioritet = _prioritet;
	}

	public double get_cijena() {
		return _cijena;
	}

	public void set_cijena(double _cijena) {
		this._cijena = _cijena;
	}
}
