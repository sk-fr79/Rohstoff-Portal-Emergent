package panter.gmbh.Echo2.ListAndMask.List.calculation;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class Test_CalcButton extends CALC_Button {

	public Test_CalcButton(E2_NavigationList oNaviList) throws myException {
		super(E2_ResourceIcon.get_RI("calc_mini.png"));
		this._SET_NAVILIST_THIS_BELONGS_TO(oNaviList);
	}

	@Override
	public CALC_Container_IF _GENERATE_Container() throws myException {
		CALC_POPUP_Window_4_NAVILIST  oCalcContainer = new CALC_POPUP_Window_4_NAVILIST();
		
		oCalcContainer._SET_CONTAINER_TEXT4TITELBAR(new MyE2_String("Summation"));
		oCalcContainer._SET_CONTAINER_HEADLINE(new MyE2_String("Hier einige Testsummen ..."));
		
		oCalcContainer._GET_CALC_RULES().add(new CALC_Rule_AnzahlAuswahl());
		oCalcContainer._GET_CALC_RULES().add(new CALC_Rule_SUMMATION_LIST_COL(this._GET_NAVILIST_THIS_BELONGS_TO().get_oComponentMAP__REF().get__DBComp(_DB.ARTIKEL$ID_ARTIKEL)));
		oCalcContainer._GET_CALC_RULES().add(new CALC_Rule_InterpretVisibleLabel(this._GET_NAVILIST_THIS_BELONGS_TO().get_oComponentMAP__REF().get__DBComp(_DB.ARTIKEL$MENGENDIVISOR)));
		
		CALC_Rule_InterpretVisibleLabel oCalcRule = new CALC_Rule_InterpretVisibleLabel(this._GET_NAVILIST_THIS_BELONGS_TO().get_oComponentMAP__REF().get__DBComp(_DB.ARTIKEL$ANR1));
		oCalcRule.set_iDECIMALNUMBERS(0);
		oCalcRule.set_cINFOTEXT(new MyE2_String("Die Summe der ANR1 ??"));
		oCalcContainer._GET_CALC_RULES().add(oCalcRule);
		
		return oCalcContainer;
	}


}
