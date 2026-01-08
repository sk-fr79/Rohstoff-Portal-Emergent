package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_MWST;
import rohstoff.utils.My_MWSTSaetze;

public class BSRG_P_MASK_ComponentMAP_Subquery_Agent extends XX_ComponentMAP_SubqueryAGENT {

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{

		String cID_VPOS_RG = oUsedResultMAP.get_UnFormatedValue("ID_VPOS_RG");
		
		//zuerst nachsehen, ob die Position eine ID_ADRESSE enthaelt
		String cID_ADRESSE = bibALL.null2leer(oUsedResultMAP.get_UnFormatedValue("ID_ADRESSE"));
		
		if (bibALL.isEmpty(cID_ADRESSE))
		{
			//dann ist es keine freie position und die adresse muss ueber den kopf ermittelt werden
			cID_ADRESSE = new RECORD_VPOS_RG(cID_VPOS_RG).get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_ID_ADRESSE_cUF();
		}
		
		String[][] cHelp = {{"-",""}}; 
		((BS_ComboBox_MWST)oMAP.get__Comp("STEUERSATZ")).set_Varianten(cHelp, null, null, false);
		//jetzt das Feld fuer die Steuersaetze an die Adresse anpassen
		if (!bibALL.isEmpty(cID_ADRESSE))
		{
			My_MWSTSaetze      oMWST = new My_MWSTSaetze(cID_ADRESSE,null); 
			((BS_ComboBox_MWST)oMAP.get__Comp("STEUERSATZ")).set_Varianten(oMWST.get_MWST_DropDownArray_AdressMWST(true), null, null, false);
		}
		
		
		
	}

}
