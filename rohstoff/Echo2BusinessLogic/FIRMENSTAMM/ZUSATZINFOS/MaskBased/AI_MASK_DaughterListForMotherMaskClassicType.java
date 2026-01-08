 
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS.MaskBased;
 
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
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
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.DAUGHTER_INTERFACE.IF_MaskDaughter_Activater;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO;
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
public class AI_MASK_DaughterListForMotherMaskClassicType extends E2_Grid implements  MyE2IF_DB_SimpleComponent, IF_MaskDaughter_Activater {
 	
 	//fieldKey fuer das einbinden dieser komponente in einer mother-mask (wird in zwei varianten eingebunden
	public static String  keyInfo4Mask = "0dec1a3b-1892-4ed1-a7a4-ff033b9464b2";
	public static String  keyMeldung4Mask = "0dec1a3b-1892-4ed1-a7a4-ff033b9464b2";
	
 	private String  m_actualIdUnformated = null;
 	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    //entscheidet, ob die Tochter eine komplette oder eine reduzierte E2_NavigationList ist
    private AI_LIST_BedienPanel 	bedienPanel = null;
    private VEK<MyE2IF__Component>  toDisableAtMask = new VEK<MyE2IF__Component>();
    private AI_LIST_DATASEARCH  	searcher = null;
    
    private boolean    				m_enabled = true;
    
    private E2_BasicModuleContainer m_listContainer = null;
    
    
	/*
	 * schalter, der das object aktiv macht, d.h. wenn innerhalb eines tabbed-panes mehrer komplexe toechter vorhanden sind,
	 * dann koennen diese die maske stark hindern, z.b. beim blaettern. dann kann das object beim klicken auf 
	 * den passenden tab aktiv gemacht werden und wird erst dann aufgebaut
	 */
	private boolean 				maskActive = false;
	

    /**
     * 
     * @param p_tpHashMap
     * @param useCompactList
     * @throws myException
     */
    public AI_MASK_DaughterListForMotherMaskClassicType(AI__TYP typ, boolean useCompactList) throws myException {
        super();
        this._setSize(800);
        
        this.m_listContainer = new InternalLIST_BasicModuleContainer(typ, null,true);
        
        this.initForUse(this.m_listContainer);
        this._a(this.m_listContainer);
        
 	}
    
    
	public IF_Field getLinkfieldToMotherTable() {
		return ADRESSE_INFO.id_adresse;
	}
	public RB_TransportHashMap getTransportHashMap() {
		return m_tpHashMap;
	}
	public AI_MASK_DaughterListForMotherMaskClassicType _setTransportHashMap(RB_TransportHashMap p_tpHashMap) {
		this.m_tpHashMap = p_tpHashMap;
		return this;
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
   
	
	/**
	 * innerer basicmodulContainer fuer die einlagerung in eine MotherMask
	 * @author martin
	 *
	 */
	public class InternalLIST_BasicModuleContainer extends Project_BasicModuleContainer    {
		
	    public InternalLIST_BasicModuleContainer(AI__TYP typ, RB_TransportHashMap p_params, boolean useCompactList) throws myException    {
	    	
	        super("");
	        
	        
	        this.set_MODUL_IDENTIFIER(typ==AI__TYP.INFO?MODUL.ADRESSE_INFO_EMBEDDED_LIST.get_callKey():MODUL.ADRESSE_MESSAGE_EMBEDDED_LIST.get_callKey());

	        
	        AI_MASK_DaughterListForMotherMaskClassicType oThis = AI_MASK_DaughterListForMotherMaskClassicType.this;
	        
	        oThis.m_tpHashMap = new RB_TransportHashMap();  
	        oThis.m_tpHashMap._setToExtender(AI_TransportExtender.TYP_INFO_OR_MELDUNG, typ);

	        
	        //die transporthashmap muss jetzt in die ableitende klasse
	        oThis._setTransportHashMap(oThis.m_tpHashMap);
	        
	        //dann den ganzen paramhash der mutter in den tochterhash einfuegen
	        oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.MOTHERPARAMSHASH, p_params);
 
	        oThis.m_tpHashMap._setModulContainerList(this);
	        oThis.m_tpHashMap._setLeadingMaskKey(AI_CONST.getLeadingMaskKey());
	        oThis.m_tpHashMap._setLeadingTableOnMask(AI_CONST.getLeadingTable());
 
	        this.set_bVisible_Row_For_Messages(false);
 	        
 	        
	        AI_LIST_ComponentMap listComponentMap  = null;
 	        
	        if (useCompactList) {
                oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.NAVILIST,new E2_NavigationListCompact());
 
                 
                AI_LIST_bt_New btNew = new AI_LIST_bt_New(oThis.m_tpHashMap);
                AI_LIST_bt_Copy btCopy = new AI_LIST_bt_Copy(oThis.m_tpHashMap);
                
                AI_LIST_bt_ListToMask btEdit = new AI_LIST_bt_ListToMask(true,oThis.m_tpHashMap);
                AI_LIST_bt_ListToMask btView = new AI_LIST_bt_ListToMask(false,oThis.m_tpHashMap);
                
                oThis.toDisableAtMask._a(btNew)._a(btCopy)._a(btEdit)._a(btView);
                oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oThis.m_tpHashMap.getNaviListCompact())._setLDC(new RB_gld()._ins(2, 0, 10, 0)._left_mid()));
                oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btNew._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
                oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btCopy._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
                oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btEdit._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
                oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btView._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
                
                oThis.searcher = new AI_LIST_DATASEARCH(oThis.m_tpHashMap);
                oThis.searcher.getLabelSuchBeschriftung().setVisible(false);
                oThis.searcher._setWidthSearchText(150);
                
                oThis.m_tpHashMap.getNaviListCompact()._addRightComponent(oThis.searcher._setLDC(new RB_gld()._ins(20, 0, 10, 0)._left_mid()));
                oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST,this);
 
                listComponentMap = new AI_LIST_ComponentMap(oThis.m_tpHashMap);
                oThis.m_tpHashMap.getNaviListCompact().INIT_WITH_ComponentMAP(listComponentMap,  E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
                this.add(oThis.m_tpHashMap.getNaviListCompact(), E2_INSETS.I_2_2_2_2);
                oThis.m_tpHashMap.getNaviListCompact()._REBUILD_COMPLETE_LIST("");
                oThis.m_tpHashMap.getNaviListCompact()._rebuildTitleLine();
                
	        } else {
	        
                oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST,this);
                oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.NAVILIST,new E2_NavigationList());
                
                listComponentMap = new AI_LIST_ComponentMap(oThis.m_tpHashMap);
                oThis.m_tpHashMap.getNavigationList().INIT_WITH_ComponentMAP(listComponentMap, E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
                oThis.bedienPanel = new AI_LIST_BedienPanel(AI_MASK_DaughterListForMotherMaskClassicType.this.m_tpHashMap);
                oThis.searcher = oThis.bedienPanel.getDataSearch();
                this.add(oThis.bedienPanel, E2_INSETS.I_2_2_2_2);
                this.add(oThis.m_tpHashMap.getNavigationList(), E2_INSETS.I_2_2_2_2);
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
					if (c instanceof AI_LIST_bt_DeleteInListRow) {
						((AI_LIST_bt_DeleteInListRow)c).setEnabledForEditOfSuperClass(false);
					}
					if (c instanceof AI_LIST_bt_ListToMaskInListRow) {
						if ( ((AI_LIST_bt_ListToMaskInListRow)c).isUsedToEdit()) {
							((AI_LIST_bt_ListToMaskInListRow)c).setEnabledForEditOfSuperClass(false);
						}
					}
					
				}
			}
		}
    	
    }
	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String ___textUnused___ , String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
		
		String idToMother = oResultMAP.get_UnFormatedValue(this.getLinkfieldToMotherTable().fn());
		MyLong l = new MyLong(idToMother);
		if (l.isOK()) {
			m_actualIdUnformated=l.get_cUF_LongString();
		}
		
		
		if (maskActive) {
			this.rb_set_db_value_manual(m_actualIdUnformated);
		}
		
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this.rb_set_db_value_manual("");
	}
    
	
	private class SearchRestriction extends XX_SearchAddonBedingung {
		@Override
		public String get_AddOnBedingung() throws myException {
			return getRestictTerm4Daughter();
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

		//damit die feldbeschraenkungen innerhalb der tochterkomponente funktionieren
		if (S.isEmpty(idUf)) {
			this._clear();
			this.m_actualIdUnformated=null;
			this.m_tpHashMap._setMotherKeyValue(null);
			this._a(new RB_lab(S.ms("Bitte speichern Sie zuerst den aktuellen Datensatz ..."))._fsa(4), new RB_gld()._left_mid()._ins(5));
		} else {
			//damit die feldbeschraenkungen innerhalb der tochterkomponente funktionieren
			this.m_actualIdUnformated=l.get_oLong().toString();
			this.m_tpHashMap._setMotherKeyValue(l.get_oLong());
			
			this._clear();
			this._setSize(new Extent(100, Extent.PERCENT));
			this._a(this.m_listContainer);
			((SQLFieldForRestrictTableRange)m_listContainer.get_oNaviListFirstAdded().get_oComponentMAP__REF().get_oSQLFieldMAP().get_(this.getLinkfieldToMotherTable().fieldName())).set_cRestrictionValue_IN_DB_FORMAT(idUf);
			
			m_listContainer.get_oNaviListFirstAdded().get_oComponentMAP__REF().get_oSQLFieldMAP().clear_BEDINGUNG_STATIC();
			m_listContainer.get_oNaviListFirstAdded().get_oComponentMAP__REF().get_oSQLFieldMAP().add_BEDINGUNG_STATIC(this.getRestictTerm4Daughter());
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
		
		this.m_tpHashMap._setMotherKeyLookupField(this.getLinkfieldToMotherTable());
		
		//jetzt nachsehen, ob in der sqlfieldmap ein korrectes restrict-field vorhanden ist, wenn nein, einfuegen
		if (	this.m_listContainer!=null 
				&& this.m_listContainer.get_oNaviListFirstAdded()!=null 
				&& this.m_listContainer.get_oNaviListFirstAdded().get_oComponentMAP__REF()!=null 
				&& this.m_listContainer.get_oNaviListFirstAdded().get_oComponentMAP__REF().get_oSQLFieldMAP()!=null) {
		
			SQLFieldMAP fm = this.m_listContainer.get_oNaviListFirstAdded().get_oComponentMAP__REF().get_oSQLFieldMAP();
			IF_Field f = this.getLinkfieldToMotherTable();
			SQLField sf = fm.get(f.fn());
//			MyE2IF__DB_Component  comp = this.m_listContainer.get_oNaviListFirstAdded().get_oComponentMAP__REF().get__DBComp(f.fn());
			
			if (sf != null) {
				if (!(sf instanceof SQLFieldForRestrictTableRange)) {
					//dann das feld erzeugen, in sqlfieldmap einfuegen, initialisieren und der komponente unterschieben
					SQLFieldForRestrictTableRange sfr = new SQLFieldForRestrictTableRange(f.tn(), f.fn(), f.fn(), S.ms(f.fn()), "", bibE2.get_CurrSession());
					fm.add_SQLField(sfr,true);
					fm.initFields();
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
	 * @see panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.DAUGHTER_INTERFACE.IF_MaskDaughter_Activater#set_bIsActive(boolean)
	 */
	@Override
	public void set_bIsActive(boolean isActive) throws myException {
		maskActive=isActive;
		if (isActive) {
			rb_set_db_value_manual(m_actualIdUnformated);
		}
	}
	
	private String getRestictTerm4Daughter() throws myException {
		if (m_tpHashMap==null || m_tpHashMap.getFromExtender(AI_TransportExtender.TYP_INFO_OR_MELDUNG)==null) {
			throw new myException(this,"Init-process is not yet completed!");
		}
		
		if (m_tpHashMap.getMotherKeyValue()!=null) {
			AI__TYP 		typ = (AI__TYP)m_tpHashMap.getFromExtender(AI_TransportExtender.TYP_INFO_OR_MELDUNG);
			String          termId   = this.getLinkfieldToMotherTable().tnfn()+"="+m_tpHashMap.getMotherKeyValue().toString();
			String          termTyp  = "NVL("+ADRESSE_INFO.ist_message.fn()+",'N')="+(typ==AI__TYP.MELDUNG?"'Y'":"'N'"); 
			return "("+termId+" AND "+termTyp+")";
		} else {
			//bei leerer maske
			return "1=2";
		}
	}
	
}
 
 
 
