package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V2;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObject;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_StandardLayoutFactory;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Column_IMMER_ENABLED;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentGrid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyDropDownSettings;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.ENUM_BEWGUNGSATZ_TYP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.AbrechnungDienstleistung.ADL__ListComponentFuhre;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.InfoAbrechnung.FU_PruefeUeberschreitungZahlungsDatum;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.BSK_P_MASK__ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.BST_K_MASK__ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN_NG.FK_MASK_bt_MULTI_FUHREN_KOSTEN;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FUO__CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.Routen.FU_LIST_btRoutingEntfernungUndKosten;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.Routen.FU_LIST_bt_show_routing;
import rohstoff.Echo2BusinessLogic.DienstleistungsProfile.DL_READABLE_FIELD_NAME;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE.WK_LIST_BT_CONNECT_FUHRE_to_WK;
import rohstoff.Echo2BusinessLogic._4_ALL.DB_BUTTON_FIRMA_WITH_INFO;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.__FU_BT_SucheHandelsDef_Zu_Fuhre;

public class FU_LIST_ComponentMAP extends E2_ComponentMAP_V2
{
	



	public FU_LIST_ComponentMAP(SQLFieldMAP oSQLFieldMAP, FU_LIST_ModulContainer oModulcontainerLIST, String BASIC_MODULE_NAME, BST_K_MASK__ModulContainer  oBasicModuleContainerTPA) throws myException
	{
		super(oSQLFieldMAP);
		
		this.set_oNavigationList_This_Belongs_to(oModulcontainerLIST.get_oNavList());
		
		/*
		 * multicolumns
		 */
		MyE2_DB_MultiComponentColumn 	oMulti_LIEFERANT = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn 	oMulti_ABNEHMER = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn 	oMulti_SPEDITEUR = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn 	oMulti_MengenGesamt = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn 	oMulti_MengenEingabeEK = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn 	oMulti_MengenEingabeVK = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn 	oMulti_Sorte = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn 	oMulti_Datum = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn 	oMulti_BuchungsNummern = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn 	oMulti_KFZNummern = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn 	oMulti_LieferAbholDat = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentGrid 		oMulti_GridIcons = new MyE2_DB_MultiComponentGrid(2);
		MyE2_DB_MultiComponentColumn 	oMulti_ColGrueneButtons = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn 	oMulti_ID_Block = new MyE2_DB_MultiComponentColumn();
		
		MyE2_DB_MultiComponentColumn 	oMulti_Preis_Steuer_EK = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn 	oMulti_Preis_Steuer_VK = new MyE2_DB_MultiComponentColumn();

		//2011-08-19: anzahlcontainer in eine eigene spalte
		MyE2_DB_MultiComponentGrid 	    oMulti_Grid_diverse = new MyE2_DB_MultiComponentGrid(1);
		oMulti_Grid_diverse.EXT().set_oLayout_ListElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutCenterTOP());
		
		
		//2011-11-18: postennummer
		MyE2_DB_MultiComponentGrid 	    oMulti_Grid_Nummern = new MyE2_DB_MultiComponentGrid(1);
		
		
		//2014-06-03: lieferbedingungen
		MyE2_DB_MultiComponentGrid 	    oMulti_Grid_LieferBed = new MyE2_DB_MultiComponentGrid(1);
		
		/*
		 * gruppen fuer die fahrplan-spalten
		 */
		MyE2_DB_MultiComponentColumn oColLKW = 			new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn oColCodes = 		new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn oColContainer = 	new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn oColInfos = 		new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn oColTaetigkeit = 	new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn oColZeiten = 		new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn oColAnrufer_dat = 	new MyE2_DB_MultiComponentColumn();
		
		
		MyE2_Row_EveryTimeEnabled  oRow_4_BAM_Button = new MyE2_Row_EveryTimeEnabled();
		
		
		//zusatzinfo VK ohne kontrakt
		//eine select-field komponente fuer die sonderfaelle, wenn eine fuhre ohne VK-kontrakt ausgeliefert werden soll
		MyDropDownSettings oDD = new MyDropDownSettings(bibE2.cTO(),"JT_VPOS_TPA_FUHRE_SONDER","AUSNAHME","ID_VPOS_TPA_FUHRE_SONDER",null,null,true,"AUSNAHME");
		MyE2_DB_SelectField  oSelAusnahmen = new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_VPOS_TPA_FUHRE_SONDER"),oDD.getDD(),false);
		oSelAusnahmen.setWidth(new Extent(200));
		oSelAusnahmen.EXT().set_bDisabledFromBasic(true);
		
		
		//NEU01
		MyE2_Label		oLabelGefahr1 = new MyE2_Label(E2_ResourceIcon.get_RI("empty10.png"));
		MyE2_Label		oLabelInfoZusatzorte = new MyE2_Label(E2_ResourceIcon.get_RI("empty10.png"));

		MyE2_Column_IMMER_ENABLED		oLabelFahrplan_yes_no_geplant5 = new MyE2_Column_IMMER_ENABLED(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());

		
		//BAM-Button zweispaltig
		GridLayoutData oGL = new GridLayoutData();
		oGL.setInsets(E2_INSETS.I_0_0_0_0);
		
		
		
		oMulti_GridIcons.add_Component(oRow_4_BAM_Button,new MyE2_String("BAM ?"),FU___CONST.HASH_KEY_LISTE_BAM_AN_FUHRE,oGL);
		oMulti_GridIcons.add_Component(new FU__ListButton_Edit_IN_LIST_FUHRE(),new MyE2_String("-"),FU___CONST.HASH_KEY_LISTE_DIREKT_EDIT,oGL);
		
		oMulti_GridIcons.add_Component(new FU_LIST_BT_OpenTPA(oSQLFieldMAP.get_("ID_VKOPF_TPA"),oBasicModuleContainerTPA),new MyE2_String("Frei?"), null,oGL);
		oMulti_GridIcons.add_Component(oLabelGefahr1,new MyE2_String(""), FU_LIST_ModulContainer.NAME_OF_ICON_GEFAHR_1,oGL);
		
		oMulti_GridIcons.add_Component(new MyE2_Label(E2_ResourceIcon.get_RI("listlabel_empty.png")),new MyE2_String("Rech ?"), FU_LIST_ModulContainer.NAME_OF_ICON_RECHNUNGEINGANG,oGL);
		oMulti_GridIcons.add_Component(oLabelInfoZusatzorte,new MyE2_String(""), FU_LIST_ModulContainer.NAME_OF_ICON_Zusatzorte,oGL);
		
		oMulti_GridIcons.add_Component(new MyE2_Column_IMMER_ENABLED(MyE2_Column.STYLE_NO_BORDER_NO_INSETS()),new MyE2_String("Buch ?"), FU_LIST_ModulContainer.NAME_OF_ICON_BUCHUNG_FERTIG,oGL);
		oMulti_GridIcons.add_Component(new MyE2_Column_IMMER_ENABLED(MyE2_Column.STYLE_NO_BORDER_NO_INSETS()),new MyE2_String(""), FU_LIST_ModulContainer.NAME_OF_SORTBUTTON,oGL);
		
		/*
		 * 20181127: steuer-zauberstab auf wunsch ausblenden (fuer nicht gf
		 */
		MyE2IF__Component compSteuerTest =  new __FU_BT_SucheHandelsDef_Zu_Fuhre();
		boolean           ausblenden = 		ENUM_MANDANT_DECISION.ZAUBERSTAB_STEUERREGEL_TEST_AUSBLENDEN.is_YES_FromSession() 
											&& (!(bibSES.get_RECORD_USER().is_DEVELOPER_YES() || bibSES.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES()));
		if (ausblenden) {
			compSteuerTest = new MyE2_Label(E2_ResourceIcon.get_RI("listlabel_empty.png"));
		}
		
		
		oMulti_GridIcons.add_Component(compSteuerTest,new MyE2_String(ausblenden?"-":"HD"), FU_LIST_ModulContainer.NAME_OF_ICON_CHECK_HANDELSDEF,oGL);
		oMulti_GridIcons.add_Component(oLabelFahrplan_yes_no_geplant5,new MyE2_String(""), FU_LIST_ModulContainer.NAME_OF_ICON_FAHRPLAN_YES_NO_GEPLANT,oGL);

	
		
		
		//statusblock
		oMulti_ColGrueneButtons.add_Component(new MyE2_Label(E2_ResourceIcon.get_RI("listlabel_trans.png")),new MyE2_String("Pl"), FU_LIST_ModulContainer.NAME_OF_ICON_PLANMENGE);
		oMulti_ColGrueneButtons.add_Component(new MyE2_Label(E2_ResourceIcon.get_RI("listlabel_trans.png")),new MyE2_String("La"), FU_LIST_ModulContainer.NAME_OF_ICON_LADEMENGE);
		oMulti_ColGrueneButtons.add_Component(new MyE2_Label(E2_ResourceIcon.get_RI("listlabel_trans.png")),new MyE2_String("Ab"), FU_LIST_ModulContainer.NAME_OF_ICON_ABLADEMENGE);
		oMulti_ColGrueneButtons.add_Component(new MyE2_Label(E2_ResourceIcon.get_RI("listlabel_trans.png")),new MyE2_String("AB"), FU_LIST_ModulContainer.NAME_OF_ICON_FERTIGBUCHUNG);

		//und der block zum in die freienpositionen springen
		oMulti_ColGrueneButtons.add_Component(new MyE2_Column_IMMER_ENABLED(),new MyE2_String("FP"), FU_LIST_ModulContainer.NAME_OF_BUTTON_SPRINGE_EX_FUHRE);

		//2011-03-01: neuer popup-button mit infofeld
		//oMulti_LIEFERANT.add_Component(new FU_LIST_BT_ED_IN_LIST_ADRESS_LIEFERANT(oSQLFieldMAP.get_("L_NAME1")),new MyE2_String("Abholadr.: Name1"), null);
		oMulti_LIEFERANT.add_Component(new DB_BUTTON_FIRMA_WITH_INFO(oSQLFieldMAP.get_("INFO_ID_ADRESSE_START"),oModulcontainerLIST.get_oFirmenMask(),"L_NAME1","JT_VPOS_TPA_FUHRE.L_NAME1 ASC","JT_VPOS_TPA_FUHRE.L_NAME1 DESC",200,100),new MyE2_String("Abholadr.: Name1"), null);
		oMulti_LIEFERANT.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("L_ORT")),new MyE2_String("Abholadr.: Ort"), null);
		oMulti_LIEFERANT.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("TEL_LIEFERANT")),new MyE2_String("Abholadr.: Telefon"), null);
		oMulti_LIEFERANT.add_Component(new MyE2_Label("",MyE2_Label.STYLE_SMALL_ITALIC()),new MyE2_String("Firma"), "RECHADRESSE_LIEFERANT");
		//2011-09-06: neues zusammengesetztes feld um anr1_ek und anr1_vk anzeigen zu koennen
		//oMulti_LIEFERANT.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("ARTBEZ1_EK"),true),new MyE2_String("EK-Artikelbez."), null);
		oMulti_LIEFERANT.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("ANR1_ANR2_ARTBEZ1_EK"),true),new MyE2_String("EK-Artikelbez."), null);

		//2011-03-01: neuer popup-button mit infofeld
		//oMulti_ABNEHMER.add_Component(new FU_LIST_BT_ED_IN_LIST_ADRESS_ABNEHMER(oSQLFieldMAP.get_("A_NAME1")),new MyE2_String("Lieferadr.: Name1"), null);
		oMulti_ABNEHMER.add_Component(new DB_BUTTON_FIRMA_WITH_INFO(oSQLFieldMAP.get_("INFO_ID_ADRESSE_ZIEL"),oModulcontainerLIST.get_oFirmenMask(),"A_NAME1","JT_VPOS_TPA_FUHRE.A_NAME1 ASC","JT_VPOS_TPA_FUHRE.A_NAME1 DESC",200,100),new MyE2_String("Lieferadr.: Name1"), null);
		oMulti_ABNEHMER.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("A_ORT")),new MyE2_String("Lieferadr.: Ort"), null);
		oMulti_ABNEHMER.add_Component(new MyE2_Label("-"),new MyE2_String(" -- "), "DUMMY_ABNEHMER");
		oMulti_ABNEHMER.add_Component(new MyE2_Label("",MyE2_Label.STYLE_SMALL_ITALIC()),new MyE2_String("Firma"), "RECHADRESSE_ABNEHMER");
		//2011-09-06: neues zusammengesetztes feld um anr1_ek und anr1_vk anzeigen zu koennen
		//oMulti_ABNEHMER.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("ARTBEZ1_VK"),true),new MyE2_String("VK-Artikelbez."), null);
		oMulti_ABNEHMER.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("ANR1_ANR2_ARTBEZ1_VK"),true),new MyE2_String("VK-Artikelbez."), null);


		//2011-03-01: neuer popup-button mit infofeld
		//oMulti_SPEDITEUR.add_Component(new FU_LIST_BT_ED_IN_LIST_ADRESS_SPEDITION(oSQLFieldMAP.get_("NAME1")),new MyE2_String("Spediteur: Name1"), null);
		oMulti_SPEDITEUR.add_Component(new DB_BUTTON_FIRMA_WITH_INFO(oSQLFieldMAP.get_("INFO_ID_ADRESSE_SPEDITION"),oModulcontainerLIST.get_oFirmenMask(),"NAME1","JT_VKOPF_TPA.NAME1 ASC","JT_VKOPF_TPA.NAME1 DESC",200,100),new MyE2_String("Spediteur: Name1"), null);
		oMulti_SPEDITEUR.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("ORT")),new MyE2_String("Spediteur: Ort"), null);
		oMulti_SPEDITEUR.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("TELEFON_AUF_FORMULAR")),new MyE2_String("Spediteur: Telefon"), null);
		
		oMulti_Sorte.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("ANR1")),new MyE2_String("ANR1"), null);
		oMulti_Sorte.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("ARTBEZ1"),true),new MyE2_String("Artikelbez.1"), null);
		oMulti_Sorte.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("ERZEUGT_VON"), MyE2_Button.StyleTextButton_LOOK_like_LABEL_BoldItalic()),new MyE2_String("Erzeugt von"), null);
		oMulti_Sorte.add_Component(oSelAusnahmen,new MyE2_String("Sonderdefinition"), null);
		oMulti_Sorte.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("ZOLLTARIFNR"),true),new MyE2_String("Zolltarif-Nr."), null);
		
		
		oMulti_BuchungsNummern.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("BUCHUNGSNUMMER")),new MyE2_String("TPA"), null);
		oMulti_BuchungsNummern.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("BUCHUNGSNR_FUHRE")),new MyE2_String("Buch.Nr Fuhre"), null);
		
		//hier werden zwei kontrakt-positionsmasken benoetigt fuer die open-buttons
		BSK_P_MASK__ModulContainer oMaskVPOS_KON_EK = new BSK_P_MASK__ModulContainer(new BS__SETTING(myCONST.VORGANGSART_EK_KONTRAKT));
		BSK_P_MASK__ModulContainer oMaskVPOS_KON_VK = new BSK_P_MASK__ModulContainer(new BS__SETTING(myCONST.VORGANGSART_VK_KONTRAKT));
		
		oMulti_BuchungsNummern.add_Component(new FU_LIST_BT_OPEN_KONTRAKT_POS(oSQLFieldMAP.get_("ID_VPOS_KON_EK"),oMaskVPOS_KON_EK,"EK"),new MyE2_String("EK-Kont.Pos"), null);
		oMulti_BuchungsNummern.add_Component(new FU_LIST_BT_OPEN_KONTRAKT_POS(oSQLFieldMAP.get_("ID_VPOS_KON_VK"),oMaskVPOS_KON_VK,"EK"),new MyE2_String("VK-Kont.Pos"), null);

		//2012-01-09: in die kontrakt-nummern-spalte auch den UMA-button eintragen
		oMulti_BuchungsNummern.add_Component(new FU_LIST_BT_DEFINIERE_UMA_VERTRAG(),new MyE2_String("UMA-Kontrakt"), FU___CONST.HASH_LIST_BUTTON_UMA_KONTRAKT);
		
		
		oMulti_KFZNummern.add_Component(new FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN(oSQLFieldMAP.get_("TRANSPORTKENNZEICHEN")),new MyE2_String("LKW-Kennz."), null);
		oMulti_KFZNummern.add_Component(new FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN(oSQLFieldMAP.get_("ANHAENGERKENNZEICHEN")),new MyE2_String("Anhänger-Kennz."), null);
//		oMulti_KFZNummern.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("WIEGEKARTENKENNER_LADEN")),new MyE2_String("Wiegek.(Laden)"), null);
//		oMulti_KFZNummern.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("WIEGEKARTENKENNER_ABLADEN")),new MyE2_String("Wiegek.(Abladen)"), null);

		//2013-09-18: wiegekarte aus der liste aenderbar
		oMulti_KFZNummern.add_Component(new FU_LIST_BT_EditWiegekarteInListe("WIEGEKARTENKENNER_LADEN"),new MyE2_String("Wiegek.(Laden)"), FU___CONST.HASH_LIST_FIELD_WIEGEKARTE_LINKS);
		oMulti_KFZNummern.add_Component(new FU_LIST_BT_EditWiegekarteInListe("WIEGEKARTENKENNER_ABLADEN"),new MyE2_String("Wiegek.(Abladen)"),  FU___CONST.HASH_LIST_FIELD_WIEGEKARTE_RECHTS);

		
//		//2013-09-16: zusatorte-wiegekenner einblenden
//		oMulti_KFZNummern.add_Component(new FU_LIST_ComponentMAP_HelpGrid(),new MyE2_String("Wiegek.(Zusatzort)"), FU___CONST.HASH_LIST_FIELD_WIEGEKARTE_FUO);
		
		oMulti_LieferAbholDat.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("DATUM_ABHOLUNG")),new MyE2_String("Abholdat (von)"), null);
		oMulti_LieferAbholDat.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("DATUM_ABHOLUNG_ENDE")),new MyE2_String("Abholdat (bis)"), null);
		oMulti_LieferAbholDat.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("DATUM_ANLIEFERUNG")),new MyE2_String("Lieferdat (von)"), null);
		oMulti_LieferAbholDat.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("DATUM_ANLIEFERUNG_ENDE")),new MyE2_String("Lieferdat (bis)"), null);
		
		ColumnLayoutData  oLayoutRight = LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_1_1_1_1);
		ColumnLayoutData  oLayoutRightText = LayoutDataFactory.get_ColLayoutGridRight(new Insets(1,2,5,0));
		ColumnLayoutData  oLayoutTitelRightText = LayoutDataFactory.get_ColLayoutGridRight_DARK(E2_INSETS.I_1_1_5_1);
		
		oMulti_MengenGesamt.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("MENGE_VORGABE_KO")),new MyE2_String("Vorgabe(kg)"),null,oLayoutRight);
		oMulti_MengenGesamt.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("MENGE_AUFLADEN_KO")),new MyE2_String("Lade(kg)"), null,oLayoutRight);
		oMulti_MengenGesamt.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("MENGE_ABLADEN_KO")),new MyE2_String("Ablade(kg)"), null,oLayoutRight);
		
		oMulti_Datum.add_Component(new FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN(oSQLFieldMAP.get_("DATUM_AUFLADEN")),new MyE2_String("Lade-Dat."), null);
		oMulti_Datum.add_Component(new FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN(oSQLFieldMAP.get_("DATUM_ABLADEN")),new MyE2_String("Ablade-Dat."), null);
		oMulti_Datum.add_Component(new FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN(oSQLFieldMAP.get_("TRANSPORTKENNZEICHEN")),new MyE2_String("LKW-Kennz."), null);
		oMulti_Datum.add_Component(new FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN(oSQLFieldMAP.get_("ANHAENGERKENNZEICHEN")),new MyE2_String("Anhänger-Kennz."), null);

		
		oMulti_MengenEingabeEK.add_Component(new FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN(oSQLFieldMAP.get_("C_ANTEIL_PLANMENGE_LIEF")),new MyE2_String("EK.Vorgabe(kg)"),null,oLayoutRight,oLayoutTitelRightText);
		oMulti_MengenEingabeEK.add_Component(new FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN(oSQLFieldMAP.get_("C_ANTEIL_LADEMENGE_LIEF")),new MyE2_String("EK-Lade(kg)"), null,oLayoutRight,oLayoutTitelRightText);
		oMulti_MengenEingabeEK.add_Component(new FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN(oSQLFieldMAP.get_("C_ANTEIL_ABLADEMENGE_LIEF")),new MyE2_String("EK-Ablade(kg)"), null,oLayoutRight,oLayoutTitelRightText);
		oMulti_MengenEingabeEK.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("C_ABZUG_LADEMENGE_LIEF")),new MyE2_String("Menge-Abzug (EK)"), null,oLayoutRightText,oLayoutTitelRightText);
		oMulti_MengenEingabeEK.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("C_ABZUG_BETRAG_EK")),new MyE2_String("Preis-Abzug (EK)"), null,oLayoutRightText,oLayoutTitelRightText);

		
		oMulti_Preis_Steuer_EK.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("BB__EINZELPREIS_EK")),new MyE2_String("Preis(EK)"), null,oLayoutRightText,oLayoutTitelRightText);
		oMulti_Preis_Steuer_EK.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("BB__STEUERSATZ_EK")),new MyE2_String("MWSt(EK)"), null,oLayoutRightText,oLayoutTitelRightText);
		oMulti_Preis_Steuer_EK.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("BB__STEUERVERMERK_EK")),new MyE2_String("EU-Verm(EK)"), null,oLayoutRightText,oLayoutTitelRightText);
		oMulti_Preis_Steuer_EK.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("BB__MENGENPRUEFUNG_EK")),new MyE2_String("Check(EK)"), null,oLayoutRightText,oLayoutTitelRightText);

		oMulti_MengenEingabeVK.add_Component(new FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN(oSQLFieldMAP.get_("C_ANTEIL_PLANMENGE_ABN")),new MyE2_String("VK.Vorgabe(kg)"),null,oLayoutRight,oLayoutTitelRightText);
		oMulti_MengenEingabeVK.add_Component(new FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN(oSQLFieldMAP.get_("C_ANTEIL_LADEMENGE_ABN")),new MyE2_String("VK-Lade(kg)"), null,oLayoutRight,oLayoutTitelRightText);
		oMulti_MengenEingabeVK.add_Component(new FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN(oSQLFieldMAP.get_("C_ANTEIL_ABLADEMENGE_ABN")),new MyE2_String("VK-Ablade(kg)"), null,oLayoutRight,oLayoutTitelRightText);
		oMulti_MengenEingabeVK.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("C_ABZUG_ABLADEMENGE_ABN")),new MyE2_String("Menge-Abzug (VK)"), null,oLayoutRightText,oLayoutTitelRightText);
		oMulti_MengenEingabeVK.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("C_ABZUG_BETRAG_VK")),new MyE2_String("Preis-Abzug (VK)"), null,oLayoutRightText,oLayoutTitelRightText);
		
		oMulti_Preis_Steuer_VK.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("BB__EINZELPREIS_VK")),new MyE2_String("Preis(VK)"), null,oLayoutRightText,oLayoutTitelRightText);
		oMulti_Preis_Steuer_VK.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("BB__STEUERSATZ_VK")),new MyE2_String("MWSt(VK)"), null,oLayoutRightText,oLayoutTitelRightText);
		oMulti_Preis_Steuer_VK.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("BB__STEUERVERMERK_VK")),new MyE2_String("EU-Verm(VK)"), null,oLayoutRightText,oLayoutTitelRightText);
		oMulti_Preis_Steuer_VK.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("BB__MENGENPRUEFUNG_VK")),new MyE2_String("Check(VK)"), null,oLayoutRightText,oLayoutTitelRightText);

		oMulti_ID_Block.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("ID_VPOS_TPA_FUHRE_2")),new MyE2_String("ID(FUH)"), null);
		oMulti_ID_Block.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("ID_VPOS_TPA")),new MyE2_String("ID(POS)"), null);
		oMulti_ID_Block.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("ID_VKOPF_TPA")),new MyE2_String("ID(KOPF)"), null);
		
		//
		// Manfred TEST
		if (ENUM_MANDANT_DECISION.USE_BUTTON_CONNECT_WK_FUHRE.is_YES()){
			oMulti_ID_Block.add_Component(new WK_LIST_BT_CONNECT_FUHRE_to_WK(VPOS_TPA_FUHRE.fullTabName() ),new MyE2_String("Wiegekarte"),FUO__CONST.HASH_LIST_BUTTON_SUCHE_WK);
		}
		// 
		
		
		
		//2011-11-18: postennummer u.a.
		oMulti_Grid_Nummern.add_Component(new FU__LIST_DB_BUTTON_EditPostenNummer(oSQLFieldMAP.get_("POSTENNUMMER_EK"),"EK"),new MyE2_String("Posten(Lade)"), null);
		oMulti_Grid_Nummern.add_Component(new FU__LIST_DB_BUTTON_EditPostenNummer(oSQLFieldMAP.get_("POSTENNUMMER_VK"),"VK"),new MyE2_String("Posten(Ablade)"), null);
		
		//2014-02-18: schalter fuer vorhandene gelangensbestaetigung
		oMulti_Grid_Nummern.add_Component(new FU__LIST_CB_GelangensbestaetigungAnAus(oSQLFieldMAP.get_(_DB.VPOS_TPA_FUHRE$GELANGENSBESTAETIGUNG_ERHALTEN)),new MyE2_String("Gelangens-Best."), null);
		
		//2014-06-02: lieferbedingungen (die automatisch gezogen werden)
		oMulti_Grid_LieferBed.add_Component(new MyE2_Grid_InLIST(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS()),new MyE2_String("Lfbed(EK)"), FU___CONST.FU_LIST_FIELDNAME_FU_LIEFERBEDINGUNGEN_EK,false);
		oMulti_Grid_LieferBed.add_Component(new MyE2_Grid_InLIST(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS()),new MyE2_String("Lfbed(VK)"), FU___CONST.FU_LIST_FIELDNAME_FU_LIEFERBEDINGUNGEN_VK,false);
		//2014-07-04: weitere felder mit passnummern
		oMulti_Grid_LieferBed.add_Component(new MyE2_Grid_InLIST(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS()),new MyE2_String("Passnummer(LIEF)"), FU___CONST.FU_LIST_FIELDNAME_FU_PASSNUMMER_EK,false);
		oMulti_Grid_LieferBed.add_Component(new MyE2_Grid_InLIST(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS()),new MyE2_String("Passnummer(ABN)"), FU___CONST.FU_LIST_FIELDNAME_FU_PASSNUMMER_VK,false);
		
		
				
		/*
		 * jetzt kommen die spalten fuer die fahrplan-listen
		 */
		//this.add_Component("HASH_SPECIAL2",oColContainer, new MyE2_String("Container/Sorte"));
		
		//2011-08-18: vor containertyp auch die anzahl anzeigen
		oColContainer.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("CONTAINER_KURZBEZEICHNUNG")),new MyE2_String("Container"),null);
		//oColContainer.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("ANZAHL_UND_CONTAINERTYP")),new MyE2_String("Container"),null);
		oColContainer.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("ART_INFO")), new MyE2_String("Sorte"),null);

		
		//2011-08-19: anzahlcontainer in eine eigene spalte
		oMulti_Grid_diverse.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("ANZAHL_CONTAINER_FP")),new MyE2_String("Zahl"),null,E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutCenterTOP());
		
		oColLKW.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("LKW_KFZKENNZEICHEN")),new MyE2_String("LKW"),null);
		oColLKW.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("ANH_KFZKENNZEICHEN")),new MyE2_String("Anhänger"),null);
		oColLKW.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("FAHRER_FP")),new MyE2_String("Fahrer"),null);

		oColTaetigkeit.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("TAETIGKEIT_FP")),new MyE2_String("Tätigkeit"),null);
		oColTaetigkeit.add_Component(new MyE2_DB_TextArea(oSQLFieldMAP.get_("BEMERKUNG"),200,5,null,new E2_FontPlain(-2)),new MyE2_String("Bemerkung"),null);

		
		oColCodes.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("ID_VPOS_TPA_FUHRE")),new MyE2_String("ID Fuhre"),null);
		oColCodes.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("FAHRPLANGRUPPE_FP")),new MyE2_String("Fahrtgrp"),null);
		oColCodes.add_Component(new MyE2_DB_Button(oSQLFieldMAP.get_("AA_ANZAHL_IN_GRUPPE")),new MyE2_String("Anzahl Grp"),null);
		oColCodes.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("EAN_CODE_FP")),new MyE2_String("EAN"),null);

		oColInfos.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("FP_VORMERK_VON_BIS")),new MyE2_String("Vormerkung"),null);
		oColInfos.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_("IST_GEPLANT_FP")),new MyE2_String("Geplant"),null);

		oColZeiten.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("DAT_FAHRPLAN_FP")),new MyE2_String("Datum Fahrplan"),null);
		oColZeiten.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("FAHRT_ANFANG_FP")),new MyE2_String("Anfang"),null);
		oColZeiten.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("FAHRT_ENDE_FP")),new MyE2_String("Ende"),null);
		
		oColAnrufer_dat.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("ANRUFER_FP")),new MyE2_String("Anrufer"),null);
		oColAnrufer_dat.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("ANRUFDATUM_FP")),new MyE2_String("Anrufdatum"),null);
		oColAnrufer_dat.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("ERFASSER_FP")), new MyE2_String("Erfasser"),null);

		
		//kostenspalte in der Fuhre (button und infogrid)
		//2011-05-12: fuhren-kosten-button
		
		MyE2_DB_MultiComponentGrid   oGridKostenErfassung = null;
		if (ENUM_MANDANT_DECISION.USE_GEOPOINT_SHOW_ROUTE.is_YES()) {
			oGridKostenErfassung = new MyE2_DB_MultiComponentGrid(3);
			oGridKostenErfassung.EXT().set_oLayout_ListElement(LayoutDataFactory.get_GridLayoutGridCenter_TOP(E2_INSETS.I_1_1_1_1));
			oGridKostenErfassung.EXT().set_oLayout_ListTitelElement(new RB_gld()._center_top()._col(new E2_ColorDark())._ins(E2_INSETS.I(1,1,1,1)));
			oGridKostenErfassung.EXT().set_oCompTitleInList(new E2_Grid()._setSize(40,40,20)
								._a(new FK_MASK_bt_MULTI_FUHREN_KOSTEN(this.get_oNavigationList_This_Belongs_to()))
								._a("Route",  new RB_gld()._span(1)._center_top())
								._a("Karte",  new RB_gld()._span(1)._center_top())
								
								._a("Kosteninfo", new RB_gld()._span(3)._center_top())
								
								._a("Proforma", new RB_gld()._span(3)._center_top())
								);
		} else {
			oGridKostenErfassung = new MyE2_DB_MultiComponentGrid(2);
			oGridKostenErfassung.EXT().set_oLayout_ListElement(LayoutDataFactory.get_GridLayoutGridCenter_TOP(E2_INSETS.I_1_1_1_1));
			oGridKostenErfassung.EXT().set_oLayout_ListTitelElement(new RB_gld()._center_top()._col(new E2_ColorDark())._ins(E2_INSETS.I(1,1,1,1)));
			oGridKostenErfassung.EXT().set_oCompTitleInList(new E2_Grid()._setSize(40,40)
					._a(new FK_MASK_bt_MULTI_FUHREN_KOSTEN(this.get_oNavigationList_This_Belongs_to()))
					._a("Route",  new RB_gld()._span(1)._center_top())
					
					._a("Kosteninfo", new RB_gld()._span(2)._center_top())
					
					._a("Proforma", new RB_gld()._span(2)._center_top())
					);
			
		}
		
		//2016-05-09: Neue Kosten Button
		//2016-07-06: Aktiviert in Echt System !
//		FU_LIST_BT_Eingabe_Kosten bt_jump = new FU_LIST_BT_Eingabe_Kosten();
//		bt_jump.EXT().set_oCompTitleInList(new FK_MASK_bt_MULTI_FUHREN_KOSTEN(this.get_oNavigationList_This_Belongs_to()));
		
		if (ENUM_MANDANT_DECISION.USE_GEOPOINT_SHOW_ROUTE.is_YES()) {
			oGridKostenErfassung.add_Component(new FU_LIST_BT_Eingabe_Kosten(),					new MyE2_String("Kosten"),	FU___CONST.HASH_LIST_BT_KOSTEN_ERFASSUNG, 	new RB_gld()._span(1)._center_mid()._ins(2));
			oGridKostenErfassung.add_Component(new FU_LIST_btRoutingEntfernungUndKosten(),		new MyE2_String("Route"),	FU___CONST.HASH_LIST_BT_ROUTING, 			new RB_gld()._span(1)._center_mid()._ins(2));
			
			//13.03.2018 @ sebastien: route schauen
			oGridKostenErfassung.add_Component(new FU_LIST_bt_show_routing(), new MyE2_String("Route anzeigen"), FU___CONST.HASH_LIST_BT_SHOW_ROUTING, new RB_gld()._span(1)._center_mid()._ins(2));
	
			oGridKostenErfassung.add_Component(new FU_LIST_INFO_FELD_Kosten(oSQLFieldMAP.get_("C__ID_VPOS_TPA_FUHRE")),new MyE2_String("Kosten-Info"),null,new RB_gld()._span(3)._center_top()._ins(2));
	
			//2013-09-06: neues feld um anzeige/start der proformarechnungen zu erlauben
			oGridKostenErfassung.add_Component(new FU__LIST_BT_CREATE_OR_OPEN_PROFORMA_RECHNUNG(), 
												new MyE2_String("Proforma"),
												FU___CONST.HASH_LIST_FIELD_ANZAHL_PROFROMARECHNUNG,new RB_gld()._span(3)._center_top()._ins(2) );
		} else {
			oGridKostenErfassung.add_Component(new FU_LIST_BT_Eingabe_Kosten(),					new MyE2_String("Kosten"),	FU___CONST.HASH_LIST_BT_KOSTEN_ERFASSUNG, 	new RB_gld()._span(1)._center_top()._ins(2));
			oGridKostenErfassung.add_Component(new FU_LIST_btRoutingEntfernungUndKosten(),		new MyE2_String("Route"),	FU___CONST.HASH_LIST_BT_ROUTING, 			new RB_gld()._span(1)._center_top()._ins(2));
	
			oGridKostenErfassung.add_Component(new FU_LIST_INFO_FELD_Kosten(oSQLFieldMAP.get_("C__ID_VPOS_TPA_FUHRE")),new MyE2_String("Kosten-Info"),null,new RB_gld()._span(2)._center_top()._ins(2));
	
			//2013-09-06: neues feld um anzeige/start der proformarechnungen zu erlauben
			oGridKostenErfassung.add_Component(new FU__LIST_BT_CREATE_OR_OPEN_PROFORMA_RECHNUNG(), 
												new MyE2_String("Proforma"),
												FU___CONST.HASH_LIST_FIELD_ANZAHL_PROFROMARECHNUNG,new RB_gld()._span(2)._center_top()._ins(2) );

		}
		
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_CHECKBOX_IN_LIST,new E2_CheckBoxForList(),new MyE2_String("?"));

		this.add_Component(FU_LIST_ModulContainer.NAME_OF_LISTMARKER_IN_LIST,new E2_ButtonListMarker(),new MyE2_String("?"));
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_INFO_ICONSBLOCK,oMulti_GridIcons,new MyE2_String("Informations-Spalte"));
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_INFO_STATUSBLOCK,oMulti_ColGrueneButtons,new MyE2_String("Status-Spalte"));
		
		//2015-06-03: spalte mit attachments und befundungsinfo
		this.add_Component(new __FU_FUO_COL_ANHAENGE_BEFUNDUNGEN(oSQLFieldMAP.get_(FU___CONST.FIELDNAME_ID_VPOS_TPA_FUHRE_3),
													ENUM_BEWGUNGSATZ_TYP.JT_VPOS_TPA_FUHRE,
													oModulcontainerLIST.get_oNavList()),new MyE2_String("Befundung/Anhänge"));
		
		
		//2018-05-07: spalte mit AH7-button
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_AH7_COL, new FU__LIST_AH7_wrapper(),  S.ms("AH7"));
		
		
		//2019-11-29: spalte mit infos zu ablaufenden abrechnungszeiten
		this.add_Component(new FU_PruefeUeberschreitungZahlungsDatum());
		
		//2019-06-17: spalte mit fakturierungsfrist
		this.add_Component(new FU__ListAnzeigeAblaufFakturierungsFrist());
		
		//2019-09-11: spalte fuer dienstleistungsrechnungen
		this.add_Component(new FU__AbrechnungDienstLeistungen_ListComponentFuhre());
		
		
		//2021-03-01: warnug ueberschreitung fahrplan-Datum
		this.add_Component(new FU__ListAnzeigePlanungsfristFahrplan())._setLongText4ColumnSelection(S.ms("Fahrplanfrist"));
		
		//2018-06-08: spalte mit waehrungsanzeige-block
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_WAEHRUNG_ANZEIGE, new FU_LIST_WaehrungsAnzeigeWrapper(),  S.ms("Währ."));
		
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_EXTENDER_ORTE,new E2_ListButtonExtendDaughterObject(true, oModulcontainerLIST.get_oNavList()),new MyE2_String("Zusatzpositionen (Ausklapp-Button)"));

		this.add_Component(FU_LIST_ModulContainer.NAME_OF_KOSTEN_BLOCK,oGridKostenErfassung, new MyE2_String("Kosten"));

		this.add_Component(FU_LIST_ModulContainer.NAME_OF_BLOCK_LIEF,oMulti_LIEFERANT, new MyE2_String("Namensblock Lieferant"));
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_BLOCK_ABN,oMulti_ABNEHMER, new MyE2_String("Namensblock Abnehmer"));
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_BLOCK_SPEDITEUR,oMulti_SPEDITEUR, new MyE2_String("Namensblock Spediteur"));
		
		
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_SORTENBLOCK,oMulti_Sorte, new MyE2_String("Sorte der TP-Pos"));
		
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_POSTENBLOCK,oMulti_Grid_Nummern, new MyE2_String("Posten"));
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_LIEFERBEDINGUNGSBLOCK,oMulti_Grid_LieferBed, new MyE2_String("Lieferbed."));
		
		
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_KENNZEICHENBLOCK,oMulti_KFZNummern, new MyE2_String("Kennzeichen/Wiegekarte"));
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_LIEFERABHOLDAT,oMulti_LieferAbholDat, new MyE2_String("Liefer/Abholdatum"));
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_BUCHUNGSNUMMERNBLOCK,oMulti_BuchungsNummern, new MyE2_String("Buchungsnummern-Block"));
		
		//aenderung 2010-12-17: Rechnungen-list und Gutschriften-Liste wo die Fuhre beteiligt ist
		this.add_Component(FU___CONST.HASH_GRID_LIST_RECHNUNGEN,new ownGridWithCopy(), new MyE2_String("RE-Nr"));
		this.add_Component(FU___CONST.HASH_GRID_LIST_GUTSCHRIFTEN,new ownGridWithCopy(), new MyE2_String("G-Nr"));
		
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_DATUMSBLOCK,oMulti_Datum, new MyE2_String("Datum Lade/Ablade"));
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_MENGENBLOCK_INPUT,oMulti_MengenEingabeEK, new MyE2_String("Lademengen"));
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_PREISBLOCK_EK,oMulti_Preis_Steuer_EK, new MyE2_String("EK-Preis/Steuer"));
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_MENGENBLOCK_OUTPUT,oMulti_MengenEingabeVK, new MyE2_String("Ablademengen"));
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_PREISBLOCK_VK,oMulti_Preis_Steuer_VK, new MyE2_String("VK-Preis/Steuer"));
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_MENGENBLOCK,oMulti_MengenGesamt, new MyE2_String("Menge Lade/Ablade"));
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_ID_BLOCK,oMulti_ID_Block, new MyE2_String("IDs (Pos/TPA-Pos/TPA-Kopf)"));

		/*
		 * fahrplan-bloecke
		 */
		//2011-08-19: anzahlcontainer in eine eigene spalte
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_FP_DIVERSE_BLOCK,oMulti_Grid_diverse, new MyE2_String("Anzahl Container"));
		
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_FP_CONTAINERBLOCK,oColContainer, new MyE2_String("Container/Sorte"));
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_FP_LKWBLOCK,oColLKW, new MyE2_String("Kennzeichen/Fahrer"));
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_FP_INFOBLOCK,oColTaetigkeit, new MyE2_String("Tätigkeit"));
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_FP_CODEBLOCK,oColCodes, new MyE2_String("Gruppe/EanCode"));
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_FP_VORMERKBLOCK,oColInfos, new MyE2_String("Vormerkzeit"));
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_FP_AUSFUEHRBLOCK,oColZeiten, new MyE2_String("Datum Fahrplan"));
		this.add_Component(FU_LIST_ModulContainer.NAME_OF_FP_ERFASSUNGBLOCK,oColAnrufer_dat, new MyE2_String("Anrufer/Anrufdatum"));
		
		
		//2011-02-22: storno-infos einblenden
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMAP.get_("AA_STORNO_INFO"), true),new MyE2_String("Storno-Info"),false,false);
		
		
		
		/*
		 * einige spezielle einstellungen
		 */
		oMulti_LIEFERANT.set_all_labels_EmtpyValue("--");
		oMulti_ABNEHMER.set_all_labels_EmtpyValue("--");
		oMulti_SPEDITEUR.set_all_labels_EmtpyValue("--");
		oMulti_BuchungsNummern.set_all_labels_EmtpyValue("--");
		oMulti_LieferAbholDat.set_all_labels_EmtpyValue("--");
		oMulti_Sorte.set_all_labels_EmtpyValue("--");
		
		
		this.get_hmRealDBComponents().get("AA_ANZAHL_IN_GRUPPE").EXT_DB().set_bIsSortable(false);
		
		
		//jetzt die einstellung der listen, je nach aufruftyp
		if (BASIC_MODULE_NAME.equals(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER))
		{
			// standardmaessig verstecken
			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_KENNZEICHENBLOCK)).EXT().set_bIsVisibleInList(false);
			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_ID_BLOCK)).EXT().set_bIsVisibleInList(false);

			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_FP_CONTAINERBLOCK)).EXT().set_bIsVisibleInList(false);
			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_FP_LKWBLOCK)).EXT().set_bIsVisibleInList(false);
			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_FP_INFOBLOCK)).EXT().set_bIsVisibleInList(false);
			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_FP_CODEBLOCK)).EXT().set_bIsVisibleInList(false);
			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_FP_VORMERKBLOCK)).EXT().set_bIsVisibleInList(false);
			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_FP_AUSFUEHRBLOCK)).EXT().set_bIsVisibleInList(false);
			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_FP_ERFASSUNGBLOCK)).EXT().set_bIsVisibleInList(false);
			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_MENGENBLOCK)).EXT().set_bIsVisibleInList(false);
		}
		else               //fahrplanaufrufe
		{
			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_KENNZEICHENBLOCK)).EXT().set_bIsVisibleInList(false);
			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_ID_BLOCK)).EXT().set_bIsVisibleInList(false);

			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_INFO_STATUSBLOCK)).EXT().set_bIsVisibleInList(false);
			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_BLOCK_SPEDITEUR)).EXT().set_bIsVisibleInList(false);
			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_SORTENBLOCK)).EXT().set_bIsVisibleInList(false);
			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_KENNZEICHENBLOCK)).EXT().set_bIsVisibleInList(false);
			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_LIEFERABHOLDAT)).EXT().set_bIsVisibleInList(false);
			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_BUCHUNGSNUMMERNBLOCK)).EXT().set_bIsVisibleInList(false);
			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_DATUMSBLOCK)).EXT().set_bIsVisibleInList(false);
			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_MENGENBLOCK)).EXT().set_bIsVisibleInList(false);
			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_ID_BLOCK)).EXT().set_bIsVisibleInList(false);
			((MyE2IF__Component)this.get(FU_LIST_ModulContainer.NAME_OF_MENGENBLOCK)).EXT().set_bIsVisibleInList(false);
			
			if (BASIC_MODULE_NAME.equals(E2_MODULNAMES.NAME_MODUL_FAHRPLAN))
			{
				//dann die sortierung der titlebuttons abschalten
				this.set_allDBComponents_Sortable(false);
			}
		}
		
		
		//jetzt den fahrplan-gruppen-selektor aktivieren
		((MyE2_DB_Button)this.get_hmRealComponents().get("AA_ANZAHL_IN_GRUPPE")).add_oActionAgent(new action_ZeigeAlleFuhrenEinerGruppe(oModulcontainerLIST.get_oNavList()));
		

		this.set_List_EXPANDER_4_ComponentMAP(new FU_LIST_Expander_TeilPositionen(oModulcontainerLIST.get_oNavList()));

		
		this.set_oSubQueryAgent(new FU_LIST_ComponentMAP_QUERYAGENT(oModulcontainerLIST.get_oNavList(),BASIC_MODULE_NAME));
		//this.add_oSubQueryAgent(new BS__SubQueryAgentShowDelGrundOncheckBox());
		this.add_oSubQueryAgent(new FU__SubQueryAgent_SHOW_StornoGrund());
		
		//2014-06-02: neue subquery-agent fuer die lieferbedingungen
		this.add_oSubQueryAgent(new FU__SubQueryAgent_SHOW_LieferBedingungen(oModulcontainerLIST));

		
		//20170901: fuhren mit buchungsnummer, aber ohne lieferschein markieren
		this.add_oSubQueryAgent(new FU_LIST_ComponentMAP_SubQueryAgentCheckLieferscheinDruck());
		
		
		//2019-09-13: dienstleistungskomponente formatieren
        this._setColExtent(     new Extent(150), ADL__ListComponentFuhre.key);
        this._setLayoutElements(new RB_gld()._center_top()._ins(3,1,3,1)._col(new E2_ColorBase()), ADL__ListComponentFuhre.key);
        this._setLayoutTitles(  new RB_gld()._center_top()._ins(1,2,1,1)._col(new E2_ColorDark()), ADL__ListComponentFuhre.key);

		
		
	}

	
	private class ownGridWithCopy extends MyE2_Grid
	{

		public ownGridWithCopy() 
		{
			super(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		}
		
		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			return new ownGridWithCopy();
		}

	}
	
	
	
	/*
	 * aktion, die alle fahrten einer gruppe einblendet, unabhaengig vom definierten selektor
	 */
	private class action_ZeigeAlleFuhrenEinerGruppe extends XX_ActionAgent
	{
		private E2_NavigationList oNaviList = null;
		
		
		public action_ZeigeAlleFuhrenEinerGruppe(E2_NavigationList naviList)
		{
			super();
			oNaviList = naviList;
		}


		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			String cID_VPOS_TPA_FUHRE=((MyE2_DB_Button) execInfo.get_MyActionEvent().getSource()).EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE);
			
			if (recFuhre.get_FAHRPLANGRUPPE_FP_lValue(new Long(-1)).longValue()!=-1)
			{
				RECLIST_VPOS_TPA_FUHRE recList = new RECLIST_VPOS_TPA_FUHRE("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE FAHRPLANGRUPPE_FP="+
											recFuhre.get_FAHRPLANGRUPPE_FP_lValue(new Long(-1)).longValue());
				
				if (recList.get_vKeyValues().size()>1)
				{
					this.oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().clear_BEDINGUNG_DYNAMIC();
					this.oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().clear_BEDINGUNG_DYNAMIC();
					
					this.oNaviList.get_vActualID_Segment().removeAllElements();
					this.oNaviList.get_vectorSegmentation().removeAllElements();
					
					this.oNaviList.ADD_NEW_ID_TO_ALL_VECTORS(recList.get_vKeyValues());
					bibMSG.add_MESSAGE(new MyE2_Info_Message("In der Liste sind nur die Fahrten der Gruppe ! Neuaufbau über den Selektor !"));
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die Gruppe ",true," "+recFuhre.get_FAHRPLANGRUPPE_FP_cUF_NN(""),false," besteht nur aus einer Fuhre !!",true).CTrans()));
				}
				
			}
			
		}
		
	}


	
	
}
