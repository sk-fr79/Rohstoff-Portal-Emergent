package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Auswertungen.AW_BasicModuleContainer;
import panter.gmbh.Echo2.Auswertungen.XX_Auswertungen;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_ComponentMap;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.REPORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_REPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN.JASPER_MODUL_LISTEN.Auswertungen_standardReports;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN.JASPER_MODUL_LISTEN.Auswertungen_standardReports_FremdeListen;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN.WARENSTROEME.AuswertungWarenstroemeNachLagerUndSorten;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN.WARENSTROEME.AuswertungenWarenStroemeViaTempTable;




public class AW_RohstoffAuswertungen extends AW_BasicModuleContainer 
{

	public static String SESSION_HASH_4_AW_ROSTOFFAUSWERTUNGEN_BASE = "SESSION_HASH_4_AW_ROSTOFFAUSWERTUNGEN_BASE";
	public static String SESSION_HASH_4_AW_ROSTOFFAUSWERTUNGEN_LIST = "SESSION_HASH_4_AW_ROSTOFFAUSWERTUNGEN_LIST";
	
	private MyE2_CheckBox  	oCB_HoleAuswertungenAndererModule = null;
	private MyE2_Grid 		oGridSteuerung = null;
	
	private Vector<XX_Auswertungen>  vAuswertungenEigen = new Vector<XX_Auswertungen>();
	private Vector<XX_Auswertungen>  vAuswertungenFremd = new Vector<XX_Auswertungen>();

	
	public AW_RohstoffAuswertungen() throws myException 
	{
		super(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_ROHSTOFFAUSWERTUNGEN.get_callKey(),null,new Extent(600));
		
		this.oCB_HoleAuswertungenAndererModule = new MyE2_CheckBox(new MyE2_String("Unabhängige Auswertungen aller Module einblenden"));
		
		this.oCB_HoleAuswertungenAndererModule.add_oActionAgent(new ownActionAgentKomplettNeuAufbau());
		this.oCB_HoleAuswertungenAndererModule.add_oActionAgent(new ownActionAgentSaveStateBase());
		
		this.get_oBTSuche().add_oActionAgent(new ownActionAgentSaveStateBase());
		this.get_oCB_ZeigeNurSelektierte().add_oActionAgent(new ownActionAgentSaveStateBase());
		
		this.oGridSteuerung = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
		
		this.oGridSteuerung.add(this.oCB_HoleAuswertungenAndererModule, LayoutDataFactory.get_GridLayoutGridLeftMID(E2_INSETS.I_0_0_0_0));

//		this.oCB_HoleAuswertungenAndererModule.setSelected(this.oCB_HoleAuswertungenAndererModule.get_StoredStatus());
		
		this.restoreSettingsBase();
		
		this._aufbauen();
		
	}

	
	
	private void _aufbauen() throws myException
	{
		this.vAuswertungenEigen.removeAllElements();
		this.vAuswertungenFremd.removeAllElements();
		
		vAuswertungenEigen.add(new AuswertungenWarenStroemeViaTempTable());
		vAuswertungenEigen.add(new AuswertungWarenstroemeNachLagerUndSorten());
//		vAuswertungenEigen.add(new old_AuswertungWarenstroemeNachLagerUndSorten());
		
		Vector<String> vJasperName = new Vector<String>();     //sorgt dafuer, dass jeder "zusammengesuchte" report nur einmal vorkommt
		
//		RECLIST_REPORT  oReclistReport = new RECLIST_REPORT("SELECT * FROM "+bibE2.cTO()+".JT_REPORT WHERE MODULE_KENNER="+bibALL.MakeSql(E2_MODULNAMES.NAME_MODUL_ROHSTOFFAUSWERTUNGEN)+" ORDER BY BUTTONTEXT");
		
		SEL reportAbfrage = new SEL().FROM(_TAB.report).WHERE(new vgl(REPORT.aktiv, "Y")).ORDERUP(REPORT.buttontext);
		
		RECLIST_REPORT  oReclistReport = new RECLIST_REPORT(reportAbfrage.s());
		
//		Alte SQL abfrage;
//		RECLIST_REPORT  oReclistReport = new RECLIST_REPORT("SELECT * FROM "+bibE2.cTO()+".JT_REPORT ORDER BY BUTTONTEXT");

		for (int i=0;i<oReclistReport.get_vKeyValues().size();i++)
		{
			RECORD_REPORT  recRep = oReclistReport.get(i);
			if (recRep.get_MODULE_KENNER_cUF_NN("").equals(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_ROHSTOFFAUSWERTUNGEN.get_callKey()))
			{
				vAuswertungenEigen.add(new Auswertungen_standardReports(recRep));
			}
			else if (recRep.is_PASS_NO_ID_YES())
			{
				if (!vJasperName.contains(recRep.get_NAME_OF_REPORTFILE_cUF_NN("")))
				{
					if (bibE2.get_bIsModuleAllowed4ThisUser(recRep.get_MODULE_KENNER_cUF_NN("")))
					{
						vJasperName.add(recRep.get_NAME_OF_REPORTFILE_cUF_NN(""));
						vAuswertungenFremd.add(new Auswertungen_standardReports_FremdeListen(recRep,bibE2.get_TabNameFromModulKenner(recRep.get_MODULE_KENNER_cUF_NN(""))));
					}
				}
			}
		}

		Vector<XX_Auswertungen>  vKomplett = new Vector<XX_Auswertungen>();
		vKomplett.addAll(vAuswertungenEigen);
		
		if (this.oCB_HoleAuswertungenAndererModule.isSelected())
		{
			vKomplett.addAll(vAuswertungenFremd);
		}
		
		
		for (XX_Auswertungen oAuswert: this.vAuswertungenEigen)
		{
			oAuswert.get_oCB_Anzeigen().add_oActionAgent(new ownActionAgentSaveStateList());
			oAuswert.get_oCB_Anzeigen().add_oActionAgent(new ownActionFuelleModulNeu());
		}
		for (XX_Auswertungen oAuswert: this.vAuswertungenFremd)
		{
			oAuswert.get_oCB_Anzeigen().add_oActionAgent(new ownActionAgentSaveStateList());
			oAuswert.get_oCB_Anzeigen().add_oActionAgent(new ownActionFuelleModulNeu());
		}
		
		
		
		this.restoreSettingsList();
		
		this.set_Auswertungen(vKomplett, this.oGridSteuerung);
		
	}

	
	
	
	private class ownActionAgentKomplettNeuAufbau extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			AW_RohstoffAuswertungen.this.get_oTFSuchfeld().setText("");
			AW_RohstoffAuswertungen.this._aufbauen();
			
		}
	}
	
	
	private void saveSettingsBase() throws myException
	{
		E2_UserSetting_ComponentMap  oSaveSettings = new E2_UserSetting_ComponentMap(AW_RohstoffAuswertungen.SESSION_HASH_4_AW_ROSTOFFAUSWERTUNGEN_BASE);
		
		oSaveSettings.put("1", this.get_oTFSuchfeld());
		oSaveSettings.put("2", this.oCB_HoleAuswertungenAndererModule);
		oSaveSettings.put("3", this.get_oCB_ZeigeNurSelektierte());
		
		oSaveSettings.SAVE_WERTE();
	}

	private void restoreSettingsBase() throws myException
	{
		E2_UserSetting_ComponentMap  oSaveSettings = new E2_UserSetting_ComponentMap(AW_RohstoffAuswertungen.SESSION_HASH_4_AW_ROSTOFFAUSWERTUNGEN_BASE);
		
		oSaveSettings.put("1", this.get_oTFSuchfeld());
		oSaveSettings.put("2", this.oCB_HoleAuswertungenAndererModule);
		oSaveSettings.put("3", this.get_oCB_ZeigeNurSelektierte());
		
		oSaveSettings.RESTORE_WERTE();
	}

	
	private void saveSettingsList() throws myException
	{
		E2_UserSetting_ComponentMap  oSaveSettings = new E2_UserSetting_ComponentMap(AW_RohstoffAuswertungen.SESSION_HASH_4_AW_ROSTOFFAUSWERTUNGEN_LIST);
		
		for (int i=0;i<this.vAuswertungenEigen.size();i++)
		{
			oSaveSettings.put("E"+i, this.vAuswertungenEigen.get(i).get_oCB_Anzeigen());
		}
		
		for (int i=0;i<this.vAuswertungenFremd.size();i++)
		{
			oSaveSettings.put("F"+i, this.vAuswertungenFremd.get(i).get_oCB_Anzeigen());
		}
		
		oSaveSettings.SAVE_WERTE();
	}

	private void restoreSettingsList() throws myException
	{
		E2_UserSetting_ComponentMap  oSaveSettings = new E2_UserSetting_ComponentMap(AW_RohstoffAuswertungen.SESSION_HASH_4_AW_ROSTOFFAUSWERTUNGEN_LIST);
		
		for (int i=0;i<this.vAuswertungenEigen.size();i++)
		{
			oSaveSettings.put("E"+i, this.vAuswertungenEigen.get(i).get_oCB_Anzeigen());
		}
		
		for (int i=0;i<this.vAuswertungenFremd.size();i++)
		{
			oSaveSettings.put("F"+i, this.vAuswertungenFremd.get(i).get_oCB_Anzeigen());
		}
		

		oSaveSettings.RESTORE_WERTE();
	}

	
	
	
	private class ownActionAgentSaveStateBase extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			AW_RohstoffAuswertungen.this.saveSettingsBase();
		}
	}

	
	private class ownActionAgentSaveStateList extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			AW_RohstoffAuswertungen.this.saveSettingsList();
		}
	}
	
	
	private class ownActionFuelleModulNeu  extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			AW_RohstoffAuswertungen.this.fuelle_modul_neu();
		}
	}

}
