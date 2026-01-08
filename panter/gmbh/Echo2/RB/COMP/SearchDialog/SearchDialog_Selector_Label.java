/**
 * panter.gmbh.Echo2.RB.COMP.SearchDialog
 * @author manfred
 * @date 26.09.2017
 * 
 */
package panter.gmbh.Echo2.RB.COMP.SearchDialog;

import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 26.09.2017
 *
 */
public class SearchDialog_Selector_Label extends MyE2_Label implements IF_SearchDialog_SelectorEntryComponent {

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.IF_SearchDialog_SelectorEntryComponent#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String value) throws myException {
		this.setText(value);
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.IF_SearchDialog_SelectorEntryComponent#getValue()
	 */
	@Override
	public String getValue() throws myException {
		return this.getText();
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.IF_SearchDialog_SelectorEntryComponent#addActionAgent(panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent)
	 */
	@Override
	public void addActionAgent(XX_ActionAgent oAgent)  {
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.IF_SearchDialog_SelectorEntryComponent#removeActionAgent(panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent)
	 */
	@Override
	public void removeActionAgent(XX_ActionAgent oAgent) {
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
