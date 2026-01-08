/**
 * panter.gmbh.Echo2.RB.COMP.SearchDialog
 * @author manfred
 * @date 
 * 
 */
package panter.gmbh.Echo2.RB.COMP.SearchDialog;



import java.util.StringTokenizer;
import java.util.function.Supplier;

import org.omg.CORBA.TCKind;

import com.sap.dbtech.jdbc.trace.Statement;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Style;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Factorys.XXX_StyleFactory;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM.TermList;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.____DA_Decision_BorderCrossing;

/**
 * Eintrag einer Selektion, auf basis von TERM-Objekten
 * @author manfred
 * @date 
 *
 */
public class SearchDialog_SelectorEntry {
	
	
	private String 		_key 			= "";
	private String 		_description 	= "";

	private TermList 	_termList 		= null;
	
	
	
	// ein Selektor-GUI-Element besteht aus (Default)  |_activeComponent (Checkbox)|_valueComponent (textfeld, datum, ...)|_descriptionComponent (Label)
	private String 				_value 			= "";
	private IF_SearchDialog_SelectorEntryComponent	 	_valueComponent 		= null;
	
	private MyE2IF__Component 	_descriptionComponent 	= null;
	
	
	private MyE2_CheckBox 		_cbSelectorActive 		= null;
	private boolean 			_bIsActive 				= true;
	
	private XX_ActionAgent      _ActionAgentRefresh   = null;


	
	/**
	 * @author manfred
	 * @date 
	 *
	 */
	public SearchDialog_SelectorEntry(String key) {
		_bIsActive = true;
		_key = key;
		
		// die Aktiv-Inaktiv-Checkbox wird immer erzeugt
		_cbSelectorActive = new MyE2_CheckBox();
		_cbSelectorActive.setSelected(_bIsActive);

		_cbSelectorActive.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				_bIsActive = _cbSelectorActive.isSelected();
				readValueFromComponent();
				updateStatus();
			}
		});
	}
	

	/**
	 * Gibt den Key des Selectors zurück
	 * @author manfred
	 * @date 
	 *
	 * @return
	 */
	public String getKey(){
		return _key;
	}
		
	
	/**
	 * erzeugt das Statement und ersetzt die Variable #WERT# mit dem Wert aus _value, bzw. wenn eine komponente vorhanden ist aus Componente.gettext(); 
	 * @author manfred
	 * @date 11.08.2017
	 *
	 * @return
	 * @throws myException
	 */
	public String getStatement () throws myException{
		Component o = null;
		String sStatement = _termList.s();
		String sStatementRet = "";

		// Instanzvariable füllen...
		readValueFromComponent();
		updateStatus();
		
		
		if (!bibALL.isEmpty(_value)){
			String sValueINNER = bibALL.MakeSqlInnerString(_value);
			
			StringTokenizer st = new StringTokenizer(sValueINNER, " ");
			if (st.countTokens() > 1 ){
				boolean bFirst = true;
				while (st.hasMoreTokens()){
					if (!bFirst){
						sStatementRet += " AND ";
					}
					sStatementRet += sStatement.replace("#WERT#", st.nextToken() );
					bFirst = false;
				}
			} else {
				sStatementRet = sStatement.replace("#WERT#", sValueINNER );
			}
		} else {
			
			if ( _valueComponent != null ){
				bibMSG.add_MESSAGE(new MyE2_Warning_Message("Wenn eine Selektion aktiv ist, darf der Wert nicht leer sein!"));
				// verhindern einer Trefferliste...
				sStatementRet = " 1 = 2 " ;  //sStatement;
			}
		}
		return sStatementRet;
	}
	
	
	
	/**
	 * gibt den SQLTerm als Termlist zurück
	 * @author manfred
	 * @date 
	 *
	 * @return
	 */
	public TermList getTermList(){
		return _termList;
	}
	
	
	/**
	 * Setzt den SQL-Term als Termlist
	 * @author manfred
	 * @date 
	 *
	 * @param t
	 * @return
	 */
	public SearchDialog_SelectorEntry setTermList(TermList t){
		_termList	= t;
		return this;
	}
	
	
	/**
	 * Gibt den Beschreibungstext zurück
	 * @author manfred
	 * @date 
	 *
	 * @return
	 */
	public String get_Description() {
		return _description;
	}

	
	/**
	 * Setzt den Wert des Beschreibungsfeldes
	 * @author manfred
	 * @date 14.09.2017
	 *
	 * @param _description
	 * @return
	 */
	public SearchDialog_SelectorEntry set_Description(String description) {
		_description = description;
		return this;
	}

	
	/**
	 * gibt den aktuellen Wert im Suchfeld zurück
	 * @author manfred
	 * @date 14.09.2017
	 *
	 * @return
	 */
	public String get_Value() {
		return _value;
	}

	
	/**
	 * setzt den Wert im Value-Feld für die Suche
	 * @author manfred
	 * @date 14.09.2017
	 *
	 * @param _value
	 * @return
	 */
	public SearchDialog_SelectorEntry set_Value(String value) {
		if (value != null){
			_value = value.trim();
			updateStatus();
		}
		return this;
	}

	
	/**
	 * gibt den Status des Selektionsfeldes zurück
	 * @author manfred
	 * @date 
	 *
	 * @return
	 */
	public boolean is_Active() {
		return _bIsActive;
	}

	
	/**
	 * Setzt den Status des Selektionsfeldes. 
	 * @author manfred
	 * @date 
	 *
	 * @param bActive
	 * @return
	 */
	public SearchDialog_SelectorEntry set_Active(boolean bActive) {
		_bIsActive = bActive;
		_cbSelectorActive.setSelected(_bIsActive);
		
		updateStatus();
		return this;
	}
	

	
	/**
	 * ermittelt vom Value-Objekt den Stringwert, "" falls kein Wert / Value-Feldvorhanden
	 * @author manfred
	 * @date 14.09.2017
	 *
	 * @return
	 */
	private void readValueFromComponent(){
		String sRet = "";
		if (_valueComponent != null){

			try {
				sRet = _valueComponent.getValue() ;
			} catch (myException e) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Lesen des Seletionswertes!"));
			}
		}
		
		_value = sRet;
	}

	
	/**
	 * setzt den Gui-Status des Eingabefeldes
	 * @author manfred
	 * @date 13.09.2017
	 *
	 */
	protected void updateStatus(){
		
		if (_valueComponent != null ){

			try {
				_valueComponent.set_bEnabled_For_Edit(_bIsActive);
			} catch (myException e) {}
			
			
			try {
				XXX_StyleFactory styleFactory = _valueComponent.EXT().get_STYLE_FACTORY();
				if (styleFactory != null){
					Style s = styleFactory.get_Style(_bIsActive,true,true);
					if (bibALL.isEmpty(_value) && _bIsActive){
						s =styleFactory.get_Style(_bIsActive,false,true);
					} else {
						s = styleFactory.get_Style(_bIsActive,false,false);
					}
					_valueComponent.c().setStyle(s);
				}
			} catch (Exception e) {
				// keine Styles vorhanden
			}

			try {
				_valueComponent.setValue(_value);
			} catch (myException e) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim zuweisen des Wertes im Selektor"));
			}
			
			
		}

		
		if (get_descriptionComponent() != null ){
			if (_descriptionComponent.c() instanceof MyE2_TextField ){
				((MyE2_TextField)_descriptionComponent).setText(_description);
			} else  if (_descriptionComponent.c() instanceof MyE2_Label){
				((MyE2_Label)_descriptionComponent).setText(_description);
			} 
			
			try {
				_descriptionComponent.set_bEnabled_For_Edit(_bIsActive);
			} catch (myException e) {}
		}
		
	}
	
	
	
	
	// Gui-Komponente für einen änderbaren Wert des Selektors
	
	/**
	 * gibt die An-Aus-Komponente des Selektors zurück
	 * @author manfred
	 * @date 13.09.2017
	 *
	 * @return
	 */
	public MyE2_CheckBox get_GUI_for_ActiveInactive(){
		return _cbSelectorActive;
	}
	
	
	
	/**
	 * Default: 
	 * @author manfred
	 * @date 13.09.2017
	 *
	 * @return
	 */
	public MyE2IF__Component get_valueComponent() {
		if (_valueComponent == null){
			return new MyE2_Label();
		}
		return _valueComponent;
	}
	
	
	
	/**
	 * Setzt die Werte-Komponente für die Eingabe
	 * Möglich: 
	 * 		MyE2_TextField 
	 * 		MyE2_SelectField
	 * 
	 * @author manfred
	 * @date 22.09.2017
	 *
	 * @param _Component
	 * @return
	 */
	public SearchDialog_SelectorEntry set_valueComponent(IF_SearchDialog_SelectorEntryComponent _Component) {
		_valueComponent = _Component;
		
		if (_valueComponent != null){
			_valueComponent.setSelectroEntry(this);
		}
		
		return this;
	}
	
	

	/**
	 * Baut die GUI des Selektors in das Grid ein
	 * @author manfred
	 * @date 14.09.2017
	 *
	 * @param gridParent
	 */
	public void addSelectorToGrid (E2_Grid gridParent){
		gridParent	._a(_cbSelectorActive)
		._a(get_descriptionComponent().c() )
		._a(get_valueComponent().c() );
		updateStatus();
	}
	
	
	
	/**
	 * Default: Label
	 * @author manfred
	 * @date 13.09.2017
	 *
	 * @return
	 */
	public MyE2IF__Component get_descriptionComponent() {
		if (_descriptionComponent == null){
			_descriptionComponent = new MyE2_Label();
		} 
		return _descriptionComponent;
	}
	
	
	
	public SearchDialog_SelectorEntry set_descriptionComponent(MyE2IF__Component _Component) {
		_descriptionComponent = _Component;
		return this;
	}
	
	
	
	public void setRefreshActionAgent(XX_ActionAgent oAgent){
		if (_valueComponent != null) {

			// alte actionAgents löschen
			if (_ActionAgentRefresh != null){
				
				_cbSelectorActive.remove_oActionAgent(_ActionAgentRefresh);
				
				if (_valueComponent.c() instanceof MyE2_SelectField){
					((MyE2_SelectField)_valueComponent).remove_oActionAgent(_ActionAgentRefresh);
				}
			}
			
			// neuen dazu hängen
			_cbSelectorActive.add_oActionAgent(oAgent);
			
			_valueComponent.addActionAgent(oAgent);
			
		}
		
		// Actionagent merken
		this._ActionAgentRefresh = oAgent;
	}
	
	
	
}
