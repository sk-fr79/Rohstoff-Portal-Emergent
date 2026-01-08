package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.SEARCH_FIELDS;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public class PopUp_CONNECT_ARCHIVE_ENTRY_TO_OTHER_OBJECTS extends	E2_BasicModuleContainer
{
	
	public static final String BUTTON_SAVE_ARCHIVE_CONNECTIONS = 	"BUTTON_SAVE_ARCHIVE_CONNECTIONS";
	public static final String BUTTON_CANCEL_ARCHIV_CONNECTIONS = 		"BUTTON_CANCEL_ARCHIV_CONNECTIONS";
	
	private String m_MODULKENNER = null;
	
	private MyE2_Button butSave		= null;
	private MyE2_Button butCancel	= null;
//	private MyE2_Row    rowButtons 	= null;
	
	private MyE2_Grid   m_oGrid					= null;
	private Vector<String> m_vIDArchivmedien 	= null;      
//	private RECORD_ARCHIV_MEDIEN_EXT m_oRecArchiveMedien = null;
	
	private Vector<I_SearchField_For_UpAndDownload> m_vSearchFields ;
	
	
	/**
	 * @param ctable_name (Ohne JT_ oder JD_)
	 * @param cid_table
	 * @param cMODULKENNER
	 * @param bUploadButton
	 * @param cPredefinedMedienkenner
	 * @throws myException
	 */
	public PopUp_CONNECT_ARCHIVE_ENTRY_TO_OTHER_OBJECTS(Vector<String> vIDArchivmedien, String sModulkenner) throws myException
	{
		super();
		this.m_vIDArchivmedien = vIDArchivmedien;
		this.m_MODULKENNER = sModulkenner;

//		if(m_oRecArchiveMedien == null){
//			throw new myException("PopUp_For_UP_AND_DOWN_FILES:Constructor:RECORD for ArchiveMedien MUST NOT BE null !");
//		}

		
		m_oGrid = new MyE2_Grid(2, 0); 
	
		butSave = new MyE2_Button(new MyE2_String("Speichern"), E2_ResourceIcon.get_RI("save.png"),E2_ResourceIcon.get_RI("save__.png"));
		butSave.setToolTipText(new MyE2_String("Verbindet die Anlage an alle gewählten Einträge.").CTrans());
		butSave.add_oActionAgent(new ActionAgentSave());
		
		butCancel = new MyE2_Button(new MyE2_String("Abbrechen"), E2_ResourceIcon.get_RI("cancel.png"),E2_ResourceIcon.get_RI("cancel__.png"));
		butCancel.setToolTipText(new MyE2_String("Bricht den Vorgang ab ohne zu speichern.").CTrans());
		butCancel.add_oActionAgent(new ActionAgentCancel());
		
		m_vSearchFields = new Vector<I_SearchField_For_UpAndDownload>();
		
		if (!bibALL.isEmpty(m_MODULKENNER))
		{
			butSave.add_GlobalValidator(new E2_ButtonAUTHValidator(m_MODULKENNER,PopUp_CONNECT_ARCHIVE_ENTRY_TO_OTHER_OBJECTS.BUTTON_SAVE_ARCHIVE_CONNECTIONS));
			butCancel.add_GlobalValidator(new E2_ButtonAUTHValidator(m_MODULKENNER,PopUp_CONNECT_ARCHIVE_ENTRY_TO_OTHER_OBJECTS.BUTTON_CANCEL_ARCHIV_CONNECTIONS));
		}
		
		E2_ComponentGroupHorizontal oBedienPanel = new E2_ComponentGroupHorizontal(1,new Insets(2,1,10,1));
		oBedienPanel.add(butSave, new Insets(2,0,10,0));
		oBedienPanel.add(butCancel, new Insets(2,0,10,0));
		this.add(oBedienPanel,new Insets(2,1,1,2));
		
		this.add(m_oGrid);
		MyE2_Label lblHeading = new MyE2_Label(new MyString("Verbinden des gewählten Anhangs mit folgenden Datensätzen:"));
		lblHeading.setFont( new E2_FontPlain(2) );
		
		m_oGrid.add(lblHeading, 2, E2_INSETS.I_2_10_2_0);
		
		
		// Alle Suchfelder und Auswahlfelder initialisieren
		initConnectors();
		
	
	}

	
	
	/**
	 * fügt die Such- /Auswahlfelder zum Dialog dazu, abhängig vom aufrufenden Archivmedium 
	 * 
	 * @author manfred
	 * @throws myException 
	 * @date   04.04.2013
	 */
	private void initConnectors() throws myException{
		
		RECORD_ARCHIVMEDIEN_ext oRecArchiveMedien 	= null;
		SingleLineSelector_Factory oFactory 		= null;
		String sIDArchiv = null;
		String sTabelle = null;
		
		if (m_vIDArchivmedien.size() == 1){
			sIDArchiv = m_vIDArchivmedien.get(0);
			oRecArchiveMedien = new RECORD_ARCHIVMEDIEN_ext(sIDArchiv);
			sTabelle = oRecArchiveMedien.get_TABLENAME_cUF();
			
			oFactory = new SingleLineSelector_Factory(oRecArchiveMedien);
			
		}

		//
		//
		// VERBINDEN MIT ADRESSEN
		//
		//
		MyE2_Label lblHeading = new MyE2_Label(new MyString("Adresse:"));
		lblHeading.setFont( new E2_FontBold(1) );
		m_oGrid.add(lblHeading, 2, E2_INSETS.I_2_10_2_0);
		
		if (oFactory != null){
			Vector<SingleLineSelektor_Base> vSelectors = oFactory.getSelectorForAddress();
			if (vSelectors.size()> 0){
				for (SingleLineSelektor_Base s : vSelectors){
					this.addSearchField(s,s.get_LabelText());
				}
			}
		}
		
		SEARCH_Adress_ARCHIV oAdr = new SEARCH_Adress_ARCHIV();
		setConditionalParameterValue(oAdr,  oRecArchiveMedien);
		this.addSearchField(oAdr, "Adresse:");
		

		
		//
		//
		// VERBINDEN MIT Sorten
		//
		//
		lblHeading = new MyE2_Label(new MyString("Sorte:"));
		lblHeading.setFont( new E2_FontBold(1) );
		m_oGrid.add(lblHeading, 2, E2_INSETS.I_2_10_2_0);
		
		if (oFactory != null){
			Vector<SingleLineSelektor_Base> vSelectors = oFactory.getSelectorForSorte();
			if (vSelectors.size()> 0){
				for (SingleLineSelektor_Base s : vSelectors){
					this.addSearchField(s,s.get_LabelText());
				}
			}
		}
		SEARCH_Sorte_ARCHIV oSorte = new SEARCH_Sorte_ARCHIV();
		setConditionalParameterValue(oSorte,  oRecArchiveMedien);
		this.addSearchField(oSorte, "Sorte:");

		
		//
		//
		// VERBINDEN MIT Maschinen
		//
		//
		lblHeading = new MyE2_Label(new MyString("Maschinen:"));
		lblHeading.setFont( new E2_FontBold(1) );
		m_oGrid.add(lblHeading, 2, E2_INSETS.I_2_10_2_0);

		SEARCH_Maschinen_ARCHIV oMaschinen = new SEARCH_Maschinen_ARCHIV();
		setConditionalParameterValue(oMaschinen,  oRecArchiveMedien);
		this.addSearchField(oMaschinen, "Maschinen:");
		
		
		//
		// 
		// VERBINDEN MIT Rechnung
		//
		//
		lblHeading = new MyE2_Label(new MyString("Rechnung/Gutschrift:"));
		lblHeading.setFont( new E2_FontBold(1) );
		m_oGrid.add(lblHeading, 2, E2_INSETS.I_2_10_2_0);

		SEARCH_RG_KOPF_ARCHIV oRech = new SEARCH_RG_KOPF_ARCHIV("RECHNUNG");
		setConditionalParameterValue(oRech,  oRecArchiveMedien);
		this.addSearchField(oRech, "Rechnung:");
		
		SEARCH_RG_KOPF_ARCHIV oGut = new SEARCH_RG_KOPF_ARCHIV("GUTSCHRIFT");
		setConditionalParameterValue(oGut,  oRecArchiveMedien);
		this.addSearchField(oGut, "Gutschrift:");
		
		
		//
		//
		// VERBINDEN MIT Kontrakten
		//
		//
//		lblHeading = new MyE2_Label(new MyString("Kontrakt:"));
//		lblHeading.setFont( new E2_FontBold(1) );
//		m_oGrid.add(lblHeading, 2, E2_INSETS.I_2_10_2_0);
//
//		SEARCH_Kontrakt_ARCHIV oKon = new SEARCH_Kontrakt_ARCHIV();
//		setConditionalParameterValue(oKon,  oRecArchiveMedien);
//		this.addSearchField(oKon, "Kontrakt:");
				
	}
	
	

	
	/**
	 * Fügt ein Objekt mit dem gegebenen Interface zu der Maske dazu
	 * @author manfred
	 * @date   02.04.2013
	 * @param oSearchField
	 */
	public void addSearchField(I_SearchField_For_UpAndDownload oSearchField, String sDescription){
		
		m_oGrid.add(new MyE2_Label(new MyString(sDescription)));
		m_oGrid.add((Component) oSearchField);
		
		m_vSearchFields.add(oSearchField);
		
	}
	

	
	private void setConditionalParameterValue(I_SearchField_For_UpAndDownload searchField,RECORD_ARCHIVMEDIEN_ext rec) throws myException{
		String sTableNameInArchiv = rec.get_TABLENAME_cUF_NN("-");
		if (sTableNameInArchiv.equalsIgnoreCase("ADRESSE")){
			searchField.set_ConditionValue(UP_AND_DOWNLOAD_ENUM_CONDITIONS.ID_ADRESSE, rec.get_ID_TABLE_cUF());
		} else if (sTableNameInArchiv.equalsIgnoreCase("ARTIKEL")){
			searchField.set_ConditionValue(UP_AND_DOWNLOAD_ENUM_CONDITIONS.ID_ARTIKEL, rec.get_ID_TABLE_cUF());
		}
	}
	
	
	/**
	 * Geht alle Suchfelder durch und prüft ob ein Objekt ausgewählt wurde.
	 * Wenn ja, wir die Archivdatei an das Objekt geknüpft 
	 * @author manfred
	 * @throws myException 
	 * @date   02.04.2013
	 */
	private void ConnectArchiveEntryToAdditionalObjects() throws myException{
		
		// für alle gewählten ARCHIVMEDIEN
		for (String sIDArchiv: m_vIDArchivmedien){
			
			try {
				RECORD_ARCHIVMEDIEN_ext oRecArchiveMedien = new RECORD_ARCHIVMEDIEN_ext(sIDArchiv);

				// für alle neuen Verbindungen
				for (I_SearchField_For_UpAndDownload obj : m_vSearchFields){
					String sID = obj.get_FoundObjectID();
					if (!bibALL.isEmpty(sID) )
					{
						if (oRecArchiveMedien != null){
							oRecArchiveMedien.connectToAdditionalEntry(obj.get_DBTableName(), obj.get_FoundObjectID(), true);
							String sAnhang = oRecArchiveMedien.get_PFAD_cUF() + "/" + oRecArchiveMedien.get_DATEINAME_cUF() ;
							bibMSG.add_MESSAGE(new MyE2_Info_Message("Der Anhang " + sAnhang + " wurde an die Tabelle " + obj.get_DBTableName() + " mit der ID " + obj.get_FoundObjectID() + " angehängt.") );
						}
					}
				}
			} catch (Exception e) {
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Der Anhang mit der ID " + sIDArchiv + " konnte nicht geladen werden. ") );
			}
		}
		
	}


	
	
	
	private class ActionAgentSave extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			PopUp_CONNECT_ARCHIVE_ENTRY_TO_OTHER_OBJECTS oThis = PopUp_CONNECT_ARCHIVE_ENTRY_TO_OTHER_OBJECTS.this;
			oThis.ConnectArchiveEntryToAdditionalObjects();
			oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);

		}
		
	}
	

	
	private class ActionAgentCancel extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			PopUp_CONNECT_ARCHIVE_ENTRY_TO_OTHER_OBJECTS oThis = PopUp_CONNECT_ARCHIVE_ENTRY_TO_OTHER_OBJECTS.this;
			oThis.CLOSE_AND_DESTROY_POPUPWINDOW(false);
		}
	}
	
	
	
}
