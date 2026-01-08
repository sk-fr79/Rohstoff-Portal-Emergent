package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED.RULES;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_FieldSetter_AND_Validator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.FK_CONST;

public class FKR_FB_MASK_SimpleDaughter_OR extends MyE2_DBC_DaughterTable
{


	
	
	public FKR_FB_MASK_SimpleDaughter_OR(	SQLFieldMAP 		fieldMAPMotherTable, 
											E2_ComponentMAP		ocomponentMAP_from_Mother) throws myException
	{
		super();
		
		String[][] databaseFields = FK_CONST.get_DatabaseComparisonColumnsArray();
		
		SQLFieldMAP oFM = new SQLFieldMAP(_DB.FILTER_OR ,bibE2.get_CurrSession());
		oFM.addCompleteTable_FIELDLIST(	_DB.FILTER_OR,
														":"+_DB.FILTER_OR$ID_FILTER_OR+
														":"+_DB.FILTER_OR$ID_FILTER_AND+
														":ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:ID_MANDANT AS DBF:",false,true,"");
		
		
		oFM.add_SQLField(
				new SQLFieldForPrimaryKey(	_DB.FILTER_OR,
											_DB.FILTER_OR$ID_FILTER_OR,
											_DB.FILTER_OR$ID_FILTER_OR,
											new MyE2_String("ID"),bibE2.get_CurrSession(),
											"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_FILTER_OR.NEXTVAL FROM DUAL",true), false);

		oFM.add_SQLField(new SQLFieldJoinOutside(_DB.FILTER_OR,_DB.FILTER_OR$ID_FILTER_AND,_DB.FILTER_OR$ID_FILTER_AND,
											new MyE2_String("ID-Mail-aus-Mask"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField(_DB.FILTER_AND$ID_FILTER_AND)), false);

		oFM.get_(_DB.FILTER_OR$CONDITION_LEFT).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oFM.get_(_DB.FILTER_OR$CONDITION_LEFT_TYPE).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oFM.get_(_DB.FILTER_OR$CONDITION_RIGHT).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oFM.get_(_DB.FILTER_OR$CONDITION_RIGHT_TYPE).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oFM.get_(_DB.FILTER_OR$CONDITION_OP).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oFM.get_(_DB.FILTER_OR$CONDITION_POSITIVE).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		oFM.get_(_DB.FILTER_OR$CONDITION_POSITIVE).set_cDefaultValueFormated("Y");
		
		oFM.initFields();
		
		E2_ComponentMAP 			oMapOrConditions = 			new E2_ComponentMAP(oFM);

		//def of components
		MyE2_ButtonMarkForDelete 	oButtonForDel = 		new MyE2_ButtonMarkForDelete();
		
//		MyE2_DB_TextArea			tf_ConditionLeft = 		new MyE2_DB_TextArea(oFM.get_SQLField(_DB.FILTER_OR$CONDITION_LEFT),300,3);
		FKR_FB_CombiComp			tf_ConditionLeft = 		new FKR_FB_CombiComp(oFM.get_SQLField(_DB.FILTER_OR$CONDITION_LEFT), oFM.get_SQLField(_DB.FILTER_OR$CONDITION_LEFT_TYPE), databaseFields, 300,3);

//		MyE2_DB_TextArea			tf_ConditionRight = 	new MyE2_DB_TextArea(oFM.get_SQLField(_DB.FILTER_OR$CONDITION_RIGHT),300,3);
		FKR_FB_CombiComp			tf_ConditionRight = 	new FKR_FB_CombiComp(oFM.get_SQLField(_DB.FILTER_OR$CONDITION_RIGHT), oFM.get_SQLField(_DB.FILTER_OR$CONDITION_RIGHT_TYPE), databaseFields, 300,3);
		MyE2_DB_SelectField         sf_ConditionTypeLeft = 	new MyE2_DB_SelectField(oFM.get_SQLField(_DB.FILTER_OR$CONDITION_LEFT_TYPE), FK_CONST.get_ConditionTypesLeftDrowDownArray(), false);
		MyE2_DB_SelectField         sf_ConditionTypeRight= 	new MyE2_DB_SelectField(oFM.get_SQLField(_DB.FILTER_OR$CONDITION_RIGHT_TYPE), FK_CONST.get_ConditionTypesRightDrowDownArray(), false);
		MyE2_DB_SelectField         sf_Operator = 			new MyE2_DB_SelectField(oFM.get_SQLField(_DB.FILTER_OR$CONDITION_OP), FK_CONST.get_CompareValuesDrowDownArray(), false);
		MyE2_DB_CheckBox            cb_ConditionPositive =  new MyE2_DB_CheckBox(oFM.get_SQLField(_DB.FILTER_OR$CONDITION_POSITIVE));

		
		tf_ConditionLeft.setStatusAktuell(sf_ConditionTypeLeft.get_ActualWert());
		
		//def of col-width's
		tf_ConditionLeft.EXT().set_oColExtent(new Extent(300));
		tf_ConditionRight.EXT().set_oColExtent(new Extent(300));
		sf_ConditionTypeLeft.EXT().set_oColExtent(new Extent(80));
		sf_ConditionTypeRight.EXT().set_oColExtent(new Extent(80));
		sf_Operator.EXT().set_oColExtent(new Extent(50));
		cb_ConditionPositive.EXT().set_oColExtent(new Extent(50));

		//def of list
		oMapOrConditions.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
		oMapOrConditions.add_Component(sf_ConditionTypeLeft,new MyE2_String("LINKE Seite (Typ)"));
		oMapOrConditions.add_Component(tf_ConditionLeft,new MyE2_String("LINKE Seite (Wert)"));
		oMapOrConditions.add_Component(sf_Operator,new MyE2_String("VERGLEICH"));
		oMapOrConditions.add_Component(sf_ConditionTypeRight,new MyE2_String("RECHTE Seite (Typ)"));
		oMapOrConditions.add_Component(tf_ConditionRight,new MyE2_String("RECHTE Seite (Vergleichswert)"));
		oMapOrConditions.add_Component(cb_ConditionPositive,new MyE2_String("Positiv?"));
			
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		//this.set_oContainerExScrollHeight(new Extent(300));
		this.set_to_100_percent();
		this.set_oContainerExScrollHeight(new Extent(40,Extent.PERCENT));
		
		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
							ocomponentMAP_from_Mother,
							oMapOrConditions,
							null);
		
		this.EXT().add_FieldSetters_AND_Validator__BeforeReadInputMAP(new XX_FieldSetter_AND_Validator() {
			
			@Override
			public MyE2_MessageVector isValid(String cSTATUS_MAP, MyE2EXT__Component EXT_own) throws myException
			{
				MyE2_MessageVector oMV = new MyE2_MessageVector();
				int iRules = ((MyE2_DBC_DaughterTable)EXT_own.get_oComponentThisBelongsTo()).get_vE2_ComponentMAPs_NewAndEdit_WithoutDeleteMarker().size();
				if (iRules==0) {
					oMV.add(new MyE2_Alarm_Message(new MyE2_String("Ein Block MUSS erfaﬂt werden !!!")));
				}
				return oMV;
			}
		});
		
		// Action Agent for TypeLeft
		sf_ConditionTypeLeft.add_oActionAgent(new ownActionAgentSetStatusRow(_DB.FILTER_OR$CONDITION_LEFT));
		sf_ConditionTypeRight.add_oActionAgent(new ownActionAgentSetStatusRow(_DB.FILTER_OR$CONDITION_RIGHT));
	}

	/** Fires when the CONDIION_TYPE_LEFT changes */
	private class ownActionAgentSetStatusRow extends XX_ActionAgent {
		private String dependentField;
		public ownActionAgentSetStatusRow(String dep) {
			dependentField = dep;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_ComponentMAP  ownMAP = ((MyE2_DB_SelectField)oExecInfo.get_MyActionEvent().getSource()).EXT().get_oComponentMAP();
			
			MyE2_DB_SelectField selTYP= ((MyE2_DB_SelectField)oExecInfo.get_MyActionEvent().getSource());
			FKR_FB_CombiComp combi = (FKR_FB_CombiComp)ownMAP.get__Comp(dependentField);
			combi.setStatusAktuell(selTYP.get_ActualWert());
		}
		
	}
	
}
