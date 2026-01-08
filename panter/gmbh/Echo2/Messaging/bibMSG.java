package panter.gmbh.Echo2.Messaging;

import java.util.Vector;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;



/*
 * klasse, die alle message-uebergaben beihnaltet 
 */
public class bibMSG 
{

	// messaging-klassen, für behandlung des benutzer-info-systems 
	public static MyE2_MessageVector MV()
	{
	    MyE2_MessageVector oMV = (MyE2_MessageVector) bibALL.getSessionValue("USERMESSAGE");
	
	    if (oMV == null)   {
	    	oMV = new MyE2_MessageVector();
	    	bibALL.setSessionValue("USERMESSAGE", oMV);
	    } 
	
	    return oMV;
	}

	
	public static void add_MESSAGE(MyE2_MessageVector oMV)
	{
		if (oMV == null)
			return;
		
		bibMSG.MV().add_MESSAGE(oMV);
	}

	
	public static void add_MESSAGE(myException ex)
	{
		bibMSG.MV().add_MESSAGE(ex.get_ErrorMessage(), false);
	}

	
	public static void add_MESSAGE(MyE2_Message oMessage, boolean bInFront)
	{
		bibMSG.MV().add_MESSAGE(oMessage, bInFront);
	}


	
	public static void add_MESSAGE_AllowDoubles(MyE2_MessageVector oMV)
	{
		if (oMV == null)
			return;
		
		bibMSG.MV().add_MESSAGE_AllowDoubles(oMV);
	}

	
	public static void add_MESSAGE_AllowDoubles(myException ex)
	{
		bibMSG.MV().add_MESSAGE_AllowDoubles(ex.get_ErrorMessage(), false);
	}

	
	public static void add_MESSAGE_AllowDoubles(MyE2_Message oMessage, boolean bInFront)
	{
		bibMSG.MV().add_MESSAGE_AllowDoubles(oMessage, bInFront);
	}

	
	
	public static void add_ALARMMESSAGE_Vector_Untranslated(Vector<String> vStringsUntranslated)
	{
		bibMSG.MV().add_ALARMMESSAGE_Vector_Untranslated(vStringsUntranslated);
	}
	

	public static void add_MESSAGE(MyE2_Message oMessage)
	{
		bibMSG.MV().add_MESSAGE(oMessage, false);
	}
	
	
	public static boolean get_bHasAlarms()
	{
		return bibMSG.MV().get_bHasAlarms();
	}
	

	public static boolean get_bIsOK()
	{
		return bibMSG.MV().get_bIsOK();
	}

	
	/**
	 * legt eine reihenfolge der messages fest (willkuerlich, hier Alarme mit Buttons vorne)
	 */
	public static void sort_Messages(MyE2_MessageVector oMV) {
		
		MyE2_MessageVector  oMV_TEMP = new MyE2_MessageVector();
		oMV_TEMP.addAll(oMV);
		
		oMV.removeAllElements();
		
		
		
		//---sort beginn
        // die erste alarm-message an den anfang stellen
		MyE2_Message  oFirstMessage = oMV_TEMP.get(0);
		
		boolean bFoundFirst = false;
		if (oMV_TEMP.get_bHasAlarms())
		{

			
			//20180307: aenderung: reihenfolge geaendert: am wichtigsten Decicions (abgekündigt), dannach alarme, danach button-messages
			//alt:
			
//			/**
//			 * meldungen mit typkennzeichen alarm und Abfragen sind ganz oben
//			 */
//			for (int i=0;i<oMV_TEMP.size();i++) {
//				if (oMV_TEMP.get(i) instanceof IF_Message_4_Decisions && oMV_TEMP.get(i).get_iType()==MyE2_Message.TYP_ALARM)	{
//					oFirstMessage=oMV_TEMP.get(i);
//					bFoundFirst = true;
//					break;
//				}
//			} 
//			
//			/**
//			 * meldungen mit typkennzeichen alarm und zusatzbutton sind ganz oben
//			 */
//			if (!bFoundFirst) {
//				for (int i=0;i<oMV_TEMP.size();i++) {
//					if (oMV_TEMP.get(i) instanceof IF_Message_WithButtons && oMV_TEMP.get(i).get_iType()==MyE2_Message.TYP_ALARM )	{
//						oFirstMessage=oMV_TEMP.get(i);
//						bFoundFirst = true;
//						break;
//					}
//				} 
//			}
//			
//			
//			//dann noch die "nur" alarm-messages durchsuchen
//			if (!bFoundFirst) {
//				for (int i=0;i<oMV_TEMP.size();i++) {
//					if (oMV_TEMP.get(i).get_iType()==MyE2_Message.TYP_ALARM)	{
//						oFirstMessage=oMV_TEMP.get(i);
//						break;
//					}
//				}
//			}

			//neu ab 20180307:
			/**
			 * meldungen mit typkennzeichen alarm und Abfragen sind ganz oben
			 */
			for (int i=0;i<oMV_TEMP.size();i++) {
				if (oMV_TEMP.get(i) instanceof IF_Message_4_Decisions && oMV_TEMP.get(i).get_iType()==MyE2_Message.TYP_ALARM)	{
					oFirstMessage=oMV_TEMP.get(i);
					bFoundFirst = true;
					break;
				}
			} 
			
			
			//dann noch die "nur" alarm-messages durchsuchen
			if (!bFoundFirst) {
				for (int i=0;i<oMV_TEMP.size();i++) {
					if (oMV_TEMP.get(i).get_iType()==MyE2_Message.TYP_ALARM)	{
						oFirstMessage=oMV_TEMP.get(i);
						bFoundFirst = true;
						break;
					}
				}
			}

			/**
			 * meldungen mit typkennzeichen alarm und zusatzbutton sind ganz oben
			 */
			if (!bFoundFirst) {
				for (int i=0;i<oMV_TEMP.size();i++) {
					if (oMV_TEMP.get(i) instanceof IF_Message_WithButtons && oMV_TEMP.get(i).get_iType()==MyE2_Message.TYP_ALARM )	{
						oFirstMessage=oMV_TEMP.get(i);
						bFoundFirst = true;
						break;
					}
				} 
			}
			//ende neu ab 20180307
			
			
			/**
			 * meldungen mit zusatzbutton sind ganz oben
			 */
			if (!bFoundFirst) {
				for (int i=0;i<oMV_TEMP.size();i++) {
					if (oMV_TEMP.get(i) instanceof IF_Message_WithButtons)	{
						oFirstMessage=oMV_TEMP.get(i);
						bFoundFirst = true;
						break;
					}
				} 
			}
			
			
		}
		else if(oMV_TEMP.get_bHasWarnings())
		{
			for (int i=0;i<oMV_TEMP.size();i++)
			{
				if (oMV_TEMP.get(i).get_iType()==MyE2_Message.TYP_WARNING)
				{
					oFirstMessage=oMV_TEMP.get(i);
					break;
				}
			}
		}

		
		//jetzt umsortieren
		oMV.add(oFirstMessage);
		for (MyE2_Message msg: oMV_TEMP) {
			if (!oMV.contains(msg)) {
				oMV.add(msg);
			}
		}
		//---sort ende
		
		//jetzt dafuer sorgen, dass nur die erste aktive message geklickt werden kann
		bibMSG.disableAllButFirst(oMV);
		
	}
	
	
	/**
	 * dafuer sorgen, dass nur die erste vorkommende buttonbehaftete Messag klickbar ist 
	 * @param oMV
	 * @throws myException 
	 */
	public static void disableAllButFirst(MyE2_MessageVector oMV) {
		boolean bFirstButtonMessageIsEnabled = false;
		for (MyE2_Message  msg: oMV) {
			if (msg instanceof IF_Message_WithButtons)  {
				if (!bFirstButtonMessageIsEnabled) {
					try {
						((IF_Message_WithButtons)msg).enable_ActiveComponents(msg);
						bFirstButtonMessageIsEnabled = true;
					} catch (myException e) {
						e.printStackTrace();
					}
				} else {
					try {
						((IF_Message_WithButtons)msg).disable_ActiveComponents(msg);
					} catch (myException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		
	}
	
	/**
	 * 
	 * @return new and empty MyE2_MessageVector
	 */
	public static MyE2_MessageVector getNewMV() {
		return new MyE2_MessageVector();
	}
	
	/**
	 * 
	 * @return new and empty MyE2_MessageVector
	 */
	public static MyE2_MessageVector newMV() {
		return new MyE2_MessageVector();
	}

}


