package panter.gmbh.Echo2.components.DB.OptionSelector;

import java.util.Hashtable;
import java.util.Vector;

import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


public abstract class E2_DB_OptionSelector extends MyE2_Button implements MyE2IF__Component,MyE2IF__DB_Component, E2_IF_Copy
{

	private static final long serialVersionUID = -7449920854652146944L;
	
	private MyE2EXT__DB_Component 	oEXTDB				= new MyE2EXT__DB_Component(this);
	private MyString  				m_cTextWhenEmpty    = null;
	private boolean 				bIsComplexObject 	= false;
	private boolean 				m_bIsEnabledForEdit   = false;
	
	// Popup Container für die Auswahl
	private OptionSelector_PopupContainer m_Popup 		= null;

	/*
	 * die Tokens, wie sie in der myConst angegeben werden sollten.
	   pro Eintrag 3 Werte: 1. Tokenwert in der DB, 
	   2. Anzeige auf dem Button, 
	   3. Anzeige in der Auswahl
	   Z.B. 
	   	 public static String[][] TOKEN_EMAIL_RECEIVER = {
		{"LAD",	"LAD", 	"Ladeschein"},
		{"LIE",	"LIE",	"Lieferschein"}
		};
	 */

	// Keyliste der Tokens: m_htTokenSet hält den key und den index des Elements in m_vTokenSet
	// m_vTokenSet hält die eigentlichen Daten
	private Hashtable<String, Integer> 	m_htTokenSet		= null;
	private Vector<String[]> 			m_vTokenSet 		= null;	
	
	
	// Der String, wie er in der DB steht
	private String					m_TokenStringInDB	= "";
	private String 					m_DisplayString 	= "";
	private int						m_LengthInDisplay 	= 0;
	
	// Ein Vector der nur die selektierten Tokens hält
	private Vector<String>          m_vTokensSelected	= null;
	
	// Der Trenner der Tokens in der DB
	private String					m_TokenSeparator	= ";";
	private String					m_TokenSeparatorOnButton = ",";
	
	// Zeichen, das als Feldbegrenzer in der Db genutzt wird
	private String 					m_TokenDelimiter = "";
	
	

	public E2_DB_OptionSelector(SQLField osqlField) throws myException
	{
		super();
		
		if (osqlField == null)
			throw new myException("E2_OptionSelector:Constructor:null-SQLField not allowed !");
	
		this.m_vTokensSelected = new Vector<String>();
		
		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
		this.setStyle(MyE2_Button.Style_Button_DB());
		this.set_cTextWhenEmpty(new MyString("-"));
		
		// default Action Agent
		this.remove_AllActionAgents();
		this.add_oActionAgent(new cActionAgentSelector(this));
		
	}


	public E2_DB_OptionSelector(SQLField osqlField, MutableStyle oStyle) throws myException
	{
		this(osqlField);
		this.setStyle(oStyle);
	}

	
	/**
	 * Setzen des Token-Trenners 
	 * @param separator
	 */
	public void setTokenSeparator(String separator){
		this.m_TokenSeparator = separator;
	}
	
	/**
	 * Setzt das Trennerzeichen auf dem Button
	 * @param separator
	 */
	public void setTokenSeparatorOnButton(String separator){
		this.m_TokenSeparatorOnButton = separator;
	}
	


	/**
	 * gibt den Begrenzer der einzelnen Tokens in der DB zurück.
	 * Der Begrenzer wird bei setTokenArray(..:) angegeben.
	 * @return
	 */
	public String getTokenDelimiter() {
		return m_TokenDelimiter;
	}


	/**
	 * Setzen der Tokendefinitionen; 
	 * die Tokendefinition sollte in der myConst definiert sein
	 * Die Key-Werte werden in eine Hashset übernommen.
	 * Die Arraywerte werden in einen Vector übernommen.
	 * @param tokens
	 */
	public void setTokenArray(String[][] tokens, String sTokenDelimiter ){
		
		this.m_htTokenSet = new Hashtable<String, Integer>();
		this.m_vTokenSet  = new Vector<String[]>();
		
		this.m_TokenDelimiter = (sTokenDelimiter != null ? sTokenDelimiter : "");
		
		for(int i=0; i < tokens.length; i++){
			// auf wunsch eines einzelnen Herren, sollen alle IDs mit einem Trenner definiert werden...
			String sTokenDB = m_TokenDelimiter + tokens[i][0].toString() + m_TokenDelimiter;
			
			String[] token = new String[3];
			token [0] = sTokenDB;
			token [1] = tokens[i][1];
			token [2] = tokens[i][2];
			m_vTokenSet.add(token);
			m_htTokenSet.put(token[0], new Integer(m_vTokenSet.size()-1));
		}
	}
	
	public Vector<String[]> getTokenArray(){
		return m_vTokenSet;
	}
	
	
	/**
	 * Setzen der selektierten Tokenliste.
	 * Die Tokens liegen als String vor getrennt mit dem definierten Trenner:
	 * z.B. "TPA;GUT;RECH" 
	 * Die Tokens werden mit den definierten Tokens abgeglichen.
	 * @param sTokenlist
	 */
	public void setSelectedTokenList(String sTokenlist){
		String[] sTokens = sTokenlist.split(this.m_TokenSeparator);
		
		this.m_vTokensSelected.clear();
		for (int i = 0; i<sTokens.length; i++){
			if (m_htTokenSet.containsKey( sTokens[i])){
				m_vTokensSelected.add(sTokens[i]);
			}
		}
		
		// String für die DB generieren
		setDBValue();
		
	}

	
	
	/**
	 * Setzen der selektierten Tokenliste
	 * Die Tokens werden in form eines Vectors übergeben.
	 * Die Tokens werden mit den definierten Tokens abgeglichen.
	 * @param vTokenList
	 */
	public void setSelectedTokenList(Vector<String> vTokenList){
		this.m_vTokensSelected.clear();
		for (int i = 0; i < vTokenList.size(); i++){
			if (m_htTokenSet.containsKey( vTokenList.get(i))){
				m_vTokensSelected.add(vTokenList.get(i));
			}
		}
		
		// String für die DB generieren
		setDBValue();
	}

	
	/**
	 * Gibt die selektierten Tokens zurück in Form eines Vectors<String>
	 * @return
	 */
	public Vector<String> getSelectedTokenList(){
		return m_vTokensSelected;
	}
	
	
	
	/**
	 * erzeugt den Text für die Darstellung auf dem Objekt (button)
	 * und erzeugt gleichzeitig den Tooltip-text des Objektes
	 * @throws myException 
	 */
	public void generateDisplayString() throws myException{
		StringBuilder sText = new StringBuilder();
		StringBuilder sTextTooltip = new StringBuilder();
		
		for (int i = 0; i < m_vTokensSelected.size(); i++){
			if (i > 0) {
				sText.append(m_TokenSeparatorOnButton);
				sTextTooltip.append(m_TokenSeparatorOnButton + " ");
			}
			
			int idx = m_htTokenSet.get(m_vTokensSelected.get(i));
			sText.append(m_vTokenSet.get(idx)[1]);
			sTextTooltip.append(m_vTokenSet.get(idx)[2]);
		}
		
		if (sText.length() == 0){
			sText.append(m_cTextWhenEmpty.CTrans());
		}
		
		// Ausgaben nochmal korrigieren, falls zu lang
		String s = sText.toString();
		if (s.length() > m_LengthInDisplay && m_LengthInDisplay > 3 ){
			s = s.substring(0, m_LengthInDisplay - 3);
			s += "...";
		}
		
		// button-text
		m_DisplayString = s;
		
		// tooltip-text
		this.setToolTipText(sTextTooltip.toString());
	}

	
	/**
	 * Erzeugt den Token-String für das DB-Feld und setzt die Instanzvariable
	 * @return
	 */
	public void setDBValue(){
		StringBuilder sText = new StringBuilder();
		for (int i = 0; i < m_vTokensSelected.size(); i++){
			if (i > 0) {
				sText.append(m_TokenSeparator);
			}
			int idx = m_htTokenSet.get(m_vTokensSelected.get(i));
			sText.append(m_vTokenSet.get(idx)[0]);
		}
		m_TokenStringInDB = sText.toString();
	}
	
	
	
	
	/**
	 * Maximale Länge des Textes auf dem Button
	 * @param lenCaption
	 */
	public void setDisplayLength(int lenCaption){
		this.m_LengthInDisplay = lenCaption;
	}
	
	
	public MyString get_cTextWhenEmpty() 
	{
		return m_cTextWhenEmpty;
	}
	
	public void set_cTextWhenEmpty(MyString setTextWhenEmpty) 
	{
		m_cTextWhenEmpty = setTextWhenEmpty;
	}
	
	
	
	//
	// *******************  E2_IF_Copy
	//
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		E2_DB_OptionSelector oCopy = null;
		
		//oCopy = new E2_DB_OptionSelector(this.oEXTDB.get_oSQLField());
		if (objHelp instanceof E2_DB_OptionSelector){
			oCopy = (E2_DB_OptionSelector)objHelp;
		} else throw new myExceptionCopy("Fehler bei der Kopie");
		
		
		oCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oCopy));
		if (this.getIcon() != null)
			oCopy.setIcon(this.getIcon());
		
		if (this.get_oText() != null)
			oCopy.set_Text(this.get_oText());
		
		oCopy.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oCopy));
		
		oCopy.setStyle(this.getStyle());
		oCopy.setInsets(this.getInsets());
//		Vector<XX_ActionAgent> vAgents = this.get_vActionAgents();
//		for (int i=0;i<vAgents.size();i++)
//			oCopy.add_oActionAgent((XX_ActionAgent)vAgents.get(i));
		
		Vector<ActionListener> vActionListeners = this.get_vExternalActionListeners();
		for (int i=0;i<vActionListeners.size();i++)
			oCopy.addActionListener((ActionListener)vActionListeners.get(i));
		
		
		for (int i=0;i<this.get_vGlobalValidators().size();i++)
			oCopy.add_GlobalValidator((XX_ActionValidator)this.get_vGlobalValidators().get(i));
		
		for (int i=0;i<this.get_vIDValidators().size();i++)
			oCopy.add_IDValidator((XX_ActionValidator)this.get_vIDValidators().get(i));
		
		oCopy.setFont(this.getFont());
		oCopy.setAlignment(this.getAlignment());
		
		oCopy.set_cTextWhenEmpty(this.m_cTextWhenEmpty);
		
		return oCopy;
	}
	
	//
	// END  **************  E2_IF_Copy
	//
	
	
	//
	// *******************  MyE2IF__DB_Component
	
	/*
	 * enabled wird nur nach der vailiderung entschieden, normalerweise ist der button immer enabled
	 */
	public void set_bEnabled_For_Edit(boolean _enabled) throws myException
	{
		boolean enabled = _enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled();
		
		if (this.EXT().get_oComponentMAP() != null && this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP() != null)
		{
			String cROWID = this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			enabled = enabled && (this.valid_IDValidation(bibALL.get_Vector(cROWID)).size()==0);
		}
		
		this.m_bIsEnabledForEdit = enabled;
		//this.setEnabled(enabled);
	}



	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this.setText("-");
		this.EXT_DB().set_cLASTActualDBValueFormated("");
		this.EXT_DB().set_cLASTActualMaskValue("");
	}
	
	public MyE2EXT__DB_Component EXT_DB() {
		return this.oEXTDB;
	}
	
	public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB) {
		this.oEXTDB = oEXT_DB;
	}
	
	public boolean get_bIsComplexObject(){
		return this.bIsComplexObject ;	
	}
	
	public void set_bIsComplexObject(boolean bComplex){
		this.bIsComplexObject=bComplex;	
	}

	
	public String get_cActualDBValueFormated() throws myException {
		return this.m_TokenStringInDB;
	}
	
	public String get_cActualMaskValue() throws myException {
		this.generateDisplayString();
		return m_DisplayString;
	}
	
	
	public void set_cActualMaskValue(Vector<String> values) throws myException{
		setSelectedTokenList(values);
		setDBValue();
		generateDisplayString();
		this.EXT_DB().set_cLASTActualMaskValue(this.m_TokenStringInDB);
		this.setText(m_DisplayString);		
	}
	
	public void set_cActualMaskValue(String cText) throws myException {
		this.EXT_DB().set_cLASTActualMaskValue(cText);
		setSelectedTokenList(cText);
		setDBValue();
		generateDisplayString();
		this.setText(m_DisplayString);
	}
	

	public void set_cActual_Formated_DBContent_To_Mask(String cText,
			String cMASKSTATUS, SQLResultMAP oResultMAP) throws myException {
		this.set_cActualMaskValue(cText);
		this.EXT_DB().set_cLASTActualDBValueFormated(m_TokenStringInDB) ;
	}

	
	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2ComponentMAP,
			SQLMaskInputMAP oMaskInputMap) throws myException {
		return null;
	}

	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2ComponentMAP,
			SQLMaskInputMAP oMaskInputMap) throws myException {
		return null;
	}
	
	// END **************  MyE2IF__DB_Component
	//
	
	
	
	public abstract IF_OptionSelector_PopupDetail 	setPopupDetailPanel();
	public abstract Vector<String[]> 				setValueListForPopup();
	public abstract Vector<String>					setSelectedValuesInPopup();
	public abstract Vector<String>					getSelectedValuesFromPopup();
//	public abstract MyString						setPopupCaption();
	
	public abstract XX_ActionAgent					setActionAgentOK_ForPopup();
	public abstract XX_ActionAgent					setActionAgentCancel_ForPopup();
	public abstract OptionSelector_PopupContainer   setOptionSelector_PopupContainer();
	
	
	public void set_Popup(OptionSelector_PopupContainer m_Popup) {
		this.m_Popup = m_Popup;
	}


	public OptionSelector_PopupContainer get_Popup() {
		return m_Popup;
	}


	/**
	 * Der Action Agent, der auf den Click des Selektors reagiert.
	 * @author manfred
	 *
	 */
	private class cActionAgentSelector extends XX_ActionAgent{
		
		private E2_DB_OptionSelector m_oThis = null;
		
		
		public cActionAgentSelector(E2_DB_OptionSelector oThis) {
			super();
			m_oThis = oThis;
		}

		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			m_oThis.set_Popup(setOptionSelector_PopupContainer());
			
			m_oThis.get_Popup().setDetailBlock(setPopupDetailPanel());
			m_oThis.get_Popup().getDetailBlock().setValueList(setValueListForPopup());
			m_oThis.get_Popup().getDetailBlock().setSelectedValues(setSelectedValuesInPopup());
			
			m_oThis.get_Popup().setActionAgentOK(setActionAgentOK_ForPopup());
			m_oThis.get_Popup().setActionAgentCancel(setActionAgentCancel_ForPopup());
			
			m_oThis.get_Popup().set_bEnabled_For_Edit(m_oThis.m_bIsEnabledForEdit);

			m_oThis.get_Popup().ShowPopup();
			
		}
		
	}
	

	
	
	
	
	

	

}
