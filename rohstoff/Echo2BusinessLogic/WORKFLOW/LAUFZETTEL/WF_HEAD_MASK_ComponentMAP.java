package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_STATUS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_STATUS;
import panter.gmbh.indep.MyDropDownSettings;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class WF_HEAD_MASK_ComponentMAP extends E2_ComponentMAP 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1712460644655859378L;
	private SQLFieldMAP m_oFM = null;

	public WF_HEAD_MASK_ComponentMAP() throws myException
	{
		super(new WF_HEAD_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		m_oFM = oFM;
		
		
		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ABGESCHLOSSEN_AM"),true,80), new MyE2_String("Abgeschlossen am"));
		
//		MyDropDownSettings oDDUserAbgeschlossen = new MyDropDownSettings(bibE2.cTO(),"JD_USER","NAME","ID_USER",null,null,true,"NAME");
		DB_Component_USER_DROPDOWN oSelectID_USER_ABGESCHLOSSEN = new DB_Component_USER_DROPDOWN(oFM.get_("ID_USER_ABGESCHLOSSEN_VON"));
		oSelectID_USER_ABGESCHLOSSEN.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				MyE2_DB_SelectField oSelectUserFinal = (MyE2_DB_SelectField)WF_HEAD_MASK_ComponentMAP.this.get__Comp("ID_USER_ABGESCHLOSSEN_VON");
				
				E2_ComponentMAP oMAP_Own = oSelectUserFinal.EXT().get_oComponentMAP();
				
				if (bibALL.isEmpty(oSelectUserFinal.get_ActualWert()))
				{
					((MyE2_DB_TextField)oMAP_Own.get__Comp("ABGESCHLOSSEN_AM")).setText("");
					
					// aktuellen Benutzer löschen
					MyE2_DB_SelectField oSelectStatus = (MyE2_DB_SelectField)oMAP_Own.get__Comp("ID_LAUFZETTEL_STATUS");
					oSelectStatus.setSelectedIndex(0);
				}
				else
				{
					if (!isAllEntriesClosed()){
						if (oMAP_Own.get_oInternalSQLResultMAP() != null){
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Vor dem Abschliessen eines Laufzettels müssen alle Einträge abgeschlossen sein."));
							((MyE2_DB_TextField)oMAP_Own.get__Comp("ABGESCHLOSSEN_AM")).setText("");
							oSelectUserFinal.set_ActiveValue("");
						} else {
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Ein neuer Laufzettel kann nicht abgeschlossen werden."));
							oSelectUserFinal.set_ActiveValue("");
							((MyE2_DB_TextField)oMAP_Own.get__Comp("ABGESCHLOSSEN_AM")).setText("");
						}
					} else {
						((MyE2_DB_TextField)oMAP_Own.get__Comp("ABGESCHLOSSEN_AM")).setText(bibALL.get_cDateNOW());
						// die Combobox setzen
						String idStatus = getIDForStatusAbgeschlossen();
						if (idStatus != null){
						 ((MyE2_DB_SelectField)oMAP_Own.get__Comp("ID_LAUFZETTEL_STATUS")).set_ActiveValue(idStatus);
						}
					}
				}
			}
		});
		
		this.add_Component(oSelectID_USER_ABGESCHLOSSEN, new MyE2_String("Abgeschlossen von:"));

		this.add_Component(new MyE2_DB_TextField(oFM.get_("ANGELEGT_AM"),true,80), new MyE2_String("Angelegt am"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_LAUFZETTEL"),true,80), new MyE2_String("Laufzettel ID"));
		
		MyDropDownSettings oDDStatus = new MyDropDownSettings(bibE2.cTO(),"JT_LAUFZETTEL_STATUS","STATUS","ID_LAUFZETTEL_STATUS",null,null,true,"STATUS_SORT");
		MyE2_DB_SelectField oSelectStatus= new MyE2_DB_SelectField(oFM.get_("ID_LAUFZETTEL_STATUS"),oDDStatus.getDD(),true);
		this.add_Component(oSelectStatus, new MyE2_String("Status:"));

		
		
		DB_Component_USER_DROPDOWN oSelectID_USER = new DB_Component_USER_DROPDOWN(oFM.get_("ID_USER_BESITZER"));
		this.add_Component(oSelectID_USER, new MyE2_String("Besitzer:"));
		
		DB_Component_USER_DROPDOWN oSelectID_Supervisor = new DB_Component_USER_DROPDOWN(oFM.get_("ID_USER_SUPERVISOR"));
		this.add_Component(oSelectID_Supervisor, new MyE2_String("Supervisor:"));
		
		MyE2_DB_TextArea o = new MyE2_DB_TextArea(oFM.get_("TEXT"),600,15);
		this.add_Component(o, new MyE2_String("Beschreibung"));

		this.add_Component(new MyE2_DB_TextField(oFM.get_(WF_HEAD_CONST.HASH_SONDERFELD_LETZTE_AENDERUNG),true,80), new MyE2_String("LETZTE_AENDERUNG"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(WF_HEAD_CONST.HASH_SONDERFELD_GEANDERT_VON),true,100), new MyE2_String("GEAENDERT_VON"));

		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("PRIVAT")), new MyE2_String("Eintrag ist Privat!"));
		
//		MyE2_Button buttonAbschluss = new MyE2_Button(new MyE2_String("Abschließen"),E2_ResourceIcon.get_RI("ok.png"),E2_ResourceIcon.get_RI("ok.png"));
//		buttonAbschluss.add_oActionAgent(new actionAbschliessen());
//		this.add_Component(WF_HEAD_CONST.HASH_ABSCHLUSSBUTTON,buttonAbschluss, new MyE2_String("Eintrag abschließen"));
//		
//		// button zum Zurücknehmen des Abschlusses
//		MyE2_Button buttonAbschlussRevert = new MyE2_Button(new MyE2_String("Freigeben"),E2_ResourceIcon.get_RI("deleted.png"),E2_ResourceIcon.get_RI("deleted.png"));
//		buttonAbschlussRevert.add_oActionAgent(new actionAbschliessenRevert());
//		this.add_Component(WF_HEAD_CONST.HASH_ABSCHLUSSBUTTON_REVERT,buttonAbschlussRevert, new MyE2_String("Eintrag freigeben"));

		
		
		
		// Feldweiten der Comboboxen einstellen
		((MyE2_DB_SelectField)this.get__Comp("ID_USER_BESITZER")).setWidth(new Extent(150));
		((MyE2_DB_SelectField)this.get__Comp("ID_USER_SUPERVISOR")).setWidth(new Extent(150));
		((MyE2_DB_SelectField)this.get__Comp("ID_USER_ABGESCHLOSSEN_VON")).setWidth(new Extent(150));
		((MyE2_DB_SelectField)this.get__Comp("ID_LAUFZETTEL_STATUS")).setWidth(new Extent(150));
		
		
		//Nur der supervisor darf den Besitzer, den Supervisor und das Angelegt_am Datum ändern
		if (!bibALL.get_bIST_SUPERVISOR())
		{
			this.get__Comp("ID_USER_BESITZER").EXT().set_bDisabledFromBasic(true);
			this.get__Comp("ANGELEGT_AM").EXT().set_bDisabledFromBasic(true);
		}
		
		
		
		
		
		/**
		 * ActionAgent für den Status des Laufzetteleintrags
		 */
		oSelectStatus.add_oActionAgent(new XX_ActionAgent() {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//				 Referenz auf die umgebende Klasse 
				WF_HEAD_MASK_ComponentMAP oThis = WF_HEAD_MASK_ComponentMAP.this;
				
				MyE2_DB_SelectField oSelectStatus = (MyE2_DB_SelectField)WF_HEAD_MASK_ComponentMAP.this.get__Comp("ID_LAUFZETTEL_STATUS");
				E2_ComponentMAP oMAP_Own = oSelectStatus.EXT().get_oComponentMAP();
				
				
				boolean bAbschliessen = false;
				
				String idStatus = oSelectStatus.get_ActualWert().replace(".", "");
				
				
				if (bibALL.isEmpty(idStatus)){
					bAbschliessen = false;
				} else {
					RECORD_LAUFZETTEL_STATUS recStatus = new RECORD_LAUFZETTEL_STATUS(idStatus);
					if (recStatus.get_TRIGGER_ABSCHLUSS_cUF_NN("N").equals("Y")){
						bAbschliessen = true;
					}
				}
				
				if (bAbschliessen && !isAllEntriesClosed()){
					if (oMAP_Own.get_oInternalSQLResultMAP() != null){
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Vor dem Abschliessen eines Laufzettels müssen alle Einträge abgeschlossen sein."));
						idStatus = oMAP_Own.get_oInternalSQLResultMAP().get_FormatedValue("ID_LAUFZETTEL_STATUS");
						oSelectStatus.set_ActiveValue(idStatus);
					} else {
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Ein neuer Laufzettel kann nicht abgeschlossen werden."));
						oSelectStatus.set_ActiveValue("");
					}
					return;
				}

				
				MyE2_DB_SelectField oDDAbgeschlossenVon = (MyE2_DB_SelectField)oMAP_Own.get__Comp("ID_USER_ABGESCHLOSSEN_VON");

				// die Combobox-Referenz...
				if (bAbschliessen){
					// den Wert auf den aktuellen Benutzer setzen...
					oDDAbgeschlossenVon.set_ActiveValue(bibALL.get_ID_USER_FORMATTED());
					oDDAbgeschlossenVon.get_vActionAgents().get(0).ExecuteAgentCode(new ExecINFO(oExecInfo.get_MyActionEvent(),true));
				} else {
					// aktuellen Benutzer löschen
					oDDAbgeschlossenVon.setSelectedIndex(0);
					((MyE2_DB_TextField)oMAP_Own.get__Comp("ABGESCHLOSSEN_AM")).setText("");
//					oDDAbgeschlossenVon.get_vActionAgents().get(0).ExecuteAgentCode(new ExecINFO(oExecInfo.get_MyActionEvent(),true));
				}
			}
		});

		
		
		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new WF_HEAD_MASK_MapSettingAgent());
		
		
		
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		//this.set_oSubQueryAgent(new WF_HEAD_LIST_FORMATING_Agent());
	}
	
	
	
	/**
	 * ermitteln der ID die für den Abschluss Zuständig ist.
	 * @return
	 * @throws myException
	 */
	private String getIDForStatusAbgeschlossen() throws myException{
		String idRet = null;
		RECLIST_LAUFZETTEL_STATUS list = new RECLIST_LAUFZETTEL_STATUS("SELECT * FROM " + bibE2.cTO() + ".JT_LAUFZETTEL_STATUS WHERE NVL(TRIGGER_ABSCHLUSS,'N') = 'Y' ");
		if (list.size() > 0){
			idRet = list.get(0).get_ID_LAUFZETTEL_STATUS_cF();
		}
		return idRet;
	}
	
	
	/**
	 * prüfen, ob alle Einträge des Laufzettels geschlossen sind.
	 * Wenn nein, nicht zulassen, dass Laufzettel geschlossen wird. 
	 * @return
	 * @throws myException 
	 */
	private boolean isAllEntriesClosed() throws myException{
		boolean bRet = false;
		
		if (this.get_oInternalSQLResultMAP() != null){
			String id = this.get_oInternalSQLResultMAP().get_UnFormatedValue("ID_LAUFZETTEL");
	
			try {
				 RECORD_LAUFZETTEL oRec = new RECORD_LAUFZETTEL(id);
				 RECLIST_LAUFZETTEL_EINTRAG list = oRec.get_DOWN_RECORD_LIST_LAUFZETTEL_EINTRAG_id_laufzettel("NVL(GELOESCHT,'N') = 'N' AND ID_USER_ABGESCHLOSSEN_VON IS NULL",null,true);
				 bRet = (list.size() == 0) ;
			} catch (Exception e) {
				// ???
				bRet = true;
			};
		} else {
			bRet = true;
		}
		
		return bRet;
	}
	
	

	/**
	 * Eventhandler für den Button "Abschließen"
	 * @author manfred
	 *
	 */
	private class actionAbschliessen extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			// Referenz auf die umgebende Klasse 
			WF_HEAD_MASK_ComponentMAP oThis = WF_HEAD_MASK_ComponentMAP.this;
			
			// die Combobox-Referenz...
			MyE2_DB_SelectField oDDAbgeschlossenVon = (MyE2_DB_SelectField)oThis.get__Comp("ID_USER_ABGESCHLOSSEN_VON");
			
			// den Wert auf den aktuellen Benutzer setzen...
			oDDAbgeschlossenVon.set_ActiveValue(bibALL.get_ID_USER_FORMATTED());
			
			// .. und dann die Action der Combobox aufrufen
			oDDAbgeschlossenVon.get_vActionAgents().get(0).ExecuteAgentCode(new ExecINFO(execInfo.get_MyActionEvent(),true));
		}
		
	}
	

	/***
	 * private Eventhandler-Klasse zum Rücsetzen des Abschlusses
	 * @author manfred
	 *
	 */
		private class actionAbschliessenRevert extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException 
		{
			WF_HEAD_MASK_ComponentMAP oThis = WF_HEAD_MASK_ComponentMAP.this;
			MyE2_DB_SelectField oDDAbgeschlossenVon = (MyE2_DB_SelectField)oThis.get__Comp("ID_USER_ABGESCHLOSSEN_VON");
			
			oDDAbgeschlossenVon.setSelectedIndex(0);
			oDDAbgeschlossenVon.get_vActionAgents().get(0).ExecuteAgentCode(new ExecINFO(execInfo.get_MyActionEvent(),true));
		}
	}
	
}
