package rohstoff.Echo2BusinessLogic.LAGER_LISTE;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.Factorys.StyleFactory_TextArea_INROW;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentGrid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class LAG_KTO_LIST_ComponentMap extends E2_ComponentMAP 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 873320494293718631L;
	BTN_NAVIGATOR__JUMP_TO_FUHRE oNavigatorFuhrenliste  = null;
	
	
	public LAG_KTO_LIST_ComponentMap() throws myException
	{
		super(new LAG_KTO_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(LAG_KTO_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(LAG_KTO_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		// hier kommen die Felder
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_LAGER_KONTO")), new MyE2_String("Lagerkonto-ID"));
		
		MyE2_DB_TextArea o_LagerInfo =new MyE2_DB_TextArea(oFM.get_("ADDRESS_INFO"),350,3);
		o_LagerInfo.__setStyleFactory(new StyleFactory_TextArea_INROW());
		this.add_Component( o_LagerInfo, new MyE2_String("Lager"));
		
		// Gewicht/Datum W1
		MyE2_DB_MultiComponentColumn oColArtikel_Bemerkung = new MyE2_DB_MultiComponentColumn();
		oColArtikel_Bemerkung.setLayoutData(LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		oColArtikel_Bemerkung.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ART_INFO")),new MyE2_String("Sorte"),null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		oColArtikel_Bemerkung.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ARTIKELBEZ2")),new MyE2_String("ArtBez2"),null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		oColArtikel_Bemerkung.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BEMERKUNG_FUHRE_LAGER")),new MyE2_String("Bemerkung"),null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		
		this.add_Component(LAG_KTO_CONST.HASH_COLUMN_SORTE_BEMERKUNG,oColArtikel_Bemerkung, new MyE2_String("Sorte/Bemerkung"));
	
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ARTIKEL")), new MyE2_String("ID Sorte"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BUCHUNGSTYP")), new MyE2_String("Typ"));

		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BUCHUNGSDATUM")), new MyE2_String("Datum"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DATUM_AUFLADEN")), new MyE2_String("Ladedatum"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DATUM_ABLADEN")), new MyE2_String("Abladedatum"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MENGE")), new MyE2_String("Menge"));
		if (bib_Settigs_Mandant.get_Lager_Allow_Buchung_Buchhalterisch()){
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MENGE_BUCH")), new MyE2_String("Menge Buchh."));
		}
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MENGE_WE")), new MyE2_String("Menge WE"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MENGE_WA")), new MyE2_String("Menge WA"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("PREIS")), new MyE2_String("Preis"));
		
		
		MyE2_DB_MultiComponentColumn oCol_Nummern = new MyE2_DB_MultiComponentColumn();

//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BUCHUNGSNUMMER")), new MyE2_String("TPA-Nummer"));
		oCol_Nummern.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BUCHUNGSNUMMER")), new MyE2_String("TPA-Nummer"),null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		oCol_Nummern.add_Component(new MyE2_DB_Label_INROW(oFM.get_("POSTENNUMMER")), new MyE2_String("Postennummer"),null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		this.add_Component(LAG_KTO_CONST.HASH_COLUMN_NUMMERN,oCol_Nummern, new MyE2_String("Buchungsnummern"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("WIEGEKARTE")), new MyE2_String("Wiegekarte"));
		
		// leere spalte einbauen, die dann vom FormattingAgent gefüllt wird
		MyE2_DB_MultiComponentColumn oColFuhre_Ort = new MyE2_DB_MultiComponentColumn();
		MyE2_Row_EveryTimeEnabled oRowFuhrenNavigator = new MyE2_Row_EveryTimeEnabled();
		
		oColFuhre_Ort.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VPOS_TPA_FUHRE")), new MyE2_String("Fuhre"), null, LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		oColFuhre_Ort.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VPOS_TPA_FUHRE_ORT")), new MyE2_String("FuhrenOrt"), null, LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		// letzte Zeile: ein Button zum Popup der Fuhre, ein Button zum Sprung in die Fuhrenzentrale
		oColFuhre_Ort.add_Component(oRowFuhrenNavigator, new MyE2_String("Sprünge"), LAG_KTO_CONST.HASH_BUTTON_SHOW_FUHRE, LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		oNavigatorFuhrenliste = new BTN_NAVIGATOR__JUMP_TO_FUHRE(this, null);
		
		
		oRowFuhrenNavigator.EXT().set_oCompTitleInList(oNavigatorFuhrenliste);
		oRowFuhrenNavigator.EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		
				
		this.add_Component(LAG_KTO_CONST.HASH_COLUMN_FUHRE_FUHRENORT,oColFuhre_Ort, new MyE2_String("Fuhre/Fuhrenort"));
		
		
		//2011-05-12: fuhren-kosten-button
		MyE2_DB_MultiComponentGrid   oGridKostenErfassung = new MyE2_DB_MultiComponentGrid(1);
		oGridKostenErfassung.EXT().set_oLayout_ListElement(LayoutDataFactory.get_GridLayoutGridCenter_TOP(E2_INSETS.I_1_1_1_1));
		oGridKostenErfassung.add_Component(new LAG_KTO_LIST_BT_Eingabe_Kosten(),new MyE2_String("Kosten"),LAG_KTO_CONST.HASH_LIST_BT_KOSTEN_ERFASSUNG,LayoutDataFactory.get_GridLayoutGridCenter(E2_INSETS.I_2_2_2_2));
		oGridKostenErfassung.add_Component(new LAG_KTO_LIST_INFO_FELD_KOSTEN(oFM.get_("C__LAGER_KONTO")),new MyE2_String("Kosten-Info"),null,LayoutDataFactory.get_GridLayoutGridCenter(E2_INSETS.I_2_2_2_2));
		this.add_Component(LAG_KTO_CONST.HASH_COLUMN_KOSTEN_ERFASSUNG,oGridKostenErfassung, new MyE2_String("Kosten"));
		

		
		
		// Kunde jetzt 2-Zeilig
		// this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("LIEFERANT_INFO")), new MyE2_String("Kunde"));
		MyE2_DB_MultiComponentColumn oColKundenIDs = new MyE2_DB_MultiComponentColumn();
		oColKundenIDs.setLayoutData(LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		oColKundenIDs.add_Component(new MyE2_DB_Label_INROW(oFM.get_("LIEFERANT_ID_ADRESSE_LAGER")), new MyE2_String("ID Adr.Lager"),null,LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_2_0_2_0));
		oColKundenIDs.add_Component(new MyE2_DB_Label_INROW(oFM.get_("LIEFERANT_ID_ADRESSE")), new MyE2_String("ID Adresse"),null,LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_2_0_2_0));
		this.add_Component(LAG_KTO_CONST.HASH_COLUMN_ADRESS_ID,oColKundenIDs, new MyE2_String("ID Adr.Lager/ID Adresse"));

		
		MyE2_DB_TextArea o_KundeInfo =new MyE2_DB_TextArea(oFM.get_("LIEFERANT_INFO"),350,3);
		o_KundeInfo.__setStyleFactory(new StyleFactory_TextArea_INROW());
		this.add_Component( o_KundeInfo, new MyE2_String("Lieferadresse / (Kunde)"));
		
		
		MyE2_DB_TextArea o_SpedInfo =new MyE2_DB_TextArea(oFM.get_("SPEDITION_INFO"),350,2);
		o_SpedInfo.__setStyleFactory(new StyleFactory_TextArea_INROW());
		this.add_Component( o_SpedInfo, new MyE2_String("Spedition"));

		
		MyE2_DB_MultiComponentColumn oColKennzeichen = new MyE2_DB_MultiComponentColumn();
		oColKennzeichen.setLayoutData(LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		oColKennzeichen.add_Component(new MyE2_DB_Label_INROW(oFM.get_("TRANSPORTKENNZEICHEN")),new MyE2_String("KFZ-Kennz."),null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		oColKennzeichen.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANHAENGERKENNZEICHEN")),new MyE2_String("Anh.-Kennz."),null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		this.add_Component(LAG_KTO_CONST.HASH_COLUMN_FUHRE_KENNZEICHEN,oColKennzeichen, new MyE2_String("Kennzeichen/Anhänger"));
		
		MyE2_DB_MultiComponentColumn oColNummernPapiere = new MyE2_DB_MultiComponentColumn();
		oColNummernPapiere.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BUCHUNGSNUMMER_FUHRE")), new MyE2_String("Liefer-/AbholscheinNr"),null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		oColNummernPapiere.add_Component(new MyE2_Label(),new MyE2_String("Re-/GutschriftNr"),LAG_KTO_CONST.HASH_FIELD_RECHNR, null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		oColNummernPapiere.add_Component(new MyE2_Label(),new MyE2_String("Kontraktnummer"),LAG_KTO_CONST.HASH_FIELD_KONTRAKTNUMMER, null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		this.add_Component(LAG_KTO_CONST.HASH_COLUMN_NUMMERN_PAPIERE,oColNummernPapiere, new MyE2_String("Liefer-Abholschein/Re-Gutschrift/Kontraktnummer"));
		
		// Storno
		MyE2_DB_MultiComponentColumn oColStorno = new MyE2_DB_MultiComponentColumn();
		oColStorno.setLayoutData(LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		oColStorno.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STORNO")), new MyE2_String("Storno"),null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		oColStorno.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_LAGER_KONTO_PARENT")), new MyE2_String("Storno-Bezug"),null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		this.add_Component(LAG_KTO_CONST.HASH_COLUMN_STORNO,oColStorno, new MyE2_String("Storno/Stornobezug"));

		// Status
		MyE2_DB_MultiComponentColumn oColStatus = new MyE2_DB_MultiComponentColumn();
		oColStatus.setLayoutData(LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		oColStatus.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STATUS_BUCHUNG")), new MyE2_String("Status Fuhre"),null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		oColStatus.add_Component(new MyE2_DB_Label_INROW(oFM.get_("OHNE_ABRECHNUNG")), new MyE2_String("Ohne Abrechnung"),null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		this.add_Component(LAG_KTO_CONST.HASH_COLUMN_STATUS,oColStatus, new MyE2_String("Status Buchung / Fuhre Ohne Abrechnung"));

		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("IST_KOMPLETT")), new MyE2_String("Komplett verbucht"));

		
		
		this.get__Comp("ID_LAGER_KONTO").EXT().set_bIsVisibleInList(false);
		
		// Layout der Datenspalten
		GridLayoutData layout = new GridLayoutData();
		layout.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		this.get__Comp("ADDRESS_INFO").EXT().set_oLayout_ListElement(layout);
		this.get__Comp_From_RealComponents("ART_INFO").EXT().set_oLayout_ListElement(layout);
		
		this.get__Comp("ID_LAGER_KONTO").EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridLeft_DARK_TOP(null));
		this.get__Comp("ADDRESS_INFO").EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridLeft_DARK_TOP(null));
		this.get__Comp_From_RealComponents("ART_INFO").EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridLeft_DARK_TOP(null));
		
		this.get__Comp("BUCHUNGSTYP").EXT().set_bIsVisibleInList(false);
		this.get__Comp("BUCHUNGSDATUM").EXT().set_bIsVisibleInList(false);
		this.get__Comp("BUCHUNGSDATUM").EXT().set_ToolTipStringForListHeader(new MyE2_String("Sortiert nach Buchungsdatum (auf/ab) und Sorte (auf)"));
		this.get__DBComp("BUCHUNGSDATUM").EXT_DB().set_cSortAusdruckFuerSortbuttonUP("JT_LAGER_KONTO.BUCHUNGSDATUM ASC, JT_ARTIKEL.ANR1 ASC");
		this.get__DBComp("BUCHUNGSDATUM").EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN("JT_LAGER_KONTO.BUCHUNGSDATUM DESC, JT_ARTIKEL.ANR1 ASC");
		
		
		this.get_hmRealDBComponents().get("ART_INFO").EXT_DB().set_cSortAusdruckFuerSortbuttonUP("JT_ARTIKEL.ANR1 ASC, JT_LAGER_KONTO.BUCHUNGSDATUM DESC");
		this.get_hmRealDBComponents().get("ART_INFO").EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN("JT_ARTIKEL.ANR1 DESC, JT_LAGER_KONTO.BUCHUNGSDATUM DESC");
		this.get__Comp_From_RealComponents("ART_INFO").EXT().set_ToolTipStringForListHeader(new MyE2_String("Sortiert nach Sorte (auf/ab) und Buchungsdatum (ab)"));
		
		// Defaultbreite der Spalten
		this.get__Comp("ADDRESS_INFO").EXT().set_oColExtent(new Extent(400));
		this.get__Comp_From_RealComponents("ART_INFO").EXT().set_oColExtent(new Extent(250));
		
		this.set_oSubQueryAgent(new LAG_KTO_LIST_FORMATING_Agent());
		
		
		
	}

}
