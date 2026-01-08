package panter.gmbh.basics4project.specialSelects;

import panter.gmbh.basics4project.DB_ENUMS.EAK_BRANCHE;
import panter.gmbh.basics4project.DB_ENUMS.EAK_CODE;
import panter.gmbh.basics4project.DB_ENUMS.EAK_GRUPPE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class SelAvvText extends SEL {


	/**
	 * 
	 * @param idEakCodeTerm : String, wird in Abfrage eingefuegt, kann eine ID sein oder ein Feldstring, wenn es eine subquery ist
	 * @param bAddText
	 * @throws myException
	 */
	public SelAvvText(String idEakCodeTerm, boolean bAddText) throws myException {
		super();
		

		String field = 	EAK_BRANCHE.key_branche.fn()+"||' - '||" +
						EAK_GRUPPE.key_gruppe.fn()+"||' - '||" +
						EAK_CODE.key_code.fn()+"||' '||" +
						"TRANSLATE(NVL("+EAK_CODE.gefaehrlich.fn()+",'N'),'YN','* ')";
		if (bAddText) {
			field = field +" "+EAK_CODE.code.fn();
		}
		
		this.ADDFIELD(field)	.FROM(_TAB.eak_code)
			.INNERJOIN(_TAB.eak_gruppe,EAK_CODE.id_eak_gruppe , EAK_GRUPPE.id_eak_gruppe)
			.INNERJOIN(_TAB.eak_branche,EAK_GRUPPE.id_eak_branche , EAK_BRANCHE.id_eak_branche)
			.WHERE(new TermSimple(EAK_CODE.id_eak_code.tnfn()+"="+idEakCodeTerm));

	}

}
