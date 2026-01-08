package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MAILPROFILE;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea_old;
import panter.gmbh.Echo2.RB.COMP.RB_TextField_old;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_CONST;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_CONST.MAILPROFILE;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_SCHABLONE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL_SEND_SCHABLONE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.query.SELECT;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.mail.MailAdressChecker;

public class EMS_Mask extends RB_ComponentMap
{

	public EMS_Mask() throws myException
	{
		super();

//		this.rb_register(	new RB_KF(_DB.EMAIL_SEND_SCHABLONE$ID_MANDANT),
//							new RB_TextField(_DB.EMAIL_SEND_SCHABLONE,_DB.EMAIL_SEND_SCHABLONE$ID_MANDANT,200));
		
		this.registerComponent(	new RB_KF(EMAIL_SEND_SCHABLONE.id_email_send_schablone),
							new RB_TextField_old(_DB.EMAIL_SEND_SCHABLONE,_DB.EMAIL_SEND_SCHABLONE$ID_EMAIL_SEND_SCHABLONE,200));
		
		this.registerComponent(	new RB_KF(EMAIL_SEND_SCHABLONE.kennung_mailversand),
							new RB_SelectField(_DB.EMAIL_SEND_SCHABLONE,_DB.EMAIL_SEND_SCHABLONE$KENNUNG_MAILVERSAND,
												MANDANT_CONST.get_MailProfileDropDown(true),false, new Extent(200)));
		
		this.registerComponent(	new RB_KF(EMAIL_SEND_SCHABLONE.send_type),
							new EMS_MASK_SendType());
		
		this.registerComponent(	new RB_KF(EMAIL_SEND_SCHABLONE.absender),
							new RB_TextField_old(_DB.EMAIL_SEND_SCHABLONE,_DB.EMAIL_SEND_SCHABLONE$ABSENDER, 400));
		
		this.registerComponent(	new RB_KF(EMAIL_SEND_SCHABLONE.betreff),
							new RB_TextField_old(_DB.EMAIL_SEND_SCHABLONE,_DB.EMAIL_SEND_SCHABLONE$BETREFF,600));

		this.registerComponent(	new RB_KF(EMAIL_SEND_SCHABLONE.text),
							new RB_TextArea_old(_DB.EMAIL_SEND_SCHABLONE,_DB.EMAIL_SEND_SCHABLONE$TEXT,600,20));

		this.registerSetterValidators(new RB_KF(EMAIL_SEND_SCHABLONE.id_email_send_schablone), new validate_Check_Double());
		this.registerSetterValidators(new RB_KF(EMAIL_SEND_SCHABLONE.kennung_mailversand), new validate_Systemmessage_MustHaveEmail());
	}

	@Override
	public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException
	{
		return null;
	}

	@Override
	public MyE2_MessageVector maskSettings_do_After_Load() throws myException
	{
		return null;
	}

	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer SurfaceSettingsContainer, MASK_STATUS status) throws myException 	{
		if (status.isStatusEdit() || status.isStatusNew()) {
	//		SurfaceSettingsContainer.get(new RB_KF(_DB.EMAIL_SEND_SCHABLONE$ID_MANDANT)).set_MustField(true);
	//		SurfaceSettingsContainer.get(new RB_KF(_DB.EMAIL_SEND_SCHABLONE$ID_MANDANT)).set_rb_Default(new EMS__FindActualIdMandant().get_ID_MANDANT_UF());
			
			SurfaceSettingsContainer.rb_get(new RB_KF(EMAIL_SEND_SCHABLONE.betreff)).set_MustField(true);
			SurfaceSettingsContainer.rb_get(new RB_KF(EMAIL_SEND_SCHABLONE.text)).set_MustField(true);
		}
		
		return null;
	}

	@Override
	public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys)
	{
	}

	
	private class validate_Check_Double extends RB_Mask_Set_And_Valid {
		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, ExecINFO oExecInfo) throws myException {
			
			if (ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
				if (rbMASK.rb_get_belongs_to().rb_Actual_DataobjectCollector().get(new RB_KM(_DB.EMAIL_SEND_SCHABLONE)).rb_MASK_STATUS().isStatusNew()) {
					String cKennung = ((IF_RB_Component_Savable)rbMASK.getRbComponent(new RB_KF(EMAIL_SEND_SCHABLONE.kennung_mailversand))).rb_readValue_4_dataobject();
					
					
					if (S.isFull(cKennung)) {
						String ID_MANDANT_IN_MASK = new EMS__FindActualIdMandant().get_ID_MANDANT_UF();
						
						SELECT sel = new SELECT("*").from(_DB.EMAIL_SEND_SCHABLONE).
								where(_DB.EMAIL_SEND_SCHABLONE$KENNUNG_MAILVERSAND, cKennung).
								and(_DB.EMAIL_SEND_SCHABLONE$ID_MANDANT,ID_MANDANT_IN_MASK);
						
						RECLIST_EMAIL_SEND_SCHABLONE rlSchablone = new RECLIST_EMAIL_SEND_SCHABLONE();
						rlSchablone.set_oDB(new EMS_DBToolBox());
						rlSchablone.set_Select(sel);
						rlSchablone.REFRESH();
						if (rlSchablone.size()>0) {
							return new MyE2_MessageVector(new MyE2_Alarm_Message(new MyE2_String(S.t("Der Kennungswert :"), S.ut(cKennung), S.t(" war bereits vergeben !"))));
						};
					}
				}
			}
			return null;
		}

	}
	
	
	private class validate_Systemmessage_MustHaveEmail extends RB_Mask_Set_And_Valid {
		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			if (ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
				
				String c_aktuelle_kennung = ((RB_SelectField)rbMASK.getRbComponent(new RB_KF(EMAIL_SEND_SCHABLONE.kennung_mailversand))).get_ActualWert();
				String c_aktuelle_eMail = ((RB_TextField_old)rbMASK.getRbComponent(new RB_KF(EMAIL_SEND_SCHABLONE.absender))).getText();
				if (S.isFull(c_aktuelle_kennung) && c_aktuelle_kennung.equals(MAILPROFILE.SYSTEMNACHRICHT.get_DB_Value())) {
					
					MAILPROFILE mail = MAILPROFILE.find_MailProfile(c_aktuelle_kennung);
					String meldung = (mail==null?c_aktuelle_kennung:new MyE2_String(mail.get_DB_MaskText()).CTrans());
					
					if (S.isEmpty(c_aktuelle_eMail)) {
						mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Das Profil ",true,meldung,false," erlaubt keine leere mail-Adresse",true)));
					} else if (! (new MailAdressChecker(c_aktuelle_eMail).isOK())) {
						mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Das Profil ",true,meldung,false," erlaubt keine FEHLERHAFTE mail-Adresse",true)));
					}
				}
			}
			return mv;
		}

	}
	
	
	
	
	
}
