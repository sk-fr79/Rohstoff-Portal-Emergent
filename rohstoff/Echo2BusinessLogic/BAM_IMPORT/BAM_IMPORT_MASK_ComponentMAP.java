package rohstoff.Echo2BusinessLogic.BAM_IMPORT;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BAM_IMPORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BAM_IMPORT.BAMImporter.ENUM_TABLES_TO_CONNECT_TO;
import rohstoff.Echo2BusinessLogic.MAIL_INBOX.MAIL_INBOX_BT_CONNECT_ADDRESS;
import rohstoff.Echo2BusinessLogic.MAIL_INBOX.MAIL_INBOX_BT_DISCONNECT_ADDRESS;
import rohstoff.Echo2BusinessLogic.MAIL_INBOX.MAIL_INBOX_Const;


public class BAM_IMPORT_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public BAM_IMPORT_MASK_ComponentMAP(BAM_IMPORT_MASK_BasicModuleContainer oModulContainerMask) throws myException
	{
		super(new BAM_IMPORT_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		/*
		 * beispiele fuer felder in der map
		 *
		*/
		String[] cFieldsStandard = {"ERZEUGT_AM","ERZEUGT_VON"}; 
		String[] cFieldsInfolabs = {"ERZEUGT_AM","ERZEUGT_VON"}; 

		//E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_BAM_IMPORT_WICHTIGKEIT","BESCHREIBUNG","ID_BAM_IMPORT_WICHTIGKEIT","ISTSTANDARD",true);
		
		String idLager = "";
		
		MaskComponentsFAB.addStandardComponentsToMAP(cFieldsStandard, cFieldsInfolabs, oFM, false, false, this, 400);

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BELEGNUMMER"),true,500,100,true), new MyE2_String("BELEGNUMMER"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_BAM_IMPORT"),true,200,10,true), new MyE2_String("ID_BAM_IMPORT"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_TPA_FUHRE"),true,200,10,true), new MyE2_String("ID_VPOS_TPA_FUHRE"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_TPA_FUHRE_ORT"),true,200,10,true), new MyE2_String("ID_VPOS_TPA_FUHRE_ORT"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_WIEGEKARTE"),true,200,10,false), new MyE2_String("ID_WIEGEKARTE"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("ABGESCHLOSSEN"),true), new MyE2_String("Abzugsliste verarbeitet"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BAM_ANGELEGT_AM"),true,200,10,true), new MyE2_String("Angelegt im Device am"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BAM_GESENDET_AM"),true,200,10,true), new MyE2_String("Übertragen am"));
		
		
		
		this.add_Component(BAM_IMPORT_Const.BUTTON_DISCONNECT_WIEGEKARTE	, new BAM_IMPORT_BT_Disconnect_Wiegekarte(oModulContainerMask), new MyE2_String("Wiegekarten-ID löschen"));
		this.add_Component(BAM_IMPORT_Const.BUTTON_CONNECT_WIEGEKARTE		, new BAM_IMPORT_BT_Connect_Wiegekarte(oModulContainerMask), new MyE2_String("Wiegekarten-ID prüfen und wenn gültig, verbinden."));
		this.add_Component(BAM_IMPORT_Const.BUTTON_AUTO_CONNECT				, new BAM_IMPORT_BT_Auto_Connect(oModulContainerMask), new MyE2_String("Wiegekarten-ID und Fuhren-IDs automatisch ermitteln"));
		

		BAM_IMPORT_SearchFieldFuhre oSearch = new BAM_IMPORT_SearchFieldFuhre( 	new ActionAgent_SearchFuhre(oModulContainerMask,this ,true),
																				new ActionAgent_SearchFuhre(oModulContainerMask,this ,false),
																				true
																				);
		oSearch.EXT().set_bDisabledFromBasic(true);
		oSearch.set_bEnabled_For_Edit(false);
		this.add_Component(BAM_IMPORT_Const.SEARCHFIELD_FUHRE, oSearch ,new MyE2_String("Fuhre suchen und zuordnen"));
		
		
		
		
		
		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new BAM_IMPORT_MASK_MapSettingAgent());

		

		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new BAM_IMPORT_MASK_FORMATING_Agent());
	}
	
	
	
	
	
	
	/**
	 * Action Agent für die Anbindung/Trennung der Fuhre vom Import
	 * @author manfred
	 *
	 */
	private class ActionAgent_SearchFuhre extends XX_ActionAgent{

		E2_BasicModuleContainer_MASK m_omaskContainer = null;
		E2_ComponentMAP				 m_ComponentMap = null;
		boolean 					 m_bAfterFound = false;
		
		public ActionAgent_SearchFuhre(E2_BasicModuleContainer_MASK omaskContainer ,E2_ComponentMAP oMap, boolean true_after_found_false_after_delete) {
			m_omaskContainer =omaskContainer;
			m_ComponentMap = oMap;
			m_bAfterFound = true_after_found_false_after_delete;
		}
		
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			BAM_IMPORT_SearchFieldFuhre oSearch = (BAM_IMPORT_SearchFieldFuhre) m_ComponentMap.get__Comp(BAM_IMPORT_Const.SEARCHFIELD_FUHRE);
			
			String sIDFuhre_MAP = m_ComponentMap.get_oInternalSQLResultMAP().get_UnFormatedValue("ID_VPOS_TPA_FUHRE");
			String sIDFuhreOrt_MAP = m_ComponentMap.get_oInternalSQLResultMAP().get_UnFormatedValue("ID_VPOS_TPA_FUHRE_ORT");
			
			String sIDFuhre_Searchfield = 	oSearch.get_IDFuhre();
			String sIDFuhreOrt_Searchfield =  oSearch.get_IDFuhreOrt();
			
			BAMImporter bamImporter = new BAMImporter();
			Long lIDBAM = m_ComponentMap.get_LActualDBValue("ID_BAM_IMPORT",null,null);
			String sIDBAM = (lIDBAM != null ? lIDBAM.toString():null);
			
			
			if (m_bAfterFound){
				// wenn die beiden Werte sich untescheiden und die 
				if (!bibALL.null2leer(sIDFuhre_MAP).equals(bibALL.null2leer(sIDFuhre_Searchfield))){
					
					// falls vorher schon eine Fuhre verbunden war muss diese zuerst gelöscht werden
					if (!bibALL.isEmpty(sIDFuhre_MAP) ){
						bamImporter.disconnectFromBAMImport(ENUM_TABLES_TO_CONNECT_TO.VPOS_TPA_FUHRE, sIDBAM);
					}
					// dann die neuen Vernküpfungen setzen
					bamImporter.connect_BAM_to_Fuhre(sIDBAM, sIDFuhre_Searchfield, sIDFuhreOrt_Searchfield);
				}
			} else {
				// wenn eine Fuhre verknüpft war und diese gelöscht wurde, dann disconnecten
				if(bibALL.isEmpty(sIDFuhre_Searchfield) && !bibALL.isEmpty(sIDFuhre_MAP)){
					bamImporter.disconnectFromBAMImport(ENUM_TABLES_TO_CONNECT_TO.VPOS_TPA_FUHRE, sIDBAM);
				}
			}
			
			if (bibMSG.get_bIsOK()){
				m_omaskContainer.get_oNavigationListWhichBelongsToTheMask().RefreshList();
				
				// neues Laden der Maske nachdem die unerliegenden Daten geändert wurden
				String sSTATUS_MASKE = m_ComponentMap.get_oModulContainerMASK_This_BelongsTo().get_vCombinedComponentMAPs().get_cLAST_FILLED_STATUS();
				m_ComponentMap.get_oModulContainerMASK_This_BelongsTo().get_vCombinedComponentMAPs().Requery_All_ActualResultMAPs(sSTATUS_MASKE);
				// ********
			}

			
			
			
//			MyE2_Button			oButton = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
//			E2_ComponentMAP 	oMap = oButton.EXT().get_oComponentMAP();
//			
//			MyE2_DB_TextField   oID = (MyE2_DB_TextField) oMap.get__Comp("ID_EMAIL_INBOX");
//			String cID = oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
//			
//			MAIL_INBOX_Handler oHandler = new MAIL_INBOX_Handler();
//			oHandler.connectAddressToEmailEntryAuto(cID);
//				
//			
//			if (bibMSG.get_bIsOK()){
//				m_omaskContainer.get_oNavigationListWhichBelongsToTheMask().RefreshList();
//
//				// neues Laden der Maske nachdem die unerliegenden Daten geändert wurden
//				String sSTATUS_MASKE = oMap.get_oModulContainerMASK_This_BelongsTo().get_vCombinedComponentMAPs().get_cLAST_FILLED_STATUS();
//				oMap.get_oModulContainerMASK_This_BelongsTo().get_vCombinedComponentMAPs().Requery_All_ActualResultMAPs(sSTATUS_MASKE);
//				// ********
//
//				DB_SEARCH_Adress o = (DB_SEARCH_Adress)oMap.get__DBComp("ID_ADRESSE_ZUGEORDNET");
//				String cIDAdresse = oMap.get_oInternalSQLResultMAP().get_UnFormatedValue("ID_ADRESSE_ZUGEORDNET");
//				
//			}
//			// buttonstatus nach Speichern Setzen
//			((MAIL_INBOX_MASK_ComponentMAP)oMap).setButtonStatus_Of_MaskButtons();

			
			
		}
		
	}
	
		
	
	
}
