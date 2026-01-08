package rohstoff.Echo2BusinessLogic.LAGER_LISTE;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.IF_HasBasicModuleContainerSettings;
import panter.gmbh.Echo2.Container.XX_BasicModulContainerSettings;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.exceptions.myException;

public class LAG_KTO_LIST_BasicModuleContainer extends Project_BasicModuleContainer implements IF_HasBasicModuleContainerSettings
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3722259148720652383L;
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public static final String KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_EA_Fuhren = "KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_EA_Fuhren";
	public static final String KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_LL_Fuhren = "KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_LL_Fuhren";
	public static final String KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Korrekturbuchungen = "KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Korrekturbuchungen";
	public static final String KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Umbuchungen = "KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Umbuchungen";

	public static final String KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Gebucht = "KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Gebucht";
	public static final String KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Teilgebucht = "KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Teilgebucht";
	public static final String KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Ungebucht = "KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Ungebucht";
	public static final String KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Keine_Buchpos = "KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Keine_Buchpos";
	public static final String KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Unvollstaendig = "KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Unvollstaendig";

	
	
	public LAG_KTO_LIST_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_LAGERLISTE_KONTO);
		
		this.set_bVisible_Row_For_Messages(true);
		
		E2_NavigationList oNaviList = new E2_NavigationList();
		
		oNaviList.INIT_WITH_ComponentMAP(new LAG_KTO_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		
		LAG_KTO_LIST_BedienPanel oPanel = new LAG_KTO_LIST_BedienPanel(oNaviList,new LAG_KTO_MASK_BasicModuleContainer(), this.get_MODUL_IDENTIFIER());

		
		LAG_KTO_LIST_Summary 	 oPanelSummary = new LAG_KTO_LIST_Summary(oNaviList, oPanel.get_oLAG_LIST_Selector());
		
		this.add(oPanel);
		this.add(oPanelSummary,E2_INSETS.I_2_0_2_0);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		// User Settings setzen
		try{
		setUserSettings(oPanel.get_oLAG_LIST_Selector());
		} catch(Exception e){
			// hier muss man auf jeden Fall weiter machen!!
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Die Settings sind nicht korrekt eingestellt!"));
		}
		
		oPanel.get_oLAG_LIST_Selector().get_oSelVector().doActionPassiv();
		
	}

	/**
	 * 
	 * @author manfred
	 * @date   16.04.2012
	 * @param oSelector
	 * @throws myException 
	 */
	private void setUserSettings(LAG_KTO_LIST_Selector oSelector) throws myException{
		String sKey = "";
		
		setUserSetting(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_EA_Fuhren, oSelector.get_SelectorTypen(), 0, oSelector);
		setUserSetting(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_LL_Fuhren, oSelector.get_SelectorTypen(), 1, oSelector);
		setUserSetting(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Korrekturbuchungen, oSelector.get_SelectorTypen(), 2, oSelector);
		setUserSetting(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Umbuchungen, oSelector.get_SelectorTypen(), 3, oSelector);
		
		setUserSetting(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Unvollstaendig, oSelector.get_SelectorStatusFuhren(), 0, oSelector);
		setUserSetting(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Keine_Buchpos, oSelector.get_SelectorStatusFuhren(), 1, oSelector);
		setUserSetting(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Ungebucht, oSelector.get_SelectorStatusFuhren(), 2, oSelector);
		setUserSetting(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Teilgebucht, oSelector.get_SelectorStatusFuhren(), 3, oSelector);
		setUserSetting(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Gebucht, oSelector.get_SelectorStatusFuhren(), 4, oSelector);
	}
	

	/**
	 * 
	 * @author manfred
	 * @date   16.04.2012
	 * @param key
	 * @param oMultiselection
	 * @param idxCheckbox
	 * @param oSelector
	 * @throws myException
	 */
	private void setUserSetting(String key, E2_ListSelektorMultiselektionStatusFeld_STD oMultiselection, int idxCheckbox ,LAG_KTO_LIST_Selector oSelector) throws myException{
		
		HashMap<String, String> hmSettingsFromDatabase = this.get_oBasicModuleContainerSettings().get_hmSettingsFromDatabase();
		boolean bHasKey =  hmSettingsFromDatabase != null &&  hmSettingsFromDatabase.containsKey(key);

		if (bHasKey){
			boolean bSetY = S.NN(this.get_oBasicModuleContainerSettings().get_hashKeySetting(key)).equals("Y");
			if (oMultiselection instanceof LAG_KTO_LIST_SelektorStatusFuhre){
				oSelector.get_SelectorStatusFuhren().selectCheckbox(idxCheckbox, bSetY);
			} else if (oMultiselection instanceof LAG_KTO_LIST_SelektorWelcheTypenEinblenden){
				oSelector.get_SelectorTypen().selectCheckbox(idxCheckbox, bSetY);
			}
		}
		
	}
	
	

	@Override
	public XX_BasicModulContainerSettings get_oBasicModuleContainerSettings()
			throws myException {
		return new cModulSettingsClass(this);
	}
	
	
	
	/**
	 * Private Klasse um StartUp-Einstellungen im Dialog Lager-Liste festzulegen 
	 * @author manfred
	 * @date   16.04.2012
	 */
	private class cModulSettingsClass extends XX_BasicModulContainerSettings
	{
		
		private MyE2_CheckBox  oCb_Zeige_EA_Fuhren = new MyE2_CheckBox(new MyE2_String("Eingangs-/Ausgangsfuhren"));
		private MyE2_CheckBox  oCb_Zeige_LL_Fuhren = new MyE2_CheckBox(new MyE2_String("Lager-Lager-Fuhren"));
		private MyE2_CheckBox  oCb_Zeige_Korrekturbuchungen = new MyE2_CheckBox(new MyE2_String("Korrekturbuchungen"));
		private MyE2_CheckBox  oCb_Zeige_Umbuchungen = new MyE2_CheckBox(new MyE2_String("Umbuchungen"));
		
		private MyE2_CheckBox  oCb_Zeige_Gebucht = new MyE2_CheckBox(new MyE2_String("Gebucht"));
		private MyE2_CheckBox  oCb_Zeige_Teilgebucht = new MyE2_CheckBox(new MyE2_String("Teilgebucht"));
		private MyE2_CheckBox  oCb_Zeige_Ungebucht = new MyE2_CheckBox(new MyE2_String("Ungebucht"));
		private MyE2_CheckBox  oCb_Zeige_Keine_Buchpos = new MyE2_CheckBox(new MyE2_String("Keine Buchungspositionen vorhanden"));
		private MyE2_CheckBox  oCb_Zeige_Unvollstaendig = new MyE2_CheckBox(new MyE2_String("Eingabe Unvollständig"));
		
		
		
		
		public cModulSettingsClass(E2_BasicModuleContainer ContainerWhichsettingsAreSaved) throws myException
		{
			super(ContainerWhichsettingsAreSaved);
			this.oCb_Zeige_EA_Fuhren.setLineWrap(false);
			this.oCb_Zeige_LL_Fuhren.setLineWrap(false);
			this.oCb_Zeige_Korrekturbuchungen.setLineWrap(false);
			this.oCb_Zeige_Umbuchungen.setLineWrap(false);
			this.oCb_Zeige_Gebucht.setLineWrap(false);
			this.oCb_Zeige_Teilgebucht.setLineWrap(false);
			this.oCb_Zeige_Ungebucht.setLineWrap(false);
			this.oCb_Zeige_Keine_Buchpos.setLineWrap(false);
			this.oCb_Zeige_Unvollstaendig.setLineWrap(false);
		}
		
		

		@Override
		public E2_BasicModuleContainer create_oContainer4Settings() throws myException
		{
			return new ownContainer4Settings();
		}

		@Override
		public MyE2_Grid create_oGridWithSettingObjects() throws myException
		{
			return new ownGrid4Settings();
		}

		@Override
		public HashMap<String, String> get_hmSettingsFromSettingScreen() throws myException
		{
			HashMap<String, String> hmRueck = new HashMap<String, String>();
			
			hmRueck.put(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_EA_Fuhren, this.oCb_Zeige_EA_Fuhren.isSelected()?"Y":"N");
			hmRueck.put(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_LL_Fuhren, this.oCb_Zeige_LL_Fuhren.isSelected()?"Y":"N");
			hmRueck.put(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Korrekturbuchungen, this.oCb_Zeige_Korrekturbuchungen.isSelected()?"Y":"N");
			hmRueck.put(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Umbuchungen, this.oCb_Zeige_Umbuchungen.isSelected()?"Y":"N");
			
			hmRueck.put(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Gebucht, this.oCb_Zeige_Gebucht.isSelected()?"Y":"N");
			hmRueck.put(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Teilgebucht, this.oCb_Zeige_Teilgebucht.isSelected()?"Y":"N");
			hmRueck.put(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Ungebucht, this.oCb_Zeige_Ungebucht.isSelected()?"Y":"N");
			hmRueck.put(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Keine_Buchpos, this.oCb_Zeige_Keine_Buchpos.isSelected()?"Y":"N");
			hmRueck.put(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Unvollstaendig, this.oCb_Zeige_Unvollstaendig.isSelected()?"Y":"N");
			
			return hmRueck;
		}

		@Override
		public void set_hmSettingsToSettingScreen(HashMap<String, String> hmSettingsFromDB) throws myException
		{
			HashMap<String, String> hmDatabaseSettings = this.get_hmSettingsFromDatabase();
			
//			if (hmDatabaseSettings!=null && hmSettingsFromDB.containsKey(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_1))
//			{
//				this.oCb_Zeige_EA_Fuhren.setSelected(hmSettingsFromDB.get(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_EA_Fuhren).equals("Y"));
//			}
			setCheckboxValue(oCb_Zeige_EA_Fuhren, LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_EA_Fuhren, hmSettingsFromDB);
			setCheckboxValue(oCb_Zeige_LL_Fuhren, LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_LL_Fuhren, hmSettingsFromDB);
			setCheckboxValue(oCb_Zeige_Korrekturbuchungen, LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Korrekturbuchungen, hmSettingsFromDB);
			setCheckboxValue(oCb_Zeige_Umbuchungen, LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Umbuchungen, hmSettingsFromDB);
			setCheckboxValue(oCb_Zeige_Gebucht, LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Gebucht, hmSettingsFromDB);
			setCheckboxValue(oCb_Zeige_Teilgebucht, LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Teilgebucht, hmSettingsFromDB);
			setCheckboxValue(oCb_Zeige_Ungebucht, LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Ungebucht, hmSettingsFromDB);
			setCheckboxValue(oCb_Zeige_Keine_Buchpos, LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Keine_Buchpos, hmSettingsFromDB);
			setCheckboxValue(oCb_Zeige_Unvollstaendig, LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Unvollstaendig, hmSettingsFromDB);
		}
		
		
		private  void setCheckboxValue(MyE2_CheckBox oCB, String sKey,HashMap<String, String> hmSettingsFromDB) throws myException{
			
			HashMap<String, String> hmDatabaseSettings = this.get_hmSettingsFromDatabase();
			
			if (hmDatabaseSettings!=null && hmSettingsFromDB.containsKey(sKey))	{
				oCB.setSelected(hmSettingsFromDB.get(sKey).equals("Y"));
			}
		}

		
		
		
		@Override
		public Vector<String> get_vKeySet_Settings4Module() throws myException
		{
			
			Vector<String> vSettings = new Vector<String>();
			vSettings.add(KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_EA_Fuhren);
			vSettings.add(KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_LL_Fuhren);
			vSettings.add(KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Umbuchungen);
			vSettings.add(KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Korrekturbuchungen);
			
			vSettings.add(KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Gebucht);
			vSettings.add(KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Teilgebucht);
			vSettings.add(KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Ungebucht);
			vSettings.add(KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Keine_Buchpos);
			vSettings.add(KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Unvollstaendig);
			
			
			return vSettings; //bibALL.get_Vector(LAG_KTO_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_1);
		}

		
		@Override
		public MyE2_String get_NameModule4User() 								{	return new MyE2_String("Lagerbewegungen");	}
		
		
		
		
		
		
		/**
		 * 
		 * @author manfred
		 * @date   16.04.2012
		 */
		private class ownContainer4Settings extends E2_BasicModuleContainer		{		}
		
		
		/**
		 * 
		 * @author manfred
		 * @date   16.04.2012
		 */
		private class ownGrid4Settings extends MyE2_Grid
		{
			public ownGrid4Settings()
			{
				super(2, MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11_W100());
				
				this.add(new MyE2_Label("Zeige:"),1, E2_INSETS.I_5_5_5_0);
				this.add(cModulSettingsClass.this.oCb_Zeige_EA_Fuhren,1, E2_INSETS.I_5_5_5_0);
				this.add(new MyE2_Label(""),1, E2_INSETS.I_5_2_5_0);
				this.add(cModulSettingsClass.this.oCb_Zeige_LL_Fuhren,1, E2_INSETS.I_5_2_5_0);
				this.add(new MyE2_Label(""),1, E2_INSETS.I_5_2_5_0);
				this.add(cModulSettingsClass.this.oCb_Zeige_Korrekturbuchungen,1, E2_INSETS.I_5_2_5_0);
				this.add(new MyE2_Label(""),1, E2_INSETS.I_5_2_5_2);
				this.add(cModulSettingsClass.this.oCb_Zeige_Umbuchungen,1, E2_INSETS.I_5_2_5_2);
				
				this.add(new MyE2_Label("Fuhrenstatus:"),1, E2_INSETS.I_5_5_5_0);
				this.add(cModulSettingsClass.this.oCb_Zeige_Unvollstaendig,1, E2_INSETS.I_5_5_5_0);
				this.add(new MyE2_Label(""),1, E2_INSETS.I_5_5_0_0);
				this.add(cModulSettingsClass.this.oCb_Zeige_Keine_Buchpos,1, E2_INSETS.I_5_2_5_0);
				this.add(new MyE2_Label(""),1, E2_INSETS.I_5_2_5_0);
				this.add(cModulSettingsClass.this.oCb_Zeige_Ungebucht,1, E2_INSETS.I_5_2_5_0);
				this.add(new MyE2_Label(""),1, E2_INSETS.I_5_2_5_0);
				this.add(cModulSettingsClass.this.oCb_Zeige_Teilgebucht,1, E2_INSETS.I_5_2_5_0);
				this.add(new MyE2_Label(""),1, E2_INSETS.I_5_2_5_0);
				this.add(cModulSettingsClass.this.oCb_Zeige_Gebucht,1, E2_INSETS.I_5_2_5_0);
				
			}
		}

		@Override
		public int[] get_WidthHeightOfPopupWindow()  							{	return bibARR.get_Array(400, 200);		}
	}
	
	
		
}
