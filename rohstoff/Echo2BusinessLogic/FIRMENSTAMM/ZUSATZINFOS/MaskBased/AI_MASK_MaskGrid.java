 
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS.MaskBased;
  
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
 
public class AI_MASK_MaskGrid extends E2_Grid {
 	
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
    
    public AI_MASK_MaskGrid(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        int iWidthComplete = AI_CONST.AI__NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue()+
                                  AI_CONST.AI__NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue()+20;
        this._setSize(iWidthComplete)._bo_no();
 
        
        this._setSize(800)._bo_no();
        
        this.m_tpHashMap = p_tpHashMap;
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_GRID,this);
        
        AI_MASK_ComponentMap  map1 = (AI_MASK_ComponentMap) this.m_tpHashMap.getMaskComponentMapCollector().get(this.m_tpHashMap.getLeadingMaskKey());
        
        //beginn erster tab
        E2_Grid g1 = fieldContainers._ar(new E2_Grid());        
        
        int iZahl = 1;
        
        tabText._a(S.ms("Tab "+(iZahl++)));
        
        if (this.m_tpHashMap.getFromExtender(AI_TransportExtender.TYP_INFO_OR_MELDUNG)==AI__TYP.INFO) {
        
        	g1._setSize( AI_CONST.AI__NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue(),
					AI_CONST.AI__NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue())._bo_no();
        	
        	RB_gld ld4Text = new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP);
        	RB_gld ld4field1 = new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP)._span(1);
        	
	        //g1._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.aktiv)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(ADRESSE_INFO.aktiv), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
        	g1	._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_adresse)) ,ld4Text)		._a(map1.getComp(ADRESSE_INFO.id_adresse), ld4field1);
        	g1	._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_adresse_info)) ,ld4Text)	._a(map1.getComp(ADRESSE_INFO.id_adresse_info), ld4field1);

        	g1._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_aktionsanlass)) ,ld4Text)	._a(map1.getComp(ADRESSE_INFO.id_aktionsanlass), ld4field1);
        	g1._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.datumeintrag)) ,ld4Text)		._a(map1.getComp(ADRESSE_INFO.datumeintrag), ld4field1);
        	g1._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.kuerzel)) ,ld4Text)			._a(map1.getComp(ADRESSE_INFO.kuerzel), ld4field1);
	        
	        g1._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.datumereignis)) ,ld4Text)		._a(map1.getComp(ADRESSE_INFO.datumereignis), ld4field1);
	        g1._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.folgedatum)) ,ld4Text)			._a(map1.getComp(ADRESSE_INFO.folgedatum), ld4field1);
	        
	        g1._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_besuchsergebnis1)) ,ld4Text)._a(map1.getComp(ADRESSE_INFO.id_besuchsergebnis1), ld4field1);
	        g1._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_besuchsergebnis2)) ,ld4Text)._a(map1.getComp(ADRESSE_INFO.id_besuchsergebnis2), ld4field1);
	        g1._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_besuchsergebnis3)) ,ld4Text)._a(map1.getComp(ADRESSE_INFO.id_besuchsergebnis3), ld4field1);
	        
	        g1._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_user)) ,ld4Text)			._a(map1.getComp(ADRESSE_INFO.id_user), ld4field1);
	        g1._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_user_ersatz)) ,ld4Text)		._a(map1.getComp(ADRESSE_INFO.id_user_ersatz), ld4field1);
	        g1._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_user_sachbearbeiter)) ,ld4Text)._a(map1.getComp(ADRESSE_INFO.id_user_sachbearbeiter), ld4field1);

	       // g1._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.ist_message)) ,ld4Text)		._a(map1.getComp(ADRESSE_INFO.ist_message), ld4field1);
	        //g1._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.message_sofort)) ,ld4Text)		._a(map1.getComp(ADRESSE_INFO.message_sofort), ld4field1);
	        //g1._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.message_typ)) ,ld4Text)		._a(map1.getComp(ADRESSE_INFO.message_typ), ld4field1);
	        g1	._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.text)) ,ld4Text)				._a(map1.getComp(ADRESSE_INFO.text), ld4field1);
	        g1	._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.wiederholungmonatlich)) ,ld4Text)._a(map1.getComp(ADRESSE_INFO.wiederholungmonatlich), ld4field1);
	        g1	._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.wiederholungjaehrlich)) ,ld4Text)._a(map1.getComp(ADRESSE_INFO.wiederholungjaehrlich), ld4field1);
         
        } else {
           	g1._setSize( AI_CONST.AI__NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue(),
    					AI_CONST.AI__NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue())._bo_no();
            	
            RB_gld ld4Text = new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP);
            RB_gld ld4field1 = new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP)._span(1);
     
        	//meldungen
        	g1	._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_adresse)) ,ld4Text)		._a(map1.getComp(ADRESSE_INFO.id_adresse), ld4field1);
        	g1	._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_adresse_info)) ,ld4Text)	._a(map1.getComp(ADRESSE_INFO.id_adresse_info), ld4field1);
 	
        	g1	._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.aktiv)) ,ld4Text)
        		._a(map1.getComp(ADRESSE_INFO.aktiv), ld4field1);
	
        	g1	._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.datumeintrag)) ,ld4Text)
	        	._a(map1.getComp(ADRESSE_INFO.datumeintrag), ld4field1);

	        g1	._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.message_typ)) ,ld4Text)
	        	._a(map1.getComp(ADRESSE_INFO.message_typ), ld4field1);
	        
	        g1._a(new RB_lab(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.text)) ,ld4Text)
	        	._a(map1.getComp(ADRESSE_INFO.text), ld4field1);
        	
        }
        
        tabText._a(S.ms("Tab "+(iZahl++)));
        //hier alles rendern (entweder nur ein grid oder ein tab mit grids ...
        this.renderMask();
        
        this.resize(AI_CONST.AI__NUM_CONST.MASKPOPUP_WIDTH.getValue(),
        			  AI_CONST.AI__NUM_CONST.MASKPOPUP_HEIGHT.getValue());
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
 
 
