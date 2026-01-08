package panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER;


import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TRIGGER_DEF;
import panter.gmbh.indep.exceptions.myException;

public class LOGTRIG__MASK extends MyE2_Grid 
{

	
	public LOGTRIG__MASK(LOGTRIG__MASK_ComponentMAP oMap) throws myException
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		oFiller.add_Line(this, new MyE2_String("ID:"), 1, "ID_TRIGGER_DEF|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Tabelle:"), 1, "TABELLE|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Spalte:"), 1, "SPALTE|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Triggername:"), 1, "TRIGG_NAME|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Eintrag:"), 1, "BESCHREIBUNG|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Vordefiniert:"), 1, RECORD_TRIGGER_DEF.FIELD__VORDEFINIERT+"|#  |",3);



	}

	
	
}
