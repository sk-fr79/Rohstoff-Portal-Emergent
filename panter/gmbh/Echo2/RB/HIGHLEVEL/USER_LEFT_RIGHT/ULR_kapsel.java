package panter.gmbh.Echo2.RB.HIGHLEVEL.USER_LEFT_RIGHT;

import panter.gmbh.Echo2.components.LeftRightSelect2.LR_ObjectExtender;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class ULR_kapsel implements LR_ObjectExtender{
	
	private MyRECORD    rec_crosstable = null;
	private RECORD_USER rec_user = null;
	
	
	public ULR_kapsel(MyRECORD _rec_crosstable, RECORD_USER _rec_user) throws myException {
		super();
		this.rec_crosstable =_rec_crosstable;
		this.rec_user = _rec_user;
	}


	public ULR_kapsel(RECORD_USER _rec_user) throws myException {
		super();
		this.rec_crosstable =null;
		this.rec_user = _rec_user;
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


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.LeftRightSelect2.LR_ObjectExtender#enableComponents(boolean)
	 */
	@Override
	public void set_enableExtenderComponents(boolean bEnabled) {
		// keine GUI-Elemente vorhanden!
	}


}
