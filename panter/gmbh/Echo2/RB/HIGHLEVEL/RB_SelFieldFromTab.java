package panter.gmbh.Echo2.RB.HIGHLEVEL;

import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class RB_SelFieldFromTab extends RB_selField {


	public RB_SelFieldFromTab(_TAB tab,  VEK<IF_Field> anzeigen,  VEK<IF_Field> sortField ) throws myException {
		super();
		
		RecList22 rl = new RecList22(tab)._fillWithAllFromMandant(new VEK<IF_Field>()._a(sortField));
		
		String[][] 	vals = new String[rl.size()][2];
		
		int iZeile = 0;
		for (Rec22 r: rl) {
			String anzeige = r.get_ufs_kette(" ", anzeigen.getArray());
			
			vals[iZeile][0]=S.NN(anzeige,"-");
			vals[iZeile++][1]=r.getFs(tab.key());
		}

		//immer ein leeres in front
		String[][]  frontEmpty = {{"-",""}};
		vals = bibARR.add_array_inFront2(vals, frontEmpty);
		
		this._populate(vals);
	}
	
}
