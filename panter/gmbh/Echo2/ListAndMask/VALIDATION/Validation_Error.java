package panter.gmbh.Echo2.ListAndMask.VALIDATION;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.indep.MyString;

public class Validation_Error {

	public static final  int  SHOW_WARNING = 1;
	public static final  int  SHOW_ERROR = -1;
	public static final  int  SHOW_NOTHING = 0;
	
	
	private MyString     cMessage = null;
	


	private Vector<String>  vHASH_KEYS_4_Error = new Vector<String>();
	private Vector<String>  vHASH_KEYS_4_Warning = new Vector<String>();
	
	// folgende boolean definieren immer den unterschied zwischen warnung (false) und error (true) fuer die unterschiedliche einsatzbereiche
	private int 	ErrorOnMaskSave = Validation_Error.SHOW_NOTHING;
	private int 	ErrorOnMaskLoad = Validation_Error.SHOW_NOTHING;
	private int 	ErrorOnGesamtBewertung = Validation_Error.SHOW_NOTHING;
	
	
	/**
	 * 
	 * @param Message   				MyString, enthaelt die meldung
	 * @param HASH_KEYS_4_Error			Vector mit Hashkey der komponeten, wo diese meldung als fehler erscheinen soll (interaktiv) 
	 * @param HASH_KEYS_4_Warning		Vector mit Hashkey der komponeten, wo diese meldung als warnung erscheinen soll (interaktiv) 
	 * @param errorOnMaskSave			Schalter definiert, ob die meldung beim speichern als fehler (-1) oder warnung (1) oder gar nicht erscheinen soll
	 * @param errorOnMaskLoad			Schalter definiert, ob die meldung beim laden als fehler (-1) oder warnung (1) oder gar nicht erscheinen soll
	 * @param errorOnGesamtBewertung	Schalter definiert, ob die meldung beim betrachten der gesamten objektes als fehler (-1) oder warnung (1) oder gar nicht erscheinen soll
	 */
	public Validation_Error(	MyString     	Message,
								Vector<String> 	HASH_KEYS_4_Error, 
								Vector<String> 	HASH_KEYS_4_Warning, 
								int 			errorOnMaskSave, 
								int 			errorOnMaskLoad,
								int   			errorOnGesamtBewertung) {
		super();
		
		this.cMessage = Message;
		if (HASH_KEYS_4_Error!=null) {
			this.vHASH_KEYS_4_Error.addAll(HASH_KEYS_4_Error);
		}
		if (HASH_KEYS_4_Warning != null) {
			this.vHASH_KEYS_4_Warning.addAll(HASH_KEYS_4_Warning);
		}
		this.ErrorOnMaskSave = 	errorOnMaskSave;
		this.ErrorOnMaskLoad = 	errorOnMaskLoad;
		this.ErrorOnGesamtBewertung = errorOnGesamtBewertung;
	}
	
	

	public MyString get_cMessage() {
		return cMessage;
	}


	
	
	public MyE2_Message get_Message_4_MaskAction(String cHASH_KEY) {
		MyE2_Message oMSG = null;
		
		if (this.vHASH_KEYS_4_Error.contains(cHASH_KEY)) {
			oMSG = new MyE2_Alarm_Message(this.cMessage);
		} else if (this.vHASH_KEYS_4_Warning.contains(cHASH_KEY)) {
			oMSG = new MyE2_Warning_Message(this.cMessage);
		}
		return oMSG;
	}
	
	
	public MyE2_Message  get_Message_4_MaskSave() {
		MyE2_Message oMSG = null;
		
		if (ErrorOnMaskSave == Validation_Error.SHOW_ERROR) {
			oMSG = new MyE2_Alarm_Message(this.cMessage);
		} else if (ErrorOnMaskSave == Validation_Error.SHOW_WARNING){
			oMSG = new MyE2_Warning_Message(this.cMessage);
		}
		return oMSG;
	}

	public MyE2_Message  get_Message_4_MaskLoad() {
		MyE2_Message oMSG = null;
		
		if (ErrorOnMaskLoad == Validation_Error.SHOW_ERROR) {
			oMSG = new MyE2_Alarm_Message(this.cMessage);
		} else if (ErrorOnMaskLoad == Validation_Error.SHOW_WARNING){
			oMSG = new MyE2_Warning_Message(this.cMessage);
		}
		return oMSG;
	}

	public MyE2_Message  get_Message_4_GesamtBewertung() {
		MyE2_Message oMSG = null;
		
		if (ErrorOnGesamtBewertung == Validation_Error.SHOW_ERROR) {
			oMSG = new MyE2_Alarm_Message(this.cMessage);
		} else if (ErrorOnGesamtBewertung == Validation_Error.SHOW_WARNING){
			oMSG = new MyE2_Warning_Message(this.cMessage);
		}
		return oMSG;
	}

}
