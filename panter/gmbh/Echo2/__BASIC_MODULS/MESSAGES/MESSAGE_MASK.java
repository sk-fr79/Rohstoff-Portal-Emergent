package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;


import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class MESSAGE_MASK extends MyE2_Grid 
{

	
	public MESSAGE_MASK(MESSAGE_MASK_ComponentMAP oMap) throws myException
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		//hier kommen die Felder	
		oFiller.add_Line(this, new MyE2_String("BESTAETIGT:"), 1, "BESTAETIGT|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID_NACHRICHT:"), 1, "ID_NACHRICHT|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID_NACHRICHT_PARENT:"), 1, "ID_NACHRICHT_PARENT|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID_USER:"), 1, "ID_USER|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID_USER_SENDER:"), 1, "ID_USER_SENDER|#  |",3);
		oFiller.add_Line(this, new MyE2_String("NACHRICHT:"), 1, "NACHRICHT|#  |",3);
		oFiller.add_Line(this, new MyE2_String("TITEL:"), 1, "TITEL|#  |",3);

		
		/*		
		 * beispiele fuer maskendefinition
		 *
		oFiller.add_Line(this, new MyE2_String("Besitzer:"), 1, "ID_USER|#  |#ID|ID_MESSAGE",3);
		oFiller.add_Line(this, new MyE2_String("Dringlichkeit:"), 1, "ID_MESSAGE_WICHTIGKEIT|# ",3);   
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
