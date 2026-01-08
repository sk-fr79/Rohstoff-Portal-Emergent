 
package rohstoff.Echo2BusinessLogic._TAX.RATE_V2;
  
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
 
public class TX_MASK_MaskGrid extends E2_Grid {
 	
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
    
    public TX_MASK_MaskGrid(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        int iWidthComplete = TX_CONST.TX_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue()+
                                  TX_CONST.TX_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue()+20;
        this._setSize(iWidthComplete)._bo_no();
 
        
        
        this.m_tpHashMap = p_tpHashMap;
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_GRID,this);
        
        TX_MASK_ComponentMap  map1 = (TX_MASK_ComponentMap) this.m_tpHashMap.getMaskComponentMapCollector().get(this.m_tpHashMap.getLeadingMaskKey());
        
        //beginn erster tab
        E2_Grid g1 = fieldContainers._ar(new E2_Grid()._setSize( 	TX_CONST.TX_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue(),
        															TX_CONST.TX_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue())._bo_no());        
        
        int iZahl = 1;
        
        tabText._a(S.ms("Tab "+(iZahl++)));
        
        RB_gld lyo1 = new RB_gld()._ins(2,2,2,4)._al(E2_ALIGN.LEFT_TOP);
        RB_gld lyo2 = new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP);
        
        g1._a(map1.getComp(TAX.id_tax.fk().getCpAdd("@TX@")) ,			lyo1)._a(map1.getComp(TAX.id_tax), lyo1);
        g1._a(map1.getComp(TAX.aktiv.fk().getCpAdd("@TX@")) ,			lyo1)._a(new E2_Grid()._setSize(350,250)._a(map1.getComp(TAX.aktiv), new RB_gld()._ins(0))
        																															._a(map1.getComp(TAX.historisch),new RB_gld()._ins(0,0,0,0)), lyo1);
        g1._a(map1.getComp(TAX.id_tax_group.fk().getCpAdd("@TX@"))  ,	lyo1)._a(new E2_Grid()._setSize(350,100,150) ._a(map1.getComp(TAX.id_tax_group), new RB_gld()._ins(0))
        																																._a(map1.getComp(TAX.sort), new RB_gld()._ins(0,0,0,0))
        																																._a(new RB_lab()._tr("(Sortierung)"), new RB_gld()._ins(4, 0, 0, 0))
        																																, lyo1);
        g1._a(map1.getComp(TAX.id_land.fk().getCpAdd("@TX@"))  ,		lyo1)._a(map1.getComp(TAX.id_land), lyo1);
        g1._a(map1.getComp(TAX.dropdown_text.fk().getCpAdd("@TX@"))  ,	lyo1)._a(map1.getComp(TAX.dropdown_text), lyo1);
        g1._a(map1.getComp(TAX.steuersatz.fk().getCpAdd("@TX@"))  ,		lyo1)._a(map1.getComp(TAX.steuersatz), lyo1);
        g1._a(map1.getComp(TAX.leervermerk.fk().getCpAdd("@TX@")) ,		lyo1)._a(map1.getComp(TAX.leervermerk), lyo1);
        g1._a(map1.getComp(TAX.steuervermerk.fk().getCpAdd("@TX@"))  ,	lyo1)._a(map1.getComp(TAX.steuervermerk), lyo1);
        g1._a(map1.getComp(TAX.taxtyp.fk().getCpAdd("@TX@"))  ,			lyo1)._a(map1.getComp(TAX.taxtyp), lyo1);
        g1._a(map1.getComp(TAX.info_text_user.fk().getCpAdd("@TX@"))  ,	lyo1)._a(map1.getComp(TAX.info_text_user), lyo1);
        g1._a(map1.getComp(TAX.wechseldatum.fk().getCpAdd("@TX@")) ,	lyo1)._a(map1.getComp(TAX.wechseldatum), lyo1);
        g1._a(map1.getComp(TAX.steuersatz_neu.fk().getCpAdd("@TX@"))  ,	lyo1)._a(map1.getComp(TAX.steuersatz_neu), lyo1);

        g1._a(map1.getComp(TAX.id_fibu_konto_gs.fk().getCpAdd("@TX@")) ,lyo1)._a(map1.getComp(TAX.id_fibu_konto_gs), lyo1);
        g1._a(map1.getComp(TAX.id_fibu_konto_re.fk().getCpAdd("@TX@")) ,lyo1)._a(map1.getComp(TAX.id_fibu_konto_re), lyo1);
         
        g1._a(new RB_lab(S.ms("Änderungen")) ,	lyo1)._a(map1.getComp(TX_AEND_MASK_DaughterListForMotherMaskStandalone.keyForMotherMask), lyo1);
        

        tabText._a(S.ms("Tab "+(iZahl++)));

        this.renderMask();
        
        this.resize(TX_CONST.TX_NUM_CONST.MASKPOPUP_WIDTH.getValue(),
        			  TX_CONST.TX_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
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
 
 
