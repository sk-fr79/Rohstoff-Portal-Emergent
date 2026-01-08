package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.FIELD_RULES.INTERACTIVE_SETTER_VALIDATOR;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.specialValidation.rulesCONST;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;

public class SET_FIELDS_WITH_REGELTYPE extends XX_MAP_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.interne_settings(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.interne_settings(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.interne_settings(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.interne_settings(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.interne_settings(oMAP, ActionType, oExecInfo, oInputMap);
	}

	private MyE2_MessageVector  interne_settings(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		String REGELTYP = oMAP.get_cActualDBValueFormated(_DB.FIELD_RULE$RULETYPE);
				
		if (S.isFull(REGELTYP) && rulesCONST.hmFieldRules4UserDropdowns.containsKey(REGELTYP)) {
				
			//ausgangszustand herstellen
			oMAP.get_oSQLFieldMAP().get_(_DB.FIELD_RULE$RULE).set_bIsNullableByUserDef_ForInteractiveModules(true);
//			oMAP.get_oSQLFieldMAP().get_(_DB.FIELD_RULE$ID_USER).set_bIsNullableByUserDef_ForInteractiveModules(true);
			
			oMAP.set_ActiveADHOC(_DB.FIELD_RULE$RULE, true, false);    //feld einschalten 

			//zwei varianten fuer das feld regel: muss leer sein oder darf nicht leer sein
			if (REGELTYP.equals(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.PRE_DEFINED.toString())) {
				
				//dann MUSS das RULE-Feld gefuellt sein
				oMAP.get_oSQLFieldMAP().get_(_DB.FIELD_RULE$RULE).set_bIsNullableByUserDef_ForInteractiveModules(false);
				oMAP.set_ActiveADHOC(_DB.FIELD_RULE$RULE, true, false);
				
			} else if (REGELTYP.equals(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.REGEX.toString())){
				
				//dann MUSS das RULE-Feld gefuellt sein
				oMAP.get_oSQLFieldMAP().get_(_DB.FIELD_RULE$RULE).set_bIsNullableByUserDef_ForInteractiveModules(false);
				oMAP.set_ActiveADHOC(_DB.FIELD_RULE$RULE,  true, false);
				
			} else if (REGELTYP.equals(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.NOT_EMPTY.toString())){
				
				//dann darf im RULE-Feld nix stehen
				oMAP.get_oSQLFieldMAP().get_(_DB.FIELD_RULE$RULE).set_bIsNullableByUserDef_ForInteractiveModules(true);
				oMAP.set_ActiveADHOC(_DB.FIELD_RULE$RULE,  false, true);
				
			} else if (REGELTYP.equals(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.ALLOW_EDIT.toString())){

				//dann darf im RULE-Feld nix stehen
				oMAP.get_oSQLFieldMAP().get_(_DB.FIELD_RULE$RULE).set_bIsNullableByUserDef_ForInteractiveModules(true);
				oMAP.set_ActiveADHOC(_DB.FIELD_RULE$RULE,  false, true);
				
				
			}

		}
		
		return oMV;
	}
	
	
}
