package rohstoff.Echo2BusinessLogic._TAX.RULES;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.bibDB_SYNTAX;

public class TR__LIST_Selector_SelActiveOrNot extends E2_ListSelektorMultiselektionStatusFeld_STD
{

	public TR__LIST_Selector_SelActiveOrNot()
	{
		super(1,false,new MyE2_String("Zeige: "),new Extent(95));
		
		bibDB_SYNTAX.GENERATE_YES_NO_WHERE_IN_BRACKETS(_DB.HANDELSDEF, _DB.HANDELSDEF$AKTIV, "N");
		
		this.ADD_STATUS_TO_Selector(true,	bibDB_SYNTAX.GENERATE_YES_NO_WHERE_IN_BRACKETS(_DB.HANDELSDEF, _DB.HANDELSDEF$AKTIV, "Y"),	new MyE2_String("Aktiv"),   	new MyE2_String("Zeige Handelsdefinitionen an, die als AKTIV markiert sind"));		
		this.ADD_STATUS_TO_Selector(true,	bibDB_SYNTAX.GENERATE_YES_NO_WHERE_IN_BRACKETS(_DB.HANDELSDEF, _DB.HANDELSDEF$AKTIV, "N"),	new MyE2_String("Inaktiv"),    new MyE2_String("Zeige Handelsdefinitionen an, die als INAKTIV markiert sind"));
		
		this.set_cConditionWhenAllIsSelected("");
		
		
		
	}

}
