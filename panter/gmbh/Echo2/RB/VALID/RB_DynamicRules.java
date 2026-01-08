package panter.gmbh.Echo2.RB.VALID;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Complex;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.specialValidation.rulesCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIELD_RULE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIELD_RULE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;

public class RB_DynamicRules {
	
	private LinkedHashMap<RB_KF, RB_DynamicRule> hm_DynamicRules = new LinkedHashMap<RB_KF, RB_DynamicRule>();
	
	public RB_DynamicRules(RB_ComponentMap rb_Mask, String cMODULE_KENNER) throws myException {
		super();

		// in der session existiert eine HashMap, der HashWert gebildet wird aus
		// Fieldname@Tablename(Base- ohne JT_ / JD_)
		// und deren values RECORD_FIELD_RULE - Objekte sind

		RECLIST_FIELD_RULE reclistRules = bibSES.get_hmFieldRules();

		//die hier gueltigen rules raussuchen
		RECLIST_FIELD_RULE  reclistFieldRules_Gueltig = new RECLIST_FIELD_RULE();
			
		for (RECORD_FIELD_RULE rule : reclistRules) {
			if (S.isEmpty(rule.get_MODUL_KENNER_cUF_NN("")) || rule.get_MODUL_KENNER_cUF_NN("").equals(cMODULE_KENNER)) {
				reclistFieldRules_Gueltig.ADD(rule, true);
				//DEBUG.System_println("regel:"+rule.get_ID_FIELD_RULE_cUF());
			}
		}
			
			
		String cALLOW_EDIT = rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.ALLOW_EDIT.toString();
		String cNOT_EMPTY = rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.NOT_EMPTY.toString();
		String cREGEX = rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.REGEX.toString();
		String cPRE_DEFINED = rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.PRE_DEFINED.toString();

			
		// zuerst alle durchlaufen und nachschauen, ob eine Regel zum ALLOW_EDIT
		// fuer ein feld vorhanden ist, dann wird sie fuer alle zuerst mal
		// gesperrt
		// und nur fuer die allowedit-benutzer wieder entsperrt
		for (MyE2IF__Component oComp : rb_Mask.values())  {
			
			RB_KF key = oComp.EXT().get_RB_K();
			if (key==null) {
				throw new myException(this,"Component was not registered !!");
			}
			
			//leere RB_DynamicRule hinterlegen
			RB_DynamicRule rule = new RB_DynamicRule();
			this.hm_DynamicRules.put(key, rule);
			
			//jetzt die rule fuellen
			if (oComp instanceof IF_RB_Component) {
				IF_RB_Component rbComp = (IF_RB_Component) oComp; 
				if (!(rbComp instanceof IF_RB_Component_Complex)) 	{    // nur nicht komplexe objekte werden erfasst
					//fieldname und tablename rauskriegen
					String fieldName = rbComp.rb_KF().FIELDNAME();
					String tableName = rbComp.rb_ComponentMap_this_belongsTo().rb_TABLENAME();
							
					String cFieldAddTable = fieldName + "@" + tableName;

					//alle regeln zu einem feld raussuchen
					HashMap<String, Vector<RECORD_FIELD_RULE>> hm_Rules4ThisField = this.get_hmRulesVectors(reclistFieldRules_Gueltig, cFieldAddTable);

					//DEBUG.System_println(cFieldAddTable+":"+hm_Rules4ThisField.size());
					
					
					// zuerst pruefen, ob ein allow-edit tag vorhanden ist
					if (hm_Rules4ThisField.get(cALLOW_EDIT).size() > 0) {
						boolean bEditAllowd4user = this.get_bIsMyUserID_IN_Vector(hm_Rules4ThisField.get(cALLOW_EDIT));
								
						if (!bEditAllowd4user) {
							rule.set_AllowEdit(false);
						}
					}
					
					//dann pruefen, ob eine not-empty-regel vorhanden ist / unabhaengig vom benutzer
					if (hm_Rules4ThisField.get(cNOT_EMPTY).size() > 0) {
						rule.set_AllowEmpty(false);
					}
							
					//dann nachsehen, ob etwas vordefiniert ist
					if (hm_Rules4ThisField.get(cPRE_DEFINED).size() > 0) {
						Vector<RECORD_FIELD_RULE> v_recRules4Me = this.filter_VectorRule_4_myUser(hm_Rules4ThisField.get(cPRE_DEFINED));
								
						if (v_recRules4Me.size()>0)	{
							rule.set_Predefined(v_recRules4Me.get(0).get_RULE_cUF_NN(""));
						}
					}

					if (hm_Rules4ThisField.get(cREGEX).size() > 0) {
						//jetzt regulaere ausdruecke suchen
						Vector<RECORD_FIELD_RULE> vRegExRules = this.filter_VectorRule_4_myUser(hm_Rules4ThisField.get(cREGEX));
								
						//wenn es regulaere ausdruecke fuer diese feld innerhalb dieses moduls gibt, dann einen inputvalidierer einfuegen
						if (vRegExRules.size()>0) {
							rule.get_vCompVALIDATOR_4_INPUT().add(new RB_Validator_FieldRules_RegEx(vRegExRules));
						}
					}
					
				} else if (rbComp instanceof IF_RB_Component_Complex) {
					//fieldname und tablename rauskriegen
					String fieldName = rbComp.rb_KF().HASHKEY();
					String tableName = rbComp.rb_ComponentMap_this_belongsTo().rb_TABLENAME();
							
					String cFieldAddTable = fieldName + "@" + tableName;
					
					//alle regeln zu einem feld raussuchen
					HashMap<String, Vector<RECORD_FIELD_RULE>> hm_Rules4ThisField = this.get_hmRulesVectors(reclistFieldRules_Gueltig, cFieldAddTable);
					
					// zuerst pruefen, ob ein allow-edit tag vorhanden ist
					if (hm_Rules4ThisField.get(cALLOW_EDIT).size() > 0) {
						boolean bEditAllowd4user = this.get_bIsMyUserID_IN_Vector(hm_Rules4ThisField.get(cALLOW_EDIT));
								
						if (!bEditAllowd4user) {
							rule.set_AllowEdit(false);
						}
					}
				}
			}

		}
	}
		
		
		
	


	public RB_DynamicRule get(RB_KF key) {
		return this.hm_DynamicRules.get(key);
	}
	

	
	
	
	
	/*
	 * sucht aus einem Rules-Vector alle raus, die fuer einen benutzer oder fuer alle benutzer gelten
	 */
	private Vector<RECORD_FIELD_RULE> filter_VectorRule_4_myUser(Vector<RECORD_FIELD_RULE> vRules)  throws myException
	{
		Vector<RECORD_FIELD_RULE> vRueck = new Vector<RECORD_FIELD_RULE>();
		
		for (RECORD_FIELD_RULE recRule: vRules)
		{
			//ein benutzer kann direkt genannt werden, oder die regel gilt fuer alle
			if (recRule.get_ID_USER_cUF_NN("-").equals(bibALL.get_ID_USER()) || 
				S.isEmpty(recRule.get_ID_USER_cUF_NN("")))
			{
				vRueck.add(recRule);
			}
		}
		
		return vRueck;
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
		//2013-04-19: neue regel: 
		Vector<RECORD_FIELD_RULE> vPRE_DEFINED = new Vector<RECORD_FIELD_RULE>();

		HashMap<String, Vector<RECORD_FIELD_RULE>> hmRueck = new HashMap<String, Vector<RECORD_FIELD_RULE>>();
		hmRueck.put(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.ALLOW_EDIT.toString(), vALLOW_EDIT);
		hmRueck.put(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.NOT_EMPTY.toString(), vNOT_EMPTY);
		hmRueck.put(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.REGEX.toString(), vREGEX);
		hmRueck.put(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.PRE_DEFINED.toString(), vPRE_DEFINED);

		for (RECORD_FIELD_RULE recRule : reclistRules.values())
		{
			if ((recRule.get_FIELD_NAME_cUF() + "@" + recRule.get_TABLE_NAME_cUF()).equals(cField_ADD_TABLE)) { 
 				if (recRule.get_RULETYPE_cUF_NN("").equals(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.ALLOW_EDIT.toString())) {
					vALLOW_EDIT.add(recRule);
				} else if (recRule.get_RULETYPE_cUF_NN("").equals(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.NOT_EMPTY.toString())) {
					vNOT_EMPTY.add(recRule);
				} else if (recRule.get_RULETYPE_cUF_NN("").equals(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.REGEX.toString())) {
					vREGEX.add(recRule);
				} else if (recRule.get_RULETYPE_cUF_NN("").equals(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.PRE_DEFINED.toString())){
					vPRE_DEFINED.add(recRule);
				}
			}
		}
		return hmRueck;
	}

//	private String get_BaseTableName(String cTableName)
//	{
//		String cRueck = S.NN(cTableName);
//
//		if (cRueck.toUpperCase().startsWith("JD_") || cRueck.toUpperCase().startsWith("JT_"))
//		{
//			cRueck = cRueck.substring(3);
//		}
//
//		return cRueck;
//	}
//	
	
	
	private boolean get_bIsMyUserID_IN_Vector(Vector<RECORD_FIELD_RULE> vRules) throws myException
	{
		boolean bRueck = false;
		
		for (RECORD_FIELD_RULE recRule: vRules)
		{
			if (recRule.get_ID_USER_cUF_NN("-").equals(bibALL.get_ID_USER()))
			{
				bRueck = true;
			}
		}
		
		return bRueck;
	}

	
	
	/**
	 * diese rules auf eine maske anwenden 
	 * @param mask
	 * @throws myException
	 */
	public void rb_execute_setting_editAllow(RB_ComponentMap  mask) throws myException {
		
		for (MyE2IF__Component comp: mask.values()) {
			RB_KF key = comp.EXT().get_RB_K();
			
			if (this.get(key)!=null) {
				if (!this.get(key).is_AllowEdit()) {
					//ueberstimmt alle vorigen settings
					comp.set_bEnabled_For_Edit(false);
				}
			}
			
		}
		
		
	}
	
}
