package panter.gmbh.Echo2.components.unboundDataFields;

import java.util.Locale;
import java.util.Vector;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MaskSearchField.MyE2_MaskSearchField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;
import panter.gmbh.indep.maggie.DotFormatterGermanFixed;

public abstract class UB_MaskSearchField extends MyE2_MaskSearchField implements IF_UB_Fields
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

	
	/**
	 * 
	 * @param cDBFieldName
	 * @param EmptyAllowed
	 * @param cSQLSelectBlock
	 * @param cSQLFromBlock
	 * @param cSQLOrderBlock
	 * @param cSQLWhereBlockJoiningTables
	 * @param cSQLWhereBlockForSelecting
	 * @param cSQLqueryForUniqueSearch
	 * @param charForUniqueSearch
	 * @param cCOMPLETE_SQL_FOR_label
	 * @param INSETS_For_Components
	 * @param ShowEraser
	 * @throws myException
	 */
	public UB_MaskSearchField(	String cDBFieldName, 
								boolean EmptyAllowed, 
								String cSQLSelectBlock, 
								String cSQLFromBlock, 
								String cSQLOrderBlock, 
								String cSQLWhereBlockJoiningTables, 
								String cSQLWhereBlockForSelecting, 
								String cSQLqueryForUniqueSearch, 
								String charForUniqueSearch, 
								String cCOMPLETE_SQL_FOR_label, 
								Insets INSETS_For_Components, 
								boolean ShowEraser) throws myException
	{
		super(	 cSQLSelectBlock, cSQLFromBlock, cSQLOrderBlock,
				cSQLWhereBlockJoiningTables, cSQLWhereBlockForSelecting,
				cSQLqueryForUniqueSearch, charForUniqueSearch, cCOMPLETE_SQL_FOR_label,
				INSETS_For_Components, ShowEraser);
		
		this.cNameDBField = cDBFieldName;
		this.bEmptyAllowed = EmptyAllowed;
		
	}
	
	
	/**
	 * 
	 * @param cDBFieldName
	 * @param EmptyAllowed
	 * @param cSQLSelectBlock
	 * @param cSQLFromBlock
	 * @param cSQLOrderBlock
	 * @param cSQLWhereBlockJoiningTables
	 * @param cSQLWhereBlockForSelecting
	 * @param cSQLqueryForUniqueSearch
	 * @param charForUniqueSearch
	 * @param cCOMPLETE_SQL_FOR_label
	 * @param INSETS_For_Components
	 * @param ShowEraser
	 * @throws myException
	 */
	public UB_MaskSearchField(	String cDBFieldName, 
								boolean EmptyAllowed, 
								String cSQLSelectBlock, 
								String cSQLFromBlock, 
								String cSQLOrderBlock, 
								String cSQLWhereBlockJoiningTables, 
								String cSQLWhereBlockForSelecting, 
								String cSQLqueryForUniqueSearch, 
								String charForUniqueSearch, 
								String cCOMPLETE_SQL_FOR_label, 
								Insets INSETS_For_Components, 
								boolean ShowEraser,
								int nMaxResults) throws myException
	{
		super(	 cSQLSelectBlock, cSQLFromBlock, cSQLOrderBlock,
				cSQLWhereBlockJoiningTables, cSQLWhereBlockForSelecting,
				cSQLqueryForUniqueSearch, charForUniqueSearch, cCOMPLETE_SQL_FOR_label,
				INSETS_For_Components, ShowEraser,nMaxResults);
		
		this.cNameDBField = cDBFieldName;
		this.bEmptyAllowed = EmptyAllowed;
		
	}
	
	

	public MyE2_MessageVector get_MV_InputOK() throws myException
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		if (! this.bEmptyAllowed && bibALL.isEmpty(this.get_oTextFieldForSearchInput().getText()))
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_StringForMSG("Eine leere eingabe ist nicht erlaubt !",this)));
		}
		
		if (!(this.bEmptyAllowed && bibALL.isEmpty(this.get_oTextFieldForSearchInput().getText())))
		{
		
			if (oMV.get_bIsOK())
			{
			
				DotFormatterGermanFixed oDF = new DotFormatterGermanFixed(this.get_oTextFieldForSearchInput().getText());
				if (!oDF.doFormat())
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_StringForMSG("Bitte eine korrekte Suche ausführen !",this)));
				}
				
				if (oMV.get_bIsOK())
				{
					for (XX_ValidateInput oValid: this.vInputValidators)
					{
						oMV.add_MESSAGE(oValid.isValid(this.get_oTextFieldForSearchInput().getText(), this));
					}
				}
			}

		}
		return oMV;
	}

	public void mark_ErrorInput(boolean bInputIsOK)
	{
		MyE2_TextField oTF = this.get_oTextFieldForSearchInput();
		oTF.setStyle(oTF.EXT().get_STYLE_FACTORY().get_Style(this.isEnabled(),this.get_bEmptyAllowd(), !bInputIsOK));
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

	public void set_StartValue(String cStartDBValue) throws myException
	{
		this.cStartDBWert = bibALL.null2leer(cStartDBValue).trim();
		this.get_oTextFieldForSearchInput().setText(this.cStartDBWert);
		
		DotFormatterGermanFixed oDF = new DotFormatterGermanFixed(this.cStartDBWert);
		if (oDF.doFormat())
			this.FillLabelWithDBQuery(oDF.getStringUnFormated());
		
		
	}

	public String get_cInsertValuePart() throws myException
	{
		if (bibALL.isEmpty(this.get_oTextFieldForSearchInput().getText()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Cannot be empty !!");
			else
				return "NULL";

		// jetzt die eingabe checken
		DotFormatter oDF = new  DotFormatter(bibALL.null2leer(this.get_oTextFieldForSearchInput().getText()),0,Locale.GERMAN,true,3);
		
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
		return this.get_oTextFieldForSearchInput().getText();
	}

	
	
	public String get_cUpdatePart() throws myException
	{
		if (bibALL.isEmpty(this.get_oTextFieldForSearchInput().getText()))
			if (! this.get_bEmptyAllowd())
				throw new myException("UB_TextField:get_cUpdatePart():"+bibALL.null2leer(this.get_cDBFieldName())+"Cannot be empty !!");
			else
				return bibALL.null2leer(this.get_cDBFieldName())+"=NULL";
		
		// jetzt die eingabe checken
		DotFormatter oDF = new  DotFormatter(bibALL.null2leer(this.get_oTextFieldForSearchInput().getText()),0,Locale.GERMAN,true,3);
		
		if (! oDF.doFormat())
			throw new myException("UB_TextField:get_get_cInsertValuePart():"+bibALL.null2leer(this.get_cDBFieldName())+" Value not allowed !!");
		else
			return this.get_cDBFieldName()+"="+oDF.getStringUnFormated();
	}

	
	public boolean get_bFieldHasChanged()
	{
		// jetzt die eingabe checken
		DotFormatter oDF = new  DotFormatter(bibALL.null2leer(this.get_oTextFieldForSearchInput().getText()),0,Locale.GERMAN,true,3);
		
		if (oDF.doFormat())
			this.get_oTextFieldForSearchInput().setText(oDF.getStringFormated());
		
		return (! this.cStartDBWert.equals(this.get_oTextFieldForSearchInput().getText().trim()));
	}

	
	public boolean get_bIsEmpty() 
	{
		return bibALL.isEmpty(this.get_oTextFieldForSearchInput().getText());
	}

	
	@Override
	public void set_StyleForInput(boolean bEnabled)
	{
		MyE2_TextField oTF = this.get_oTextFieldForSearchInput();
		if (oTF.EXT().get_STYLE_FACTORY() != null)
		{
			oTF.setStyle(oTF.EXT().get_STYLE_FACTORY().get_Style(bEnabled, this.get_bEmptyAllowd(), false));
		}
	}

	
	
	

}
