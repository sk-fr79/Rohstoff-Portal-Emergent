package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_VERWALTUNG;


import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class MODUL__MASK extends MyE2_Grid 
{

	
	public MODUL__MASK(MODUL__MASK_ComponentMAP oMap) throws myException
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null,E2_INSETS.I_10_2_10_2,E2_INSETS.I_10_2_10_2,new Alignment(Alignment.LEFT,Alignment.CENTER));

		//hier kommen die Felder	
		oFiller.add_Line(this, new MyE2_String("ID:"), 1,		 		"ID_SERVLETS|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Hauptmenü:"), 1, 		"ID_HAUPTMENUE|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Menüeintrag:"), 1, 		"MENUEEINTRAG|#  |#Auswahl: |HOLE_MODUL",3);
		oFiller.add_Line(this, new MyE2_String("Text auf Tab:"), 1, 	"TABTEXT|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Beschreibung:"), 1, 	"BESCHREIBUNG|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Servlet-Aufruf:"), 1,	"SERVLETAUFRUF|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Sortierung innerhalb des Menüs:"), 1, 		"SORTNUMMER|#  |",3);
	}

	
	
}
