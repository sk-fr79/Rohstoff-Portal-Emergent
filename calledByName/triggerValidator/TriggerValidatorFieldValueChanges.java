package calledByName.triggerValidator;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import calledByName.triggerExecuter.TriggerExecutorInterpretXML;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.IF_trigger_validator;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.Rec20_trigger_action_def;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.Rec20_trigger_action_log;
import panter.gmbh.basics4project.BasicTools.readXML;
import panter.gmbh.basics4project.DB_ENUMS.TRIGGER_ACTION_DEF;
import panter.gmbh.basics4project.DB_ENUMS.TRIGGER_ACTION_LOG;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;



/**
 * dummy-trigger
 * @author martin
 *
 */
public class TriggerValidatorFieldValueChanges implements IF_trigger_validator{

	//tags fuer messages
	private static String XMLTAG_VALID = 			"valid";
	private static String XMLTAG_VERGLEICH = 		"vergleich";
	private static String XMLTAG_WERT_ALT = 		"wertalt";
	private static String XMLTAG_WERT_NEU = 		"wertneu";
	private static String XMLTAG_VERGLEICHSTYP = 	"vergleichstyp";
	
	//erlaubt datentypen zum vergleichen
	public enum FIELDTYPE_VALID {
		DATE, LONG, CHAR;
		
		public static boolean is_type_correct(String typ) {
			boolean b_ret = false;
			
			for (FIELDTYPE_VALID t: FIELDTYPE_VALID.values()) {
				if (t.name().equals(typ)) {
					b_ret = true;
				}
			}
			return b_ret;
		}
	}

	@Override
	public boolean isValid(Rec20_trigger_action_log trigger_log, Rec20_trigger_action_def trigger_def, MyE2_MessageVector mv) throws myException {
		boolean b_ret = false;
		
		if (S.isEmpty(trigger_def.get_ufs_dbVal(TRIGGER_ACTION_DEF.field_name))) {
			return false; 		//hier wirkt der trigger nicht
		}
		
		try {

			String db_val_vorher = trigger_log.get_ufs_dbVal(TRIGGER_ACTION_LOG.val_before,"");
			String db_val_nachher = trigger_log.get_ufs_dbVal(TRIGGER_ACTION_LOG.val_after,"");
			
			
			String XML_text = trigger_def.get_ufs_dbVal(TRIGGER_ACTION_DEF.execution_code);
			
			Vector<LinkedHashMap<String, Vector<String>>> v_xmlDefs = new readXML()
					._set_nameOfStartingNode(TriggerValidatorFieldValueChanges.XMLTAG_VALID)
					._set_nameOfBlockToRead(TriggerValidatorFieldValueChanges.XMLTAG_VERGLEICH)._set_xmlText(XML_text).interpret();
			
			
			//jetzt die werte aus der xml-hash lesen
			LinkedHashMap<String, Vector<String>> hmXML = v_xmlDefs.get(0);
			if (hmXML==null) {
				mv.add_MESSAGE(new MyE2_Alarm_Message("SYSTEM-Error: XML-Validator (1): No correct Validationblock found "));
				throw new myException(this, "SYSTEM-Error: XML-Validator (1): No correct Validationblock found ");
			}

			String def_val_vorher = "0";
			String def_val_nachher = "1";
			String typ = FIELDTYPE_VALID.CHAR.name();
			
			if (hmXML.get(TriggerValidatorFieldValueChanges.XMLTAG_WERT_ALT)!=null && hmXML.get(TriggerValidatorFieldValueChanges.XMLTAG_WERT_ALT).size()==1) {
				def_val_vorher = hmXML.get(TriggerValidatorFieldValueChanges.XMLTAG_WERT_ALT).get(0);
					
				if (hmXML.get(TriggerValidatorFieldValueChanges.XMLTAG_WERT_NEU)!=null && hmXML.get(TriggerValidatorFieldValueChanges.XMLTAG_WERT_NEU).size()==1) {
					def_val_nachher = hmXML.get(TriggerValidatorFieldValueChanges.XMLTAG_WERT_NEU).get(0);
					
					if (hmXML.get(TriggerValidatorFieldValueChanges.XMLTAG_VERGLEICHSTYP)!=null && hmXML.get(TriggerValidatorFieldValueChanges.XMLTAG_VERGLEICHSTYP).size()==1) {
						typ = hmXML.get(TriggerValidatorFieldValueChanges.XMLTAG_VERGLEICHSTYP).get(0);
						
						if (FIELDTYPE_VALID.is_type_correct(typ)) {
							if (typ.equals(FIELDTYPE_VALID.CHAR)) {
								if (def_val_nachher.equals(db_val_vorher) && def_val_nachher.equals(db_val_nachher)) {
									b_ret = true;
								}
							} else if (typ.equals(FIELDTYPE_VALID.DATE)) {
								//umwandlung in vergleichbare typen (Format: YYYY-MM-DD)
								String vgl_txt_vorher = "";
								String vgl_txt_nachher = "";
								String db_txt_vorher = 	(db_val_vorher+ "           ").substring(0, 10).trim();
								String db_txt_nachher = (db_val_nachher+"           ").substring(0, 10).trim();
								
								
								MyDate vgl_date_vorher = new MyDate(def_val_vorher);
								MyDate vgl_date_nachher = new MyDate(def_val_nachher);
								
								if (vgl_date_vorher.isOK()) {
									vgl_txt_vorher=vgl_date_vorher.get_cDBFormatErgebnis();
								}
								if (vgl_date_nachher.isOK()) {
									vgl_txt_nachher=vgl_date_nachher.get_cDBFormatErgebnis();
								}
								if (vgl_txt_vorher.equals(db_txt_vorher) && vgl_txt_nachher.equals(db_txt_nachher)) {
									b_ret = true;
								}
							} else if (typ.equals(FIELDTYPE_VALID.LONG)) {
								//umwandlung in vergleichbare typen (Format: 1000)
								String vgl_txt_vorher = "";
								String vgl_txt_nachher = "";
								String db_txt_vorher = 	db_val_vorher;
								String db_txt_nachher = db_val_nachher;
								
								MyLong vgl_long_vorher = new MyLong(def_val_vorher);
								MyLong vgl_long_nachher = new MyLong(def_val_nachher);
								
								if (vgl_long_vorher.isOK()) {
									vgl_txt_vorher=vgl_long_vorher.get_cUF_LongString();
								}
								if (vgl_long_nachher.isOK()) {
									vgl_txt_nachher=vgl_long_nachher.get_cUF_LongString();
								}
								if (vgl_txt_vorher.equals(db_txt_vorher) && vgl_txt_nachher.equals(db_txt_nachher)) {
									b_ret = true;
								}
							}
							
						} else {
							mv.add_MESSAGE(new MyE2_Alarm_Message("SYSTEM-Error: XML-Validator (4): <vergleichstyp> <"+typ+"> is not allowed: only: DATE, LONG, FLOAT, CHAR" ));
							throw new myException(this, "SYSTEM-Error: XML-Validator (4): <vergleichstyp> <"+typ+"> is not allowed: only: DATE, LONG, FLOAT, CHAR" );
						}
						
					} 
					
					
				} else {
					mv.add_MESSAGE(new MyE2_Alarm_Message("SYSTEM-Error: XML-Validator (3): No <wertneu> found"));
					throw new myException(this, "SYSTEM-Error: XML-Validator (3): No correct Validationblock found ");
				}
					
					
			
			} else {
				mv.add_MESSAGE(new MyE2_Alarm_Message("SYSTEM-Error: XML-Validator (2): No <wertalt> found"));
				throw new myException(this, "SYSTEM-Error: XML-Validator (1): No correct Validationblock found ");
			}
			
			 
			
			
			
			
			
			
			
			
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

		
		return b_ret;
	}

}
