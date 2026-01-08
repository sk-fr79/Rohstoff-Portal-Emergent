package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.ENUM_SESSION_SIMPLE_SAVER;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LIEFERADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LIEFERADRESSE;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class WK_LIST_BT_PRINT extends MyE2_Button
{

	public enum ENUM_DruckTyp	{ WIEGEKARTE, WK_EINGANGSSCHEIN, EINGANGS_LIEFERSCHEIN, ETIKETT, BUERO, HOFSCHEIN };
	
	private E2_NavigationList 	m_navigationList = null;
	private String 				m_IdWiegekarte = null;
	private WK_WiegekartenHandler m_oWKHandler = null;
	
	
	private E2_BasicModuleContainer m_oParent = null;

	private boolean 			m_bDruckeEtikett = false;
	private boolean 			m_bDruckeWiegekarte = false;
	private boolean			m_bDruckeEingangsscheinLieferschein = false;
	private boolean 			m_bDruckeAusgangBuero = false;
	private boolean 			m_bErzwingeDruckEingangsscheinLieferschein = false;
	
	private boolean  			m_bDruckeHofschein = false;
	
	private String 				m_sWEWA = ""; 			
	
	
	private WK_Print_Handler 	oPH = null;
	
	public WK_LIST_BT_PRINT(E2_BasicModuleContainer oParent, String Caption){
		super(new MyE2_String(Caption),E2_ResourceIcon.get_RI("printer.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.m_oParent = oParent;
		this.add_oActionAgent(new printActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("DRUCKEN_WK"));

	}

	public WK_LIST_BT_PRINT(E2_BasicModuleContainer oParent, String Caption,ENUM_DruckTyp eTyp){
		this(oParent,Caption);
		switch (eTyp) {
			case WIEGEKARTE:
				m_bDruckeWiegekarte = true;
			break;
			case WK_EINGANGSSCHEIN:
				m_bDruckeWiegekarte = true;
				m_bDruckeEingangsscheinLieferschein = true;
			break;
			case BUERO:
				m_bDruckeWiegekarte = true;
				m_bDruckeAusgangBuero = true;
			break;
			case ETIKETT:
				m_bDruckeEtikett = true;
			break;
			case EINGANGS_LIEFERSCHEIN:
				m_bDruckeEingangsscheinLieferschein = true;
				m_bErzwingeDruckEingangsscheinLieferschein = true;
			break;	
			case HOFSCHEIN:
				m_bDruckeHofschein= true;
			break;	
			
			default:
				//
			break;
		}
	}
	
	
	
	public WK_LIST_BT_PRINT(E2_BasicModuleContainer oParent, String Caption,ENUM_DruckTyp eTyp, E2_NavigationList onavigationList) throws myException{
		this(oParent,Caption,eTyp);
		
		this.m_navigationList = onavigationList;
		this.set_bEnabled_For_Edit(onavigationList != null);
	}


	public WK_LIST_BT_PRINT(E2_BasicModuleContainer oParent, String Caption,ENUM_DruckTyp eTyp,  String idWiegekarte) throws myException{
		this(oParent,Caption,eTyp);
		this.m_IdWiegekarte = idWiegekarte;
		this.set_bEnabled_For_Edit(m_IdWiegekarte != null);
		
	}


	
	
	/**
	 * Setzen der aktuellen zu druckenden Wiegekarte
	 * @param idWiegekarte
	 */
//	public void setIDWiegekarte ( String idWiegekarte){
//		m_IdWiegekarte = idWiegekarte;
//		try {
//			this.set_bEnabled_For_Edit(m_IdWiegekarte != null);
//		} catch (myException e) {
//			e.printStackTrace();
//		}
//	}
	public void setIDWiegekarte ( WK_WiegekartenHandler oHandler){
		m_oWKHandler = oHandler;
		
		if (m_oWKHandler != null ){
			m_IdWiegekarte = m_oWKHandler.get_ID_Wiegekarte();

			// Extra-Jasperwert setzen
			String sIstLieferant = m_oWKHandler.get_BuchungWiegekarte().getIST_LIEFERANT();
			if (!bibALL.isEmpty(sIstLieferant) && sIstLieferant.equalsIgnoreCase("Y") ) {
					m_sWEWA = "WE";
			} else {
					m_sWEWA = "WA";
			} 
		} 
		
		
		try {
			this.set_bEnabled_For_Edit(m_IdWiegekarte != null);
		} catch (myException e) {
			e.printStackTrace();
		}
	}

	
	
	private class printActionAgent extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WK_LIST_BT_PRINT oThis = WK_LIST_BT_PRINT.this;
			
			// DEBUG (nicht nötig aber stört auch nicht...)
			WK_Print_Handler oTest = (WK_Print_Handler) ENUM_SESSION_SIMPLE_SAVER.WIEGEKARTEN_DRUCK.getValue();
			// DEBUG
			
			if (oThis.m_IdWiegekarte != null){
				oThis.oPH = new WK_Print_Handler(oThis.m_IdWiegekarte);
			} else if(m_navigationList != null) {
				oThis.oPH = new WK_Print_Handler(oThis.m_navigationList);
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Es ist keine Wiegekarte ausgewählt!")));
			}
			
			ENUM_SESSION_SIMPLE_SAVER.WIEGEKARTEN_DRUCK.setValue(oThis.oPH);
			
			// abfragen, wieviele Kopien man braucht, wenn es etiketten sind:
			if (m_bDruckeEtikett){
				if (m_oParent instanceof WK_Erfassung_Waegung){
					Integer nCopies = ((WK_Erfassung_Waegung)m_oParent).getAnzahlEtiketten();
					if (nCopies != null && nCopies.compareTo(1) >= 0){
						oThis.oPH.set_nCopyEtikett(nCopies.intValue());
					} else {
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Es muss die Anzahl der Kopien angegeben werden!")));
					}
				}
			}

			if (bibMSG.get_bIsOK()){

				oThis.oPH.set_PrintEtikett(m_bDruckeEtikett);
				oThis.oPH.set_PrintEingangsschein(m_bDruckeEingangsscheinLieferschein);
				oThis.oPH.set_PrintWiegekarte(m_bDruckeWiegekarte);
				oThis.oPH.set_PrintBuero(m_bDruckeAusgangBuero);
				oThis.oPH.set_ErzwingeDruckEingangsschein(m_bErzwingeDruckEingangsscheinLieferschein);
			    oThis.oPH.set_PrintHofschein(m_bDruckeHofschein);
				
				// ermitteln des Lieferanten/Abnehmers  : Hauptadresse und Lageradresse
				
				// das Lager des Lieferanten übergeben
				oThis.oPH.set_IDAdresseLager(m_oWKHandler.get_BuchungWiegekarte().getID_ADRESSE_LIEFERANT());
				
				// die Adresse des Lieferanten ermitteln
				if (m_oWKHandler.get_BuchungWiegekarte().getID_ADRESSE_LIEFERANT()!= null){
					RECLIST_LIEFERADRESSE oListLieferadresse = new RECLIST_LIEFERADRESSE(
							RECORD_LIEFERADRESSE.FIELD__ID_ADRESSE_LIEFER + " = " + m_oWKHandler.get_BuchungWiegekarte().getID_ADRESSE_LIEFERANT() , "");
					if (oListLieferadresse.size() > 0){
						oThis.oPH.set_IDAdresse(oListLieferadresse.get(0).get_ID_ADRESSE_BASIS_cUF());
					} else {
						oThis.oPH.set_IDAdresse(m_oWKHandler.get_BuchungWiegekarte().getID_ADRESSE_LIEFERANT());
					}
				}

				// WE/WA setzen
				oThis.oPH.setExtraHashValue(m_sWEWA);
				
				// Zusätzliche Infos in der JasperHash setzen
				String sIDFuhre = oThis.m_oWKHandler.m_REC_Wiegekarte.get_ID_VPOS_TPA_FUHRE_cUF_NN("");
				oThis.oPH.addAdditionalParameter("ID_FUHRE", sIDFuhre);
				oThis.oPH.addAdditionalParameter("HAT_FUHRE", (bibALL.isEmpty(sIDFuhre)?"0":"1"));
				
				// Waagestandort noch übernehmen
				String sIDWaageStandort = oThis.m_oWKHandler.m_REC_Wiegekarte.get_ID_WAAGE_STANDORT_cUF_NN("");
				oThis.oPH.addAdditionalParameter(WK_Print_Handler.ENUM_ADDITIONAL_VALUES.ID_WAAGE_STANDORT.Name(), sIDWaageStandort);
				
				
				// weitere Parameter übernehmen..
				String sIDAdresseKunde;
				String sIDAdresseLager;
				String sIDAdresseSpedition;
				String sIDADresseAbnStrecke;
				try {
					sIDAdresseKunde = oThis.m_oWKHandler.m_REC_Wiegekarte.get_ID_ADRESSE_LIEFERANT_cUF_NN("");
					sIDAdresseLager = oThis.m_oWKHandler.m_REC_Wiegekarte.get_ID_ADRESSE_LAGER_cUF_NN("");
					sIDAdresseSpedition = oThis.m_oWKHandler.m_REC_Wiegekarte.get_ID_ADRESSE_SPEDITION_cUF_NN("");
					sIDADresseAbnStrecke = oThis.m_oWKHandler.m_REC_Wiegekarte.get_ID_ADRESSE_ABN_STRECKE_cUF_NN("");
					
				} catch (myException e1) {
					sIDAdresseKunde = "";
					sIDAdresseLager = "";
					sIDAdresseSpedition = "";
					sIDADresseAbnStrecke = "";
				}
				
				oThis.oPH.addAdditionalParameter(WK_Print_Handler.ENUM_ADDITIONAL_VALUES.ID_ADRESSE_KUNDE.Name(), sIDAdresseKunde);
				oThis.oPH.addAdditionalParameter(WK_Print_Handler.ENUM_ADDITIONAL_VALUES.ID_ADRESSE_LAGER.Name(), sIDAdresseLager);
				oThis.oPH.addAdditionalParameter(WK_Print_Handler.ENUM_ADDITIONAL_VALUES.ID_ADRESSE_SPEDITION.Name(), sIDAdresseSpedition);
				oThis.oPH.addAdditionalParameter(WK_Print_Handler.ENUM_ADDITIONAL_VALUES.ID_ADRESSE_ABN_STRECKE.Name(), sIDADresseAbnStrecke);
				
				// drucken
				oThis.oPH.Print();
				
				if (m_bDruckeWiegekarte){
					m_oWKHandler.setWiegekartenInFuhre();
				}
				
				if (m_navigationList != null){
					m_navigationList.RefreshList();
				}
			}
		}
	}
	
}
