package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev;

import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVBetrag;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVColumn;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVDatum;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVKonto;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVLine;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVText;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVZahl;
/**
 * Code model for one line in the exported "Debitoren/Kreditoren". See the Datev 
 * format specification for details.
 * @author nils
 *
 */
public class AddressLine extends DatevCSVLine {
	
	public AddressLine() {
		AddressLine s = this;
		s.put(1, 	new DatevCSVColumn("Konto", 									true, 	new DatevCSVKonto(1,10)));
		s.put(2, 	new DatevCSVColumn("Name (Adressattyp Unternehmen)", 			false, 	new DatevCSVText(0,50)));
		s.put(3, 	new DatevCSVColumn("Unternehmensgegenstand", 					false, 	new DatevCSVText(0,50)));
		s.put(4, 	new DatevCSVColumn("Name (Adressattyp natürl. Person)", 		false, 	new DatevCSVText(0,30)));
		s.put(5, 	new DatevCSVColumn("Vorname (Adressattyp natürl. Person)",	false, 	new DatevCSVText(0,30)));
		s.put(6, 	new DatevCSVColumn("Name (Adressattyp keine Angabe)", 		false, 	new DatevCSVText(0,50)));
		s.put(7, 	new DatevCSVColumn("Adressattyp", 							false, 	new DatevCSVText(0,1)));
		s.put(8, 	new DatevCSVColumn("Kurzbezeichnung", 						false, 	new DatevCSVText(0,15)));
		
		s.put(9, 	new DatevCSVColumn("EU-Land", 								false, 	new DatevCSVText(0,2)));
		s.put(10, 	new DatevCSVColumn("EU-UStID", 								false, 	new DatevCSVText(0,13)));

		s.put(11, 	new DatevCSVColumn("Anrede", 									false, 	new DatevCSVText(0,30)));
		s.put(12, 	new DatevCSVColumn("Titel/Akad. Grad", 						false, 	new DatevCSVText(0,25)));
		s.put(13, 	new DatevCSVColumn("Adelstitel", 								false, 	new DatevCSVText(0,15)));
		s.put(14, 	new DatevCSVColumn("Namensvorsatz", 							false, 	new DatevCSVText(0,14)));
		s.put(15, 	new DatevCSVColumn("Adressart", 								false, 	new DatevCSVText(0,3)));

		s.put(16, 	new DatevCSVColumn("Straße", 									false, 	new DatevCSVText(0,36)));
		s.put(17, 	new DatevCSVColumn("Postfach", 								false, 	new DatevCSVText(0,10)));
		s.put(18, 	new DatevCSVColumn("Postleitzahl", 							false, 	new DatevCSVText(0,10)));
		s.put(19, 	new DatevCSVColumn("Ort", 									false, 	new DatevCSVText(0,30)));
		s.put(20, 	new DatevCSVColumn("Land", 									false, 	new DatevCSVText(0,2)));
		
		s.put(21, 	new DatevCSVColumn("Versandzusatz", 							false, 	new DatevCSVText(0,2)));
		s.put(22, 	new DatevCSVColumn("Adresszusatz", 							false, 	new DatevCSVText(0,2)));
		s.put(23, 	new DatevCSVColumn("Abweichende Anrede", 						false, 	new DatevCSVText(0,2)));
		s.put(24, 	new DatevCSVColumn("Abw. Zustellbezeichnung 1", 				false, 	new DatevCSVText(0,2)));
		s.put(25, 	new DatevCSVColumn("Abw. Zustellbezeichnung 2", 				false, 	new DatevCSVText(0,2)));
		s.put(26, 	new DatevCSVColumn("Kennz. Korrespondenzadresse", 			false, 	new DatevCSVZahl(1,0,1)));
		s.put(27, 	new DatevCSVColumn("Adresse Gültig von", 						false, 	new DatevCSVDatum()));
		s.put(28, 	new DatevCSVColumn("Adresse Gültig bis", 						false, 	new DatevCSVDatum()));

		s.put(29, 	new DatevCSVColumn("Telefon", 								false, 	new DatevCSVText(0,60)));
		s.put(30, 	new DatevCSVColumn("Bemerkung (Telefon)", 					false, 	new DatevCSVText(0,40)));
		s.put(31, 	new DatevCSVColumn("Telefon GL", 								false, 	new DatevCSVText(0,60)));
		s.put(32, 	new DatevCSVColumn("Bemerkung (Telefon GL)", 					false, 	new DatevCSVText(0,40)));
		s.put(33, 	new DatevCSVColumn("E-Mail", 									false, 	new DatevCSVText(0,60)));
		s.put(34, 	new DatevCSVColumn("Bemerkung (E-Mail)", 						false, 	new DatevCSVText(0,40)));

		s.put(35, 	new DatevCSVColumn("Internet", 								false, 	new DatevCSVText(0,60)));
		s.put(36, 	new DatevCSVColumn("Bemerkung (Internet)", 					false, 	new DatevCSVText(0,40)));
		s.put(37, 	new DatevCSVColumn("Fax", 									false, 	new DatevCSVText(0,60)));
		s.put(38, 	new DatevCSVColumn("Bemerkung (Fax)", 						false, 	new DatevCSVText(0,40)));
		s.put(39, 	new DatevCSVColumn("Sonstige", 								false, 	new DatevCSVText(0,60)));
		s.put(40, 	new DatevCSVColumn("Bemerkung (Sonstige)", 					false, 	new DatevCSVText(0,40)));
		
		s.put(41, 	new DatevCSVColumn("Bankleitzahl 1", 							false, 	new DatevCSVText(0,8)));
		s.put(42, 	new DatevCSVColumn("Bankbezeichnung 1", 						false, 	new DatevCSVText(0,30)));
		s.put(43, 	new DatevCSVColumn("Bank-Kontonummer 1", 						false, 	new DatevCSVText(0,10)));
		s.put(44, 	new DatevCSVColumn("Länderkennzeichen 1", 					false, 	new DatevCSVText(0,2)));
		s.put(45, 	new DatevCSVColumn("IBAN-Nr. 1", 								false, 	new DatevCSVText(0,34)));
		s.put(46, 	new DatevCSVColumn("IBAN1 korrekt", 							false, 	new DatevCSVText(0,1)));
		s.put(47, 	new DatevCSVColumn("SWIFT-Code 1", 							false, 	new DatevCSVText(0,11)));
		s.put(48, 	new DatevCSVColumn("Abw. Kontoinhaber 1", 					false, 	new DatevCSVText(0,70)));
		s.put(49, 	new DatevCSVColumn("Kennz. Hauptbankverb. 1", 				false, 	new DatevCSVText(0,1)));
		s.put(50, 	new DatevCSVColumn("Bankverb 1 Gültig von", 					false, 	new DatevCSVDatum()));
		s.put(51, 	new DatevCSVColumn("Bankverb 1 Gültig bis", 					false, 	new DatevCSVDatum()));

		s.put(52, 	new DatevCSVColumn("Bankleitzahl 2", 							false, 	new DatevCSVText(0,8)));
		s.put(53, 	new DatevCSVColumn("Bankbezeichnung 2", 						false, 	new DatevCSVText(0,30)));
		s.put(54, 	new DatevCSVColumn("Bank-Kontonummer 2", 						false, 	new DatevCSVText(0,10)));
		s.put(55, 	new DatevCSVColumn("Länderkennzeichen 2", 					false, 	new DatevCSVText(0,2)));
		s.put(56, 	new DatevCSVColumn("IBAN-Nr. 2", 								false, 	new DatevCSVText(0,34)));
		s.put(57, 	new DatevCSVColumn("IBAN2 korrekt", 							false, 	new DatevCSVText(0,1)));
		s.put(58, 	new DatevCSVColumn("SWIFT-Code 2", 							false, 	new DatevCSVText(0,11)));
		s.put(59, 	new DatevCSVColumn("Abw. Kontoinhaber 2", 					false, 	new DatevCSVText(0,70)));
		s.put(60, 	new DatevCSVColumn("Kennz. Hauptbankverb. 2", 				false, 	new DatevCSVText(0,1)));
		s.put(61, 	new DatevCSVColumn("Bankverb 2 Gültig von", 					false, 	new DatevCSVDatum()));
		s.put(62, 	new DatevCSVColumn("Bankverb 2 Gültig bis", 					false, 	new DatevCSVDatum()));
		
		s.put(63, 	new DatevCSVColumn("Bankleitzahl 3", 							false, 	new DatevCSVText(0,8)));
		s.put(64, 	new DatevCSVColumn("Bankbezeichnung 3", 						false, 	new DatevCSVText(0,30)));
		s.put(65, 	new DatevCSVColumn("Bank-Kontonummer 3", 						false, 	new DatevCSVText(0,10)));
		s.put(66, 	new DatevCSVColumn("Länderkennzeichen 3", 					false, 	new DatevCSVText(0,2)));
		s.put(67, 	new DatevCSVColumn("IBAN-Nr. 3", 								false, 	new DatevCSVText(0,34)));
		s.put(68, 	new DatevCSVColumn("IBAN3 korrekt", 							false, 	new DatevCSVText(0,1)));
		s.put(69, 	new DatevCSVColumn("SWIFT-Code 3", 							false, 	new DatevCSVText(0,11)));
		s.put(70, 	new DatevCSVColumn("Abw. Kontoinhaber 3", 					false, 	new DatevCSVText(0,70)));
		s.put(71, 	new DatevCSVColumn("Kennz. Hauptbankverb. 3", 				false, 	new DatevCSVText(0,1)));
		s.put(72, 	new DatevCSVColumn("Bankverb 3 Gültig von", 					false, 	new DatevCSVDatum()));
		s.put(73, 	new DatevCSVColumn("Bankverb 3 Gültig bis", 					false, 	new DatevCSVDatum()));
		
		s.put(74, 	new DatevCSVColumn("Bankleitzahl 4", 							false, 	new DatevCSVText(0,8)));
		s.put(75, 	new DatevCSVColumn("Bankbezeichnung 4", 						false, 	new DatevCSVText(0,30)));
		s.put(76, 	new DatevCSVColumn("Bank-Kontonummer 4", 						false, 	new DatevCSVText(0,10)));
		s.put(77, 	new DatevCSVColumn("Länderkennzeichen 4", 					false, 	new DatevCSVText(0,2)));
		s.put(78, 	new DatevCSVColumn("IBAN-Nr. 4", 								false, 	new DatevCSVText(0,34)));
		s.put(79, 	new DatevCSVColumn("IBAN4 korrekt", 							false, 	new DatevCSVText(0,1)));
		s.put(80, 	new DatevCSVColumn("SWIFT-Code 4", 							false, 	new DatevCSVText(0,11)));
		s.put(81, 	new DatevCSVColumn("Abw. Kontoinhaber 4", 					false, 	new DatevCSVText(0,70)));
		s.put(82, 	new DatevCSVColumn("Kennz. Hauptbankverb. 4", 				false, 	new DatevCSVText(0,2)));
		s.put(83, 	new DatevCSVColumn("Bankverb 4 Gültig von", 					false, 	new DatevCSVDatum()));
		s.put(84, 	new DatevCSVColumn("Bankverb 4 Gültig bis", 					false, 	new DatevCSVDatum()));
		
		s.put(85, 	new DatevCSVColumn("Bankleitzahl 5", 							false, 	new DatevCSVText(0,8)));
		s.put(86, 	new DatevCSVColumn("Bankbezeichnung 5", 						false, 	new DatevCSVText(0,30)));
		s.put(87, 	new DatevCSVColumn("Bank-Kontonummer 5", 						false, 	new DatevCSVText(0,10)));
		s.put(88, 	new DatevCSVColumn("Länderkennzeichen 5", 					false, 	new DatevCSVText(0,2)));
		s.put(89, 	new DatevCSVColumn("IBAN-Nr. 5", 								false, 	new DatevCSVText(0,34)));
		s.put(90, 	new DatevCSVColumn("IBAN5 korrekt", 							false, 	new DatevCSVText(0,1)));
		s.put(91, 	new DatevCSVColumn("SWIFT-Code 5", 							false, 	new DatevCSVText(0,11)));
		s.put(92, 	new DatevCSVColumn("Abw. Kontoinhaber 5", 					false, 	new DatevCSVText(0,70)));
		s.put(93, 	new DatevCSVColumn("Kennz. Hauptbankverb. 5", 				false, 	new DatevCSVText(0,2)));
		s.put(94, 	new DatevCSVColumn("Bankverb 5 Gültig von", 					false, 	new DatevCSVDatum()));
		s.put(95, 	new DatevCSVColumn("Bankverb 5 Gültig bis", 					false, 	new DatevCSVDatum()));
		
		
		
		s.put(96, 	new DatevCSVColumn("Leerfeld", 								false, 	new DatevCSVZahl(3,0,3)));
		s.put(97, 	new DatevCSVColumn("Briefanrede", 							false, 	new DatevCSVText(0,100)));
		s.put(98, 	new DatevCSVColumn("Grußformel", 								false, 	new DatevCSVText(0,50)));
		s.put(99, 	new DatevCSVColumn("Kundennummer", 							false, 	new DatevCSVText(0,15)));
		s.put(100, 	new DatevCSVColumn("Steuernummer", 							false, 	new DatevCSVText(0,20)));
		s.put(101, 	new DatevCSVColumn("Sprache", 								false, 	new DatevCSVZahl(2,0,2)));
		s.put(102, 	new DatevCSVColumn("Ansprechpartner", 						false, 	new DatevCSVText(0,40)));
		s.put(103, 	new DatevCSVColumn("Vertreter", 								false, 	new DatevCSVText(0,40)));
		s.put(104, 	new DatevCSVColumn("Sachbearbeiter", 							false, 	new DatevCSVText(0,40)));
		s.put(105, 	new DatevCSVColumn("Diverse-Konto", 							false, 	new DatevCSVZahl(1,0,1)));
		s.put(106, 	new DatevCSVColumn("Ausgabeziel", 							false, 	new DatevCSVZahl(1,0,1)));
		s.put(107, 	new DatevCSVColumn("Währungssteuerung", 						false, 	new DatevCSVText(0,1)));
		
		s.put(108, 	new DatevCSVColumn("Kreditlimit (Debitor)", 					false, 	new DatevCSVBetrag(13,0,13)));
		s.put(109, 	new DatevCSVColumn("Zahlungsbedingung", 						false, 	new DatevCSVZahl(3,0,3)));
		s.put(110, 	new DatevCSVColumn("Fälligkeit in Tagen (Debitor)", 			false, 	new DatevCSVZahl(3,0,3)));
		s.put(111, 	new DatevCSVColumn("Skonto in Prozent (Debitor)", 			false, 	new DatevCSVZahl(2,2,5)));
		s.put(112, 	new DatevCSVColumn("Kreditoren-Ziel 1 Tg.", 					false, 	new DatevCSVZahl(2,0,2)));
		s.put(113, 	new DatevCSVColumn("Kreditoren-Skonto 1 %", 					false, 	new DatevCSVZahl(2,2,5)));
		s.put(114, 	new DatevCSVColumn("Kreditoren-Ziel 2 Tg.", 					false, 	new DatevCSVZahl(2,0,2)));
		s.put(115, 	new DatevCSVColumn("Kreditoren-Skonto 2 %", 					false, 	new DatevCSVZahl(2,2,5)));
		s.put(116, 	new DatevCSVColumn("Kreditoren-Ziel 3 Brutto Tg.", 			false, 	new DatevCSVZahl(2,0,2)));
		s.put(117, 	new DatevCSVColumn("Kreditoren-Ziel 4 Tg.", 					false, 	new DatevCSVZahl(3,0,3)));
		s.put(118, 	new DatevCSVColumn("Kreditoren-Skonto 4 %", 					false, 	new DatevCSVZahl(2,2,5)));
		s.put(119, 	new DatevCSVColumn("Kreditoren-Ziel 5 Tg.", 					false, 	new DatevCSVZahl(2,0,2)));
		s.put(120, 	new DatevCSVColumn("Kreditoren-Skonto 5 %", 					false, 	new DatevCSVZahl(2,2,5)));
		s.put(121, 	new DatevCSVColumn("Mahnung", 								false, 	new DatevCSVZahl(1,0,1)));
		
		s.put(122, 	new DatevCSVColumn("Kontoauszug", 							false, 	new DatevCSVZahl(1,0,1)));
		s.put(123, 	new DatevCSVColumn("Mahntext 1", 								false, 	new DatevCSVZahl(1,0,1)));
		s.put(124, 	new DatevCSVColumn("Mahntext 2", 								false, 	new DatevCSVZahl(1,0,1)));
		s.put(125, 	new DatevCSVColumn("Mahntext 3", 								false, 	new DatevCSVZahl(1,0,1)));
		s.put(126, 	new DatevCSVColumn("Kontoauszugstext", 						false, 	new DatevCSVZahl(1,0,1)));

		s.put(127, 	new DatevCSVColumn("Mahnlimit Betrag", 						false, 	new DatevCSVBetrag(5,2,9)));
		s.put(128, 	new DatevCSVColumn("Mahnlimit %", 							false, 	new DatevCSVZahl(2,2,5)));
		s.put(129, 	new DatevCSVColumn("Zinsberechnung", 							false, 	new DatevCSVZahl(1,0,1)));
		s.put(130, 	new DatevCSVColumn("Mahnzinssatz 1", 							false, 	new DatevCSVZahl(2,2,5)));
		s.put(131, 	new DatevCSVColumn("Mahnzinssatz 2", 							false, 	new DatevCSVZahl(2,2,5)));
		s.put(132, 	new DatevCSVColumn("Mahnzinssatz 3", 							false, 	new DatevCSVZahl(2,2,5)));
		s.put(133, 	new DatevCSVColumn("Lastschrift", 							false, 	new DatevCSVText(0,1)));
		s.put(134, 	new DatevCSVColumn("Verfahren", 								false, 	new DatevCSVText(0,1)));
		s.put(135, 	new DatevCSVColumn("Mandantenbank", 							false, 	new DatevCSVZahl(4,0,4)));
		s.put(136, 	new DatevCSVColumn("Zahlungsträger", 							false, 	new DatevCSVText(0,1)));
		s.put(137, 	new DatevCSVColumn("Indiv. Feld 1", 							false, 	new DatevCSVText(0,40)));
		s.put(138, 	new DatevCSVColumn("Indiv. Feld 2", 							false, 	new DatevCSVText(0,40)));
		s.put(139, 	new DatevCSVColumn("Indiv. Feld 3", 							false, 	new DatevCSVText(0,40)));
		s.put(140, 	new DatevCSVColumn("Indiv. Feld 4", 							false, 	new DatevCSVText(0,40)));
		s.put(141, 	new DatevCSVColumn("Indiv. Feld 5", 							false, 	new DatevCSVText(0,40)));
		s.put(142, 	new DatevCSVColumn("Indiv. Feld 6", 							false, 	new DatevCSVText(0,40)));
		s.put(143, 	new DatevCSVColumn("Indiv. Feld 7", 							false, 	new DatevCSVText(0,40)));
		s.put(144, 	new DatevCSVColumn("Indiv. Feld 8", 							false, 	new DatevCSVText(0,40)));
		s.put(145, 	new DatevCSVColumn("Indiv. Feld 9", 							false, 	new DatevCSVText(0,40)));
		s.put(146, 	new DatevCSVColumn("Indiv. Feld 10", 							false, 	new DatevCSVText(0,40)));
		s.put(147, 	new DatevCSVColumn("Indiv. Feld 11", 							false, 	new DatevCSVText(0,40)));
		s.put(148, 	new DatevCSVColumn("Indiv. Feld 12", 							false, 	new DatevCSVText(0,40)));
		s.put(149, 	new DatevCSVColumn("Indiv. Feld 13", 							false, 	new DatevCSVText(0,40)));
		s.put(150, 	new DatevCSVColumn("Indiv. Feld 14", 							false, 	new DatevCSVText(0,40)));
		s.put(151, 	new DatevCSVColumn("Indiv. Feld 15", 							false, 	new DatevCSVText(0,40)));
		
		s.put(152, 	new DatevCSVColumn("Abweichende Anrede (Rechnungsadresse)", 	false, 	new DatevCSVText(0,30)));
		s.put(153, 	new DatevCSVColumn("Adressart (Rechnungsadresse)", 			false, 	new DatevCSVText(0,3)));
		s.put(154, 	new DatevCSVColumn("Straße (Rechnungsadresse)", 				false, 	new DatevCSVText(0,36)));
		s.put(155, 	new DatevCSVColumn("Postfach (Rechnungsadresse)", 			false, 	new DatevCSVText(0,10)));
		s.put(156, 	new DatevCSVColumn("Postleitzahl (Rechnungsadresse)", 		false, 	new DatevCSVText(0,10)));
		s.put(157, 	new DatevCSVColumn("Ort (Rechnungsadresse)", 					false, 	new DatevCSVText(0,30)));
		s.put(158, 	new DatevCSVColumn("Land (Rechnungsadresse)", 				false, 	new DatevCSVText(0,2)));
		s.put(159, 	new DatevCSVColumn("Versandzusatz (Rechnungsadresse)", 		false, 	new DatevCSVText(0,50)));
		s.put(160, 	new DatevCSVColumn("Adresszusatz (Rechnungsadresse)", 		false, 	new DatevCSVText(0,36)));
		s.put(161, 	new DatevCSVColumn("Abw. Zustellbezeichnung 1 (Rechnungsadresse)", 	false, 	new DatevCSVText(0,50)));
		s.put(162, 	new DatevCSVColumn("Abw. Zustellbezeichnung 2 (Rechnungsadresse)", 	false, 	new DatevCSVText(0,36)));
		s.put(163, 	new DatevCSVColumn("Adresse Gültig von (Rechnungsadresse)", 			false, 	new DatevCSVDatum()));
		s.put(164, 	new DatevCSVColumn("Adresse Gültig bis (Rechnungsadresse)", 			false, 	new DatevCSVDatum()));

		s.put(165, 	new DatevCSVColumn("Bankleitzahl 6", 							false, 	new DatevCSVText(0,8)));
		s.put(166, 	new DatevCSVColumn("Bankbezeichnung 6", 						false, 	new DatevCSVText(0,30)));
		s.put(167, 	new DatevCSVColumn("Bank-Kontonummer 6", 						false, 	new DatevCSVText(0,10)));
		s.put(168, 	new DatevCSVColumn("Länderkennzeichen 6", 					false, 	new DatevCSVText(0,2)));
		s.put(169, 	new DatevCSVColumn("IBAN-Nr. 6", 								false, 	new DatevCSVText(0,34)));
		s.put(170, 	new DatevCSVColumn("IBAN6 korrekt", 							false, 	new DatevCSVText(0,1)));
		s.put(171, 	new DatevCSVColumn("SWIFT-Code 6", 							false, 	new DatevCSVText(0,11)));
		s.put(172, 	new DatevCSVColumn("Abw. Kontoinhaber 6", 					false, 	new DatevCSVText(0,70)));
		s.put(173, 	new DatevCSVColumn("Kennz. Hauptbankverb. 6", 				false, 	new DatevCSVText(0,1)));
		s.put(174, 	new DatevCSVColumn("Bankverb 6 Gültig von", 					false, 	new DatevCSVDatum()));
		s.put(175, 	new DatevCSVColumn("Bankverb 6 Gültig bis", 					false, 	new DatevCSVDatum()));
		
		s.put(176, 	new DatevCSVColumn("Bankleitzahl 7", 							false, 	new DatevCSVText(0,8)));
		s.put(177, 	new DatevCSVColumn("Bankbezeichnung 7", 						false, 	new DatevCSVText(0,30)));
		s.put(178, 	new DatevCSVColumn("Bank-Kontonummer 7", 						false, 	new DatevCSVText(0,10)));
		s.put(179, 	new DatevCSVColumn("Länderkennzeichen 7", 					false, 	new DatevCSVText(0,2)));
		s.put(180, 	new DatevCSVColumn("IBAN-Nr. 7", 								false, 	new DatevCSVText(0,34)));
		s.put(181, 	new DatevCSVColumn("IBAN7 korrekt", 							false, 	new DatevCSVText(0,1)));
		s.put(182, 	new DatevCSVColumn("SWIFT-Code 7", 							false, 	new DatevCSVText(0,11)));
		s.put(183, 	new DatevCSVColumn("Abw. Kontoinhaber 7", 					false, 	new DatevCSVText(0,70)));
		s.put(184, 	new DatevCSVColumn("Kennz. Hauptbankverb. 7", 				false, 	new DatevCSVText(0,1)));
		s.put(185, 	new DatevCSVColumn("Bankverb 7 Gültig von", 					false, 	new DatevCSVDatum()));
		s.put(186, 	new DatevCSVColumn("Bankverb 7 Gültig bis", 					false, 	new DatevCSVDatum()));
		
		s.put(187, 	new DatevCSVColumn("Bankleitzahl 8", 							false, 	new DatevCSVText(0,8)));
		s.put(188, 	new DatevCSVColumn("Bankbezeichnung 8", 						false, 	new DatevCSVText(0,30)));
		s.put(189, 	new DatevCSVColumn("Bank-Kontonummer 8", 						false, 	new DatevCSVText(0,10)));
		s.put(190, 	new DatevCSVColumn("Länderkennzeichen 8", 					false, 	new DatevCSVText(0,2)));
		s.put(191, 	new DatevCSVColumn("IBAN-Nr. 8", 								false, 	new DatevCSVText(0,34)));
		s.put(192, 	new DatevCSVColumn("IBAN8 korrekt", 							false, 	new DatevCSVText(0,1)));
		s.put(193, 	new DatevCSVColumn("SWIFT-Code 8", 							false, 	new DatevCSVText(0,11)));
		s.put(194, 	new DatevCSVColumn("Abw. Kontoinhaber 8", 					false, 	new DatevCSVText(0,70)));
		s.put(195, 	new DatevCSVColumn("Kennz. Hauptbankverb. 8", 				false, 	new DatevCSVText(0,1)));
		s.put(196, 	new DatevCSVColumn("Bankverb 8 Gültig von", 					false, 	new DatevCSVDatum()));
		s.put(197, 	new DatevCSVColumn("Bankverb 8 Gültig bis", 					false, 	new DatevCSVDatum()));
		
		s.put(198, 	new DatevCSVColumn("Bankleitzahl 9", 							false, 	new DatevCSVText(0,8)));
		s.put(199, 	new DatevCSVColumn("Bankbezeichnung 9", 						false, 	new DatevCSVText(0,30)));
		s.put(200, 	new DatevCSVColumn("Bank-Kontonummer 9", 						false, 	new DatevCSVText(0,10)));
		s.put(201, 	new DatevCSVColumn("Länderkennzeichen 9", 					false, 	new DatevCSVText(0,2)));
		s.put(202, 	new DatevCSVColumn("IBAN-Nr. 9", 								false, 	new DatevCSVText(0,34)));
		s.put(203, 	new DatevCSVColumn("IBAN9 korrekt", 							false, 	new DatevCSVText(0,1)));
		s.put(204, 	new DatevCSVColumn("SWIFT-Code 9", 							false, 	new DatevCSVText(0,11)));
		s.put(205, 	new DatevCSVColumn("Abw. Kontoinhaber 9", 					false, 	new DatevCSVText(0,70)));
		s.put(206, 	new DatevCSVColumn("Kennz. Hauptbankverb. 9", 				false, 	new DatevCSVText(0,1)));
		s.put(207, 	new DatevCSVColumn("Bankverb 9 Gültig von", 					false, 	new DatevCSVDatum()));
		s.put(208, 	new DatevCSVColumn("Bankverb 9 Gültig bis", 					false, 	new DatevCSVDatum()));
		
		s.put(209, 	new DatevCSVColumn("Bankleitzahl 10", 						false, 	new DatevCSVText(0,8)));
		s.put(210, 	new DatevCSVColumn("Bankbezeichnung 10", 						false, 	new DatevCSVText(0,30)));
		s.put(211, 	new DatevCSVColumn("Bank-Kontonummer 10", 					false, 	new DatevCSVText(0,10)));
		s.put(212, 	new DatevCSVColumn("Länderkennzeichen 10", 					false, 	new DatevCSVText(0,2)));
		s.put(213, 	new DatevCSVColumn("IBAN-Nr. 10", 							false, 	new DatevCSVText(0,34)));
		s.put(214, 	new DatevCSVColumn("IBAN10 korrekt", 							false, 	new DatevCSVText(0,1)));
		s.put(215, 	new DatevCSVColumn("SWIFT-Code 10", 							false, 	new DatevCSVText(0,11)));
		s.put(216, 	new DatevCSVColumn("Abw. Kontoinhaber 10", 					false, 	new DatevCSVText(0,70)));
		s.put(217, 	new DatevCSVColumn("Kennz. Hauptbankverb. 10", 				false, 	new DatevCSVText(0,1)));
		s.put(218, 	new DatevCSVColumn("Bankverb 10 Gültig von", 					false, 	new DatevCSVDatum()));
		s.put(219, 	new DatevCSVColumn("Bankverb 10 Gültig bis", 					false, 	new DatevCSVDatum()));
		
		s.put(220, 	new DatevCSVColumn("Nummer Fremdsystem", 						false, 	new DatevCSVText(0,15)));
		s.put(221, 	new DatevCSVColumn("Insolvent", 								false, 	new DatevCSVZahl(1,0,1)));
		s.put(222, 	new DatevCSVColumn("Mandatsreferenz 1", 						false, 	new DatevCSVText(0,35)));
		s.put(223, 	new DatevCSVColumn("Mandatsreferenz 2", 						false, 	new DatevCSVText(0,35)));
		s.put(224, 	new DatevCSVColumn("Mandatsreferenz 3", 						false, 	new DatevCSVText(0,35)));
		s.put(225, 	new DatevCSVColumn("Mandatsreferenz 4", 						false, 	new DatevCSVText(0,35)));
		s.put(226, 	new DatevCSVColumn("Mandatsreferenz 5", 						false, 	new DatevCSVText(0,35)));
		s.put(227, 	new DatevCSVColumn("Mandatsreferenz 6", 						false, 	new DatevCSVText(0,35)));
		s.put(228, 	new DatevCSVColumn("Mandatsreferenz 7", 						false, 	new DatevCSVText(0,35)));
		s.put(229, 	new DatevCSVColumn("Mandatsreferenz 8", 						false, 	new DatevCSVText(0,35)));
		s.put(230, 	new DatevCSVColumn("Mandatsreferenz 9", 						false, 	new DatevCSVText(0,35)));
		s.put(231, 	new DatevCSVColumn("Mandatsreferenz 10", 						false, 	new DatevCSVText(0,35)));
		s.put(232, 	new DatevCSVColumn("Verknüpftes OPOS-Konto", 					false, 	new DatevCSVKonto(0,9)));	
	}
}