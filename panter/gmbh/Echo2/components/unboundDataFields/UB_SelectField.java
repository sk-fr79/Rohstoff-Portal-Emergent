package panter.gmbh.Echo2.components.unboundDataFields;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class UB_SelectField extends MyE2_SelectField implements IF_UB_Fields
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



	public UB_SelectField(String NameDBField,boolean EmptyAllowed)
	{
		super();
		this.cNameDBField = NameDBField;
		this.bEmptyAllowed = EmptyAllowed; 
	}


	/**
	 * 
	 * @param NameDBField
	 * @param EmptyAllowed
	 * @param query_For_LIST
	 * @param thirdColumnIS_STANDARD_MARKER
	 * @param emtyValueInFront
	 * @param valuesFormated
	 * @param btranslate
	 * @throws myException
	 */
	public UB_SelectField(String NameDBField,boolean EmptyAllowed,String query_For_LIST, boolean thirdColumnIS_STANDARD_MARKER, boolean emtyValueInFront,boolean valuesFormated, boolean btranslate) throws myException
	{
		super(query_For_LIST, thirdColumnIS_STANDARD_MARKER, emtyValueInFront,valuesFormated, btranslate);
		this.cNameDBField = NameDBField;
		this.bEmptyAllowed = EmptyAllowed; 
	}


	public UB_SelectField(String NameDBField,boolean EmptyAllowed,String[] defArray, String cdefaultValue,boolean btranslate) throws myException
	{
		super(defArray, cdefaultValue, btranslate);
		this.cNameDBField = NameDBField;
		this.bEmptyAllowed = EmptyAllowed; 
	}


	public UB_SelectField(String NameDBField,boolean EmptyAllowed,String[][] defArray, String cdefaultValue,boolean btranslate) throws myException
	{
		super(defArray, cdefaultValue, btranslate);
		this.cNameDBField = NameDBField;
		this.bEmptyAllowed = EmptyAllowed; 
	}


	@Override
	public MyE2_MessageVector get_MV_InputOK()  throws myException
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
	
		if (bibALL.isEmpty(this.get_ActualWert()))
		{
			if (! this.bEmptyAllowed)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_StringForMSG("Eine leere Eingabe ist nicht erlaubt !",this)));
			}
			if (this.bEmptyAllowed)
			{
				//dann folgt keine validierung mehr
				return oMV;
			}
		}
		
		if (oMV.get_bIsOK())
		{
			for (XX_ValidateInput oValid: this.vInputValidators)
			{
				oMV.add_MESSAGE(oValid.isValid(this.get_ActualWert(), this));
			}
		}


		return oMV;
	}


	public String get_cInsertValuePart() throws myException
	{
		if (bibALL.isEmpty(this.get_ActualWert()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.cNameDBField)+"Cannot be empty !!");
			else
				return "NULL";
		
		return bibALL.MakeSql(bibALL.null2leer(this.get_ActualWert()));
	}


	
	public String get_cString4Database() throws myException
	{
		if (bibALL.isEmpty(this.get_ActualWert()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.cNameDBField)+"Cannot be empty !!");
			else
				return "";
		
		return bibALL.null2leer(this.get_ActualWert());
	}

	public String get_cText() throws myException
	{
		return this.get_ActualWert();
	}

	
	public String get_cUpdatePart() throws myException
	{
		if (bibALL.isEmpty(this.get_ActualWert()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextField:get_cUpdatePart():"+bibALL.null2leer(this.cNameDBField)+"Cannot be empty !!");
			else
				return bibALL.null2leer(this.cNameDBField)+"=NULL";
		
		return bibALL.null2leer(this.cNameDBField)+"="+bibALL.MakeSql(bibALL.null2leer(this.get_ActualWert()));
	}

	public void mark_ErrorInput(boolean bInputIsOK)
	{
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(this.isEnabled(),this.get_bEmptyAllowd(), !bInputIsOK));
	}


	@Override
	public void set_StartValue(String cStartDBValue) throws myException
	{
		this.cStartDBWert = bibALL.null2leer(cStartDBValue).trim();
		this.set_ActiveValue(this.cStartDBWert);
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


	public boolean get_bFieldHasChanged()  throws myException
	{
		return (! this.cStartDBWert.equals(this.get_ActualWert().trim()));
	}

	public boolean get_bIsEmpty()   throws myException
	{
		return bibALL.isEmpty(this.get_ActualWert());
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
