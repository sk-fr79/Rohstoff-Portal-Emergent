package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC;

import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_ABZUG_GEB;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.TermLMR;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class RecList_WK_Gebinde extends RecList22 {

	public RecList_WK_Gebinde() {
		super(_TAB.wiegekarte_abzug_geb);
		
	}
	
	public RecList_WK_Gebinde(String idWiegekarte) throws myException {
		this();
		this._fill(getSqlExt_Default(idWiegekarte));
	}

	
	public RecList_WK_Gebinde( SqlStringExtended sql ) throws myException{
		super (_TAB.wiegekarte_abzug_geb);
		this._fill(sql);
	}
	
	
	public static SqlStringExtended getSqlExt_Default(String idWiegekarte) throws myException{
		
		VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
		
		SEL sel = new SEL("*")
				.FROM(_TAB.wiegekarte_abzug_geb)
				.WHERE(new TermSimple(WIEGEKARTE_ABZUG_GEB.id_mandant.fn() + " = ? "));
		vecParam._a(new Param_Long(Long.parseLong(bibALL.get_ID_MANDANT())));
		
		// aktiv-Schalter
		And and_wk = new And( new TermLMR (new TermSimple(WIEGEKARTE_ABZUG_GEB.id_wiegekarte.fn()) ,_TermCONST.COMP.EQ.s()," ? " ));
		vecParam._a(new Param_Long(Long.parseLong(idWiegekarte) ) );
		sel.getAnd().add(and_wk);
		
		// order by
		sel.ORDERUP(WIEGEKARTE_ABZUG_GEB.id_wiegekarte_gebinde);

		
		SqlStringExtended  sql = new SqlStringExtended(sel.s())._addParameters(vecParam);
		
		return sql;
	}
}
