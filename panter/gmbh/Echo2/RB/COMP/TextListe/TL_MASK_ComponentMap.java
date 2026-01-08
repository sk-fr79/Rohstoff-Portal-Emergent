 
package panter.gmbh.Echo2.RB.COMP.TextListe;
   
import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_LabelFilledWithResult;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_labSaveable;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.EnumReadableTableName;
import panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;
  
public class TL_MASK_ComponentMap extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
    private EnumTableTranslator   tabTrans  = null;
	
	
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public TL_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
       super();
    
       this.m_tpHashMap = p_tpHashMap;        
       
       tabTrans = (EnumTableTranslator)this.m_tpHashMap.getSB(TL_CONST.TabTrans);
         
       this.registerComponent(TEXT_LISTE.id_text_liste.fk(),    		new RB_lab());
       this.registerComponent(TEXT_LISTE.id_table.fk(),    				new RB_lab());
       this.registerComponent(TEXT_LISTE.tablename.fk(),    			new OwnLabelWithTranslation());

       this.registerComponent(TEXT_LISTE.position_enum.fk(),    		new RB_selField()._populate(TL_enumPositionierung.KOPF, true)._width(200));
       this.registerComponent(TEXT_LISTE.position.fk(), 		   		new RB_labSaveable());
       
       this.registerComponent(TEXT_LISTE.titel_text.fk(),    			new RB_TextField()._width(650));
       this.registerComponent(TEXT_LISTE.fontsize_titel_text.fk(),    	new RB_selField()._populate(TL_enumFontSize.ZEHN, true)._width(100)._aaa(new TL_AgentSaveMaskSettings(this, tabTrans)));
       this.registerComponent(TEXT_LISTE.bold_titel_text.fk(),    		new RB_cb()._width(20)._aaa(new TL_AgentSaveMaskSettings(this, tabTrans)));
       this.registerComponent(TEXT_LISTE.italic_titel_text.fk(),    	new RB_cb()._width(20)._aaa(new TL_AgentSaveMaskSettings(this, tabTrans)));
       this.registerComponent(TEXT_LISTE.underline_titel_text.fk(),    	new RB_cb()._width(20)._aaa(new TL_AgentSaveMaskSettings(this, tabTrans)));

       this.registerComponent(TEXT_LISTE.aufzaehl_text.fk(),    	  	new RB_TextField()._width(300));
       this.registerComponent(TEXT_LISTE.fontsize_aufzaehl_text.fk(),  	new RB_selField()._populate(TL_enumFontSize.ZEHN, true)._width(100)._aaa(new TL_AgentSaveMaskSettings(this, tabTrans)));
       this.registerComponent(TEXT_LISTE.bold_aufzaehl_text.fk(),    	new RB_cb()._width(20)._aaa(new TL_AgentSaveMaskSettings(this, tabTrans)));
       this.registerComponent(TEXT_LISTE.italic_aufzaehl_text.fk(),     new RB_cb()._width(20)._aaa(new TL_AgentSaveMaskSettings(this, tabTrans)));
       this.registerComponent(TEXT_LISTE.underline_aufzaehl_text.fk(),  new RB_cb()._width(20)._aaa(new TL_AgentSaveMaskSettings(this, tabTrans)));

       this.registerComponent(TEXT_LISTE.lang_text.fk(),    			new RB_TextArea()._width(650)._rows(10));
       this.registerComponent(TEXT_LISTE.fontsize_lang_text.fk(),    	new RB_selField()._populate(TL_enumFontSize.ZEHN, true)._width(100)._aaa(new TL_AgentSaveMaskSettings(this, tabTrans)));
       this.registerComponent(TEXT_LISTE.bold_lang_text.fk(),   	   	new RB_cb()._width(20)._aaa(new TL_AgentSaveMaskSettings(this, tabTrans)));
       this.registerComponent(TEXT_LISTE.italic_lang_text.fk(),    		new RB_cb()._width(20)._aaa(new TL_AgentSaveMaskSettings(this, tabTrans)));
       this.registerComponent(TEXT_LISTE.underline_lang_text.fk(),    	new RB_cb()._width(20)._aaa(new TL_AgentSaveMaskSettings(this, tabTrans)));
//     
       //beispiel fuer einen setter-validator 
       this.registerSetterValidators(TEXT_LISTE.id_text_liste,new RB_Mask_Set_And_Valid() {
			@Override
			public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
				MyE2_MessageVector mv = new MyE2_MessageVector();
				if (ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION_BEFORE_FIELDVALIDATION) {
					RB_MaskController mc = new RB_MaskController(TL_MASK_ComponentMap.this);
					if (S.isAllEmpty(mc.get_liveVal(TEXT_LISTE.titel_text),mc.get_liveVal(TEXT_LISTE.aufzaehl_text),mc.get_liveVal(TEXT_LISTE.lang_text))) {
						mv._addAlarm("Bitte füllen Sie mindestens einen Text aus !");
					}
				}
				return mv;
			}
		});
 
       
       this._addListenersAfterMaskWasPopulated((e)->{
    	   
    	   MyE2_MessageVector mv = bibMSG.getNewMV();
    	   
    	   try {
	    		RB_MaskController mc = new RB_MaskController(TL_MASK_ComponentMap.this);
				if (mc.getMaskStatus()==MASK_STATUS.NEW || mc.getMaskStatus()==MASK_STATUS.NEW_COPY ) {
					
				   Long idMotherTab = this.m_tpHashMap.getMotherKeyValue();
					String folgeWert = "1";

					//jetzt die positions-id vordefinieren
					SEL selNextOrder = new SEL("MAX("+TEXT_LISTE.position.fn()+")").FROM(_TAB.text_liste)
								.WHERE(new vgl(TEXT_LISTE.tablename,tabTrans.db_val()))
								.AND(new vgl(TEXT_LISTE.id_table,idMotherTab.toString()));
					
					VEK<Object[]> result = bibDB.getResultLines(new SqlStringExtended(selNextOrder.s()),true);
					
					if (result!=null && result.size()==1 && result.get(0).length==1 && result.get(0)[0]!=null) {
						Long maxWert = ((BigDecimal)result.get(0)[0]).longValue();
						folgeWert = ""+(maxWert.longValue()+1);
					}
					
					mc.set_maskVal(TEXT_LISTE.position, folgeWert, mv);
				}
    	   } catch (Exception e1) {
    		   e1.printStackTrace();
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
    
		MyE2_MessageVector mv = new MyE2_MessageVector();
		try {
			EnumTableTranslator tabTrans = (EnumTableTranslator)this.m_tpHashMap.getSB(TL_CONST.TabTrans);
			
	        //falls sich alles in einer tochtermaske abspielt, dannn muessen hier die verknuepfung zu presettingscontainer hergestellt werden
	        if (this.m_tpHashMap.getMotherKeyLookupField()!=null && this.m_tpHashMap.getMotherKeyValue().toString()!=null) {
	            preSettingsContainer.rb_set_forcedValueAtSave(this.m_tpHashMap.getMotherKeyLookupField(), this.m_tpHashMap.getMotherKeyValue().toString());
	            preSettingsContainer.rb_set_forcedValueAtSave(TEXT_LISTE.tablename, tabTrans.getTableNameForTextListe());
	        }
        
				
			//vorgaben nur bei new
			if (status==MASK_STATUS.NEW) {
				//dann die zuletzt benutzte attributskombination laden
				HMAP<IF_Field,String>  hmapStored = new TL_SaveSettingsOnMask(tabTrans).read();
				if (hmapStored!=null) {
					for (IF_Field g: hmapStored.keySet()) {
						preSettingsContainer.rb_set_defaultMaskValue(g, hmapStored.get(g));
					}
				}
				//jetzt nachsehen, ob die auswahl der schriftgoesse erlaubt ist
				String s_defaultFont = ENUM_MANDANT_CONFIG.FORMULAR_TEXT_LISTEN_STANDARD_SCHRIFTGROESSE.getCheckedValue();
				Long   lDefaultFont = 10l;
				VEK<Long> allowedSizes = new VEK<Long>()._a(8l,10l,12l,14l);
				if (S.isFull(s_defaultFont)) {
					 try {
						lDefaultFont = Long.parseLong(s_defaultFont);
						if (!allowedSizes.contains(lDefaultFont)) {
							lDefaultFont = 10l;
						}
					} catch (Exception e) {
						lDefaultFont=10l;
						e.printStackTrace();
					}
				}
				if (!ENUM_MANDANT_DECISION.FORMULAR_TEXT_LISTEN_SCHRIFTGROESSE_ERLAUBT.is_YES()) {
					preSettingsContainer.rb_set_defaultMaskValue(TEXT_LISTE.fontsize_titel_text, lDefaultFont.toString());
					preSettingsContainer.rb_set_defaultMaskValue(TEXT_LISTE.fontsize_aufzaehl_text, lDefaultFont.toString());
					preSettingsContainer.rb_set_defaultMaskValue(TEXT_LISTE.fontsize_lang_text, lDefaultFont.toString());
				}
			}
				
			
			//in allen faellen, die erlaubnis-settings aus der systemeinstellung kopieren
			Boolean erlaubeSchriftgroesse = 		ENUM_MANDANT_DECISION.FORMULAR_TEXT_LISTEN_SCHRIFTGROESSE_ERLAUBT.is_YES();
			Boolean erlaubeFettdruck = 				ENUM_MANDANT_DECISION.FORMULAR_TEXT_LISTEN_FETTDRUCK_ERLAUBT.is_YES();
			Boolean erlaubeKursivDruck= 			ENUM_MANDANT_DECISION.FORMULAR_TEXT_LISTEN_KURSIVDRUCK_ERLAUBT.is_YES();
			Boolean erlaubeUnterstreichung = 		ENUM_MANDANT_DECISION.FORMULAR_TEXT_LISTEN_UNTERSTREICHUNG_ERLAUBT.is_YES();
			
			preSettingsContainer.rb_set_enabled(TEXT_LISTE.fontsize_titel_text, erlaubeSchriftgroesse);
			preSettingsContainer.rb_set_enabled(TEXT_LISTE.fontsize_aufzaehl_text, erlaubeSchriftgroesse);
			preSettingsContainer.rb_set_enabled(TEXT_LISTE.fontsize_lang_text, erlaubeSchriftgroesse);
			
			preSettingsContainer.rb_set_enabled(TEXT_LISTE.bold_titel_text, erlaubeFettdruck);
			preSettingsContainer.rb_set_enabled(TEXT_LISTE.bold_aufzaehl_text, erlaubeFettdruck);
			preSettingsContainer.rb_set_enabled(TEXT_LISTE.bold_lang_text, erlaubeFettdruck);
			
			preSettingsContainer.rb_set_enabled(TEXT_LISTE.italic_titel_text, erlaubeKursivDruck);
			preSettingsContainer.rb_set_enabled(TEXT_LISTE.italic_aufzaehl_text, erlaubeKursivDruck);
			preSettingsContainer.rb_set_enabled(TEXT_LISTE.italic_lang_text, erlaubeKursivDruck);

			preSettingsContainer.rb_set_enabled(TEXT_LISTE.underline_titel_text, erlaubeUnterstreichung);
			preSettingsContainer.rb_set_enabled(TEXT_LISTE.underline_aufzaehl_text, erlaubeUnterstreichung);
			preSettingsContainer.rb_set_enabled(TEXT_LISTE.underline_lang_text, erlaubeUnterstreichung);
			
		} catch (Exception e) {
			e.printStackTrace();
			mv._addWarn("Error reading usersettings on mask <8e7cb14e-4c8c-4d8e-bffc-0206459cf17b>");
		}
        
        return mv;
     }
	
	
	private class OwnLabelWithTranslation extends RB_LabelFilledWithResult {

		public OwnLabelWithTranslation() throws myException {
			super();
		}

		@Override
		public String transferDbValueToVisibleText(String baseTableName) throws myException {
			EnumReadableTableName tabTrans = EnumReadableTableName.KONTRAKT.getEnumFromBaseTableName(baseTableName);
			if (tabTrans == null) {
				return "<unbekannt>";
			} else {
				return tabTrans.user_text();
			}
		}
		
	}
	
	
}
 
 
