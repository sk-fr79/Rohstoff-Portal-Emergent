package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectFieldWithParameter;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class WK_SelectfieldParam_ContainerTyp extends MyE2_DB_SelectFieldWithParameter {

	public WK_SelectfieldParam_ContainerTyp(SQLField osqlField) throws myException {
		super (osqlField,	
				" SELECT  CASE WHEN C_ID_CONTAINERTYP IS NOT NULL THEN '* ' ELSE '' END || T.KURZBEZEICHNUNG , T.ID_CONTAINERTYP " +
				" FROM JT_CONTAINERTYP T " +
				" LEFT OUTER JOIN " +
				"  ( " +
				"      SELECT DISTINCT C.ID_CONTAINERTYP AS C_ID_CONTAINERTYP FROM JT_ADR_CONTAINERTYP C  " +
				"       WHERE TO_NCHAR(C.ID_ADRESSE) = TO_NCHAR('#ID_ADRESSE#')" +
				" ) ON C_ID_CONTAINERTYP = T.ID_CONTAINERTYP " +
				" WHERE T.ID_MANDANT = " + bibALL.get_ID_MANDANT() + 
				" ORDER BY CASE WHEN C_ID_CONTAINERTYP IS NOT NULL THEN '*' ELSE '' END , T.CONTAINERINHALT, T.KURZBEZEICHNUNG " +
				"  " 
			   ,false
			   ,false);
		this.AddParameter("#ID_ADRESSE#");
		// default-Adresse 
		
		this.set_cDefineAction_IF_ValueNotFound(MyE2_SelectField.SHOW_ERROR_STRING_IF_VALUE_NOT_FOUND);
		this.SetParameter("#ID_ADRESSE#", "-1");
		
		this.RefreshComboboxFromSQL();

	}

	

}
