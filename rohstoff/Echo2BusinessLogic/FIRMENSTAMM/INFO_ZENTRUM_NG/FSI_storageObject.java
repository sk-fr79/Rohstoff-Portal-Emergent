package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG;

import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;

public class FSI_storageObject {
	
	private  	boolean isEk;
	
	private  	String id;
	private 	String id_ort;
	
	/*
	"DEF_QUELLE_ZIEL", "ID_VPOS_TPA_FUHRE", "ID_VPOS_TPA_FUHRE_ORT", "ID_ADRESSE_STATION", 	"ID_ARTIKEL",
				"PLAN",	"MENGE_BRUTTO","ABZUG_MENGE", "EPREIS", "EPREIS_RESULT", "ANR1_2", "ART_BEZ1", "BUCHUNGSNUMMER",
		"DATUM"
	*/
	
	public FSI_storageObject( String oId, String oId_ort, boolean isEk) {
		this.id = oId;
		this.id_ort = oId_ort;
		this.isEk = isEk;
	}

	public boolean isEk() {
		return isEk;
	}

	public boolean isOrt() {
		return S.isFull(bibALL.convertID2UnformattedID(id_ort)) && !(bibALL.convertID2UnformattedID(id_ort).equals("0"));
	}

	public String getId() {
		return bibALL.convertID2UnformattedID(id);
	}

	public String getIdOrt() {
		return  bibALL.convertID2UnformattedID(id_ort);
	}
}