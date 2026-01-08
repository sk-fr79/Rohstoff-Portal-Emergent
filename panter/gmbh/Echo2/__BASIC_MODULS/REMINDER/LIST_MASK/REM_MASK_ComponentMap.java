package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_Label;
import panter.gmbh.Echo2.RB.COMP.RB_TextAnzeige;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea_old;
import panter.gmbh.Echo2.RB.COMP.RB_TextField_old;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HighLevel_SelectFieldUser;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.Echo2.components.LeftRightSelect2.LR_CB2;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.DB_ENUMS.REMINDER_DEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_REMINDER_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REMINDER_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REMINDER_USER;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class REM_MASK_ComponentMap extends RB_ComponentMap {

	private  REM__IF_getTableAndID  f_list_container = null;
	
	/**
	 * 
	 * @param p_list_container (REM_LIST_BasicModuleContainer calling, can be null)
	 * @throws myException
	 */
	public REM_MASK_ComponentMap(REM__IF_getTableAndID  p_list_container) throws myException {
        super();
        
        this.f_list_container = p_list_container;
        
        this.registerComponent(REMINDER_DEF.abgeschlossen_am.fk(),    			new RB_TextField_old(REMINDER_DEF.abgeschlossen_am,100));
        this.registerComponent(new RB_KF(REMINDER_DEF.erinnerung_ab),    			new RB_date_selektor() );
        
        RB_CheckBox cb = new RB_CheckBox(REMINDER_DEF.erinnerung_bei_anlage, 
				new MyE2_String("Nachricht bei Neuanlage"),
				new MyE2_String("Wenn angewählt, wird beim ersten speichern (erstellen) der erstellen Erinnerungsmeldung eine Nachricht geschickt.")
				);
        cb.setNoBorder();

        this.registerComponent(new RB_KF(REMINDER_DEF.erinnerung_bei_anlage),    	cb);
        this.registerComponent(new RB_KF(REMINDER_DEF.id_reminder_def),    		new RB_Label(REMINDER_DEF.id_reminder_def));
        this.registerComponent(new RB_KF(REMINDER_DEF.id_table),    				new RB_TextField_old(REMINDER_DEF.id_table));

        this.registerComponent(new RB_KF(REMINDER_DEF.id_user_abgeschlossen),		new RB_HighLevel_SelectFieldUser(REMINDER_DEF.id_user_abgeschlossen,
	        																									true, 
	        																									new Extent(122), 
	        																									ENUM_USER_TYP.AKTIV));

        this.registerComponent(new RB_KF(REMINDER_DEF.id_user_angelegt),			new RB_HighLevel_SelectFieldUser(REMINDER_DEF.id_user_angelegt,
																												true, 
																												new Extent(122), 
																												ENUM_USER_TYP.AKTIV));

        this.registerComponent(new RB_KF(REMINDER_DEF.intervall),    				new RB_TextField_old(REMINDER_DEF.intervall,50));
        this.registerComponent(new RB_KF(REMINDER_DEF.reminder_heading),    		new RB_TextField_old(REMINDER_DEF.reminder_heading,500));
        this.registerComponent(new RB_KF(REMINDER_DEF.reminder_text),    			new RB_TextArea_old(REMINDER_DEF.reminder_text,500,6));
        this.registerComponent(new RB_KF(REMINDER_DEF.table_name),    			new RB_TextField_old(REMINDER_DEF.table_name,150));
        //this.rb_register(new RB_KF(REMINDER_DEF.modul_connect_ziel), 		new RB_TextField_old(REMINDER_DEF.modul_connect_ziel,150));
        this.registerComponent(new RB_KF(REMINDER_DEF.modul_connect_ziel), 		new RB_TextAnzeige(150));
        
		this.registerComponent(REM_CONST.MASK_KEYS.USER_CROSSTABLE.key(), 		new REM_MASK_UserLeftRight_CrossField());
		this.registerComponent(REM_CONST.MASK_KEYS.BUTTON_ABSCHLUSS.key(), 		new REM_MASK_Button_Abschliessen(this));
		

		
		//validierer mindestens ein benutzer
		this.getMaskSetAndValidContainer().registerComponent(new RB_KF(REMINDER_DEF.id_reminder_def), new RB_Mask_Set_And_Valid() {
			@Override
			public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, ExecINFO oExecInfo) throws myException {
				
				MyE2_MessageVector mv_rueck = new MyE2_MessageVector();
				
				if (ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
					REM_MASK_UserLeftRight_CrossField ulr = (REM_MASK_UserLeftRight_CrossField)rbMASK.getComp(REM_CONST.MASK_KEYS.USER_CROSSTABLE.key());
					
					if (ulr.get_v_cb_right().size()==0) {
						mv_rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte mindestens Benutzer zuteilen !")));
					}
				}
				return mv_rueck;
			}
		});

		
    }
	
    @Override
    public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {
    	
        return null;
    }
    
    @Override
    public MyE2_MessageVector maskSettings_do_After_Load() throws myException {

    	// Modulname des verknüpften Moduls anzeigen
    	RB_TextAnzeige tfZiel = (RB_TextAnzeige)this.getComp(REMINDER_DEF.modul_connect_ziel.fk());
    	String sModulname = tfZiel.getText();

//    	String sModulname = this.get_cActualDBValueFormated(REMINDER_DEF.modul_connect_ziel.fn());
    	if (!bibALL.isEmpty(sModulname)){
    		MODUL  oModul = E2_MODULNAME_ENUM.find_Corresponding_Enum(sModulname);
    		if (oModul != null){
    			tfZiel.setText(oModul.get_userInfo().CTrans());
    		}
    	}
   	
        return null;
    }

    
    @Override
    public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
    }
    
    
	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer, MASK_STATUS status) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		preSettingsContainer.rb_get(REMINDER_DEF.id_user_abgeschlossen.fk()).set_Enabled(false);
		preSettingsContainer.rb_get(REMINDER_DEF.abgeschlossen_am.fk()).set_Enabled(false);

		preSettingsContainer.rb_get(REMINDER_DEF.table_name.fk()).set_Enabled(false);
		preSettingsContainer.rb_get(REMINDER_DEF.id_table.fk()).set_Enabled(false);
		preSettingsContainer.rb_get(REMINDER_DEF.modul_connect_ziel.fk()).set_Enabled(false);
		preSettingsContainer.rb_get(REMINDER_DEF.id_user_angelegt.fk()).set_Enabled(false);
		
		preSettingsContainer.rb_set_mandatoryField(REMINDER_DEF.intervall,true);
		preSettingsContainer.rb_set_mandatoryField(REMINDER_DEF.erinnerung_ab,true);
		
		if (status==MASK_STATUS.NEW) {
			_TAB 	oBaseTable	 		= this.f_list_container.get_table();
			String 	sBaseTableID		= this.f_list_container.get_id();
			MODUL 	oModulConnect 		= this.f_list_container.get_modul();
			
			String  sModulConnectCallKey	= null;
			
			
			if (oBaseTable == null){
				oBaseTable 			= _TAB.user;
				sBaseTableID 		= bibALL.get_ID_USER();
				oModulConnect		= null;
				sModulConnectCallKey	= null;
			} else {
				if (oModulConnect != null) {
					sModulConnectCallKey = oModulConnect.get_callKey();
				}
			}
			
			
			preSettingsContainer.rb_set_defaultMaskValue(REMINDER_DEF.table_name,oBaseTable.baseTableName() );
			preSettingsContainer.rb_set_defaultMaskValue(REMINDER_DEF.id_table,sBaseTableID);
			preSettingsContainer.rb_set_defaultMaskValue(REMINDER_DEF.modul_connect_ziel,sModulConnectCallKey);
			
			preSettingsContainer.rb_set_defaultMaskValue(REMINDER_DEF.intervall,"1");
			preSettingsContainer.rb_set_defaultMaskValue(REMINDER_DEF.id_user_angelegt, bibALL.get_ID_USER_FORMATTED());

			REM_MASK_UserLeftRight_CrossField ulr = (REM_MASK_UserLeftRight_CrossField)this.getComp(REM_CONST.MASK_KEYS.USER_CROSSTABLE.key());
			Vector<LR_CB2> v_left = ulr.get_v_cb_left();
			
			this.get_me(v_left).setSelected(true);
			this.get_me(v_left).doActionPassiv();
			
		} else if (status==MASK_STATUS.EDIT) {
			
			preSettingsContainer.rb_get(REMINDER_DEF.erinnerung_bei_anlage.fk()).set_Enabled(false);
			
			boolean bAllowEdit 		= false;
			boolean bAllowClose 	= false;
			boolean bIstAbgeschlossen = false;
			
			RECORD_REMINDER_DEF oReminder = (RECORD_REMINDER_DEF) this.getRbDataObjectActual().get_RecORD();
			
			if (oReminder != null){
				bIstAbgeschlossen = (oReminder.get_ID_USER_ABGESCHLOSSEN_cUF() != null);
				
				RECLIST_REMINDER_USER lUser = oReminder.get_DOWN_RECORD_LIST_REMINDER_USER_id_reminder_def();
				
				for (RECORD_REMINDER_USER user : lUser){
					if (bibALL.get_ID_USER().equals(user.get_ID_USER_cUF()) ){
						bAllowEdit = user.is_ALLOW_CHANGE_YES() ;
						bAllowClose = user.is_ALLOW_CLOSE_YES();
						break;
					}
				}
				
				// Ausnahme: man ist der Ersteller des Reminders
				if (bibALL.get_ID_USER().equals(oReminder.get_ID_USER_ANGELEGT_cUF())){
					bAllowEdit = true;
					bAllowClose = true;
				}
				
				//2016-05-17: martin: Ausnahme 2: man ist geschaeftsfuehrer oder developer
				if (bibALL.get_RECORD_USER().is_DEVELOPER_YES() || bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES()) {
					bAllowEdit = true;
					bAllowClose = true;
				}
				
				// abgeschlossen noch einarbeiten
				bAllowEdit &= !bIstAbgeschlossen;
				
			} else {
				// status == neu??
				bAllowEdit = true;
				bAllowClose = true;
			}
			
			// allow edit
			preSettingsContainer.rb_get(REMINDER_DEF.erinnerung_ab.fk()).set_Enabled(bAllowEdit);
			preSettingsContainer.rb_get(REMINDER_DEF.intervall.fk()).set_Enabled(bAllowEdit);
			preSettingsContainer.rb_get(REMINDER_DEF.reminder_heading.fk()).set_Enabled(bAllowEdit);
			preSettingsContainer.rb_get(REMINDER_DEF.reminder_text.fk()).set_Enabled(bAllowEdit);
			preSettingsContainer.rb_get(REM_CONST.MASK_KEYS.USER_CROSSTABLE.key()).set_Enabled(bAllowEdit);
			
			preSettingsContainer.rb_get(REM_CONST.MASK_KEYS.BUTTON_ABSCHLUSS.key()).set_Enabled(bAllowClose);
			
			// buttontext "Abgeschlossen" setzen
			((REM_MASK_Button_Abschliessen)this.get_Comp(REM_CONST.MASK_KEYS.BUTTON_ABSCHLUSS.name())).updateButtonText();
		}
		
		return mv;
	}

	
	private LR_CB2 get_me(Vector<LR_CB2> v_left) throws myException {
	
		for (LR_CB2 c: v_left) {
			REM_MASK_UserLeftRight_kapsel kapsel =(REM_MASK_UserLeftRight_kapsel)c.get_place_4_everything();
			if (kapsel.get_rec_user().get_ID_USER_cUF().equals(bibALL.get_RECORD_USER().get_ID_USER_cUF())) {
				return c;
			}
			
		}
		return null;
		
	}
	
	
}
 
