package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.GROOVYS;


import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibGroovy;
import panter.gmbh.indep.exceptions.myException;

public class GROOVY_MASK extends MyE2_Grid 
{

	
	public GROOVY_MASK(GROOVY_MASK_ComponentMAP oMap) throws myException
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		//hier kommen die Felder	
		oFiller.add_Line(this, new MyE2_String("ID:"), 							1, 	_DB.GROOVYSCRIPT$ID_GROOVYSCRIPT+"|#  |"+GROOVY_MASK_ComponentMAP.HASHKEY_HELP_BUTTON,3);
		oFiller.add_Line(this, new MyE2_String("Name der Rückgabe-Variable"), 	1, 	_DB.GROOVYSCRIPT$NAME_RETURN_VAR+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Text in der Benutzeranzeige"),	1, 	_DB.GROOVYSCRIPT$USER_TEXT+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Script"),			 			1, 	_DB.GROOVYSCRIPT$SCRIPT+"|#  |",3);
	}

	
	
}
