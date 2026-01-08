 
package panter.gmbh.Echo2.basic_tools.emailv2;
 
import java.util.Collection;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
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
import panter.gmbh.Echo2.RB.BASICS.IF_RbComponentWithOwnKey;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterBasedOnBasicModulContainer;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponentForAttachments;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;
 
 
/**
 * @author martin
 * Komponente fuer eine maske eine mothertable, die diese als fullDaughter einblendet
 */
public class EM2_MASK_DaughterAttachmentsList extends RB_MaskDaughterBasedOnBasicModulContainer  implements IF_RbComponentWithOwnKey {
 	
 	//fieldKey fuer das einbinden dieser komponente in einer mother-mask
	public static RB_KF  keyForMotherMask = (RB_KF)new RB_KF()._setHASHKEY("EMA2_EMAIL_SEND_ATTACH_MASK_DaughterListForMotherMask<16eda7c2-6bb1-11eb-9439-0242ac130002>")
																._setREALNAME("EMA2_EMAIL_SEND_ATTACH_MASK_DaughterListForMotherMask<16eda7c2-6bb1-11eb-9439-0242ac130002>");
	
 	
 	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    //entscheidet, ob die Tochter eine komplette oder eine reduzierte E2_NavigationList ist
//    private EMA2_LIST_BedienPanel 	bedienPanel = null;
    private VEK<MyE2IF__Component>  toDisableAtMask = new VEK<MyE2IF__Component>();
//    private EMA2_LIST_DATASEARCH  	searcher = null;
    
    private boolean    				m_enabled = true;
    
    /**
     * 
     * @param p_tpHashMap
     * @param useCompactList
     * @throws myException
     */
    public EM2_MASK_DaughterAttachmentsList(RB_TransportHashMap p_tpHashMap) throws myException {
        super();
    
        this._init(new InternalLIST_BasicModuleContainer(p_tpHashMap));
 	}
  
  
  
  	@Override
	public RB_KF getFieldKey() {
		return EM2_MASK_DaughterAttachmentsList.keyForMotherMask;
	}
 
    
    @Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
    	this.m_enabled = enabled; 
    	
//		//falls ausfuehrliche variante
//		if (this.bedienPanel!=null) {
//			this.bedienPanel.set_bEnabled_For_Edit(enabled);
//		}
		
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
		return EMAIL_SEND_ATTACH.id_email_send;
	}
	
	/**
	 * innerer basicmodulContainer fuer die einlagerung in eine MotherMask
	 * @author martin
	 *
	 */
	public class InternalLIST_BasicModuleContainer extends Project_BasicModuleContainer    {
		
	    public InternalLIST_BasicModuleContainer(RB_TransportHashMap p_params) throws myException    {
	    	
	        super(E2_MODULNAME_ENUM.MODUL.EMAIL_SEND_ATTACH_V2_LIST.get_callKey());
	        
	        EM2_MASK_DaughterAttachmentsList oThis = EM2_MASK_DaughterAttachmentsList.this;
	        
	        oThis.m_tpHashMap = new RB_TransportHashMap();  
	        //die transporthashmap muss jetzt in die ableitende klasse
	        oThis._setTransportHashMap(oThis.m_tpHashMap);
	        
	        //dann den ganzen paramhash der mutter in den tochterhash einfuegen
	        oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.MOTHERPARAMSHASH, p_params);
 
	        oThis.m_tpHashMap._setModulContainerList(this);
	        oThis.m_tpHashMap._setLeadingMaskKey(EMA2_CONST.getLeadingMaskKey());
	        oThis.m_tpHashMap._setLeadingTableOnMask(EMA2_CONST.getLeadingTable());
 
	        this.set_bVisible_Row_For_Messages(false);
 	        
 	        
	        EMA2_LIST_ComponentMap listComponentMap  = null;
 	        
            oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.NAVILIST,new E2_NavigationListCompact());
 
             
//            EMA2_LIST_bt_New btNew = new EMA2_LIST_bt_New(oThis.m_tpHashMap);
//            EMA2_LIST_bt_Copy btCopy = new EMA2_LIST_bt_Copy(oThis.m_tpHashMap);
                
 //           oThis.toDisableAtMask._a(btNew)._a(btCopy);
            oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oThis.m_tpHashMap.getNaviListCompact())._setLDC(new RB_gld()._ins(2, 0, 10, 0)._left_mid()));
//            oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btNew._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
//            oThis.m_tpHashMap.getNaviListCompact()._addLeftComponent(btCopy._setLDC(new RB_gld()._ins(0, 0, 10, 0)._left_mid()));
//            oThis.searcher = new EMA2_LIST_DATASEARCH(oThis.m_tpHashMap);
//            oThis.searcher.getLabelSuchBeschriftung().setVisible(false);
//            oThis.searcher._setWidthSearchText(150);
//                
//            oThis.m_tpHashMap.getNaviListCompact()._addRightComponent(oThis.searcher._setLDC(new RB_gld()._ins(20, 0, 10, 0)._left_mid()));
            oThis.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST,this);
 
            listComponentMap = new EMA2_LIST_ComponentMap(oThis.m_tpHashMap);
            oThis.m_tpHashMap.getNaviListCompact().INIT_WITH_ComponentMAP(listComponentMap,  E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
            this.add(oThis.m_tpHashMap.getNaviListCompact(), E2_INSETS.I_2_2_2_2);
            oThis.m_tpHashMap.getNaviListCompact()._REBUILD_COMPLETE_LIST("");
            oThis.m_tpHashMap.getNaviListCompact()._rebuildTitleLine();
	        
	        listComponentMap.add_oSubQueryAgent(new mapSettingAgent());
	    }
	}
	
    @Override
    public E2_DataSearch getSearcher() {
        return null;
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
//					if (c instanceof EMA2_LIST_bt_DeleteInListRow) {
//						((EMA2_LIST_bt_DeleteInListRow)c).setEnabledForEditOfSuperClass(false);
//					}
//					if (c instanceof EMA2_LIST_bt_ListToMaskInListRow) {
//						if ( ((EMA2_LIST_bt_ListToMaskInListRow)c).isUsedToEdit()) {
//							((EMA2_LIST_bt_ListToMaskInListRow)c).setEnabledForEditOfSuperClass(false);
//						}
//					}
					
				}
			}
		}
    	
    }
    
    
    
    public static class EMA2_CONST {
        public enum EMA2_NAMES implements IF_enum_4_db {
            
            CHECKBOX_LISTE( S.ms("Auswahl-Checkbox"))
            ,MARKER_LISTE( S.ms("Markierung Liste"))
            ,DIRECT_DEL( S.ms("Loeschbutton in Listenzeile"))
            ,DIRECT_EDIT( S.ms("Editbutton in Listenzeile"))
            ,DIRECT_VIEW( S.ms("Anzeigebutton in Listenzeile"))
            ,DIRECT_UPLOAD( S.ms("Dateien hochladen"))
            ,DATASET_NAME(S.ms("<dataset-name>"))
            ;
            
            private MyE2_String userText = null; 
            private String      m_dbVal = null;
            
            private EMA2_NAMES(MyE2_String p_userText) {
                this.userText = p_userText;
            }
            
            //konstuktor mit abweichenden werten
            private EMA2_NAMES(MyE2_String p_userText, String dbVal) {
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
            return _TAB.email_send_attach;
        }
        
        
        /**
         * 
         * @return der ueberall verwendete mask-key
         * @throws myException
         */
        public static RB_KM getLeadingMaskKey() throws myException {
            return _TAB.email_send_attach.rb_km();
        }
        
        
        
        
        
        public enum EMA2_BUTTONS implements IF_enum_4_db  {
            DELETE("LOESCHE_EMAIL_SEND_ATTACH")
            ,EDIT("BEARBEITE_EMAIL_SEND_ATTACH")
            ,VIEW("ANZEIGE_EMAIL_SEND_ATTACH")
            ,NEW("NEUEINGABE_EMAIL_SEND_ATTACH")
            ,
            
            ;
            
            private String KEY = null;
            
            private EMA2_BUTTONS() {
                this.KEY=this.name();
            }
            private EMA2_BUTTONS(String key) {
                this.KEY=key;
            }
            public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
            @Override
            public String user_text_lang() {return this.name();    }
            @Override
            public String user_text() {        return this.name();        }
            @Override
            public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
                return bibENUM.dd_array(EMA2_BUTTONS.values(), emptyPairInFront);
            }
            
        }
        
        
        
        
        /*
         * enum: hier koennen numerische (long) werte zentral gesteuert werden 
         */
        public static enum EMA2_NUM_CONST {
             MASKPOPUP_WIDTH(new Integer(800))
            ,MASKPOPUP_HEIGHT(new Integer(800))
            ,MASKPOPUP_DESCRIPTION_COL_SIZE(new Integer(100))
            ,MASKPOPUP_FIELD_COL_SIZE(new Integer(700))
             
            ;
            
            private Integer   m_value = null;
            
            private EMA2_NUM_CONST(Integer p_value) {
                this.m_value=p_value;
            }
            
            public Integer getValue() {
                return this.m_value;
            }
        }
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
 
 
 
 
 
 
    private class EMA2_LIST_ComponentMap extends E2_ComponentMAP_V22  {
        
        private RB_TransportHashMap   m_tpHashMap = null;
        
        public EMA2_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException  {
      
            super(new EMA2_LIST_SqlFieldMAP(p_tpHashMap));
            
            this.m_tpHashMap = p_tpHashMap;        
            
            SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
            
            this.add_Component(EMA2_CONST.EMA2_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
            this.add_Component(EMA2_CONST.EMA2_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
            
            //hier kommen die Felder  
            this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(EMAIL_SEND_ATTACH.id_archivmedien),true),     new MyE2_String(EMA2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND_ATTACH.id_archivmedien)));
            this.add_Component(new ListComponentForShowDownloads());
            
            this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(EMAIL_SEND_ATTACH.id_email_send),true)._setVisibleInList(false),     new MyE2_String(EMA2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND_ATTACH.id_email_send)));
            this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(EMAIL_SEND_ATTACH.id_email_send_attach),true),     new MyE2_String(EMA2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND_ATTACH.id_email_send_attach)));
            //neu ab 20171025        
            this._setLineWrapListHeader(true 
                                      ,EMAIL_SEND_ATTACH.id_archivmedien.fn()
                                      ,EMAIL_SEND_ATTACH.id_email_send_attach.fn()
            );
            
            RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
            this._setLayoutElements(gldElementCenter
                                     ,EMAIL_SEND_ATTACH.id_archivmedien.fn()
                                     ,EMAIL_SEND_ATTACH.id_email_send_attach.fn()
            );
            
            RB_gld gldTitelCenter = 	new RB_gld()._center_top()._ins(1,2,1,1)._col(new E2_ColorDark());
            this._setLayoutTitles(gldTitelCenter
                                     ,EMAIL_SEND_ATTACH.id_archivmedien.fn()
                                     ,EMAIL_SEND_ATTACH.id_email_send_attach.fn()
            );
      
      
            //hier kann das layout der einzelnen spalten definiert werden 
            // spaltenlayout fuer:  EMAIL_SEND_ATTACH.id_archivmedien.fn()
            this._setColExtent(     new Extent(EMA2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(EMAIL_SEND_ATTACH.id_archivmedien)), EMAIL_SEND_ATTACH.id_archivmedien.fn());
            this._setLayoutElements(new RB_gld()._al(EMA2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND_ATTACH.id_archivmedien))._ins(3,1,3,1)._col(new E2_ColorDark()), EMAIL_SEND_ATTACH.id_archivmedien.fn());
            this._setLayoutTitles(  new RB_gld()._al(EMA2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND_ATTACH.id_archivmedien))._ins(1,2,1,1)._col(new E2_ColorDark()), EMAIL_SEND_ATTACH.id_archivmedien.fn());
            // ----
            //
//            // spaltenlayout fuer:  EMAIL_SEND_ATTACH.id_email_send.fn()
//            this._setColExtent(     new Extent(EMA2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(EMAIL_SEND_ATTACH.id_email_send)), EMAIL_SEND_ATTACH.id_email_send.fn());
//            this._setLayoutElements(new RB_gld()._al(EMA2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND_ATTACH.id_email_send))._ins(3,1,3,1)._col(new E2_ColorDark()), EMAIL_SEND_ATTACH.id_email_send.fn());
//            this._setLayoutTitles(  new RB_gld()._al(EMA2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND_ATTACH.id_email_send))._ins(1,2,1,1)._col(new E2_ColorDark()), EMAIL_SEND_ATTACH.id_email_send.fn());
//            // ----
//            //
            // spaltenlayout fuer:  EMAIL_SEND_ATTACH.id_email_send_attach.fn()
            this._setColExtent(     new Extent(EMA2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(EMAIL_SEND_ATTACH.id_email_send_attach)), EMAIL_SEND_ATTACH.id_email_send_attach.fn());
            this._setLayoutElements(new RB_gld()._al(EMA2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND_ATTACH.id_email_send_attach))._ins(3,1,3,1)._col(new E2_ColorDark()), EMAIL_SEND_ATTACH.id_email_send_attach.fn());
            this._setLayoutTitles(  new RB_gld()._al(EMA2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND_ATTACH.id_email_send_attach))._ins(1,2,1,1)._col(new E2_ColorDark()), EMAIL_SEND_ATTACH.id_email_send_attach.fn());
            // ----
            //
            this.set_oSubQueryAgent(new EMA2_LIST_FORMATING_Agent(this.m_tpHashMap));
                
        }
        
        
        
        @Override
        public Object get_Copy(Object objHelp) throws myExceptionCopy {
            E2_ComponentMAP_V22 oRueck = new E2_ComponentMAP_V22(this.get_oSQLFieldMAP());
            E2_ComponentMAP.Copy_FieldsAndSettings(this, oRueck);
            oRueck.setComponentMapMarker(new EMA2_LIST_ComponentMapMapMarker(oRueck));
        
            return oRueck;
        }
        
        /*
         * umstellung auf E2_ComponentMAP_V22 mit marker-option
         * korrespontiert mit  
         */
        public class EMA2_LIST_ComponentMapMapMarker extends E2_ComponentMapMarker {
            public EMA2_LIST_ComponentMapMapMarker(E2_ComponentMAP_V22 p_map) {
                super(p_map);
            }
            @Override
            protected void innerFormat(Collection<Component> v) {
                super.innerFormat(v);
                
                E2_ComponentMAP_V22 map = (E2_ComponentMAP_V22)this.getMap();
                
                try {
                    Rec22 recEMA2 = map.getRec22();
                } catch (myException e) {
                    e.printStackTrace();
                }
            }
        }
      
        
    }
     
 
    private class EMA2_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT  {
        //zentrale hashmap fuer transport von infos
        private RB_TransportHashMap   m_tpHashMap = null;
        
        public EMA2_LIST_FORMATING_Agent(RB_TransportHashMap  p_tpHashMap) throws myException {
           this.m_tpHashMap = p_tpHashMap;
        }
        
        
        public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)    throws myException   {
        }
        
        public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException  {
        }
    }
 
    
    
    private class EMA2_LIST_SqlFieldMAP extends Project_SQLFieldMAP  {
	
        //zentrale hashmap fuer transport von infos
        private RB_TransportHashMap   m_tpHashMap = null;
        
        public EMA2_LIST_SqlFieldMAP(RB_TransportHashMap  p_tpHashMap) throws myException   {
            
            super(_TAB.email_send_attach.n(), "", false);
            
            this.m_tpHashMap = p_tpHashMap;        
            this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SQL_FIELDMAP,this);
            
            this.initFields();
        }
        
    }
    
    
    
    

     
    
    
 
    /*
     * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
     */
    private enum EMA2_READABLE_FIELD_NAME {
        
        ID_ARCHIVMEDIEN(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_ATTACH.id_archivmedien,"ID(Archiv)",100,100,"ID(Archiv)",new Alignment(Alignment.RIGHT, Alignment.TOP)),
        ID_EMAIL_SEND(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_ATTACH.id_email_send,"ID(Email)",100,100,"ID(Email)",new Alignment(Alignment.RIGHT, Alignment.TOP)),
        ID_EMAIL_SEND_ATTACH(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_ATTACH.id_email_send_attach,"ID",100,100,"ID",new Alignment(Alignment.RIGHT, Alignment.TOP)),
        ;
        
        private IF_Field m_field = null;
        private String   m_readAbleForList = null;
        private String   m_readAbleForMask = null;
        private int      m_widthLabelList = 10;                  //breite fuer die labels
        private int      m_widthComponentList = 10;              //breite fuer die komponente
        private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
        
        private EMA2_READABLE_FIELD_NAME(IF_Field f, String readAble) {
            this.m_field=f;
            this.m_readAbleForList = readAble;
        }
        
        private EMA2_READABLE_FIELD_NAME(IF_Field f, String readAbleForList, int m_widthLabelList, int widthComponentList, String readAbleForMask, Alignment align) {
            this.m_field=f;
            this.m_readAbleForList = readAbleForList;
            this.m_readAbleForMask = readAbleForMask;
            this.m_widthLabelList = m_widthLabelList;
            this.m_widthComponentList = widthComponentList;
            this.m_align = align;
        }
        
        
        
        public static String getReadableForList(IF_Field f) {
            String ret = "";
            
            for (EMA2_READABLE_FIELD_NAME  rf: EMA2_READABLE_FIELD_NAME.values() ) {
                if (rf.m_field == f) {
                    return rf.m_readAbleForList;
                }
            }
            return ret;
        }
        

        
        public static String getReadableForMask(IF_Field f) {
            String ret = "";
            
            for (EMA2_READABLE_FIELD_NAME  rf: EMA2_READABLE_FIELD_NAME.values() ) {
                if (rf.m_field == f) {
                    return rf.m_readAbleForMask;
                }
            }
            return ret;
        }
        


        
        
        public static Alignment getAlignment(IF_Field f) {
            Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
            
            for (EMA2_READABLE_FIELD_NAME  rf: EMA2_READABLE_FIELD_NAME.values() ) {
                if (rf.m_field == f) {
                    return rf.m_align;
                }
            }
            return ret;
        }
        
        
        //sucht die groessere breite zwischen label und componenten-breite aus	
        public static int getMaxComponentOrLabelSize(IF_Field f) {
            int ret = 10;
                
            for (EMA2_READABLE_FIELD_NAME  rf: EMA2_READABLE_FIELD_NAME.values() ) {
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
 
    
    private class ListComponentForShowDownloads extends E2_UniversalListComponentForAttachments {

		/**
		 * @author martin
		 * @date 10.02.2021
		 *
		 */
		public ListComponentForShowDownloads() {
			super();
			this._setShowDownloadButton(true)._setShowDownFileName(true)._setShowMedienkenner(true);
		}

		@Override
		public String key() throws myException {
			return "ListComponentForShowDownloads<c40aa434-6bc3-11eb-9439-0242ac130002>";
		}

		@Override
		public String userText() throws myException {
			return "Anlagen";
		}

		@Override
		public String getFieldNameOfIdArchivMedien() {
			return EMAIL_SEND_ATTACH.id_archivmedien.fn();
		}

		@Override
		public Object get_Copy(Object objHelp) throws myExceptionCopy {
			return new ListComponentForShowDownloads();
		}
    	
    }
    
}
 
 
 
