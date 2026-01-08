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
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.ResourceImageReference;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.BasicInterfaces.IF_Color;
import panter.gmbh.Echo2.BasicInterfaces.IF_FontandText;
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
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_HelpPopUp;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_Enum4db;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;


/**
 * 
 * @author martin
 * @date 13.11.2020
 *
 */
public class RB_GridLabelForMask extends E2_Grid implements 	IF_RB_Component,
																IF_FontandText<RB_GridLabelForMask>, 
																IF_Color<RB_GridLabelForMask>, 
																IF_Style<RB_GridLabelForMask> ,
																E2_IF_Copy {

	private MyE2EXT__Component 				EXT = new MyE2EXT__Component(this);
	private RB_lab             				rbLab = new RB_lab();
	private RB_KF 							key = null;
	private Vector<RB_Validator_Component> 	validators = new Vector<RB_Validator_Component>();
	
	private VEK<String>						helpInfos = new VEK<String>();
	
	private RB_gld     						layoutDataForLabel =  new RB_gld()._left_top()._ins(0, 0, 0, 0) ;
	
	private int   							topOfset = 0; 
	
	public RB_GridLabelForMask() {
		super();
		this._render();
	}

	/**
	 * 
	 * @param text (not translated)
	 */
	public RB_GridLabelForMask(String text) {
		super();
		rbLab._t(text);
		this._render();
	}

	public RB_GridLabelForMask(MyE2_String text) {
		super();
		rbLab._t(text);
		this._render();
	}

	
	@SuppressWarnings("unchecked")
	public RB_GridLabelForMask(@SuppressWarnings("rawtypes") IF_Enum4db en4db) {
		super();
		rbLab._t(en4db.user_text());
		if (en4db.getHelpTextVek()!=null && en4db.getHelpTextVek().size()>0) {
			helpInfos._a(en4db.getHelpTextVek());
		}
		this._render();
	}
	

	
	
	public RB_GridLabelForMask(ResourceImageReference icon) {
		super();
		this._render();
		rbLab._icon(icon);
	}

	
	private void _render() {
		this._clear();
		this.setSize(1);
		this._bo(new Border(0, new E2_ColorBase(), Border.STYLE_NONE));
		this._ins(new Insets(0));
		this._w(new Extent(100,Extent.PERCENT));
		this._h(new Extent(100,Extent.PERCENT));
		rbLab._f(new E2_FontPlain());

		rbLab.setLayoutData(this.layoutDataForLabel);
		
		this.add(rbLab);
		
		if (helpInfos!=null && helpInfos.size()>0) {
			E2_HelpPopUp popup = new E2_HelpPopUp();
			popup._setIconActiv(E2_ResourceIcon.get_RI("maskhelp_mini.png"));
			int longestLine = 30;  //ergibt mindestbreite
			for (String s: helpInfos) {
				if (S.NN(s).length()>longestLine) {longestLine=S.NN(s).length();}
				popup._addTextLine(s,-2);
			}
			this._addComponentAtRight(popup);
			popup._setWidth(new Double(longestLine*5.7).intValue());
		}
		
	}


	public RB_GridLabelForMask _addComponentAtRight(Component component) {
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
		return key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.key = key;
	}


	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() {
		return this.validators;
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

	/**
	 * 
	 * @param text (no translation)
	 * @return
	 */
	public RB_GridLabelForMask _t(String text) {
		rbLab.setText(text);
		return this;
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public RB_GridLabelForMask _t(MyE2_String text) {
		rbLab.setText(text.CTrans());
		return this;
	}

	/**
	 * 
	 * @param text (is translated)
	 * @return
	 */
	public RB_GridLabelForMask _tr(String text) {
		rbLab.setText(new MyE2_String(text).CTrans());
		return this;
	}


	
	
	
	
	
	/**
	 * 
	 * @param f  (font)
	 * @return
	 */
	public RB_GridLabelForMask _f(Font f) {
		rbLab.setFont(f);
		return this;
	}


	/**
	 * 
	 * @param iSizeAdd  (increase or decrease fontsize)
	 * @return
	 */
	public RB_GridLabelForMask _fsa(int iSizeAdd) {
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
	public RB_GridLabelForMask _fs(int iSize) {
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
	public RB_GridLabelForMask _b() {
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
	public RB_GridLabelForMask _i() {
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
	public RB_GridLabelForMask _bi() {
		rbLab._b()._i();
		return this;
	}
	
	
	/**
	 * set font underlined
	 * @return
	 */
	public RB_GridLabelForMask _ul() {
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
	public RB_GridLabelForMask _lt() {
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
	public RB_GridLabelForMask _ri(ResourceImageReference icon) {
		rbLab.setIcon(icon);
		return this;
	}

	
	/**
	 * set icon
	 * @param icon
	 * @return
	 */
	public RB_GridLabelForMask _icon(ResourceImageReference icon) {
		rbLab.setIcon(icon);
		return this;
	}

	/**
	 * set icon
	 * @param iconName
	 * @return
	 */
	public RB_GridLabelForMask _icon(String iconName) {
		rbLab.setIcon(E2_ResourceIcon.get_RI(iconName));
		return this;
	}

	/**
	 * 
	 * @param tooltips
	 * @return
	 * @throws myException
	 */
	public RB_GridLabelForMask _ttt(MyE2_String tooltips){
		rbLab.setToolTipText(tooltips.CTrans());
		return this;
	}

	/**
	 * 
	 * activate lineWrap
	 * @return
	 */
	public RB_GridLabelForMask _lw() {
		rbLab.setLineWrap(true);
		return this;
	}

	/**
	 * 
	 * deactivate lineWrap
	 * @return
	 */
	public RB_GridLabelForMask _lwn() {
		rbLab.setLineWrap(false);
		return this;
	}
	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		return new RB_GridLabelForMask()._t(rbLab.getText())._f(rbLab.getFont());
	}
	
	
	
	/**
	 * 
	 * @param tooltips (is untranslated)
	 * @return
	 * @throws myException
	 */
	public  RB_GridLabelForMask _ttt(String tooltips){
		rbLab.setToolTipText(tooltips);
		return this;
	}

	public RB_lab getRbLab() {
		return rbLab;
	}

	public RB_gld getLayoutDataForLabel() {
		return layoutDataForLabel;
	}

	public RB_GridLabelForMask setLayoutDataForLabel(RB_gld layoutDataForLabel) {
		this.layoutDataForLabel = layoutDataForLabel;
		this.rbLab.setLayoutData(layoutDataForLabel);
		return this;
	}

	
	public RB_GridLabelForMask _setStyleNormal() {
		this.rbLab._col_fore_black();
		this.rbLab._fo_plain();
		return this;
	}
	
	public RB_GridLabelForMask _setStyleHigh() {
		this.rbLab._col_fore_black();
		this.rbLab._fo_plain();
		this.rbLab._fo_bold();
		return this;
	}


	public RB_GridLabelForMask _setStyleLow() {
		this.rbLab._col_fore(new E2_ColorDDDark());
		this.rbLab._fo_plain();
		return this;
	}

	public int getTopOfset() {
		return topOfset;
	}

	public RB_GridLabelForMask _setTopOfset(int topOfset) {
		this.topOfset = topOfset;
		RB_gld newLayout = this.layoutDataForLabel._c() ;
		newLayout._ins(0, topOfset, 0, 0);
		this.rbLab.setLayoutData(newLayout);
		this.layoutDataForLabel=newLayout;
		return this;
	}

	
	public RB_GridLabelForMask _setShowError() {
		RB_gld newLayout = this.layoutDataForLabel._c() ;
		
		newLayout._col_back(new E2_ColorHelpBackground());
		
		this.rbLab.setLayoutData(newLayout);
		this.layoutDataForLabel=newLayout;
		return this;
	}
	
	
	public RB_GridLabelForMask _setClearError() {
		RB_gld newLayout = this.layoutDataForLabel._c() ;
		
		newLayout._col_back(null);
		
		this.rbLab.setLayoutData(newLayout);
		this.layoutDataForLabel=newLayout;
		return this;
	}
	

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
	}

	
	


}
