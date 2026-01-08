package rohstoff.Echo2BusinessLogic.BAM_BETRIEB;

import java.util.HashMap;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_SearchDefinition_NG;
import panter.gmbh.Echo2.ListAndMask.List.Search.XX_SearchAddonBedingung;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelektorDateFromTo;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK.REM_bt_New_Generic;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_ENUMS.FBAM;
import panter.gmbh.indep.exceptions.myException;

public class BAMB_LIST_ModulContainer extends Project_BasicModuleContainer
{
	public static final String HASH_LABEL_COLUMN_BUCHUNG = "BAM_E2_ComponentMAP_LIST-HASH_LABEL_COLUMN_BUCHUNG";
	public static final String HASH_LABEL_COLUMN_GRUND = "BAM_E2_ComponentMAP_LIST-HASH_LABEL_COLUMN_GRUND";
	public static final String HASH_LABEL_COLUMN_FESTSTELLUNG = "BAM_E2_ComponentMAP_LIST-HASH_LABEL_COLUMN_FESTSTELLUNG";
	public static final String HASH_LABEL_COLUMN_IDS = "BAM_E2_ComponentMAP_LIST-HASH_LABEL_COLUMN_IDS";
	public static final String NAME_OF_LISTMARKER_IN_LIST =	"BAM_E2_ComponentMAP_LIST-NAME_OF_LISTMARKER_IN_LIST";
	public static final String NAME_OF_CHECKBOX_IN_LIST = 		"BAM_E2_ComponentMAP_LIST-NAME_OF_CHECKBOX_IN_LIST";

	public static final String NAME_OF_SQL_INFOFIELD	= "NAME_OF_SQL_INFOFIELD";
	

	private BAMB_MASK_ModulContainer 	oBAM_ModulContainerMASK = null;
	private E2_NavigationList			oNavigationList = null; 
	
	
	public BAMB_LIST_ModulContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_BBAM_LIST);
	
		
		BAMB_SQLFieldMAP 			oSQLFieldMAP = 			new BAMB_SQLFieldMAP();
		BAMB_LIST_ComponentMAP 	oComponentMAP_LIST = 	new BAMB_LIST_ComponentMAP(oSQLFieldMAP);
	
		this.oNavigationList = new E2_NavigationList();

		oNavigationList.INIT_WITH_ComponentMAP(oComponentMAP_LIST, null, this.get_MODUL_IDENTIFIER());

		
		/*
		 * suchfelder definieren
		 */
		HashMap<String, MyE2IF__Component> oHelpHash = oComponentMAP_LIST.get_REAL_ComponentHashMap();
		E2_DataSearch oSearch = new E2_DataSearch("JT_FBAM","ID_FBAM",this.get_MODUL_IDENTIFIER());
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNavigationList);
		oSearch.set_oSearchAgent(oSearchAgent);
		

		oSearch.add_SearchElement((MyE2IF__DB_Component)oHelpHash.get("BUCHUNGSNUMMER"));
		oSearch.add_SearchElement((MyE2IF__DB_Component)oHelpHash.get("FEHLERURSACHE"));
		oSearch.add_SearchElement((MyE2IF__DB_Component)oHelpHash.get("BAM_GRUND"));
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.bam_ort, new MyE2_String("Ort der BAM")));
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.fehlerbeschreibung, new MyE2_String("Fehlerbeschreibung")));
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.fehlerursache, new MyE2_String("Fehlerursache")));
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.feststellung_bam, new MyE2_String("Fehler festgestellt bei")));
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.betreff_bam, new MyE2_String("Fehler betrifft")));
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.auswirkungen, new MyE2_String("Auswirkungen")));
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.beheb_vorschlag, new MyE2_String("Behebung Vorschlag intern")));
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.beheb_vermerk, new MyE2_String("Behebung Vermerk / intern")));
		

		//jetzt eine add-on-bedingung um sicherzustellen, dass in der fuhren-bam keine betriebsbam gefunden wird
		oSearch.add_XX_SearchAddonBedingung_to_all_SearchDefinitions(new XX_SearchAddonBedingung()
		{
			@Override
			public String get_AddOnBedingung() throws myException
			{
				return "(JT_FBAM.ID_VPOS_TPA_FUHRE IS NULL AND JT_FBAM.ID_VPOS_TPA_FUHRE_ORT IS NULL )";
			}
		});
		

		
		
		/*
		 * aufbau der listen-"maske"
		 */
		this.add(new MyE2_Label(new MyE2_String("Betriebs-BAM Liste")), new Insets(2,5,2,10));
		
		E2_ComponentGroupHorizontal oGroupBedienPanel = new E2_ComponentGroupHorizontal(null);
		E2_ComponentGroupHorizontal oGroupSelector = new E2_ComponentGroupHorizontal(null);
		
		this.add(oGroupSelector, new Insets(2,5,2,2));
		this.add(oGroupBedienPanel, new Insets(2,2,2,10));
		this.add(oNavigationList,new Insets(2,5,2,1));
		
		
		/*
		 * den masken-container initialisieren
		 */
		this.oBAM_ModulContainerMASK = new BAMB_MASK_ModulContainer(this.oNavigationList);
		

		/*
		 * selektionen aufbauen
		 */
		E2_SelectionComponentsVector 	oSelVector = new E2_SelectionComponentsVector(this.oNavigationList);
		MyE2_CheckBox 					oCheckBoxAbgeschlossenBehebung = new MyE2_CheckBox(new MyE2_String("BAM nicht behoben"));
		MyE2_CheckBox 					oCheckBoxAbgeschlossenKontrolle = new MyE2_CheckBox(new MyE2_String("BAM nicht kontrolliert"));
		E2_SelektorDateFromTo oSelBereichDatum = new E2_SelektorDateFromTo(new MyE2_String("Datum BAM: "),"JT_FBAM.BAM_DATUM","JT_FBAM.BAM_DATUM");
		oSelVector.add(new E2_ListSelectorStandard(oCheckBoxAbgeschlossenBehebung,"  NVL(JT_FBAM.ABGESCHLOSSEN_BEHEBUNG,'N') ='N'",""));
		oSelVector.add(new E2_ListSelectorStandard(oCheckBoxAbgeschlossenKontrolle,"  NVL(JT_FBAM.ABGESCHLOSSEN_KONTROLLE,'N') ='N'",""));
		oSelVector.add(oSelBereichDatum);
		oCheckBoxAbgeschlossenBehebung.setSelected(true);
		oCheckBoxAbgeschlossenKontrolle.setSelected(false);

	
		/*
		 * jetzt das bedienpanel bestuecken
		 */
		Insets oInset = new Insets(1,1,10,1);
		oGroupBedienPanel.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNavigationList),oInset);
		oGroupBedienPanel.add(new BT_LIST_NEW_BAMB( oNavigationList,this.oBAM_ModulContainerMASK),oInset);
		oGroupBedienPanel.add(new BT_LIST_EDIT_BAMB( oNavigationList,this.oBAM_ModulContainerMASK),oInset);
		oGroupBedienPanel.add(new BT_LIST_VIEW_BAMB( oNavigationList,this.oBAM_ModulContainerMASK),oInset);
		oGroupBedienPanel.add(new BT_LIST_DEL_BAMB( oNavigationList),oInset);
		oGroupBedienPanel.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNavigationList,this.get_MODUL_IDENTIFIER()),oInset);
		oGroupBedienPanel.add(new REP__POPUP_Button(this.get_MODUL_IDENTIFIER(),this.oNavigationList), oInset);
		
		
		//2016-12-20: reminder-buttons
		oGroupBedienPanel.add(new REM_bt_New_Generic(this.oNavigationList, MODUL.NAME_MODUL_BBAM_LIST));
		
		oGroupBedienPanel.add(oSearch,oInset);

		oGroupSelector.add(oCheckBoxAbgeschlossenBehebung,oInset);
		oGroupSelector.add(oCheckBoxAbgeschlossenKontrolle,oInset);
		oGroupSelector.add(oSelBereichDatum.get_oComponentForSelection(),oInset);
		
		/*
		 * hiermit wird auch die liste komplett aufgebaut (mit den eingestellten selektionen)
		 */
		oSelVector.doActionPassiv();

	}
}
