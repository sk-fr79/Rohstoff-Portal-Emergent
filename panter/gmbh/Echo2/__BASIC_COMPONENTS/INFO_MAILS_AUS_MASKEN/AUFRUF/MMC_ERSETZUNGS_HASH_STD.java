package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.AUFRUF;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class MMC_ERSETZUNGS_HASH_STD extends MMC_ERSETZUNGS_HASH {
	
	private E2_BasicModuleContainer_MASK 	oMaskContainer = null;
	
	
	public MMC_ERSETZUNGS_HASH_STD(E2_BasicModuleContainer_MASK 	maskContainer) throws myException {
		super();
		oMaskContainer = maskContainer;
	}

	

	@Override
	public HashMap<String, String> get_hmMAP_KEY_ERSETZUNGS_TABELLE() throws myException {
		
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		Vector<E2_ComponentMAP>  vMap = new Vector<E2_ComponentMAP>();
		vMap.addAll(this.oMaskContainer.get_vCombinedComponentMAPs());
		
		for (int i=0; i<vMap.size();i++) {
			this.add_HashFields(vMap.get(i), hmRueck);
		}

		return hmRueck;
	}




	
	private void add_HashFields(E2_ComponentMAP  oMAP, HashMap<String, String>  hmTarget) throws myException {
		
		HashMap<String, MyE2IF__DB_Component> hmComponents = new HashMap<String, MyE2IF__DB_Component>();
		
		//DEBUG.System_println("Start");
		
		hmComponents.putAll(oMAP.get_hmRealDBComponents());
		
		for (String cHASH: hmComponents.keySet()) {
			try {
				hmTarget.put(cHASH+"@ACT_F", S.NN(oMAP.get_cActualDBValueFormated(cHASH)));
//				if (S.isFull(S.NN(oMAP.get_cActualDBValueFormated(cHASH)))) {
//					DEBUG.System_println(cHASH+"@ACT_F"+">> "+this.get(cHASH+"@ACT_F")+" <<",null);
//				}
			} catch (Exception e) {
				hmTarget.put(cHASH+"@ACT_F", "");
			}
		}

		if (!oMAP.get_bIs_Neueingabe()) {
			String cTABLENAME=oMAP.get_oSQLFieldMAP().get_cMAIN_TABLE();
			String cID = oMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			MyRECORD  oRec = new MyRECORD("SELECT * FROM "+bibE2.cTO()+"."+cTABLENAME+" WHERE ID_"+cTABLENAME.substring(3)+"="+cID);
			
			for (String cHASH: hmComponents.keySet()) {
				try {
					hmTarget.put(cHASH+"@DB_F", S.NN(oRec.get_FormatedValue(cHASH)));
					hmTarget.put(cHASH+"@DB_UF", S.NN(oRec.get_UnFormatedValue(cHASH)));
//					if (S.isFull(this.get(cHASH+"@DB_F"))) {
//						DEBUG.System_println(cHASH+"@DB_F"+" --->"+this.get(cHASH+"@DB_F"));
//						DEBUG.System_println(cHASH+"@DB_UF"+" --->"+this.get(cHASH+"@DB_UF"));
//					}
				} catch (Exception e) {
					hmTarget.put(cHASH+"@DB_F", "");
					hmTarget.put(cHASH+"@DB_UF", "");
				}
			}
		}
		
		//DEBUG.System_println("Ende");
	}



	@Override
	public String get_cKEY_MASK_STATUS() 	throws myException {
		String cTest= this.oMaskContainer.get_vCombinedComponentMAPs().get(0).get_STATUS_LAST_FILL();
		
		String cRueck = MMC_ERSETZUNGS_HASH.STATUS_HASHKEY_UNDEF;
		
		if 			(cTest.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				     cTest.equals(E2_ComponentMAP.STATUS_NEW_COPY)  ||
				     this.oMaskContainer.get_vCombinedComponentMAPs().get(0).get_bIs_Neueingabe()) {
			cRueck = MMC_ERSETZUNGS_HASH.STATUS_HASHKEY_NEW;
		} else if 	(cTest.equals(E2_ComponentMAP.STATUS_EDIT)) {
			cRueck = MMC_ERSETZUNGS_HASH.STATUS_HASHKEY_EDIT;
		} else if   (cTest.equals(E2_ComponentMAP.STATUS_VIEW)) {
			cRueck = MMC_ERSETZUNGS_HASH.STATUS_HASHKEY_VIEW;
		}
		
		return cRueck;
	}

	
	
	
}
