/**
 * rohstoff.Echo2BusinessLogic.CONTAINERTYP
 * @author manfred
 * @date 08.12.2017
 * 
 */
package rohstoff.Echo2BusinessLogic.CONTAINERTYP;

import java.util.HashMap;
import java.util.Map;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.exceptions.myException;

/**
 * Button der den Zustand immer von Ja->Nein->Egal->Ja... durchrotiert und den Zustand intern ablegt.
 * 
 * @author manfred
 * @date 08.12.2017
 *
 */
public class BTN_JaNeinEgal_EXT extends E2_Grid {

	public static enum ENUM_BTN_JA_NEIN_EGAL {
		JA(0,"Ja",new E2_ColorDDDark()	,new E2_ColorBase() ),
		NEIN(1,"Nein",new E2_ColorDDDark()	,new E2_ColorBase() ),
		EGAL(2,"n/a",new E2_ColorDDDark()	,new E2_ColorBase() )
		;
			
		private int 	_Index;
		private String 	_TextDefault;
		private Color  	_BGColDefaultSelected;
		private Color  	_BGColDefaultUnselected;
		
		
		private int     		_defaultWidth = 30;
		private E2_Button  		_button = null;
		private MyE2_CheckBox 	_checkBox = null;
		
		
		private ENUM_BTN_JA_NEIN_EGAL(int Index,String Default, Color colDefault, Color colDefaultUnselected){
			this._Index = Index;
			this._TextDefault = Default;
			this._BGColDefaultSelected = colDefault;
			this._BGColDefaultUnselected = colDefaultUnselected;
		}
		
		
		public int getIndex(){
			return _Index;
		}
		
		public String getDefault(){
			return _TextDefault;
		}
		public Color getColDefaultSelected(){
			return _BGColDefaultSelected;
		}
		
		public Color getColDefaultUnselected(){
			return _BGColDefaultUnselected;
		}
		
		public int getDefaultWidth(){
			return _defaultWidth;
		}
		
	}
	
	
	private class complexButton {
		MyE2_CheckBox _checkBox;
		E2_Button     _button;
		Integer       _width;
		Color		  _colSelected;
		Color		  _colUnselected;
		boolean       _useCheckbox;
		String		  _ButtonText;
		
		public complexButton(ENUM_BTN_JA_NEIN_EGAL enumval, boolean useCheckbox) {
			_useCheckbox = useCheckbox;
			if (_useCheckbox){
				_checkBox = new MyE2_CheckBox(enumval.getDefault());
				_checkBox._aaa(new actionButtonBase(enumval));
			} else {
				_button = new E2_Button()._t(enumval.getDefault());
				_button._aaa(new actionButtonBase(enumval));
			}
			
			this.setColSelected( enumval.getColDefaultSelected());
			this.setColUnselected( enumval.getColDefaultUnselected() );
			this.setWidth(enumval.getDefaultWidth());
		}

		
		
		public Component getComponent(){
			if (_useCheckbox){
				return _checkBox;
			} else {
				return _button;
			}
		}
		
		
		
		public void setActionAgentExternal(XX_ActionAgent oAgent){
			if (_useCheckbox){
				_checkBox._aaa(oAgent);
			} else {
				_button._aaa(oAgent);
			}
		}
		
		
		public void setButtonText(String caption){
			_ButtonText = caption;
			if (_useCheckbox){
				_checkBox.__setText(_ButtonText);
			} else {
				_button._t(_ButtonText);
			}
		}

		
		public void setSelected(boolean bSelected){
			Color col = bSelected ? _colSelected : _colUnselected;
			if (_useCheckbox){
				_checkBox.setSelected(bSelected);
			} else {
				_button.setBackground(col);
			}
		}

		public void setColSelected(Color col){
			_colSelected = col;
		}
		
		public void setColUnselected(Color col){
			_colUnselected = col;
		}
		
		// Spaltenbreite
		public void setWidth(int width){
			_width = width;
		}
		public int getWidth(){
			return _width;
		}
		
	}
	
	
	// Darstellungsart /checkbox oder button
	private boolean 										_useCheckbox = false;
	
	// heading-label
	private MyE2_Label										_lblHeading = null;
	private String 											_heading = null;
	private int												_headingWidth = 100;
	
	
	// der ActionAgent für den Selektor
	private XX_ActionAgent									_oActionAgentExternal = null;
	
	
	// hasmap für die Buttons
	private HashMap<ENUM_BTN_JA_NEIN_EGAL, complexButton>   _hmButtons = new HashMap<>();
	
	
	// aktuelle Selektion der Buttons
	private ENUM_BTN_JA_NEIN_EGAL      				   		_current = ENUM_BTN_JA_NEIN_EGAL.JA;
	
	
	/**
	 * Buttonreihe ohne Heading
	 * @author manfred
	 * @date 12.12.2017
	 *
	 * @param useCheckBox
	 */
	public BTN_JaNeinEgal_EXT(boolean useCheckBox) {
		this(useCheckBox,null);
	}
		
	
	/**
	 * Buttonreihe mit Heading
	 * @author manfred
	 * @date 08.12.2017
	 *
	 */
	public BTN_JaNeinEgal_EXT(boolean useCheckBox, String sHeading) {
		super();
		
		
		this._s(3)
		._setSize(20,20,20)
		._bo_no();

		_heading = sHeading;
		_useCheckbox = useCheckBox;
		
		// Buttons erzeugen
		if (_heading != null){
			_lblHeading = new MyE2_Label(sHeading);
		}
		
		for (ENUM_BTN_JA_NEIN_EGAL enumval : ENUM_BTN_JA_NEIN_EGAL.values()){
			_hmButtons.put(enumval, new complexButton(enumval, _useCheckbox));
		}

		initButtons();
	
	}


	
	/**
	 * Baut die Buttons im Grid auf
	 * @author manfred
	 * @date 12.12.2017
	 *
	 */
	private void initButtons(){
		this.removeAll();
		this._s(3);
		
		if (_heading != null){
			this._s(4);
			this._a(_lblHeading ,new RB_gld()._ins(2, 2, 2, 2)._right_mid());
			this.setColWidthHeading(_headingWidth);
		}
		
		for (ENUM_BTN_JA_NEIN_EGAL enumval : ENUM_BTN_JA_NEIN_EGAL.values()){
			this._a(_hmButtons.get(enumval).getComponent(),	new RB_gld()._ins(2, 2, 2, 2));
			this.setColWidth(enumval, _hmButtons.get(enumval).getWidth());
		}
		
		this.setZustand(_current);
	}
	

	public BTN_JaNeinEgal_EXT setBorderObject(Border bo){
		this.setBorder(bo);
		return this;
	}

	
	
	/**
	 * Setzt die aktive Hintergrundfarbe des selektierten Buttons
	 * @author manfred
	 * @date 08.12.2017
	 *
	 * @param zustand
	 */
	public BTN_JaNeinEgal_EXT setZustand(ENUM_BTN_JA_NEIN_EGAL zustand){
		
		this._current = zustand;
		
		for (ENUM_BTN_JA_NEIN_EGAL enumval : ENUM_BTN_JA_NEIN_EGAL.values()){
				_hmButtons.get(enumval).setSelected(enumval.equals(this._current));
		}
		
		return this;
	}

	
	
	/**
	 * setzt die Breite des / der Buttons
	 * wenn zustand == null, dann werden alle Buttons gleich gesetzt
	 * 
	 * @author manfred
	 * @date 11.12.2017
	 *
	 * @param zustand
	 * @param width
	 * @return
	 */
	public BTN_JaNeinEgal_EXT setColWidth(ENUM_BTN_JA_NEIN_EGAL zustand, int width){
	
		
		if (zustand == null){
			// alle Buttons
			if (_heading != null){
				this._setSize(_headingWidth,width,width,width);
			} else {
				this._setSize(width,width,width);
				this._hmButtons.get(ENUM_BTN_JA_NEIN_EGAL.JA).setWidth(width);
				this._hmButtons.get(ENUM_BTN_JA_NEIN_EGAL.NEIN).setWidth(width);
				this._hmButtons.get(ENUM_BTN_JA_NEIN_EGAL.EGAL).setWidth(width);
			}
		} else {
			
			// nur den bestimmten Button
			int heading_offset = _heading == null ? 0 : 1;
			
			int nCol = 0;
			switch (zustand) {
			case JA:
				nCol = 0 + heading_offset;
				this._hmButtons.get(ENUM_BTN_JA_NEIN_EGAL.JA).setWidth(width);
				break;
			case NEIN:
				nCol = 1+ heading_offset;
				this._hmButtons.get(ENUM_BTN_JA_NEIN_EGAL.NEIN).setWidth(width);
				break;
			case EGAL:
				nCol = 2+ heading_offset;
				this._hmButtons.get(ENUM_BTN_JA_NEIN_EGAL.EGAL).setWidth(width);
				break;
			default:
				nCol = -1;
				break;
			}
			
			if (nCol > 0){
				this.setColumnWidth(nCol, new Extent(width));
			}
		}

		return this;
	}
	
	
	
	/**
	 * Setzt die Spaltenbreite der Beschreibungs-Spalte
	 * @author manfred
	 * @date 12.12.2017
	 *
	 * @param width
	 * @return
	 */
	public BTN_JaNeinEgal_EXT setColWidthHeading(int width) {
		_headingWidth = width;
		if (_heading != null) {
			this.setColumnWidth(0, new Extent(_headingWidth));
		}
		return this;
	}


	public BTN_JaNeinEgal_EXT setColLayoutHeading(RB_gld gld) {
		if (_heading != null) {
			_lblHeading.setLayoutData(gld);
		}
		return this;
	}

	public BTN_JaNeinEgal_EXT setFontHeading(E2_Font font) {
		if (_heading != null) {
			_lblHeading.setFont(font);
		}
		return this;
	}
	
	
	
	/***
	 * default Action zum umschalten des Zustands
	 * @author manfred
	 * @date 08.12.2017
	 *
	 */
	private class actionButtonBase extends XX_ActionAgent{
		ENUM_BTN_JA_NEIN_EGAL _zustand;

		public actionButtonBase(ENUM_BTN_JA_NEIN_EGAL zustand) {
			_zustand = zustand;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			setZustand(_zustand);
		}
		
	}
	
	/**
	 * Setzen der einzelnen Button-ZUstands-Texte
	 * @author manfred
	 * @date 08.12.2017
	 *
	 * @param zustand
	 * @param Caption
	 * @return
	 */
	public BTN_JaNeinEgal_EXT setCaption(ENUM_BTN_JA_NEIN_EGAL zustand, String Caption){
		
		_hmButtons.get(zustand).setButtonText(Caption);
		return this;
	}
	
	
	public BTN_JaNeinEgal_EXT setCheckboxStyle(ENUM_BTN_JA_NEIN_EGAL zustand, MutableStyle Style){
		if (zustand == null){
			for (ENUM_BTN_JA_NEIN_EGAL enumval : ENUM_BTN_JA_NEIN_EGAL.values()){
				if (_hmButtons.get(enumval).getComponent() instanceof MyE2_CheckBox){
					((MyE2_CheckBox)_hmButtons.get(enumval).getComponent()).setStyle(Style);
				}
			}
		} else {
			if (_hmButtons.get(zustand).getComponent() instanceof MyE2_CheckBox){
				((MyE2_CheckBox)_hmButtons.get(zustand).getComponent()).setStyle(Style);
			}
		}
		return this;
	}
	
	
	
	public BTN_JaNeinEgal_EXT setButtonFont(ENUM_BTN_JA_NEIN_EGAL zustand, E2_Font font){
		if (zustand == null){
			for (ENUM_BTN_JA_NEIN_EGAL enumval : ENUM_BTN_JA_NEIN_EGAL.values()){
					if (_hmButtons.get(enumval).getComponent() instanceof E2_Button){
						((E2_Button)_hmButtons.get(enumval).getComponent())._f(font);
				}
			}
		} else {
			if (_hmButtons.get(zustand).getComponent() instanceof E2_Button){
				((E2_Button)_hmButtons.get(zustand).getComponent())._f(font);
			}
		}
		return this;
	}
	
	

	

	/**
	 * gibt den aktuellen Zustand des Buttons zurück
	 * @author manfred
	 * @date 08.12.2017
	 *
	 * @return
	 */
	public ENUM_BTN_JA_NEIN_EGAL getZustand(){
		return _current;
	}

	
	/**
	 * @author manfred
	 * @date 08.12.2017
	 *
	 * @param oAgent
	 */
	public BTN_JaNeinEgal_EXT _aaa(XX_ActionAgent oAgent) {
		_oActionAgentExternal = oAgent;
		this.setActionAgents();
		return this;
	}
	
	
	/**
	 * actionagents aller Buttons/checkboxen setzen
	 * @author manfred
	 * @date 11.12.2017
	 *
	 */
	private void setActionAgents(){
		for (ENUM_BTN_JA_NEIN_EGAL enumval : ENUM_BTN_JA_NEIN_EGAL.values()){
			_hmButtons.get(enumval).setActionAgentExternal(_oActionAgentExternal);
			
		}
	}

	
}
