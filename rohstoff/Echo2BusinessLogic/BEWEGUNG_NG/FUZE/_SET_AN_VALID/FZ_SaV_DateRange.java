package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE._SET_AN_VALID;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class FZ_SaV_DateRange extends RB_Mask_Set_And_Valid {

	
	//pruefung erfasste daten-zeitraeume, von- bis in der richtigen reihe
	private RB_KM     	maskVon = null;
	private RB_KM     	maskBis = null;
	private IF_Field  	fieldVon = null;
	private IF_Field  	fieldBis = null;
	private MyE2_String messageWhenRangeIsUnfitting = null;
	
	
	
	
	
	/**
	 * @param p_maskVon
	 * @param p_fieldVon
	 * @param p_maskBis
	 * @param p_fieldBis
	 * @param p_messageWhenRangeIsUnfitting
	 */
	public FZ_SaV_DateRange(RB_KM p_maskVon, IF_Field p_fieldVon, RB_KM p_maskBis, IF_Field p_fieldBis, MyE2_String p_messageWhenRangeIsUnfitting) {
		super();
		this.maskVon = p_maskVon;
		this.fieldVon = p_fieldVon;
		this.maskBis = p_maskBis;
		this.fieldBis = p_fieldBis;
		this.messageWhenRangeIsUnfitting = p_messageWhenRangeIsUnfitting;
	}





	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, ExecINFO oExecInfo) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
	
		if (ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
			RB_MaskController controller = new RB_MaskController(rbMASK);
			
			MyDate datumVon = controller.get_MyDate_maskVal(this.maskVon, this.fieldVon);
			MyDate datumBis = controller.get_MyDate_maskVal(this.maskBis, this.fieldBis);
			
			if (datumVon!=null && datumBis != null && datumVon.isOK() && datumBis.isOK()) {
				if (!(myDateHelper.get_Date1_GreaterEqual_Date2(datumBis.get_cDateStandardFormat(), datumVon.get_cDateStandardFormat()))) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(this.messageWhenRangeIsUnfitting));
				}
			}
		}
		return mv;
	}

}
