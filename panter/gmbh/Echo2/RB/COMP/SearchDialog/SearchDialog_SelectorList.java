/**
 * panter.gmbh.Echo2.RB.COMP.SearchDialog
 * @author manfred
 * @date 09.08.2017
 * 
 */
package panter.gmbh.Echo2.RB.COMP.SearchDialog;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Factorys.GLD;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 09.08.2017
 *
 */
public class SearchDialog_SelectorList  {
	
	private Vector<SearchDialog_SelectorEntry> vSelectionEntries = new Vector<>();
	private E2_Grid  		_gridSelectors = new E2_Grid();
	private Insets 			_default_insets = E2_INSETS.I(2,2,10,0);
	
	private XX_ActionAgent 	_oDefaultSelectionAction;
	
	private MyE2_Button 	_btnRefreshSearch;
	
	
	/**
	 * @author manfred
	 * @date 09.08.2017
	 *
	 */
	public SearchDialog_SelectorList() {
	}
	
	
	/**
	 * fügt einen Selektor zur Selektion hinzu
	 * @author manfred
	 * @date 15.09.2017
	 *
	 * @param entry
	 * @return
	 */
	public SearchDialog_SelectorList addSelectorEntry(SearchDialog_SelectorEntry entry){
		if (entry != null){
			vSelectionEntries.addElement(entry);
		}
		
		return this;
	}
	
	
	/**
	 * gibt den Eintrag der SearchDialog_SelectorEntry der Liste zurück, falls der Eintrag nicht gefunden wurde: null
	 * @author manfred
	 * @date 17.08.2017
	 *
	 * @param key
	 * @return
	 */
	public SearchDialog_SelectorEntry getSelectorEntry(String key){
		SearchDialog_SelectorEntry entry = null;
		for (SearchDialog_SelectorEntry e: vSelectionEntries){
			if (e.getKey().equals(key)){
				entry = e;
				break;
			}
		}
		return entry;
	}
	
	/**
	 * Setzt den EintragsWert der SearchDialog_SelectorEntry der Liste 
	 * @author manfred
	 * @date 17.08.2017
	 *
	 * @param key
	 * @return
	 */
	public SearchDialog_SelectorList setSearchValue(String key, String Value){
		for (SearchDialog_SelectorEntry e: vSelectionEntries){
			if (e.getKey().equals(key)){
				e.set_Value(Value);
			}
		}
		return this;
	}
	
	/**
	 * Setzt den EintragsWert der SearchDialog_SelectorEntry der Liste 
	 * @author manfred
	 * @date 17.08.2017
	 *
	 * @param key
	 * @return
	 */
	public SearchDialog_SelectorList setActive(String key, boolean active){
		for (SearchDialog_SelectorEntry e: vSelectionEntries){
			if (e.getKey().equals(key)){
				e.set_Active(active);
			}
		}
		return this;
	}
	
	
	
	
	
	/**
	 * baut das Grid aus den selektionsobjekten auf
	 * @author manfred
	 * @date 09.08.2017
	 *
	 * @return
	 */
	public E2_Grid getGrid(){
		_gridSelectors._clear();
		
		RB_gld gld = new RB_gld();
		gld.setInsets(_default_insets);
		_gridSelectors._gld(gld);
		_gridSelectors._s(3);
		
		for (SearchDialog_SelectorEntry e: vSelectionEntries){
			e.setRefreshActionAgent(_oDefaultSelectionAction);
			e.addSelectorToGrid(_gridSelectors);
		}
			
				
		// Refresh-Button : Colspan entspricht Anzahl aller Spalten
		MyE2_Row rowRefresh = new MyE2_Row();
		_btnRefreshSearch  = new MyE2_Button("Suche aktualisieren",E2_ResourceIcon.get_RI("suche.png"),E2_ResourceIcon.get_RI("leer.png"));
		_btnRefreshSearch.add_oActionAgent(_oDefaultSelectionAction);
		rowRefresh.add(_btnRefreshSearch);

		RB_gld gld_span = gld._c();
		int colsInGrid = _gridSelectors.getSize();
		gld_span.setColumnSpan(colsInGrid);
		
		_gridSelectors._a(rowRefresh,gld_span);

		return _gridSelectors;
	}

	
	
	
	/**
	 * gibt die zusammengesetzten Terme alle Selektionsobjekte zurück
	 * @author manfred
	 * @date 09.08.2017
	 *
	 * @return
	 * @throws myException
	 */
	public String getStatement () throws myException{
		String s = "";
		Vector<String> vs = new Vector<>();
		for (SearchDialog_SelectorEntry e: vSelectionEntries){

			if (e.is_Active()){
				String sStatement = e.getStatement();
				if (!bibALL.isEmpty(sStatement)) { 
					vs.add(sStatement);
				}
			}
		}

		if (vs.size()== 0){
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Es muss mindestens eine Seletion aktiv und gültig sein."));
		}
		
		s = bibALL.Concatenate(vs , " AND ", "1=0"); 
		return s;
	}
	
	
	/**
	 * gibt die Selektionseinträge zurück
	 * @author manfred
	 * @date 09.08.2017
	 *
	 * @return
	 */
	public Vector<SearchDialog_SelectorEntry> getEntries(){
		return vSelectionEntries;
	}
	
	
	/**
	 * die default insets der Selektionszeilen
	 * @author manfred
	 * @date 15.09.2017
	 *
	 * @return
	 */
	public Insets get_default_insets() {
		return _default_insets;
	}


	/**
	 * setzen der Default Insets
	 * @author manfred
	 * @date 15.09.2017
	 *
	 * @param _default_insets
	 * @return
	 */
	public SearchDialog_SelectorList set_default_insets(Insets _default_insets) {
		this._default_insets = _default_insets;
		return this;
	}


	/**
	 * Rückgabe des Default-Action-Agents
	 * @author manfred
	 * @date 15.09.2017
	 *
	 * @return
	 */
	public XX_ActionAgent get_oDefaultSelectionAction() {
		return _oDefaultSelectionAction;
	}

	
	/**
	 * Setzen des Default-ActionAgents zur Ausführung der Suche
	 * @author manfred
	 * @date 15.09.2017
	 *
	 * @param _oDefaultSelectionAction
	 * @return
	 */
	public SearchDialog_SelectorList set_oDefaultSelectionAction(XX_ActionAgent _oDefaultSelectionAction) {
		this._oDefaultSelectionAction = _oDefaultSelectionAction;
		return this;
	}
	
}
