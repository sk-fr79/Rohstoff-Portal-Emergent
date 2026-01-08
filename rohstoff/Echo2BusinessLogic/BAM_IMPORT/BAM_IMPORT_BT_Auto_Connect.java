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
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;


public class BAM_IMPORT_BT_Auto_Connect extends MyE2_Button
{

	public BAM_IMPORT_BT_Auto_Connect( E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("wizard_mini.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.setToolTipText(new MyString("Versucht die Wiegekarte und Fuhre zum importierten Eintrag zu finden.").CTrans());
		this.add_oActionAgent(new ownActionAgent(omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("BAM_AUTO_CONNECT"));
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
			
			// Automatisches Verbinden der Wiegekarten mit der BAM-Meldung
			BAMImporter bamImport = new BAMImporter();
			
			String cID = oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			bamImport.doConnect_BAM_Automatically(cID);
			
			
			if (bibMSG.get_bIsOK()){
				m_omaskContainer.get_oNavigationListWhichBelongsToTheMask().RefreshList();
	
				// neues Laden der Maske nachdem die unerliegenden Daten geändert wurden
				String sSTATUS_MASKE = oMap.get_oModulContainerMASK_This_BelongsTo().get_vCombinedComponentMAPs().get_cLAST_FILLED_STATUS();
				oMap.get_oModulContainerMASK_This_BelongsTo().get_vCombinedComponentMAPs().Requery_All_ActualResultMAPs(sSTATUS_MASKE);
				
				// ********
				
			}

			
		}
		
		
	}

}
