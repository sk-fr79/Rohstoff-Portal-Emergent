package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE;

import java.util.Date;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.basics4project.myCONST_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_PN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.ORACLE.bibORA;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._RECS.Rec20_atom;


public class FZ_dataObjectsCollector_doAfterSave_writePrimanotaTable implements FZ_dataObjectsCollector_doAfterSave {



	@Override
	public void executeInOpenTransactionAfterSave(FZ_dataObjectsCollector do_collector, MyE2_MessageVector mv) throws myException {
		
		
		//primanota-nummer aus der sequenz holen, ebenfalls eindeutige zeit
		String nextPrimanotaNummer = myCONST_ENUM.SEQ_MANDANT.PRIMANOTABLOCK.getNextVal();
		MyRECORD  rec = new MyRECORD("SELECT SYSDATE as DAT FROM DUAL");
		Date datumZeit = rec.get("DAT").getTimeStampValue();
		String rawField = bibORA.insertStringFunction(datumZeit);
		String id_user = bibALL.get_RECORD_USER().get_ID_USER_cUF();
		
		for (RB_Dataobject dob: do_collector) {
		
			if (dob instanceof RB_Dataobject_V2) {
				
				RB_Dataobject_V2 dob2 = (RB_Dataobject_V2)dob;

				Vector<Rec20>  v_newPn = new Vector<>();
				
				if (dob2.get_rec20().get_tab()==_TAB.bewegung_atom) {
					Rec20_atom recSourceAtom4Primanota = new Rec20_atom(dob2.get_rec20().get_rec_after_save_new()==null?dob2.get_rec20():dob2.get_rec20().get_rec_after_save_new());
					recSourceAtom4Primanota._rebuild();
					
					Rec20 stat_start = recSourceAtom4Primanota.__station_start();
					Rec20 stat_ziel = recSourceAtom4Primanota.__station_ziel();
					
					Rec20 newPrimaNota = new Rec20(_TAB.bewegung_vektor_pn);
					
					newPrimaNota.nv(BEWEGUNG_VEKTOR_PN.id_bewegung_vektor, 				recSourceAtom4Primanota.get_fs_dbVal(BEWEGUNG_ATOM.id_bewegung_vektor), mv);
					newPrimaNota.nv(BEWEGUNG_VEKTOR_PN.id_adresse_start,   				stat_start.get_fs_dbVal(BEWEGUNG_STATION.id_adresse), mv);
					newPrimaNota.nv(BEWEGUNG_VEKTOR_PN.id_adresse_ziel,    				stat_ziel.get_fs_dbVal(BEWEGUNG_STATION.id_adresse), mv);
					newPrimaNota.nv(BEWEGUNG_VEKTOR_PN.id_adresse_besitzer_start,   	stat_start.get_fs_dbVal(BEWEGUNG_STATION.id_adresse_besitzer), mv);
					newPrimaNota.nv(BEWEGUNG_VEKTOR_PN.id_adresse_besitzer_ziel,    	stat_ziel.get_fs_dbVal(BEWEGUNG_STATION.id_adresse_besitzer), mv);
					newPrimaNota.nv(BEWEGUNG_VEKTOR_PN.id_artikel_bez, 					recSourceAtom4Primanota.get_fs_dbVal(BEWEGUNG_ATOM.id_artikel_bez), mv);
					newPrimaNota.nv(BEWEGUNG_VEKTOR_PN.menge, 							recSourceAtom4Primanota.get_fs_dbVal(BEWEGUNG_ATOM.menge), mv);
					newPrimaNota.nv(BEWEGUNG_VEKTOR_PN.abzug_menge, 					recSourceAtom4Primanota.get_fs_dbVal(BEWEGUNG_ATOM.abzug_menge), mv);
					newPrimaNota.nv(BEWEGUNG_VEKTOR_PN.verteilung_menge, 				"0", mv);
					newPrimaNota.nv(BEWEGUNG_VEKTOR_PN.e_preis, 						recSourceAtom4Primanota.get_fs_dbVal(BEWEGUNG_ATOM.e_preis), mv);
					newPrimaNota.nv(BEWEGUNG_VEKTOR_PN.e_preis_result_netto_mge, 		recSourceAtom4Primanota.get_fs_dbVal(BEWEGUNG_ATOM.e_preis_result_netto_mge), mv);
					
					newPrimaNota._add_field_val_pair(BEWEGUNG_VEKTOR_PN.saving_date_time, 	rawField);
					newPrimaNota._add_field_val_pair(BEWEGUNG_VEKTOR_PN.current_number, 	nextPrimanotaNummer);
					newPrimaNota._add_field_val_pair(BEWEGUNG_VEKTOR_PN.id_user, 			id_user);
					
					v_newPn.add(newPrimaNota);
				}
				
				for (Rec20 r: v_newPn) {
//					DEBUG.System_println(r.get_sql_4_save(true, mv));
					r._SAVE(false, mv);
				}
				
//				DEBUG.System_println("tabellen ... "+dob2.get_rec20().get_TABLENAME());
				
				
			}
		}
		
	}
	


}
