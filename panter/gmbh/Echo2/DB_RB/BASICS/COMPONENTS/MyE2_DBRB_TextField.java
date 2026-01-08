package panter.gmbh.Echo2.DB_RB.BASICS.COMPONENTS;

import nextapp.echo2.app.Font;
import nextapp.echo2.app.LayoutData;
import panter.gmbh.Echo2.DB_RB.BASICS.DBRB_MASK;
import panter.gmbh.Echo2.DB_RB.BASICS.IF_DBRB_Component;
import panter.gmbh.Echo2.Factorys.XXX_StyleFactory;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class MyE2_DBRB_TextField extends MyE2_DB_TextField implements IF_DBRB_Component{

	

	
	
	
	public MyE2_DBRB_TextField(DBRB_MASK  oDBRB_MASK, String fieldName, boolean bAutoAlign,	int iWidthInPixel, int iMaxInputSize, boolean bDisabledFromBasic,Font oFont) throws myException {
		super(oDBRB_MASK.get_SQLFieldDummy(fieldName), bAutoAlign, iWidthInPixel, iMaxInputSize, bDisabledFromBasic,oFont);
	}





	public MyE2_DBRB_TextField(DBRB_MASK  oDBRB_MASK, String fieldName, boolean bAutoAlign, int iWidthInPixel, int iMaxInputSize, boolean bDisabledFromBasic) 	throws myException {
		super(oDBRB_MASK.get_SQLFieldDummy(fieldName), bAutoAlign, iWidthInPixel, iMaxInputSize, bDisabledFromBasic);
	}





	public MyE2_DBRB_TextField(DBRB_MASK  oDBRB_MASK, String fieldName, boolean bAutoAlign,	int iWidthInPixel, LayoutData oLayout, Font oFont) throws myException {
		super(oDBRB_MASK.get_SQLFieldDummy(fieldName), bAutoAlign, iWidthInPixel, oLayout, oFont);
	}





	public MyE2_DBRB_TextField(DBRB_MASK  oDBRB_MASK, String fieldName, boolean bAutoAlign, 	int iWidthInPixel, LayoutData oLayout) throws myException {
		super(oDBRB_MASK.get_SQLFieldDummy(fieldName), bAutoAlign, iWidthInPixel, oLayout);
	}



	public MyE2_DBRB_TextField(DBRB_MASK  oDBRB_MASK, String fieldName, boolean bAutoAlign,	int iWidthInPixel) throws myException {
		super(oDBRB_MASK.get_SQLFieldDummy(fieldName), bAutoAlign, iWidthInPixel);
	}



	public MyE2_DBRB_TextField(DBRB_MASK  oDBRB_MASK, String fieldName, int iWidthInPixel,	XXX_StyleFactory oStyle) throws myException {
		super(oDBRB_MASK.get_SQLFieldDummy(fieldName), iWidthInPixel, oStyle);
	}





	public MyE2_DBRB_TextField(DBRB_MASK  oDBRB_MASK, String fieldName) throws myException {
		super(oDBRB_MASK.get_SQLFieldDummy(fieldName));
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
