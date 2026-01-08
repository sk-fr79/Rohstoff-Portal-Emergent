package panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCode;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectFieldWithParameter;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class LOGTRIG__MASK_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
		// wegen der DropDown-Liste mit Parametern muss man hier die Tabellenspalten immer neu lesen und dann nochmal die Spalte auswählen.
		((MyE2_DB_SelectField)oMAP.get__Comp("TABELLE")).get_vActionAgents().get(0).ExecuteAgentCode(new ExecINFO_OnlyCode());
		((MyE2_DB_SelectFieldWithParameter)oMAP.get__Comp("SPALTE")).set_ActiveInhalt_or_FirstInhalt("");
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		// wegen der DropDown-Liste mit Parametern muss man hier die Tabellenspalten immer neu lesen und dann nochmal die Spalte auswählen.
		((MyE2_DB_SelectField)oMAP.get__Comp("TABELLE")).get_vActionAgents().get(0).ExecuteAgentCode(new ExecINFO_OnlyCode());
		((MyE2_DB_SelectFieldWithParameter)oMAP.get__Comp("SPALTE")).set_ActiveInhalt_or_FirstInhalt(oUsedResultMAP.get_UnFormatedValue("SPALTE"));
	}

}
