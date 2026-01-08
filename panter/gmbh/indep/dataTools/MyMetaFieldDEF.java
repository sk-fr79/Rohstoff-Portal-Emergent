package panter.gmbh.indep.dataTools;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import panter.gmbh.Echo2.BasicInterfaces.IF_BasicTypeWrapper;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDBString;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD.DATATYPES;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_BigDecimal;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Date;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionDataValueNotFittingToField;
import panter.gmbh.indep.maggie.DotFormatter;
import panter.gmbh.indep.maggie.TestingDate;




/*
 * zur speicherung einer meta-info zu einer spalte
 */
public class MyMetaFieldDEF extends HashMap<String,String> 
{	
	/*
	 * 2015-06-25: typen via enums definieren
	 */
	public enum BASETYPE {
		NUMBER,
		CHAR,
		DATE
	}
	
	
	public enum DBTYPE {
		FLOAT(BASETYPE.NUMBER), 
		DOUBLE(BASETYPE.NUMBER), 
		FIXED(BASETYPE.NUMBER), 
		INTEGER(BASETYPE.NUMBER), 
		SMALLINT(BASETYPE.NUMBER),
		NUMBER(BASETYPE.NUMBER),
		VARCHAR(BASETYPE.CHAR),
		VARCHAR2(BASETYPE.CHAR),
		CHAR(BASETYPE.CHAR),
		NVARCHAR(BASETYPE.CHAR),
		NVARCHAR2(BASETYPE.CHAR),
		NCHAR(BASETYPE.CHAR),
		ROWID(BASETYPE.NUMBER),
		// long ist ein longvarchar, kein nummerischer Wert
		LONG(BASETYPE.CHAR),
		DATE(BASETYPE.DATE),
		DATETIME(BASETYPE.DATE),
		TIMESTAMP(BASETYPE.DATE)
		;
		
		private BASETYPE baseType = null;
		private DBTYPE(BASETYPE bt) {
			this.baseType=bt;
		}
		public BASETYPE baseType() {
			return this.baseType;
		}
		
		public static DBTYPE get_DBTYPE(String type) {
			for (DBTYPE typ: DBTYPE.values()) {
				if (typ.name().equals(type.toUpperCase())) {
					return typ;
				}
			}
			return null;
		}
	}
	
	
	
	public static final String KEY_TABLENAME = 						"KEY_TABLENAME";
	public static final String KEY_FIELDNAME = 						"KEY_FIELDNAME";
	public static final String KEY_FIELDLABEL = 					"KEY_FIELDLABEL";
	public static final String KEY_FIELDTYPE = 						"KEY_FIELDTYPE";

	private int   ColumnNumber = -1;
	
	
	private Integer    iKEY_FIELDTEXTLENGTH = null;
	private Integer    iKEY_FIELDNUMBERLENGTH = null;
	private Integer    iKEY_FIELDDECIMALS = null;
	private Integer    iKEY_FIELD_IS_NULLABLE_BASIC = null;
	private Integer    iKEY_FIELD_IS_NULLABLE_INTERACTIVE = null;
	
	
	/*
	 * hilfsfeld fuer die definition der Feldeingabelaenge in den masken.
	 * ist standardmaessig auf -1 , d.h. wird dann unbeachtet gelassen,
	 * bei nvarchar2 - feldern wird es besetzt und in die textfelder definiert
	 */
	private int 	    iNumberCharactersInMask = -1;
	
	
	


	
	/**
	 * @param oRS  Resultset
	 * @param iColNumber_AB_0 (nummer im resultset (zaehlweise ab 0)
	 * @param cTableName (kann null oder "" sein, dann wird versucht den tablename zu ermitteln -> funktioniert selten)
	 * @throws SQLException
	 */
	public MyMetaFieldDEF(ResultSet oRS, int iColNumber_AB_0, String cTableName) throws SQLException 
	{
		super();
		
		this.ColumnNumber = iColNumber_AB_0;
		
		if (bibALL.isEmpty(cTableName))
			this.put(MyMetaFieldDEF.KEY_TABLENAME, 			oRS.getMetaData().getTableName(iColNumber_AB_0+1));
		else
			this.put(MyMetaFieldDEF.KEY_TABLENAME, 			cTableName);
		
		this.put(MyMetaFieldDEF.KEY_FIELDNAME, 			oRS.getMetaData().getColumnName(iColNumber_AB_0+1));
		this.put(MyMetaFieldDEF.KEY_FIELDLABEL, 		oRS.getMetaData().getColumnLabel(iColNumber_AB_0+1));
		
		//this.put(MyMetaFieldDEF.KEY_FIELDTEXTLENGTH, 	new Integer(oRS.getMetaData().getColumnDisplaySize(iColNumber+1)));
		this.iKEY_FIELDTEXTLENGTH = new Integer(oRS.getMetaData().getColumnDisplaySize(iColNumber_AB_0+1));
		
		this.put(MyMetaFieldDEF.KEY_FIELDTYPE, 			oRS.getMetaData().getColumnTypeName(iColNumber_AB_0+1));
		
		
		//this.put(MyMetaFieldDEF.KEY_FIELDNUMBERLENGTH,	new Integer(oRS.getMetaData().getPrecision(iColNumber+1)));
		this.iKEY_FIELDNUMBERLENGTH = new Integer(oRS.getMetaData().getPrecision(iColNumber_AB_0+1));

		//this.put(MyMetaFieldDEF.KEY_FIELDDECIMALS,		new Integer(oRS.getMetaData().getScale(iColNumber+1)));
		this.iKEY_FIELDDECIMALS = new Integer(oRS.getMetaData().getScale(iColNumber_AB_0+1));
		
		// oracle liefert bei intrinsisch definierten Nummer-spalten negative scale-werte. 
		if (oRS.getMetaData().getScale(iColNumber_AB_0+1)<0)
		{
			//this.put(MyMetaFieldDEF.KEY_FIELDDECIMALS,		new Integer(0));
			this.iKEY_FIELDDECIMALS = new Integer(0);
		}
		
		
		//this.put(MyMetaFieldDEF.KEY_FIELD_IS_NULLABLE_BASIC, 		new Integer(oRS.getMetaData().isNullable(iColNumber+1)));
		this.iKEY_FIELD_IS_NULLABLE_BASIC = new Integer(oRS.getMetaData().isNullable(iColNumber_AB_0+1));
		
		//fuer interactive umschaltungen von null zu nicht null
		//this.put(MyMetaFieldDEF.KEY_FIELD_IS_NULLABLE_INTERACTIVE, 	new Integer(1));
		this.iKEY_FIELD_IS_NULLABLE_INTERACTIVE = new Integer(1);
		
		if (DB_META.vDB_TEXT_TYPES.contains(this.get_FieldType()))
		{
			this.iNumberCharactersInMask = this.iKEY_FIELDTEXTLENGTH;
			
		}
		
		
		//DEBUG.System_println("NAME: "+this.get(MyMetaFieldDEF.KEY_FIELDLABEL)+" // PRECISION: "+this.iKEY_FIELDNUMBERLENGTH+" // DEZIMALE: "+this.iKEY_FIELDDECIMALS);
		
	}

	
	/**
	 * Baut  MyMetaFieldDEF anhand des Metadata-Objektes auf
	 * @author manfred
	 * @date   16.04.2012
	 * @param oMetaData
	 * @param iColNumber_AB_0
	 * @param cTableName
	 * @throws SQLException
	 */
	public MyMetaFieldDEF(ResultSetMetaData oMetaData, int iColNumber_AB_0, String cTableName) throws SQLException {
		super();
		
		this.ColumnNumber = iColNumber_AB_0;
		
		if (bibALL.isEmpty(cTableName))
			this.put(MyMetaFieldDEF.KEY_TABLENAME, 			oMetaData.getTableName(iColNumber_AB_0+1));
		else
			this.put(MyMetaFieldDEF.KEY_TABLENAME, 			cTableName);
		
		this.put(MyMetaFieldDEF.KEY_FIELDNAME, 			oMetaData.getColumnName(iColNumber_AB_0+1));
		this.put(MyMetaFieldDEF.KEY_FIELDLABEL, 		oMetaData.getColumnLabel(iColNumber_AB_0+1));
		
		//this.put(MyMetaFieldDEF.KEY_FIELDTEXTLENGTH, 	new Integer(oRS.getMetaData().getColumnDisplaySize(iColNumber+1)));
		this.iKEY_FIELDTEXTLENGTH = new Integer(oMetaData.getColumnDisplaySize(iColNumber_AB_0+1));
		
		this.put(MyMetaFieldDEF.KEY_FIELDTYPE, 			oMetaData.getColumnTypeName(iColNumber_AB_0+1));
		
		
		//this.put(MyMetaFieldDEF.KEY_FIELDNUMBERLENGTH,	new Integer(oRS.getMetaData().getPrecision(iColNumber+1)));
		this.iKEY_FIELDNUMBERLENGTH = new Integer(oMetaData.getPrecision(iColNumber_AB_0+1));

		//this.put(MyMetaFieldDEF.KEY_FIELDDECIMALS,		new Integer(oRS.getMetaData().getScale(iColNumber+1)));
		this.iKEY_FIELDDECIMALS = new Integer(oMetaData.getScale(iColNumber_AB_0+1));
		
		// oracle liefert bei intrinsisch definierten Nummer-spalten negative scale-werte. 
		if (oMetaData.getScale(iColNumber_AB_0+1)<0)
		{
			//this.put(MyMetaFieldDEF.KEY_FIELDDECIMALS,		new Integer(0));
			this.iKEY_FIELDDECIMALS = new Integer(0);
		}
		
		
		//this.put(MyMetaFieldDEF.KEY_FIELD_IS_NULLABLE_BASIC, 		new Integer(oRS.getMetaData().isNullable(iColNumber+1)));
		this.iKEY_FIELD_IS_NULLABLE_BASIC = new Integer(oMetaData.isNullable(iColNumber_AB_0+1));
		
		//fuer interactive umschaltungen von null zu nicht null
		//this.put(MyMetaFieldDEF.KEY_FIELD_IS_NULLABLE_INTERACTIVE, 	new Integer(1));
		this.iKEY_FIELD_IS_NULLABLE_INTERACTIVE = new Integer(1);
		
		if (DB_META.vDB_TEXT_TYPES.contains(this.get_FieldType()))
		{
			this.iNumberCharactersInMask = this.iKEY_FIELDTEXTLENGTH;
			
		}

	}
	
	
	
	/**
	 * 2014-08-26: 
	 * EXPERIMENTELLE Implementierung eines MyFieldMetaDef 
	 * NOCH ZU PRUEFEN ! 
	 * @param cTableName
	 * @param cFieldName
	 * @param cFieldLabel
	 * @param cFIELDTYPE
	 * @param iFieldTextLength
	 * @param iFieldNumberlength
	 * @param iFieldDecimals
	 * @param bNullableBasic
	 */
	public MyMetaFieldDEF(	String 	cTableName, 
							String 	cFieldName, 
							String 	cFieldLabel, 
							String 	cFIELDTYPE, 
							int 	iFieldTextLength, 
							int 	iFieldNumberlength, 
							int 	iFieldDecimals,
							boolean bNullableBasic) {
		
		this.ColumnNumber = -1;    //undef
		
		this.put(MyMetaFieldDEF.KEY_TABLENAME, 			cTableName);
		this.put(MyMetaFieldDEF.KEY_FIELDNAME, 			cFieldName);
		this.put(MyMetaFieldDEF.KEY_FIELDLABEL, 		S.isEmpty(cFieldLabel)?cFieldName:cFieldLabel);
		this.put(MyMetaFieldDEF.KEY_FIELDTYPE, 			cFIELDTYPE);
		this.iKEY_FIELDTEXTLENGTH = iFieldTextLength;
		this.iKEY_FIELDNUMBERLENGTH = iFieldNumberlength;
		this.iKEY_FIELDDECIMALS = iFieldDecimals;

		this.iKEY_FIELD_IS_NULLABLE_BASIC = bNullableBasic?1:0;
		
		//fuer interactive umschaltungen von null zu nicht null
		//this.put(MyMetaFieldDEF.KEY_FIELD_IS_NULLABLE_INTERACTIVE, 	new Integer(1));
		this.iKEY_FIELD_IS_NULLABLE_INTERACTIVE = new Integer(1);
		
		if (DB_META.vDB_TEXT_TYPES.contains(cFIELDTYPE))
		{
			this.iNumberCharactersInMask = this.iKEY_FIELDTEXTLENGTH;
			
		}
	}

	
	
	
	
	public String get_TableName()
	{
		return (String)this.get(MyMetaFieldDEF.KEY_TABLENAME);
	}
	
	//2013-09-20: setter fuer den tablename
	public void set_Tablename(String tablename, boolean bOverwrite) {
		if (S.isEmpty(this.get_TableName()) || bOverwrite) {
			this.put(MyMetaFieldDEF.KEY_TABLENAME, 	tablename);
		}
	}
	
	
	public String get_FieldName()
	{
		return (String)this.get(MyMetaFieldDEF.KEY_FIELDNAME);
	}
	
	public String get_FieldLabel()
	{
		return (String)this.get(MyMetaFieldDEF.KEY_FIELDLABEL);
	}

	
	public String get_FieldType()
	{
		return (String)this.get(MyMetaFieldDEF.KEY_FIELDTYPE);
	}

	public int get_FieldNumberLENGTH()
	{
		//return ((Integer)this.get(MyMetaFieldDEF.KEY_FIELDNUMBERLENGTH)).intValue();
		return this.iKEY_FIELDNUMBERLENGTH.intValue();
	}

	public int get_FieldTextLENGTH()
	{
		//return ((Integer)this.get(MyMetaFieldDEF.KEY_FIELDTEXTLENGTH)).intValue();
		return this.iKEY_FIELDTEXTLENGTH.intValue();
	}

	
	public int get_FieldDecimals()
	{
		//return ((Integer)this.get(MyMetaFieldDEF.KEY_FIELDDECIMALS)).intValue();
		return this.iKEY_FIELDDECIMALS.intValue();
	}

	public boolean get_bFieldNullableBasicAndInteractive()
	{
		return (this.get_bFieldNullableBasic() && this.get_bFieldNullableInteractive());
	}

	public boolean get_bFieldNullableBasic()
	{
		//return (((Integer)this.get(MyMetaFieldDEF.KEY_FIELD_IS_NULLABLE_BASIC)).intValue()!=0);
		return (this.iKEY_FIELD_IS_NULLABLE_BASIC.intValue()!=0);
	}


	public boolean get_bFieldNullableInteractive()
	{
		//return (((Integer)this.get(MyMetaFieldDEF.KEY_FIELD_IS_NULLABLE_INTERACTIVE)).intValue()!=0);
		return (this.iKEY_FIELD_IS_NULLABLE_INTERACTIVE.intValue()!=0);
	}

	
	//2015-02-09: komplementaere getters
	public boolean get_bMUST_Field_Basic() {
		return (this.iKEY_FIELD_IS_NULLABLE_BASIC.intValue()==0);
	}

	public boolean get_bMUST_Field_Interactive() {
		return (this.iKEY_FIELD_IS_NULLABLE_INTERACTIVE.intValue()==0);
	}

	
	
	
	/**
	 * @return SpaltenNummer im Resultset (zaehlweise ab 0) 
	 */
	public int get_iColumnNumber() 
	{
		return ColumnNumber;
	}

	public int get_iColumnNumberForResultSet() 
	{
		return ColumnNumber+1;
	}

	
	public boolean get_bIsNumberTypeWithDecimals()
	{
		return (DB_META.vDB_NUMBER_TYPES.contains(this.get_FieldType()) && this.get_FieldDecimals()>0);
	}
	
	public boolean get_bIsNumberTypeWithOutDecimals()
	{
		// oracle liefert bei nicht explizit definierten number-fields negative dezimalstellen aus 
		return (DB_META.vDB_NUMBER_TYPES.contains(this.get_FieldType()) && this.get_FieldDecimals()<=0);
	}
	
	public boolean get_bIsDateType()
	{
		return (DB_META.vDB_DATE_TYPES.contains(this.get_FieldType()));
	}
	
	public boolean get_bIsTextType()
	{
		return (DB_META.vDB_TEXT_TYPES.contains(this.get_FieldType()));
	}

	
	
	/**
	 * @param bisNullable
	 */
	public void    set_bIsNullableBasic(boolean bisNullable)
	{
		boolean bIsNullable = this.get_bFieldNullableBasic();   //alter wert
		
		bIsNullable = bIsNullable && bisNullable;				//wenn einer der beiden werte falsch ist, dann beide
		
		if (bIsNullable)
		{
			//this.put(MyMetaFieldDEF.KEY_FIELD_IS_NULLABLE_BASIC, 	new Integer(1));
			this.iKEY_FIELD_IS_NULLABLE_BASIC = new Integer(1);
		}
		else
		{
			//this.put(MyMetaFieldDEF.KEY_FIELD_IS_NULLABLE_BASIC, 	new Integer(0));
			this.iKEY_FIELD_IS_NULLABLE_BASIC = new Integer(0);
		}
		
		
	}


	/**
	 * @param bisNullable
	 * fuer masken, wobei der nullableBasic immer der wichtigere wert ist
	 */
	public void    set_bIsNullableInteractiv(boolean bisNullable)
	{
		if (bisNullable)
		{
			//this.put(MyMetaFieldDEF.KEY_FIELD_IS_NULLABLE_INTERACTIVE, 	new Integer(1));
			this.iKEY_FIELD_IS_NULLABLE_INTERACTIVE = new Integer(1);
		}
		else
		{
			//this.put(MyMetaFieldDEF.KEY_FIELD_IS_NULLABLE_INTERACTIVE, 	new Integer(0));
			this.iKEY_FIELD_IS_NULLABLE_INTERACTIVE = new Integer(0);
		}
		
	}

	
	
	
	/**
	 * @returns  DB_META.BASIC_TYPE_TEXT, BASIC_TYPE_DATUM, BASIC_TYPE_NUMMER
	 * @throws myException
	 */
	public String get_cBASIC_Field_TYPE() throws myException
	{
		return DB_META.get_cColumnTYPE_BASIC(this.get_FieldType());
	}

	

	
	
	/**
	 * @param cInputString aus Maske
	 * @param bCheckWetherNULLIsAllowed
	 * @param bForceFieldIsNotNullable   (wenn true und bCheckWetherNULLIsAllowed, dann wird auf jedenfall die pruefung auf leer gemacht)   //2015-02-24
	 * @return s String fuer eine direkte Nutzung in SQL-Statements (z.B. 2007-10-31 oder 'Meier') 
	 * @throws myException
	 * @throws myExceptionDataValueNotFittingToField
	 */
	public String get_cStringForDataBase(String cInputString, boolean bCheckWetherNULLIsAllowed, boolean bForceFieldIsNotNullable) throws myException, myExceptionDataValueNotFittingToField
	{
		
		/*
		 * zuerst checken, ob null vorhanden ist und auch erlaubt, der wert von nullableInteractive wird hier nicht beruecksichtigt
		 */
		if (bCheckWetherNULLIsAllowed)
		{
			if ((  (!this.get_bFieldNullableBasicAndInteractive()) || bForceFieldIsNotNullable ) && bibALL.isEmpty(cInputString))
				throw new myExceptionDataValueNotFittingToField(myExceptionDataValueNotFittingToField.VALUE_CAN_NOT_BE_NULL,
						"Error Unformating Field->"+this.get_TableName()+" - "+this.get_FieldLabel()+" can not be null!");
		}

		/*
		 * null sofort zurueckgeben
		 */
		if (cInputString == null || cInputString.trim().equals(""))
			return "NULL";
		
		
		if (this.get_bIsNumberTypeWithDecimals() || this.get_bIsNumberTypeWithOutDecimals())
        {
			DotFormatter oDF = new DotFormatter(cInputString,this.get_FieldDecimals(),Locale.GERMAN,true,3);
			if (oDF.doFormat())
			{
				String cRueck = oDF.getStringUnFormated();
				if (cRueck.length()>this.get_FieldTextLENGTH())
					throw new myExceptionDataValueNotFittingToField(
							myExceptionDataValueNotFittingToField.VALUE_IS_TOO_LONG,"Error Unformating Field:"+this.get_TableName()+" - "+this.get_FieldLabel());
					
				return cRueck;
			}
			else
				throw new myExceptionDataValueNotFittingToField(
						myExceptionDataValueNotFittingToField.VALUE_CAN_NOT_BE_A_NUMBER,"Error Unformating Field:"+this.get_TableName()+" - "+this.get_FieldLabel());
			
        }
		else if (DB_META.vDB_DATE_TYPES.contains(this.get_FieldType()))
	    {
			TestingDate oDate = new TestingDate(cInputString);
			if (!oDate.testing())
				throw new myExceptionDataValueNotFittingToField(
						myExceptionDataValueNotFittingToField.VALUE_CAN_NOT_BE_A_DATE,"Error Unformating Field:"+this.get_TableName()+" - "+this.get_FieldLabel());				
			else
			{
				return oDate.get_ISO_DateFormat(true);
			}
	    }
		else if (DB_META.vDB_TEXT_TYPES.contains(this.get_FieldType()))
	    {
			if (cInputString.length()>this.get_FieldTextLENGTH())
				throw new myExceptionDataValueNotFittingToField(
						myExceptionDataValueNotFittingToField.VALUE_IS_TOO_LONG,"Error Unformating Field:"+this.get_TableName()+" - "+this.get_FieldLabel()+" input-value is too long");
			else
				return bibALL.MakeSql(cInputString);
			
	    }
		else
			throw new myException("myFieldMetaInformation:get_cColumnTYPE_GABOR:Error define Fieldtyp !");
	}


	public int get_iNumberCharactersInMask()
	{
		return iNumberCharactersInMask;
	}
	

	
	public MyMetaFieldDEF get_Clone() {
		return new MyMetaFieldDEF(	this.get(MyMetaFieldDEF.KEY_TABLENAME), 
									this.get(MyMetaFieldDEF.KEY_FIELDNAME), 
									this.get(MyMetaFieldDEF.KEY_FIELDLABEL), 
									this.get_FieldType(), 
									this.get_FieldTextLENGTH(), 
									this.get_FieldNumberLENGTH(), 
									this.get_FieldDecimals(), 
									this.get_bFieldNullableBasic());
	}

	
	
	/**
	 * 2015-12-15:  neue methode stellt fest, ob es ein "pseudo-boolescher" wert ist
	 * @return
	 * @throws myException
	 */
	public boolean is_boolean_single_char() throws myException {
		if (this.get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_TEXT)) {
			if (this.iKEY_FIELDTEXTLENGTH==1) {
				return true;
			}
		}
		
		return false;
	}
	
	
	
	
	/**
	 * liefert ein passendes object vom typ IF_BasicTypeWrapper zum uebergebenen formatierten string. falls der wert fuer das feld nicht passt, wird eine exception geschmissen
	 * @param formatedString
	 * @return
	 * @throws myException
	 */
	public IF_BasicTypeWrapper get_object_throwExWhenNotFitting(String formatedString) throws myException {
		this.get_cStringForDataBase(formatedString, true, false);    //erste pruefung, schmeisst bei nichtpassen eine exception
		return this._get_wrapper(formatedString);
	}
	

	/**
	 * When not fitting, then null
	 * @param formatedString
	 * @return
	 * @throws myException
	 */
	public IF_BasicTypeWrapper get_object(String formatedString) throws myException {
		try {
			this.get_cStringForDataBase(formatedString, true, false);    //erste pruefung, schmeisst bei nichtpassen eine exception
		} catch (Exception e) {
			return null;
		}
		return this._get_wrapper(formatedString);
	}
	
	private IF_BasicTypeWrapper _get_wrapper(String formatedString) throws myException {
		IF_BasicTypeWrapper  ret_val = null;
		
		if (this.get_bIsNumberTypeWithOutDecimals())   {
			if (S.isEmpty(formatedString)) {
				ret_val = (MyLong)null;
			} else {
				ret_val = new MyLong(formatedString);
			}
		} else if (this.get_bIsNumberTypeWithDecimals())   {
			if (S.isEmpty(formatedString)) {
				ret_val = (MyBigDecimal)null;
			} else {
				ret_val = new MyBigDecimal(formatedString);
			}
        }
		else if (DB_META.vDB_DATE_TYPES.contains(this.get_FieldType()))   {
			if (S.isEmpty(formatedString)) {
				ret_val = (MyDate)null;
			} else {
				ret_val = new MyDate(formatedString);
			}
 	    }
		else if (DB_META.vDB_TEXT_TYPES.contains(this.get_FieldType()))  {
			if (S.isEmpty(formatedString)) {
				ret_val = (MyDBString)null;
			} else {
				ret_val = new MyDBString(formatedString);
			}
	    } else {
			throw new myException("MyMetaFieldDEF.get_object: unidentified type!");
		}
		return ret_val;

	}
	
	
	/**
	 * 2016-11-04: pruefmethode, um die inhalte zu verifizieren bevor sie in die records geschrieben werden
	 *             in den MyE2_MessageVector werden nur messages geschrieben, die dem anwender eine info geben, was nicht passt (myExceptionDataValueNotFittingToField)
	 *             Andere Exceptions werden weitergegeben
	 * @param cNewValueFormated
	 * @return 
	 * @throws myException
	 */
	public MyE2_MessageVector  check_NewValueForDatabase(String cNewValueFormated) throws myException	{
		
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
	
		try	{
			this.get_cStringForDataBase(cNewValueFormated, true, false);
		} catch (myExceptionDataValueNotFittingToField ex) {
			String infoField = this.get_FieldName();
			oMV.add(ex.get_Message(infoField, cNewValueFormated));
		}
		catch (myException exx)	{
			throw new myException(S.NN(this.get_FieldName())+": Error: "+exx.get_ErrorMessage() );
		}
		
		return oMV;
		
	}

	
	
	
	/**
	 * 2016-11-09: vergleichsmethode, um gleichheit zwischen metadefs zu pruefen
	 */
	public boolean is_equal_to(MyMetaFieldDEF  def2compare, boolean includeTablename) {
		if (includeTablename) {
			if (! S.NN(this.get_TableName(),"1").equalsIgnoreCase(S.NN(def2compare.get_TableName(),"2"))) {
				return false;
			}
		}
		
		if (! S.NN(this.get_FieldName(),"1").equalsIgnoreCase(S.NN(def2compare.get_FieldName(),"2"))) {
			DEBUG.System_println("Fieldname: "+this.get_FieldName()+"->"+def2compare.get_FieldName());
			return false;
		}
		
		if (! S.NN(this.get_FieldType(),"1").equalsIgnoreCase(S.NN(def2compare.get_FieldType(),"2"))) {
			DEBUG.System_println("FieldType: "+this.get_FieldType()+"->"+def2compare.get_FieldType());
			return false;
		}
		
		if (this.iKEY_FIELD_IS_NULLABLE_BASIC.intValue()!=def2compare.iKEY_FIELD_IS_NULLABLE_BASIC.intValue()) {
			DEBUG.System_println("Nullable: "+this.iKEY_FIELD_IS_NULLABLE_BASIC.intValue()+"->"+def2compare.iKEY_FIELD_IS_NULLABLE_BASIC.intValue());
			return false;
		}
		
		if (this.iKEY_FIELDNUMBERLENGTH.intValue()!=def2compare.iKEY_FIELDNUMBERLENGTH.intValue()) {
			DEBUG.System_println("FIELDNUMBERLENGTH: "+this.iKEY_FIELDNUMBERLENGTH.intValue()+"->"+def2compare.iKEY_FIELDNUMBERLENGTH.intValue());
			return false;
		}
		
		if (this.iKEY_FIELDTEXTLENGTH.intValue()!=def2compare.iKEY_FIELDTEXTLENGTH.intValue()) {
			DEBUG.System_println("FIELDTEXTLENGTH: "+this.iKEY_FIELDTEXTLENGTH.intValue()+"->"+def2compare.iKEY_FIELDTEXTLENGTH.intValue());
			return false;
		}
	
		if (this.iKEY_FIELDDECIMALS.intValue()!=def2compare.iKEY_FIELDDECIMALS.intValue()) {
			DEBUG.System_println("iKEY_FIELDDECIMALS: "+this.iKEY_FIELDDECIMALS.intValue()+"->"+def2compare.iKEY_FIELDDECIMALS.intValue());
			return false;
		}
	
		return true;
	}
	
	
	
	/**
	 * 2017-10-13: vergleichsmethode, nur datentyp, um gleichheit zwischen metadefs zu pruefen
	 */
	public boolean is_equal_type(MyMetaFieldDEF  def2compare, boolean includeTablename) {
		if (includeTablename) {
			if (! S.NN(this.get_TableName(),"1").equalsIgnoreCase(S.NN(def2compare.get_TableName(),"2"))) {
				return false;
			}
		}
		
		if (! S.NN(this.get_FieldName(),"1").equalsIgnoreCase(S.NN(def2compare.get_FieldName(),"2"))) {
			DEBUG.System_println("Fieldname: "+this.get_FieldName()+"->"+def2compare.get_FieldName());
			return false;
		}
		
		if (! S.NN(this.get_FieldType(),"1").equalsIgnoreCase(S.NN(def2compare.get_FieldType(),"2"))) {
			DEBUG.System_println("FieldType: "+this.get_FieldType()+"->"+def2compare.get_FieldType());
			return false;
		}
	
		return true;
	}
	


	/**
	 * 2018-03-13: erzeugt ein raw-object aus einem formated-string
	 * @param formatedString
	 * @return
	 * @throws myException
	 */
	public Object getRaw(String formatedString) throws myException {
		this.get_cStringForDataBase(formatedString, true, false);    //erste pruefung, schmeisst bei nichtpassen eine exception

		if (this.get_bIsNumberTypeWithOutDecimals())   {
			if (S.isEmpty(formatedString)) {
				return (Long)null;
			} else {
				MyLong l = new MyLong(formatedString);
				if (l.isOK()) {
					return l.get_oLong();
				} else {
					throw new myException("Error creating raw Long-Object !");
				}
			}
		} else if (this.get_bIsNumberTypeWithDecimals())   {
			if (S.isEmpty(formatedString)) {
				return (BigDecimal)null;
			} else {
				MyBigDecimal bd = new MyBigDecimal(formatedString);
				if (bd.isOK()) {
					return bd.get_bdWert();
				} else {
					throw new myException("Error creating raw BigDecimal-Object !");
				}
			}
        }
		else if (DB_META.vDB_DATE_TYPES.contains(this.get_FieldType()))   {
			if (S.isEmpty(formatedString)) {
				return (Date)null;
			} else {
				MyDate date = new MyDate(formatedString);
				if (date.isOK()) {
					return date.get_Calendar().getTime();
				} else {
					throw new myException("Error creating raw Date-Object !");
				}
				
			}
 	    }
		else if (DB_META.vDB_TEXT_TYPES.contains(this.get_FieldType()))  {
			if (S.isEmpty(formatedString)) {
				return (String)null;
			} else {
				return formatedString;
			}
	    } else {
			throw new myException("MyMetaFieldDEF.get_object: unidentified type!");
		}
	}


	

	/**
	 * 
	 * @author martin
	 * @date 05.03.2020
	 *
	 * @param formatedString
	 * @return rawvalue, ohne auf den Datenbank-context mit nullable, zu lang o.ae. usw zu achten
	 * @throws myException
	 */
	public Object getRawWithoutDatabaseCheck(String formatedString) throws myException {

		if (this.get_bIsNumberTypeWithOutDecimals())   {
			if (S.isEmpty(formatedString)) {
				return (Long)null;
			} else {
				MyLong l = new MyLong(formatedString);
				if (l.isOK()) {
					return l.get_oLong();
				} else {
					throw new myException("Error creating raw Long-Object !");
				}
			}
		} else if (this.get_bIsNumberTypeWithDecimals())   {
			if (S.isEmpty(formatedString)) {
				return (BigDecimal)null;
			} else {
				MyBigDecimal bd = new MyBigDecimal(formatedString);
				if (bd.isOK()) {
					return bd.get_bdWert();
				} else {
					throw new myException("Error creating raw BigDecimal-Object !");
				}
			}
        }
		else if (DB_META.vDB_DATE_TYPES.contains(this.get_FieldType()))   {
			if (S.isEmpty(formatedString)) {
				return (Date)null;
			} else {
				MyDate date = new MyDate(formatedString);
				if (date.isOK()) {
					return date.get_Calendar().getTime();
				} else {
					throw new myException("Error creating raw Date-Object !");
				}
				
			}
 	    }
		else if (DB_META.vDB_TEXT_TYPES.contains(this.get_FieldType()))  {
			if (S.isEmpty(formatedString)) {
				return (String)null;
			} else {
				return formatedString;
			}
	    } else {
			throw new myException("MyMetaFieldDEF.get_object: unidentified type!");
		}
	}

	
	

	/**
	 * 2018-03-13: erzeugt ein raw-object aus einem formated-string
	 * @param formatedString
	 * @return
	 * @throws myException
	 */
	public ParamDataObject getParamDataObject(Object ob) throws myException {

		if (this.get_bIsNumberTypeWithOutDecimals())   {
			if (ob==null) {
				return new Param_Long((Long)null);
			} else {
				if (ob instanceof Long) {
					return new Param_Long((Long)ob);
				} else {
					throw new myException(this,"Error creating raw Long-Object !");
				}
			}
		} else if (this.get_bIsNumberTypeWithDecimals())   {
			if (ob==null) {
				return new Param_BigDecimal((BigDecimal)null);
			} else {
				if (ob instanceof BigDecimal) {
					return new Param_BigDecimal((BigDecimal)ob);
				} else {
					throw new myException(this,"Error creating raw BigDecimal-Object !");
				}
			}
        }
		else if (DB_META.vDB_DATE_TYPES.contains(this.get_FieldType()))   {
			if (ob==null) {
				return new Param_Date((Date)null);
			} else {
				if (ob instanceof Date) {
					return new Param_Date((Date)ob);
				} else {
					throw new myException(this,"Error creating raw Date-Object !");
				}
			}
 	    }
		else if (DB_META.vDB_TEXT_TYPES.contains(this.get_FieldType()))  {
			if (ob==null) {
				return new Param_String("",(String)null);
			} else {
				if (ob instanceof String) {
					return new Param_String("",(String)ob);
				} else {
					throw new myException(this,"Error creating raw String-Object !");
				}
			}
	    } else {
			throw new myException(this,"MyMetaFieldDEF.get_object: unidentified type!");
		}
	}


	
	/**
	 * 2018-03-27: uebersetzt die metadef in ein DATATYP-enum
	 * @param formatedString
	 * @return
	 * @throws myException
	 */
	public DATATYPES getDATATYPE() throws myException {

		if (this.get_bIsNumberTypeWithOutDecimals())   {
			return DATATYPES.NUMBER;
		} else if (this.get_bIsNumberTypeWithDecimals())   {
			return DATATYPES.DECIMALNUMBER;
        }
		else if (DB_META.vDB_DATE_TYPES.contains(this.get_FieldType()))   {
			return DATATYPES.DATE;
 	    }
		else if (DB_META.vDB_TEXT_TYPES.contains(this.get_FieldType()))  {
			if (this.iKEY_FIELDTEXTLENGTH==1) {
				return DATATYPES.YESNO;
			} else {
				return DATATYPES.TEXT;
			}
		}
		throw new myException(this,"MyMetaFieldDEF.get_object: unidentified type!");
	}

	
}
