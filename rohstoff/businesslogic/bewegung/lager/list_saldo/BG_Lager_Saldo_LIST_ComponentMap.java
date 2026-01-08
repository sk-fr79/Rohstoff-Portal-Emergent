 
package rohstoff.businesslogic.bewegung.lager.list_saldo;
  
import java.util.ArrayList;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
//import panter.gmbh.basics4project.DB_ENUMS.BG_LADUNG;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
//import panter.gmbh.basics4project.DB_RECORDS.RECORD_BG_LADUNG;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung.lager.list_saldo.BG_Lager_Saldo_CONST.BG_Lager_Saldo_NAMES;
import rohstoff.businesslogic.bewegung.lager.saldo.BG_LagerSaldoErmittlung;
import rohstoff.businesslogic.bewegung.lager.vertragsmengen.BG_Lager_KontraktmengenErmittlung;

@Deprecated 
public class BG_Lager_Saldo_LIST_ComponentMap extends E2_ComponentMAP  {
	
	
		// Die Component-Map bekommt eine Referenz auf den Vertragsmengen-Ermittler
		private BG_Lager_KontraktmengenErmittlung m_oVertragsMengen = null;
		
		// die Component-Map bekommt eine Referenz auf den Saldo-Ermittler
		private BG_LagerSaldoErmittlung m_oSaldoErmittlungen = null;
			
	
	
		public BG_Lager_Saldo_LIST_ComponentMap(	BG_Lager_KontraktmengenErmittlung oVertragsMengen, 
													BG_LagerSaldoErmittlung oSaldoErmittlung,
													ArrayList<BG_LagerSaldoErmittlung> alSaldoErmittlungAdd  
    															
				) throws myException  {
        super(new BG_Lager_Saldo_LIST_SqlFieldMAP());
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(BG_Lager_Saldo_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,    new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(BG_Lager_Saldo_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,    new E2_ButtonListMarker(),new MyE2_String("?"));

        
		// Objekt zur Vertragsmengen-Ermittlung merken
		this.m_oVertragsMengen 		= oVertragsMengen;
		this.m_oSaldoErmittlungen 	= oSaldoErmittlung;
		

		//hier kommen die Felder	
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(BG_LADUNG.id_bg_ladung.fn())), new MyE2_String("Ladung-ID"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("S1_ID_ADRESSE")), new MyE2_String("ID Adresse"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(BG_Lager_Saldo_NAMES.ADRESS_INFO.name())), new MyE2_String("Adresse"));
		
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(BG_LADUNG.id_artikel)), new MyE2_String("ID Sorte"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(BG_Lager_Saldo_NAMES.ART_INFO.name())), new MyE2_String("Sorte"));
		
		
		this.add_Component(BG_Lager_Saldo_NAMES.SaldoMitInventur.name(), new MyE2_Label(),new MyE2_String("Lagerbestand aktuell"));
		this.add_Component(BG_Lager_Saldo_NAMES.SaldoDynamisch1.name(), new MyE2_Label(),new MyE2_String("Lagerbestand1 zusätzlich"));
		this.add_Component(BG_Lager_Saldo_NAMES.SaldoDynamisch2.name(), new MyE2_Label(),new MyE2_String("Lagerbestand2 zusätzlich"));
		this.add_Component(BG_Lager_Saldo_NAMES.SaldoDelta.name(), new MyE2_Label(),new MyE2_String("Lagerbestandsänderung"));
		
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EINH_EINHEITKURZ")), new MyE2_String("Einheit"));
		this.add_Component(BG_Lager_Saldo_NAMES.InventurDatum.name(), new MyE2_Label(),new MyE2_String("Inventurdatum"));
		this.add_Component(BG_Lager_Saldo_NAMES.InventurMenge.name(), new MyE2_Label(),new MyE2_String("Inventurmenge"));
		
		this.add_Component(BG_Lager_Saldo_NAMES.GesamtRestmenge.name(), new MyE2_Label(),new MyE2_String("Mögl. Saldo mit Kontrakten"));
		this.add_Component(BG_Lager_Saldo_NAMES.EKLagermenge.name(), new MyE2_Label(),new MyE2_String("Summe EK-Kontr."));
		this.add_Component(BG_Lager_Saldo_NAMES.EKRestmenge.name(), new MyE2_Label(),new MyE2_String("Restmengen EK-Kontr."));
		this.add_Component(BG_Lager_Saldo_NAMES.VKLagermenge.name(), new MyE2_Label(),new MyE2_String("Summe VK-Kontr."));
		this.add_Component(BG_Lager_Saldo_NAMES.VKRestmenge.name(), new MyE2_Label(),new MyE2_String("Restmengen VK-Kontr."));
		
		
		
//		this.get__Comp(BG_LADUNG.id_bg_ladung.fn()).EXT().set_bIsVisibleInList(false);

		
		// Gridlayout für den Header
		GridLayoutData  GLHead = new GridLayoutData();
		GLHead.setAlignment(new Alignment(Alignment.RIGHT, Alignment.TOP));
		GLHead.setBackground(new E2_ColorDark());
		
		// Gridlayout für die Liste
		GridLayoutData  GLList = new GridLayoutData();
		GLList.setAlignment(new Alignment(Alignment.RIGHT, Alignment.CENTER));
		

		this.get(BG_Lager_Saldo_NAMES.InventurDatum.name()).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(BG_Lager_Saldo_NAMES.InventurDatum.name()).EXT().set_oLayout_ListElement(GLList);
		this.get(BG_Lager_Saldo_NAMES.InventurDatum.name()).EXT().set_ToolTipStringForListHeader(new MyE2_String("Inventurdatum "));

		this.get(BG_Lager_Saldo_NAMES.InventurMenge.name()).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(BG_Lager_Saldo_NAMES.InventurMenge.name()).EXT().set_oLayout_ListElement(GLList);
		this.get(BG_Lager_Saldo_NAMES.InventurMenge.name()).EXT().set_ToolTipStringForListHeader(new MyE2_String("Inventurmenge "));

		
		

		this.get(BG_Lager_Saldo_NAMES.EKLagermenge.name()).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(BG_Lager_Saldo_NAMES.EKLagermenge.name()).EXT().set_oLayout_ListElement(GLList);
		this.get(BG_Lager_Saldo_NAMES.EKLagermenge.name()).EXT().set_ToolTipStringForListHeader(new MyE2_String("Gesamtmenge der offenen EK-Kontrakte."));
		
		this.get(BG_Lager_Saldo_NAMES.GesamtRestmenge.name()).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(BG_Lager_Saldo_NAMES.GesamtRestmenge.name()).EXT().set_oLayout_ListElement(GLList);
		this.get(BG_Lager_Saldo_NAMES.GesamtRestmenge.name()).EXT().set_ToolTipStringForListHeader(new MyE2_String("Mögliche Gesamtmenge, d.h. aktueller Saldo plus der Restmengen der EK-Kontrakte, abzüglich der Restmengen der VK-Kontrakte."));
		
		this.get(BG_Lager_Saldo_NAMES.EKRestmenge.name()).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(BG_Lager_Saldo_NAMES.EKRestmenge.name()).EXT().set_oLayout_ListElement(GLList);
		this.get(BG_Lager_Saldo_NAMES.EKRestmenge.name()).EXT().set_ToolTipStringForListHeader(new MyE2_String("Restmengen der offenen EK-Kontrakte."));
		
		
		this.get(BG_Lager_Saldo_NAMES.VKLagermenge.name()).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(BG_Lager_Saldo_NAMES.VKLagermenge.name()).EXT().set_oLayout_ListElement(GLList);
		this.get(BG_Lager_Saldo_NAMES.VKLagermenge.name()).EXT().set_ToolTipStringForListHeader(new MyE2_String("Gesamtmenge der offenen VK-Kontrakte"));
		
		this.get(BG_Lager_Saldo_NAMES.VKRestmenge.name()).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(BG_Lager_Saldo_NAMES.VKRestmenge.name()).EXT().set_oLayout_ListElement(GLList);
		this.get(BG_Lager_Saldo_NAMES.VKRestmenge.name()).EXT().set_ToolTipStringForListHeader(new MyE2_String("Restmengen der offenen VK-Kontrakte."));

		
		
		// Layout der Datenspalten
		GridLayoutData layout = new GridLayoutData();
		layout.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		
		this.get__Comp(BG_Lager_Saldo_NAMES.ADRESS_INFO.name()).EXT().set_oLayout_ListElement(layout);
		this.get__Comp(BG_Lager_Saldo_NAMES.ADRESS_INFO.name()).EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridLeft_DARK(null));
		this.get__Comp(BG_Lager_Saldo_NAMES.ADRESS_INFO.name()).EXT().set_ToolTipStringForListHeader(new MyE2_String("Lager-Adresse"));

		this.get__Comp(BG_Lager_Saldo_NAMES.ART_INFO.name()).EXT().set_oLayout_ListElement(layout);
		this.get__Comp(BG_Lager_Saldo_NAMES.ART_INFO.name()).EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridLeft_DARK(null));
		this.get__Comp(BG_Lager_Saldo_NAMES.ART_INFO.name()).EXT().set_ToolTipStringForListHeader(new MyE2_String("Sortennr. und Bezeichnung."));
		
		this.get__Comp("ID_ARTIKEL").EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridLeft_DARK(null));
		this.get__Comp("ID_ARTIKEL").EXT().set_ToolTipStringForListHeader(new MyE2_String("ArtikelID"));

		this.get__Comp(BG_Lager_Saldo_NAMES.SaldoMitInventur.name()).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get__Comp(BG_Lager_Saldo_NAMES.SaldoMitInventur.name()).EXT().set_oLayout_ListElement(GLList);
		this.get__Comp(BG_Lager_Saldo_NAMES.SaldoMitInventur.name()).EXT().set_ToolTipStringForListHeader(new MyE2_String("Aktuelle Lagermenge"));
		

		this.get__Comp(BG_Lager_Saldo_NAMES.SaldoDynamisch1.name()).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get__Comp(BG_Lager_Saldo_NAMES.SaldoDynamisch1.name()).EXT().set_oLayout_ListElement(GLList);
		this.get__Comp(BG_Lager_Saldo_NAMES.SaldoDynamisch1.name()).EXT().set_ToolTipStringForListHeader(new MyE2_String("Lagersaldo1 zum angegebenen Datum"));
		
		this.get__Comp(BG_Lager_Saldo_NAMES.SaldoDynamisch2.name()).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get__Comp(BG_Lager_Saldo_NAMES.SaldoDynamisch2.name()).EXT().set_oLayout_ListElement(GLList);
		this.get__Comp(BG_Lager_Saldo_NAMES.SaldoDynamisch2.name()).EXT().set_ToolTipStringForListHeader(new MyE2_String("Lagersaldo2 zum angegebenen Datum"));
		
		this.get__Comp(BG_Lager_Saldo_NAMES.SaldoDelta.name()).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get__Comp(BG_Lager_Saldo_NAMES.SaldoDelta.name()).EXT().set_oLayout_ListElement(GLList);
		this.get__Comp(BG_Lager_Saldo_NAMES.SaldoDelta.name()).EXT().set_ToolTipStringForListHeader(new MyE2_String("Lagersaldo-Änderung"));
		
//		ATOM_SALDO_LIST_FORMATING_Agent oFormattingAgent =new ATOM_SALDO_LIST_FORMATING_Agent ( m_oVertragsMengen, m_oSaldoErmittlungen)
//																	.setAdditionalSaldo(alSaldoErmittlungAdd);

        
        
        
        
        //hier kommen die Felder  
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.abzug_menge),true),     new MyE2_String("abzug_menge"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.anr1),true),     new MyE2_String("anr1"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.anr2),true),     new MyE2_String("anr2"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.artbez1),true),     new MyE2_String("artbez1"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.artbez2),true),     new MyE2_String("artbez2"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.bemerkung_extern),true),     new MyE2_String("bemerkung_extern"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.bemerkung_intern),true),     new MyE2_String("bemerkung_intern"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.eu_steuer_vermerk),true),     new MyE2_String("eu_steuer_vermerk"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.e_preis_result_brutto_mge),true),     new MyE2_String("e_preis_result_brutto_mge"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.e_preis_result_netto_mge),true),     new MyE2_String("e_preis_result_netto_mge"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.gesamtpreis),true),     new MyE2_String("gesamtpreis"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.gpreis_abz_auf_nettomge),true),     new MyE2_String("gpreis_abz_auf_nettomge"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.gpreis_abz_mge),true),     new MyE2_String("gpreis_abz_mge"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.gpreis_abz_vorauszahlung),true),     new MyE2_String("gpreis_abz_vorauszahlung"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_adresse_besitzer),true),     new MyE2_String("id_adresse_besitzer"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_artikel),true),     new MyE2_String("id_artikel"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_artikel_bez),true),     new MyE2_String("id_artikel_bez"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_bg_atom),true),     new MyE2_String("id_bg_atom"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_bg_del_info),true),     new MyE2_String("id_bg_del_info"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.id_bg_ladung),true),     new MyE2_String("id_bg_ladung"));
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
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.leistungsdatum),true),     new MyE2_String("leistungsdatum"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.menge),true),     new MyE2_String("menge"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.mengenvorzeichen),true),     new MyE2_String("mengenvorzeichen"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_LADUNG.menge_netto),true),     new MyE2_String("menge_netto"));
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
      	
        this.set_oSubQueryAgent(new BG_Lager_Saldo_LIST_FORMATING_Agent(m_oVertragsMengen, m_oSaldoErmittlungen ).setAdditionalSaldo(alSaldoErmittlungAdd));
        	
//        this.set_Factory4Records(new factory4Records());
    }
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return null;
//            new RECORD_BG_LADUNG(cID_MAINTABLE);
        }
        
    }
    
    
}
 
