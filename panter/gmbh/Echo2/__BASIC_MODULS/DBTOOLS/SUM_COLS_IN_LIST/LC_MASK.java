package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.SUM_COLS_IN_LIST;


import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class LC_MASK extends MyE2_Grid 
{

	
	public LC_MASK(LC_MASK_ComponentMAP oMap) throws myException
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		//hier kommen die Felder	
		oFiller.add_Line(this, new MyE2_String("ID:"), 1, 						"ID_COLUMNS_TO_CALC|#  |",3);
		oFiller.add_Separator(this, E2_INSETS.I(0,0,0,0));
		oFiller.add_Line(this, new MyE2_String("Aktiv:"), 1, 					_DB.COLUMNS_TO_CALC$ACTIVE+"|#  |",3);
		oFiller.add_Separator(this, E2_INSETS.I(0,0,0,0));
		oFiller.add_Line(this, new MyE2_String("Listenmodul:"), 1, 				"MODULNAME_LISTE|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Name der Spalte:"), 1, 			"COLUMN_LABEL|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Validierungskennung:"), 1, 		"VALIDATION_TAG|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Nachkommastellen:"), 1, 		"NUMBER_DECIMALS|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Summation über SQL-Query:"), 1, "SUMMATION_VIA_QUERY|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Zeige Ergebnis in Titelzeile:"), 1, _DB.COLUMNS_TO_CALC$SHOW_LINE_IN_LISTHEADER+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Titelzeile Popup-Fenster:"), 1, "TEXT4WINDOWTITLE|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Überschrift im Fenster:"), 1, 	"TEXT4TITLE_IN_WINDOW|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Beschriftung der Summe:"), 1, 	"TEXT4SUMMATION|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Tooltips auf dem Button:"), 1, 	"TOOLTIPS|#  |",3);
	}
}
