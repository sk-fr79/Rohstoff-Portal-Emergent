package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.LeftRightSelect2.LR_ObjectExtender;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BORDERCROSSING_USERINFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class BOR_MASK_UserLeftRight_kapsel implements LR_ObjectExtender{
	
	private MyRECORD    rec_crosstable = null;
	private RECORD_USER rec_user = null;
	
	
	private RECORD_BORDERCROSSING_USERINFO  rec_user_info = null;
	
	//private ownCB_change        			cb_change = null;
	private ownCB_close        				cb_close = null;

	
	public BOR_MASK_UserLeftRight_kapsel(MyRECORD _rec_crosstable, RECORD_USER _rec_user) throws myException {
		super();
		this.rec_crosstable =_rec_crosstable;
		this.rec_user = _rec_user;
		//this.cb_change = new ownCB_change(this.rec_user_info);
		this.cb_close = new ownCB_close(this.rec_user_info);
	}


	public BOR_MASK_UserLeftRight_kapsel(RECORD_USER _rec_user) throws myException {
		super();
		this.rec_crosstable =null;
		this.rec_user = _rec_user;
		//this.cb_change = new ownCB_change(null);
		this.cb_close = new ownCB_close(null);
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
		private RECORD_BORDERCROSSING_USERINFO  rec_user_info = null;

		public ownCB_change(RECORD_BORDERCROSSING_USERINFO p_rec_user_info) throws myException {
			super();
			this.rec_user_info = p_rec_user_info;
			
			
			if (this.rec_user_info!=null && this.rec_user_info.is_ALLOW_CHANGE_YES()) {
				this.setSelected(true);
			}
			this.setText(new MyE2_String("Ändern").CTrans());
			this.setToolTipText(new MyE2_String("Benutzer darf Meldung ändern").CTrans());
		}
	}

	public class ownCB_close extends MyE2_CheckBox {
		private RECORD_BORDERCROSSING_USERINFO  rec_user_info = null;

		public ownCB_close(RECORD_BORDERCROSSING_USERINFO p_rec_user_info) throws myException {
			super();
			this.rec_user_info = p_rec_user_info;
			
			if (this.rec_user_info!=null && this.rec_user_info.is_ALLOW_CLOSE_YES()) {
				this.setSelected(true);
			}
			this.setText(new MyE2_String("Schließen").CTrans());
			this.setToolTipText(new MyE2_String("Benutzer darf Meldung abschliessen").CTrans());
		}
	}

	
//	public ownCB_change get_cb_change() {
//	
//		return cb_change;
//	}
//
	
	public ownCB_close get_cb_close() {
	
		return cb_close;
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.LeftRightSelect2.LR_ObjectExtender#enableExtenderComponents(boolean)
	 */
	@Override
	public void set_enableExtenderComponents(boolean bEnabled) throws myException {
		this.cb_close.set_bEnabled_For_Edit(bEnabled);
	}
	
}
