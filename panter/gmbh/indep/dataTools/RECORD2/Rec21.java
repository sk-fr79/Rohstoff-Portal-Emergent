package panter.gmbh.indep.dataTools.RECORD2;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
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
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.DB_STATICS;
import panter.gmbh.indep.dataTools.DB_STATICS.AUTOMATC_FIELDS;
import panter.gmbh.indep.dataTools.DB_STATICS.TRIGGER_FIELDS;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBResultSet;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_FILLABLE;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.Rec20.CreatorForDifferentDataTypes.TARGETTYPE;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class Rec21 extends Rec20 implements MyRECORD_IF_FILLABLE {

	protected SqlStringExtended  			sql4queryExt = null; 
	protected HashMap<IF_Field, Rec21>     	hm_upRecords = new HashMap<>();
	protected HashMap<String, RecList21>   	hm_downRecLists = new HashMap<>();    //der key besteht aus dem feldnamen (auf Laenge 100 gefuellt) und dem Where-Statement des aufrufs	
	

	//wenn mit der eigenen save-methode ein neuer datensatz gespeichert wurde, dann 
	//wird dieses feld mithilfe der letzten sequence neu aufgebaut
	protected Rec21           rec_after_save_new = null;
	
	
	protected boolean     		rawModeSaveing = false;    		//speichert herkoemmlich, aber ohne zusatzwerte und trigger 
	protected boolean    		savePrepared =  true;			//speichert mit prepared-statement, keine Trigger, Zusatzwerte werden dem Rec21 uebergeben
	
	/*
	 * 2019-11-18: vector mit  RecWatch-Objecten, die beim speichern gezogen werden und eine sperrpruefung ausfuehren
	 */
	private   VEK<RecWatch>     recWatches = new VEK<>();
	
	


	/**
	 * @param p_tab
	 * @throws myException 
	 */
	public Rec21(_TAB p_tab) throws myException {
		super(p_tab);
	}
	
	
	public Rec21(Rec21 baseRec) throws myException {
		this(baseRec.tab);
		this._fill(baseRec);
	}
	
	
	
	public Rec21 _fill(Rec21 baseRec) throws myException {
		super._fill(baseRec);
		try {
			if (this.sql4queryExt==null) {
				if (baseRec.is_ExistingRecord()) {
					this.sql4queryExt = this.createOwnSqlStringExtended(baseRec.get_myLong_dbVal(this.tab.key()).get_oLong());
				}
			} else {
				this.sql4queryExt=baseRec.sql4queryExt.clone();
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			throw new myException(e.getLocalizedMessage());
		}
		return this;
	}
	
	

	
	public Rec21 _fill_id(String id) throws myException {
		MyLong lid = new MyLong(id);
		if (lid.get_bOK()) {
			this._fill_id(lid.get_lValue());
		} else {
			throw new myException(this,"Error ID "+id+" is no number !");
		}
		return this;
	}
	
	
	public Rec21 _fill_id(long id) throws myException {
		SqlStringExtended sql = new SqlStringExtended("SELECT * FROM "+bibE2.cTO()+"."+this.tab.fullTableName()+" WHERE "+this.tab.keyFieldName()+"=?");
		sql.getValuesList().add(new Param_Long(id));
		this.executeQueryExt(sql);
		return this;
	}
	
	
	
	public Rec21 _fill_sql(String sql) throws myException {
		if (sql.toUpperCase().contains("DELETE") ||sql.toUpperCase().contains("INSERT") || sql.toUpperCase().contains("UPDATE")  ) {
			throw new myException(this,"Error sql "+sql+" is no query-statement!");
		} else {
			this.executeQueryExt(new SqlStringExtended(sql));
		}
		return this;
	}
	
	
	/**
	 * 
	 * @param sqlStringExtended
	 * @return
	 * @throws myException
	 */
	public Rec21 _fill_sql(SqlStringExtended sqlStringExtended) throws myException {
		if (sqlStringExtended.getSqlString().toUpperCase().contains("DELETE") ||sqlStringExtended.getSqlString().toUpperCase().contains("INSERT") || sqlStringExtended.getSqlString().toUpperCase().contains("UPDATE")  ) {
			throw new myException(this,"Error sql "+sqlStringExtended+" is no query-statement!");
		} else {
			this.executeQueryExt(sqlStringExtended);
		}
		return this;
	}

	
	/**
	 * creates standard-sql-extended-object for this record (without addon-fields, only table-own-fields)
	 * @param id
	 * @return
	 */
	public SqlStringExtended createOwnSqlStringExtended(Long id) {
		if (this.savePrepared) {
			SqlStringExtended sql = new SqlStringExtended("SELECT * FROM "+bibE2.cTO()+"."+this.tab.fullTableName()+" WHERE "+this.tab.keyFieldName()+"=?");
			sql.getValuesList().add(new Param_Long(id));
			return sql;
		} else {
			SqlStringExtended sql = new SqlStringExtended("SELECT * FROM "+bibE2.cTO()+"."+this.tab.fullTableName()+" WHERE "+this.tab.keyFieldName()+"="+id.toString());
			return sql;
		}
	}

	
	public Rec21 _fill(MyRECORD_IF_RECORDS rec) throws myException {
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
			this.sql4queryExt=this.createOwnQueryExt();
		} else {
			throw new myException(this,"._fill(MyRECORD) has false tablename !");
		}
		
		return this;
	}
	
	

	
	
	
	
	/**
	 * 20180105: neue variante mit beruecksichtigung des rec_after_save_new
	 * @return
	 * @throws myException
	 */
	public Rec21 _rebuildRecord() throws myException {
		if (this.is_ExistingRecord()) {
			if (this.sql4queryExt!=null && S.isFull(this.sql4queryExt.getSqlString())) {
				this.executeQueryExt(this.sql4queryExt);
			} else {
				throw new myException(this,"rebuild only possible with existing statement (1)");
			}
		} else {
			if (this.rec_after_save_new != null) {
				Rec21 r_new = this.rec_after_save_new;
				//20190403: bugfix: //alt: 
				//alt: if (r_new.sql4queryExt!=null && S.isFull(sql4queryExt.getSqlString())) {
				if (r_new.sql4queryExt!=null && S.isFull(r_new.sql4queryExt.getSqlString())) {
					try {
						this.executeQueryExt(r_new.sql4queryExt.clone());
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
						throw new myException(this,e.getLocalizedMessage());
					}
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
	
	


	
	protected void executeQueryExt(SqlStringExtended sql) throws myException {

		//zuerst die alten resultvalues rausschmeissen
		for (IF_Field f: this.keySet()) {
			this.get(f)._setResult(null);
		}
		
		
		boolean toolbox_created = false;
		if (this.oDB==null) {
			this.oDB = bibALL.get_myDBToolBox();
			toolbox_created=true;
		}
		this.sql4queryExt = sql;
		MyDBResultSet oRS = this.sql4queryExt.generateResultset(this.oDB);
		
		if (oRS.RS != null) {
			this.handleResultset(oRS, toolbox_created, S.NN(this.sql4queryExt.getSqlString()));
 		}
		else
		{
        	DEBUG.System_println("--------------------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
        	DEBUG.System_println("Rec20: Error SQL  ---------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
        	DEBUG.System_println(sql.getSqlString(),DEBUG.DEBUG_FLAG_SQL_ERROR);
        	DEBUG.System_println("--------------------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
            if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
          	throw new myException(myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT,"Rec20:__build_Hash: Cannnot open resultset !"+sql);
		}

        oRS.Close();
        if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }

	}



	/**
	 * 
	 * @return Sql-statement to build this record
	 */
	public SqlStringExtended get_sql4queryExt() {
		return sql4queryExt;
	}
	
	
	
	
	
	/**
	 * 2017-10-13: aenderung: der messagevektor wird nur noch als sammler benutzt, 
	 *             jeder ablauf innen bekommt einen eigenen
	 * @param b_commit
	 * @param mv_from_call
	 * @return
	 * @throws myException
	 */
	public Rec21 _SAVE(boolean b_commit, MyE2_MessageVector mv_from_call) throws myException {
		MyE2_MessageVector  mv = new MyE2_MessageVector();
		boolean toolbox_created = false;
		if (this.oDB==null) {
			this.oDB = bibALL.get_myDBToolBox();
			toolbox_created=true;
		}

		//20180307: fehler bei uebergabe des messagevektors mv, wurde hier nicht beruechsichtigt
		//ALT: String  c_sql = this.get_sql_4_save(true);
		//NEU
		String  			c_sql = null;
		SqlStringExtended 	o_Sql = null;
		
		if (this.savePrepared) {
			o_Sql = this.createSaveString(oDB);
		} else {
			c_sql =	this.get_sql_4_save(true,mv);
		}
		//
		
		//20180312: fehler: hier kann die mv bereits einen fehler enthalten: dann abbruch
		if (mv.get_bIsOK()) {
		
			if (S.isFull(c_sql) || o_Sql!=null) {
				
				try {

					//2019-11-18: sperren 
					mv._add(this.watchRecs());
					
					if (mv.isOK()) {
					
						//DEBUG.System_print(v_sql, "-------------Statements in Rec20: "+this.tab.fullTableName()+"   ---------------------------", DEBUG_FLAGS.MARTINS_EIGENER);
						if (this.savePrepared) {
							this.oDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(o_Sql.generatePreparedStatement(this.oDB), false, true,mv);
						} else {
							if (this.rawModeSaveing) {
								mv.add_MESSAGE(this.oDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(new VEK<String>()._a(c_sql), false, null, null));
							} else {
								mv.add_MESSAGE(this.oDB.ExecMultiSQLVector(new VEK<String>()._a(c_sql), false));
							}
						}
						if (this.is_newRecordSet()) {
							//20180108:bugfix fuer direkt uebergebene keys
							String id = this.getIdFromNewDataset();
							
							//String id = this.oDB.EinzelAbfrage(this.tab.sql_currval());
							this.rec_after_save_new = new Rec21(this.tab)._setRawModeSaveing(this.rawModeSaveing)._setPrepared(this.savePrepared)._fill_id(id);
							
							this.rec_after_save_new.getRecWatches()._a(this.recWatches);
						}
					}
				} catch (Exception e1) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("SQL-Fehler !")));
					e1.printStackTrace();
				}
				
		        if (b_commit || mv.get_bHasAlarms())  {
			        // jetzt prüfen, ob commit oder rollback
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
	 * ueberschrieben mit intrgration der watchRecs
	 */
	public MyE2_MessageVector DELETE(boolean commit) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();

		String sql = this.get_sql_2_delete();

		
		if (S.isFull(sql)) {
			boolean toolbox_created = false;
			if (this.oDB==null) {
				this.oDB = bibALL.get_myDBToolBox();
				toolbox_created=true;
			}
			//2019-11-18: sperren 
			mv._add(this.watchRecs());

			if (mv.isOK()) {
				mv.add_MESSAGE(this.oDB.ExecMultiSQLVector(new VEK<String>()._a(sql), commit));
			}
			if (!mv.isOK()) {
				this.oDB.Rollback();
			}
			
			if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
		} else {
			mv._addAlarm("Nothing to delete !");
		}
		return mv;
	}



	
//2018-08-09: MyRecord auch bei prepared-statements zulassen, die sql-create-strings einfach aufbauen	
//	/**
//	 * 
//	 * @return MyRECORD of dataset, can be casted to i.e. RECORD_ADRESSE
//	 *     If its a new Record(), then an empty Record is returned
//	 * @throws myException
//	 */
//	public MyRECORD gen_record(boolean null_if_newset) throws myException {
//		
//		//kann nur auf der not-prepaerd-basis erzeugt werden
//		if (this.sql4queryExt.isForPreparedStatement()) {
//			throw new myException(this,"Error! can only be used in not prepared-variants");
//		}
//		
//		if (this.is_newRecordSet()) {
//			if (null_if_newset) {
//				return null;
//			} else {
//				return this.tab.get_RECORD();
//			}
//		} else {
//			
//			MyRECORD record = this.tab.get_RECORD();
//			for (IF_Field f: this.keySet()) {
//				record.put(f.fieldName(), this.get(f).get_result_val());
//			}
//			record.set_cSQL_4_Build(this.sql4queryExt.getSqlString());
//			return record;
//		}
//	}


	/**
	 * neu ab 2018-08-09
	 * 
	 * @return MyRECORD of dataset, can be casted to i.e. RECORD_ADRESSE
	 *     If its a new Record(), then an empty Record is returned
	 * @throws myException
	 */
	public MyRECORD gen_record(boolean null_if_newset) throws myException {
		
//		//kann nur auf der not-prepaerd-basis erzeugt werden
//		if (this.sql4queryExt.isForPreparedStatement()) {
//			throw new myException(this,"Error! can only be used in not prepared-variants");
//		}
		
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
			record.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+"."+this.tab.fullTableName()+" WHERE "+this.tab.keyFieldName()+"="+this.get_key_value());
			return record;
		}
	}

	
	
	
	
	/**
	 * 
	 * @param my_field_related_key_of_mother_table = (field in own table:) schluessel der lookup-tabelle in der eigenen tabellen
	 * @param key_of_mother_table = (field in lookup-table:)schluessel in der lookup-tabelle
	 * @param b_buildNew
	 * @return null, when my_field_related_key_of_mother_table is empty
	 * @throws myException if field in owntable is null  or false tablerelation 
	 */
	public Rec21 get_up_Rec20(IF_Field my_field_related_key_of_mother_table, IF_Field key_of_mother_table, boolean b_buildNew) throws myException {
		Rec21 rec_ret = null;
		
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
					rec_ret = new Rec21(key_of_mother_table._t())._fill_id(id);
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
	public Rec21 get_up_Rec20(IF_Field key_of_mother_table) throws myException {
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
	 * @param my_field_related_key_of_mother_table = (field in own table:) schluessel der lookup-tabelle in der eigenen tabellen
	 * @param key_of_mother_table = (field in lookup-table:)schluessel in der lookup-tabelle
	 * @param b_buildNew
	 * @return null, when my_field_related_key_of_mother_table is empty
	 * @throws myException if field in owntable is null  or false tablerelation 
	 */
	public Rec21 get_up_Rec21(IF_Field my_field_related_key_of_mother_table, IF_Field key_of_mother_table, boolean b_buildNew) throws myException {
		return this.get_up_Rec20(my_field_related_key_of_mother_table,key_of_mother_table,b_buildNew);
	}


	/**
	 * 
	 * @param key_of_mother_table = (field in lookup-table:)schluessel in der lookup-tabelle // possible, if field in own table has same name
	 * @return
	 * @throws myException
	 */
	public Rec21 get_up_Rec21(IF_Field key_of_mother_table) throws myException {
		return this.get_up_Rec20(key_of_mother_table);
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
	public RecList21 get_down_reclist21(IF_Field key_in_daughter, String where, String order, boolean force_rebuild) throws myException {
		String key = key_in_daughter.tnfn()+"|"+S.NN(where);
		
		RecList21 rl = this.hm_downRecLists.get(key);
		
		
		if (rl==null || force_rebuild) {
			_TAB tableDaughter = key_in_daughter._t();

			rl = new RecList21(tableDaughter);
			
			//falls es ein recnew ist, der bereits abespeichert wurde, dann findet sich die ID im @rec_after_save_new
			String id = null;
			if (S.isFull(this.get_key_value())) {
				id = this.get_key_value();
			} else {
				if (this.rec_after_save_new!=null && S.isFull(this.rec_after_save_new.get_key_value())) {
					id = this.rec_after_save_new.get_key_value();
				}	
			}
			
			if (S.isEmpty(id)) {
				throw new myException(this,"get_down_reclist20 is not possible!");
			}
 
			String c_sql_in_daughter = "SELECT * FROM "+bibE2.cTO()+"."+tableDaughter.fullTableName()+" WHERE "+key_in_daughter.tnfn()+"=?";
			if (S.isFull(where)) {
				c_sql_in_daughter = c_sql_in_daughter + " AND " +where;
			}
			if (S.isFull(order)) {
				c_sql_in_daughter = c_sql_in_daughter + " ORDER BY " +order;
			}
			
			SqlStringExtended sqlExt = new SqlStringExtended(c_sql_in_daughter);
			sqlExt.getValuesList().add(new Param_Long(new Long(id)));
			rl._fill(sqlExt);
			
			rl._setPreparedStatusAllMembers(this.savePrepared);
			
			this.hm_downRecLists.put(key, rl);
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
	public RecList21 get_down_reclist21(IF_Field key_in_daughter, String where, String order) throws myException {
		return this.get_down_reclist21(key_in_daughter, where, order,true);
	}

	/**
	 * 
	 * @param key_in_daughter = schluessel in der tochter-tabelle
	 * @return
	 * @throws myException
	 */
	public RecList21 get_down_reclist21(IF_Field key_in_daughter) throws myException {
		return this.get_down_reclist21(key_in_daughter, null, null,true);
	}


	
	public void set_sql4query(SqlStringExtended p_sql4query) {
		this.sql4queryExt = p_sql4query;
	}
	
	
	public SqlStringExtended createOwnQueryExt() throws myException {
		return new SqlStringExtended("SELECT * FROM "+bibE2.cTO()+"."+this.tab.fullTableName()+" WHERE "+this.tab.keyFieldName()+"="+this.get_ufs_dbVal(this.tab.key(), "@@@@@@@@@"));
	}
	
	
	
	
	
	public Rec20 _set_recAfterSave(Rec20 rec) throws myException {
		throw new myException(this,"Only record from same type (Rec21) can be set as recAfterSave !!");
	}

	/**
	 * hier kann eine rec_after_save von aussen eingespielt werden ...
	 * @param rec
	 * @return
	 */
	public Rec21 _set_recAfterSave(Rec21 rec) throws myException {
		this.rec_after_save_new = rec;
		if (this.get_tab()!= rec.get_tab()) {
			throw new myException(this,"Only record from same type can be set as recAfterSave !!");
		}
		
		return this;
	}
	
	
	
	
	
	/**
	 * 2018-01-09: ausgabe debugging - informationen
	 */
	public LinkedHashMap<String, String> getDebugInfos() {
		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();
		
		if (this.tab!=null) {
			hm.put("TABLENAME", this.tab.fullTableName());
		} else {
			hm.put("TABLENAME", "<tabname>");
		}
		if (this.sql4queryExt!=null) {
			hm.put("BUILDSTRING", this.sql4queryExt.getSqlString());
		} else {
			hm.put("BUILDSTRING", "<buildstring>");
		}
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


	public boolean isRawModeSaveing() {
		return rawModeSaveing;
	}


	/**
	 * when true, the reocrd saves without triggers 
	 * @param p_rawModeSaveing
	 * @return
	 */
	public Rec21 _setRawModeSaveing(boolean p_rawModeSaveing) {
		this.rawModeSaveing = p_rawModeSaveing;
		return this;
	}

	/**
	 * when true, the reocrd saves without triggers 
	 * @param rawModeSaveing
	 * @return
	 */
	public Rec21 _setRaw() {
		this.rawModeSaveing = true;
		return this;
	}


	public SqlStringExtended createSaveString(MyDBToolBox oDB) throws myException {
		
		HashMap<IF_Field, ParamDataObject> hmValues4Statement = new HashMap<IF_Field, ParamDataObject>();
		
		
		//triggerfields immer rauslassen
		VEK<IF_Field>  fieldNotTouch = new VEK<IF_Field>()._addVektor(()-> { 	VEK<IF_Field> v = new VEK<>();
						for (DB_STATICS.TRIGGER_FIELDS t: DB_STATICS.TRIGGER_FIELDS.values()) {
							v._a(_TAB.find_field(this.tab, t.name()));
						}
						return v;
				});
		

		Vector<ParamDataObject> vParams = new Vector<ParamDataObject>();

		StringBuffer sb = new StringBuffer();
		
		if (this.is_newRecordSet()) {
			
			
			//dann gibt es ein insert
			if (!this.get(this.tab.key()).is_newValPresent()) {
				if (this.hm_Field_Value_pairs_from_outside.get(this.tab.keyFieldName())==null) {
					this._add_sequencer();
				}
			}
			
			//jetzt die die gewuenschten automatikfelder in die hashmaps einfuegen
			Vector<String> alleAutomatics = oDB.get_AutomaticWrittenFields();
			for (DB_STATICS.AUTOMATC_FIELDS f: DB_STATICS.AUTOMATC_FIELDS.values()) {
				if (alleAutomatics.contains(f.name())) {
					this.addDbVal(f, hmValues4Statement, this.hm_Field_Value_pairs_from_outside);
				}
			}
			

			//jetzt alle felder durchsuchen und die restlichen einfuegen
			for (IF_Field f: this.get_tab().get_fields()) {
				if (fieldNotTouch.contains(f)) {
					continue;
				}
				
				if (hmValues4Statement.containsKey(f)) {
					continue;
				}
				
				if (this.hm_Field_Value_pairs_from_outside.containsKey(f.fieldName())) {
					continue;
				}
				
				hmValues4Statement.put(f, this.get(f).getParamDataObject());
			}
			
			//jetzt das statement bauen
			//alle zu sammelnden keys rausziehen
			VEK<IF_Field> fieldsForStatement = new VEK<IF_Field>()
						._addVektor(()->{
										VEK<IF_Field> v = new VEK<IF_Field>();
										for (String s1: this.hm_Field_Value_pairs_from_outside.keySet()) {
											v._a(_TAB.find_field(this.tab, s1));
										}
										return v;
									}
						)._a(hmValues4Statement.keySet());
			
			
			sb.append("INSERT INTO "+bibE2.cTO()+"."+this.tab.fullTableName()+" (");
			
			IF_Field fLast = fieldsForStatement.lastElement();
			
			for (IF_Field f: fieldsForStatement) {
				String addS = (f==fLast)?") VALUES (":",";
				sb.append(f.fn()+addS);
			}

			
			for (IF_Field f: fieldsForStatement) {
				String addS = (f==fLast)?")":",";

				if (hmValues4Statement.containsKey(f)) {
					sb.append("?"+addS);
					vParams.add(hmValues4Statement.get(f));
				} else {
					sb.append(this.hm_Field_Value_pairs_from_outside.get(f.fn())+addS);
				}
			}
			
		} else {

			
			//dann ein update
			fieldNotTouch.add(this.tab.key());
			
			
			//jetzt die die gewuenschten automatikfelder in die hashmaps einfuegen
			Vector<String> alleAutomatics = oDB.get_AutomaticWrittenFields();
			for (DB_STATICS.AUTOMATC_FIELDS f: DB_STATICS.AUTOMATC_FIELDS.values()) {
				if (alleAutomatics.contains(f.name())) {
					this.addDbVal(f, hmValues4Statement, this.hm_Field_Value_pairs_from_outside);
				}
			}
			

			//jetzt alle felder durchsuchen und die restlichen einfuegen
			for (IF_Field f: this.get_tab().get_fields()) {
				if (fieldNotTouch.contains(f)) {
					continue;
				}
				
				if (hmValues4Statement.containsKey(f)) {
					continue;
				}
				
				if (this.hm_Field_Value_pairs_from_outside.containsKey(f.fn())) {
					continue;
				}

				hmValues4Statement.put(f, this.get(f).getParamDataObject());
			}
			
			//jetzt das statement bauen
			//alle zu sammelnden keys rausziehen
			VEK<IF_Field> fieldsForStatement = new VEK<IF_Field>()
						._addVektor(()->{
										VEK<IF_Field> v = new VEK<IF_Field>();
										for (String s1: this.hm_Field_Value_pairs_from_outside.keySet()) {
											v._a(_TAB.find_field(this.tab, s1));
										}
										return v;
									}
						)._a(hmValues4Statement.keySet());
			
			
			sb.append("UPDATE "+bibE2.cTO()+"."+this.tab.fullTableName()+" SET ");
			
			IF_Field fLast = fieldsForStatement.lastElement();
			
			for (IF_Field f: fieldsForStatement) {
				String addS = (f==fLast)?"":",";

				if (hmValues4Statement.containsKey(f)) {
					sb.append(f.fieldName()+"=?"+addS);
					vParams.add(hmValues4Statement.get(f));
				} else {
					sb.append(f.fn()+"="+this.get_hm_Field_Value_pairs_from_outside().get(f.fn())+addS);
//					DEBUG._print(this.hm_Field_Value_pairs_from_outside.get(f.fn())+"="+this.get_hm_Field_Value_pairs_from_outside().get(f.fn())+addS);
					
				}
			}
			sb.append(" WHERE "+this.tab.key().fn()+"=?");
			vParams.add(this.get(this.tab.key()).getParamDataObject());

		}
		
		return new SqlStringExtended(sb.toString())._addParameters(vParams);
	}
	

	/**
	 * 
	 * @param f
	 * @param hmValues4Statement
	 * @param hm_Field_Value_pairs_from_outside
	 * adds imput into the correct hashmaps
	 * @throws myException
	 */
	public void addDbVal(	AUTOMATC_FIELDS f, 
							HashMap<IF_Field, ParamDataObject> hmValues4Statement, 
							HashMap<String,String> hm_Field_Value_pairs_from_outside
							) throws myException {
		if (f==null) {
			throw new myException("DB_STATICS:getDbVal: null is not allowed !");
		}
		
		switch (f) {
		case ID_MANDANT:
			hmValues4Statement.put(this.find_field(f.name()), new Param_Long(bibALL.get_RECORD_MANDANT().get_ID_MANDANT_lValue(0l)));
			break;
			
		case GEAENDERT_VON:
			hmValues4Statement.put(this.find_field(f.name()), new Param_String("",bibALL.get_KUERZEL()));
			break;
			
		case LETZTE_AENDERUNG:
			hm_Field_Value_pairs_from_outside.put(f.name(), "SYSDATE");
			break;
		
		default:
			throw new myException("DB_STATICS:getDbVal: "+f.name()+" cannot be used!");
		}
	}

	
//	@Override
//	public Rec21 _setNewVal(IF_Field field, Object value, MyE2_MessageVector mv)  throws myException {
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
	@Override
	public Rec21 _setNewVal(String fieldName,  Object value, MyE2_MessageVector mv) throws myException {
		IF_Field field = _TAB.find_field(this.get_tab(), fieldName);
		if (field == null) {
			throw new myException("Field with name :"+fieldName+" cannot be found in Rec21 with _TAB "+this.get_tab().fullTableName());
		} else {
			return _setNewVal(field, value, mv);
		}
	}
	
	

	@Override
	public Rec21 _setNewVal(IF_Field field, Object value, MyE2_MessageVector mv)  throws myException {
		if (!this.keySet().contains(field)) {
			throw new myException(this,"nv(): Field: "+field.fn()+" is not in the Rec20 "+S.NN(this.get_TABLENAME()));
		}
		
		MyMetaFieldDEF fd = field.generate_MetaFieldDef();
		
		
		//20190404: separate behandlung von null 
		if (value == null) {
			if (!fd.get_bFieldNullableBasicAndInteractive()) {
				throw new myException(this,"Nullable not allowed! Field "+field.fn()+" needs a non-null-value!");
			} else {
				
				HashMap<TARGETTYPE, Object> hmRet =  this.creatorForDifferentDataTypes.getDataTypes(field, value);

				this.get(field)._setNewVal((String)hmRet.get(TARGETTYPE.FORMATED_STRING),value, mv);
				return this;

			}
		} else {
		
			//hier pruefen, ob der typ uebereinsstimmt
			if (fd.get_bIsNumberTypeWithOutDecimals())   {
				if (!(value instanceof Long)) {
					throw new myException(this,"False datatyp! Field "+field.fn()+" needs a type Long, you sended "+value.getClass().getName());
				}
			} else if (fd.get_bIsNumberTypeWithDecimals())   {
				if (!(value instanceof BigDecimal)) {
					throw new myException(this,"False datatyp! Field "+field.fn()+" needs a type BigDecimal, you sended "+value.getClass().getName());
				}
	        }
			else if (DB_META.vDB_DATE_TYPES.contains(fd.get_FieldType()))   {
				if (!(value instanceof Date)) {
					throw new myException(this,"False datatyp! Field "+field.fn()+" needs a type Date, you sended "+value.getClass().getName());
				}
	 	    }
			else if (DB_META.vDB_TEXT_TYPES.contains(fd.get_FieldType()))  {
				if (!(value instanceof String)) {
					throw new myException(this,"False datatyp! Field "+field.fn()+" needs a type String, you sended "+value.getClass().getName());
				}
			} else {
				throw new myException(this,"Field "+field.fn()+"! Error! Cannot find Metadefs");
			}
			
			
			HashMap<TARGETTYPE, Object> hmRet =  this.creatorForDifferentDataTypes.getDataTypes(field, value);
	
			this.get(field)._setNewVal((String)hmRet.get(TARGETTYPE.FORMATED_STRING),value, mv);
			return this;
		}
	}
	
	
	@Override
	public Rec21 _nv(IF_Field f, String formated_value, MyE2_MessageVector mv) throws myException {
		if (!this.keySet().contains(f)) {
			throw new myException(this,"_nv() not possible, field is not in this set!", f.tnfn());
		}
		//HashMap<TARGETTYPE, Object> hmRet =  this.creatorForDifferentDataTypes.getDataTypes(f, formated_value);
		
		this.get(f)._setNewVal(formated_value,f.generate_MetaFieldDef().getRaw(formated_value), mv);
		return this;
	}


	
	
	public boolean isSavePrepared() {
		return savePrepared;
	}


	public Rec21 _setPrepared() {
		this.savePrepared = true;
		return this;
	}
	public Rec21 _setPrepared(boolean prepared) {
		this.savePrepared = prepared;
		return this;
	}

	/**
	 * Rec21-pointer auf die das zuletzt von der datenbank geladene recset
	 * Bei normalem gelesenen record ist das ein pointer auf sich selbst, sonst ein pointer auf das object rec_after_save_new, falls der recordnew bereits gefuellt ist  
	 * 
	 * @return last loadet record (or before save new Rec20 itself)
	 */
	public Rec21 getRec20LastRead() {
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
	 * Rec21-pointer auf die das zuletzt von der datenbank geladene recset
	 * Bei normalem gelesenen record ist das ein pointer auf sich selbst, sonst ein pointer auf das object rec_after_save_new, falls der recordnew bereits gefuellt ist  
	 * 
	 * @return last loadet record (or before save new Rec20 itself)
	 */
	public Rec21 getRec21LastRead() {
		return this.getRec20LastRead();
	}


	/**
	 * 2018-04-27 
	 * @param conn
	 * @return
	 */
	public Rec21 _setConn(MyConnection conn) {
		super._setConn(conn);
		return this;
	}
	

	/**
	 * 2018-08-09: mues hier ueberschrieben werden, da sonst die methode immer null uebergibt, der alter
	 *             Rec20 hat ein eigenes feld rec_after_save_new
	 * bei eigen geschriebene datensaetzen steht hier der neu geschriebene record
	 * @return
	 */
	@Override
	public Rec21 get_rec_after_save_new() {
		return rec_after_save_new;
	}

	
	/**
	 * 
	 * @author martin
	 * @date 09.04.2019
	 *
	 * @param excludeField
	 * @param mv
	 * @return new Rec21 with fields filled with values of this record
	 * @throws myException
	 */
	public Rec21 getRecForCreateCopy(VEK<IF_Field> excludeField, MyE2_MessageVector mv) throws myException {
		
		Rec21 recNew = new Rec21(this.tab);
		
		for (IF_Field f: this.keySet()) {
			if (excludeField != null && excludeField.contains(f)) {
				continue;
			}
			
//			Object raw = this.getRawValueCorrected(f);
//			
//			if (raw!=null) {
//				DEBUG._print("Feld:"+f.fieldName()+": Wert: "+this.getRawVal(f).toString()+"  type:"+raw.getClass().getName());
//			}
			
			recNew._setNewVal(f, this.getRawValueCorrected(f), mv);
		}

		return recNew;
	}
	
	/**
	 * 
	 * @author martin
	 * @date 09.04.2019
	 *
	 * @param excludeField
	 * @param mv
	 * @return new Rec21 with fields filled with values of this record
	 * @throws myException
	 */
	public Rec21 getRecForCreateCopyStdExclude(MyE2_MessageVector mv) throws myException {
		
		VEK<IF_Field> excludes = new VEK<>();
		excludes._a(this.tab.key());
		
		for (TRIGGER_FIELDS tf: TRIGGER_FIELDS.values()) {
			IF_Field f = _TAB.find_field(this.tab, tf.name());
			if (f!=null) {
				excludes._a(f);
			}
		}
		for (AUTOMATC_FIELDS af: AUTOMATC_FIELDS.values()) {
			IF_Field f = _TAB.find_field(this.tab, af.name());
			if (f!=null) {
				excludes._a(f);
			}
		}
		
		return this.getRecForCreateCopy(excludes, mv);
	}
	
	
	public VEK<RecWatch> getRecWatches() {
		return recWatches;
	}

	
	public Rec21 _addRecWatch(RecWatch rw) {
		this.recWatches._a(rw);
		return this;
	}
	public Rec21 _addRecWatch(VEK<RecWatch> rws) {
		this.recWatches._a(rws);
		return this;
	}
	
	
	protected MyE2_MessageVector watchRecs() {
		MyE2_MessageVector mv = bibMSG.newMV();
		
		for (RecWatch w: recWatches) {
			try {
				if (!w.lock()) {
					mv._addAlarm(S.ms("Sperre ist fehlgegangen. Vermutlich wurde ein Datensatz extern verändert !"));
				}
			} catch (myException e) {
				e.printStackTrace();
				mv._addAlarm(S.ms("Sperre ist fehlgegangen. Unbekannter Fehler !<6e303917-67bd-429d-b075-4503f3f98b6d>"));
			}
		}
		
		
		return mv;
	}
	
	
}
