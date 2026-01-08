package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import java.util.HashMap;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_SearchDefinition_NG;
import panter.gmbh.Echo2.ListAndMask.List.Search.XX_SearchAddonBedingung;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectorMultiDropdown_IdUser;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelektorDateFromTo;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SimpleStatus_an_aus;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK.REM_bt_New_Generic;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_ENUMS.FBAM;
import panter.gmbh.basics4project.DB_ENUMS.FBAM_USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_ModulContainer;

public class BAMF_LIST_ModulContainer extends Project_BasicModuleContainer
{
	public static final String HASH_LABEL_COLUMN_BUCHUNG = "BAM_E2_ComponentMAP_LIST-HASH_LABEL_COLUMN_BUCHUNG";
	public static final String HASH_LABEL_COLUMN_GRUND = "BAM_E2_ComponentMAP_LIST-HASH_LABEL_COLUMN_GRUND";
	public static final String HASH_LABEL_COLUMN_FUHRE = "BAM_E2_ComponentMAP_LIST-HASH_LABEL_COLUMN_FUHRE";
	public static final String HASH_LABEL_COLUMN_SORTE = "BAM_E2_ComponentMAP_LIST-HASH_LABEL_COLUMN_SORTE";
	public static final String HASH_LABEL_COLUMN_FESTSTELLUNG = "BAM_E2_ComponentMAP_LIST-HASH_LABEL_COLUMN_FESTSTELLUNG";
	public static final String HASH_LABEL_COLUMN_IDS = "BAM_E2_ComponentMAP_LIST-HASH_LABEL_COLUMN_IDS";
	public static final String NAME_OF_LISTMARKER_IN_LIST =	"BAM_E2_ComponentMAP_LIST-NAME_OF_LISTMARKER_IN_LIST";
	public static final String NAME_OF_CHECKBOX_IN_LIST = 	"BAM_E2_ComponentMAP_LIST-NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_JUMPBUTTON_TO_FUHRE ="BAM_E2_ComponentMAP_LIST-NAME_OF_JUMPBUTTON_TO_FUHRE";

	public static final String NAME_OF_SQL_INFOFIELD	= 	"NAME_OF_SQL_INFOFIELD";
	
	public static final String NAME_OF_SORTE	= 				"NAME_OF_SORTE";
	public static final String NAME_LISTFELD_LIEFERANT	= 	"NAME_LISTFELD_LIEFERANT";
	public static final String NAME_LISTFELD_ABNEHMER	= 		"NAME_LISTFELD_ABNEHMER";
	
	private BAMF_MASK_ModulContainer 	oBAM_ModulContainerMASK = null;
	private E2_NavigationList			oNavigationList = null; 
	private FU_MASK_ModulContainer      oFuhrenMaskContainer = null;
	
	
	public BAMF_LIST_ModulContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_FBAM_LIST);
	
		BAMF_SQLFieldMAP 			oSQLFieldMAP = 		new BAMF_SQLFieldMAP(null,null);
		BAMF_LIST_ComponentMAP 	oComponentMAP_LIST = 	new BAMF_LIST_ComponentMAP(oSQLFieldMAP,this);
		
		this.oFuhrenMaskContainer = new FU_MASK_ModulContainer(null, null,false,true);
		
		this.oNavigationList = new E2_NavigationList();
		this.oNavigationList.set_bSaveSortStatus(true);
		
		oNavigationList.INIT_WITH_ComponentMAP(oComponentMAP_LIST, new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID)), this.get_MODUL_IDENTIFIER());

		/*
		 * suchfelder definieren
		 */
		HashMap<String, MyE2IF__Component> oHelpHash = oComponentMAP_LIST.get_REAL_ComponentHashMap();
		E2_DataSearch oSearch = new E2_DataSearch("JT_FBAM","ID_FBAM",this.get_MODUL_IDENTIFIER());
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNavigationList);
		oSearch.set_oSearchAgent(oSearchAgent);
		
		
		 
//		String cQuery1 = "SELECT JT_FBAM.ID_FBAM FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE,"+bibE2.cTO()+".JT_FBAM WHERE " +
//				"JT_FBAM.ID_VPOS_TPA_FUHRE = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE AND upper(JT_VPOS_TPA_FUHRE.L_NAME1) like upper('%#WERT#%')";
//		String cQuery2 = "SELECT JT_FBAM.ID_FBAM FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE,"+bibE2.cTO()+".JT_FBAM WHERE " +
//				"JT_FBAM.ID_VPOS_TPA_FUHRE = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE AND upper(JT_VPOS_TPA_FUHRE.A_NAME1) like upper('%#WERT#%')";
		
		//2013-04-22: neue abfrage nach lieferant und abnehmer
		String Subquery_lieferant = "(CASE "+
				" WHEN JT_FBAM.ID_VPOS_TPA_FUHRE IS NOT NULL THEN "+
				"    (SELECT NVL(AD.NAME1,'<name1>')||',  '||NVL(AD.ORT,'<ort>') "+
				"         FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE  FU  "+
				"         INNER JOIN "+bibE2.cTO()+".JT_ADRESSE AD ON (FU.ID_ADRESSE_START=AD.ID_ADRESSE) WHERE FU.ID_VPOS_TPA_FUHRE=JT_FBAM.ID_VPOS_TPA_FUHRE) "+
				" "+
				" WHEN JT_FBAM.ID_VPOS_TPA_FUHRE_ORT IS NOT NULL THEN "+
				"    ( "+
				"    CASE WHEN (SELECT NVL(FO.DEF_QUELLE_ZIEL,'EK') FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FO WHERE FO.ID_VPOS_TPA_FUHRE_ORT=JT_FBAM.ID_VPOS_TPA_FUHRE_ORT)='EK' THEN "+
				"        (SELECT NVL(AD.NAME1,'<name1>')||',  '||NVL(AD.ORT,'<ort>')  "+
				"             FROM  "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO "+
				"             INNER JOIN "+bibE2.cTO()+".JT_ADRESSE AD ON (FUO.ID_ADRESSE=AD.ID_ADRESSE) WHERE FUO.ID_VPOS_TPA_FUHRE_ORT=JT_FBAM.ID_VPOS_TPA_FUHRE_ORT) "+
				"     ELSE "+
				"        (SELECT NVL(AD.NAME1,'<name1>')||',  '||NVL(AD.ORT,'<ort>')  "+
				"             FROM  "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO "+
				"             INNER JOIN "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU ON (FU.ID_VPOS_TPA_FUHRE=FUO.ID_VPOS_TPA_FUHRE) "+
				"             INNER JOIN "+bibE2.cTO()+".JT_ADRESSE AD ON (FU.ID_ADRESSE_START=AD.ID_ADRESSE) WHERE FUO.ID_VPOS_TPA_FUHRE_ORT=JT_FBAM.ID_VPOS_TPA_FUHRE_ORT) "+
				"     END   "+
				"    ) "+
				" ELSE "+
				"   null "+
				" END)";


		String Subquery_Abnehmer = "(CASE "+
				" WHEN JT_FBAM.ID_VPOS_TPA_FUHRE IS NOT NULL THEN "+
				"    (SELECT NVL(AD.NAME1,'<name1>')||',  '||NVL(AD.ORT,'<ort>') "+
				"         FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE  FU  "+
				"         INNER JOIN "+bibE2.cTO()+".JT_ADRESSE AD ON (FU.ID_ADRESSE_ZIEL=AD.ID_ADRESSE) WHERE FU.ID_VPOS_TPA_FUHRE=JT_FBAM.ID_VPOS_TPA_FUHRE) "+
				" "+
				" WHEN JT_FBAM.ID_VPOS_TPA_FUHRE_ORT IS NOT NULL THEN "+
				"    ( "+
				"    CASE WHEN (SELECT NVL(FO.DEF_QUELLE_ZIEL,'EK') FROM JT_VPOS_TPA_FUHRE_ORT FO WHERE FO.ID_VPOS_TPA_FUHRE_ORT=JT_FBAM.ID_VPOS_TPA_FUHRE_ORT)='VK' THEN "+
				"        (SELECT NVL(AD.NAME1,'<name1>')||',  '||NVL(AD.ORT,'<ort>') "+
				"             FROM  "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO "+
				"             INNER JOIN "+bibE2.cTO()+".JT_ADRESSE AD ON (FUO.ID_ADRESSE=AD.ID_ADRESSE) WHERE FUO.ID_VPOS_TPA_FUHRE_ORT=JT_FBAM.ID_VPOS_TPA_FUHRE_ORT) "+
				"     ELSE "+
				"        (SELECT NVL(AD.NAME1,'<name1>')||',  '||NVL(AD.ORT,'<ort>') "+
				"             FROM  "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO "+
				"             INNER JOIN "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU ON (FU.ID_VPOS_TPA_FUHRE=FUO.ID_VPOS_TPA_FUHRE) "+
				"             INNER JOIN "+bibE2.cTO()+".JT_ADRESSE AD ON (FU.ID_ADRESSE_ZIEL=AD.ID_ADRESSE) WHERE FUO.ID_VPOS_TPA_FUHRE_ORT=JT_FBAM.ID_VPOS_TPA_FUHRE_ORT) "+
				"     END "+
				"    ) "+
				" ELSE "+
				"   null "+
				" END)";

		String cSubquery_gesamt = "SELECT JT_FBAM.ID_FBAM,"+Subquery_lieferant+" AS LIEFERANT,"+Subquery_Abnehmer+" AS ABNEHMER  FROM "+bibE2.cTO()+".JT_FBAM ";
		cSubquery_gesamt = "SELECT JT_FBAM.ID_FBAM FROM JT_FBAM WHERE JT_FBAM.ID_FBAM IN (SELECT ID_FBAM FROM ("+cSubquery_gesamt+") WHERE UPPER(#LIEF_ABN#) LIKE UPPER('%#WERT#%'))";

		String cQuery1 = bibALL.ReplaceTeilString(cSubquery_gesamt,"#LIEF_ABN#","LIEFERANT");
		String cQuery2 = bibALL.ReplaceTeilString(cSubquery_gesamt,"#LIEF_ABN#","ABNEHMER");
		
		
		String cQuery3 = "SELECT JT_FBAM.ID_FBAM FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE,"+bibE2.cTO()+".JT_FBAM WHERE " +
				"JT_FBAM.ID_VPOS_TPA_FUHRE = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE AND upper(JT_VPOS_TPA_FUHRE.TRANSPORTKENNZEICHEN) like upper('%#WERT#%')";
		String cQuery4 = "SELECT JT_FBAM.ID_FBAM FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE,"+bibE2.cTO()+".JT_FBAM WHERE " +
				"JT_FBAM.ID_VPOS_TPA_FUHRE = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE AND upper(JT_VPOS_TPA_FUHRE.ANHAENGERKENNZEICHEN) like upper('%#WERT#%')";

		String cQuery5 = "SELECT JT_FBAM.ID_FBAM FROM "+bibE2.cTO()+".JT_ARTIKEL,"+bibE2.cTO()+".JT_VPOS_TPA_FUHRE,"+bibE2.cTO()+".JT_FBAM WHERE " +
				"JT_FBAM.ID_VPOS_TPA_FUHRE = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE AND " +
				"JT_VPOS_TPA_FUHRE.ID_ARTIKEL = JT_ARTIKEL.ID_ARTIKEL AND JT_ARTIKEL.ANR1 = '#WERT#'";

		String cQuery6 = "SELECT JT_FBAM.ID_FBAM FROM "+bibE2.cTO()+".JT_ARTIKEL,"+bibE2.cTO()+".JT_VPOS_TPA_FUHRE,"+bibE2.cTO()+".JT_FBAM WHERE " +
				"JT_FBAM.ID_VPOS_TPA_FUHRE = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE AND " +
				"JT_VPOS_TPA_FUHRE.ID_ARTIKEL = JT_ARTIKEL.ID_ARTIKEL AND upper(JT_ARTIKEL.ARTBEZ1) like upper('%#WERT#%')";

		String cQuery7 = "SELECT JT_FBAM.ID_FBAM FROM "+bibE2.cTO()+".JT_FBAM,"+bibE2.cTO()+".JT_VPOS_TPA_FUHRE,"+bibE2.cTO()+".JT_VPOS_TPA,"+bibE2.cTO()+".JT_VKOPF_TPA WHERE " +
				" JT_FBAM.ID_VPOS_TPA_FUHRE = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE AND JT_VPOS_TPA_FUHRE.ID_VPOS_TPA = JT_VPOS_TPA.ID_VPOS_TPA " +
				" AND JT_VPOS_TPA.ID_VKOPF_TPA = JT_VKOPF_TPA.ID_VKOPF_TPA " +
				" AND  upper(JT_VKOPF_TPA.BUCHUNGSNUMMER) like upper('%#WERT#%')";
		
		//ZEITSTEMPEL
		String cQuery10 = "SELECT JT_FBAM.ID_FBAM FROM "+bibE2.cTO()+".JT_FBAM,"+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE " +
						" JT_FBAM.ID_VPOS_TPA_FUHRE = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE "+
						" AND  upper(JT_VPOS_TPA_FUHRE.BUCHUNGSNR_FUHRE) like upper('%#WERT#%')";


		String cQuery11 = "SELECT JT_FBAM.ID_FBAM FROM "+bibE2.cTO()+".JT_FBAM  WHERE " +
							" TO_CHAR(JT_FBAM.ID_VPOS_TPA_FUHRE) = '#WERT#' "+
							" UNION "+
						   "SELECT JT_FBAM.ID_FBAM FROM "+bibE2.cTO()+".JT_FBAM WHERE " +
						    " JT_FBAM.ID_VPOS_TPA_FUHRE_ORT IN " +
						    " (SELECT FUO.ID_VPOS_TPA_FUHRE_ORT FROM " +bibE2.cTO()+ ".JT_VPOS_TPA_FUHRE_ORT FUO " +
						    " WHERE TO_CHAR(FUO.ID_VPOS_TPA_FUHRE)='#WERT#')";	
							

		String cQuery12 = "SELECT JT_FBAM.ID_FBAM FROM "+bibE2.cTO()+".JT_FBAM  WHERE " +
							" TO_CHAR(JT_FBAM.ID_FBAM) = '#WERT#' ";

		
		
		oSearch.add_SearchElement((MyE2IF__DB_Component)oHelpHash.get("BUCHUNGSNUMMER"));
		oSearch.add_SearchElement((MyE2IF__DB_Component)oHelpHash.get("FEHLERURSACHE"));
		oSearch.add_SearchElement((MyE2IF__DB_Component)oHelpHash.get("BAM_GRUND"));
		oSearch.add_SearchElement(cQuery1,new MyE2_String("Lieferant Name 1"));
		oSearch.add_SearchElement(cQuery2,new MyE2_String("Abnehmer Name 1"));
		oSearch.add_SearchElement(cQuery3,new MyE2_String("Transport/LKW-Kennzeichen"));
		oSearch.add_SearchElement(cQuery4,new MyE2_String("Anhänger-Kennzeichen"));
		oSearch.add_SearchElement(cQuery5,new MyE2_String("Sorte-Nr"));
		oSearch.add_SearchElement(cQuery6,new MyE2_String("Artikelbezeichung 1"));
		oSearch.add_SearchElement(cQuery7,new MyE2_String("Buchungsnummer TPA"));
		oSearch.add_SearchElement(cQuery10,new MyE2_String("Buchungsnummer Fuhre"));
		oSearch.add_SearchElement(cQuery11,new MyE2_String("ID-Fuhre"));
		oSearch.add_SearchElement(cQuery12,new MyE2_String("ID-FBAM"));

		
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.bam_ort, new MyE2_String("Ort der BAM")));
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.fehlerbeschreibung, new MyE2_String("Fehlerbeschreibung/intern")));
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.auswirkungen, new MyE2_String("Auswirkungen/intern")));
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.fehlerursache, new MyE2_String("Fehlerursache")));
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.feststellung_bam, new MyE2_String("Fehler festgestellt bei")));
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.betreff_bam, new MyE2_String("Fehler betrifft")));
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.beheb_vorschlag, new MyE2_String("Behebung Vorschlag intern")));
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.beheb_vermerk, new MyE2_String("Behebung Vermerk / intern")));

		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.wm_artbez1, new MyE2_String("Sorte der Lieferung")));
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.wm_abnehmer, new MyE2_String("Geweigert von")));
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.wm_ansprechpartner_inhouse, new MyE2_String("Ansprechpartner Inhouse")));
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.wm_ansprechpartner_lieferant, new MyE2_String("Ansprechpartner Lieferant")));

		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.fehlerinfo_extern, new MyE2_String("Fehlerbeschreibung / Weigermeldung")));
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.wm_uebernahmevorschlag, new MyE2_String("Übernahme-Vorschl./ Weigermeldung")));
		oSearch.add_SearchElement(new E2_SearchDefinition_NG(FBAM.wm_ort, new MyE2_String("Weigermeldung Ort")));
		
		
		//jetzt eine add-on-bedingung um sicherzustellen, dass in der fuhren-bam keine betriebsbam gefunden wird
		oSearch.add_XX_SearchAddonBedingung_to_all_SearchDefinitions(new XX_SearchAddonBedingung()
		{
			@Override
			public String get_AddOnBedingung() throws myException
			{
				return "(JT_FBAM.ID_VPOS_TPA_FUHRE IS NOT NULL OR JT_FBAM.ID_VPOS_TPA_FUHRE_ORT IS NOT NULL )";
			}
		});
		
		
		/*
		 * aufbau der listen-"maske"
		 */
		this.add(new MyE2_Label(new MyE2_String("Transport-Position-BAM Liste")), new Insets(2,5,2,10));
		
		E2_ComponentGroupHorizontal oGroupBedienPanel = new E2_ComponentGroupHorizontal(null);
//		E2_ComponentGroupHorizontal oGroupSelector = new E2_ComponentGroupHorizontal(0,E2_INSETS.I_0_5_5_5);
		
		E2_ListSelectorContainer  oListSelektor = new E2_ListSelectorContainer();
//		oListSelektor.add(oGroupSelector,E2_INSETS.I_0_0_0_0);
		
		
		this.add(oListSelektor, new Insets(2,5,2,2));
		this.add(oGroupBedienPanel, new Insets(2,2,2,10));
		this.add(oNavigationList,new Insets(2,5,2,1));
		
		
		/*
		 * den masken-container initialisieren
		 */
		this.oBAM_ModulContainerMASK = new BAMF_MASK_ModulContainer(this.oNavigationList,new BAMF_SQLFieldMAP(null,null));

		
		/* 
		 * selektoren definieren
		 */
		/*
		 * selektionen aufbauen
		 */
		MyE2_CheckBox 					oCheckBoxAbgeschlossenBehebung = new MyE2_CheckBox(new MyE2_String("BAM nicht behoben"));
		MyE2_CheckBox 					oCheckBoxAbgeschlossenKontrolle = new MyE2_CheckBox(new MyE2_String("BAM nicht kontrolliert"));
		E2_SelektorDateFromTo oSelBereichDatum = new E2_SelektorDateFromTo(new MyE2_String("Datum BAM: "),"JT_FBAM.BAM_DATUM","JT_FBAM.BAM_DATUM");
		oCheckBoxAbgeschlossenBehebung.setSelected(true);
		oCheckBoxAbgeschlossenKontrolle.setSelected(false);

		
		//2019-12-27: neue selektoren
		E2_SimpleStatus_an_aus selBearbeitetYesNo = new E2_SimpleStatus_an_aus( FBAM.bearbeitung.tnfn()
																					  ,"Ja" 
																					  ,"Nein"
																					  ,"Auswahl der BAMs, die bearbeitet sind"
																					  ,"Auswahl der BAMs, die noch nicht bearbeitet sind"
																					  ,"BAM ist bearbeitet", true, true, 60);

		E2_SimpleStatus_an_aus selAbgeschlossenBehebungYesNo = new E2_SimpleStatus_an_aus( FBAM.abgeschlossen_behebung.tnfn()
																					  ,"Ja" 
																					  ,"Nein"
																					  ,"Auswahl der BAMs, die bereits behoben sind"
																					  ,"Auswahl der BAMs, die noch nicht behoben sind"
																					  ,"BAM ist behoben", true, true, 60);
		
		E2_SimpleStatus_an_aus selAbgeschlossenKontrolleYesNo = new E2_SimpleStatus_an_aus( FBAM.abgeschlossen_kontrolle.tnfn()
																					  ,"Ja" 
																					  ,"Nein"
																					  ,"Auswahl der BAMs, die bereits kontrolliert sind"
																					  ,"Auswahl der BAMs, die noch nicht kontrolliert sind"
																					  ,"BAM ist kontrolliert", false, true, 60);
		
		E2_SelectorMultiDropdown_IdUser  selectorIdUserAusstellung = 	new E2_SelectorMultiDropdown_IdUser(FBAM.id_user_ausstellung, 	"Aussteller",170,100);
		E2_SelectorMultiDropdown_IdUser  selectorIdUserBearbeitung = 	new E2_SelectorMultiDropdown_IdUser(FBAM.id_user_bearbeitung, 	"Bearbeiter",170,100);
		E2_SelectorMultiDropdown_IdUser  selectorIdUserBehebung = 		new E2_SelectorMultiDropdown_IdUser(FBAM.id_user_behebung, 		"Behoben von",170,100);
		E2_SelectorMultiDropdown_IdUser  selectorIdUserKontrolle = 		new E2_SelectorMultiDropdown_IdUser(FBAM.id_user_kontrolle, 	"Kontrolleur",170,100);
		
		OwnSelectorUserVerteiler         selectorIdUserVerteiler =      new OwnSelectorUserVerteiler("Verteiler",170,80);
		
		
		E2_SelectionComponentsVector 	oSelVector = new E2_SelectionComponentsVector(this.oNavigationList);
//		oSelVector.add(new E2_ListSelectorStandard(oCheckBoxAbgeschlossenBehebung,"  NVL(JT_FBAM.ABGESCHLOSSEN_BEHEBUNG,'N') ='N'",""));
//		oSelVector.add(new E2_ListSelectorStandard(oCheckBoxAbgeschlossenKontrolle,"  NVL(JT_FBAM.ABGESCHLOSSEN_KONTROLLE,'N') ='N'",""));
		oSelVector.add(selBearbeitetYesNo);
		oSelVector.add(selAbgeschlossenBehebungYesNo);
		oSelVector.add(selAbgeschlossenKontrolleYesNo);
		oSelVector.add(oSelBereichDatum);

		oSelVector.add(selectorIdUserAusstellung);
		oSelVector.add(selectorIdUserBearbeitung);
		oSelVector.add(selectorIdUserBehebung);
		oSelVector.add(selectorIdUserKontrolle);
		
		oSelVector.add(selectorIdUserVerteiler);
		
		
		/*
		 * jetzt das bedienpanel bestuecken
		 */
		Insets oInset = new Insets(1,5,20,5);
		oGroupBedienPanel.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNavigationList),oInset);
		oGroupBedienPanel.add(new BT_LIST_Edit_BAMF( oNavigationList,this.oBAM_ModulContainerMASK),oInset);
		oGroupBedienPanel.add(new BT_LIST_VIEW_BAMF( oNavigationList,this.oBAM_ModulContainerMASK),oInset);
		oGroupBedienPanel.add(new BT_LIST_DEL_BAMF( oNavigationList),oInset);
		oGroupBedienPanel.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNavigationList,this.get_MODUL_IDENTIFIER()),oInset);

		oGroupBedienPanel.add(new REP__POPUP_Button(this.get_MODUL_IDENTIFIER(),this.oNavigationList),oInset);
		
		//2016-12-20: reminder-buttons
		oGroupBedienPanel.add(new REM_bt_New_Generic(this.oNavigationList, MODUL.NAME_MODUL_FBAM_LIST));

		
		oGroupBedienPanel.add(oSearch,oInset);

		
		E2_Grid selectorGrid = new E2_Grid()._setSize(130,130,130,300,300,300)._bo_dd();
		selectorGrid._a(selBearbeitetYesNo.get_oComponentForSelection(),			new RB_gld()._ins(4, 4, 4, 2));
		selectorGrid._a(selAbgeschlossenBehebungYesNo.get_oComponentForSelection(),	new RB_gld()._ins(4, 4, 4, 2));
		selectorGrid._a(selAbgeschlossenKontrolleYesNo.get_oComponentForSelection(),new RB_gld()._ins(4, 4, 4, 2));
		
		selectorGrid._a(new E2_Grid()._setSize(200)	._a(selectorIdUserAusstellung.get_oComponentForSelection())
													._a(selectorIdUserBearbeitung.get_oComponentForSelection())
													,new RB_gld()._ins(4, 2, 4, 2));
		selectorGrid._a(new E2_Grid()._setSize(200)	._a(selectorIdUserBehebung.get_oComponentForSelection())
													._a(selectorIdUserKontrolle.get_oComponentForSelection())
													,new RB_gld()._ins(4, 2, 4, 2));
		
		selectorGrid._a(new E2_Grid()._setSize(200)	._a(oSelBereichDatum.get_oComponentForSelection())
													._a(selectorIdUserVerteiler.get_oComponentForSelection())
													,new RB_gld()._ins(4, 2, 4, 2));
		
		
		oListSelektor.add(selectorGrid);
		
//		oGroupSelector.add(oCheckBoxAbgeschlossenBehebung,oInset);
//		oGroupSelector.add(oCheckBoxAbgeschlossenKontrolle,oInset);
//		oGroupSelector.add(oSelBereichDatum.get_oComponentForSelection(),oInset);
		
		/*
		 * hiermit wird auch die liste komplett aufgebaut (mit den eingestellten selektionen)
		 */
		oSelVector.doActionPassiv();
		
		
		
	}


	public BAMF_MASK_ModulContainer get_oBAM_ModulContainerMASK()
	{
		return oBAM_ModulContainerMASK;
	}


	public E2_NavigationList get_oNavigationList()
	{
		return oNavigationList;
	}


	public FU_MASK_ModulContainer get_oFuhrenMaskContainer()
	{
		return oFuhrenMaskContainer;
	}
	
	
//	private void addSearchDef(E2_DataSearch oSearch, String cFieldName,String cInfoText, boolean bNumber) throws myException
//	{
//		String cSearch = "";
//		if (bNumber)
//			cSearch = "SELECT JT_FBAM.ID_FBAM FROM "+bibE2.cTO()+".JT_FBAM WHERE TO_CHAR(JT_FBAM."+cFieldName+")='#WERT#'";
//		else
//			cSearch = "SELECT JT_FBAM.ID_FBAM FROM "+bibE2.cTO()+".JT_FBAM WHERE UPPER(JT_FBAM."+cFieldName+") like upper('%#WERT#%')";
//		
//		oSearch.add_SearchElement(cSearch,new MyE2_String(cInfoText));
//	}

	
	
	private class OwnSelectorUserVerteiler extends E2_SelectorMultiDropdown_IdUser {

		/**
		 * @author martin
		 * @date 30.12.2019
		 *
		 * @param fieldUser
		 * @param p_feldBenennung
		 * @param selectFieldWidth
		 * @param anzeigeLabelWidth
		 * @throws myException
		 */
		public OwnSelectorUserVerteiler(String p_feldBenennung, int selectFieldWidth,int anzeigeLabelWidth) throws myException {
			super( p_feldBenennung, selectFieldWidth, anzeigeLabelWidth);
		}

		@Override
		public String get_WhereBlock() throws myException {
			String cWhere = "";
			
			VEK<Long> ids = new VEK<>();
			
			if (this.getSelectFieldsZusatzWerte().size()==0) {
				MyLong basis = new MyLong(S.NN(this.get_oSelFieldBasis().get_ActualWert(),"-")); 
				if (basis.isOK()) {
					ids._a(basis.getLong());
				}
			} else {
				for (String cWert:this.getSelectFieldsZusatzWerte()) {
					MyLong basis = new MyLong(S.NN(cWert,"-")); 
					if (basis.isOK()) {
						ids._a(basis.getLong());
					}
				}
			}
			
			if (ids.size()==0) {
				cWhere = ("1=1");
			} else {
				String inBlock = "";
				for (Long l: ids) {
					inBlock = inBlock+","+l.toString();
				}
				inBlock = "("+inBlock.substring(1)+")";    //das erste komma weg
				
				SEL subSel = new SEL(FBAM_USER.id_fbam).FROM(_TAB.fbam_user).WHERE(new TermSimple(FBAM_USER.id_user.tnfn()+" IN "+inBlock)); 
				cWhere  = "("+FBAM.id_fbam.tnfn()+" IN ("+subSel.s()+"))"; 
			}
			
			return cWhere;
		}
		
		
	}
	
	
	
	
}
