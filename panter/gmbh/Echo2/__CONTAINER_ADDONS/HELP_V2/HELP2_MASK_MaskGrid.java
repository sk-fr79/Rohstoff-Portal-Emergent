 
package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2;
  
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DB_ENUMS.HILFETEXT;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
 
public class HELP2_MASK_MaskGrid extends E2_Grid {
 	
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
    
    public HELP2_MASK_MaskGrid(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        int iWidthComplete = HELP2_CONST.HELP2_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue()+
                                  HELP2_CONST.HELP2_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue()+20;
        this._setSize(iWidthComplete)._bo_no();
 
        
        this._setSize(800)._bo_no();
        
        this.m_tpHashMap = p_tpHashMap;
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_GRID,this);
        
        HELP2_MASK_ComponentMap  map1 = (HELP2_MASK_ComponentMap) this.m_tpHashMap.getMaskComponentMapCollector().get(this.m_tpHashMap.getLeadingMaskKey());
        
        //beginn erster tab
        E2_Grid g1 = fieldContainers._ar(new E2_Grid()._setSize( HELP2_CONST.HELP2_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue(),
        															HELP2_CONST.HELP2_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue())._bo_no());        
        
        
        tabText._a(S.ms("Informationen"));
        
        g1	._a(new RB_lab(HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.id_hilfetext)) ,		new RB_gld()._ins(2,10,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(map1.getComp(HILFETEXT.id_hilfetext), 											new RB_gld()._ins(2,10,2,2)._al(E2_ALIGN.LEFT_TOP));

        g1	._a(new RB_lab(HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.ticketnummer)) ,		new RB_gld()._ins(2,10,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(map1.getComp(HILFETEXT.ticketnummer), 											new RB_gld()._ins(2,10,2,2)._al(E2_ALIGN.LEFT_TOP));

        g1	._a(new RB_lab(HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.modulkenner)) ,		new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(map1.getComp(HILFETEXT.modulkenner), 											new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));

        
        g1	._a(new RB_lab(HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.typ)) ,				new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
    		._a(map1.getComp(HILFETEXT.typ), 													new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));

        g1	._a(new RB_lab(HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.id_version)) ,		new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
    		._a(map1.getComp(HILFETEXT.id_version), 											new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
        
        g1	._a(new RB_lab(HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.titel)) ,			new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(map1.getComp(HILFETEXT.titel), 													new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));

        g1	._a(new RB_lab(HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.hilfetext)) ,		new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(map1.getComp(HILFETEXT.hilfetext), 												new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));

    
        
        
        
        
       

        //falls geschaeftsfuehrer oder entwickler, dann die developerinfo anzeigen
        if (bibSES.get_RECORD_USER().is_DEVELOPER_YES() || bibSES.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES() ) {
        	E2_Grid g2 = fieldContainers._ar(new E2_Grid()._setSize(HELP2_CONST.HELP2_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue(),
        			HELP2_CONST.HELP2_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue())._bo_no());
        	
        	tabText._a(S.ms("Interne/Entwickler Infos"));

            
        
	        g2	._a(new RB_lab(HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.info_developer)) ,	new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
	        	._a(map1.getComp(HILFETEXT.info_developer), 										new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));

	        g2	._a(new RB_lab(HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.id_user_bearbeiter)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        		._a(map1.getComp(HILFETEXT.id_user_bearbeiter), 									new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
    
	        g2	._a(new RB_lab(HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.id_user_ursprung)) ,	new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        		._a(map1.getComp(HILFETEXT.id_user_ursprung), 										new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));

	        g2	._a(new RB_lab(HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.status)) ,			new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
	        	._a(map1.getComp(HILFETEXT.status), 												new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));

	        g2	._a(new RB_lab(HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.abschlussdatum)) ,	new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        		._a(map1.getComp(HILFETEXT.abschlussdatum), 										new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
	        

	        
        }
        
        //hier alles rendern (entweder nur ein grid oder ein tab mit grids ...
        this.renderMask();
        
        this.resize(HELP2_CONST.HELP2_NUM_CONST.MASKPOPUP_WIDTH.getValue(),
        			  HELP2_CONST.HELP2_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
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
 
 
