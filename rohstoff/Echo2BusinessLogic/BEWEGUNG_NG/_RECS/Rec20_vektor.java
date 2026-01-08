package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._RECS;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_PN;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorNN;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_adresse;

public class Rec20_vektor extends Rec20 {

	/**
	 * @param baseRec
	 * @throws myException
	 */
	public Rec20_vektor(Rec20 baseRec) throws myException {
		super(baseRec);
	}

	
	
	/**
	 * @throws myException
	 */
	public Rec20_vektor() throws myException {
		super(_TAB.bewegung_vektor);
	}

	
	public Rec20_vektor _fill_id(String id) throws myException {
		super._fill_id(id);
//		MyLong lid = new MyLong(id);
//		if (lid.get_bOK()) {
//			this.execute_query("SELECT * FROM "+bibE2.cTO()+"."+this.get_tab().fullTableName()+" WHERE "+this.get_tab().keyFieldName()+"="+lid.get_cUF_LongString());
//		} else {
//			throw new myException(this,"Error ID "+id+" is no number !");
//		}
		return this;
	}




	public String __sql_update_statistik(MyE2_MessageVector mv) throws myException  {
		
		String id_bewegung_vektor = this.is_newRecordSet()?this.get_rec_after_save_new().get_key_value():this.get_key_value();

		MyLong l_start = 		new MyLong(bibDB.EinzelAbfrage("SELECT START_ATOM("+id_bewegung_vektor+") FROM DUAL"));
		MyLong l_ziel = 		new MyLong(bibDB.EinzelAbfrage("SELECT ZIEL_ATOM("+id_bewegung_vektor+") FROM DUAL"));
		MyLong l_zahl_rechpos = new MyLong(bibDB.EinzelAbfrage("SELECT RECHPOS("+id_bewegung_vektor+") FROM DUAL"));
		MyLong l_zahl_gutpos  = new MyLong(bibDB.EinzelAbfrage("SELECT GUTPOS("+	id_bewegung_vektor+") FROM DUAL"));
		
		
		String sql = "UPDATE "+bibE2.cTO()+"."+BEWEGUNG_VEKTOR.fullTabName()+" SET "
						+BEWEGUNG_VEKTOR.id_bewegung_atom_trigstart.fn()+"="+l_start.get_cUF_LongString()+","
						+BEWEGUNG_VEKTOR.id_bewegung_atom_trigziel.fn()+"="+l_ziel.get_cUF_LongString()+","
						+BEWEGUNG_VEKTOR.zahl_rechpos.fn()+"="+l_zahl_rechpos.get_cUF_LongString()+","
						+BEWEGUNG_VEKTOR.zahl_gutpos.fn()+"="+l_zahl_gutpos.get_cUF_LongString()+","
						+BEWEGUNG_VEKTOR.statistik_timestamp.fn()+"=SYSDATE+NUMTODSINTERVAL(2,'SECOND')"+
						" WHERE "+BEWEGUNG_VEKTOR.id_bewegung_vektor.fn()+"="+id_bewegung_vektor;
		
		return sql;
	}

	

	
	public RecList20 __down_rl20_vektor_pos(ENUM_VEKTORPOS_TYP pos_typ) throws myException {
		RecList20 rl = this.get_down_reclist20(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor, new vgl(BEWEGUNG_VEKTOR_POS.pos_typ,pos_typ.db_val()).s(), BEWEGUNG_VEKTOR_POS.id_bewegung_vektor.fn());
		return rl;
	}
	
	
	
	public Rec20_bewegung get_up_bewegung() throws myException {
		return new Rec20_bewegung(this.get_up_rec20(BEWEGUNG.id_bewegung));
	}
	
	
	
	/**
	 *  Klasse, die einen Vektor durchsucht und fuer alle Datensaetze, die zu automatisch geschriebenen Vektorpositionen gehoerten,
	 *  die delete-statements erzeugt 
	 * @throws myException 
	 */
	public VectorNN<String> generate_sql_2_delete_all_automatic_datasets() throws myException {
		RecList20  rl_vektor_pos =  this.get_down_reclist20(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor, 
									ENUM_VEKTORPOS_TYP.get_where_statement_query_all_automatic_types().s(), BEWEGUNG_VEKTOR_POS.posnr.fn());
		
		
		VectorNN<String> v_rueck = new VectorNN<>();
		
		for (Rec20 r_vektor_pos: rl_vektor_pos) {
			
			RecList20 rl_atom = r_vektor_pos.get_down_reclist20(BEWEGUNG_ATOM.id_bewegung_vektor_pos, null, null);
			
			for (Rec20 r_atom: rl_atom) {
				v_rueck.addAll(r_atom.get_down_reclist20(BEWEGUNG_STATION.id_bewegung_atom, null, null).get_sqls_2_delete());
			}
			v_rueck.addAll(rl_atom.get_sqls_2_delete());
		}
		v_rueck.addAll(rl_vektor_pos.get_sqls_2_delete());
		
		return v_rueck;
	}

	

	
	
	public VectorNN<String> __get_update_endpreis_calc_4_all_atome(MyE2_MessageVector mv) throws myException {
		VectorNN<String> v = new VectorNN<>();
		
		for (Rec20 r_vektor_pos: this.get_down_reclist20(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor, null, BEWEGUNG_VEKTOR_POS.posnr.fn())) {
			for (Rec20 r_atom: r_vektor_pos.get_down_reclist20(BEWEGUNG_ATOM.id_bewegung_vektor_pos, null, BEWEGUNG_ATOM.posnr.fn())) {
				Rec20_atom r_atom_ext = new Rec20_atom(r_atom);
				String c_sql = r_atom_ext.__sql_to_write_endpreis_und_sorte(mv);
				v.add(c_sql);
			}
		}
		DEBUG.System_print(v, true);
		return v;
	}
	
	
	
	
	
	/**
	 * erzeugt eine kurzuebersicht ueber alle internen buchungen eines vektors
	 * erzeugt fuer jedes atom eine vectors 
	 * @return
	 * @throws myException 
	 */
	public E2_Grid generate_info_grid_interne_buchungen() throws myException {
		
		E2_Grid g = new E2_Grid()._setSize(60,20,250,60,400,400,200,200);
		
		RecList20  list_vektor_pos = this.get_down_reclist20(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor, null, BEWEGUNG_VEKTOR_POS.posnr.fn());
		
		if (list_vektor_pos.size()>0) {
			RB_gld ldt = new RB_gld()._left_top()._ins(1, 0, 8, 0)._col(new E2_ColorLight());
			g._a(new RB_lab()._tr("Menge")._fs(8)._i(),ldt._c()._right_top());
			g._a(new RB_lab()._tr("EH")._fs(8)._i(),ldt);
			g._a(new RB_lab()._tr("Sorte")._fs(8)._i(),ldt);
			g._a(new RB_lab()._tr("Preis")._fs(8)._i(),ldt._c()._right_top());
			g._a(new RB_lab()._tr("von")._fs(8)._i(),ldt);
			g._a(new RB_lab()._tr("nach")._fs(8)._i(),ldt);
			g._a(new RB_lab()._tr("Besitz vorher")._fs(8)._i(),ldt);
			g._a(new RB_lab()._tr("Besitz nachher")._fs(8)._i(),ldt);
	
			
			for (Rec20 rec_pos: list_vektor_pos) {
				RecList20  reclist_atome = rec_pos.get_down_reclist20(BEWEGUNG_ATOM.id_bewegung_vektor_pos, null, BEWEGUNG_ATOM.posnr.fn());
				
//				for (Rec20 atom: rec_pos.get_down_reclist20(BEWEGUNG_ATOM.id_bewegung_vektor_pos, null, BEWEGUNG_ATOM.posnr.fn())) {
				for (int i=0; i<reclist_atome.size();i++) {

					Rec20_atom 	atom_e = new Rec20_atom(reclist_atome.get(i));
					Rec20_station  	station_start = atom_e.__station_start();
					Rec20_station  	station_ziel  = atom_e.__station_ziel();
					
					RB_lab info_besitzerwechselvon 	= new RB_lab();
					RB_lab info_besitzerwechselziel = new RB_lab();
					
					if (i==0 || i==(reclist_atome.size()-1)) {
						//besitzerwechsel feststellen
						MyLong id_besitzer_start = station_start.get_myLong_dbVal(BEWEGUNG_STATION.id_adresse_besitzer);
						MyLong id_besitzer_ziel = station_ziel.get_myLong_dbVal(BEWEGUNG_STATION.id_adresse_besitzer);
					
						if (id_besitzer_start !=null && id_besitzer_ziel!=null && id_besitzer_start.isOK() && id_besitzer_ziel.isOK()) {
							Rec20_adresse ra_links = 	new Rec20_adresse(station_start.get_up_rec20(BEWEGUNG_STATION.id_adresse_besitzer, ADRESSE.id_adresse,true));
							Rec20_adresse ra_rechts = 	new Rec20_adresse(station_ziel.get_up_rec20(BEWEGUNG_STATION.id_adresse_besitzer, ADRESSE.id_adresse,true));
							if (id_besitzer_start.get_lValue()== id_besitzer_ziel.get_lValue()) {
								info_besitzerwechselvon._t(ra_links.__get_name1_ort())._fs(8)._col_fore_dgrey();
								info_besitzerwechselziel._t(ra_rechts.__get_name1_ort())._fs(8)._col_fore_dgrey();
							} else {
								info_besitzerwechselvon._t(ra_links.__get_name1_ort())._fs(8);
								info_besitzerwechselziel._t(ra_rechts.__get_name1_ort())._fs(8);
							}
						}
					}
					
	
					RB_gld ld = new RB_gld()._left_top()._ins(1, 0, 8, 0);
					g._a(new RB_lab()._t(atom_e.get_fs_dbVal(BEWEGUNG_ATOM.menge))._fs(8),ld._c()._right_top());
					g._a(new RB_lab()._t(atom_e._get_einheitkurz())._fs(8),ld);
					g._a(new RB_lab()._t(atom_e._get_anr1_anr2_artbez1())._fs(8),ld);
					g._a(new RB_lab()._t(atom_e.get_fs_dbVal(BEWEGUNG_ATOM.e_preis))._fs(8),ld._c()._right_top());
					
					if (station_start.is_reale_adresse()) {
						g._a(new RB_lab()._t(station_start.info_string_station())._fs(8),ld);
					} else {
						g._a(new RB_lab()._t(station_start.info_string_station())._fs(8)._col_fore_dgrey(),ld);
					}
					
					if (station_ziel.is_reale_adresse()) {
						g._a(new RB_lab()._t(station_ziel.info_string_station())._fs(8),ld);
					} else {
						g._a(new RB_lab()._t(station_ziel.info_string_station())._fs(8)._col_fore_dgrey(),ld);
					}
					
					g._a(info_besitzerwechselvon, ld);
					g._a(info_besitzerwechselziel, ld);
					
					
				}
			}
		}
		return g;
	}
	

	
	
	/**
	 * sucht bei wareneingaengen nach einer lagerseitigen mengenkorrektur, die im fall von mengendifferenzen
	 * direkt in die daten geschrieben wird
	 * @return wert der lagerseite oder null
	 * @throws myException 
	 */
	public MyBigDecimal _get_menge_WE_MENGENKORREKTUR() throws myException {
		//jetzt nachsehen, ob es unterhalb des bewegungsvektors einen vektorpos-datensatz vom typ WE_MENGEN_DIFF_LIEFERANT_LAGER gibt, wenn ja
		//dort den wert des atoms auslesen, das aus dem mengendifferenzlager kommt
		MyBigDecimal bd_rueck = null;
		
		RecList20 rl_vektor_pos = this.get_down_reclist20(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor, null, null);
		RecList20 rl_vektor_pos2 = rl_vektor_pos.get_filtered_list(rec->rec.get_ufs_dbVal(BEWEGUNG_VEKTOR_POS.pos_typ,"").equals(ENUM_VEKTORPOS_TYP.WE_MENGEN_DIFF_LIEFERANT_LAGER.db_val()));

		
		if (rl_vektor_pos2.size()>0) {
			//es darf nur eine oder keine solche vektorpos geben
			if (rl_vektor_pos2.size()==1) {
				//dann den richtigen wert aus dem 2. atom raussuchen
				RecList20 rl_atome = rl_vektor_pos2.get(0).get_down_reclist20(BEWEGUNG_ATOM.id_bewegung_vektor_pos, null, null);
				// es muss zwei atome geben, sonst fehler
				if (rl_atome.size()==2) {
					Rec20_atom  recAtom1 = new Rec20_atom(rl_atome.get(0));
					Rec20_atom  recAtom2 = new Rec20_atom(rl_atome.get(1));
					
					//es muss das atom gefunden werden, das aus dem sortenwechsellager raus fuehrt (in die reale adresse)
					if (recAtom1.__station_ziel().is_reale_adresse()) {
						bd_rueck=recAtom1.get_myBigDecimal_dbVal(BEWEGUNG_ATOM.menge);
					} else {
						bd_rueck=recAtom2.get_myBigDecimal_dbVal(BEWEGUNG_ATOM.menge);
					}
					bd_rueck = (bd_rueck.isOK()?bd_rueck:null);
				} else {
					throw new myException(this,"Error! It MUST be 2 record  bewegung_atom under bewegung_vektor_pos WE_MENGEN_DIFF_LIEFERANT_LAGER!");
				}
			} else {
				throw new myException(this,"Error! Only 1 vektor_pos of type WE_MENGEN_DIFF_LIEFERANT_LAGER is possible !");
			}
		}

		
		return bd_rueck;
		
	}
	
	
	
	
//	public Rec20_vektor _write_primanota(boolean b_commit, MyE2_MessageVector mv) throws myException {
//		String c_query_atom = "SELECT START_ATOM("+this.get_ufs_dbVal(BEWEGUNG_VEKTOR.id_bewegung_vektor)+") as ID_START_ATOM, ZIEL_ATOM("+this.get_ufs_dbVal(BEWEGUNG_VEKTOR.id_bewegung_vektor)+") as ID_ZIEL_ATOM FROM DUAL ";
//		
//		MyRECORD  rec = new MyRECORD(c_query_atom);
//		
//		Rec20_atom  a_start = new Rec20_atom()._fill_id(rec.ufs("ID_START_ATOM"));
//		Rec20_atom  a_ziel = new Rec20_atom()._fill_id(rec.ufs("ID_ZIEL_ATOM"));
//		
//		Rec20_station stat_start = a_start.__station_start();
//		Rec20_station stat_ziel =  a_ziel.__station_ziel();
//		
//		DEBUG.System_println("ID_ATOM-Start: "+rec.ufs("ID_START_ATOM")+"    ->      "+"ID_ATOM-Ziel: "+rec.ufs("ID_ZIEL_ATOM"));
////		DEBUG.System_println("menge Start: "+rec.ufs("ID_START_ATOM")+"    ->      "+"menge-Ziel: "+rec.ufs("ID_ZIEL_ATOM"));
//
//		Rec20 neu = new Rec20(_TAB.bewegung_vektor_pn);
//		neu
//			._nv(BEWEGUNG_VEKTOR_PN.id_bewegung_vektor, 			this.get_ufs_dbVal(BEWEGUNG_VEKTOR.id_bewegung_vektor), mv)
//			._nv(BEWEGUNG_VEKTOR_PN.id_adresse_start, 				stat_start.get_fs_dbVal(BEWEGUNG_STATION.id_adresse), mv)
//			._nv(BEWEGUNG_VEKTOR_PN.id_adresse_ziel, 				stat_ziel.get_fs_dbVal(BEWEGUNG_STATION.id_adresse), mv)
//			._nv(BEWEGUNG_VEKTOR_PN.id_artikel_bez_start, 			a_start.get_fs_dbVal(BEWEGUNG_ATOM.id_artikel_bez), mv)
//			._nv(BEWEGUNG_VEKTOR_PN.id_artikel_bez_ziel, 			a_ziel.get_fs_dbVal(BEWEGUNG_ATOM.id_artikel_bez), mv)
//			._nv(BEWEGUNG_VEKTOR_PN.menge_start, 					a_start.get_fs_dbVal(BEWEGUNG_ATOM.menge), mv)
//			._nv(BEWEGUNG_VEKTOR_PN.menge_ziel, 					a_ziel.get_fs_dbVal(BEWEGUNG_ATOM.menge), mv)
//			._nv(BEWEGUNG_VEKTOR_PN.abzug_menge_start, 				a_start.get_fs_dbVal(BEWEGUNG_ATOM.abzug_menge), mv)
//			._nv(BEWEGUNG_VEKTOR_PN.abzug_menge_ziel, 				a_ziel.get_fs_dbVal(BEWEGUNG_ATOM.abzug_menge), mv)
//			._nv(BEWEGUNG_VEKTOR_PN.e_preis_start, 					a_start.get_fs_dbVal(BEWEGUNG_ATOM.e_preis), mv)
//			._nv(BEWEGUNG_VEKTOR_PN.e_preis_ziel, 					a_ziel.get_fs_dbVal(BEWEGUNG_ATOM.e_preis), mv)
//			._nv(BEWEGUNG_VEKTOR_PN.e_preis_result_netto_mge_start, a_start.get_fs_dbVal(BEWEGUNG_ATOM.e_preis_result_netto_mge), mv)
//			._nv(BEWEGUNG_VEKTOR_PN.e_preis_result_netto_mge_ziel, 	a_ziel.get_fs_dbVal(BEWEGUNG_ATOM.e_preis_result_netto_mge), mv)
//			._nv(BEWEGUNG_VEKTOR_PN.id_user, 						bibALL.get_ID_USER(), mv)
//			._nv(BEWEGUNG_VEKTOR_PN.id_adresse_besitzer_start, 		stat_start.get_fs_dbVal(BEWEGUNG_STATION.id_adresse_besitzer), mv)
//			._nv(BEWEGUNG_VEKTOR_PN.id_adresse_besitzer_ziel, 		stat_ziel.get_fs_dbVal(BEWEGUNG_STATION.id_adresse_besitzer), mv)
//			
//			
//		   ;
//		
//		neu._SAVE(b_commit, mv);
//		
//		return this;
//	}
	
}
