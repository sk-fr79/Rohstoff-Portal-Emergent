package rohstoff.Echo2BusinessLogic.FIBU;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_StandardLayoutFactory;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_LIST_comp_anlagen_email;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Icon;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG_K_MASK__ModulContainer;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;
import rohstoff.Echo2BusinessLogic._4_ALL.DB_BUTTON_FIRMA_WITH_INFO;

public class FIBU_LIST_ComponentMap extends E2_ComponentMAP 
{

	public FIBU_LIST_ComponentMap(	BSRG_K_MASK__ModulContainer 	oMaskContainer_RG, 
			FS_ModulContainer_MASK 			oMaskContainer_ADRESSE,
			FIBU_LIST_BasicModuleContainer  oListContainer ,
			boolean 						bMacheGeschlosseneListenzeilenGrau) throws myException
	{
		super(new FIBU_LIST_SqlFieldMAP());

		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();

		this.add_Component(FIBU_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(FIBU_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		Vector<E2_ResourceIcon> vIcons = new Vector<E2_ResourceIcon>();
		vIcons.add(E2_ResourceIcon.get_RI("geld_rein.png"));
		vIcons.add(E2_ResourceIcon.get_RI("geld_raus.png"));
		Vector<String> vWerte = new Vector<String>();
		vWerte.add("1");
		vWerte.add("-1");

		MyE2_DB_Icon  dbReinRaus = new MyE2_DB_Icon(oFM.get("FAKTOR_BUCHUNG_PLUS_MINUS"),E2_ResourceIcon.get_RI("empty10.png"),vIcons,vWerte);
		dbReinRaus.EXT().set_oLayout_ListTitelElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutCenterTOPHeader());
		dbReinRaus.EXT().set_oLayout_ListElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutCenterTOP());




		//hier kommen die Felder	
		this.add_Component(dbReinRaus, 														new MyE2_String("+/-",false),		true,true,new MyE2_String("Geldeingang oder Geldausgang"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BUCHUNGSTYP")), 				new MyE2_String("Typ"),				true,true,new MyE2_String("Ursprung der Buchung (Rechnung/Gutschrift/Scheck/Geldeingang/Geldausgang)"),null,null);


		//jump-button in header
		FIBU__LIST_BT_JumpToRG jump = new FIBU__LIST_BT_JumpToRG();
		jump.EXT().set_oCompTitleInList(new FIBU__LIST_BT_JumpToRG_MultiSelect(oListContainer.get_oNaviList()));
		this.add_Component(FIBU_CONST.KEY_SPALTE_JUMP_TO_RG, 								jump, 									new MyE2_String(">>"),	true,new MyE2_String("In das Rechnungs- oder Gutschriftsmodul zu diesem Beleg springen "),null,null);
		this.add_Component(FIBU_CONST.KEY_SPALTE_DOWNLOAD_RECH_GUT_AUS_ARCHIV, 				new FIBU_LIST_BT_DownloadBeleg(), 		new MyE2_String("RG"),	true,new MyE2_String("Beleg aus Archiv holen "),null,null);


		//2016-03-21: spalte fuer anlagen- und email-aufruf
		this.add_Component(FIBU_CONST.SONDERNAMEN.COMP_ANLAGEN_EMAILS.name(), 				new E2_LIST_comp_anlagen_email(oListContainer.get_oNaviList()), 	new MyE2_String("A/E"),	true,new MyE2_String("Zeige Anlagen- und eMail-Popup"),null,null);




		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("VKOPF_BUCHUNGSNUMMER")), 		new MyE2_String("BuchungsNr."),	true,true,new MyE2_String("Bei Rechnungen/Gutschriften die Buchungsnummer"),null,null);

		//2016-02-16: neues feld zur erfassung von fremdbelegen
		this.add_Component(new FIBU_LIST_BT_ToEdit_FremdBelegNummer(oFM.get_("FREMDBELEGNUMMER")),	new MyE2_String("Fremdbeleg"),	false,true,new MyE2_String("Fremdbelegnummer"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EXP_ID_EXPORT_LOG")), 		new MyE2_String("Export-ID"),	true,true,new MyE2_String("Export ID"),null,null);
		
		//2016-10-04: Sebastien @ Schalter fuer die alte Mahnung 
		if(ENUM_MANDANT_DECISION.USE_MAHNUNG_BUTTON_ALT.is_YES()){
			this.add_Component(FIBU_CONST.KEY_SPALTE_MAHNUNGSBUTTONS,new MyE2_Grid_InLIST(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS()),new MyE2_String("M123"), true,new MyE2_String("Zeigt an, ob der Beleg bereits gemahnt wurde"),null,null);
		}
		//2016-09-22: neues mahnung Feld - fuer die MV @ Sebastien
		if(ENUM_MANDANT_DECISION.USE_MAHNUNG_BUTTON_NEU.is_YES()){
			this.add_Component(FIBU_CONST.KEY_SPALTE_MAHNUNGSBUTTONS_NEU,new MyE2_Grid_InLIST(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS()),new MyE2_String("M123 (Neu)"), true,new MyE2_String("Zeigt an, ob der Beleg bereits gemahnt wurde"),null,null);
		}
		
		//2012-01-16: ablauf der mahnung anzeigen
		if(ENUM_MANDANT_DECISION.USE_MAHNUNG_BUTTON_ALT.is_YES()){
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ABLAUF_MAHNUNG")), 			new MyE2_String("Mahn.Ablauf"),	true,true,new MyE2_String("Mahndatum + Frist in Tagen der letzten Mahnung"),null,null);
		}
		
		//2012-01-18: keine Mahnung un Firma oder Rechnungsbeleg
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("SUBQUERY__KEINE_MAHNUNGEN")),    new MyE2_String("Keine Mahn."), true,true,
				new MyE2_String("Ist aktiv wenn entweder die Firma den Vermerk <Keine Mahnungen> oder der Beleg den Vermerk  <Keine Mahnungen> trägt"),null,MyE2_Grid.LAYOUT_CENTER_TOP(E2_INSETS.I_0_2_0_0));


		//		this.add_Component(new FIBU_BT_LIST_OPEN_FIRMA(oFM.get_("ID_ADRESSE"),oMaskContainer_ADRESSE,oListContainer), new MyE2_String("Adress-ID"),						true,true,new MyE2_String("Adress-ID des Belegempfängers"),null,null);
		DB_BUTTON_FIRMA_WITH_INFO obutton = new DB_BUTTON_FIRMA_WITH_INFO(oFM.get_("ID_ADRESSE"),oMaskContainer_ADRESSE,null,"NAME_ORT ASC","NAME_ORT DESC",400,40);
		obutton.get_oButtonOpenMask().setLineWrap(false);
		obutton.EXT().set_oColExtent(new Extent(300));
		this.add_Component(	obutton, new MyE2_String("Firma"),	true,true, new MyE2_String("Adress-ID des Belegempfängers"),null,null);

		//2012-02-10: forderungsverrechnung und scheckdruck-merkmal der firma einblenden
		this.add_Component(new FIBU_LIST_ForderungsVerrechnung(oFM.get_("FI_FORDERUNGSVERRECHNUNG")), new MyE2_String("Verrechnung"),true,false,
				new MyE2_String("Zeigt den Status der Forderungsverrechnungsvereinbarung an"),null,null);

		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("FI_SCHECKDRUCK_JN")), new MyE2_String("Scheck?"),true,false,
				new MyE2_String("Kunde ist Scheck-Kunde"),null,MyE2_Grid.LAYOUT_CENTER_TOP(E2_INSETS.I_0_2_0_0));



		FIBU_BT_ZEIGE_ALL_ZU_BUCHUNGSBLOCK oBlockBt = new FIBU_BT_ZEIGE_ALL_ZU_BUCHUNGSBLOCK(oFM.get_("BUCHUNGSBLOCK_NR"));
		this.add_Component(oBlockBt, 			new MyE2_String("Block"),			true,true,new MyE2_String("Kennzeichnet zusammenghörende Buchungen (z.B. Druck und Storno-Druck)"),null,null);
		//spalten, die bloecke qualifizieren
		this.add_Component(FIBU_CONST.KEY_SPALTE_BETRAGBLOCK, 	    new MyE2_Label(""), 	new MyE2_String("B-Summe"),			true,new MyE2_String("Summe der Rechnungen und Gutschriften im Buchungsblock"),null,null);
		this.add_Component(FIBU_CONST.KEY_SPALTE_SUMME_ZAHLUNGEN, 	new MyE2_Label(""), 	new MyE2_String("B-Gegen"),		    true,new MyE2_String("Summe der Zahlungen und Schecks im Buchungsblock"),null,null);
		this.add_Component(FIBU_CONST.KEY_SPALTE_REST, 				new MyE2_Label(""), 	new MyE2_String("B-Offen"),			true,new MyE2_String("Restbetrag des Buchungsblocks"),null,null);

		this.add_Component(FIBU_CONST.KEY_SPALTE_ZEIGE_STORNO_INFOS,new MyE2_Grid_InLIST(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS()), 	new MyE2_String("S"), true,new MyE2_String("Zeigt an, wenn der Beleg Storno-Positionen enthält"),null,null);

		//2011-02-19: fibu-infos aus adresse einblenden
		int[] iSpalte = {200};
		this.add_Component(FIBU_CONST.KEY_SPALTE_FIBU_INFOS_AUS_ADRESSE, 	new MyE2_Grid_InLIST(iSpalte, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS()), new MyE2_String("Fibu-Meldung(Adresse)"));


		//201-03-24: buchungsinfo und interne info untereinander und bearbeitbar
		//		MyE2_DB_MultiComponentGrid  oGrid = new MyE2_DB_MultiComponentGrid(1, 300);
		//		oGrid.add_Component(new FIBU_LIST_BT_ToEdit_Buchungsinfo(oFM.get_("BUCHUNGSINFO")), new MyE2_String("Buchungstext (extern)"), null);
		//		oGrid.add_Component(new FIBU_LIST_BT_ToEdit_InfoIntern(oFM.get_("INTERN_INFO")), new MyE2_String("Buchungsinfos (intern)"), null);
		//		
		//		this.add_Component(FIBU_CONST.KEY_SPALTE_INFOS,oGrid,new MyE2_String("Buchungsinformationen"));

		this.add_Component(new FIBU_LIST_BT_ToEdit_InfoIntern(oFM.get_("INTERN_INFO")), new MyE2_String("Buchungsinfos (intern)"),	true,true,new MyE2_String("Interne Infos zum Buchungssatz"),null,null);


		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NETTOSUMME_BASIS_WAEHRUNG"),80), new MyE2_String("Netto"),		false,true,new MyE2_String("Nettobetrag in der Basiswährung"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NETTOSUMME_FREMD_WAEHRUNG"),80), new MyE2_String("Netto FW"),	true,true,new MyE2_String("Nettobetrag in der Fremdwährung"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STEUERSUMME_BASIS_WAEHRUNG"),80), new MyE2_String("MWSt"),	false,true,new MyE2_String("Steuer des gesamten Betrags (alle MWSt.-Beträge summiert)"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STEUERSUMME_FREMD_WAEHRUNG"),80), new MyE2_String("MWSt FW"),	true,true,new MyE2_String("Steuer FW des gesamten Betrags (alle MWSt.-Beträge summiert)"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ENDBETRAG_BASIS_WAEHRUNG"),80), 	new MyE2_String("Endbetrag"),		false,true,new MyE2_String("Bruttobetrag in der Basiswährung"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ENDBETRAG_FREMD_WAEHRUNG"),80), 	new MyE2_String("Endbetrag FW"),	true,true,new MyE2_String("Bruttobetrag in der Fremdwährung"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SKONTOBETRAG_BASIS_WAEHRUNG"),80), new MyE2_String("Skonto"),			false,true,new MyE2_String("Skontoabzug in der Fremdwährung"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SKONTOBETRAG_FREMD_WAEHRUNG"),80),  new MyE2_String("Skonto FW"),		true,true,new MyE2_String("Skontoabzug in der Fremdwährung"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ZAHLUNGSBETRAG_BASIS_WAEHRUNG"),80), new MyE2_String("Betrag"),	false,true,new MyE2_String("Endbetrag, Storno wurde bereits abgezogen bei Rech/Gut, Zahlbetrag bei Buchungseinträgen"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ZAHLUNGSBETRAG_FREMD_WAEHRUNG"),80), new MyE2_String("Betrag FW"),true,true,new MyE2_String("Endbetrag, Storno wurde bereits abgezogen bei Rech/Gut, Zahlbetrag bei Buchungseinträgen (Fremdwährung)"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("WAEHRUNG_FREMD"),40),  new MyE2_String("Währ."),						true,true,new MyE2_String("Die Währung, in der der Belegt ausgeführt wurde"),null,null);

		//2011-03-08: zahlungsziel bearbeitbar machen
		//this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ZAHLUNGSZIEL")), new MyE2_String("Zahlungsziel"),					true,true,new MyE2_String("Zahlungsziel aus den Zahlungsbedingungen"),null,null);
		this.add_Component(new FIBU_LIST_BT_ToEdit_ZahlungsZiel(oFM.get_("ZAHLUNGSZIEL")), new MyE2_String("Zahlungsziel"),		true,true,new MyE2_String("Zahlungsziel aus der Fibu"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ORIGINAL_ZAHLUNGSZIEL")), new MyE2_String("Z.Ziel(orig)"),			true,true,new MyE2_String("Zahlungsziel aus der Rechnung/Gutschrift"),null,null);

		//2015-06-01: zahlungsbedinungen einfuegen
		MyE2_DB_Label_INROW labZB = new MyE2_DB_Label_INROW(oFM.get_("ZB_BEZEICHNUNG"));
		labZB.setFont(new E2_FontPlain(-2));
		labZB.setLineWrap(true);
		this.add_Component(labZB, new MyE2_String("Zahlungsbedingung"),			true,true,new MyE2_String("Zahlungsbedingung aus der Rechnung/Gutschrift"),null,null);

		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SCHECKNUMMER")), new MyE2_String("Scheck-Nr."),					true,true,new MyE2_String("Schecknummer des verschickten Schecks"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANZAHL_SCHECK_DRUCK")), 		new MyE2_String("Scheck ?"),		false,true,new MyE2_String("Wie oft wurde der Scheck gedruckt ?"),null,null);

		//2011-03-25: leistungszeitraum einbauen
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("LEISTUNGSDATUM_VON")), 		new MyE2_String("LDat.von"),	false,true,new MyE2_String("Leistungsdatums-Bereich eines Belegs  (untere Grenze)"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("LEISTUNGSDATUM_BIS")), 		new MyE2_String("LDat.bis"),	false,true,new MyE2_String("Leistungsdatums-Bereich eines Belegs  (obere Grenze)"),null,null);

		this.add_Component(new FIBU_LIST_BT_ToEdit_Buchungsinfo(oFM.get_("BUCHUNGSINFO")), 										new MyE2_String("Info Beleg"),	false,true,new MyE2_String("Information zur Buchung"),null,null);

		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("STORNIERT")), new MyE2_String("Sto"),									false,true,new MyE2_String("Storno-Merkmal des Eintrages"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STORNIERT_AM")), new MyE2_String("Sto am"),						false,true,new MyE2_String("Storno-Datum"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STORNOGRUND")),  new MyE2_String("Stornogrund"),					false,true,new MyE2_String("Grund der Stornierung"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STORNOKUERZEL")),  new MyE2_String("St.Kürzel"),					false,true,new MyE2_String("Wer hat Stornierung gemacht"),null,null);

		this.add_Component(new FIBU_BT_LIST_OPEN_RECH_GUT(oFM.get_("ID_VKOPF_RG"),oMaskContainer_RG,oListContainer), new MyE2_String("ID Vorgang"),	true,true,new MyE2_String("ID des Vorgangs"),null,null);

		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("BUCHUNG_GESCHLOSSEN")), 			new MyE2_String("Verbucht"),		true,true,new MyE2_String("Betrag ist durch Gegenbuchungen gedeckt"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BEARBEITERKUERZEL")), 			new MyE2_String("?"),				true,true,new MyE2_String("Bearbeiter, der die Buchung ausgelöst hat"),null,null);

		//neu am 2010-07-28
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DRUCK_ODER_BUCHUNGSDATUM")), 	new MyE2_String("Buch.Dat"),	true,true,new MyE2_String("Bei Rechnungs/Gutschrifts-Zeilen das Druckdatum auf Beleg, sonst das Datum(Erst.)"),null,null);

		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BUCHUNGSDATUM")), 				new MyE2_String("Datum(Erst.)"),	true,true,new MyE2_String("Datum des Eintrags in die Buchungsliste"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DATUMVERAENDERUNG")), 			new MyE2_String("Datum(Änd.)"),		true,true,new MyE2_String("Datum der letzten Änderung der Buchung"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_FIBU")), new MyE2_String("ID"),									true,true,new MyE2_String("ID des Buchungseintrags"),null,null);


		//		MyE2_DB_Label_INROW oRowInfo = (MyE2_DB_Label_INROW)this.get__Comp("BUCHUNGSINFO");
		//		oRowInfo.setFont(new E2_FontPlain(-2));

		((MyE2_Label)this.get__Comp(FIBU_CONST.KEY_SPALTE_BETRAGBLOCK)).EXT().set_ToolTipStringForListHeader(new MyE2_String("Summe der Rechnungen/Gutschriften in diesem Buchungsblock"));
		((MyE2_Label)this.get__Comp(FIBU_CONST.KEY_SPALTE_SUMME_ZAHLUNGEN)).EXT().set_ToolTipStringForListHeader(new MyE2_String("Summe der Zahlungen in diesem Buchungsblock"));
		((MyE2_Label)this.get__Comp(FIBU_CONST.KEY_SPALTE_REST)).EXT().set_ToolTipStringForListHeader(new MyE2_String("Feldender, unbeglichener Betrag in diesem Buchungsblock"));

		this.get__DBComp("BUCHUNGSBLOCK_NR").EXT_DB().set_cSortAusdruckFuerSortbuttonUP(FIBU_CONST.ORDER_BUCHUNGSBLOCK_UP);
		this.get__DBComp("BUCHUNGSBLOCK_NR").EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN(FIBU_CONST.ORDER_BUCHUNGSBLOCK_DOWN);

		this.get__DBComp("ID_ADRESSE").EXT_DB().set_cSortAusdruckFuerSortbuttonUP(FIBU_CONST.ORDER_ADRESS_UP);
		this.get__DBComp("ID_ADRESSE").EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN(FIBU_CONST.ORDER_ADRESS_DOWN);

		//		((MyE2_DB_Label_INROW)this.get("BUCHUNGSINFO")).setLineWrap(true);
		//		((MyE2_DB_Label_INROW)this.get("BUCHUNGSINFO")).EXT().set_oColExtent(new Extent(100));

		//2016-10-04: Sebastien @ Schalter fuer die alte Mahnung
		if(ENUM_MANDANT_DECISION.USE_MAHNUNG_BUTTON_ALT.is_YES()){
			this.get__Comp(FIBU_CONST.KEY_SPALTE_MAHNUNGSBUTTONS).EXT().set_oColExtent(new Extent(45));
		}
		//2016-10-04: Sebastien @ Schalter fuer die neue Mahnung
		if(ENUM_MANDANT_DECISION.USE_MAHNUNG_BUTTON_NEU.is_YES()){
			this.get__Comp(FIBU_CONST.KEY_SPALTE_MAHNUNGSBUTTONS_NEU).EXT().set_oColExtent(new Extent(45));
		}
		((MyE2_Label)this.get(FIBU_CONST.KEY_SPALTE_BETRAGBLOCK)).EXT().set_oLayout_ListTitelElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOPHeader());
		((MyE2_Label)this.get(FIBU_CONST.KEY_SPALTE_SUMME_ZAHLUNGEN)).EXT().set_oLayout_ListTitelElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOPHeader());
		((MyE2_Label)this.get(FIBU_CONST.KEY_SPALTE_REST)).EXT().set_oLayout_ListTitelElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOPHeader());

		((MyE2_Label)this.get(FIBU_CONST.KEY_SPALTE_BETRAGBLOCK)).EXT().set_oLayout_ListElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP());
		((MyE2_Label)this.get(FIBU_CONST.KEY_SPALTE_SUMME_ZAHLUNGEN)).EXT().set_oLayout_ListElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP());
		((MyE2_Label)this.get(FIBU_CONST.KEY_SPALTE_REST)).EXT().set_oLayout_ListElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP());


		this.set_oSubQueryAgent(new FIBU_LIST_FORMATING_Agent(oListContainer.get_oNaviList(),bMacheGeschlosseneListenzeilenGrau));
	}

}
