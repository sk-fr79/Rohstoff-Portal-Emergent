/**
 * panter.gmbh.Echo2.RB.COMP.SearchDialog
 * @author manfred
 * @date 26.09.2017
 * 
 */
package panter.gmbh.Echo2.RB.COMP.SearchDialog;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 26.09.2017
 *
 */
public class SearchDialog_Selector_SelectField extends MyE2_SelectField implements IF_SearchDialog_SelectorEntryComponent {

	public SearchDialog_Selector_SelectField() {
		super();
	}

	public SearchDialog_Selector_SelectField(Extent oWidth) {
		super(oWidth);
	}

	public SearchDialog_Selector_SelectField(HashMap<String, String> aDefArray, String cdefaultValue,
			boolean btranslate) throws myException {
		super(aDefArray, cdefaultValue, btranslate);
	}

	public SearchDialog_Selector_SelectField(LinkedHashMap<String, MyE2_String> hm_values, boolean bEmptyInFront)
			throws myException {
		super(hm_values, bEmptyInFront);
	}

	public SearchDialog_Selector_SelectField(LinkedHashMap<String, String> hm_values, boolean bEmptyInFront,
			boolean bTranslate) throws myException {
		super(hm_values, bEmptyInFront, bTranslate);
	}

	public SearchDialog_Selector_SelectField(String cSQL_Query_For_LIST, boolean bThirdColumnIS_STANDARD_MARKER,
			boolean bEmtyValueInFront, boolean bValuesFormated, boolean btranslate, Extent Width) throws myException {
		super(cSQL_Query_For_LIST, bThirdColumnIS_STANDARD_MARKER, bEmtyValueInFront, bValuesFormated, btranslate, Width);
	}

	public SearchDialog_Selector_SelectField(String cSQL_Query_For_LIST, boolean bThirdColumnIS_STANDARD_MARKER,
			boolean bEmtyValueInFront, boolean bValuesFormated, boolean btranslate) throws myException {
		super(cSQL_Query_For_LIST, bThirdColumnIS_STANDARD_MARKER, bEmtyValueInFront, bValuesFormated, btranslate);
	}

	public SearchDialog_Selector_SelectField(String cSQL_Query_For_LIST, String cSQL_Query_4_Inaktiv,
			boolean bThirdColumnIS_STANDARD_MARKER, boolean bEmtyValueInFront, boolean bValuesFormated,
			boolean btranslate, Extent Width) throws myException {
		super(cSQL_Query_For_LIST, cSQL_Query_4_Inaktiv, bThirdColumnIS_STANDARD_MARKER, bEmtyValueInFront, bValuesFormated,
				btranslate, Width);
	}

	public SearchDialog_Selector_SelectField(String[] aDefArray, String cdefaultValue, boolean btranslate,
			Extent oWidth) throws myException {
		super(aDefArray, cdefaultValue, btranslate, oWidth);
	}

	public SearchDialog_Selector_SelectField(String[] aDefArray, String cdefaultValue, boolean btranslate)
			throws myException {
		super(aDefArray, cdefaultValue, btranslate);
	}

	public SearchDialog_Selector_SelectField(String[][] aDefArray, String cdefaultValue, boolean btranslate,
			Extent oWidth) throws myException {
		super(aDefArray, cdefaultValue, btranslate, oWidth);
	}

	public SearchDialog_Selector_SelectField(String[][] aDefArray, String cdefaultValue, boolean btranslate)
			throws myException {
		super(aDefArray, cdefaultValue, btranslate);
	}

	public SearchDialog_Selector_SelectField(Vector<String> aDefArray, String cdefaultValue, boolean btranslate)
			throws myException {
		super(aDefArray, cdefaultValue, btranslate);
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.IF_SearchDialog_SelectorEntryComponent#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String value) throws myException {
		this.set_ActiveValue_OR_FirstValue(value);
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.IF_SearchDialog_SelectorEntryComponent#getValue()
	 */
	@Override
	public String getValue() throws myException {
		return this.get_ActualWert();
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.IF_SearchDialog_SelectorEntryComponent#addActionAgent(panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent)
	 */
	@Override
	public void addActionAgent(XX_ActionAgent oAgent)  {
		if (oAgent != null){
			this.add_oActionAgent(oAgent);
		}
	}
	

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.IF_SearchDialog_SelectorEntryComponent#removeActionAgent(panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent)
	 */
	@Override
	public void removeActionAgent(XX_ActionAgent oAgent) {
		if(oAgent != null){
			this.remove_oActionAgent(oAgent);
		}
	}
	
	private SearchDialog_SelectorEntry _selector_entry = null;
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.IF_SearchDialog_SelectorEntryComponent#setSelectroEntry(panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_SelectorEntry)
	 */
	@Override
	public void setSelectroEntry(SearchDialog_SelectorEntry selectorEntry) {
		_selector_entry = selectorEntry;
	}
	

}
