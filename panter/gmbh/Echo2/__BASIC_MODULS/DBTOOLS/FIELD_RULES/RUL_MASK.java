package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.FIELD_RULES;


import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class RUL_MASK extends MyE2_Grid 
{

	
	public RUL_MASK(RUL_MASK_ComponentMAP oMap) throws myException
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		//hier kommen die Felder	
		oFiller.add_Line(this, new MyE2_String("ID"), 1, 				_DB.FIELD_RULE$ID_FIELD_RULE+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Modul"), 1,				_DB.FIELD_RULE$MODUL_KENNER+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Datenbank-Tabelle"), 1,	_DB.FIELD_RULE$TABLE_NAME+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Datenbank-Feld"), 1,	_DB.FIELD_RULE$FIELD_NAME+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Benutzer"), 1,			_DB.FIELD_RULE$ID_USER+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Typ der Regel"), 1,		_DB.FIELD_RULE$RULETYPE+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Regel"), 1,				_DB.FIELD_RULE$RULE+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Information"), 1,		_DB.FIELD_RULE$RULE_INFO+"|#  |",3);

	}

	
	
}
