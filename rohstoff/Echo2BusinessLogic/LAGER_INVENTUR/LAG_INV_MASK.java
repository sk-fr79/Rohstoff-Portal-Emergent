package rohstoff.Echo2BusinessLogic.LAGER_INVENTUR;


import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class LAG_INV_MASK extends MyE2_Grid 
{

	
	public LAG_INV_MASK(LAG_INV_MASK_ComponentMAP oMap) throws myException
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		//hier kommen die Felder	
		oFiller.add_Line(this, new MyE2_String("Inventur ID:"), 1, "ID_LAGER_INVENTUR|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Lager:"), 1, "ID_ADRESSE_LAGER|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Sorte:"), 1, "ID_ARTIKEL_SORTE|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Menge:"), 1, "MENGE|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Preis:"), 1, "PREIS|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Buchungsdatum:"), 1, "BUCHUNGSDATUM|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Buchungszeit:"), 1, "BUCHUNGSZEIT|#  |",3);

		/*		
		 * beispiele fuer maskendefinition
		 *
		oFiller.add_Line(this, new MyE2_String("Besitzer:"), 1, "ID_USER|#  |#ID|ID_LAG_INV",3);
		oFiller.add_Line(this, new MyE2_String("Dringlichkeit:"), 1, "ID_LAG_INV_WICHTIGKEIT|# ",3);   
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
