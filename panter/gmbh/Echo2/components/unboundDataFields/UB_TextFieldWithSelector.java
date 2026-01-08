package panter.gmbh.Echo2.components.unboundDataFields;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_TextField_WithSelektor;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class UB_TextFieldWithSelector extends MyE2_TextField_WithSelektor implements IF_UB_Fields
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


	public UB_TextFieldWithSelector(	String cDBFieldName, 
										boolean EmptyAllowed,
										String cStartWert,
										String[][] Varianten, 
										String cInfo_When_Empty) throws myException
	{
		super(Varianten, cInfo_When_Empty);
		this.cNameDBField = cDBFieldName;
		this.bEmptyAllowed = EmptyAllowed;
		this.cStartDBWert = cStartWert;
		this.set_StartValue(this.cStartDBWert);
	}

	public UB_TextFieldWithSelector(String 	cDBFieldName, 
									boolean EmptyAllowed,
									String cStartWert,
									String 	cSQLQuery) throws myException
	{
		super(cSQLQuery);
		this.cNameDBField = cDBFieldName;
		this.bEmptyAllowed = EmptyAllowed;
		this.cStartDBWert = cStartWert;
		this.set_StartValue(this.cStartDBWert);
	}

	
	//2012-02-08: weiterer konstruktor
	public UB_TextFieldWithSelector(String cDBFieldName, boolean EmptyAllowed,String cText, int iwidthPixel, int imaxInputSize) throws myException
	{
		super();
		this.cNameDBField = cDBFieldName;
		this.bEmptyAllowed = EmptyAllowed;
		this.set_StartValue(cText);
		
		this.get_oTextField().set_iWidthPixel(iwidthPixel);
		this.get_oTextField().set_iMaxInputSize(imaxInputSize);

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
		
		if (oMV.get_bIsOK())
		{
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
				throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.cNameDBField)+"Cannot be empty !!");
			else
				return "NULL";
		
		return bibALL.MakeSql(bibALL.null2leer(this.get_oTextField().getText()));
	}

	public String get_cString4Database() throws myException
	{
		if (bibALL.isEmpty(this.get_oTextField().getText()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.cNameDBField)+"Cannot be empty !!");
			else
				return "NULL";
		
		return bibALL.null2leer(this.get_oTextField().getText());
	}

	
	public String get_cText()
	{
		return this.get_oTextField().getText();
	}
	


	
	public String get_cUpdatePart() throws myException
	{
		if (bibALL.isEmpty(this.get_oTextField().getText()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextField:get_cUpdatePart():"+bibALL.null2leer(this.cNameDBField)+"Cannot be empty !!");
			else
				return bibALL.null2leer(this.cNameDBField)+"=NULL";
		
		return bibALL.null2leer(this.cNameDBField)+"="+bibALL.MakeSql(bibALL.null2leer(this.get_oTextField().getText()));
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
