package panter.gmbh.Echo2.components.DB;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.LayoutData;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF_IsMarkable;
import panter.gmbh.Echo2.components.MyE2IF__Indirect;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_DB_Label_INROW extends MyE2_DB_Label implements MyE2IF__Indirect, MyE2IF_IsMarkable
{
	// wird fuer die darstellung der variante button im inaktiven Zustand benoetigt 
	private MyE2_Row   		oRowContainer = new  MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());  //STYLE_NO_BORDER_NO_INSETS()
	private ownButton 		oErsatzButton = new ownButton();

	
	
	public MyE2_DB_Label_INROW(SQLField osqlField, E2_MutableStyle style, LayoutData layout) throws myException
	{
		super(osqlField, style, layout);
		this.oErsatzButton.setFont(this.getFont());
		this.oErsatzButton.setLineWrap(this.isLineWrap());

	}

	public MyE2_DB_Label_INROW(SQLField osqlField, E2_MutableStyle style)	throws myException
	{
		super(osqlField, style);
		this.oErsatzButton.setFont(this.getFont());
		this.oErsatzButton.setLineWrap(this.isLineWrap());

	}

	public MyE2_DB_Label_INROW(SQLField osqlField) throws myException
	{
		super(osqlField);
		this.oErsatzButton.setFont(this.getFont());
		this.oErsatzButton.setLineWrap(this.isLineWrap());
	}

	public MyE2_DB_Label_INROW(SQLField osqlField, boolean bLinewrap) throws myException
	{
		super(osqlField);
		this.setLineWrap(bLinewrap);
		this.oErsatzButton.setFont(this.getFont());
		this.oErsatzButton.setLineWrap(this.isLineWrap());
	}

	public MyE2_DB_Label_INROW(SQLField osqlField, int iExtColInList) throws myException
	{
		super(osqlField);
		this.oErsatzButton.setFont(this.getFont());
		this.oErsatzButton.setLineWrap(this.isLineWrap());
		this.EXT().set_oColExtent(new Extent(iExtColInList));
	}

	public MyE2_DB_Label_INROW(SQLField osqlField, boolean bLinewrap, int iExtColInList) throws myException
	{
		super(osqlField);
		this.setLineWrap(bLinewrap);
		this.oErsatzButton.setFont(this.getFont());
		this.oErsatzButton.setLineWrap(this.isLineWrap());
		this.EXT().set_oColExtent(new Extent(iExtColInList));
	}


	
	public MyE2_DB_Label_INROW(SQLField osqlField, Font oFont) throws myException
	{
		super(osqlField);
		this.setFont(oFont);
		this.oErsatzButton.setLineWrap(this.isLineWrap());

	}

	
	/// 
	public MyE2_DB_Label_INROW(MyE2_DB_Label oLabelOrig, E2_MutableStyle style, LayoutData layout) throws myException
	{
		super(oLabelOrig.EXT_DB().get_oSQLField(), style, layout);
		this.oErsatzButton.setFont(this.getFont());
		this.oErsatzButton.setLineWrap(this.isLineWrap());

	}

	public MyE2_DB_Label_INROW(MyE2_DB_Label oLabelOrig, E2_MutableStyle style)	throws myException
	{
		super(oLabelOrig.EXT_DB().get_oSQLField(), style);
		this.setLayoutData(oLabelOrig.getLayoutData());
		this.oErsatzButton.setFont(this.getFont());
		this.oErsatzButton.setLineWrap(this.isLineWrap());

	}

	public MyE2_DB_Label_INROW(MyE2_DB_Label oLabelOrig) throws myException
	{
		super(oLabelOrig.EXT_DB().get_oSQLField());
		this.setStyle(oLabelOrig.getStyle());
		this.setLayoutData(oLabelOrig.getLayoutData());
		this.oErsatzButton.setFont(this.getFont());
		this.oErsatzButton.setLineWrap(this.isLineWrap());
		
	}

	public void setLineWrap(boolean bWrap)
	{
		super.setLineWrap(bWrap);
		if (this.oErsatzButton != null)
		{
			this.oErsatzButton.setLineWrap(bWrap);
		}
	}

	
	
	//die methode setFont ueberschreiben, damit die deleted-anzeige in den listen funktioniert
	public void setFont(Font oFont)
	{
		super.setFont(oFont);
		//kann bei der super - initialisierung zu laufzeitfehler fuehren, weil dort der ersatzbutton noch nicht initialisiert ist
		if (this.oErsatzButton !=null)
		{
			this.oErsatzButton.setFont(oFont);
		}
	}
	
	
	@Override
	public Component get_RenderedComponent() throws myException
	{
		return this.oRowContainer;
	}

	
	
	/*
	 * die methode setText() ueberschreiben, damit der text auch im button erscheint
	 */
	public void setText(String newText)
	{
		super.setText(newText);
		if (this.oErsatzButton != null) this.oErsatzButton.setText(newText);
	}
	

	
	/*
	 * ueberschreiben, um ein enabled bei einem nicht editierbaren feld
	 * zu unterbinden
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		super.set_bEnabled_For_Edit(enabled);
		
		//inhalt anpassen
		this.oRowContainer.removeAll();
		if (this.isEnabled())
		{
			this.oRowContainer.add(this);
		}
		else
		{
			this.oRowContainer.add(this.oErsatzButton);
		}
	}


	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		super.set_cActual_Formated_DBContent_To_Mask(cText, cMASK_STATUS, oResultMAP);
		this.oErsatzButton.setText(bibALL.isEmpty(this.getText())?"-":this.getText());
	}

	public void prepare_ContentForNew(boolean bSetDefault) throws myException 											
	{
		super.prepare_ContentForNew(bSetDefault);
		this.oErsatzButton.setText(bibALL.isEmpty(this.getText())?"-":this.getText());
	}
	
	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		MyE2_DB_Label_INROW oLabCopy = null;
		try
		{
			oLabCopy = new MyE2_DB_Label_INROW(this.EXT_DB().get_oSQLField());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_DB_Label:get_Copy:copy-error!");
		}
		
		oLabCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oLabCopy));
		oLabCopy.setFont(this.getFont());
		
		if (this.getIcon() != null)
			oLabCopy.setIcon(this.getIcon());
		
		if (this.get_oTextObject() != null)
			oLabCopy.set_Text(this.get_oTextObject());
		
		oLabCopy.set_cErsatzFuerLeeranzeige(this.get_cErsatzFuerLeeranzeige());
		
		oLabCopy.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oLabCopy));
		
		if (this.getStyle() != null)
			oLabCopy.setStyle(this.getStyle());

		oLabCopy.setLayoutData(this.getLayoutData());
		oLabCopy.setLineWrap(this.isLineWrap());

		
		return oLabCopy;

		
	}

	
	
	public void setBackground(Color ocolor)
	{
		super.setBackground(ocolor);
		this.oErsatzButton.setBackground(ocolor);
	}
	
	@Override
	public MyE2_Button get_oErsatzButton()
	{
		return oErsatzButton;
	}
	
	
	
	private class ownButton extends MyE2_Button
	{

		public ownButton()
		{
			super();
			this.add_oActionAgent(new ownActionAgent());
			this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL());
		}
		
		
		
		private class ownActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				//2012-08-31: vereinheitlichte Listenselektion
				E2_ComponentMAP  oMap = MyE2_DB_Label_INROW.this.EXT().get_oComponentMAP();
				
				if (oMap != null)
				{
					MyE2_DB_Label_INROW.this.EXT().get_oComponentMAP().set_CheckBoxForList_ToggleSelected();
				}
			}
			
		}
	}



	public MyE2_Row get_oRowContainer()
	{
		return oRowContainer;
	}

	@Override
	public void make_Look_Deleted(boolean bIsDeleted)
	{
		
		Font oDelFontDeleted = bibE2.get_Font4DeletedLinesInLists();
		Font oDelFontNormal = bibE2.get_Font4NormalLists();
		
		if (!bIsDeleted)
		{
			super.setFont(oDelFontNormal);
			//kann bei der super - initialisierung zu laufzeitfehler fuehren, weil dort der ersatzbutton noch nicht initialisiert ist
			if (this.oErsatzButton !=null)
			{
				this.oErsatzButton.setFont(oDelFontNormal);
			}
		}
		else
		{
			super.setFont(oDelFontDeleted);
			//kann bei der super - initialisierung zu laufzeitfehler fuehren, weil dort der ersatzbutton noch nicht initialisiert ist
			if (this.oErsatzButton !=null)
			{
				this.oErsatzButton.setFont(oDelFontDeleted);
			}
		}
		
		
	}

	@Override
	public void setForeColorActive(Color ForeColor)
	{
		super.setForeground(ForeColor);
		//kann bei der super - initialisierung zu laufzeitfehler fuehren, weil dort der ersatzbutton noch nicht initialisiert ist
		if (this.oErsatzButton !=null)
		{
			this.oErsatzButton.setForeground(ForeColor);
		}
	}


	@Override
	public void setFontActive(Font oFont)
	{
		Font oFont4Mark = oFont==null?new E2_FontPlain():oFont;
		
		if (this.oErsatzButton!=null)
		{
			this.oErsatzButton.setFont(oFont4Mark);
		}
	}


	
	
//	@Override
//	public Color get_ForeColor_of_markableComponent()
//	{
//		Color oColRueck = null;
//		
//		if (this.oErsatzButton!=null)
//		{
//			oColRueck = this.oErsatzButton.getForeground();
//		}
//		return oColRueck;
//	}

//
//	private Color unmarked_Color = null;
//	public void store_unmarked_ForeColor() {
//		this.unmarked_Color = this.oErsatzButton.getForeground();
//	}
//
//	public void restore_unmarked_ForeColor() {
//		if (this.unmarked_Color!=null) {
//			this.oErsatzButton.setForeground(this.unmarked_Color);
//		}
//	}

	
	@Override
	public Color get_Unmarked_ForeColor() {
		return this.oErsatzButton.getForeground();
	}

	@Override
	public Font get_Unmarked_Font() {
		return this.oErsatzButton.getFont();
	}

	
}
