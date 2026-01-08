package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.indep.dataTools.IF_Field;

public class AH7_LIST_Selector_SperreAnAus extends E2_ListSelektorMultiselektionStatusFeld_STD {

	static int[] CheckBoxSelektorSpalten = {40,55};
	public AH7_LIST_Selector_SperreAnAus() {
		super(CheckBoxSelektorSpalten, false, null, null);
	
		IF_Field  feld = AH7_STEUERDATEI.locked;
		this.ADD_STATUS_TO_Selector(true,	"(NVL(#FELD#,'N')='Y')".replace("#FELD#",feld.tnfn()),	new MyE2_String("Gesperrte Relationen"),   		
						new MyE2_String("Zeige Relationen an, die gesperrt sind"));		
		this.ADD_STATUS_TO_Selector(true,	"(NVL(#FELD#,'N')='N')".replace("#FELD#",feld.tnfn()),	new MyE2_String("Nicht gesperrte Relationen"),   
						new MyE2_String("Zeige Relationen an, die NICHT gesperrt sind"));
		
		
	}

}
