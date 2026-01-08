package panter.gmbh.Echo2.RB.DATA;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_MessageTranslator;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.bibVECTOR;


public abstract class RB_DataobjectsCollector_V21 implements RB_DataobjectsCollector {

	//extender fuer zukuenftige erweiterungen
	private RB_DataobjectsCollector_EXT  ext_do_collector = new RB_DataobjectsCollector_EXT();
	
	
	private LinkedHashMap<RB_KM,RB_Dataobject>  	hmDataObjects = new LinkedHashMap<RB_KM,RB_Dataobject>();

		
//	/**
//	 * im falle des speichervorgangs wird die folgende hashmap geloescht und dann innerhalb der Transaktion
//	 * mit evtl. neuen werten gefuellt  (wenn eine seq_xxx.nextval angefordert wird, wird innerhalb der Transaktion dieser wert mit currval gefuellt)
//	 */
//	private LinkedHashMap<String, String>           hmCurrValuesNewRecords = new LinkedHashMap<String, String>();
	
	
	private RB_ComponentMapCollector  				rb_component_map_collector_this_belongsTo = null;
	
	
	/**
	 * 2015-05-06: Toolbox-Generator, um DBToolboxen mit von der norm abweichenden Ausnahmefeldern erzeugen zu koennen
	 */
	private MyDBToolBox_FAB  						DBToolBox_FAB = null;

	
	
	//2016-06-06: beim registrieren der RB_Dataobject s die keys in der reihenfolge sammeln
	private Vector<RB_K> 							v_keys = new Vector<>();
	
	
	//20171102: uebersetzer von speichermeldungern
	private VEK<RB_MessageTranslator>            	vMessageTranslators = new VEK<RB_MessageTranslator>();
	
	
	
	//20170815: schalter zum steuern des verhaltens
	private boolean   								reloadRec20AfterSave = 			true;
	private boolean   								cleanRec20NewValuesAfterSave = 	true;
	
	
	//2018-08-09: schalter, um fuer die nutzung der Rec21-varianten vorzubereiten
	private boolean  								useRec21 = false;
	
	
	public RB_DataobjectsCollector_V21() {
		super();
	}

	@Override
	public RB_Dataobject get(RB_KM ob) {
		return this.hmDataObjects.get(ob);
	}
	
	
	public RB_Dataobject_V21 getRec21(RB_KM ob) throws myException {
		if (this.hmDataObjects.get(ob)instanceof RB_Dataobject_V21) {
			return (RB_Dataobject_V21)this.hmDataObjects.get(ob);
		}
		throw new myException(this,"No dataobject of type RB_Dataobject_V21!");
	}
	
	
	
	@Override
	public RB_Dataobject registerComponent(RB_K maskHash, RB_Dataobject dataOb) throws myException {
		
		if (!(dataOb instanceof RB_Dataobject_V21)) {
			throw new myException(this,"In this only RB_dataobjects of type <RB_Dataobject_V21>  are allowed !");
		}
		if (!(maskHash instanceof RB_KM)) {
			throw new myException(this,"Register MUST use an KEY type RB_KM");
		}
		
		this.v_keys.add(maskHash);

		this.hmDataObjects.put((RB_KM)maskHash, dataOb);
		dataOb.rb_set_belongs_to(this);
		return dataOb;
	}

	
	
	
	/*
	 * in dieser methode muss der zusammenhang der RECORD - objekte aufgebaut werden (je nach zusammenstellung der maske und  verbindung der teile)
	 */
	public abstract void database_to_dataobject(Object startPoint) throws myException;
	
	
	/*
	 * hier koennen noch veraenderungen definiert werden, wenn z.B. weitere felder berechnet werden muessen, dann kann dies auf der basis der neu- und auch
	 * der bearbeiteten MyRECORD_IF_RECORDS erfolgen 
	 */
	public abstract void manipulate_filled_records_before_save(RB_DataobjectsCollector_V21 do_collector, MyE2_MessageVector mv) throws myException;
	
	/*
	 * hier koennen noch globale veraenderungen definiert werden (z.B. fuer alle atome den korrekten endpreis rechnen oder aehnliches 
	 */
	public abstract void execute_final_statements_in_open_transaction(RB_DataobjectsCollector_V21 do_collector, MyE2_MessageVector mv) throws myException;
	
	

	
	/**
	 * 
	 * @author martin
	 * @date 24.04.2019
	 * wird nach dem commit der transaktion aufgerufen ! Wichtig benoetigen eigene Fehlerbehandlung !
	 *
	 * @param do_collector
	 * @param mv
	 * 
	 */
	public void executeFinalStatementsAfterCommitTransaction(RB_DataobjectsCollector_V21 do_collector, MyE2_MessageVector mv) {
	}
	
	
	
	
	private void checkIfContentIsCompleteRec21() throws myException {
		//jetzt sicherstellen, dass alle dataobject der collection vom typ RB_Dataobject_V21 sind
		for (RB_Dataobject  d_ob: this.hmDataObjects.values()) {
			if (!(d_ob instanceof RB_Dataobject_V21)) {
				throw new RuntimeException("System-Error: 3cc61441-a393-4df8-93c5-69642f38c089: Only Dataobjects Type RB_Dataobject_V21 are allowed !");
			}
		}
	}
	
	/**
	 * methode, um die "huelle" zu kopieren, damit im verarbeitungslauf weiter RB_Dataobjects in die verarbeitung zu packen (z.B. fuer innere, automatisch zugefuegte records)
	 */
	public abstract RB_DataobjectsCollector_V21 get_copy() throws myException;
	
	
	@Override
	public MyE2_MessageVector rb_Dataobjects_to_Database(boolean forceSave) throws myException {
	
		this.checkIfContentIsCompleteRec21();
		
		MyE2_MessageVector  mv = new MyE2_MessageVector();
		
		boolean something_has_changed = false;
		
		
		//jetzt sind alle reelvante objecte in der rb_rec_coll und koennen manipuliert werden
		this.manipulate_filled_records_before_save(this,mv);
		//---------------------------------------------------------------------------------------
		
		if (mv.get_bIsOK()) {
	
			//nachsehen, ob sich etwas veraendert hat
			for (RB_Dataobject  d_ob: this.hmDataObjects.values()) {
				RB_Dataobject_V21 do_v2 = (RB_Dataobject_V21)d_ob;
				
				if (do_v2.is_even_one_new_value_set() || do_v2.get_v_STATEMENTBUILDERS().size()>0) {
					something_has_changed = true;
					break;
				}
			}
			
			
	
			try {
				//jetzt liegen alle statements vor
				//der speichervorgang kann starten
				if (forceSave || something_has_changed) {
					
					//uncommitted lock-statement
					mv._add(bibDB.ExecMultiSQLVector(bibVECTOR.get_Vector("UPDATE "+bibE2.cTO()+".JT_LOCK SET KENNSTRING=KENNSTRING WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()), false));

					if (mv.get_bIsOK()) {
						//dann ist ab hier gesperrt
						
						//hier pruefen, ob es veraenderungen gibt
						int tochterzaehler = 1;
						HashMap<String, String> hm_changes = new HashMap<>();
						for (RB_KM  key: this.hmDataObjects.keySet()) {
							RB_Dataobject_V21 do_v2 = (RB_Dataobject_V21)this.hmDataObjects.get(key);
							if (!do_v2.is_newRecordSet()) {
								do_v2.compare_to_actual(hm_changes);
							}

							//ebenfalls fuer die complexen toechter pruefen
							RB_StatementBuilderCollector  collector_tochter = new RB_StatementBuilderCollector(do_v2.get_v_STATEMENTBUILDERS());
							if (!collector_tochter.bCheck_4_concurrent_changes_all_OK()) {
								hm_changes.put("Tochter-Tabelle:"+tochterzaehler++, "Werte eines komplexen tochterfeldes wurden im Hintergrund geaendert !");
							}
						}

						//ende pruefungen
						if (hm_changes.size()>0) {
							for (String field: hm_changes.keySet()) {
								mv._add(new MyE2_Alarm_Message(new MyE2_String("Die Daten wurden im Hintergrund geändert: ").ut(field).ut(" -> ").ut(hm_changes.get(field))));
							}
						}
						
						if (mv.get_bIsOK()) {

							
							
							//zuerst die statements vorher
							mv._add(bibDB.ExecMultiSQLVector(this.EXT_DO_Collector().get_v_statements_in_front(),false));
							
							if (mv.get_bIsOK()) {
								//jetzt speichern:
								for (RB_KM key: this.hmDataObjects.keySet()) {
									RB_Dataobject_V21 d_ob = (RB_Dataobject_V21)this.hmDataObjects.get(key);
									
									d_ob._SAVE(false, mv);

									
									//20170816: geaendert: Martin
									if (this.reloadRec20AfterSave && d_ob.is_ExistingRecord()) {
										//2018-08-09: schalter, um fuer die nutzung der Rec21-varianten vorzubereiten
										d_ob._rebuildRecord();
									}
									if (this.cleanRec20NewValuesAfterSave) {
										d_ob.RESET_ALL_NEWVALUES();
									}
									
									
									RB_StatementBuilderCollector  collector4dataobject = new RB_StatementBuilderCollector(d_ob.get_v_STATEMENTBUILDERS());
									if (collector4dataobject.size()>0) {
										boolean b_erzeugt = false;
										MyDBToolBox oDB = d_ob.get_oDB();
										if (oDB == null) {
											oDB=bibALL.get_myDBToolBox();
										}
										Vector<String> v_statements4Save = collector4dataobject.get_v_sqlstatements();
									
										mv._add(oDB.ExecMultiSQLVector(v_statements4Save, false));
										
										if (b_erzeugt) {bibALL.destroy_myDBToolBox(oDB);}
									}
									
									
									if (!mv.get_bIsOK()) {
										break;
									}
									
								}
								if (mv.get_bIsOK()) {
									
									//zuerst die statements vorher
									mv._add(bibDB.ExecMultiSQLVector(this.EXT_DO_Collector().get_v_statements_at_end(),false));
									
									//jetzt die finalen, programmierten aenderungen
									this.execute_final_statements_in_open_transaction(this, mv);
								} 
								
								//20171102: standardschnittstelle fuer datenbank-fehlermeldungen-Ersetzung
								if (!mv.get_bIsOK()) {
									if (this.vMessageTranslators.size()>0) {
										for (RB_MessageTranslator mt: this.vMessageTranslators) {
											mv.replaceMessageWhenAllSubstringsAreInMessageText(mt.getvMatchingKeys(),S.ms(mt.getcLesbar4User()));
										}
									}
								}
							}
						}

						//hier beenden
						MyDBToolBox oDB = bibALL.get_myDBToolBox();

						try {
							if (mv.get_bIsOK()) {
								oDB.ownCommit();
								this.executeFinalStatementsAfterCommitTransaction(this,mv);
								
								
							} else {
								oDB.ownRollBack();
							}
						} catch (SQLException e) {
							e.printStackTrace();
							oDB.Rollback();
						} catch (Exception ex) {
							ex.printStackTrace();
							oDB.Rollback();
						}
						
						bibALL.destroy_myDBToolBox(oDB);
						
					} else {
						//rollback
						mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler! Speichern ist nicht möglich, Datenlock gescheitert (1) !")));
						if (!bibDB.Rollback()) {
							mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler! Kein Rollback möglich nach Datenlock !")));
						}
					}
					
				} else {
					mv.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Es gab keine Änderungen zu speichern !")));
				}
			} catch (Exception e) {     			//notfall, falls etwas unvorhergesehenes passiert ...
				bibDB.Rollback();
				
				e.printStackTrace();
				if (e instanceof myException) {
					throw e;
				} else {
					throw new myException(this,"Systemfehler 9c75075b-c11c-4463-b70d-83ff4ee96e29 ! Bitte beim Admin melden !!");
				}
			}
			
		}

		return mv;
	}

	@Override
	public RB_ComponentMapCollector rb_ComponentMapCollector_ThisBelongsTo() {
		return rb_component_map_collector_this_belongsTo;
	}

	@Override
	public void set_rb_ComponentMapCollector_ThisBelongsTo(RB_ComponentMapCollector mask_ContainerThisBelongsTo)	{
		this.rb_component_map_collector_this_belongsTo = mask_ContainerThisBelongsTo;
	}



	/**
	 * @deprecated
	 */
	@Override
	public void rb_Rebuild_ALL_RECORD(boolean bOnlyWhenExists) throws myException	{
		
		throw new RuntimeException("System Error: 2c385b05-e821-4fc5-930c-4c4687c57207 Method: rb_Rebuild_ALL_RECORD() is forbidden "); 
	}

	
	
	/**
	 * 20180105: martin
	 */
	@Override
	public void rb_RebuildAllRecords() throws myException	{
		
		this.checkIfContentIsCompleteRec21();
		
		//falls nix da ist, exception
		for (RB_Dataobject d_ob: this) {
			RB_Dataobject_V21 d_ob2 = (RB_Dataobject_V21)d_ob;
			d_ob2.rb_RebuildRecord();
		}
	}

	

	/**
	 * beim speichern eines neuen datensatzes wird der letzte currval-eintrag in der datenbank mitgeschrieben
	 * @param cTablename
	 * @return
	 * @throws myException 
	 */
	@Override
	public String get_LastWrittenNewID(String cTablename) throws myException	{
		this.checkIfContentIsCompleteRec21();
		
		//nachsehen, ob sich etwas veraendert hat
		Vector<String> v_id_new = new Vector<>();
		for (RB_Dataobject  d_ob: this.hmDataObjects.values()) {
			RB_Dataobject_V21 do_v2 = (RB_Dataobject_V21)d_ob;
			if (do_v2.is_newRecordSet()) {
				//dann muss im feld rec_after_save_new ein record des neuen datensatzes stehen
				if (do_v2.get_TABLENAME().equals(cTablename)) {
					v_id_new.add(do_v2.get_rec_after_save_new().get_key_value());
				}
			}
		}
		if (v_id_new.size()>0) {
			v_id_new.sort(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					Integer i1 = Integer.getInteger(o1);
					Integer i2 = Integer.getInteger(o2);
					return Integer.compare(i1, i2);
				}
			});
			return v_id_new.get(v_id_new.size()-1);
		}
		return null;
	}
	
	
	/**
	 * 2016-04-27: martin
	 * beim speichern eines neuen datensatzes wird der letzte currval-eintrag in der datenbank mitgeschrieben
	 * @param table
	 * @return
	 * @throws myException 
	 */
	@Override
	public String get_LastWrittenNewID(_TAB table) throws myException	{
		return this.get_LastWrittenNewID(table.fullTableName());
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
		return this.ext_do_collector;
	}

	
	public Vector<RB_K> get_keys() {
		return v_keys;
	}


	@Override
	public LinkedHashMap<RB_KM, RB_Dataobject> rb_hm_DataObjects() {
		return hmDataObjects;
	}

	
	/**
	 * adds MySqlStatementBuilder_Simple at the start-stack (only if c_sql is not empty)
	 * @param c_sql
	 * @return
	 * @throws myException
	 */
	public RB_DataobjectsCollector_V21 _addSqlAtStart(String c_sql) throws myException {
		if (S.isFull(c_sql)) {
			this.ext_do_collector.get_v_statements_in_front().add(c_sql);
		}
		return this;
	}
	
	
	/**
	 * adds MySqlStatementBuilder_Simple at the end-stack (only if c_sql is not empty)
	 * @param c_sql
	 * @return
	 * @throws myException
	 */
	public RB_DataobjectsCollector_V21 _addSqlAtEnd(String c_sql) throws myException {
		if (S.isFull(c_sql)) {
			this.ext_do_collector.get_v_statements_at_end().add(c_sql);
		}
		return this;
	}
	
	
	
	
	/**
	 * adds MySqlStatementBuilder_Simple at the start-stack (only if c_sql is not empty)
	 * @param v_sql
	 * @return
	 * @throws myException
	 */
	public RB_DataobjectsCollector_V21 _addSqlAtStart(Vector<String> v_sql) throws myException {
		if (v_sql!=null) {
			this.ext_do_collector.get_v_statements_in_front().addAll(v_sql);
		}
		return this;
	}
	
	
	/**
	 * adds MySqlStatementBuilder_Simple at the end-stack (only if c_sql is not empty)
	 * @param v_sql
	 * @return
	 * @throws myException
	 */
	public RB_DataobjectsCollector_V21 _addSqlAtEnd(Vector<String> v_sql) throws myException {
		if (v_sql!=null) {
			this.ext_do_collector.get_v_statements_at_end().addAll(v_sql);
		}
		return this;
	}

	public boolean isReloadRec20AfterSave() {
		return reloadRec20AfterSave;
	}

	public boolean isCleanRec20NewValuesAfterSave() {
		return cleanRec20NewValuesAfterSave;
	}

	public RB_DataobjectsCollector_V21 setReloadRec20AfterSave(boolean p_reloadRec20AfterSave) {
		this.reloadRec20AfterSave = p_reloadRec20AfterSave;
		return this;
	}

	public RB_DataobjectsCollector_V21 setCleanRec20NewValuesAfterSave(boolean p_cleanRec20NewValuesAfterSave) {
		this.cleanRec20NewValuesAfterSave = p_cleanRec20NewValuesAfterSave;
		return this;
	}

	
	public Vector<RB_MessageTranslator> getvMessageTranslators() {
		return vMessageTranslators;
	}

	
	public RB_DataobjectsCollector_V21  _addMessageTranslator(RB_MessageTranslator mt) {
		this.vMessageTranslators._a(mt);
		return this;
	}

	//2018-08-09: schalter, um fuer die nutzung der Rec21-varianten vorzubereiten
	public boolean isUseRec21() {
		return useRec21;
	}

	//2018-08-09: schalter, um fuer die nutzung der Rec21-varianten vorzubereiten
	public RB_DataobjectsCollector_V21 _setUseRec21(boolean useRec21) {
		this.useRec21 = useRec21;
		return this;
	}
	
	
	public RB_Dataobject_V21 get_v21(RB_KM ob) throws myException {
		if (this.hmDataObjects.get(ob)instanceof RB_Dataobject_V21) {
			return (RB_Dataobject_V21)this.hmDataObjects.get(ob);
		}
		throw new myException(this,"No dataobject of type RB_Dataobject_V2!");
	}

}

