package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.BasicInterfaces.IF_EXT;
import panter.gmbh.Echo2.BasicInterfaces.IF_FontandText;
import panter.gmbh.Echo2.BasicInterfaces.IF_LayoutData;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.IF_Pass_Automatic_GridLayoutDataHandling;
import panter.gmbh.indep.bibFONT;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


/**
 * einfache komponente, die zur label-darstellung in listen verwendet werden kann. b 
 * @author martin
 *
 */
public class MyE2_LabelButton4ListLineSelection extends MyE2_Button  implements  MyE2IF_IsMarkable
																				, IF_FontandText<MyE2_LabelButton4ListLineSelection> 
																				, IF_LayoutData<MyE2_LabelButton4ListLineSelection> 
																				, IF_EXT<MyE2_LabelButton4ListLineSelection> 
																				, IF_Pass_Automatic_GridLayoutDataHandling {

	private Font 	f_font = null;
	private Integer f_colWidth = null;
	private Boolean f_lineWrap = true;

	private Color   f_unmarkedForeColor = Color.BLACK;
	private Font    f_unmarkedFont = new E2_FontPlain();
	
	
	
	public MyE2_LabelButton4ListLineSelection() throws myException {
		super();
		this.add_oActionAgent(new ownActionAgent());
		this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL());
	}
	
	
	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException 	{
			E2_ComponentMAP  oMap = MyE2_LabelButton4ListLineSelection.this.EXT().get_oComponentMAP();
			
			if (oMap != null) 	{
				MyE2_LabelButton4ListLineSelection.this.EXT().get_oComponentMAP().set_CheckBoxForList_ToggleSelected();
			}
		}
	}


	
	
	public MyE2_LabelButton4ListLineSelection _set_icon(ImageReference icon) {
		this.setIcon(icon);
		return this;
	}
	


	@Override
	public MyE2_LabelButton4ListLineSelection _ttt(String tooltips) {
		MyE2_String tt = new MyE2_String(tooltips); 
		super.setToolTipText(tt.CTrans());
		return this;
	}
	

	@Override
	public MyE2_LabelButton4ListLineSelection _ttt_ut(String tooltips) {
		super.setToolTipText(tooltips);
		return this;
	}

	
	
	
	

	
	
	/*
	 * die methode setText() ueberschreiben, damit der text auch im button erscheint
	 */
	public void setText(String newText) {
		super.setText(newText);
	}
	

	
	/*
	 * ist immer enabled 
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}

	
	

	@Override
	public MyE2_LabelButton4ListLineSelection _set_gld(int iLeft, int iTop, int iRight, int iBtm, Alignment align, Color background) {
		GridLayoutData  gld = new GridLayoutData();
		gld.setInsets(new Insets(iLeft, iTop, iRight, iBtm));
		if (align!=null) {
			gld.setAlignment(align);
		}
		if (background!=null) {
			gld.setBackground(background);
		}

		this.setLayoutData(gld);
		return this;
	}

	@Override
	public MyE2_LabelButton4ListLineSelection _set_ld(LayoutData layout) {
		this.setLayoutData(layout);
		return this;
	}




	
	
	public void init(Font p_font, Integer p_colWidth, Boolean p_lineWrap) {
		this.f_font = p_font;
		this.f_colWidth = p_colWidth;
		this.f_lineWrap = p_lineWrap;
		
		if (this.f_font!=null) {
			this.setFont(this.f_font);
		}
		
		if (this.f_colWidth!=null) {
			this.EXT().set_oColExtent(new Extent(this.f_colWidth));
		}
		if (this.f_lineWrap!=null) {
			this.setLineWrap(this.f_lineWrap);
		}
	}
	
	
	@Override
	public void make_Look_Deleted(boolean bIsDeleted)	{
		bibFONT.change_fontToLineThrough(this, bIsDeleted);
	}

	
	@Override
	public void setForeColorActive(Color ForeColor) {
		super.setForeground(ForeColor);
		this.f_unmarkedForeColor = ForeColor; 
	}


	@Override
	public void setFontActive(Font oFont) {
		Font oFont4UMark = oFont==null?new E2_FontPlain():oFont;
		this.f_unmarkedFont = oFont4UMark;
		super.setFont(oFont4UMark);
	}


	
	

	public Object get_Copy(Object ob) throws myExceptionCopy {
		MyE2_LabelButton4ListLineSelection oLabCopy = null;
		try {
			oLabCopy = new MyE2_LabelButton4ListLineSelection();
		} catch (myException ex) {
			throw new myExceptionCopy("MyE2_DB_Label:get_Copy:copy-error!");
		}
		
		oLabCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oLabCopy));
		
		if (this.getIcon() != null) {
			oLabCopy.setIcon(this.getIcon());
		}
		
		
		if (this.getStyle() != null) {
			oLabCopy.setStyle(this.getStyle());
		}

		oLabCopy.setLayoutData(this.getLayoutData());

		oLabCopy.init(this.f_font,this.f_colWidth,this.f_lineWrap);
		
		return oLabCopy;
	}

	
	@Override
	public Color get_Unmarked_ForeColor() {
		return this.f_unmarkedForeColor;
	}

	@Override
	public Font get_Unmarked_Font() {
		return this.f_unmarkedFont;
	}
	
	@Override
	public MyE2_LabelButton4ListLineSelection _lw() {
		this.setLineWrap(true);
		return this;
	}

}
