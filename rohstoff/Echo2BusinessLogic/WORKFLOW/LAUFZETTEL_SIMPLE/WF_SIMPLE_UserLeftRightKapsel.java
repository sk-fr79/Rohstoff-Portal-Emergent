/**
 * rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE
 * @author manfred
 * @date 03.04.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;

import panter.gmbh.Echo2.RB.HIGHLEVEL.USER_LEFT_RIGHT.V21.ULR_kapsel_v21;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_user;

/**
 * @author manfred
 * @date 03.04.2019
 *
 */
public class WF_SIMPLE_UserLeftRightKapsel extends ULR_kapsel_v21 {

	/**
	 * @author manfred
	 * @date 03.04.2019
	 *
	 * @param _rec_crosstable
	 * @param _rec_user
	 * @throws myException
	 */
	public WF_SIMPLE_UserLeftRightKapsel(Rec21 _rec_crosstable, Rec21_user _rec_user) throws myException {
		super(_rec_crosstable, _rec_user);
		
	}

	/**
	 * @author manfred
	 * @date 03.04.2019
	 *
	 * @param _rec_user
	 * @throws myException
	 */
	public WF_SIMPLE_UserLeftRightKapsel(Rec21_user _rec_user) throws myException {
		super(_rec_user);
		
	}
	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.HIGHLEVEL.USER_LEFT_RIGHT.V21.ULR_kapsel_v21#get_text4_checkBox()
	 */
	@Override
	public String get_text4_checkBox() throws myException {
		Rec21_user r =this.get_rec_user();
		
		return  (S.isFull(r.get_ufs_dbVal(USER.name1, "") ) ? r.get_ufs_dbVal(USER.name1, "")   : "" )  +
				(S.isFull(r.get_ufs_dbVal(USER.vorname,"")) ? ", " + r.get_ufs_dbVal(USER.vorname,"") : "" ) 
//				+ " (" + r.get_ufs_dbVal(USER.kuerzel,"?") + ")"
				;
	}

}
