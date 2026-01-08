package rohstoff.Echo2BusinessLogic._KORRE.LIEF_BED;

import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._4_ALL.PruefeEigeneLKW;

/*
 * Regel: Wareneingang mit fremdem LKW und Transportauftrag: FCA
 */
public class R4_KorrRuleWareneingang_fremd_lkw_TPA extends XX_KorrRule {

	private PruefeEigeneLKW  oLKW_Checkker = null;
	
	
	public R4_KorrRuleWareneingang_fremd_lkw_TPA() throws myException {
		super();
		this.oLKW_Checkker =  new  PruefeEigeneLKW();
	}

	@Override
	public boolean ist_Fuer_WE_Seite() {
		return true;
	}
	


	@Override
	public String __suchePassende_LiefBedingungen(String cID_ADRESSE_START, String cID_ADRESSE_ZIEL, String cLKWNR, String cID_VPOS_TPA, KorrCounter oCount) throws myException {
		
		if (S.isFull(cLKWNR)) {
			if (this.bPruefe_RegelIstAnwendbar(cID_ADRESSE_START, cID_ADRESSE_ZIEL)) {
				if (oLKW_Checkker.is_Fremd_LKW(cLKWNR)) {
					if (S.isFull(cID_VPOS_TPA)) {
						return "FCA";
					}
				}
			}
		} 
		return null;
	}

	@Override
	public String get_UebersichtsText() {
		return "WE Fremd-Kennzeichen / TPA: FCA";
	}

	@Override
	public String get_Kennung4ProtokollTabelle() {
		return XX_KorrRule.KORR_RULE_WE_FREMD_LKW_TPA_FCA;
	}

	@Override
	public String get_Info4ProtokollTabelle() {
		return this.get_UebersichtsText();
	}

}
