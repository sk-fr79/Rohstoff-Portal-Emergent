package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FBAM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class BAMF_RECORD_VPOS_TPA_FUHRE_AUS_VIEW extends RECORD_VPOS_TPA_FUHRE
{


	public BAMF_RECORD_VPOS_TPA_FUHRE_AUS_VIEW(String cID_VPOS_TPA_FUHRE, String cID_VPOS_TPA_FUHRE_ORT)  throws myException
	{
		super("SELECT * FROM "+bibE2.cTO()+".V"+bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF()+"_FUHREN WHERE ID_VPOS_TPA_FUHRE="+
				cID_VPOS_TPA_FUHRE+(S.isEmpty(cID_VPOS_TPA_FUHRE_ORT)?" AND ID_VPOS_TPA_FUHRE_ORT IS NULL ":" AND ID_VPOS_TPA_FUHRE_ORT="+cID_VPOS_TPA_FUHRE_ORT));

	}

	
	
	public BAMF_RECORD_VPOS_TPA_FUHRE_AUS_VIEW(RECORD_FBAM recFBAM)  throws myException
	{
		super();
		
		//die id_vpos_tpa_fuhre muss sein
		String cID_VPOS_TPA_FUHRE = 		recFBAM.get_ID_VPOS_TPA_FUHRE_cUF_NN("");
		String cID_VPOS_TPA_FUHRE_ORT =   	recFBAM.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN("");;
		
		if (S.isEmpty(cID_VPOS_TPA_FUHRE))  //dann muss es einen ort geben
		{
			cID_VPOS_TPA_FUHRE = new RECORD_VPOS_TPA_FUHRE_ORT(cID_VPOS_TPA_FUHRE_ORT).get_ID_VPOS_TPA_FUHRE_cUF();
		}
		
		this.BuildRecord("SELECT * FROM "+bibE2.cTO()+".V"+bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF()+"_FUHREN WHERE ID_VPOS_TPA_FUHRE="+
				cID_VPOS_TPA_FUHRE+(S.isEmpty(cID_VPOS_TPA_FUHRE_ORT)?" AND ID_VPOS_TPA_FUHRE_ORT IS NULL ":" AND ID_VPOS_TPA_FUHRE_ORT="+cID_VPOS_TPA_FUHRE_ORT));
		
	}
	
	
	public String get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN(String cNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_ORT", cNullValue);
	}
	
	
	
	public boolean get_b_FBAM_CAN_BE_SAVED() throws myException
	{
		
		
		if (this.is_DELETED_YES() || this.is_IST_STORNIERT_YES() || this.get_STATUS_BUCHUNG_cUF_NN("-").equals(""+myCONST.STATUS_FUHRE__TEILSGEBUCHT) ||
			this.get_STATUS_BUCHUNG_cUF_NN("-").equals(""+myCONST.STATUS_FUHRE__GANZGEBUCHT))
		{
			return false;
		}
		else
		{
			return true;
		}

	}


}
