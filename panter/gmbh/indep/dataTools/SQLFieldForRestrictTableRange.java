package panter.gmbh.indep.dataTools;

import javax.servlet.http.HttpSession;

import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;




/**
 *  @author martin
 *  Builds field, which defines a where-statement for subtables and
 *  make automatic a value in insert statements
 *
 */
public class SQLFieldForRestrictTableRange extends SQLField
{
	/*
	 * basis-wert (z.B. "Meier")
	 */
	String cBaseValueRaw = null;
	

	/**
	 * @param ctablename
	 * @param cfieldname
	 * @param cfieldlabel
	 * @param cfieldLabelForUser
	 * @param cbaseValueDatabase_readable_Format (MUSS direkt in SQL-String passen !!!)
	 * @param oses
	 * @throws myException
	 */
	public SQLFieldForRestrictTableRange(	String ctablename, 
											String cfieldname, 
											String cfieldlabel, 
											MyString cfieldLabelForUser,
											String cbaseValueDatabase_readable_Format, 
											HttpSession oses) throws myException
	{
		super(ctablename, cfieldname, cfieldlabel, cfieldLabelForUser, false,	oses);
		
		this.cBaseValueRaw = cbaseValueDatabase_readable_Format;
		
		this.set_bFieldCanBeWrittenInMask(false);

		
	}

	
	
	/**
	 * @param newValueRAW_DB_Format    Uebergabe eines neuen beschraenkungswertes 
	 * @throws myException
	 */
	public void set_cRestrictionValue_IN_DB_FORMAT(String newValueRAW_DB_Format) throws myException			
	{
		
		if (newValueRAW_DB_Format == null)
			throw new myException("SQLFieldForRestrictTableRange:set_cRestictionValueFormated:nullvalue not allowed !");
		/*
		 * hier pruefen, ob der wert fuer dieses feld zulaessig ist 
		 */
		this.cBaseValueRaw = newValueRAW_DB_Format;

	}
	

	
	/**
	 * @param newValueRAW_DB_Format    Uebergabe eines neuen beschraenkungswertes 
	 * @throws myException
	 */
	public String get_cRestictionValue_IN_DB_FORMAT() throws myException			
	{
		
		if (this.cBaseValueRaw == null)
			throw new myException("SQLFieldForRestrictTableRange:get_cRestictionValueFormated:nullvalue not allowed !");
		return this.cBaseValueRaw;
	}

	
	
	
	public void set_cNewValueFormated(String newValueFormated) throws myException			
	{
		throw new myException("SQLFieldForRestrictTableRange:set_cNewValueFormated:not allowed in this type !");
	}

	
	public void set_cDefaultValueFormated(String defaultValue) throws myException						
	{		
		throw new myException("SQLFieldForRestrictTableRange:set_cDefaultValue:not allowed in this type !");
	}
	
	
	public String get_cInsertFieldPart() throws myException
	{
		if (this.get_oFieldMetaDef() == null)
			throw new myException("SQLField:get_cInsertBlock:this field is not initialized/has no metadef-infos!!");
		
		return this.get_cFieldName();
	}
	
	
	
	public String get_cInsertValuePart() throws myException
	{
		if (this.get_oFieldMetaDef() == null)
			throw new myException("SQLField:get_cInsertBlock:this field is not initialized/has no metadef-infos!!");
		
		return this.cBaseValueRaw;
		
	}
	
	
	public String get_cUpdateSequence() throws myException
	{
		return this.get_cInsertFieldPart()+"="+this.get_cInsertValuePart();		
	}



	/*
	 * muss ueberschrieben werden, da die pruefung, ob das beschraenkungsfeld einen
	 * korrekten wert hat, nur mit vorhandener metadef meoglich ist
	 * @see utilities.indep.dataTools.SQLField#set_oFieldMetaDef(utilities.indep.dataTools.myFieldMetaInformation)
	 */
	public void set_oFieldMetaDef(MyMetaFieldDEF fieldMetaDef) throws myException	
	{		
		super.set_oFieldMetaDef(fieldMetaDef);
		this.get_oFieldMetaDef().set_bIsNullableBasic(this.get_bIsNullableByUserDef_Before_INIT_SQLFieldMAP());

		if (this.cBaseValueRaw == null)
			throw new myException("SQLFieldForRestrictTableRange:set_oFieldMetaDef:nullvalue not allowed !");
		
	}

	
	
	//2013-06-24: eigene methode zur erzeugung des where-Blocks
	public String get_WhereBlockForSQL_Bedingung() throws myException {
		return (this.get_cTableName()+"."+this.get_cFieldName()+"="+this.get_cInsertValuePart());
	}
	
}
