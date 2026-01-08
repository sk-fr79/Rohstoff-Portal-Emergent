package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_ComponentMAP;


public class FU_VALID_HELPER_FREMDWAREN_FREMDWAREN_LAGER {

	private Long   		lID_ADRESSE_START = 		null;
	private Long   		lID_ADRESSE_ZIEL = 			null;
	
	private Long   		lID_ADRESSE_LAGER_START = 	null;
	private Long   		lID_ADRESSE_LAGER_ZIEL = 	null;
	
	private boolean 	bIstFremdware = 			false;
	private Long   		lID_ADRESSE_FREMDAUFTRAG = 	null;
	
	private Long     	lID_VPOS_TPA_FUHRE_SONDER = null;
	
	public FU_VALID_HELPER_FREMDWAREN_FREMDWAREN_LAGER(FU_MASK_ComponentMAP oMAP) throws myException {
		super();
		
		//zuerst aktuelle feldwerte beschaffen
		this.lID_ADRESSE_START = 		oMAP.get_LActualDBValue(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_START, -1l, -1l);
		this.lID_ADRESSE_ZIEL = 		oMAP.get_LActualDBValue(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_ZIEL, -1l, -1l);
		
		this.lID_ADRESSE_LAGER_START = 	oMAP.get_LActualDBValue(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_LAGER_START, -1l, -1l);
		this.lID_ADRESSE_LAGER_ZIEL = 	oMAP.get_LActualDBValue(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_LAGER_ZIEL, -1l, -1l);
		
		this.bIstFremdware = 			oMAP.get_bActualDBValue(_DB.VPOS_TPA_FUHRE$OHNE_ABRECHNUNG);
		this.lID_ADRESSE_FREMDAUFTRAG =	oMAP.get_LActualDBValue(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_FREMDAUFTRAG, -1l, -1l);
		
		this.bIstFremdware = 			(bIstFremdware || lID_ADRESSE_FREMDAUFTRAG>0);
		
		this.lID_VPOS_TPA_FUHRE_SONDER = oMAP.get_LActualDBValue(_DB.VPOS_TPA_FUHRE$ID_VPOS_TPA_FUHRE_SONDER, -1l, -1l);
		
	}

	
	public MyE2_MessageVector   get_vValidErgebnis() throws myException {
		
		MyE2_MessageVector oMV_Rueck = new MyE2_MessageVector(); 
		
		oMV_Rueck.add_MESSAGE(new FU__PRUEFUNG_FREMDWARE_AUF_FREMDLAGER(this.lID_ADRESSE_START, this.lID_ADRESSE_LAGER_START, this.bIstFremdware, "Hauptfuhre", true, this.lID_VPOS_TPA_FUHRE_SONDER).get_oMV_Rueck());
		oMV_Rueck.add_MESSAGE(new FU__PRUEFUNG_FREMDWARE_AUF_FREMDLAGER(this.lID_ADRESSE_ZIEL, this.lID_ADRESSE_LAGER_ZIEL, this.bIstFremdware, "Hauptfuhre", false, this.lID_VPOS_TPA_FUHRE_SONDER).get_oMV_Rueck());
		
		return oMV_Rueck;
	}
	

	
	
}
