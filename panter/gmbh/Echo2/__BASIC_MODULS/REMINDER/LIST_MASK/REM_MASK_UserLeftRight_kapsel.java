package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.LeftRightSelect2.LR_ObjectExtender;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BORDERCROSSING_USERINFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REMINDER_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class REM_MASK_UserLeftRight_kapsel implements LR_ObjectExtender{
	
	private MyRECORD    rec_crosstable = null;
	private RECORD_USER rec_user = null;
	
	
	private RECORD_REMINDER_USER  rec_user_info = null;
	
	private ownCB_change        			cb_change = null;
	private ownCB_close        				cb_close = null;
	private ownCB_sofortanzeige				cb_sofortanzeige = null;
	private ownCB_sendMail					cb_sendMail = null;
	

	
	public REM_MASK_UserLeftRight_kapsel(MyRECORD _rec_crosstable, RECORD_USER _rec_user) throws myException {
		super();
		this.rec_crosstable =_rec_crosstable;
		this.rec_user = _rec_user;
		this.cb_change = new ownCB_change(this.rec_user_info);
		this.cb_close = new ownCB_close(this.rec_user_info);
		this.cb_sendMail = new ownCB_sendMail(this.rec_user_info);
		this.cb_sofortanzeige = new ownCB_sofortanzeige(this.rec_user_info);
	}


	public REM_MASK_UserLeftRight_kapsel(RECORD_USER _rec_user) throws myException {
		super();
		this.rec_crosstable =null;
		this.rec_user = _rec_user;
		this.cb_change = new ownCB_change(null);
		this.cb_close = new ownCB_close(null);
		this.cb_sendMail = new ownCB_sendMail(null);
		this.cb_sofortanzeige = new ownCB_sofortanzeige(null);

		this.cb_close.setSelected(true);
		this.cb_sendMail.setSelected(true);
		this.cb_sofortanzeige.setSelected(true);
		
	}
	
	
	public MyRECORD get_rec_crosstable() {
		return rec_crosstable;
	}
	
	public RECORD_USER get_rec_user() throws myException {
		return rec_user; 
	}

	public String get_text4_checkBox() throws myException {
		return this.rec_user.get_NAME1_cUF_NN("<name1>")+", "+this.rec_user.get_VORNAME_cUF_NN("<vorname>")+" ("+this.rec_user.get_KUERZEL_cUF_NN("??")+")";
	}

	public String get_saveKEY() throws myException {
		return this.rec_user.get_NAME_cUF_NN("--");
	}

	
	public void set_rec_crosstable(MyRECORD p_rec_crosstable) {
		this.rec_crosstable = p_rec_crosstable;
	}

	public class ownCB_change extends MyE2_CheckBox {
		private RECORD_REMINDER_USER  rec_user_info = null;

		public ownCB_change(RECORD_REMINDER_USER p_rec_user_info) throws myException {
			super();
			this.rec_user_info = p_rec_user_info;
			
			
			if (this.rec_user_info!=null && this.rec_user_info.is_ALLOW_CHANGE_YES()) {
				this.setSelected(true);
			}
			this.setText(new MyE2_String("Ändern").CTrans());
			this.setToolTipText(new MyE2_String("Benutzer darf die Meldung ändern").CTrans());
		}
	}

	public class ownCB_close extends MyE2_CheckBox {
		private RECORD_REMINDER_USER  rec_user_info = null;

		public ownCB_close(RECORD_REMINDER_USER p_rec_user_info) throws myException {
			super();
			this.rec_user_info = p_rec_user_info;
			
			if (this.rec_user_info!=null && this.rec_user_info.is_ALLOW_CLOSE_YES()) {
				this.setSelected(true);
			}
			this.setText(new MyE2_String("Abschließen").CTrans());
			this.setToolTipText(new MyE2_String("Benutzer darf die Meldung abschliessen").CTrans());
		}
	}
	
	public class ownCB_sendMail extends MyE2_CheckBox {
		private RECORD_REMINDER_USER  rec_user_info = null;

		public ownCB_sendMail(RECORD_REMINDER_USER p_rec_user_info) throws myException {
			super();
			this.rec_user_info = p_rec_user_info;
			
			if (this.rec_user_info!=null && this.rec_user_info.is_SEND_MAIL_YES() ) {
				this.setSelected(true);
			}
			this.setText(new MyE2_String("Email").CTrans());
			this.setToolTipText(new MyE2_String("Benutzer erhält eine zusätzliche Email").CTrans());
		}
	}

	
	public class ownCB_sofortanzeige extends MyE2_CheckBox {
		private RECORD_REMINDER_USER  rec_user_info = null;

		public ownCB_sofortanzeige(RECORD_REMINDER_USER p_rec_user_info) throws myException {
			super();
			this.rec_user_info = p_rec_user_info;
			
			if (this.rec_user_info!=null && this.rec_user_info.is_SOFORTANZEIGE_YES() ) {
				this.setSelected(true);
			}
			this.setText(new MyE2_String("Sofortanzeige").CTrans());
			this.setToolTipText(new MyE2_String("Nachricht wird als Sofortnachricht angezeigt.").CTrans());
		}
	}
	
	
	public ownCB_change get_cb_change() {
	
		return cb_change;
	}

		public ownCB_close get_cb_close() {
	
		return cb_close;
	}
	
	public ownCB_sendMail get_cb_sendMail() {
		
		return cb_sendMail;
	}
	
	public ownCB_sofortanzeige get_cb_Sofortnachricht() {
		
		return cb_sofortanzeige;
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.LeftRightSelect2.LR_ObjectExtender#enableExtenderComponents(boolean)
	 */
	@Override
	public void set_enableExtenderComponents(boolean bEnabled)throws myException {
		if (cb_change != null) cb_change.set_bEnabled_For_Edit(bEnabled);
		if (cb_close  != null) cb_close.set_bEnabled_For_Edit(bEnabled);
		if (cb_sendMail != null) cb_sendMail.set_bEnabled_For_Edit(bEnabled);
		if (cb_sofortanzeige != null) cb_sofortanzeige.set_bEnabled_For_Edit(bEnabled);
	}
	
}
