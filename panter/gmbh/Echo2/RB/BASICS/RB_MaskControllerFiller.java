package panter.gmbh.Echo2.RB.BASICS;

import java.util.HashMap;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec20_field;
import panter.gmbh.indep.exceptions.myException;

public class RB_MaskControllerFiller {

	public enum FILLTYPE {
		 FILLWHENEMPTY
		,FILLFORCE
	}
	
	private Rec20  						recWithValues = null;
	private RB_MaskControllerMap  		maskController = null;
	private RB_KM   					maskKey = null;
	

	/**
	 * @param p_maskKey
	 * @param p_maskController
	 * @param p_recWithValues
	 */
	public RB_MaskControllerFiller(RB_KM p_maskKey, RB_MaskControllerMap p_maskController, Rec20 p_recWithValues) {
		super();
		this.maskKey = p_maskKey;
		this.maskController = p_maskController;
		this.recWithValues = p_recWithValues;
	}	
	
	
	/**
	 * fuellt die felder mit gleichnamigen felder aus dem record
	 * @param fields
	 * @return
	 * @throws myException
	 */
	public MyE2_MessageVector   fill_fields(IF_Field ... fields) throws myException {
		
		MyE2_MessageVector mv = new MyE2_MessageVector();

		 HashMap<RB_KF,RB_MaskControllerField> fieldMap = this.maskController.get(this.maskKey);
		 
		 if (fieldMap == null) {
			 mv.add_MESSAGE(new MyE2_Alarm_Message(S.mt("Die Maske ").ut(this.maskKey.get_REALNAME()).t(" besitzt keinen Vertreter in der MaskMap !")));
		 } else {
			 for (IF_Field f: fields) {
				 if (fieldMap == null) {
					 mv.add_MESSAGE(new MyE2_Alarm_Message(S.mt("Das Feld ").ut(f.fn()).t(" kann in der Maske ").ut(this.maskKey.get_REALNAME()).t(" nicht gefunden werden !")));
				 }	else {
					 
					 //das feld gleichen namens in der Rec20 suchen
					 IF_Field f2 = this.recWithValues.find_field(f.fn());
					 if (f2==null) {
						 mv.add_MESSAGE(new MyE2_Alarm_Message(S.mt("Das Feld ").ut(f.fn()).t(" ist kein Feld des übergebenen Records ").ut(this.recWithValues.get_TABLENAME())));
					 } else {
						 Rec20_field rf = this.recWithValues.get(f2);
						 
						 if (rf==null) {
							 mv.add_MESSAGE(new MyE2_Alarm_Message(S.mt("Das Feld ").ut(f.fn()).t(" kann im übergebenen Record ").ut(this.maskKey.get_REALNAME()).t(" nicht gefunden werden !")));
						 } else {
							 this.maskController.set_maskVal(this.maskKey, f, rf.get_database_value_f(), mv);
						 }
					 }
				 }
			 }
		 }
		
		return mv;
	}
	
	
}
