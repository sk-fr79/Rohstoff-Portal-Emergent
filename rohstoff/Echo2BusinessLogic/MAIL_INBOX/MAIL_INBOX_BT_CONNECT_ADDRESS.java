package rohstoff.Echo2BusinessLogic.MAIL_INBOX;

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


public class MAIL_INBOX_BT_CONNECT_ADDRESS extends MyE2_Button
{

	public MAIL_INBOX_BT_CONNECT_ADDRESS( E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("verbinden.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.setToolTipText(new MyString("Versucht die zur Email gehörige Adresse zu ermitteln und zu verbinden.").CTrans());
		this.add_oActionAgent(new ownActionAgent(omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("BEARBEITE_MAIL_INBOX"));
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
//			
//			MyE2IF__Component oComponentFromExecInfo 	= (MyE2IF__Component)oExecInfo.get_MyActionEvent().getSource();
//			
//			if (oComponentFromExecInfo instanceof MyE2_Button){
//				E2_ComponentMAP oMap1 = oComponentFromExecInfo.EXT().get_oComponentMAP();
//				String ID1 = oMap1.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
//				
//				E2_ComponentMAP   oMap 	= ((MyE2_Button)oComponentFromExecInfo).EXT().get_oComponentMAP();
//				String ID = oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
//				
//				SQLResultMAP resMap = oMap.get_oInternalSQLResultMAP();
//				System.out.println( "******** Start of Fields **********" );
//				for(Map.Entry<String,MyResultValue> v: resMap.entrySet()){
//					System.out.println( v.getKey() + " - "+v.getValue().get_FieldValueUnformated() );
//				}
//				System.out.println( "******** End of Fields **********" );
//				
//			}
			
			
			MyE2_Button			oButton = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			E2_ComponentMAP 	oMap = oButton.EXT().get_oComponentMAP();
			
			MyE2_DB_TextField   oID = (MyE2_DB_TextField) oMap.get__Comp("ID_EMAIL_INBOX");
			String cID = oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			MAIL_INBOX_Handler oHandler = new MAIL_INBOX_Handler();
			oHandler.connectAddressToEmailEntryAuto(cID);
				
			
			if (bibMSG.get_bIsOK()){
				m_omaskContainer.get_oNavigationListWhichBelongsToTheMask().RefreshList();

				// neues Laden der Maske nachdem die unerliegenden Daten geändert wurden
				String sSTATUS_MASKE = oMap.get_oModulContainerMASK_This_BelongsTo().get_vCombinedComponentMAPs().get_cLAST_FILLED_STATUS();
				oMap.get_oModulContainerMASK_This_BelongsTo().get_vCombinedComponentMAPs().Requery_All_ActualResultMAPs(sSTATUS_MASKE);
				// ********

				DB_SEARCH_Adress o = (DB_SEARCH_Adress)oMap.get__DBComp("ID_ADRESSE_ZUGEORDNET");
				String cIDAdresse = oMap.get_oInternalSQLResultMAP().get_UnFormatedValue("ID_ADRESSE_ZUGEORDNET");
				
			}
			// buttonstatus nach Speichern Setzen
			((MAIL_INBOX_MASK_ComponentMAP)oMap).setButtonStatus_Of_MaskButtons();
			
		}
	}

}
