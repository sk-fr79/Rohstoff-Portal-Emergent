package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.exceptions.myException;

public class FP_ActionAgent_UMBUCHEN_STD extends FP_ActionAgent_UMBUCHEN
{

	public FP_ActionAgent_UMBUCHEN_STD(E2_NavigationList naviList,	RECORD_VPOS_TPA_FUHRE RecFuhre, XX_ActionAgent ZusatzActionNachSave) throws myException
	{
		super(naviList, RecFuhre, ZusatzActionNachSave);
	}

	@Override
	public void aktion_nach_umbuchen(String umbuchung) throws myException
	{
	}

}
