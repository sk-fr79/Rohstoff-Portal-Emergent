package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FU_LIST_BT_DEFINIERE_UMA_VERTRAG extends __FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG
{
	
	public FU_LIST_BT_DEFINIERE_UMA_VERTRAG() {
		super();
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		FU_LIST_BT_DEFINIERE_UMA_VERTRAG oRueck = new FU_LIST_BT_DEFINIERE_UMA_VERTRAG();
		oRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRueck));
		return oRueck;
	}
	
	
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		//immer enabled
	}
	
	/**
	 * liest die notwendigen felder aus der resultmap der liste
	 */
	public MyE2_MessageVector PRUEFE_und_SETZE_STATUS_DER_NOTWENDIGEN_FELDER() throws myException
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		E2_ComponentMAP  oMAP = this.EXT().get_oComponentMAP();
		SQLResultMAP     oResultMap = oMAP.get_oInternalSQLResultMAP();
		
		this.set_lID_ADRESSE_START(oResultMap.get_LActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__ID_ADRESSE_START, true));
		this.set_lID_ADRESSE_ZIEL(oResultMap.get_LActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__ID_ADRESSE_ZIEL, true));
		
		this.set_lID_ARTKELBEZ_EK(oResultMap.get_LActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__ID_ARTIKEL_BEZ_EK, true));
		this.set_lID_ARTKELBEZ_VK(oResultMap.get_LActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__ID_ARTIKEL_BEZ_VK, true));
		
		this.set_lID_UMA_KONTRAKT(oResultMap.get_LActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__ID_UMA_KONTRAKT, true));

		this.set_lID_ADRESSE_FREMDAUFTRAG(oResultMap.get_LActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__ID_ADRESSE_FREMDAUFTRAG, true));
		this.set_bIsFremdwarenFuhre(oResultMap.get_UnFormatedValue(RECORD_VPOS_TPA_FUHRE.FIELD__OHNE_ABRECHNUNG,"N").equals("Y"));
		
		this.set_lID_VPOS_TPA_FUHRE(oResultMap.get_LActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__ID_VPOS_TPA_FUHRE, true));
		
		
		this.KorrigiereAnzeigeFuer_UMA_STATUS(this.get_lID_UMA_KONTRAKT());
		
		return oMV;
	}

	@Override
	protected void INDIVIDUELLE_Anzeige(Long lID_UMA) throws myException
	{
	}
	

	
}
