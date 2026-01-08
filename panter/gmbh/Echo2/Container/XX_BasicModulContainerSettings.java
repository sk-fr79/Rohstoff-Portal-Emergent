package panter.gmbh.Echo2.Container;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_HashMap;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public abstract class XX_BasicModulContainerSettings
{

	public static String                      SESSION_HASH_4_SAVE_SETTINGS = "SESSION_HASH_4_SAVE_SETTINGS@@@20120213"; 
	

	//eigener container, damit die eigene groesse agespeichert werden kann
	public abstract E2_BasicModuleContainer   	create_oContainer4Settings() 		throws myException;
	public abstract MyE2_Grid    				create_oGridWithSettingObjects() 	throws myException;
	
	//uebergabe der gerade eingestellten settings
	public abstract HashMap<String, String>     get_hmSettingsFromSettingScreen() 	throws myException;
	
	//uebergabe der eingelesenen werte an die maske
	public abstract void   						set_hmSettingsToSettingScreen(HashMap<String, String> hmSettingsFromDB) throws myException;

	//das keyset der speicher-hashmap
	public abstract Vector<String>   		 	get_vKeySet_Settings4Module() 		throws myException;
	
	//ein sprechender name fuer das modul
	public abstract MyE2_String    				get_NameModule4User();
	
	//initiale fenstergroesse des settings-popup einstellen
	public abstract int[]                       get_WidthHeightOfPopupWindow();


	//button, der in die titelzeile des moduls eingeblendet wird
	private StartButton   						oButton4Start = 				new StartButton();

	private E2_BasicModuleContainer             oContainerBase = null;
	private E2_BasicModuleContainer  			oContainer4Settings = null;
	private MyE2_Grid   						oGridWithSettings = null;
	
	
	private boolean   							bSettingModuleWasCalledFirstTime = false;
	
	
	
	public XX_BasicModulContainerSettings(E2_BasicModuleContainer ContainerWhichsettingsAreSaved) throws myException
	{
		super();
		this.oContainerBase = ContainerWhichsettingsAreSaved;
	}

	
	
	/*
	 * das darf erst beim klick auf den aufrufbutton stattfinden
	 */
	private void init_module() throws myException
	{
		if (!this.bSettingModuleWasCalledFirstTime)
		{
			this.oContainer4Settings = 	this.create_oContainer4Settings();
			this.oGridWithSettings = 	this.create_oGridWithSettingObjects();
			
			this.oContainer4Settings.add(this.oGridWithSettings, E2_INSETS.I_5_5_5_5);
			this.oContainer4Settings.add(new E2_ComponentGroupHorizontal(0, new ownButtonSave(this.oContainer4Settings), new ownButtonCancel(this.oContainer4Settings), E2_INSETS.I_0_0_10_0));
			
			//darf nur beim ersten klick aufgerufen werden
			this.bSettingModuleWasCalledFirstTime = true;
		}
	}
	

	public String get_hashKeySetting(String cSettingsKey) throws myException
	{
		String cRueck = null;
		
		HashMap<String, String> hmSettings = this.get_hmSettingsFromDatabase();
		
		if (hmSettings!=null && hmSettings.containsKey(cSettingsKey))
		{
			cRueck = hmSettings.get(cSettingsKey);		
		}
		return cRueck;
	}
	
	
	public HashMap<String, String> get_hmSettingsFromDatabase() throws myException
	{
		return (HashMap<String, String>)new ownSettingAgent().get_Settings(XX_BasicModulContainerSettings.this.oContainerBase.get_MODUL_IDENTIFIER());
	}



	private class ownButtonSave extends MyE2_Button
	{
		private E2_BasicModuleContainer  oContainer = null;
		
		public ownButtonSave(E2_BasicModuleContainer Container)
		{
			super(new MyE2_String("Einstellung(en) Speichern"));
			
			this.oContainer = Container;
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					new ownSettingAgent().STORE(XX_BasicModulContainerSettings.this.oContainerBase.get_MODUL_IDENTIFIER(), XX_BasicModulContainerSettings.this.get_hmSettingsFromSettingScreen());
					ownButtonSave.this.oContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					bibMSG.add_MESSAGE(new MyE2_Warning_Message("Die Einstellung wurde gespeichert. Bitte schliessen Sie den Modulreiter und öffnen Sie dieses Modul neu"));
					
				}
			});
		}

	}
	
	
	private class ownButtonCancel extends MyE2_Button
	{
		private E2_BasicModuleContainer  oContainer = null;
		
		public ownButtonCancel(E2_BasicModuleContainer Container)
		{
			super(new MyE2_String("Abbrechen"));
			
			this.oContainer = Container;

			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					ownButtonCancel.this.oContainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				}
			});
		}
	}
	
	
	private class StartButton extends MyE2_Button implements IF_BasicModuleContainer_ADD_ON_Component
	{
		
		public StartButton()
		{
			super(E2_ResourceIcon.get_RI("einstellungen_modul.png"),E2_ResourceIcon.get_RI("einstellungen_modul__.png"));
			
			this.setToolTipText(new MyE2_String("Benutzereinstellungen für das Modul ",true,
									XX_BasicModulContainerSettings.this.get_NameModule4User().CTrans(),false).CTrans());
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					XX_BasicModulContainerSettings.this.init_module();
					
					int[] iBreiteHoehe = XX_BasicModulContainerSettings.this.get_WidthHeightOfPopupWindow();
					
					XX_BasicModulContainerSettings.this.get_oContainer4Settings().CREATE_AND_SHOW_POPUPWINDOW(
							new Extent(iBreiteHoehe[0]), new Extent(iBreiteHoehe[1]), new MyE2_String("Benutzereinstellungen für das Modul ",true,
									XX_BasicModulContainerSettings.this.get_NameModule4User().CTrans(),false));
					
					XX_BasicModulContainerSettings.this.set_hmSettingsToSettingScreen(XX_BasicModulContainerSettings.this.get_hmSettingsFromDatabase());
				}
			});
		}
		
		@Override
		public boolean get_bIsShown() throws myException
		{
			return true;
		}

		@Override
		public void set_cMODULKENNER(String cModulKenner)
		{
		}

		@Override
		public String get_cMODULKENNER()
		{
			return XX_BasicModulContainerSettings.this.oContainerBase.get_MODUL_IDENTIFIER();
		}

		@Override
		public void add_cZusatzMODULKENNER(String cModulKenner)
		{
		}

		
	}
	


	
	private class ownSettingAgent extends E2_UserSetting_HashMap 
	{

		public ownSettingAgent() throws myException
		{
			super(XX_BasicModulContainerSettings.SESSION_HASH_4_SAVE_SETTINGS);
			this.set_KeySet(XX_BasicModulContainerSettings.this.get_vKeySet_Settings4Module());
		}
		
	}




	public static String get_SESSION_HASH_4_SAVE_SETTINGS()
	{
		return SESSION_HASH_4_SAVE_SETTINGS;
	}
	
	public E2_BasicModuleContainer get_oContainerBase()
	{
		return oContainerBase;
	}
	
	public E2_BasicModuleContainer get_oContainer4Settings()
	{
		return oContainer4Settings;
	}
	public MyE2_Grid get_oGridWithSettings()
	{
		return oGridWithSettings;
	}
	
	
	public MyE2_Button get_oButton4Start()
	{
		return this.oButton4Start;
	}
	
	
	
	
}
