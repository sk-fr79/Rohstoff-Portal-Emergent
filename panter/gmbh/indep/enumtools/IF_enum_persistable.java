package panter.gmbh.indep.enumtools;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.exceptions.myException;

/**
 * interface fuer enums, die in eine systemtabelle abgebildet werden koennen
 * @author martin
 *
 */
public interface IF_enum_persistable<T extends Enum<T>> {
	
	public _TAB 	get_tab(); 		//liefert die persistenz-tabelle 
	
	public boolean 	enum_equals_record(T en, Rec20 rec_enumTab) throws myException;		//vergleichsmethode, wann ist eine enum und der verglichene record gleich
	public T[]    	get_Values(); 														//abbildung der getValues()
	public Rec20 	get_new_rec20_with_enum_vals(MyE2_MessageVector mv) throws myException; 					//return eines mit werten gefuellten Rec20 fuer die enum
	
	
	public default boolean delete_recs_not_fitting(MyE2_MessageVector mv) throws myException {
		MyE2_MessageVector mv_innen = new MyE2_MessageVector();
		RecList20 record_to_delete = this.get_records_to_delete(new RecList20(this.get_tab())._fill(null, null));
		if (record_to_delete.size()>0) {
			DEBUG.System_println("ENUM-Records to delete: "+record_to_delete.size(),true);

			mv_innen.add_MESSAGE(bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(record_to_delete.get_sqls_2_delete(), true));
			mv.add_MESSAGE(mv_innen);
		}
		return mv_innen.get_bIsOK();
	}
	

	public default boolean create_recs_missing(MyE2_MessageVector mv) throws myException {
		MyE2_MessageVector mv_innen = new MyE2_MessageVector();
		Vector<T> enums_missing_in_db = this.get_enums_for_create_record(new RecList20(this.get_tab())._fill(null, null));
		Vector<String> v_sqls_4_insert = new Vector<>();
		
		for (T t: enums_missing_in_db) {
			@SuppressWarnings("unchecked")
			String sql = ((IF_enum_persistable<T>)t).get_new_rec20_with_enum_vals(mv).get_sql_4_save(true, mv);
			if (S.isFull(sql)) {
				v_sqls_4_insert.add(sql);
			}
		}
		if (v_sqls_4_insert.size()>0) {
			DEBUG.System_println("ENUM-Records to create: "+v_sqls_4_insert.size(),true);
			mv_innen.add_MESSAGE(bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(v_sqls_4_insert, true));
			mv.add_MESSAGE(mv_innen);
		}
		return mv_innen.get_bIsOK();
	}
	
	
	
	
	
	/**
	 * sucht alle records, die geloescht werden muessen
	 * @param rl_all_enum_records
	 * @return
	 * @throws myException
	 */
	public default RecList20 get_records_to_delete(RecList20 rl_all_enum_records) throws myException {
		RecList20  rl_to_del = new RecList20(this.get_tab());
	
		T[] enums = this.get_Values();
		
		for (Rec20 r: rl_all_enum_records) {
			boolean record_is_ok = false;
			for (T t: enums) {
				if (this.enum_equals_record(t, r)) {
					record_is_ok=true; 						//ist gleich
					break;
				}
			}
			if (!record_is_ok) {
				rl_to_del._add(r);
			}
		}
		
		return rl_to_del;
	}
	

	/**
	 * sucht alle records, die geloescht werden muessen
	 * @param rl_all_enum_records
	 * @return
	 * @throws myException
	 */
	public default Vector<T> get_enums_for_create_record(RecList20 rl_all_enum_records) throws myException {
		Vector<T>  v_enums_to_create = new Vector<T>();
	
		T[] enums = this.get_Values();
		
		for (T e: enums) {
			boolean record_found = false;
			for (Rec20 r: rl_all_enum_records) {
				if (this.enum_equals_record(e, r)) {
					record_found=true;
				}
			}
			if (!record_found) {
				v_enums_to_create.add(e);
			}
		}
		
		return v_enums_to_create;
	}
	

	
	
	
	
	
}
