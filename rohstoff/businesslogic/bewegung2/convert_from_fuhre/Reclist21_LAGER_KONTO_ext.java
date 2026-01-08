package rohstoff.businesslogic.bewegung2.convert_from_fuhre;

import java.util.Vector;

import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_KONTO;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAGER_KONTO;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.In;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.exceptions.myException;


public class Reclist21_LAGER_KONTO_ext  extends RecList21{

	public Reclist21_LAGER_KONTO_ext() throws myException{
		super (_TAB.lager_konto);
	}
	
	
	
//	RECLIST_LAGER_KONTO listKto = new RECLIST_LAGER_KONTO("NVL(STORNO,'N') = 'N' AND MENGE < 0 AND ID_VPOS_TPA_FUHRE = " + sIDFuhre , "");

	public Reclist21_LAGER_KONTO_ext(String idFuhre, boolean WA, boolean WE) throws myException{
		this();
		
		if (idFuhre == null) {
			throw new myException("Lagerliste braucht zugehörige Fuhreninformation. Fuhre ist null"); 		
		};
		
		Long lIdFuhre = Long.parseLong(idFuhre);
		
		vgl v1 = null;
		if (WA && WE) {
		
		} else if (WA) {
			v1 = new vgl(LAGER_KONTO.menge,COMP.LT,"0");
		} else {
			v1 = new vgl(LAGER_KONTO.menge,COMP.GT,"0");;
		}
		
		SEL s = new SEL("*")
				.FROM(_TAB.lager_konto)
				.WHERE(new vgl(LAGER_KONTO.storno, COMP.EQ, "N"))
				.AND (new vglParam(LAGER_KONTO.id_vpos_tpa_fuhre));
		
		if (v1 != null) {
			s.AND(v1);
		}
				
		SqlStringExtended sqlExt = new SqlStringExtended(s.s());
		sqlExt.getValuesList().add(new Param_Long(lIdFuhre) );
		
		this._fill(sqlExt);	
		
	}
	
	
	
	
}
