package panter.gmbh.indep.dataTools;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

import javax.sql.rowset.CachedRowSet;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionDataValueNotFittingToField;
import panter.gmbh.indep.maggie.TestingDate;


/**
 * speichert innerhalb einer query formatierte und unformatierte datenbankwerte 
 * @author martin
 *
 */
public class MyResultValue 
{
	private String 			cFieldValueUnformated 	= null;
	private String 			cFieldValueFormated		= null;
	
	private String 			cDateTimeValueFormated	= null;     // enthalt bei textfeldern die Datum - Zeit - Kombi in "DD.MM.YYYY HH24:MI:SS" 
	private String 			cDateValueFormated	= 	  null;     // enthalt bei textfeldern die Datum - Zeit - Kombi in "DD.MM.YYYY"
	
	private Double 			doubleValue = null;
	private BigDecimal      bdValue = null;
	
	private Long 			longValue = null;
	private Date            dateValue = null;
	
	//neu, handling von exacten datumswerten
	private Date            timeStampValue = null;
	
	private boolean      	bIsDateType = false;

	private String  		cHASH_LABEL = null;
	private String    		cFieldType = null;
	
	private MyMetaFieldDEF  MetaFieldDef = null;
	
	
	/*
	 * das object baut gleich wieder einen string auf, der in ein datenbank-statement eingesetzt werden koennte
	 * dieser wert kann auch durch einen neuen ueberschrieben werden (mit pruefung) und dann in sql-statements genutzt werden
	 */
	private String          c_actual_formated_string_for_save = null;     //das formatierte uebergeben Datenbankfeld 2015-02-27
	private String   		cFieldValue_ForDatabase = null;
	private boolean   		bDatabaseValue_Was_Set_from_outside = false;
	
    //2015-02-17: feld speichert den wert fieldValue4database bei erstellen des resultsetValue-objekts. damit kann ein vergleich erzeugt
	//            werden, ob ein evtl. neu zugewiesener wert ueberhaupt unterschiedlich ist
	private String   		cFieldValue_ForDatabaseOnCreationTime = null;
	
//	//2013-07-17: status-feld, nimmt bei zuweisungen den letzten fehlerstatus auf
//	private Integer        iLastAssignmentError = null;
	

	//2014-10-24: neues Feld Rueckgabe als Object
	private Object	   		o_NativeDataObject = null;

	


	/**
	 * @param MetaColumnDefinition
	 * @param oRS
	 * @param WertWennNull
	 * @throws myException
	 */
	public MyResultValue(MyMetaFieldDEF oMetaColumnDefinition, ResultSet oRS, boolean bLeerStringWennNull) throws myException
	{
		super();

		this.MetaFieldDef = oMetaColumnDefinition;
		
		//this.cWertWennNull = WertWennNull;

		this.bIsDateType  = oMetaColumnDefinition.get_bIsDateType();
		this.cFieldType = oMetaColumnDefinition.get_FieldType();
		
		try
		{
			this.cHASH_LABEL = oMetaColumnDefinition.get_FieldLabel();
			
			this.cFieldValueUnformated  = oRS.getString(oMetaColumnDefinition.get_iColumnNumberForResultSet());
			
			//2014-10-24
			if (cFieldType.equals(MyMetaFieldDEF.DBTYPE.LONG.toString())){
				this.o_NativeDataObject  = this.cFieldValueUnformated;
			} else {
				this.o_NativeDataObject = oRS.getObject(oMetaColumnDefinition.get_iColumnNumberForResultSet());
			}
			
			
			/*
			 * !!!!!! G E F A H R   !!  die tatsache, dass eine nachkommastelle vorhanden ist, wird anhand der meta-daten getScale() festgestellt.
			 *                          dieser Wert wird aber nur bei nicht mittels funktionen erzeugten abfrage-spalten korrekt uebermittelt. 
			 *                          Bereits ein NVL(0) kann einen falschen wert liefern und damit dem modul vorgaukeln, es waere eine ganze zahl.
			 *                          Deshalb wird hier dieser fall der unbestimmten werte ueber eine analyse des des unformatierten wertes gemacht
			 */
			
	        if (oMetaColumnDefinition.get_bIsNumberTypeWithDecimals())
			{
	            double dRueck = oRS.getDouble(oMetaColumnDefinition.get_iColumnNumberForResultSet());
	
	            if (!oRS.wasNull())
	            {
	            	this.cFieldValueFormated = MyResultValue.formatDez(dRueck, oMetaColumnDefinition.get_FieldDecimals(), true);
	            	this.doubleValue = new Double(dRueck);
	            	this.bdValue = oRS.getBigDecimal(oMetaColumnDefinition.get_iColumnNumberForResultSet());
	            }
	
			}
			else if (oMetaColumnDefinition.get_bIsNumberTypeWithOutDecimals())
			{

	            long lRueck = oRS.getLong(oMetaColumnDefinition.get_iColumnNumberForResultSet());
	            double dRueck = oRS.getDouble(oMetaColumnDefinition.get_iColumnNumberForResultSet());

	            if (!oRS.wasNull())
	            {
					if (this.cFieldValueUnformated.indexOf(".")>=0)        // dann wurde der wert faelschlicherweise als ganze zahl gemeldet
					{
						int iLaenge = this.cFieldValueUnformated.length();
						int iPosPoint = this.cFieldValueUnformated.indexOf(".");
						int iNachKomma = iLaenge-iPosPoint-1;
		            	this.cFieldValueFormated = MyResultValue.formatDez(dRueck, iNachKomma, true);
		            	this.doubleValue = new Double(dRueck);
		            	this.bdValue = oRS.getBigDecimal(oMetaColumnDefinition.get_iColumnNumberForResultSet());
					}
					else
					{
		            	this.cFieldValueFormated = MyResultValue.formatDez(lRueck, true);
		            	this.longValue = new Long(lRueck);
		            	this.doubleValue = new Double(dRueck);
		            	this.bdValue = oRS.getBigDecimal(oMetaColumnDefinition.get_iColumnNumberForResultSet());
					}
	            }
			}
			else if (oMetaColumnDefinition.get_bIsDateType())
			{

	            Date dateWert = oRS.getDate(oMetaColumnDefinition.get_iColumnNumberForResultSet());
	            Date timeStampWert=  oRS.getTimestamp(oMetaColumnDefinition.get_iColumnNumberForResultSet());
	           
	
	            // normalerweise kommen datumswerte unformatiert mit zeitwerten (1900-01-01 00:00:00.0)
	            // deshalb werden diese hier gekuertzt
	            
	            if (!oRS.wasNull())
	            {
		            if (this.cFieldValueUnformated.length()>10)
		            	this.cFieldValueUnformated = this.cFieldValueUnformated.substring(0,10);

	            	
	            	this.cFieldValueFormated = MyResultValue.formatDateNormal(dateWert);
	            	//2014-11-27: fehlerkorrektur: fuer den kompletten datumsstring muss der timestamp-wert uebergeben werden nicht der dateWert 
	            	this.cDateTimeValueFormated = MyResultValue.formatDateTimeNormal(/*dateWert*/timeStampWert);
	            	this.cDateValueFormated = MyResultValue.formatDateNormal(dateWert);
	            	this.dateValue = dateWert;
	            	this.timeStampValue = timeStampWert;
	            }
			}
			else if (oMetaColumnDefinition.get_bIsTextType())
			{

	            if (!oRS.wasNull())
	            {
	            	this.cFieldValueFormated = this.cFieldValueUnformated ;
	            }
			}
			else
			{
				throw new myException(this.getClass().getName()+": Undefined FieldType: "+":"+oMetaColumnDefinition.get_FieldName()+":"+oMetaColumnDefinition.get_FieldType()+":"+oMetaColumnDefinition.get_FieldNumberLENGTH()+":"+oMetaColumnDefinition.get_FieldDecimals());
			}
		}
		catch (SQLException exx)
		{
			exx.printStackTrace();
			throw new myException(this.getClass().getName()+":SQLEXCEPTION: "+exx.getLocalizedMessage());
		}
		
		
		if (bLeerStringWennNull)
		{
            if (this.cFieldValueUnformated==null) this.cFieldValueUnformated="";
            if (this.cFieldValueFormated==null) this.cFieldValueFormated="";
            if (this.cDateTimeValueFormated==null) this.cDateTimeValueFormated="";
            if (this.cDateValueFormated==null) this.cDateValueFormated="";
		}
		
		this.cFieldValue_ForDatabase = this.makeVALUE_FOR_SQLSTATEMENT();
		this.c_actual_formated_string_for_save=this.cFieldValueFormated;          //das formatierte uebergebene Datenbankfeld 27.02.2015
		
		//aufbau des eingelesenen feldes wie wenn es direkt wieder gespeichert wuerde
		try {
			this.cFieldValue_ForDatabaseOnCreationTime = this.MetaFieldDef.get_cStringForDataBase(this.cFieldValueFormated,true, false);
		}catch(Exception ex){
			this.cFieldValue_ForDatabaseOnCreationTime=null;
		}
		
	}
	
	
	
	

//	/**
//	 * ResultValue aus einem CachedRowSet (anstatt aus einem Recordset-Objekt)
//	 * @author manfred
//	 * @date   13.04.2012
//	 * @param oMetaColumnDefinition
//	 * @param oCRS
//	 * @param bLeerStringWennNull
//	 * @throws myException
//	 */
//	public MyResultValue(MyMetaFieldDEF oMetaColumnDefinition, CachedRowSet oCRS, boolean bLeerStringWennNull) throws myException
//	{
//		super();
//
//		this.MetaFieldDef = oMetaColumnDefinition;
//		
//		//this.cWertWennNull = WertWennNull;
//
//		this.bIsDateType  = oMetaColumnDefinition.get_bIsDateType();
//		this.cFieldType = oMetaColumnDefinition.get_FieldType();
//		
//		try
//		{
//			this.cHASH_LABEL = oMetaColumnDefinition.get_FieldLabel();
//			
//			this.cFieldValueUnformated  = oCRS.getString(oMetaColumnDefinition.get_iColumnNumberForResultSet());
//
//			//2014-10-24
//			this.o_NativeDataObject = oCRS.getObject(oMetaColumnDefinition.get_iColumnNumberForResultSet());
//
//			
//			/*
//			 * !!!!!! G E F A H R   !!  die tatsache, dass eine nachkommastelle vorhanden ist, wird anhand der meta-daten getScale() festgestellt.
//			 *                          dieser Wert wird aber nur bei nicht mittels funktionen erzeugten abfrage-spalten korrekt uebermittelt. 
//			 *                          Bereits ein NVL(0) kann einen falschen wert liefern und damit dem modul vorgaukeln, es waere eine ganze zahl.
//			 *                          Deshalb wird hier dieser fall der unbestimmten werte ueber eine analyse des des unformatierten wertes gemacht
//			 */
//			
//	        if (oMetaColumnDefinition.get_bIsNumberTypeWithDecimals())
//			{
//	            double dRueck = oCRS.getDouble(oMetaColumnDefinition.get_iColumnNumberForResultSet());
//	
//	            if (!oCRS.wasNull())
//	            {
//	            	this.cFieldValueFormated = MyResultValue.formatDez(dRueck, oMetaColumnDefinition.get_FieldDecimals(), true);
//	            	this.doubleValue = new Double(dRueck);
//	            	this.bdValue = oCRS.getBigDecimal(oMetaColumnDefinition.get_iColumnNumberForResultSet());
//	            }
//	
//			}
//			else if (oMetaColumnDefinition.get_bIsNumberTypeWithOutDecimals())
//			{
//	            long lRueck = oCRS.getLong(oMetaColumnDefinition.get_iColumnNumberForResultSet());
//	            double dRueck = oCRS.getDouble(oMetaColumnDefinition.get_iColumnNumberForResultSet());
//
//	            if (!oCRS.wasNull())
//	            {
//					if (this.cFieldValueUnformated.indexOf(".")>=0)        // dann wurde der wert faelschlicherweise als ganze zahl gemeldet
//					{
//						int iLaenge = this.cFieldValueUnformated.length();
//						int iPosPoint = this.cFieldValueUnformated.indexOf(".");
//						int iNachKomma = iLaenge-iPosPoint-1;
//		            	this.cFieldValueFormated = MyResultValue.formatDez(dRueck, iNachKomma, true);
//		            	this.doubleValue = new Double(dRueck);
//		            	this.bdValue = oCRS.getBigDecimal(oMetaColumnDefinition.get_iColumnNumberForResultSet());
//					}
//					else
//					{
//		            	this.cFieldValueFormated = MyResultValue.formatDez(lRueck, true);
//		            	this.longValue = new Long(lRueck);
//		            	this.doubleValue = new Double(dRueck);
//		            	this.bdValue = oCRS.getBigDecimal(oMetaColumnDefinition.get_iColumnNumberForResultSet());
//					}
//	            }
//			}
//			else if (oMetaColumnDefinition.get_bIsDateType())
//			{
//				
////	            Timestamp dateWert = null;
////	            dateWert  = oCRS.getTimestamp(oMetaColumnDefinition.get_iColumnNumberForResultSet());
////	            
////	            
////	
////	            // normalerweise kommen datumswerte unformatiert mit zeitwerten (1900-01-01 00:00:00.0)
////	            // deshalb werden diese hier gekuertzt
////	            
////	            if (!oCRS.wasNull())
////	            {
////		            if (this.cFieldValueUnformated.length()>10)
////		            	this.cFieldValueUnformated = this.cFieldValueUnformated.substring(0,10);
////
////	            	
////	            	this.cFieldValueFormated = MyResultValue.formatDateNormal(dateWert);
////	            	this.cDateTimeValueFormated = MyResultValue.formatDateTimeNormal(dateWert);
////	            	this.cDateValueFormated = MyResultValue.formatDateNormal(dateWert);
////	            	this.dateValue = dateWert;
////	            }
//				// debug
//	            this.cFieldValueUnformated="";
//	            this.cFieldValueFormated="";
//	            this.cDateTimeValueFormated="";
//	            this.cDateValueFormated="";
//				
//			}
//			else if (oMetaColumnDefinition.get_bIsTextType())
//			{
//	            if (!oCRS.wasNull())
//	            {
//	            	this.cFieldValueFormated = this.cFieldValueUnformated ;
//	            }
//			}
//			else
//			{
//				throw new myException(this.getClass().getName()+": Undefined FieldType: "+":"+oMetaColumnDefinition.get_FieldName()+":"+oMetaColumnDefinition.get_FieldType()+":"+oMetaColumnDefinition.get_FieldNumberLENGTH()+":"+oMetaColumnDefinition.get_FieldDecimals());
//			}
//		}
//		catch (SQLException exx)
//		{
//			exx.printStackTrace();
//			throw new myException(this.getClass().getName()+":SQLEXCEPTION: "+exx.getLocalizedMessage());
//		}
//		
//		
//		if (bLeerStringWennNull)
//		{
//            if (this.cFieldValueUnformated==null) this.cFieldValueUnformated="";
//            if (this.cFieldValueFormated==null) this.cFieldValueFormated="";
//            if (this.cDateTimeValueFormated==null) this.cDateTimeValueFormated="";
//            if (this.cDateValueFormated==null) this.cDateValueFormated="";
//		}
//		
//		this.cFieldValue_ForDatabase = this.makeVALUE_FOR_SQLSTATEMENT();
//		this.c_actual_formated_string_for_save=this.cFieldValueFormated;    //das formatierte uebergebene Datenbankfeld 27.02.2015
//
//		
//		//aufbau des eingelesenen feldes wie wenn es direkt wieder gespeichert wuerde
//		try {
//			this.cFieldValue_ForDatabaseOnCreationTime = this.MetaFieldDef.get_cStringForDataBase(this.cFieldValueFormated,true, false);
//		}catch(Exception ex){
//			this.cFieldValue_ForDatabaseOnCreationTime=null;
//		}
//
//		
//	}

	
	
	
	
//	public void set_NotNull()
//	{
//		if (this.cFieldValueFormated == null) 			this.cFieldValueFormated="";
//		if (this.cFieldValueUnformated == null) 		this.cFieldValueUnformated="";
//		if (this.cDateTimeValueFormated == null) 		this.cDateTimeValueFormated="";
//		if (this.cDateValueFormated == null) 			this.cDateValueFormated="";
//	}
	
    
	public String get_cDateTimeValueFormated() throws myException
	{
		if (!this.bIsDateType)
			throw new myException(this,":only allowed in Date-fields!");
			
		return cDateTimeValueFormated;
	}
	
	
	public String get_cDateValueFormated() throws myException
	{
		if (!this.bIsDateType)
			throw new myException(this,":only allowed in Date-fields!");
			
		return cDateValueFormated;
	}


	public String get_FieldValueFormated() 
	{
		return cFieldValueFormated;
	}

	public String get_FieldValueUnformated() {
		return cFieldValueUnformated;
	}


	public Date getDateValue() {
		return dateValue;
	}

	public Date getTimeStampValue()
	{
		return timeStampValue;
	}
	

	public Double getDoubleValue() {
		return doubleValue;
	}


	public BigDecimal  getBigDecimalValue()
	{
		return bdValue;
	}
	
	
	public Long getLongValue() {
		return longValue;
	}

	
	
	   // variante mit tausender-punkt
    public static String formatDez_old(double dZahl, int iAnzahlDez, boolean bTausender)
    {
        String cFormat = "";
        String cHelp = "00000000000000000000000000000000000000";
        String cRueck = "" + dZahl;
        DecimalFormat df;

        if (iAnzahlDez > 0)
        {
            if (bTausender)
            {
                cFormat = "#,###,##0." + cHelp.substring(0, iAnzahlDez);
            }
            else
            {
                cFormat = "#0." + cHelp.substring(0, iAnzahlDez);
            }
        }
        else
        {
            if (bTausender)
            {
                cFormat = "#,###,##0";
            }
            else
            {
                cFormat = "#0";
            }
        }

        df = new DecimalFormat(cFormat);

        cRueck = df.format(dZahl);

        return (cRueck);
    }

    // variante mit tausender-punkt
    public static String formatDez(double dZahl, int iAnzahlDez, boolean bTausender)
    {
    	StringBuilder sb = new StringBuilder();
    	
		if (bTausender)
        {
            sb.append( "#,###,##0");
        }
        else
        {
            sb.append("#0") ;
        }
		
		if (iAnzahlDez > 0)
        {
			sb.append(".");
			sb.append(org.apache.commons.lang.StringUtils.repeat("0", iAnzahlDez));
        }
		
		DecimalFormat df = new DecimalFormat(sb.toString());

		return (df.format(dZahl));
    	        
    }

    
    

    // variante mit tausender-punkt (long integer)
    public static String formatDez(long dZahl, boolean bTausender)
    {
        String cFormat = "";
        String cRueck = "" + dZahl;
        DecimalFormat df;

        if (bTausender)
        {
            cFormat = "#,###,##0";
        }
        else
        {
            cFormat = "#0";
        }

        df = new DecimalFormat(cFormat);

        cRueck = df.format(dZahl);

        return (cRueck);
    }


    // mittelformat: "31.12.2001"
    public static String formatDateNormal(java.util.Date dDate)
    {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        cRueck = df.format(dDate);

        return (cRueck);
    }


    // mittelformat: "31.12.2001"
    public static String formatDateTimeNormal(java.util.Date dDate)
    {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        DateFormat tf = DateFormat.getTimeInstance(DateFormat.MEDIUM);
        cRueck = df.format(dDate) + " " + tf.format(dDate);

        return (cRueck);
    }


	public String get_cFieldLABEL()
	{
		return cHASH_LABEL;
	}


	
	private String makeVALUE_FOR_SQLSTATEMENT() throws myException
	{
		//den string fuer ein Datenbank-Statement erzeugen
		String cRueck = "NULL";
		if (S.isFull(this.cFieldValueUnformated))
		{	
			if (DB_META.vDB_NUMBER_TYPES.contains(this.cFieldType))
			{
				cRueck  = this.cFieldValueUnformated;
		    }
			else if (DB_META.vDB_DATE_TYPES.contains(this.cFieldType))
			{
				TestingDate oTD = new TestingDate(this.get_cDateValueFormated());
				
				if (!oTD.testing())
					throw new myException(this,"get_cVALUE_FOR_SQLSTATEMENT:get_ValueStringForSQLStatement:"+this.cHASH_LABEL+" is bad Dateformat!");
				
				cRueck = oTD.get_ISO_DateFormat(true);
			}
			else if (DB_META.vDB_TEXT_TYPES.contains(this.cFieldType))
			{
				cRueck = bibALL.MakeSql(this.cFieldValueUnformated);
			}
		}
		return cRueck;
	}

	
	
	public String get_cVALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.cFieldValue_ForDatabase;
	}
	


	
	public MyMetaFieldDEF get_MetaFieldDef()
	{
		return MetaFieldDef;
	}

	/**
	 * 
	 * @param cNewValueFormated
	 * @return
	 */
	public MyE2_MessageVector set_NewValueForDatabase(String cNewValueFormated)
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
//		//2013-07-17: assignmentstatus resetten
//		this.iLastAssignmentError = null;
		
		try
		{
			this.c_actual_formated_string_for_save=cNewValueFormated;  //das formatierte uebergebene Datenbankfeld 2015-02-27
			this.cFieldValue_ForDatabase = this.MetaFieldDef.get_cStringForDataBase(cNewValueFormated, true,false);
			this.bDatabaseValue_Was_Set_from_outside = true;
			
		}
		catch (myExceptionDataValueNotFittingToField ex)
		{
			//2015-02-24: vereinheitlichung der fehlergenerierung
			oMV.add(ex.get_Message(this.MetaFieldDef.get_FieldName(), cNewValueFormated));

//			if (ex.get_iErrorType() == myExceptionDataValueNotFittingToField.VALUE_IS_TOO_LONG)
//			{
//				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler: Eingabe ist zu lang: ",true,"<"+S.NN(this.MetaFieldDef.get_FieldName())+">",false)));
////				this.iLastAssignmentError=ex.get_iErrorType();
//			}
//			else if (ex.get_iErrorType() == myExceptionDataValueNotFittingToField.VALUE_CAN_NOT_BE_A_DATE)
//			{
//				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler: Eingabe ist kein Datumswert: ",true,"<"+S.NN(this.MetaFieldDef.get_FieldName())+">",false)));
////				this.iLastAssignmentError=ex.get_iErrorType();
//			}
//			else if (ex.get_iErrorType() == myExceptionDataValueNotFittingToField.VALUE_CAN_NOT_BE_A_NUMBER)
//			{
//				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler: Eingabe ist keine Zahl: ",true,"<"+S.NN(this.MetaFieldDef.get_FieldName())+">",false)));
////				this.iLastAssignmentError=ex.get_iErrorType();
//			}
//			else if (ex.get_iErrorType() == myExceptionDataValueNotFittingToField.VALUE_CAN_NOT_BE_NULL)
//			{
//				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler: Eingabe darf nicht leer sein: ",true,"<"+S.NN(this.MetaFieldDef.get_FieldName())+">",false)));
////				this.iLastAssignmentError=ex.get_iErrorType();
//			}
		}
		catch (myException exx)
		{
			oMV.add_MESSAGE(exx.get_ErrorMessage());
		}
		
		return oMV;
	}

	
	
	//2013-09-18: reine pruefmethode fuer eingaben vor der eigentlichen uebergabe in den wert fuer die aenderung
	/**
	 * 
	 * @param cNewValueFormated
	 * @return
	 */
	public MyE2_MessageVector check_NewValueForDatabase(String cNewValueFormated)
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
//		//2013-07-17: assignmentstatus resetten
//		this.iLastAssignmentError = null;
		
		try
		{
			//DEBUG.MetaDef_print_infos(this.MetaFieldDef);
			this.MetaFieldDef.get_cStringForDataBase(cNewValueFormated, true, false);
			
		}
		catch (myExceptionDataValueNotFittingToField ex)
		{
			//2015-02-24: vereinheitlichung der fehlergenerierung
			oMV.add(ex.get_Message(this.MetaFieldDef.get_FieldName(), cNewValueFormated));

//			if (ex.get_iErrorType() == myExceptionDataValueNotFittingToField.VALUE_IS_TOO_LONG)
//			{
//				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler: Eingabe ist zu lang: ",true,"<"+S.NN(this.MetaFieldDef.get_FieldName())+">",false)));
////				this.iLastAssignmentError=ex.get_iErrorType();
//			}
//			else if (ex.get_iErrorType() == myExceptionDataValueNotFittingToField.VALUE_CAN_NOT_BE_A_DATE)
//			{
//				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler: Eingabe ist kein Datumswert: ",true,"<"+S.NN(this.MetaFieldDef.get_FieldName())+">",false)));
////				this.iLastAssignmentError=ex.get_iErrorType();
//			}
//			else if (ex.get_iErrorType() == myExceptionDataValueNotFittingToField.VALUE_CAN_NOT_BE_A_NUMBER)
//			{
//				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler: Eingabe ist keine Zahl: ",true,"<"+S.NN(this.MetaFieldDef.get_FieldName())+">",false)));
////				this.iLastAssignmentError=ex.get_iErrorType();
//			}
//			else if (ex.get_iErrorType() == myExceptionDataValueNotFittingToField.VALUE_CAN_NOT_BE_NULL)
//			{
//				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler: Eingabe darf nicht leer sein: ",true,"<"+S.NN(this.MetaFieldDef.get_FieldName())+">",false)));
////				this.iLastAssignmentError=ex.get_iErrorType();
//			}
		}
		catch (myException exx)
		{
			oMV.add_MESSAGE(exx.get_ErrorMessage());
		}
		
		return oMV;
	}

	
	
	
	

	public boolean get_bDatabaseValue_Was_Set_from_outside()
	{
		return bDatabaseValue_Was_Set_from_outside;
	}
	

	/**
	 * 2015-02-17: vergleich des neu uebergebenen (setNewValue)-Wertes mit dem geladenen wert
	 * @return
	 * @throws myException 
	 */
	public boolean get_bDatabaseValue_Was_Set_from_outside_And_Is_Different() throws myException
	{
		if (this.bDatabaseValue_Was_Set_from_outside) {
			if (this.cFieldValue_ForDatabaseOnCreationTime ==null) {
				throw new myException(this,"cFieldValue_ForDatabaseOnCreationTime is not defined !!");
			}
			return ! (S.NN(this.cFieldValue_ForDatabase).equals(S.NN(this.cFieldValue_ForDatabaseOnCreationTime)));
		} else {
			return false;
		}
			
	}
	
	
	
	
	/**
	 * resettet den datenbank-Wert wieder auf das original
	 */
	public void ResetOriginalDatabaseValue() throws myException
	{
		this.c_actual_formated_string_for_save=this.cFieldValueFormated;   //das formatierte uebergebene Datenbankfeld 2015-02-27
		this.cFieldValue_ForDatabase = this.makeVALUE_FOR_SQLSTATEMENT();
		this.bDatabaseValue_Was_Set_from_outside = false;
		
		
		//aufbau des eingelesenen feldes wie wenn es direkt wieder gespeichert wuerde
		try {
			this.cFieldValue_ForDatabaseOnCreationTime = this.MetaFieldDef.get_cStringForDataBase(this.cFieldValueFormated,true, false);
		}catch(Exception ex){
			this.cFieldValue_ForDatabaseOnCreationTime=null;
		}

	}



//	//2013-07-17: status-feld, nimmt bei zuweisungen den letzten fehlerstatus auf
//	public Integer get_iLastAssignmentError() {
//		return iLastAssignmentError;
//	}
//
//	//2013-07-17: status-feld, nimmt bei zuweisungen den letzten fehlerstatus auf
//	public void set_iLastAssignmentError(Integer iLastAssignmentError) {
//		this.iLastAssignmentError = iLastAssignmentError;
//	}
//



	//2014-10-24
	public Object get_oNativeDataObject() {
		return o_NativeDataObject;
	}

	

	 
	
	
	/**
	 * 20180314: 
	 * @return korrigierter nativ-wert, falls die metafielddef ein numerisches Feld ohne nachkomma anzeigt, dann in eine Long wandeln 
	 */
	public Object getNativeDateObjectCorrected() {
		if (this.o_NativeDataObject==null) {
			return null;
		}
		if (this.o_NativeDataObject instanceof BigDecimal && this.MetaFieldDef.get_bIsNumberTypeWithOutDecimals()) {
			return new Long(((BigDecimal)this.o_NativeDataObject).longValue());
		}
		return this.o_NativeDataObject;
	}



	/**
	 * 
	 * @return nach fuellen des Resultvalues den formatierten datenbank wert, nach uebergabe aus mask den neuen datenbank-wert, can be null !
	 */
	public String get_actual_formated_string_for_save() {
		return c_actual_formated_string_for_save;
	}

	
	
	
	//2016-11-09: copy eines resultsets
	//privater konstruktor fuer die copy
	private MyResultValue() {
		super();
	}
	

	public MyResultValue get_copy() {
		MyResultValue res_copy = new MyResultValue();
		
		res_copy.cFieldValueUnformated 	= cFieldValueUnformated;
		res_copy.cFieldValueFormated		= cFieldValueFormated;
		res_copy.cDateTimeValueFormated	= cDateTimeValueFormated;  
		res_copy.cDateValueFormated	= 	  cDateValueFormated;    
		res_copy.doubleValue = doubleValue;
		res_copy.bdValue = bdValue;
		res_copy.longValue = longValue;
		res_copy.dateValue = dateValue;
		res_copy.timeStampValue = timeStampValue;
		res_copy.bIsDateType = bIsDateType;
		res_copy.cHASH_LABEL = cHASH_LABEL;
		res_copy.cFieldType = cFieldType;
		res_copy.MetaFieldDef = MetaFieldDef;
		res_copy.c_actual_formated_string_for_save = c_actual_formated_string_for_save;
		res_copy.cFieldValue_ForDatabase = cFieldValue_ForDatabase;
		res_copy.bDatabaseValue_Was_Set_from_outside = bDatabaseValue_Was_Set_from_outside;
		res_copy.cFieldValue_ForDatabaseOnCreationTime = cFieldValue_ForDatabaseOnCreationTime;
		res_copy.o_NativeDataObject = o_NativeDataObject;
		
		return res_copy;
	}
	
	
	
	
}
