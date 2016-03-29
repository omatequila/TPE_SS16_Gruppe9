package de.hs_mannheim.i.imb.ss16.tpe.group9.Aufgabe_1;
import static org.junit.Assert.*;

//Testklasse
public class Main {

	static Betrag minusvierfuenfUSD = new Betrag(-45, Waehrungen.USD);
	static Betrag minusvierfuenfEUR = new Betrag(-45, Waehrungen.Euro);
	static Betrag minusvierfuenfYen = new Betrag(-45, Waehrungen.Yen);
	static Betrag minusvierfuenfRUB = new Betrag(-45, Waehrungen.Rubel);
	static Betrag minusvierfuenfCHF = new Betrag(-45, Waehrungen.SchweizerFranken);
	static Betrag fuenfUSD = new Betrag(5, Waehrungen.USD);
	static Betrag fuenfEUR = new Betrag(5, Waehrungen.Euro);
	static Betrag fuenfYen = new Betrag(5, Waehrungen.Yen);
	static Betrag fuenfRUB = new Betrag(5, Waehrungen.Rubel);
	static Betrag fuenfCHF = new Betrag(5, Waehrungen.SchweizerFranken);
	static Konto meins = new Konto("Max Mustermann", Waehrungen.USD, fuenfUSD);

	//Testen aller Methoden
	@org.junit.Test
	public void testAddition() {
		assertEquals(fuenfUSD.addiere(fuenfYen), 504);
	}
	
	@org.junit.Test
	public void testSubtraktion() {
		assertEquals(minusvierfuenfEUR.subtrahiere(fuenfUSD), -3152);
	}

	@org.junit.Test
	public void testMultiplikation() {
		assertEquals(fuenfUSD.multipliziere(5), 2500);
		assertEquals(fuenfUSD.multipliziere(5.0), 2500);
	}

	@org.junit.Test
	public void testPromille() {
		assertEquals(fuenfUSD.promille(2), 1);
	}

	@org.junit.Test
	public void testProzent() {
		assertEquals(fuenfEUR.prozent(100), 500);
	}

	@org.junit.Test
	public void testGetVorzeichen() {
		assertEquals(minusvierfuenfYen.getVorzeichen(), -1);
	}

	@org.junit.Test
	public void testGetVorkomma() {
		assertEquals(fuenfEUR.getVorkomma(), 5);
	}

	@org.junit.Test
	public void testGetNachkomma() {
		assertEquals(fuenfEUR.getNachkomma(), 0);
	}

	@org.junit.Test
	public void testBetragToString() {
		assertEquals(fuenfUSD.toString(), "5.0 $");
	}

	@org.junit.Test
	public void testGetAsDouble() {
		assertEquals(minusvierfuenfEUR.getAsDouble(), -45.00, 0.01);
	}

	//Testen der 3 Betragskonstruktoren
	@org.junit.Test
	public void testBetrag() {
		assertEquals(fuenfUSD, new Betrag(5, Waehrungen.USD));
	}

	@org.junit.Test
	public void testBetrag2() {
		assertEquals(fuenfUSD, new Betrag(5.0041, Waehrungen.USD));
	}

	@org.junit.Test
	public void testBetrag3() {
		assertEquals(fuenfUSD, new Betrag(500, Waehrungen.USD, true));
	}

	//Testen der 3 Waehrungskonstruktoren
	@org.junit.Test
	public void testWaehrung() {
		assertEquals(Waehrungen.USD, new Waehrung("USD", "$", 10000));
	}

	@org.junit.Test
	public void testUmrechnen() {
		assertEquals(Waehrungen.USD.umrechnen(5, Waehrungen.Rubel), 196);
	}

	@org.junit.Test
	public void testWaehrungToString() {
		assertEquals(Waehrungen.Yen.toString(), "Yen [¥] 1 $ = 0.0091 ¥");
	}

	@org.junit.Test
	public void testWaehrungGetName() {
		assertEquals(Waehrungen.SchweizerFranken.getName(), "SchweizerFranken");
	}

	@org.junit.Test
	public void testWaehrungGetKuerzel() {
		assertEquals(Waehrungen.Rubel.getKuerzel(), "RUB");
	}

	@org.junit.Test
	public void testWaehrungGetKurs() {
		assertEquals(Waehrungen.Euro.getKurs(), 12690);
	}

	@org.junit.Test
	public void testKontoGetInhaber() {
		assertEquals(meins.getInhaber(), "Max Mustermann");
	}
	@org.junit.Test
	public void testKontoGetSaldo(){
		assertEquals(meins.getSaldo().getBetrag(), 500);
		assertEquals(meins.getSaldo().getWaehrung(), Waehrungen.USD);
	}
	@org.junit.Test
	public void testKontoBuchen(){
		assertEquals(meins.getSaldo().getBetrag(), 500);
		assertEquals(meins.getBetraege(0).getBetrag(), 500 );
		assertEquals(meins.getBetraege(1), null );
		meins.buche(fuenfCHF);
		assertEquals(meins.getSaldo().getBetrag(), 1025 );
		assertEquals(meins.getBetraege(1).getBetrag(), 525 );
	}
	@org.junit.Test
	public void testKontoGebuehren(){
		assertEquals(meins.gebuehren(6), 6);
	}
}
