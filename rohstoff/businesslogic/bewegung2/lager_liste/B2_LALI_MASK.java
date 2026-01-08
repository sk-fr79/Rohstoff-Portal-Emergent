package rohstoff.businesslogic.bewegung2.lager_liste;


import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class B2_LALI_MASK extends MyE2_Grid 
{

	
	public B2_LALI_MASK(B2_LALI_MASK_ComponentMAP oMap) throws myException
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

//		//hier kommen die Felder	
//		oFiller.add_Line(this, new MyE2_String("ABGERECHNET:"), 1, "ABGERECHNET|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("ABRECHENBAR:"), 1, "ABRECHENBAR|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("ABZUG_MENGE:"), 1, "ABZUG_MENGE|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("ARTBEZ1:"), 1, "ARTBEZ1|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("ARTBEZ2:"), 1, "ARTBEZ2|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("BEMERKUNG:"), 1, "BEMERKUNG|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("BEMERKUNG_SACHBEARBEITER:"), 1, "BEMERKUNG_SACHBEARBEITER|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("BESTELLNUMMER:"), 1, "BESTELLNUMMER|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("BUCHUNGSNUMMER:"), 1, "BUCHUNGSNUMMER|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("DEL_DATE:"), 1, "DEL_DATE|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("DELETED:"), 1, "DELETED|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("DEL_GRUND:"), 1, "DEL_GRUND|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("DEL_KUERZEL:"), 1, "DEL_KUERZEL|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("E_PREIS:"), 1, "E_PREIS|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("E_PREIS_RESULT_NETTO_MGE:"), 1, "E_PREIS_RESULT_NETTO_MGE|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("EU_STEUER_VERMERK:"), 1, "EU_STEUER_VERMERK|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("ID_ARTIKEL:"), 1, "ID_ARTIKEL|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("ID_ARTIKEL_BEZ:"), 1, "ID_ARTIKEL_BEZ|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("ID_BEWEGUNG:"), 1, "ID_BEWEGUNG|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("ID_BEWEGUNG_ATOM:"), 1, "ID_BEWEGUNG_ATOM|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("ID_BEWEGUNG_VEKTOR:"), 1, "ID_BEWEGUNG_VEKTOR|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("ID_VPOS_KON:"), 1, "ID_VPOS_KON|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("ID_VPOS_STD:"), 1, "ID_VPOS_STD|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("KONTRAKTZWANG:"), 1, "KONTRAKTZWANG|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("KONTRAKTZWANG_AUS_AM:"), 1, "KONTRAKTZWANG_AUS_AM|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("KONTRAKTZWANG_AUS_GRUND:"), 1, "KONTRAKTZWANG_AUS_GRUND|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("KONTRAKTZWANG_AUS_VON:"), 1, "KONTRAKTZWANG_AUS_VON|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("LEISTUNGSDATUM:"), 1, "LEISTUNGSDATUM|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("LIEFERINFO_TPA:"), 1, "LIEFERINFO_TPA|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("MENGE:"), 1, "MENGE|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("NATIONALER_ABFALL_CODE:"), 1, "NATIONALER_ABFALL_CODE|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("NOTIFIKATION_NR:"), 1, "NOTIFIKATION_NR|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("ORDNUNGSNUMMER_CMR:"), 1, "ORDNUNGSNUMMER_CMR|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("PLANMENGE:"), 1, "PLANMENGE|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("POSNR:"), 1, "POSNR|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("POSTENNUMMER:"), 1, "POSTENNUMMER|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("PREISERMITTLUNG:"), 1, "PREISERMITTLUNG|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("PRUEFUNG_MENGE:"), 1, "PRUEFUNG_MENGE|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("PRUEFUNG_MENGE_AM:"), 1, "PRUEFUNG_MENGE_AM|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("PRUEFUNG_MENGE_VON:"), 1, "PRUEFUNG_MENGE_VON|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("SETZKASTEN_KOMPLETT:"), 1, "SETZKASTEN_KOMPLETT|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("STEUERSATZ:"), 1, "STEUERSATZ|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("STORNIERT:"), 1, "STORNIERT|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("STORNIERT_AM:"), 1, "STORNIERT_AM|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("STORNIERT_GRUND:"), 1, "STORNIERT_GRUND|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("STORNIERT_VON:"), 1, "STORNIERT_VON|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("ZEITSTEMPEL:"), 1, "ZEITSTEMPEL|#  |",3);

		/*		
		 * beispiele fuer maskendefinition
		 *
		oFiller.add_Line(this, new MyE2_String("Besitzer:"), 1, "ID_USER|#  |#ID|ID_ATOM_LAG",3);
		oFiller.add_Line(this, new MyE2_String("Dringlichkeit:"), 1, "ID_ATOM_LAG_WICHTIGKEIT|# ",3);   
		oFiller.add_Line(this, new MyE2_String("Erstellt am:"), 1, "GENERIERUNGSDATUM|#   |#Zu erledigen bis|ABLAUFDATUM|#  |#Abgeschlossen |ERLEDIGT|#  am |ABSCHLUSSDATUM",3);
		this.add(new Separator(), 4, E2_INSETS.I_2_2_2_2);
		oFiller.add_Line(this, new MyE2_String("Aufgabe:"), 1, "AUFGABEKURZ",3);
		oFiller.add_Line(this, new MyE2_String("Details:"), 1, "AUFGABENTEXT",3);
		this.add(new Separator(), 4, E2_INSETS.I_2_2_2_2);
		oFiller.add_Line(this, new MyE2_String("Ergebnis:"), 1, "ANTWORTKURZ",3);
		oFiller.add_Line(this, new MyE2_String("Details:"), 1, "ANTWORTTEXT",3);
		*/

	}

	
	
}
