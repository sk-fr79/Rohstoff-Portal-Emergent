 
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ReportVarianten.Params;
  
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.REP_VARIANTEN_PARAM;
import panter.gmbh.indep.exceptions.myException;
 
public class RVP_MASK_MaskGrid extends E2_Grid {
 	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public RVP_MASK_MaskGrid(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        int iWidthComplete = RVP_CONST.RVP_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue()+
                                  RVP_CONST.RVP_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue()+20;
        this._setSize(iWidthComplete)._bo_no();
 
        
        this._setSize(800)._bo_no();
        
        this.m_tpHashMap = p_tpHashMap;
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_GRID,this);
        
        RVP_MASK_ComponentMap  map1 = (RVP_MASK_ComponentMap) this.m_tpHashMap.getMaskComponentMapCollector().get(this.m_tpHashMap.getLeadingMaskKey());
        
        //beginn erster tab
        E2_Grid g1 = new E2_Grid()._setSize( RVP_CONST.RVP_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue(),
        															RVP_CONST.RVP_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue())._bo_no();        
        
        g1._a(new RB_lab(RVP_READABLE_FIELD_NAME.getReadable(REP_VARIANTEN_PARAM.id_rep_varianten_param)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(REP_VARIANTEN_PARAM.id_rep_varianten_param), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
        g1._a(new RB_lab(RVP_READABLE_FIELD_NAME.getReadable(REP_VARIANTEN_PARAM.param_name)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(REP_VARIANTEN_PARAM.param_name), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
        g1._a(new RB_lab(RVP_READABLE_FIELD_NAME.getReadable(REP_VARIANTEN_PARAM.param_value)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(REP_VARIANTEN_PARAM.param_value), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
         
        this._a(g1);
        
    }
  
}
 
 
