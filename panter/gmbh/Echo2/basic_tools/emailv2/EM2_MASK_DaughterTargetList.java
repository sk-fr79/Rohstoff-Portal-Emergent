 
package panter.gmbh.Echo2.basic_tools.emailv2;
 
import java.util.Collection;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V22;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationListCompact;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.IF_RbComponentWithOwnKey;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_MessageTranslator;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
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
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_SelField_USER;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.DB.E2_DBLabelUserInList;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_TARGETS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.mail.MailException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22EmailSend;
 
 
/**
 * @author martin
 * Komponente fuer eine maske eine mothertable, die diese als fullDaughter einblendet
 */
public class EM2_MASK_DaughterTargetList extends RB_MaskDaughterBasedOnBasicModulContainer  implements IF_RbComponentWithOwnKey {
 	
 	//fieldKey fuer das einbinden dieser komponente in einer mother-mask
	public static RB_KF  keyForMotherMask = (RB_KF)new RB_KF()
											._setHASHKEY("EMT2_EMAIL_SEND_TARGETS_MASK_DaughterListForMotherMask<8694599e-6c66-11eb-9439-0242ac130002>")
											._setREALNAME("EMT2_EMAIL_SEND_TARGETS_MASK_DaughterListForMotherMask<8694599e-6c66-11eb-9439-0242ac130002>");
	
 	
 	
    private RB_TransportHashMap   m_tpHashMap = null;
    private EM2_TransportHashMap  em2TransportHashMap = null;
    
    private VEK<MyE2IF__Component>  toDisableAtMask = new VEK<MyE2IF__Component>();
    
    private boolean    				m_enabled = true;
    
    
    private boolean 				allowEdit = false;
    private boolean 				allowCopy = false;
    private boolean 				allowDelete = false;
    private boolean 				allowNew = false;
    private boolean 				allowEMailSendButton = false;
    private boolean 				allowEMailAddTarget = false;
    /**
     * 
     * @param p_tpHashMap
     * @param useCompactList
     * @throws myException
     */
    public EM2_MASK_DaughterTargetList(EM2_TransportHashMap p_tpHashMap) throws myException {
        super();
        em2TransportHashMap = p_tpHashMap;	
        
        allowEdit = p_tpHashMap.getAllowEdit();
        allowCopy = p_tpHashMap.getAllowCopy();
        allowDelete = p_tpHashMap.getAllowDelete();
        allowNew = p_tpHashMap.getAllowNew();
        allowEMailSendButton = p_tpHashMap.getAllowSendButton();
        allowEMailAddTarget  = p_tpHashMap.getAllowAddTarget();
        
        this._init(new InternalLIST_BasicModuleContainer(p_tpHashMap));
 	}
  
  
  
  	@Override
	public RB_KF getFieldKey() {
		return EM2_MASK_DaughterTargetList.keyForMotherMask;
	}
 
    
    @Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
    	this.m_enabled = enabled; 
    	
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
		return EMAIL_SEND_TARGETS.id_email_send;
	}
	
	/**
	 * innerer basicmodulContainer fuer die einlagerung in eine MotherMask
	 * @author martin
	 *
	 */
	public class InternalLIST_BasicModuleContainer extends Project_BasicModuleContainer    {
		
	    public InternalLIST_BasicModuleContainer(EM2_TransportHashMap tpHashMap) throws myException    {
	    	
	        super(E2_MODULNAME_ENUM.MODUL.EMAIL_SEND_TARGETS_V2_LIST.get_callKey());
	        
	        EM2_MASK_DaughterTargetList oThis = EM2_MASK_DaughterTargetList.this;
	        
	        oThis.m_tpHashMap = new RB_TransportHashMap();  
	        //die transporthashmap muss jetzt in die ableitende klasse
	        oThis._setTransportHashMap(oThis.m_tpHashMap);
	        
	        //dann den ganzen paramhash der mutter in den tochterhash einfuegen
	        oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.MOTHERPARAMSHASH, tpHashMap);
 
	        oThis.m_tpHashMap._setModulContainerList(this);
	        oThis.m_tpHashMap._setLeadingMaskKey(EMT2_CONST.getLeadingMaskKey());
	        oThis.m_tpHashMap._setLeadingTableOnMask(EMT2_CONST.getLeadingTable());
 
	        this.set_bVisible_Row_For_Messages(false);
 	        
 	        
	        EMT2_LIST_ComponentMap listComponentMap  = null;
 	        
	        oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.NAVILIST,new E2_NavigationListCompact());
 
                 
	        EMT2_LIST_bt_New btNew = new EMT2_LIST_bt_New(oThis.m_tpHashMap);
	        EMT2_LIST_bt_New btNewSpecialForTargets = new EMT2_LIST_bt_New(oThis.m_tpHashMap);

	        EMT2_LIST_bt_Copy btCopy = new EMT2_LIST_bt_Copy(oThis.m_tpHashMap);
                
	        oThis.toDisableAtMask._a(btNew)._a(btCopy);
	        oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oThis.m_tpHashMap.getNaviListCompact())._setLDC(new RB_gld()._ins(2, 0, 10, 0)._left_mid()));
	        if (allowNew) {
	        	oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btNew._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
	        }
	        if (allowEMailAddTarget && !(allowNew)) {
	        	//btNewSpecialForTargets ist auch aktiv, wenn die maske von emailSend in view-modus aufgerufen wird
	        	oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btNewSpecialForTargets._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
	        }

	        if (allowCopy) {
	        	oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btCopy._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
	        }
                
	        
	        
	        oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST,this);
 
	        listComponentMap = new EMT2_LIST_ComponentMap(oThis.m_tpHashMap);
	        oThis.m_tpHashMap.getNaviListCompact().INIT_WITH_ComponentMAP(listComponentMap,  E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
	        this.add(oThis.m_tpHashMap.getNaviListCompact(), E2_INSETS.I_2_2_2_2);
	        oThis.m_tpHashMap.getNaviListCompact()._REBUILD_COMPLETE_LIST("");
            oThis.m_tpHashMap.getNaviListCompact()._rebuildTitleLine();
                
	        
	        listComponentMap.add_oSubQueryAgent(new mapSettingAgent());
	        
	    }
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
					if (c instanceof EMT2_LIST_bt_DeleteInListRow) {
						((EMT2_LIST_bt_DeleteInListRow)c).setEnabledForEditOfSuperClass(false);
					}
					if (c instanceof EMT2_LIST_bt_ListToMaskInListRow) {
						if ( ((EMT2_LIST_bt_ListToMaskInListRow)c).isUsedToEdit()) {
							((EMT2_LIST_bt_ListToMaskInListRow)c).setEnabledForEditOfSuperClass(false);
						}
					}
					
				}
			}
		}
    	
    }
    
    
    
    public static class EMT2_CONST {
        public enum EMT2_NAMES implements IF_enum_4_db {
            
            CHECKBOX_LISTE( S.ms("Auswahl-Checkbox"))
            ,MARKER_LISTE( S.ms("Markierung Liste"))
            ,DIRECT_DEL( S.ms("Loeschbutton in Listenzeile"))
            ,DIRECT_EDIT( S.ms("Editbutton in Listenzeile"))
            ,DIRECT_VIEW( S.ms("Anzeigebutton in Listenzeile"))
            ,DIRECT_UPLOAD( S.ms("Dateien hochladen"))
            ,DATASET_NAME(S.ms("eMail-Zieladresse"))
            ;
            
            private MyE2_String userText = null; 
            private String      m_dbVal = null;
            
            private EMT2_NAMES(MyE2_String p_userText) {
                this.userText = p_userText;
            }
            
            //konstuktor mit abweichenden werten
            private EMT2_NAMES(MyE2_String p_userText, String dbVal) {
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
            return _TAB.email_send_targets;
        }
        
        
        /**
         * 
         * @return der ueberall verwendete mask-key
         * @throws myException
         */
        public static RB_KM getLeadingMaskKey() throws myException {
            return _TAB.email_send_targets.rb_km();
        }
        
        
        
        
        
        public enum EMT2_BUTTONS implements IF_enum_4_db  {
            DELETE("LOESCHE_EMAIL_SEND_TARGETS")
            ,EDIT("BEARBEITE_EMAIL_SEND_TARGETS")
            ,VIEW("ANZEIGE_EMAIL_SEND_TARGETS")
            ,NEW("NEUEINGABE_EMAIL_SEND_TARGETS")
            ,
            
            ;
            
            private String KEY = null;
            
            private EMT2_BUTTONS() {
                this.KEY=this.name();
            }
            private EMT2_BUTTONS(String key) {
                this.KEY=key;
            }
            public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
            @Override
            public String user_text_lang() {return this.name();    }
            @Override
            public String user_text() {        return this.name();        }
            @Override
            public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
                return bibENUM.dd_array(EMT2_BUTTONS.values(), emptyPairInFront);
            }
            
        }
        
        
        
        
        /*
         * enum: hier koennen numerische (long) werte zentral gesteuert werden 
         */
        public static enum EMT2_NUM_CONST {
             MASKPOPUP_WIDTH(new Integer(600))
            ,MASKPOPUP_HEIGHT(new Integer(300))
            ,MASKPOPUP_DESCRIPTION_COL_SIZE(new Integer(160))
            ,MASKPOPUP_FIELD_COL_SIZE(new Integer(430))
             
            ;
            
            private Integer   m_value = null;
            
            private EMT2_NUM_CONST(Integer p_value) {
                this.m_value=p_value;
            }
            
            public Integer getValue() {
                return this.m_value;
            }
        }
    }
    
    
    
    
    
    private class EMT2_LIST_bt_Copy extends RB_BtV4_NewCopy {
	
        public EMT2_LIST_bt_Copy(RB_TransportHashMap  p_tpHashMap) {
            super();
            this._setTransportHashMap(p_tpHashMap);
            
            this.set_text4MaskTitel(S.ms("Kopie eines Datensatzes vom Typ: ").ut(EMT2_CONST.EMT2_NAMES.DATASET_NAME.user_text()));
        }
     
        
        @Override
        public RB_ModuleContainerMASK generateMaskContainer() throws myException {
            this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK,new EMT2_MASK_MaskModulContainer(this.getTtransportHashMap()));
            return this.getTtransportHashMap().getRBModulContainerMask();
        }
        
        
        @Override
        public RB_DataobjectsCollector generateDataObjects4MaskCopy(Long idToCopy) throws myException {
     
            if (idToCopy==null) {
                throw new myException("Cannot copy null-id");
            }
            this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,new EMT2_MASK_DataObjectCollector(this.getTtransportHashMap(), idToCopy.toString(), RB__CONST.MASK_STATUS.NEW_COPY));
            return this.getTtransportHashMap().getMaskDataObjectsCollector();
        }
        
        
        
        @Override
        public RB_DataobjectsCollector generateDataObjects4Copy(String idToCopy) throws myException {
     
            MyLong l = new MyLong(idToCopy);
            
            if (l.isNotOK()) {
                throw new myException("Cannot copy null-id");
            }
            this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,
                        new EMT2_MASK_DataObjectCollector(this.getTtransportHashMap(), idToCopy.toString(), RB__CONST.MASK_STATUS.EDIT));
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
                           EMT2_LIST_bt_Copy.this.getTtransportHashMap().getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector(), null));
       }
     
        
    }
     
    
    
    
    
    private class EMT2_LIST_bt_DeleteInListRow extends RB_BtV4_Delete  {
    
	
        public EMT2_LIST_bt_DeleteInListRow(RB_TransportHashMap p_tpHashMap) {
            super();
            
            this._setShapeDeleteButton();
            this._setTransportHashMap(p_tpHashMap);
            
            this.setToolTipText(new MyE2_String("Listenfeld-Definition in dieser Zeile loeschen").CTrans());
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
                Rec22 rec = new Rec22(_TAB.email_send_targets)._fill_id(lid.get_oLong());
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
            EMT2_LIST_bt_DeleteInListRow copy= new EMT2_LIST_bt_DeleteInListRow(this.getTransportHashMap());
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
 
    

    
    
    
    
    
    
    private class EMT2_LIST_bt_ListToMaskInListRow extends RB_BtV4_List2Mask {
    
        public EMT2_LIST_bt_ListToMaskInListRow(boolean bEdit, RB_TransportHashMap  p_tpHashMap) {
            super(bEdit);
            this._setTransportHashMap(p_tpHashMap);
        }
        @Override
        public RB_ModuleContainerMASK generateMaskContainer() throws myException {
            this.getTransportHashMap()._setRBModulContainerMask(
                    new EMT2_MASK_MaskModulContainer(this.getTransportHashMap()));
            return this.getTransportHashMap().getRBModulContainerMask();
        }
      
        
        @Override
        public RB_hm_multi_DataobjectsCollector generateDataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {
            
            EMT2_LIST_bt_ListToMaskInListRow oThis = EMT2_LIST_bt_ListToMaskInListRow.this;
            
            
            if (this.EXT().get_oComponentMAP()!=null) {
                MyLong  id = new MyLong(this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
                
                if (id.isOK()) {
                    RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
                    RB__CONST.MASK_STATUS aktuellerStatus = this.isUsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;
                    collector.put(id.get_cUF_LongString(), new EMT2_MASK_DataObjectCollector(oThis.getTransportHashMap(), id.get_cUF_LongString(),aktuellerStatus));
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
            MyE2_String bearbeiten = S.ms("Bearbeiten eines Datensatzes vom Typ: ").ut(EMT2_CONST.EMT2_NAMES.DATASET_NAME.getUserText().CTrans());
            MyE2_String anzeigen  = S.ms("Anzeige eines Datensatzes vom Typ: ").ut(EMT2_CONST.EMT2_NAMES.DATASET_NAME.getUserText().CTrans());
            return  this.isUsedToEdit()?bearbeiten:anzeigen;
            
        }
        
        @Override
        public MyE2_String generateMessagetextForSaveRecord() throws myException {
             return S.ms("Datensatzes vom Typ: ").ut(EMT2_CONST.EMT2_NAMES.DATASET_NAME.getUserText().CTrans()).t(" wurde gespeichert");
        }
        
        
        @Override
        public Vector<XX_ActionAgentWhenCloseWindow> generateWindowCloseActions() throws myException {
            Vector<XX_ActionAgentWhenCloseWindow>  v_rueck = new Vector<>();
            EMT2_LIST_bt_ListToMaskInListRow oThis = EMT2_LIST_bt_ListToMaskInListRow.this;
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
            EMT2_LIST_bt_ListToMaskInListRow copy= new EMT2_LIST_bt_ListToMaskInListRow(this.isUsedToEdit(), this.getTransportHashMap());
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
 
 
 
 
 
 
  
 
     private class EMT2_LIST_bt_New extends RB_BtV4_New {
      
        public EMT2_LIST_bt_New(RB_TransportHashMap  p_tpHashMap) {
            super();
            
            this._setTransportHashMap(p_tpHashMap);
            this.set_Text4MaskTitle(S.ms("Neuerfassung eines Datensatzes vom Typ: ").ut(EMT2_CONST.EMT2_NAMES.DATASET_NAME.user_text()));
        }
      
        
        @Override
        public RB_ModuleContainerMASK generateMaskContainer() throws myException {
            this.getTtransportHashMap()._setRBModulContainerMask(new EMT2_MASK_MaskModulContainer(this.getTtransportHashMap()));
            return this.getTtransportHashMap().getRBModulContainerMask();
        }
        
        
        @Override
        public RB_DataobjectsCollector generateDataObjects4New() throws myException {
      
            this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,new EMT2_MASK_DataObjectCollector(this.getTtransportHashMap()));
            return this.getTtransportHashMap().getMaskDataObjectsCollector();
        }
        
        
        
        @Override
        public RB_DataobjectsCollector generateDataObjects4Edit(String id) throws myException {
      
            MyLong l = new MyLong(id);
            
            if (l.isNotOK()) {
                throw new myException("Cannot copy null-id");
            }
            this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,new EMT2_MASK_DataObjectCollector(this.getTtransportHashMap(), l.get_cUF_LongString(), RB__CONST.MASK_STATUS.EDIT));
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
 
    private class EMT2_LIST_ComponentMap extends E2_ComponentMAP_V22  {
        
        private RB_TransportHashMap   m_tpHashMap = null;
        
        private EM2_TransportHashMap  em2TransportHashMap = null;
        
        public EMT2_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException  {
      
            super(new EMT2_LIST_SqlFieldMAP(p_tpHashMap));
            
            this.m_tpHashMap = p_tpHashMap;

            em2TransportHashMap = (EM2_TransportHashMap)m_tpHashMap.getMotherTransportHashMap();
            
            SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
            
            this.add_Component(EMT2_CONST.EMT2_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
            this.add_Component(EMT2_CONST.EMT2_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
            //hier optionale spalten fuer direktes loeschen/edit/view
            
            if (allowDelete) {
	            this.add_Component(EMT2_CONST.EMT2_NAMES.DIRECT_DEL.db_val(),    	new EMT2_LIST_bt_DeleteInListRow(this.m_tpHashMap)
	                                                                                        ._setGridLayout4List(new RB_gld()._ins(4))
	                                                                                        ._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
	                                                                                        new MyE2_String("?"));
            }
            
            if (allowEdit)  {
	            this.add_Component(EMT2_CONST.EMT2_NAMES.DIRECT_EDIT.db_val(),   	new EMT2_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
	                                                                                        ._setGridLayout4List(new RB_gld()._ins(4))
	                                                                                        ._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
	                                                                                        new MyE2_String("?"));
            }
            this.add_Component(EMT2_CONST.EMT2_NAMES.DIRECT_VIEW.db_val(),   	new EMT2_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
                                                                                        ._setGridLayout4List(new RB_gld()._ins(4))
                                                                                        ._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
                                                                                        new MyE2_String("?"));
            
            if (allowEMailSendButton) {
            	this.add_Component(new SendComponent());
            }
            
            
            //hier kommen die Felder  
            this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(EMAIL_SEND_TARGETS.target_adress),true),     new MyE2_String(EMT2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND_TARGETS.target_adress)));
            this.add_Component(new E2_DBLabelUserInList(oFM.get_(EMAIL_SEND_TARGETS.id_user_send)),     new MyE2_String(EMT2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND_TARGETS.id_user_send)));
            this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(EMAIL_SEND_TARGETS.sending_time),true),     new MyE2_String(EMT2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND_TARGETS.sending_time)));
            this.add_Component(new MyE2_DB_CheckBox(oFM.get_(EMAIL_SEND_TARGETS.send_ok),true),     new MyE2_String(EMT2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND_TARGETS.send_ok)));
            this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(EMAIL_SEND_TARGETS.pos),true)._setVisibleInList(false),     new MyE2_String(EMT2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND_TARGETS.pos)));
            this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(EMAIL_SEND_TARGETS.id_email_send),true)._setVisibleInList(false),     new MyE2_String(EMT2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND_TARGETS.id_email_send)));
            this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(EMAIL_SEND_TARGETS.id_email_send_targets),true),     new MyE2_String(EMT2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND_TARGETS.id_email_send_targets)));
            //neu ab 20171025        
            this._setLineWrapListHeader(true 
                                      ,EMAIL_SEND_TARGETS.id_email_send.fn()
                                      ,EMAIL_SEND_TARGETS.id_email_send_targets.fn()
                                      ,EMAIL_SEND_TARGETS.id_user_send.fn()
                                      ,EMAIL_SEND_TARGETS.pos.fn()
                                      ,EMAIL_SEND_TARGETS.sending_time.fn()
                                      ,EMAIL_SEND_TARGETS.send_ok.fn()
                                      ,EMAIL_SEND_TARGETS.target_adress.fn()
            );
            
            RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
            this._setLayoutElements(gldElementCenter
                                     ,EMAIL_SEND_TARGETS.id_email_send.fn()
                                     ,EMAIL_SEND_TARGETS.id_email_send_targets.fn()
                                     ,EMAIL_SEND_TARGETS.id_user_send.fn()
                                     ,EMAIL_SEND_TARGETS.pos.fn()
                                     ,EMAIL_SEND_TARGETS.sending_time.fn()
                                     ,EMAIL_SEND_TARGETS.send_ok.fn()
                                     ,EMAIL_SEND_TARGETS.target_adress.fn()
            );
            
            RB_gld gldTitelCenter = 	new RB_gld()._center_top()._ins(1,2,1,1)._col(new E2_ColorDark());
            this._setLayoutTitles(gldTitelCenter
                                     ,EMAIL_SEND_TARGETS.id_email_send.fn()
                                     ,EMAIL_SEND_TARGETS.id_email_send_targets.fn()
                                     ,EMAIL_SEND_TARGETS.id_user_send.fn()
                                     ,EMAIL_SEND_TARGETS.pos.fn()
                                     ,EMAIL_SEND_TARGETS.sending_time.fn()
                                     ,EMAIL_SEND_TARGETS.send_ok.fn()
                                     ,EMAIL_SEND_TARGETS.target_adress.fn()
            );
      
      
            //hier kann das layout der einzelnen spalten definiert werden 
            // spaltenlayout fuer:  EMAIL_SEND_TARGETS.id_email_send.fn()
            this._setColExtent(     new Extent(EMT2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(EMAIL_SEND_TARGETS.id_email_send)), EMAIL_SEND_TARGETS.id_email_send.fn());
            this._setLayoutElements(new RB_gld()._al(EMT2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND_TARGETS.id_email_send))._ins(3,1,3,1)._col(new E2_ColorDark()), EMAIL_SEND_TARGETS.id_email_send.fn());
            this._setLayoutTitles(  new RB_gld()._al(EMT2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND_TARGETS.id_email_send))._ins(1,2,1,1)._col(new E2_ColorDark()), EMAIL_SEND_TARGETS.id_email_send.fn());
            // ----
            //
            // spaltenlayout fuer:  EMAIL_SEND_TARGETS.id_email_send_targets.fn()
            this._setColExtent(     new Extent(EMT2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(EMAIL_SEND_TARGETS.id_email_send_targets)), EMAIL_SEND_TARGETS.id_email_send_targets.fn());
            this._setLayoutElements(new RB_gld()._al(EMT2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND_TARGETS.id_email_send_targets))._ins(3,1,3,1)._col(new E2_ColorDark()), EMAIL_SEND_TARGETS.id_email_send_targets.fn());
            this._setLayoutTitles(  new RB_gld()._al(EMT2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND_TARGETS.id_email_send_targets))._ins(1,2,1,1)._col(new E2_ColorDark()), EMAIL_SEND_TARGETS.id_email_send_targets.fn());
            // ----
            //
            // spaltenlayout fuer:  EMAIL_SEND_TARGETS.id_user_send.fn()
            this._setColExtent(     new Extent(EMT2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(EMAIL_SEND_TARGETS.id_user_send)), EMAIL_SEND_TARGETS.id_user_send.fn());
            this._setLayoutElements(new RB_gld()._al(EMT2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND_TARGETS.id_user_send))._ins(3,1,3,1)._col(new E2_ColorDark()), EMAIL_SEND_TARGETS.id_user_send.fn());
            this._setLayoutTitles(  new RB_gld()._al(EMT2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND_TARGETS.id_user_send))._ins(1,2,1,1)._col(new E2_ColorDark()), EMAIL_SEND_TARGETS.id_user_send.fn());
            // ----
            //
            // spaltenlayout fuer:  EMAIL_SEND_TARGETS.pos.fn()
            this._setColExtent(     new Extent(EMT2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(EMAIL_SEND_TARGETS.pos)), EMAIL_SEND_TARGETS.pos.fn());
            this._setLayoutElements(new RB_gld()._al(EMT2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND_TARGETS.pos))._ins(3,1,3,1)._col(new E2_ColorDark()), EMAIL_SEND_TARGETS.pos.fn());
            this._setLayoutTitles(  new RB_gld()._al(EMT2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND_TARGETS.pos))._ins(1,2,1,1)._col(new E2_ColorDark()), EMAIL_SEND_TARGETS.pos.fn());
            // ----
            //
            // spaltenlayout fuer:  EMAIL_SEND_TARGETS.sending_time.fn()
            this._setColExtent(     new Extent(EMT2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(EMAIL_SEND_TARGETS.sending_time)), EMAIL_SEND_TARGETS.sending_time.fn());
            this._setLayoutElements(new RB_gld()._al(EMT2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND_TARGETS.sending_time))._ins(3,1,3,1)._col(new E2_ColorDark()), EMAIL_SEND_TARGETS.sending_time.fn());
            this._setLayoutTitles(  new RB_gld()._al(EMT2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND_TARGETS.sending_time))._ins(1,2,1,1)._col(new E2_ColorDark()), EMAIL_SEND_TARGETS.sending_time.fn());
            // ----
            //
            // spaltenlayout fuer:  EMAIL_SEND_TARGETS.send_ok.fn()
            this._setColExtent(     new Extent(EMT2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(EMAIL_SEND_TARGETS.send_ok)), EMAIL_SEND_TARGETS.send_ok.fn());
            this._setLayoutElements(new RB_gld()._al(EMT2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND_TARGETS.send_ok))._ins(3,1,3,1)._col(new E2_ColorDark()), EMAIL_SEND_TARGETS.send_ok.fn());
            this._setLayoutTitles(  new RB_gld()._al(EMT2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND_TARGETS.send_ok))._ins(1,2,1,1)._col(new E2_ColorDark()), EMAIL_SEND_TARGETS.send_ok.fn());
            // ----
            //
            // spaltenlayout fuer:  EMAIL_SEND_TARGETS.target_adress.fn()
            this._setColExtent(     new Extent(EMT2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(EMAIL_SEND_TARGETS.target_adress)), EMAIL_SEND_TARGETS.target_adress.fn());
            this._setLayoutElements(new RB_gld()._al(EMT2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND_TARGETS.target_adress))._ins(3,1,3,1)._col(new E2_ColorDark()), EMAIL_SEND_TARGETS.target_adress.fn());
            this._setLayoutTitles(  new RB_gld()._al(EMT2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND_TARGETS.target_adress))._ins(1,2,1,1)._col(new E2_ColorDark()), EMAIL_SEND_TARGETS.target_adress.fn());
            // ----
            //
            this.set_oSubQueryAgent(new EMT2_LIST_FORMATING_Agent(this.m_tpHashMap));
                
        }
        
        
        
        @Override
        public Object get_Copy(Object objHelp) throws myExceptionCopy {
            try {
				return new EMT2_LIST_ComponentMap(m_tpHashMap);
			} catch (myException e) {
				e.printStackTrace();
				throw new myExceptionCopy(e.ErrorMessage);
			}
        }
        
        /*
         * umstellung auf E2_ComponentMAP_V22 mit marker-option
         * korrespontiert mit  
         */
        public class EMT2_LIST_ComponentMapMapMarker extends E2_ComponentMapMarker {
            public EMT2_LIST_ComponentMapMapMarker(E2_ComponentMAP_V22 p_map) {
                super(p_map);
            }
            @Override
            protected void innerFormat(Collection<Component> v) {
                super.innerFormat(v);
                
                E2_ComponentMAP_V22 map = (E2_ComponentMAP_V22)this.getMap();
                
                try {
                    Rec22 recEMT2 = map.getRec22();
                    //hier koennen formatierungsoptionen hinterlegt werden
                    
    //                if (recEMT2.is_no_db_val(EMAIL_SEND_TARGETS.aktiv)) {
    //                    this.setLayoutBackgroundColorInMap(v,new Color(230,230,230));
    //                    this.setTextColorInMap(v,Color.DARKGRAY);
    //                }
                    
                } catch (myException e) {
                    e.printStackTrace();
                }
            }
        }
      
       
        private class SendComponent extends E2_UniversalListComponent {
        	private String key = "SendComponent<6618f97e-7654-422d-9cd9-7b371cec9f00>";
        	
			/**
			 * @author martin
			 * @date 16.02.2021
			 *
			 */
			public SendComponent() {
				super();
			}


			@Override
			public String key() throws myException {
				return key;
			}

			@Override
			public String userText() throws myException {
				return "OK?";
			}

			@Override
			public void prepare_ContentForNew(boolean bSetDefault) throws myException {
				this._clear();
			}

			@Override
			public void populate(SQLResultMAP resultMap) throws myException {
				this._clear()._w100()._h(20);
				Rec22 recEmailTarget = EMT2_LIST_ComponentMap.this.getRec22();

				try {
					if (recEmailTarget.is_yes_db_val(EMAIL_SEND_TARGETS.send_ok)) {
						this._a(new RB_lab()._icon(E2_ResourceIcon.get_RI("email_done.png")), new RB_gld()._center_mid());
					} else {
						this._a(new ButtonSend(), new RB_gld()._center_mid());
					}
				} catch (Exception e) {

				}
				
			}

			private class ButtonSend extends E2_Button {
				public ButtonSend() {
					super();
					this._image(E2_ResourceIcon.get_RI("email.png"));
					
					this._aaa(()-> {
						Rec22EmailSend rec22send = null;
						try {
							Rec22 recEmailTarget = EMT2_LIST_ComponentMap.this.getRec22();
							rec22send = new Rec22EmailSend(recEmailTarget.getUpRec22(EMAIL_SEND.id_email_send));
							rec22send._sendEmail(new VEK<Long>()._a(recEmailTarget.getIdLong()));
							em2TransportHashMap.getNavigationList().Refresh_ComponentMAP(rec22send.getIdLong().toString(), E2_ComponentMAP.STATUS_VIEW);
							bibMSG.MV()._addInfo(S.ms("Emails wurden verschickt\n").ut(rec22send.getLastSendedEmailsOK().concatenante("\n")));
						} catch (MailException e) {
							e.printStackTrace();
							bibMSG.MV()._addAlarm(S.ms("eMail-Fehler beim eMail-Versand\n").ut(rec22send.getLastSendedEmailsError().concatenante("\n")));
							bibMSG.MV()._add(e);
						} catch (Exception e) {
							e.printStackTrace();
							bibMSG.MV()._addAlarm(S.ms("Fehler beim eMail-Versand"));
							bibMSG.MV()._add(e);
						}
					});
				}
			}
			
			@Override
			public Object get_Copy(Object objHelp) throws myExceptionCopy {
				return new SendComponent();
			}        

			
			@Override
			public void set_bEnabled_For_Edit(boolean enabled) throws myException {
			}
			
        }





        
    }
     
// 
 
    private class EMT2_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT  {
        //zentrale hashmap fuer transport von infos
        private RB_TransportHashMap   m_tpHashMap = null;
        
        public EMT2_LIST_FORMATING_Agent(RB_TransportHashMap  p_tpHashMap) throws myException {
           this.m_tpHashMap = p_tpHashMap;
        }
        
        
        public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)    throws myException   {
        }
        
        public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException  {
        }
    }
 
    
    
    
    
    
     
    
    private class EMT2_LIST_SqlFieldMAP extends Project_SQLFieldMAP  {
	
        //zentrale hashmap fuer transport von infos
        private RB_TransportHashMap   m_tpHashMap = null;
        
        public EMT2_LIST_SqlFieldMAP(RB_TransportHashMap  p_tpHashMap) throws myException   {
            
            super(_TAB.email_send_targets.n(), "", false);
            
            this.m_tpHashMap = p_tpHashMap;        
            this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SQL_FIELDMAP,this);
            
            this.initFields();
        }
        
    }
    
    
    
    
    
    
    
    private class EMT2_MASK_ComponentMap extends RB_ComponentMap {
        //zentrale hashmap fuer transport von infos
     
        private RB_TransportHashMap   m_tpHashMap = null;
         
        public RB_TransportHashMap getParams() {
            return m_tpHashMap;
        }
        
        public EMT2_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
           super();
        
           this.m_tpHashMap = p_tpHashMap;        
           
             
            this.registerComponent(new RB_KF(EMAIL_SEND_TARGETS.id_email_send),    		new RB_lab());
            this.registerComponent(new RB_KF(EMAIL_SEND_TARGETS.id_email_send_targets), new RB_lab());
            this.registerComponent(new RB_KF(EMAIL_SEND_TARGETS.id_user_send),    		new RB_SelField_USER(true,new Extent(200),ENUM_USER_TYP.BUERO,ENUM_USER_TYP.ENTWICKLER));
            this.registerComponent(new RB_KF(EMAIL_SEND_TARGETS.pos),    				new RB_TextField()._width(100));
            this.registerComponent(new RB_KF(EMAIL_SEND_TARGETS.sending_time),    		new RB_lab());
            this.registerComponent(new RB_KF(EMAIL_SEND_TARGETS.send_ok),    			new RB_cb());
            this.registerComponent(new RB_KF(EMAIL_SEND_TARGETS.target_adress),   		new RB_TextField()._width(400));
     
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

                //auser der target-adresse/pos sind alle inaktiv
                preSettingsContainer.rb_set_enabled(EMAIL_SEND_TARGETS.id_email_send, false);
                preSettingsContainer.rb_set_enabled(EMAIL_SEND_TARGETS.id_email_send_targets, false);
                preSettingsContainer.rb_set_enabled(EMAIL_SEND_TARGETS.id_user_send, false);
                preSettingsContainer.rb_set_enabled(EMAIL_SEND_TARGETS.send_ok, false);
                
                Rec22EmailSend recSend = new Rec22EmailSend()._fill_id(m_tpHashMap.getMotherKeyValue());
                RecList22 targets = recSend.get_down_reclist22(EMAIL_SEND_TARGETS.id_email_send);
                
                
                preSettingsContainer.rb_set_defaultMaskValue(EMAIL_SEND_TARGETS.pos,""+(targets.size()+1));
            }
            return null;
         }
        
    }
     
    
    
    private class EMT2_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
        
        //zentrale hashmap fuer transport von infos
     
        private RB_TransportHashMap   m_tpHashMap = null;
        
        public EMT2_MASK_ComponentMapCollector(RB_TransportHashMap  p_tpHashMap) throws myException {
            super();
            
            this.m_tpHashMap = p_tpHashMap;     
            this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_COMPONENT_MAP_COLLECTOR,this);
            
            this.registerComponent(EMT2_CONST.getLeadingMaskKey(), new EMT2_MASK_ComponentMap(this.m_tpHashMap));
        }
        
        public RB_TransportHashMap getParams() {
            return m_tpHashMap;
        }
     
    }
 
    
    private class EMT2_MASK_DataObject extends RB_Dataobject_V22 {
     
        //zentrale hashmap fuer transport von infos
        private RB_TransportHashMap   m_tpHashMap = null;
     
     
        public EMT2_MASK_DataObject(Rec22 recORD, MASK_STATUS status, RB_TransportHashMap  p_tpHashMap)     throws myException {
            super(recORD, status);
            
            this.m_tpHashMap = p_tpHashMap;     
            
        }
     
        public EMT2_MASK_DataObject(RB_TransportHashMap  p_params) throws myException {
            super(_TAB.email_send_targets);
            
             this.m_tpHashMap = p_params;     
        }
        
        
        public RB_TransportHashMap getParams() {
            return m_tpHashMap;
        }
     
    }
     
    
    
    private class EMT2_MASK_DataObjectCollector extends RB_DataobjectsCollector_V22 {
 	
        private RB_TransportHashMap   m_tpHashMap = null;
        
        public EMT2_MASK_DataObjectCollector(RB_TransportHashMap  p_tpHashMap) throws myException {
            super();
            this.m_tpHashMap = p_tpHashMap;     
            
            this.m_tpHashMap._setMaskDataObjectsCollector(this);    
            this.m_tpHashMap._setMaskStatusOnLoad(MASK_STATUS.NEW);
            
            this.registerComponent(_TAB.email_send_targets.rb_km(), new EMT2_MASK_DataObject(this.m_tpHashMap));
            
            this._addMessageTranslator(new RB_MessageTranslator(
                            new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
            
        }
        
       
        public EMT2_MASK_DataObjectCollector(RB_TransportHashMap  p_tpHashMap, String id_EMAIL_SEND_TARGETS, MASK_STATUS status) throws myException {
            super();
            
            this.m_tpHashMap = p_tpHashMap;     
        
            this.m_tpHashMap._setMaskDataObjectsCollector(this);    
            this.m_tpHashMap._setMaskStatusOnLoad(status);
            
            this.registerComponent(_TAB.email_send_targets.rb_km(), new EMT2_MASK_DataObject(new Rec22(_TAB.email_send_targets)._fill_id(id_EMAIL_SEND_TARGETS),status,this.m_tpHashMap));
            
            this._addMessageTranslator(new RB_MessageTranslator(
                            new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
        }
     
        @Override
        public void database_to_dataobject(Object startPoint) throws myException {
        }
      
        @Override
        public RB_DataobjectsCollector_V22 get_copy() throws myException {
            return null;
        }
        
        
        @Override
        public void manipulate_filled_records_before_save(RB_DataobjectsCollector_V22 do_collector, MyE2_MessageVector mv)	throws myException {
            
        }
        @Override
        public void execute_final_statements_in_open_transaction(RB_DataobjectsCollector_V22 do_collector,	MyE2_MessageVector mv) throws myException {
            
        }
    }
 
 
 
 
 
 
     
     private class EMT2_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
     
        //zentrale hashmap fuer transport von infos
        private RB_TransportHashMap   params = null;
        
        private EMT2_MASK_MaskGrid maskGrid = null;
        
        public EMT2_MASK_MaskModulContainer(RB_TransportHashMap  p_tpHashMap) throws myException {
            super();
            
            this.params = p_tpHashMap;
            
            //anfangsausmasse des fensterpopups
            this._setWidth(EMT2_CONST.EMT2_NUM_CONST.MASKPOPUP_WIDTH.getValue())._setHeight(EMT2_CONST.EMT2_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
            
            this.params.put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK,this);
            
            EMT2_MASK_ComponentMapCollector compMapCollector = new EMT2_MASK_ComponentMapCollector(this.params) ; 
            this.registerComponent(_TAB.email_send_targets.rb_km(), compMapCollector );
            
            this.rb_INIT(E2_MODULNAME_ENUM.MODUL.EMAIL_SEND_TARGETS_V2_MASK, this.maskGrid=new EMT2_MASK_MaskGrid(this.params), true);
            
            this.set_oResizeHelper(new ownResizer());
        }
        
        
        private class ownResizer extends XX_BasicContainerResizeHelper {
            @Override
            public void do_actionAfterResizeWindow(E2_BasicModuleContainer ownContainer) throws myException {
                maskGrid.resize(ownContainer.get_oExtWidth().getValue(), ownContainer.get_oExtHeight().getValue());
            }
            
        }
    }
 
 
 
     public class EMT2_MASK_MaskGrid extends E2_Grid {
        
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
        
        public EMT2_MASK_MaskGrid(RB_TransportHashMap  p_tpHashMap) throws myException {
            super();
            
            int iWidthComplete = EMT2_CONST.EMT2_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue()+
                                      EMT2_CONST.EMT2_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue()+20;
            this._setSize(iWidthComplete)._bo_no();
            
            this.m_tpHashMap = p_tpHashMap;
            this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_GRID,this);
            
            EMT2_MASK_ComponentMap  map1 = (EMT2_MASK_ComponentMap) this.m_tpHashMap.getMaskComponentMapCollector().get(this.m_tpHashMap.getLeadingMaskKey());
            
            //beginn erster tab
            E2_Grid g1 = fieldContainers._ar(new E2_Grid()._setSize( EMT2_CONST.EMT2_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue(),
                                                                        EMT2_CONST.EMT2_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue())._bo_no());        
            
            int iZahl = 1;
            
            tabText._a(S.ms("Tab "+(iZahl++)));
            
            g1._a(new RB_lab(EMT2_READABLE_FIELD_NAME.getReadableForMask(EMAIL_SEND_TARGETS.id_email_send)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(EMAIL_SEND_TARGETS.id_email_send), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
            g1._a(new RB_lab(EMT2_READABLE_FIELD_NAME.getReadableForMask(EMAIL_SEND_TARGETS.id_email_send_targets)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(EMAIL_SEND_TARGETS.id_email_send_targets), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
            g1._a(new RB_lab(EMT2_READABLE_FIELD_NAME.getReadableForMask(EMAIL_SEND_TARGETS.target_adress)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(EMAIL_SEND_TARGETS.target_adress), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
            g1._a(new RB_lab(EMT2_READABLE_FIELD_NAME.getReadableForMask(EMAIL_SEND_TARGETS.id_user_send)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(EMAIL_SEND_TARGETS.id_user_send), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
            g1._a(new RB_lab(EMT2_READABLE_FIELD_NAME.getReadableForMask(EMAIL_SEND_TARGETS.pos)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(EMAIL_SEND_TARGETS.pos), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
            g1._a(new RB_lab(EMT2_READABLE_FIELD_NAME.getReadableForMask(EMAIL_SEND_TARGETS.sending_time)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(EMAIL_SEND_TARGETS.sending_time), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
            g1._a(new RB_lab(EMT2_READABLE_FIELD_NAME.getReadableForMask(EMAIL_SEND_TARGETS.send_ok)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(EMAIL_SEND_TARGETS.send_ok), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
             
            this.renderMask();
            
            this.resize(EMT2_CONST.EMT2_NUM_CONST.MASKPOPUP_WIDTH.getValue(),
                          EMT2_CONST.EMT2_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
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
    private enum EMT2_READABLE_FIELD_NAME {
        
        ID_EMAIL_SEND(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_TARGETS.id_email_send,"ID-Email",100,100,"ID-Email",new Alignment(Alignment.RIGHT, Alignment.TOP)),
        ID_EMAIL_SEND_TARGETS(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_TARGETS.id_email_send_targets,"ID",100,100,"ID",new Alignment(Alignment.RIGHT, Alignment.TOP)),
        ID_USER_SEND(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_TARGETS.id_user_send,"Wer",100,100,"Versand von",new Alignment(Alignment.LEFT, Alignment.TOP)),
        POS(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_TARGETS.pos,"Sort",100,100,"Sort",new Alignment(Alignment.RIGHT, Alignment.TOP)),
        SENDING_TIME(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_TARGETS.sending_time,"Wann",100,100,"Versand am",new Alignment(Alignment.CENTER, Alignment.TOP)),
        SEND_OK(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_TARGETS.send_ok,"OK",30,30,"Sendung OK",new Alignment(Alignment.CENTER, Alignment.TOP)),
        TARGET_ADRESS(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_TARGETS.target_adress,"Zieladresse",400,400,"Zieladresse",new Alignment(Alignment.LEFT, Alignment.TOP)),
        ;
        
        private IF_Field m_field = null;
        private String   m_readAbleForList = null;
        private String   m_readAbleForMask = null;
        private int      m_widthLabelList = 10;                  //breite fuer die labels
        private int      m_widthComponentList = 10;              //breite fuer die komponente
        private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
        
        private EMT2_READABLE_FIELD_NAME(IF_Field f, String readAble) {
            this.m_field=f;
            this.m_readAbleForList = readAble;
        }
        
        private EMT2_READABLE_FIELD_NAME(IF_Field f, String readAbleForList, int m_widthLabelList, int widthComponentList, String readAbleForMask, Alignment align) {
            this.m_field=f;
            this.m_readAbleForList = readAbleForList;
            this.m_readAbleForMask = readAbleForMask;
            this.m_widthLabelList = m_widthLabelList;
            this.m_widthComponentList = widthComponentList;
            this.m_align = align;
        }
        
        
        
        public static String getReadableForList(IF_Field f) {
            String ret = "";
            
            for (EMT2_READABLE_FIELD_NAME  rf: EMT2_READABLE_FIELD_NAME.values() ) {
                if (rf.m_field == f) {
                    return rf.m_readAbleForList;
                }
            }
            return ret;
        }
        
        public static String getListText(IF_Field f) {
          return EMT2_READABLE_FIELD_NAME.getReadableForList(f);
        }
        
        public static String getReadableForMask(IF_Field f) {
            String ret = "";
            
            for (EMT2_READABLE_FIELD_NAME  rf: EMT2_READABLE_FIELD_NAME.values() ) {
                if (rf.m_field == f) {
                    return rf.m_readAbleForMask;
                }
            }
            return ret;
        }
        
        public static String getMaskText(IF_Field f) {
          return EMT2_READABLE_FIELD_NAME.getReadableForMask(f);
        }
        
        
        public static int getLabelWidth(IF_Field f) {
            int ret = 10;
            
            for (EMT2_READABLE_FIELD_NAME  rf: EMT2_READABLE_FIELD_NAME.values() ) {
                if (rf.m_field == f) {
                    return rf.m_widthLabelList;
                }
            }
            return ret;
        }
        
        public static int getComponentWidth(IF_Field f) {
            int ret = 10;
            
            for (EMT2_READABLE_FIELD_NAME  rf: EMT2_READABLE_FIELD_NAME.values() ) {
                if (rf.m_field == f) {
                    return rf.m_widthComponentList;
                }
            }
            return ret;
        }
        
        
        public static Alignment getAlignment(IF_Field f) {
            Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
            
            for (EMT2_READABLE_FIELD_NAME  rf: EMT2_READABLE_FIELD_NAME.values() ) {
                if (rf.m_field == f) {
                    return rf.m_align;
                }
            }
            return ret;
        }
        
        
        public static int getMaxLabelWidth() {
            int i = 10;
            for (EMT2_READABLE_FIELD_NAME  rf: EMT2_READABLE_FIELD_NAME.values() ) {
                if (rf.m_widthLabelList >i) {
                    i=rf.m_widthLabelList;
                }
            }
            return i;
        }
        
        public static int getMaxComponentWidth() {
            int i = 10;
            for (EMT2_READABLE_FIELD_NAME  rf: EMT2_READABLE_FIELD_NAME.values() ) {
                if (rf.m_widthComponentList >i) {
                    i=rf.m_widthComponentList;
                }
            }
            return i;
        }
        
        //sucht die groessere breite zwischen label und componenten-breite aus	
        public static int getMaxComponentOrLabelSize(IF_Field f) {
            int ret = 10;
                
            for (EMT2_READABLE_FIELD_NAME  rf: EMT2_READABLE_FIELD_NAME.values() ) {
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




	@Override
	public E2_DataSearch getSearcher() {
		return null;
	}
 
}
 
 
 
