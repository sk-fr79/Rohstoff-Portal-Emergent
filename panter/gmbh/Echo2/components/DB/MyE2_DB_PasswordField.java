package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_PasswordField;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


public class MyE2_DB_PasswordField extends MyE2_PasswordField implements MyE2IF__DB_Component,MyE2IF__Component, E2_IF_Copy
{

	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);
	private boolean 				bIsComplexObject = false;

	
	public MyE2_DB_PasswordField(SQLField osqlField) throws myException
	{
		super();
		if (osqlField == null)
			throw new myException("MyE2_PasswordField:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
	}  
	
	
	
	/**
	 * 
	 * @param osqlField
	 * @param iWidthInPixel
	 * @param iMaxInputSize
	 * @param bDisabledFromBasic
	 * @throws myException
	 */
	public MyE2_DB_PasswordField(SQLField osqlField, int iWidthInPixel, int iMaxInputSize, boolean bDisabledFromBasic) throws myException
	{
		super();
		if (osqlField == null)
			throw new myException("MyE2_DB_TextField:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
		
		
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
	
	
 
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		MyE2_DB_PasswordField oRueck = null;
		
		try
		{
			oRueck =  new MyE2_DB_PasswordField(this.EXT_DB().get_oSQLField());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_PasswordField:get_Copy:copy-error!");
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
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(bVoraussetzung,this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_bFieldNullableBasicAndInteractive(),false));
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
				throw new myException("MyE2_PasswordField:set_cActual_Formated_DBContent_To_Mask:Status not allowed !");


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
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(this.isEnabled(),this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_bFieldNullableBasicAndInteractive(),!bInputIsOK));
	}




	
	
	
}
