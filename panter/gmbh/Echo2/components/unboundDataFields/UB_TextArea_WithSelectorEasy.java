package panter.gmbh.Echo2.components.unboundDataFields;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_TextArea_WithSelektorEASY;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class UB_TextArea_WithSelectorEasy extends MyE2_TextArea_WithSelektorEASY  implements IF_UB_Fields
{	
	private String cNameDBField = null;
	private boolean bEmptyAllowed = false;
	private String cStartDBWert = null; 

	private Vector<XX_ValidateInput> vInputValidators = new Vector<XX_ValidateInput>(); 


	public UB_TextArea_WithSelectorEasy(String cDBFieldName, boolean EmptyAllowed, String cText, String cNameTextblockGroup)	throws myException 
	{
		super(cNameTextblockGroup);
		this.cNameDBField = cDBFieldName;
		this.bEmptyAllowed = EmptyAllowed;
		this.get_oTextArea().setText(bibALL.null2leer(cText));
		this.set_StartValue(cText);

		
	}

	public UB_TextArea_WithSelectorEasy(String cDBFieldName, boolean EmptyAllowed, String cText, String cNameTextblockGroup, int iWidthInPixel, int iRows) throws myException 
	{
		super(cNameTextblockGroup, iWidthInPixel, iRows);
		this.cNameDBField = cDBFieldName;
		this.bEmptyAllowed = EmptyAllowed;
		this.get_oTextArea().setText(bibALL.null2leer(cText));
		this.set_StartValue(cText);
	}



	@Override
	public MyE2_MessageVector get_MV_InputOK() throws myException 
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		if (! this.bEmptyAllowed && bibALL.isEmpty(this.get_cText()))
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_StringForMSG("Eine leere eingabe ist nicht erlaubt !",this)));
		}
		
		if (oMV.get_bIsOK())
		{
			for (XX_ValidateInput oValid: this.vInputValidators)
			{
				oMV.add_MESSAGE(oValid.isValid(this.get_cText(), this));
			}
		}
		
		return oMV;
	}

	@Override
	public void mark_ErrorInput(boolean bInputIsOK)
	{
		this.get_oTextArea().setStyle(this.get_oTextArea().EXT().get_STYLE_FACTORY().get_Style(this.isEnabled(),this.get_bEmptyAllowd(), !bInputIsOK));
	}

	@Override
	public boolean get_bEmptyAllowd()
	{
		return this.bEmptyAllowed;
	}

	@Override
	public void set_bEmptyAllowd(boolean bAllowed)
	{
		this.bEmptyAllowed=bAllowed;
	}

	@Override
	public String get_cDBFieldName()
	{
		return bibALL.null2leer(this.cNameDBField);
	}

	@Override
	public void set_cDBFieldName(String cFieldName)
	{
		this.cNameDBField=cFieldName;
	}

	@Override
	public void set_StyleForInput(boolean bEnabled)
	{
		if (this.get_oTextArea().EXT().get_STYLE_FACTORY() != null)
		{
			this.get_oTextArea().setStyle(this.get_oTextArea().EXT().get_STYLE_FACTORY().get_Style(bEnabled, this.get_bEmptyAllowd(), false));
		}
	}
	
	
	@Override
	public void set_StartValue(String cStartDBValue) throws myException
	{
		this.cStartDBWert = bibALL.null2leer(cStartDBValue).trim();
		this.get_oTextArea().setText(this.cStartDBWert);
	}

	
	@Override
	public String get_cInsertValuePart() throws myException
	{
		if (bibALL.isEmpty(this.get_oTextArea().getText()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextArea_WithSelectorEasy:get_get_cInsertValuePart():"+bibALL.null2leer(this.cNameDBField)+"Cannot be empty !!");
			else
				return "NULL";
		
		return bibALL.MakeSql(bibALL.null2leer(this.get_oTextArea().getText()));
	}

	@Override
	public String get_cUpdatePart() throws myException
	{
		if (bibALL.isEmpty(this.get_oTextArea().getText()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextArea_WithSelectorEasy:get_cUpdatePart():"+bibALL.null2leer(this.cNameDBField)+"Cannot be empty !!");
			else
				return bibALL.null2leer(this.cNameDBField)+"=NULL";
		
		return bibALL.null2leer(this.cNameDBField)+"="+bibALL.MakeSql(bibALL.null2leer(this.get_oTextArea().getText()));
	}

	@Override
	public String get_cString4Database() throws myException
	{
		if (bibALL.isEmpty(this.get_oTextArea().getText()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextArea_WithSelectorEasy:get_get_cInsertValuePart():"+bibALL.null2leer(this.cNameDBField)+"Cannot be empty !!");
			else
				return "";
		
		return bibALL.null2leer(this.get_oTextArea().getText());
	}

	@Override
	public String get_cText() throws myException 
	{
		return this.get_oTextArea().getText();
	}

	@Override
	public boolean get_bFieldHasChanged()
	{
		return (! this.cStartDBWert.equals(this.get_oTextArea().getText().trim()));
	}

	@Override
	public boolean get_bIsEmpty() 
	{
		return bibALL.isEmpty(this.get_oTextArea().getText());
	}

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


}
