package de.hs_mannheim.i.imb.ss16.tpe.group9.Aufgabe_1;

	
	public class Betrag {
		// 2 private Klassenvariablen
		private long betrag;
		private Waehrung waehrung;
		
		//Konstruktor 1, Übergabe betrag + waehrung, 
		public Betrag (long  betrag, Waehrung waehrung){
			this.betrag = betrag*100; 	
			this.waehrung = waehrung;
		}

		public Betrag (double  betrag, Waehrung waehrung){
			this.betrag = (long)(betrag*100);	
			this.waehrung = waehrung;
		}
		
		//Neuer Konstruktor, um Probleme mit Pointer zu umgehen
		public Betrag (long betrag, Waehrung waehrung, boolean saldoFlag){ 
			this.betrag = betrag;
			this.waehrung = waehrung;
		}
		
		//Hash-Methode
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (betrag ^ (betrag >>> 32));
			result = prime * result + ((waehrung == null) ? 0 : waehrung.hashCode());
			return result;
		}

		//Equals-Methode zum Vergleichen von 2 Objekten
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Betrag other = (Betrag) obj;
			if (betrag != other.betrag)
				return false;
			if (waehrung == null) {
				if (other.waehrung != null)
					return false;
			} else if (!waehrung.equals(other.waehrung))
				return false;
			return true;
		}

		//Get-Methoden
		public long getBetrag(){
			return this.betrag;
		}
		public Waehrung getWaehrung(){
			return this.waehrung;
		}
		public long getVorzeichen(){
			if(this.betrag<0){
				return -1;
			}else{
				return 1;
			}
			
		}
		
		//Additionsmethode
		public long addiere( Betrag betragEins){
			//Speichern des bereits umgerechneten Betrags in Additionsbetrag-Variable
			long Additionsbetrag = this.waehrung.umrechnen(this.betrag, betragEins.waehrung);
				Additionsbetrag = (Additionsbetrag + betragEins.betrag);
				Additionsbetrag = betragEins.waehrung.umrechnen(Additionsbetrag, this.waehrung);
			return Additionsbetrag;
		}

		//Subtraktionsmethode
		public long subtrahiere( Betrag betragEins){
			long Subtraktionsbetrag = this.waehrung.umrechnen(this.betrag, betragEins.waehrung);
			// da betragEins.betrag negativ ist "addieren" wir die beiden Beträge
				Subtraktionsbetrag = (this.betrag + betragEins.betrag);
				Subtraktionsbetrag = betragEins.waehrung.umrechnen(Subtraktionsbetrag, this.waehrung);
			return Subtraktionsbetrag;
		}
		
		//Multiplikation mit double
		public long multipliziere(double betragEins){
			long summe = (long)(this.betrag * betragEins);
			return summe;
		}
		
		//Multiplikation mit long
		public long multipliziere(long betragEins){
			long summe = (this.betrag*betragEins);
			return summe;	
		}
		
		//Prozent-Methode und speichern in long Variable
		public long prozent(long prozentwert){
			long pwert = (this.betrag*prozentwert)/100;
			return pwert;
		}
		
		//Promille-Methode 
		public long promille(long promillewert){
			long pwert = (this.betrag*promillewert)/1000;
			return pwert;
		}

		public long getVorkomma(){
			//sebasti erkärt mir später 
			if (this.betrag > 0) {
				return this.betrag / 100;
			}
			else {
				return (this.betrag * (-1)) / 100;
			}
		}
		
		public long getNachkomma(){
			
			double KommaZahl = this.betrag;
			
			int zweiNachkommaStellen = (int)(KommaZahl * 100.0) % 100;
			return zweiNachkommaStellen;
			
		}

		// To-String Methode zum Ausgeben des Objekts als String
		@Override
		public String toString(){
			String ausgabe = this.getAsDouble()+" "+this.waehrung.getKuerzel();
			return ausgabe;
		}
		
		public double getAsDouble(){
			double dWert = (double)this.betrag/100;
			return dWert;
		}
		
		

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
