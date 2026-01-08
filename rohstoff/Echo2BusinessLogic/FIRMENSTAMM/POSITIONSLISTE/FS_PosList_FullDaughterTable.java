package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.POSITIONSLISTE;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_BASIC_EditListButtonPanel;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.XX_FULL_DAUGHTER;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FS_PosList_FullDaughterTable extends XX_FULL_DAUGHTER {
	
	private static String   					MODULKENNER_ADDON = "RG_POS_LIST";
	
	private MyE2_Grid 							oGrid4Daughter = 		new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private E2_NavigationList   				oNaviList = 			new E2_NavigationList();
	private MyE2_Label  						oLabel4New= 			new MyE2_Label(new MyE2_String("Es gibt keine Rechnungs-/Gutschriftspositionen bei neuen Adressen ..."));
	private FS_PosList_ComponentMAP_LIST		oComponentMAP = 		null;
	private FS_PosList_SQLFieldMap  			oSQLFieldMAP = 			null;
	private E2_BASIC_EditListButtonPanel        oEditPanel = null;
	private E2_ButtonToSelectVisibleListColumns_and_other_settings  oButtonListSettings = null;
	private MyE2_Grid   						oGridBedienPanel = 		new MyE2_Grid(7,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
	//bei freien positionen ist die id_adresse zwingend in der jt_vpos_rg, sonst kann sie in der jt_vkopf_rg stehen
	private String 								cFeldAusdruckID_ADRESSE = "  NVL("+_DB.VPOS_RG+"."+_DB.VPOS_RG$ID_ADRESSE+","+_DB.VKOPF_RG+"."+_DB.VKOPF_RG$ID_ADRESSE+")";
	
	
	private FS_PosList_Selector    				oSelectionVector = null;
	
	
	public FS_PosList_FullDaughterTable(SQLFieldMAP  osqlFieldGroup) throws myException {

		super((SQLFieldForPrimaryKey)osqlFieldGroup.get_("ID_ADRESSE"));
		
		this.oComponentMAP = new FS_PosList_ComponentMAP_LIST();
		this.oSQLFieldMAP = (FS_PosList_SQLFieldMap)this.oComponentMAP.get_oSQLFieldMAP();

		this.oNaviList.set_bSaveSortStatus(true);
		
		this.oSelectionVector = new FS_PosList_Selector(this.oNaviList);
		 
		this.oEditPanel = new E2_BASIC_EditListButtonPanel(
										this.oNaviList,
										true,
										false,
										false,
										null,
										null,
										E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK,
										MODULKENNER_ADDON,
										null,
										null,
										null);

		this.oButtonListSettings = new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.oNaviList);

		//aufbau des Bedienpanels
		this.oGridBedienPanel.add(this.oButtonListSettings,E2_INSETS.I(0,0,10,0));
		this.oGridBedienPanel.add(this.oEditPanel,E2_INSETS.I(0,0,30,0));
		this.oGridBedienPanel.add(this.oSelectionVector.get_oGridWithSelectors(),E2_INSETS.I(0,0,10,0));
		
		//aubau der Seite
		this.oGrid4Daughter.add(this.oGridBedienPanel);
		this.oGrid4Daughter.add(this.oNaviList);
		
		this.oNaviList.INIT_WITH_ComponentMAP(	oComponentMAP, 
												new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID)), 
												E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK);
		
		this.set_bIsActive(false);
		
	}

															
	
	
	@Override
	public Component build_content_for_Mask(String cID_ADRESSE_F, String cID_ADRESSE_UF, String cMASK_STATUS) throws myException {
		
		this.oSQLFieldMAP.clear_BEDINGUNG_STATIC();
		this.oSQLFieldMAP.add_BEDINGUNG_STATIC(this.cFeldAusdruckID_ADRESSE+"="+cID_ADRESSE_UF);
		this.oSQLFieldMAP.add_BEDINGUNG_STATIC("NVL("+_DB.VPOS_RG+"."+_DB.VPOS_RG$DELETED+",'N')='N'");
		
		this.oNaviList.Fill_NavigationList("");
		
//		this.oSQL_RestrictRange.set_cRestrictionValue_IN_DB_FORMAT(cID_ADRESSE_UF);
//		this.oNaviList._REBUILD_COMPLETE_LIST("");
		this.oSelectionVector.doActionPassiv();
		
		this.oGrid4Daughter.removeAll();
		this.oGrid4Daughter.add(this.oGridBedienPanel);
		this.oGrid4Daughter.add(this.oNaviList);
		
		
		return this.oGrid4Daughter;
	}

	@Override
	public Component build_non_active_placeholder() throws myException {
		return new MyE2_Label(new MyE2_String("INAKTIV !!!"));
	}

	@Override
	public void set_buttons_IN_Status(String cMASK_STATUS) throws myException {

	}

	@Override
	public void prepare_DaughterContentForNew() throws myException {
		this.removeAll();
		this.add(this.oLabel4New,E2_INSETS.I(5,5,5,5));
	}

	

	
}
