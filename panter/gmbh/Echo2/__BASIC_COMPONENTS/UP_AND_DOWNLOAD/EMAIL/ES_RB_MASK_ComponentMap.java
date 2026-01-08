package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_SQLField;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_TextField_old;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_CONST;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_SCHABLONE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_MANDANT_ext;

public class ES_RB_MASK_ComponentMap extends RB_ComponentMap {

	
	private String table_base_name=null;
	private String id_table = null;
	
	
	public ES_RB_MASK_ComponentMap(String p_table_base_name, String p_id_table) throws myException {
		super();
		this.rb_INIT_4_DB(_DB.EMAIL_SEND);
		
		this.id_table = 		p_id_table;
		this.table_base_name = 	p_table_base_name;
		
		this.registerComponent(new RB_KF(EMAIL_SEND.id_email_send),	new RB_TextField_old(new RB_SQLField(_DB.EMAIL_SEND, _DB.EMAIL_SEND$ID_EMAIL_SEND),60));
		this.registerComponent(new RB_KF(EMAIL_SEND.betreff),			new RB_TextField(500));
		this.registerComponent(new RB_KF(EMAIL_SEND.text), 			new RB_TextArea(500,7));
		this.registerComponent(new RB_KF(EMAIL_SEND.betreff_2_send),	new RB_TextField(500));
		this.registerComponent(new RB_KF(EMAIL_SEND.text_2_send), 	new RB_TextArea(500,7));
		this.registerComponent(new RB_KF(EMAIL_SEND.sender_adress), 	new RB_TextField_old(new RB_SQLField(_DB.EMAIL_SEND, _DB.EMAIL_SEND$SENDER_ADRESS),300));
		this.registerComponent(new RB_KF(EMAIL_SEND.id_email_send,ES_CONST.HASHKEY_MASK_DAUGHTER_TARGETS),	new ES_mask_complex_EMAIL_SEND_TARGETS(this));
		this.registerComponent(new RB_KF(EMAIL_SEND.id_email_send,ES_CONST.HASHKEY_MASK_LIST_ARCHIVMEDIEN), 	new ES_mask_complex_LISTE_ANLAGEN(this));
		this.registerComponent(new RB_KF(EMAIL_SEND.id_email_send,ES_CONST.HASHKEY_MASK_SHOW_INFO_REPLACETAGS), 	new ES_MASK_bt_show_replacing_list(p_table_base_name, p_id_table));
		this.registerComponent(new RB_KF(EMAIL_SEND.id_email_send,ES_CONST.HASHKEY_MASK_SHOW_FIELDS_4_SEND), 		new ES_MASK_bt_show_fields_for_sending(p_table_base_name, p_id_table));
		this.registerComponent(EMAIL_SEND.send_type.fk(),				new ES_MASK_SendType());
		
		
		this.registerSetterValidators(new RB_KF(EMAIL_SEND.id_email_send), new ES_SAV_AreAllAttachementsAllowed());
		this.registerSetterValidators(new RB_KF(EMAIL_SEND.id_email_send), new ES_SAV_BeiOriginalNur_EINE_Zieladresse());
		this.registerSetterValidators(new RB_KF(EMAIL_SEND.id_email_send), new ES_SAV_Even_ONE_Attachment());
		this.registerSetterValidators(new RB_KF(EMAIL_SEND.id_email_send), new ES_SAV_Even_ONE_Target());
		this.registerSetterValidators(new RB_KF(EMAIL_SEND.id_email_send), new ES_SAV_MailAdresses_Correct());
		this.registerSetterValidators(new RB_KF(EMAIL_SEND.id_email_send), new ES_SAV_Make_ComponentsInactivWhenTargetWasSend());
		//bei neueingabe wird automatisch ein target angehaengt
		this.registerSetterValidators(new RB_KF(EMAIL_SEND.id_email_send), new ES_SAV_AddNewTargetLineOnNew());

	}

	@Override
	public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {
		return null;
	}

	@Override
	public MyE2_MessageVector maskSettings_do_After_Load() throws myException {
		return new MyE2_MessageVector();
	}



	@Override
	public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
	}

	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer, MASK_STATUS status) throws myException {
		
		preSettingsContainer.rb_get(new RB_KF(EMAIL_SEND.betreff)).set_MustField(true);
		preSettingsContainer.rb_get(new RB_KF(EMAIL_SEND.text)).set_AlignHorizontal(RB__CONST.ALIGN_HORIZONTAL.LEFT);
		
		preSettingsContainer.rb_get(new RB_KF(EMAIL_SEND.betreff)).set_Description(new MyE2_String("eMail-Betreff"), new MyE2_String("Hauptmaske"));
		preSettingsContainer.rb_get(new RB_KF(EMAIL_SEND.sender_adress)).set_Description(new MyE2_String("eMail-Absender"), new MyE2_String("Hauptmaske"));
		preSettingsContainer.rb_get(new RB_KF(EMAIL_SEND.text)).set_Description(new MyE2_String("eMail-Text"), new MyE2_String("Hauptmaske"));
		
		preSettingsContainer.rb_get(new RB_KF(EMAIL_SEND.sender_adress)).set_rb_Default(bibALL.get_RECORD_USER().get_EMAIL_cUF_NN(""));
		
		if (status.isStatusNew()) {
			//dann die signatur der standard-vorgabe aus dem mandantenstamm vorgeben
			RECORD_MANDANT_ext recMandant_ext = new RECORD_MANDANT_ext(bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF());
			
			RECORD_EMAIL_SEND_SCHABLONE schab = recMandant_ext.get_mandant_mailSchablone(MANDANT_CONST.MAILPROFILE.ALLGEMEIN);
			if (schab!=null) {
				preSettingsContainer.rb_get(new RB_KF(EMAIL_SEND.text)).set_rb_Default("\n\n"+S.NN(schab.get_TEXT_cUF_NN("")));
			} else {
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Bitte legen Sie ein Standard-eMail-Sendeprofile im Mandanten an!")));
			}
			
			
			//2015-05-27: als absender-text wird die signatur des benutzer genommen
			if (S.isFull(bibALL.get_RECORD_USER().get_MAIL_SIGNATUR_cUF_NN(""))) {
				preSettingsContainer.rb_get(new RB_KF(EMAIL_SEND.text)).set_rb_Default("\n\n"+S.NN(bibALL.get_RECORD_USER().get_MAIL_SIGNATUR_cUF_NN("")));
			}
			
		}
		
		preSettingsContainer.rb_get_forcedDB(EMAIL_SEND.id_table).rb_set_formated_value_forced_at_save(this.id_table);
		preSettingsContainer.rb_get_forcedDB(EMAIL_SEND.table_base_name).rb_set_formated_value_forced_at_save(this.table_base_name);
		
		return new MyE2_MessageVector();
	}	


}