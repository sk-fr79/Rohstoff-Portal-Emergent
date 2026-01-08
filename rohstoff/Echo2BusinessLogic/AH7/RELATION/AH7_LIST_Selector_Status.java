package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.indep.dataTools.IF_Field;
import rohstoff.Echo2BusinessLogic.AH7.AH7__ENUM_STATUSRELATION;

public class AH7_LIST_Selector_Status extends E2_ListSelektorMultiselektionStatusFeld_STD {

	static int[] CheckBoxSelektorSpalten = {40,55};
	public AH7_LIST_Selector_Status() {
		super(CheckBoxSelektorSpalten, false, null, null);
	
		IF_Field  feld = AH7_STEUERDATEI.status_relation;
		this.ADD_STATUS_TO_Selector(true,	("(NVL(#FELD#,'-')='"+AH7__ENUM_STATUSRELATION.UNDEF.db_val()+"')").replace("#FELD#",feld.tnfn()),		new MyE2_String("Undefinierte Relationen"),   	new MyE2_String("Zeige Relationen an, die noch nicht definiert sind"));		
		this.ADD_STATUS_TO_Selector(true,	("(NVL(#FELD#,'-')='"+AH7__ENUM_STATUSRELATION.INACTIVE.db_val()+"')").replace("#FELD#",feld.tnfn()),	new MyE2_String("Inaktive Relationen"),   		new MyE2_String("Zeige Relationen an, definiert, aber nocht nicht freigegeben sind"));
		this.ADD_STATUS_TO_Selector(true,	("(NVL(#FELD#,'-')='"+AH7__ENUM_STATUSRELATION.ACTIVE.db_val()+"')").replace("#FELD#",feld.tnfn()),		new MyE2_String("Aktive Relationen"),   		new MyE2_String("Zeige Relationen an, die aktiv sind"));		
	}

}
