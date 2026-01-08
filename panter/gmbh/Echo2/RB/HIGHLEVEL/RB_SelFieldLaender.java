package panter.gmbh.Echo2.RB.HIGHLEVEL;

import java.util.Vector;

import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class RB_SelFieldLaender extends RB_selField {

	public RB_SelFieldLaender() throws myException {
		this(true);
	}
	
	public RB_SelFieldLaender(boolean emptyInFront) throws myException {
		super();
		
		RecList22 rl = new RecList22(_TAB.land)._fillWithAllFromMandant(new VEK<IF_Field>()._a(LAND.laendername));
		
		String[][] 	vals = new String[rl.size()][2];
		Vector<Long> vIncluded = new Vector<Long>();
		
		int iZeile = 0;
		for (Rec22 r: rl) {
			vals[iZeile][0]=S.NN(r.getUfs(LAND.laendername), "<name>")+" ("+S.NN(r.getUfs(LAND.laendercode),"?")+")";
			vals[iZeile++][1]=r.getFs(LAND.id_land);
			
			vIncluded.add(r.getLongDbValue(LAND.id_land));
		}

		if (emptyInFront) {
			vals = bibARR.add_array_inFront(vals, bibARR.ARR_EMPTY_DB_VALUE_IN_FRONT);
		}
		
		this._populate(vals);
	}
	
}
