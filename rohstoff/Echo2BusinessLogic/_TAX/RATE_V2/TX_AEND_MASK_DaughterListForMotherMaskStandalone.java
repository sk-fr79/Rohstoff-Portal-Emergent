 
package rohstoff.Echo2BusinessLogic._TAX.RATE_V2;
 
import java.util.Date;
import java.util.Vector;

import org.apache.lucene.document.DateTools;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V22;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationListCompact;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.IF_RbComponentWithOwnKey;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_MessageTranslator;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterBasedOnBasicModulContainer;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_Delete;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_List2Mask;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_New;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_NewCopy;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V22;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V22;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonAttachmentUseInListRow;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS.TAX_AENDERUNGEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;
 
 
/**
 * @author martin
 * Komponente fuer eine maske eine mothertable, die diese als fullDaughter einblendet
 */
public class TX_AEND_MASK_DaughterListForMotherMaskStandalone extends RB_MaskDaughterBasedOnBasicModulContainer implements IF_RbComponentWithOwnKey {
 	
 	//fieldKey fuer das einbinden dieser komponente in einer mother-mask
	public static RB_KF  keyForMotherMask = (RB_KF)new RB_KF()._setHASHKEY("TX_AEND_TAX_AENDERUNGEN_MASK_DaughterListForMotherMask")._setREALNAME("TX_AEND_TAX_AENDERUNGEN_MASK_DaughterListForMotherMask");
	
 	
 	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    //entscheidet, ob die Tochter eine komplette oder eine reduzierte E2_NavigationList ist
//    private TX_AEND_LIST_BedienPanel 	bedienPanel = null;
    private VEK<MyE2IF__Component>  toDisableAtMask = new VEK<MyE2IF__Component>();
    private TX_AEND_LIST_DATASEARCH  	searcher = null;
    
    private boolean    				m_enabled = true;
    
    /**
     * 
     * @param p_tpHashMap
     * @param useCompactList
     * @throws myException
     */
    public TX_AEND_MASK_DaughterListForMotherMaskStandalone(RB_TransportHashMap p_tpHashMap, boolean useCompactList) throws myException {
        super();
    
        this._init(new InternalLIST_BasicModuleContainer(p_tpHashMap, useCompactList));
 	}
  
	@Override
	public RB_KF getFieldKey() {
		return TX_AEND_MASK_DaughterListForMotherMaskStandalone.keyForMotherMask;
	}
 
    
    
    @Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
    	this.m_enabled = enabled; 
    	
//		//falls ausfuehrliche variante
//		if (this.bedienPanel!=null) {
//			this.bedienPanel.set_bEnabled_For_Edit(enabled);
//		}
//		
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
		return TAX_AENDERUNGEN.id_tax;
	}
	
	/**
	 * innerer basicmodulContainer fuer die einlagerung in eine MotherMask
	 * @author martin
	 *
	 */
	public class InternalLIST_BasicModuleContainer extends Project_BasicModuleContainer    {
		
	    public InternalLIST_BasicModuleContainer(RB_TransportHashMap p_params, boolean useCompactList) throws myException    {
	    	
	        super(E2_MODULNAME_ENUM.MODUL.TAX_AENDERUNGEN_LIST.get_callKey());
	        
	        TX_AEND_MASK_DaughterListForMotherMaskStandalone oThis = TX_AEND_MASK_DaughterListForMotherMaskStandalone.this;
	        
	        oThis.m_tpHashMap = new RB_TransportHashMap();  
	        //die transporthashmap muss jetzt in die ableitende klasse
	        oThis._setTransportHashMap(oThis.m_tpHashMap);
	        
	        //dann den ganzen paramhash der mutter in den tochterhash einfuegen
	        oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.MOTHERPARAMSHASH, p_params);
 
	        oThis.m_tpHashMap._setModulContainerList(this);
	        oThis.m_tpHashMap._setLeadingMaskKey(TX_AEND_CONST.getLeadingMaskKey());
	        oThis.m_tpHashMap._setLeadingTableOnMask(TX_AEND_CONST.getLeadingTable());
 
	        this.set_bVisible_Row_For_Messages(false);
 	        
 	        
	        TX_AEND_LIST_ComponentMap listComponentMap  = null;
 	        
            oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.NAVILIST,new E2_NavigationListCompact());
 
                 
            TX_AEND_LIST_bt_New btNew = new TX_AEND_LIST_bt_New(oThis.m_tpHashMap);
            TX_AEND_LIST_bt_Copy btCopy = new TX_AEND_LIST_bt_Copy(oThis.m_tpHashMap);
                
            oThis.toDisableAtMask._a(btNew)._a(btCopy);
            oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oThis.m_tpHashMap.getNaviListCompact())._setLDC(new RB_gld()._ins(2, 0, 10, 0)._left_mid()));
            oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btNew._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
            oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btCopy._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
            oThis.searcher = new TX_AEND_LIST_DATASEARCH(oThis.m_tpHashMap);
            oThis.searcher.getLabelSuchBeschriftung().setVisible(false);
            oThis.searcher._setWidthSearchText(150);
                
            oThis.m_tpHashMap.getNaviListCompact()._addRightComponent(oThis.searcher._setLDC(new RB_gld()._ins(20, 0, 10, 0)._left_mid()));
            oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST,this);
 
            listComponentMap = new TX_AEND_LIST_ComponentMap(oThis.m_tpHashMap);
            oThis.m_tpHashMap.getNaviListCompact().INIT_WITH_ComponentMAP(listComponentMap,  E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
            this.add(oThis.m_tpHashMap.getNaviListCompact(), E2_INSETS.I_2_2_2_2);
            oThis.m_tpHashMap.getNaviListCompact()._REBUILD_COMPLETE_LIST("");
            oThis.m_tpHashMap.getNaviListCompact()._rebuildTitleLine();
                
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
					if (c instanceof TX_AEND_LIST_bt_DeleteInListRow) {
						((TX_AEND_LIST_bt_DeleteInListRow)c).setEnabledForEditOfSuperClass(false);
					}
					if (c instanceof TX_AEND_LIST_bt_ListToMaskInListRow) {
						if ( ((TX_AEND_LIST_bt_ListToMaskInListRow)c).isUsedToEdit()) {
							((TX_AEND_LIST_bt_ListToMaskInListRow)c).setEnabledForEditOfSuperClass(false);
						}
					}
					
				}
			}
		}
    	
    }
    
    
    
    private static class TX_AEND_CONST {
        public enum TX_AEND_NAMES implements IF_enum_4_db {
            
            CHECKBOX_LISTE( S.ms("Auswahl-Checkbox"))
            ,MARKER_LISTE( S.ms("Markierung Liste"))
            ,DIRECT_DEL( S.ms("Loeschbutton in Listenzeile"))
            ,DIRECT_EDIT( S.ms("Editbutton in Listenzeile"))
            ,DIRECT_VIEW( S.ms("Anzeigebutton in Listenzeile"))
            ,DIRECT_UPLOAD( S.ms("Dateien hochladen"))
            ,DATASET_NAME(S.ms("Steuer-Ausnahme-Zeitraum"))
            ;
            
            private MyE2_String userText = null; 
            private String      m_dbVal = null;
            
            private TX_AEND_NAMES(MyE2_String p_userText) {
                this.userText = p_userText;
            }
            
            //konstuktor mit abweichenden werten
            private TX_AEND_NAMES(MyE2_String p_userText, String dbVal) {
                this.userText = p_userText;
                this.m_dbVal=dbVal;
            }
            
            @Override
            public String db_val() {
                if (S.isFull(m_dbVal)) {
                    return m_dbVal;
                }
               return this.name();
            }
            
            @Override
            public String user_text() {
                if (S.isEmpty(this.userText)) {
                    return this.name();
                } else {
                    return this.userText.CTrans();
                }
            }
            
            public MyE2_String getUserText() {
                if (this.userText!=null && S.isFull(this.userText)) {
                    return this.userText;
                }
                return S.msUt(this.name());
            }
                    
            
            
            @Override
            public String user_text_lang() {
                return this.user_text();
            }
            
            @Override
            public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
                return null;
            }
            
        }
        
        
        
        /**
         * 
         * @return der ueberall verwendete mask-key
         * @throws myException
         */
        public static _TAB getLeadingTable() throws myException {
            return _TAB.tax_aenderungen;
        }
        
        
        /**
         * 
         * @return der ueberall verwendete mask-key
         * @throws myException
         */
        public static RB_KM getLeadingMaskKey() throws myException {
            return _TAB.tax_aenderungen.rb_km();
        }
        
        
        
        
        

        
        /*
         * enum: hier koennen numerische (long) werte zentral gesteuert werden 
         */
        private static enum TX_AEND_NUM_CONST {
             MASKPOPUP_WIDTH(new Integer(600))
            ,MASKPOPUP_HEIGHT(new Integer(400))
            ,MASKPOPUP_DESCRIPTION_COL_SIZE(new Integer(100))
            ,MASKPOPUP_FIELD_COL_SIZE(new Integer(700))
            ;
            
            private Integer   m_value = null;
            
            private TX_AEND_NUM_CONST(Integer p_value) {
                this.m_value=p_value;
            }
            
            public Integer getValue() {
                return this.m_value;
            }
        }
    }
    
    
    

    
    
    
    private class TX_AEND_LIST_bt_Copy extends RB_BtV4_NewCopy {
	
        public TX_AEND_LIST_bt_Copy(RB_TransportHashMap  p_tpHashMap) {
            super();
            this.add_GlobalValidator(TX_AEND_VALIDATORS.NEW.getValidator());
            this._setTransportHashMap(p_tpHashMap);
            
            this.set_text4MaskTitel(S.ms("Kopie eines Datensatzes vom Typ: ").ut(TX_AEND_CONST.TX_AEND_NAMES.DATASET_NAME.user_text()));

        }
     
        
        @Override
        public RB_ModuleContainerMASK generateMaskContainer() throws myException {
            this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK,new TX_AEND_MASK_MaskModulContainer(this.getTtransportHashMap()));
            return this.getTtransportHashMap().getRBModulContainerMask();
        }
        
        
        @Override
        public RB_DataobjectsCollector generateDataObjects4MaskCopy(Long idToCopy) throws myException {
     
            if (idToCopy==null) {
                throw new myException("Cannot copy null-id");
            }
            this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,new TX_AEND_MASK_DataObjectCollector(this.getTtransportHashMap(), idToCopy.toString(), RB__CONST.MASK_STATUS.NEW_COPY));
            return this.getTtransportHashMap().getMaskDataObjectsCollector();
        }
        
        
        
        @Override
        public RB_DataobjectsCollector generateDataObjects4Copy(String idToCopy) throws myException {
     
            MyLong l = new MyLong(idToCopy);
            
            if (l.isNotOK()) {
                throw new myException("Cannot copy null-id");
            }
            this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,
                        new TX_AEND_MASK_DataObjectCollector(this.getTtransportHashMap(), idToCopy.toString(), RB__CONST.MASK_STATUS.EDIT));
            return this.getTtransportHashMap().getMaskDataObjectsCollector();
        }
     
        
       @Override
       public E2_Break4PopupController getBreak4PopupController4Save() throws myException {
           return null;
       }
       
       
       @Override
       public E2_Break4PopupController getBreak4PopupController4Cancel() throws myException {
           return new E2_Break4PopupController()._registerBreak(
                   new Break4MaskCloseWhenSomethingWasChanged(
                           TX_AEND_LIST_bt_Copy.this.getTtransportHashMap().getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector(), null));
       }
     
        
    }
     
    
    
    
    
    private class TX_AEND_LIST_bt_DeleteInListRow extends RB_BtV4_Delete  {
    
        public TX_AEND_LIST_bt_DeleteInListRow(RB_TransportHashMap p_tpHashMap) {
            super();
            
            this._setShapeDeleteButton();
            this._setTransportHashMap(p_tpHashMap);
            this.setToolTipText(S.ms("Diesen Steuersatz-Zeitraum loeschen").CTrans());
            this.add_GlobalValidator(TX_AEND_VALIDATORS.DELETE.getValidator());
        }
     
        @Override
        public MyE2_String get_message_text_mindestens_eine_irgendwas_markieren() {
            return S.ms("Bitte mindestens eine Zeile zum Loeschen markieren");
        }
     
        @Override
        public MyE2_String get_warnung_achtung_es_wird_ein_irgendwas_geloescht() {
            return S.ms("Soll dieser Eintrag geloescht werden ?");	}
     
        @Override
        public String get_warnung_achtung_es_werden_n_irgendwas_geloescht_mit_platzhalter_fuer_zahl() {
            
            return "Sollen diese #WERT# Eintraege geloescht werden ?";
        }
     
        @Override
        public Vector<String> get_delete_sql_statements(String id_to_delete, MyE2_MessageVector mv) throws myException {
            
            Vector<String> v = new Vector<>();
            
            MyLong lid = new MyLong(id_to_delete);
            
            if (lid.isOK()) {
                Rec22 rec = new Rec22(_TAB.tax_aenderungen)._fill_id(lid.get_oLong());
                v.add(rec.get_DELETE_STATEMENT());
            } else {
                mv._addAlarm(S.ms("Fehler beim erstellen der Delete-Statements !"));
            }
            
            return v;
        }
       
        @Override
        public Vector<Long> getIdsToDelete(MyE2_MessageVector mv) throws myException {
            //in der navilist als listenelement die id aus der aktuellen E2_ComponentMap holen
            
            if (this.EXT().get_oComponentMAP()!=null) {
                MyLong  idToDel = new MyLong(this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
                
                if (idToDel.isOK()) {
                    return new VEK<Long>()._a(idToDel.get_oLong());
                } else {
                    throw new myException("Error finding id to delete");
                }
            } else {
                throw new myException("Error:  no containing E2_ComponentMAP");
       
            }
        }
       
        
        @Override
        public Object get_Copy(Object objHelp) throws myExceptionCopy {
            TX_AEND_LIST_bt_DeleteInListRow copy= new TX_AEND_LIST_bt_DeleteInListRow(this.getTransportHashMap());
            copy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(copy));
            return copy;
        }
      
        @Override
        public void set_bEnabled_For_Edit(boolean enabled) throws myException {
        }
        
        
        /**
         * spezielle methode zum ein permanent enables object doch noch disablen zu koennen
         * @param enabled
         * @throws myException
         */
        public void setEnabledForEditOfSuperClass(boolean enabled) throws myException {
            super.set_bEnabled_For_Edit(enabled);
        }
        
  
    }
 
    
    
    
    private class TX_AEND_LIST_bt_ListToMaskInListRow extends RB_BtV4_List2Mask {
    
        public TX_AEND_LIST_bt_ListToMaskInListRow(boolean bEdit, RB_TransportHashMap  p_tpHashMap) {
            super(bEdit);
            this._setTransportHashMap(p_tpHashMap);
       
            this.add_GlobalValidator(bEdit?TX_AEND_VALIDATORS.EDIT.getValidator():TX_AEND_VALIDATORS.EDIT.getValidator());
            
        }
        @Override
        public RB_ModuleContainerMASK generateMaskContainer() throws myException {
            this.getTransportHashMap()._setRBModulContainerMask(
                    new TX_AEND_MASK_MaskModulContainer(this.getTransportHashMap()));
            return this.getTransportHashMap().getRBModulContainerMask();
        }
      
        
        @Override
        public RB_hm_multi_DataobjectsCollector generateDataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {
            
            TX_AEND_LIST_bt_ListToMaskInListRow oThis = TX_AEND_LIST_bt_ListToMaskInListRow.this;
            
            
            if (this.EXT().get_oComponentMAP()!=null) {
                MyLong  id = new MyLong(this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
                
                if (id.isOK()) {
                    RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
                    RB__CONST.MASK_STATUS aktuellerStatus = this.isUsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;
                    collector.put(id.get_cUF_LongString(), new TX_AEND_MASK_DataObjectCollector(oThis.getTransportHashMap(), id.get_cUF_LongString(),aktuellerStatus));
                    return collector;
                } else {
                    throw new myException("Error finding id to Edit");
                }
            } else {
                throw new myException("Error:  no containing E2_ComponentMAP");
            }
        }
        
        
        @Override
        public MyE2_String generateTitelInfo4MaskWindow() throws myException {
            MyE2_String bearbeiten = S.ms("Bearbeiten eines Datensatzes vom Typ: ").ut(TX_AEND_CONST.TX_AEND_NAMES.DATASET_NAME.getUserText().CTrans());
            MyE2_String anzeigen  = S.ms("Anzeige eines Datensatzes vom Typ: ").ut(TX_AEND_CONST.TX_AEND_NAMES.DATASET_NAME.getUserText().CTrans());
            return  this.isUsedToEdit()?bearbeiten:anzeigen;
            
        }
        
        @Override
        public MyE2_String generateMessagetextForSaveRecord() throws myException {
             return S.ms("Datensatzes vom Typ: ").ut(TX_AEND_CONST.TX_AEND_NAMES.DATASET_NAME.getUserText().CTrans()).t(" wurde gespeichert");
        }
        
        
        @Override
        public Vector<XX_ActionAgentWhenCloseWindow> generateWindowCloseActions() throws myException {
            Vector<XX_ActionAgentWhenCloseWindow>  v_rueck = new Vector<>();
            TX_AEND_LIST_bt_ListToMaskInListRow oThis = TX_AEND_LIST_bt_ListToMaskInListRow.this;
            v_rueck.add(new XX_ActionAgentWhenCloseWindow(oThis.getTransportHashMap().getRBModulContainerMask()) {
                @Override
                public void executeAgentCode(ExecINFO oExecInfo) throws myException {
                    oThis.getTransportHashMap().getNavigationList()._RebuildSiteAndKeepMarkers("");
                }
            });
            
            return v_rueck;
        }
        
        @Override
        public E2_Break4PopupController getBreak4PopupController4Save() throws myException {
            return null;
        }
        
        @Override
        public E2_Break4PopupController getBreak4PopupController4Cancel() throws myException {
            return new E2_Break4PopupController()._registerBreak(new Break4MaskCloseWhenSomethingWasChanged(
                    this.getTransportHashMap().getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector(), null));
        }
        
       
        @Override
        public void set_bEnabled_For_Edit(boolean enabled) throws myException {
        }
        
       
        @Override
        public Object get_Copy(Object objHelp) throws myExceptionCopy {
            TX_AEND_LIST_bt_ListToMaskInListRow copy= new TX_AEND_LIST_bt_ListToMaskInListRow(this.isUsedToEdit(), this.getTransportHashMap());
            copy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(copy));
            return copy;
        }
       
        /**
         * spezielle methode zum ein permanent enables object doch noch disablen zu koennen
         * @param enabled
         * @throws myException
         */
        public void setEnabledForEditOfSuperClass(boolean enabled) throws myException {
            super.set_bEnabled_For_Edit(enabled);
        }
    }
 
 
 
     private class TX_AEND_LIST_bt_New extends RB_BtV4_New {
      
        public TX_AEND_LIST_bt_New(RB_TransportHashMap  p_tpHashMap) {
            super();
            
            this._setTransportHashMap(p_tpHashMap);
            this.add_GlobalValidator(TX_AEND_VALIDATORS.NEW.getValidator());
            
            this.set_Text4MaskTitle(S.ms("Neuerfassung eines Datensatzes vom Typ: ").ut(TX_AEND_CONST.TX_AEND_NAMES.DATASET_NAME.user_text()));
        }
        
        @Override
        public RB_ModuleContainerMASK generateMaskContainer() throws myException {
            this.getTtransportHashMap()._setRBModulContainerMask(new TX_AEND_MASK_MaskModulContainer(this.getTtransportHashMap()));
            return this.getTtransportHashMap().getRBModulContainerMask();
        }
        
        @Override
        public RB_DataobjectsCollector generateDataObjects4New() throws myException {
            this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,new TX_AEND_MASK_DataObjectCollector(this.getTtransportHashMap()));
            return this.getTtransportHashMap().getMaskDataObjectsCollector();
        }
        
        @Override
        public RB_DataobjectsCollector generateDataObjects4Edit(String id) throws myException {
            MyLong l = new MyLong(id);
            if (l.isNotOK()) {
                throw new myException("Cannot copy null-id");
            }
            this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,new TX_AEND_MASK_DataObjectCollector(this.getTtransportHashMap(), l.get_cUF_LongString(), RB__CONST.MASK_STATUS.EDIT));
            return this.getTtransportHashMap().getMaskDataObjectsCollector();
        }
        
       
        
        @Override
        public E2_Break4PopupController getBreak4PopupController4Save() throws myException {
            return null;
        }
        
        
        @Override
        public E2_Break4PopupController getBreak4PopupController4Cancel() throws myException {
            return new E2_Break4PopupController()._registerBreak(new Break4MaskCloseWhenSomethingWasChanged(this.getTtransportHashMap().getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector(), null));
        }
        
    }
 
    private class TX_AEND_LIST_ComponentMap extends E2_ComponentMAP_V22  {
        
        private RB_TransportHashMap   m_tpHashMap = null;
        
        public TX_AEND_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException  {
      
            super(new TX_AEND_LIST_SqlFieldMAP(p_tpHashMap));
            
            this.m_tpHashMap = p_tpHashMap;        
            
            SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
            
            this.add_Component(TX_AEND_CONST.TX_AEND_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),S.ms("?"));
            this.add_Component(TX_AEND_CONST.TX_AEND_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),S.ms("?"));
            //hier optionale spalten fuer direktes loeschen/edit/view
            this.add_Component(TX_AEND_CONST.TX_AEND_NAMES.DIRECT_DEL.db_val(),    	new TX_AEND_LIST_bt_DeleteInListRow(this.m_tpHashMap)
                                                                                        ._setGridLayout4List(new RB_gld()._ins(4))
                                                                                        ._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
                                                                            S.ms("?"));
            
            this.add_Component(TX_AEND_CONST.TX_AEND_NAMES.DIRECT_EDIT.db_val(),   	new TX_AEND_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
                                                                                        ._setGridLayout4List(new RB_gld()._ins(4))
                                                                                        ._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
                                                                            S.ms("?"));
            this.add_Component(TX_AEND_CONST.TX_AEND_NAMES.DIRECT_VIEW.db_val(),   	new TX_AEND_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
                                                                                        ._setGridLayout4List(new RB_gld()._ins(4))
                                                                                        ._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
                                                                            S.ms("?"));
            this.add_Component(TX_AEND_CONST.TX_AEND_NAMES.DIRECT_UPLOAD.db_val(), new E2_ButtonAttachmentUseInListRow()
                                                                                        ._addGlobalValidator(TX_AEND_VALIDATORS.ATTACHMENT.getValidator())
                                                                                        ._setGridLayout4List(new RB_gld()._ins(4))
                                                                                        ._setLongText4ColumnSelection(S.ms("Anlagen verwalten"))
                                                                                        , S.ms("?"));
            
            //hier kommen die Felder  
            this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TAX_AENDERUNGEN.beschreibung),true),     		S.ms(TX_AEND_READABLE_FIELD_NAME.getReadableForList(TAX_AENDERUNGEN.beschreibung)));
            this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TAX_AENDERUNGEN.steuersatz),true),     		S.ms(TX_AEND_READABLE_FIELD_NAME.getReadableForList(TAX_AENDERUNGEN.steuersatz)));
            this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TAX_AENDERUNGEN.gueltig_von),true),     		S.ms(TX_AEND_READABLE_FIELD_NAME.getReadableForList(TAX_AENDERUNGEN.gueltig_von)));
            this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TAX_AENDERUNGEN.gueltig_bis),true),     		S.ms(TX_AEND_READABLE_FIELD_NAME.getReadableForList(TAX_AENDERUNGEN.gueltig_bis)));
            this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TAX_AENDERUNGEN.id_tax),true),     			S.ms(TX_AEND_READABLE_FIELD_NAME.getReadableForList(TAX_AENDERUNGEN.id_tax)));
            this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TAX_AENDERUNGEN.id_tax_aenderungen),true),     S.ms(TX_AEND_READABLE_FIELD_NAME.getReadableForList(TAX_AENDERUNGEN.id_tax_aenderungen)));
            //neu ab 20171025        
            this._setLineWrapListHeader(true 
                                      ,TAX_AENDERUNGEN.beschreibung.fn()
                                      ,TAX_AENDERUNGEN.gueltig_bis.fn()
                                      ,TAX_AENDERUNGEN.gueltig_von.fn()
                                      ,TAX_AENDERUNGEN.id_tax.fn()
                                      ,TAX_AENDERUNGEN.id_tax_aenderungen.fn()
                                      ,TAX_AENDERUNGEN.steuersatz.fn()
            );
            
            RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
            this._setLayoutElements(gldElementCenter
                                     ,TAX_AENDERUNGEN.beschreibung.fn()
                                     ,TAX_AENDERUNGEN.gueltig_bis.fn()
                                     ,TAX_AENDERUNGEN.gueltig_von.fn()
                                     ,TAX_AENDERUNGEN.id_tax.fn()
                                     ,TAX_AENDERUNGEN.id_tax_aenderungen.fn()
                                     ,TAX_AENDERUNGEN.steuersatz.fn()
            );
            
            RB_gld gldTitelCenter = 	new RB_gld()._center_top()._ins(1,2,1,1)._col(new E2_ColorDark());
            this._setLayoutTitles(gldTitelCenter
                                     ,TAX_AENDERUNGEN.beschreibung.fn()
                                     ,TAX_AENDERUNGEN.gueltig_bis.fn()
                                     ,TAX_AENDERUNGEN.gueltig_von.fn()
                                     ,TAX_AENDERUNGEN.id_tax.fn()
                                     ,TAX_AENDERUNGEN.id_tax_aenderungen.fn()
                                     ,TAX_AENDERUNGEN.steuersatz.fn()
            );
      
      
            //hier kann das layout der einzelnen spalten definiert werden 
            // spaltenlayout fuer:  TAX_AENDERUNGEN.beschreibung.fn()
            this._setColExtent(     new Extent(TX_AEND_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX_AENDERUNGEN.beschreibung)), TAX_AENDERUNGEN.beschreibung.fn());
            this._setLayoutElements(new RB_gld()._al(TX_AEND_READABLE_FIELD_NAME.getAlignment(TAX_AENDERUNGEN.beschreibung))._ins(3,1,3,1)._col(new E2_ColorDark()), TAX_AENDERUNGEN.beschreibung.fn());
            this._setLayoutTitles(  new RB_gld()._al(TX_AEND_READABLE_FIELD_NAME.getAlignment(TAX_AENDERUNGEN.beschreibung))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX_AENDERUNGEN.beschreibung.fn());
            // ----
            //
            // spaltenlayout fuer:  TAX_AENDERUNGEN.gueltig_bis.fn()
            this._setColExtent(     new Extent(TX_AEND_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX_AENDERUNGEN.gueltig_bis)), TAX_AENDERUNGEN.gueltig_bis.fn());
            this._setLayoutElements(new RB_gld()._al(TX_AEND_READABLE_FIELD_NAME.getAlignment(TAX_AENDERUNGEN.gueltig_bis))._ins(3,1,3,1)._col(new E2_ColorDark()), TAX_AENDERUNGEN.gueltig_bis.fn());
            this._setLayoutTitles(  new RB_gld()._al(TX_AEND_READABLE_FIELD_NAME.getAlignment(TAX_AENDERUNGEN.gueltig_bis))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX_AENDERUNGEN.gueltig_bis.fn());
            // ----
            //
            // spaltenlayout fuer:  TAX_AENDERUNGEN.gueltig_von.fn()
            this._setColExtent(     new Extent(TX_AEND_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX_AENDERUNGEN.gueltig_von)), TAX_AENDERUNGEN.gueltig_von.fn());
            this._setLayoutElements(new RB_gld()._al(TX_AEND_READABLE_FIELD_NAME.getAlignment(TAX_AENDERUNGEN.gueltig_von))._ins(3,1,3,1)._col(new E2_ColorDark()), TAX_AENDERUNGEN.gueltig_von.fn());
            this._setLayoutTitles(  new RB_gld()._al(TX_AEND_READABLE_FIELD_NAME.getAlignment(TAX_AENDERUNGEN.gueltig_von))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX_AENDERUNGEN.gueltig_von.fn());
            // ----
            //
            // spaltenlayout fuer:  TAX_AENDERUNGEN.id_tax.fn()
            this._setColExtent(     new Extent(TX_AEND_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX_AENDERUNGEN.id_tax)), TAX_AENDERUNGEN.id_tax.fn());
            this._setLayoutElements(new RB_gld()._al(TX_AEND_READABLE_FIELD_NAME.getAlignment(TAX_AENDERUNGEN.id_tax))._ins(3,1,3,1)._col(new E2_ColorDark()), TAX_AENDERUNGEN.id_tax.fn());
            this._setLayoutTitles(  new RB_gld()._al(TX_AEND_READABLE_FIELD_NAME.getAlignment(TAX_AENDERUNGEN.id_tax))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX_AENDERUNGEN.id_tax.fn());
            // ----
            //
            // spaltenlayout fuer:  TAX_AENDERUNGEN.id_tax_aenderungen.fn()
            this._setColExtent(     new Extent(TX_AEND_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX_AENDERUNGEN.id_tax_aenderungen)), TAX_AENDERUNGEN.id_tax_aenderungen.fn());
            this._setLayoutElements(new RB_gld()._al(TX_AEND_READABLE_FIELD_NAME.getAlignment(TAX_AENDERUNGEN.id_tax_aenderungen))._ins(3,1,3,1)._col(new E2_ColorDark()), TAX_AENDERUNGEN.id_tax_aenderungen.fn());
            this._setLayoutTitles(  new RB_gld()._al(TX_AEND_READABLE_FIELD_NAME.getAlignment(TAX_AENDERUNGEN.id_tax_aenderungen))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX_AENDERUNGEN.id_tax_aenderungen.fn());
            // ----
            //
            // spaltenlayout fuer:  TAX_AENDERUNGEN.steuersatz.fn()
            this._setColExtent(     new Extent(TX_AEND_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX_AENDERUNGEN.steuersatz)), TAX_AENDERUNGEN.steuersatz.fn());
            this._setLayoutElements(new RB_gld()._al(TX_AEND_READABLE_FIELD_NAME.getAlignment(TAX_AENDERUNGEN.steuersatz))._ins(3,1,3,1)._col(new E2_ColorDark()), TAX_AENDERUNGEN.steuersatz.fn());
            this._setLayoutTitles(  new RB_gld()._al(TX_AEND_READABLE_FIELD_NAME.getAlignment(TAX_AENDERUNGEN.steuersatz))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX_AENDERUNGEN.steuersatz.fn());
            // ----
            //
                
        }
        
        
        @Override
        public Object get_Copy(Object objHelp) throws myExceptionCopy {
            E2_ComponentMAP_V22 oRueck = new E2_ComponentMAP_V22(this.get_oSQLFieldMAP());
            E2_ComponentMAP.Copy_FieldsAndSettings(this, oRueck);
            return oRueck;
        }
    }

    
    
    private class TX_AEND_LIST_DATASEARCH extends E2_DataSearch {
        private RB_TransportHashMap   m_tpHashMap = null;
        
        public TX_AEND_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
            super(_TAB.tax_aenderungen.n(),_TAB.scanner_settings.keyFieldName(),E2_MODULNAME_ENUM.MODUL.TAX_AENDERUNGEN_LIST.get_callKey());
            
            this.m_tpHashMap = p_tpHashMap;        
            E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
            this.set_oSearchAgent(oSearchAgent);
            this.addSearchDef(TAX_AENDERUNGEN.id_tax_aenderungen.fn(),TX_AEND_READABLE_FIELD_NAME.getReadableForMask(TAX_AENDERUNGEN.id_tax_aenderungen),	true);
            this.addSearchDef(TAX_AENDERUNGEN.beschreibung.fn(),TX_AEND_READABLE_FIELD_NAME.getReadableForMask(TAX_AENDERUNGEN.beschreibung),	true);
            this.addSearchDef(TAX_AENDERUNGEN.gueltig_bis.fn(),TX_AEND_READABLE_FIELD_NAME.getReadableForMask(TAX_AENDERUNGEN.gueltig_bis),	true);
            this.addSearchDef(TAX_AENDERUNGEN.gueltig_von.fn(),TX_AEND_READABLE_FIELD_NAME.getReadableForMask(TAX_AENDERUNGEN.gueltig_von),	true);
            this.addSearchDef(TAX_AENDERUNGEN.steuersatz.fn(),TX_AEND_READABLE_FIELD_NAME.getReadableForMask(TAX_AENDERUNGEN.steuersatz),	true);
      
            this.initAfterConstruction();
        }
        
        private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
        
            String cSearch = null;
            if (searchWithLike) {
               cSearch = "SELECT id_TAX_AENDERUNGEN  FROM "+bibE2.cTO()+"."+_TAB.tax_aenderungen.n()+" WHERE UPPER(TO_CHAR("+_TAB.tax_aenderungen.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
            } else {
               cSearch = "SELECT id_TAX_AENDERUNGEN  FROM "+bibE2.cTO()+"."+_TAB.tax_aenderungen.n()+" WHERE UPPER(TO_CHAR("+_TAB.tax_aenderungen.n()+"."+cFieldName+"))=UPPER('#WERT#')";
            }           
            
            this.add_SearchElement(cSearch,S.ms(cInfoText));
        }
    }
 

    
     
    
    private class TX_AEND_LIST_SqlFieldMAP extends Project_SQLFieldMAP  {
        private RB_TransportHashMap   m_tpHashMap = null;

        public TX_AEND_LIST_SqlFieldMAP(RB_TransportHashMap  p_tpHashMap) throws myException   {
            super(_TAB.tax_aenderungen.n(), "", false);
            this.m_tpHashMap = p_tpHashMap;        
            this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SQL_FIELDMAP,this);
            this.initFields();
        }
    }
    
    
    
    
    
    
    
    private class TX_AEND_MASK_ComponentMap extends RB_ComponentMap {
     
        private RB_TransportHashMap   m_tpHashMap = null;
        
        public TX_AEND_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
           super();
        
           this.m_tpHashMap = p_tpHashMap;        
             
           this.registerComponent(new RB_KF(TAX_AENDERUNGEN.id_tax),   		 		new RB_lab());
           this.registerComponent(new RB_KF(TAX_AENDERUNGEN.id_tax_aenderungen),    new RB_lab());
           this.registerComponent(new RB_KF(TAX_AENDERUNGEN.gueltig_bis),    		new RB_date_selektor(100,true));
           this.registerComponent(new RB_KF(TAX_AENDERUNGEN.gueltig_von),    		new RB_date_selektor(100,true));
           this.registerComponent(new RB_KF(TAX_AENDERUNGEN.steuersatz),    		new RB_TextField()._width(100));
           this.registerComponent(new RB_KF(TAX_AENDERUNGEN.beschreibung),   		new RB_TextArea()._width(400)._rows(5));
           
           
           this.registerSetterValidators(TAX_AENDERUNGEN.id_tax_aenderungen, (m,b)->{
        	   MyE2_MessageVector mv = bibMSG.getNewMV();
        	   RB_MaskController mc = new RB_MaskController(TX_AEND_MASK_ComponentMap.this);
        	   
        	   Date  von = (Date)mc.getValueFromScreen(_TAB.tax_aenderungen.rb_km(), TAX_AENDERUNGEN.gueltig_von.fk());
        	   Date  bis = (Date)mc.getValueFromScreen(_TAB.tax_aenderungen.rb_km(), TAX_AENDERUNGEN.gueltig_bis.fk());
        	   
        	   if (O.isNoOneNull(von,bis) && bis.before(von)) {
        		   mv._addAlarm(S.ms("Bitte beachten Sie die Datumsreihenfolge !"));
        	   }
        	   
        	   return mv;
           });
           
        }
      
        @Override
        public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {
            return null;
        }
        
        @Override
        public MyE2_MessageVector maskSettings_do_After_Load() throws myException {
            return null;
        }
        
        @Override
        public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
        }
        
        @Override
        public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer,MASK_STATUS status) throws myException {
            //falls sich alles in einer tochtermaske abspielt, dannn muessen hier die verknuepfung zu presettingscontainer hergestellt werden
            if (this.m_tpHashMap.getMotherKeyLookupField()!=null && this.m_tpHashMap.getMotherKeyValue().toString()!=null) {
                preSettingsContainer.rb_set_forcedValueAtSave(this.m_tpHashMap.getMotherKeyLookupField(), this.m_tpHashMap.getMotherKeyValue().toString());
            }
            return null;
         }
        
    }
     
    
    private class TX_AEND_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
        private RB_TransportHashMap   m_tpHashMap = null;
        public TX_AEND_MASK_ComponentMapCollector(RB_TransportHashMap  p_tpHashMap) throws myException {
            super();
            
            this.m_tpHashMap = p_tpHashMap;     
            this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_COMPONENT_MAP_COLLECTOR,this);
            this.registerComponent(TX_AEND_CONST.getLeadingMaskKey(), new TX_AEND_MASK_ComponentMap(this.m_tpHashMap));
        }
    }
 
    
    private class TX_AEND_MASK_DataObject extends RB_Dataobject_V22 {
        private RB_TransportHashMap   m_tpHashMap = null;
     
        public TX_AEND_MASK_DataObject(Rec22 recORD, MASK_STATUS status, RB_TransportHashMap  p_tpHashMap)     throws myException {
            super(recORD, status);
            this.m_tpHashMap = p_tpHashMap;     
        }
     
        public TX_AEND_MASK_DataObject(RB_TransportHashMap  p_params) throws myException {
            super(_TAB.tax_aenderungen);
             this.m_tpHashMap = p_params;     
        }
    }
     
    

    
    private class TX_AEND_MASK_DataObjectCollector extends RB_DataobjectsCollector_V22 {
 	
        private RB_TransportHashMap   m_tpHashMap = null;
        
        public TX_AEND_MASK_DataObjectCollector(RB_TransportHashMap  p_tpHashMap) throws myException {
            super();
            this.m_tpHashMap = p_tpHashMap;     
            this.m_tpHashMap._setMaskDataObjectsCollector(this);    
            this.m_tpHashMap._setMaskStatusOnLoad(MASK_STATUS.NEW);
            
            this.registerComponent(_TAB.tax_aenderungen.rb_km(), new TX_AEND_MASK_DataObject(this.m_tpHashMap));
            
            this._addMessageTranslator(new RB_MessageTranslator(
                            new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
        }
        
       
        public TX_AEND_MASK_DataObjectCollector(RB_TransportHashMap  p_tpHashMap, String id_TAX_AENDERUNGEN, MASK_STATUS status) throws myException {
            super();
            this.m_tpHashMap = p_tpHashMap;     
            this.m_tpHashMap._setMaskDataObjectsCollector(this);    
            this.m_tpHashMap._setMaskStatusOnLoad(status);
            
            this.registerComponent(_TAB.tax_aenderungen.rb_km(), new TX_AEND_MASK_DataObject(new Rec22(_TAB.tax_aenderungen)._fill_id(id_TAX_AENDERUNGEN),status,this.m_tpHashMap));
            
            this._addMessageTranslator(new RB_MessageTranslator(
                            new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
        }
     
        @Override
        public void database_to_dataobject(Object startPoint) throws myException { }
      
        @Override
        public RB_DataobjectsCollector_V22 get_copy() throws myException {return null; }
        
        @Override
        public void manipulate_filled_records_before_save(RB_DataobjectsCollector_V22 do_collector, MyE2_MessageVector mv)	throws myException { }
        
        @Override
        public void execute_final_statements_in_open_transaction(RB_DataobjectsCollector_V22 do_collector,	MyE2_MessageVector mv) throws myException { }
    }
 
 
 
 
 
 
     
     private class TX_AEND_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
     
        //zentrale hashmap fuer transport von infos
        private RB_TransportHashMap   params = null;
        
        private TX_AEND_MASK_MaskGrid maskGrid = null;
        
        public TX_AEND_MASK_MaskModulContainer(RB_TransportHashMap  p_tpHashMap) throws myException {
            super();
            
            this.params = p_tpHashMap;
            
            //anfangsausmasse des fensterpopups
            this._setWidth(TX_AEND_CONST.TX_AEND_NUM_CONST.MASKPOPUP_WIDTH.getValue())._setHeight(TX_AEND_CONST.TX_AEND_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
            
            this.params.put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK,this);
            
            TX_AEND_MASK_ComponentMapCollector compMapCollector = new TX_AEND_MASK_ComponentMapCollector(this.params) ; 
            this.registerComponent(_TAB.tax_aenderungen.rb_km(), compMapCollector );
            
            this.rb_INIT(E2_MODULNAME_ENUM.MODUL.TAX_AENDERUNGEN_MASK, this.maskGrid=new TX_AEND_MASK_MaskGrid(this.params), true);
            
            this.set_oResizeHelper(new ownResizer());
        }
        
        
        private class ownResizer extends XX_BasicContainerResizeHelper {
            @Override
            public void do_actionAfterResizeWindow(E2_BasicModuleContainer ownContainer) throws myException {
                maskGrid.resize(ownContainer.get_oExtWidth().getValue(), ownContainer.get_oExtHeight().getValue());
            }
            
        }
    }
 
 
 
     public class TX_AEND_MASK_MaskGrid extends E2_Grid {
        
        private RB_TransportHashMap   	m_tpHashMap = null;
        private MyE2_TabbedPane  		ta  = new MyE2_TabbedPane(600);
        private VEK<E2_Grid>   			fieldContainers = 	new VEK<E2_Grid>();
        private VEK<MyString>  			tabText = 			new VEK<MyString>();

        
        public TX_AEND_MASK_MaskGrid(RB_TransportHashMap  p_tpHashMap) throws myException {
            super();
            
            int iWidthComplete = TX_AEND_CONST.TX_AEND_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue()+
                                      TX_AEND_CONST.TX_AEND_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue()+20;
            this._setSize(iWidthComplete)._bo_no();
     
            
            this._setSize(800)._bo_no();
            
            this.m_tpHashMap = p_tpHashMap;
            this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_GRID,this);
            
            TX_AEND_MASK_ComponentMap  map1 = (TX_AEND_MASK_ComponentMap) this.m_tpHashMap.getMaskComponentMapCollector().get(this.m_tpHashMap.getLeadingMaskKey());
            
            //beginn erster tab
            E2_Grid g1 = fieldContainers._ar(new E2_Grid()._setSize( TX_AEND_CONST.TX_AEND_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue(),
                                                                        TX_AEND_CONST.TX_AEND_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue())._bo_no());        
            
            int iZahl = 1;
            
            tabText._a(S.ms("Tab "+(iZahl++)));
            
            g1._a(new RB_lab(TX_AEND_READABLE_FIELD_NAME.getReadableForMask(TAX_AENDERUNGEN.id_tax)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(TAX_AENDERUNGEN.id_tax), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
            g1._a(new RB_lab(TX_AEND_READABLE_FIELD_NAME.getReadableForMask(TAX_AENDERUNGEN.id_tax_aenderungen)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(TAX_AENDERUNGEN.id_tax_aenderungen), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
            g1._a(new RB_lab(TX_AEND_READABLE_FIELD_NAME.getReadableForMask(TAX_AENDERUNGEN.gueltig_von)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(TAX_AENDERUNGEN.gueltig_von), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
            g1._a(new RB_lab(TX_AEND_READABLE_FIELD_NAME.getReadableForMask(TAX_AENDERUNGEN.gueltig_bis)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(TAX_AENDERUNGEN.gueltig_bis), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
            g1._a(new RB_lab(TX_AEND_READABLE_FIELD_NAME.getReadableForMask(TAX_AENDERUNGEN.steuersatz)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(TAX_AENDERUNGEN.steuersatz), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
            g1._a(new RB_lab(TX_AEND_READABLE_FIELD_NAME.getReadableForMask(TAX_AENDERUNGEN.beschreibung)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(TAX_AENDERUNGEN.beschreibung), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));

            //hier alles rendern (entweder nur ein grid oder ein tab mit grids ...
            this.renderMask();
            
            this.resize(TX_AEND_CONST.TX_AEND_NUM_CONST.MASKPOPUP_WIDTH.getValue(),
                          TX_AEND_CONST.TX_AEND_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
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
     
 
 
 
    /*
     * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
     */
    private enum TX_AEND_READABLE_FIELD_NAME {
        
        BESCHREIBUNG(panter.gmbh.basics4project.DB_ENUMS.TAX_AENDERUNGEN.beschreibung,"Beschreibung",400,400,"Beschreibung",new Alignment(Alignment.LEFT, Alignment.TOP)),
        GUELTIG_BIS(panter.gmbh.basics4project.DB_ENUMS.TAX_AENDERUNGEN.gueltig_bis,"Gültig bis",100,100,"Gültig bis",new Alignment(Alignment.CENTER, Alignment.TOP)),
        GUELTIG_VON(panter.gmbh.basics4project.DB_ENUMS.TAX_AENDERUNGEN.gueltig_von,"Gültig von",100,100,"Gültig von",new Alignment(Alignment.CENTER, Alignment.TOP)),
        ID_TAX(panter.gmbh.basics4project.DB_ENUMS.TAX_AENDERUNGEN.id_tax,"ID(Steuer)",100,100,"ID(Steuer)",new Alignment(Alignment.RIGHT, Alignment.TOP)),
        ID_TAX_AENDERUNGEN(panter.gmbh.basics4project.DB_ENUMS.TAX_AENDERUNGEN.id_tax_aenderungen,"ID",100,100,"ID",new Alignment(Alignment.RIGHT, Alignment.TOP)),
        STEUERSATZ(panter.gmbh.basics4project.DB_ENUMS.TAX_AENDERUNGEN.steuersatz,"Steuersatz",100,100,"Steuersatz",new Alignment(Alignment.RIGHT, Alignment.TOP)),
        ;
        
        private IF_Field m_field = null;
        private String   m_readAbleForList = null;
        private String   m_readAbleForMask = null;
        private int      m_widthLabelList = 10;                  //breite fuer die labels
        private int      m_widthComponentList = 10;              //breite fuer die komponente
        private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
        
        private TX_AEND_READABLE_FIELD_NAME(IF_Field f, String readAble) {
            this.m_field=f;
            this.m_readAbleForList = readAble;
        }
        
        private TX_AEND_READABLE_FIELD_NAME(IF_Field f, String readAbleForList, int m_widthLabelList, int widthComponentList, String readAbleForMask, Alignment align) {
            this.m_field=f;
            this.m_readAbleForList = readAbleForList;
            this.m_readAbleForMask = readAbleForMask;
            this.m_widthLabelList = m_widthLabelList;
            this.m_widthComponentList = widthComponentList;
            this.m_align = align;
        }
        
        
        
        public static String getReadableForList(IF_Field f) {
            String ret = "";
            
            for (TX_AEND_READABLE_FIELD_NAME  rf: TX_AEND_READABLE_FIELD_NAME.values() ) {
                if (rf.m_field == f) {
                    return rf.m_readAbleForList;
                }
            }
            return ret;
        }
        
        
        public static String getReadableForMask(IF_Field f) {
            String ret = "";
            
            for (TX_AEND_READABLE_FIELD_NAME  rf: TX_AEND_READABLE_FIELD_NAME.values() ) {
                if (rf.m_field == f) {
                    return rf.m_readAbleForMask;
                }
            }
            return ret;
        }
        
        
        public static Alignment getAlignment(IF_Field f) {
            Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
            
            for (TX_AEND_READABLE_FIELD_NAME  rf: TX_AEND_READABLE_FIELD_NAME.values() ) {
                if (rf.m_field == f) {
                    return rf.m_align;
                }
            }
            return ret;
        }
        
        
        //sucht die groessere breite zwischen label und componenten-breite aus	
        public static int getMaxComponentOrLabelSize(IF_Field f) {
            int ret = 10;
                
            for (TX_AEND_READABLE_FIELD_NAME  rf: TX_AEND_READABLE_FIELD_NAME.values() ) {
                if (rf.m_field == f) {
                    if (rf.m_widthLabelList>rf.m_widthComponentList) {
                       return rf.m_widthLabelList;
                    } else {
                       return rf.m_widthComponentList;
                    }
                }
            }
            return ret;
        }
        
    }
 
 
 
     private enum TX_AEND_VALIDATORS {
        EDIT(ENUM_VALIDATION.TAX_EDIT)
        ,VIEW(ENUM_VALIDATION.TAX_VIEW)
        ,NEW(ENUM_VALIDATION.TAX_NEW)
        ,DELETE(ENUM_VALIDATION.TAX_DELETE)
        ,ATTACHMENT(ENUM_VALIDATION.TAX_ATTACHMENT)
        ;
        
        private ENUM_VALIDATION m_validation = null;
     
        private TX_AEND_VALIDATORS(ENUM_VALIDATION validation) {
            this.m_validation = validation; 
        }
     
        public E2_ButtonAUTHValidator getValidator() {
            return this.m_validation.getValidator();
        }
    }



}
 
 
 
