package rohstoff.Echo2BusinessLogic.LAGER_LISTE;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_KONTO;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerBuchung_Preiskorrektur;


public class LAG_KTO_BT_Preiskorrektur extends MyE2_Button
{

	/**
	 * @author manfred
	 * @date   05.10.2011
	 */
	private static final long serialVersionUID = -6305134016037954078L;

	public LAG_KTO_BT_Preiskorrektur(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("buchung_edit.png") , E2_ResourceIcon.get_RI("buchung_edit.png"));
		this.add_oActionAgent(new actionAgentPreiskorrektur(onavigationList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("LAG_KTO_PREISAENDERUNG_HANDBUCHUNG"));
		this.setToolTipText(new MyString("Preis einer Korrekturbuchung ändern").CTrans());
	}
	
	private class actionAgentPreiskorrektur extends XX_ActionAgent{

		E2_NavigationList m_navList = null;
		public actionAgentPreiskorrektur(E2_NavigationList onavigationList) {
			m_navList = onavigationList;
		}
		
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			
			if (m_navList == null || m_navList.get_vSelectedIDs_Unformated().size() != 1){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es kann nur genau eine Korrekturbuchung korrigiert werden.")));
				return;
			}

		    RECORD_LAGER_KONTO rec = new RECORD_LAGER_KONTO( m_navList.get_vSelectedIDs_Unformated().get(0) );	
			if(rec.get_BUCHUNG_HAND_cUF_NN("-").equals("-")){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es können nur Korrektur- oder Umbuchungen korrigiert werden.")));
				return;
			}
			
			
			String idLagerKonto = m_navList.get_vSelectedIDs_Unformated().firstElement();
			new LAG_LagerBuchung_Preiskorrektur(idLagerKonto,m_navList);
			
		}
		
	}
	
}
