package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_Upload_And_Import_ExportFile;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_PasswordField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;

public class PluginCol_ImportData extends Basic_PluginColumn
{

	private MyConnection 							oConnection = null;
	private MyE2_CheckBox  							oCB_ORACLE = 			new MyE2_CheckBox("Importiert wird in eine ORACLE-DB");
	private	MyE2_CheckBox  							oCB_SAPDB = 			new MyE2_CheckBox("Importiert wird in eine SAPDB-DB");
	private MyE2_TextField  						oTF_CLASSNAME = 		new MyE2_TextField("",600,300);
	private MyE2_TextField  						oTF_JDBC_STRING = 		new MyE2_TextField("",600,300);
	private MyE2_TextField  						oTF_LOGIN = 			new MyE2_TextField("",600,300);
	private MyE2_PasswordField  					oTF_PASSWORD = 			new MyE2_PasswordField("",600,300);
	private MyE2_TextField   						oTF_DB_TYP = 			new MyE2_TextField("",200,200);
	private MyE2_Button  							oButtonStartImport = 	new MyE2_Button("Starte Import");
	private MyE2_Button  							oButtonBaueConnection = new MyE2_Button("Prüefe die Verbindung");
	private ActionAgent_Upload_And_Import_ExportFile 	oActionAgentStartUpload = null;


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
	public PluginCol_ImportData(ContainerForVerwaltungsTOOLS oMothercontainer)
	{
		super(oMothercontainer);
		
		this.oCB_ORACLE.add_oActionAgent(new actionEnableORA());
		this.oCB_SAPDB.add_oActionAgent(new actionEnableSAP());
		this.oButtonBaueConnection.add_oActionAgent(new actionCheckAndBuildConnection());

		MyE2_Grid       oGridBedienFeld = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_DDARK_BORDER());
		
		oButtonStartImport.add_GlobalValidator(new E2_ButtonAUTHValidator(oMothercontainer.get_MODUL_IDENTIFIER(),"IMPORT_DATEN"));
		

		try
		{
			this.oTF_DB_TYP.set_bEnabled_For_Edit(false);

			this.oActionAgentStartUpload = new ActionAgent_Upload_And_Import_ExportFile(new XX_ActionAgent()
																					{
																						@Override
																						public void executeAgentCode(ExecINFO execInfo)	throws myException
																						{
																							PluginCol_ImportData.this.get_TextArea4Output().setText(PluginCol_ImportData.this.oActionAgentStartUpload.get_oStringBufferInfo().toString());
																						}
																					});
					
			oButtonStartImport.add_oActionAgent(this.oActionAgentStartUpload);
			
			Insets I_2_0_4_0 = new Insets(2,0,4,0);
			Insets I_0_0_10_0 = new Insets(0,0,10,0);
			
			// die felder anordnen
			oGridBedienFeld.add(this.oCB_ORACLE,1, 	I_2_0_4_0);
			oGridBedienFeld.add(this.oCB_SAPDB,1, 	I_2_0_4_0);
			
			oGridBedienFeld.add(new MyE2_Label(new MyE2_String("JDBC-Klassenname")),1, I_2_0_4_0);
			oGridBedienFeld.add(this.oTF_CLASSNAME,1, I_2_0_4_0);
			
			oGridBedienFeld.add(new MyE2_Label(new MyE2_String("JDBC-String")),1, I_2_0_4_0);
			oGridBedienFeld.add(this.oTF_JDBC_STRING,1, I_2_0_4_0);
			
			oGridBedienFeld.add(new MyE2_Label(new MyE2_String("Login")),1, I_2_0_4_0);
			oGridBedienFeld.add(this.oTF_LOGIN,1, I_2_0_4_0);

			oGridBedienFeld.add(new MyE2_Label(new MyE2_String("Passwort")),1, I_2_0_4_0);
			oGridBedienFeld.add(this.oTF_PASSWORD,1, I_2_0_4_0);

			oGridBedienFeld.add(new MyE2_Label(new MyE2_String("DB-Kennung")),1, I_2_0_4_0);
			oGridBedienFeld.add(this.oTF_DB_TYP,1, I_2_0_4_0);

			oGridBedienFeld.add(new E2_ComponentGroupHorizontal(0,this.oButtonBaueConnection, this.oButtonStartImport,I_0_0_10_0),2,I_2_0_4_0);			
			
			this.add(oGridBedienFeld,E2_INSETS.I_2_2_2_2);
			this.add(this.get_TextArea4Output(),E2_INSETS.I_2_2_2_2);
		}
		catch (myException ex)
		{
			this.add(new MyE2_Label("@@@ERROR@@@"));
			this.add(new MyE2_Label(ex.get_ErrorMessage()));
		}
	}

	
	
	private class actionEnableSAP extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			PluginCol_ImportData oThis = PluginCol_ImportData.this;
			
			if (oThis.oCB_SAPDB.isSelected())
			{
				oThis.oButtonBaueConnection.set_bEnabled_For_Edit(true);
				oThis.oButtonStartImport.set_bEnabled_For_Edit(false);
				oThis.oConnection = null;
				oThis.oActionAgentStartUpload.set_oConnectionToDB_toExport(null);
				
				oThis.oCB_ORACLE.setSelected(false);
				oThis.oTF_CLASSNAME.setText("com.sap.dbtech.jdbc.DriverSapDB");
				oThis.oTF_JDBC_STRING.setText("jdbc:sapdb://<IP>/<DBNAME>?unicode=yes&timeout=0");
				oThis.oTF_DB_TYP.setText(DB_META.DB_SAP);
				oThis.oTF_LOGIN.setText("");
				oThis.oTF_PASSWORD.setText("");
				
				oThis.get_TextArea4Output().setText("");
				
			}
		}
	}
	

	private class actionEnableORA extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			PluginCol_ImportData oThis = PluginCol_ImportData.this;
			
			if (oThis.oCB_ORACLE.isSelected())
			{
				oThis.oButtonBaueConnection.set_bEnabled_For_Edit(true);
				oThis.oButtonStartImport.set_bEnabled_For_Edit(false);
				oThis.oConnection = null;
				oThis.oActionAgentStartUpload.set_oConnectionToDB_toExport(null);
				

				oThis.oCB_SAPDB.setSelected(false);
				oThis.oTF_CLASSNAME.setText("oracle.jdbc.driver.OracleDriver");
				oThis.oTF_JDBC_STRING.setText("jdbc:oracle:thin:@<IP>:1521:<DBNAME>");
				oThis.oTF_DB_TYP.setText(DB_META.DB_ORA);
				oThis.oTF_LOGIN.setText("");
				oThis.oTF_PASSWORD.setText("");
				
				oThis.get_TextArea4Output().setText("");
			}
			
		}
	}

	
	private class actionCheckAndBuildConnection extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			PluginCol_ImportData oThis = PluginCol_ImportData.this;

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
				oThis.oButtonStartImport.set_bEnabled_For_Edit(true);
				oThis.oActionAgentStartUpload.set_oConnectionToDB_toExport(oThis.oConnection);
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Verbindung steht !")), false);
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Verbindung hat nicht geklappt !"), false);
			}

		}
	}
	
	
	
	

}
