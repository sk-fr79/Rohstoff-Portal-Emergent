package panter.gmbh.Echo2.RB.COMP;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.TextArea;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.BasicInterfaces.IF_Border;
import panter.gmbh.Echo2.BasicInterfaces.IF_Color;
import panter.gmbh.Echo2.BasicInterfaces.IF_Dimension;
import panter.gmbh.Echo2.BasicInterfaces.IF_FontandText;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;


public class RB_TextArea extends TextArea 	implements 	IF_RB_Component_Savable 
														,IF_Color<RB_TextArea>
														,IF_Border<RB_TextArea>
														,IF_FontandText<RB_TextArea>
														,IF_Dimension<RB_TextArea>
														{

	private MyE2EXT__Component  EXT = new MyE2EXT__Component(this) ;
	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();

	private Border borderNeutral = null;

	
	private int rows = 1;
	
	public RB_TextArea() {
		super();
		this._init();
	}
	
	
	
	public RB_TextArea(int i_width, int i_rows) {
		super();
		this.setWidth(new Extent(i_width));
		this.setHeight(new Extent(i_rows, Extent.PC));

		this.rows = i_rows;
		
		this._init();
	}

	
	public RB_TextArea(int i_width,  int i_rows, int i_max_input_size) {
		super();
		this.setWidth(new Extent(i_width));
		this.setHeight(new Extent(i_rows, Extent.PC));
		this.setMaximumLength(i_max_input_size);
		
		this.rows = i_rows;
		
		this._init();
	}


	public RB_TextArea small() {
		this.setFont(new E2_FontPlain(-2));
		return this;
	}
	
	/*
	 * settings 
	 */
	private void _init() {
		this.setFont(new E2_FontPlain());
	}
	
	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (dataObject.get_RecORD()==null) {
			this.setText("");
		} else {
			this.setText(((MyRECORD)dataObject.get_RecORD()).fs(this.rb_KF().FIELDNAME(),""));
		}
	}

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		this.setText(S.NN(valueFormated));
	}

	@Override
	public RB_KF rb_KF() throws myException {
		return this.EXT.get_RB_K();
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.EXT.set_RB_K(key);
	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return this.vVALIDATORS_4_INPUT;	
	}

	@Override
	public void set_EXT(MyE2EXT__Component oEXT) {
		this.EXT = oEXT;
	}

	@Override
	public MyE2EXT__Component EXT() {
		return this.EXT;
	}

	@Override
	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException {
		boolean  enabled_super = (!this.EXT.get_bDisabledFromBasic())&&bEnabled;
		
		this.setEnabled(enabled_super);
		
		if (this.isEnabled()) {
			this.setBackground(new E2_ColorEditBackground());
		} else {
			this.mark_Disabled();
		}
	}

	
	@Override
	@Deprecated
	public void show_InputStatus(boolean bInputIsOK) {
	}

	@Override
	@Deprecated
	public void set_ME_First(boolean ME_InFront) {
	}

	@Override
	@Deprecated
	public void set_SpaceInPX(int iSpace) {
	}

	@Override
	public MyE2IF__Component ME() throws myException {
		return this;
	}

	@Override
	public Component C_ME() throws myException {
		return this;
	}

	@Override
	@Deprecated
	public Vector<MyE2IF__Component> get_vADDONS_IN_WRAP() throws myException {
		return null;
	}

	@Override
	public void mark_Neutral() throws myException {
		if (this.borderNeutral != null) {
			this.setBorder(this.borderNeutral);
		} else {
			this.setBorder(new Border(1, new E2_ColorBase(), Border.STYLE_SOLID));
		}
		this.setBackground(new E2_ColorEditBackground());
		this.setAlignment(new Alignment(Alignment.LEFT, Alignment.CENTER));
	}

	@Override
	public void mark_MustField() throws myException {
		this.setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
	}

	@Override
	public void mark_Disabled() throws myException {
		this.setBackground(new E2_ColorGray(230));
	}

	@Override
	public void mark_FalseInput() throws myException {
		this.setBackground(new E2_ColorHelpBackground());
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
		this.setAlignment(align);		
	}

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return this.getText().trim();
	}

	
	@Override
	public RB_TextArea setNeutralBorder(Border border) {
		this.borderNeutral = border;
		return this;
	}

	
	/**
	 * makes it small
	 * @return
	 */
	public RB_TextArea _small() {
		this.setFont(new E2_FontPlain(-2));
		this.setHeight(new Extent(this.rows*16, Extent.PX));
		return this;
	}

	
	
	/**
	 * 
	 * @param text (no translation)
	 * @return
	 */
	public RB_TextArea _t(String text) {
		this.setText(text);
		return this;
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public RB_TextArea _t(MyE2_String text) {
		this.setText(text.CTrans());
		return this;
	}

	/**
	 * 
	 * @param text (is translated)
	 * @return
	 */
	public RB_TextArea _tr(String text) {
		this.setText(new MyE2_String(text).CTrans());
		return this;
	}


	/**
	 * 
	 * @param iSizeAdd  (increase or decrease fontsize)
	 * @return
	 */
	public RB_TextArea _fsa(int iSizeAdd) {
		Font f = this.getFont();
		int style = 0;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		if (f.isUnderline()) {style+=Font.UNDERLINE;}
		
		this.setFont(new Font(this.getFont().getTypeface(),style, new Extent(f.getSize().getValue()+iSizeAdd,Extent.PT)));
		return this;
	}
	
	
	/**
	 * set fontsize
	 * @param iSize
	 * @return
	 */
	public RB_TextArea _fs(int iSize) {
		Font f = this.getFont();
		int style = 0;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		if (f.isUnderline()) {style+=Font.UNDERLINE;}
		
		this.setFont(new Font(this.getFont().getTypeface(),style, new Extent(iSize,Extent.PT)));
		return this;
	}



	/**
	 * @param rows the rows to set
	 */
	public RB_TextArea _setRows(int rows) {
		return this._rows(rows);
	}

	/**
	 * @param rows the rows to set
	 */
	public RB_TextArea _rows(int rows) {
		this.rows = rows;
		this.setHeight(new Extent(this.rows, Extent.PC));
		return this;
	}
}
