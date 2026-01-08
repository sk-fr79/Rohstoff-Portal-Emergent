package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import nextapp.echo2.app.LayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_TextField_With_DatePOPUP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_DB_TextField_With_DatePOPUP extends MyE2_TextField_With_DatePOPUP implements MyE2IF__DB_Component,MyE2IF__Component, E2_IF_Copy
{
	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);

	public MyE2_DB_TextField_With_DatePOPUP(SQLField osqlField) throws myException
	{
		super();
		if (osqlField == null)
			throw new myException("MyE2_DB_TextField:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
	}

	public MyE2_DB_TextField_With_DatePOPUP(SQLField osqlField, String text, int iwidthPixel) throws myException
	{
		super(text, iwidthPixel);
		if (osqlField == null)
			throw new myException("MyE2_DB_TextField:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
	}

	public MyE2_DB_TextField_With_DatePOPUP(SQLField osqlField, String text, int iwidthPixel, boolean bDisabledFromBasic, LayoutData oLayout) throws myException
	{
		super(text, iwidthPixel);
		if (osqlField == null)
			throw new myException("MyE2_DB_TextField:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
		
		if (bDisabledFromBasic)
		{
			this.EXT().set_bDisabledFromBasic(true);
		}
		
		if (oLayout != null)
		{
			this.setLayoutData(oLayout);
		}

	}
	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		MyE2_DB_TextField_With_DatePOPUP oRueck = null;
		
		try
		{
			oRueck =  new MyE2_DB_TextField_With_DatePOPUP(this.EXT_DB().get_oSQLField());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_DB_TextField_With_DatePOPUP:get_Copy:copy-error!");
		}

		oRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRueck));
		oRueck.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oRueck));
		
		oRueck.get_oTextField().setStyle(this.getStyle());
		oRueck.get_oTextField().setFont(this.getFont());
		
		oRueck.get_oTextField().set_iMaxInputSize(this.get_oTextField().get_iMaxInputSize());
		oRueck.get_oTextField().set_iWidthPixel(this.get_oTextField().get_iWidthPixel());
		oRueck.get_oTextField().setText(this.get_oTextField().getText());
		oRueck.get_oTextField().setWidth(this.get_oTextField().getWidth());
		oRueck.get_oTextField().setAlignment(this.get_oTextField().getAlignment());
		
		oRueck.setLayoutData(this.getLayoutData());
		return oRueck;
	}

	
	
	public String get_cActualMaskValue() throws myException
	{
		return this.get_oTextField().getText();
	}


	public String get_cActualDBValueFormated() throws myException
	{
		return this.get_oTextField().getText();
	}


	public void set_cActualMaskValue(String cText) throws myException
	{
		this.get_oTextField().setText(cText);
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


	public boolean get_bIsComplexObject()							{		return false;	}
	public void set_bIsComplexObject(boolean bComplex)				{			}
	
	
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
		this.get_oTextField().setStyle(this.get_oTextField().EXT().get_STYLE_FACTORY().get_Style(this.isEnabled(),this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_bFieldNullableBasicAndInteractive(),!bInputIsOK));
	}

	
	public void prepare_ContentForNew(boolean bSetDefault) throws myException 											
	{		
		String cText = "";

		
		if (!bSetDefault)
		{
			this.get_oTextField().setText("");
			this.EXT_DB().set_cLASTActualDBValueFormated(cText);
			this.EXT_DB().set_cLASTActualMaskValue(cText);

			return;
		}

		
		if (this.EXT_DB().get_oSQLField().get_cDefaultValueFormated() != null)
			cText = this.EXT_DB().get_oSQLField().get_cDefaultValueFormated();

		this.get_oTextField().setText(cText);
		this.EXT_DB().set_cLASTActualDBValueFormated(cText);
		this.EXT_DB().set_cLASTActualMaskValue(cText);

	}


	
	

}
