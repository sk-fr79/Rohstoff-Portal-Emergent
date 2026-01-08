package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_KREDITVERS_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_KREDITVERS_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KREDITVERS_ADRESSE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/**
 * Maske zum verknüpfen der Kreditversicherung und den Firmen.
 * 
 * @author manfred
 * @date   20.09.2011
 */
public class KV_Mask_Adresse_Versicherung extends Project_BasicModuleContainer {

	
	private E2_NavigationList	 m_NavList 	= null;
	private String				 m_idAdresse_Main = null;
	private String				 m_id_VersKopf = null;
	
	private MyE2_Grid			 m_GridMain = null;
	
	
	/**
	 * 
	 * @author manfred
	 * @throws myException 
	 * @date   20.09.2011
	 */
	public KV_Mask_Adresse_Versicherung(E2_NavigationList navList, String idAdresse) throws myException {
		super(E2_MODULNAMES.NAME_MODUL_KREDITVERSICHERUNG_MASKE);
		m_NavList = navList;
		m_idAdresse_Main = idAdresse;
		
		
		if (! (m_NavList.get_vSelectedIDs_Unformated().size() == 1) ){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Es darf nur genau ein Versicherungsvertrag ausgewählt sein.")));
			return;
		}

		// der eine selektierte Eintrag 
		m_id_VersKopf = m_NavList.get_vSelectedIDs_Unformated().get(0);
		
		//
		m_GridMain = new MyE2_Grid(1,1);

		// Maskenobjekte erzugen
		initMask();
		
		this.add(m_GridMain, E2_INSETS.I_2_2_2_2);
		
		// nach dem Schliessen der Erfassung muss der parent aktualisiert werden
		this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this){
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				if (m_NavList != null){
					m_NavList.RefreshList();
				}
			}
		});

		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(500), new MyE2_String("Erfassung/Ändern einer Kreditversicherung"));

	}
	
	
	
	/**
	 * Baut die Maske auf
	 * 
	 * @author manfred
	 * @throws myException 
	 * @date   20.09.2011
	 */
	private void initMask() throws myException{

		// Grid leeren
		m_GridMain.removeAll();
		
		m_GridMain.add ( new MyE2_Label(new MyString("Zuordnung von Firmen zur Kreditversicherung"), MyE2_Label.STYLE_TITEL_BIG()));
		
		E2_MutableStyle oStyleCheckboxNoBorder = new E2_MutableStyle();
		oStyleCheckboxNoBorder.setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));
		
		RECLIST_KREDITVERS_ADRESSE reclist = new RECLIST_KREDITVERS_ADRESSE("ID_KREDITVERS_KOPF = " + m_id_VersKopf, "ID_KREDITVERS_ADRESSE ASC");

		for (RECORD_KREDITVERS_ADRESSE rec : reclist.values()){
			if (!rec.get_ID_ADRESSE_cUF().equals(m_idAdresse_Main) ){
				m_GridMain.add(new ROW_Adress(rec, m_id_VersKopf));
			}
		}
		
		// dann noch einen "neueintrag"
		m_GridMain.add(new ROW_Adress(null, m_id_VersKopf));
		
	}
	
	
	
	
	/**
	 * eine zeile mit einer Kundenadresse
	 * @author manfred
	 * @date   22.09.2011
	 */
	private class ROW_Adress extends MyE2_Grid{
		RECORD_KREDITVERS_ADRESSE m_recKreditversAdresse = null;
		String m_idVers_Kopf = null;
		MyE2_Button m_btnEdit = null;
		UB_SearchFieldAdresse oSearchAdresse = null;
		
		
		public ROW_Adress(RECORD_KREDITVERS_ADRESSE oRec, String id_VersKopf) throws myException {
			super(2,0);
			m_recKreditversAdresse = oRec;
			m_idVers_Kopf = id_VersKopf;
			
			
			oSearchAdresse = new UB_SearchFieldAdresse("ID_ADRESSE", true, 300, 80);
			oSearchAdresse.set_bLabel4AnzeigeStattText4Anzeige(true);
			oSearchAdresse.get_oTextForAnzeige().setWidth(new Extent(390));
			oSearchAdresse.get_oTextForAnzeige().setFont(new E2_FontPlain(0));
			oSearchAdresse.get_oLabel4Anzeige().setWidth(new Extent(390));
			oSearchAdresse.get_oLabel4Anzeige().get_oLabel().setFont(new E2_FontPlain(0));

			

			if (m_recKreditversAdresse == null ){
				// neu einfügen
				m_btnEdit = new MyE2_Button(E2_ResourceIcon.get_RI("save.png") , E2_ResourceIcon.get_RI("save__.png"));
				m_btnEdit.add_oActionAgent(new actionAdd());
				
				oSearchAdresse.get_oTextFieldForSearchInput().setText("");
				oSearchAdresse.get_oTextForAnzeige().setText("");
				
			} else{
				
				// löschen
				m_btnEdit = new MyE2_Button(E2_ResourceIcon.get_RI("delete_mini.png") , E2_ResourceIcon.get_RI("delete_mini__.png"));
				m_btnEdit.add_oActionAgent(new actionDelete());
				
				oSearchAdresse.get_oTextFieldForSearchInput().setText(oRec.get_ID_ADRESSE_cUF());
				try {
					oSearchAdresse.FillLabelWithDBQuery(oRec.get_ID_ADRESSE_cUF());
				} catch (Exception e) {
					oSearchAdresse.get_oTextFieldForSearchInput().setText(null);
				}
				oSearchAdresse.set_bEnabled_For_Edit(false);
			}
			
			this.add(oSearchAdresse);
			this.add(m_btnEdit);
		}

		
		/**
		 * Action zum speichern eines neuen Eintrags
		 * @author manfred
		 * @date   23.09.2011
		 */
		private class actionAdd extends XX_ActionAgent{

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				MyLong l = new MyLong(oSearchAdresse.get_oTextFieldForSearchInput().getText(), null, null);
				String idAdresse = (l != null ? l.get_cUF_LongString() : null);
				if ( !bibALL.isEmpty(idAdresse) ){
					RECORDNEW_KREDITVERS_ADRESSE oRecnew = new RECORDNEW_KREDITVERS_ADRESSE();
					oRecnew.set_NEW_VALUE_ID_ADRESSE(idAdresse);
					oRecnew.set_NEW_VALUE_ID_KREDITVERS_KOPF(m_idVers_Kopf);
					String sSql = oRecnew.get_InsertSQLStatementWith_Id_Field(false, true);
					
					bibDB.ExecMultiSQLVector(bibALL.get_Vector(sSql), true);
					
					// die Maske neu aufbauen
					initMask();
					
				} else {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es muss eine Adresse ausgewählt werden"));
				}
				
			}
			
		}
		
		/**
		 * Action zum löschen des aktuellen Eintrags
		 * @author manfred
		 * @date   23.09.2011
		 */
		private class actionDelete extends XX_ActionAgent{

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				String sSql = "DELETE FROM JT_KREDITVERS_ADRESSE WHERE ID_KREDITVERS_ADRESSE =  " + m_recKreditversAdresse.get_ID_KREDITVERS_ADRESSE_cUF();
		
				bibDB.ExecMultiSQLVector(bibALL.get_Vector(sSql), true);
				
				// die Maske neu aufbauen
				initMask();

			}
		}
		
	}
	

}
