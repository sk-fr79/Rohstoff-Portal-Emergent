package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FUO_MASK_ComponentMAP;


public class FUO_VALID_HELPER_FREMDWAREN_FREMDWAREN_LAGER {

	private Long   		lID_ADRESSE = 				null;

	
	private Long   		lID_ADRESSE_LAGER = 		null;
	
	private boolean 	bIstFremdware = 			false;
	private Long   		lID_ADRESSE_FREMDAUFTRAG = 	null;
	
	private Long     	lID_VPOS_TPA_FUHRE_SONDER = null;
	
	private boolean 	bIstLadestation = false;
	

	public FUO_VALID_HELPER_FREMDWAREN_FREMDWAREN_LAGER(FUO_MASK_ComponentMAP oMAP) throws myException {
		super();
		
		//zuerst aktuelle feldwerte beschaffen
		String cID_VPOS_TPA_FUHRE = oMAP.get_oSQLFieldMAP().get_hmRestrictionFieldValues().get(_DB.VPOS_TPA_FUHRE_ORT$ID_VPOS_TPA_FUHRE);
		MyLong lID_Fuhre = new MyLong(cID_VPOS_TPA_FUHRE);
		
		if (lID_Fuhre.get_oLong()==null) {
			throw new myException(this,"Error, cannot identify <fuhre>");
		}
		
		this.bIstLadestation = oMAP.get_oSQLFieldMAP().get_hmRestrictionFieldValues().get(_DB.VPOS_TPA_FUHRE_ORT$DEF_QUELLE_ZIEL).equals("'EK'");
		
		//zuerst aktuelle feldwerte beschaffen
		this.lID_ADRESSE = 				oMAP.get_LActualDBValue(_DB.VPOS_TPA_FUHRE_ORT$ID_ADRESSE, -1l, -1l);
		this.lID_ADRESSE_LAGER = 		oMAP.get_LActualDBValue(_DB.VPOS_TPA_FUHRE_ORT$ID_ADRESSE_LAGER, -1l, -1l);
		
		this.bIstFremdware = 			oMAP.get_oFU_FUO_DaughterComponent().EXT().get_oComponentMAP().get_bActualDBValue(_DB.VPOS_TPA_FUHRE$OHNE_ABRECHNUNG);
		this.lID_ADRESSE_FREMDAUFTRAG =	oMAP.get_oFU_FUO_DaughterComponent().EXT().get_oComponentMAP().get_LActualDBValue(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_FREMDAUFTRAG, -1l, -1l);
		
		this.bIstFremdware = 			(bIstFremdware || lID_ADRESSE_FREMDAUFTRAG>0);
		
		this.lID_VPOS_TPA_FUHRE_SONDER = oMAP.get_LActualDBValue(_DB.VPOS_TPA_FUHRE_ORT$ID_VPOS_TPA_FUHRE_SONDER, -1l, -1l);

	}

	
	public MyE2_MessageVector   get_vValidErgebnis() throws myException {
		
		MyE2_MessageVector oMV_Rueck = new MyE2_MessageVector(); 

		oMV_Rueck.add_MESSAGE(new FU__PRUEFUNG_FREMDWARE_AUF_FREMDLAGER(this.lID_ADRESSE, this.lID_ADRESSE_LAGER, this.bIstFremdware, "Fuhrenort (Zusatz)", this.bIstLadestation,this.lID_VPOS_TPA_FUHRE_SONDER).get_oMV_Rueck());
		
		return oMV_Rueck;
	}
	
	
}
