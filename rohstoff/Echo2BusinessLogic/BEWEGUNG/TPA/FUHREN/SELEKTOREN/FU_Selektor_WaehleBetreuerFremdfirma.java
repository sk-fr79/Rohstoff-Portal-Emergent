package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELECTOR_BENUTZER_MIT_Beschreibung_Multi;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class FU_Selektor_WaehleBetreuerFremdfirma extends SELECTOR_BENUTZER_MIT_Beschreibung_Multi {

	private static String[] SondereintragKundeOhneBetreuer = {"<kein Betreuer>","-1"};
	
	public FU_Selektor_WaehleBetreuerFremdfirma() throws myException {
		super(100, 100, new MyE2_String("Händler:"), 
				" JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE IN ("+
						" SELECT "+
						"    FU.ID_VPOS_TPA_FUHRE "+
						" FROM "+
						"    JT_VPOS_TPA_FUHRE FU "+
						" LEFT OUTER JOIN JT_ADRESSE LA ON (LA.ID_ADRESSE=FU.ID_ADRESSE_START) "+
						" LEFT OUTER JOIN JT_ADRESSE RA ON (RA.ID_ADRESSE=FU.ID_ADRESSE_ZIEL) "+
						" LEFT OUTER JOIN JT_VPOS_TPA_FUHRE_ORT FO ON (FO.ID_VPOS_TPA_FUHRE=FU.ID_VPOS_TPA_FUHRE) "+
						" LEFT OUTER JOIN JT_ADRESSE AO ON (FO.ID_ADRESSE=AO.ID_ADRESSE) "+
						" WHERE "+
						"    (NVL(LA.ID_ADRESSE,-1)>0 AND NVL(LA.ID_ADRESSE,-1)<>"+bibALL.get_ID_ADRESS_MANDANT()+" AND  (NVL(LA.ID_USER,-1)=#WERT# OR NVL(LA.ID_USER_ERSATZ,-1)=#WERT#)) "+
						"    OR "+
						"    (NVL(RA.ID_ADRESSE,-1)>0 AND NVL(RA.ID_ADRESSE,-1)<>"+bibALL.get_ID_ADRESS_MANDANT()+" AND  (NVL(RA.ID_USER,-1)=#WERT# OR NVL(RA.ID_USER_ERSATZ,-1)=#WERT#)) "+
						"    OR "+
						"    (NVL(AO.ID_ADRESSE,-1)>0 AND NVL(AO.ID_ADRESSE,-1)<>"+bibALL.get_ID_ADRESS_MANDANT()+" AND  (NVL(AO.ID_USER,-1)=#WERT# OR NVL(AO.ID_USER_ERSATZ,-1)=#WERT#)) "+
						")"   	
			 , FU_Selektor_WaehleBetreuerFremdfirma.SondereintragKundeOhneBetreuer);
	}

//	public String get_WhereBlock() throws myException
//	{
//		String cWhere = super.get_WhereBlock();
//		DEBUG.System_println("<"+cWhere+">", null);
//		return cWhere;	
//	}
}
