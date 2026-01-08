package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Row;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_ContentPane;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_ContentPane_NUMBER_ONE;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/*
 * textfeld mit einem popup-selektor, der varianten darstellt. Diese komponenten enthaelt eine methode, die 
 * im moment des klickens auf den button eine einstellung vornehmen kann, z.b. das fuellen der auswahl oder aehnliches
 */
public class MyE2_DB_TextField_WithPopUpSelektor extends MyE2_Row implements MyE2IF__DB_Component,MyE2IF__Component, E2_IF_Copy
{

	public static XX_ActionAgent 	ownAction=null;
	public static MutableStyle		ownRowStyle = null;
	static
	{
		XX_ActionAgent oACT = new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo)
			{
				/*
				 * popup-button hat den Text in  get_C_MERKMAL und das textobject in get_O_PLACE_FOR_EVERYTHING
				 */
				MyE2_Button 		oBUT = ((MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource());
				MyE2_TextField 		oTextField = (MyE2_TextField)((MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource()).EXT().get_O_PLACE_FOR_EVERYTHING();
				MyE2_WindowPane		oWindow = 	 (MyE2_WindowPane)((MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource()).EXT().get_O_PLACE_FOR_EVERYTHING2();
				
				String cHelpText = bibALL.CleanString(oBUT.EXT().get_C_MERKMAL());
				
				if (cHelpText.length()>oTextField.get_iMaxInputSize())
					cHelpText = cHelpText.substring(0,oTextField.get_iMaxInputSize());
				
				oTextField.setText(cHelpText);
				oWindow.userClose();
			}
			
		};
		ownAction = oACT;
		
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty( Row.PROPERTY_INSETS, new Insets(0)); 
		oStyle.setProperty( Row.PROPERTY_FONT, new E2_FontPlain());
		ownRowStyle = oStyle;
		
	};
	
	

	
	
	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);
	private boolean 				bIsComplexObject = false;
	
	private MyE2_TextField			oTextField        	= new MyE2_TextField();
	private String[][]				cVarianten		  	= null; 
	private String 					cInfoWhenEmpty		= null;
	private MyE2_Button				oButtonPopUp			  	= new MyE2_Button(E2_ResourceIcon.get_RI("textbaustein.png"),E2_ResourceIcon.get_RI("leer.png"));
	
	private Insets					oInsetsForPopupButtons = new Insets(4,2,4,0);

	
	private	Extent					oExtentPopupX = 		null;    //NEU_09
	private	Extent					oExtentPopupY = 		null;    //NEU_09
	private	Extent					oExtentPopupWidth = 	new Extent(250);
	private	Extent					oExtentPopupHeight = 	new Extent(600);
	
	
	/*
	 * wenn dieses flag true ist und es mehr als eine auswahl gibt, dann 
	 * bleibt das fenster zu (z.B. ausgeloest von anderen steuerelementen)
	 */
	private boolean 				bPassiv = 	false;
	
	

	/**
	 * @param osqlField
	 * @param Varianten
	 * @param cInfo_When_Empty
	 * @param ContentPaneForPopup
	 * @param oMessageAgent
	 * @throws myException
	 */
	public MyE2_DB_TextField_WithPopUpSelektor(	SQLField 		osqlField, 
												String[][] 		Varianten,
												String 			cInfo_When_Empty) throws myException
	{
		super(ownRowStyle);
		if (osqlField == null)
			throw new myException("MyE2_DB_TextField_WithSelektor:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);

		this.set_Varianten(Varianten,cInfo_When_Empty);

		/*
		 * uebernahme wird auf die maximale laenge definiert
		 */
		this.oTextField.set_iMaxInputSize(osqlField.get_oFieldMetaDef().get_FieldTextLENGTH());
		
		this.add(this.oTextField,new Insets(0,0,2,0));
		this.add(this.oButtonPopUp,new Insets(2,0,2,0));
		
		this.oButtonPopUp.add_oActionAgent(new popupActionAgent());
		
	}  

	
	public void set_Varianten(String[][] Varianten, String cInfoWhenempty) throws myException
	{
		this.cInfoWhenEmpty=cInfoWhenempty;
		this.cVarianten = Varianten;
		if (this.cVarianten !=null && this.cVarianten.length>0)
			if (this.cVarianten[0].length!=2)
				throw new myException("MyE2_DB_TextArea_WithSelektor: set_Varianten: cVarianten musst have an array nx2");
			
	}
		
	
	public String[][] get_Varianten()
	{
		return this.cVarianten;
	}
	
	
 
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		MyE2_DB_TextField_WithPopUpSelektor oRueck = null;
		
		try
		{
			oRueck =  new MyE2_DB_TextField_WithPopUpSelektor(this.EXT_DB().get_oSQLField(),null,null);
			oRueck.set_Varianten(this.cVarianten,this.cInfoWhenEmpty);
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_DB_TextField_WithSelektor:get_Copy:copy-error!"+ex.get_ErrorMessage().get_cMessage().COrig());
		}

		oRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRueck));
		oRueck.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oRueck));
		
		oRueck.setStyle(this.getStyle());
		oRueck.setFont(this.getFont());
		
		oRueck.get_oTextField().set_iMaxInputSize(this.oTextField.get_iMaxInputSize());
		oRueck.get_oTextField().set_iWidthPixel(this.oTextField.get_iWidthPixel());
		oRueck.get_oTextField().setText(this.oTextField.getText());
		oRueck.get_oTextField().setWidth(this.oTextField.getWidth());
		oRueck.get_oTextField().setAlignment(this.oTextField.getAlignment());
		
		return oRueck;
	}
	


	public void prepare_ContentForNew(boolean bSetDefault) throws myException 											
	{		
		
		String cText = "";

		if (!bSetDefault)
		{
			this.oTextField.setText("");
			this.EXT_DB().set_cLASTActualDBValueFormated(cText);
			this.EXT_DB().set_cLASTActualMaskValue(cText);

			return;
		}

		
		if (this.EXT_DB().get_oSQLField().get_cDefaultValueFormated() != null)
			cText = this.EXT_DB().get_oSQLField().get_cDefaultValueFormated();

		this.oTextField.setText(cText);
		this.EXT_DB().set_cLASTActualDBValueFormated(cText);
		this.EXT_DB().set_cLASTActualMaskValue(cText);
	}
	
	

	// kann ueberschrieben werden
	public boolean fillListForSelection(boolean Passiv)
	{
		if (Passiv)
			return true;
		
		return true;
	}
	
	
	
	
	/*
	 * ueberschreiben, um ein enabled bei einem nicht editierbaren feld
	 * zu unterbinden
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		boolean bVoraussetzung = enabled && this.EXT().is_ValidEnableAlowed() && this.EXT_DB().get_bCanBeEnabled(false) && this.EXT().get_bCanBeEnabled();
		this.oTextField.setEnabled(bVoraussetzung);
		this.oTextField.setStyle(this.oTextField.EXT().get_STYLE_FACTORY().get_Style(bVoraussetzung,this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_bFieldNullableBasicAndInteractive(),false));
		this.oButtonPopUp.set_bEnabled_For_Edit(bVoraussetzung);
	}


	

	public String get_cActualMaskValue() throws myException
	{
		return this.oTextField.getText();
	}


	public String get_cActualDBValueFormated() throws myException
	{
		return this.oTextField.getText();
	}


	public void set_cActualMaskValue(String cText) throws myException
	{
		this.oTextField.setText(cText);
		this.EXT_DB().set_cLASTActualMaskValue(cText);
	}


	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		if (!(	cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)))
				throw new myException("MyE2_DB_TextField:set_cActual_Formated_DBContent_To_Mask:Status not allowed !");


		this.set_cActualMaskValue(cText);
		this.EXT_DB().set_cLASTActualDBValueFormated(cText);
	}

	
	public MyE2EXT__DB_Component EXT_DB()							{		return this.oEXTDB;		}
	public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB)			{		this.oEXTDB = oEXT_DB;	}


	public boolean get_bIsComplexObject()											{		return this.bIsComplexObject;	}
	public void set_bIsComplexObject(boolean bComplex)							{		this.bIsComplexObject=bComplex;	}
	
	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oInputMAP) throws myException
	{
		return null;
	}
	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
	{
		return null;
	}

	public void show_InputStatus(boolean bInputIsOK)
	{
		this.oTextField.setStyle(this.oTextField.EXT().get_STYLE_FACTORY().get_Style(this.oTextField.isEnabled(),this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_bFieldNullableBasicAndInteractive(),!bInputIsOK));
	}

	public MyE2_TextField get_oTextField()
	{
		return this.oTextField;
	}

	public Insets get_oInsetsForPopupButtons() 
	{
		return oInsetsForPopupButtons;
	}

	public void set_oInsetsForPopupButtons(Insets insetsForPopupButtons) 
	{
		oInsetsForPopupButtons = insetsForPopupButtons;
	}


	public Extent get_oExtentPopupHeight() 
	{
		return oExtentPopupHeight;
	}


	public void set_oExtentPopupHeight(Extent extentPopupHeight) 
	{
		oExtentPopupHeight = extentPopupHeight;
	}


	public Extent get_oExtentPopupWidth() 
	{
		return oExtentPopupWidth;
	}


	public void set_oExtentPopupWidth(Extent extentPopupWidth) 
	{
		oExtentPopupWidth = extentPopupWidth;
	}


	public Extent get_oExtentPopupX() 
	{
		return oExtentPopupX;
	}


	public void set_oExtentPopupX(Extent extentPopupX) 
	{
		oExtentPopupX = extentPopupX;
	}


	public Extent get_oExtentPopupY() 
	{
		return oExtentPopupY;
	}


	public void set_oExtentPopupY(Extent extentPopupY) 
	{
		oExtentPopupY = extentPopupY;
	}

	public MyE2_Button get_oButtonPopUp() 
	{
		return oButtonPopUp;
	}
	

	public boolean get_bPassiv() 
	{
		return bPassiv;
	}


	public void set_bPassiv(boolean passiv) 
	{
		bPassiv = passiv;
	}






	public String get_cInfoWhenEmpty() 
	{
		return cInfoWhenEmpty;
	}

	


	// action-agent fuer den popup-button
	private class popupActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			MyE2_DB_TextField_WithPopUpSelektor oThis = MyE2_DB_TextField_WithPopUpSelektor.this;
			
			if (!oThis.fillListForSelection(oThis.bPassiv))
				return;
			
			
				
			if (oThis.cVarianten.length==1)    // falls es nur eine variante gibt, dann wird diese agberufen, popup bleibt zu
			{
				oThis.oTextField.setText(oThis.cVarianten[0][1]);
				return;
			}
			
			if (oThis.bPassiv)
				return;
			
			
			MyE2_WindowPane 	oWindow = new MyE2_WindowPane(new MyE2_String("Auswahl:"),oThis.oExtentPopupWidth,oThis.oExtentPopupHeight,true);
			E2_ContentPane 		oPane = new E2_ContentPane(true);
			MyE2_Column 			oCol = new MyE2_Column();
			
			oWindow.add(oPane);
			oPane.add(oCol);
			
			
			if (oThis.cVarianten != null && oThis.cVarianten.length>0)
			{
				for (int i=0;i<oThis.cVarianten.length;i++)
				{
					MyE2_Button oButHelp = new MyE2_Button(new MyE2_String(oThis.cVarianten[i][0],false));
					oButHelp.EXT().set_C_MERKMAL(oThis.cVarianten[i][1]);
					oButHelp.EXT().set_O_PLACE_FOR_EVERYTHING(oThis.oTextField);
					oButHelp.EXT().set_O_PLACE_FOR_EVERYTHING2(oWindow);
					oButHelp.add_oActionAgent(MyE2_DB_TextField_WithPopUpSelektor.ownAction);
					oCol.add(oButHelp,oThis.oInsetsForPopupButtons);
					
				}
			}
			else
			{
				if (bibALL.isEmpty(oThis.cInfoWhenEmpty))
				{
					oCol.add(new MyE2_Label(new MyE2_String("Text-Auswahl ist undefiniert !")));
				}
				else
				{
					try
					{
						StringSeparator oSep = new StringSeparator(oThis.cInfoWhenEmpty,"|");
						for (int i=0;i<oSep.size();i++)
						{
							oCol.add(new MyE2_Label(new MyE2_String(oSep.get_(i),false)),new Insets(2,2,2,2));
						}
					}
					catch (myException ex)
					{
						oCol.add(new MyE2_Label(new MyE2_String("@@@ERROR@@@")));
					}
				}
			}
			
			if (oThis.oExtentPopupX != null) oWindow.setPositionX(oThis.oExtentPopupX);
			if (oThis.oExtentPopupY != null) oWindow.setPositionX(oThis.oExtentPopupY);
			
			new E2_RecursiveSearchParent_ContentPane_NUMBER_ONE().get_FoundPane_ThrowExWhenNotFound().add(oWindow);
		} 
	}



















	
}
