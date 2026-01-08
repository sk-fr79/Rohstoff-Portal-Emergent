 
package panter.gmbh.Echo2.basic_tools.emailv2;
  
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
 
public class EM2_MASK_MaskGrid extends E2_Grid {
 	
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
    
    public EM2_MASK_MaskGrid(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        int iWidthComplete = EM2_CONST.EM2_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue()+
                             EM2_CONST.EM2_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue()+20;
        this._setSize(iWidthComplete*2)._bo_no();
        
        this.m_tpHashMap = p_tpHashMap;
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_GRID,this);
        
        EM2_MASK_ComponentMap  map1 = (EM2_MASK_ComponentMap) this.m_tpHashMap.getMaskComponentMapCollector().get(this.m_tpHashMap.getLeadingMaskKey());
        
        //beginn erster tab
        E2_Grid g1 = fieldContainers._ar(new E2_Grid()._setSize(    EM2_CONST.EM2_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue(),
        															EM2_CONST.EM2_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue(),
        															EM2_CONST.EM2_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue(),
        															EM2_CONST.EM2_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue()
        															)._bo_no());        
        
        int iZahl = 1;
        
        tabText._a(S.ms("Tab "+(iZahl++)));
        
        g1	._a(map1.getComp(EMAIL_SEND.id_email_send.fk().getCpAdd("@EM2@")) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(map1.getComp(EMAIL_SEND.id_email_send.fk()), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a()._a();
        
        g1	._a(map1.getComp(EMAIL_SEND.table_base_name.fk().getCpAdd("@EM2@")) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(
        		new E2_Grid()._s(3)._a(map1.getComp(EMAIL_SEND.table_base_name.fk()), new RB_gld()._left_mid()._ins(0, 0, 5, 0))
        						   ._a(new RB_lab()._tr("ID:"), new RB_gld()._left_mid()._ins(0, 0, 2, 0))	
        						   ._a(map1.getComp(EMAIL_SEND.id_table.fk()), new RB_gld()._left_mid())
        		, new RB_gld()._ins(2,2,2,2)._span(3)._al(E2_ALIGN.LEFT_TOP))
        	;
        
//        g1._a(map1.getComp(EMAIL_SEND.id_table.fk().getCpAdd("@EM2@")) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(EMAIL_SEND.id_table.fk()), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
        g1	._a(map1.getComp(EMAIL_SEND.sender_adress.fk().getCpAdd("@EM2@")) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(map1.getComp(EMAIL_SEND.sender_adress.fk()), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a()._a();
        
        g1	._a(map1.getComp(EMAIL_SEND.send_type.fk().getCpAdd("@EM2@")) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(map1.getComp(EMAIL_SEND.send_type.fk()), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a()._a();

        g1	._a(map1.getComp(EMAIL_SEND.betreff.fk().getCpAdd("@EM2@")) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(map1.getComp(EMAIL_SEND.betreff.fk()), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
            ._a(map1.getComp(EMAIL_SEND.betreff_2_send.fk().getCpAdd("@EM2@")) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
            ._a(map1.getComp(EMAIL_SEND.betreff_2_send.fk()), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));

        g1	._a(map1.getComp(EMAIL_SEND.text.fk().getCpAdd("@EM2@")) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(map1.getComp(EMAIL_SEND.text.fk()), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(map1.getComp(EMAIL_SEND.text_2_send.fk().getCpAdd("@EM2@")) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(map1.getComp(EMAIL_SEND.text_2_send.fk()), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));

        g1	._a(map1.getComp(EMAIL_SEND.email_type.fk().getCpAdd("@EM2@")) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(map1.getComp(EMAIL_SEND.email_type.fk()), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(map1.getComp(EMAIL_SEND.email_verification_key.fk().getCpAdd("@EM2@")) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(map1.getComp(EMAIL_SEND.email_verification_key.fk()), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));

        
        g1	._a(new RB_lab()._t("Anlagen-Liste"),new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(map1.getComp(EM2_MASK_DaughterAttachmentsList.keyForMotherMask),new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP)._span(3))
        	;

        g1	._a(new RB_lab()._t("Zieladressen"),new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
    		._a(map1.getComp(EM2_MASK_DaughterTargetList.keyForMotherMask),new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP)._span(3))
    		;
        


        //hier alles rendern (entweder nur ein grid oder ein tab mit grids ...
        this.renderMask();
        
        this.resize(EM2_CONST.EM2_NUM_CONST.MASKPOPUP_WIDTH.getValue(),
        			  EM2_CONST.EM2_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
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
 
 
