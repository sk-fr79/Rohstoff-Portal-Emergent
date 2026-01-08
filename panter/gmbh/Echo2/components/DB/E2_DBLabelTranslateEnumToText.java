/**
 * panter.gmbh.Echo2.components.DB
 * @author martin
 * @date 17.12.2018
 * 
 */
package panter.gmbh.Echo2.components.DB;

import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * @author martin
 * @date 17.12.2018
 * allgemeine klasse um einen JD_USER - eintrag in der liste anzuzeigen.
 * uebergeben wird ein String mit der ID, zurueck kommt ein String aus mehreren feldinhalten
 */
@SuppressWarnings("rawtypes")
public class E2_DBLabelTranslateEnumToText extends E2_DBActiveLabelForLists {

	private IF_enum_4_db_specified  	m_enum = null;;
	private String  				   	valWhenEmpty = "-";
	private String  				   	valWhenError = "<Err>";
	/**
	 * @author martin
	 * @date 17.12.2018
	 *
	 * @param osqlField
	 * @throws myException
	 */
	public E2_DBLabelTranslateEnumToText(SQLField osqlField) throws myException {
		super(osqlField);
	}

	@Override
	public void set_cActualMaskValue(String enumbDbVal) throws myException {
		
		
		if (this.m_enum==null || m_enum.get_dd_Array(false).length==0) {
			this.setText(valWhenError);
		} else {
			String[][]  arr = m_enum.get_dd_Array(false);

			String userText = "";
			
			for (int i=0;i<arr.length;i++) {
				if (arr[i][1].equals(enumbDbVal)) {
					userText=arr[i][0];
					break;
				}
			}
			if (S.isFull(userText)) {
				this.setText(userText);
			} else {
				this.setText(valWhenEmpty);
			}
		}
	}



	public IF_enum_4_db_specified getEnum() {
		return m_enum;
	}

	public E2_DBLabelTranslateEnumToText _setEnum(IF_enum_4_db_specified p_enum) {
		this.m_enum = p_enum;
		return this;
	}

	public String getValWhenEmpty() {
		return valWhenEmpty;
	}

	public E2_DBLabelTranslateEnumToText _setValWhenEmpty(String valWhenEmpty) {
		this.valWhenEmpty = valWhenEmpty;
		return this;
	}

	public String getValWhenError() {
		return valWhenError;
	}

	public E2_DBLabelTranslateEnumToText _setValWhenError(String valWhenError) {
		this.valWhenError = valWhenError;
		return this;
	}

	@Override
	public Object get_Copy(Object ob) throws myExceptionCopy {
		try {
			E2_DBLabelTranslateEnumToText cop = new E2_DBLabelTranslateEnumToText(this.EXT_DB().get_oSQLField())
												._setEnum(this.m_enum)
												._setValWhenEmpty(this.valWhenEmpty)
												._setValWhenError(this.valWhenError);

			cop.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(cop));
			return cop;
			
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
