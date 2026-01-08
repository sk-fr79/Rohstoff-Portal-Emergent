 
package rohstoff.Echo2BusinessLogic._TAX.RATE_V2;
  
import nextapp.echo2.app.Border;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDownV3;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionV2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.basics4project.DB_ENUMS.TAX_GROUP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.EnumTaxTyp;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22Land;
             
public class TX_LIST_Selector extends E2_ExpandableRow {
    
    /*
     * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
     */
    
    private E2_SelectionComponentsVector     oSelVector = null;
    
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public TX_LIST_Selector(RB_TransportHashMap  p_tpHashMap) throws myException
    {
        super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
        
        this.m_tpHashMap = p_tpHashMap;       
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR,this);
        this.oSelVector = new E2_SelectionComponentsVector(this.m_tpHashMap.getNavigationList());
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTIONCOMPONENTVECTOR,this.oSelVector );
        
        E2_Grid oGridInnen = new E2_Grid();
        
        
        //selektoren erzeugen
        XX_ListSelektor selaktiv = new E2_ListSelektorMultiselektionV2()
        		._addLabel(S.msUt("Aktiv:"), 40)
        		._addCheck(TAX.aktiv, true, new vgl_YN(TAX.aktiv, true).s(), S.ms("Aktiv"), S.ms("Aktive Steuersätze anzeigen"), 70)
        		._addCheck(TAX.aktiv, true, new vgl_YN(TAX.aktiv, false).s(), S.ms("Inaktiv"), S.ms("Aktive Steuersätze anzeigen"), 70);
        
        XX_ListSelektor selhistorisch = new E2_ListSelektorMultiselektionV2()
        		._addLabel(S.ms("Hist.:"), 40)
        		._addCheck(TAX.historisch, true, new vgl_YN(TAX.historisch, true).s(), S.ms("Ja"), S.ms("Historische Steuersätze anzeigen"), 50)
        		._addCheck(TAX.historisch, true, new vgl_YN(TAX.historisch, false).s(), S.ms("Nein"), S.ms("Aktuelle Steuersätze anzeigen"), 50);

        XX_ListSelektor selid_land = new E2_ListSelectorMultiDropDownV3(_TAB.find_field(_TAB.tax,"id_land"),(s)->{
        									try {
												return new Rec22Land()._fill_id(s).get_ufs_kette(" ", true, LAND.laendername);
											} catch (myException e) {
												e.printStackTrace();
												return s;
											}
        							})
        							._setBeschriftung("Land:")._setWidthForBeschriftung(40);
        
        
        XX_ListSelektor selid_tax_group = new E2_ListSelectorMultiDropDownV3(_TAB.find_field(_TAB.tax,"id_tax_group"),(s)-> {
        									try {
												Rec22 rt = new Rec22(_TAB.tax_group)._fill_id(s);
												return rt.getFs(TAX_GROUP.kurztext," ???");
											} catch (myException e) {
												e.printStackTrace();
												return s;
											}
        								  })
        							._setBeschriftung("Gruppe:")._setWidthForBeschriftung(50);
        							
        							
        XX_ListSelektor seltaxtyp = new E2_ListSelectorMultiDropDownV3(EnumTaxTyp.TAXTYP_NULL_LAGERSEITE,_TAB.find_field(_TAB.tax,"taxtyp"))
        						._setBeschriftung("Steuer-Typ:")._setWidthForBeschriftung(80);

        
        this.oSelVector.add(selaktiv);
        this.oSelVector.add(selhistorisch);
        this.oSelVector.add(selid_land);
        this.oSelVector.add(selid_tax_group);
        this.oSelVector.add(seltaxtyp);
        //
        
        //selektoren am panel anordnen
        oGridInnen._a(selaktiv.get_oComponentForSelection(), new RB_gld()._ins(0, 0, 20, 0)._left_mid());
        oGridInnen._a(selhistorisch.get_oComponentForSelection(), new RB_gld()._ins(0, 0, 20, 0)._left_mid());
        oGridInnen._a(selid_land.get_oComponentForSelection(), new RB_gld()._ins(0, 0, 20, 0)._left_mid());
        oGridInnen._a(selid_tax_group.get_oComponentForSelection(), new RB_gld()._ins(0, 0, 20, 0)._left_mid());
        oGridInnen._a(seltaxtyp.get_oComponentForSelection(), new RB_gld()._ins(0, 0, 20, 0)._left_mid());
        
        // db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(E2_MODULNAME_ENUM.MODUL.TAX_RATE_V2_LIST.get_callKey());
		oSelVector.add(oDB_BasedSelektor);
		oGridInnen._a(oDB_BasedSelektor.get_oComponentForSelection(S.ms("Diverse:"),100), new RB_gld()._ins(0,0,5,0));
        
        oGridInnen._s(oGridInnen.getComponentCount());
        this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
        
    }
    public E2_SelectionComponentsVector get_oSelVector()
    {
        return oSelVector;
    }
    

    
    
}
 
 
