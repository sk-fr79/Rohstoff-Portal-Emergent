package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.exceptions.myException;

public class AH7_LIST_Selector_WA_WE_LL_ST extends E2_ListSelektorMultiselektionStatusFeld_STD {

	static int[] CheckBoxSelektorSpalten = {40,55};
	public AH7_LIST_Selector_WA_WE_LL_ST() throws myException {
		super(CheckBoxSelektorSpalten, false, null, null);
	
		String id_adresse_mandant = bibALL.get_ID_ADRESS_MANDANT();
		
		vgl startIsMandant = new vgl(AH7_STEUERDATEI.id_adresse_jur_start, id_adresse_mandant);
		vgl startIsNotMandant = new vgl(AH7_STEUERDATEI.id_adresse_jur_start,COMP.NOT_EQ, id_adresse_mandant);
		vgl zielIsMandant = new vgl(AH7_STEUERDATEI.id_adresse_jur_ziel, id_adresse_mandant);
		vgl zielIsNotMandant = new vgl(AH7_STEUERDATEI.id_adresse_jur_ziel,COMP.NOT_EQ, id_adresse_mandant);
		
		this.ADD_STATUS_TO_Selector(true,	startIsMandant.s()+" AND "+zielIsNotMandant.s(),	new MyE2_String("Warenausgang"),   	new MyE2_String("Zeige Warenausgänge an"));		
		this.ADD_STATUS_TO_Selector(true,	startIsNotMandant.s()+" AND "+zielIsMandant.s(),	new MyE2_String("Wareneingang"),   	new MyE2_String("Zeige Wareneingänge an"));		
		this.ADD_STATUS_TO_Selector(true,	startIsMandant.s()+" AND "+zielIsMandant.s(),		new MyE2_String("Lager-Lager"),   	new MyE2_String("Zeige Relationen an, die vom eigenen Lager zum eigenen Lager gehen"));		
		this.ADD_STATUS_TO_Selector(true,	startIsNotMandant.s()+" AND "+zielIsNotMandant.s(),	new MyE2_String("Strecken"),   		new MyE2_String("Zeige Strecken-Relationen an"));		
	}

}
