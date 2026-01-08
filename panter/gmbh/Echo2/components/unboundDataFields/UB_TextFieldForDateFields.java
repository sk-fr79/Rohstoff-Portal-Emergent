package panter.gmbh.Echo2.components.unboundDataFields;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;

public class UB_TextFieldForDateFields extends UB_TextField implements IF_UB_Fields
{
	private String cStartDBWert = null; 


	//  erlaubter konstructor 
	public UB_TextFieldForDateFields(String cDBFieldName, boolean EmptyAllowed, String cStartValue, int iwidthPixel, int imaxInputSize) throws myException
	{
		super(cDBFieldName, EmptyAllowed, cStartValue, iwidthPixel, imaxInputSize);
		this.set_StartValue(cStartValue);
	}

	
	
	//  erlaubter konstructor 
	public UB_TextFieldForDateFields(String cDBFieldName, boolean EmptyAllowed, String cStartValue) throws myException
	{
		super(cDBFieldName, EmptyAllowed, cStartValue);
		
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

		// formatierter wert zurueckschreiben
		this.setText(this.cStartDBWert);
	
	}

	
	
	public UB_TextFieldForDateFields() throws myException
	{
		super();
		throw new myException("UB_DateBrowser:empty Constructor not allowed !!");
	}

	public UB_TextFieldForDateFields(String cText, int iwidthPixel, int imaxInputSize) throws myException
	{
		super(cText, iwidthPixel, imaxInputSize);
		throw new myException("UB_DateBrowser:empty Constructor not allowed !!");
	}



	public MyE2_MessageVector get_MV_InputOK() throws myException
	{
		MyE2_MessageVector oMV = super.get_MV_InputOK();
		
		if (oMV.get_bIsOK())
		{

			if ( !(this.get_bEmptyAllowd() && S.isEmpty(this.getText())))
			{
				// jetzt die eingabe checken
				TestingDate oTestDate = new TestingDate(bibALL.null2leer(this.getText()));
				
				if (! oTestDate.testing())
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_StringForMSG("Das Feld benötigt ein Datum als Eingabe !",this)));
				}
			}
		}
		
		return oMV;
	}


	
	public String get_cInsertValuePart() throws myException
	{
		if (bibALL.isEmpty(this.getText()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Cannot be empty !!");
			else
				return "NULL";

		// jetzt die eingabe checken
		TestingDate oTestDate = new TestingDate(bibALL.null2leer(this.getText()));
		
		if (! oTestDate.testing())
			throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Value not allowed !!");

		return oTestDate.get_ISO_DateFormat(true);
	}

	public String get_cString4Database() throws myException
	{
		if (bibALL.isEmpty(this.getText()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Cannot be empty !!");
			else
				return "";

		// jetzt die eingabe checken
		TestingDate oTestDate = new TestingDate(bibALL.null2leer(this.getText()));
		
		if (! oTestDate.testing())
			throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Value not allowed !!");
		
		return oTestDate.get_FormatedDateString("dd.mm.yyyy");
	}

	
	public String get_cUpdatePart() throws myException
	{
		if (bibALL.isEmpty(this.getText()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextField:get_cUpdatePart():"+bibALL.null2leer(this.get_cDBFieldName())+"Cannot be empty !!");
			else
				return bibALL.null2leer(this.get_cDBFieldName())+"=NULL";
		
		// jetzt die eingabe checken
		TestingDate oTestDate = new TestingDate(bibALL.null2leer(this.getText()));
		
		if (! oTestDate.testing())
			throw new myException("UB_TextField:get_cUpdatePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Value not allowed !!");

		return this.get_cDBFieldName()+"="+oTestDate.get_ISO_DateFormat(true);
	}

	
	
	public boolean get_bFieldHasChanged()
	{
		// jetzt die eingabe checken
		TestingDate oTestDate = new TestingDate(bibALL.null2leer(this.getText()));
		
		if (oTestDate.testing())
			this.setText(oTestDate.get_FormatedDateString("dd.mm.yyyy"));
		
		return (! this.cStartDBWert.equals(this.getText().trim()));
	}

	
	
}
