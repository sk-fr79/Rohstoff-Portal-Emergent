 
package rohstoff.businesslogic.bewegung.lager.list_bewegung;
  
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.Factorys.StyleFactory_TextArea_INROW;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.basics4project.DB_ENUMS.AH7_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_AH7_PROFIL;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWEGUNG.ATOM_LAG_CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWEGUNG.ATOM_LAG_LIST_BTN_NAVIGATOR__JUMP_TO_FUHRE;
import rohstoff.businesslogic.bewegung.lager.list_bewegung.BG_Lager_Bewegung_CONST.BG_Lager_Bewegung_NAMES;
//import panter.gmbh.basics4project.DB_ENUMS.BG_LADUNG;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
//import panter.gmbh.basics4project.DB_RECORDS.RECORD_BG_LADUNG;
  
@Deprecated
public class BG_Lager_Bewegung_LIST_ComponentMap extends E2_ComponentMAP  {
	
	BG_Lager_Bewegung_LIST_bt_Jump_To_Fuhre oNavigatorFuhrenliste;
	
    public BG_Lager_Bewegung_LIST_ComponentMap() throws myException  {
        super(new BG_Lager_Bewegung_LIST_SqlFieldMAP());
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(BG_Lager_Bewegung_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,    new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(BG_Lager_Bewegung_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,    new E2_ButtonListMarker(),new MyE2_String("?"));
        
        //hier kommen die Felder  
        
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_bg_ladung),true),     new MyE2_String("ID Ladung"));
        
        
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_("EW_FW")), new MyE2_String("EW"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("ABRECHENBAR")), new MyE2_String("Abr."));
		
	    this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("LAGER_INFO"),true),     new MyE2_String("Lager"));
//	    this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_artikel),true),     new MyE2_String("id_artikel"));
	    
	    MyE2_DB_MultiComponentColumn oColArtikel_Bemerkung = new MyE2_DB_MultiComponentColumn();
	    oColArtikel_Bemerkung.setLayoutData(LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
	    oColArtikel_Bemerkung.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("ART_INFO")),new MyE2_String("Sorte"),null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
//	    oColArtikel_Bemerkung.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.artbez2)),new MyE2_String("ArtBez2"),null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
//	    oColArtikel_Bemerkung.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.bemerkung_intern)),new MyE2_String("Bemerkung"),null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
	    this.add_Component(BG_Lager_Bewegung_CONST.HASH_COLUMN_SORTE_BEMERKUNG,oColArtikel_Bemerkung, new MyE2_String("Sorte/Bemerkung"));
	   

//	    this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.leistungsdatum),true),     new MyE2_String("Datum"));

	    this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_Lager_Bewegung_CONST.MENGE_BRUTTO_WE),true),     new MyE2_String("WE brutto"));
	    this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_Lager_Bewegung_CONST.MENGE_NETTO_WE),true),     new MyE2_String("WE netto"));
	    this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_Lager_Bewegung_CONST.MENGE_BRUTTO_WA),true),     new MyE2_String("WA brutto"));
	    this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_Lager_Bewegung_CONST.MENGE_NETTO_WA),true),     new MyE2_String("WA netto"));
	    
	    this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_Lager_Bewegung_CONST.MENGE_BRUTTO_VZ),true),     new MyE2_String("Menge Brutto"));
//	    this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.abzug_menge),true),     new MyE2_String("Mengenabzug"));
	    this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_Lager_Bewegung_CONST.MENGE_NETTO_VZ),true),     new MyE2_String("Menge Netto"));
	    
	    this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("A_E_PREIS"),true),     new MyE2_String("E-Preis"));
//	    this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.e_preis_result_netto_mge),true),     new MyE2_String("Result. EPreis"));
	    
	    this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("S2_ID_ADRESSE_BASIS"),true),     new MyE2_String("ID Kunde"));
//	    this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("KUNDEN_INFO"),true),     new MyE2_String("Lieferardrese/Kunde"));
		MyE2_DB_TextArea o_KundeInfo =new MyE2_DB_TextArea(oFM.get_("KUNDEN_INFO"),350,3);
		o_KundeInfo.__setStyleFactory(new StyleFactory_TextArea_INROW());
		this.add_Component( o_KundeInfo, new MyE2_String("Lieferadresse / (Kunde)"));
		
	    this.add_Component(new MyE2_DB_CheckBox(oFM.get_(BG_Lager_Bewegung_CONST.LADUNG_STORNO)), new MyE2_String("Storniert"));
	    this.add_Component(new MyE2_DB_CheckBox(oFM.get_(BG_Lager_Bewegung_CONST.LADUNG_DELETED)), new MyE2_String("Gelöscht"));
		
	    
		// leere spalte einbauen, die dann vom  gefüllt wird
		MyE2_DB_MultiComponentColumn oColBewegungIDS = new MyE2_DB_MultiComponentColumn();
		
		oColBewegungIDS.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_BG_VEKTOR")), new MyE2_String("Vektor"), null, LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		oColBewegungIDS.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_BG_ATOM")), new MyE2_String("Atom"), null, LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		oColBewegungIDS.add_Component(new MyE2_DB_Label_INROW(oFM.get_("A_ID_VPOS_TPA_FUHRE")), new MyE2_String("Fuhre"), null, LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));

		MyE2_Row_EveryTimeEnabled oRowFuhrenNavigator = new MyE2_Row_EveryTimeEnabled();
		oColBewegungIDS.add_Component(oRowFuhrenNavigator, new MyE2_String("Sprünge"), BG_Lager_Bewegung_CONST.HASH_BUTTON_SHOW_FUHRE, LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		oNavigatorFuhrenliste = new BG_Lager_Bewegung_LIST_bt_Jump_To_Fuhre(this, null);
		oRowFuhrenNavigator.EXT().set_oCompTitleInList(oNavigatorFuhrenliste);
		oRowFuhrenNavigator.EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		this.add_Component(BG_Lager_Bewegung_CONST.HASH_COLUMN_FUHRE_FUHRENORT,oColBewegungIDS, new MyE2_String("Bewegung-IDs"));

	    
		
	    
	    
		
		
//	    this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.artbez2),true),     new MyE2_String("ArtBez2"));
//	    this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.bemerkung_intern),true),     new MyE2_String("Bemerkung intern"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.anr1),true),     new MyE2_String("anr1"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.anr2),true),     new MyE2_String("anr2"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.artbez1),true),     new MyE2_String("artbez1"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.bemerkung_extern),true),     new MyE2_String("bemerkung_extern"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.eu_steuer_vermerk),true),     new MyE2_String("eu_steuer_vermerk"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.e_preis_result_brutto_mge),true),     new MyE2_String("e_preis_result_brutto_mge"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.gesamtpreis),true),     new MyE2_String("gesamtpreis"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.gpreis_abz_auf_nettomge),true),     new MyE2_String("gpreis_abz_auf_nettomge"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.gpreis_abz_mge),true),     new MyE2_String("gpreis_abz_mge"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.gpreis_abz_vorauszahlung),true),     new MyE2_String("gpreis_abz_vorauszahlung"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_adresse_besitzer),true),     new MyE2_String("id_adresse_besitzer"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_artikel_bez),true),     new MyE2_String("id_artikel_bez"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_bg_del_info),true),     new MyE2_String("id_bg_del_info"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_bg_pruefprot_abschluss),true),     new MyE2_String("id_bg_pruefprot_abschluss"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_bg_pruefprot_menge),true),     new MyE2_String("id_bg_pruefprot_menge"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_bg_pruefprot_preis),true),     new MyE2_String("id_bg_pruefprot_preis"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_bg_station),true),     new MyE2_String("id_bg_station"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_bg_storno_info),true),     new MyE2_String("id_bg_storno_info"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_bg_vektor),true),     new MyE2_String("id_bg_vektor"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_lager_konto),true),     new MyE2_String("id_lager_konto"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_tax),true),     new MyE2_String("id_tax"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_vpos_kon),true),     new MyE2_String("id_vpos_kon"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_vpos_std),true),     new MyE2_String("id_vpos_std"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.kontraktzwang),true),     new MyE2_String("kontraktzwang"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.kosten_produkt),true),     new MyE2_String("kosten_produkt"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.mengenvorzeichen),true),     new MyE2_String("mengenvorzeichen"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.postennummer),true),     new MyE2_String("postennummer"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.steuersatz),true),     new MyE2_String("steuersatz"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.timestamp_in_mask),true),     new MyE2_String("timestamp_in_mask"));
//        this._setLineWrapListHeader(true 
//                                  ,BG_LADUNG.abzug_menge.fn()
//                                  ,BG_LADUNG.anr1.fn()
//                                  ,BG_LADUNG.anr2.fn()
//                                  ,BG_LADUNG.artbez1.fn()
//                                  ,BG_LADUNG.artbez2.fn()
//                                  ,BG_LADUNG.bemerkung_extern.fn()
//                                  ,BG_LADUNG.bemerkung_intern.fn()
//                                  ,BG_LADUNG.eu_steuer_vermerk.fn()
//                                  ,BG_LADUNG.e_preis_result_brutto_mge.fn()
//                                  ,BG_LADUNG.e_preis_result_netto_mge.fn()
//                                  ,BG_LADUNG.gesamtpreis.fn()
//                                  ,BG_LADUNG.gpreis_abz_auf_nettomge.fn()
//                                  ,BG_LADUNG.gpreis_abz_mge.fn()
//                                  ,BG_LADUNG.gpreis_abz_vorauszahlung.fn()
//                                  ,BG_LADUNG.id_adresse_besitzer.fn()
//                                  ,BG_LADUNG.id_artikel.fn()
//                                  ,BG_LADUNG.id_artikel_bez.fn()
//                                  ,BG_LADUNG.id_bg_atom.fn()
//                                  ,BG_LADUNG.id_bg_del_info.fn()
//                                  ,BG_LADUNG.id_bg_ladung.fn()
//                                  ,BG_LADUNG.id_bg_pruefprot_abschluss.fn()
//                                  ,BG_LADUNG.id_bg_pruefprot_menge.fn()
//                                  ,BG_LADUNG.id_bg_pruefprot_preis.fn()
//                                  ,BG_LADUNG.id_bg_station.fn()
//                                  ,BG_LADUNG.id_bg_storno_info.fn()
//                                  ,BG_LADUNG.id_bg_vektor.fn()
//                                  ,BG_LADUNG.id_lager_konto.fn()
//                                  ,BG_LADUNG.id_tax.fn()
//                                  ,BG_LADUNG.id_vpos_kon.fn()
//                                  ,BG_LADUNG.id_vpos_std.fn()
//                                  ,BG_LADUNG.kontraktzwang.fn()
//                                  ,BG_LADUNG.kosten_produkt.fn()
//                                  ,BG_LADUNG.leistungsdatum.fn()
//                                  ,BG_LADUNG.menge.fn()
//                                  ,BG_LADUNG.mengenvorzeichen.fn()
//                                  ,BG_LADUNG.menge_netto.fn()
//                                  ,BG_LADUNG.postennummer.fn()
//                                  ,BG_LADUNG.steuersatz.fn()
//                                  ,BG_LADUNG.timestamp_in_mask.fn()
//        );
//        
//        RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
//       	this._setLayoutElements(gldElementCenter
//                                 ,BG_LADUNG.abzug_menge.fn()
//                                 ,BG_LADUNG.anr1.fn()
//                                 ,BG_LADUNG.anr2.fn()
//                                 ,BG_LADUNG.artbez1.fn()
//                                 ,BG_LADUNG.artbez2.fn()
//                                 ,BG_LADUNG.bemerkung_extern.fn()
//                                 ,BG_LADUNG.bemerkung_intern.fn()
//                                 ,BG_LADUNG.eu_steuer_vermerk.fn()
//                                 ,BG_LADUNG.e_preis_result_brutto_mge.fn()
//                                 ,BG_LADUNG.e_preis_result_netto_mge.fn()
//                                 ,BG_LADUNG.gesamtpreis.fn()
//                                 ,BG_LADUNG.gpreis_abz_auf_nettomge.fn()
//                                 ,BG_LADUNG.gpreis_abz_mge.fn()
//                                 ,BG_LADUNG.gpreis_abz_vorauszahlung.fn()
//                                 ,BG_LADUNG.id_adresse_besitzer.fn()
//                                 ,BG_LADUNG.id_artikel.fn()
//                                 ,BG_LADUNG.id_artikel_bez.fn()
//                                 ,BG_LADUNG.id_bg_atom.fn()
//                                 ,BG_LADUNG.id_bg_del_info.fn()
//                                 ,BG_LADUNG.id_bg_ladung.fn()
//                                 ,BG_LADUNG.id_bg_pruefprot_abschluss.fn()
//                                 ,BG_LADUNG.id_bg_pruefprot_menge.fn()
//                                 ,BG_LADUNG.id_bg_pruefprot_preis.fn()
//                                 ,BG_LADUNG.id_bg_station.fn()
//                                 ,BG_LADUNG.id_bg_storno_info.fn()
//                                 ,BG_LADUNG.id_bg_vektor.fn()
//                                 ,BG_LADUNG.id_lager_konto.fn()
//                                 ,BG_LADUNG.id_tax.fn()
//                                 ,BG_LADUNG.id_vpos_kon.fn()
//                                 ,BG_LADUNG.id_vpos_std.fn()
//                                 ,BG_LADUNG.kontraktzwang.fn()
//                                 ,BG_LADUNG.kosten_produkt.fn()
//                                 ,BG_LADUNG.leistungsdatum.fn()
//                                 ,BG_LADUNG.menge.fn()
//                                 ,BG_LADUNG.mengenvorzeichen.fn()
//                                 ,BG_LADUNG.menge_netto.fn()
//                                 ,BG_LADUNG.postennummer.fn()
//                                 ,BG_LADUNG.steuersatz.fn()
//                                 ,BG_LADUNG.timestamp_in_mask.fn()
//      	);
//      	
//      	RB_gld gldTitelCenter = 	new RB_gld()._center_top()._ins(1,2,1,1)._col(new E2_ColorDark());
//       	this._setLayoutElements(gldTitelCenter
//                                 ,BG_LADUNG.abzug_menge.fn()
//                                 ,BG_LADUNG.anr1.fn()
//                                 ,BG_LADUNG.anr2.fn()
//                                 ,BG_LADUNG.artbez1.fn()
//                                 ,BG_LADUNG.artbez2.fn()
//                                 ,BG_LADUNG.bemerkung_extern.fn()
//                                 ,BG_LADUNG.bemerkung_intern.fn()
//                                 ,BG_LADUNG.eu_steuer_vermerk.fn()
//                                 ,BG_LADUNG.e_preis_result_brutto_mge.fn()
//                                 ,BG_LADUNG.e_preis_result_netto_mge.fn()
//                                 ,BG_LADUNG.gesamtpreis.fn()
//                                 ,BG_LADUNG.gpreis_abz_auf_nettomge.fn()
//                                 ,BG_LADUNG.gpreis_abz_mge.fn()
//                                 ,BG_LADUNG.gpreis_abz_vorauszahlung.fn()
//                                 ,BG_LADUNG.id_adresse_besitzer.fn()
//                                 ,BG_LADUNG.id_artikel.fn()
//                                 ,BG_LADUNG.id_artikel_bez.fn()
//                                 ,BG_LADUNG.id_bg_atom.fn()
//                                 ,BG_LADUNG.id_bg_del_info.fn()
//                                 ,BG_LADUNG.id_bg_ladung.fn()
//                                 ,BG_LADUNG.id_bg_pruefprot_abschluss.fn()
//                                 ,BG_LADUNG.id_bg_pruefprot_menge.fn()
//                                 ,BG_LADUNG.id_bg_pruefprot_preis.fn()
//                                 ,BG_LADUNG.id_bg_station.fn()
//                                 ,BG_LADUNG.id_bg_storno_info.fn()
//                                 ,BG_LADUNG.id_bg_vektor.fn()
//                                 ,BG_LADUNG.id_lager_konto.fn()
//                                 ,BG_LADUNG.id_tax.fn()
//                                 ,BG_LADUNG.id_vpos_kon.fn()
//                                 ,BG_LADUNG.id_vpos_std.fn()
//                                 ,BG_LADUNG.kontraktzwang.fn()
//                                 ,BG_LADUNG.kosten_produkt.fn()
//                                 ,BG_LADUNG.leistungsdatum.fn()
//                                 ,BG_LADUNG.menge.fn()
//                                 ,BG_LADUNG.mengenvorzeichen.fn()
//                                 ,BG_LADUNG.menge_netto.fn()
//                                 ,BG_LADUNG.postennummer.fn()
//                                 ,BG_LADUNG.steuersatz.fn()
//                                 ,BG_LADUNG.timestamp_in_mask.fn()
//      	);
      	
        this.set_oSubQueryAgent(new BG_Lager_Bewegung_LIST_FORMATING_Agent());
        	
        //this.set_Factory4Records(new factory4Records());
    }
    
    
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
			return null;
//            return new RECORD_BG_LADUNG(cID_MAINTABLE);
        }
        
    }
    
    
}
 
