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
public class BTN_JaNeinEgal extends E2_Grid {

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
		
		private ENUM_BTN_JA_NEIN_EGAL(int Index,String Default, Color colDefault, Color colDefaultUnselected){
			this._Index = Index;
			this._TextDefault = Default;
			this._BGColDefaultSelected = colDefault;
			this._BGColDefaultUnselected = colDefaultUnselected;
		}
		
		public int getIndex(){
			return _Index;
		}
		
		public String getDefaultCaption(){
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
	
	
	// heading-label
	private MyE2_Label										_lblHeading = null;
	private String 											_heading = null;
	private int												_headingWidth = 100;
	
	
	// der ActionAgent für den Selektor
	private XX_ActionAgent									_oActionAgentExternal = null;
	
	
	// hasmap für die Buttons
	private HashMap<ENUM_BTN_JA_NEIN_EGAL, MyE2_CheckBox>   _hmButtons = new HashMap<>();
	
	// aktuelle Selektion der Buttons
	private ENUM_BTN_JA_NEIN_EGAL      				   		_current = ENUM_BTN_JA_NEIN_EGAL.JA;
	
	
	
	/**
	 * Buttonreihe mit Heading
	 * @author manfred
	 * @date 08.12.2017
	 *
	 */
	public BTN_JaNeinEgal() {
		super();
		
		
		this._s(3)
		._setSize(20,20,20)
		._bo_no();

		
		for (ENUM_BTN_JA_NEIN_EGAL enumval : ENUM_BTN_JA_NEIN_EGAL.values()){
			_hmButtons.put(enumval, new MyE2_CheckBox(enumval.getDefaultCaption()));
			_hmButtons.get(enumval)._aaa(new actionButtonBase(enumval));
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
			this._a(_hmButtons.get(enumval)	,new RB_gld()._ins(2, 2, 2, 2)._left_mid());
		}
		
		this.setZustand(_current);
	}
	

	/**
	 * Setzt die Border des kompletten Grids 
	 * @author manfred
	 * @date 13.12.2017
	 *
	 * @param bo
	 * @return
	 */
	public BTN_JaNeinEgal setBorderObject(Border bo){
		this.setBorder(bo);
		return this;
	}

	
	
	/**
	 * fügt den gleichen ActionAgent allen Checkboxen zu
	 * @author manfred
	 * @date 13.12.2017
	 *
	 * @param oAgent
	 * @return
	 */
	public BTN_JaNeinEgal addActionAgentExternal(XX_ActionAgent oAgent){
		for (ENUM_BTN_JA_NEIN_EGAL enumval : ENUM_BTN_JA_NEIN_EGAL.values()){
			this.addActionAgentExternal(enumval, oAgent);
		}
		
		return this;
	}
	
	/**
	 * fügt der zum Zustand gehörenden Checkbox den ActionAgent dazu 
	 * @author manfred
	 * @date 13.12.2017
	 *
	 * @param zustand
	 * @param oAgent
	 * @return
	 */
	public BTN_JaNeinEgal addActionAgentExternal(ENUM_BTN_JA_NEIN_EGAL zustand, XX_ActionAgent oAgent){
		_hmButtons.get(zustand)._aaa(oAgent);
		return this;
	}
	
	
	
	/**
	 * Setzt die aktive Hintergrundfarbe des selektierten Buttons
	 * @author manfred
	 * @date 08.12.2017
	 *
	 * @param zustand
	 */
	public BTN_JaNeinEgal setZustand(ENUM_BTN_JA_NEIN_EGAL zustand){
		
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
	public BTN_JaNeinEgal setColWidth(ENUM_BTN_JA_NEIN_EGAL zustand, int width){
	
		
		if (zustand == null){
			// alle Buttons
			if (_heading != null){
				this._setSize(_headingWidth,width,width,width);
			} else {
				this._setSize(width,width,width);
			}
		} else {
			
			// nur den bestimmten Button
			int heading_offset = _heading == null ? 0 : 1;
			int nCol = 0;
			switch (zustand) {
			case JA:
				nCol = 0 + heading_offset;
				break;
			case NEIN:
				nCol = 1+ heading_offset;
				break;
			case EGAL:
				nCol = 2+ heading_offset;
				break;
			default:
				nCol = -1;
				break;
			}
			
			if (nCol > 0){
				this.setColumnWidth(nCol, new Extent(width) );
			}
		}

		return this;
	}
	
	
	
	/**
	 * Setzt den Heading-Text der Auswahl
	 * @author manfred
	 * @date 13.12.2017
	 *
	 * @param sHeading
	 * @return
	 */
	public BTN_JaNeinEgal setHeading(String sHeading){
		_heading = sHeading;
		_lblHeading = new MyE2_Label(_heading);
		
		this.initButtons();
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
	public BTN_JaNeinEgal setColWidthHeading(int width) {
		_headingWidth = width;
		if (_heading != null) {
			this.setColumnWidth(0, new Extent(_headingWidth));
		}
		return this;
	}

	public BTN_JaNeinEgal setColLayoutHeading(RB_gld gld) {
		if (_heading != null) {
			_lblHeading.setLayoutData(gld);
		}
		return this;
	}

	
	public BTN_JaNeinEgal setFontHeading(E2_Font font) {
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
	public BTN_JaNeinEgal setCaption(ENUM_BTN_JA_NEIN_EGAL zustand, String Caption){
		_hmButtons.get(zustand).setText(Caption);
		return this;
	}
	
	/**
	 * Setzen des Styles der Checkboxen wenn zustand == null, dann alle Checkboxen
	 * @author manfred
	 * @date 13.12.2017
	 *
	 * @param zustand
	 * @param Style
	 * @return
	 */
	public BTN_JaNeinEgal setCheckboxStyle(ENUM_BTN_JA_NEIN_EGAL zustand, MutableStyle Style){
		if (zustand == null){
			for (ENUM_BTN_JA_NEIN_EGAL enumval : ENUM_BTN_JA_NEIN_EGAL.values()){
					_hmButtons.get(enumval).setStyle(Style);
			}
		} else {
				_hmButtons.get(zustand).setStyle(Style);
		}
		return this;
	}
	
	
	
//	public BTN_JaNeinEgal_EXT setTextFont(ENUM_BTN_JA_NEIN_EGAL zustand, E2_Font font){
//		if (zustand == null){
//			for (ENUM_BTN_JA_NEIN_EGAL enumval : ENUM_BTN_JA_NEIN_EGAL.values()){
//					if (_hmButtons.get(enumval).getComponent() instanceof E2_Button){
//						((E2_Button)_hmButtons.get(enumval).getComponent())._f(font);
//				}
//			}
//		} else {
//			if (_hmButtons.get(zustand).getComponent() instanceof E2_Button){
//				((E2_Button)_hmButtons.get(zustand).getComponent())._f(font);
//			}
//		}
//		return this;
//	}
//	
	

	

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
	public BTN_JaNeinEgal _aaa(XX_ActionAgent oAgent) {
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
			_hmButtons.get(enumval)._aaa(_oActionAgentExternal);
			
		}
	}

	
}
