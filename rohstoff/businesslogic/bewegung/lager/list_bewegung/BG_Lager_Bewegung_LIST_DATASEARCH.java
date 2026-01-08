 
package rohstoff.businesslogic.bewegung.lager.list_bewegung;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import rohstoff.businesslogic.bewegung.lager.list_bewegung.BG_Lager_Bewegung_CONST.TRANSLATOR;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
//import panter.gmbh.basics4project.DB_ENUMS.BG_LADUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  

@Deprecated
public class BG_Lager_Bewegung_LIST_DATASEARCH extends E2_DataSearch
{
    public BG_Lager_Bewegung_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
    {
//        super(_TAB.bg_ladung.n(),_TAB.scanner_settings.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
        this.set_oSearchAgent(oSearchAgent);
        
//        this.addSearchDef(BG_LADUNG.abzug_menge.fn(),"abzug_menge",true);
//        this.addSearchDef(BG_LADUNG.anr1.fn(),"anr1",true);
//        this.addSearchDef(BG_LADUNG.anr2.fn(),"anr2",true);
//        this.addSearchDef(BG_LADUNG.artbez1.fn(),"artbez1",true);
//        this.addSearchDef(BG_LADUNG.artbez2.fn(),"artbez2",true);
//        this.addSearchDef(BG_LADUNG.bemerkung_extern.fn(),"bemerkung_extern",true);
//        this.addSearchDef(BG_LADUNG.bemerkung_intern.fn(),"bemerkung_intern",true);
//        this.addSearchDef(BG_LADUNG.eu_steuer_vermerk.fn(),"eu_steuer_vermerk",true);
//        this.addSearchDef(BG_LADUNG.e_preis_result_brutto_mge.fn(),"e_preis_result_brutto_mge",true);
//        this.addSearchDef(BG_LADUNG.e_preis_result_netto_mge.fn(),"e_preis_result_netto_mge",true);
//        this.addSearchDef(BG_LADUNG.gesamtpreis.fn(),"gesamtpreis",true);
//        this.addSearchDef(BG_LADUNG.gpreis_abz_auf_nettomge.fn(),"gpreis_abz_auf_nettomge",true);
//        this.addSearchDef(BG_LADUNG.gpreis_abz_mge.fn(),"gpreis_abz_mge",true);
//        this.addSearchDef(BG_LADUNG.gpreis_abz_vorauszahlung.fn(),"gpreis_abz_vorauszahlung",true);
//        this.addSearchDef(BG_LADUNG.id_adresse_besitzer.fn(),"id_adresse_besitzer",true);
//        this.addSearchDef(BG_LADUNG.id_artikel.fn(),"id_artikel",true);
//        this.addSearchDef(BG_LADUNG.id_artikel_bez.fn(),"id_artikel_bez",true);
//        this.addSearchDef(BG_LADUNG.id_bg_atom.fn(),"id_bg_atom",true);
//        this.addSearchDef(BG_LADUNG.id_bg_del_info.fn(),"id_bg_del_info",true);
//        this.addSearchDef(BG_LADUNG.id_bg_ladung.fn(),"id_bg_ladung",true);
//        this.addSearchDef(BG_LADUNG.id_bg_pruefprot_abschluss.fn(),"id_bg_pruefprot_abschluss",true);
//        this.addSearchDef(BG_LADUNG.id_bg_pruefprot_menge.fn(),"id_bg_pruefprot_menge",true);
//        this.addSearchDef(BG_LADUNG.id_bg_pruefprot_preis.fn(),"id_bg_pruefprot_preis",true);
//        this.addSearchDef(BG_LADUNG.id_bg_station.fn(),"id_bg_station",true);
//        this.addSearchDef(BG_LADUNG.id_bg_storno_info.fn(),"id_bg_storno_info",true);
//        this.addSearchDef(BG_LADUNG.id_bg_vektor.fn(),"id_bg_vektor",true);
//        this.addSearchDef(BG_LADUNG.id_lager_konto.fn(),"id_lager_konto",true);
//        this.addSearchDef(BG_LADUNG.id_tax.fn(),"id_tax",true);
//        this.addSearchDef(BG_LADUNG.id_vpos_kon.fn(),"id_vpos_kon",true);
//        this.addSearchDef(BG_LADUNG.id_vpos_std.fn(),"id_vpos_std",true);
//        this.addSearchDef(BG_LADUNG.kontraktzwang.fn(),"kontraktzwang",true);
//        this.addSearchDef(BG_LADUNG.kosten_produkt.fn(),"kosten_produkt",true);
//        this.addSearchDef(BG_LADUNG.leistungsdatum.fn(),"leistungsdatum",true);
//        this.addSearchDef(BG_LADUNG.menge.fn(),"menge",true);
//        this.addSearchDef(BG_LADUNG.mengenvorzeichen.fn(),"mengenvorzeichen",true);
//        this.addSearchDef(BG_LADUNG.menge_netto.fn(),"menge_netto",true);
//        this.addSearchDef(BG_LADUNG.postennummer.fn(),"postennummer",true);
//        this.addSearchDef(BG_LADUNG.steuersatz.fn(),"steuersatz",true);
//        this.addSearchDef(BG_LADUNG.timestamp_in_mask.fn(),"timestamp_in_mask",true);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
   }
    private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
    {
        String cSearch = "";
//        if (bNumber) {
//            cSearch = "SELECT id_bg_ladung  FROM "+bibE2.cTO()+"."+_TAB.bg_ladung.n()+" WHERE TO_CHAR("+_TAB.bg_ladung.n()+"."+cFieldName+")='#WERT#'";
//        } else {
//            cSearch = "SELECT id_bg_ladung  FROM "+bibE2.cTO()+"."+_TAB.bg_ladung.n()+" WHERE UPPER("+_TAB.bg_ladung.n()+"."+cFieldName+") like upper('%#WERT#%')";
//        }
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
