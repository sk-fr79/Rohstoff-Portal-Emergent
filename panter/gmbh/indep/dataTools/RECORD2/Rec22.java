/**
 * panter.gmbh.indep.dataTools.RECORD2
 * @author martin
 * @date 15.10.2020
 * 
 */
package panter.gmbh.indep.dataTools.RECORD2;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.BasicInterfaces.IF_BasicTypeWrapper.DataTypeException;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector.MessageException;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.DB_STATICS;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.Rec20.CreatorForDifferentDataTypes.TARGETTYPE;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 15.10.2020
 * weitere Entwicklung von Rec21: speichert immer via preparedStatement und 
 * nutzt beim Speichern den status des Felds SYS_TRIGGER_VERSION zur pruefung auf concurred access (z.B. aus CUBA-apps)
 */
public class Rec22 extends Rec21 {

	protected Rec22           rec_after_save_new = null;
	private   Long            idOfSaveNew = null;             //wird nach dem ersten speichern des neuen datensatzes gesetzt, bei weiteren speichervorgaengen null




	protected HashMap<IF_Field, Rec22>     	hm_upRecords = new HashMap<>();
	protected HashMap<String, RecList22>   	hm_downRecLists = new HashMap<>();    //der key besteht aus dem feldnamen (auf Laenge 100 gefuellt) und dem Where-Statement des aufrufs	

	/**
	 * @author martin
	 * @date 15.10.2020
	 *
	 * @param p_tab
	 * @throws myException
	 */
	public Rec22(_TAB p_tab) throws myException {
		super(p_tab);
	}

	/**
	 * @author martin
	 * @date 15.10.2020
	 *
	 * @param baseRec
	 * @throws myException
	 */
	public Rec22(Rec21 baseRec) throws myException {
		super(baseRec);
	}

	@Override
	public Rec22 _fill(Rec21 baseRec) throws myException {
		super._fill(baseRec);
		return this;
	}

	
	/**
	 * 
	 * @author manfred
	 * @date 16.12.2020
	 *
	 * @param baseRec
	 * @throws myException
	 */
	public Rec22(Rec22 baseRec) throws myException {
		super(baseRec);
	}

	/**
	 * 
	 * @author manfred
	 * @date 16.12.2020
	 *
	 * @param baseRec
	 * @return
	 * @throws myException
	 */
	
	public Rec22 _fill(Rec22 baseRec) throws myException {
		super._fill(baseRec);
		return this;
	}

	
	@Override
	public Rec22 _fill_id(String id) throws myException {
		super._fill_id(id);
		return this;

	}

	@Override
	public Rec22 _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}

	@Override
	public Rec22 _fill_sql(String sql) throws myException {
		super._fill_sql(sql);		
		return this;
	}

	@Override
	public Rec22 _fill_sql(SqlStringExtended sqlStringExtended) throws myException {
		super._fill_sql(sqlStringExtended);
		return this;
	}

	@Override
	public Rec22 _fill(MyRECORD_IF_RECORDS rec) throws myException {
		super._fill(rec);
		return this;
	}

	@Override
	public Rec22 _rebuildRecord() throws myException {
		if (this.is_ExistingRecord()) {
			if (this.sql4queryExt!=null && S.isFull(this.sql4queryExt.getSqlString())) {
				this.executeQueryExt(this.sql4queryExt);
			} else {
				throw new myException(this,"rebuild only possible with existing statement (c606cd46-568b-11eb-ae93-0242ac130002)");
			}
		} 
		
//		else {
//			//hier muesste bei einem nicht neuen immer ein sql4queryExt vorhanden sein
//			try {
//				SqlStringExtended sql= this.sql4queryExt.clone();
//				this.executeQueryExt(sql);
//			} catch (CloneNotSupportedException e) {
//				e.printStackTrace();
//				throw new myException(this,e.getLocalizedMessage()+"(d3d72e98-568b-11eb-ae93-0242ac130002)");
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new myException(this,"rebuild only possible with existing statement (c0e21f32-568b-11eb-ae93-0242ac130002)"+e.getLocalizedMessage());
//			}
//
//		}
		return this;
	}

	
	@Override
	public Rec20 _set_recAfterSave(Rec20 rec) throws myException {
		throw new myException(this,"Only record from same type (Rec22) can be set as recAfterSave !!");
	}

	public Rec22 _set_recAfterSave(Rec22 rec) throws myException {
		super._set_recAfterSave(rec);
		return this;		
	}

	@Override
	@Deprecated
	public Rec22 _setRawModeSaveing(boolean p_rawModeSaveing) {
		return this;
	}

	@Override
	@Deprecated
	public Rec22 _setRaw() {
		return this;
	}

	@Override
	public Rec22 _setNewVal(String fieldName, Object value, MyE2_MessageVector mv) throws myException {
		super._setNewVal(fieldName, value, mv);	
		return this;		
	}

	@Override
	public Rec22 _setNewVal(IF_Field field, Object value, MyE2_MessageVector mv) throws myException {
		super._setNewVal(field, value, mv);
		return this;		
	}

	@Override
	public Rec22 _nv(IF_Field f, String formated_value, MyE2_MessageVector mv) throws myException {
		super._nv(f, formated_value, mv);
		return this;		
	}

	@Override
	@Deprecated
	public Rec22 _setPrepared() {
		return this;
	}

	@Override
	@Deprecated
	public Rec22 _setPrepared(boolean prepared) {
		return this;
	}

	@Override
	public Rec22 _setConn(MyConnection conn) {
		super._setConn(conn);
		return this;		
	}

	@Override
	public Rec22 _addRecWatch(RecWatch rw) {
		super._addRecWatch(rw);
		return this;		
	}

	@Override
	public Rec22 _addRecWatch(VEK<RecWatch> rws) {
		super._addRecWatch(rws);
		return this;		
	}


	@Override
	public boolean compare_to_actual(HashMap<String, String> hm_diffs) throws myException {
		if (this.is_newRecordSet()) {
			throw new myException(this,"Compare to actual is only possible on an existing record !");
		}
		Rec22 recComp = new Rec22(this.tab)._fill_id(this.get_key_value());
		return this.compareTo(recComp, hm_diffs);
	}

	

	/**
	 * veraenderte _SAVE - methode, speichert nur noch im prepared-modus und gibt im fehlerfall null zurueck, sonst sich selbst, neu geladen Rec22-object neu geladen
	 */
	@Override
	public Rec22 _SAVE(boolean b_commit, MyE2_MessageVector mv_from_call) throws myException {
		
		MyE2_MessageVector  mv = new MyE2_MessageVector();
		boolean toolbox_created = false;
		if (this.oDB==null) {
			this.oDB = bibALL.get_myDBToolBox();
			toolbox_created=true;
		}

		SqlStringExtended 	o_Sql = this.createSaveString(oDB);
		
		//20180312: fehler: hier kann die mv bereits einen fehler enthalten: dann abbruch
		if (mv.get_bIsOK()) {
		
			if (o_Sql!=null) {
				
				try {
					// sperrsaetze ausfuehren 
					mv._add(this.watchRecs());
					
					if (mv.isOK()) {
						
						int countSave = this.oDB.executeStatements(new VEK<SqlStringExtended>()._a(o_Sql), false);
						
						if (countSave!=1) {
							
							mv._addAlarm(S.ms("Speicherfehler, es wurde ein externer Datenzugriff festgestellt (").ut(this.tab.baseTableName()+")"));
							
						} else {

							if (this.is_newRecordSet()) {
								String id = this.getIdFromNewDataset();
								//beide fuellen (recAftersavenew wg. kompatibilitaet und sich selbst) 
								this.RESET_ALL_NEWVALUES();

								
								//zuerst eine neue recAfterSaveNew
								this.rec_after_save_new = new Rec22(this.tab)._fill_id(id);
								this.rec_after_save_new.getRecWatches()._a(this.getRecWatches());
								//und dann selbst fuellen
								this._fill_id(id);
								
								this.idOfSaveNew = this.getIdLong();
								 
							} else {
								
								//sich selbst neu laden (alles drumrum loeschen)
								Long id = this.getIdLong();
								this.RESET_ALL_NEWVALUES();
								this.hmOverheadFields.clear();
								this._fill_id(id);
								this.rec_after_save_new=null;
								this.idOfSaveNew = null;
								
							}
						}
					}
				} catch (SQLException eSql) {
					mv._addAlarm(eSql.getLocalizedMessage());
					eSql.printStackTrace();
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

	
	
	//neue methode ohne MyE2_MessageVector
	public Rec22 _SAVE(boolean b_commit) throws Exception {
		MyE2_MessageVector mv = bibMSG.getNewMV();
		this._SAVE(b_commit,mv);
		if (mv.hasAlarms()) {
			throw new Exception("Rec22-Save-Error: <cb17bca6-7057-11eb-9439-0242ac130002>"+mv.get_MessagesAsText());
		}
		return this;
	}
	
	
	
	@Override
	public SqlStringExtended createSaveString(MyDBToolBox oDB) throws myException {
		
		
		//nachsehen, ob das Feld SYS_TRIGGER_VERSION vorhanden ist
		IF_Field versionField = _TAB.find_field(this.get_tab(), "SYS_TRIGGER_VERSION"); 
		boolean isVersioned = (versionField!=null);
		
		
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
			
			if (isVersioned) {
				Long versionNumber = (Long)this.getLongResultValueNative("SYS_TRIGGER_VERSION");
				if (versionNumber!=null) {
					sb.append(" AND "+versionField.fn()+"=?");
					vParams.add(this.get(versionField).getParamDataObject());
				} else {
					sb.append(" AND "+versionField.fn()+" IS NULL ");
				}
			}
		}
		
		return new SqlStringExtended(sb.toString())._addParameters(vParams);
	}
	
	@Override
	@Deprecated
	public Rec22 get_rec_after_save_new() {
		return rec_after_save_new;
	}

	
	
	



	
	/**
	 * 
	 * @param my_field_related_key_of_mother_table = (field in own table:) schluessel der lookup-tabelle in der eigenen tabellen
	 * @param key_of_mother_table = (field in lookup-table:)schluessel in der lookup-tabelle
	 * @param b_buildNew
	 * @return null, when my_field_related_key_of_mother_table is empty
	 * @throws myException if field in owntable is null  or false tablerelation 
	 */
	public Rec22 get_up_Rec20(IF_Field my_field_related_key_of_mother_table, IF_Field key_of_mother_table, boolean b_buildNew) throws myException {
		Rec22 rec_ret = null;
		
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
					rec_ret = new Rec22(key_of_mother_table._t())._fill_id(id);
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

	
	
	public Rec22 getUpRec2(IF_Field my_field_related_key_of_mother_table, IF_Field key_of_mother_table, boolean b_buildNew) throws myException {
		return this.get_up_Rec20(my_field_related_key_of_mother_table, key_of_mother_table, b_buildNew);
	}
	
	public Rec22 getUpRec2(IF_Field key_of_mother_table) throws myException {
		return this.get_up_Rec20( key_of_mother_table);
	}
	
	
	
	public Rec22 getUpRec22(IF_Field my_field_related_key_of_mother_table, IF_Field key_of_mother_table, boolean b_buildNew) throws myException {
		return this.get_up_Rec20(my_field_related_key_of_mother_table, key_of_mother_table, b_buildNew);
	}
	
	public Rec22 getUpRec22(IF_Field key_of_mother_table) throws myException {
		return this.get_up_Rec20( key_of_mother_table);
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
	public RecList22 get_down_reclist22(IF_Field key_in_daughter, String where, String order, boolean force_rebuild) throws myException {
		String key = key_in_daughter.tnfn()+"|"+S.NN(where);
		
		RecList22 rl = this.hm_downRecLists.get(key);
		
		
		if (rl==null || force_rebuild) {
			_TAB tableDaughter = key_in_daughter._t();

			rl = new RecList22(tableDaughter);
			
			//falls es ein recnew ist, der bereits abespeichert wurde, dann findet sich die ID im @rec_after_save_new
			String id = null;
			if (S.isFull(this.get_key_value())) {
				id = this.get_key_value();
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
	public RecList22 get_down_reclist22(IF_Field key_in_daughter, String where, String order) throws myException {
		return this.get_down_reclist22(key_in_daughter, where, order,true);
	}

	/**
	 * 
	 * @param key_in_daughter = schluessel in der tochter-tabelle
	 * @return
	 * @throws myException
	 */
	public RecList22 get_down_reclist22(IF_Field key_in_daughter) throws myException {
		return this.get_down_reclist22(key_in_daughter, null, null,true);
	}



	/**
	 * 
	 * @param key_of_mother_table = (field in lookup-table:)schluessel in der lookup-tabelle // possible, if field in own table has same name
	 * @return
	 * @throws myException
	 */
	public Rec22 get_up_Rec20(IF_Field key_of_mother_table) throws myException {
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
	public Rec22 get_up_Rec22(IF_Field my_field_related_key_of_mother_table, IF_Field key_of_mother_table, boolean b_buildNew) throws myException {
		return this.get_up_Rec20(my_field_related_key_of_mother_table,key_of_mother_table,b_buildNew);
	}

	
	
	public Rec22 get_up_Rec22(IF_Field key_of_mother_table) throws myException {
		return this.get_up_Rec20(key_of_mother_table);
	}

	

	
	@Override
	public MyE2_MessageVector DELETE() throws myException {
		return this.DELETE(true);
	}

	
	@Override
	public MyE2_MessageVector DELETE(boolean commit) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();

		boolean toolbox_created = false;
		if (this.oDB==null) {
			this.oDB = bibALL.get_myDBToolBox();
			toolbox_created=true;
		}

		SqlStringExtended 	o_Sql = this.getDeleteString(mv);
		
		//20180312: fehler: hier kann die mv bereits einen fehler enthalten: dann abbruch
		if (mv.get_bIsOK()) {
			if (o_Sql!=null) {
				try {
					
					// sperrsaetze ausfuehren 
					mv._add(this.watchRecs());
					if (mv.isOK()) {
						int countSave = this.oDB.executeStatements(new VEK<SqlStringExtended>()._a(o_Sql), false);
						if (countSave!=1) {
							mv._addAlarm(S.ms("Fehler beim Löschen, es wurde ein externer Datenzugriff festgestellt (").ut(this.tab.baseTableName()+")"));
						}
					}
				} catch (SQLException eSql) {
					mv._addAlarmUT(eSql.getLocalizedMessage());
					eSql.printStackTrace();
				} catch (myException e1) {
					mv._addAlarmUT(e1.getLocalizedMessage());
					e1.printStackTrace();
				}
				
		        if (commit || mv.get_bHasAlarms())  {
			        // jetzt prüfen, ob commit oder rollback
			        try {
			            if (mv.get_bIsOK()) {
			            	this.oDB.Commit();
			            } else {
			            	this.oDB.ownRollBack();
			            	bibALL.reset_Trigger_Counter();
			            }
			        } catch (SQLException e) {
			            mv._addAlarmUT(e.getLocalizedMessage());
			        }
		        }
			}
		}
        if (toolbox_created) {
        	bibALL.destroy_myDBToolBox(oDB); 
        }
        
   		return mv;
	}

	
	
	public SqlStringExtended  getDeleteString(MyE2_MessageVector mv)  {
		
		SqlStringExtended  sql = null;
		
		try {
			Vector<ParamDataObject> vParams = new Vector<ParamDataObject>();
			
			if (this.is_ExistingRecord()) {
				
				//nachsehen, ob das Feld SYS_TRIGGER_VERSION vorhanden ist
				IF_Field versionField = _TAB.find_field(this.get_tab(), "SYS_TRIGGER_VERSION"); 
				boolean isVersioned = (versionField!=null);
				
				VEK<String> wheres = new VEK<String>();
				wheres._a(this.tab.key().tnfn()+"=?");
				vParams.add(this.get(this.tab.key()).getParamDataObject());

				if (isVersioned) {
					Long versionNumber = (Long)this.getLongResultValueNative("SYS_TRIGGER_VERSION");
					if (versionNumber!=null) {
						wheres._a(versionField.fn()+"=?");
						vParams.add(this.get(versionField).getParamDataObject());

					} else {
						mv._addAlarm("Error creating delete-statement ... <5de83542-12ac-11eb-adc1-0242ac120002>");
						sql=null;
					}
				}
				sql = new SqlStringExtended("DELETE FROM "+bibE2.cTO()+"."+this.tab.fullTableName()+" WHERE "+wheres.concatenante(" AND "))._addParameters(vParams);
			} else {
				mv._addAlarm("Error: Record was not saved !");
				sql=null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv._addAlarm("Undefined Error: <2b9106a2-12af-11eb-adc1-0242ac120002>");
			sql=null;
		}
		
		return sql;
	}
	


	@Override
	public String get_DELETE_STATEMENT() throws myException {
		return this.get_sql_2_delete();
	}

	@Override
	public String get_sql_2_delete() throws myException {
		if (this.is_ExistingRecord()) {
			
			//nachsehen, ob das Feld SYS_TRIGGER_VERSION vorhanden ist
			IF_Field versionField = _TAB.find_field(this.get_tab(), "SYS_TRIGGER_VERSION"); 
			boolean isVersioned = (versionField!=null);
			
			VEK<String> wheres = new VEK<String>();
			wheres._a(this.tab.key().tnfn()+"="+this.get_key_value());
			
			if (isVersioned) {
				Long versionNumber = (Long)this.getLongResultValueNative("SYS_TRIGGER_VERSION");
				if (versionNumber!=null) {
					wheres._a(versionField.fn()+"="+versionNumber.toString());
					
				} else {
					return null;
				}
			}

			return "DELETE FROM "+bibE2.cTO()+"."+this.tab.fullTableName()+" WHERE "+wheres.concatenante(" AND ");
		} else {
			return null;
		}
	}




	/**
	 * @author manfred
	 * @date 16.12.2020
	 * Übernommen aus Rec21
 	 */
	@Override
	public Rec22 getRecForCreateCopy(VEK<IF_Field> excludeField, MyE2_MessageVector mv) throws myException {
		Rec22 recNew = new Rec22(this.tab);
		
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
	 * @return the idOfSaveNew
	 */
	public Long getIdOfSaveNew() {
		return idOfSaveNew;
	}


	
	
	//2021-02-18
	//neue setters mit Exception
	public Rec22 _setNewVal(String fieldName, Object value) throws Rec22FieldNotInTableException,MessageException,Exception {
		IF_Field field = _TAB.find_field(this.tab, fieldName);
		if (field==null) {
			throw new Rec22FieldNotInTableException("Field "+fieldName+" not found in Rec22!");
		}
		this._setNewVal(field, value);	
		return this;		
	}

	
	
	
	//2021-02-18
	//neue setters mit Exception
	public Rec22 _setNewVal(IF_Field field, Object value) throws Rec22FieldNotNullableException, Rec22FieldNotInTableException,MessageException,Exception {
		MyE2_MessageVector mv = bibMSG.getNewMV();
		
		if (!this.keySet().contains(field)) {
			throw new Rec22FieldNotInTableException("Field "+field.tnfn()+" not found in Rec22!");
		}
		
		MyMetaFieldDEF fd = field.generate_MetaFieldDef();
		
		
		if (value == null) {
			if (!fd.get_bFieldNullableBasicAndInteractive()) {
				throw new Rec22FieldNotNullableException("Null not allowed! Field "+field.fn()+" needs a non-null-value!");
			} else {
				
				HashMap<TARGETTYPE, Object> hmRet =  this.creatorForDifferentDataTypes.getDataTypes(field, value);

				this.get(field)._setNewVal((String)hmRet.get(TARGETTYPE.FORMATED_STRING),value, mv);
				return this;

			}
		} else {
		
			//hier pruefen, ob der typ uebereinsstimmt
			if (fd.get_bIsNumberTypeWithOutDecimals())   {
				if (!(value instanceof Long)) {
					throw new Rec22FalseDataTypeException("Field "+field.fn()+" needs a type Long, you sended "+value.getClass().getSimpleName());
				}
			} else if (fd.get_bIsNumberTypeWithDecimals())   {
				if (!(value instanceof BigDecimal)) {
					throw new  Rec22FalseDataTypeException("Field "+field.fn()+" needs a type BigDecimal, you sended "+value.getClass().getSimpleName());
				}
	        }
			else if (DB_META.vDB_DATE_TYPES.contains(fd.get_FieldType()))   {
				if (!(value instanceof Date)) {
					throw new  Rec22FalseDataTypeException("Field "+field.fn()+" needs a type Date, you sended "+value.getClass().getSimpleName());
				}
	 	    }
			else if (DB_META.vDB_TEXT_TYPES.contains(fd.get_FieldType()))  {
				if (!(value instanceof String)) {
					throw new  Rec22FalseDataTypeException("Field "+field.fn()+" needs a type String, you sended "+value.getClass().getSimpleName());
				}
			} else {
				throw new Exception("Field "+field.fn()+"! Error! Cannot find Metadefs <9cd9ed84-71d6-11eb-9439-0242ac130002>");
			}
			
			
			HashMap<TARGETTYPE, Object> hmRet =  this.creatorForDifferentDataTypes.getDataTypes(field, value);
	
			this.get(field)._setNewVal((String)hmRet.get(TARGETTYPE.FORMATED_STRING),value, mv);
			return this;
		}
		
	}

	//2021-02-18
	//neue setters mit Exception
	public Rec22 _nv(IF_Field f, String formated_value) throws  myException,MessageException,Exception {
		MyE2_MessageVector mv = bibMSG.getNewMV();
		super._nv(f, formated_value, mv);
		if (mv.hasAlarms()) {
			throw new MessageException(mv);
		}
		return this;		
	}
	
	
	/**
	 * return object of lastValue (when newValue is set, then this as object, else database-value)
	 * @author martin
	 * @date 17.02.2021
	 *
	 * @param f
	 * @return String, Date, Long or BigDecimal
	 * @throws Exception
	 */
	public Object getValueLast(IF_Field f) throws DataTypeException,Exception {
		if (!this.containsKey(f) || this.get(f)==null) {
			throw new Rec22FieldNotInTableException(f.fn()+" not in Rec22 <9d117b1e-c41d-4601-a7a1-29b5e24fa430>");
		}
		if (S.isEmpty(this.get(f).get_last_value_f())) {
			return null;
		} else {
			return this.get(f).get_object_lastVal().getBasicObject();
		}
	}
	
	/**
	 * 
	 * @author martin
	 * @date 18.02.2021
	 *
	 * @param f
	 * @return value from db
	 * @throws DataTypeException
	 * @throws Rec22FieldNotInTableException
	 * @throws Exception
	 */
	public Object getValueDb(IF_Field f) throws DataTypeException, Rec22FieldNotInTableException,Exception {

		if (!this.containsKey(f) || this.get(f)==null) {
			throw new Rec22FieldNotInTableException(f.fn()+" not in Rec22 <9d117b1e-c41d-4601-a7a1-29b5e24fa430>");
		}
		if (S.isEmpty(this.get(f).get_database_value_f())) {
			return null;
		} else {
			return this.get(f).get_metadef().get_object(this.get(f).get_database_value_f()).getBasicObject();
		}
	}
	
	
	
	public class Rec22Exception extends Exception {
		public Rec22Exception(String message) {
			super(message);
		}
	}
	
	
	public class Rec22FieldNotInTableException extends Rec22Exception {
		public Rec22FieldNotInTableException(String message) {
			super(message);
		}
	}
	public class Rec22FieldNotNullableException extends Rec22Exception {
		public Rec22FieldNotNullableException(String message) {
			super(message);
		}
	}
	public class Rec22FalseDataTypeException extends Rec22Exception {
		public Rec22FalseDataTypeException(String message) {
			super(message);
		}
	}
	
}
