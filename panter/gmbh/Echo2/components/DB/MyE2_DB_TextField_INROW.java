package panter.gmbh.Echo2.components.DB;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
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
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_DB_TextField_INROW extends MyE2_DB_TextField implements MyE2IF__Indirect, MyE2IF_IsMarkable
{

	// wird fuer die darstellung der variante button im inaktiven Zustand benoetigt 
	private MyE2_Row   		oRowContainer = new  MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
	private ownButton 		oErsatzButton = new ownButton();
	
	
	public MyE2_DB_TextField_INROW(SQLField osqlField, boolean autoAlign,int widthInPixel, LayoutData layout, Font font) throws myException
	{
		super(osqlField, autoAlign, widthInPixel, layout, font);
	}

	public MyE2_DB_TextField_INROW(SQLField osqlField, boolean autoAlign,int widthInPixel, LayoutData layout) throws myException
	{
		super(osqlField, autoAlign, widthInPixel, layout);
	}

	public MyE2_DB_TextField_INROW(SQLField osqlField, boolean autoAlign,int widthInPixel) throws myException
	{
		super(osqlField, autoAlign, widthInPixel);
	}

	public MyE2_DB_TextField_INROW(SQLField osqlField) throws myException
	{
		super(osqlField);
	}

	
	public MyE2_DB_TextField_INROW(MyE2_DB_TextField oTextFieldBase, boolean autoAlign,int widthInPixel, LayoutData layout, Font font) throws myException
	{
		super(oTextFieldBase.EXT_DB().get_oSQLField(), autoAlign, widthInPixel, layout, font);
		this.oErsatzButton.setFont(this.getFont());
	}

	public MyE2_DB_TextField_INROW(MyE2_DB_TextField oTextFieldBase, boolean autoAlign,int widthInPixel, LayoutData layout) throws myException
	{
		super(oTextFieldBase.EXT_DB().get_oSQLField(), autoAlign, widthInPixel, layout,oTextFieldBase.getFont());
		this.oErsatzButton.setFont(this.getFont());
	}

	public MyE2_DB_TextField_INROW(MyE2_DB_TextField oTextFieldBase, boolean autoAlign,int widthInPixel) throws myException
	{
		super(oTextFieldBase.EXT_DB().get_oSQLField(), autoAlign, widthInPixel, oTextFieldBase.getLayoutData(),oTextFieldBase.getFont());
		this.oErsatzButton.setFont(this.getFont());
	}

	public MyE2_DB_TextField_INROW(MyE2_DB_TextField oTextFieldBase) throws myException
	{
		super(oTextFieldBase.EXT_DB().get_oSQLField(), oTextFieldBase.get_bAutoalign(), oTextFieldBase.get_iWidthPixel(), oTextFieldBase.getLayoutData(),oTextFieldBase.getFont());
		this.oErsatzButton.setFont(this.getFont());
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

	
	/*
	 * die methode setText() ueberschreiben, damit der text auch im button erscheint
	 */
	public void setText(String newText)
	{
		super.setText(newText);
		if (this.oErsatzButton != null) this.oErsatzButton.setText(newText);
	}
	
	
	@Override
	public Component get_RenderedComponent() throws myException
	{
		return this.oRowContainer;
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
		MyE2_DB_TextField_INROW oRueck = null;
		
		try
		{
			oRueck =  new MyE2_DB_TextField_INROW(this.EXT_DB().get_oSQLField());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_DB_TextField:get_Copy:copy-error!");
		}

		oRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRueck));
		oRueck.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oRueck));
		
		oRueck.setStyle(this.getStyle());
		oRueck.setFont(this.getFont());
		
		oRueck.set_iMaxInputSize(this.get_iMaxInputSize());
		oRueck.set_iWidthPixel(this.get_iWidthPixel());
		oRueck.setText(this.getText());
		oRueck.setWidth(this.getWidth());
		oRueck.setAlignment(this.getAlignment());
		
		oRueck.setLayoutData(this.getLayoutData());
		return oRueck;
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
				E2_ComponentMAP  oMap = MyE2_DB_TextField_INROW.this.EXT().get_oComponentMAP();
				
				if (oMap != null)
				{
					MyE2_DB_TextField_INROW.this.EXT().get_oComponentMAP().set_CheckBoxForList_ToggleSelected();
				}


				
			
//				MyE2_DB_TextField_INROW oThis = MyE2_DB_TextField_INROW.this;
//				
//				E2_ComponentMAP oMap = oThis.EXT().get_oComponentMAP();
//				Vector<E2_ComponentMAP> vVectorComponentMapThisBelongsTo = null;
//				
//				//2012-08-30: multiselect-option in der liste nutzen
//				boolean 			bMultiSelectInList = false;
//				E2_NavigationList  	oNaviList = oMap.get_oNavigationList_This_Belongs_to();
//				if (oNaviList!=null)
//				{
//					bMultiSelectInList = oMap.get_oNavigationList_This_Belongs_to().get_bMultiSelectWithButtonsInList();
//				}
//				
//				
//				//zuerst nachsehen, ob die komponente in einer componentMap ist
//				if 	(oMap != null)
//				{
//					vVectorComponentMapThisBelongsTo = oMap.get_VectorComponentMAP_thisBelongsTo();
//					
//					//dann nachsehen, ob sich das ganze in einer NaviList abspielt
//					if (vVectorComponentMapThisBelongsTo!=null)
//					{
//						//dann nachsehen, ob die eingen ComponentMAP eine CheckBox hat
//						for (Map.Entry<String,MyE2IF__Component> oEntry: oMap.entrySet())
//						{
//							if (oEntry.getValue() instanceof E2_CheckBoxForList)
//							{
//								E2_CheckBoxForList oCB_For_List = (E2_CheckBoxForList)oEntry.getValue();
//								
//								if (oCB_For_List.isEnabled())
//								{
//									if (!bMultiSelectInList)
//									{
//										for (E2_ComponentMAP oMapSchleife: vVectorComponentMapThisBelongsTo)
//										{
//											oMapSchleife.setChecked_CheckBoxForList(false);   //alle aus 
//										}
//										oCB_For_List.setSelected(true);   //ausser ich
//									}
//									else
//									{
//										oCB_For_List.setSelected(!oCB_For_List.isSelected());
//									}
//								}
//							}
//						}
//
//						if (oNaviList!=null)
//						{
//							oNaviList.ShowMessageWithInfoAboutSelectedLinesAndMarkSelectedLines();
//						}
//
//					}
//				}
			}
			
		}
	}




	@Override
	public MyE2_Button get_oErsatzButton()
	{
		return this.oErsatzButton;
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
