/**
 * panter.gmbh.Echo2.RB.COMP.SearchDialog
 * @author manfred
 * @date 26.09.2017
 * 
 */
package panter.gmbh.Echo2.RB.COMP.SearchDialog;

import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.exceptions.myException;

/**
 * Interface für die Komponenten, um die Werte zu setzen und zu lesen
 * sowie actionagents zu setzen/löschen
 * @author manfred
 * @date 26.09.2017
 *
 */
public interface IF_SearchDialog_SelectorEntryComponent extends MyE2IF__Component{
	
	
	public void setValue(String value) throws myException;
	public String getValue() throws myException;
	
	public void addActionAgent(XX_ActionAgent oAgent) ;
	public void removeActionAgent(XX_ActionAgent oAgent) ;
	
	public void setSelectroEntry(SearchDialog_SelectorEntry selectorEntry);
}
