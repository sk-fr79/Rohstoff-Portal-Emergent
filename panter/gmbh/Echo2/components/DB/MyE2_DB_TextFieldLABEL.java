package panter.gmbh.Echo2.components.DB;

import nextapp.echo2.app.Font;
import nextapp.echo2.app.LayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/*
 * spezielle TextFieldklasse, die immer auf Diasabled steht 
 */
public class MyE2_DB_TextFieldLABEL extends MyE2_DB_TextField implements MyE2IF__DB_Component,MyE2IF__Component, E2_IF_Copy
{

	public MyE2_DB_TextFieldLABEL(SQLField osqlField) throws myException
	{
		super(osqlField);
		this.EXT().set_bDisabledFromBasic(true);
	}

	public MyE2_DB_TextFieldLABEL(SQLField osqlField, boolean bAutoAlign, int iWidthInPixel) throws myException
	{
		super(osqlField, bAutoAlign, iWidthInPixel);
		this.EXT().set_bDisabledFromBasic(true);
	}

	public MyE2_DB_TextFieldLABEL(SQLField osqlField, boolean bAutoAlign,int iWidthInPixel, LayoutData oLayout) throws myException
	{
		super(osqlField, bAutoAlign, iWidthInPixel,oLayout);
		this.EXT().set_bDisabledFromBasic(true);
	}
	public MyE2_DB_TextFieldLABEL(SQLField osqlField, boolean bAutoAlign,int iWidthInPixel, LayoutData oLayout, Font oFont) throws myException
	{
		super(osqlField, bAutoAlign, iWidthInPixel,oLayout,oFont);
		this.EXT().set_bDisabledFromBasic(true);
	}

	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		MyE2_DB_TextFieldLABEL oRueck = null;
		
		try
		{
			oRueck =  new MyE2_DB_TextFieldLABEL(this.EXT_DB().get_oSQLField());
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
		
		return oRueck;
	}
	


	
}
