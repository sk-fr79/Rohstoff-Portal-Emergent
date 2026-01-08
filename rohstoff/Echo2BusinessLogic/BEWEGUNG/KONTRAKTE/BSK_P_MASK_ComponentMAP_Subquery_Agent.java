package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.MyAdressLIGHT;
import rohstoff.utils.My_VPos_KON;

public class BSK_P_MASK_ComponentMAP_Subquery_Agent extends XX_ComponentMAP_SubqueryAGENT {

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
		((BSK_P_MASK_BT_AdressePOPUP_BUTTON)oMAP.get_Comp(BSK__CONST.BUTTON_TO_LOAD_ADRESSE)).EXT().set_bDisabledFromInteractive(true);
		((BSK_P_MASK_BT_AdressePOPUP_BUTTON)oMAP.get_Comp(BSK__CONST.BUTTON_TO_LOAD_ADRESSE)).set_bEnabled_For_Edit(false);
		((BSK_P_MASK_BT_AdressePOPUP_BUTTON)oMAP.get_Comp(BSK__CONST.BUTTON_TO_LOAD_ADRESSE)).setText(new MyE2_String("---Neueingabe---").CTrans());

	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		((BSK_P_MASK_BT_AdressePOPUP_BUTTON)oMAP.get_Comp(BSK__CONST.BUTTON_TO_LOAD_ADRESSE)).EXT().set_bDisabledFromInteractive(false);
		((BSK_P_MASK_BT_AdressePOPUP_BUTTON)oMAP.get_Comp(BSK__CONST.BUTTON_TO_LOAD_ADRESSE)).set_bEnabled_For_Edit(true);

		String cID_VPOS_KON = oUsedResultMAP.get_UnFormatedValue("ID_VPOS_KON");
		
		My_VPos_KON oVposKon = new My_VPos_KON(cID_VPOS_KON);
		String cID_ADRESSE = oVposKon.get_oVKopfKON().get_UnFormatedValue("ID_ADRESSE");

		
		MyAdressLIGHT oAdress = new MyAdressLIGHT(cID_ADRESSE);
		
		String cName = bibALL.null2leer(oAdress.get_FormatedValue("NAME1"));
		cName += (" "+bibALL.null2leer(oAdress.get_FormatedValue("PLZ")));
		cName += (" "+bibALL.null2leer(oAdress.get_FormatedValue("ORT")));
		cName += (" ("+bibALL.null2leer(oAdress.get_FormatedValue("ID_ADRESSE"))+")");
	
		((BSK_P_MASK_BT_AdressePOPUP_BUTTON)oMAP.get_Comp(BSK__CONST.BUTTON_TO_LOAD_ADRESSE)).setText(cName);
		
	}

}
