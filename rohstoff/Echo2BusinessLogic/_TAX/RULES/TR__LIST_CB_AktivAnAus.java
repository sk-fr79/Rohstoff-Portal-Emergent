package rohstoff.Echo2BusinessLogic._TAX.RULES;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HANDELSDEF;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class TR__LIST_CB_AktivAnAus extends MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction
{

	public TR__LIST_CB_AktivAnAus(SQLField osqlField) throws myException
	{
		super(osqlField);
		this.add_oActionAgent(this.get_ToggleAction());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(TR__CONST.VALID_KEY_ERLAUBE_AKTIV_PASSIV_STEUER_IN_TR_LISTE));
		this.add_GlobalValidator(new ownValidator());
	}

	@Override
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		TR__LIST_CB_AktivAnAus oCBCopy = null;
		try {
			oCBCopy = new TR__LIST_CB_AktivAnAus(this.EXT_DB().get_oSQLField());
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
				RECORD_HANDELSDEF recRule = new RECORD_HANDELSDEF(TR__LIST_CB_AktivAnAus.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());		
				
				boolean newValue = !recRule.is_AKTIV_YES();
				
				recRule.set_NEW_VALUE_AKTIV(recRule.is_AKTIV_YES()?"N":"Y");
				
				MyE2_MessageVector oMV = recRule.UPDATE(true);
				if (oMV.get_bHasAlarms()) {
					bibMSG.add_MESSAGE(oMV);
				} else {
					TR__LIST_CB_AktivAnAus.this.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
					TR__LIST_CB_AktivAnAus.this.EXT().get_oComponentMAP().getComponentMapMarker().formatComponentMap();

//					Color  colHighlight = new RECORD_MANDANT_ext(bibALL.get_RECORD_MANDANT()).get_COLOR_MASK_HIGHLIGHT();
//					E2_ComponentMAP_TOOLS.SetBackgroundColorInList(TR__LIST_CB_AktivAnAus.this.EXT().get_oComponentMAP(), newValue?colHighlight:null);
					
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Status wurde auf "+(newValue?"AKTIV":"INAKTIV")+" geändert!")));
				}
				
			}
		};
	}

	
	private class ownValidator extends XX_ActionValidator {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			TR__LIST_CB_AktivAnAus oThis = TR__LIST_CB_AktivAnAus.this;
			
			MyE2_MessageVector  oMVRueck = new MyE2_MessageVector(); 
			
			String cID_HANDELSDEF = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			RECORD_HANDELSDEF  recDEF = new RECORD_HANDELSDEF(cID_HANDELSDEF);
			
			if (oThis.isSelected()) {
				if (S.isEmpty(recDEF.get_ID_TAX_QUELLE_cUF_NN("")) || 
					S.isEmpty(recDEF.get_ID_TAX_ZIEL_cUF_NN(""))) {
					
					oMVRueck.add(new MyE2_Alarm_Message(new MyE2_String("Eine Handelsdefinition kann nur aktiv sein, wenn die Steuersätze definiert sind !")));
				}
			}
			
			return oMVRueck;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
			return new MyE2_MessageVector();
		}
		
	}
	
}
