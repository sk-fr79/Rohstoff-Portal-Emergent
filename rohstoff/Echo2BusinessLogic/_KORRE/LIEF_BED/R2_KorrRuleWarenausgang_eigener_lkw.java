package rohstoff.Echo2BusinessLogic._KORRE.LIEF_BED;

import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._4_ALL.PruefeEigeneLKW;


/*
 * Regel: Warenausgang mit eigenem Fuhrpark: CPT
 */
public class R2_KorrRuleWarenausgang_eigener_lkw extends XX_KorrRule {

	private PruefeEigeneLKW  oLKW_Checkker = null;
	
	
	public R2_KorrRuleWarenausgang_eigener_lkw() throws myException {
		super();
		this.oLKW_Checkker =  new  PruefeEigeneLKW();
	}

	@Override
	public boolean ist_Fuer_WE_Seite() {
		return false;
	}
	


	@Override
	public String __suchePassende_LiefBedingungen(String cID_ADRESSE_START, String cID_ADRESSE_ZIEL, String cLKWNR, String cID_VPOS_TPA, KorrCounter oCount) {
		
		if (S.isFull(cLKWNR)) {
			if (this.bPruefe_RegelIstAnwendbar(cID_ADRESSE_START, cID_ADRESSE_ZIEL)) {
				if (oLKW_Checkker.is_Eigener_LKW(cLKWNR)) {
					return "CPT";
				}
			}
		} 
		return null;
	}
	
	
	
	
	@Override
	public String get_UebersichtsText() {
		return "WA eigener Fuhrpark: CPT";
	}

	@Override
	public String get_Kennung4ProtokollTabelle() {
		return XX_KorrRule.KORR_RULE_WA_EIGENER_LKW_CPT;
	}

	@Override
	public String get_Info4ProtokollTabelle() {
		return this.get_UebersichtsText();
	}

}
