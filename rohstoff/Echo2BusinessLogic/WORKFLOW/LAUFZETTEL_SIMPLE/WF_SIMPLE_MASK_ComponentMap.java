 
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;
   
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_RB_CheckboxList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMapEnumExtender;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.Echo2.components.LeftRightSelect2.LR_CB2;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_PRIO;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_STATUS;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_STATUS;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE.MASK_Controller.WF_SIMPLE_WK_MaskController_Abgeschlossen;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.RecList21_User;
  
public class WF_SIMPLE_MASK_ComponentMap extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   						m_tpHashMap = null;
    
	private String 				  						m_idStatusAbgeschlossen = null;
	
	private ActionAgent_RadioFunction_RB_CheckboxList 	agentWatchdogKadenz = null;

	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public WF_SIMPLE_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
       super();
       this.m_tpHashMap = p_tpHashMap;        
    
       m_idStatusAbgeschlossen = (String)m_tpHashMap.getFromExtender(WF_SIMPLE_CONST.WF_SIMPLE_TransportExtender.ID_STATUS_ABGESCHLOSSEN);
        
       this.registerComponent(new RB_KF(LAUFZETTEL_EINTRAG.id_laufzettel),    new RB_lab());
       this.registerComponent(new RB_KF(LAUFZETTEL_EINTRAG.id_laufzettel_eintrag),    new RB_TextField()._width(100));
       this.registerComponent(new RB_KF(LAUFZETTEL_EINTRAG.angelegt_am),    new RB_date_selektor(100,true));
       this.registerComponent(new RB_KF(LAUFZETTEL_EINTRAG.geloescht),    new RB_cb("Aufgabe ist gelöscht"));

       this.registerComponent(new RB_KF(LAUFZETTEL_EINTRAG.faellig_am),    new RB_date_selektor(100,true));
       
       this.registerComponent(new RB_KF(LAUFZETTEL_EINTRAG.abgeschlossen_am),    new RB_date_selektor(100,true));
       this.registerComponent(new RB_KF(LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von),    new RB_selField()._populate(new RecList21(_TAB.user)._fill(RecList21_User.getSqlExt_Default(true, true)),USER.name,_TAB.user.key(),true)._width(120)._fo_s_plus(0))
       								;
       
       this.registerComponent(new RB_KF(LAUFZETTEL_EINTRAG.id_user_besitzer),    new RB_selField()._populate(new RecList21(_TAB.user)._fill(RecList21_User.getSqlExt_Default(true, true)),USER.name,_TAB.user.key(),true)._width(120)._fo_s_plus(0));
       this.registerComponent(new RB_KF(LAUFZETTEL_EINTRAG.id_user_bearbeiter),    new RB_selField()._populate(new RecList21(_TAB.user)._fill(RecList21_User.getSqlExt_Default(true, true)),USER.name,_TAB.user.key(),true)._width(120)._fo_s_plus(0) );
       
       this.registerComponent(new RB_KF(LAUFZETTEL_EINTRAG.aufgabe),    new RB_TextArea()._width(800)._rows(7));
       this.registerComponent(new RB_KF(LAUFZETTEL_EINTRAG.bericht),    new RB_TextArea()._width(800)._rows(7));

       this.registerComponent(new RB_KF(LAUFZETTEL_EINTRAG.id_eintrag_parent),    new RB_TextField()._width(100));
       this.registerComponent(new RB_KF(LAUFZETTEL_EINTRAG.id_laufzettel_prio),    new RB_selField()._populate(new RecList21(_TAB.laufzettel_prio)._fillWithAll(),LAUFZETTEL_PRIO.prio,_TAB.laufzettel_prio.key(),true)._width(120)._fo_s_plus(0));
       this.registerComponent(new RB_KF(LAUFZETTEL_EINTRAG.id_laufzettel_status),    new RB_selField()._populate(new RecList21(_TAB.laufzettel_status)._fillWithAll(),LAUFZETTEL_STATUS.status,_TAB.laufzettel_status.key(),true)._width(120)._fo_s_plus(0));
       this.registerComponent(new RB_KF(LAUFZETTEL_EINTRAG.id_parent_kadenz),    new RB_TextField()._width(100));
       
       this.registerComponent(new RB_KF(LAUFZETTEL_EINTRAG.kadenz_nach_abschluss),    	new RB_TextField()._width(50));
       this.registerComponent(new RB_KF(LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit),    new RB_cb("nach Fälligkeitstermin der Aufgabe"));
       
       this.registerComponent( RB_cb_KadenzAbschluss.key,	new RB_cb_KadenzAbschluss("nach Abschluss der Aufgabe") );
       
       
       this.registerComponent(new RB_KF(LAUFZETTEL_EINTRAG.nachricht_sent),    new RB_date_selektor(100,true));
       this.registerComponent(new RB_KF(LAUFZETTEL_EINTRAG.privat),    new RB_cb());
       this.registerComponent(new RB_KF(LAUFZETTEL_EINTRAG.send_nachricht),    new RB_cb("Nachricht bei Fälligkeit erzeugen"));
       
       this.registerComponent(WF_SIMPLE_CONST.MASK_KEYS.USER_CROSSTABLE.key(), 		new WF_SIMPLE_UserLeftRight());
       
       // connector-Modul
       this.registerComponent(WF_SIMPLE_CONST.MASK_KEYS_String.MODUL_CONNECTOR.key(),  new RB_MODUL_LINK_Connector() );
       this.registerComponent(WF_SIMPLE_CONST.MASK_KEYS_String.LAUFZETTEL_EINTRAG_ATTACHMENT.key(), new RB_BT_AttachmentToWFEntry());
       
       
       
       // Action agents
       RB_selField 		selStatus 		= (RB_selField) this.get__Comp(LAUFZETTEL_EINTRAG.id_laufzettel_status);
       RB_selField 		selVon 			= (RB_selField) this.get__Comp(LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von);
       RB_date_selektor dtAbgeschlossen = (RB_date_selektor) this.get__Comp(LAUFZETTEL_EINTRAG.abgeschlossen_am);

       
       selStatus._aaa( () ->{
    	   	   String status = selStatus.getActualDbVal().replace(".", "");
    	   	   
	    	   if (status.equals( m_idStatusAbgeschlossen )){
					dtAbgeschlossen.rb_set_db_value_manual(bibALL.get_cDateNOW());
					selVon.rb_set_db_value_manual(bibALL.get_ID_USER_FORMATTED());
	    	   } else {
					dtAbgeschlossen.rb_set_db_value_manual(null);
					selVon.rb_set_db_value_manual(null);
	    	   }
       })  ;

       selStatus._aaa(()-> {
    	   new WF_SIMPLE_WK_MaskController_Abgeschlossen(m_tpHashMap.getMaskComponentMapCollector())	
    	   																		._executeMaskValueSetters()
    			   																._executeMaskAppearencePreSetters()
    			   																._executeMaskAppearenceSetters();
       });

       
       selVon._aaa(() -> {
    	   if (selVon.getActualDbVal() != null && selVon.getActualDbVal() != ""){
				dtAbgeschlossen.rb_set_db_value_manual(bibALL.get_cDateNOW());
				
				// Selectfield braucht formatierte ID :-(
				selStatus.rb_set_db_value_manual(new MyLong(m_idStatusAbgeschlossen).get_cF_LongString());
    	   } else {
				dtAbgeschlossen.rb_set_db_value_manual(null);
				selStatus.rb_set_db_value_manual(null);
    	   }
       });

       selVon._aaa(()-> {
    	   new WF_SIMPLE_WK_MaskController_Abgeschlossen(m_tpHashMap.getMaskComponentMapCollector()) 
    			   																._executeMaskValueSetters()
    			   																._executeMaskAppearencePreSetters()
    			   																._executeMaskAppearenceSetters();
       });
       
      
       RB_cb oCBKadenzNachAbschluss = (RB_cb) this.get__Comp(LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit);
       RB_cb_KadenzAbschluss oCBKadenzNachFaelligkeit = (RB_cb_KadenzAbschluss) this.get__Comp(RB_cb_KadenzAbschluss.key.get_HASHKEY());
       
       
       
		// Watchdog für die Radio-Button-Funktion
		agentWatchdogKadenz = new ActionAgent_RadioFunction_RB_CheckboxList(false);
		agentWatchdogKadenz.add_CheckBox(oCBKadenzNachAbschluss);
		agentWatchdogKadenz.add_CheckBox(oCBKadenzNachFaelligkeit);
		agentWatchdogKadenz.set_AllUnselected();
		
       
//       //beispiel fuer einen setter-validator 
//       this.registerSetterValidators(LAUFZETTEL_EINTRAG.id_xxx,new RB_Mask_Set_And_Valid() {
//			@Override
//			public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,
//					ExecINFO oExecInfo) throws myException {
//				return null;
//			}
//		});

		this.registerSetterValidators(LAUFZETTEL_EINTRAG.id_laufzettel_eintrag, new RB_Mask_Set_And_Valid() {
			
			@Override
			public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,ExecINFO oExecInfo) throws myException {
				if (VALID_TYPE.USE_IN_MASK_LOAD_ACTION  == ActionType ){
					
					
					RB_MaskController rbc = new RB_MaskController(WF_SIMPLE_MASK_ComponentMap.this);
					MASK_STATUS status = rbc.getMaskStatus();

					// Button Attachments
					RB_BT_AttachmentToWFEntry btAttachment = (RB_BT_AttachmentToWFEntry)rbc.get_comp(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag(),WF_SIMPLE_CONST.MASK_KEYS_String.LAUFZETTEL_EINTRAG_ATTACHMENT.key());

					// Button Modul Link
					RB_MODUL_LINK_Connector o = (RB_MODUL_LINK_Connector)rbc.get_comp(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag(), WF_SIMPLE_CONST.MASK_KEYS_String.MODUL_CONNECTOR.key());
					
					
					if (status.equals(MASK_STATUS.NEW) || status.equals(MASK_STATUS.NEW_COPY) )  {
						// einzelnes Feld User ausblenden
						RB_selField sel = (RB_selField)rbc.get_comp(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag(),LAUFZETTEL_EINTRAG.id_user_bearbeiter.fk());
						sel.setVisible(false);
						
						// attachments nicht bei neueintrag
						btAttachment.set_bEnabled_For_Edit(false);
						
						
					} else {
						// Auswahlfeld User ausblenden
						WF_SIMPLE_UserLeftRight c = (WF_SIMPLE_UserLeftRight)rbc.get_comp(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag(), WF_SIMPLE_CONST.MASK_KEYS.USER_CROSSTABLE.key());
						c.setVisible(false);
					
						// Modul-Connector init nur im Edit-Modus
						String idLZEintrag = rbc.get_dbVal(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag(), LAUFZETTEL_EINTRAG.id_laufzettel_eintrag);//.replaceAll(".", "");
						idLZEintrag = idLZEintrag.replace(".", "");
						
						o.initConnector(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_EINTRAG_LISTE, idLZEintrag, m_tpHashMap.getRBModulContainerMask());
						o.set_bEnabled_For_Edit(true);
						
						//
						btAttachment.set_bEnabled_For_Edit(true);
												
					}
			
					//
					WF_SIMPLE_WK_MaskController_Abgeschlossen mk = new WF_SIMPLE_WK_MaskController_Abgeschlossen(m_tpHashMap.getMaskComponentMapCollector());
					mk._executeMaskValueSetters()
					._executeMaskAppearencePreSetters()
					._executeMaskAppearenceSetters();
			
					
					
				}
				return null;
			}
		});
		
		
		
    }

	
	
    @Override
    public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {
    	return null;
    }
    
    @Override
    public MyE2_MessageVector maskSettings_do_After_Load() throws myException {
    	RB_MaskController rb = new RB_MaskController(this);
    	
		String kadenz_nach_faelligkeit = rb.get_dbVal(LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit);
		kadenz_nach_faelligkeit = (kadenz_nach_faelligkeit != null ? kadenz_nach_faelligkeit : "N");
		
		if (kadenz_nach_faelligkeit.equalsIgnoreCase("Y")){
			agentWatchdogKadenz.setCheckbox((RB_cb)getComp(LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit), true);
		} else {
			agentWatchdogKadenz.setCheckbox((RB_cb_KadenzAbschluss)getComp(RB_cb_KadenzAbschluss.key), true);
		}
		return null;
    }
    
    
    @Override
    public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
    }
    
    
	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer,MASK_STATUS status) throws myException {
		
		preSettingsContainer.rb_set_enabled(LAUFZETTEL_EINTRAG.abgeschlossen_am, false);
		
		WF_SIMPLE_UserLeftRight ulr = (WF_SIMPLE_UserLeftRight)this.getComp(WF_SIMPLE_CONST.MASK_KEYS.USER_CROSSTABLE.key());
		Vector<LR_CB2> v_left = ulr.get_v_cb_left();
		
		// falls man sich selber setzen will
//		this.get_me(v_left).setSelected(true);
//		this.get_me(v_left).doActionPassiv();
		
		
		if (status.equals(MASK_STATUS.NEW) || status.equals(MASK_STATUS.NEW_COPY)){
			
			HashMap<RB_TransportHashMapEnumExtender,Object> hm = (HashMap<RB_TransportHashMapEnumExtender,Object>)m_tpHashMap.get(RB_TransportHashMap_ENUM.PLACE_FOR_STUCTURED_EXTENSION);
		       
			String idPrio= (String)m_tpHashMap.getFromExtender(WF_SIMPLE_CONST.WF_SIMPLE_TransportExtender.DEFAULT_PRIO);
			String idStatus= (String)m_tpHashMap.getFromExtender(WF_SIMPLE_CONST.WF_SIMPLE_TransportExtender.DEFAULT_STATUS);
			
			preSettingsContainer.rb_set_defaultMaskValue(LAUFZETTEL_EINTRAG.id_laufzettel_prio, bibALL.convertID2FormattedID(idPrio));
			preSettingsContainer.rb_set_defaultMaskValue(LAUFZETTEL_EINTRAG.id_laufzettel_status, bibALL.convertID2FormattedID(idStatus));
			preSettingsContainer.rb_set_defaultMaskValue(LAUFZETTEL_EINTRAG.id_user_besitzer, bibALL.get_ID_USER_FORMATTED());
			preSettingsContainer.rb_set_defaultMaskValue(LAUFZETTEL_EINTRAG.angelegt_am, bibALL.get_cDateNOW());
		
		}
		
		
			
		
        //falls sich alles in einer tochtermaske abspielt, dannn muessen hier die verknuepfung zu presettingscontainer hergestellt werden
        if (this.m_tpHashMap.getMotherKeyLookupField()!=null && this.m_tpHashMap.getMotherKeyValue().toString()!=null) {
            preSettingsContainer.rb_set_forcedValueAtSave(this.m_tpHashMap.getMotherKeyLookupField(), this.m_tpHashMap.getMotherKeyValue().toString());
        }
        return null;
    }
	
	
	
//	/**
//	 * ermitteln der ID die für den Abschluss Zuständig ist.
//	 * @return
//	 * @throws myException
//	 */
//	private String getIDForStatusAbgeschlossen() throws myException{
//		String idRet = null;
//		RECLIST_LAUFZETTEL_STATUS list = new RECLIST_LAUFZETTEL_STATUS("SELECT * FROM " + bibE2.cTO() + ".JT_LAUFZETTEL_STATUS WHERE NVL(TRIGGER_ABSCHLUSS,'N') = 'Y' ");
//		if (list.size() > 0){
//			idRet = list.get(0).get_ID_LAUFZETTEL_STATUS_cF();
//		}
//		return idRet;
//	}
	
	
	private LR_CB2 get_me(Vector<LR_CB2> v_left) throws myException {
		
		for (LR_CB2 c: v_left) {
			WF_SIMPLE_UserLeftRightKapsel kapsel =(WF_SIMPLE_UserLeftRightKapsel)c.get_place_4_everything();
			if (kapsel.get_rec_user().get_key_value().equals(bibALL.get_RECORD_USER().get_ID_USER_cUF())) {
				return c;
			}
			
		}
		return null;
		
	}

}
 
 
