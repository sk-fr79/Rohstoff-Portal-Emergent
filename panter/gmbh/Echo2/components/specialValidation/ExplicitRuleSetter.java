package panter.gmbh.Echo2.components.specialValidation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Pattern;

import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.TextArea;
import nextapp.echo2.app.TextField;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIELD_RULE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIELD_RULE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;

/**
 * definiert fuer eine E2_ComponentMap die Regelsaetze aus der Tabelle
 * JT_FIELD_RULE fuer die uebergebene E2_ComponentMAP, den eingeloggten benutzer
 * 
 * @author martin
 * 
 */
public class ExplicitRuleSetter
{
	private String m_sModulKenner = null;
	
	/**
	 * HashMap in der die zu prüfenden Felder abgelegt werden
	 */
	private HashMap<String, Object> m_hmFieldList = null; 
	
	// Reclist mit den Regeld des angegebenen Moduls
	RECLIST_FIELD_RULE  oRecListModulKenner = null;

	/**
	 * 
	 * @author manfred
	 * @date   12.03.2012
	 * @param cMODULE_KENNER
	 * @throws myException
	 */
	public ExplicitRuleSetter( String cMODULE_KENNER) throws myException
	{
		super();
		m_sModulKenner = cMODULE_KENNER;
		
		
		
		m_hmFieldList = new HashMap<String, Object>();
		oRecListModulKenner = new RECLIST_FIELD_RULE();
		

		// in der session existiert eine HashMap, der HashWert gebildet wird aus
		// Fieldname@Tablename(Base- ohne JT_ / JD_)
		// und deren values RECORD_FIELD_RULE - Objekte sind

		RECLIST_FIELD_RULE reclistRules = bibSES.get_hmFieldRules();   //2015-03-13: Martin: verlagert in die bibSES

//		if (reclistRules == null)
//		{
//			reclistRules = new RECLIST_FIELD_RULE("SELECT * FROM " + bibE2.cTO() + ".JT_FIELD_RULE");
//			bibALL.set_hmFieldRules(reclistRules);
//		}
		//jetzt eine reclist erzeugen, die nur diesem modulkenner (oder bei leerem modulkenner - ALLEN) zugeteilt ist
		Iterator<RECORD_FIELD_RULE>  oIter = reclistRules.values().iterator();
		while (oIter.hasNext())
		{
			RECORD_FIELD_RULE  recFieldRule = oIter.next();
			if (S.isEmpty(recFieldRule.get_MODUL_KENNER_cUF_NN("")) || recFieldRule.get_MODUL_KENNER_cUF_NN("").equals(cMODULE_KENNER))
			{
				oRecListModulKenner.ADD(recFieldRule, true);
			}
		}
		}

	
	
	
	/**
	
	
	/**
	 * Beim expliziten RuleSetter müssen die Regeln vom Programm auch explizit angewendet, bzw. abgefragt werden.
	 * Aktuell sind die Texttypen 
	 *  Checkbox, 
	 *  Textfeld, 
	 *  Textarea geprüft
	 * @author manfred
	 * @throws myException 
	 * @date   12.03.2012
	 */
	public void ValidateRules_OnInit() throws myException{
		
		String cALLOW_EDIT = rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.ALLOW_EDIT.toString();
		String cNOT_EMPTY = rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.NOT_EMPTY.toString();
		String cREGEX = rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.REGEX.toString();

		
		
		// zuerst alle durchlaufen und nachschauen, ob eine Regel zum ALLOW_EDIT
		// fuer ein feld vorhanden ist, dann wird sie fuer alle zuerst mal
		// gesperrt
		// und nur fuer die allowedit-benutzer wieder entsperrt
		
		for (String FieldName : m_hmFieldList.keySet()){
			
			String cFieldAddTable = FieldName+ "@" + m_sModulKenner;
			HashMap<String, Vector<RECORD_FIELD_RULE>> hm_Rules4ThisField = this.get_hmRulesVectors(oRecListModulKenner, cFieldAddTable);
			
			// das GUI-Objekt
			Object oObject = m_hmFieldList.get(FieldName);
			
			
			// zuerst pruefen, ob ein allow-edit tag vorhanden ist
			if (hm_Rules4ThisField.get(cALLOW_EDIT).size() > 0)
			{
				boolean bEditAllowd = this.get_bIsMyUserID_IN_Vector(hm_Rules4ThisField.get(cALLOW_EDIT));
				
				if (!bEditAllowd)
				{
					if (oObject instanceof CheckBox){
						((CheckBox)oObject).setEnabled(false);
					} else if (oObject instanceof TextField){
						((TextField)oObject).setEnabled(false);
						((TextField)oObject).setBackground(new E2_ColorDark());
					} else if (oObject instanceof TextArea){
						((TextArea)oObject).setEnabled(false);
						((TextArea)oObject).setBackground(new E2_ColorDark());
					}
				}
			}
		}
		
	}
	
	
	
	
	/**
	 * Beim expliziten RuleSetter müssen die Regeln vom Programm auch explizit angewendet, bzw. abgefragt werden.
	 * Aktuell sind die Texttypen 
	 *  Checkbox, 
	 *  Textfeld, 
	 *  Textarea geprüft
	 * @author manfred
	 * @throws myException 
	 * @date   12.03.2012
	 */
	public MyE2_MessageVector ValidateRules_OnSave() throws myException{
		
		String cALLOW_EDIT = rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.ALLOW_EDIT.toString();
		String cNOT_EMPTY = rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.NOT_EMPTY.toString();
		String cREGEX = rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.REGEX.toString();
		
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		// zuerst alle durchlaufen und nachschauen, ob eine Regel zum ALLOW_EDIT
		// fuer ein feld vorhanden ist, dann wird sie fuer alle zuerst mal
		// gesperrt
		// und nur fuer die allowedit-benutzer wieder entsperrt
		
		for (String FieldName : m_hmFieldList.keySet()){
			
			String cFieldAddTable = FieldName+ "@" + m_sModulKenner;
			HashMap<String, Vector<RECORD_FIELD_RULE>> hm_Rules4ThisField = this.get_hmRulesVectors(oRecListModulKenner, cFieldAddTable);
			
			// das GUI-Objekt
			Object oObject = m_hmFieldList.get(FieldName);
			
			
			//dann pruefen, ob eine not-empty-regel vorhanden ist / unabhaengig vom benutzer
			if (hm_Rules4ThisField.get(cNOT_EMPTY).size() > 0)
			{
				
				boolean bEmpty = true;
				if (oObject instanceof CheckBox){
					bEmpty = false;
				} else if (oObject instanceof TextField){
					bEmpty = ((TextField)oObject).getText().trim().equals("");
				} else if (oObject instanceof TextArea){
					bEmpty = ((TextArea)oObject).getText().trim().equals("");
				}
				
				if (bEmpty){
					mv.add(new MyE2_Alarm_Message("Feld " + FieldName + " darf nicht leer sein!"));
					if (oObject instanceof MyE2_TextField){
						((MyE2_TextField)oObject).show_InputStatus(true);
					} else if (oObject instanceof MyE2_TextArea){
						((MyE2_TextArea)oObject).show_InputStatus(true);
					}
				}
			}
			
			//jetzt regulaere ausdruecke suchen
			Vector<RECORD_FIELD_RULE> vRegExRules = hm_Rules4ThisField.get(cREGEX);
			
			//wenn es regulaere ausdruecke fuer diese feld innerhalb dieses moduls gibt, dann einen inputvalidierer einfuegen
			if (vRegExRules.size()>0)
			{
				String sValue = null;
				
				if (oObject instanceof TextField){
					sValue = ((TextField)oObject).getText();
				} else if (oObject instanceof TextArea){
					sValue = ((TextArea)oObject).getText();
				}
				
				// Prüfen
				MyE2_MessageVector msg = isRegExValid(vRegExRules, sValue, FieldName);
				
				if (!msg.isEmpty()){
					mv.addAll(msg);
					
					if (oObject instanceof MyE2_TextField){
						((MyE2_TextField)oObject).show_InputStatus(true);
					} else if (oObject instanceof MyE2_TextArea){
						((MyE2_TextArea)oObject).show_InputStatus(true);
					}
					
				}
								
			}
			
		}
		
		return mv;
		
	}
	

	
	
	
	/**
	 * fügt ein unabhängiges Objekt einer Liste von zu prüfenden Objekten zu
	 * die Objekte werden mit sFieldname geprüft und sind die IDs die in der Tabelle abgelegt werden. 
	 * @author manfred
	 * @date   12.03.2012
	 * @param sFieldname
	 * @param oField
	 */
	public void addRule( String sFieldname,Object oField){
//		if (oField instanceof CheckBox ||
//			oField instanceof TextField	||
//			oField instanceof TextArea ){
//			this.m_hmFieldList.put(sFieldname, oField);
//		} else {
//			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("ExplicitRuleSetter:addRule::Der Typ des Objekts kann nicht geprüft werden.")));
//		}
		this.m_hmFieldList.put(sFieldname, oField);
	}
	
	
	
	/**
	 * prüft die RegEx-Regeln des Feldes
	 * @author manfred
	 * @date   12.03.2012
	 * @param vRecAllowedRegEx
	 * @param sValue
	 * @param sFieldname
	 * @return
	 * @throws myException
	 */
	private MyE2_MessageVector isRegExValid(Vector<RECORD_FIELD_RULE> vRecAllowedRegEx, String sValue, String sFieldname) throws myException
	{
		MyE2_MessageVector oMV_Rueck = new MyE2_MessageVector();

		//schritt 1: wenn der eintrag leer ist, dann keine pruefung
		String cWert = sValue;
		
		if (S.isFull(cWert))
		{
			boolean bEinAusdruckErfuellt = false;
			
			for (RECORD_FIELD_RULE recREGEX: vRecAllowedRegEx)
			{
				if (S.isFull(recREGEX.get_RULE_cUF_NN("")))
				{
					if (Pattern.matches(recREGEX.get_RULE_cUF_NN(""), cWert))
					{
						bEinAusdruckErfuellt=true;
						break;
					}
				}
			}
			
			if (!bEinAusdruckErfuellt)
			{
				String cPraefix = "Feld:" + sFieldname + ":" + " Erlaubte Feldregeln: ";
				String cInfo = cPraefix;
				for (RECORD_FIELD_RULE recREGEX: vRecAllowedRegEx)
				{
					cInfo=cInfo+(cInfo.equals(cPraefix)?"":" // ")+recREGEX.get_RULE_INFO_cUF_NN(recREGEX.get_RULE_cUF_NN("<keine Info zur Eingaberegel ...>"));
				}
				oMV_Rueck.add(new MyE2_Alarm_Message(new MyE2_String(cInfo,false)));
			}
		}
		
		return oMV_Rueck;
	}
	
	
	
	private boolean get_bIsMyUserID_IN_Vector(Vector<RECORD_FIELD_RULE> vRules) throws myException
	{
		boolean bRueck = false;
		
		for (RECORD_FIELD_RULE recRule: vRules)
		{
			if (recRule.get_ID_USER_cF_NN("-").equals(bibALL.get_ID_USER()))
			{
				bRueck = true;
			}
		}
		
		return bRueck;
	}

	
	
	/**
	 * 
	 * @param cField_ADD_TABLE
	 *            : der feldkenner muss in der form: NAME1@ADRESSE kommen
	 * @return s Hashmap mit einem Vector von den HashTypen: REGEX, ALLOW_EDIT,
	 *         NOT_EMPTY
	 * @throws myException
	 */
	private HashMap<String, Vector<RECORD_FIELD_RULE>> get_hmRulesVectors(RECLIST_FIELD_RULE reclistRules, String cField_ADD_TABLE) throws myException
	{
		Vector<RECORD_FIELD_RULE> vREGEX = new Vector<RECORD_FIELD_RULE>();
		Vector<RECORD_FIELD_RULE> vALLOW_EDIT = new Vector<RECORD_FIELD_RULE>();
		Vector<RECORD_FIELD_RULE> vNOT_EMPTY = new Vector<RECORD_FIELD_RULE>();

		HashMap<String, Vector<RECORD_FIELD_RULE>> hmRueck = new HashMap<String, Vector<RECORD_FIELD_RULE>>();
		hmRueck.put(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.ALLOW_EDIT.toString(), vALLOW_EDIT);
		hmRueck.put(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.NOT_EMPTY.toString(), vNOT_EMPTY);
		hmRueck.put(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.REGEX.toString(), vREGEX);

		for (RECORD_FIELD_RULE recRule : reclistRules.values())
		{
			String sModulkenner = (recRule.get_FIELD_NAME_cUF() + "@" + this.get_BaseTableName(recRule.get_MODUL_KENNER_cUF_NN("")));
			if (sModulkenner.equals(cField_ADD_TABLE))
			{
				if (recRule.get_RULETYPE_cUF_NN("").equals(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.ALLOW_EDIT.toString()))
				{
					vALLOW_EDIT.add(recRule);
				}
				else if (recRule.get_RULETYPE_cUF_NN("").equals(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.NOT_EMPTY.toString()))
				{
					vNOT_EMPTY.add(recRule);
				}
				else if (recRule.get_RULETYPE_cUF_NN("").equals(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.REGEX.toString()))
				{
					vREGEX.add(recRule);
				}
			}
		}
		return hmRueck;
	}

	
	
	private String get_BaseTableName(String cTableName)
	{
		String cRueck = S.NN(cTableName);

		if (cRueck.toUpperCase().startsWith("JD_") || cRueck.toUpperCase().startsWith("JT_"))
		{
			cRueck = cRueck.substring(3);
		}

		return cRueck;
	}

}
