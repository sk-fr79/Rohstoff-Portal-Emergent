 
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;
 
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationListCompact;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterBasedOnBasicModulContainer;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.TERM.VglNull;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.LAGERHALTUNG.LH_CONST;
import rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE.LH_P_CONST.TRANSLATOR;
 
/**
 * @author martin
 * Komponente fuer eine maske eine mothertable, die diese als fullDaughter einblendet
 */
public class LH_P_MASK_DaughterListForMotherMask extends RB_MaskDaughterBasedOnBasicModulContainer {
 	
 	//fieldKey fuer das einbinden dieser komponente in einer mother-mask
	public static RB_KF  keyForMotherMask = (RB_KF)new RB_KF()._setHASHKEY("LH_P_LAGER_PALETTE_MASK_DaughterListForMotherMask")._setREALNAME("LH_P_LAGER_PALETTE_MASK_DaughterListForMotherMask");
	
 	
 	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    //entscheidet, ob die Tochter eine komplette oder eine reduzierte E2_NavigationList ist
    private LH_P_LIST_BedienPanel 	bedienPanel = null;
    private VEK<MyE2IF__Component>  toDisableAtMask = new VEK<MyE2IF__Component>();
    private LH_P_LIST_DATASEARCH  	searcher = null;
    private boolean    				m_enabled = true;
    
    /**
     * 
     * @param p_tpHashMap
     * @param useCompactList
     * 
     * TABELLE: JT_LAGER_PALETTE
     * @throws myException
     */
    public LH_P_MASK_DaughterListForMotherMask(RB_TransportHashMap p_tpHashMap, boolean useCompactList) throws myException {
        super();
    
        this._init(new InternalLIST_BasicModuleContainer(p_tpHashMap, useCompactList));
 	}
  
    
    @Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
    	this.m_enabled = enabled; 
    	
		//falls ausfuehrliche variante
		if (this.bedienPanel!=null) {
			this.bedienPanel.set_bEnabled_For_Edit(enabled);
		}
		
		//oder kurze variante
		for (MyE2IF__Component c: this.toDisableAtMask) {
			c.set_bEnabled_For_Edit(enabled);
		}
		
		//jetzt direkt den mapsettingagent starten
		for (E2_ComponentMAP map: this.m_tpHashMap.getNavigationList().get_vComponentMAPS()) {
			for (XX_ComponentMAP_SubqueryAGENT subagent: map.get_vComponentMapSubQueryAgents()) {
				subagent.fillComponents(map, map.get_oInternalSQLResultMAP());
			}
		}
		
		
	}
   
	@Override
	public void mark_Neutral() throws myException {
	}
  
	@Override
	public void mark_MustField() throws myException {
	}
 
	@Override
	public void mark_Disabled() throws myException {
	}
 
	@Override
	public void mark_FalseInput() throws myException {
	}
 
	@Override
	public IF_Field getLinkfieldToMotherTable() {
		return LAGER_PALETTE.id_lager_box;
	}
	
	/**
	 * innerer basicmodulContainer fuer die einlagerung in eine MotherMask
	 * @author martin
	 *
	 */
	public class InternalLIST_BasicModuleContainer extends Project_BasicModuleContainer    {
		
	    public InternalLIST_BasicModuleContainer(RB_TransportHashMap p_params, boolean useCompactList) throws myException    {
	    	
	        super(TRANSLATOR.LAGER_PALETTE_LIST_INTERNAL.get_modul().get_callKey());
	        
	        LH_P_MASK_DaughterListForMotherMask oThis = LH_P_MASK_DaughterListForMotherMask.this;
	        
	        oThis.m_tpHashMap = new RB_TransportHashMap();  
	        //die transporthashmap muss jetzt in die ableitende klasse
	        oThis._setTransportHashMap(oThis.m_tpHashMap);
	        
	        //dann den ganzen paramhash der mutter in den tochterhash einfuegen
	        oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.MOTHERPARAMSHASH, p_params);
 
	        oThis.m_tpHashMap._setModulContainerList(this);
	        oThis.m_tpHashMap._setLeadingMaskKey(LH_P_CONST.getLeadingMaskKey());
	        oThis.m_tpHashMap._setLeadingTableOnMask(LH_P_CONST.getLeadingTable());
 
	        this.set_bVisible_Row_For_Messages(false);
 	        
 	        
	        LH_P_LIST_ComponentMap listComponentMap  = null;
 	        
	        if (useCompactList) {
                oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.NAVILIST,new E2_NavigationListCompact());
 
                 
                LH_P_LIST_bt_New btNew = new LH_P_LIST_bt_New(oThis.m_tpHashMap);
                LH_P_LIST_bt_Copy btCopy = new LH_P_LIST_bt_Copy(oThis.m_tpHashMap);
                LH_P_LIST_bt_Ausbuchung ausbuchung_bt = new LH_P_LIST_bt_Ausbuchung(m_tpHashMap);
               	LH_P_LIST_bt_move_box2box box2box_bt = new LH_P_LIST_bt_move_box2box(m_tpHashMap);
               	
                
               	oThis.toDisableAtMask._a(btNew)._a(btCopy)._a(ausbuchung_bt)._a(box2box_bt);
                oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oThis.m_tpHashMap.getNaviListCompact())._setLDC(new RB_gld()._ins(2, 0, 10, 0)._left_mid()));
                oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btNew._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
                oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btCopy._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
                oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(ausbuchung_bt._setLDC(new RB_gld()._ins(2,2,10,2)._left_mid()));
                
                oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(box2box_bt._setLDC(new RB_gld()._ins(2,2,10,2)._left_mid()));
                
                
                oThis.searcher = new LH_P_LIST_DATASEARCH(oThis.m_tpHashMap);
                oThis.searcher.getLabelSuchBeschriftung().setVisible(false);
                oThis.searcher._setWidthSearchText(150);
                
                oThis.m_tpHashMap.getNaviListCompact()._addRightComponent(oThis.searcher._setLDC(new RB_gld()._ins(20, 0, 10, 0)._left_mid()));
                oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST,this);
 
                listComponentMap = new LH_P_LIST_ComponentMap(oThis.m_tpHashMap, new own_LH_P_LIST_SqlFieldMAP(oThis.m_tpHashMap));
                oThis.m_tpHashMap.getNaviListCompact().INIT_WITH_ComponentMAP(listComponentMap,  E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
                this.add(oThis.m_tpHashMap.getNaviListCompact(), E2_INSETS.I_2_2_2_2);
                oThis.m_tpHashMap.getNaviListCompact()._REBUILD_COMPLETE_LIST("");
                oThis.m_tpHashMap.getNaviListCompact()._rebuildTitleLine();
             
	        } else {
	        
                oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST,this);
                oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.NAVILIST,new E2_NavigationList());
                
                listComponentMap = new LH_P_LIST_ComponentMap(oThis.m_tpHashMap, new own_LH_P_LIST_SqlFieldMAP(oThis.m_tpHashMap));
                oThis.m_tpHashMap.getNavigationList().INIT_WITH_ComponentMAP(listComponentMap, E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
                oThis.bedienPanel = new LH_P_LIST_BedienPanel(LH_P_MASK_DaughterListForMotherMask.this.m_tpHashMap);
                oThis.searcher = oThis.bedienPanel.getDataSearch();
                this.add(oThis.bedienPanel, E2_INSETS.I_2_2_2_2);
                this.add(oThis.m_tpHashMap.getNavigationList(), E2_INSETS.I_2_2_2_2);
                oThis.bedienPanel.get_list_Selector().get_oSelVector().doActionPassiv();
	        }
	        
	        listComponentMap.add_oSubQueryAgent(new mapSettingAgent());
	        
	    }
	}
	
    @Override
    public E2_DataSearch getSearcher() {
        return this.searcher;
    }
    
    
    /**
     * mapsettingagent um bei einen maskenaufruf im Status view die Buttons in der liste auch
     * disablen zu koennen
     * @author martin
     *
     */
    private class mapSettingAgent extends XX_ComponentMAP_SubqueryAGENT {
		@Override
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException {
		}
		@Override
		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException {
			if (!m_enabled) {
				//dann in der maskentochterliste die direct-edit und delete-buttons deaktivieren
				for (MyE2IF__Component c: oMAP.get_hmRealComponents().values()) {
					if (c instanceof LH_P_LIST_bt_DeleteInListRow) {
						((LH_P_LIST_bt_DeleteInListRow)c).setEnabledForEditOfSuperClass(false);
					}
					if (c instanceof LH_P_LIST_bt_ListToMaskInListRow) {
						if ( ((LH_P_LIST_bt_ListToMaskInListRow)c).isUsedToEdit()) {
							((LH_P_LIST_bt_ListToMaskInListRow)c).setEnabledForEditOfSuperClass(false);
						}
					}
					
				}
			}
		}
    	
    }
    
    
    
    
    public class own_LH_P_LIST_SqlFieldMAP extends Project_SQLFieldMAP  {
    	
        //zentrale hashmap fuer transport von infos
        private RB_TransportHashMap   m_tpHashMap = null;
        
        @SuppressWarnings("unchecked")
		public own_LH_P_LIST_SqlFieldMAP(RB_TransportHashMap  p_tpHashMap) throws myException   {
        	
            super(_TAB.lager_palette.n(), "", false);
            
            this.m_tpHashMap = p_tpHashMap;        
            this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SQL_FIELDMAP,this);

            this.add_JOIN_Table(
    				_TAB.vpos_tpa_fuhre.fullTableName(), 
    				_TAB.vpos_tpa_fuhre.fullTableName(), 
    				LEFT_OUTER, 
    				":"+VPOS_TPA_FUHRE.id_vpos_tpa_fuhre.fn()+":"+VPOS_TPA_FUHRE.buchungsnr_fuhre.fn()+":"+VPOS_TPA_FUHRE.ohne_abrechnung.fn()+":", 
    				true, 
    				LAGER_PALETTE.id_vpos_tpa_fuhre_ein.tnfn() + "=" + VPOS_TPA_FUHRE.id_vpos_tpa_fuhre.tnfn(), 
    				"", 
    				null);
            
            this.add_JOIN_Table(
    				_TAB.artikel_bez.fullTableName(), 
    				_TAB.artikel_bez.fullTableName(), 
    				LEFT_OUTER, 
    				":"+ARTIKEL_BEZ.anr2.tnfn()+":"+ARTIKEL_BEZ.artbez2.tnfn()+":", 
    				true, 
    				LAGER_PALETTE.id_artikel_bez.tnfn() + "=" + ARTIKEL_BEZ.id_artikel_bez.tnfn(), 
    				"", 
    				null);

    		this.add_JOIN_Table(
    				_TAB.artikel.fullTableName(), 
    				_TAB.artikel.fullTableName(), 
    				LEFT_OUTER, 
    				":"+ARTIKEL.anr1.tnfn()+":"+ARTIKEL.artbez1.tnfn()+":", 
    				true, 
    				ARTIKEL_BEZ.id_artikel.tnfn() + "=" + ARTIKEL.id_artikel.tnfn(), 
    				"", 
    				null);

    		this.add_SQLField(new SQLField(ARTIKEL.anr1.tnfn() + " || '-' || " + ARTIKEL_BEZ.anr2.tnfn() + " || ' ' ||  " + ARTIKEL_BEZ.artbez1.tnfn()  +  " "  , 
    				LH_P_CONST.ENUM_PALETTE_LISTKEY.ARTIKEL_INFO.k(), 
    				new MyE2_String("Artikel"), 
    				bibE2.get_CurrSession()),true);
    		
            
            
            
            
            String fuhre_id = null;
            if (m_tpHashMap.getMotherTransportHashMap().getFromExtender(LH_CONST.LH_EXTENDER.LH_FUHRE_ID)!=null) {
            	fuhre_id = (String) m_tpHashMap.getMotherTransportHashMap().getFromExtender(LH_CONST.LH_EXTENDER.LH_FUHRE_ID);
            }

            VEK<String> paletten_id = new VEK<String>();
            if (m_tpHashMap.getMotherTransportHashMap().getFromExtender(LH_CONST.LH_EXTENDER.LH_EINZELPALETTE_LISTE)!=null) {
            	paletten_id._a((VEK<String>) m_tpHashMap.getMotherTransportHashMap().getFromExtender(LH_CONST.LH_EXTENDER.LH_EINZELPALETTE_LISTE));
            }
            
            And and = new And(new VglNull(LAGER_PALETTE.id_vpos_tpa_fuhre_aus));
            
            if (S.isFull(fuhre_id)) {
            	and.and(new vgl(LAGER_PALETTE.id_vpos_tpa_fuhre_ein, fuhre_id));
            }else if(paletten_id.size()>0) {
            	and.and(LAGER_PALETTE.id_lager_palette, COMP.IN.ausdruck(), "("+bibALL.Concatenate(paletten_id, ",","")+")");
            }
            and .and(new vgl_YN(LAGER_PALETTE.ausbuchung_hand, false));
            this.add_BEDINGUNG_STATIC(and.s());

            
         this.initFields();
        }
        
    }

}
 
 
 
