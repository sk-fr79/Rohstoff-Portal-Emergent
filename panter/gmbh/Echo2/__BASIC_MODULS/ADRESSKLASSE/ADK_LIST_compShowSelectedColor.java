package panter.gmbh.Echo2.__BASIC_MODULS.ADRESSKLASSE;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSKLASSE_DEF;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSKLASSE_DEF_EXT;

public class ADK_LIST_compShowSelectedColor extends MyE2_DB_PlaceHolder {

	public ADK_LIST_compShowSelectedColor(SQLField osqlField) throws myException {
		super(osqlField);
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {

		this.removeAll();
		this.setSize(1);
		RECORD_ADRESSKLASSE_DEF recDef = (RECORD_ADRESSKLASSE_DEF)this.EXT().get_oComponentMAP().get_Record4MainTable();
		
		this.add(new RECORD_ADRESSKLASSE_DEF_EXT(recDef).grid_with_color(100, 20,new E2_ColorBase()),E2_INSETS.I(1,1,1,1));
		
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			return new ADK_LIST_compShowSelectedColor(this.EXT_DB().get_oSQLField());
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.getOriginalMessage());
		}
	}
	
}
