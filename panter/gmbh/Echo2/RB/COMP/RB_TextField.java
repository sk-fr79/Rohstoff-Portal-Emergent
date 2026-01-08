package panter.gmbh.Echo2.RB.COMP;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.TextField;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.BasicInterfaces.IF_Align;
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


public class RB_TextField extends TextField implements 	IF_RB_Component_Savable
														,IF_Color<RB_TextField>
														,IF_Border<RB_TextField>
														,IF_FontandText<RB_TextField>
														,IF_Align<RB_TextField>
														,IF_Dimension<RB_TextField>
														{

	private MyE2EXT__Component  EXT = new MyE2EXT__Component(this) ;
	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();

	private Border borderNeutral = new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID);
	
	public RB_TextField() {
		super();
		this._init();
	}
	
	
	
	public RB_TextField(int i_width) {
		super();
		this.setWidth(new Extent(i_width));
		this._init();
	}

	
	public RB_TextField(int i_width, int i_max_input_size) {
		super();
		this.setWidth(new Extent(i_width));
		this.setMaximumLength(i_max_input_size);
		
		this._init();
	}
	
	

	/**
	 * makes it small
	 * @return
	 */
	public RB_TextField _maxInputSize(int i_max_input_size) {
		this.setMaximumLength(i_max_input_size);
		return this;
	}




	/**
	 * makes it small
	 * @return
	 */
	public RB_TextField _small() {
		this.setFont(new E2_FontPlain(-2));
		this.setHeight(new Extent(1, Extent.PC));
		return this;
	}


	
	
	/**
	 * hight	 * @return
	 */
	public RB_TextField _h(int height) {
		this.setHeight(new Extent(height));
		return this;
	}


	/*
	 * settings 
	 */
	private void _init() {
		this.setFont(new E2_FontPlain());
		this._setLookStd();
	}
	
	public RB_TextField  _setLookStd() {
		this.setBorder(this.borderNeutral);
		return this;
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
			this.setFocusTraversalParticipant(true);
		} else {
			this.mark_Disabled();
			this.setFocusTraversalParticipant(false);
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
	public void mark_Neutral()  {
		if (this.borderNeutral!=null) {
			this.setBorder(this.borderNeutral);
		} else {
			this.setBorder(new Border(1, new E2_ColorBase(), Border.STYLE_SOLID));
		}
		this.setBackground(new E2_ColorEditBackground());
	}

	@Override
	public void mark_MustField()  {
		this.setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
	}

	@Override
	public void mark_Disabled() {
		this.setBackground(new E2_ColorGray(230));
	}

	@Override
	public void mark_FalseInput() {
		this.setBackground(new E2_ColorHelpBackground());
	}

	@Override
	public void set_Alignment(Alignment align)  {
		this.setAlignment(align);		
	}

	@Override
	public String rb_readValue_4_dataobject()  {
		return this.getText().trim();
	}

	public RB_TextField _size_and_font(int i_heigth, Font  o_font) {
		this.setHeight(new Extent(i_heigth));
		this.setFont(o_font);
		return this;
	}

	public RB_TextField _size_and_font(int i_heigth, int iWidth, Font  o_font) {
		this.setHeight(new Extent(i_heigth));
		this.setWidth(new Extent(iWidth));
		this.setFont(o_font);
		return this;
	}
	
	public RB_TextField _w(int i_width) {
		this.setWidth(new Extent(i_width));
		return this;
	}

	public RB_TextField _f(Font o_font) {
		this.setFont(o_font);
		return this;
	}
	
	
	public RB_TextField _left() {
		this.setAlignment(new Alignment(Alignment.LEFT,Alignment.DEFAULT));
		return this;
	}

	public RB_TextField _center() {
		this.setAlignment(new Alignment(Alignment.CENTER,Alignment.DEFAULT));
		return this;
	}
	
	public RB_TextField _right() {
		this.setAlignment(new Alignment(Alignment.RIGHT,Alignment.DEFAULT));
		return this;
	}



	
	public Border get_borderWasSet() {
	
		return borderNeutral;
	}

	
	
	/**
	 * 
	 * @param iSizeAdd  (increase or decrease fontsize)
	 * @return
	 */
	public RB_TextField _fsa(int iSizeAdd) {
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
	public RB_TextField _fs(int iSize) {
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



	@Override
	public RB_TextField setNeutralBorder(Border border) {
		this.borderNeutral = border;
		return this;
	}
	
		
	/**
	 * 
	 * @param text (no translation)
	 * @return
	 */
	public RB_TextField _t(String text) {
		this.setText(text);
		return this;
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public RB_TextField _t(MyE2_String text) {
		this.setText(text.CTrans());
		return this;
	}

	/**
	 * 
	 * @param text (is translated)
	 * @return
	 */
	public RB_TextField _tr(String text) {
		this.setText(new MyE2_String(text).CTrans());
		return this;
	}

}
