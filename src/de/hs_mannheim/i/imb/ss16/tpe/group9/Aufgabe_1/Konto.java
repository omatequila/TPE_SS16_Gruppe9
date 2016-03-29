package de.hs_mannheim.i.imb.ss16.tpe.group9.Aufgabe_1;

public class Konto {

	// 4 private Klassenvariablen, Erstellung eines Arrays zu Abspeichern der Beträge
	private String inhaber;
	private Waehrung waehrung;
	private Betrag saldo;
	private Betrag betraege[] = new Betrag[100];
	private int betragszaehler = 0;

	// Konstruktor zum Setzen der Variablen bei Erstellung eines Objekts inkl. Betrag
	public Konto(String inhaber, Waehrung waehrung, Betrag saldo) {
		this.inhaber = inhaber;
		this.waehrung = waehrung;
		this.saldo = saldo;
		betraege[0] = saldo;
	}
	// Konstruktor zum Setzen der Variablen bei Erstellung eines Objekts und Betrag gleich 0
	public Konto(String inhaber, Waehrung waehrung) {
		this.inhaber = inhaber;
		this.waehrung = waehrung;
		Betrag saldo = new Betrag(0, waehrung);
		this.saldo = saldo;
		betraege[0] = saldo;
	}

	// To-String Methode zum Ausgeben des Objekts als String
	public String toString() {
		// Variable initialisieren, um innerhalb und außerhalb der Schleife zu verwenden
		String buchungen = ""; 
		//For-Schleife zum Speichern der Buchungen als String
		for (int i = 0; i < betraege.length; i++) {
			if (betraege[i] == null) {
				break;
			}
			buchungen = buchungen + (betraege[i]) + "\n";
		}
		//Ausgabe des Kontoauszugs
		return "Kontoinhaber: " + this.inhaber + "\n" + "Währung: " + this.waehrung.getName() + "\n" + "----------"
				+ "\n" + buchungen + "----------" + "\n" + "Saldo: " + this.saldo.getAsDouble() + " "
				+ this.waehrung.getKuerzel();

	}
	//Hash-Methode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((betraege == null) ? 0 : betraege.hashCode());
		result = prime * result + ((inhaber == null) ? 0 : inhaber.hashCode());
		result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
		result = prime * result + ((waehrung == null) ? 0 : waehrung.hashCode());
		return result;
	}

	//Equals Methode zum Vergleichen von zwei Objekten des Typs Konto
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Konto other = (Konto) obj;
		if (betraege == null) {
			if (other.betraege != null)
				return false;
		} else if (!betraege.equals(other.betraege))
			return false;
		if (inhaber == null) {
			if (other.inhaber != null)
				return false;
		} else if (!inhaber.equals(other.inhaber))
			return false;
		if (saldo == null) {
			if (other.saldo != null)
				return false;
		} else if (!saldo.equals(other.saldo))
			return false;
		if (waehrung == null) {
			if (other.waehrung != null)
				return false;
		} else if (!waehrung.equals(other.waehrung))
			return false;
		return true;
	}

	//Get-Methoden
	public String getInhaber() {
		return this.inhaber;
	}

	public Betrag getSaldo() {
		return this.saldo;
	}

	public Betrag getBetraege(int ziel) {
		Betrag betragX;
		betragX = this.betraege[ziel];
		return betragX;
	}
	
	public Waehrung getWaehrung() {
		return this.waehrung;
	}

	//Buchen-Methode zum Buchen eines Betrags
	public void buche(Betrag betrag) {
		long saldoneu;
		Betrag betragneu;
		//Vergleichen ob aktuelle Waehrung gleich Zielwaehrung + evtl Umrechnung
		if (betrag.getWaehrung().equals(this.getWaehrung())) {
			//Erstellung eines neuen Objekts; Konstruktor mit "true", weil Betrag bereits *100 genommen
			betragneu = new Betrag(betrag.getBetrag(), this.waehrung, true);
		} else {
			betragneu = new Betrag(betrag.getWaehrung().umrechnen(betrag.getBetrag(), this.waehrung), this.waehrung,
					true);
		}
		//Abziehen oder Aufaddieren der Beträge auf Konto
		if (betrag.getBetrag() < 0) {
			saldoneu = saldo.subtrahiere(betragneu);
		} else {
			saldoneu = saldo.addiere(betragneu);
		}
		//Stelle im Array "Beträge" festlegen 
		betragszaehler = betragszaehler + 1;
		betraege[betragszaehler] = betragneu;
		//Erstellung eines neuen Objekts Saldo
		saldo = new Betrag(saldoneu, this.waehrung, true);

	}

	public long saldo() {
		return this.saldo.getBetrag();
	}
	//Gebuehren-methode mit Übergabe der Variablen pwert
	public long gebuehren(long pwert) {
		long erg = this.saldo.promille(pwert);
		//Speichern des Ergebnisses in neuer Varibale
		Betrag gebuehrenBetrag = new Betrag(erg, this.waehrung);
		this.saldo.subtrahiere(gebuehrenBetrag);
		//Ausgabe des Saldos nach Abzug der Gebuehren
		return erg;
	}

}
