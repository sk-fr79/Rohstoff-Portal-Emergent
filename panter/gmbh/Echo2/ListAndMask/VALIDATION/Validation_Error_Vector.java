package panter.gmbh.Echo2.ListAndMask.VALIDATION;

import java.util.Collection;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;

public class Validation_Error_Vector extends Vector<Validation_Error> {

	public Validation_Error_Vector() {
		super();
	}

	public Validation_Error_Vector(	Collection<? extends Validation_Error> c) {
		super(c);
	}

	
	public MyE2_MessageVector  get_Messages(String cHashKey) {
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		for (Validation_Error oError: this) {
			MyE2_Message  oMSG = oError.get_Message_4_MaskAction(cHashKey);
			if (oMSG != null) {
				oMV.add_MESSAGE(oMSG);
			}
		}
		return oMV;
	}
	

	public MyE2_MessageVector  get_MessagesOnSave() {
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		for (Validation_Error oError: this) {
			MyE2_Message  oMSG = oError.get_Message_4_MaskSave();
			if (oMSG != null) {
				oMV.add_MESSAGE(oMSG);
			}
		}
		return oMV;
	}

	
	public MyE2_MessageVector  get_MessagesOnLoad() {
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		for (Validation_Error oError: this) {
			MyE2_Message  oMSG = oError.get_Message_4_MaskLoad();
			if (oMSG != null) {
				oMV.add_MESSAGE(oMSG);
			}
		}
		return oMV;
	}
	
	
	public MyE2_MessageVector  get_Messages_4_Bewertung()  {
		
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		for (Validation_Error oError: this) {
			MyE2_Message  oMSG = oError.get_Message_4_GesamtBewertung();
			if (oMSG != null) {
				oMV.add_MESSAGE(oMSG);
			}
		}
		return oMV;
	}
	
	public boolean get_bHAT_FEHLER() {
		return this.get_Messages_4_Bewertung().get_bHasAlarms();
	}
	
}
