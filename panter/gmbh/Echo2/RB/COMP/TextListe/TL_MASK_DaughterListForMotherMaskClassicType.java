 
package panter.gmbh.Echo2.RB.COMP.TextListe;
 
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.If_ComponentWithOwnKey;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationListCompact;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.XX_SearchAddonBedingung;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF_DB_SimpleComponent;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
 
/**
 * @author martin
 * Komponente fuer eine maske eine mothertable, die diese als fullDaughter einblendet
 * Definiert fuer die klassiche Varinate der Masken (nicht RB...)
 */
public class TL_MASK_DaughterListForMotherMaskClassicType extends E2_Grid implements  MyE2IF_DB_SimpleComponent, If_ComponentWithOwnKey {
 	
 	//fieldKey fuer das einbinden dieser komponente in einer mother-mask
	public static String  keyForMotherMask = "TL_REPORTING_QUERY_MASK_DaughterListForMotherMask";
	
 	private String  m_actualIdUnformated = null;
 	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    //entscheidet, ob die Tochter eine komplette oder eine reduzierte E2_NavigationList ist
    private TL_LIST_BedienPanel 	bedienPanel = null;
    private VEK<MyE2IF__Component>  toDisableAtMask = new VEK<MyE2IF__Component>();
    private TL_LIST_DATASEARCH  	searcher = null;
    
    private boolean    				m_enabled = true;
    
    private E2_BasicModuleContainer m_listContainer = null;
    
    
    private String   				beschriftungIdField = null;   //beschriftung des id-feldes der mothertable auf der maske

    private EnumTableTranslator     tableTranslator = null;    	// hier werden die angaben hinterlegt, die noetig sind, um eine 
    															// liste speichern / laden zu koennen
																// der Speicher/lade-Button wird nur angezeigt, wenn 
    															// tableTranslator.getSaveKeyForVorlage() != null
																				   

    /**
     * 
     * @author martin
     * @date 30.03.2020
     *
     * @param useCompactList
     * @param beschriftungIdFeld
     * @param p_tableTranslator
     * @throws myException
     */
    public TL_MASK_DaughterListForMotherMaskClassicType( boolean useCompactList
    													, String beschriftungIdFeld
    	    											, EnumTableTranslator p_tableTranslator
   													) throws myException {
        super();
        
        this.beschriftungIdField = beschriftungIdFeld;
        this.tableTranslator = p_tableTranslator;	
       
        this._setSize(800);
        this._w100();
        
        this.m_listContainer = new InternalLIST_BasicModuleContainer(null,true);
        
        this.initForUse(this.m_listContainer);
        this._a(this.m_listContainer);
        
 	}
    
    
	public IF_Field getLinkfieldToMotherTable() {
		return TEXT_LISTE.id_table;
	}
	public RB_TransportHashMap getTransportHashMap() {
		return m_tpHashMap;
	}
	public TL_MASK_DaughterListForMotherMaskClassicType _setTransportHashMap(RB_TransportHashMap p_tpHashMap) {
		this.m_tpHashMap = p_tpHashMap;
		return this;
	}
    
    @Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
    	this.m_enabled = enabled; 
    	
		boolean realEnabled = enabled && this.EXT().is_ValidEnableAlowed()  && this.EXT().get_bCanBeEnabled();

    	
		//falls ausfuehrliche variante
		if (this.bedienPanel!=null) {
			this.bedienPanel.set_bEnabled_For_Edit(realEnabled);
		}
		
		//oder kurze variante
		for (MyE2IF__Component c: this.toDisableAtMask) {
			c.set_bEnabled_For_Edit(realEnabled);
		}
		
		//jetzt direkt den mapsettingagent starten
		for (E2_ComponentMAP map: this.m_tpHashMap.getNavigationList().get_vComponentMAPS()) {
			for (XX_ComponentMAP_SubqueryAGENT subagent: map.get_vComponentMapSubQueryAgents()) {
				subagent.fillComponents(map, map.get_oInternalSQLResultMAP());
			}
		}
		
		
	}
   
	
	/**
	 * innerer basicmodulContainer fuer die einlagerung in eine MotherMask
	 * @author martin
	 *
	 */
	public class InternalLIST_BasicModuleContainer extends Project_BasicModuleContainer    {
		
	    public InternalLIST_BasicModuleContainer(RB_TransportHashMap tpHashmapFromCallingModule, boolean useCompactList) throws myException    {
	    	
	        super(E2_MODULNAME_ENUM.MODUL.TL_LIST_LIST.get_callKey());
	        
	        TL_MASK_DaughterListForMotherMaskClassicType oThis = TL_MASK_DaughterListForMotherMaskClassicType.this;
	        
	        oThis.m_tpHashMap = new RB_TransportHashMap();  
	        
	        oThis.m_tpHashMap._putSB("BESCHRIFTUNG_ID_FELD_MASK", beschriftungIdField);
	        oThis.m_tpHashMap._putSB(TL_CONST.TabTrans, tableTranslator);
	        
	        //dann den ganzen paramhash der mutter in den tochterhash einfuegen
	        oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.MOTHERPARAMSHASH, tpHashmapFromCallingModule);

	        oThis.m_tpHashMap._setModulContainerList(this);
	        oThis.m_tpHashMap._setLeadingMaskKey(TL_CONST.getLeadingMaskKey());
	        oThis.m_tpHashMap._setLeadingTableOnMask(TL_CONST.getLeadingTable());
 
	        this.set_bVisible_Row_For_Messages(false);
 	        
 	        
	        TL_LIST_ComponentMap listComponentMap  = null;
 	        
	        if (useCompactList) {
                oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.NAVILIST,new E2_NavigationListCompact());
 
                 
                TL_LIST_bt_New btNew = (TL_LIST_bt_New) new TL_LIST_bt_New(oThis.m_tpHashMap)._ttt(S.ms("Neuen Texteintrag erzeugen"));
                TL_LIST_bt_Copy btCopy = (TL_LIST_bt_Copy) new TL_LIST_bt_Copy(oThis.m_tpHashMap)._ttt(S.ms("Texteintrag kopieren"));
                TL_LIST_btSort  btSort = (TL_LIST_btSort) new TL_LIST_btSort(oThis.m_tpHashMap)._ttt(S.ms("Textliste sortieren"));
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
                	oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btSave._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
                	oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btLoad._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
                }
                
                oThis.searcher = new TL_LIST_DATASEARCH(oThis.m_tpHashMap);
                oThis.searcher.getLabelSuchBeschriftung().setVisible(false);
                oThis.searcher._setWidthSearchText(150);
                
                oThis.m_tpHashMap.getNaviListCompact()._addRightComponent(oThis.searcher._setLDC(new RB_gld()._ins(20, 0, 10, 0)._left_mid()));
                oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST,this);
 
                listComponentMap = new TL_LIST_ComponentMap(oThis.m_tpHashMap);
                oThis.m_tpHashMap.getNaviListCompact().INIT_WITH_ComponentMAP(listComponentMap,  E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
                this.add(oThis.m_tpHashMap.getNaviListCompact(), E2_INSETS.I_2_2_2_2);
                oThis.m_tpHashMap.getNaviListCompact().get_oComponentMAP__REF().get_oSQLFieldMAP()._clearOrders()._addOrderUp(TEXT_LISTE.position);
                oThis.m_tpHashMap.getNaviListCompact()._REBUILD_COMPLETE_LIST("");
                oThis.m_tpHashMap.getNaviListCompact()._rebuildTitleLine();
                
	        } else {
	        
                oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST,this);
                oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.NAVILIST,new E2_NavigationList());
                
                listComponentMap = new TL_LIST_ComponentMap(oThis.m_tpHashMap);
                oThis.m_tpHashMap.getNavigationList().INIT_WITH_ComponentMAP(listComponentMap, E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
                oThis.bedienPanel = new TL_LIST_BedienPanel(TL_MASK_DaughterListForMotherMaskClassicType.this.m_tpHashMap);
                oThis.searcher = oThis.bedienPanel.getDataSearch();
                this.add(oThis.bedienPanel, E2_INSETS.I_2_2_2_2);
                this.add(oThis.m_tpHashMap.getNavigationList(), E2_INSETS.I_2_2_2_2);
                oThis.m_tpHashMap.getNaviListCompact().get_oComponentMAP__REF().get_oSQLFieldMAP()._clearOrders()._addOrderUp(TEXT_LISTE.position);
                oThis.bedienPanel.get_list_Selector().get_oSelVector().doActionPassiv();
	        }
	        
	        listComponentMap.add_oSubQueryAgent(new mapSettingAgent());
	        
	    }
	}
	
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
    
	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String ___textUnused___ , String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
		String idToMother = oResultMAP.get_UnFormatedValue(tableTranslator.getTableForTextListe().keyFieldName());
		this.rb_set_db_value_manual(idToMother);
	}
	
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this.rb_set_db_value_manual("");
	}
    
	
	private class SearchRestriction extends XX_SearchAddonBedingung {
		@Override
		public String get_AddOnBedingung() throws myException {
			
			TL_MASK_DaughterListForMotherMaskClassicType oThis = TL_MASK_DaughterListForMotherMaskClassicType.this;
			
			if (S.isFull(oThis.m_actualIdUnformated)) {
				return oThis.getLinkfieldToMotherTable().tnfn()+"="+oThis.m_actualIdUnformated;
			} else {
				return "1=2";
			}
		}
	}
	
	public String getActualIdUnformated() {
		return m_actualIdUnformated;
	}
	
	public void rb_set_db_value_manual(String id_of_mothertable) throws myException {
		MyLong l = new MyLong(id_of_mothertable);
		String idUf = "";
		if (l.isOK()) {
			idUf = l.get_cUF_LongString();
		}
		
		if (S.isEmpty(id_of_mothertable)) {
			this.m_tpHashMap._setMotherKeyValue(null);

			
			this._clear();
			this.m_actualIdUnformated=null;
			this._a(new RB_lab(S.ms("Bitte speichern Sie zuerst den aktuellen Datensatz ..."))._fsa(4), new RB_gld()._left_mid()._ins(5));
		} else {
			this._clear();
			this._setSize(new Extent(100, Extent.PERCENT));
			this._a(this.m_listContainer);
			this.m_actualIdUnformated=idUf;
			
			this.m_tpHashMap._setMotherKeyValue(l.get_oLong());
			this.m_tpHashMap._setMotherKeyLookupField(this.getLinkfieldToMotherTable());
			
			((SQLFieldForRestrictTableRange)m_listContainer.get_oNaviListFirstAdded().get_oComponentMAP__REF().get_oSQLFieldMAP().get_(this.getLinkfieldToMotherTable().fieldName())).set_cRestrictionValue_IN_DB_FORMAT(idUf);
			m_listContainer.get_oNaviListFirstAdded()._REBUILD_COMPLETE_LIST("");
		}
	}
	
	
	/**
	 * schaut sich die eingebettete navilist an und fuehrt gegebenenfalls ein SQLField4restict ein
	 * @param listContainer
	 * @throws myException
	 */
	private void initForUse(E2_BasicModuleContainer listContainer) throws myException {
		this.m_listContainer = listContainer;
		
		//jetzt nachsehen, ob in der sqlfieldmap ein korrectes restrict-field vorhanden ist, wenn nein, einfuegen
		if (	this.m_listContainer!=null 
				&& this.m_listContainer.get_oNaviListFirstAdded()!=null 
				&& this.m_listContainer.get_oNaviListFirstAdded().get_oComponentMAP__REF()!=null 
				&& this.m_listContainer.get_oNaviListFirstAdded().get_oComponentMAP__REF().get_oSQLFieldMAP()!=null) {
		
			SQLFieldMAP fm = this.m_listContainer.get_oNaviListFirstAdded().get_oComponentMAP__REF().get_oSQLFieldMAP();
			IF_Field f = this.getLinkfieldToMotherTable();
			SQLField sf = fm.get(f.fn());
			MyE2IF__DB_Component  comp = this.m_listContainer.get_oNaviListFirstAdded().get_oComponentMAP__REF().get__DBComp(f.fn());
			
			if (sf != null && comp != null) {
				if (!(sf instanceof SQLFieldForRestrictTableRange)) {
					//dann das feld erzeugen, in sqlfieldmap einfuegen, initialisieren und der komponente unterschieben
					SQLFieldForRestrictTableRange sfr = new SQLFieldForRestrictTableRange(f.tn(), f.fn(), f.fn(), S.ms(f.fn()), "", bibE2.get_CurrSession());
					fm.add_SQLField(sfr,true);
					fm.initFields();
					comp.EXT_DB().set_oSQLField(sfr);
				}
			}
		}
		
		this._setSize(new Extent(100, Extent.PERCENT));
		//sonderbedingung in der Suche
		if (this.getSearcher() != null) {
			this.getSearcher().add_XX_SearchAddonBedingung_to_all_SearchDefinitions(new SearchRestriction());
		}
		
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ListAndMask.If_ComponentWithOwnKey#key()
	 */
	@Override
	public String key() throws myException {
		return TL_MASK_DaughterListForMotherMaskClassicType.keyForMotherMask;
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ListAndMask.If_ComponentWithOwnKey#userText()
	 */
	@Override
	public String userText() throws myException {
		return "Textliste";
	}
	
	
	
}
 
 
 
