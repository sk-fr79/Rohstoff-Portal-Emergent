 
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;
  
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.BasicInterfaces.IF_wrappedMulticomponentsInGrid;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
 
public class WF_SIMPLE_MASK_MaskGrid extends E2_Grid {
 	
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
    
    public WF_SIMPLE_MASK_MaskGrid(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        int iWidthComplete = WF_SIMPLE_CONST.WF_SIMPLE_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue()+
                                  WF_SIMPLE_CONST.WF_SIMPLE_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue()+20;
        
        this._setSize(iWidthComplete)._bo_no();
        
        this.m_tpHashMap = p_tpHashMap;
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_GRID,this);
        
        WF_SIMPLE_MASK_ComponentMap  map1 = (WF_SIMPLE_MASK_ComponentMap) this.m_tpHashMap.getMaskComponentMapCollector().get(this.m_tpHashMap.getLeadingMaskKey());
        WF_SIMPLE_MASK_CompMap_LAUFZETTEL mapLZ = (WF_SIMPLE_MASK_CompMap_LAUFZETTEL) this.m_tpHashMap.getMaskComponentMapCollector().get(WF_SIMPLE_CONST.getMaskKeyLaufzettel());
        
        MASK_STATUS maskStatus = m_tpHashMap.getLastMaskLoadStatus();
        
        
        //beginn erster tab
        E2_Grid gridMain = fieldContainers._ar(new E2_Grid()._setSize( 150, 250,250,250)._bo_no());        
        Insets ins_grid = E2_INSETS.I_2_2_2_2;
        
        RB_gld gld_endLine = new RB_gld();
        RB_gld gld_startLine = new RB_gld()._span(0);
        
        
        tabText._a(S.ms("Laufzettel-Aufgabe") );
        RB_gld ld = 	new RB_gld()._span(1)._ins(E2_INSETS.I(0,0,5,0));   
		IF_wrappedMulticomponentsInGrid  wrap = (Component... comps )-> {E2_Grid g = new E2_Grid(); for (Component c: comps) {g._a(c,ld);} return g._s(comps.length); };

//		gridMain	._startLine(gld_startLine)
//    	._a(new RB_lab("1")._fsa(+2),new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
//    	._a(new RB_lab("2")._fsa(+2),new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
//    	._a(new RB_lab("3")._fsa(+2),new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
//    	._a(new RB_lab("4")._fsa(+2),new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
//    	._addSeparator()
//    	;
    	
		
        // Heading
        gridMain	._startLine(gld_startLine)
        	._a(new RB_lab("Laufzettel")._fsa(+2)._i(),new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
        	._endLine(gld_endLine);
        
//        	._endLine( 	gld_endLine._span(2)._al(E2_ALIGN.RIGHT_MID), new RB_gld()._ins(0)._al(E2_ALIGN.RIGHT_MID)
//        				,wrap.grid(new RB_lab("ID"),mapLZ.getComp(LAUFZETTEL.id_laufzettel)) 
//    				);

        
        if(maskStatus.equals(MASK_STATUS.EDIT)){
        	gridMain
        	._startLine(gld_startLine)
    		._a(new RB_lab("Thema") ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
    		._a(mapLZ.getComp(WF_SIMPLE_CONST.MASK_KEYS.LAUFZETTEL_TEXT_RO.key()), new RB_gld()._ins(0)._al(E2_ALIGN.LEFT_TOP)._span(3))
    		._endLine(gld_endLine)
    		._addSeparator();
        	
        } else {
        	gridMain
        	._startLine(gld_startLine)
        	._a(new RB_lab("Thema") ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(mapLZ.getComp(LAUFZETTEL.text), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID)._span(3))
        	._endLine(gld_endLine)
        	._addSeparator()
        	;
        }

        

		
        // Heading
//        gridMain	._startLine(gld_startLine)
//        	._a(new RB_lab("Laufzettel-Eintrag")._fsa(+2)._i(),new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP)._span(2))
//        	._endLine(gld_endLine._span(2)._al(E2_ALIGN.RIGHT_MID), new RB_gld()._ins(0)._al(E2_ALIGN.RIGHT_MID)
//    				,wrap.grid(new RB_lab("ID"),map1.getComp(LAUFZETTEL_EINTRAG.id_laufzettel_eintrag)) )
//        	;
//
        
//        E2_Grid gridOwner = new E2_Grid()._setSize(100,40,100);
//        gridOwner._a(map1.getComp(LAUFZETTEL_EINTRAG.id_user_besitzer), new RB_gld()._ins(ins_grid)._al(E2_ALIGN.LEFT_MID))
//        		._a(new RB_lab("am") ,new RB_gld()._ins(ins_grid)._al(E2_ALIGN.CENTER_MID))
//		    	._a(map1.getComp(LAUFZETTEL_EINTRAG.angelegt_am), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID))
//		    	._endLine(gld_endLine)
//		    	;
//
//        
//        gridMain._a(new RB_lab("Angelegt von") ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID))
//	        	._a(gridOwner,new RB_gld()._ins(0)._al(E2_ALIGN.LEFT_TOP)._span(3))
//		    	._endLine(gld_endLine)
//		        ;
//	    
        
        E2_Grid gridOwner = new E2_Grid()._setSize(400,100,100,50,100,150);
        gridOwner
        		._a(new RB_lab("Laufzettel-Eintrag")._fsa(+2)._i(),new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
        		._a(new RB_lab("Angelegt von") ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.RIGHT_MID))
        		._a(map1.getComp(LAUFZETTEL_EINTRAG.id_user_besitzer), new RB_gld()._ins(ins_grid)._al(E2_ALIGN.LEFT_MID))
        		._a(new RB_lab("am") ,new RB_gld()._ins(ins_grid)._al(E2_ALIGN.CENTER_MID))
		    	._a(map1.getComp(LAUFZETTEL_EINTRAG.angelegt_am), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID))
    			._endLine(gld_endLine._span(2)._al(E2_ALIGN.RIGHT_MID), new RB_gld()._ins(0)._al(E2_ALIGN.RIGHT_MID)
				,wrap.grid(new RB_lab("ID"),map1.getComp(LAUFZETTEL_EINTRAG.id_laufzettel_eintrag)) )
		    	;
        gridMain._a(gridOwner,new RB_gld()._ins(0)._al(E2_ALIGN.LEFT_TOP)._span(4))._endLine(gld_endLine);
	    
        
        
        E2_Grid gridUser = new E2_Grid()._setSize(400);
        gridUser._a(map1.getComp(LAUFZETTEL_EINTRAG.id_user_bearbeiter), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        		._a(map1.getComp(WF_SIMPLE_CONST.MASK_KEYS.USER_CROSSTABLE.key()),new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
		    	;
        
        
        gridMain._a(new RB_lab("Zu bearbeiten von") ,new RB_gld()._ins(2,10,2,2)._al(E2_ALIGN.LEFT_TOP))
	        	._a(gridUser,new RB_gld()._ins(0,10,0,0)._al(E2_ALIGN.LEFT_TOP)._span(3))
    			._endLine(gld_endLine)
    			;


        
        gridMain._startLine(gld_startLine)
	        	._a(new RB_lab(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.aufgabe)) ,new RB_gld()._ins(2,5,2,2)._al(E2_ALIGN.LEFT_TOP))
	        	._a(map1.getComp(LAUFZETTEL_EINTRAG.aufgabe), new RB_gld()._ins(2,5,2,2)._al(E2_ALIGN.LEFT_TOP)._span(3))
	        	._endLine(gld_endLine)
	        	;
        
        
        RB_MODUL_LINK_Connector connector = (RB_MODUL_LINK_Connector) map1.getComp(WF_SIMPLE_CONST.MASK_KEYS_String.MODUL_CONNECTOR.key());
        gridMain	._startLine(gld_startLine)
		    	._a(new RB_lab("") ,new RB_gld()._ins(2,5,2,2)._al(E2_ALIGN.LEFT_TOP))
		    	._a(connector, new RB_gld()._ins(2,5,2,2)._al(E2_ALIGN.LEFT_TOP)._span(3))
		    	._endLine(gld_endLine)
		    	;

        
        gridMain	._startLine(gld_startLine)
	        	._a(new RB_lab(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.bericht)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
	        	._a(map1.getComp(LAUFZETTEL_EINTRAG.bericht), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP)._span(3))
	        	._endLine(gld_endLine)
	        	;
        
        E2_Grid  gridFaelligkeit = new E2_Grid()._setSize(100,400);
        gridFaelligkeit	._a(map1.getComp(LAUFZETTEL_EINTRAG.faellig_am), new RB_gld()._ins(ins_grid)._al(E2_ALIGN.LEFT_MID));
//        				._a(map1.getComp(LAUFZETTEL_EINTRAG.send_nachricht), new RB_gld()._ins(5,2,2,2)._al(E2_ALIGN.LEFT_MID));
        
        gridMain._a(new RB_lab("Fällig zum") ,new RB_gld()._ins(2,5,2,2)._al(E2_ALIGN.LEFT_MID))
        		._a(gridFaelligkeit,new RB_gld()._ins(0)._al(E2_ALIGN.LEFT_TOP)._span(3));
        
        gridMain._a(new RB_lab("") ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID))
        		._a(map1.getComp(LAUFZETTEL_EINTRAG.send_nachricht), new RB_gld()._ins(0,2,0,0)._al(E2_ALIGN.LEFT_MID)._span(3));
        
        E2_Grid gridWiedervorlage = new E2_Grid()._setSize(150,80,750)._bo_dd();
        gridWiedervorlage._a(map1.getComp(LAUFZETTEL_EINTRAG.kadenz_nach_abschluss), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP)._span_r(2))
    			._a(new RB_lab("Tage"),new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP)._span_r(2))
    			._a(map1.getComp(RB_cb_KadenzAbschluss.key),new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
            	._a(map1.getComp(LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
            	;	
        
        gridMain._startLine(gld_startLine)
	        	._a(new RB_lab("Aufgabenerneuerung") ,new RB_gld()._ins(2,5,2,2)._al(E2_ALIGN.LEFT_TOP))
	        	._a(gridWiedervorlage, new RB_gld()._ins(0,5,0,0)._al(E2_ALIGN.LEFT_TOP)._span(3))
	        	._endLine(gld_endLine);
	        
        
        E2_Grid gridAbgeschlossen = new E2_Grid()._setSize(150,100,50,100)._bo_dd();
        gridAbgeschlossen
        		._a(new RB_lab("Abgeschlossen von") ,new RB_gld()._ins(ins_grid)._al(E2_ALIGN.RIGHT_MID))
        		._a(map1.getComp(LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID))
		    	._a(new RB_lab("am") ,new RB_gld()._ins(ins_grid)._al(E2_ALIGN.RIGHT_MID))
		    	._a(map1.getComp(LAUFZETTEL_EINTRAG.abgeschlossen_am), new RB_gld()._ins(ins_grid)._al(E2_ALIGN.LEFT_MID))
		    	;

        gridMain._startLine(gld_startLine)
    	._a(new RB_lab("Anhang") ,new RB_gld()._ins(2,10,2,2)._al(E2_ALIGN.LEFT_TOP))
    	._a(map1.getComp(WF_SIMPLE_CONST.MASK_KEYS_String.LAUFZETTEL_EINTRAG_ATTACHMENT.key()), new RB_gld()._ins(0,10,0,0)._al(E2_ALIGN.LEFT_TOP)._span(3))
    	._endLine(gld_endLine);

        gridMain._addSeparator()
        	._a(new RB_lab("Status") ,new RB_gld()._ins(2,10,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(map1.getComp(LAUFZETTEL_EINTRAG.id_laufzettel_status), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
	        ._endLine(gld_endLine._al(E2_ALIGN.RIGHT_MID),new RB_gld()._ins(0)._al(E2_ALIGN.RIGHT_MID),gridAbgeschlossen)
	        ;
        
        
        
        //
        //
        //  TAB Laufzettel ********************************
        //
        //
		
        tabText._a(S.ms("Laufzettel / Übersicht "));
            
        E2_Grid gLaufzettel = fieldContainers._ar(new E2_Grid()
        		._setSize( 150, 250,250,250)
        		._bo_no());
        
//        gLaufzettel	._startLine(gld_startLine)
//    	._a(new RB_lab("1")._fsa(+2),new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
//    	._a(new RB_lab("2")._fsa(+2),new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
//    	._a(new RB_lab("3")._fsa(+2),new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
//    	._a(new RB_lab("4")._fsa(+2),new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
//    	._addSeparator()
//    	;
    	
        // Heading
        gLaufzettel	._startLine(gld_startLine)
        	._a(new RB_lab("Laufzettel")._fsa(+2)._i(),new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
        	._endLine(gld_endLine._span(2)._al(E2_ALIGN.RIGHT_MID), new RB_gld()._ins(0)._al(E2_ALIGN.RIGHT_MID)
    				,wrap.grid(new RB_lab("ID"),mapLZ.getComp(LAUFZETTEL.id_laufzettel)) );

        
        if(maskStatus.equals(MASK_STATUS.NEW)){
        	gLaufzettel
        	._startLine(gld_startLine)
    		._a(new RB_lab("Thema") ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
    		._a(mapLZ.getComp(WF_SIMPLE_CONST.MASK_KEYS.LAUFZETTEL_TEXT_RO.key()), new RB_gld()._ins(0)._al(E2_ALIGN.LEFT_TOP)._span(3))
    		._endLine(gld_endLine);
        } else {
        	gLaufzettel
        	._a(new RB_lab("Thema") ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(mapLZ.getComp(LAUFZETTEL.text), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID)._span(3))
        	._endLine(gld_endLine)
//        	._addSeparator()
        	;
        }

        E2_Grid gridLZAbschluss = new E2_Grid()._setSize(100,80,100);
        gridLZAbschluss
		    	._a(mapLZ.getComp(LAUFZETTEL.id_user_abgeschlossen_von), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID))
		    	._a(new RB_lab("am") ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
		    	._a(mapLZ.getComp(LAUFZETTEL.abgeschlossen_am), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID)._span(2))
		    	._endLine(gld_endLine)
		    	;

        
        gLaufzettel
    	._a(new RB_lab("Abschluss von") ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
    	._a(gridLZAbschluss,new RB_gld()._ins(0)._al(E2_ALIGN.LEFT_TOP)._span(3))
    	._endLine(gld_endLine)
    	;

        gLaufzettel
    	._a(new RB_lab("Abschluss d. Einträge") ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
    	._a(mapLZ.getComp(LAUFZETTEL.close_all), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID)._span(3))
    	._endLine(gld_endLine)
    	._addSeparator()
    	;
        
        

        // Heading
        gLaufzettel	._startLine(gld_startLine)
        	._a(new RB_lab("Übersicht der Laufzettel-Einträge")._fsa(+2)._i(),new RB_gld()._ins(2,20,2,10)._al(E2_ALIGN.LEFT_TOP)._span(3))
        	._endLine(gld_endLine )
        	;

        
        // Laufzettel-Einträge in Sub-Grid
        MyE2_ContainerEx  containerScroller = new MyE2_ContainerEx();
        containerScroller.setBorder(new Border(1, new E2_ColorDDark(), Border.STYLE_SOLID));
        containerScroller.setHeightStretched(true);
		
        E2_Grid gScrollerInnen = new E2_Grid()._w100()._s(1);
        containerScroller.add(gScrollerInnen);
        gScrollerInnen.add(mapLZ.getComp(WF_SIMPLE_CONST.MASK_KEYS_String.LAUFZETTEL_GRID_TASKS.key()));
        
        E2_Grid gLeftSide = new E2_Grid()._setSize( 150)._bo_no();
        MyE2_CheckBox cbDeleted = new MyE2_CheckBox("Gelöschte anzeigen",MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());
        cbDeleted.setFont(new E2_FontPlain(-2));
        cbDeleted.setSelected(false);
        cbDeleted._aaa(new XX_ActionAgent() {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				WF_SIMPLE_MASK_SubgridEntries entries =  (WF_SIMPLE_MASK_SubgridEntries) mapLZ.getComp(WF_SIMPLE_CONST.MASK_KEYS_String.LAUFZETTEL_GRID_TASKS.key());
				entries.setShowDeleted(cbDeleted.isSelected());
				entries.refresh();
			}
		});
        
        gLeftSide._startLine(new RB_gld()._ins(0))
				._a(new RB_lab("Einträge") ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
				._a(cbDeleted,new RB_gld()._ins(0));

        
        gLaufzettel
	        ._startLine(gld_startLine)
			._a(gLeftSide ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
			._a(containerScroller, new RB_gld()._ins(0)._al(E2_ALIGN.LEFT_TOP)._span(3))
			._endLine(gld_endLine);
          
        //hier alles rendern (entweder nur ein grid oder ein tab mit grids ...
        this.renderMask();
        
        this.resize(WF_SIMPLE_CONST.WF_SIMPLE_NUM_CONST.MASKPOPUP_WIDTH.getValue(),
        			  WF_SIMPLE_CONST.WF_SIMPLE_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
    }
    
    
    private void renderMask() throws myException {
    	 MASK_STATUS maskStatus = m_tpHashMap.getLastMaskLoadStatus();
    	 
    	
    	 if (this.fieldContainers.size()==1 || maskStatus.equals(MASK_STATUS.NEW) || maskStatus.equals(MASK_STATUS.NEW_COPY)) {
    		 // Neuanlage: nur 1. Tab anzeigen
    		this._a(this.fieldContainers.get(0));
    	 } else {
    		 // sonst alle
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
 
 
