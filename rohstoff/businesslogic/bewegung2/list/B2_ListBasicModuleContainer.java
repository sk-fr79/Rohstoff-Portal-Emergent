 
package rohstoff.businesslogic.bewegung2.list;
  

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.basics4project.DB_ENUMS.TRANSPORTMITTEL;
import panter.gmbh.basics4project.DB_ENUMS.VERARBEITUNG;
import panter.gmbh.basics4project.DB_ENUMS.VERPACKUNGSART;
import panter.gmbh.basics4project.DB_ENUMS.ZAHLUNGSBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.businesslogic.bewegung2.recs.RecV;
  
  
public class B2_ListBasicModuleContainer extends Project_BasicModuleContainer {
 	
    private RB_TransportHashMap   tpHashMap = null;
    
    public B2_ListBasicModuleContainer() throws myException    {
    	
    	super(E2_MODULNAME_ENUM.MODUL.B_TRANSPORT_LIST.get_callKey());
 
    	this.tpHashMap = new RB_TransportHashMap();
        
        this.set_bVisible_Row_For_Messages(true);
        
        E2_NavigationList oNaviList = new E2_NavigationList();
        
        this.tpHashMap._setModulContainerList(this);
        this.tpHashMap._setNavigationList(oNaviList);
        this.tpHashMap._setLeadingMaskKey(RecV.key);
        this.tpHashMap._setLeadingTableOnMask( _TAB.bg_vektor);
        
        //jetzt die cache-werte setzen
        this.tpHashMap._setToExtender(B2_TransportHashMapAddons.CACHE_RECLIST21_LAND, new RecList21(_TAB.land)._fillWithAll(new VEK<IF_Field>()._a(LAND.laendername)));
        this.tpHashMap._setToExtender(B2_TransportHashMapAddons.CACHE_RECLIST21_LIEFERBEDINGUNGEN, new RecList21(_TAB.lieferbedingungen)._fillWithAll(new VEK<IF_Field>()._a(LIEFERBEDINGUNGEN.kurzbezeichnung)));
        this.tpHashMap._setToExtender(B2_TransportHashMapAddons.CACHE_RECLIST21_ZAHLUNGSBEDINGUNGEN, new RecList21(_TAB.zahlungsbedingungen)._fillWithAll(new VEK<IF_Field>()._a(ZAHLUNGSBEDINGUNGEN.kurzbezeichnung)));
        this.tpHashMap._setToExtender(B2_TransportHashMapAddons.CACHE_RECLIST21_TAX, new RecList21(_TAB.tax)._fillWithAll(new VEK<IF_Field>()._a(TAX.dropdown_text)));
        this.tpHashMap._setToExtender(B2_TransportHashMapAddons.CACHE_RECLIST21_TRANSPORTMITTEL, new RecList21(_TAB.transportmittel)._fillWithAll(new VEK<IF_Field>()._a(TRANSPORTMITTEL.beschreibung)));
        this.tpHashMap._setToExtender(B2_TransportHashMapAddons.CACHE_RECLIST21_VERPACKUNGSART, 	new RecList21(_TAB.verpackungsart)._fillWithAll(new VEK<IF_Field>()._a(VERPACKUNGSART.verpackungsart)));
        this.tpHashMap._setToExtender(B2_TransportHashMapAddons.CACHE_RECLIST21_VERARBEITUNG , 		new RecList21(_TAB.verarbeitung)._fillWithAll(new VEK<IF_Field>()._a(VERARBEITUNG.verarbeitung)));
        this.tpHashMap._setToExtender(B2_TransportHashMapAddons.CACHE_REC21_RECORD_ADRESSE_MANDANT, new Rec21(_TAB.adresse)._fill_id(bibALL.get_ID_ADRESS_MANDANT()));
        this.tpHashMap._setToExtender(B2_TransportHashMapAddons.HIGHLIGHT_COLOR_SEARCHBLOCKS, 		new E2_ColorBase());
        
        
        
        SEL selSubZollTarif = new SEL(ARTIKEL.id_zolltarifnummer).FROM(_TAB.artikel).ADD_Distinct();
        SEL selZollTarifRec = new SEL(_TAB.zolltarifnummer).FROM(_TAB.zolltarifnummer).WHERE(
        		new TermSimple(ZOLLTARIFNUMMER.id_zolltarifnummer.tnfn()+" IN ("+selSubZollTarif.s()+")")).ORDERUP(ZOLLTARIFNUMMER.text1);
        
        SqlStringExtended sqlExt = new SqlStringExtended(selZollTarifRec.s()); 
        
        this.tpHashMap._setToExtender(B2_TransportHashMapAddons.CACHE_RECLIST21_ZOLLTARIFNUMMER, 
        		new RecList21(_TAB.zolltarifnummer)._fill(sqlExt));

        oNaviList.set_bSaveSortStatus(true);
        oNaviList.INIT_WITH_ComponentMAP(new B2_ListComponentMap(this.tpHashMap),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
        
        B2_ListBedienPanel oPanel = new B2_ListBedienPanel(this.tpHashMap);
        
        this.tpHashMap._setListBedienPanel(oPanel);
        
        
        this.add(oPanel, E2_INSETS.I_2_2_2_2);
        this.add(oNaviList, E2_INSETS.I_2_2_2_2);
        
        oPanel.get_list_Selector().get_oSelVector().doActionPassiv();
    }
        
}
 
 
 