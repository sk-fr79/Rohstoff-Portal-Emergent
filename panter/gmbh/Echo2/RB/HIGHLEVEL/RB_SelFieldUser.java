package panter.gmbh.Echo2.RB.HIGHLEVEL;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.basics4project.ENUM_MANDANT_SESSION_STORE;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;

public class RB_SelFieldUser extends RB_selField {

	public RB_SelFieldUser(SqlStringExtended sqlEx, boolean emptyInFront) throws myException {
		super();
		
		RecList21 rl = new RecList21(_TAB.user)._fill(sqlEx);
		
		@SuppressWarnings("unchecked")
		HashMap<Long, Rec21> hmAllUses = (HashMap<Long, Rec21>)ENUM_MANDANT_SESSION_STORE.HASHMAP_ALL_PROGRAMM_USERS.getValueFromSession();
		
		String[][] 	vals = new String[rl.size()][2];
		Vector<Long> vIncluded = new Vector<Long>();
		
		int iZeile = 0;
		for (Rec21 r: rl) {
			vals[iZeile][0]=S.NN(r.getUfs(USER.name1), "<name>")+", "+S.NN(r.getUfs(USER.vorname))+" ("+S.NN(r.getUfs(USER.kuerzel),"?")+")";
			vals[iZeile++][1]=r.getFs(USER.id_user);
			
			vIncluded.add(r.getLongDbValue(USER.id_user));
		}

		//jetzt die shadow-werte
		String[][] 	valShadow = new String[hmAllUses.size()-vIncluded.size()][2];

		iZeile =0;
		for (Rec21 r: hmAllUses.values()) {
			if (!vIncluded.contains(r.getLongDbValue(USER.id_user))) {
				valShadow[iZeile][0]=S.NN(r.getUfs(USER.name1), "<name>")+", "+S.NN(r.getUfs(USER.vorname))+" ("+S.NN(r.getUfs(USER.kuerzel),"?")+")";
				valShadow[iZeile++][1]=r.getFs(USER.id_user);
			}
		}
		
		
		if (emptyInFront) {
			vals = bibARR.add_array_inFront(vals, bibARR.ARR_EMPTY_DB_VALUE_IN_FRONT);
		}
		
		
		
		this._populate(vals);
		this._populateShadow(valShadow);
	}
	
}
