 
package panter.gmbh.Echo2.RB.COMP.TextListe;
 
import panter.gmbh.Echo2.E2_INSETS;
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
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
 
/**
 * @author martin
 * Komponente fuer eine maske eine mothertable, die diese als fullDaughter einblendet
 */
public class TL_MASK_DaughterListForMotherMask extends RB_MaskDaughterBasedOnBasicModulContainer {
 	
 	//fieldKey fuer das einbinden dieser komponente in einer mother-mask
	public static RB_KF  keyForMotherMask = (RB_KF)new RB_KF()._setHASHKEY("TL_TEXT_LISTE_MASK_DaughterListForMotherMask")._setREALNAME("TL_TEXT_LISTE_MASK_DaughterListForMotherMask");
	
    private RB_TransportHashMap   			m_tpHashMap = null;
    
    //entscheidet, ob die Tochter eine komplette oder eine reduzierte E2_NavigationList ist
    private TL_LIST_BedienPanel 			bedienPanel = null;
    private VEK<MyE2IF__Component>  		toDisableAtMask = new VEK<MyE2IF__Component>();
    private TL_LIST_DATASEARCH  			searcher = null;
    
    private boolean    						m_enabled = true;
    
    private String   						beschriftungIdField = null;   //beschriftung des id-feldes der mothertable auf der maske
    
    private EnumTableTranslator             tableTranslator = null;    // hier werden die angaben hinterlegt, die noetig sind, um eine 
                          																 // liste speichern / laden zu koennen
    																					 // der Speicher/lade-Button wird nur angezeigt, wenn 
    																					 // tableTranslatorForSaveAndLoadList != null
    

    /**
     * 
     * @author martin
     * @date 30.03.2020
     *
     * @param p_tpHashMapFromCallingModule
     * @param useCompactList
     * @param beschriftungIdFeld
     * @param p_tableTranslator
     * @throws myException
     */
    public TL_MASK_DaughterListForMotherMask(	   RB_TransportHashMap p_tpHashMapFromCallingModule
    											,  boolean useCompactList
    											,  String beschriftungIdFeld
    											,  EnumTableTranslator p_tableTranslator
    											) throws myException {
        super();
        this.beschriftungIdField = beschriftungIdFeld;
        this.tableTranslator = p_tableTranslator;	
        
        this._w100();

        this._init(new InternalLIST_BasicModuleContainer(p_tpHashMapFromCallingModule, useCompactList));
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
		return TEXT_LISTE.id_table;
	}
	
	/**
	 * innerer basicmodulContainer fuer die einlagerung in eine MotherMask
	 * @author martin
	 *
	 */
	public class InternalLIST_BasicModuleContainer extends Project_BasicModuleContainer    {
		
	    public InternalLIST_BasicModuleContainer(RB_TransportHashMap p_tpHashMapFromCallingModule, boolean useCompactList) throws myException    {
	    	
	        super(E2_MODULNAME_ENUM.MODUL.TL_LIST_LIST.get_callKey());
	        
	        TL_MASK_DaughterListForMotherMask oThis = TL_MASK_DaughterListForMotherMask.this;
	        
	        oThis.m_tpHashMap = new RB_TransportHashMap();  
	        //die transporthashmap muss jetzt in die ableitende klasse
	        oThis._setTransportHashMap(oThis.m_tpHashMap);
	        
	        oThis.m_tpHashMap._putSB("BESCHRIFTUNG_ID_FELD_MASK", beschriftungIdField);
	        oThis.m_tpHashMap._putSB(TL_CONST.TabTrans, tableTranslator);
	        
	        
	        //dann den ganzen paramhash der mutter in den tochterhash einfuegen
	        oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.MOTHERPARAMSHASH, p_tpHashMapFromCallingModule);
 
	        oThis.m_tpHashMap._setModulContainerList(this);
	        oThis.m_tpHashMap._setLeadingMaskKey(TL_CONST.getLeadingMaskKey());
	        oThis.m_tpHashMap._setLeadingTableOnMask(TL_CONST.getLeadingTable());
 
	        this.set_bVisible_Row_For_Messages(false);
 	        
 	        
	        TL_LIST_ComponentMap listComponentMap  = null;
 	        
	        if (useCompactList) {
                oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.NAVILIST,new E2_NavigationListCompact());
 
                 
                TL_LIST_bt_New btNew = new TL_LIST_bt_New(oThis.m_tpHashMap);
                TL_LIST_bt_Copy btCopy = new TL_LIST_bt_Copy(oThis.m_tpHashMap);
                
                TL_LIST_btSort  btSort = 					(TL_LIST_btSort) 					new TL_LIST_btSort(oThis.m_tpHashMap)._ttt(S.ms("Textliste sortieren"));
                TL_LIST_btSaveToTextListeVorlage 	btSave = 	(TL_LIST_btSaveToTextListeVorlage) 	new TL_LIST_btSaveToTextListeVorlage(oThis.m_tpHashMap)._ttt(S.ms("Textliste als Vorlage abspeichern"));
                TL_LIST_btLoadFromVorlage 			btLoad = 	(TL_LIST_btLoadFromVorlage) 		new TL_LIST_btLoadFromVorlage(oThis.m_tpHashMap)._ttt(S.ms("Textliste aus einer Vorlage laden"));
                
                TL_LIST_bt_ListToMask btEditMulti = new TL_LIST_bt_ListToMask(true,oThis.m_tpHashMap);
                TL_LIST_bt_ListToMask btViewMulti = new TL_LIST_bt_ListToMask(false,oThis.m_tpHashMap);
                TL_LIST_bt_multiDelete btMultiDelete = new TL_LIST_bt_multiDelete(oThis.m_tpHashMap);
 
                
                oThis.toDisableAtMask._a(btNew)._a(btCopy)._a(btSort)._a(btEditMulti)._a(btSave)._a(btLoad)._a(btMultiDelete);
                oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oThis.m_tpHashMap.getNaviListCompact())._setLDC(new RB_gld()._ins(2, 0, 10, 0)._left_mid()));
                oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btNew._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
                oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btEditMulti._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
                oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btViewMulti._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
                oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btMultiDelete._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
             
                oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btCopy._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
                oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btSort._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
                
                if (tableTranslator!=EnumTableTranslator.TEXT_LISTE_VORLAGE) {
                	oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btSave._setLDC(new RB_gld()._ins(0, 0, 5, 0)._left_mid()));
                	oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btLoad._setLDC(new RB_gld()._ins(0, 0, 5, 0)._left_mid()));
                }
               
                
                oThis.searcher = new TL_LIST_DATASEARCH(oThis.m_tpHashMap);
                oThis.searcher.getLabelSuchBeschriftung().setVisible(false);
                oThis.searcher._setWidthSearchText(150);
                
                oThis.m_tpHashMap.getNaviListCompact()._addRightComponent(oThis.searcher._setLDC(new RB_gld()._ins(20, 0, 10, 0)._left_mid()));
                oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST,this);
 
                listComponentMap = new TL_LIST_ComponentMap(oThis.m_tpHashMap);
                oThis.m_tpHashMap.getNaviListCompact().INIT_WITH_ComponentMAP(listComponentMap,  E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
                this.add(oThis.m_tpHashMap.getNaviListCompact(), E2_INSETS.I_2_2_2_2);
                oThis.m_tpHashMap.getNaviListCompact()._REBUILD_COMPLETE_LIST("");
                oThis.m_tpHashMap.getNaviListCompact().get_oComponentMAP__REF().get_oSQLFieldMAP()._clearOrders()._addOrderUp(TEXT_LISTE.position);
                oThis.m_tpHashMap.getNaviListCompact()._rebuildTitleLine();
                
	        } else {
	        
                oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST,this);
                oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.NAVILIST,new E2_NavigationList());
                
                listComponentMap = new TL_LIST_ComponentMap(oThis.m_tpHashMap);
                oThis.m_tpHashMap.getNavigationList().INIT_WITH_ComponentMAP(listComponentMap, E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
                oThis.bedienPanel = new TL_LIST_BedienPanel(TL_MASK_DaughterListForMotherMask.this.m_tpHashMap);
                oThis.searcher = oThis.bedienPanel.getDataSearch();
                this.add(oThis.bedienPanel, E2_INSETS.I_2_2_2_2);
                this.add(oThis.m_tpHashMap.getNavigationList(), E2_INSETS.I_2_2_2_2);
                oThis.m_tpHashMap.getNaviListCompact().get_oComponentMAP__REF().get_oSQLFieldMAP()._clearOrders()._addOrderUp(TEXT_LISTE.position);
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
					if (c instanceof TL_LIST_bt_DeleteInListRow) {
						((TL_LIST_bt_DeleteInListRow)c).setEnabledForEditOfSuperClass(false);
					}
					if (c instanceof TL_LIST_bt_ListToMaskInListRow) {
						if ( ((TL_LIST_bt_ListToMaskInListRow)c).isUsedToEdit()) {
							((TL_LIST_bt_ListToMaskInListRow)c).setEnabledForEditOfSuperClass(false);
						}
					}
					
				}
			}
		}
    	
    }
    
}
 
 
 
