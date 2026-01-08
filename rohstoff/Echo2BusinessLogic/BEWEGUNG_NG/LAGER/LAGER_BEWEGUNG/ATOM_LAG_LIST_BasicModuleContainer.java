package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWEGUNG;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.IF_HasBasicModuleContainerSettings;
import panter.gmbh.Echo2.Container.XX_BasicModulContainerSettings;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.exceptions.myException;

public class ATOM_LAG_LIST_BasicModuleContainer extends Project_BasicModuleContainer implements IF_HasBasicModuleContainerSettings
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public static final String KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_EA_Fuhren = "KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_EA_Fuhren";
	public static final String KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_LL_Fuhren = "KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_LL_Fuhren";
	public static final String KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Korrekturbuchungen = "KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Korrekturbuchungen";
	public static final String KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Umbuchungen = "KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Umbuchungen";
	public static final String KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Mengenkorrekturen = "KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Mengenkorrekturen";
	public static final String KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Sortenwechsel = "KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Sortenwechsel";
	
	ATOM_LAG_LIST_BedienPanel oPanel = null;
	
	
	public ATOM_LAG_LIST_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_ATOM_LAGER_BEWEGUNG_LIST);
		
		this.set_bVisible_Row_For_Messages(true);
		
		E2_NavigationList oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(new ATOM_LAG_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		oPanel = new ATOM_LAG_LIST_BedienPanel(oNaviList,new ATOM_LAG_MASK_BasicModuleContainer());
		
		ATOM_LAG_LIST_Summay 	 oPanelSummary = new ATOM_LAG_LIST_Summay(oNaviList, oPanel.get_oATOM_LAG_LIST_Selector());

		
		this.add(oPanel);
		this.add(oPanelSummary,E2_INSETS.I_2_0_2_0);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
//		// User Settings setzen
//		try{
//			if (this.get_oBasicModuleContainerSettings() instanceof cModulSettingsClass){
//				this.get_oBasicModuleContainerSettings().set_hmSettingsToSettingScreen(null);
//			}
//			
////			setUserSettings(oPanel.get_oATOM_LAG_LIST_Selector());
//		} catch(Exception e){
//					// hier muss man auf jeden Fall weiter machen!!
//					bibMSG.add_MESSAGE(new MyE2_Info_Message("Die Settings sind nicht korrekt eingestellt!"));
//		}
				
		
		oPanel.get_oATOM_LAG_LIST_Selector().get_oSelVector().doActionPassiv();
	}
	
	
	
	/**
	 * 
	 * @author manfred
	 * @date   16.04.2012
	 * @param oSelector
	 * @throws myException 
	 */
//	private void setUserSettings(ATOM_LAG_LIST_Selector oSelector) throws myException{
//		String sKey = "";
//		
//		setUserSetting(ATOM_LAG_LIST_BasicModuleContainer.KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_EA_Fuhren, oSelector.get_SelectorTypen(), 0, oSelector);
//		setUserSetting(ATOM_LAG_LIST_BasicModuleContainer.KEY_SETTINGS_LAGER_LISTE_oCb_Zeige_Sortenwechsel, oSelector.get_SelectorTypen(), 1, oSelector);
//		setUserSetting(ATOM_LAG_LIST_BasicModuleContainer.KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Mengenkorrekturen, oSelector.get_SelectorTypen(), 2, oSelector);
//		setUserSetting(ATOM_LAG_LIST_BasicModuleContainer.KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_LL_Fuhren, oSelector.get_SelectorTypen(), 3, oSelector);
//		setUserSetting(ATOM_LAG_LIST_BasicModuleContainer.KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Korrekturbuchungen, oSelector.get_SelectorTypen(),4, oSelector);
//		setUserSetting(ATOM_LAG_LIST_BasicModuleContainer.KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Umbuchungen, oSelector.get_SelectorTypen(), 5, oSelector);
//	}
	


	@Override
	public XX_BasicModulContainerSettings get_oBasicModuleContainerSettings()	throws myException {
		return new cModulSettingsClass(this);
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
//	private void setUserSetting(String key, E2_ListSelektorMultiselektionStatusFeld_STD oMultiselection, int idxCheckbox ,ATOM_LAG_LIST_Selector oSelector) throws myException{
//		
//		HashMap<String, String> hmSettingsFromDatabase = this.get_oBasicModuleContainerSettings().get_hmSettingsFromDatabase();
//		boolean bHasKey =  hmSettingsFromDatabase != null &&  hmSettingsFromDatabase.containsKey(key);
//
//		if (bHasKey){
//			boolean bSetY = S.NN(this.get_oBasicModuleContainerSettings().get_hashKeySetting(key)).equals("Y");
//			if (oMultiselection instanceof ATOM_LAG_LIST_SelektorWelcheTypenEinblenden){
//				oSelector.get_SelectorTypen().selectCheckbox(idxCheckbox, bSetY);
//			}
//		}
//		
//	}
	
	
	
	
	
	/**
	 * Private Klasse um StartUp-Einstellungen im Dialog Lager-Liste festzulegen 
	 * @author manfred
	 * @date   16.04.2012
	 */
	private class cModulSettingsClass extends XX_BasicModulContainerSettings
	{
		
		
		
		public cModulSettingsClass(E2_BasicModuleContainer ContainerWhichsettingsAreSaved) throws myException
		{
			super(ContainerWhichsettingsAreSaved);
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
			ATOM_LAG_LIST_SelektorWelcheTypenEinblenden oSelektor = null;

			if (this.get_oContainerBase() instanceof ATOM_LAG_LIST_BasicModuleContainer){
				oSelektor = ((ATOM_LAG_LIST_BasicModuleContainer) this.get_oContainerBase()).oPanel.get_oATOM_LAG_LIST_Selector().get_SelectorTypen();
				if (oSelektor == null ) return hmRueck;
			}
			
					
			hmRueck.put(ATOM_LAG_LIST_BasicModuleContainer.KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_EA_Fuhren,oSelektor.getCheckbox(0).isSelected()?"Y":"N");
			hmRueck.put(ATOM_LAG_LIST_BasicModuleContainer.KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Sortenwechsel, oSelektor.getCheckbox(1).isSelected()?"Y":"N");
			hmRueck.put(ATOM_LAG_LIST_BasicModuleContainer.KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Mengenkorrekturen, oSelektor.getCheckbox(2).isSelected()?"Y":"N");
			hmRueck.put(ATOM_LAG_LIST_BasicModuleContainer.KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_LL_Fuhren, oSelektor.getCheckbox(3).isSelected()?"Y":"N");
			hmRueck.put(ATOM_LAG_LIST_BasicModuleContainer.KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Korrekturbuchungen, oSelektor.getCheckbox(4).isSelected()?"Y":"N");
			hmRueck.put(ATOM_LAG_LIST_BasicModuleContainer.KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Umbuchungen, oSelektor.getCheckbox(5).isSelected()?"Y":"N");
			
			return hmRueck;
		}

		
		@Override
		public void set_hmSettingsToSettingScreen(HashMap<String, String> hmSettingsFromDB) throws myException
		{
			ATOM_LAG_LIST_SelektorWelcheTypenEinblenden oSelektor = null;
			if (this.get_oContainerBase() instanceof ATOM_LAG_LIST_BasicModuleContainer){
				oSelektor = ((ATOM_LAG_LIST_BasicModuleContainer) this.get_oContainerBase()).oPanel.get_oATOM_LAG_LIST_Selector().get_SelectorTypen();
				if (oSelektor == null ) return ;
			}
			
			setCheckboxValue(oSelektor.getCheckbox(0), ATOM_LAG_LIST_BasicModuleContainer.KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_EA_Fuhren, hmSettingsFromDB);
			setCheckboxValue(oSelektor.getCheckbox(1), ATOM_LAG_LIST_BasicModuleContainer.KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Sortenwechsel, hmSettingsFromDB);
			setCheckboxValue(oSelektor.getCheckbox(2), ATOM_LAG_LIST_BasicModuleContainer.KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Korrekturbuchungen, hmSettingsFromDB);
			setCheckboxValue(oSelektor.getCheckbox(3), ATOM_LAG_LIST_BasicModuleContainer.KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_LL_Fuhren, hmSettingsFromDB);
			setCheckboxValue(oSelektor.getCheckbox(4), ATOM_LAG_LIST_BasicModuleContainer.KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Umbuchungen, hmSettingsFromDB);
			setCheckboxValue(oSelektor.getCheckbox(5), ATOM_LAG_LIST_BasicModuleContainer.KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Mengenkorrekturen, hmSettingsFromDB);
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
			vSettings.add(KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_EA_Fuhren);
			vSettings.add(KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_LL_Fuhren);
			vSettings.add(KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Umbuchungen);
			vSettings.add(KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Korrekturbuchungen);
			vSettings.add(KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Mengenkorrekturen);
			vSettings.add(KEY_SETTINGS_ATOM_LAG_LISTE_oCb_Zeige_Sortenwechsel);
			
			return vSettings; 
		}

		
		@Override
		public MyE2_String get_NameModule4User() 								{	return new MyE2_String("Lagerbewegungen Atom");	}
		
		
		
		
		
		
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
//				super(2, MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11_W100());
//				
//				this.add(new MyE2_Label("Zeige:"),1, E2_INSETS.I_5_5_5_0);
//				this.add(cModulSettingsClass.this.oCb_Zeige_EA_Fuhren,1, E2_INSETS.I_5_5_5_0);
//				this.add(new MyE2_Label(""),1, E2_INSETS.I_5_2_5_0);
//				this.add(cModulSettingsClass.this.oCb_Zeige_LL_Fuhren,1, E2_INSETS.I_5_2_5_0);
//				this.add(new MyE2_Label(""),1, E2_INSETS.I_5_2_5_0);
//				this.add(cModulSettingsClass.this.oCb_Zeige_Korrekturbuchungen,1, E2_INSETS.I_5_2_5_0);
//				this.add(new MyE2_Label(""),1, E2_INSETS.I_5_2_5_2);
//				this.add(cModulSettingsClass.this.oCb_Zeige_Umbuchungen,1, E2_INSETS.I_5_2_5_2);
//				this.add(new MyE2_Label(""),1, E2_INSETS.I_5_2_5_2);
//				this.add(cModulSettingsClass.this.oCb_Zeige_Sortenwechsel,1, E2_INSETS.I_5_2_5_2);
//				this.add(new MyE2_Label(""),1, E2_INSETS.I_5_2_5_2);
//				this.add(cModulSettingsClass.this.oCb_Zeige_Mengenkorrekturen,1, E2_INSETS.I_5_2_5_2);
				
				
			}
		}

		@Override
		public int[] get_WidthHeightOfPopupWindow() {	return bibARR.get_Array(400, 200);		}
	}

	

		
}
