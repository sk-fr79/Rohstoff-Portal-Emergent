/**
 * panter.gmbh.Echo2.components.DB
 * @author martin
 * @date 17.12.2018
 * 
 */
package panter.gmbh.Echo2.components.DB;

import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__IndirectHELPER;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * @author martin
 * @date 17.12.2018
 * allgemeine klasse um einen JD_USER - eintrag in der liste anzuzeigen.
 * uebergeben wird ein String mit der ID
 */
public class E2_DBLabelUserInList extends E2_DBActiveLabelForLists {

	/**
	 * @author martin
	 * @date 17.12.2018
	 *
	 * @param osqlField
	 * @throws myException
	 */
	public E2_DBLabelUserInList(SQLField osqlField) throws myException {
		super(osqlField);
	}

	@Override
	public void set_cActualMaskValue(String s_id) throws myException {
		
		Long id = null;
		
		if (S.isFull(s_id)) {
			MyLong l = new MyLong(s_id); 
			if (l.isOK()) {
				id=l.getLong();
			}
		}
		
		if (id!=null) {
			Rec21 user = new Rec21(_TAB.user)._fill_id(id);
			if (user.is_ExistingRecord()) {
				this.setText(user.get_ufs_kette(" ", USER.vorname,USER.name1)+" ("+user.getUfs(USER.kuerzel,"?")+")");
				
			} else {
				this.setText("??");
			}
		} else {
			this.setText("-");
		}
	}

	
	
	@Override
	public Object get_Copy(Object ob) throws myExceptionCopy {
		try {
			E2_DBLabelUserInList cop = new E2_DBLabelUserInList(this.EXT_DB().get_oSQLField());
			cop.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(cop));
			return cop;
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}

}
