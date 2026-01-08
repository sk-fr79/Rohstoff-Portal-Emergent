/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugFuhre
 * @author manfred
 * @date 29.06.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugFuhre;

import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.basics4project.DB_ENUMS.ABZUGSGRUND;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.Nvl;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author manfred
 * @date 29.06.2020
 *
 */
public class WK_RB_CHILD_MGE_ABZ_SelAbzuege extends RB_selField {

	/**
	 * @author manfred
	 * @throws myException 
	 * @date 29.06.2020
	 *
	 */
	public WK_RB_CHILD_MGE_ABZ_SelAbzuege() throws myException {
		super();
//		this._fo_bold();
		this._populate(getDDValues(true));
		this._populateShadow(getDDValues(false));
	}

	/**
	 * 
	 * @author manfred
	 * @date 29.06.2020
	 *
	 * @param aktive  true: die aktuellen Werte, false : shadow-Werte
	 * @return
	 * @throws myException
	 */
	private String[][] getDDValues(boolean aktive) throws myException{
		
		SEL sel = new SEL("*")
				.FROM(ABZUGSGRUND._tab())
				.WHERE(new Nvl(ABZUGSGRUND.anzeige_wiegekarte.t(), "'N'"),"=",new TermSimple("?"))
				.ORDERUP(ABZUGSGRUND.abzugsgrund);
	
		SqlStringExtended  sqlExt = new SqlStringExtended(sel.s())
					._addParameters(new VEK<ParamDataObject>()
					._a(new Param_String("",aktive ? "Y" : "N")) );
		
			

		RecList22 rl = new RecList22(ABZUGSGRUND._tab())._fill(sqlExt);
		
		String aRet[][] = new String[rl.size()+1][2];
		aRet[0][0] = "-";
		aRet[0][1] = "";
		
		for (int i = 0; i< rl.size(); i++){
			Rec22 rec = rl.get(i);
			aRet[i+1][0] = rec.getFs(ABZUGSGRUND.abzugsgrund);
			aRet[i+1][1] = rec.getFs(ABZUGSGRUND.id_abzugsgrund);
		}
		
		return aRet;
	}
	
}
