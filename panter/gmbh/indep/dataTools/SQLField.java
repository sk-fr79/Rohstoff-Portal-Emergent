package panter.gmbh.indep.dataTools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.enumtools.IF_enum_4_fielddefinition;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionDataValueNotFittingToField;


public class SQLField 
{
	/*
	 * es existieren 2 auspraegeungen von SQLField:
	 * die erste ist ein abfrage-feld, kann nicht geschrieben werden,
	 * das zweite ist ein standard-feld, kann inserted und updated werden
	 */
	private boolean 	bWriteable = false;
	
	
	
	/*
	 * ein weiterer schalter sorgt dafuer, dass felder, die geschrieben werden koennen
	 * in der maske gesperrt werden, d.h. nicht geoffnet werden koennen innerhalb von masken.
	 * Dies gilt insbesondere fuer die Typen:
	 * SQLFieldForConnectToMother
	 * SQLFieldForPrimaryKEY
	 * SQLFieldForRestrictTableRange
	 */
	private boolean 	bFieldCanBeWrittenInMask = true;
	
	
	private String 		cTableName 			= 	null;   // bleibt bei reinen abfrage-feldern null
	private String 		cFieldAusdruck 		= 	null;
	private String 		cFieldName			= 	null;
	private String 		cFieldLabel			= 	null;
	private MyString 	cFieldLabelForUser	= 	null;
	
	private String		cNewValueFormated			= 	null;
	
	private boolean		bIsNullableByUserDef= true;
	
	/*
	 * es ist noetig, ein feld zu initialisieren, d.h. aus einer leeren abfrage eine
	 * myFieldMetaInformation des feldes zu bekommen. vorher geht nichts.
	 */
	private MyMetaFieldDEF oFieldMetaDef = null;

	
//	private HttpSession 	oSES	= null;
	
	/*
	 * ein vector fuer die eingabevalidierer
	 */
	private Vector<XX_FieldNewValueValidator>	vValidInput	= new Vector<XX_FieldNewValueValidator>();
	
	
	private String 			cDefaultValueFormated = null;
	
		
	
	private SQLFieldMAP    		oFieldMapThisBelongsTo = null;
	
	
	/**
	 * @param ctablename
	 * @param cfieldname
	 * @param cfieldlabel
	 * @param cfieldLabelForUser
	 * @param bisNullable
	 * @param oses
	 * @throws myException
	 * Erzeugt ein SQLField vom Typ Writeable

	 */
	public SQLField(	String 		ctablename, 
						String 		cfieldname,
						String 		cfieldlabel,
						MyString 	cfieldLabelForUser,
						boolean		bisNullable,
						HttpSession oses) throws myException
	{
		super();
		this.bWriteable = true;
		
		this.__defineField(ctablename, 
							ctablename+"."+cfieldname, 
							cfieldname,
							bibALL.isEmpty(cfieldlabel) ? cfieldname:cfieldlabel,
							cfieldLabelForUser,
							bisNullable,
							oses
							);
		
	}
	
	
	/**
	 * @param fieldAusdruck
	 * @param cfieldlabel
	 * @param cfieldlabelforUser
	 * @param oses
	 * @throws myException
	 * 
	 * Erzeugt ein reines abfrage-feld
	 */
	public SQLField(	String 		fieldAusdruck, 
						String 		cfieldlabel,
						MyString 	cfieldlabelforUser,
						HttpSession oses)  throws myException
	{
		super();
		this.__defineField(null, 
							fieldAusdruck, 
							null,
							cfieldlabel,
							cfieldlabelforUser,
							false,
							oses
							);
	}


	
	/**
	 * @param fieldAusdruck
	 * @param cfieldlabel
	 * @param cfieldlabelforUser
	 * @param oses
	 * @throws myException
	 * 
	 * Erzeugt ein reines abfrage-feld
	 */
	public SQLField(	String 		fieldAusdruck, 
						String 		cfieldlabel,
						MyString 	cfieldlabelforUser)  throws myException
	{
		super();
		this.__defineField(null, 
							fieldAusdruck, 
							null,
							cfieldlabel,
							cfieldlabelforUser,
							false,
							null
							);
	}


	
	/**
	 * 2015-08-24: neuer Konstruktor fuer die definition der felder aus enums
	 * @param def
	 * @throws myException
	 */
	public SQLField(IF_FieldDefs_4_SQLFieldMAP def) throws myException {
		this(def.fieldDef(),def.fieldAlias(),def.userText());
	}
	
	
	/**
	 * 2016-09-27: neuer Konstruktor fuer die definition der felder aus enums
	 * @param def
	 * @throws myException
	 */
	public SQLField(IF_enum_4_fielddefinition def) throws myException {
		this(def.querydef(),def.alias(),new MyE2_String(def.user_text_lang(),false));
	}
	
	
	
	
	/**
	 * @param ctablename
	 * @param fieldAusdruck
	 * @param cfieldname
	 * @param cfieldlabel
	 * @param cfieldlabelforUser
	 * @param bisNullableByUserDef
	 * @param oses
	 * @throws myException
	 */
	private void __defineField(String 		ctablename, 
								String 		fieldAusdruck, 
								String 		cfieldname,
								String 		cfieldlabel,
								MyString 	cfieldlabelforUser,
								boolean		bisNullableByUserDef,
								HttpSession oses) throws myException
	{
		
		
		
		if (bibALL.isEmpty(fieldAusdruck))
			throw new myException("SQLField:not complete definition !!");
		
		if (ctablename != null)
			this.cTableName 	= 		ctablename.toUpperCase();
		
		this.cFieldAusdruck = 		fieldAusdruck;
		if (cfieldname != null)
			this.cFieldName = 			cfieldname.toUpperCase();
		
		this.cFieldLabel = 			cfieldlabel;
		this.cFieldLabelForUser=	cfieldlabelforUser;
		if (this.cFieldLabelForUser == null)
			this.cFieldLabelForUser = new MyString(this.cFieldLabel);
		
		this.bIsNullableByUserDef =			bisNullableByUserDef;
//		this.oSES	= 				oses;
		

		/*
		 * ein input-validator wird immer rangezogen
		 */
		if (this.bWriteable)
			this.vValidInput.add(new MyFieldStandardValidator(this));
		
		
	}
	
	
	
	public String get_cFieldName() 		{		return this.cFieldName;	}
	public String get_cFieldLabel() 		{		return this.cFieldLabel;	}
	public String get_cFieldAusdruck()		{		return this.cFieldAusdruck;	}
	public String get_cTableName()			{		return this.cTableName;	}
	


	
	
	/**
	 * @return query-segment for select-block
	 */
	public String getCompleteSelectBlock()
	{
		return this.cFieldAusdruck+" AS "+this.cFieldLabel;
	}

	


	
	public MyResultValue get_oResultValue(ResultSet oRs) throws myException
	{
		if (this.oFieldMetaDef == null)
			throw new myException("SQLField:get_oResultValue:this field is not initialized/has no metadef-infos!!");
		
		// *************************************************
		// position im resultset finden (Zaehlweise ab 0)
	  	int iPosInQuery = -1;
	  	try
    	{
    		// Manfred: 2014-04-01 : spart die Schleife...
    		iPosInQuery = oRs.findColumn(this.get_cFieldLabel()) - 1;
    		
    		
//    		for (int i=0;i<oRs.getMetaData().getColumnCount();i++)
//	    	{
//	    		
//	    		if (oRs.getMetaData().getColumnLabel(i+1).equals(this.get_cFieldLabel()))
//	    		{
//	    			iPosInQuery = i;
//	    			break;
//	    		}
//	    	}
    		
    	}
    	catch (SQLException ex)
    	{
    		throw new myException(this,"__ResultString:SQL-Error:"+ex.getMessage());
    	}
       	catch (Exception ex)
    	{
    		throw new myException(this,"__ResultString:Error:"+ex.getMessage());
    	}
       	if (iPosInQuery==-1)
       		throw new myException(this,"__ResultString:Row:"+this.get_cFieldLabel()+" not found !!");
		// *************************************************

       	
       	MyResultValue oRueck;
		try 
		{
			oRueck = new MyResultValue(new MyMetaFieldDEF(oRs,iPosInQuery, null),oRs,true);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new myException(this,"Error Creating MyResultField: SQLError:"+e.getLocalizedMessage());
		}
       	return oRueck;
	}
	

	
	
	public String get_cInsertFieldPart() throws myException
	{
		if (this.oFieldMetaDef == null)
			throw new myException("SQLField:get_cInsertBlock:this field is not initialized/has no metadef-infos!!");
		
		if (! this.bWriteable)
			throw new myException("SQLField:get_cInsertBlock:this field is not an insert-field: "+this.cFieldName);
		
		return this.cFieldName;
		
	}
	
	
	
	public String get_cInsertValuePart() throws myException
	{
		if (this.oFieldMetaDef == null)
			throw new myException("SQLField:get_cInsertBlock:this field is not initialized/has no metadef-infos!!");
		
		if (! this.bWriteable)
			throw new myException("SQLField:get_cInsertBlock:this field is not an insert-field: "+this.cFieldName);
		
		return this.oFieldMetaDef.get_cStringForDataBase(this.cNewValueFormated, true, false);
	}
	
	
	
	public String get_cUpdateSequence() throws myException
	{
		return this.get_cInsertFieldPart()+"="+this.get_cInsertValuePart();		
	}
	
	

	/**
	 * @return emtpy vector, when all is ok,
	 * otherwise vector of myStrings with errormessages
	 */
	public MyE2_MessageVector get_vCheckNewValue(String cnewValueFormated)
	{
		
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		
		for (int i=0;i<this.vValidInput.size();i++)
		{
			XX_FieldNewValueValidator oValid = (XX_FieldNewValueValidator)this.vValidInput.get(i);
			if (!oValid.doValidate(cnewValueFormated))
			{
				vRueck.add_MESSAGE(new MyE2_Message(oValid.get_oErrorStringForUser(),MyE2_Message.TYP_ALARM,false), false);
			}
				
		}
		
		return vRueck;
	}
	

	public MyMetaFieldDEF get_oFieldMetaDef()								{		return oFieldMetaDef;	}
	public String get_cNewValueFormated()									{		return cNewValueFormated;	}
	public MyString get_cFieldLabelForUser()								{		return cFieldLabelForUser;	}
	public void set_cFieldLabelForUser(MyString fieldLabelForUser)			{		cFieldLabelForUser = fieldLabelForUser;	}
//	public HttpSession get_oSES()											{		return oSES;	}
	public String get_cDefaultValueFormated()								{		return cDefaultValueFormated;	}
	public boolean get_bWriteable()											{		return bWriteable;	}
	public Vector<XX_FieldNewValueValidator> 	get_vValidInput()			{		return vValidInput; }
	
	public void set_bWriteable(boolean bwriteable)	 throws myException						
	{		
		if (this.cTableName == null && bwriteable)
			throw new myException("SQLField:set_bWriteable:A query-field cannot set writeable !");
		
		this.bWriteable = bwriteable;
	
	}


	
	public void set_oFieldMetaDef(MyMetaFieldDEF fieldMetaDef) throws myException	
	{		
		oFieldMetaDef = fieldMetaDef;
		this.oFieldMetaDef.set_bIsNullableBasic(this.bIsNullableByUserDef);
		if (fieldMetaDef == null)
			throw new myException("SQLField:set_oFieldMetaDef:Null not allowed !!");
		
	}

	

	
	public void set_cNewValueFormated(String newValueFormated) throws myException			
	{
		if (!this.bWriteable)
			throw new myException("SQLField:set_cNewValueFormated:Field "+this.cFieldLabel+" not writable !");

		cNewValueFormated = newValueFormated;	
	}

	
	public void set_cDefaultValueFormated(String defaultValue) throws myException						
	{		
		if (!this.bWriteable)
			throw new myException("SQLField:set_cDefaultValue:Field "+this.cFieldLabel+" not writable !");

		cDefaultValueFormated = defaultValue;	
	}


	
	/**
	 * @param cTestString
	 * @return unformated String for use in SQL-statemtents (i.E. 'test', 1000 usw)
	 * @throws myException
	 */
	public String get_cValueString_For_DB(String cTestString) throws myException
	{
		String cRueck = null;
		try
		{
			cRueck = this.oFieldMetaDef.get_cStringForDataBase(cTestString, true, false);
		}
		catch (myExceptionDataValueNotFittingToField ex)
		{
			throw new myException("SQLField:get_cValueString_For_DB:TestString is not Correkt for this field:"+ex.get_ErrorMessage().get_cMessage().COrig());
		}
		
		return cRueck;
	}


	
	
	/**
	 * @param cTestString
	 * @return unformated String for internal use (i.E. test, 1000.00, 2005-12-31 usw)
	 * @throws myException
	 */
	public String get_cUnformated_ValueString_For_InternalUse(String cTestString, boolean bCheckWetherNullIsAllowed) throws myException
	{
		String cRueck = null;
		try
		{
			cRueck = this.oFieldMetaDef.get_cStringForDataBase(cTestString,bCheckWetherNullIsAllowed, false);
			if (cRueck.equals("NULL"))
				cRueck="";
			else if (cRueck.startsWith("'"))
				cRueck = cRueck.substring(1);
			
			if (cRueck.endsWith("'"))
				cRueck = cRueck.substring(0,cRueck.length()-1);
		
			return cRueck;
		}
		catch (myExceptionDataValueNotFittingToField ex)
		{
			throw new myException("SQLField:get_cUnformated_ValueString_For_InternalUse:TestString is not Correkt for this field:"+ex.get_ErrorMessage().get_cMessage().COrig());
		}
		
	}

	
	
	
	
	
	/**
	 * @param bIsNullable überstimmt evtl. nullable felder in der datenbank-definition
	 */
	public void set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(boolean bIsNullable)
	{
		this.bIsNullableByUserDef = bIsNullable;
	}
	
	
	public boolean get_bIsNullableByUserDef_Before_INIT_SQLFieldMAP()
	{
		return bIsNullableByUserDef;
	}
	
	
	

	/**
	 * 2011-05-02
	 * @param bIsNullable: damit kann eine nullablabe einstellung auch nach intialisierung der sqlfieldmap noch in die metadef geschrieben werden 
	 */
	public void set_bIsNullableByUserDef_AFTER_INIT_SQLFieldMAP(boolean bIsNullable)
	{
		this.bIsNullableByUserDef = this.bIsNullableByUserDef && bIsNullable;
		this.oFieldMetaDef.set_bIsNullableBasic(bIsNullable);
	}
	


	
	/**
	 * @param set_bIsNullableByUserDef_ForInteractiveModules überstimmt evtl. nullable felder in der datenbank-definition,
	 * fuer die benutzung in MAPSettings usw. (nach SQLFieldMAP - Init !)
	 */
	public void set_bIsNullableByUserDef_ForInteractiveModules(boolean bIsNullable)
	{
		this.oFieldMetaDef.set_bIsNullableInteractiv(bIsNullable);
	}
	
	
	


	public boolean get_bFieldCanBeWrittenInMask()
	{
		return bFieldCanBeWrittenInMask;
	}


	public void set_bFieldCanBeWrittenInMask(boolean fieldCanBeWrittenInMask)
	{
		bFieldCanBeWrittenInMask = fieldCanBeWrittenInMask;
	}


	public SQLFieldMAP get_oFieldMapThisBelongsTo() 
	{
		return oFieldMapThisBelongsTo;
	}

	public void set_oFieldMapThisBelongsTo(SQLFieldMAP fieldMapThisBelongsTo) 
	{
		oFieldMapThisBelongsTo = fieldMapThisBelongsTo;
	}
	

}
