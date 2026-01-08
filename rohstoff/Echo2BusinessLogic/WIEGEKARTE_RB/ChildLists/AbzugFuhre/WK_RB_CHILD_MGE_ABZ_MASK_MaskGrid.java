 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugFuhre;
  
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_MGE_ABZ;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
 
public class WK_RB_CHILD_MGE_ABZ_MASK_MaskGrid extends E2_Grid {
 	
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    //wird benutzt, falls mehr als ein E2_Grid verwendung findet
    private MyE2_TabbedPane  ta  = new MyE2_TabbedPane(600);
    
	/*
	 * vector nimmt alle container auf, die reale felder enthalten
	 * und die zugehoerigen tab-texte (falls mehr als ein container noetig ist) 
	 */
    
	private VEK<E2_Grid>   fieldContainers = 	new VEK<E2_Grid>();
    private VEK<MyString>  tabText = 			new VEK<MyString>();
    
    public WK_RB_CHILD_MGE_ABZ_MASK_MaskGrid(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        int iWidthComplete = WK_RB_CHILD_MGE_ABZ_CONST.WK_RB_CHILD_MGE_ABZ_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue()+
                                  WK_RB_CHILD_MGE_ABZ_CONST.WK_RB_CHILD_MGE_ABZ_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue()+20;

        this._setSize(iWidthComplete)._bo_no();
        
        this.m_tpHashMap = p_tpHashMap;
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_GRID,this);
        
        WK_RB_CHILD_MGE_ABZ_MASK_ComponentMap  map1 = (WK_RB_CHILD_MGE_ABZ_MASK_ComponentMap) this.m_tpHashMap.getMaskComponentMapCollector().get(this.m_tpHashMap.getLeadingMaskKey());
        
        //beginn erster tab 
        E2_Grid g1 = fieldContainers._ar(new E2_Grid()._setSize( WK_RB_CHILD_MGE_ABZ_CONST.WK_RB_CHILD_MGE_ABZ_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue(),
        														WK_RB_CHILD_MGE_ABZ_CONST.WK_RB_CHILD_MGE_ABZ_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue()
        														)._bo_no());
        
        tabText._a(S.ms("Abzüge Fuhre"));

//        int colsize = WK_RB_CHILD_MGE_ABZ_CONST.WK_RB_CHILD_MGE_ABZ_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue() / 4;
//        E2_Grid g2 = new E2_Grid()._setSize(colsize,colsize,colsize,colsize-5);
//        g1._a(new RB_lab(WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_MGE_ABZ.id_wiegekarte_mge_abz)+":") ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));
//        g2	._a(map1.getComp(WIEGEKARTE_MGE_ABZ.id_wiegekarte_mge_abz), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID))
//        	._a(new RB_lab(WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_MGE_ABZ.id_wiegekarte)+":") ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.RIGHT_MID))
//        	._a(map1.getComp(WIEGEKARTE_MGE_ABZ.id_wiegekarte), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));
//        g1._a(g2, new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));
        
        g1._a(new RB_lab(WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_MGE_ABZ.id_abzugsgrund)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID))
        ._a(map1.getComp(WIEGEKARTE_MGE_ABZ.id_abzugsgrund), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));

        g1._a(new RB_lab(WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_MGE_ABZ.abzug_menge)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID))
          ._a(map1.getComp(WIEGEKARTE_MGE_ABZ.abzug_menge), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));
        g1._a(new RB_lab(WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_MGE_ABZ.abzug_prozent)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID))
          ._a(map1.getComp(WIEGEKARTE_MGE_ABZ.abzug_prozent), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));
        g1._a(new RB_lab(WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_MGE_ABZ.langtext)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID))
        ._a(map1.getComp(WIEGEKARTE_MGE_ABZ.langtext), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));



//        g1._a(new RB_lab(WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_MGE_ABZ.sys_trigger_timestamp)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
//          ._a(map1.getComp(WIEGEKARTE_MGE_ABZ.sys_trigger_timestamp), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
//        g1._a(new RB_lab(WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_MGE_ABZ.sys_trigger_uuid)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
//          ._a(map1.getComp(WIEGEKARTE_MGE_ABZ.sys_trigger_uuid), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
//        g1._a(new RB_lab(WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_MGE_ABZ.sys_trigger_version)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
//          ._a(map1.getComp(WIEGEKARTE_MGE_ABZ.sys_trigger_version), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
         

        //hier alles rendern (entweder nur ein grid oder ein tab mit grids ...
        this.renderMask();
        
        this.resize(WK_RB_CHILD_MGE_ABZ_CONST.WK_RB_CHILD_MGE_ABZ_NUM_CONST.MASKPOPUP_WIDTH.getValue(),
        			  WK_RB_CHILD_MGE_ABZ_CONST.WK_RB_CHILD_MGE_ABZ_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
    }
    
    
    private void renderMask() throws myException {
    
    	if (this.fieldContainers.size()==1) {
    		this._a(this.fieldContainers.get(0));
    	} else {
    		for (int i=0; i<this.fieldContainers.size(); i++) {
    			MyString s_tab = this.tabText.size()>i?S.NN(this.tabText.get(i), S.ms("..")):S.ms("Tab Nr: ").ut(" "+(i+1));
    			this.ta.add_Tabb(s_tab, this.fieldContainers.get(i));
    		}
    		this._a(this.ta);
    	}
    }
  
  
  	 /*
  	  * zieht bei groessenaenderungen der maske die interne tab-kompontente mit
  	  */
     public void resize(int width, int height) {
	   this.ta.setWidth(new Extent(width-60));
	   this.ta.setHeight(new Extent(height-170));
	 }
    
}
 
 
