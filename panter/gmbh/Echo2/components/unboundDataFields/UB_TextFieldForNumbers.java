package panter.gmbh.Echo2.components.unboundDataFields;


import java.util.Locale;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;

public class UB_TextFieldForNumbers extends UB_TextField implements IF_UB_Fields
{

	private int iDecimalSize = 0;
	private String cStartDBWert = null; 


	// zwei erlaubte konstruktoren, der rest schmeisst exception
	public UB_TextFieldForNumbers(String cDBFieldName, int DecimalSize, boolean EmptyAllowed, String cTextFormated) throws myException
	{
		super(cDBFieldName, EmptyAllowed, cTextFormated);
		this.iDecimalSize = DecimalSize;
		this.set_StartValue(cTextFormated);
		this.setAlignment(new Alignment(Alignment.RIGHT,Alignment.DEFAULT));
	}
	
	public UB_TextFieldForNumbers(String cDBFieldName, int DecimalSize, boolean EmptyAllowed,String cTextFormated, int iwidthPixel, int imaxInputSize) throws myException
	{
		super(cDBFieldName, EmptyAllowed,cTextFormated, iwidthPixel, imaxInputSize);
		this.iDecimalSize = DecimalSize;
		this.set_StartValue(cTextFormated);
		this.setAlignment(new Alignment(Alignment.RIGHT,Alignment.DEFAULT));
	}

	
	public void set_StartValue(String cStartValue) throws myException
	{
		this.cStartDBWert = bibALL.null2leer(cStartValue).trim();
		if (!this.cStartDBWert.equals(""))
		{
			// dann formatieren oder abweisen
			DotFormatter oDF = new  DotFormatter(bibALL.null2leer(cStartValue),this.iDecimalSize,Locale.GERMAN,true,3);
			
			if (!oDF.doFormat())
			{
				throw new myException("UB_TextFieldForNumbers:Constructor: StartValue not allowed not allowed !!");
			}
			else
			{
				this.cStartDBWert = oDF.getStringFormated();
			}
		}

		// formatierter wert zurueckschreiben
		this.setText(this.cStartDBWert);

	}
	
	
	public UB_TextFieldForNumbers() throws myException
	{
		super();
		throw new myException("UB_TextFieldForNumbers:Constructor without DecimalSize not allowed not allowed !!");
	}


	public UB_TextFieldForNumbers(String cDBFieldName, boolean EmptyAllowed, String cText, int iwidthPixel, int imaxInputSize) throws myException
	{
		super(cDBFieldName, EmptyAllowed, cText, iwidthPixel, imaxInputSize);
		throw new myException("UB_TextFieldForNumbers:Constructor without DecimalSize not allowed not allowed !!");
	}


	public UB_TextFieldForNumbers(String cDBFieldName, boolean EmptyAllowed, String cText) throws myException
	{
		super(cDBFieldName, EmptyAllowed, cText);
		throw new myException("UB_TextFieldForNumbers:Constructor without DecimalSize not allowed not allowed !!");
	}


	public UB_TextFieldForNumbers(String cText, int iwidthPixel, int imaxInputSize) throws myException
	{
		super(cText, iwidthPixel, imaxInputSize);
		throw new myException("UB_TextFieldForNumbers:Constructor without DecimalSize not allowed not allowed !!");
	}



	public Double get_ValueAsDOUBLE(boolean bNullAsNumber) throws myException
	{
		if (bibALL.isEmpty(this.getText()))
		{
			if (bNullAsNumber)
				return new Double(0);
			else
				return null;
		}
		else
		{
			// dann formatieren oder abweisen
			DotFormatter oDF = new  DotFormatter(bibALL.null2leer(this.getText()),this.iDecimalSize,Locale.GERMAN,true,3);
			
			if (!oDF.doFormat())
			{
				throw new myException("UB_TextFieldForNumbers:Constructor: StartValue not allowed not allowed !!");
			}
			else
			{
				return new Double(oDF.getDoubleValue());
			}
		
		}
	}
	

	
	public Double get_OldValueAsDOUBLE(boolean bNullAsNumber) throws myException
	{
		if (bibALL.isEmpty(this.cStartDBWert))
		{
			if (bNullAsNumber)
				return new Double(0);
			else
				return null;
		}
		else
		{
			// dann formatieren oder abweisen
			DotFormatter oDF = new  DotFormatter(bibALL.null2leer(this.cStartDBWert),this.iDecimalSize,Locale.GERMAN,true,3);
			
			if (!oDF.doFormat())
			{
				throw new myException("UB_TextFieldForNumbers:Constructor: StartValue not allowed not allowed !!");
			}
			else
			{
				return new Double(oDF.getDoubleValue());
			}
		
		}
	}

	
	
	public MyE2_MessageVector get_MV_InputOK() throws myException
	{
		MyE2_MessageVector oMV = super.get_MV_InputOK();
		
		if (oMV.get_bIsOK())
		{

			if ( !(this.get_bEmptyAllowd() && S.isEmpty(this.getText())))
			{
				// jetzt die eingabe checken
				// jetzt die eingabe checken
				DotFormatter oDF = new  DotFormatter(bibALL.null2leer(this.getText()),this.iDecimalSize,Locale.GERMAN,true,3);
				
				if (! oDF.doFormat())
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_StringForMSG("Das Feld benötigt eine Zahl als Eingabe !",this)));
				}
			}
		}
		
		return oMV;
	}

	


	
	/* (non-Javadoc)
	 * @see utilities.myEcho2.components.unboundDataFields.IF_UB_Fields#get_cInsertValuePart()
	 * 'MEIER'
	 */
	public String get_cInsertValuePart() throws myException
	{
		if (bibALL.isEmpty(this.getText()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Cannot be empty !!");
			else
				return "NULL";

		// jetzt die eingabe checken
		DotFormatter oDF = new  DotFormatter(bibALL.null2leer(this.getText()),this.iDecimalSize,Locale.GERMAN,true,3);
		
		if (! oDF.doFormat())
			throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Value not allowed !!");
		else
			return oDF.getStringUnFormated();
	}

	
	public String get_cString4Database() throws myException
	{
		String cHelp = this.get_cInsertValuePart();
		if (cHelp.equals("NULL"))
		{
			return "";
		}
		return cHelp;
	}

	
	/* (non-Javadoc)
	 * @see utilities.myEcho2.components.unboundDataFields.IF_UB_Fields#get_cUpdatePart()
	 * NAME='MEIER'
	 */
	public String get_cUpdatePart() throws myException
	{
		if (bibALL.isEmpty(this.getText()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextField:get_cUpdatePart():"+bibALL.null2leer(this.get_cDBFieldName())+"Cannot be empty !!");
			else
				return bibALL.null2leer(this.get_cDBFieldName())+"=NULL";
		
		// jetzt die eingabe checken
		DotFormatter oDF = new  DotFormatter(bibALL.null2leer(this.getText()),this.iDecimalSize,Locale.GERMAN,true,3);
		
		if (! oDF.doFormat())
			throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Value not allowed !!");
		else
			return this.get_cDBFieldName()+"="+oDF.getStringUnFormated();
	}

	
	public boolean get_bFieldHasChanged()
	{
		// jetzt die eingabe checken
		DotFormatter oDF = new  DotFormatter(bibALL.null2leer(this.getText()),this.iDecimalSize,Locale.GERMAN,true,3);
		
		if (oDF.doFormat())
			this.setText(oDF.getStringFormated());
		
		return (! this.cStartDBWert.equals(this.getText().trim()));
	}

	
}
