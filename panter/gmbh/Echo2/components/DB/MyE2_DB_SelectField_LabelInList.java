package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF_IsMarkable;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2IF__Indirect;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


/**
 * ableitung der MyE2_DB_SelectField - klasse, erscheint in der ersatz-darstellung wie ein MyE2_DB_LabelInList
 * @author martin
 *
 */
public class MyE2_DB_SelectField_LabelInList extends MyE2_DB_SelectField implements MyE2IF__Indirect, MyE2IF_IsMarkable
{

	// wird fuer die darstellung der variante button im inaktiven Zustand benoetigt 
//	private MyE2_Row   		oRowContainer = new  MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());  //STYLE_NO_BORDER_NO_INSETS()
	private MyE2_Row   		oRowContainer = new  MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());  //STYLE_NO_BORDER_NO_INSETS()
	private ownButton 		oErsatzButton = new ownButton();

	
	private boolean         bUseInList = false;
	
	
	
	public MyE2_DB_SelectField_LabelInList(SQLField osqlField, dataToView oDataToView, Extent oExt) throws myException
	{
		super(osqlField, oDataToView, oExt);
		this.set_bSetToolTipsToActiveListValue(true);
	}


	public MyE2_DB_SelectField_LabelInList(SQLField osqlField, dataToView oDataToView) throws myException
	{
		super(osqlField, oDataToView);
		this.set_bSetToolTipsToActiveListValue(true);	
	}


	public MyE2_DB_SelectField_LabelInList(SQLField osqlField, Extent oExt) throws myException
	{
		super(osqlField, oExt);
		this.set_bSetToolTipsToActiveListValue(true);	
	}


	public MyE2_DB_SelectField_LabelInList(SQLField osqlField, String cSQL_Query_For_LIST, boolean bThirdColumnIS_STANDARD_MARKER, boolean btranslate, Extent oExt) throws myException
	{
		super(osqlField, cSQL_Query_For_LIST, bThirdColumnIS_STANDARD_MARKER, btranslate, oExt);
		this.set_bSetToolTipsToActiveListValue(true);	
	}


	public MyE2_DB_SelectField_LabelInList(SQLField osqlField, String cSQL_Query_For_LIST, boolean bThirdColumnIS_STANDARD_MARKER, boolean btranslate) throws myException
	{
		super(osqlField, cSQL_Query_For_LIST, bThirdColumnIS_STANDARD_MARKER, btranslate);
		this.set_bSetToolTipsToActiveListValue(true);	
	}


	public MyE2_DB_SelectField_LabelInList(SQLField osqlField, String[] aDefArray, boolean btranslate, Extent oExt) throws myException
	{
		super(osqlField, aDefArray, btranslate, oExt);
		this.set_bSetToolTipsToActiveListValue(true);	
	}


	public MyE2_DB_SelectField_LabelInList(SQLField osqlField, String[] aDefArray, boolean btranslate) throws myException
	{
		super(osqlField, aDefArray, btranslate);
		this.set_bSetToolTipsToActiveListValue(true);	
	}


	public MyE2_DB_SelectField_LabelInList(SQLField osqlField, String[][] aDefArray, boolean btranslate, Extent oExt) throws myException
	{
		super(osqlField, aDefArray, btranslate, oExt);
		this.set_bSetToolTipsToActiveListValue(true);	
	}


	public MyE2_DB_SelectField_LabelInList(SQLField osqlField, String[][] aDefArray, boolean btranslate, Extent oExt, boolean bUseInList) throws myException
	{
		super(osqlField, aDefArray, btranslate, oExt);
		this.set_bUseInList(bUseInList);
		this.set_bSetToolTipsToActiveListValue(true);	
	}

	public MyE2_DB_SelectField_LabelInList(SQLField osqlField, String[][] aDefArray, boolean btranslate) throws myException
	{
		super(osqlField, aDefArray, btranslate);
		this.set_bSetToolTipsToActiveListValue(true);	
	}


	public MyE2_DB_SelectField_LabelInList(SQLField osqlField) throws myException
	{
		super(osqlField);
		this.set_bSetToolTipsToActiveListValue(true);	
	}

	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		if (this.get_oDataToView() == null)
			throw new myExceptionCopy("MyE2_DB_SelectField:get_Copy: Error: SelectField not initialized !");
		
		MyE2_DB_SelectField_LabelInList oSelField = null;
		
		try
		{
			oSelField = new MyE2_DB_SelectField_LabelInList(this.EXT_DB().get_oSQLField(),this.get_oDataToView());
			oSelField.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oSelField));
		}
		catch (myException ex)
		{
			throw new  myExceptionCopy("MyE2_DB_SelectField:get_Copy: Error: "+ex.get_ErrorMessage().get_cMessage().COrig());
		}
		
		oSelField.set_EXT((MyE2EXT__Component)((MyE2IF__Component)this).EXT().get_Copy(oSelField));
		oSelField.setStyle(this.getStyle());
		oSelField.set_oDataToView(this.get_oDataToView());
		oSelField.set_odataToViewShadow(this.get_odataToViewShadow());

		oSelField.setFont(this.getFont());
		
		
		Vector<XX_ActionAgent> vAgents = this.get_vActionAgents();
		for (int i=0;i<vAgents.size();i++)
			oSelField.add_oActionAgent((XX_ActionAgent)vAgents.get(i));
		
		Vector<ActionListener> vActionListeners = this.get_vExternalActionListeners();
		for (int i=0;i<vActionListeners.size();i++)
			oSelField.addActionListener((ActionListener)vActionListeners.get(i));
		
		for (int i=0;i<this.get_vGlobalValidators().size();i++)
			oSelField.add_GlobalValidator((XX_ActionValidator)this.get_vGlobalValidators().get(i));
				
		
		for (int i=0;i<this.get_vIDValidators().size();i++)
			oSelField.add_IDValidator((XX_ActionValidator)this.get_vIDValidators().get(i));

		oSelField.setFocusTraversalParticipant(this.isFocusTraversalParticipant());
		
		oSelField.setWidth(this.getWidth());
		oSelField.set_bUseInList(this.get_bUseInList());

		oSelField.set_bSetToolTipsToActiveListValue(this.get_bSetToolTipsToActiveListValue());
		
		return oSelField;
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
		
		if (this.bUseInList)
		{
			if (this.isEnabled())
			{
				this.oRowContainer.add(this);
			}
			else
			{
				this.oRowContainer.add(this.oErsatzButton);
			}
		}
	}


	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		super.set_cActual_Formated_DBContent_To_Mask(cText, cMASK_STATUS, oResultMAP);

		String cAnzeigeText = this.get_ActualView();
		this.oErsatzButton.setText(S.isEmpty(cAnzeigeText)?"-":cAnzeigeText);
	}

	public void prepare_ContentForNew(boolean bSetDefault) throws myException 											
	{
		super.prepare_ContentForNew(bSetDefault);
		String cAnzeigeText = this.get_ActualView();
		this.oErsatzButton.setText(S.isEmpty(cAnzeigeText)?"-":cAnzeigeText);
	}
	

	
	
	private class ownButton extends MyE2_Button
	{

		public ownButton()
		{
			super();
			this.add_oActionAgent(new ownActionAgent());
			this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL(true));
		}
		
		
		
		private class ownActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				//2012-08-31: vereinheitlichte Listenselektion
				E2_ComponentMAP  oMap = MyE2_DB_SelectField_LabelInList.this.EXT().get_oComponentMAP();
				
				if (oMap != null)
				{
					MyE2_DB_SelectField_LabelInList.this.EXT().get_oComponentMAP().set_CheckBoxForList_ToggleSelected();
				}
			}
			
		}
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

//
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

	

	@Override
	public Component get_RenderedComponent() throws myException
	{
		return this.oRowContainer;
	}


	@Override
	public MyE2_Button get_oErsatzButton() throws myException
	{
		return oErsatzButton;
	}


	public boolean get_bUseInList()
	{
		return bUseInList;
	}


	public void set_bUseInList(boolean bUseInList)
	{
		this.bUseInList = bUseInList;
	}

	
	
}
