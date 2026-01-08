package panter.gmbh.Echo2.components.unboundDataFields;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_TextField_With_DatePOPUP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;

public class UB_TextField_With_DatePOPUP extends MyE2_TextField_With_DatePOPUP implements IF_UB_Fields
{
	
	private String cNameDBField = null;
	private boolean bEmptyAllowed = false;
	private String cStartDBWert = null; 

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


	public UB_TextField_With_DatePOPUP(	String cDBFieldName, 
										boolean EmptyAllowed,
										String cStartWert,
										int iwidthPixel) throws myException
	{
		super(cStartWert,iwidthPixel);
		this.cNameDBField = cDBFieldName;
		this.bEmptyAllowed = EmptyAllowed;
		this.cStartDBWert = cStartWert;
		this.set_StartValue(this.cStartDBWert);
	}


	
	
	public void set_StartValue(String cStartDBValue) throws myException
	{
		this.cStartDBWert = bibALL.null2leer(cStartDBValue).trim();
		this.get_oTextField().setText(this.cStartDBWert);
	}



	
	@Override
	public MyE2_MessageVector get_MV_InputOK() throws myException
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();

		if (! this.bEmptyAllowed && bibALL.isEmpty(this.get_oTextField().getText()))
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_StringForMSG("Eine leere Eingabe ist nicht erlaubt !",this)));
		}
		
		if (this.bEmptyAllowed && bibALL.isEmpty(this.get_oTextField().getText()))
		{
			return oMV;
		}
		
		
		if (oMV.get_bIsOK())
		{
			// jetzt die eingabe checken
			TestingDate oTestDate = new TestingDate(bibALL.null2leer(this.get_oTextField().getText()));
			
			if (! oTestDate.testing())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_StringForMSG("Das Feld benötigt ein Datum als Eingabe !",this)));
			}

			
			for (XX_ValidateInput oValid: this.vInputValidators)
			{
				oMV.add_MESSAGE(oValid.isValid(this.get_oTextField().getText(), this));
			}
		}

		
		return oMV;
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



	
	public String get_cInsertValuePart() throws myException
	{
		if (bibALL.isEmpty(this.get_oTextField().getText()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Cannot be empty !!");
			else
				return "NULL";

		// jetzt die eingabe checken
		TestingDate oTestDate = new TestingDate(bibALL.null2leer(this.get_oTextField().getText()));
		
		if (! oTestDate.testing())
			throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Value not allowed !!");

		return oTestDate.get_ISO_DateFormat(true);
			
	}


	public String get_cString4Database() throws myException
	{
		if (bibALL.isEmpty(this.get_oTextField().getText()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Cannot be empty !!");
			else
				return "";

		// jetzt die eingabe checken
		TestingDate oTestDate = new TestingDate(bibALL.null2leer(this.get_oTextField().getText()));
		
		if (! oTestDate.testing())
			throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Value not allowed !!");

		return oTestDate.get_FormatedDateString("dd.mm.yyyy");
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
		TestingDate oTestDate = new TestingDate(bibALL.null2leer(this.get_oTextField().getText()));
		
		if (! oTestDate.testing())
			throw new myException("UB_TextField:get_cUpdatePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Value not allowed !!");
		
		return this.get_cDBFieldName()+"="+oTestDate.get_ISO_DateFormat(true);

	}

	
	
	
	public boolean get_bFieldHasChanged()
	{
		return (! this.cStartDBWert.equals(this.get_oTextField().getText().trim()));
	}

	
	public boolean get_bIsEmpty() 
	{
		return bibALL.isEmpty(this.get_oTextField().getText());
	}
	
	@Override
	public void set_StyleForInput(boolean bEnabled)
	{
		if (this.get_oTextField().EXT().get_STYLE_FACTORY() != null)
		{
			this.get_oTextField().setStyle(this.get_oTextField().EXT().get_STYLE_FACTORY().get_Style(bEnabled, this.get_bEmptyAllowd(), false));
		}
	}

}
