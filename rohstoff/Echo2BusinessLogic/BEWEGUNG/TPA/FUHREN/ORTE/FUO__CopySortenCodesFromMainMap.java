package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._4_ALL.MASK_COMPONENT_SEARCH_EAK_CODES;

public class FUO__CopySortenCodesFromMainMap
{

	//kopiert die werte aus der fuhre in die tochter
	public FUO__CopySortenCodesFromMainMap(E2_ComponentMAP  oMAP_Fuhre, E2_ComponentMAP oMAP_Daughter) throws myException
	{
		super();
		
		//uebernommen werden folgende felder:
		//ID_EAK_CODE
		String cID_EAK_CODE_FromFuhre = ((MASK_COMPONENT_SEARCH_EAK_CODES)oMAP_Fuhre.get__DBComp("ID_EAK_CODE")).get_cActualDBValueFormated();
		
		cID_EAK_CODE_FromFuhre = new MyLong(cID_EAK_CODE_FromFuhre).get_cUF_LongString();
		
		if (S.isFull(cID_EAK_CODE_FromFuhre))
		{
			//2011-09-07: aenderung der AVV-komponente in der Fuhre
			//((FUO_MASK_DB_SEARCH_EAK_CODE)oMAP_Daughter.get__DBComp("ID_EAK_CODE")).set_cActualMaskValue(cID_EAK_CODE_FromFuhre);
			//((FUO_MASK_DB_SEARCH_EAK_CODE)oMAP_Daughter.get__DBComp("ID_EAK_CODE")).FillLabelWithDBQuery(cID_EAK_CODE_FromFuhre);
			
			((MASK_COMPONENT_SEARCH_EAK_CODES)oMAP_Daughter.get__DBComp("ID_EAK_CODE")).set_cActualMaskValue(cID_EAK_CODE_FromFuhre);
			((MASK_COMPONENT_SEARCH_EAK_CODES)oMAP_Daughter.get__DBComp("ID_EAK_CODE")).FillLabelWithDBQuery(cID_EAK_CODE_FromFuhre);

			
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Der AVV-Code wurde von der Fuhre übernommen !"));
		}
	}
	
	
}
