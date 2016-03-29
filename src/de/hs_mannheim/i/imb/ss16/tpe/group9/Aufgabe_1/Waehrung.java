package de.hs_mannheim.i.imb.ss16.tpe.group9.Aufgabe_1;

public class Waehrung {

	//3 Klassenvariablensaa
	private String name;
	private String kuerzel;
	private long kurs;

	//Konstruktor zum Setzen der Variablen bei Erstellung eines Objekts
	public Waehrung(String name, String kuerzel, long kurs) {
		this.name = name;
		this.kuerzel = kuerzel;
		this.kurs = kurs;
	}

	//get-Methoden
	public String getName() {
		return this.name;
	}

	public String getKuerzel() {
		return this.kuerzel;
	}

	public long getKurs() {
		return this.kurs;
	}

	//Umrechen-Methode mit Rückgabetyp long
	//Rechnet Beträge von einer Waehrung in eine andere um
	public long umrechnen(long Betrag, Waehrung Zielwaehrung) {
		// Umwandlung in USD
		//*100 um Rundungsfehler zu vermeiden
		long hilfe = (Betrag * 100) * this.kurs;
		// Umwandlung in Zielwaehrung
		hilfe = hilfe / Zielwaehrung.kurs;
		//Teilen durch 100 um auf Betragsgröße zu kommen
		hilfe = hilfe / 100;
		return hilfe;
	}

	@Override
	public String toString() { 
		double dhilfe;
		// Keine Klammer um ganze Rechnung weil (double) sich nur auf urspr.
		// long-wert this.kurs bezieht
		dhilfe = (double) (this.kurs) / 10000;
		return this.name + " [" + this.kuerzel + "] 1 $ = " + dhilfe + " " + this.kuerzel;
	}
	
//Equals-methode zum Vergleichen von 2 Objekten
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Waehrung other = (Waehrung) obj;
		if (kuerzel == null) {
			if (other.kuerzel != null)
				return false;
		} else if (!kuerzel.equals(other.kuerzel))
			return false;
		if (kurs != other.kurs)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
//Hash-Methode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kuerzel == null) ? 0 : kuerzel.hashCode());
		result = prime * result + (int) (kurs ^ (kurs >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

}
