package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.POS_VORLAGE;

import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CONST;

public class BL_VL_MASK_MapValidator extends XX_MAP_ValidBeforeSAVE
{

	@Override
	public MyE2_MessageVector _doValidation(E2_ComponentMAP map, SQLMaskInputMAP inputMap, String cstatus_maske) throws myException
	{
		//den neuberechnungsbutton druecken
		MyE2_Button oButtonNeuBerechnen = (MyE2_Button)map.get__Comp(BS__CONST.HASHKEY_FOR_NEUBERECHNUNGS_BUTTON);
		oButtonNeuBerechnen.doActionPassiv();
		inputMap.Read_ActualInputValues(true);    // neue werte in die map einlesen
		
		return new MyE2_MessageVector();
	}

}
