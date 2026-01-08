package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.text.TextComponent;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.BasicInterfaces.IF_Align;
import panter.gmbh.Echo2.Factorys.XXX_StyleFactory;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_DB_TextField extends MyE2_TextField implements MyE2IF__DB_Component,MyE2IF__Component, E2_IF_Copy, IF_Align<MyE2_DB_TextField>
{

	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);
	private boolean 				bIsComplexObject = false;

	private boolean  				bAutoalign = false;
	
	
	
	/**
	 * @param osqlField
	 * @throws myException
	 */
	public MyE2_DB_TextField(SQLField osqlField) throws myException
	{
		super();
		if (osqlField == null)
			throw new myException("MyE2_DB_TextField:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
		
		if (this.oEXTDB.get_oSQLField().get_oFieldMetaDef().get_iNumberCharactersInMask()>0)
		{
			this.set_iMaxInputSize(this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_iNumberCharactersInMask());
		}
		
		this.setAlignment(new Alignment(Alignment.LEFT,Alignment.DEFAULT));

	}  
	
	

	
	/**
	 * @param osqlField
	 * @param bAutoAlign
	 * @param iWidthInPixel
	 * @throws myException
	 */
	public MyE2_DB_TextField(SQLField osqlField, boolean bAutoAlign,int iWidthInPixel) throws myException
	{
		super();
		if (osqlField == null)
			throw new myException("MyE2_DB_TextField:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
		
		this.setAlignment(new Alignment(Alignment.LEFT,Alignment.DEFAULT));
		
		if (bAutoAlign)
		{
			if 		(osqlField.get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_NUMMER))
			{
				this.setAlignment(new Alignment(Alignment.RIGHT,Alignment.DEFAULT));
			}
			else if (osqlField.get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_DATUM))
			{
				this.setAlignment(new Alignment(Alignment.CENTER,Alignment.DEFAULT));
			}
		}
		
		if (iWidthInPixel > 0)
		{
			this.set_iWidthPixel(iWidthInPixel);
		}
		
		if (this.oEXTDB.get_oSQLField().get_oFieldMetaDef().get_iNumberCharactersInMask()>0)
		{
			this.set_iMaxInputSize(this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_iNumberCharactersInMask());
		}
	
	}  

	
	/**
	 * @param osqlField
	 * @param bAutoAlign
	 * @param iWidthInPixel
	 * @throws myException
	 */
	public MyE2_DB_TextField(SQLField osqlField, int iWidthInPixel, XXX_StyleFactory oStyle) throws myException
	{
		super();
		if (osqlField == null)
			throw new myException("MyE2_DB_TextField:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
		
		//this.setAlignment(new Alignment(Alignment.LEFT,Alignment.DEFAULT));

		if (oStyle!=null)
		{
			this.EXT().set_STYLE_FACTORY(oStyle);
		}
		
		if (iWidthInPixel > 0)
		{
			this.set_iWidthPixel(iWidthInPixel);
		}
		
		if (this.oEXTDB.get_oSQLField().get_oFieldMetaDef().get_iNumberCharactersInMask()>0)
		{
			this.set_iMaxInputSize(this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_iNumberCharactersInMask());
		}
	
	}  

	
	/**
	 * 
	 * @param osqlField
	 * @param bAutoAlign
	 * @param iWidthInPixel
	 * @param iMaxInputSize
	 * @param bDisabledFromBasic
	 * @throws myException
	 */
	public MyE2_DB_TextField(SQLField osqlField, boolean bAutoAlign,int iWidthInPixel, int iMaxInputSize, boolean bDisabledFromBasic) throws myException
	{
		super();
		if (osqlField == null)
			throw new myException("MyE2_DB_TextField:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
		
		this.setAlignment(new Alignment(Alignment.LEFT,Alignment.DEFAULT));

		
		if (bAutoAlign)
		{
			if 		(osqlField.get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_NUMMER))
			{
				this.setAlignment(new Alignment(Alignment.RIGHT,Alignment.DEFAULT));
			}
			else if (osqlField.get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_DATUM))
			{
				this.setAlignment(new Alignment(Alignment.RIGHT,Alignment.DEFAULT));
			}
		}
		
		if (iWidthInPixel > 0)
		{
			this.set_iWidthPixel(iWidthInPixel);
		}
		
		
		if (iMaxInputSize > 0)
		{
			this.set_iMaxInputSize(iMaxInputSize);
		}
		
		if (bDisabledFromBasic)
		{
			this.EXT().set_bDisabledFromBasic(true);
		}
		
		if (this.oEXTDB.get_oSQLField().get_oFieldMetaDef().get_iNumberCharactersInMask()>0)
		{
			this.set_iMaxInputSize(this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_iNumberCharactersInMask());
		}
	
	}  

	

	/**
	 * 
	 * @param osqlField
	 * @param bAutoAlign
	 * @param iWidthInPixel
	 * @param iMaxInputSize
	 * @param bDisabledFromBasic
	 * @param oFont
	 * @throws myException
	 */
	public MyE2_DB_TextField(SQLField osqlField, boolean bAutoAlign,int iWidthInPixel, int iMaxInputSize, boolean bDisabledFromBasic, Font oFont) throws myException
	{
		super();
		if (osqlField == null)
			throw new myException("MyE2_DB_TextField:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
		
		this.setAlignment(new Alignment(Alignment.LEFT,Alignment.DEFAULT));

		if (bAutoAlign)
		{
			if 		(osqlField.get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_NUMMER))
			{
				this.setAlignment(new Alignment(Alignment.RIGHT,Alignment.DEFAULT));
			}
			else if (osqlField.get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_DATUM))
			{
				this.setAlignment(new Alignment(Alignment.RIGHT,Alignment.DEFAULT));
			}
		}
		
		if (iWidthInPixel > 0)
		{
			this.set_iWidthPixel(iWidthInPixel);
		}
		
		
		if (iMaxInputSize > 0)
		{
			this.set_iMaxInputSize(iMaxInputSize);
		}
		
		if (bDisabledFromBasic)
		{
			this.EXT().set_bDisabledFromBasic(true);
		}
		
		if (this.oEXTDB.get_oSQLField().get_oFieldMetaDef().get_iNumberCharactersInMask()>0)
		{
			this.set_iMaxInputSize(this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_iNumberCharactersInMask());
		}
	
		if (oFont != null)
		{
			this.setFont(oFont);
		}

		
	}  


	
	
	

	/**
	 * 
	 * @param osqlField
	 * @param bAutoAlign
	 * @param iWidthInPixel
	 * @param oLayout
	 * @throws myException
	 */
	public MyE2_DB_TextField(SQLField osqlField, boolean bAutoAlign,int iWidthInPixel, LayoutData oLayout) throws myException
	{
		super();
		if (osqlField == null)
			throw new myException("MyE2_DB_TextField:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
		
		this.setAlignment(new Alignment(Alignment.LEFT,Alignment.DEFAULT));

		if (bAutoAlign)
		{
			if 		(osqlField.get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_NUMMER))
			{
				this.setAlignment(new Alignment(Alignment.RIGHT,Alignment.DEFAULT));
			}
			else if (osqlField.get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_DATUM))
			{
				this.setAlignment(new Alignment(Alignment.CENTER,Alignment.DEFAULT));
			}
		}
		
		if (iWidthInPixel > 0)
		{
			this.set_iWidthPixel(iWidthInPixel);
		}
		
		if (oLayout != null)
		{
			this.setLayoutData(oLayout);
		}
		
		if (this.oEXTDB.get_oSQLField().get_oFieldMetaDef().get_iNumberCharactersInMask()>0)
		{
			this.set_iMaxInputSize(this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_iNumberCharactersInMask());
		}

	}  

	
	

	/**
	 * 
	 * @param osqlField
	 * @param bAutoAlign
	 * @param iWidthInPixel
	 * @param oLayout
	 * @param oFont
	 * @throws myException
	 */
	public MyE2_DB_TextField(SQLField osqlField, boolean bAutoAlign,int iWidthInPixel, LayoutData oLayout, Font oFont) throws myException
	{
		super();
		if (osqlField == null)
			throw new myException("MyE2_DB_TextField:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);

		this.setAlignment(new Alignment(Alignment.LEFT,Alignment.DEFAULT));

		if (bAutoAlign)
		{
			if 		(osqlField.get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_NUMMER))
			{
				this.setAlignment(new Alignment(Alignment.RIGHT,Alignment.DEFAULT));
			}
			else if (osqlField.get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_DATUM))
			{
				this.setAlignment(new Alignment(Alignment.CENTER,Alignment.DEFAULT));
			}
		}
		
		if (iWidthInPixel > 0)
		{
			this.set_iWidthPixel(iWidthInPixel);
		}
		
		if (oLayout != null)
		{
			this.setLayoutData(oLayout);
		}
		
		if (oFont != null)
		{
			this.setFont(oFont);
		}
		
		if (this.oEXTDB.get_oSQLField().get_oFieldMetaDef().get_iNumberCharactersInMask()>0)
		{
			this.set_iMaxInputSize(this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_iNumberCharactersInMask());
		}

	}  

	
	
//	//2012-02-21: fuer die reg-ex-validierung
//	private void __set4All()
//	{
//		this.EXT().add_FieldSetters_AND_Validator__AfterReadInputMAP(new Valid_DBField_MaskInput());
//	}
	
	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		MyE2_DB_TextField oRueck = null;
		
		try
		{
			oRueck =  new MyE2_DB_TextField(this.EXT_DB().get_oSQLField());
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
		
		
		//2011-11-22: textfeld mit automatischer verkleinerung ab einer bestimmten laenge
		oRueck.set_AutoFontDownSize(this.get_iLengthFontDownsizingStart(), this.get_oFontNormal_4_FontDownsizing(), this.get_oFontSmall_4_FontDownsizing());

		return oRueck;
	}
	


	public void prepare_ContentForNew(boolean bSetDefault) throws myException 											
	{		
		String cText = "";

		
		if (!bSetDefault)
		{
			this.setText("");
			this.EXT_DB().set_cLASTActualDBValueFormated(cText);
			this.EXT_DB().set_cLASTActualMaskValue(cText);

			return;
		}

		
		if (this.EXT_DB().get_oSQLField().get_cDefaultValueFormated() != null)
			cText = this.EXT_DB().get_oSQLField().get_cDefaultValueFormated();

		this.setText(cText);
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

		this.setEnabled(bVoraussetzung);
		
		MutableStyle  style = this.EXT().get_STYLE_FACTORY().get_Style(bVoraussetzung,this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_bFieldNullableBasicAndInteractive(),false);
		
		//style.setProperty(TextComponent.PROPERTY_BACKGROUND, Color.GREEN);
		this.setStyle(style);
		
		if (bVoraussetzung) {
			this.setFocusTraversalParticipant(true);
		} else {
			this.setFocusTraversalParticipant(false);
		}
		this.setBackground((Color)style.getProperty(TextComponent.PROPERTY_BACKGROUND));
	}


	

	public String get_cActualMaskValue() throws myException
	{
		return this.getText();
	}


	public String get_cActualDBValueFormated() throws myException
	{
		return this.getText();
	}


	public void set_cActualMaskValue(String cText) throws myException
	{
		this.setText(cText);
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
//		if (this.EXT_DB().get_oSQLField().get_cFieldName().equals(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_PROZENT))
//		{
//			System.out.println(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_PROZENT+":input-ok ?"+(bInputIsOK?" <JA>":"<NEIN>"));
//		}
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(this.isEnabled(),this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_bFieldNullableBasicAndInteractive(),!bInputIsOK));
	}



	public boolean get_bAutoalign()
	{
		return bAutoalign;
	}

	

	
	
}
