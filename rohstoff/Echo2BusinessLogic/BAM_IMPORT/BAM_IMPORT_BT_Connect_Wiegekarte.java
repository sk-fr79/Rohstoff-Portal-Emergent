package rohstoff.Echo2BusinessLogic.BAM_IMPORT;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BAM_IMPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WIEGEKARTE;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BAM_IMPORT.BAMImporter.ENUM_TABLES_TO_CONNECT_TO;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;


public class BAM_IMPORT_BT_Connect_Wiegekarte extends MyE2_Button
{

	public BAM_IMPORT_BT_Connect_Wiegekarte( E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("suche.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.setToolTipText(new MyString("Prüft die eingegebene Wiegekarten-ID und verbindet die Anhänge bei erfolgreicher Prüfung.").CTrans());
		this.add_oActionAgent(new ownActionAgent(omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("BAM_CONNECT_WIEGEKARTE"));
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
			
			MyE2_DB_TextField   oIDwiegekarte = (MyE2_DB_TextField) oMap.get__Comp("ID_WIEGEKARTE");
			String cIDWiegekarte = oIDwiegekarte.getText();
			// Punkt der formatierten Darstellung entfernen
			cIDWiegekarte = cIDWiegekarte != null ? cIDWiegekarte.replace(".", "") : null;
			
			String cIDWiegekarteOld = oMap.get_oInternalSQLResultMAP().get_UnFormatedValue("ID_WIEGEKARTE");
			
			String cID = oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			RECORD_WIEGEKARTE recWK = null;
			RECORD_BAM_IMPORT recBAM = null;
			
			if (cIDWiegekarte.equals(cIDWiegekarteOld)){
				// wenn sich nichts geändert hat, dann auch nichts zu prüfen!!
				return;
			}

			
			try {
				// Wiegekarte und BAM lesen, ob beides vorhanden ist.
				recWK = new RECORD_WIEGEKARTE(cIDWiegekarte);
				recBAM = new RECORD_BAM_IMPORT(cID);
			} catch (Exception e) {
				recWK = null;
			}

			
			if (recWK != null && recBAM != null){
				// wenn Wiegekarte und BAM vorhanden sind, dann verbinden
				
				// zuerst die alte Wiegekarte trennen, falls eine gebunden war
				bamImport.disconnectFromBAMImport(ENUM_TABLES_TO_CONNECT_TO.WIEGEKARTE, cID);
				
				// dann die neue binden
				bamImport.connect_BAM_to_Wiegekarte(recBAM, cIDWiegekarte);
				
				if (bibMSG.get_bIsOK()){
					m_omaskContainer.get_oNavigationListWhichBelongsToTheMask().RefreshList();
					// neues Laden der Maske nachdem die unerliegenden Daten geändert wurden
					String sSTATUS_MASKE = oMap.get_oModulContainerMASK_This_BelongsTo().get_vCombinedComponentMAPs().get_cLAST_FILLED_STATUS();
					oMap.get_oModulContainerMASK_This_BelongsTo().get_vCombinedComponentMAPs().Requery_All_ActualResultMAPs(sSTATUS_MASKE);
					// ********
				}
			} else {
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Es konnte keine Wiegekarte mit der angegenben ID gefunden werden"));
				oIDwiegekarte.setText("");
			}
			
		}
		
		
	}

}
