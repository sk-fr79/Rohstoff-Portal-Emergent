package panter.gmbh.Echo2.components.unboundDataFields;

import java.util.Locale;
import java.util.Vector;

import nextapp.echo2.app.Alignment;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_TextField_WithSelektor;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;


public class UB_TextFieldWithSelectorForNumbers extends MyE2_TextField_WithSelektor implements IF_UB_Fields
{
	
	private String cNameDBField = null;
	private boolean bEmptyAllowed = false;
	private String cStartDBWert = null; 

	private int iDecimalSize = 0;

	private Vector<XX_ValidateInput> vInputValidators = new Vector<XX_ValidateInput>(); 
	
	@Override
	public void add_InputValidator(XX_ValidateInput validator)
	{
		this.vInputValidators.add(validator);
	}


	@Override
	public Vector<XX_ValidateInput> get_vInputValidator()
	{
		return this.vInputValidators;
	}

	
	public UB_TextFieldWithSelectorForNumbers(	String cDBFieldName, 
												boolean EmptyAllowed,
												String cStartWert,
												int    DecimalSize,
												String[][] Varianten, 
												String cInfo_When_Empty) throws myException
	{
		super(Varianten, cInfo_When_Empty);
		this.cNameDBField = cDBFieldName;
		this.bEmptyAllowed = EmptyAllowed;
		this.cStartDBWert = cStartWert;
		this.iDecimalSize = DecimalSize;
		this.set_StartValue(this.cStartDBWert);
		this.get_oTextField().setAlignment(new Alignment(Alignment.RIGHT,Alignment.DEFAULT));

		
	}

	public UB_TextFieldWithSelectorForNumbers(	String 	cDBFieldName, 
												boolean EmptyAllowed,
												String 	cStartWert,
												int    	DecimalSize,
												String 	cSQLQuery) throws myException
	{
		super(cSQLQuery);
		this.cNameDBField = cDBFieldName;
		this.bEmptyAllowed = EmptyAllowed;
		this.cStartDBWert = cStartWert;
		this.iDecimalSize = DecimalSize;
		this.set_StartValue(this.cStartDBWert);
		this.get_oTextField().setAlignment(new Alignment(Alignment.RIGHT,Alignment.DEFAULT));
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
				throw new myException("UB_TextFieldWithSelectorForNumbers:Constructor: StartValue not allowed not allowed !!");
			}
			else
			{
				this.cStartDBWert = oDF.getStringFormated();
			}
		}

		// formatierter wert zurueckschreiben
		this.get_oTextField().setText(this.cStartDBWert);

	}
	
	



	public Double get_ValueAsDOUBLE() throws myException
	{
		if (bibALL.isEmpty(this.get_oTextField().getText()))
		{
			return null;
		}
		else
		{
			// dann formatieren oder abweisen
			DotFormatter oDF = new  DotFormatter(bibALL.null2leer(this.get_oTextField().getText()),this.iDecimalSize,Locale.GERMAN,true,3);
			
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
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		if (! this.bEmptyAllowed && bibALL.isEmpty(this.get_oTextField().getText()))
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_StringForMSG("Eine leere Eingabe ist nicht erlaubt !",this)));
		}

		
		if (oMV.get_bIsOK())
		{

			if ( !(this.get_bEmptyAllowd() && S.isEmpty(this.get_oTextField().getText())))
			{
				// jetzt die eingabe checken
				DotFormatter oDF = new  DotFormatter(bibALL.null2leer(this.get_oTextField().getText()),this.iDecimalSize,Locale.GERMAN,true,3);
				
				if (! oDF.doFormat())
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_StringForMSG("Das Feld benötigt eine Zahl als Eingabe !",this)));
				}
				else
				{
					this.get_oTextField().setText(oDF.getStringFormated());
				}
			}
		}
		
		
		if (oMV.get_bIsOK())
		{
			for (XX_ValidateInput oValid: this.vInputValidators)
			{
				oMV.add_MESSAGE(oValid.isValid(this.get_oTextField().getText(), this));
			}
		}


		return oMV;
	}

	

	
	public String get_cInsertValuePart() throws myException
	{
		if (bibALL.isEmpty(this.get_oTextField().getText()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Cannot be empty !!");
			else
				return "NULL";

		// jetzt die eingabe checken
		DotFormatter oDF = new  DotFormatter(bibALL.null2leer(this.get_oTextField().getText()),this.iDecimalSize,Locale.GERMAN,true,3);
		
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

	public String get_cText()
	{
		return this.get_oTextField().getText();
	}
	

	
	public String get_cUpdatePart() throws myException
	{
		if (bibALL.isEmpty(this.get_oTextField().getText()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextField:get_cUpdatePart():"+bibALL.null2leer(this.get_cDBFieldName())+"Cannot be empty !!");
			else
				return bibALL.null2leer(this.get_cDBFieldName())+"=NULL";
		
		// jetzt die eingabe checken
		DotFormatter oDF = new  DotFormatter(bibALL.null2leer(this.get_oTextField().getText()),this.iDecimalSize,Locale.GERMAN,true,3);
		
		if (! oDF.doFormat())
			throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Value not allowed !!");
		else
			return this.get_cDBFieldName()+"="+oDF.getStringUnFormated();
	}

	
	public boolean get_bFieldHasChanged()
	{
		// jetzt die eingabe checken
		DotFormatter oDF = new  DotFormatter(bibALL.null2leer(this.get_oTextField().getText()),this.iDecimalSize,Locale.GERMAN,true,3);
		
		if (oDF.doFormat())
			this.get_oTextField().setText(oDF.getStringFormated());
		
		return (! this.cStartDBWert.equals(this.get_oTextField().getText().trim()));
	}

	
	
	

	public void mark_ErrorInput(boolean bInputIsOK)
	{
		this.get_oTextField().setStyle(this.get_oTextField().EXT().get_STYLE_FACTORY().get_Style(this.get_oTextField().isEnabled(),this.get_bEmptyAllowd(), !bInputIsOK));
	}

	public boolean get_bEmptyAllowd()
	{
		return this.bEmptyAllowed;
	}

	public void set_bEmptyAllowd(boolean bAllowed)
	{
		this.bEmptyAllowed=bAllowed;
	}

	public String get_cDBFieldName()
	{
		return bibALL.null2leer(this.cNameDBField);
	}

	public void set_cDBFieldName(String cFieldName)
	{
		this.cNameDBField=cFieldName;
	}

	public boolean get_bIsEmpty() 
	{
		return bibALL.isEmpty(this.get_oTextField().getText());
	}
	@Override
	public void set_StyleForInput(boolean bEnabled)
	{
		if (this.EXT().get_STYLE_FACTORY() != null)
		{
			this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(bEnabled, this.get_bEmptyAllowd(), false));
		}
	}

	
}
