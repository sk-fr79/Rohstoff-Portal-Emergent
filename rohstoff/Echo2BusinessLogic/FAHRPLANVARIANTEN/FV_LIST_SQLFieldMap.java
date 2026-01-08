package rohstoff.Echo2BusinessLogic.FAHRPLANVARIANTEN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FV_LIST_SQLFieldMap extends SQLFieldMAP
{

	public FV_LIST_SQLFieldMap() throws myException
	{
		super("JT_FAHRTENVARIANTEN",bibE2.get_CurrSession());
		
		this.addCompleteTable_FIELDLIST("JT_FAHRTENVARIANTEN",":ID_FAHRTENVARIANTEN:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true, "");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_FAHRTENVARIANTEN","ID_FAHRTENVARIANTEN","ID_FAHRTENVARIANTEN",new MyE2_String("ID"),bibE2.get_CurrSession(),"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_FAHRTENVARIANTEN.NEXTVAL FROM DUAL",true), false);
	
		this.initFields();
	}

}
