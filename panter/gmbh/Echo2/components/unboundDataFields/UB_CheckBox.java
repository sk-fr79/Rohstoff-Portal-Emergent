package panter.gmbh.Echo2.components.unboundDataFields;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class UB_CheckBox extends MyE2_CheckBox implements IF_UB_Fields
{

	private String cNameDBField = null;
	private boolean bEmptyAllowed = false;   // bei checkbox ist immer was da
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



	public UB_CheckBox(String cDBFieldName, Object cTextBeschreibung, String StartWert) throws myException
	{
		super(cTextBeschreibung);
		this.cNameDBField = cDBFieldName;
		
		this.set_StartValue(StartWert);
	}

	
	public void set_StartValue(String cStartValue) throws myException
	{
		this.cStartDBWert = bibALL.null2leer(cStartValue);
		
		if    (this.cStartDBWert.equals("Y"))
			this.setSelected(true);
		else if (this.cStartDBWert.equals("N") || bibALL.isEmpty(this.cStartDBWert))
			this.setSelected(false);
		else
			throw new myException("UB_CheckBox:Constructor: Startwert not allowed ! -> "+this.cStartDBWert);
	
	}
	
	
	
	public MyE2_MessageVector get_MV_InputOK() throws myException
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		if (oMV.get_bIsOK())
		{
			for (XX_ValidateInput oValid: this.vInputValidators)
			{
				oMV.add_MESSAGE(oValid.isValid(this.isSelected()?"Y":"N", this));
			}
		}
		
		
		return oMV;
	}

	public void mark_ErrorInput(boolean oInputOK)
	{
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

	public String get_cInsertValuePart()
	{
		
		String cWert = "'N'";
		if (this.isSelected())
			cWert = "'Y'";
		
		return cWert;
	}

	
	public String get_cString4Database()
	{
		
		String cWert = "N";
		if (this.isSelected())
			cWert = "Y";
		
		return cWert;
	}

	public String get_cText()
	{
		
		String cWert = "N";
		if (this.isSelected())
			cWert = "Y";
		
		return cWert;
	}


	public String get_cUpdatePart() throws myException
	{

		String cWert = "'N'";
		if (this.isSelected())
			cWert = "'Y'";
		
		return bibALL.null2leer(this.cNameDBField)+"="+cWert;
	}



	public boolean get_bFieldHasChanged()
	{
		
		if (this.cStartDBWert.trim().equals("") && !this.isSelected()) 
			return false;
		
		if (this.cStartDBWert.trim().equals("") && this.isSelected()) 
			return true;
		
		String cActual = "N";
		if (this.isSelected())
			cActual = "Y";
		
		return (!cActual.equals(this.cStartDBWert)); 
	}


	public boolean get_bIsEmpty() 
	{
		return false;
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
