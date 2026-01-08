/**
 * panter.gmbh.indep.dataTools.RECORD2
 * @author martin
 * @date 10.07.2019
 * 
 */
package panter.gmbh.indep.dataTools.RECORD2;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DEBUG.DEBUG_FLAGS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_STATICS;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 10.07.2019
 * Ableitung von Rec21 mit der faehigkeit, bei Updates nur die neu uebergebenen felder zu speichern und nicht alle
 *
 */
public class Rec21SaveOnlyChanged extends Rec21 {

	/**
	 * @author martin
	 * @date 10.07.2019
	 *
	 * @param p_tab
	 * @throws myException
	 */
	public Rec21SaveOnlyChanged(_TAB p_tab) throws myException {
		super(p_tab);
	}

	/**
	 * @author martin
	 * @date 10.07.2019
	 *
	 * @param baseRec
	 * @throws myException
	 */
	public Rec21SaveOnlyChanged(Rec21 baseRec) throws myException {
		super(baseRec);
	}

	public Rec21SaveOnlyChanged _fill(Rec21 baseRec) throws myException {
		super._fill(baseRec);
		return this;
	}

	
	public Rec21SaveOnlyChanged _fill_id(String id) throws myException {
		super._fill_id(id);
		return this;
	}
	
	
	public Rec21SaveOnlyChanged _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}
	
	
	
	public Rec21SaveOnlyChanged _fill_sql(String sql) throws myException {
		super._fill_sql(sql);
		return this;
	}
	
	
	public Rec21SaveOnlyChanged _fill_sql(SqlStringExtended sqlStringExtended) throws myException {
		super._fill_sql(sqlStringExtended);
		return this;
	}
	
	
	
	public Rec21SaveOnlyChanged _SAVE(boolean commit, MyE2_MessageVector mv_from_call) throws myException {
		return this._SAVE(commit, true,  mv_from_call);
	}
	
	
	
	/**
	 * 2017-10-13: aenderung: der messagevektor wird nur noch als sammler benutzt, 
	 *             jeder ablauf innen bekommt einen eigenen
	 * @param commit
	 * @param onlyNewValues
	 * @param mv_from_call
	 * 
	 * @return
	 * @throws myException
	 */
	public Rec21SaveOnlyChanged _SAVE(boolean commit, boolean onlyNewValues,  MyE2_MessageVector mv_from_call) throws myException {
		MyE2_MessageVector  mv = new MyE2_MessageVector();
		boolean toolbox_created = false;
		if (this.oDB==null) {
			this.oDB = bibALL.get_myDBToolBox();
			toolbox_created=true;
		}

		String  			c_sql = null;
		SqlStringExtended 	o_Sql = null;
		
		if (this.savePrepared) {
			o_Sql = this.createSaveString(onlyNewValues,oDB);
		} else {
			c_sql =	this.get_sql_4_save(true,mv);
		}
		//
		
		//20180312: fehler: hier kann die mv bereits einen fehler enthalten: dann abbruch
		if (mv.get_bIsOK()) {
		
			if (S.isFull(c_sql) || o_Sql!=null) {
				
				try {
					
					//DEBUG.System_print(v_sql, "-------------Statements in Rec20: "+this.tab.fullTableName()+"   ---------------------------", DEBUG_FLAGS.MARTINS_EIGENER);
					if (this.savePrepared) {
						this.oDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(o_Sql.generatePreparedStatement(this.oDB), false, true,mv);
						
						DEBUG._print("-----------------<code 85081a30-a2ef-11e9-a2a3-2a2ae2dbcce4>---------",DEBUG_FLAGS.MARTINS_EIGENER);
						DEBUG._print(o_Sql.getSqlString(),DEBUG_FLAGS.MARTINS_EIGENER);
						for (ParamDataObject dataOb: o_Sql.getValuesList()) {
							DEBUG._print("Value: "+dataOb.ValuePlain(),DEBUG_FLAGS.MARTINS_EIGENER);
						}
						DEBUG._print("--------------------------",DEBUG_FLAGS.MARTINS_EIGENER);

						
						
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
						this.rec_after_save_new = new Rec21(this.tab)._setRawModeSaveing(this.rawModeSaveing)._setPrepared(this.savePrepared)._fill_id(id);;
					}
				} catch (Exception e1) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("SQL-Fehler !")));
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
			            mv.add_MESSAGE(new MyE2_Alarm_Message(e.getLocalizedMessage(),false), false);
			        }
		        }
			}
		}
        if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
        
        mv_from_call.add_MESSAGE(mv);
        
		return this;
	}
	

	public SqlStringExtended createSaveString(MyDBToolBox oDB) throws myException {
		return this.createSaveString(false, oDB);
	}
	
	public SqlStringExtended createSaveString(boolean onlyNewValues, MyDBToolBox oDB) throws myException {
		
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

				// hier die aenderung 2019-07-10 
				if (this.get(f).is_new_value_set() || (!onlyNewValues)) {
					hmValues4Statement.put(f, this.get(f).getParamDataObject());
				}

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
				}
			}
			sb.append(" WHERE "+this.tab.key().fn()+"=?");
			vParams.add(this.get(this.tab.key()).getParamDataObject());

		}
		
		return new SqlStringExtended(sb.toString())._addParameters(vParams);
	}

	


	
	
	
	
}
