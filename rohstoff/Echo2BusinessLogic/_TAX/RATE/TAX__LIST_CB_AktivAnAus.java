package rohstoff.Echo2BusinessLogic._TAX.RATE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TAX;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;

public class TAX__LIST_CB_AktivAnAus extends MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction
{

	public TAX__LIST_CB_AktivAnAus(SQLField osqlField) throws myException
	{
		super(osqlField);
		this.add_oActionAgent(this.get_ToggleAction());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(TAX_CONST.VALID_KEY_ERLAUBE_AKTIV_PASSIV_STEUER_IN_LISTE));
	}

	@Override
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		TAX__LIST_CB_AktivAnAus oCBCopy = null;
		try {
			oCBCopy = new TAX__LIST_CB_AktivAnAus(this.EXT_DB().get_oSQLField());
		} catch (myException e) {
			e.printStackTrace();
		}
	
		return oCBCopy;
	}

	@Override
	public XX_ActionAgent get_ToggleAction() throws myException
	{
		
		return new XX_ActionAgent()
		{
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				RECORD_TAX recTax = new RECORD_TAX(TAX__LIST_CB_AktivAnAus.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());		
				
				boolean newValue = !recTax.is_AKTIV_YES();
				
				recTax.set_NEW_VALUE_AKTIV(recTax.is_AKTIV_YES()?"N":"Y");
				
				MyE2_MessageVector oMV = recTax.UPDATE(true);
				if (oMV.get_bHasAlarms()) {
					bibMSG.add_MESSAGE(oMV);
				} else {
					TAX__LIST_CB_AktivAnAus.this.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Status wurde auf "+(newValue?"AKTIV":"INAKTIV")+" geändert!")));
				}
				
			}
		};
	}

}
