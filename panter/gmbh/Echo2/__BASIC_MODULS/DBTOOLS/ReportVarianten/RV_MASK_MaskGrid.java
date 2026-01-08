 
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ReportVarianten;
  
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ReportVarianten.Params.RVP_MASK_DaughterListForMotherMask;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.REP_VARIANTEN;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
 
public class RV_MASK_MaskGrid extends E2_Grid {
 	
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
//    //wird benutzt, falls mehr als ein E2_Grid verwendung findet
//    private MyE2_TabbedPane  ta  = new MyE2_TabbedPane(600);
    
	/*
	 * vector nimmt alle container auf, die reale felder enthalten
	 * und die zugehoerigen tab-texte (falls mehr als ein container noetig ist) 
	 */
    
	private VEK<E2_Grid>   fieldContainers = 	new VEK<E2_Grid>();
    
    public RV_MASK_MaskGrid(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        int iWidthComplete = RV_CONST.RV_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue()+
                                  RV_CONST.RV_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue()+20;
        this._setSize(iWidthComplete)._bo_no();
 
        
        this._setSize(800)._bo_no();
        
        this.m_tpHashMap = p_tpHashMap;
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_GRID,this);
        
        RV_MASK_ComponentMap  map1 = (RV_MASK_ComponentMap) this.m_tpHashMap.getMaskComponentMapCollector().get(this.m_tpHashMap.getLeadingMaskKey());
        
        //beginn erster tab
        E2_Grid g1 = fieldContainers._ar(new E2_Grid()._setSize( RV_CONST.RV_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue(),
       															RV_CONST.RV_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue())._bo_no());        
        
        
        g1	._a(new RB_lab(RV_READABLE_FIELD_NAME.getReadable(REP_VARIANTEN.id_rep_varianten)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(map1.getComp(REP_VARIANTEN.id_rep_varianten), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
        g1	._a(new RB_lab(RV_READABLE_FIELD_NAME.getReadable(REP_VARIANTEN.rep_file_name)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(map1.getComp(REP_VARIANTEN.rep_file_name), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
        g1	._a(new RB_lab(RV_READABLE_FIELD_NAME.getReadable(REP_VARIANTEN.rep_file_name_trans)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
    		._a(map1.getComp(REP_VARIANTEN.rep_file_name_trans), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
        g1	._a(new RB_lab(RV_READABLE_FIELD_NAME.getReadable(REP_VARIANTEN.aktiv)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(map1.getComp(REP_VARIANTEN.aktiv), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));

        g1._addSeparator();
        g1._a(new RB_lab(S.ms("Parameter-Liste")) ,new RB_gld()._ins(2,10,2,2)._al(E2_ALIGN.LEFT_TOP)._span(2));
        g1._a(map1.getComp(RVP_MASK_DaughterListForMotherMask.keyForMotherMask), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP)._span(2));
        
        
        this.renderMask();
        
//        this.resize(RV_CONST.RV_NUM_CONST.MASKPOPUP_WIDTH.getValue(),
//        			  RV_CONST.RV_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
    }
    
    
    private void renderMask() throws myException {
   		this._a(this.fieldContainers.get(0));
    }
  
  
//  	 /*
//  	  * zieht bei groessenaenderungen der maske die interne tab-kompontente mit
//  	  */
//     public void resize(int width, int height) {
//	   this.ta.setWidth(new Extent(width-60));
//	   this.ta.setHeight(new Extent(height-170));
//	 }
    
}
 
 
