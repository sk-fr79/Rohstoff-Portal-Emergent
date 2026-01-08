
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS.AH7_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class Rec21_AH7_Profil extends Rec21 {

	/**
	 * @param p_tab
	 * @throws myException
	 */
	public Rec21_AH7_Profil() throws myException {
		super(_TAB.ah7_profil);
	}
	
	/**
	 * @param p_tab
	 * @throws myException
	 */
	public Rec21_AH7_Profil(Rec21 rec) throws myException {
		super(rec);
		
		if (!rec.get_TABLENAME().equals(_TAB.ah7_profil.n())) {
			throw new myException("AH7_Rec21Profile: false table");
		}
	}
	
	
	
	public boolean isBlock_1_vollstaendig() throws myException {
		VEK<String> v = new VEK<String>();
					v	._a(this.get_ufs_dbVal(AH7_PROFIL.verbr_veranlasser_1))
						._a(this.get_ufs_dbVal(AH7_PROFIL.import_empfaenger_1))
						._a(this.get_ufs_dbVal(AH7_PROFIL.abfallerzeuger_1))
						._a(this.get_ufs_dbVal(AH7_PROFIL.verwertungsanlage_1));

		int i=0;
		for (String s: v) {
			if (S.isFull(s)) {
				i++;
			}
		}
		return (i==4);
	}
	
	
	public boolean isBlock_2_vollstaendig() throws myException {
		VEK<String> v = new VEK<String>();
					v	._a(this.get_ufs_dbVal(AH7_PROFIL.verbr_veranlasser_2))
						._a(this.get_ufs_dbVal(AH7_PROFIL.import_empfaenger_2))
						._a(this.get_ufs_dbVal(AH7_PROFIL.abfallerzeuger_2))
						._a(this.get_ufs_dbVal(AH7_PROFIL.verwertungsanlage_2));

		int i=0;
		for (String s: v) {
			if (S.isFull(s)) {
				i++;
			}
		}
		return (i==4);
	}
	
	
	public boolean isBlock_3_vollstaendig() throws myException {
		VEK<String> v = new VEK<String>();
					v	._a(this.get_ufs_dbVal(AH7_PROFIL.verbr_veranlasser_3))
						._a(this.get_ufs_dbVal(AH7_PROFIL.import_empfaenger_3))
						._a(this.get_ufs_dbVal(AH7_PROFIL.abfallerzeuger_3))
						._a(this.get_ufs_dbVal(AH7_PROFIL.verwertungsanlage_3));

		int i=0;
		for (String s: v) {
			if (S.isFull(s)) {
				i++;
			}
		}
		return (i==4);
	}
	
	
}
