package calledByName.triggerExecuter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.EMAIL.RB_Email;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST.SEND_TYPE;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.IF_trigger_executer;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.Rec20_trigger_action_def;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.Rec20_trigger_action_log;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES._SIMPLE_MESSAGE;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.BasicTools.readXML;
import panter.gmbh.basics4project.DB_ENUMS.TRIGGER_ACTION_DEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.Replacer.bibReplacer;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.mail.MailAdressChecker;

public class TriggerExecutorInterpretXML implements IF_trigger_executer {

	
	//tags fuer messages
	private static String XMLTAG_MESSAGE = 			"message";
	private static String XMLTAG_MESSAGE_TITEL = 	"titel";
	private static String XMLTAG_MESSAGE_TEXT = 	"text";
	private static String XMLTAG_MESSAGE_ID_USER = 	"user";
	
	//tags fuer email
	private static String XMLTAG_EMAIL = 			"email";
	private static String XMLTAG_EMAIL_ABSENDER = 	"absender";
	private static String XMLTAG_EMAIL_BETREFF = 	"betreff";
	private static String XMLTAG_EMAIL_TEXT = 		"text";
	private static String XMLTAG_EMAIL_TARGET = 	"target";
	
	
	
	
	
	@Override
	public void execute(Rec20_trigger_action_log trigger_log, Rec20_trigger_action_def trigger_def, MyE2_MessageVector mv) throws myException {
		
		String XML_text = trigger_def.get_ufs_dbVal(TRIGGER_ACTION_DEF.execution_code);
		
		try {
			
			this.interpret_and_execute_XML_4_messages(XML_text);
			this.interpret_and_execute_XML_4_email(XML_text);
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			mv.add_MESSAGE(new MyE2_Alarm_Message("SYSTEM-Error: XML-Trigger-Action (1): Error parsing XML-Definition ->> cause: "+e.getLocalizedMessage()));
			throw new myException(e.getLocalizedMessage());
		} catch (SAXException e) {
			mv.add_MESSAGE(new MyE2_Alarm_Message("SYSTEM-Error: XML-Trigger-Action (2): Error parsing XML-Definition ->> cause: "+e.getLocalizedMessage()));
			e.printStackTrace();
			throw new myException(e.getLocalizedMessage());
		} catch (IOException e) {
			mv.add_MESSAGE(new MyE2_Alarm_Message("SYSTEM-Error: XML-Trigger-Action (3): Error parsing XML-Definition ->> cause: "+e.getLocalizedMessage()));
			e.printStackTrace();
			throw new myException(e.getLocalizedMessage());
		} catch (Exception e){
			mv.add_MESSAGE(new MyE2_Alarm_Message("SYSTEM-Error: XML-Trigger-Action (4): unknown error ->> cause: "+e.getLocalizedMessage()));
			e.printStackTrace();
			throw new myException(e.getLocalizedMessage());
		}
		
	}

	
	/**
	 * sucht im xml-Text nach der struktur message fuer meldungen
	 * @param XML_text
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws myException
	 */
	private void interpret_and_execute_XML_4_messages(String XML_text) throws ParserConfigurationException, SAXException, IOException, myException {
		Vector<LinkedHashMap<String, Vector<String>>> v_messageDefs = new readXML()._set_nameOfStartingNode("block")._set_nameOfBlockToRead(TriggerExecutorInterpretXML.XMLTAG_MESSAGE)._set_xmlText(XML_text).interpret();
		
		DEBUG.System_println("Länge: "+v_messageDefs.size());
		
		for (LinkedHashMap<String, Vector<String>> hm: v_messageDefs) {

			_SIMPLE_MESSAGE message = new _SIMPLE_MESSAGE();
			
			//titel raussuchen
			for (String key: hm.keySet()) {
				if (key.equals(TriggerExecutorInterpretXML.XMLTAG_MESSAGE_TITEL)){
					Vector<String>  v = hm.get(TriggerExecutorInterpretXML.XMLTAG_MESSAGE_TITEL);
					if (v==null) {v=new Vector<>();}
					for (String titel: v) {
						titel = bibReplacer.ReplaceSysvariablesInStrings(titel);
						message._setMessage_title(titel);   //titel sollte nur einmal vorkommen, sonst hat der erste gewonnen
						break;
					}
				}
				
				if (key.equals(TriggerExecutorInterpretXML.XMLTAG_MESSAGE_TEXT)){
					Vector<String>  v = hm.get(TriggerExecutorInterpretXML.XMLTAG_MESSAGE_TEXT);
					if (v==null) {v=new Vector<>();}
					String textgesamt = "";
					for (String text: v) {
						textgesamt=textgesamt+text+"\n";
					}
					textgesamt = bibReplacer.ReplaceSysvariablesInStrings(textgesamt);
					message._setMessage_block(textgesamt);
				}
				
				if (key.equals(TriggerExecutorInterpretXML.XMLTAG_MESSAGE_ID_USER)){
					Vector<String>  v = hm.get(TriggerExecutorInterpretXML.XMLTAG_MESSAGE_ID_USER);
					if (v==null) {v=new Vector<>();}
					boolean has_user=false;
					for (String iduser: v) {
						
						iduser = bibReplacer.ReplaceSysvariablesInStrings(iduser);
						
						Rec20 recUser = new Rec20(_TAB.user);
						try {
							recUser._fill_id(iduser);
							message._add_id_user(iduser);
							has_user = true;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (!has_user) {
						message._add_id_user(bibALL.get_ID_USER());   //falls keiner angegeben ist, dann an sich selbst
					}
				}
			}
			//jetzt starten 
			message._START();
			
			
//			
//			for (Vector<String> v: hm.values()) {
//				DEBUG.System_print(v);
//			}
			
			
		}

		
	}
	
	
	
	
	
	
	/**
	 * sucht im xml-Text nach der struktur message fuer meldungen
	 * @param XML_text
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws myException
	 */
	private void interpret_and_execute_XML_4_email(String XML_text) throws ParserConfigurationException, SAXException, IOException, myException {
		Vector<LinkedHashMap<String, Vector<String>>> v_messageDefs = new readXML()._set_nameOfStartingNode("block")._set_nameOfBlockToRead(TriggerExecutorInterpretXML.XMLTAG_EMAIL)._set_xmlText(XML_text).interpret();
		
		//DEBUG.System_println("Länge: "+v_messageDefs.size());
		
		for (LinkedHashMap<String, Vector<String>> hm: v_messageDefs) {

			RB_Email message = new RB_Email();
			
			
			//die tags durchgehen
			for (String key: hm.keySet()) {
				
				if (key.equals(TriggerExecutorInterpretXML.XMLTAG_EMAIL_ABSENDER)){
					Vector<String>  v = hm.get(TriggerExecutorInterpretXML.XMLTAG_EMAIL_ABSENDER);
					if (v==null) {v=new Vector<>();}
					boolean has_absender = false;
					for (String absender: v) {
						if (new MailAdressChecker(absender).isOK()) {
							absender = bibReplacer.ReplaceSysvariablesInStrings(absender);
							message._absender(absender);   //betreff sollte nur einmal vorkommen, sonst hat der erste gewonnen
							has_absender=true;
							break;
						}
					}
					
					if (!has_absender) {
						throw new myException(this,"Error ActionTrigger ! No From-Adress in Mail is not allowed");
					}
				}

				
				if (key.equals(TriggerExecutorInterpretXML.XMLTAG_EMAIL_BETREFF)){
					Vector<String>  v = hm.get(TriggerExecutorInterpretXML.XMLTAG_EMAIL_BETREFF);
					if (v==null) {v=new Vector<>();}
					boolean has_betreff = false;
					for (String betreff: v) {
						betreff = bibReplacer.ReplaceSysvariablesInStrings(betreff);
						message._betreff(betreff);   //betreff sollte nur einmal vorkommen, sonst hat der erste gewonnen
						has_betreff=true;
						break;
					}
					if (!has_betreff) {
						throw new myException(this,"Error ActionTrigger ! No mail-subject not allowed");
					}
				}
				
				if (key.equals(TriggerExecutorInterpretXML.XMLTAG_EMAIL_TEXT)){
					Vector<String>  v = hm.get(TriggerExecutorInterpretXML.XMLTAG_EMAIL_TEXT);
					if (v==null) {v=new Vector<>();}
					String textgesamt = "";
					for (String text: v) {
						textgesamt=textgesamt+text+"\n";
					}
					textgesamt = bibReplacer.ReplaceSysvariablesInStrings(textgesamt);
					if (S.isEmpty(textgesamt)) {
						throw new myException(this,"Error ActionTrigger ! No mail-text not allowed");
					}
					message._text(textgesamt);
				}
				
				if (key.equals(TriggerExecutorInterpretXML.XMLTAG_EMAIL_TARGET)){
 					Vector<String>  v = hm.get(TriggerExecutorInterpretXML.XMLTAG_EMAIL_TARGET);
					if (v==null) {v=new Vector<>();}
					boolean has_target=false;
					for (String target: v) {
						message._to(target);
						has_target=true;
					}
					if (!has_target) {
						throw new myException(this,"Error ActionTrigger ! Mail must habe even one target-adress");
					}
				}
				
				
			}
			//jetzt starten 
			message._set_table_and_id(_TAB.user, bibALL.get_ID_USER());
			message._save_and_send(SEND_TYPE.CC, bibMSG.MV());
			
			
//			
//			for (Vector<String> v: hm.values()) {
//				DEBUG.System_print(v);
//			}
			
			
		}

		
	}
	
	
	
}
