package panter.gmbh.Echo2.RB.HIGHLEVEL.USER_LEFT_RIGHT.V21;

import panter.gmbh.Echo2.components.LeftRightSelect2.LR_ObjectExtender;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_user;

public class ULR_kapsel_v21 implements LR_ObjectExtender{
	
	private Rec21    rec_crosstable = null;
	private Rec21_user  rec_user = null;
	
	
	
	public ULR_kapsel_v21(Rec21 _rec_crosstable, Rec21_user _rec_user) throws myException {
		super();
		rec_crosstable =_rec_crosstable;
		rec_user = _rec_user;
	}


	public ULR_kapsel_v21(Rec21_user _rec_user) throws myException {
		super();
		rec_crosstable =null;
		rec_user = _rec_user;
	}
	
	
	public Rec21 get_rec_crosstable() {
		return rec_crosstable;
	}
	
	public Rec21_user get_rec_user() throws myException {
		return rec_user; 
	}

	public String get_text4_checkBox() throws myException {
		return  rec_user.get_ufs_dbVal(USER.name1, "<name1>") +	", " +
				rec_user.get_ufs_dbVal(USER.vorname,"<vorname>") + " (" + 
				rec_user.get_ufs_dbVal(USER.kuerzel,"??") + ")";
				
	}

	public String get_saveKEY() throws myException {
		return rec_user.get_ufs_dbVal(USER.name, "--");
	}

	
	public void set_rec_crosstable(Rec21 p_rec_crosstable) {
		rec_crosstable = p_rec_crosstable;
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.LeftRightSelect2.LR_ObjectExtender#enableComponents(boolean)
	 */
	@Override
	public void set_enableExtenderComponents(boolean bEnabled) {
		// keine GUI-Elemente vorhanden!
	}


}
