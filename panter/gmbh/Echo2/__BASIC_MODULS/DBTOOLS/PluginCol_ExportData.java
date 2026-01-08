package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.BasicInterfaces.Service.PdServiceExportIntoCsv;
import panter.gmbh.BasicInterfaces.Service.PdServiceExportIntoCsv.genIds;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.IF_agentSimple;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_ExportDBStructureAndData;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_PasswordField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class PluginCol_ExportData extends Basic_PluginColumn {

	private MyConnection 							oConnection = null;
	private MyE2_CheckBox  							oCB_ORACLE = 			new MyE2_CheckBox("Exportiert wird eine ORACLE-DB");
	private	MyE2_CheckBox  							oCB_SAPDB = 			new MyE2_CheckBox("Exportiert wird eine SAPDB-DB");
	private MyE2_TextField  						oTF_CLASSNAME = 		new MyE2_TextField("",600,300);
	private MyE2_TextField  						oTF_JDBC_STRING = 		new MyE2_TextField("",600,300);
	private MyE2_TextField  						oTF_LOGIN = 			new MyE2_TextField("",600,300);
	private MyE2_PasswordField  					oTF_PASSWORD = 			new MyE2_PasswordField("",600,300);
	private MyE2_TextField   						oTF_DB_TYP = 			new MyE2_TextField("",200,200);
	private MyE2_Button  							oButtonStartExport = 	new MyE2_Button("Starte Export");
	private MyE2_CheckBox  							oCBMigration = 			new MyE2_CheckBox("Migration SAPDB-->Oracle ?");
	private MyE2_CheckBox  							oCBOnlyStructure = 		new MyE2_CheckBox("Nur DB-Struktur ?");
	private MyE2_Button  							oButtonBaueConnection = new MyE2_Button("Prüefe die Verbindung");
	private ActionAgent_ExportDBStructureAndData 	oActionAgentExport = null;

	//private MyE2_Grid    							oInfoGrid = new MyE2_Grid(4,MyE2_Grid.STYLE_GRID_DDARK_BORDER());

	//2018-02-27: weiterer export
	private RB_selField selTabelleToExport = new RB_selField();
	
	
	protected void finalize()
	{
		try
		{
			if (this.oConnection != null)
				this.oConnection.get_oConnection().close();
			this.oConnection = null;
		}
		catch (Exception ex)
		{
		}
	}

	
	
	/*
	 * das plugin kann mit jeder datenbank verbindung aufnehmen und diese exportieren. dazu braucht es alle infos, die zu einer
	 * connection vorliegen. Diese werden in einem eingabebereich eingetragen
	 */
	public PluginCol_ExportData(ContainerForVerwaltungsTOOLS oMothercontainer)
	{
		super(oMothercontainer);
		
		
		
		try
		{
			//2018-02-27: weiterer export angefuegt: tabellen als csv exportieren (nur entwickler oder geschaeftsfuehrer)
			E2_Grid 		gridAussen = new E2_Grid()._s(1);
			E2_Grid  		gridNeu = new E2_Grid();
			MyE2_Grid       oGridExportAlt = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_DDARK_BORDER());
			gridAussen._a(gridNeu, new RB_gld()._ins(2,10,2,10))._a(oGridExportAlt,new RB_gld()._ins(2,10,2,10));

			//neuen export definieren
			String[][] 	cTabellen = bibDB.EinzelAbfrageInArray(DB_META.get_TablesQuerySort_A_to_Z_NUR_JD_JT_TABLES(bibE2.cTO(),true),false);

			this.selTabelleToExport._populate(cTabellen);
			
			gridNeu._setSize(200,200,200)
					._a(S.ms("Tabellen exportieren: "), new RB_gld()._ins(2))
					._a(selTabelleToExport, new RB_gld()._ins(2))
					._a(new btStartExportCSV(), new RB_gld()._ins(2))
					;
			
			
		
			this.oCB_ORACLE.add_oActionAgent(new actionEnableORA());
			this.oCB_SAPDB.add_oActionAgent(new actionEnableSAP());
			this.oButtonBaueConnection.add_oActionAgent(new actionCheckAndBuildConnection());
			this.oCBMigration.add_oActionAgent(new actionMakeSettings());
			this.oCBOnlyStructure.add_oActionAgent(new actionMakeSettings());
	
			
			oButtonStartExport.add_GlobalValidator(new E2_ButtonAUTHValidator(oMothercontainer.get_MODUL_IDENTIFIER(),"EXPORT_DATEN_DOWNLOAD"));
		

			this.oTF_DB_TYP.set_bEnabled_For_Edit(false);

			this.oActionAgentExport = new ActionAgent_ExportDBStructureAndData(
												bibALL.get_TEMPPATH_COMPLETE(),
												true,
												true,
												true);
					
			oButtonStartExport.add_oActionAgent(this.oActionAgentExport);
			
			// zusatzagent, um die export-protokol-liste anzuzeigen
			oButtonStartExport.add_oActionAgent(new XX_ActionAgent()     
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					PluginCol_ExportData.this.get_TextArea4Output().setText(PluginCol_ExportData.this.oActionAgentExport.get_cStringBufferInfo().toString());
				}
			});
			
			
			Insets I_2_0_4_0 = new Insets(2,0,4,0);
			Insets I_0_0_10_0 = new Insets(0,0,10,0);
			
			// die felder anordnen
			oGridExportAlt.add(this.oCB_ORACLE,1, 	I_2_0_4_0);
			oGridExportAlt.add(this.oCB_SAPDB,1, 	I_2_0_4_0);
			
			oGridExportAlt.add(new MyE2_Label(new MyE2_String("JDBC-Klassenname")),1, I_2_0_4_0);
			oGridExportAlt.add(this.oTF_CLASSNAME,1, I_2_0_4_0);
			
			oGridExportAlt.add(new MyE2_Label(new MyE2_String("JDBC-String")),1, I_2_0_4_0);
			oGridExportAlt.add(this.oTF_JDBC_STRING,1, I_2_0_4_0);
			
			oGridExportAlt.add(new MyE2_Label(new MyE2_String("Login")),1, I_2_0_4_0);
			oGridExportAlt.add(this.oTF_LOGIN,1, I_2_0_4_0);

			oGridExportAlt.add(new MyE2_Label(new MyE2_String("Passwort")),1, I_2_0_4_0);
			oGridExportAlt.add(this.oTF_PASSWORD,1, I_2_0_4_0);

			oGridExportAlt.add(new MyE2_Label(new MyE2_String("DB-Kennung")),1, I_2_0_4_0);
			oGridExportAlt.add(this.oTF_DB_TYP,1, I_2_0_4_0);

			oGridExportAlt.add(new E2_ComponentGroupHorizontal(0,oCBMigration,oCBOnlyStructure,this.oButtonBaueConnection, this.oButtonStartExport,I_0_0_10_0),2,I_2_0_4_0);			
			
			this.add(gridAussen,E2_INSETS.I_2_2_2_2);
			this.add(this.get_TextArea4Output(),E2_INSETS.I_2_2_2_2);
		}
		catch (myException ex)
		{
			this.add(new MyE2_Label("@@@ERROR@@@"));
			this.add(new MyE2_Label(ex.get_ErrorMessage()));
		}
	}

	
	
	// wird nach jedem klick auf einen knopf/checkbox ausgeloest
	private void makeSettingInExportAgent()
	{
		this.oActionAgentExport.set_bExportOnlyStructure(this.oCBOnlyStructure.isSelected());
		this.oActionAgentExport.set_bMakeMigration(this.oCBMigration.isSelected());
	}
	
	
	
	
	private class actionEnableSAP extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			PluginCol_ExportData oThis = PluginCol_ExportData.this;
			
			if (oThis.oCB_SAPDB.isSelected())
			{
				oThis.oButtonBaueConnection.set_bEnabled_For_Edit(true);
				oThis.oButtonStartExport.set_bEnabled_For_Edit(false);
				oThis.oConnection = null;
				oThis.oActionAgentExport.set_oConnectionToDB_toExport(null);
				
				oThis.oCB_ORACLE.setSelected(false);
				oThis.oTF_CLASSNAME.setText("com.sap.dbtech.jdbc.DriverSapDB");
				oThis.oTF_JDBC_STRING.setText("jdbc:sapdb://<IP>/<DBNAME>?unicode=yes&timeout=0");
				oThis.oTF_DB_TYP.setText(DB_META.DB_SAP);
				oThis.oTF_LOGIN.setText("");
				oThis.oTF_PASSWORD.setText("");
				
				// migration ist moeglich, weil SAP-DB die quelle ist
				oThis.oCBMigration.setSelected(false);
				oThis.oCBMigration.set_bEnabled_For_Edit(true);
				
			}
			PluginCol_ExportData.this.makeSettingInExportAgent();
		}
	}
	

	private class actionEnableORA extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			PluginCol_ExportData oThis = PluginCol_ExportData.this;
			
			if (oThis.oCB_ORACLE.isSelected())
			{
				oThis.oButtonBaueConnection.set_bEnabled_For_Edit(true);
				oThis.oButtonStartExport.set_bEnabled_For_Edit(false);
				oThis.oConnection = null;
				oThis.oActionAgentExport.set_oConnectionToDB_toExport(null);
				

				oThis.oCB_SAPDB.setSelected(false);
				oThis.oTF_CLASSNAME.setText("oracle.jdbc.driver.OracleDriver");
				oThis.oTF_JDBC_STRING.setText("jdbc:oracle:thin:@<ID>:1521:<DBNAME>");
				oThis.oTF_DB_TYP.setText(DB_META.DB_ORA);
				oThis.oTF_LOGIN.setText("");
				oThis.oTF_PASSWORD.setText("");
				
				// migration nur moeglich, wenn SAP-DB die quelle ist
				oThis.oCBMigration.setSelected(false);
				oThis.oCBMigration.set_bEnabled_For_Edit(false);

			}
			PluginCol_ExportData.this.makeSettingInExportAgent();
			
		}
	}

	
	private class actionCheckAndBuildConnection extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			PluginCol_ExportData oThis = PluginCol_ExportData.this;

			if (oThis.oTF_CLASSNAME.getText().trim().equals("")||
				oThis.oTF_JDBC_STRING.getText().trim().equals("") ||
				oThis.oTF_LOGIN.getText().trim().equals(""))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte füllen Sie die Verbindungsinformationen aus !"), false);
				return;
			}
			
			try
			{
				oThis.oConnection = new MyConnection(	oThis.oTF_CLASSNAME.getText().trim(),
														oThis.oTF_JDBC_STRING.getText().trim(),
														oThis.oTF_LOGIN.getText().trim(),
														oThis.oTF_PASSWORD.getText().trim(),
														oThis.oTF_DB_TYP.getText().trim());

				oThis.oButtonBaueConnection.set_bEnabled_For_Edit(false);
				oThis.oButtonStartExport.set_bEnabled_For_Edit(true);
				oThis.oActionAgentExport.set_oConnectionToDB_toExport(oThis.oConnection);
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Verbindung steht !")), false);
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Verbindung hat nicht geklappt !"), false);
			}

			PluginCol_ExportData.this.makeSettingInExportAgent();
		}
	}
	
	
	private class actionMakeSettings extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			PluginCol_ExportData.this.makeSettingInExportAgent();
		}
	}
	
	
	
	private class btStartExportCSV extends E2_Button {
		
		private boolean 		check4break = true;
		private RB_TextArea  	tfBedingung = new RB_TextArea()._width(new Extent(300))._height(new Extent(200));
		
		/**
		 * @throws myException 
		 * 
		 */
		public btStartExportCSV() throws myException {
			super();
			this._tr("Starte Export ins CSV-Format")._standard_text_button()._addValidatorOnlyGfOrDev()._aaa(new ownActionExportCSV());
			this.setBreak4PopupController(new ownPopupController());
		}
		
		private class ownActionExportCSV extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				check4break=true;
				MyE2_MessageVector mv = new MyE2_MessageVector();
				if (S.isFull(selTabelleToExport.getActualDbVal())) {
					if (S.isAllFull(selTabelleToExport.getActualDbVal())) {
						new PdServiceExportIntoCsv().export(_TAB.find_Table(selTabelleToExport.getActualDbVal()),new generator() , true, null, mv);
					}
				} else {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Bitte wählen Sie eine Tabelle zum Export !")));
				}
			}
		}

		
		private class ownPopupController extends E2_Break4PopupController {
			public ownPopupController() {
				super();
				this._registerBreak(new ownPopup());
			}
			
			private class ownPopup extends E2_Break4Popup {
				public ownPopup() {
					super();
					this.setPopupWidth(400);
					this.setPopupHeight(400);
					this.setTitle(S.ms("Zusatz-Statement"));
				}

				@Override
				public E2_BasicModuleContainer generatePopUpContainer() throws myException {
					check4break=false;
					return new ownContainer();
				}

				@Override
				protected boolean check4break(MyE2_MessageVector mv) throws myException {
					return check4break;
				}
				
				private class ownContainer extends E2_BasicModuleContainer {
					public ownContainer() {
						super();
						//ownPopup.this.getOwnSaveButton()._aaa(()-> {check4break=true;});
						
						IF_agentSimple a_s = ()-> {check4break=true;};
						ownPopup.this.getOwnCloseButton()._aaa(()-> {check4break=true;});
						E2_Grid grid = new E2_Grid()
									._setSize(400)
									._a("Bitte geben Sie, falls gewünscht, eine where-Bedingung (ohne WHERE) ein :")
									._a(tfBedingung)
									._a(new E2_Grid()._setSize(100,100)
											._a(ownPopup.this.getOwnSaveButton()._style(E2_Button.StyleTextButtonCentered()))
											._a(ownPopup.this.getOwnCloseButton()._style(E2_Button.StyleTextButtonCentered()))
										);
						
						this.add(grid, E2_INSETS.I(5,5,5,5));
						
						this.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_SMALL_TITLE_NO_CLOSE());
						
					}
				}
				
				
			}
		}
		
		private class generator implements genIds {
			@Override
			public Vector<String> ids(_TAB table, MyE2_MessageVector mv) throws myException {
				VEK<String>  vIds = new VEK<>();
				SEL selIdQuery = new SEL(table.key()).FROM(table).ORDERUP(table.key());
				if (S.isFull(tfBedingung.getText())) {
					selIdQuery.WHERE(new TermSimple(tfBedingung.getText()));
				}
				
				String[][] a_id = bibDB.EinzelAbfrageInArray(selIdQuery.s());
				vIds._addVektor(  ()->{ 	Vector<String> v = new Vector<String>(); 
											for (int i=0;i<a_id.length;i++) {
												v.add(a_id[i][0]);	
											}; 
											return v;
										});
				if (a_id==null || a_id.length==0) {
					mv.add_MESSAGE(new MyE2_Alarm_Message("Error Quering IDs"));
					return null;
				}
				
				return vIds;
			}
			
		}
	}

}
