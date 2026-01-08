/**
 * panter.gmbh.Echo2.__BASIC_MODULS.REMINDER
 * @author manfred
 * @date 01.04.2016
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER;

/**
 * @author manfred
 * @date 01.04.2016
 *
 */
public class REMINDER_USER_Entry_Data {
	String 		_id_user = null;
	boolean  	_allow_edit = false;
	boolean 	_allow_close = false;
	boolean     _sofortanzeige = true;
	boolean     _send_mail     = true;

	
	public boolean is_send_mail() {
		return _send_mail;
	}
	public REMINDER_USER_Entry_Data set_send_mail(boolean _send_mail) {
		this._send_mail = _send_mail;
		return this;
	}

	public boolean is_sofortanzeige() {
		return _sofortanzeige;
	}
	public REMINDER_USER_Entry_Data set_sofortanzeige(boolean _sofortanzeige) {
		this._sofortanzeige = _sofortanzeige;
		return this;
	}
	
	
	public String get_id_user() {
		return _id_user;
	}
	public REMINDER_USER_Entry_Data set_id_user(String _id_user) {
		this._id_user = _id_user;
		return this;
	}
	

	public boolean is_allow_edit() {
		return _allow_edit;
	}
	public REMINDER_USER_Entry_Data set_allow_edit(boolean _can_edit) {
		this._allow_edit = _can_edit;
		return this;
	}
	
	public boolean is_allow_close() {
		return _allow_close;
	}
	public REMINDER_USER_Entry_Data set_allow_close(boolean _can_close) {
		this._allow_close = _can_close;
		return this;
	} 

	

}
