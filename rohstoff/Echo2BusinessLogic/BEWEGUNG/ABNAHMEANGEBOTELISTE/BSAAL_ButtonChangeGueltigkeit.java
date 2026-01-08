package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;


import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


/*
 * unlock gesperrte angebote
 */
public class BSAAL_ButtonChangeGueltigkeit extends MyE2_Button 
{
	private BSAAL__ModulContainerLIST	oModulContainerList = null;
	
	
	public BSAAL_ButtonChangeGueltigkeit(BSAAL__ModulContainerLIST	oModulContainer) throws myException
	{

		super(E2_ResourceIcon.get_RI("calendar.png"), true);
		
		this.oModulContainerList = oModulContainer;

		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Änderung von Gültigkeitszeiträumen ...").CTrans());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oModulContainer.get_MODUL_IDENTIFIER(),BSAAL__CONST.BUTTON_AENDERE_DATUM_VON_BIS));
		
		this.add_IDValidator(new E2_Validator_ID_DBQuery("SELECT  NVL(JT_VKOPF_STD.ABGESCHLOSSEN,'N'),  NVL(JT_VKOPF_STD.DELETED,'N'),  NVL(JT_VPOS_STD.DELETED,'N') FROM " +
															bibE2.cTO()+".JT_VKOPF_STD," +bibE2.cTO()+".JT_VPOS_STD  WHERE "+
															"JT_VKOPF_STD.ID_VKOPF_STD=JT_VPOS_STD.ID_VKOPF_STD AND "+
															"JT_VPOS_STD.ID_VPOS_STD=#WERT#",
															bibALL.get_Array("N","N","N"),
															true, new MyE2_String("Gültigkeitsänderung nur in ungelöschten und offenen Positionen !")));			
	}
	

	
	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			BSAAL_ButtonChangeGueltigkeit oThis = BSAAL_ButtonChangeGueltigkeit.this;
			
			Vector<String> vSelectedIDs = oThis.oModulContainerList.get_oNaviList().get_vSelectedIDs_Unformated();

			if (vSelectedIDs.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte selektieren Sie mindestens 1 Angebotsposition !!"));
				return;
			}
			
			bibMSG.add_MESSAGE(oThis.valid_IDValidation(vSelectedIDs));
			
			if (bibMSG.get_bIsOK())
			{
				new BSAAL_POPUP_WINDOW_CHANGE_DATES(vSelectedIDs,
													BSAAL_ButtonChangeGueltigkeit.this.oModulContainerList.get_oNaviList());
			}
		}
	}
	
	
}
