package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.indep.exceptions.myException;

public class FU__MaskSettingsAfterInput__Plausibilitaet
{

	public FU__MaskSettingsAfterInput__Plausibilitaet(MyE2IF__Component oComponentFromMask) throws myException {
		
		new FU__MaskSettingsAfterInput_AVV_Checkbox(oComponentFromMask);
		new FU__MaskSettingsAfterInput_Pruefe_Kontrakt_Adresse_Sorte(oComponentFromMask);
		new FU__Mask_ZEIGE_GEFAHRGUT_Label(oComponentFromMask);
		new FU__Mask_ZEIGE_SORTENBEZ_Label(oComponentFromMask);
		
		//20170705: neuer suchalgorithmus wegen beruecksichtigung der ausgangs-artikel
		if (ENUM_MANDANT_DECISION.AUSGANGSAVV_IN_FUHRE_VERWENDEN.is_YES()) {
			new FU__decide_correct_avv_code_transport_fuhre(oComponentFromMask).set_found_code();
			
			//2018-01-30: bug: hier muss nochmal der EU-Blatt-Druck geprueft werden, da dieser abhaengig ist vom AVV-Code
			new FU__MaskSettingsAfterInput_AVV_Checkbox(oComponentFromMask);
		}
	}

	
}
