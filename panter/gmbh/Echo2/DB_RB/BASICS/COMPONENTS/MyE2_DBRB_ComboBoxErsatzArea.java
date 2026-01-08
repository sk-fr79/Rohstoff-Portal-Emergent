package panter.gmbh.Echo2.DB_RB.BASICS.COMPONENTS;

import panter.gmbh.Echo2.DB_RB.BASICS.DBRB_MASK;
import panter.gmbh.Echo2.DB_RB.BASICS.IF_DBRB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatzArea;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class MyE2_DBRB_ComboBoxErsatzArea extends MyE2_DB_ComboBoxErsatzArea implements IF_DBRB_Component{

	public MyE2_DBRB_ComboBoxErsatzArea(DBRB_MASK  oDBRB_MASK,  String cFieldName, String cSQLQuery) throws myException {
		super(oDBRB_MASK.get_SQLFieldDummy(cFieldName), cSQLQuery);
	}

	public MyE2_DBRB_ComboBoxErsatzArea(DBRB_MASK  oDBRB_MASK,  String cFieldName, String[][] aDefArray) throws myException {
		super(oDBRB_MASK.get_SQLFieldDummy(cFieldName), aDefArray);
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask(MyRECORD oRec) throws myException {
		this.set_cActualMaskValue(oRec.get_FormatedValue(this.get_FIELDNAME()));
		this.EXT_DB().set_cLASTActualDBValueFormated(this.get_FIELDNAME());
	}

	@Override
	public String get_FIELDNAME() throws myException {
		return this.EXT_DB().get_oSQLField().get_cFieldLabel();
	}


}
