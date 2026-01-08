package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import java.util.HashMap;

import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.AUFRUF.MMC_ERSETZUNGS_HASH;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WIEGEKARTE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class MMC_ERSETZUNGS_HASH_WIEGEKARTE extends MMC_ERSETZUNGS_HASH {
	
	private E2_BasicModuleContainer_MASK 	oMaskContainer = null;
	WK_WiegekartenHandler  m_oWKHandler = null;
	
	public MMC_ERSETZUNGS_HASH_WIEGEKARTE(WK_WiegekartenHandler  oWKHandler) throws myException {
		super();
		m_oWKHandler = oWKHandler;
	}
	

	@Override
	public HashMap<String, String> get_hmMAP_KEY_ERSETZUNGS_TABELLE() throws myException {
		
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		this.add_HashFields(m_oWKHandler, hmRueck);
		return hmRueck;
	}




	
	private void add_HashFields(WK_WiegekartenHandler  oWKHandler, HashMap<String, String>  hmTarget) throws myException {
		
		if (oWKHandler == null) return;
		
		if ( !bibALL.isEmpty(oWKHandler.get_ID_Wiegekarte()) && oWKHandler.get_REC_Wiegekarte() != null ){
			RECORD_WIEGEKARTE oRec = oWKHandler.get_REC_Wiegekarte();
			
			for (String cHASH: oRec.keySet() ) {
				try {
					hmTarget.put(cHASH+"@DB_F", S.NN(oRec.get_FormatedValue(cHASH)));
					hmTarget.put(cHASH+"@DB_UF", S.NN(oRec.get_UnFormatedValue(cHASH)));
					if (S.isFull(hmTarget.get(cHASH+"@DB_F"))) {
						DEBUG.System_println(cHASH+"@DB_F"+" --->"+hmTarget.get(cHASH+"@DB_F"),DEBUG.DEBUG_FLAG_DIVERS1);
						DEBUG.System_println(cHASH+"@DB_UF"+" --->"+hmTarget.get(cHASH+"@DB_UF"),DEBUG.DEBUG_FLAG_DIVERS1);
					}
				} catch (Exception e) {
					hmTarget.put(cHASH+"@DB_F", "");
					hmTarget.put(cHASH+"@DB_UF", "");
				}
			}
		}
		
		DEBUG.System_println("Ende",DEBUG.DEBUG_FLAG_DIVERS1);
	}




	
	
	@Override
	public String get_cKEY_MASK_STATUS() 	throws myException {
		String cRueck = MMC_ERSETZUNGS_HASH.STATUS_HASHKEY_UNDEF;

		if(     m_oWKHandler.get_REC_Wiegekarte() != null &&
				m_oWKHandler.get_REC_Waegung1() != null &&
				m_oWKHandler.get_REC_Waegung2() != null ){
			cRueck = MMC_ERSETZUNGS_HASH.STATUS_HASHKEY_VIEW;
		} 
		else {
			cRueck = MMC_ERSETZUNGS_HASH.STATUS_HASHKEY_UNDEF;
		}
			
		
//		
//		String cTest= this.oMaskContainer.get_vCombinedComponentMAPs().get(0).get_STATUS_LAST_FILL();
//		
//		
//		if 			(cTest.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
//				     cTest.equals(E2_ComponentMAP.STATUS_NEW_COPY)  ||
//				     this.oMaskContainer.get_vCombinedComponentMAPs().get(0).get_bIs_Neueingabe()) {
//			cRueck = MMC_ERSETZUNGS_HASH.STATUS_HASHKEY_NEW;
//		} else if 	(cTest.equals(E2_ComponentMAP.STATUS_EDIT)) {
//			cRueck = MMC_ERSETZUNGS_HASH.STATUS_HASHKEY_EDIT;
//		} else if   (cTest.equals(E2_ComponentMAP.STATUS_VIEW)) {
//			cRueck = MMC_ERSETZUNGS_HASH.STATUS_HASHKEY_VIEW;
//		}
//		
		return cRueck;
	}

	
	
	
}
