package panter.gmbh.Echo2.RB.DATA;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DEBUG.DEBUG_FLAGS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB;
import panter.gmbh.indep.dataTools.MyRECORD_IF_FILLABLE;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder_primitiv;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public abstract class RB_DataobjectsCollector_V1 implements RB_DataobjectsCollector {

	//extender fuer zukuenftige erweiterungen
	private RB_DataobjectsCollector_EXT  extdo = new RB_DataobjectsCollector_EXT();
	
	
	private LinkedHashMap<RB_KM,RB_Dataobject>  	hmDataObjects = new LinkedHashMap<RB_KM,RB_Dataobject>();

		
	/**
	 * im falle des speichervorgangs wird die folgende hashmap geloescht und dann innerhalb der Transaktion
	 * mit evtl. neuen werten gefuellt  (wenn eine seq_xxx.nextval angefordert wird, wird innerhalb der Transaktion dieser wert mit currval gefuellt)
	 */
	private LinkedHashMap<String, String>           hmCurrValuesNewRecords = new LinkedHashMap<String, String>();
	
	
	private RB_ComponentMapCollector  				rb_component_map_collector_this_belongsTo = null;
	
	
	/**
	 * 2015-05-06: Toolbox-Generator, um DBToolboxen mit von der norm abweichenden Ausnahmefeldern erzeugen zu koennen
	 */
	private MyDBToolBox_FAB  						DBToolBox_FAB = null;

	
	
	//2016-06-06: beim registrieren der RB_Dataobject s die keys in der reihenfolge sammeln
	private Vector<RB_K> 							v_keys = new Vector<>();
	
	
	public RB_DataobjectsCollector_V1() {
		super();
	}

	@Override
	public RB_Dataobject get(RB_KM ob) {
		return this.hmDataObjects.get(ob);
	}
	
	


	@Override
	public RB_Dataobject registerComponent(RB_K maskHash, RB_Dataobject dataOb) throws myException {
		
		this.v_keys.add(maskHash);
		
		if (maskHash instanceof RB_KM) {
			this.hmDataObjects.put((RB_KM)maskHash, dataOb);
			dataOb.rb_set_belongs_to(this);
			return dataOb;
		} else {
			throw new myException(this,"Register MUST use an KEY type RB_KM");
		}
	}

	
	/*
	 * in dieser methode muss der zusammenhang der RECORD - objekte aufgebaut werden (je nach zusammenstellung der maske und  verbindung der teile)
	 */
	public abstract void database_to_dataobject(Object startPoint) throws myException;
	
	/*
	 * hier wird beim speichern festgelegt, wie die StatementBuilder zusammenhaengen
	 */
	public abstract void dataobject_to_database_connect_RB_MASK_DATA(RB_StatementBuilderCollector Statemenbuilder_Collector) throws myException;

	
	
	@Override
	public MyE2_MessageVector rb_Dataobjects_to_Database(boolean forceSave) throws myException {
	
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		this.hmCurrValuesNewRecords.clear();
		
		boolean something_has_changed = false;
		
		RB_StatementBuilderCollector  statementBuilderCollector = new RB_StatementBuilderCollector();
		
		//die basismasken iterieren
		for (Map.Entry<RB_KM,RB_Dataobject> o_entry: this.hmDataObjects.entrySet()) {
			MyRECORD_IF_FILLABLE				record = 	o_entry.getValue().rb_relevant_record_to_fill();
			
			something_has_changed = something_has_changed || record.get_bHasSomething_to_save();
			
			//zuerst die hauptmaske
			MySqlStatementBuilder stmtbd=record.get_StatementBuilder(true);
			stmtbd.set_RecordCorrelated(record);
			statementBuilderCollector.add(o_entry.getKey(),stmtbd);
			
			
			//dann die angehaengten (aus complex-fields)
			for (MyRECORD_IF_FILLABLE rec: o_entry.getValue().get_v_RECORD_IF_FILLABLE()) {
				MySqlStatementBuilder stmtbd2=rec.get_StatementBuilder(true);
				stmtbd2.set_RecordCorrelated(rec);
				statementBuilderCollector.add(o_entry.getKey(), stmtbd2);
				something_has_changed = true;           //sind toechter vorhanden, dann immer speichern
			}
			
			//dann die sonder-statementbuilder
			for (MySqlStatementBuilder stmb: o_entry.getValue().get_v_STATEMENTBUILDERS()) {
				statementBuilderCollector.add(o_entry.getKey(),stmb);
			}
		}
		
		//hier wird die verbindung der Statementbuilders erzeugt
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		this.dataobject_to_database_connect_RB_MASK_DATA(statementBuilderCollector);
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		

		//jetzt die sql-statements generieren
		Vector<String>   vSQL = new Vector<String>();
		
		for (Vector<MySqlStatementBuilder> vSqlStatementBuilder: statementBuilderCollector) {
			for (MySqlStatementBuilder stmb: vSqlStatementBuilder) {
				if (stmb.get_RecordCorrelated() != null) {
					if (stmb.get_RecordCorrelated() instanceof MyRECORD_IF_RECORDS) {
						//update
						MyRECORD_IF_RECORDS rec = (MyRECORD_IF_RECORDS)stmb.get_RecordCorrelated();
						vSQL.add(stmb.get_CompleteUPDATEString(	rec.get_TABLENAME(), 
																bibE2.cTO(), 
																rec.get_PRIMARY_KEY_NAME()+"="+rec.get_PRIMARY_KEY_UF(), 
																bibVECTOR.get_Vector(rec.get_PRIMARY_KEY_NAME())));
						
					} else if (stmb.get_RecordCorrelated() instanceof MyRECORD_IF_FILLABLE) {
						//insert-records
						MyRECORD_IF_FILLABLE rec = (MyRECORD_IF_FILLABLE)stmb.get_RecordCorrelated();
						//hier den sequencer einbauen
						stmb.addSQL_Paar(rec.get_PRIMARY_KEY_NAME(), "SEQ_"+rec.get_TABLENAME().substring(3)+".NEXTVAL");
						
						//jetzt die currVal-hash fuellen
						this.hmCurrValuesNewRecords.put(rec.get_TABLENAME(), "");
						
						
						vSQL.add(stmb.get_CompleteInsertString(rec.get_TABLENAME(),bibE2.cTO()));
					
					} else {
						throw new myException(this,"dataobjects_to_database(): undefined Interface !");
					}
				} else {
					if (stmb instanceof MySqlStatementBuilder_primitiv) {
						vSQL.add( ((MySqlStatementBuilder_primitiv)stmb).get_SQL_Single());
					} else {
						throw new myException(this,"dataobjects_to_database(): undefined Statementbuilder !");
					}
				}
			}
		}
			

		//jetzt liegen alle statements vor
		//der speichervorgang kann starten
		if (forceSave || something_has_changed) {
			MyE2_MessageVector oMV_Control = new MyE2_MessageVector();
			
			//uncommitted lock-statement
			oMV_Control.add_MESSAGE(bibDB.ExecMultiSQLVector(bibVECTOR.get_Vector(
					"UPDATE "+bibE2.cTO()+".JT_LOCK SET KENNSTRING=KENNSTRING WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()), false));
			if (oMV_Control.get_bIsOK()) {
				//dann ist ab hier gesperrt
				if (statementBuilderCollector.bCheck_4_concurrent_changes_all_OK()) {
					
					DEBUG.System_print(vSQL,"RB_Dataobjects: show sql-stack!",DEBUG_FLAGS.RB_SAVE_COMPLETE_SQL_STACK);
					
					oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, false));
					
					if (oMV.get_bIsOK()) {
						
						//jetzt die this.hmCurrValuesNewRecords abarbeiten damit die info, welche saetze neu sind, zur verfuegung steht
						boolean bDurchlaufOK=true;
						ArrayList<String> vNames = new ArrayList<String>();
						vNames.addAll(this.hmCurrValuesNewRecords.keySet());
 						for (String cTableNew: vNames) {
							String idNext = bibDB.EinzelAbfrage("SELECT SEQ_"+cTableNew.substring(3)+".CURRVAL FROM DUAL");
							MyLong lTest = new MyLong(idNext);
							if (lTest.get_bOK()) {
								this.hmCurrValuesNewRecords.put(cTableNew, lTest.get_cUF_LongString());
							} else {
								bDurchlaufOK=false;
								break;
							}
						}
						
 						if (!bDurchlaufOK) {
 							oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Speichern (2)!")));
 							if (!bibDB.Rollback()) {
 								oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("SCHWERER DB-Fehler! Kennung: RB_MASK_HM_LEVEL_ONE:COMPLETE_SAVE_STD:4")));
 							}
 						} else {
 							if (!bibDB.Commit()) {
 								oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("SCHWERER DB-Fehler! Kennung: RB_MASK_HM_LEVEL_ONE:COMPLETE_SAVE_STD:5")));
 							}
						}
					} else {
						//dann erfolgt rollback
						oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Speichern !")));
						if (!bibDB.Rollback()) {
							oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("SCHWERER DB-Fehler! Kennung: RB_MASK_HM_LEVEL_ONE:COMPLETE_SAVE_STD:3")));
						}
					}
					
					
					
				} else {
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Daten wurden von anderer Seite geändert. Speichern ist nicht möglich !")));
					if (!bibDB.Rollback()) {
						oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("SCHWERER DB-Fehler! Kennung: RB_MASK_HM_LEVEL_ONE:COMPLETE_SAVE_STD:1")));
					}
				}
			} else {
				//rollback
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler! Speichern ist nicht möglich !")));
				if (!bibDB.Rollback()) {
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("SCHWERER DB-Fehler! Kennung: RB_MASK_HM_LEVEL_ONE:COMPLETE_SAVE_STD:2")));
				}
			}
			
		} else {
			oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Es gab keine Änderungen zu speichern !")));
		}
		
		return oMV;
	}

	@Override
	public RB_ComponentMapCollector rb_ComponentMapCollector_ThisBelongsTo() {
		return rb_component_map_collector_this_belongsTo;
	}

	@Override
	public void set_rb_ComponentMapCollector_ThisBelongsTo(RB_ComponentMapCollector mask_ContainerThisBelongsTo)	{
		this.rb_component_map_collector_this_belongsTo = mask_ContainerThisBelongsTo;
	}

//	@Override
//	public void rb_Clear_ALL_RECNEW() throws myException	{
//		for (RB_Dataobject d_ob: this) {
//			d_ob.rb_Clear_RECNEW();
//		}
//	}

	/**
	 * @deprecated
	 */
	@Override
	public void rb_Rebuild_ALL_RECORD(boolean bOnlyWhenExists) throws myException	{
		if (bOnlyWhenExists) {
			//falls nix da ist, exception
			for (RB_Dataobject d_ob: this) {
				if (d_ob.get_RecORD()!=null) {
					d_ob.rb_Rebuild_RECORD();
				}
			}
		} else {
			//falls nix da ist, exception
			for (RB_Dataobject d_ob: this) {
				d_ob.rb_Rebuild_RECORD();
			}
		}
	}

	//nur in V2 neu implementiert
	@Override
	public void rb_RebuildAllRecords() throws myException	{
		this.rb_Rebuild_ALL_RECORD(true);
	}

	

	
	

	/**
	 * beim speichern eines neuen datensatzes wird der letzte currval-eintrag in der datenbank mitgeschrieben
	 * @param cTablename
	 * @return
	 */
	@Override
	public String get_LastWrittenNewID(String cTablename)	{
		return this.hmCurrValuesNewRecords.get(cTablename);
	}
	
	
	/**
	 * 2016-04-27: martin
	 * beim speichern eines neuen datensatzes wird der letzte currval-eintrag in der datenbank mitgeschrieben
	 * @param table
	 * @return
	 */
	@Override
	public String get_LastWrittenNewID(_TAB table)	{
		return this.hmCurrValuesNewRecords.get(table.fullTableName());
	}
	
	
	

	
	/**
	 * 2015-05-06: das holen der DBToolBox hier ausgelagert in eine eigene Methode, die ueberschrieben werden kann
	 * @return
	 * @throws myException
	 */
	@Override
	public MyDBToolBox  generate_DBToolBox(MyConnection conn) throws myException	{
		if (this.DBToolBox_FAB!=null) {
			return this.DBToolBox_FAB.generate_INDIVIDUELL_DBToolBox(conn);
		} else {
			return MyDBToolBox_FAB.generate_STANDARD_DBToolBox(conn);	
		}
	}


	@Override
	public MyDBToolBox_FAB get_DBToolBox_FAB()	{
		return this.DBToolBox_FAB;
	}


	@Override
	public void set_DBToolBox_FAB(MyDBToolBox_FAB dBToolBox_FAB)	{
		this.DBToolBox_FAB = dBToolBox_FAB;
	}
	
	/**
	 * 2015-05-06: ExecMultiSQLVector ausgelagert in eine eigene Methode, damit die eigene DBToolBox verwendet wird
	 * @param vSQLStack
	 * @param bCommit
	 * @return
	 * @throws myException 
	 */
	@Override
	public MyE2_MessageVector ExecMultiSQLVector(Vector<String> vSQLStack, boolean bCommit) throws myException	{
		MyDBToolBox oDB = this.generate_DBToolBox(bibALL.get_oConnectionNormal());
		MyE2_MessageVector VRueck = oDB.ExecMultiSQLVector(vSQLStack, bCommit);
		bibALL.destroy_myDBToolBox(oDB);
		return VRueck;
	}


	//get und set Extender
	@Override
	public RB_DataobjectsCollector_EXT EXT_DO_Collector() {
		return this.extdo;
	}

	
	public Vector<RB_K> get_keys() {
		return v_keys;
	}


	@Override
	public LinkedHashMap<RB_KM, RB_Dataobject> rb_hm_DataObjects() {
		return hmDataObjects;
	}

	
	
	
	
	
	
	
	
}
