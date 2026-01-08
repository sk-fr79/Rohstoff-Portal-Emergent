package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev;

import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVBetrag;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVColumn;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVDatum;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVKonto;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVLine;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVText;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVZahl;
/**
 * Code model for one line in the exported "Buchungsstapel". See the Datev 
 * format specification for details.
.
 * @author nils
 *
 */
public class BuchungLine extends DatevCSVLine {
	public BuchungLine() {
		// Umsatz
		BuchungLine s = this;
		s.put(1, 	new DatevCSVColumn("Umsatz", 					true, 	new DatevCSVBetrag(10, 2, 16)));
		s.put(2, 	new DatevCSVColumn("Soll/Haben-Kennzeichen", 	true, 	new DatevCSVText(1, 1)));
		s.put(3, 	new DatevCSVColumn("WKZ Umsatz", 				false, 	new DatevCSVText(3, 3)));
		s.put(4, 	new DatevCSVColumn("Kurs", 					false, 	new DatevCSVZahl(10, 2, 16)));
		s.put(5, 	new DatevCSVColumn("Basis-Umsatz", 			false, 	new DatevCSVBetrag(10, 2, 16)));
		s.put(6, 	new DatevCSVColumn("WKZ Basis-Umsatz", 		false, 	new DatevCSVText(3, 3)));

		// Konto/Gegenkonto
		s.put(7, 	new DatevCSVColumn("Konto", 					true,	new DatevCSVKonto()));
		s.put(8, 	new DatevCSVColumn("Gegenkonto", 				true, 	new DatevCSVKonto()));
		s.put(9, 	new DatevCSVColumn("BU-Schlüssel", 			false, 	new DatevCSVText(2, 2)));

		// Datum
		s.put(10, 	new DatevCSVColumn("Belegdatum", 				true,	new DatevCSVDatum()));

		// Belegfelder
		s.put(11, 	new DatevCSVColumn("Belegfeld 1", 			false,	new DatevCSVText(12, 12)));
		s.put(12, 	new DatevCSVColumn("Belegfeld 2", 			false,	new DatevCSVText(12, 12)));
		s.put(13, 	new DatevCSVColumn("Skonto", 					false,	new DatevCSVBetrag(8, 2, 13)));
		s.put(14, 	new DatevCSVColumn("Buchungstext", 			false,	new DatevCSVText(60, 60)));

		// OPOS-Informationen
		s.put(15, 	new DatevCSVColumn("Postensperre", 			false,	new DatevCSVZahl(1, 0, 1)));
		s.put(16, 	new DatevCSVColumn("Diverse Adressnummer", 	false,	new DatevCSVText(9, 9)));
		s.put(17, 	new DatevCSVColumn("Geschäftspartnerbank", 	false,	new DatevCSVZahl(3, 0, 3)));
		s.put(18, 	new DatevCSVColumn("Sachverhalt", 			false,	new DatevCSVZahl(2, 0, 2)));
		s.put(19, 	new DatevCSVColumn("Zinssperre", 				false,	new DatevCSVZahl(1, 0, 1)));
		
		// Digitaler Beleg
		s.put(20, 	new DatevCSVColumn("Beleglink", 				false,	new DatevCSVText(210, 210)));
		
		// Beleginfo
		s.put(21, 	new DatevCSVColumn("Beleginfo - Art 1", 		false,	new DatevCSVText(20, 20)));
		s.put(22, 	new DatevCSVColumn("Beleginfo - Inhalt 1", 	false,	new DatevCSVText(210, 210)));
		s.put(23, 	new DatevCSVColumn("Beleginfo - Art 2", 		false,	new DatevCSVText(20, 20)));
		s.put(24, 	new DatevCSVColumn("Beleginfo - Inhalt 2", 	false,	new DatevCSVText(210, 210)));
		s.put(25, 	new DatevCSVColumn("Beleginfo - Art 3", 		false,	new DatevCSVText(20, 20)));
		s.put(26, 	new DatevCSVColumn("Beleginfo - Inhalt 3", 	false,	new DatevCSVText(210, 210)));
		s.put(27, 	new DatevCSVColumn("Beleginfo - Art 4", 		false,	new DatevCSVText(20, 20)));
		s.put(28, 	new DatevCSVColumn("Beleginfo - Inhalt 4", 	false,	new DatevCSVText(210, 210)));
		s.put(29, 	new DatevCSVColumn("Beleginfo - Art 5", 		false,	new DatevCSVText(20, 20)));
		s.put(30, 	new DatevCSVColumn("Beleginfo - Inhalt 5", 	false,	new DatevCSVText(210, 210)));
		s.put(31, 	new DatevCSVColumn("Beleginfo - Art 6", 		false,	new DatevCSVText(20, 20)));
		s.put(32, 	new DatevCSVColumn("Beleginfo - Inhalt 6", 	false,	new DatevCSVText(210, 210)));
		s.put(33, 	new DatevCSVColumn("Beleginfo - Art 7", 		false,	new DatevCSVText(20, 20)));
		s.put(34, 	new DatevCSVColumn("Beleginfo - Inhalt 7", 	false,	new DatevCSVText(210, 210)));
		s.put(35, 	new DatevCSVColumn("Beleginfo - Art 8", 		false,	new DatevCSVText(20, 20)));
		s.put(36, 	new DatevCSVColumn("Beleginfo - Inhalt 8", 	false,	new DatevCSVText(210, 210)));

		// Kostenstelle
		s.put(37, 	new DatevCSVColumn("Kost 1 - Kostenstelle", 	false,	new DatevCSVText(8, 8)));
		s.put(38, 	new DatevCSVColumn("Kost 2 - Kostenstelle", 	false,	new DatevCSVText(8, 8)));
		s.put(39, 	new DatevCSVColumn("Kost-Menge", 				false,	new DatevCSVZahl(9, 2, 14)));

		// Steuerrechnung
		s.put(40, 	new DatevCSVColumn("EU-Land u. UStID", 		false,	new DatevCSVText(15, 15)));
		s.put(41, 	new DatevCSVColumn("EU-Steuersatz", 			false,	new DatevCSVZahl(2, 2, 5)));
		s.put(42, 	new DatevCSVColumn("Abw. Versteuerungsart", 	false,	new DatevCSVText(1, 1)));

		// L+L Sachverhalt
		s.put(43, 	new DatevCSVColumn("Sachverhalt L+L", 		false,	new DatevCSVZahl(3, 0, 3)));
		s.put(44, 	new DatevCSVColumn("Funktionsergänzung L+L",	false,	new DatevCSVZahl(3, 0, 1)));

		// Funktion SteuerschlÃ¼ssel 49
		s.put(45, 	new DatevCSVColumn("BU 49 Hauptfunktionstyp", 		false,	new DatevCSVZahl(1, 0, 1)));
		s.put(46, 	new DatevCSVColumn("BU 49 Hauptfunktionsnummer",		false,	new DatevCSVZahl(2, 0, 2)));
		s.put(47, 	new DatevCSVColumn("BU 49 Funktionsergänzung", 		false,	new DatevCSVZahl(3, 0, 3)));

		// Zusatzinformationen
		s.put(48, 	new DatevCSVColumn("Zusatzinformationen - Art 1", 	false,	new DatevCSVText(20, 20)));
		s.put(49, 	new DatevCSVColumn("Zusatzinformationen - Inhalt 1",	false,	new DatevCSVText(210, 210)));
		s.put(50, 	new DatevCSVColumn("Zusatzinformationen - Art 2", 	false,	new DatevCSVText(20, 20)));
		s.put(51, 	new DatevCSVColumn("Zusatzinformationen - Inhalt 2",	false,	new DatevCSVText(210, 210)));
		s.put(52, 	new DatevCSVColumn("Zusatzinformationen - Art 3", 	false,	new DatevCSVText(20, 20)));
		s.put(53, 	new DatevCSVColumn("Zusatzinformationen - Inhalt 3",	false,	new DatevCSVText(210, 210)));
		s.put(54, 	new DatevCSVColumn("Zusatzinformationen - Art 4", 	false,	new DatevCSVText(20, 20)));
		s.put(55, 	new DatevCSVColumn("Zusatzinformationen - Inhalt 4",	false,	new DatevCSVText(210, 210)));
		s.put(56, 	new DatevCSVColumn("Zusatzinformationen - Art 5", 	false,	new DatevCSVText(20, 20)));
		s.put(57, 	new DatevCSVColumn("Zusatzinformationen - Inhalt 5",	false,	new DatevCSVText(210, 210)));
		s.put(58, 	new DatevCSVColumn("Zusatzinformationen - Art 6", 	false,	new DatevCSVText(20, 20)));
		s.put(59, 	new DatevCSVColumn("Zusatzinformationen - Inhalt 6",	false,	new DatevCSVText(210, 210)));
		s.put(60, 	new DatevCSVColumn("Zusatzinformationen - Art 7", 	false,	new DatevCSVText(20, 20)));
		s.put(61, 	new DatevCSVColumn("Zusatzinformationen - Inhalt 7",	false,	new DatevCSVText(210, 210)));
		s.put(62, 	new DatevCSVColumn("Zusatzinformationen - Art 8", 	false,	new DatevCSVText(20, 20)));
		s.put(63, 	new DatevCSVColumn("Zusatzinformationen - Inhalt 8",	false,	new DatevCSVText(210, 210)));
		s.put(64, 	new DatevCSVColumn("Zusatzinformationen - Art 9", 	false,	new DatevCSVText(20, 20)));
		s.put(65, 	new DatevCSVColumn("Zusatzinformationen - Inhalt 9",	false,	new DatevCSVText(210, 210)));
		s.put(66, 	new DatevCSVColumn("Zusatzinformationen - Art 10", 	false,	new DatevCSVText(20, 20)));
		s.put(67, 	new DatevCSVColumn("Zusatzinformationen - Inhalt 10",	false,	new DatevCSVText(210, 210)));
		s.put(68, 	new DatevCSVColumn("Zusatzinformationen - Art 11", 	false,	new DatevCSVText(20, 20)));
		s.put(69, 	new DatevCSVColumn("Zusatzinformationen - Inhalt 11",	false,	new DatevCSVText(210, 210)));
		s.put(70, 	new DatevCSVColumn("Zusatzinformationen - Art 12", 	false,	new DatevCSVText(20, 20)));
		s.put(71, 	new DatevCSVColumn("Zusatzinformationen - Inhalt 12",	false,	new DatevCSVText(210, 210)));
		s.put(72, 	new DatevCSVColumn("Zusatzinformationen - Art 13", 	false,	new DatevCSVText(20, 20)));
		s.put(73, 	new DatevCSVColumn("Zusatzinformationen - Inhalt 13",	false,	new DatevCSVText(210, 210)));
		s.put(74, 	new DatevCSVColumn("Zusatzinformationen - Art 14", 	false,	new DatevCSVText(20, 20)));
		s.put(75, 	new DatevCSVColumn("Zusatzinformationen - Inhalt 14",	false,	new DatevCSVText(210, 210)));
		s.put(76, 	new DatevCSVColumn("Zusatzinformationen - Art 15", 	false,	new DatevCSVText(20, 20)));
		s.put(77, 	new DatevCSVColumn("Zusatzinformationen - Inhalt 15",	false,	new DatevCSVText(210, 210)));
		s.put(78, 	new DatevCSVColumn("Zusatzinformationen - Art 16", 	false,	new DatevCSVText(20, 20)));
		s.put(79, 	new DatevCSVColumn("Zusatzinformationen - Inhalt 16",	false,	new DatevCSVText(210, 210)));
		s.put(80, 	new DatevCSVColumn("Zusatzinformationen - Art 17", 	false,	new DatevCSVText(20, 20)));
		s.put(81, 	new DatevCSVColumn("Zusatzinformationen - Inhalt 17",	false,	new DatevCSVText(210, 210)));
		s.put(82, 	new DatevCSVColumn("Zusatzinformationen - Art 18", 	false,	new DatevCSVText(20, 20)));
		s.put(83, 	new DatevCSVColumn("Zusatzinformationen - Inhalt 18",	false,	new DatevCSVText(210, 210)));
		s.put(84, 	new DatevCSVColumn("Zusatzinformationen - Art 19", 	false,	new DatevCSVText(20, 20)));
		s.put(85, 	new DatevCSVColumn("Zusatzinformationen - Inhalt 19",	false,	new DatevCSVText(210, 210)));
		s.put(86, 	new DatevCSVColumn("Zusatzinformationen - Art 20", 	false,	new DatevCSVText(20, 20)));
		s.put(87, 	new DatevCSVColumn("Zusatzinformationen - Inhalt 20",	false,	new DatevCSVText(210, 210)));
		
		// Mengenfelder LuF
		s.put(88, 	new DatevCSVColumn("Stück", 					false,	new DatevCSVZahl(8, 0, 8)));
		s.put(89, 	new DatevCSVColumn("Gewicht",					false,	new DatevCSVZahl(8, 2, 11)));
		
		// Zahlweise
		s.put(90, 	new DatevCSVColumn("Zahlweise",				false,	new DatevCSVZahl(2, 0, 2)));
		s.put(91, 	new DatevCSVColumn("Forderungsart", 			false,	new DatevCSVText(10, 10)));
		s.put(92, 	new DatevCSVColumn("Veranlagungsjahr", 		false,	new DatevCSVZahl(4, 0, 4)));
		s.put(93, 	new DatevCSVColumn("Zugeordnete Fälligkeit", 	false,	new DatevCSVDatum()));

		// Weitere Felder
		s.put(94, 	new DatevCSVColumn("Skontotyp",				 		false,	new DatevCSVZahl(1, 0, 1)));

		// Anzahlungen
		s.put(95, 	new DatevCSVColumn("Auftragsnummer", 					false,	new DatevCSVText(30, 30)));
		s.put(96, 	new DatevCSVColumn("Ust-Schlüssel (Anzahlungen)", 	false,	new DatevCSVText(2, 2)));
		s.put(97, 	new DatevCSVColumn("Ust-Schlüssel (Anzahlungen)",		false,	new DatevCSVZahl(2, 0, 2)));
		s.put(98, 	new DatevCSVColumn("EU-Land (Anzahlungen)", 			false,	new DatevCSVText(2, 2)));
		s.put(99, 	new DatevCSVColumn("Sachverhalt L+L (Anzahlungen)",	false,	new DatevCSVZahl(3, 0, 3)));
		s.put(100,	new DatevCSVColumn("EU-Steuersatz (Anzahlungen)",		false,	new DatevCSVZahl(2, 2, 5)));
		s.put(101, 	new DatevCSVColumn("Erlöskonto (Anzahlungen)",		false,	new DatevCSVKonto()));

		// Stapelinformationen
		s.put(102, 	new DatevCSVColumn("Herkunft-Kz", 			false,	new DatevCSVText(2, 2)));
		s.put(103, 	new DatevCSVColumn("Buchungs GUID", 			false,	new DatevCSVText(36, 36)));
		s.put(104, 	new DatevCSVColumn("Kost-Datum", 				false,	new DatevCSVDatum()));
		s.put(105, 	new DatevCSVColumn("Mandatsreferenz", 		false,	new DatevCSVText(35, 35)));
		
	}
}