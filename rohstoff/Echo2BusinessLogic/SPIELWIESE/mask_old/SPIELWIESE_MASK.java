package rohstoff.Echo2BusinessLogic.SPIELWIESE.mask_old;


import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class SPIELWIESE_MASK extends MyE2_Grid 
{

	
	public SPIELWIESE_MASK(SPIELWIESE_MASK_ComponentMAP oMap) throws myException
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		//hier kommen die Felder	
		oFiller.add_Line(this, new MyE2_String("AKTIV:"), 1, "AKTIV|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID_FIBU_KONTENREGEL_NEU:"), 1, "ID_FIBU_KONTENREGEL_NEU|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID_FIBU_KONTO:"), 1, "ID_FIBU_KONTO|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID_FILTER:"), 1, "ID_FILTER|#  |",3);
		oFiller.add_Line(this, new MyE2_String("KOMMENTAR:"), 1, "KOMMENTAR|#  |",3);

		/*		
		 * beispiele fuer maskendefinition
		 *
		oFiller.add_Line(this, new MyE2_String("Besitzer:"), 1, "ID_USER|#  |#ID|ID_SPIELWIESE",3);
		oFiller.add_Line(this, new MyE2_String("Dringlichkeit:"), 1, "ID_SPIELWIESE_WICHTIGKEIT|# ",3);   
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
