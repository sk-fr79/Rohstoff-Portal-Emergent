package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;


import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2;
import panter.gmbh.BasicInterfaces.IF_HasChangeListeners;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.components.E2_PopUp;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;


/**
 * E2_Popu zum setzen von Werten oder IDs in einer zugeordneten Komponente
 * Der Wert wird aus einen Popup-Menü gelesen und an das angegebenen Feld übertragen
 * 
 * @author manfred
 * @date 20.07.2020
 *
 */
public class E2_PopUp_For_LookupValue extends E2_PopUp implements  IF_HasChangeListeners<E2_PopUp_For_LookupValue>
{

	private E2_ResourceIcon ICON_FOR_POPUP = E2_ResourceIcon.get_RI("textbaustein.png");
	private E2_ResourceIcon ICON_FOR_POPUP_INACTIVE = E2_ResourceIcon.get_RI("leer.png"); 
	
	private Vector<Component>		_vComponentsAtTopOfElements = new Vector<>();
	private Vector<Component>		_vComponentsAtBottomOfElements = new Vector<>();
	
	
	private IF_RB_Component         _ComponentToSetValueIn   = null;

	private String[][]				_arrKeyValuePair	  = null; 
	
	private String[] 				_resultKeyValuePair = new String[2];
	
	private String 					_cInfoWhenEmpty	  = "<- Keine Einträge ->";
	
	private boolean 				_bKeyValueToComponent = false;
	
	
	private String  				_sSqlPlain = null;
	private SqlStringExtended		_sSqlExtended = null;
	
	// der Wert wird in der Liste im Popup gezeigt
	private boolean 				_bValueToList = true;
	
	
	
	/**
	 * Main Constructor
	 * 
	 * ACHTUNG: _render() als letzte Methode aufrufen
	 * 
	 * @author manfred
	 * @date 20.07.2020
	 *
	 * @throws myException
	 */
	public E2_PopUp_For_LookupValue() throws myException
	{
		super();
		// set default Icon
		_set_icon_activ(ICON_FOR_POPUP);
		_set_icon_inactiv(ICON_FOR_POPUP_INACTIVE);
		
		
	} 
	

	/**
	 * 	 
	 *  ACHTUNG: _render() als letzte Methode aufrufen
	 *  
	 * @author manfred
	 * @date 22.07.2020
	 *
	 * @param component_to_set_value_in
	 * @throws myException
	 */
	public E2_PopUp_For_LookupValue(	IF_RB_Component component_to_set_value_in) throws myException
	{
		this();
		_ComponentToSetValueIn = component_to_set_value_in;
	}  



	/**
	 * ACHTUNG: _render() als letzte Methode aufrufen
	 * 
	 * @author manfred
	 * @date 22.07.2020
	 *
	 * @param component_to_set_value_in
	 * @param sQLExt
	 * @throws myException
	 */
	public E2_PopUp_For_LookupValue(IF_RB_Component component_to_set_value_in, SqlStringExtended sQLExt) throws myException
	{
		this();
		_ComponentToSetValueIn = component_to_set_value_in;
		_sSqlExtended = sQLExt;
	}  
	
	/**
	 * ACHTUNG: _render() als letzte Methode aufrufen
	 * 
	 * @author manfred
	 * @date 22.07.2020
	 *
	 * @param component_to_set_value_in
	 * @param cSQLQuery
	 * @throws myException
	 */
	public E2_PopUp_For_LookupValue(IF_RB_Component component_to_set_value_in, String cSQLQuery) throws myException
	{
		this();
		_ComponentToSetValueIn = component_to_set_value_in;
		_sSqlPlain = cSQLQuery;
	}  

	
	/**
	 * Setzen der Komponente, die angebunden werden soll
	 * @author manfred
	 * @date 20.07.2020
	 *
	 * @param component
	 * @return
	 */
	public E2_PopUp_For_LookupValue setComponentToSetValueIn(IF_RB_Component component) {
		this._ComponentToSetValueIn = component;
		return this;
	}

	/**
	 * Components, die am Anfang der Popup-Liste angezeigt werden
	 * @author manfred
	 * @date 20.07.2020
	 *
	 * @param component
	 * @return
	 */
	public E2_PopUp_For_LookupValue addElementAtTopOfList(Component component) {
		_vComponentsAtTopOfElements.add(component);
		return this;
	}
	
	/**
	 * Components, die am Ende der Popup-Liste angezeigt werden
	 * @author manfred
	 * @date 20.07.2020
	 *
	 * @param component
	 * @return
	 */	
	public E2_PopUp_For_LookupValue addElementAtBottomOfList(Component component) {
		_vComponentsAtBottomOfElements.add(component);
		return this;
	}
	
	
	/**
	 * Setzt den Text für den Hinweis, falls keine Buttons vorhanden sind;
	 * @author manfred
	 * @date 21.07.2020
	 *
	 * @param info
	 * @return
	 */
	public E2_PopUp_For_LookupValue setInfoWhenEmpty(String info) {
		 this._cInfoWhenEmpty = info;
		 return this;
	 }
	
	
	public E2_PopUp_For_LookupValue setIconActive(E2_ResourceIcon icon) {
		_set_icon_activ(icon);
		return this;
	}
	
	
	public E2_PopUp_For_LookupValue setIconInactive(E2_ResourceIcon icon) {
		_set_icon_inactiv(icon);
		return this;
	}
	
	
	
	/**
	 * ALT: Besser setSQL( SqlStringExtended )
	 * 
	 * Setzt den SqlString als einfaches SQL. Selektiert muss sein:
	 * select KEY, VALUE from ...
	 * @author manfred
	 * @date 21.07.2020
	 *
	 * @param sql
	 * @return
	 */
	public E2_PopUp_For_LookupValue setSQL(String sql) {
		this._sSqlPlain = sql;
		return this;
	}
	
	
	/**
	 * Setzt den SqlString als  SqlStringExtended. Selektiert muss sein:
	 * select KEY, VALUE from ...
	 * @author manfred
	 * @date 21.07.2020
	 *
	 * @param sql
	 * @return
	 */
	public E2_PopUp_For_LookupValue setSQL(SqlStringExtended sqlExtended) {
		this._sSqlExtended = sqlExtended;
		return this;
	}
	
	
	
	
	
	
	
	 
	/**
	 * Erzeugen der Key-Value-Pairs mit reinem (alten) SQL
	 * @author manfred
	 * @date 20.07.2020
	 *
	 * @param cSQLQuery
	 * @return
	 */
	private String[][] get_KeyValuePairsFromQuery(String cSQLQuery)
	{
		String[][] cRueck=bibDB.EinzelAbfrageInArray(cSQLQuery,"");
		
		String[][] 	arrKeyValue = null;
		
		if (cRueck != null && cRueck.length>0)
		{
			if (cRueck[0].length==1)
			{
				arrKeyValue = new String[cRueck.length][2];
				for (int i=0;i<cRueck.length;i++)
					arrKeyValue[i][0]=arrKeyValue[i][1]=cRueck[i][0];

			}
			else
			{
				arrKeyValue = new String[cRueck.length][2];
				for (int i=0;i<cRueck.length;i++)
				{
					arrKeyValue[i][0]=cRueck[i][0];
					arrKeyValue[i][1]=cRueck[i][1];
				}
			}
		}
		
		return arrKeyValue;
	}
	
	
	
	/**
	 * Erzeugen der Key-Value-Pairs mit reinem (alten) SQL
	 * @author manfred
	 * @date 20.07.2020
	 *
	 * @param cSQLQuery
	 * @return
	 */
	private String[][] get_KeyValuePairsFromQuery(SqlStringExtended cSQLExt)
	{
		String[][] cRueck=bibDB.EinzelAbfrageInArray(cSQLExt,"");
		
		String[][] 	arrKeyValue = null;
		
		if (cRueck != null && cRueck.length>0)
		{
			if (cRueck[0].length==1)
			{
				arrKeyValue = new String[cRueck.length][2];
				for (int i=0;i<cRueck.length;i++)
					arrKeyValue[i][0]=arrKeyValue[i][1]=cRueck[i][0];

			}
			else
			{
				arrKeyValue = new String[cRueck.length][2];
				for (int i=0;i<cRueck.length;i++)
				{
					arrKeyValue[i][0]=cRueck[i][0];
					arrKeyValue[i][1]=cRueck[i][1];
				}
			}
		}
		
		return arrKeyValue;
	}
	
	
	
	private void __prepare()
	{
		if (_sSqlExtended != null ) {
			_arrKeyValuePair = get_KeyValuePairsFromQuery(_sSqlExtended); 
		} else if (_sSqlPlain != null){
			_arrKeyValuePair = get_KeyValuePairsFromQuery(_sSqlPlain);
		} else {
			_arrKeyValuePair = null; //new String[][]{{"","KEIN EINTRAG"}};
		}
	}

	
	
	
	
	/**
	 * Füllt das PopUp-Menü
	 * @author manfred
	 * @date 20.07.2020
	 *
	 * @return
	 */
	public E2_PopUp_For_LookupValue _render() 
	{
		
		this.clear();
		
		this.__prepare();
		
		this.get_grid_innen().removeAll();
		
		if ( (_arrKeyValuePair == null || _arrKeyValuePair.length == 0 ) && _vComponentsAtBottomOfElements.size() == 0 && _vComponentsAtTopOfElements.size() == 0)
		{
			if (S.isEmpty(_cInfoWhenEmpty)) {
				_cInfoWhenEmpty = "<Kein Eintrag vorhanden>";
			}
			
			String sInfo = new MyE2_String(_cInfoWhenEmpty).CTrans();
			this.get_grid_innen()._a(new MyE2_Label(sInfo));
		} 
		
		
		// Elemente am Kopf der Liste anhängen
		if (this._vComponentsAtTopOfElements.size() > 0 ) {
			for (Component c : _vComponentsAtTopOfElements) {
				this.get_grid_innen().add(c);
			}
		}
		
		// Elemente erzeugen
		if (this._arrKeyValuePair != null && this._arrKeyValuePair.length>0)
		{
			
			int idx_display = _bValueToList ? 1 : 0;
			int idx_other 	= _bValueToList ? 0 : 1;
			
			
			for (int i=0;i<this._arrKeyValuePair.length;i++)
			{
				// Button mit Text "Value"
				MyE2_Button oButHelp = new MyE2_Button(new MyE2_String(this._arrKeyValuePair[i][idx_display],false));
				// Merkmal: Key
				oButHelp.EXT().set_C_MERKMAL(this._arrKeyValuePair[i][idx_other]);
				// Everything: Key/Value
				oButHelp.EXT().set_O_PLACE_FOR_EVERYTHING(this._arrKeyValuePair[i]);
				
				oButHelp.add_oActionAgent(new ownActionPopupButtons());
				
				oButHelp.add_oActionAgent(new ActionOnChangeListeners());
				
				this.get_grid_innen()._a(oButHelp);
			}
		}


		
		// Elemente am Ende der Liste anhängen
		if (this._vComponentsAtBottomOfElements.size() > 0 ) {
			for (Component c : _vComponentsAtBottomOfElements) {
				this.get_grid_innen().add(c);
			}
		}
		return this;
	}
	
	
	public String[][] get_KeyValuePairs()
	{
		return this._arrKeyValuePair;
	}
	
	
	/**
	 * Gibt ein Array zurück mit 
	 * 		String[Key][Value]
	 * @author manfred
	 * @date 20.07.2020
	 *
	 * @return
	 */
	public String[] get_ResultKeyValue() {
		return this._resultKeyValuePair;
	}
	
	
	/**
	 * setzt den TooltipText 
	 * @author manfred
	 * @date 21.07.2020
	 *
	 * @param Tooltip
	 * @return
	 */
	public E2_PopUp_For_LookupValue setTooltipText(String Tooltip) {
		super.setToolTipText(new MyE2_String(Tooltip).CTrans());
		return this;
	}
	
 
	
	
	/*
	 * Get Copy of Object
	 * (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.E2_PopUp#get_Copy(java.lang.Object)
	 */
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		E2_PopUp_For_LookupValue oRueck = null;
		try
		{
			oRueck =  new E2_PopUp_For_LookupValue(_ComponentToSetValueIn);
			oRueck.setSQL(_sSqlPlain);
			oRueck.setSQL(_sSqlExtended);
			oRueck.setTooltipText(this.getToolTipText());
			
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_DB_TextField_WithSelektor:get_Copy:copy-error!"+ex.get_ErrorMessage().get_cMessage().COrig());
		}

		oRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRueck));
		
		oRueck.setStyle(this.getStyle());
		oRueck.setFont(this.getFont());
		oRueck._set_icon_activ(this.get_oIconAktiv());
		oRueck._set_icon_inactiv(this.get_oIconInactiv());
		
		
		return oRueck;
	}
	

	
	/*
	 * ueberschreiben, um ein enabled bei einem nicht editierbaren feld
	 * zu unterbinden
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		boolean bVoraussetzung = enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled() ;
		super.set_bEnabled_For_Edit(bVoraussetzung);		
	}


	


	public void show_InputStatus(boolean bInputIsOK)
	{
	}


	/**
	 * Definiert, ob der Key-Wert (true) oder Value-Wert (false) in das angebundene Feld kopiert werden soll
	 * @author manfred
	 * @date 20.07.2020
	 *
	 * @param Key
	 * @return
	 */
//	public E2_PopUp_For_LookupValue fillComponentValueFromKey(boolean Key) {
//		this._bKeyValueToComponent = Key;
//		return this;
//	}


	/**
	 * Der Key-Wert wird in das angebundene Feld kopiert
	 * @author manfred
	 * @date 20.07.2020
	 *
	 * @return
	 */
	public E2_PopUp_For_LookupValue setKeyInComponent() {
		this._bKeyValueToComponent = true;
		return this;
	}
	
	/**
	 * Der Value-Wert wird in das angebundene Feld kopiert
	 * @author manfred
	 * @date 20.07.2020
	 *
	 * @return
	 */
	public E2_PopUp_For_LookupValue setValueInComponent() {
		this._bKeyValueToComponent = false;
		return this;
	}
	
	
	/**
	 * Der Key-Text wird in den PopUps angezeigt
	 * @author manfred
	 * @date 20.07.2020
	 *
	 * @return
	 */
	public E2_PopUp_For_LookupValue setKeyInList() {
		this._bValueToList = false;
		return this;
	}
	
	/**
	 * Der Value-Wert wird in den PopUps angezeigt
	 * @author manfred
	 * @date 20.07.2020
	 *
	 * @return
	 */
	public E2_PopUp_For_LookupValue setValueInList() {
		this._bValueToList = true;
		return this;
	}
	
	
//	public E2_PopUp_For_LookupValue _set_icon_activ(E2_ResourceIcon icon) {
//		super._set_icon_activ(icon);
//		return this;
//	}
//	
//	public E2_PopUp_For_LookupValue _set_icon_inactive(E2_ResourceIcon icon) {
//		super._set_icon_inactiv(icon);
//		return this;
//	}
//	

	
	private class ownActionPopupButtons extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{

			MyE2_Button 	oBUT = ((MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource());
			
			_resultKeyValuePair = (String[]) oBUT.EXT().get_O_PLACE_FOR_EVERYTHING(); 
			String sText = _bKeyValueToComponent ? _resultKeyValuePair[0] : _resultKeyValuePair[1];
		    
			
			// setzen des Wertes in der Komponente
			if (_ComponentToSetValueIn != null) {
				_ComponentToSetValueIn.rb_set_db_value_manual(sText);
			} else {
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Wert aus Popup: "+ sText ));
			}
			
			// PopUp Schliessen
			E2_PopUp_For_LookupValue.this.closeMeAndMotherPopup();
		}
	}


	
	private VEK<IF_ExecuterOnComponentV2<E2_PopUp_For_LookupValue>>    changeListeners = new   VEK<>();
	
	@Override
	public E2_PopUp_For_LookupValue _clearChangeListener() {
		changeListeners._clear();
		return this;
	}

	@Override
	public E2_PopUp_For_LookupValue _addChangeListener(IF_ExecuterOnComponentV2<E2_PopUp_For_LookupValue> changeListener) {
		this.changeListeners._a(changeListener);
		return this;
	}


	
	/**
	 * Weitergabe der von aussen gesetzten Listeners 
	 * @author manfred
	 * @date 29.05.2020
	 *
	 */
	private class ActionOnChangeListeners extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			for (IF_ExecuterOnComponentV2<E2_PopUp_For_LookupValue> executer: changeListeners) {
				mv._add(executer.execute(E2_PopUp_For_LookupValue.this));
			}
			bibMSG.MV()._add(mv);
		}
	}

	
	
}
