package rohstoff.Echo2BusinessLogic.FIBU;

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
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG_K_MASK__ModulContainer;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;

public class FIBU_LIST_BasicModuleContainer extends Project_BasicModuleContainer implements IF_HasBasicModuleContainerSettings
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";
	
	public static final String KEY_SETTINGS_FIBU_FAELLIGKEITSDATUM_VORBESETZEN = "KEY_SETTINGS_FIBU_FAELLIGKEITSDATUM_VORBESETZEN";
	public static final String KEY_SETTINGS_VERBUCHTE_GRAU_IN_LISTE = "KEY_SETTINGS_VERBUCHTE_GRAU_IN_LISTE";

	
	/*
	 * zwei masken-container fuer die popup-masken aus der liste
	 */
	private BSRG_K_MASK__ModulContainer   oMaskContainer_RG = null;
	private FS_ModulContainer_MASK        oMaskContainer_ADRESSE = null;
	private E2_NavigationList             oNaviList = null;
	
	public FIBU_LIST_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_FIBU_LIST);
		
		this.oMaskContainer_ADRESSE = new FS_ModulContainer_MASK();
		this.oMaskContainer_RG  = new BSRG_K_MASK__ModulContainer(new BS__SETTING(myCONST.VORGANGSART_RECHNUNG),null);
		
		this.set_bVisible_Row_For_Messages(true);
		
		this.oNaviList = new E2_NavigationList();

		
		//2012-06-19: weiterer setter:
		boolean bMacheGeschlosseneListenzeilenGrau =  S.NN(this.get_oBasicModuleContainerSettings().get_hashKeySetting(FIBU_LIST_BasicModuleContainer.KEY_SETTINGS_VERBUCHTE_GRAU_IN_LISTE)).equals("Y");
		
		FIBU_LIST_ComponentMap oListMAP = new FIBU_LIST_ComponentMap(oMaskContainer_RG,oMaskContainer_ADRESSE,this,bMacheGeschlosseneListenzeilenGrau);
		oNaviList.INIT_WITH_ComponentMAP(oListMAP,E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		FIBU_LIST_BedienPanel oPanel = new FIBU_LIST_BedienPanel(oNaviList);
		
		
		
		
		//2012-02-14: die settings-einstellungen aus der datenbank lesen
		boolean bSetDatum = S.NN(this.get_oBasicModuleContainerSettings().get_hashKeySetting(FIBU_LIST_BasicModuleContainer.KEY_SETTINGS_FIBU_FAELLIGKEITSDATUM_VORBESETZEN)).equals("Y");
		
		if (bSetDatum)
		{
			oPanel.get_oFIBU_LIST_Selector().get_oSelBereichDatumFaelligkeit().get_oTFDatumBis().get_oTextField().setText(bibALL.get_cDateNOW());
		}
		
		
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		oPanel.get_oFIBU_LIST_Selector().get_oSelVector().doActionPassiv();
	}

	public E2_NavigationList get_oNaviList()
	{
		return oNaviList;
	}

	public BSRG_K_MASK__ModulContainer get_oMaskContainer_RG()
	{
		return oMaskContainer_RG;
	}

	public FS_ModulContainer_MASK get_oMaskContainer_ADRESSE()
	{
		return oMaskContainer_ADRESSE;
	}

	@Override
	public XX_BasicModulContainerSettings get_oBasicModuleContainerSettings() throws myException
	{
		return new ownSettingsClass(this);
	}


	
	//settings-class um einstellungen innerhalb des moduls vornehmen 
	private class ownSettingsClass extends XX_BasicModulContainerSettings
	{
		private MyE2_CheckBox  oCB_SetActualDateInSelektor_Faelligkeit = new MyE2_CheckBox(new MyE2_String("Das Selektor-Feld Fälligkeit (Ende) mit aktuellem Datum vorbesetzen"));
		
		//2012-06-19: weiteres setting: verbuchte grau darstellen
		private MyE2_CheckBox  oCB_SetClosedRowsGrey = new MyE2_CheckBox(new MyE2_String("In der Liste geschlossene/verbuchte Belege grau darstellen"));
		
		
		
		public ownSettingsClass(E2_BasicModuleContainer ContainerWhichsettingsAreSaved) throws myException
		{
			super(ContainerWhichsettingsAreSaved);
			this.oCB_SetActualDateInSelektor_Faelligkeit.setLineWrap(true);
			this.oCB_SetClosedRowsGrey.setLineWrap(true);
			
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
			hmRueck.put(FIBU_LIST_BasicModuleContainer.KEY_SETTINGS_FIBU_FAELLIGKEITSDATUM_VORBESETZEN, this.oCB_SetActualDateInSelektor_Faelligkeit.isSelected()?"Y":"N");
			hmRueck.put(FIBU_LIST_BasicModuleContainer.KEY_SETTINGS_VERBUCHTE_GRAU_IN_LISTE, this.oCB_SetClosedRowsGrey.isSelected()?"Y":"N");
			return hmRueck;
		}

		@Override
		public void set_hmSettingsToSettingScreen(HashMap<String, String> hmSettingsFromDB) throws myException
		{
			HashMap<String, String> hmDatabaseSettings = this.get_hmSettingsFromDatabase();
			
			if (hmDatabaseSettings!=null && hmSettingsFromDB.containsKey(FIBU_LIST_BasicModuleContainer.KEY_SETTINGS_FIBU_FAELLIGKEITSDATUM_VORBESETZEN))
			{
				this.oCB_SetActualDateInSelektor_Faelligkeit.setSelected(
						hmSettingsFromDB.get(FIBU_LIST_BasicModuleContainer.KEY_SETTINGS_FIBU_FAELLIGKEITSDATUM_VORBESETZEN).equals("Y"));
			}
			
			if (hmDatabaseSettings!=null && hmSettingsFromDB.containsKey(FIBU_LIST_BasicModuleContainer.KEY_SETTINGS_VERBUCHTE_GRAU_IN_LISTE))
			{
				this.oCB_SetClosedRowsGrey.setSelected(
						hmSettingsFromDB.get(FIBU_LIST_BasicModuleContainer.KEY_SETTINGS_VERBUCHTE_GRAU_IN_LISTE).equals("Y"));
			}

			
		}

		@Override
		public Vector<String> get_vKeySet_Settings4Module() throws myException
		{
			return bibALL.get_Vector(
					FIBU_LIST_BasicModuleContainer.KEY_SETTINGS_FIBU_FAELLIGKEITSDATUM_VORBESETZEN,
					FIBU_LIST_BasicModuleContainer.KEY_SETTINGS_VERBUCHTE_GRAU_IN_LISTE
					);
		}

		@Override
		public MyE2_String get_NameModule4User() 								{	return new MyE2_String("Fibu");	}
		
		
		private class ownContainer4Settings extends E2_BasicModuleContainer		{		}
		
		
		private class ownGrid4Settings extends MyE2_Grid
		{
			public ownGrid4Settings()
			{
				super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11_W100());
				this.add(ownSettingsClass.this.oCB_SetActualDateInSelektor_Faelligkeit,E2_INSETS.I_5_5_5_5);
				this.add(ownSettingsClass.this.oCB_SetClosedRowsGrey,E2_INSETS.I_5_5_5_5);
				
			}
		}

		@Override
		public int[] get_WidthHeightOfPopupWindow()  							{	return bibARR.get_Array(400, 200);		}
	}
	
	
	
	
}
