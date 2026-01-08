package panter.gmbh.Echo2.__CONTAINER_ADDONS.TODOS;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;

public class TODO_MASK extends MyE2_Grid 
{

	
	public TODO_MASK(TODO_MASK_ComponentMAP oMap) throws myException
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);
		
		oFiller.add_Line(this, new MyE2_String("Besitzer:"), 1, "ID_USER|#  |#ID|ID_TODO",3);
		oFiller.add_Line(this, new MyE2_String("Dringlichkeit:"), 1, "ID_TODO_WICHTIGKEIT|# ",3);   
		oFiller.add_Line(this, new MyE2_String("Erstellt am:"), 1, "GENERIERUNGSDATUM|#   |#Zu erledigen bis|ABLAUFDATUM|#  |#Abgeschlossen |ERLEDIGT|#  am |ABSCHLUSSDATUM",3);
		this.add(new Separator(), 4, E2_INSETS.I_2_2_2_2);
		oFiller.add_Line(this, new MyE2_String("Aufgabe:"), 1, "AUFGABEKURZ",3);
		oFiller.add_Line(this, new MyE2_String("Details:"), 1, "AUFGABENTEXT",3);
		this.add(new Separator(), 4, E2_INSETS.I_2_2_2_2);
		oFiller.add_Line(this, new MyE2_String("Ergebnis:"), 1, "ANTWORTKURZ",3);
		oFiller.add_Line(this, new MyE2_String("Details:"), 1, "ANTWORTTEXT",3);
		this.add(new Separator(), 4, E2_INSETS.I_2_2_2_2);
		oFiller.add_Line(this, new MyE2_String("Teilnehmer:"), 1, TODO_MASK_BasicModuleContainer.HASHVALUE_TODO_TEILNEHMER,3);
		
	}

	
	
}
