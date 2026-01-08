package panter.gmbh.Echo2.components.DB.OptionSelector;

import java.util.Vector;

import panter.gmbh.indep.exceptions.myException;

public interface IF_OptionSelector_PopupDetail {
	
	/**
	 * Übergabe der möglichen Tokens an die innere Darstellung
	 * @param m_htTokens
	 */
	public void setValueList(Vector<String[]> vAllValues);
	
	public void setSelectedValues(Vector<String> vSelectedValues);
	public Vector<String> getSelectedValues();

	/**
	 * Überschreiben, um die Enable/Disable-Funktionalität anzupassen
	 * @param bEnable
	 * @throws myException
	 */
	public void setEnabledForEdit(boolean bEnable) throws myException;

}
