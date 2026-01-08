package panter.gmbh.Echo2.components.specialValidation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
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
public class DynamicRuleSetter
{
	
	
	
	private E2_ComponentMAP oMap = null;

	public DynamicRuleSetter(E2_ComponentMAP Map, String cMODULE_KENNER) throws myException
	{
		super();
		this.oMap = Map;

		// in der session existiert eine HashMap, der HashWert gebildet wird aus
		// Fieldname@Tablename(Base- ohne JT_ / JD_)
		// und deren values RECORD_FIELD_RULE - Objekte sind

		RECLIST_FIELD_RULE reclistRules = bibSES.get_hmFieldRules();         //2015-03-13: Martin: verlagert in die bibSES

//		if (reclistRules == null)
//		{
//			reclistRules = new RECLIST_FIELD_RULE("SELECT * FROM " + bibE2.cTO() + ".JT_FIELD_RULE");
//			bibALL.set_hmFieldRules(reclistRules);
//		}

		
		//jetzt eine reclist erzeugen, die nur diesem modulkenner (oder bei leerem modulkenner - ALLEN) zugeteilt ist
		Iterator<RECORD_FIELD_RULE>  oIter = reclistRules.values().iterator();
		
		RECLIST_FIELD_RULE  oRecListModulKenner = new RECLIST_FIELD_RULE();
		
		while (oIter.hasNext())
		{
			RECORD_FIELD_RULE  recFieldRule = oIter.next();
			if (S.isEmpty(recFieldRule.get_MODUL_KENNER_cUF_NN("")) || recFieldRule.get_MODUL_KENNER_cUF_NN("").equals(cMODULE_KENNER))
			{
				oRecListModulKenner.ADD(recFieldRule, true);
			}
		}
		
		
		
		
		String cALLOW_EDIT = rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.ALLOW_EDIT.toString();
		String cNOT_EMPTY = rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.NOT_EMPTY.toString();
		String cREGEX = rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.REGEX.toString();
		//2013-04-19: pre_defined
		String cPRE_DEFINED = rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.PRE_DEFINED.toString();

		
		// zuerst alle durchlaufen und nachschauen, ob eine Regel zum ALLOW_EDIT
		// fuer ein feld vorhanden ist, dann wird sie fuer alle zuerst mal
		// gesperrt
		// und nur fuer die allowedit-benutzer wieder entsperrt
		for (MyE2IF__Component oComp : this.oMap.values())
		{
			
			boolean bIstStandard_DB_Component = false;
			
			if (oComp instanceof MyE2IF__DB_Component)
			{
				MyE2IF__DB_Component oCompDB = (MyE2IF__DB_Component) oComp;
				{
					if (!oCompDB.get_bIsComplexObject()) // nur nicht komplexe
															// objekte werden
															// erfasst
					{
						bIstStandard_DB_Component = true;
						
						String cFieldAddTable = oCompDB.EXT_DB().get_oSQLField().get_cFieldName() + "@" + this.get_BaseTableName(oCompDB.EXT_DB().get_oSQLField().get_cTableName());

						HashMap<String, Vector<RECORD_FIELD_RULE>> hm_Rules4ThisField = this.get_hmRulesVectors(oRecListModulKenner, cFieldAddTable);

						// zuerst pruefen, ob ein allow-edit tag vorhanden ist
						if (hm_Rules4ThisField.get(cALLOW_EDIT).size() > 0)
						{
							boolean bEditAllowd = this.get_bIsMyUserID_IN_Vector(hm_Rules4ThisField.get(cALLOW_EDIT));
							
							if (!bEditAllowd)
							{
								oComp.EXT().set_bDisabledFromBasic(true);
							}
						}
						
						
						//dann pruefen, ob eine not-empty-regel vorhanden ist / unabhaengig vom benutzer
						if (hm_Rules4ThisField.get(cNOT_EMPTY).size() > 0)
						{
							oCompDB.EXT_DB().get_oSQLField().set_bIsNullableByUserDef_AFTER_INIT_SQLFieldMAP(false);
						}
						
						
						//2013-04-19: dann nachsehen, ob etwas vordefiniert ist
						if (hm_Rules4ThisField.get(cPRE_DEFINED).size() > 0)
						{
							Vector<RECORD_FIELD_RULE> v_recRules4Me = this.filter_VectorRule_4_myUser(hm_Rules4ThisField.get(cPRE_DEFINED));
							
							if (v_recRules4Me.size()>0)
							{
								oCompDB.EXT_DB().get_oSQLField().set_cDefaultValueFormated(v_recRules4Me.get(0).get_RULE_cUF_NN(""));
							}
						}
						//2013-04-19: pre_defined
						
						
						//jetzt regulaere ausdruecke suchen
						Vector<RECORD_FIELD_RULE> vRegExRules = hm_Rules4ThisField.get(cREGEX);
						
						//wenn es regulaere ausdruecke fuer diese feld innerhalb dieses moduls gibt, dann einen inputvalidierer einfuegen
						if (vRegExRules.size()>0)
						{
							oCompDB.EXT().add_Field_Validator_Check_Input_and_MarkFalseValues(new Valid_DBField_RegEx(oCompDB, vRegExRules));
						}
						
						
					}
				}
			}

			//2012-08-13: komplexe felder und andere Sonderfelder werden nur auf bearbeiten-erlaubt geprueft
			if (!bIstStandard_DB_Component)         
			{
				if (oComp instanceof MyE2IF__Component)   
				{
					//dann pruefen, ob die komponente einen hashkey besitzt
					String cHASH_KEY = ((MyE2IF__Component)oComp).EXT().get_C_HASHKEY();
					
					//System.out.println(cHASH_KEY);
					
					if (S.isFull(cHASH_KEY))
					{
						//dann erfolgt die pruefung mit dem pseudo-Tabellenname COMPLEX_FIELD
						String cFieldAddTable = cHASH_KEY+"@"+rulesCONST.KEY_NO_STANDARD_DB_FIELD;

						HashMap<String, Vector<RECORD_FIELD_RULE>> hm_Rules4ThisField = this.get_hmRulesVectors(oRecListModulKenner, cFieldAddTable);

						// zuerst pruefen, ob ein allow-edit tag vorhanden ist
						if (hm_Rules4ThisField.get(cALLOW_EDIT).size() > 0)
						{
							boolean bEditAllowd = this.get_bIsMyUserID_IN_Vector(hm_Rules4ThisField.get(cALLOW_EDIT));
							
							if (!bEditAllowd)
							{
								oComp.EXT().set_bDisabledFromBasic(true);
							}
						}
					}
				}
				
			}
		}
	}

	
	
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
			if ((recRule.get_FIELD_NAME_cUF() + "@" + this.get_BaseTableName(recRule.get_TABLE_NAME_cUF())).equals(cField_ADD_TABLE))
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
				else if (recRule.get_RULETYPE_cUF_NN("").equals(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.PRE_DEFINED.toString()))
				{
					vPRE_DEFINED.add(recRule);
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
