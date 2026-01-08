package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._RECS;

import java.math.BigDecimal;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;

public class Rec20_atom extends Rec20 {

	
	
	/**
	 * @param p_tab
	 * @throws myException
	 */
	public Rec20_atom() throws myException {
		super(_TAB.bewegung_atom);
	}


	public Rec20_atom(Rec20 baseRec) throws myException {
		super(baseRec);
	}
	
	
	
	public Rec20_atom _fill_id(String id) throws myException {
		super._fill_id(id);
		return this;
	}
	
	
	public Rec20_atom _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}

	
	
	
	public Rec20_station __station_start() throws myException {
		RecList20 rl_station = this.get_down_reclist20(BEWEGUNG_STATION.id_bewegung_atom, null, BEWEGUNG_STATION.mengenvorzeichen.fn());
		if (rl_station.size()!=2) {
			throw new myException(this,"Every RECORD_BEWEGUNG_ATOM must have exact 2 RECORD_BEWEGUNG_STATION - objects !");
		}
		
//		DEBUG.System_println("Vorzeichen(Start): "+rl_station.get(0).ufs_db_val(BEWEGUNG_STATION.mengenvorzeichen));
		
		return new Rec20_station(rl_station.get(0));
	}
	
	public Rec20_station __station_ziel() throws myException {
		RecList20 rl_station = this.get_down_reclist20(BEWEGUNG_STATION.id_bewegung_atom, null, BEWEGUNG_STATION.mengenvorzeichen.fn());
		if (rl_station.size()!=2) {
			throw new myException(this,"Every RECORD_BEWEGUNG_ATOM must have exact 2 RECORD_BEWEGUNG_STATION - objects !");
		}

//		DEBUG.System_println("Vorzeichen(ziel): "+rl_station.get(1).ufs_db_val(BEWEGUNG_STATION.mengenvorzeichen));

		return new Rec20_station(rl_station.get(1));
	}

	
	
	
	
	/**
	 * 
	 * @param mv
	 * @return sql-statement to correct endpreis und sorte (anhand der id_Artikel_bez)
	 * @throws myException
	 */
	public String __sql_to_write_endpreis_und_sorte(MyE2_MessageVector mv) throws myException {
		
		String c_sql = null;
		
		//zuerst die sorte laden, sonst kann keine kalkulation erfolgen
		MyLong l_id_artikel_bez = this.get_myLong_dbVal(BEWEGUNG_ATOM.id_artikel_bez);
		
		if (l_id_artikel_bez != null && l_id_artikel_bez.get_bOK()) {
		
			Rec20 recArtikel = new Rec20(_TAB.artikel_bez)._fill_id(l_id_artikel_bez.get_cUF_LongString()).get_up_rec20(ARTIKEL.id_artikel);
			
			MyBigDecimal mengendivisor = recArtikel.get_myBigDecimal_dbVal(ARTIKEL.mengendivisor);

			//anzahl und einzelpreis beschaffen
			MyBigDecimal bd_epreis = this.get_myBigDecimal_dbVal(BEWEGUNG_ATOM.e_preis);
			if (bd_epreis==null || bd_epreis.isNotOK()) {
				bd_epreis = new MyBigDecimal("0");
			}
			
			MyBigDecimal bd_anzahl = this.get_myBigDecimal_dbVal(BEWEGUNG_ATOM.menge);
			
			if (mengendivisor!=null && bd_epreis!=null && bd_anzahl!=null && mengendivisor.get_bOK() && bd_epreis.get_bOK() && bd_anzahl.get_bOK()) {
				
				BigDecimal mengen_div = 					mengendivisor.get_bdWert();
				BigDecimal e_preis_brutto = 				bd_epreis.get_bdWert();

				BigDecimal abzug_menge = 					this.get_myBigDecimal_dbVal(BEWEGUNG_ATOM.abzug_menge,new MyBigDecimal(0)).get_bdWert();
				BigDecimal gpreis_abzug_menge = 			this.get_myBigDecimal_dbVal(BEWEGUNG_ATOM.gpreis_abz_mge,new MyBigDecimal(0)).get_bdWert();
				BigDecimal gpreis_abzug_auf_nettomenge = 	this.get_myBigDecimal_dbVal(BEWEGUNG_ATOM.gpreis_abz_auf_nettomge, new MyBigDecimal(0)).get_bdWert();
				
				BigDecimal menge_brutto_to = 				MyBigDecimal.divide(bd_anzahl.get_bdWert(),mengen_div,6);
				BigDecimal menge_netto_to = 				MyBigDecimal.divide(bd_anzahl.get_bdWert().subtract(abzug_menge),mengen_div,6);
				
				BigDecimal wert_brutto = 					MyBigDecimal.multiplicate(menge_brutto_to, e_preis_brutto, 2);
				BigDecimal wert_netto = 					wert_brutto.subtract(gpreis_abzug_menge).subtract(gpreis_abzug_auf_nettomenge);
				
				BigDecimal epreis_result_auf_brutto =      	MyBigDecimal.divide(wert_netto, menge_brutto_to, 2);
				BigDecimal epreis_result_auf_netto =   	   	MyBigDecimal.divide(wert_netto, menge_netto_to, 2);
				
				MyLong id_artikel = recArtikel.get_myLong_dbVal(ARTIKEL.id_artikel);
				
				MySqlStatementBuilder stmt = new MySqlStatementBuilder();
				stmt.add_raw(BEWEGUNG_ATOM.id_artikel, id_artikel.get_cUF_LongString(),false);
				stmt.add_raw(BEWEGUNG_ATOM.gesamtpreis, new MyBigDecimal(wert_brutto).get_UnFormatedRoundedNumber(2),false);
				stmt.add_raw(BEWEGUNG_ATOM.e_preis_result_brutto_mge, new MyBigDecimal(epreis_result_auf_brutto).get_UnFormatedRoundedNumber(2),false);
				stmt.add_raw(BEWEGUNG_ATOM.e_preis_result_netto_mge, new MyBigDecimal(epreis_result_auf_netto).get_UnFormatedRoundedNumber(2),false);
				
				c_sql = stmt.get_CompleteUPDATEString(BEWEGUNG_ATOM.fullTabName(), bibE2.cTO(), BEWEGUNG_ATOM.id_bewegung_atom.fn()+"="+this.get_key_value(), null);
			} 

		}
		
		
		return c_sql;
	}
	
	
	/**
	 * 
	 * @param mv
	 * @return sql-statement to correct endpreis und sorte (anhand der id_Artikel_bez)
	 * @throws myException
	 */
	public String __sql_to_write_sorte(MyE2_MessageVector mv) throws myException {
		
		String c_sql = null;
		
		//zuerst die sorte laden, sonst kann keine kalkulation erfolgen
		MyLong l_id_artikel_bez = this.get_myLong_dbVal(BEWEGUNG_ATOM.id_artikel_bez);
		
		if (l_id_artikel_bez != null && l_id_artikel_bez.get_bOK()) {
		
			Rec20 recArtikel = new Rec20(_TAB.artikel_bez)._fill_id(l_id_artikel_bez.get_cUF_LongString()).get_up_rec20(ARTIKEL.id_artikel);
			
			MyBigDecimal mengendivisor = recArtikel.get_myBigDecimal_dbVal(ARTIKEL.mengendivisor);

			//anzahl und einzelpreis beschaffen
			MyBigDecimal bd_epreis = this.get_myBigDecimal_dbVal(BEWEGUNG_ATOM.e_preis);
			MyBigDecimal bd_anzahl = this.get_myBigDecimal_dbVal(BEWEGUNG_ATOM.menge);
			
			if (mengendivisor!=null && bd_epreis!=null && bd_anzahl!=null && mengendivisor.get_bOK() && bd_epreis.get_bOK() && bd_anzahl.get_bOK()) {
				BigDecimal bd_wert = MyBigDecimal.multiplicate(bd_anzahl.get_bdWert(),bd_epreis.get_bdWert(),5);
				MyBigDecimal wert = new MyBigDecimal(MyBigDecimal.divide(bd_wert, mengendivisor.get_bdWert(), 2));
				MyLong id_artikel = recArtikel.get_myLong_dbVal(ARTIKEL.id_artikel);
				
				MySqlStatementBuilder stmt = new MySqlStatementBuilder();
				stmt.add_raw(BEWEGUNG_ATOM.id_artikel, id_artikel.get_cUF_LongString(),false);
				stmt.add_raw(BEWEGUNG_ATOM.gesamtpreis, wert.get_UnFormatedRoundedNumber(2),false);
				
				c_sql = stmt.get_CompleteUPDATEString(BEWEGUNG_ATOM.fullTabName(), bibE2.cTO(), BEWEGUNG_ATOM.id_bewegung_atom.fn()+"="+this.get_key_value(), null);
			} 

		}
		
		
		return c_sql;
	}

	
	
	/**
	 * prueft, ob ein atom vom typ real zu sonderlager ist 
	 * @return
	 * @throws myException
	 */
	public boolean __is_type_real_to_sw() throws myException {
		if (this.__station_ziel().__rec_adresse().get_ufs_dbVal(ADRESSE.sonderlager,"").equals(ENUM_SONDERLAGER.SW.db_val())) {
			if (this.__station_start().__rec_adresse().get_ufs_dbVal(ADRESSE.sonderlager,"").equals("")) {
				return true;
			}
		}
		return false;
	}

	
	
	public String _get_anr1_anr2_artbez1() throws myException {
		if (S.isFull(this.get_ufs_dbVal(BEWEGUNG_ATOM.id_artikel_bez,""))) {
			Rec20  artikel_bez 	= this.get_up_rec20(ARTIKEL_BEZ.id_artikel_bez);
			if (artikel_bez!=null && artikel_bez.size()>0) {
				Rec20 artikel	= artikel_bez.get_up_rec20(ARTIKEL.id_artikel);
				if (artikel!=null && artikel.size()>0) {
					StringBuffer c_rueck = new StringBuffer();
					return c_rueck.append(artikel.get_fs_dbVal(ARTIKEL.anr1,"")).append(" ").append(artikel_bez.get_fs_dbVal(ARTIKEL_BEZ.anr2)).append(" ").append(artikel.get_fs_dbVal(ARTIKEL.artbez1,"")).toString();
				}
			}
		}
		return "<?>";
	}
	
	
	
	public String _get_einheitkurz() throws myException {
		if (S.isFull(this.get_ufs_dbVal(BEWEGUNG_ATOM.id_artikel_bez,""))) {
			Rec20  artikel_bez 	= this.get_up_Rec20(ARTIKEL_BEZ.id_artikel_bez);
			if (artikel_bez!=null && artikel_bez.size()>0) {
				Rec20 artikel	= artikel_bez.get_up_Rec20(ARTIKEL.id_artikel);
				if (artikel!=null && artikel.size()>0) {
					Rec20 eh = artikel.get_up_Rec20(EINHEIT.id_einheit);
					if (eh!=null && eh.size()>0) {
						return eh.get_fs_dbVal(EINHEIT.einheitkurz,"");
					}
				}
			} 
		}
		return "<?>";
	}
	

}
