package rohstoff.Echo2BusinessLogic.BAM_IMPORT;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BAM_IMPORT.BAMImporter.ENUM_TABLES_TO_CONNECT_TO;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;


public class BAM_IMPORT_BT_Disconnect_Wiegekarte extends MyE2_Button
{

	public BAM_IMPORT_BT_Disconnect_Wiegekarte( E2_BasicModuleContainer_MASK omaskContainer )
	{
		super(E2_ResourceIcon.get_RI("clear.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.setToolTipText(new MyString("Löscht die Verbindung zwischen importierten Daten und Wiegekarte").CTrans());
		this.add_oActionAgent(new ownActionAgent(omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("BAM_DISCONNECT_WIEGEKARTE"));
	}
	
	
	
	/**
	 * 
	 * @author manfred
	 * @date   13.03.2013
	 */
	private class ownActionAgent extends XX_ActionAgent
	{
		
		E2_BasicModuleContainer_MASK m_omaskContainer = null;
		String m_sIDEmailEntry = null;
		
		public ownActionAgent( E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super();
			m_omaskContainer = omaskContainer;
		}

		
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
 
			
			
			MyE2_Button			oButton = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			E2_ComponentMAP 	oMap = oButton.EXT().get_oComponentMAP();
			
			String cIDBAMImport = oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
//			MyE2_DB_TextField   oID = (MyE2_DB_TextField) oMap.get__Comp("ID_WIEGEKARTE");
//			String cIDWiegekarte = oMap.get_oInternalSQLResultMAP().get_UnFormatedValue("ID_WIEGEKARTE");
			
			// trennen der Wiegeakarte vom Import-Modul
			BAMImporter bamImport = new BAMImporter();
			bamImport.disconnectFromBAMImport(ENUM_TABLES_TO_CONNECT_TO.WIEGEKARTE, cIDBAMImport);
			
			if (bibMSG.get_bIsOK()){
				m_omaskContainer.get_oNavigationListWhichBelongsToTheMask().RefreshList();

				// neues Laden der Maske nachdem die unerliegenden Daten geändert wurden
				String sSTATUS_MASKE = oMap.get_oModulContainerMASK_This_BelongsTo().get_vCombinedComponentMAPs().get_cLAST_FILLED_STATUS();
				oMap.get_oModulContainerMASK_This_BelongsTo().get_vCombinedComponentMAPs().Requery_All_ActualResultMAPs(sSTATUS_MASKE);
				// ********

//				DB_SEARCH_Adress o = (DB_SEARCH_Adress)oMap.get__DBComp("ID_ADRESSE_ZUGEORDNET");
//				String cIDAdresse = oMap.get_oInternalSQLResultMAP().get_UnFormatedValue("ID_ADRESSE_ZUGEORDNET");
				
			}
			// buttonstatus nach Speichern Setzen
//			((MAIL_INBOX_MASK_ComponentMAP)oMap).setButtonStatus_Of_MaskButtons();
			
		}
	}

}
