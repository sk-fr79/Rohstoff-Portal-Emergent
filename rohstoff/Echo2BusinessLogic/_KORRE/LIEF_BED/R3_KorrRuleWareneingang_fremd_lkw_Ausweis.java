package rohstoff.Echo2BusinessLogic._KORRE.LIEF_BED;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._4_ALL.PruefeEigeneLKW;

/*
 * Regel: Wareneingang mit fremdem LKW/PKW und Ausweis: DDP
 */
public class R3_KorrRuleWareneingang_fremd_lkw_Ausweis extends XX_KorrRule {

	private PruefeEigeneLKW  oLKW_Checkker = null;
	
	
	public R3_KorrRuleWareneingang_fremd_lkw_Ausweis() throws myException {
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
					RECORD_ADRESSE  recAdresseStart = new RECORD_ADRESSE(cID_ADRESSE_START);
					if (S.isFull(recAdresseStart.get_AUSWEIS_NUMMER_cUF_NN(""))) {
						return "DDP";
					}
				}
			}
		} 
		return null;
	}
	

	
	
	
	@Override
	public String get_UebersichtsText() {
		return "WE Fremd-Kennzeichen / Personalausweis: DDP";
	}

	
	@Override
	public String get_Kennung4ProtokollTabelle() {
		return XX_KorrRule.KORR_RULE_WE_FREMD_LKW_AUSWEIS_DDP;
	}

	@Override
	public String get_Info4ProtokollTabelle() {
		return this.get_UebersichtsText();
	}

	
}
