/**
 * panter.gmbh.Echo2.RB.COMP
 * @author martin
 * @date 29.05.2020
 * 
 */
package panter.gmbh.Echo2.RB.COMP;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.ResourceImageReference;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.BasicInterfaces.IF_Border;
import panter.gmbh.Echo2.BasicInterfaces.IF_Color;
import panter.gmbh.Echo2.BasicInterfaces.IF_FontandText;
import panter.gmbh.Echo2.BasicInterfaces.IF_LayoutData;
import panter.gmbh.Echo2.BasicInterfaces.IF_Style;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * @author martin
 * @date 29.05.2020
 * ein grid mit der extend 100% in beide richtungen und eine inneren label
 */
public class RB_labInGrid extends Grid implements 	IF_RB_Component,
														IF_FontandText<RB_labInGrid>, 
														IF_Color<RB_labInGrid>, 
														IF_LayoutData<RB_labInGrid>, 
														IF_Style<RB_labInGrid> ,
														IF_Border<RB_labInGrid>,
														E2_IF_Copy {

	private MyE2EXT__Component 	EXT = new MyE2EXT__Component(this);
	private RB_lab             	rbLab = new RB_lab();
	private RB_KF 				Key = null;
	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();
	
	private RB_gld     			layoutDataForLabel =  new RB_gld()._left_top()._ins(0, 0, 0, 0) ;
	
	private int   				topOfset = 0; 
	
	public RB_labInGrid() {
		super();
		this._init();
	}

	/**
	 * 
	 * @param text (not translated)
	 */
	public RB_labInGrid(String text) {
		super();
		this._init();
		rbLab._t(text);
	}

	public RB_labInGrid(MyE2_String text) {
		super();
		this._init();
		rbLab._t(text);
	}


	
	
	public RB_labInGrid(ResourceImageReference icon) {
		super();
		this._init();
		rbLab._icon(icon);
	}

	private void _init() {
		this.setSize(1);
		this.setBorder(new Border(0, new E2_ColorBase(), Border.STYLE_NONE));
		this.setInsets(new Insets(0));
		this.setWidth(new Extent(100,Extent.PERCENT));
		this.setHeight(new Extent(100,Extent.PERCENT));
		rbLab.setFont(new E2_FontPlain());

		rbLab.setLayoutData(this.layoutDataForLabel);
		this.add(rbLab);
	}


	public RB_labInGrid _addComponentAtRight(Component component) {
		this.setSize(this.getSize()+1);
		this.setColumnWidth(this.getSize()-1, new Extent(10));
		component.setLayoutData(this.layoutDataForLabel._c()._right_top());
		this.add(component);
		return this;
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


	@Override
	public RB_KF rb_KF() throws myException {
		return Key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.Key = key;
	}


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
		rbLab._t(valueFormated);
	}

	@Override
	public void mark_Neutral() throws myException {
		this.setBorder(new Border(0,new E2_ColorBase(),Border.STYLE_NONE));
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
		rbLab.setText("");
		if (dataObject.get_RecORD()!=null) {
			rbLab.setText(((MyRECORD)dataObject.get_RecORD()).get_FormatedValue(this.rb_KF().FIELDNAME()));
		}
	}



	//selbst-referenzierende methoden

	/**
	 * 
	 * @param text (no translation)
	 * @return
	 */
	public RB_labInGrid _t(String text) {
		rbLab.setText(text);
		return this;
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public RB_labInGrid _t(MyE2_String text) {
		rbLab.setText(text.CTrans());
		return this;
	}

	/**
	 * 
	 * @param text (is translated)
	 * @return
	 */
	public RB_labInGrid _tr(String text) {
		rbLab.setText(new MyE2_String(text).CTrans());
		return this;
	}


	
	
	
	
	
	/**
	 * 
	 * @param f  (font)
	 * @return
	 */
	public RB_labInGrid _f(Font f) {
		rbLab.setFont(f);
		return this;
	}


	/**
	 * 
	 * @param iSizeAdd  (increase or decrease fontsize)
	 * @return
	 */
	public RB_labInGrid _fsa(int iSizeAdd) {
		Font f = rbLab.getFont();
		int style = 0;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		if (f.isUnderline()) {style+=Font.UNDERLINE;}

		rbLab.setFont(new Font(rbLab.getFont().getTypeface(),style, new Extent(f.getSize().getValue()+iSizeAdd,Extent.PT)));
		return this;
	}


	/**
	 * set fontsize
	 * @param iSize
	 * @return
	 */
	public RB_labInGrid _fs(int iSize) {
		Font f = rbLab.getFont();
		int style = 0;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		if (f.isUnderline()) {style+=Font.UNDERLINE;}

		rbLab.setFont(new Font(rbLab.getFont().getTypeface(),style, new Extent(iSize,Extent.PT)));
		return this;
	}


	/**
	 * set font bold
	 * @return
	 */
	public RB_labInGrid _b() {
		Font f = rbLab.getFont();
		int style = Font.BOLD;   //entspricht plain
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		if (f.isUnderline()) {style+=Font.UNDERLINE;}

		rbLab.setFont(new Font(rbLab.getFont().getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT)));
		return this;
	}

	/**
	 * set font italic
	 * @return
	 */
	public RB_labInGrid _i() {
		Font f = rbLab.getFont();
		int style = Font.ITALIC;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		if (f.isUnderline()) {style+=Font.UNDERLINE;}

		rbLab.setFont(new Font(rbLab.getFont().getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT)));
		return this;
	}


	/**
	 * set font bold-italic
	 * @return
	 */
	public RB_labInGrid _bi() {
		rbLab._b()._i();
		return this;
	}
	
	
	/**
	 * set font underlined
	 * @return
	 */
	public RB_labInGrid _ul() {
		Font f = rbLab.getFont();
		int style = Font.UNDERLINE;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}

		rbLab.setFont(new Font(rbLab.getFont().getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT)));
		return this;
	}
	
	/**
	 * set font line trough
	 * @author sebastien
	 * @since 13.07.2017
	 * @return
	 */
	public RB_labInGrid _lt() {
		Font f = rbLab.getFont();
		int style = Font.LINE_THROUGH;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}

		rbLab.setFont(new Font(rbLab.getFont().getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT)));
		return this;
	}

	/**
	 * set icon
	 * @param icon
	 * @return
	 */
	public RB_labInGrid _ri(ResourceImageReference icon) {
		rbLab.setIcon(icon);
		return this;
	}

	
	/**
	 * set icon
	 * @param icon
	 * @return
	 */
	public RB_labInGrid _icon(ResourceImageReference icon) {
		rbLab.setIcon(icon);
		return this;
	}

	/**
	 * set icon
	 * @param iconName
	 * @return
	 */
	public RB_labInGrid _icon(String iconName) {
		rbLab.setIcon(E2_ResourceIcon.get_RI(iconName));
		return this;
	}

	/**
	 * 
	 * @param tooltips
	 * @return
	 * @throws myException
	 */
	public RB_labInGrid _ttt(MyE2_String tooltips){
		rbLab.setToolTipText(tooltips.CTrans());
		return this;
	}

	/**
	 * 
	 * activate lineWrap
	 * @return
	 */
	public RB_labInGrid _lw() {
		rbLab.setLineWrap(true);
		return this;
	}

	/**
	 * 
	 * deactivate lineWrap
	 * @return
	 */
	public RB_labInGrid _lwn() {
		rbLab.setLineWrap(false);
		return this;
	}
	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		return new RB_labInGrid()._t(rbLab.getText())._f(rbLab.getFont());
	}
	
	
	
	/**
	 * 
	 * @param tooltips (is untranslated)
	 * @return
	 * @throws myException
	 */
	public  RB_labInGrid _ttt(String tooltips){
		rbLab.setToolTipText(tooltips);
		return this;
	}

	public RB_lab getRbLab() {
		return rbLab;
	}

	public RB_gld getLayoutDataForLabel() {
		return layoutDataForLabel;
	}

	public RB_labInGrid setLayoutDataForLabel(RB_gld layoutDataForLabel) {
		this.layoutDataForLabel = layoutDataForLabel;
		this.rbLab.setLayoutData(layoutDataForLabel);
		return this;
	}

	
	public RB_labInGrid _setStyleNormal() {
		this.rbLab._col_fore_black();
		this.rbLab._fo_plain();
		return this;
	}
	
	public RB_labInGrid _setStyleHigh() {
		this.rbLab._col_fore_black();
		this.rbLab._fo_plain();
		this.rbLab._fo_bold();
		return this;
	}


	public RB_labInGrid _setStyleLow() {
		this.rbLab._col_fore(new E2_ColorDDDark());
		this.rbLab._fo_plain();
		return this;
	}

	public int getTopOfset() {
		return topOfset;
	}

	public RB_labInGrid _setTopOfset(int topOfset) {
		this.topOfset = topOfset;
		RB_gld newLayout = this.layoutDataForLabel._c() ;
		newLayout._ins(0, topOfset, 0, 0);
		this.rbLab.setLayoutData(newLayout);
		this.layoutDataForLabel=newLayout;
		return this;
	}

	
	public RB_labInGrid _setShowError() {
		RB_gld newLayout = this.layoutDataForLabel._c() ;
		
		newLayout._col_back(new E2_ColorHelpBackground());
		
		this.rbLab.setLayoutData(newLayout);
		this.layoutDataForLabel=newLayout;
		return this;
	}
	
	
	public RB_labInGrid _setClearError() {
		RB_gld newLayout = this.layoutDataForLabel._c() ;
		
		newLayout._col_back(null);
		
		this.rbLab.setLayoutData(newLayout);
		this.layoutDataForLabel=newLayout;
		return this;
	}
	


}
