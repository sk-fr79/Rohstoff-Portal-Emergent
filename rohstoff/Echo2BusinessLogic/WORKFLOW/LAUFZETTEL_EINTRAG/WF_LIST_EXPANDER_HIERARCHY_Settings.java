/**
 * rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG
 * @author manfred
 * @date 20.07.2016
 * 
 */
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import panter.gmbh.indep.bibALL;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_CheckBoxOption;

/**
 * Klasse hält die Zustände der Aufgaben-Anzeige die an verschiedene Module übergeben werden soll.
 * 
 * @author manfred
 * @date 20.07.2016
 *
 */
public class WF_LIST_EXPANDER_HIERARCHY_Settings {
	private boolean _showDeleted = false;
	private boolean _showUndeleted = false;
	
	private boolean _showClosed = false;
	private boolean _showOpen = false;
	
	private boolean _showBesitzer = false;
	private boolean _showBesitzerNOT = false;
	
	private boolean _showBearbeiter = false;
	private boolean _showBearbeiterNOT = false;
	
	private String  _idOwnUser = null;

	
	/**
	 * Setzt den Zustand der Checkbox in der Instanz, abhängig von der Option der Checkbox
	 * @author manfred
	 * @date 21.07.2016
	 *
	 * @param cbOptions
	 */
	public void setValue(WF_CheckBoxOption cbOptions){
		boolean bIsSelected = cbOptions.isSelected();
		switch (cbOptions.getOption())
		{
			case SHOW_DELETED:
				this.set_showDeleted(bIsSelected);
				break;
			case SHOW_UNDELETED:
				this.set_showUndeleted(bIsSelected);
				break;
			case SHOW_FINISHED:
				this.set_showClosed(bIsSelected);
				break;
			case SHOW_OPEN:
				this.set_showOpen(bIsSelected);
				break;
			case SHOW_BEARBEITER_JA:
				this.set_showBearbeiter(bIsSelected);
				break;
			case SHOW_BEARBEITER_NEIN:
				this.set_showBearbeiterNOT(bIsSelected);
				break;
			case SHOW_BESITZER_JA:
				this.set_showBesitzer(bIsSelected);
				break;
			case SHOW_BESITZER_NEIN:
				this.set_showBesitzerNOT(bIsSelected);
				break;
			default:
				break;
		}
	}
	
	
	
	
	public boolean is_showDeleted() {
		return _showDeleted;
	}

	public WF_LIST_EXPANDER_HIERARCHY_Settings set_showDeleted(boolean _showDeleted) {
		this._showDeleted = _showDeleted;
		return this;
	}

	public boolean is_showUndeleted() {
		return _showUndeleted;
	}

	public WF_LIST_EXPANDER_HIERARCHY_Settings set_showUndeleted(boolean _showUndeleted) {
		this._showUndeleted = _showUndeleted;
		return this;
	}

	public boolean is_showClosed() {
		return _showClosed;
	}

	public WF_LIST_EXPANDER_HIERARCHY_Settings set_showClosed(boolean _showClosed) {
		this._showClosed = _showClosed;
		return this;
	}

	public boolean is_showOpen() {
		return _showOpen;
	}

	public WF_LIST_EXPANDER_HIERARCHY_Settings set_showOpen(boolean _showOpen) {
		this._showOpen = _showOpen;
		return this;
	}

	public boolean is_showBesitzer() {
		return _showBesitzer;
	}

	public WF_LIST_EXPANDER_HIERARCHY_Settings set_showBesitzer(boolean _showBesitzer) {
		this._showBesitzer = _showBesitzer;
		return this;
	}

	public boolean is_showBesitzerNOT() {
		return _showBesitzerNOT;
	}

	public WF_LIST_EXPANDER_HIERARCHY_Settings set_showBesitzerNOT(boolean _showBesitzerNOT) {
		this._showBesitzerNOT = _showBesitzerNOT;
		return this;
	}

	public boolean is_showBearbeiter() {
		return _showBearbeiter;
	}

	public WF_LIST_EXPANDER_HIERARCHY_Settings set_showBearbeiter(boolean _showBearbeiter) {
		this._showBearbeiter = _showBearbeiter;
		return this;
	}

	public boolean is_showBearbeiterNOT() {
		return _showBearbeiterNOT;
	}

	public WF_LIST_EXPANDER_HIERARCHY_Settings set_showBearbeiterNOT(boolean _showBearbeiterNOT) {
		this._showBearbeiterNOT = _showBearbeiterNOT;
		return this;
	}

	public String get_idOwnUser() {
		return _idOwnUser;
	}

	public WF_LIST_EXPANDER_HIERARCHY_Settings set_idOwnUser(String _idOwnUser) {
		this._idOwnUser = _idOwnUser;
		return this;
	}
	
	
}
