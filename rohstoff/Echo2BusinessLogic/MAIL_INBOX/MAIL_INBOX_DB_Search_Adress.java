package rohstoff.Echo2BusinessLogic.MAIL_INBOX;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;

public class MAIL_INBOX_DB_Search_Adress extends DB_SEARCH_Adress {


	
	public MAIL_INBOX_DB_Search_Adress(SQLField osqlfield, E2_BasicModuleContainer_MASK omaskContainer) throws myException {
		super(osqlfield);
		
		this.set_oMaskActionAfterMaskValueIsFound(new actionAfterFound(omaskContainer));
	}

	
	/**
	 * hält den Maskencontainer um einen gefundenen Wert zu sichern
	 * 
	 * @author manfred
	 * @date   13.03.2013
	 */
	private class actionAfterFound extends XX_MaskActionAfterFound{

		E2_BasicModuleContainer_MASK m_omaskContainer = null;
		public actionAfterFound( E2_BasicModuleContainer_MASK omaskContainer ) {
			m_omaskContainer = omaskContainer;
		}
		
		@Override
		public void doMaskSettingsAfterValueWrittenInMaskField(	String cMaskValue, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall)
				throws myException {
			
			MAIL_INBOX_DB_Search_Adress oThis = MAIL_INBOX_DB_Search_Adress.this;
			
			String sIDAddress = oThis.get_oTextFieldForSearchInput().getText().replace(".", "");
			
			// prüfen, ob der wert nicht schon in der MAP steht, also schon in der DB vorhanden ist
			E2_ComponentMAP 	oMap = oSearchField.EXT().get_oComponentMAP();
			String sIDAdresseZugeordnet_ORI = oMap.get_oInternalSQLResultMAP().get_UnFormatedValue("ID_ADRESSE_ZUGEORDNET");
			
			
			if (!bibALL.isEmpty(sIDAddress) && !sIDAddress.equals(sIDAdresseZugeordnet_ORI)){
				// ermitteln der ID des INBOX-Satzes
				MyE2_DB_TextField   oID = (MyE2_DB_TextField) oMap.get__Comp("ID_EMAIL_INBOX");
				String sIDInbox = oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				
				MAIL_INBOX_Handler oHandler = new MAIL_INBOX_Handler();
				oHandler.connectAddressToEmailEntry(sIDInbox,sIDAddress);
				
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
	
	
}
