package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import java.util.HashMap;

import nextapp.echo2.app.Extent;

public class BSKC__hm_ColExtend extends HashMap<String,Extent>
{

	public BSKC__hm_ColExtend()
	{
		super();
		//die Spaltenbreiten festlegen, damit die ausklapplisten auf die gleiche breite kommen, wie die hauptliste
		this.put(BSKC__CONST.HASH_LABEL_LOCKED,new Extent(30));
		this.put(BSKC__CONST.HASH_BUTTON_ZUORDNUNG,new Extent(30));
		
		this.put("GUELTIGKEITSZEITRAUM",new Extent(130));
		this.put("K_NAME1",new Extent(200));
		this.put("K_ORT",new Extent(200));
		this.put("ANR1_ANR2",new Extent(100));
		this.put("ARTBEZ1",new Extent(200));
		this.put("ANZAHL",new Extent(100));
		
		this.put(BSKC__CONST.HASH_MENGE_SUMME_GEGENSEITE,new Extent(70));
		this.put(BSKC__CONST.HASH_MENGE_SUMME_LAGER,new Extent(70));
		
		this.put(BSKC__CONST.FILTERCOLUMN.HASH_R_MENGE_SUMME_ZUORDNUNG.key(),new Extent(150));
		this.put(BSKC__CONST.FILTERCOLUMN.HASH_R_MENGE_SUMME_DIFFERENZ.key(),new Extent(150));
		this.put(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_EK_VK_FUHREN_PLANMENGE.key(),new Extent(150));
		this.put(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_RECH_GUT_POS.key(),new Extent(150));
		
		this.put("ID_VPOS_KON",new Extent(100));
		this.put("K_ID_VKOPF_KON",new Extent(100));
		this.put("AD_ID_ADRESSE",new Extent(100));

		
	}
	
	

}
