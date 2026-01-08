package panter.gmbh.indep.dataTools.RECORD2;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.DB_STATICS;
import panter.gmbh.indep.dataTools.DB_STATICS.AUTOMATC_FIELDS;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBResultSet;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD.DATATYPES;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MyRECORD_NEW;
import panter.gmbh.indep.dataTools.MyResultValue;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.RECORD2.Rec20.CreatorForDifferentDataTypes.TARGETTYPE;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.VectorNN;

public class Rec20 extends HashMap<IF_Field, Rec20_field> implements /*MyRECORD_IF_FILLABLE ab 20180327 erweitertes inferface*/ MyRECORD_IF_RECORDS {

	protected _TAB  			tab = null;            //_TAB muss immer vorhanden sein
	protected String  		sql4query = null; 
	protected MyDBToolBox 	oDB = null;
	protected IF_Field[]      fields = null;
	
	protected HashMap<String, String>      hm_Field_Value_pairs_from_outside = new HashMap<String, String>();

	
	protected HashMap<IF_Field, Rec20>     hm_upRecords = new HashMap<>();
	protected HashMap<String, RecList20>   hm_downRecLists = new HashMap<>();    //der key besteht aus dem feldnamen (auf Laenge 100 gefuellt) und dem Where-Statement des aufrufs	

	

	//wenn mit der eigenen save-methode ein neuer datensatz gespeichert wurde, dann 
	//wird dieses feld mithilfe der letzten sequence neu aufgebaut
	protected Rec20           rec_after_save_new = null;
	
	
	//20170922: strukturaenderung: wird eine query ausgefuehrt mit resultfeldern, die nicht in der enum der Tabelle vorhanden sind, dann
	//          werden die ResultValues in eine zusatzhashmap hinterlegt, die von ausen erreichbar ist. Es wird kein fehler mehr geschmissen
	protected HashMap<String , MyResultValue>   hmOverheadFields = new HashMap<>();
	
	
	//20180228: umsetzungsmethode extern erstellen fuer raw-values in strings fuer die datenbank
	protected  CreatorForDifferentDataTypes  creatorForDifferentDataTypes = new CreatorForDifferentDataTypesStd();
	
	/**
	 * @param p_tab
	 * @throws myException 
	 */
	public Rec20(_TAB p_tab) throws myException {
		super();
		if (p_tab == null) {
			throw new myException(this, "Empty _TAB-object is not allowed !");
		}

		this.tab = p_tab;

		this.fields=this.tab.get_fields();
		for (IF_Field f: this.tab.get_fields()) {
			this.put(f, new Rec20_field(f));
		}
	}
	
	
	public Rec20(Rec20 baseRec) throws myException {
		this(baseRec.tab);
		this._fill(baseRec);
	}
	
	
	
	public Rec20 _fill(Rec20 baseRec) throws myException {
		if (baseRec.get_tab()!=this.get_tab()) {
			throw new myException(this,"parameter-Rec20 is false type !!");
		}
		this.fields=this.tab.get_fields();
		this.oDB = baseRec.oDB;
		for (IF_Field f: this.tab.get_fields()) {
			this.put(f, (Rec20_field)baseRec.get(f).get_copy());
		}
		
		this.hm_Field_Value_pairs_from_outside.putAll(baseRec.hm_Field_Value_pairs_from_outside);
		this.sql4query=baseRec.sql4query;
		this.rec_after_save_new=baseRec.rec_after_save_new;
		
		//20170922: overheadFields
		this.hmOverheadFields.putAll(baseRec.getOverheadFields());
		
		return this;
	}
	
	
	

	
	public Rec20 _fill_id(String id) throws myException {
		MyLong lid = new MyLong(id);
		if (lid.get_bOK()) {
			this.execute_query("SELECT * FROM "+bibE2.cTO()+"."+this.tab.fullTableName()+" WHERE "+this.tab.keyFieldName()+"="+lid.get_cUF_LongString());
		} else {
			throw new myException(this,"Error ID "+id+" is no number !");
		}
		return this;
	}
	
	
	public Rec20 _fill_id(long id) throws myException {
		this.execute_query("SELECT * FROM "+bibE2.cTO()+"."+this.tab.fullTableName()+" WHERE "+this.tab.keyFieldName()+"="+id);
		return this;
	}
	
	
	public Rec20 _fill_sql(String sql) throws myException {
		if (sql.toUpperCase().contains("DELETE") ||sql.toUpperCase().contains("INSERT") || sql.toUpperCase().contains("UPDATE")  ) {
			throw new myException(this,"Error sql "+sql+" is no query-statement!");
		} else {
			this.execute_query(sql);
		}
		return this;
	}
	

	
	public Rec20 _fill(MyRECORD_IF_RECORDS rec) throws myException {
		if (S.NN(rec.get_TABLENAME(),"@@@").equals(this.tab.fullTableName())) {
			rec.set_Tablename_To_FieldMetaDefs();
			for (String name: ((MyRECORD)rec).keySet()) {
				IF_Field field =this.find_field(name);
				if (field==null) {
					//20170922: oberhead-fields
					//throw new myException(this,"._fill(MyRECORD): Field "+name+" was not found !");
					System.out.println("._fill(MyRECORD): Field "+name+" was not found !");
					this.hmOverheadFields.put(name.toUpperCase(), ((MyRECORD)rec).get(name));
				} else {
					this.get(field)._setResult(((MyRECORD)rec).get(name));
				}
			}
			this.sql4query=this.createOwnQuery();
		} else {
			throw new myException(this,"._fill(MyRECORD) has false tablename !");
		}
		
		return this;
	}
	
	
	
	
	
	public Rec20 _set_dbtoolbox(MyDBToolBox db) {
		this.oDB=db;
		return this;
	}
	
	
	/**
	 * @deprecated  use instead _rebuildRecord()
	 * @return
	 * @throws myException
	 */
	public Rec20 _rebuild() throws myException{
		if (S.isFull(this.sql4query)) {
			this.execute_query(this.sql4query);
		} else {
			throw new myException(this,"rebuild only possible with existing statement");
		}
		return this;
	}
	
	
	
	/**
	 * 20180105: neue variante mit beruecksichtigung des rec_after_save_new
	 * @return
	 * @throws myException
	 */
	public Rec20 _rebuildRecord() throws myException {
		if (this.is_ExistingRecord()) {
			if (S.isFull(this.sql4query)) {
				this.execute_query(this.sql4query);
			} else {
				throw new myException(this,"rebuild only possible with existing statement (1)");
			}
		} else {
			if (this.rec_after_save_new != null) {
				Rec20 r_new = this.rec_after_save_new;
				if (S.isFull(r_new.get_sql4query())) {
					this.execute_query(r_new.get_sql4query());
					this.rec_after_save_new=null;
				} else {
					throw new myException(this,"rebuild only possible with existing statement (2)");
				}
			} else {
				throw new myException(this,"rebuild only possible with existing statement (3)");
			}
		}
		return this;
	}
	
	
	
	
	/**
	 * 
	 * @param fieldName
	 * @return field, when not found null
	 */
	public IF_Field find_field(String fieldName) {
		for (IF_Field f: this.fields) {
			if (f.fieldName().equalsIgnoreCase(fieldName)) {
				return f;
			}
		}
		return null;
	}
	

	/**
	 * 
	 * @param fieldName
	 * @return field, when not found throws exception
	 * @throws myException
	 */
	public IF_Field find_field_exception(String fieldName) throws myException{
		for (IF_Field f: this.fields) {
			if (f.fieldName().equalsIgnoreCase(fieldName)) {
				return f;
			}
		}
		throw new myException(this, "field was not found :"+fieldName);
	}

	
	
	protected void execute_query(String sql) throws myException {

		//zuerst die alten resultvalues rausschmeissen
		for (IF_Field f: this.keySet()) {
			this.get(f)._setResult(null);
		}
		
		
		boolean toolbox_created = false;
		if (this.oDB==null) {
			this.oDB = bibALL.get_myDBToolBox();
			toolbox_created=true;
		}
		this.sql4query = sql;
		MyDBResultSet oRS = this.oDB.OpenResultSet(sql);
		
		if (oRS.RS != null) {
			
			this.handleResultset(oRS, toolbox_created, sql);
			
//            try  {
//                
//            	int iAnzahlSpalten = oRS.RS.getMetaData().getColumnCount();
//
//                int iCount = 0;
//                if (iAnzahlSpalten > 0) {
//                    
//                    while (oRS.RS.next()) {
//                        iCount++;
//                        if (iCount>1) {
//                        	oRS.Close();
//                            if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
//                           	throw new myException(myException.TYPE_ERROR_SQL_QUERY_IS_NOT_UNIQUE,"Rec20:__build_Hash: More than on result-rows cannot be !!"+sql);
//                        }
//                        for (int i = 0; i < iAnzahlSpalten; i++) {
//                        	MyMetaFieldDEF mdef = new MyMetaFieldDEF(oRS.RS,i, null);
//                        	MyResultValue oResult = new MyResultValue(mdef,oRS.RS,false);
//                        	IF_Field f= this.find_field(mdef.get_FieldName());
//                        	//2017-922: overheadFields
//                        	//alt:
////                        	if (f==null) {
////                        		oRS.Close();
////                                if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
////                               	throw new myException(myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT,"Field "+mdef.get_FieldName()+" ist not in the regular list of table "+this.tab.fullTableName()+": "+sql);
////                        	}
////                        	//der leere wert wird ersetzt durch einen mit result
////                        	this.get(f)._setResult(oResult);
//                        	if (f==null) {
//                               	this.hmOverheadFields.put(mdef.get_FieldName().toUpperCase(), oResult);
//                        	} else {
//                        		this.get(f)._setResult(oResult);
//                        	}
//                        }
//                    }
//
//                }
//                
//                if (iCount==0) {
//                	oRS.Close();
//                    if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
//                    
//                    this.sql4query=null;   // es bleibt ein record im status new uebrig
//                    
//                }
//
//            }
//            catch (myException ex)
//            {
//            	oRS.Close();
//                if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
//            	throw ex;
//            }
//            catch (Exception e)
//            {
//            	oRS.Close();
//                if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
//            	e.printStackTrace();
//            	throw new myException(e.getLocalizedMessage());
//            }
 		}
		else
		{
        	DEBUG.System_println("--------------------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
        	DEBUG.System_println("Rec20: Error SQL  ---------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
        	DEBUG.System_println(sql,DEBUG.DEBUG_FLAG_SQL_ERROR);
        	DEBUG.System_println("--------------------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
            if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
          	throw new myException(myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT,"Rec20:__build_Hash: Cannnot open resultset !"+sql);
		}

        oRS.Close();
        if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }

	}


	/**
	 * 20180309: ausgelagert fuer bessere ableitungsmoeglichkeiten
	 * @param oRS
	 * @param toolbox_created
	 * @param infoSql
	 * @throws myException
	 */
	protected void handleResultset(MyDBResultSet oRS, boolean toolbox_created, String infoSql) throws myException {
        try  {
            
        	int iAnzahlSpalten = oRS.RS.getMetaData().getColumnCount();

            int iCount = 0;
            if (iAnzahlSpalten > 0) {
                
                while (oRS.RS.next()) {
                    iCount++;
                    if (iCount>1) {
                    	oRS.Close();
                        if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
                       	throw new myException(myException.TYPE_ERROR_SQL_QUERY_IS_NOT_UNIQUE,"Rec20:__build_Hash: More than on result-rows cannot be !!"+infoSql);
                    }
                    for (int i = 0; i < iAnzahlSpalten; i++) {
                    	MyMetaFieldDEF mdef = new MyMetaFieldDEF(oRS.RS,i, null);
                    	MyResultValue oResult = new MyResultValue(mdef,oRS.RS,false);
                    	IF_Field f= this.find_field(mdef.get_FieldName());
                    	//2017-922: overheadFields
                    	//alt:
//                    	if (f==null) {
//                    		oRS.Close();
//                            if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
//                           	throw new myException(myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT,"Field "+mdef.get_FieldName()+" ist not in the regular list of table "+this.tab.fullTableName()+": "+sql);
//                    	}
//                    	//der leere wert wird ersetzt durch einen mit result
//                    	this.get(f)._setResult(oResult);
                    	if (f==null) {
                           	this.hmOverheadFields.put(mdef.get_FieldName().toUpperCase(), oResult);
                    	} else {
                    		this.get(f)._setResult(oResult);
                    	}
                    }
                }

            }
            
            if (iCount==0) {
            	oRS.Close();
                if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
                
                this.sql4query=null;   // es bleibt ein record im status new uebrig
                
            }

        }
        catch (myException ex)
        {
        	oRS.Close();
            if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
        	throw ex;
        }
        catch (Exception e)
        {
        	oRS.Close();
            if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
        	e.printStackTrace();
        	throw new myException(e.getLocalizedMessage());
        }

	}


	public _TAB get_tab() {
		return tab;
	}



	/**
	 * 
	 * @return Sql-statement to build this record
	 */
	public String get_sql4query() {
		return sql4query;
	}
	
	

	
	public boolean is_yes_db_val(IF_Field f) throws myException {
		if (f.generate_MetaFieldDef().is_boolean_single_char() && f._t()==this.tab) {
			return this.get_ufs_dbVal(f, "N").toUpperCase().equals("Y");
		} else {
			throw new myException(this,"Only on Y/N-Fields of Table "+this.tab.n()+" possible");
		}
	}

	
	public boolean is_yes_last_val(IF_Field f) throws myException {
		if (f.generate_MetaFieldDef().is_boolean_single_char() && f._t()==this.tab) {
			return this.get_fs_lastVal(f, "N").toUpperCase().equals("Y");
		} else {
			throw new myException(this,"Only on Y/N-Fields of Table "+this.tab.n()+" possible");
		}
	}

	
	public boolean is_no_db_val(IF_Field f) throws myException {
		return !this.is_yes_db_val(f);
	}

	
	public boolean is_no_last_val(IF_Field f) throws myException {
		return !this.is_yes_last_val(f);
	}

	
	
	/**
	 * 
	 * @return unformated key value when not recordnew, when new then null
	 * @throws myException
	 */
	public String get_key_value() throws myException {
		if (!this.is_newRecordSet()) {
			return this.get_ufs_dbVal(this.tab.key());
		}
		return null;
	}
	
	
	/**
	 * new heisst: es gibt keine resultvalues in den Rec20_fields
	 * @return
	 */
	public boolean is_newRecordSet() {
		
		for (IF_Field f: this.keySet()) {
			if (this.get(f).get_result_val()!=null) {
				return false;
			}
		}
		
		return true;
	}
	
	
	/**
	 * der record besitzt daten
	 * @return
	 */
	public boolean is_ExistingRecord() {
		return !this.is_newRecordSet();
	}	
	
	
	public Rec20 _set_to_status_recnew() throws myException {
		for (IF_Field f: this.keySet()) {
			this.get(f)._setResult(null);
		}
		return this;
	}
	
	
	
	public Rec20 _nv(IF_Field f, String formated_value, MyE2_MessageVector mv) throws myException {
		if (!this.keySet().contains(f)) {
			throw new myException(this,"_nv() not possible, field "+f.tnfn()+" is not in this set "+this.tab.n(), f.tnfn());
		}

		this.get(f)._setNewVal(formated_value, mv);
		return this;
	}

	
	public Rec20 _nv(String fieldname, String formated_value, MyE2_MessageVector mv) throws myException {
		IF_Field f = this.find_field(S.NN(fieldname,"@@@@@@"));
		if (f==null) {
			throw new myException(this,"_nv() (1) not possible, field is not in this set!", S.NN(fieldname,"@@@@@@"));
		}
		
		if (!this.keySet().contains(f)) {
			throw new myException(this,"_nv() (2) not possible, field is not in this set!", f.tnfn());
		}

		this.get(f)._setNewVal(formated_value, mv);
		return this;
	}
	
	public Rec20 _check_nv(IF_Field f, String formated_value, MyE2_MessageVector mv) throws myException {
		if (!this.keySet().contains(f)) {
			throw new myException(this,"_nv() not possible, field is not in this set!", f.tnfn());
		}

		this.get(f)._checkNewVal(formated_value, mv);
		return this;
	}

	
	public Rec20 _check_nv(String fieldname, String formated_value, MyE2_MessageVector mv) throws myException {
		IF_Field f = this.find_field(S.NN(fieldname,"@@@@@@"));
		if (f==null) {
			throw new myException(this,"_nv() (1) not possible, field is not in this set!", S.NN(fieldname,"@@@@@@"));
		}
		
		if (!this.keySet().contains(f)) {
			throw new myException(this,"_nv() (2) not possible, field is not in this set!", f.tnfn());
		}

		this.get(f)._checkNewVal(formated_value, mv);
		return this;
	}

	
	public HashMap<String, String> get_hm_Field_Value_pairs_from_outside() {
		return this.hm_Field_Value_pairs_from_outside;
	}

	
	
	public Rec20 _add_sequencer() throws myException {
		this.hm_Field_Value_pairs_from_outside.put(tab.keyFieldName(), tab.seq_nextval());
		return this;
	}
	
	public Rec20 _add_id_mandant() throws myException {
		this.hm_Field_Value_pairs_from_outside.put(this.find_field_exception(AUTOMATC_FIELDS.ID_MANDANT.name()).fn(), bibALL.get_ID_MANDANT());
		return this;
	}
	
	public Rec20 _add_timestamp() throws myException {
		this.hm_Field_Value_pairs_from_outside.put(this.find_field_exception(AUTOMATC_FIELDS.LETZTE_AENDERUNG.name()).fn(), "SYSDATE");
		return this;
	}
	
	public Rec20 _add_user() throws myException {
		this.hm_Field_Value_pairs_from_outside.put(this.find_field_exception(AUTOMATC_FIELDS.GEAENDERT_VON.name()).fn(), bibALL.MakeSql(bibALL.get_KUERZEL()));
		return this;
	}

	
	/**
	 * 2017-01-31: martin: neue add-methode fuer raw-fieldvals
	 */
	public Rec20 _add_field_val_pair(IF_Field field, String val) throws myException {
		this.hm_Field_Value_pairs_from_outside.put(this.find_field_exception(field.fieldName()).fn(), val);
		return this;
	}

	/**
	 * 2017-08-08: martin: neue add-methode fuer raw-fieldvals
	 */
	public Rec20 _add_field_val_pair(String fieldName, String val) throws myException {
		this.hm_Field_Value_pairs_from_outside.put(this.find_field_exception(fieldName).fn(), val);
		return this;
	}


	/**
	 * 20171220: synonyme raw-fields
	 * @throws myException 
	 */
	public Rec20 _setNewValueInDatabaseTerminus(IF_Field field, String val) throws myException {
		return this._add_field_val_pair(field,val);
	}
	
//	public void set_NewValueForDatabaseRaw(IF_Field field, String val) throws myException {
//		this._add_field_val_pair(field,val);
//	}

//	public Rec20 _nvRaw(String field, String val) throws myException {
//		return this._add_field_val_pair(field,val);
//	}
	
	public Rec20 _setNewValueInDatabaseTerminus(String field, String val) throws myException {
		return this._add_field_val_pair(field,val);
	}
	
	
	/**
	 * allgemeiner statement-builder
	 * @param fields_to_exclude
	 * @param append_only_new_values
	 * @param mv  (can be null, when null, in case of not fitting it throws myException)
	 * @return
	 * @throws myException when field is not fitting
	 */
	public MySqlStatementBuilder  get_stmbd(Vector<String> fields_to_exclude, boolean append_only_new_values, MyE2_MessageVector mv) throws myException {
		
		
		MySqlStatementBuilder stmb = new MySqlStatementBuilder();
		
		stmb.set_cTableName(this.tab.fullTableName());
		
		Vector<String> exclude = new Vector<>();
		if (fields_to_exclude!=null) {
			exclude.addAll(fields_to_exclude);
		}
		
		//jetzt mit den Werten der map fuellen
		for (IF_Field f:  this.keySet()) {
			Rec20_field rf = this.get(f);
			
			if (!exclude.contains(f.fn())) {
				if (append_only_new_values) {
					if (rf.is_new_value_set()) {
						if (!this.hm_Field_Value_pairs_from_outside.containsKey(f.fn())) {                //sonst wurde ein raw-wert eingefuegt
							stmb.addSQL_Paar(rf.get_field().fn(), rf.get_value4sql_statements(mv),false);
						}
					}
				} else {
					if (!this.hm_Field_Value_pairs_from_outside.containsKey(f.fn())) {                //sonst wurde ein raw-wert eingefuegt
						stmb.addSQL_Paar(rf.get_field().fn(), rf.get_value4sql_statements(mv),false);
					}
				}
			}
		}

		//die sonderfelder dazu
		for (String field: this.hm_Field_Value_pairs_from_outside.keySet()) {
			if (!exclude.contains(field)) {
				stmb.addSQL_Paar(field, this.hm_Field_Value_pairs_from_outside.get(field), false);
			}
		}
		
		return stmb;
	}
	
	
	
	
	/**
	 * allgemeiner statement-builder
	 * @param mv  (can be null, when null, in case of not fitting it throws myException)
	 * @param fields_to_exclude
	 * @param append_only_new_values
	 * @return
	 * @throws myException when field is not fitting
	 */
	public MySqlStatementBuilder  get_stmbd(Vector<String> fields_to_exclude, boolean append_only_new_values) throws myException {
		return this.get_stmbd(fields_to_exclude, append_only_new_values,null);
	}


	/**
	 * 
	 * @return true, when some new value was set
	 */
	public boolean is_even_one_new_value_set() {
		
		for (IF_Field f: this.keySet()) {
			if (this.get(f).is_new_value_set()) {
				return true;
			}
		}
		
		if (this.hm_Field_Value_pairs_from_outside.size()>0) {
			return true;
		}
		
		return false;
	}
	
	
	public MySqlStatementBuilder get_stmbd_std() throws myException {
		return this.get_stmbd(Rec20.get_AutomaticWrittenFields_STD(), true);
	}
	
	
	public MySqlStatementBuilder get_stmbd_std(Vector<String> v_fields_to_exclude) throws myException {
		return this.get_stmbd(v_fields_to_exclude, true);
	}
	
	
	
	
	public static Vector<String> get_AutomaticWrittenFields_STD() {
		return DB_STATICS.get_AutomaticWrittenFields_STD();
	}
 
	
	/**
	 * es wird ein sql-statemtent definiert, das abhaengt davon, ob der datensatz neu oder veraendert.
	 * Bei neuen wird das komplette set gespeichert  (alle felder), bei updates nur geaenderte.
	 * Die definition, welche felder ausgelassen werden (automatische felder bei der erzeugung des statements)
	 * haengt davon ab, ob es eine MyDBToolbox gibt oder ob er sich eine beschafft. Mit einer eigenen 
	 * MyDBToolBox kann die anzahl der automatikfelder definiert werden
	 * @param b_return_null_when_nothingtosave
	 * @param mv  (can be null, then throws myExceptipn
	 * @return
	 * @throws myException
	 */
	public String get_sql_4_save(boolean b_return_null_when_nothingtosave, MyE2_MessageVector mv) throws myException {
		String sql = null;
		
		if (this.is_newRecordSet()) {
			
			//dann gibt es ein insert
			
			if (!this.get(this.tab.key()).is_newValPresent()) {
				if (this.hm_Field_Value_pairs_from_outside.get(this.tab.keyFieldName())==null) {
					//dann wird ein sequencer eingefuegt
					this._add_sequencer();
				}
			}
			//hier darf auch die id nicht in den statements auftauchen
			Vector<String> v_exclude = new Vector<>(Rec20.get_AutomaticWrittenFields_STD());
			if (this.oDB!=null) {
				v_exclude = this.oDB.get_AutomaticWrittenFields();
			}

			MySqlStatementBuilder stb = this.get_stmbd(v_exclude,false,mv);
			sql = stb.get_CompleteInsertString(this.tab.fullTableName());
		} else {

			//hier darf auch die id nicht in den statements auftauchen
			Vector<String> v_exclude = new Vector<>(Rec20.get_AutomaticWrittenFields_STD());
			if (this.oDB!=null) {
				v_exclude = this.oDB.get_AutomaticWrittenFields();
			}
			v_exclude.add(this.tab.keyFieldName());

			//sonst ein update
			//zuerst checken, ob es veraenderte felder gibt
			if (this.is_even_one_new_value_set()) {
				//dann nur die veraenderten	aufbauen
				MySqlStatementBuilder stb = this.get_stmbd(v_exclude, true,mv);
				Rec20_field keyfield = this.get(this.tab.key());
				String id_val = keyfield.get_result_val().get_FieldValueUnformated();
				MyLong lid = new MyLong(id_val);
				if (!lid.isOK()) {
					throw new myException(this,"No correct id-value found !! Cannot create an update-statement !");
				}
				
				sql = stb.get_CompleteUPDATEString(this.tab.fullTableName(), bibE2.cTO(), this.tab.keyFieldName()+"="+id_val, null);
				
			} else {
				//sonst alle felder mitnehmen
				MySqlStatementBuilder stb = this.get_stmbd(v_exclude, false,mv);
				Rec20_field keyfield = this.get(this.tab.key());
				String id_val = keyfield.get_result_val().get_FieldValueUnformated();
				MyLong lid = new MyLong(id_val);
				if (!lid.isOK()) {
					throw new myException(this,"No correct id-value found !! Cannot create an update-statement !");
				}
				
				if (b_return_null_when_nothingtosave) {
					sql=null;
				} else {
					sql = stb.get_CompleteUPDATEString(this.tab.fullTableName(), bibE2.cTO(), this.tab.keyFieldName()+"="+id_val, null);
				}
			}
			
		}
		
		return sql;
	}

	/**
	 * es wird ein sql-statemtent definiert, das abhaengt davon, ob der datensatz neu oder veraendert.
	 * Bei neuen wird das komplette set gespeichert  (alle felder), bei updates nur geaenderte.
	 * Die definition, welche felder ausgelassen werden (automatische felder bei der erzeugung des statements)
	 * haengt davon ab, ob es eine MyDBToolbox gibt oder ob er sich eine beschafft. Mit einer eigenen 
	 * MyDBToolBox kann die anzahl der automatikfelder definiert werden
	 * @param b_return_null_when_nothingtosave
	 * @return
	 * @throws myException
	 */
	public String get_sql_4_save(boolean b_return_null_when_nothingtosave) throws myException {
		return this.get_sql_4_save(b_return_null_when_nothingtosave,null);
	}
	
	
	/**
	 * 2017-10-13: aenderung: der messagevektor wird nur noch als sammler benutzt, 
	 *             jeder ablauf innen bekommt einen eigenen
	 * @param b_commit
	 * @param mv_from_call
	 * @return
	 * @throws myException
	 */
	public Rec20 _SAVE(boolean b_commit, MyE2_MessageVector mv_from_call) throws myException {
		MyE2_MessageVector  mv = new MyE2_MessageVector();
		boolean toolbox_created = false;
		if (this.oDB==null) {
			this.oDB = bibALL.get_myDBToolBox();
			toolbox_created=true;
		}

		Vector<String>  v_sql = new Vector<>();
		//20180307: fehler bei uebergabe des messagevektors mv, wurde hier nicht beruechsichtigt
		//ALT: String  c_sql = this.get_sql_4_save(true);
		//NEU
		String  c_sql = this.get_sql_4_save(true,mv);
		//
		
		//20180312: fehler: hier kann die mv bereits einen fehler enthalten: dann abbruch
		if (mv.get_bIsOK()) {
			
			if (S.isFull(c_sql)) {
				v_sql.add(c_sql);
				
				try {
					
					//DEBUG.System_print(v_sql, "-------------Statements in Rec20: "+this.tab.fullTableName()+"   ---------------------------", DEBUG_FLAGS.MARTINS_EIGENER);
	
					
					mv.add_MESSAGE(this.oDB.ExecMultiSQLVector(v_sql, false));
					if (this.is_newRecordSet()) {
						//20180108:bugfix fuer direkt uebergebene keys
						String id = this.getIdFromNewDataset();
						
						//String id = this.oDB.EinzelAbfrage(this.tab.sql_currval());
						this.rec_after_save_new = new Rec20(this.tab)._fill_id(id);
					}
				} catch (Exception e1) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("SQL-Fehler !")));
					e1.printStackTrace();
				}
				
		        if (b_commit || mv.get_bHasAlarms())  {
			        // jetzt pruefen, ob commit oder rollback
			        try {
			            if (mv.get_bIsOK()) {
			            	this.oDB.Commit();
			            } else {
			            	this.oDB.ownRollBack();
			            	bibALL.reset_Trigger_Counter();
			            }
			        } catch (SQLException e) {
			            mv.add_MESSAGE(new MyE2_Alarm_Message(e.getLocalizedMessage(),false), false);
			        }
		        }
			}
		}
        if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
        
        mv_from_call.add_MESSAGE(mv);
        
		return this;
	}
	
	
	/**
	 * prueft alle varianten der moeglichkeiten der id eines neuen datensatzes 
	 * @return
	 * @throws myException
	 */
	protected String getIdFromNewDataset() throws myException {
		MyLong id = null;
		if (this.get(this.tab.key()).is_newValPresent()) {
			//als standard-feldeintrag im newVal
			id = new MyLong(this.get(this.tab.key()).get_new_val_formated_string());
		} else {
			if (S.isFull(this.hm_Field_Value_pairs_from_outside.get(this.tab.keyFieldName()))) {
				//wenn raw als zusatzwert
				if (this.hm_Field_Value_pairs_from_outside.get(this.tab.keyFieldName()).toUpperCase().contains("NEXTVAL")){
					id = new MyLong(this.oDB.EinzelAbfrage(this.tab.sql_currval()));
				} else {
					id =  new MyLong(this.hm_Field_Value_pairs_from_outside.get(this.tab.keyFieldName()));
				}
			}
		}

		if (id == null || id.isNotOK()) {
			throw new myException(this,"Cannot query id of new record !");
		}
		return id.get_cUF_LongString();
	}
	
	/**
	 * @deprecated  use _SaveCleanRebuild 
	 * 20170809: martin
	 * @param b_commit
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public Rec20 _SaveCleanReload(boolean b_commit, MyE2_MessageVector mv) throws myException {
		this._SAVE(b_commit, mv);
		this.RESET_ALL_NEWVALUES();
		return this._rebuild();
	}
	

	
	/**
	 * 20170809: martin
	 * @param b_commit
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public Rec20 _SaveAndClean(boolean b_commit, MyE2_MessageVector mv) throws myException {
		this._SAVE(b_commit, mv);
		this.RESET_ALL_NEWVALUES();
		return this;
	}

	/**
	 * 20170809: martin
	 * @param b_commit
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public Rec20 _SaveCleanRebuild(boolean b_commit, MyE2_MessageVector mv) throws myException {
		this._SAVE(b_commit, mv);
		this.RESET_ALL_NEWVALUES();
		return this._rebuildRecord();
	}
	

	
	/**
	 * 
	 * @param field
	 * @return formated String from result or null
	 * @throws myException
	 */
	public String get_fs_dbVal(IF_Field field) throws myException {
		this.is_field_in_collection_and_not_null(field);
		return this.get(field).get_database_value_f();
	}

	/**
	 * 
	 * @param field
	 * @return formated String from result or null
	 * @throws myException
	 */
	public String get_ufs_dbVal(IF_Field field) throws myException {
		this.is_field_in_collection_and_not_null(field);
		return this.get(field).get_database_value_uf();
	}

	
	/**
	 * kurzform von get_ufs_dbVal
	 * @param field
	 * @return
	 * @throws myException
	 */
	public String getUfs(IF_Field field) throws myException {
		return this.get_ufs_dbVal(field);
	}
	
	
	/**
	 * kurzform von get_ufs_dbVal
	 * @param field
	 * @param nulVal
	 * @return
	 * @throws myException
	 */
	public String getUfs(IF_Field field, String nulVal) throws myException {
		return this.get_ufs_dbVal(field, nulVal);
	}
	

	
	/**
	 * @param fields (das erste nicht leere wird zurueckgegeben)
	 * @return
	 * @throws myException
	 */
	public String getUfs(IF_Field ... fields) throws myException {
		for (IF_Field f: fields) {
			String s= this.get_ufs_dbVal(f);
			if (S.isFull(s)) {
				return s;
			}
		}
		return null;
	}
	
	/**
	 * @param fields (das erste nicht leere wird zurueckgegeben)
	 * @param ersatzfield, when fieldval null
	 * @return
	 * @throws myException
	 */
	public String getUfs(String whenEmptyOrError,IF_Field ... fields) {
		try {
			for (IF_Field f: fields) {
				String s= this.get_ufs_dbVal(f);
				if (S.isFull(s)) {
					return s;
				}
			}
			return whenEmptyOrError;
		} catch (myException e) {
			e.printStackTrace();
			return whenEmptyOrError;
		}
	}

	
	
	/**
	 * 
	 * @param field
	 * @return formated String from result or null
	 * @throws myException
	 */
	public String get_fs_dbVal(IF_Field field, String valWhenNull) throws myException {
		return S.NN(this.get_fs_dbVal(field),valWhenNull);
	}

	
	/**
	 * kurzform von get_fs_dbVal
	 * @param field
	 * @return
	 * @throws myException
	 */
	public String getFs(IF_Field field) throws myException {
		return this.get_fs_dbVal(field);
	}
	

	
	/**
	 * kurzform von get_fs_dbVal
	 * @param field
	 * @param nulVal
	 * @return
	 * @throws myException
	 */
	public String getFs(IF_Field field, String nulVal) throws myException {
		return this.get_fs_dbVal(field, nulVal);
	}
	
	
	
	//201805028: zwei getter ohne exception

	/**
	 * 
	 * @param field
	 * @param nulVal
	 * @param valIfException
	 * @return
	 */
	public String getUfs(IF_Field field, String nulVal, String valIfException) {
		try {
			return this.get_ufs_dbVal(field, nulVal);
		} catch (myException e) {
			e.printStackTrace();
			return valIfException;
		}
	}

	/**
	 * 
	 * @param field
	 * @param nulVal
	 * @param valIfException
	 * @return
	 */
	public String getFs(IF_Field field, String nulVal, String valIfException) {
		try {
			return this.get_fs_dbVal(field, nulVal);
		} catch (myException e) {
			e.printStackTrace();
			return valIfException;
		}
	}
	

	//-----------------------------------------------
	
	
	
	
	/**
	 * 
	 * @param field
	 * @return formated String from result or null
	 * @throws myException
	 */
	public String get_ufs_dbVal(IF_Field field, String valWhenNull) throws myException {
		return S.NN(this.get_ufs_dbVal(field),valWhenNull);
	}


	
	
	/**
	 * 
	 * @param field
	 * @return formated String from result or null
	 * @throws myException
	 */
	public String get_fs_lastVal(IF_Field field) throws myException {
		this.is_field_in_collection_and_not_null(field);
		return this.get(field).get_last_value_f();
	}

	
	/**
	 * unformated db_value or the last new_val, which is set to this field (when empty, then null)
	 * @author manfred
	 * @date 08.09.2020
	 *
	 * @param field
	 * @return
	 * @throws myException
	 */
	public String get_ufs_lastVal(IF_Field field) throws myException{
		this.is_field_in_collection_and_not_null(field);
		return this.get(field).get_last_value_uf();
	}
	
	
	/**
	 * 
	 * @param field
	 * @return formated String from result or null
	 * @throws myException
	 */
	public String get_fs_lastVal(IF_Field field, String valWhenNull) throws myException {
		return S.NN(this.get_fs_lastVal(field),valWhenNull);
	}


	
	public MyLong get_myLong_dbVal(IF_Field field)  throws myException {
		this.is_field_in_collection_and_not_null(field);
		return this.get(field).get_MyLong_dbVal();
	}
	
	public MyLong get_myLong_lastVal(IF_Field field)  throws myException {
		this.is_field_in_collection_and_not_null(field);
		return this.get(field).get_MyLong_lastVal();
	}
	
	
	public MyDate get_myDate_dbVal(IF_Field field)  throws myException {
		this.is_field_in_collection_and_not_null(field);
		return this.get(field).get_MyDate_dbVal();
	}
	
	public MyDate get_myDate_lastVal(IF_Field field)  throws myException {
		this.is_field_in_collection_and_not_null(field);
		return this.get(field).get_MyDate_lastVal();
	}

	
	public MyBigDecimal get_myBigDecimal_dbVal(IF_Field field)  throws myException {
		this.is_field_in_collection_and_not_null(field);
		return this.get(field).get_MyBigDecimal_dbVal();
	}
	
	public MyBigDecimal get_myBigDecimal_lastVal(IF_Field field)  throws myException {
		this.is_field_in_collection_and_not_null(field);
		return this.get(field).get_MyBigDecimal_lastVal();
	}

	/**
	 * 
	 * @param field
	 * @return last loaded database-value or null when no BigDecimal
	 * @throws myException
	 */
	public MyBigDecimal getMyBdOld(IF_Field field)  throws myException {
		return this.get_myBigDecimal_dbVal(field);
	}
	
	/**
	 * 
	 * @param field
	 * @param bdWhenNull
	 * @return last loaded database-value or bdWhenNull when no BigDecimal
	 * @throws myException
	 */
	public MyBigDecimal getMyBdOld(IF_Field field, MyBigDecimal bdWhenNull)  throws myException {
		return this.get_myBigDecimal_dbVal(field, bdWhenNull);
	}
	
	
	/**
	 * 
	 * @param field
	 * @return last given value (even not persitent) or database-value or null
	 * @throws myException
	 */
	public MyBigDecimal getMyBdActual(IF_Field field)  throws myException {
		return this.get_myBigDecimal_lastVal(field);
	}

	
	/**
	 * 
	 * @param field
	 * @param bdWhenNull
	 * @return last given value (even not persitent) or database-value or bdWhenNull
	 * @throws myException
	 */
	public MyBigDecimal getMyBdActual(IF_Field field, MyBigDecimal bdWhenNull)  throws myException {
		return this.get_myBigDecimal_lastVal(field,bdWhenNull);
	}

	
	
	/*
	 * 2017-01-03: getter-varianten mit null-ersatzwert
	 */
	public MyLong get_myLong_dbVal(IF_Field field, MyLong whenNull)  throws myException {
		this.is_field_in_collection_and_not_null(field);
		return this.get(field).get_MyLong_dbVal(whenNull);
	}
	
	public MyLong get_myLong_lastVal(IF_Field field, MyLong whenNull)  throws myException {
		this.is_field_in_collection_and_not_null(field);
		return this.get(field).get_MyLong_lastVal(whenNull);
	}
	
	
	public MyDate get_myDate_dbVal(IF_Field field, MyDate whenNull)  throws myException {
		this.is_field_in_collection_and_not_null(field);
		return this.get(field).get_MyDate_dbVal(whenNull);
	}
	
	public MyDate get_myDate_lastVal(IF_Field field, MyDate whenNull)  throws myException {
		this.is_field_in_collection_and_not_null(field);
		return this.get(field).get_MyDate_lastVal(whenNull);
	}

	
	public MyBigDecimal get_myBigDecimal_dbVal(IF_Field field, MyBigDecimal whenNull)  throws myException {
		this.is_field_in_collection_and_not_null(field);
		return this.get(field).get_MyBigDecimal_dbVal(whenNull);
	}
	
	public MyBigDecimal get_myBigDecimal_lastVal(IF_Field field, MyBigDecimal whenNull)  throws myException {
		this.is_field_in_collection_and_not_null(field);
		return this.get(field).get_MyBigDecimal_lastVal(whenNull);
	}

	
	/*
	 * Ende 2017-01-03: getter-varianten mit null-ersatzwert
	 */
	
	
	
	/**
	 * 
	 * @param field
	 * @return String for SQL-statement
	 * @throws myException
	 */
	public String get_value4sql_statements(IF_Field field) throws myException {
		this.is_field_in_collection_and_not_null(field);
		return this.get(field).get_value4sql_statements(null);
	}

	
	
	
	private boolean	is_field_in_collection_and_not_null(IF_Field field) throws myException {
		if (field == null) {
			throw new myException(this,"Field MUST NOT BE null in Rec20!");
		}
		if (!this.containsKey(field) || this.get(field)==null) {
			throw new myException(this,field.fn()+" not in Rec20!");
		}
		return true;
	}
	
	
	

	
	
	
	
	
	


	
	//ab hier methoden von MyRECORD_IF_FILLABLE
	/**
	 * @deprecated use the _setNewValue -methods
	 */
	@Deprecated
	@Override
	public MyE2_MessageVector set_NewValueForDatabase(String FIELDNAME, String cNewValueFormated) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		IF_Field field = this.find_field_exception(FIELDNAME);   //falls das feld nicht gefunden wird, exception
		this._nv(field, cNewValueFormated, mv);
		return mv;
	}



	/**
	 * @deprecated use the _setNewValue -methods
	 */
	@Deprecated
	@Override
	public MyE2_MessageVector check_NewValueForDatabase(String FIELDNAME, String cNewValueFormated) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		IF_Field field = this.find_field_exception(FIELDNAME);
		this._check_nv(field, cNewValueFormated, mv);
		return mv;
	}



	/**
	 * @deprecated use the _setNewValue -methods
	 */
	@Deprecated
	@Override
	public MyE2_MessageVector set_NewValueForDatabase(String FIELDNAME, long longVal) throws myException {
		return this.set_NewValueForDatabase(FIELDNAME, ""+longVal);
	}



	/**
	 * @deprecated use the _setNewValue -methods
	 */
	@Deprecated
	@Override
	public MyE2_MessageVector set_NewValueForDatabase(String FIELDNAME, double dNewValueFormated) throws myException {
		IF_Field field = this.find_field_exception(FIELDNAME);
		return set_NewValueForDatabase(FIELDNAME, MyNumberFormater.formatDez(dNewValueFormated, this.get(field).get_metadef().get_FieldDecimals(),false));		
	}



	/**
	 * @deprecated use the _setNewValue -methods
	 */
	@Deprecated
	@Override
	public MyE2_MessageVector set_NewValueForDatabase(String FIELDNAME, BigDecimal bdNewValueFormated)	throws myException {
		if (bdNewValueFormated==null) {
			return this.set_NewValueForDatabase(FIELDNAME, "");
		} else {
			IF_Field field = this.find_field_exception(FIELDNAME);
			return set_NewValueForDatabase(FIELDNAME, MyNumberFormater.formatDez(bdNewValueFormated, this.get(field).get_metadef().get_FieldDecimals(),true));		
		}
	}



	/**
	 * @deprecated use the _setNewValue -methods
	 */
	@Deprecated
	@Override
	public MyE2_MessageVector set_NewValueForDatabase(String FIELDNAME, GregorianCalendar calNewValueFormated)	throws myException {
		if (calNewValueFormated==null) {
			return this.set_NewValueForDatabase(FIELDNAME, "");
		} else {
			return set_NewValueForDatabase(FIELDNAME, myDateHelper.FormatDateNormal(calNewValueFormated.getTime()));		
		}

	}

	
	
	/**
	 * neue methoden fuer die string-befuellung
	 */
	public Rec20 _setNewValue(IF_Field field, String s_newValueFormated, MyE2_MessageVector mv) throws myException {
		return this._nv(field, s_newValueFormated, (mv == null ? bibMSG.newMV():mv));
	}
	
	public Rec20 _setNewValue(String fieldName, String s_newValueFormated, MyE2_MessageVector mv) throws myException {
		return this._setNewValue(this.find_field_exception(fieldName), s_newValueFormated, mv);
	}

	public Rec20 _setNewValue(IF_Field field, Long longVal, MyE2_MessageVector mv) throws myException {
		return this._nv(field, longVal==null?"":longVal.toString(), (mv == null ? bibMSG.newMV():mv));
	}
	
	public Rec20 _setNewValue(String fieldName, Long longVal, MyE2_MessageVector mv) throws myException {
		return this._setNewValue(this.find_field_exception(fieldName), longVal, mv);
	}

	public Rec20 _setNewValue(IF_Field field, BigDecimal bdVal, MyE2_MessageVector mv) throws myException {
		return this._nv(field, bdVal==null?"": MyNumberFormater.formatDez(bdVal, this.get(field).get_metadef().get_FieldDecimals(),false), (mv == null ? bibMSG.newMV():mv));
	}
	
	public Rec20 _setNewValue(String fieldName, BigDecimal bdVal, MyE2_MessageVector mv) throws myException {
		return this._setNewValue(this.find_field_exception(fieldName), bdVal, mv);
	}

	public Rec20 _setNewValue(IF_Field field, Date date, MyE2_MessageVector mv) throws myException {
		return this._nv(field, date==null?"":myDateHelper.FormatDateNormal(date), (mv == null ? bibMSG.newMV():mv));
	}
	
	public Rec20 _setNewValue(String fieldName,  Date date, MyE2_MessageVector mv) throws myException {
		return this._setNewValue(this.find_field_exception(fieldName), date, mv);
	}


	


	@Override
	public MySqlStatementBuilder get_StatementBuilder(boolean bExcludeAutomaticFields) throws myException {
		if (bExcludeAutomaticFields) {
			Vector<String>  v_automatic_fields = Rec20.get_AutomaticWrittenFields_STD();
			if (this.oDB!=null) {
				v_automatic_fields=this.oDB.get_AutomaticWrittenFields();
			}
			return this.get_stmbd(v_automatic_fields, false);
		} else {
			return this.get_stmbd(null, false);
		}
	}



	@Override
	public String get_TABLENAME() throws myException {
		return this.tab.fullTableName();
	}



	@Override
	public String get_PRIMARY_KEY_NAME() throws myException {
		return this.tab.keyFieldName();
	}



	@Override
	public boolean get_bHasSomething_to_save() throws myException {
		boolean b_something_new = false;
		for (Rec20_field rf: this.values()) {
			if (rf.is_new_value_set()) {
				b_something_new=true;
				break;
			}
		}
		return b_something_new;
	}



	@Override
	public HashMap<String, String> get_hm_InputValuesFormated() throws myException {
		HashMap<String, String>  hm = new HashMap<>();
		for (IF_Field f: this.keySet()) {
			hm.put(f.fieldName(), this.get(f).get_new_val_formated_string());
		}
		return hm;
	}


	public HashMap<String, MyMetaFieldDEF> get_hm_metaFieldDefs() throws myException {
		HashMap<String, MyMetaFieldDEF>  hm = new HashMap<>();
		for (IF_Field f: this.keySet()) {
			hm.put(f.fieldName(), this.get(f).get_metadef());
		}
		return hm;
	}

	
	/**
	 * 
	 * @param field
	 * @param val
	 * @param wrapInApostroph
	 * @return
	 * @throws myException
	 */
	public  Rec20 _put_raw_value(IF_Field field, String val, boolean wrapInApostroph) throws myException {
		this.get_hm_Field_Value_pairs_from_outside().put(field.fieldName(),(wrapInApostroph?bibALL.MakeSql(val):val));
		return this;
	}

	
	

	@Override
	public void RESET_ALL_NEWVALUES() throws myException {
		for (Rec20_field rf: this.values()) {
			rf._resetNewVal();
		}
		//die sonderfelder auch loeschen
		this.hm_Field_Value_pairs_from_outside.clear();
	}
	
	
	/**
	 * 
	 * @return MyRECORD of dataset, can be casted to i.e. RECORD_ADRESSE
	 *     If its a new Record(), then an empty Record is returned
	 * @throws myException
	 */
	public MyRECORD gen_record(boolean null_if_newset) throws myException {
		if (this.is_newRecordSet()) {
			if (null_if_newset) {
				return null;
			} else {
				return this.tab.get_RECORD();
			}
		} else {
			
			MyRECORD record = this.tab.get_RECORD();
			for (IF_Field f: this.keySet()) {
				record.put(f.fieldName(), this.get(f).get_result_val());
			}
			record.set_cSQL_4_Build(this.sql4query);
			return record;
		}
	}

	
	
	
	public MyRECORD_NEW  gen_record_new() throws myException {
		return new MyRECORD_NEW(this.tab.fullTableName(), this.get_hm_metaFieldDefs());
	}



	
	
	
	/**
	 * @param oVGL
	 * @return
	 */
	public boolean compareTo(Rec20 oVGL, HashMap<String, String> hmDifferents) {
		if (oVGL != null)	{
		
			// groessen-vergleich
			if (oVGL.entrySet().size() != this.entrySet().size()) {
				hmDifferents.put(this.tab.fullTableName(), "Different Fieldnumbers!");
			}
			if (this.tab!=oVGL.tab) {
				hmDifferents.put(this.tab.fullTableName(), "Different Tables "+this.tab.fullTableName()+"<->"+oVGL.tab.fullTableName());
			}

			if (this.is_newRecordSet() || oVGL.is_newRecordSet()) {
				hmDifferents.put(this.tab.fullTableName(), "Only existing records can be compared , no new Records !");
			}
			
			String tablename = this.tab.fullTableName();

			for (IF_Field  field: this.keySet()) {
				if (oVGL.get(field)==null) {
					hmDifferents.put(tablename+":"+field.fieldName(), "Field not in other Rec20");
				}
				
//				if (field==BEWEGUNG_ATOM.e_preis) {
//					DEBUG.System_println("stop ...");
//				}
				
				MyResultValue oResultSetToCompare = 	this.get(field).get_result_val();
				MyResultValue oResultSetOwn   = 		oVGL.get(field).get_result_val();
				    
			    if (oResultSetToCompare != null || oResultSetOwn != null)  {

			    	if (oResultSetToCompare == null && oResultSetOwn != null) {
				    	hmDifferents.put(tablename+":"+field.fn(), "Field not in other RECORD");
			    	}
						    
				    if (oResultSetToCompare != null && oResultSetOwn == null) {
				    	hmDifferents.put(tablename+":"+field.fn(), "Field not in this RECORD");
				    }
						    
				    // zum vergleich wird der unformatierte string rangezogen
			    	String ccHmVGLValue = 	oResultSetToCompare.get_FieldValueUnformated();
			    	String ccOwnValue = 	oResultSetOwn.get_FieldValueUnformated();
					    	
			    	if (ccHmVGLValue != null || ccOwnValue != null)	{
				    	if (ccHmVGLValue == null && ccOwnValue != null) {
				    		hmDifferents.put(tablename+":"+field.fn(), "other: NULL"+			":   own: "+ccOwnValue);
				    	}
							    
					    if (ccHmVGLValue != null && ccOwnValue == null) {
				    		hmDifferents.put(tablename+":"+field.fn(), "other: "+ccHmVGLValue+	":   own: NULL");
					    }
			    		
					    if (!ccHmVGLValue.equals(ccOwnValue)) {
				    		hmDifferents.put(tablename+":"+field.fn(), "other: "+ccHmVGLValue+	":   own: "+ccOwnValue);
					    }
	
			    	}
				}
			}
		}
		return (hmDifferents.size()==0);

	}
	
	
	public boolean compare_to_actual(HashMap<String, String> hm_diffs) throws myException {
		if (this.is_newRecordSet()) {
			throw new myException(this,"Compare to actual is only possible on an existing record !");
		}
		Rec20 recComp = new Rec20(this.tab)._fill_id(this.get_key_value());
		return this.compareTo(recComp, hm_diffs);
	}



	public MyDBToolBox get_oDB() {
		return oDB;
	}



	/**
	 * bei eigen geschriebene datensaetzen steht hier der neu geschriebene record
	 * @return
	 */
	public Rec20 get_rec_after_save_new() {
		return rec_after_save_new;
	}
	
	/**
	 * 
	 * @param my_field_related_key_of_mother_table = (field in own table:) schluessel der lookup-tabelle in der eigenen tabellen
	 * @param key_of_mother_table = (field in lookup-table:)schluessel in der lookup-tabelle
	 * 
	 * @return
	 * @throws myException if field in owntable is null  or false tablerelation 
	 */
	@Deprecated
	public Rec20 get_up_rec20(IF_Field my_field_related_key_of_mother_table, IF_Field key_of_mother_table, boolean b_buildNew) throws myException {
		if (my_field_related_key_of_mother_table == null || key_of_mother_table == null) {
			throw new myException(this,"both fields MUST NOT be NULL !!");
		}
		
		if (this.is_newRecordSet()) {
			throw new myException(this,"get_up_rec20() not possible on new Rec20()");
		}
		if (my_field_related_key_of_mother_table._t()==this.tab) {
			//nachsehen, ob der record gecashed ist
			Rec20 r_cash = this.hm_upRecords.get(my_field_related_key_of_mother_table);
			if (r_cash==null || b_buildNew) {
				r_cash = new Rec20(key_of_mother_table._t())._fill_id(this.get_ufs_dbVal(my_field_related_key_of_mother_table));
				this.hm_upRecords.put(my_field_related_key_of_mother_table, r_cash);
			}
			return r_cash;
		} else {
			throw new myException(this,"First key MUST be from this table !!");
		}
	}
	
	
	/**
	 * !! geht nur, wenn der name der felder in beiden tabellen gleich is 
	 * @param key_of_mother_table = schluessel in der lookup-tabelle
	 * @return
	 * @throws myException
	 */
	@Deprecated
	public Rec20 get_up_rec20(IF_Field key_of_mother_table) throws myException {
		if (this.is_newRecordSet()) {
			throw new myException(this,"get_up_rec20() not possible on new Rec20()");
		}
		//jetzt nachsehen, ob fuer den fremdkey ein gleichnamiger eigener vorhanden ist
		IF_Field f = this.find_field(key_of_mother_table.fn());
		
		if (f==null) {
			throw new myException(this,"get_up_rec20(): Cannot find field with name :"+key_of_mother_table.fieldName()+" in own record!");
		}
		return this.get_up_rec20(f, key_of_mother_table,false);
	}
	
	
	
	/**
	 * 
	 * @param my_field_related_key_of_mother_table = (field in own table:) schluessel der lookup-tabelle in der eigenen tabellen
	 * @param key_of_mother_table = (field in lookup-table:)schluessel in der lookup-tabelle
	 * @param b_buildNew
	 * @return null, when my_field_related_key_of_mother_table is empty
	 * @throws myException if field in owntable is null  or false tablerelation 
	 */
	public Rec20 get_up_Rec20(IF_Field my_field_related_key_of_mother_table, IF_Field key_of_mother_table, boolean b_buildNew) throws myException {
		Rec20 rec_ret = null;
		
		if (my_field_related_key_of_mother_table == null || key_of_mother_table == null) {
			throw new myException(this,"both fields MUST NOT be NULL !!");
		}
		
		if (this.is_newRecordSet()) {
			throw new myException(this,"get_up_rec20() not possible on new Rec20()");
		}
		
		if (my_field_related_key_of_mother_table._t()==this.tab) {
			//nachsehen, ob der record gecashed ist
			rec_ret = this.hm_upRecords.get(my_field_related_key_of_mother_table);
			if (rec_ret==null || b_buildNew) {
				String id = this.get_ufs_dbVal(my_field_related_key_of_mother_table);
				if (S.isFull(id)) {
					rec_ret = new Rec20(key_of_mother_table._t())._fill_id(id);
					this.hm_upRecords.put(my_field_related_key_of_mother_table, rec_ret);
				} else {
					rec_ret = null;
					this.hm_upRecords.put(my_field_related_key_of_mother_table, rec_ret);
				}
			}
		} else {
			throw new myException(this,"First key MUST be from this table !!");
		}
		
		return rec_ret;
	}


	/**
	 * 
	 * @param key_of_mother_table = (field in lookup-table:)schluessel in der lookup-tabelle // possible, if field in own table has same name
	 * @return
	 * @throws myException
	 */
	public Rec20 get_up_Rec20(IF_Field key_of_mother_table) throws myException {
		if (this.is_newRecordSet()) {
			throw new myException(this,"get_up_rec20() not possible on new Rec20()");
		}
		//jetzt nachsehen, ob fuer den fremdkey ein gleichnamiger eigener vorhanden ist
		IF_Field f = this.find_field(key_of_mother_table.fn());
		
		if (f==null) {
			throw new myException(this,"get_up_rec20(): Cannot find field with name :"+key_of_mother_table.fieldName()+" in own record!");
		}
		return this.get_up_Rec20(f, key_of_mother_table,true);

	}
	
	
	
	
	/**
	 * 
	 * @param key_in_daughter = schluessel in der tochter-tabelle
	 * @param where -null allowed
	 * @param order -null allowed
	 * @param force_rebuild    
	 * @return
	 * @throws myException
	 */
	public RecList20 get_down_reclist20(IF_Field key_in_daughter, String where, String order, boolean force_rebuild) throws myException {
		String key = key_in_daughter.tnfn()+"|"+S.NN(where);
		
		
		RecList20 rl = this.hm_downRecLists.get(key);
		if (rl==null || force_rebuild) {
			rl = new RecList20(key_in_daughter._t());
			
			//falls es ein recnew ist, der bereits abespeichert wurde, dann finden sich die werte im @rec_after_save_new
			
			if (S.isFull(this.get_key_value())) {
				rl._fill(key_in_daughter.tnfn()+"="+this.get_key_value()+(S.isFull(where)?" AND "+where:""), order);
				this.hm_downRecLists.put(key, rl);
			} else {
				if (this.rec_after_save_new!=null && S.isFull(this.rec_after_save_new.get_key_value())) {
					rl._fill(key_in_daughter.tnfn()+"="+this.rec_after_save_new.get_key_value()+(S.isFull(where)?" AND "+where:""), order);
					this.hm_downRecLists.put(key, rl);
				}	
			}
		}
		
		return rl;
	}

	/**
	 * 
	 * @param key_in_daughter = schluessel in der tochter-tabelle
	 * @param where -null allowed
	 * @param order -null allowed
	 * @return
	 * @throws myException
	 */
	public RecList20 get_down_reclist20(IF_Field key_in_daughter, String where, String order) throws myException {
		return this.get_down_reclist20(key_in_daughter, where, order,true);
	}

	public void set_sql4query(String p_sql4query) {
		this.sql4query = p_sql4query;
	}
	
	public String createOwnQuery() throws myException {
		return "SELECT * FROM "+bibE2.cTO()+"."+this.tab.fullTableName()+" WHERE "+this.tab.keyFieldName()+"="+this.get_ufs_dbVal(this.tab.key(), "@@@@@@@@@");
	}
	
	
	
	/**
	 * 
	 * @return s delete-statement or null (if record is new )
	 * @throws myException 
	 */
	public String get_sql_2_delete() throws myException {
		if (this.is_ExistingRecord()) {
			return "DELETE FROM "+bibE2.cTO()+"."+this.tab.fullTableName()+" WHERE "+this.tab.key().tnfn()+"="+this.get_key_value();
		} else {
			return null;
		}
	}
	
	
	public String get_ufs_kette(String separator, IF_Field... fields) throws myException {
		
		VectorNN<String> v_werte = new VectorNN<>();
		
		for (IF_Field f: fields) {
			v_werte.add(this.get_ufs_dbVal(f,""));
		}

		String sep = (separator == null)?" ":separator;
		
		return S.Concatenate(v_werte, sep, "");
	}
	

	public String get_ufs_kette(String separator, boolean excludeEmptyValues, IF_Field... fields) throws myException {
		
		VectorNN<String> v_werte = new VectorNN<>();
		
		for (IF_Field f: fields) {
			if (S.isFull(this.get_ufs_dbVal(f,"")) || (!excludeEmptyValues)) {
				v_werte.add(this.get_ufs_dbVal(f,""));
			}
		}

		String sep = (separator == null)?" ":separator;
		
		return S.Concatenate(v_werte, sep, "");
	}

	
	
	public Rec20_field get_field(IF_Field field) {
		return this.get(field);
	}
	
	
	
	/**
	 * 
	 * @param sfield  
	 * @return Rec20_field  with name sfield (when not found then null) 
	 */
	public Rec20_field get_field(String sfield) {
		IF_Field field = this.find_field(sfield);
		if (field!=null) {
			return this.get(field);
		}
		return null;
	}
	
	
	
	
	/**
	 * 2017-01-13: raw-objekte zurueckgeben
	 * @throws myException 
	 */
	public Object getValue(IF_Field field) throws myException {
		if (this.get(field) != null) {
			return this.get(field).get_result_val().get_oNativeDataObject();
		} else {
			throw new myException(this,"Error: Field:"+field.fn()+" not in this table !");
		}
	}
 
	
	
	/**
	 * 2019-02-11: raw-objekte zurueckgeben 
	 * valWheNull, when empty or error 
	 */
	public Object getValue(IF_Field field,Object valWheNull)  {
		if (this.get(field) != null) {
			return this.get(field).get_result_val().get_oNativeDataObject();
		} else {
			return valWheNull;
		}
	}
 
	
	
	/**
	 * returns last read DateValue or null on error or false type
	 * @author martin
	 * @date 26.09.2019
	 *
	 * @param field
	 * @return
 	 * @throws myException 

	 */
	public Date getDateDbValue(IF_Field field)  throws myException {

		Date retDate = null;

		Object val = this.getValue(field);
		if (val != null && val instanceof Date) {
			retDate = (Date)val;
		}
			
		return retDate;
	}
	
	
	
	/**
	 * 2018-04-06: long-objekte
	 * @throws myException 
	 */
	public Long getLongDbValue(IF_Field field) throws myException {
		if (this.get(field) != null) {
			if (field.generate_MetaFieldDef().get_bIsNumberTypeWithOutDecimals()) {
				Object o = this.getValue(field);
				if (o==null) {
					return (Long)null;
				} else if (o instanceof BigDecimal) {
					return ((BigDecimal)o).longValue();
				} else if (o instanceof Long) {
					return ((Long)o);
				}
				throw new myException(this, "You asked for Long-Value, but real result is not compatible (1)!");
			} else {
				throw new myException(this, "You asked for Long-Value, but real result is not compatible (2)!");
			}
		} else {
			throw new myException(this,"Error: Field:"+field.fn()+" not in this table !");
		}
	}
 
	
	/**
	 * 2018-04-06: BigDecimal-objekte
	 * @throws myException 
	 */
	public BigDecimal getBigDecimalDbValue(IF_Field field) throws myException {
		if (this.get(field) != null) {
			if (field.generate_MetaFieldDef().get_bIsNumberTypeWithDecimals() || field.generate_MetaFieldDef().get_bIsNumberTypeWithOutDecimals()) {
				Object o = this.getValue(field);
				if (o==null) {
					return (BigDecimal)null;
				} else if (o instanceof BigDecimal) {
					return (BigDecimal)o;
				} else if (o instanceof Long) {
					return new BigDecimal((Long)o);
				}
				throw new myException(this, "You asked for BigDecimal-Value, but real result is not compatible (1)!");
			} else {
				throw new myException(this, "You asked for BigDecimal-Value, but real result is not compatible (2)!");
			}
		} else {
			throw new myException(this,"Error: Field:"+field.fn()+" not in this table !");
		}
	}
 

	
	
	
	/**
	 * 2017-03-16: raw-objekte zurueckgeben, allerdings falls ganzzahlen als Long (statt BigDecimal)
	 * @throws myException 
	 */
	public Object getRawValueCorrected(IF_Field field) throws myException {
		if (this.get(field) != null) {
			return this.get(field).get_result_val().getNativeDateObjectCorrected();
		} else {
			throw new myException(this,"Error: Field:"+field.fn()+" not in this table !");
		}
	}
 

	
	public BigDecimal get_raw_resultValue_bigDecimal(IF_Field field) throws myException {
		if (this.get(field) != null) {
			return this.get(field).get_raw_resultValue_bigDecimal();
		} else {
			throw new myException(this,"Error: Field:"+field.fn()+" not in this table !");
		}
	}
	
	
	/**
	 * Alternativer BigDecimal-Wert wenn null
	 * @author manfred
	 * @date 22.02.2018
	 *
	 * @param field
	 * @param whenNull
	 * @return
	 * @throws myException
	 */
	public BigDecimal get_raw_resultValue_bigDecimal(IF_Field field,BigDecimal whenNull) throws myException {
		if (this.get(field) != null) {
			BigDecimal bdRet = this.get(field).get_raw_resultValue_bigDecimal();
			return (bdRet != null ? bdRet : whenNull );
		} else {
			throw new myException(this,"Error: Field:"+field.fn()+" not in this table !");
		}
	}
	
	
	public Timestamp get_raw_resultValue_timeStamp(IF_Field field) throws myException {
		if (this.get(field) != null) {
			return this.get(field).get_raw_resultValue_timeStamp();
		} else {
			throw new myException(this,"Error: Field:"+field.fn()+" not in this table !");
		}
	}

	/**
	 * Alternativer Timestamp-Wert wenn null
	 * @author manfred
	 * @date 22.02.2018
	 *
	 * @param field
	 * @param whenNull
	 * @return
	 * @throws myException
	 */
	public Timestamp get_raw_resultValue_timeStamp(IF_Field field, Timestamp whenNull) throws myException {
		if (this.get(field) != null) {
			Timestamp t = this.get(field).get_raw_resultValue_timeStamp();
			return (t != null ? t : whenNull);
		} else {
			throw new myException(this,"Error: Field:"+field.fn()+" not in this table !");
		}
	}

	
	
	public Long get_raw_resultValue_Long(IF_Field field) throws myException {
		if (this.get(field) != null) {
			return this.get(field).get_raw_resultValue_Long();
		} else {
			throw new myException(this,"Error: Field:"+field.fn()+" not in this table !");
		}
	}
	

	/**
	 * Alternativer Long-Wert wenn null
	 * @author manfred
	 * @date 22.02.2018
	 *
	 * @param field
	 * @param whenNull
	 * @return
	 * @throws myException
	 */
	public Long get_raw_resultValue_Long(IF_Field field, Long whenNull) throws myException {
		if (this.get(field) != null) {
			Long l = this.get(field).get_raw_resultValue_Long();
			return (l != null ? l : whenNull);
		} else {
			throw new myException(this,"Error: Field:"+field.fn()+" not in this table !");
		}
	}
	
	
	
	public BigDecimal getRawResultValueAsBigDecimal(IF_Field field, BigDecimal bdWhenNull) throws myException {
		if (this.get(field) != null) {
			Object raw = this.get(field).get_raw_resultValue();
			if (raw == null) {
				return bdWhenNull;
			} else if (raw instanceof BigDecimal) {
				return (BigDecimal)raw;
			} else if (raw instanceof Long) {
				return new BigDecimal((Long)raw);
			} else if (raw instanceof Integer) {
				return new BigDecimal((Integer)raw);
			} else if (raw instanceof Double) {
				return new BigDecimal((Double)raw);
			} else if (raw instanceof Float) {
				return new BigDecimal((Float)raw);
			} else {
				throw new myException(this, "Cannot convert "+field.fn()+" to BigDecmial !!");
			}
		} else {
			throw new myException(this,"Error: Field:"+field.fn()+" not in this table !");
		}
	}
	
	
	public BigDecimal getRawResultValueAsBigDecimal(IF_Field field) throws myException {
		return this.getRawResultValueAsBigDecimal(field, null);
	}
	
	
	
	


	/**
	 * hier kann eine rec_after_save von aussen eingespielt werden ...
	 * @param rec
	 * @return
	 */
	public Rec20 _set_recAfterSave(Rec20 rec) throws myException {
		this.rec_after_save_new = rec;
		if (this.get_tab()!= rec.get_tab()) {
			throw new myException(this,"Only record from same type can be set as recAfterSave !!");
		}
		
		return this;
	}
	
	
	/**
	 * 
	 * @return actual id (when new and not saved, then null)
	 * @throws myException
	 */
	public Long getActualID() throws myException {
		if (this.is_newRecordSet()) {
			if (this.rec_after_save_new!=null) {
				return this.rec_after_save_new.get_myLong_dbVal(this.tab.key()).get_oLong();
			} else {
				return null;
			}
		} else {
			return this.get_myLong_dbVal(this.tab.key()).get_oLong();
		}
	}
	
	
	
	

	/**
	 * martin: 2017-08-08
	 * @param field
	 * @param mv
	 * @return  String for sql-statement (can be set als raw-field to other record)
	 * @throws myException
	 */
	public String  getRawSqlFieldTerm4Save(IF_Field field, MyE2_MessageVector mv) throws myException {

		String c_ret = "NULL";
		
		if (field == null) {
			throw new myException(this,"Parameter field MUST NOT BE null !");
		}
		
		if (field._t()!=this.get_tab()) {
			throw new myException(this, "Table of called field is not Table of Rec20-Object !");
		}
		
		//situation der automatischen felder pruefen
		//hier darf auch die id nicht in den statements auftauchen
		Vector<String> v_exclude = new Vector<>(Rec20.get_AutomaticWrittenFields_STD());
		if (this.oDB!=null) {
			v_exclude = this.oDB.get_AutomaticWrittenFields();
		}
		if (v_exclude.contains(field.fn())) {
			throw new myException(this,"Automatic fields are not allowed here !");
		}
		
		//jetzt mit den Werten der map fuellen
		Rec20_field rf = this.get(field);
		c_ret = rf.get_value4sql_statements(mv);

		//falls ein raw-field uebergeben wurde, dann ueberschreiben
		if (this.hm_Field_Value_pairs_from_outside.containsKey(field.fn())) {
			c_ret = this.hm_Field_Value_pairs_from_outside.get(field.fn());
		}
		return c_ret;
	}
	

	/**
	 * eine eigene ausnahme-feld liste uebergeben
	 * @param fw
	 * @return
	 */
	public Rec20 _setAutomaticFields(FieldValPairs fw) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		this._set_dbtoolbox(oDB);
		
		String[][] zusatz = new String[fw.size()][2];
		
		int i=0;
		for (String field: fw.keySet()) {
			zusatz[i][0]=field;
			zusatz[i++][1]=fw.get(field);
		}
		oDB.setZusatzFelder(zusatz);
		
		return this;
	}
	
	
	
	

	/**
	 * 
	 * @param field
	 * @param testStringFormated
	 * @return testStringFormated in db-sql-compatible variant
	 * @throws myException
	 */
	public String getRawValue(IF_Field field, String testStringFormated) throws myException {
		MyMetaFieldDEF fd = this.get(field).get_metadef();
		String c_ret = fd.get_cStringForDataBase(testStringFormated, true, false);
		return c_ret;
	}
	


//	/**
//	 * !! can be null, when new Rec20 is unsaved!
//	 * @return
//	 */
//	public Rec20 getRec20_or_newRec20() {
//		if (this.is_newRecordSet()) {
//			return this.get_rec_after_save_new();
//		}
//		return this;
//	}

    
	/**
	 * hier koenne die Felder, die im nachhinein in die tabelle eingefuegt wurden, oder die in einer speziellen abfrage definiert wurden, 
	 * abgeholte werden, unter dem grossgeschreibenen spaltennamen
	 */
	public HashMap<String, MyResultValue> getOverheadFields() {
		return hmOverheadFields;
	}
	
	



	/**
	 * 20171123: einfacherzugriff auf overheadvalue
	 */
	public String getOverHeadValue(String key) throws myException {
		String s = null;
		if (this.getOverheadFields().get(key)!=null) {
			s=this.getOverheadFields().get(key).get_FieldValueUnformated();
		} else {
			throw new myException(this,"Overheadfield: "+key+" is not in the Rec20 present !");
		}
		
		return s;
	}

	/**
	 * 20171123: einfacherzugriff auf overheadvalue
	 */
	public String getOverHeadValueUF(IF_Field f) throws myException {
		String s = null;
		if (this.getOverheadFields().get(f.fn())!=null) {
			s=this.getOverheadFields().get(f.fn()).get_FieldValueUnformated();
		} else {
			throw new myException(this,"Overheadfield: "+f.fn()+" is not in the Rec20 present !");
		}
		
		return s;
	}
	
	
	
	/**
	 * 2017-09-22: raw-objekte zurueckgeben nach namen, auch aus overhead-map
	 * @param columnName
	 * @param returnWhenNull
	 * @return value or returnWhenNull
	 * @throws myException
	 */
	public Object getRawVal(String columnName, Object returnWhenNull) throws myException {
		if (this.getRawResultValue(columnName)==null) {
			return returnWhenNull;
		} else {
			return this.getRawResultValue(columnName);
		}
	}


	/**
	 * 2018-02-20: raw-objekte zurueckgeben nach namen, auch aus overhead-map
	 * @param column
	 * @return value or null
	 * @throws myException
	 */
	public Object getRawVal(IF_Field column) throws myException {
		return this.getRawVal(column.fn(),null);
	}

	
	/**
	 * 2018-02-20: raw-objekte zurueckgeben nach namen, auch aus overhead-map
	 * @param column
	 * @param returnWhenNull
	 * @return value or returnWhenNull
	 * @throws myException
	 */
	public Object getRawVal(IF_Field column, Object returnWhenNull) throws myException {
		return this.getRawVal(column.fn(),returnWhenNull);
	}


	
	/**
	 * 2017-09-22: raw-objekte zurueckgeben nach namen, auch aus overhead-map
	 * @param columnName
	 * @return value or null, when resultVal is not present or resultval ==null
	 * @throws myException
	 */
	public Object getRawResultValue(String columnName) throws myException {
		IF_Field field = this.find_field(columnName);
		
		if (field != null) {
			if (this.get(field).get_result_val()==null) {
				return null;
			} else {
				return this.get(field).get_result_val().get_oNativeDataObject();
			}
		} else {
			if (this.hmOverheadFields.get(columnName.toUpperCase())!=null) {
				return this.hmOverheadFields.get(columnName).get_oNativeDataObject();
			} else {
				throw new myException(this,"Error: Field:"+columnName+" not in this Rec20 !");
			}
		}
	}

	
	
	/**
	 * Rec20-pointer auf die das zuletzt von der datenbank geladene recset
	 * Bei normalem gelesenen record ist das ein pointer auf sich selbst, sonst ein pointer auf das object rec_after_save_new, falls der recordnew bereits gefuellt ist  
	 * 
	 * @return last loadet record (or before save new Rec20 itself)
	 */
	public Rec20 getRec20LastRead() {
		if (this.is_ExistingRecord()) {
			return this;
		} else {
			if (this.rec_after_save_new==null) {
				return this;
			} else {
				return this.rec_after_save_new;
			}
		}
	}


	
	

	/**
	 * 
	 * @param mv
	 * @param excludes  (for example this.getAutoFieldsStd())
	 * @return Rec20 in state new filled with actual values
	 * @throws myException
	 */
	public Rec20 getRec20CopyAsNew(MyE2_MessageVector mv, VEK<IF_Field> excludes) throws myException {
		
		//2020-02-21: fehler: vorher wurde wenn autoexclude==null nix kopiert, siehe:
		
//		Rec20 rec = new Rec20(this.tab);
//		
//		for (IF_Field f: this.keySet()) {
//			if (autoExcludes != null && (!autoExcludes.contains(f))) {
//				rec._nv(f, this.get_fs_dbVal(f), mv);
//			}
//		}
//		
//		return rec;
		 
		Rec20 rec = new Rec20(this.tab);
		
		if (excludes==null) {
			excludes=new VEK<>();
		}
		
		for (IF_Field f: this.keySet()) {
			if (!excludes.contains(f)) {
				rec._nv(f, this.get_fs_dbVal(f), mv);
			}
		}
		
		return rec;
	}
	
	


	/**
	 * 
	 * @author martin
	 * @date 21.02.2020
	 *
	 * @param mv
	 * @param excludes
	 * @return
	 * @throws myException
	 */
	public Rec20 getRec20CopyAsNewWithoutAutomaticFields(MyE2_MessageVector mv, VEK<IF_Field> excludes) throws myException {
		VEK<IF_Field> v_exclude = new VEK<>();
		if (excludes!=null) {
			v_exclude._a(excludes);
		}
		v_exclude._a(getAutoFieldsStd());
		
		return this.getRec20CopyAsNew(mv,v_exclude);
	}
	
	
	
	public VEK<IF_Field> getAutoFieldsStd() throws myException {
		VEK<IF_Field> v = new VEK<IF_Field>();
		 v	._a(_TAB.find_field(this.tab, DB_STATICS.AUTOMATC_FIELDS.ID_MANDANT.toString()))
		 	._a(_TAB.find_field(this.tab, DB_STATICS.AUTOMATC_FIELDS.GEAENDERT_VON.toString()))
		 	._a(_TAB.find_field(this.tab, DB_STATICS.AUTOMATC_FIELDS.LETZTE_AENDERUNG.toString()))
		 	._a(_TAB.find_field(this.tab, DB_STATICS.TRIGGER_FIELDS.ERZEUGT_AM.toString()))
		 	._a(_TAB.find_field(this.tab, DB_STATICS.TRIGGER_FIELDS.ERZEUGT_VON.toString()))
		 	._a(this.tab.key())
		 	;
		return v;
	}
	
//	
//	public Rec20 _setNewVal(IF_Field field, Object value, MyE2_MessageVector mv)  throws myException {
//		return this._newVal(field, value, mv);
//	}
//	
	
	/**
	 * 
	 * @author martin
	 * @date 16.01.2020
	 *
	 * @param fieldName
	 * @param value
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public Rec20 _setNewVal(String fieldName,  Object value, MyE2_MessageVector mv) throws myException {
		IF_Field field = _TAB.find_field(this.get_tab(), fieldName);
		if (field == null) {
			throw new myException("Field with name :"+fieldName+" cannot be found in Rec21 with _TAB "+this.get_tab().fullTableName());
		} else {
			return _setNewVal(field, value, mv);
		}
	}
	
	
	
	/*
	 * bequemer 
	 */
	public Rec20 _setNewVal(IF_Field field, Object value, MyE2_MessageVector mv)  throws myException {
		//MyMetaFieldDEF fd = field.generate_MetaFieldDef();
		
		if (!this.keySet().contains(field)) {
			throw new myException(this,"nv(): Field: "+field.fn()+" is not in the Rec20 "+S.NN(this.get_TABLENAME()));
		}
		
		//aenderung 2018-02-28: auslagerung der umsetzung raw auf newVal
		
		HashMap<TARGETTYPE, Object> hmRet =  this.creatorForDifferentDataTypes.getDataTypes(field, value);
		
		//String inputText = this.translatorObjectToString.getSqlTermString(field, value, this.hm_Field_Value_pairs_from_outside);
		String inputText = (String)hmRet.get(TARGETTYPE.FORMATED_STRING);
		
		if (O.isNotNull(hmRet.get(TARGETTYPE.SQL_TERM))) {
			//bei Date-Werten wird hier auch in der nicht-prepared-variante das datum korrekt uebergeben, ueber die hm_Field_Value_pairs_from_outside 
			this.get_hm_Field_Value_pairs_from_outside().put(field.fn(), (String) hmRet.get(TARGETTYPE.SQL_TERM));
		}
		
//		if (value == null) {
//			inputText = "";
//		} else if (value instanceof String) {
//			inputText = (String) value;
//		} else if (value instanceof Integer) {
//			inputText = ""+((Integer)value).intValue();
//		} else if (value instanceof Long) {
//			inputText = ""+((Long)value).longValue();
//		} else if (value instanceof Double) {
//			inputText = MyNumberFormater.formatDez(((Double)value).doubleValue(), fd.get_FieldDecimals(),false);
//		} else if (value instanceof BigDecimal) {
//			inputText = MyNumberFormater.formatDez(((BigDecimal)value), fd.get_FieldDecimals(),true);	
//		} else if (value instanceof GregorianCalendar) {
//			inputText = myDateHelper.FormatDateNormal(((GregorianCalendar)value).getTime());	
//		} else {
//			String infoText = field.fieldName()+": Unhandeled type: "+value.getClass().toString();
//			
//		//	throw new myException(this,"newVal(): Only Datatypes  String,Integer,Long,Double,BigDecimal,GregorianCalendar are allowed !"+infoText);
//		}
		
		//mv.add_MESSAGE(this.set_NewValueForDatabase(field.fn(), inputText));
		this._nv(field, inputText, mv);

		return this;
	}
	
	
	

	/**
	 * 20171227: martin
	 * @param fieldName
	 * @return UnFormated value of field or overheadfield with fieldName, on new Rec20 null,  if field not found exception
	 * @throws myException
	 */
	public String getDbValueUnFormated(String fieldName) throws myException {
	
		IF_Field 		field = this.find_field(fieldName);
		MyResultValue 	resVal = null;
		
		if (field != null) {
			if (this.is_ExistingRecord()) {
				resVal = this.get(field).get_result_val();
			} else {
				return null;
			}
		} else {
			resVal = this.hmOverheadFields.get(fieldName.toUpperCase());
			if (resVal == null) {
				throw new myException(this, "field: "+fieldName+" is no field in this Rec20-object!");
			}
		}

		return resVal.get_FieldValueUnformated();
	}
	
	
	/**
	 * 20171227: martin
	 * @param fieldName
	 * @return Formated value of field or overheadfield with fieldName, on new Rec20 null,  if field not found exception
	 * @throws myException
	 */
	public String getFDbValueFormated(String fieldName) throws myException {
	
		IF_Field 		field = this.find_field(fieldName);
		MyResultValue 	resVal = null;
		
		if (field != null) {
			if (this.is_ExistingRecord()) {
				resVal = this.get(field).get_result_val();
			} else {
				return null;
			}
		} else {
			resVal = this.hmOverheadFields.get(fieldName.toUpperCase());
			if (resVal == null) {
				throw new myException(this, "field: "+fieldName+" is no field in this Rec20-object!");
			}
		}

		return resVal.get_FieldValueFormated();
	}
	

	/**
	 * 20171227: martin
	 * @param fieldName
	 * @return Formated value of field or overheadfield with fieldName, on new Rec20 null,  if field not found retWhenNotFound
	 * 
	 */
	public String getFDbValueFormated(String fieldName, String retWhenNotFound)  {
	
		IF_Field 		field = this.find_field(fieldName);
		MyResultValue 	resVal = null;
		
		if (field != null) {
			if (this.is_ExistingRecord()) {
				resVal = this.get(field).get_result_val();
			} else {
				return null;
			}
		} else {
			resVal = this.hmOverheadFields.get(fieldName.toUpperCase());
			if (resVal == null) {
				return retWhenNotFound;
			}
		}

		return resVal.get_FieldValueFormated();
	}
	

	
	
	/**
	 * 2018-01-09: ausgabe debugging - informationen
	 */
	public LinkedHashMap<String, String> getDebugInfos() {
		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();
		
		hm.put("TABLENAME", this.tab.fullTableName());
		hm.put("BUILDSTRING", this.sql4query);
		hm.put("IS-NEW ?", this.is_newRecordSet()?" TRUE ":" FALSE" );
		
		for (IF_Field f: this.fields) {
			try {
				hm.put(f.fieldName(),S.NN(this.get_fs_dbVal(f),"NULL"));
			} catch (myException e) {
				hm.put(f.fieldName(),"@@ERROR@@: "+e.getMessage());
			}
		}
		
		return hm;
	}

	/**
	 * 
	 * @return translator-object Obkect-to-String
	 */
	public CreatorForDifferentDataTypes getTranslatorObjectToString() {
		return creatorForDifferentDataTypes;
	}


	public void setTranslatorObjectToString(CreatorForDifferentDataTypes p_translatorObjectToString) {
		this.creatorForDifferentDataTypes = p_translatorObjectToString;
	}

	
	/**
	 * interface fuer die umwandlung 
	 * @author martin
	 *
	 */
	public static interface CreatorForDifferentDataTypes {
		public static enum TARGETTYPE {  		//define target-types in the return hashmap
			FORMATED_STRING
			,RAW_OBJECT
			,SQL_TERM
		}
		public HashMap<TARGETTYPE, Object> getDataTypes(IF_Field f, Object o) throws myException;
	}
	
	
	public static class CreatorForDifferentDataTypesStd implements CreatorForDifferentDataTypes {

		@Override
		public HashMap<TARGETTYPE, Object> getDataTypes(IF_Field f, Object value) throws myException {

			HashMap<TARGETTYPE, Object> hmRet = new HashMap<Rec20.CreatorForDifferentDataTypes.TARGETTYPE, Object>();
			
			hmRet.put(TARGETTYPE.RAW_OBJECT, 		value);   			//der raw-wert wird hier immer uebergeben
			hmRet.put(TARGETTYPE.FORMATED_STRING, 	null);   
			hmRet.put(TARGETTYPE.SQL_TERM, 			null);   
			
			
			MyMetaFieldDEF fd = f.generate_MetaFieldDef();

			if (value == null) {
				hmRet.put(TARGETTYPE.FORMATED_STRING, "");
//				inputText = "";
			} else if (value instanceof String) {
				hmRet.put(TARGETTYPE.FORMATED_STRING, (String) value);
//				inputText = (String) value;
			} else if (value instanceof Integer) {
				hmRet.put(TARGETTYPE.FORMATED_STRING, ""+((Integer)value).intValue());
//				inputText = ""+((Integer)value).intValue();
			} else if (value instanceof Long) {
				hmRet.put(TARGETTYPE.FORMATED_STRING, ""+((Long)value).longValue());
//				inputText = ""+((Long)value).longValue();
			} else if (value instanceof Double) {
				hmRet.put(TARGETTYPE.FORMATED_STRING, MyNumberFormater.formatDez(((Double)value).doubleValue(), fd.get_FieldDecimals(),false));
//				inputText = MyNumberFormater.formatDez(((Double)value).doubleValue(), fd.get_FieldDecimals(),false);
			} else if (value instanceof BigDecimal) {
				hmRet.put(TARGETTYPE.FORMATED_STRING, MyNumberFormater.formatDez(((BigDecimal)value), fd.get_FieldDecimals(),true));
//				inputText = MyNumberFormater.formatDez(((BigDecimal)value), fd.get_FieldDecimals(),true);	
			} else if (value instanceof GregorianCalendar) {
//				inputText = myDateHelper.FormatDateNormal(((GregorianCalendar)value).getTime());
				this.handleDate(((GregorianCalendar)value).getTime(), hmRet);
				
			} else if (value instanceof java.sql.Timestamp) {
				
				this.handleDate(new Date(((java.sql.Timestamp)value).getTime()), hmRet);
				
				//Date date = new Date(((java.sql.Timestamp)value).getTime());
				
				
				
				
//				inputText = myDateHelper.FormatDateNormal(date);
//				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				//hier wird der input-text formal uebergeben, aber ueber einen eintrag in die hm_Field_Value_pairs_from_outside ueberschrieben
				//hmFieldValuePairsFromOutside.put(f.fn(), "TO_DATE('"+format.format(date)+"','YYYY-MM-DD HH24:MI:SS')");
				
//				hmRet.put(TARGETTYPE.FORMATED_STRING, myDateHelper.FormatDateNormal(date));
//				hmRet.put(TARGETTYPE.SQL_TERM, "TO_DATE('"+format.format(date)+"','YYYY-MM-DD HH24:MI:SS')");

				
			} else if (value instanceof java.sql.Date) {
//				Date date = new Date(((java.sql.Date)value).getTime());
				this.handleDate(new Date(((java.sql.Date)value).getTime()), hmRet);
				
				
//				inputText = myDateHelper.FormatDateNormal(date);
//				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//				//hier wird der input-text formal uebergeben, aber ueber einen eintrag in die hm_Field_Value_pairs_from_outside ueberschrieben
//				//hmFieldValuePairsFromOutside.put(f.fn(), "TO_DATE('"+format.format(date)+"','YYYY-MM-DD HH24:MI:SS')");
//
//				hmRet.put(TARGETTYPE.FORMATED_STRING, myDateHelper.FormatDateNormal(date));
//				hmRet.put(TARGETTYPE.SQL_TERM, "TO_DATE('"+format.format(date)+"','YYYY-MM-DD HH24:MI:SS')");

			} else if (value instanceof java.util.Date) {
				
				this.handleDate((java.util.Date)value, hmRet);

				
//				Date date = (java.util.Date)value;
////				inputText = myDateHelper.FormatDateNormal(date);
//				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//				//hier wird der input-text formal uebergeben, aber ueber einen eintrag in die hm_Field_Value_pairs_from_outside ueberschrieben
//				//hmFieldValuePairsFromOutside.put(f.fn(), "TO_DATE('"+format.format(date)+"','YYYY-MM-DD HH24:MI:SS')");
//				
//				hmRet.put(TARGETTYPE.FORMATED_STRING, myDateHelper.FormatDateNormal(date));
//				hmRet.put(TARGETTYPE.SQL_TERM, "TO_DATE('"+format.format(date)+"','YYYY-MM-DD HH24:MI:SS')");
			
			} else {
				String infoText = f.fieldName()+": Unhandeled type: "+value.getClass().toString();
				throw new myException(this,"newVal(): Only Datatypes  String,Integer,Long,Double,BigDecimal,Timestamp,Date and GregorianCalendar are allowed !"+infoText);
			}
			
			return hmRet;
		}
		
		
		private void handleDate(Date date, HashMap<TARGETTYPE, Object> hmRet) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			//hier wird der input-text formal uebergeben, aber ueber einen eintrag in die hm_Field_Value_pairs_from_outside ueberschrieben
			//hmFieldValuePairsFromOutside.put(f.fn(), "TO_DATE('"+format.format(date)+"','YYYY-MM-DD HH24:MI:SS')");
			
			hmRet.put(TARGETTYPE.FORMATED_STRING, myDateHelper.FormatDateNormal(date));   //alte ungenaue varianten: 31.12.2018
			hmRet.put(TARGETTYPE.SQL_TERM, "TO_DATE('"+format.format(date)+"','YYYY-MM-DD HH24:MI:SS')");

		}
	}


	
	
	
	/**
	 * 20180327: ab hier die implementierung des interface MyRECORD_IF_RECORDS  statt  MyRECORD_IF_FILLABLE 
	 */
	/* (non-Javadoc)
	 * @see panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS#get_HM_FIELDNAMES()
	 */
	@Override
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() throws myException  {
		HashMap<String, DATATYPES> hmDataTypes = new  HashMap<String, DATATYPES>();
		for (IF_Field f: this.keySet()) {
			hmDataTypes.put(f.fn(), this.get(f).metadef.getDATATYPE());
		}
		return hmDataTypes;
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS#get_RECORD_NEW()
	 */
	@Override
	public MyRECORD_NEW get_RECORD_NEW() throws myException {
		return new MyRECORD_NEW(this.tab.fullTableName());
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS#get_PRIMARY_KEY_UF()
	 */
	@Override
	public String get_PRIMARY_KEY_UF() throws myException {
		return this.tab.key().fn();
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS#get_PRIMARY_KEY_VALUE()
	 */
	@Override
	public long get_PRIMARY_KEY_VALUE() throws myException {
		if (this.is_newRecordSet()) {
			throw new myException(this,"keyValue in new record not possible");
		}
		// TODO Auto-generated method stub
		return new MyLong(this.get_key_value()).get_lValue();
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS#get_SQL_UPDATE_STATEMENT(java.util.Vector, boolean)
	 */
	@Override
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields)throws myException {
		if (this.is_newRecordSet()) {
			throw new myException(this,"updatestatement in new record not possible");
		}
		MySqlStatementBuilder sb = this.get_stmbd(vFieldsNotInUpdate, bOnlyChangedFields,bibMSG.MV());
		return sb.get_CompleteUPDATEString(this.tab.fullTableName(), bibE2.cTO(), this.tab.keyFieldName()+"="+this.get_key_value(), null);
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS#get_SQL_UPDATE_STD()
	 */
	@Override
	public String get_SQL_UPDATE_STD() throws myException {
		return this.get_sql_4_save(true);
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS#UPDATE(boolean)
	 */
	@Override
	public MyE2_MessageVector UPDATE(boolean bCommit) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		this._SAVE(bCommit, mv);
		return mv;
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS#UPDATE(java.util.Vector, boolean)
	 */
	@Override
	public MyE2_MessageVector UPDATE(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException {
		String sql = this.get_SQL_UPDATE_STATEMENT(vFieldsNotInUpdate,bOnlyChangedFields);
		MyE2_MessageVector mv = new MyE2_MessageVector();
		if (S.isFull(sql)) {
			boolean toolbox_created = false;
			if (this.oDB==null) {
				this.oDB = bibALL.get_myDBToolBox();
				toolbox_created=true;
			}
			mv.add_MESSAGE(this.oDB.ExecMultiSQLVector(new VEK<String>()._a(sql), false));
			if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
		} else {
			mv._addAlarm("Nothing to save !");
		}
		return mv;
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS#DELETE()
	 */
	@Override
	public MyE2_MessageVector DELETE() throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();

		String sql = this.get_sql_2_delete();
		
		if (S.isFull(sql)) {
			boolean toolbox_created = false;
			if (this.oDB==null) {
				this.oDB = bibALL.get_myDBToolBox();
				toolbox_created=true;
			}
			mv.add_MESSAGE(this.oDB.ExecMultiSQLVector(new VEK<String>()._a(sql), false));
			if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
		} else {
			mv._addAlarm("Nothing to delete !");
		}
		return mv;
	}

	

	public MyE2_MessageVector DELETE(boolean commit) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();

		String sql = this.get_sql_2_delete();
		
		if (S.isFull(sql)) {
			boolean toolbox_created = false;
			if (this.oDB==null) {
				this.oDB = bibALL.get_myDBToolBox();
				toolbox_created=true;
			}
			mv.add_MESSAGE(this.oDB.ExecMultiSQLVector(new VEK<String>()._a(sql), commit));
			if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
		} else {
			mv._addAlarm("Nothing to delete !");
		}
		return mv;
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS#get_DELETE_STATEMENT()
	 */
	@Override
	public String get_DELETE_STATEMENT() throws myException {
		return this.get_sql_2_delete();
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS#REBUILD()
	 */
	@Override
	public void REBUILD() throws myException {
		this._rebuildRecord();
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS#get_hm_FieldMetaDefs()
	 */
	@Override
	public HashMap<String, MyMetaFieldDEF> get_hm_FieldMetaDefs() {
		HashMap<String, MyMetaFieldDEF> hmDataTypes = new  HashMap<String, MyMetaFieldDEF>();
		for (IF_Field f: this.keySet()) {
			hmDataTypes.put(f.fn(), this.get(f).metadef);
		}
		return hmDataTypes;
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS#set_Tablename_To_FieldMetaDefs(java.lang.String)
	 */
	@Override
	public void set_Tablename_To_FieldMetaDefs(String tablename) {
		for (MyMetaFieldDEF oMeta: this.get_hm_FieldMetaDefs().values()) {
			oMeta.set_Tablename(tablename.toUpperCase(), false);
		}
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS#set_cSQL_4_Build(java.lang.String)
	 */
	@Override
	public void set_cSQL_4_Build(String sql_4_Build) {
		this.sql4query=sql_4_Build;
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS#set_oConn(panter.gmbh.indep.dataTools.MyConnection)
	 */
	@Override
	public void set_oConn(MyConnection conn) {
		this.oDB = bibALL.get_myDBToolBox(conn);
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS#build_NEW_INSTANCE_ACTUAL_DATABASEVALUES()
	 */
	@Override
	public MyRECORD_IF_RECORDS build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
		return new Rec20(this.tab)._fill_id(this.get_key_value());
	}


	/**
	 * 2018-04-27 
	 * @param conn
	 * @return
	 */
	public Rec20 _setConn(MyConnection conn) {
		this.oDB = bibALL.get_myDBToolBox(conn);
		return this;
	}
	
	

	/**
	 * 
	 * @return record-id or on new recs throws exception
	 * @throws myException
	 */
	public long getId() throws myException {
		if (this.is_ExistingRecord()) {
			return this.getLongDbValue(this.tab.key()).longValue();
		} else {
			throw new myException(this, "only possible on existing records !");
		}
	}
	
	
	/**
	 * 
	 * @return record-id or on new recs throws exception
	 * @throws myException
	 */
	public Long getIdLong() throws myException {
		if (this.is_ExistingRecord()) {
			return this.getLongDbValue(this.tab.key());
		} else {
			throw new myException(this, "only possible on existing records !");
		}
	}

	

	
	public static boolean isFull(Rec20 r) {
		if (r !=null && r.is_ExistingRecord())  {
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(Rec20 r) {
		return !Rec20.isFull(r);
	}
	
	
	
	/**
	 * neuer getter
	 * @author martin
	 * @date 15.10.2020
	 *
	 * @param fieldName
	 * @return
	 * @throws FieldFalseTypeException
	 * @throws FieldNotInRecException
	 * @throws myException
	 */
	public Long getLongResultValueNative(String fieldName) throws FieldFalseTypeException, FieldNotInRecException,myException {
		IF_Field f = this.find_field(fieldName);
		if (f == null) {
			throw new FieldNotInRecException("Field "+S.NN(fieldName,"????")+" is not a field of table "+this.get_TABLENAME());
		} else {
			return getLongResultValueNative(f);
		}
	}
	
	/**
	 * neuer getter
	 * @author martin
	 * @date 15.10.2020
	 *
	 * @param field
	 * @return
	 * @throws FieldFalseTypeException
	 */
	public Long getLongResultValueNative(IF_Field field) throws FieldFalseTypeException {
		Long 		ret = 	null;
		Rec20_field f = 	this.get_field(field);
		
		if (f.get_metadef().get_bIsNumberTypeWithOutDecimals()) {
			Object ob = f.getRawResultValueCorrected();
			if (ob != null) {
				if (ob instanceof Long) {
					ret = (Long)ob;
				} else  if (ob instanceof Integer) {
					ret = new Long((Integer)ob);
				} else {
					throw new FieldFalseTypeException("Field "+field.fn()+" is not a Long-Typ");
				}
			}
		} else {
			throw new FieldFalseTypeException("Field "+field.fn()+" is not a Long-Typ");
		}
		
		return ret;
	}
	

	/**
	 * neuer getter
	 * @author martin
	 * @date 15.10.2020
	 *
	 * @param fieldName
	 * @return
	 * @throws FieldFalseTypeException
	 * @throws FieldNotInRecException
	 * @throws myException
	 */
	public BigDecimal getBigResultDecimalValueNative(String fieldName) throws FieldFalseTypeException,FieldNotInRecException,myException {
		IF_Field f = this.find_field(fieldName);
		if (f == null) {
			throw new FieldNotInRecException("Field "+S.NN(fieldName,"????")+" is not a field of table "+this.get_TABLENAME());
		} else {
			return getBigDecimalResultValueNative(f);
		}
	}

	

	/**
	 * neuer getter
	 * @author martin
	 * @date 15.10.2020
	 *
	 * @param field
	 * @return
	 * @throws FieldFalseTypeException
	 */
	public BigDecimal getBigDecimalResultValueNative(IF_Field field) throws FieldFalseTypeException {
		BigDecimal 		ret = 	null;
		Rec20_field f = 	this.get_field(field);
		
		if (f.get_metadef().get_bIsNumberTypeWithDecimals()) {
			Object ob = f.getRawResultValueCorrected();
			if (ob != null) {
				if (ob instanceof BigDecimal) {
					ret = (BigDecimal)ob;
				} else {
					throw new FieldFalseTypeException("Field "+field.fn()+" is not a BigDecimal-Typ");
				}
			}
		} else {
			throw new FieldFalseTypeException("Field "+field.fn()+" is not a BigDecimal-Typ");
		}
		
		return ret;
	}
	
	
	
	/**
	 * neuer getter
	 * @author martin
	 * @date 15.10.2020
	 *
	 * @param fieldName
	 * @return
	 * @throws FieldFalseTypeException
	 * @throws FieldNotInRecException
	 * @throws myException
	 */
	public Date getDateResultValueNative(String fieldName) throws FieldFalseTypeException,FieldNotInRecException,myException {
		IF_Field f = this.find_field(fieldName);
		if (f == null) {
			throw new FieldNotInRecException("Field "+S.NN(fieldName,"????")+" is not a field of table "+this.get_TABLENAME());
		} else {
			return getDateResultValueNative(f);
		}
	}
	
	
	
	
	/**
	 * neuer getter
	 * @author martin
	 * @date 15.10.2020
	 *
	 * @param field
	 * @return
	 * @throws FieldFalseTypeException
	 */
	public Date getDateResultValueNative(IF_Field field) throws FieldFalseTypeException {
		Date 		ret = 	null;
		Rec20_field f = 	this.get_field(field);
		
		if (f.get_metadef().get_bIsDateType()) {
			Object ob = f.getRawResultValueCorrected();
			if (ob != null) {
				if (ob instanceof Date) {
					ret = (Date)ob;
				} else {
					throw new FieldFalseTypeException("Field "+field.fn()+" is not a Date-Typ");
				}
			}
		} else {
			throw new FieldFalseTypeException("Field "+field.fn()+" is not a Date-Typ");
		}
		
		return ret;
	}
	
	
	
	
	/**
	 * neuer getter
	 * @author martin
	 * @date 15.10.2020
	 *
	 * @param fieldName
	 * @return
	 * @throws FieldFalseTypeException
	 * @throws FieldNotInRecException
	 * @throws myException
	 */
	public String getStringResultValueNative(String fieldName) throws FieldFalseTypeException,FieldNotInRecException,myException {
		IF_Field f = this.find_field(fieldName);
		if (f == null) {
			throw new FieldNotInRecException("Field "+S.NN(fieldName,"????")+" is not a field of table "+this.get_TABLENAME());
		} else {
			return getStringResultValueNative(f);
		}
	}
	
	
	
	/**
	 * neuer getter
	 * @author martin
	 * @date 15.10.2020
	 *
	 * @param field
	 * @return
	 * @throws FieldFalseTypeException
	 */
	public String getStringResultValueNative(IF_Field field) throws FieldFalseTypeException {
		String 		ret = 	null;
		Rec20_field f = 	this.get_field(field);
		
		if (f.get_metadef().get_bIsTextType()) {
			Object ob = f.getRawResultValueCorrected();
			if (ob != null) {
				if (ob instanceof String) {
					ret = (String)ob;
				} else {
					throw new FieldFalseTypeException("Field "+field.fn()+" is not a String-Typ");
				}
			}
		} else {
			throw new FieldFalseTypeException("Field "+field.fn()+" is not a String-Typ");
		}
		
		return ret;
	}
	
	
}
