package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SELECTORS;


import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class SEL_MASK extends MyE2_Grid 
{

	
	public SEL_MASK(SEL_MASK_ComponentMAP oMap) throws myException
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		//hier kommen die Felder	
		oFiller.add_Line(this, new MyE2_String("ID:"), 							1, 	_DB.SELECTOR$ID_SELECTOR+"|#  |"+
								SEL_MASK_ComponentMAP.MASK_COMP_HELPBUTTON+"|"+SEL_MASK_ComponentMAP.MASK_COMP_PRUEFBUTTON+"|# Aktiv:|"+_DB.SELECTOR$AKTIV,3);
		oFiller.add_Line(this, new MyE2_String("Anzeige im DropDown-Menue:"), 	1, 	_DB.SELECTOR$USER_TEXT+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Where-Block:"),			 		1, 	_DB.SELECTOR$WHEREBLOCK+"|#  |",3);

		/*		
		 * beispiele fuer maskendefinition
		 *
		oFiller.add_Line(this, new MyE2_String("Besitzer:"), 1, "ID_USER|#  |#ID|ID_SEL",3);
		oFiller.add_Line(this, new MyE2_String("Dringlichkeit:"), 1, "ID_SEL_WICHTIGKEIT|# ",3);   
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
