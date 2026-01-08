package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Row;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/*
 * textfeld mit einem popup-selektor, der varianten darstellt
 */
public class MyE2_DB_TextField_WithSelektor extends MyE2_Row implements MyE2IF__DB_Component,MyE2IF__Component, E2_IF_Copy
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
				
				String cHelpText = bibALL.CleanString(oBUT.EXT().get_C_MERKMAL());
				
				if (cHelpText.length()>oTextField.get_iMaxInputSize())
					cHelpText = cHelpText.substring(0,oTextField.get_iMaxInputSize());
				
				oTextField.setText(cHelpText);
			}
			
		};
		ownAction = oACT;
		
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty( Row.PROPERTY_INSETS, new Insets(0)); 
		oStyle.setProperty( Row.PROPERTY_FONT, new E2_FontPlain());
		ownRowStyle = oStyle;
		
	};
	
	
	
	public static E2_ResourceIcon ICON_FOR_POPUP = E2_ResourceIcon.get_RI("textbaustein.png");

	
	
	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);
	private boolean 				bIsComplexObject = false;
	
	private ownTextField			oTextField        = new ownTextField();
	private String[][]				cVarianten		  = null; 
	private String 					cInfoWhenEmpty		= null;
	private MyE2_PopUpMenue			oPopUp			  = null;
	private Component				oTopInfoComponent = null;
	
	private boolean  				bTextFieldIsWriteable = true;
	
	private boolean     			b_AddEmptyValueInFront = false;
	


	/**
	 * @param osqlField
	 * @param Varianten ([i][0] = beschreibung  [i][1] = wert
	 * @param TopZusatzInfo
	 * @param cInfoWhenEmpty (Falls mehrzeilig, durch | getrennte Teilstrings)
	 * @throws myException
	 */
	public MyE2_DB_TextField_WithSelektor(	SQLField 		osqlField) throws myException
	{
		super(ownRowStyle);
		if (osqlField == null)
			throw new myException("MyE2_DB_TextField_WithSelektor:Constructor:null-SQLField not allowed !");

		
		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);

		this.oPopUp=new MyE2_PopUpMenue(ICON_FOR_POPUP,E2_ResourceIcon.get_RI("leer.png"), true);

		/*
		 * uebernahme wird auf die maximale laenge definiert
		 */
		this.oTextField.set_iMaxInputSize(osqlField.get_oFieldMetaDef().get_FieldTextLENGTH());
		
		this.add(this.oTextField,new Insets(0,0,2,0));
		this.add(this.oPopUp,new Insets(2,0,2,0));
	}  


	
	
	
	/**
	 * @param osqlField
	 * @param Varianten ([i][0] = beschreibung  [i][1] = wert
	 * @param bAddEmptyValueInFront 
	 * @param TopZusatzInfo
	 * @param cInfoWhenEmpty (Falls mehrzeilig, durch | getrennte Teilstrings)
	 * @throws myException
	 */
	public MyE2_DB_TextField_WithSelektor(	SQLField 		osqlField, 
											String[][] 		Varianten,
											String 			cInfo_When_Empty,
											boolean 		bAddEmptyValueInFront) throws myException
	{
		super(ownRowStyle);
		if (osqlField == null)
			throw new myException("MyE2_DB_TextField_WithSelektor:Constructor:null-SQLField not allowed !");


		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);

		this.oPopUp=new MyE2_PopUpMenue(ICON_FOR_POPUP,E2_ResourceIcon.get_RI("leer.png"), true);

		this.set_Varianten(Varianten,cInfo_When_Empty, null, bAddEmptyValueInFront);

		this.b_AddEmptyValueInFront = bAddEmptyValueInFront;
		
		/*
		 * uebernahme wird auf die maximale laenge definiert
		 */
		this.oTextField.set_iMaxInputSize(osqlField.get_oFieldMetaDef().get_FieldTextLENGTH());
		
		this.add(this.oTextField,new Insets(0,0,2,0));
		this.add(this.oPopUp,new Insets(2,0,2,0));
	}  


	
	
	/**
	 * @param osqlField
	 * @param Varianten ([i][0] = beschreibung  = wert
	 * @param bAddEmptyValueInFront 
	 * @param TopZusatzInfo
	 * @param cInfoWhenEmpty (Falls mehrzeilig, durch | getrennte Teilstrings)
	 * @throws myException
	 */
	public MyE2_DB_TextField_WithSelektor(	SQLField 		osqlField, 
											String[] 		Varianten,
											String 			cInfo_When_Empty, 
											boolean 		bAddEmptyValueInFront) throws myException
	{
		super(ownRowStyle);
		if (osqlField == null)
			throw new myException("MyE2_DB_TextField_WithSelektor:Constructor:null-SQLField not allowed !");

		this.b_AddEmptyValueInFront = bAddEmptyValueInFront;


		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);

		this.oPopUp=new MyE2_PopUpMenue(ICON_FOR_POPUP,E2_ResourceIcon.get_RI("leer.png"), true);

		this.set_Varianten(Varianten,cInfo_When_Empty, null, bAddEmptyValueInFront);

		/*
		 * uebernahme wird auf die maximale laenge definiert
		 */
		this.oTextField.set_iMaxInputSize(osqlField.get_oFieldMetaDef().get_FieldTextLENGTH());
		
		this.add(this.oTextField,new Insets(0,0,2,0));
		this.add(this.oPopUp,new Insets(2,0,2,0));
	}  

	
	
	

	/**
	 * @param osqlField
	 * @param cSQLQuery  Abfrage enthaehlt 	 beschreibung,wert
	 * @param bAddEmptyValueInFront 
	 * @param TopZusatzInfo 
	 * @throws myException
	 */
	public MyE2_DB_TextField_WithSelektor(	SQLField 		osqlField, 
											String 			cSQLQuery, 
											boolean 		bAddEmptyValueInFront) throws myException
	{
		super(ownRowStyle);
		if (osqlField == null)
			throw new myException("MyE2_DB_TextField_WithSelektor:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
		
		this.b_AddEmptyValueInFront = bAddEmptyValueInFront;


		String[][] cRueck = null;
		if (!bibALL.isEmpty(cSQLQuery))
		{
			cRueck=bibDB.EinzelAbfrageInArray(cSQLQuery,"");
		}
		
		String[][] 	Varianten = null;
		String 		cInfo_When_Empty = new MyE2_String("*** KEINE EINTRÄGE ***").CTrans();
		
		if (cRueck != null && cRueck.length>0)
		{
			if (cRueck[0].length==1)
			{
				Varianten = new String[cRueck.length][2];
				for (int i=0;i<cRueck.length;i++)
					Varianten[i][0]=Varianten[i][1]=cRueck[i][0];

			}
			else
			{
				Varianten = new String[cRueck.length][2];
				for (int i=0;i<cRueck.length;i++)
				{
					Varianten[i][0]=cRueck[i][0];
					Varianten[i][1]=cRueck[i][1];
				}
			}
		}
		
		this.oPopUp=new MyE2_PopUpMenue(ICON_FOR_POPUP,E2_ResourceIcon.get_RI("leer.png"), true);
		this.set_Varianten(Varianten,cInfo_When_Empty, null, bAddEmptyValueInFront);

		/*
		 * uebernahme wird auf die maximale laenge definiert
		 */
		this.oTextField.set_iMaxInputSize(osqlField.get_oFieldMetaDef().get_FieldTextLENGTH());
		
		this.add(this.oTextField,new Insets(0,0,2,0));
		this.add(this.oPopUp,new Insets(2,0,2,0));
		
		
		
	}  

	
	
	public void set_Varianten(String[] Varianten, String cInfoWhenempty, Component TopZusatzInfo, boolean bAddEmptyValueInFront) throws myException
	{
		String[][] cHelp = null;
		
		if (Varianten != null && Varianten.length>0)
		{
			cHelp = new String[Varianten.length][2];
			for (int i=0;i<Varianten.length;i++)
			{
				cHelp[i][0]=Varianten[i];
				cHelp[i][1]=Varianten[i];
			}
		}
		this.set_Varianten(cHelp,cInfoWhenempty,TopZusatzInfo, bAddEmptyValueInFront);
		
	}
	
	
	
	public void set_Varianten(String[][] Varianten, String cInfoWhenempty, Component TopZusatzInfo, boolean bAddEmptyValueInFront) throws myException
	{
		this.oTopInfoComponent = TopZusatzInfo;
		this.cInfoWhenEmpty=cInfoWhenempty;
		this.cVarianten = Varianten;
		if (this.cVarianten !=null && this.cVarianten.length>0)
			if (this.cVarianten[0].length!=2)
				throw new myException("MyE2_DB_TextArea_WithSelektor: set_Varianten: cVarianten musst have an array nx2");
			
		this.oPopUp.removeAllButtons();
		if (this.oTopInfoComponent != null)
			this.oPopUp.get_oColInnen().add(this.oTopInfoComponent);

		
		if (this.cVarianten != null && this.cVarianten.length>0)
		{
			if (bAddEmptyValueInFront)
			{
				MyE2_Button oButHelp = new MyE2_Button(new MyE2_String("-",false));
				oButHelp.EXT().set_C_MERKMAL("");
				oButHelp.EXT().set_O_PLACE_FOR_EVERYTHING(this.oTextField);
				oButHelp.add_oActionAgent(MyE2_DB_TextField_WithSelektor.ownAction);
				this.oPopUp.addButton(oButHelp,true);
			}
			
			for (int i=0;i<this.cVarianten.length;i++)
			{
				MyE2_Button oButHelp = new MyE2_Button(new MyE2_String(this.cVarianten[i][0],false));
				oButHelp.EXT().set_C_MERKMAL(this.cVarianten[i][1]);
				oButHelp.EXT().set_O_PLACE_FOR_EVERYTHING(this.oTextField);
				oButHelp.add_oActionAgent(MyE2_DB_TextField_WithSelektor.ownAction);
				this.oPopUp.addButton(oButHelp,true);
				
			}
		}
		else
		{
			if (bibALL.isEmpty(this.cInfoWhenEmpty))
			{
				this.oPopUp.get_oColInnen().add(new MyE2_Label(new MyE2_String("Text-Auswahl ist undefiniert !")));
			}
			else
			{
				StringSeparator oSep = new StringSeparator(this.cInfoWhenEmpty,"|");
				for (int i=0;i<oSep.size();i++)
				{
					this.oPopUp.get_oColInnen().add(new MyE2_Label(new MyE2_String(oSep.get_(i),false)),new Insets(2,2,2,2));
				}
			}
		}
		
	}
		
	
	public String[][] get_Varianten()
	{
		return this.cVarianten;
	}
	
	
 
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		MyE2_DB_TextField_WithSelektor oRueck = null;
		
		try
		{
			oRueck =  new MyE2_DB_TextField_WithSelektor(this.EXT_DB().get_oSQLField());
			oRueck.set_Varianten(this.cVarianten,this.cInfoWhenEmpty, this.oTopInfoComponent, false);
			oRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRueck));
			oRueck.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oRueck));
			
			oRueck.setStyle(this.getStyle());
			oRueck.setFont(this.getFont());
			
			oRueck.get_oTextField().set_iMaxInputSize(this.oTextField.get_iMaxInputSize());
			oRueck.get_oTextField().set_iWidthPixel(this.oTextField.get_iWidthPixel());
			oRueck.get_oTextField().setText(this.oTextField.getText());
			oRueck.get_oTextField().setFont(this.oTextField.getFont());
			oRueck.get_oTextField().setWidth(this.oTextField.getWidth());
			oRueck.get_oTextField().setAlignment(this.oTextField.getAlignment());
			
			oRueck.get_oPopUp().get_oContainerEx().setWidth(this.get_oPopUp().get_oContainerEx().getWidth());
			oRueck.get_oPopUp().get_oContainerEx().setHeight(this.get_oPopUp().get_oContainerEx().getHeight());
			
			oRueck.get_oPopUp().set_oIconAktiv(this.oPopUp.get_oIconAktiv());
			oRueck.get_oPopUp().set_oIconInactiv(this.oPopUp.get_oIconInactiv());
			
			oRueck.get_oPopUp().setPopUpAlignment(this.get_oPopUp().getPopUpAlignment());
			oRueck.get_oPopUp().setPopUpLeftOffset(this.get_oPopUp().getPopUpLeftOffset());
			
			oRueck.get_oPopUp().setFocusTraversalParticipant(this.get_oPopUp().isFocusTraversalParticipant());
			oRueck.get_oTextField().setFocusTraversalParticipant(this.get_oTextField().isFocusTraversalParticipant());
			
			oRueck.set_bTextFieldIsWriteable(this.get_bTextFieldIsWriteable());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_DB_TextField_WithSelektor:get_Copy:copy-error!"+ex.get_ErrorMessage().get_cMessage().COrig());
		}

		
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
	

	
	/*
	 * ueberschreiben, um ein enabled bei einem nicht editierbaren feld
	 * zu unterbinden
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		boolean bVoraussetzung = enabled && this.EXT().is_ValidEnableAlowed() && this.EXT_DB().get_bCanBeEnabled(false) && this.EXT().get_bCanBeEnabled();
		this.oTextField.set_bEnabled_For_Edit(bVoraussetzung && this.bTextFieldIsWriteable);
		this.oTextField.setStyle(this.oTextField.EXT().get_STYLE_FACTORY().get_Style(bVoraussetzung && this.bTextFieldIsWriteable,this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_bFieldNullableBasicAndInteractive(),false));
		this.oPopUp.set_bEnabled_For_Edit(bVoraussetzung);
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



	public MyE2_PopUpMenue get_oPopUp() 
	{
		return oPopUp;
	}


	public boolean get_bTextFieldIsWriteable()
	{
		return bTextFieldIsWriteable;
	}
	
	
	
	public void set_bTextFieldIsWriteable(boolean textFieldIsWriteable) throws myException
	{
		bTextFieldIsWriteable = textFieldIsWriteable;
		if (!textFieldIsWriteable)
		{
			this.oTextField.set_bEnabled_For_Edit(false);
		}
	}


	private class ownTextField extends MyE2_TextField
	{

		public ownTextField()
		{
			super();
		}
		
		public void set_bEnabled_For_Edit(boolean bEnabled) throws myException
		{

			boolean enabled = bEnabled;
			
			if (!MyE2_DB_TextField_WithSelektor.this.bTextFieldIsWriteable)
			{
				enabled = false;
			}
			
			this.setEnabled(enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled());
			this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(this.isEnabled(),true,false));
			
			if (this.isEnabled())
				this.setFocusTraversalParticipant(true);
			else
				this.setFocusTraversalParticipant(false);
		
		}

	}



	public void set_TopZusatzInfo(Component TopZusatzInfo) throws myException
	{
		this.set_Varianten(this.cVarianten, this.cInfoWhenEmpty, TopZusatzInfo, this.b_AddEmptyValueInFront);
	}


	
	public boolean get_bAddEmptyValueInFront() 
	{
		return b_AddEmptyValueInFront;
	}




	
}
