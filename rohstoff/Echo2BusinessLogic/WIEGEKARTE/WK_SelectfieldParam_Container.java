package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectFieldWithParameter;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class WK_SelectfieldParam_Container extends MyE2_DB_SelectFieldWithParameter {

	public WK_SelectfieldParam_Container(SQLField osqlField) throws myException {
		super (	osqlField,
			   " SELECT C.CONTAINER_NR , C.ID_CONTAINER" +
			   " FROM JT_CONTAINER C " +
			   " WHERE to_nchar(C.ID_CONTAINERTYP) = case when to_nchar('#ID_CONTAINERTYP#') != to_nchar('-1') then to_nchar('#ID_CONTAINERTYP#') else to_nchar(C.ID_CONTAINERTYP) END " +
			   " ORDER BY C.CONTAINER_NR "
			   ,false
			   ,false);
		this.AddParameter("#ID_CONTAINERTYP#");
		this.set_cDefineAction_IF_ValueNotFound(MyE2_SelectField.SHOW_EMPTY_STRING_IF_VALUE_NOT_FOUND);
		this.SetParameter("#ID_CONTAINERTYP#", "-1");
		
		this.RefreshComboboxFromSQL();

	}

	

}
