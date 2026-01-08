package panter.gmbh.indep.dataTools;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.DB_STATICS.AUTOMATC_FIELDS;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionDataValueNotFittingToField;



/*
 * aufbau einer hashmap aus der LEER-abfrage einer Tabelle
 */
public class MyRECORD_NEW extends HashMap<String, MyMetaFieldDEF> implements MyRECORD_IF_FILLABLE
{
	
	private MyConnection  				oConn = 			bibALL.get_oConnectionNormal();
	private String        				cTABLE_NAME = 	null;               //dTablename darf nicht null sein
	
	/**
	 * 2015-05-06: Toolbox-Generator, um DBToolboxen mit von der norm abweichenden Ausnahmefeldern erzeugen zu koennen
	 */
	private MyDBToolBox_FAB  	DBToolBox_FAB = null;

	
	//hashmap, die die neueingegebenen Strings mitspeichert
	private HashMap<String, String>   	hmNewValues  = new HashMap<String, String>();
	
	
	//hashmap, uebernimmt die formatierten uebergebenen Maskenwerte vor dem speichern 2015-02-27
	private HashMap<String, String>   	hmNewValues_To_DB_Field  = new HashMap<String, String>();
	
	
	
	//falls die seq-nummer hier geholt wurde, dann wird sie in dieses Feld eingetragen
	private String  					cLastSEQ_NUMBER = null;
	
	
//	//2013-07-17: hashmap mit zuweisungsfehler-statuswerten
//	private HashMap<String, Integer> hmLastAssignmentErrors =new HashMap<String, Integer>();

	
	//2015-03-05: zusatzpaare feld-wert von aussen fuer die erzeugung der statementbuilders
	private HashMap<String, String>      hm_Field_Value_pairs_from_outside = new HashMap<String, String>();
	
	
	
	//2013-09-20: neue recordnew-variante mit uebergabe einer hashmap aus fielddefs
	public MyRECORD_NEW(String cTableName, HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException
	{
		super();
		this.cTABLE_NAME=cTableName;
		this.putAll(hmMetadefs);
	}

	
	public MyRECORD_NEW(String cTableName) throws myException
	{
		super();
		this.cTABLE_NAME=cTableName;
		this.BuildRecord();
		
	}
	
	/**
	 * 
	 * @param Conn
	 * @throws myException 
	 */
	public MyRECORD_NEW(MyConnection Conn, String cTableName) throws myException
	{
		super();
		this.oConn = Conn;
		this.cTABLE_NAME=cTableName;
		this.BuildRecord();
	}

	

	public String get_cLastSEQ_NUMBER() 
	{
		return cLastSEQ_NUMBER;
	}
	


	
	private void BuildRecord() throws myException
	{
		
		this.clear();
		
		String cNullQuery = "SELECT * FROM "+bibE2.cTO()+"."+this.cTABLE_NAME+" WHERE ID_"+this.cTABLE_NAME.substring(3)+"=-99999999";
		
		//2015-05-06: geaendert 
		//MyDBToolBox oDB = bibALL.get_myDBToolBox(this.oConn);
		MyDBToolBox oDB = this.generate_DBToolBox(this.oConn);
		
		MyDBResultSet oRS = oDB.OpenResultSet(cNullQuery);
		
		if (oRS.RS != null)
		{
 
            try
            {
                int iAnzahlSpalten = oRS.RS.getMetaData().getColumnCount();

                if (iAnzahlSpalten > 0)
                {
					for (int i = 0; i < iAnzahlSpalten; i++)
					{
						MyMetaFieldDEF NewFieldDef = new MyMetaFieldDEF(oRS.RS,i, null);
						this.put(NewFieldDef.get_FieldName().toUpperCase(),new MyMetaFieldDEF(oRS.RS,i, null));
						this.hmNewValues.put(NewFieldDef.get_FieldName(), null);
					}
                }
                else
                {
                	oRS.Close();
                	bibALL.destroy_myDBToolBox(oDB);
                	throw new myException(myException.TYPE_ERROR_SQL_QUERY_IS_EMPTY,"MyRecord_NEW:BuildRecord: Resultset has now rows !! : "+cNullQuery);
                }

            }
            catch (myException ex)
            {
            	oRS.Close();
            	bibALL.destroy_myDBToolBox(oDB);
            	throw ex;
            }
            catch (Exception e)
            {
            	oRS.Close();
            	bibALL.destroy_myDBToolBox(oDB);
            	e.printStackTrace();
            	throw new myException(e.getLocalizedMessage());
            }
 		}
		else
		{
        	DEBUG.System_println("--------------------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
        	DEBUG.System_println("MyRecord: Error SQL  ---------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
        	DEBUG.System_println(cNullQuery,DEBUG.DEBUG_FLAG_SQL_ERROR);
        	DEBUG.System_println("--------------------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
        	bibALL.destroy_myDBToolBox(oDB);
           	throw new myException(myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT,"MyRecord__NEW:__build_Hash: Cannnot open resultset !"+cNullQuery);
		}

        oRS.Close();
        bibALL.destroy_myDBToolBox(oDB);
		
	}
	
	
	/**
	 * 2015-02-24 
	 * @param FIELDNAME
	 * @param cNewValueFormated (Wert muss wie eine maskeneingabe uebergeben werden, z.B. 1.233,55 fuer 1233.55
	 * @return
	 * @throws myException
	 */
	public MyE2_MessageVector  set_NewValueForDatabase(String FIELDNAME, String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase(FIELDNAME, cNewValueFormated, false);
	}
		

	/**
	 * 2016-04-06 
	 * @param field
	 * @param cNewValueFormated (Wert muss wie eine maskeneingabe uebergeben werden, z.B. 1.233,55 fuer 1233.55
	 * @return
	 * @throws myException
	 */
	public MyE2_MessageVector  nv(IF_Field field, String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase(field.fn(), cNewValueFormated, false);
	}
		
	/**
	 * 2016-06-13 
	 * @param Field ...
	 * @param cNewValueFormated (Wert muss wie eine maskeneingabe uebergeben werden, z.B. 1.233,55 fuer 1233.55
	 * @return
	 * @throws myException
	 */
	public MyE2_MessageVector  nv(FieldValPair... fields) throws myException	{
		MyE2_MessageVector mv = new MyE2_MessageVector();
		for (FieldValPair  p: fields) {
			mv.add_MESSAGE(this.nv(p.getF(),p.getS_f()));
		}
		return mv;
	}
	
	

	
	
	
	/**
	 * 
	 * @param FIELDNAME
	 * @param cNewValueFormated (Wert muss wie eine maskeneingabe uebergeben werden, z.B. 1.233,55 fuer 1233.55
	 * @param bForceFieldIsNotNullable   //2015-02-24
	 * @return
	 * @throws myException
	 */
	public MyE2_MessageVector  set_NewValueForDatabase(String FIELDNAME, String cNewValueFormated, boolean bForceFieldIsNotNullable) throws myException
	{
		
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		String cFeldName = FIELDNAME.toUpperCase();
	
		if (!this.containsKey(cFeldName) || this.get(cFeldName)==null)
			throw new myException(this,"set_NewValueForDatabase:"+cFeldName+" not in MyRecord!");
		
		try
		{
			String cFieldValue_ForDatabase = this.get(cFeldName).get_cStringForDataBase(cNewValueFormated, true, bForceFieldIsNotNullable);
			this.hmNewValues.put(cFeldName, cFieldValue_ForDatabase);
			this.hmNewValues_To_DB_Field.put(cFeldName, cNewValueFormated);
		}
		catch (myExceptionDataValueNotFittingToField ex)
		{
			//2015-02-24: vereinheitlichung der fehlergenerierung
			oMV.add(ex.get_Message(FIELDNAME, cNewValueFormated));
		}
		catch (myException exx)
		{
			oMV.add_MESSAGE(exx.get_ErrorMessage());
		}

		
		return oMV;
		
	}
	
	// 2015-02-24
	public MyE2_MessageVector  check_NewValueForDatabase(String FIELDNAME, String cNewValueFormated) throws myException {
		return this.check_NewValueForDatabase(FIELDNAME, cNewValueFormated, false);
	}
	
	
	/**
	 * 2013-09-18: pruefmethode, um die inhalte zu verifizieren bevor sie in die records geschrieben werden
	 * @param FIELDNAME
	 * @param cNewValueFormated
	 * @param bForceFieldIsNotNullable   //2015-02-24
	 * @return
	 * @throws myException
	 */
	public MyE2_MessageVector  check_NewValueForDatabase(String FIELDNAME, String cNewValueFormated, boolean bForceFieldIsNotNullable) throws myException
	{
		
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		String cFeldName = FIELDNAME.toUpperCase();
	
		if (!this.containsKey(cFeldName) || this.get(cFeldName)==null)
			
			throw new myException(this,"set_NewValueForDatabase:"+cFeldName+" not in MyRecord!");
		try
		{
			this.get(cFeldName).get_cStringForDataBase(cNewValueFormated, true, bForceFieldIsNotNullable);
		}
		catch (myExceptionDataValueNotFittingToField ex)
		{
			String infoField = FIELDNAME;
			if (S.isFull(this.cTABLE_NAME)) {
				infoField=this.cTABLE_NAME+"."+FIELDNAME;
			}
			
			oMV.add(ex.get_Message(infoField, cNewValueFormated));
		}
		catch (myException exx)
		{
			oMV.add_MESSAGE(exx.get_ErrorMessage());
		}

		
		return oMV;
		
	}

	
	
	
	
	
	public MyE2_MessageVector set_NewValueForDatabase(String FIELDNAME, long lNewValueFormated) throws myException
	{
		return set_NewValueForDatabase(FIELDNAME, MyNumberFormater.formatDez(lNewValueFormated, false));		
	}
	
	public MyE2_MessageVector set_NewValueForDatabase(String FIELDNAME, double dNewValueFormated) throws myException
	{
		return set_NewValueForDatabase(FIELDNAME, MyNumberFormater.formatDez(dNewValueFormated, this.get(FIELDNAME).get_FieldDecimals(),false));		
	}

	public MyE2_MessageVector set_NewValueForDatabase(String FIELDNAME, BigDecimal bdNewValueFormated) throws myException
	{
		return set_NewValueForDatabase(FIELDNAME, MyNumberFormater.formatDez(bdNewValueFormated, this.get(FIELDNAME).get_FieldDecimals(),true));		
	}

	
	public MyE2_MessageVector set_NewValueForDatabase(String FIELDNAME, GregorianCalendar calNewValueFormated) throws myException
	{
		return set_NewValueForDatabase(FIELDNAME, myDateHelper.FormatDateNormal(calNewValueFormated.getTime()));		
	}


	
	
	/**
	 * reset aller bestehenden neuen Datenbank-Values
	 * @throws myException
	 */
	public void RESET_ALL_NEWVALUES() throws myException
	{
		for (Map.Entry<String, String> oEntry: this.hmNewValues.entrySet())
		{
			this.hmNewValues.put(oEntry.getKey(), null);
		}
		
		this.hmNewValues_To_DB_Field.clear();
	}
	

	public MySqlStatementBuilder get_StatementBuilder(boolean bExcludeAutomaticFields) throws myException
	{
		MySqlStatementBuilder oStateBuilder = new MySqlStatementBuilder();
		
		Vector<String> oFieldsNotInMap = new Vector<String>();
		
		
		if (bExcludeAutomaticFields)
		{
			oFieldsNotInMap.addAll(this.get_vSonderFelder());
		}
		
		//jetzt mit den Werten der map fuellen
		for (java.util.Map.Entry<String,String> oEntry: this.hmNewValues.entrySet())
		{
			if (!oFieldsNotInMap.contains(oEntry.getKey()))
			{
				if (S.isFull(oEntry.getValue()))
				{
					oStateBuilder.addSQL_Paar(oEntry.getKey(),oEntry.getValue(), false);
				}
			}
		}
		
		//2015-03-05: von aussen zugefuegte statements hier mit einbauen
		for (String field: this.hm_Field_Value_pairs_from_outside.keySet()) {
			oStateBuilder.addSQL_Paar(field, this.hm_Field_Value_pairs_from_outside.get(field), false);
		}

		
		
		return oStateBuilder;
	}

	
	
	//2014-07-02: weitere methode
	public MySqlStatementBuilder get__StatementBuilder(boolean bExcludeAutomaticFields, boolean bAddPrimaryKeySequence) throws myException
	{
		MySqlStatementBuilder oStatementBuilder = this.get_StatementBuilder(bExcludeAutomaticFields);
			
		if (bAddPrimaryKeySequence) {
			oStatementBuilder.addSQL_Paar("ID_"+this.get_TABLE_NAME().substring(3), "SEQ_"+this.get_TABLE_NAME().substring(3)+".NEXTVAL", false, true);
		}
		return oStatementBuilder;
	}

	
	
	//2016-01-18: neues statementbuilder-objekt
	public MySqlStatementBuilder get_StmtBuilder(boolean bExcludeAutomaticFields) throws myException {
		MySqlStatementBuilder oStateBuilder = new MySqlStatementBuilder(this.cTABLE_NAME);
		
		Vector<String> oFieldsNotInMap = new Vector<String>();
		
		
		if (bExcludeAutomaticFields) {
			oFieldsNotInMap.addAll(this.get_vSonderFelder());
		}
		
		//jetzt mit den Werten der map fuellen
		for (java.util.Map.Entry<String,String> oEntry: this.hmNewValues.entrySet()) {
			if (!oFieldsNotInMap.contains(oEntry.getKey())) 	{
				if (S.isFull(oEntry.getValue()))	{
					oStateBuilder.addSQL_Paar(oEntry.getKey(),oEntry.getValue(), false);
				}
			}
		}
		
		//2015-03-05: von aussen zugefuegte statements hier mit einbauen
		for (String field: this.hm_Field_Value_pairs_from_outside.keySet()) {
			oStateBuilder.addSQL_Paar(field, this.hm_Field_Value_pairs_from_outside.get(field), false);
		}
		
		return oStateBuilder;
	}
	
	
	public MySqlStatementBuilder get_StmtBuilder(boolean bExcludeAutomaticFields, boolean bAddPrimaryKeySequence) throws myException
	{
		MySqlStatementBuilder oStatementBuilder = this.get_StmtBuilder(bExcludeAutomaticFields);
			
		if (bAddPrimaryKeySequence) {
			oStatementBuilder.addSQL_Paar("ID_"+this.get_TABLE_NAME().substring(3), "SEQ_"+this.get_TABLE_NAME().substring(3)+".NEXTVAL", false, true);
		}
		return oStatementBuilder;
	}

	

	//2016-01-18: ------------------------------------------
	
	
	
	
	/*
	 * liefert die uebergebenen Werte in eine SQL-INSERT-Anweisung eingebettet
	 */
	public String get_InsertSQLStatement(boolean bExcludeAutomaticFields) throws myException
	{
		this.cLastSEQ_NUMBER = null;
		return  this.get_StatementBuilder(bExcludeAutomaticFields).get_CompleteInsertString(this.cTABLE_NAME, bibE2.cTO());
	}


	/*
	 * liefert die uebergebenen Werte in eine SQL-INSERT-Anweisung eingebettet
	 */
	public String get_InsertSQLStatementWith_Id_Field(boolean bSequencer_In_Statement, boolean bExcludeAutomaticFields) throws myException
	{
		this.cLastSEQ_NUMBER = null;
		if (bSequencer_In_Statement)
		{
			this.cLastSEQ_NUMBER = null;
			
			//2011-06-09: fuehrt zu einem fehler, da der sequencer-ausdruck nicht in das feld passt, deswegen via put reingeschrieben
			//this.set_NewValueForDatabase("ID_"+this.cTABLE_NAME.substring(3), "SEQ_"+this.cTABLE_NAME.substring(3)+".NEXTVAL");
			//NEU
			this.hmNewValues.put("ID_"+this.cTABLE_NAME.substring(3), "SEQ_"+this.cTABLE_NAME.substring(3)+".NEXTVAL");

			
		}
		else
		{
			this.cLastSEQ_NUMBER = bibDB.get_NextSequenceValueOfTable(this.cTABLE_NAME);
			this.set_NewValueForDatabase("ID_"+this.cTABLE_NAME.substring(3), this.cLastSEQ_NUMBER);
		}
		return  this.get_StatementBuilder(bExcludeAutomaticFields).get_CompleteInsertString(this.cTABLE_NAME, bibE2.cTO());
	}


	
	
	public MyE2_MessageVector  CheckNotNullables(Vector<String> vFieldsNotToCheck) throws myException
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		Vector<String> vLeereNotNulls = new Vector<String>();
		
		
		Vector<String> vFieldsExcludeFromCheck = new Vector<String>();
		
		if (vFieldsNotToCheck != null)
		{
			vFieldsExcludeFromCheck.addAll(vFieldsNotToCheck);
		}
		
		
		//jetzt mit den Werten der map fuellen
		for (java.util.Map.Entry<String,MyMetaFieldDEF> oEntry: this.entrySet())
		{
			if (!oEntry.getValue().get_bFieldNullableBasic() && !vFieldsExcludeFromCheck.contains(oEntry.getKey()))
			{
				if (S.isEmpty(this.hmNewValues.get(oEntry.getKey())))
				{
					vLeereNotNulls.add(oEntry.getKey());
				}
			}
		}

		
		if (vLeereNotNulls.size()>0)
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(
					new MyE2_String("Schreiben eines neuen Satzes ",true,"<"+this.cTABLE_NAME+">",false," Es sind noch leere NOTNULL-Felder in der Zusammenstellung: ",true,
																	bibALL.Concatenate(vLeereNotNulls, ",", ""),false)));
		}
		return oMV;
	}
	
	
	
	
	public MyConnection get_oConn()
	{
		return oConn;
	}


	public String get_TABLE_NAME()
	{
		return this.cTABLE_NAME;
	}


	//2013-09-20: im normalfall ist in den MyFieldMetaDefs keine Tablename-Angabe vorhande
	//in den speziell erzeugten record-objketen der Tabellen steht der Tablename aber fest.
	//mit dieser methode kann dieser Tablename an die MyFieldMetaDefs uebergeben werden
	public void set_Tablename_To_FieldMetaDefs(String tablename) {
		for (MyMetaFieldDEF oMeta: this.values()) {
			oMeta.set_Tablename(tablename.toUpperCase(), false);
		}
	}
	

	//2014-02-27: in die feldname/value-hash einen unveraenderten wert einsetzen
	public void set_NewValueForDatabase_RAW_AS_STRING_IN_STATEMENT(String FIELDNAME, String cStringInStatement) throws myException {
		this.hmNewValues.put(FIELDNAME, cStringInStatement);
	}


	@Override
	public String get_TABLENAME() throws myException {
		return this.cTABLE_NAME;
	}


	@Override
	public String get_PRIMARY_KEY_NAME() throws myException {
		if (S.isFull(this.cTABLE_NAME)) {
			return "ID_"+this.cTABLE_NAME.trim().substring(3);
		} else {
			throw new myException(this,"No Tablename is present !!!");
		}
		
	}


	@Override
	public boolean get_bHasSomething_to_save() throws myException {
		return true;
	}
	
	
	@Override
	public HashMap<String, String> get_hm_InputValuesFormated() throws myException {
		return this.hmNewValues_To_DB_Field;
	}

	
	
	//2015-03-05: zusatzpaare feld-wert von aussen fuer die erzeugung der statementbuilders
	@Override
	public HashMap<String, String> get_hm_Field_Value_pairs_from_outside() {
		return hm_Field_Value_pairs_from_outside;
	}

	/**
	 * Neue Methode, um eine HashMap als key=>values komplett zu übergeben (nilsandre)
	 * @param values
	 * @return
	 * @throws myException
	 */
	public MyE2_MessageVector setValues(HashMap<String, Object> values) throws myException {
		MyE2_MessageVector me2mv = new MyE2_MessageVector();
		Iterator<String> it = values.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			me2mv.addAll(set_NewValueForDatabase(key, ""+values.get(key)));
		}
		return me2mv;
	}
	
	
	/**
	 * 2015-03-26: martin
	 * @param oMV
	 * @return
	 * @throws myException
	 */
	public MyRECORD do_WRITE_NEW_RECORD(MyE2_MessageVector oMV) throws myException  {
		
		boolean bThrowExceptionWhenAlarm = false;
	
		if (S.isEmpty(this.cTABLE_NAME)) {
			throw new myException(this, "do_WRITE_NEW_RECORD(): no Tablename is present !" );
		}
		
		if (oMV==null) {
			oMV=new MyE2_MessageVector();
			bThrowExceptionWhenAlarm = true;
		}
		
		
		//zuerst die NotNull-felder pruefen (ausser den automatismen)
		Vector<String> vExcludeFields = new Vector<String>();
		vExcludeFields.addAll(this.get_vSonderFelder());
		vExcludeFields.add(this.get_PRIMARY_KEY_NAME());
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));

			//2015-05-06: geaendert
//			oMV.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())	{
				return new MyRECORD("SELECT * FROM "+bibE2.cTO()+"."+this.cTABLE_NAME+" WHERE "+this.get_PRIMARY_KEY_NAME()+"="+this.cLastSEQ_NUMBER);
			}
			else {
				if (bThrowExceptionWhenAlarm)  {  //falls fehler nicht in einen Messagevector geht, dann exception
					throw new myException("Error writing new Dataset :"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}
	
	
	
	
	/**
	 * 2016-04-06: martin
	 * @param oMV
	 * @param keepTransactionOpen (wenn true, dann wird immer der Term SEQ_XXXX.nextval im statement verwendet
	 * @return
	 * @throws myException
	 */
	public MyRECORD do_WRITE_NEW_RECORD(MyE2_MessageVector oMV, boolean keepTransactionOpen) throws myException  {
		
		if (!keepTransactionOpen) {
			return this.do_WRITE_NEW_RECORD(oMV);
		}
		
		boolean bThrowExceptionWhenAlarm = false;
	
		if (S.isEmpty(this.cTABLE_NAME)) {
			throw new myException(this, "do_WRITE_NEW_RECORD(): no Tablename is present !" );
		}
		
		if (oMV==null) {
			oMV=new MyE2_MessageVector();
			bThrowExceptionWhenAlarm = true;
		}
		
		
		//zuerst die NotNull-felder pruefen (ausser den automatismen)
		Vector<String> vExcludeFields = new Vector<String>();
		vExcludeFields.addAll(this.get_vSonderFelder());
		vExcludeFields.add(this.get_PRIMARY_KEY_NAME());
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(true, true));
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, false));
			
			//jetzt den sequencer auslesen in der offenen transaktion
			this.cLastSEQ_NUMBER = this.get_currSeqValue();
			
			if (!oMV.get_bHasAlarms())	{
				return new MyRECORD("SELECT * FROM "+bibE2.cTO()+"."+this.cTABLE_NAME+" WHERE "+this.get_PRIMARY_KEY_NAME()+"="+this.cLastSEQ_NUMBER);
			}
			else {
				if (bThrowExceptionWhenAlarm)  {  //falls fehler nicht in einen Messagevector geht, dann exception
					throw new myException("Error writing new Dataset :"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

	/**
	 * 2016-04-06: martin
	**/
	private String get_currSeqValue() throws myException {
		MyDBToolBox oDB = this.generate_DBToolBox(this.oConn);
		String seq_name = "SEQ_"+_TAB.find_Table(this.cTABLE_NAME).baseTableName();
		String c_seq = oDB.EinzelAbfrage("SELECT "+seq_name+".CURRVAL FROM DUAL");
		bibALL.destroy_myDBToolBox(oDB);
		return c_seq;
	}
	
	
	/**
	 * 2015-05-06: ExecMultiSQLVector ausgelagert in eine eigene Methode, damit die eigene DBToolBox verwendet wird
	 * @param vSQLStack
	 * @param bCommit
	 * @return
	 * @throws myException 
	 */
	public MyE2_MessageVector ExecMultiSQLVector(Vector<String> vSQLStack, boolean bCommit) throws myException {
		MyDBToolBox oDB = this.generate_DBToolBox(this.oConn);
		MyE2_MessageVector VRueck = oDB.ExecMultiSQLVector(vSQLStack, bCommit);
		bibALL.destroy_myDBToolBox(oDB);
		return VRueck;
	}
	
	
	
	
	/**
	 * 2015-05-06: das holen der DBToolBox hier ausgelagert in eine eigene Methode, die ueberschrieben werden kann
	 * @return
	 * @throws myException
	 */
	public MyDBToolBox  generate_DBToolBox(MyConnection conn) throws myException {
		if (this.DBToolBox_FAB!=null) {
			return this.DBToolBox_FAB.generate_INDIVIDUELL_DBToolBox(conn);
		} else {
			return MyDBToolBox_FAB.generate_STANDARD_DBToolBox(conn);	
		}
	}

	
	
	/**
	 * 2015-05-06: die sonderfelder werden jetzt aus der (evtl. eigenen) MyDBToolBox gelesen, nicht mehr global
	 * @return
	 */
	public Vector<String> get_vSonderFelder() throws myException {
		Vector<String> vRueck = new Vector<String>();
		
		MyDBToolBox tb_temp = this.generate_DBToolBox(this.oConn);
		vRueck.addAll(tb_temp.get_AutomaticWrittenFields());
		bibALL.destroy_myDBToolBox(tb_temp);

		return vRueck;
	}

	
	
	
	public MyDBToolBox_FAB get_DBToolBox_FAB() {
		return this.DBToolBox_FAB;
	}


	public void set_DBToolBox_FAB(MyDBToolBox_FAB dBToolBoxGenerator) {
		this.DBToolBox_FAB = dBToolBoxGenerator;
	}

	

	/**
	 * 2016-01-20: neue methode zum anhaengen einer raw-felds
	 * @param field
	 * @param value
	 * @throws myException
	 */
	public void add_raw_val(IF_Field  field, String value) throws myException {
		if (S.isFull(this.cTABLE_NAME)&&field.tn().equals(this.cTABLE_NAME)) {
			this.hm_Field_Value_pairs_from_outside.put(field.fn(), value);
		} else {
			throw new myException(this,"Tablename is not present or false table!");
		}
	}
	
	
	
	

	/**
	 * martin: 2016-09-05
	 * @param oMV
	 * @param bSequencer_In_Statement
	 * @param bExcludeAutomaticFields
	 * @return
	 * @throws myException
	 */
	public MyRECORD do_WRITE_NEW_RECORD(MyE2_MessageVector oMV, boolean bSequencer_In_Statement, boolean bExcludeAutomaticFields) throws myException  {
		
		boolean bThrowExceptionWhenAlarm = false;
	
		if (S.isEmpty(this.cTABLE_NAME)) {
			throw new myException(this, "do_WRITE_NEW_RECORD(): no Tablename is present !" );
		}
		
		if (oMV==null) {
			oMV=new MyE2_MessageVector();
			bThrowExceptionWhenAlarm = true;
		}
		
		
		//zuerst die NotNull-felder pruefen (ausser den automatismen)
		Vector<String> vExcludeFields = new Vector<String>();
		vExcludeFields.addAll(this.get_vSonderFelder());
		vExcludeFields.add(this.get_PRIMARY_KEY_NAME());
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(bSequencer_In_Statement, bExcludeAutomaticFields));

			//2015-05-06: geaendert
//			oMV.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())	{
				return new MyRECORD("SELECT * FROM "+bibE2.cTO()+"."+this.cTABLE_NAME+" WHERE "+this.get_PRIMARY_KEY_NAME()+"="+this.cLastSEQ_NUMBER);
			}
			else {
				if (bThrowExceptionWhenAlarm)  {  //falls fehler nicht in einen Messagevector geht, dann exception
					throw new myException("Error writing new Dataset :"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}
	
	

	
	public MyRECORD_NEW _add_sequencer() throws myException {
		if (S.isEmpty(this.cTABLE_NAME)) {
			throw new myException(this, "do_WRITE_NEW_RECORD(): no Tablename is present !" );
		}
		
		String baseName = this.cTABLE_NAME.substring(3);
		this.hm_Field_Value_pairs_from_outside.put("ID_"+baseName, "SEQ_"+baseName+".NEXTVAL");
		return this;
	}
	
	public MyRECORD_NEW _add_id_mandant() throws myException {
		this.hm_Field_Value_pairs_from_outside.put(AUTOMATC_FIELDS.ID_MANDANT.toString(), bibALL.get_ID_MANDANT());
		return this;
	}
	
	public MyRECORD_NEW _add_timestamp() throws myException {
		this.hm_Field_Value_pairs_from_outside.put(AUTOMATC_FIELDS.LETZTE_AENDERUNG.toString(), "SYSDATE");
		return this;
	}
	
	public MyRECORD_NEW _add_user() throws myException {
		this.hm_Field_Value_pairs_from_outside.put(AUTOMATC_FIELDS.GEAENDERT_VON.toString(), bibALL.MakeSql(bibALL.get_KUERZEL()));
		return this;
	}
	
	/**
	 * speichert den record ohne jegliche automatismen, keine id, keine mandenten- user oder timestamps
	 * @param commit
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public void SAVE_RAW(boolean commit, MyE2_MessageVector mv) throws myException {
			
		if (S.isEmpty(this.cTABLE_NAME)) {
			throw new myException(this, "do_WRITE_NEW_RECORD(): no Tablename is present !" );
		}

		Vector<String> vSQL = new Vector<String>();
		vSQL.add(this.get_StatementBuilder(false).get_CompleteInsertString(this.cTABLE_NAME, bibE2.cTO()));

		MyDBToolBox oDB = MyDBToolBox_FAB.get_myDBToolBox(false, false);
		mv.add_MESSAGE(oDB.ExecMultiSQLVector(vSQL, commit));
		bibALL.destroy_myDBToolBox(oDB);
		
	}
	
	/**
	 * gibt das kompette sql-insert-statement zurueck, ohne irgendein automatisches feld zu entfernen
	 * @return
	 * @throws myException
	 */
	public String get_RAW_SQL_4_SAVE() throws myException {
			
		if (S.isEmpty(this.cTABLE_NAME)) {
			throw new myException(this, "do_WRITE_NEW_RECORD(): no Tablename is present !" );
		}

		return this.get_StatementBuilder(false).get_CompleteInsertString(this.cTABLE_NAME, bibE2.cTO());
	}

	
	/** 
	 * 2016-09-07: martin
	 * sorgt dafuer, dass abfragen auf den basisadressen und speichervorgaenge ohne automatische zusatzfelder ausgefuehrt werden
	 */
	public void set_to_raw_state() {
		this.set_DBToolBox_FAB(new MyDBToolBox_FAB_raw());
	}

	
	
}
