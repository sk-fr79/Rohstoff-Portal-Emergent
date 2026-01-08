/**
 * panter.gmbh.Echo2.components.DB
 * @author martin
 * @date 17.12.2018
 * 
 */
package panter.gmbh.Echo2.components.DB;

import java.util.HashMap;
import java.util.LinkedHashMap;

import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.HMAP;

/**
 * @author martin
 * @date 17.12.2018
 * allgemeine klasse um einen JD_USER - eintrag in der liste anzuzeigen.
 * uebergeben wird ein String mit der ID, zurueck kommt ein String aus mehreren feldinhalten
 */
public class E2_DBLabelTranslateIdToText extends E2_DBActiveLabelForLists {

	private HMAP<IF_Field, String>  m_fieldsToShow = new HMAP<IF_Field, String>();
	private _TAB                       m_tab = null;
	private String  				   valWhenEmpty = "-";
	private String  				   valWhenError = "<Err>";
	private String 					   separator = " ";
	
	/**
	 * @author martin
	 * @date 17.12.2018
	 *
	 * @param osqlField
	 * @throws myException
	 */
	public E2_DBLabelTranslateIdToText(SQLField osqlField) throws myException {
		super(osqlField);
	}

	@Override
	public void set_cActualMaskValue(String s_id) throws myException {
		
		Long id = null;
		
		if (this.m_tab==null || this.m_fieldsToShow==null || this.m_fieldsToShow.size()==0) {
			this.setText(valWhenError);
		} else {
			if (S.isFull(s_id)) {
				MyLong l = new MyLong(s_id); 
				if (l.isOK()) {
					id=l.getLong();
				}
			}
			
			if (id!=null) {
				Rec21 r = new Rec21(this.m_tab)._fill_id(id);
				if (r.is_ExistingRecord()) {
					String ersatzText = "";
					
					for (IF_Field f: m_fieldsToShow.keySet()) {
						ersatzText = ersatzText+separator+r.getFs(f, m_fieldsToShow.get(f));
					}
					if (ersatzText.startsWith(separator)) {
						ersatzText = ersatzText.substring(separator.length());
					}
					
					this.setText(ersatzText);
					
				} else {
					this.setText(valWhenEmpty);
				}
			} else {
				this.setText(valWhenEmpty);
			}
		}
	}

	public HashMap<IF_Field, String> getFieldsToShow() {
		return m_fieldsToShow;
	}

	public E2_DBLabelTranslateIdToText _addFieldsToShow(HMAP<IF_Field, String> m_fieldsToShow) {
		this.m_fieldsToShow.putAll(m_fieldsToShow);
		return this;
	}

	public _TAB getM_tab() {
		return m_tab;
	}

	public E2_DBLabelTranslateIdToText _setTab(_TAB m_tab) {
		this.m_tab = m_tab;
		return this;
	}

	public String getValWhenEmpty() {
		return valWhenEmpty;
	}

	public E2_DBLabelTranslateIdToText _setValWhenEmpty(String valWhenEmpty) {
		this.valWhenEmpty = valWhenEmpty;
		return this;
	}

	public String getValWhenError() {
		return valWhenError;
	}

	public E2_DBLabelTranslateIdToText _setValWhenError(String valWhenError) {
		this.valWhenError = valWhenError;
		return this;
	}

	@Override
	public Object get_Copy(Object ob) throws myExceptionCopy {
		try {
			E2_DBLabelTranslateIdToText cop = new E2_DBLabelTranslateIdToText(this.EXT_DB().get_oSQLField())
												._setTab(this.m_tab)
												._addFieldsToShow(this.m_fieldsToShow)
												._setValWhenEmpty(this.valWhenEmpty)
												._setValWhenError(this.valWhenError);
			cop.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(cop));
			return cop;
			
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @return the separator
	 */
	public String getSeparator() {
		return separator;
	}

	/**
	 * @param separator the separator to set
	 */
	public E2_DBLabelTranslateIdToText _setSeparator(String separator) {
		this.separator = separator;
		return this;
	}
	
	
}
