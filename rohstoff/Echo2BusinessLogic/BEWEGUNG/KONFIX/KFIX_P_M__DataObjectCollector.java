
package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.Echo2.RB.DATA.RB_StatementBuilderCollector;
import panter.gmbh.Echo2.components.E2_calendar.E2_TF_4_Date;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DEBUG.DEBUG_FLAGS;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_AENDERUNGEN;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VKOPF_KON;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VPOS_KON;

public class KFIX_P_M__DataObjectCollector extends RB_DataobjectsCollector_V2 {

	private String belegTyp ="";

	private Rec20_VKOPF_KON record_kopf = null;

	private MASK_STATUS status = MASK_STATUS.NEW;

	private MyBigDecimal anzahl_value_onload =new MyBigDecimal(0L);
	private MyBigDecimal ePreis_value_onload =new MyBigDecimal(0L);
	private String id_vpos_kon_when_edit = "";
	private boolean update_aenderung_tabelle = false;

	public KFIX_P_M__DataObjectCollector(String oBelegTyp, Rec20 rec20_record_kopf) throws myException {
		super();

		this.belegTyp = oBelegTyp;
		this.record_kopf = new Rec20_VKOPF_KON(rec20_record_kopf);

		/*
		 * proforma-datensatz im edit-modus zum locking
		 */
		this.registerComponent(_TAB.vkopf_kon.rb_km(), 		new KFIX_K__dataObject(rec20_record_kopf.get_ufs_dbVal(VKOPF_KON.id_vkopf_kon)));

		this.registerComponent(_TAB.vpos_kon.rb_km(), 		new KFIX_P_M__DataObject(_TAB.vpos_kon));
		this.registerComponent(_TAB.vpos_kon_trakt.rb_km(), 	new KFIX_P_M__DataObjectAddOn(_TAB.vpos_kon_trakt));
	}


	public KFIX_P_M__DataObjectCollector(String id_vpos_kon, MASK_STATUS status, String oBelegTyp) throws myException {
		super();

		this.status = status; 

		Rec20 rec20_vpos_kon = new Rec20(_TAB.vpos_kon)._fill_id(id_vpos_kon);
		Rec20 rec20_vpos_kon_trakt = new Rec20_VPOS_KON(rec20_vpos_kon).get_rec_vpos_kon_trakt();

		this.belegTyp = oBelegTyp;
		this.record_kopf = new Rec20_VKOPF_KON(rec20_vpos_kon.get_up_Rec20(VPOS_KON.id_vkopf_kon, VKOPF_KON.id_vkopf_kon, false));
		this.id_vpos_kon_when_edit = id_vpos_kon;

		
		/*
		 * proforma-datensatz im edit-modus zum locking
		 */
		this.registerComponent(_TAB.vkopf_kon.rb_km(), 		new KFIX_K__dataObject(this.record_kopf.get_ufs_dbVal(VKOPF_KON.id_vkopf_kon)));

		
		
		this.registerComponent(_TAB.vpos_kon.rb_km(), 			new KFIX_P_M__DataObject(rec20_vpos_kon, status, oBelegTyp));
		this.registerComponent(_TAB.vpos_kon_trakt.rb_km(), 	new KFIX_P_M__DataObjectAddOn(rec20_vpos_kon_trakt, status, oBelegTyp));

		anzahl_value_onload = rec20_vpos_kon.get_myBigDecimal_dbVal(VPOS_KON.anzahl, new MyBigDecimal(0L));
		ePreis_value_onload = rec20_vpos_kon.get_myBigDecimal_dbVal(VPOS_KON.einzelpreis, new MyBigDecimal(0L));
	}

	@Override
	public void database_to_dataobject(Object id_vpos_kon) throws myException {	
		new Rec20(_TAB.vpos_kon)._fill_id((String)id_vpos_kon).get_fs_dbVal(VPOS_KON.anzahl);

	}


	@Override
	public MyE2_MessageVector rb_Dataobjects_to_Database(boolean forceSave) throws myException {

		MyE2_MessageVector  mv = new MyE2_MessageVector();

		boolean something_has_changed = false;


		//jetzt sind alle reelvante objecte in der rb_rec_coll und koennen manipuliert werden
		this.manipulate_filled_records_before_save(this,mv);
		//---------------------------------------------------------------------------------------

		LinkedHashMap<RB_KM, RB_Dataobject> hmDO = this.rb_hm_DataObjects();
		if(! update_aenderung_tabelle){
			if(hmDO.containsKey(new RB_KM(_TAB.vpos_kon_aenderungen))){
				hmDO.remove(new RB_KM(_TAB.vpos_kon_aenderungen));
			}
		}

		if (mv.get_bIsOK()) {

			//jetzt sicherstellen, dass alle dataobject der collection vom typ RB_Dataobject_V2 sind
			for (RB_Dataobject  d_ob: hmDO.values()) {
				if (!(d_ob instanceof RB_Dataobject_V2)) {
					throw new myException(this,"Only Dataobjects Type RB_Dataobject_V2 are allowed !");
				}
			}

			//nachsehen, ob sich etwas veraendert hat
			for (RB_Dataobject  d_ob: hmDO.values()) {
				RB_Dataobject_V2 do_v2 = (RB_Dataobject_V2)d_ob;

				if (do_v2.get_rec20().is_even_one_new_value_set() || do_v2.get_v_STATEMENTBUILDERS().size()>0) {
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
						for (RB_KM  key: hmDO.keySet()) {
							RB_Dataobject_V2 do_v2 = (RB_Dataobject_V2)hmDO.get(key);
							if (!do_v2.get_rec20().is_newRecordSet()) {
								do_v2.get_rec20().compare_to_actual(hm_changes);
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

							DEBUG.System_print(this.EXT_DO_Collector().get_v_statements_in_front(), "-------------Statements in Front of RB_DataobjectsCollector   ---------------------------", DEBUG_FLAGS.MARTINS_EIGENER);


							//zuerst die statements vorher
							mv._add(bibDB.ExecMultiSQLVector(this.EXT_DO_Collector().get_v_statements_in_front(),false));

							if (mv.get_bIsOK()) {

								Vector<String> v_do_stmt = new Vector<>();

								//jetzt speichern:
								for (RB_KM key: hmDO.keySet()) {
									RB_Dataobject_V2 d_ob = (RB_Dataobject_V2)hmDO.get(key);

									v_do_stmt.add(d_ob.get_rec20().get_sql_4_save(true));

									//									d_ob.get_rec20()._SAVE(false, mv);

									RB_StatementBuilderCollector  collector4dataobject = new RB_StatementBuilderCollector(d_ob.get_v_STATEMENTBUILDERS());

									if (collector4dataobject.size()>0) {

										boolean b_erzeugt = false;

										MyDBToolBox oDB = d_ob.get_rec20().get_oDB();

										if (oDB == null) {
											oDB=bibALL.get_myDBToolBox();
										}

										Vector<String> v_statements4Save = collector4dataobject.get_v_sqlstatements();

										DEBUG.System_print(v_statements4Save, "-------------Statemtents DataObject"+key.get_REALNAME()+"/"+key.get_HASHKEY()+"  ---------------------------", DEBUG_FLAGS.MARTINS_EIGENER);

										mv._add(oDB.ExecMultiSQLVector(v_statements4Save, false));

										if (b_erzeugt) {
											bibALL.destroy_myDBToolBox(oDB);
										}
									}


									if (!mv.get_bIsOK()) {
										break;
									}



									//										mv._add(bibDB.ExecMultiSQLVector(v_do_stmt,false));


								}
								if (mv.get_bIsOK()) {

									DEBUG.System_print(v_do_stmt, "-------------Statements in RB_DataobjectsCollector   ---------------------------", DEBUG_FLAGS.MARTINS_EIGENER);

									for(String do_stmt:v_do_stmt){
										bibDB.ExecSQL(do_stmt, false);
									}
									//martin: 2017-01-27: hier muessen die recnew gefuellt werden falls es eine neuerfassung war
									if (this.status.isStatusNew()) {
										Rec20 rec_vpos_kon = 		this.get(_TAB.vpos_kon.rb_km()).rec20();
										Rec20 rec_vpos_kon_trakt = 	this.get(_TAB.vpos_kon_trakt.rb_km()).rec20();

										String id_new_vpos = bibDB.EinzelAbfrage(_TAB.vpos_kon.sql_currval());
										String id_new_vpos_trakt = bibDB.EinzelAbfrage(_TAB.vpos_kon_trakt.sql_currval());

										rec_vpos_kon._set_recAfterSave(new Rec20(_TAB.vpos_kon)._fill_id(id_new_vpos));
										rec_vpos_kon_trakt._set_recAfterSave(new Rec20(_TAB.vpos_kon_trakt)._fill_id(id_new_vpos_trakt));
									}

								}

								if (mv.get_bIsOK()) {
									DEBUG.System_print(this.EXT_DO_Collector().get_v_statements_at_end(), "-------------Statements at the End of RB_DataobjectsCollector   ---------------------------", DEBUG_FLAGS.MARTINS_EIGENER);

									//zuerst die statements vorher
									mv._add(bibDB.ExecMultiSQLVector(this.EXT_DO_Collector().get_v_statements_at_end(),false));

									//jetzt die finalen, programmierten aenderungen
									this.execute_final_statements_in_open_transaction(this, mv);
								}					
							}
						}

						//hier beenden
						MyDBToolBox oDB = bibALL.get_myDBToolBox();

						try {
							if (mv.get_bIsOK()) {
								oDB.ownCommit();
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
					throw new myException(this,"Systemfehler Nummer 4783774 ! Bitte beim Admin melden !!");
				}
			}

		}

		return mv;		
	}

	@Override
	public void manipulate_filled_records_before_save(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv)
			throws myException {

		RB_ComponentMap oMap = this.rb_ComponentMapCollector_ThisBelongsTo().rb_get_ComponentMAP(_TAB.vpos_kon.rb_km());

		if(status == MASK_STATUS.NEW || status == MASK_STATUS.NEW_COPY){
			do_collector.get_v2(_TAB.vpos_kon_trakt.rb_km()).get_rec20()._put_raw_value(VPOS_KON_TRAKT.id_vpos_kon, _TAB.vpos_kon.seq_currval(), false);
			do_collector.get_v2(_TAB.vpos_kon_trakt.rb_km()).get_rec20()._put_raw_value(VPOS_KON_TRAKT.id_vpos_kon_trakt, _TAB.vpos_kon_trakt.seq_nextval(), false);
			do_collector.get_v2(_TAB.vpos_kon_trakt.rb_km()).get_rec20()._put_raw_value(VPOS_KON_TRAKT.deleted, "N", true);
			do_collector.get_v2(_TAB.vpos_kon_trakt.rb_km()).get_rec20()._put_raw_value(VPOS_KON_TRAKT.abgeschlossen, "N", true);
		}

		if(status == MASK_STATUS.EDIT){
 
			MyBigDecimal epreis_initValue =  new MyBigDecimal( ((RB_TextField)oMap.getRbComponentSavable(VPOS_KON.einzelpreis)).rb_readValue_4_dataobject() ,new BigDecimal(0L),new BigDecimal(0L));
			MyBigDecimal anzahl_initValue =  new MyBigDecimal( ((RB_TextField)oMap.getRbComponentSavable(VPOS_KON.anzahl)).rb_readValue_4_dataobject(),new BigDecimal(0L),new BigDecimal(0L) );
			String id_vpos_kon = bibALL.convertID2UnformattedID(id_vpos_kon_when_edit);
 
			boolean anzahl_aenderung = anzahl_initValue.get_FormatedRoundedNumber(3).equals(anzahl_value_onload.get_FormatedRoundedNumber(3));
			boolean epreis_aenderung = epreis_initValue.get_FormatedRoundedNumber(3).equals(ePreis_value_onload.get_FormatedRoundedNumber(3));

			if(epreis_aenderung || anzahl_aenderung ){
				this.update_aenderung_tabelle = true;

				Rec20 record_aenderung = new Rec20(_TAB.vpos_kon_aenderungen);

				record_aenderung._nv(VPOS_KON_AENDERUNGEN.id_vpos_kon, id_vpos_kon , mv);
				record_aenderung._nv(VPOS_KON_AENDERUNGEN.anzahl, anzahl_initValue.get_UnFormatedRoundedNumber(0), mv);
				record_aenderung._nv(VPOS_KON_AENDERUNGEN.einzelpreis, epreis_initValue.get_UnFormatedRoundedNumber(0), mv);
				record_aenderung._nv(VPOS_KON_AENDERUNGEN.kuerzel_aenderung, bibALL.get_KUERZEL(), mv);
				record_aenderung._nv(VPOS_KON_AENDERUNGEN.datum_aenderung, bibALL.get_cDateNOW(), mv);
				record_aenderung._nv(VPOS_KON_AENDERUNGEN.uhrzeit_aenderung, bibALL.get_cTimeNow().substring(0, 5) , mv);
			
				do_collector._addSqlAtEnd(record_aenderung.get_sql_4_save(true, mv));
			}else{
				this.update_aenderung_tabelle = false;
			}
		}

		if(status == MASK_STATUS.EDIT || status == MASK_STATUS.NEW){
			Rec20 record_vkopf = new Rec20(_TAB.vkopf_kon)._fill_id(oMap.getRbComponentSavable(VPOS_KON.id_vkopf_kon).rb_readValue_4_dataobject());
			
			if(record_vkopf.is_yes_db_val(VKOPF_KON.ist_fixierung)){
			
				String fix_von = 			((E2_TF_4_Date)oMap.get__Comp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_KOPF_FIX_VON.name())).get_oFormatedDateFromTextField();
				String fix_bis = 			((E2_TF_4_Date)oMap.get__Comp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_KOPF_FIX_BIS.name())).get_oFormatedDateFromTextField();
	
				record_vkopf._nv(VKOPF_KON.fix_von, fix_von, mv);
				record_vkopf._nv(VKOPF_KON.fix_bis, fix_bis, mv);
				record_vkopf._nv(VKOPF_KON.typ_25_to, oMap.getRbComponentSavable(VPOS_KON.typ_25_to).rb_readValue_4_dataobject(), mv);
				record_vkopf._nv(VKOPF_KON.typ_ladung, oMap.getRbComponentSavable(VPOS_KON.typ_ladung).rb_readValue_4_dataobject() , mv);
				if(record_vkopf.is_even_one_new_value_set()){
					do_collector._addSqlAtEnd(record_vkopf.get_sql_4_save(true, mv));
				}
			}
		}
	}

	@Override
	public void execute_final_statements_in_open_transaction(RB_DataobjectsCollector_V2 do_collector,
			MyE2_MessageVector mv) throws myException {

	}

	@Override
	public RB_DataobjectsCollector_V2 get_copy() throws myException {
		return null;
	}

	public String getBelegTyp() {
		return belegTyp;
	}

	public Rec20 getRecordKopf(){
		return record_kopf;
	}
}

