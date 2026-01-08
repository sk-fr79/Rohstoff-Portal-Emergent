package panter.gmbh.Echo2.components.unboundDataFields;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.E2_DateBrowser;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;

public class UB_DateBrowser extends E2_DateBrowser implements IF_UB_Fields
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

	
	public UB_DateBrowser() throws myException
	{
		throw new myException("UB_DateBrowser:empty Constructor not allowed !!");
	}

	
	// nur dieser konstructor ist erlaubt
	public UB_DateBrowser(String cDBFIELD_NAME, boolean EmtpyAllowed, String cStartValue) throws myException
	{
		super();
		
		this.cNameDBField = cDBFIELD_NAME;
		this.bEmptyAllowed = EmtpyAllowed;
		this.set_StartValue(cStartValue);
		
	}


	public void set_StartValue(String cStartValue) throws myException
	{
		this.cStartDBWert = bibALL.null2leer(cStartValue).trim();
		if (!this.cStartDBWert.equals(""))
		{
			// dann formatieren oder abweisen
			TestingDate oTest = new TestingDate(this.cStartDBWert);
			
			if (!oTest.testing())
			{
				throw new myException("UB_DateBrowser:Constructor: StartValue not allowed not allowed !!");
			}
			else
			{
				this.cStartDBWert = oTest.get_FormatedDateString("dd.mm.yyyy");
			}
		}

		this.get_oDatumsFeld().setText(this.cStartDBWert);
	
	}


	
	public MyE2_MessageVector get_MV_InputOK() throws myException
	{

		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		if (! this.bEmptyAllowed && bibALL.isEmpty(this.get_oDatumsFeld().getText()))
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Eine leere Eingabe ist nicht erlaubt !"))) ;
		
		
		// jetzt die eingabe checken
		TestingDate oTestDate = new TestingDate(bibALL.null2leer(this.get_oDatumsFeld().getText()));
		
		if (oMV.get_bIsOK())
		{
			if (! oTestDate.testing())
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_StringForMSG("Bitte ein Datum eingeben !",this)));
		}
		
		if (oMV.get_bIsOK())
		{
			for (XX_ValidateInput oValid: this.vInputValidators)
			{
				oMV.add_MESSAGE(oValid.isValid(this.get_oDatumsFeld().getText(), this));
			}
		}

		
		return oMV;
	}


	
	public String get_cInsertValuePart() throws myException
	{
		if (bibALL.isEmpty(this.get_oDatumsFeld().getText()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Cannot be empty !!");
			else
				return "NULL";

		// jetzt die eingabe checken
		TestingDate oTestDate = new TestingDate(bibALL.null2leer(this.get_oDatumsFeld().getText()));
		
		if (! oTestDate.testing())
			throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Value not allowed !!");

		return oTestDate.get_ISO_DateFormat(true);
			
	}


	
	public String get_cString4Database() throws myException
	{
		if (bibALL.isEmpty(this.get_oDatumsFeld().getText()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Cannot be empty !!");
			else
				return "";

		// jetzt die eingabe checken
		TestingDate oTestDate = new TestingDate(bibALL.null2leer(this.get_oDatumsFeld().getText()));
		
		if (! oTestDate.testing())
			throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Value not allowed !!");

		
		
		return oTestDate.get_FormatedDateString("dd.mm.yyyy");
			
	}

	
	public String get_cText()
	{
		return this.get_oDatumsFeld().getText();
	}

	
	public String get_cUpdatePart() throws myException
	{
		if (bibALL.isEmpty(this.get_oDatumsFeld().getText()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextField:get_cUpdatePart():"+bibALL.null2leer(this.get_cDBFieldName())+"Cannot be empty !!");
			else
				return bibALL.null2leer(this.get_cDBFieldName())+"=NULL";
		
		// jetzt die eingabe checken
		TestingDate oTestDate = new TestingDate(bibALL.null2leer(this.get_oDatumsFeld().getText()));

		if (! oTestDate.testing())
			throw new myException("UB_TextField:get_cUpdatePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Value not allowed !!");

		
		return this.get_cDBFieldName()+"="+oTestDate.get_ISO_DateFormat(true);

	}

	
	
	public void mark_ErrorInput(boolean bInputIsOK)
	{
		this.get_oDatumsFeld().setStyle(this.get_oDatumsFeld().EXT().get_STYLE_FACTORY().get_Style(this.isEnabled(),this.get_bEmptyAllowd(),!bInputIsOK));
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



	
	public boolean get_bFieldHasChanged()
	{
		// jetzt die eingabe checken
		TestingDate oTestDate = new TestingDate(bibALL.null2leer(this.get_oDatumsFeld().getText()));
		
		if (oTestDate.testing())
			this.get_oDatumsFeld().setText(oTestDate.get_FormatedDateString("dd.mm.yyyy"));

		
		return (! this.cStartDBWert.equals(this.get_oDatumsFeld().getText().trim()));
	}

	
	public boolean get_bIsEmpty() 
	{
		return bibALL.isEmpty(this.get_oDatumsFeld().getText());
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
