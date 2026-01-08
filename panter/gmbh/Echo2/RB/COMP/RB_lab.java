
package panter.gmbh.Echo2.RB.COMP;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.ResourceImageReference;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.BasicInterfaces.IF_Color;
import panter.gmbh.Echo2.BasicInterfaces.IF_FontandText;
import panter.gmbh.Echo2.BasicInterfaces.IF_LayoutData;
import panter.gmbh.Echo2.BasicInterfaces.IF_Style;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


public class RB_lab extends Label implements IF_RB_Component, IF_FontandText<RB_lab>, IF_Color<RB_lab>, IF_LayoutData<RB_lab>, IF_Style<RB_lab> , E2_IF_Copy {

	private MyE2EXT__Component EXT = new MyE2EXT__Component(this);


	public RB_lab() {
		super();
		this.setFont(new E2_FontPlain());
	}

	/**
	 * 
	 * @param text (not translated)
	 */
	public RB_lab(String text) {
		super(text);
		this.setFont(new E2_FontPlain());
	}

	public RB_lab(MyE2_String text) {
		super(text.CTrans());
		this.setFont(new E2_FontPlain());
	}


	
	
	public RB_lab(ResourceImageReference icon) {
		super(icon);
		this.setFont(new E2_FontPlain());
	}

	@Override
	public void set_EXT(MyE2EXT__Component oEXT) {
		this.EXT=oEXT;
	}

	@Override
	public MyE2EXT__Component EXT() {
		return this.EXT;
	}

	@Override
	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException {
	}

	@Override
	public void show_InputStatus(boolean bInputIsOK) {
	}

	@Override
	public void set_ME_First(boolean ME_InFront) {
	}

	@Override
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
	public Vector<MyE2IF__Component> get_vADDONS_IN_WRAP() throws myException {
		return null;
	}






	private RB_KF Key = null;


	@Override
	public RB_KF rb_KF() throws myException {
		return Key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.Key = key;
	}

	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();
	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() {
		return this.vVALIDATORS_4_INPUT;
	}

	@Override
	public RB_ComponentMap rb_ComponentMap_this_belongsTo() throws myException {
		return (RB_ComponentMap)this.EXT().get_oComponentMAP();
	}


	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		this.setText(valueFormated);
	}

	@Override
	public void mark_Neutral() throws myException {
	}

	@Override
	public void mark_MustField() throws myException {
	}

	@Override
	public void mark_Disabled() throws myException {
	}

	@Override
	public void mark_FalseInput() throws myException {
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		this.setText("");
		if (dataObject.get_RecORD()!=null) {
			this.setText(((MyRECORD)dataObject.get_RecORD()).get_FormatedValue(this.rb_KF().FIELDNAME()));
		}
	}



	//selbst-referenzierende methoden

	/**
	 * 
	 * @param text (no translation)
	 * @return
	 */
	public RB_lab _t(String text) {
		this.setText(text);
		return this;
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public RB_lab _t(MyE2_String text) {
		this.setText(text.CTrans());
		return this;
	}

	/**
	 * 
	 * @param text (is translated)
	 * @return
	 */
	public RB_lab _tr(String text) {
		this.setText(new MyE2_String(text).CTrans());
		return this;
	}


	
	
	
	
	
	/**
	 * 
	 * @param f  (font)
	 * @return
	 */
	public RB_lab _f(Font f) {
		this.setFont(f);
		return this;
	}


	/**
	 * 
	 * @param iSizeAdd  (increase or decrease fontsize)
	 * @return
	 */
	public RB_lab _fsa(int iSizeAdd) {
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
	public RB_lab _fs(int iSize) {
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
	 * set font bold
	 * @return
	 */
	public RB_lab _b() {
		Font f = this.getFont();
		int style = Font.BOLD;   //entspricht plain
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		if (f.isUnderline()) {style+=Font.UNDERLINE;}

		this.setFont(new Font(this.getFont().getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT)));
		return this;
	}

	/**
	 * set font italic
	 * @return
	 */
	public RB_lab _i() {
		Font f = this.getFont();
		int style = Font.ITALIC;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		if (f.isUnderline()) {style+=Font.UNDERLINE;}

		this.setFont(new Font(this.getFont().getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT)));
		return this;
	}


	/**
	 * set font bold-italic
	 * @return
	 */
	public RB_lab _bi() {
		this._b()._i();
		return this;
	}
	
	
	/**
	 * set font underlined
	 * @return
	 */
	public RB_lab _ul() {
		Font f = this.getFont();
		int style = Font.UNDERLINE;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}

		this.setFont(new Font(this.getFont().getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT)));
		return this;
	}
	
	/**
	 * set font line trough
	 * @author sebastien
	 * @since 13.07.2017
	 * @return
	 */
	public RB_lab _lt() {
		Font f = this.getFont();
		int style = Font.LINE_THROUGH;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}

		this.setFont(new Font(this.getFont().getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT)));
		return this;
	}

	/**
	 * set icon
	 * @param icon
	 * @return
	 */
	public RB_lab _ri(ResourceImageReference icon) {
		this.setIcon(icon);
		return this;
	}

	
	/**
	 * set icon
	 * @param icon
	 * @return
	 */
	public RB_lab _icon(ResourceImageReference icon) {
		this.setIcon(icon);
		return this;
	}

	/**
	 * set icon
	 * @param iconName
	 * @return
	 */
	public RB_lab _icon(String iconName) {
		this.setIcon(E2_ResourceIcon.get_RI(iconName));
		return this;
	}

	/**
	 * 
	 * @param tooltips
	 * @return
	 * @throws myException
	 */
	public RB_lab _ttt(MyE2_String tooltips){
		this.setToolTipText(tooltips.CTrans());
		return this;
	}

	/**
	 * 
	 * activate lineWrap
	 * @return
	 */
	public RB_lab _lw() {
		this.setLineWrap(true);
		return this;
	}

	/**
	 * 
	 * deactivate lineWrap
	 * @return
	 */
	public RB_lab _lwn() {
		this.setLineWrap(false);
		return this;
	}
	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		return new RB_lab()._t(this.getText())._f(this.getFont());
	}
	
	
	
	/**
	 * 
	 * @param tooltips (is untranslated)
	 * @return
	 * @throws myException
	 */
	public  RB_lab _ttt(String tooltips){
		this.setToolTipText(tooltips);
		return this;
	}

	
	public static RB_lab t(String unTranslated) {
		return new RB_lab()._t(unTranslated)._lw();
	}
	
	public static RB_lab tr(String translated) {
		return new RB_lab()._tr(translated)._lw();
	}
	
	
}