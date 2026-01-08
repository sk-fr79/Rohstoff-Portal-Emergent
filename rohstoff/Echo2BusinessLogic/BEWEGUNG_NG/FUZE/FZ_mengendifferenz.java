package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorNN;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class FZ_mengendifferenz {

//	private VectorNN<String>  	v_sql = new VectorNN<>();
	
	/**
	 * id_bewegung_vektor kann sein: ein bestehender ID-WERT oder ein SEQ_BEWEGUNG_VEKTOR.CURRVAL,
	 * nach der pruefung ist der wert im raw-modus nutzbar
	 */
	private String  			id_bewegung_vektor = null;
	
	private String 				id_adresse_beginn_der_buchung = null;
	private String   			id_adresse_besitzer = null;
	private String  			id_artikel_bez = null;
	private String		        e_preis_rein = null;
	private String		        e_preis_raus = null;
	private String          	menge_vorher = null;
	private String          	menge_nachher = null;
	
	private ENUM_VEKTORPOS_TYP   	vektorpos_typ = null;
	private String 				leistungsdatum = null;
	private long    			atom_posnr_startwert = 0;
	private long    			vektor_posnr_startwert = 0;
	
	/**
	 * 
	 */
	public FZ_mengendifferenz() {
		super();
	}
	
	public FZ_mengendifferenz _01_vektorpos_typ(ENUM_VEKTORPOS_TYP typ) {
		this.vektorpos_typ = typ;
		return this;
	}

	
	public FZ_mengendifferenz _02_bewegung_vektor(String id) {
		this.id_bewegung_vektor = id;
		return this;
	}

	public FZ_mengendifferenz _03_vektor_posnr_startwert(int vektor_posnr_startwert) {
		this.vektor_posnr_startwert = vektor_posnr_startwert;
		return this;
	}
	public FZ_mengendifferenz _04_atom_posnr_startwert(int atom_posnr_startwert) {
		this.atom_posnr_startwert = atom_posnr_startwert;
		return this;
	}
	public FZ_mengendifferenz _05_leistungsdatum(String datum) {
		this.leistungsdatum = datum;
		return this;
	}

	
	public FZ_mengendifferenz _06_adresse_ausgang(String id) {
		this.id_adresse_beginn_der_buchung = id;
		return this;
	}
	
	public FZ_mengendifferenz _07_adresse_besitzer(String id) {
		this.id_adresse_besitzer = id;
		return this;
	}
	
	
	public FZ_mengendifferenz _08_artikel_bez(String id) {
		this.id_artikel_bez = id;
		return this;
	}

	
	public FZ_mengendifferenz _00_e_preis_ins_umbuchungslager(String p_e_preis_ins_umbuchungslager) {
		this.e_preis_rein = p_e_preis_ins_umbuchungslager;
		return this;
	}
	
	public FZ_mengendifferenz _00_e_preis_aus_umbuchungslager(String p_e_preis_aus_umbuchungslager) {
		this.e_preis_raus = p_e_preis_aus_umbuchungslager;
		return this;
	}

	public FZ_mengendifferenz _00_menge_vorher(String p_menge) {
		this.menge_vorher = p_menge;
		return this;
	}
	public FZ_mengendifferenz _00_menge_nachher(String p_menge) {
		this.menge_nachher = p_menge;
		return this;
	}
	
	
	
	public VectorNN<String>  get_statements(boolean throwExceptionIfNotPossible,MyE2_MessageVector mv) throws myException {
		VectorNN<String>  v = new VectorNN<>();
		
		if (this.pruefeVollstaendig()) {
			
			//jetzt nachsehen, ob es noetig ist (wenn beide mengen leer sind oder gleich, dann nix tun
			if ( 	(S.isEmpty(this.menge_vorher) && S.isEmpty(this.menge_nachher)) 
					|| 
					(new MyBigDecimal(this.menge_vorher).get_FormatedRoundedNumber(3).equals(new MyBigDecimal(this.menge_nachher).get_FormatedRoundedNumber(3)))
				){
				return v;   //leer, nichts zu tun
			}
			
		
			Rec20  vektor_pos = new Rec20(_TAB.bewegung_vektor_pos);
			Rec20  atom_links = new Rec20(_TAB.bewegung_atom);
			Rec20  stat1_links = new Rec20(_TAB.bewegung_station);
			Rec20  stat2_links = new Rec20(_TAB.bewegung_station);
			
			Rec20  atom_rechts = new Rec20(_TAB.bewegung_atom);
			Rec20  stat1_rechts = new Rec20(_TAB.bewegung_station);
			Rec20  stat2_rechts = new Rec20(_TAB.bewegung_station);
		
			vektor_pos	._nv(BEWEGUNG_VEKTOR_POS.pos_typ, this.vektorpos_typ.db_val(), mv)
							._nv(BEWEGUNG_VEKTOR_POS.posnr, ""+(this.vektor_posnr_startwert++), mv)
							._put_raw_value(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor, this.id_bewegung_vektor, false)
							;

			Rec20 					rec_artikelbez1 = 	new Rec20(_TAB.artikel_bez)._fill_id(this.id_artikel_bez);
			Rec20 					rec_artikel1 =		rec_artikelbez1.get_up_rec20(ARTIKEL.id_artikel);
			RECORD_ADRESSE_extend 	rec_adresse   =   new RECORD_ADRESSE_extend(new MyLong(this.id_adresse_beginn_der_buchung).get_cUF_LongString());
			
			if (!rec_adresse.get_main_Adress().get_ID_ADRESSE_cUF().equals(bibALL.get_ID_ADRESS_MANDANT())) {
				throw new myException(this,"Sortenwechsel nur in Lagern, die zum Mandanten gehören !!");
			}
			
			atom_links	._nv(BEWEGUNG_ATOM.id_artikel, 			rec_artikel1.get_fs_dbVal(ARTIKEL.id_artikel), mv)
						._nv(BEWEGUNG_ATOM.id_artikel_bez, 		rec_artikelbez1.get_fs_dbVal(ARTIKEL_BEZ.id_artikel_bez), mv)
						._nv(BEWEGUNG_ATOM.e_preis, 			this.e_preis_rein, mv)
						._nv(BEWEGUNG_ATOM.posnr, 				""+(atom_posnr_startwert++), mv)
						._nv(BEWEGUNG_ATOM.menge, 				this.menge_vorher, mv)
						._put_raw_value(BEWEGUNG_ATOM.id_bewegung_vektor, 		this.id_bewegung_vektor, false)
						._put_raw_value(BEWEGUNG_ATOM.id_bewegung_vektor_pos, _TAB.bewegung_vektor_pos.seq_currval(), false)
						;
			
			stat1_links ._nv(BEWEGUNG_STATION.id_adresse, 		rec_adresse.get_ID_ADRESSE_cF(), mv)
						._nv(BEWEGUNG_STATION.id_adresse_basis, bibALL.get_ID_ADRESS_MANDANT(), mv)
						._nv(BEWEGUNG_STATION.mengenvorzeichen, "-1", mv)
						._nv(BEWEGUNG_STATION.id_adresse_besitzer, this.id_adresse_besitzer, mv)
						._put_raw_value(BEWEGUNG_STATION.id_bewegung_atom, _TAB.bewegung_atom.seq_currval(), false)
						;
			
			stat2_links ._nv(BEWEGUNG_STATION.id_adresse, 		bibSES.get_ID_ADRESSE_LAGER_MENGENKORREKTUR(), mv)
						._nv(BEWEGUNG_STATION.id_adresse_basis, bibALL.get_ID_ADRESS_MANDANT(), mv)
						._nv(BEWEGUNG_STATION.mengenvorzeichen, "1", mv)
						._nv(BEWEGUNG_STATION.id_adresse_besitzer, this.id_adresse_besitzer, mv)
						._put_raw_value(BEWEGUNG_STATION.id_bewegung_atom, _TAB.bewegung_atom.seq_currval(), false)
						;
			

			if (S.isEmpty(e_preis_raus)) {
				e_preis_raus=e_preis_rein;
			}
			
			atom_rechts	._nv(BEWEGUNG_ATOM.id_artikel, 			rec_artikel1.get_fs_dbVal(ARTIKEL.id_artikel), mv)
						._nv(BEWEGUNG_ATOM.id_artikel_bez, 		rec_artikelbez1.get_fs_dbVal(ARTIKEL_BEZ.id_artikel_bez), mv)
						._nv(BEWEGUNG_ATOM.e_preis, 			this.e_preis_raus, mv)
						._nv(BEWEGUNG_ATOM.posnr, 				""+(atom_posnr_startwert++), mv)
						._nv(BEWEGUNG_ATOM.menge, 				this.menge_nachher, mv)
						._put_raw_value(BEWEGUNG_ATOM.id_bewegung_vektor, 		this.id_bewegung_vektor, false)
						._put_raw_value(BEWEGUNG_ATOM.id_bewegung_vektor_pos, _TAB.bewegung_vektor_pos.seq_currval(), false)
						;

			stat1_rechts ._nv(BEWEGUNG_STATION.id_adresse, 		bibSES.get_ID_ADRESSE_LAGER_MENGENKORREKTUR(), mv)
						._nv(BEWEGUNG_STATION.id_adresse_basis, bibALL.get_ID_ADRESS_MANDANT(), mv)
						._nv(BEWEGUNG_STATION.id_adresse_besitzer, this.id_adresse_besitzer, mv)
						._nv(BEWEGUNG_STATION.mengenvorzeichen, "-1", mv)
						._put_raw_value(BEWEGUNG_STATION.id_bewegung_atom, _TAB.bewegung_atom.seq_currval(), false)
						;
			
			stat2_rechts ._nv(BEWEGUNG_STATION.id_adresse, 		rec_adresse.get_ID_ADRESSE_cF(), mv)
						._nv(BEWEGUNG_STATION.id_adresse_basis, bibALL.get_ID_ADRESS_MANDANT(), mv)
						._nv(BEWEGUNG_STATION.id_adresse_besitzer, this.id_adresse_besitzer, mv)
						._nv(BEWEGUNG_STATION.mengenvorzeichen, "1", mv)
						._put_raw_value(BEWEGUNG_STATION.id_bewegung_atom, _TAB.bewegung_atom.seq_currval(), false)
						;

			v.add(vektor_pos.get_sql_4_save(true));
			v.add(atom_links.get_sql_4_save(true));
			v.add(stat1_links.get_sql_4_save(true));
			v.add(stat2_links.get_sql_4_save(true));
			v.add(atom_rechts.get_sql_4_save(true));
			v.add(stat1_rechts.get_sql_4_save(true));
			v.add(stat2_rechts.get_sql_4_save(true));
			
		} else {
			if (throwExceptionIfNotPossible) {
				throw new myException(this,"Not possible, not all values present !");
			} else {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Automatische Umbuchungen <Mengendifferenz> nicht möglich !")));
			}
		}
		
		return v;
	}
	
	
	public boolean pruefeVollstaendig() {
		boolean b_id_bewegung_vektor_ok = false;
		if (this.id_bewegung_vektor.startsWith("SEQ") || new MyLong(this.id_bewegung_vektor).get_bOK()) {
			b_id_bewegung_vektor_ok=true;
			if (!(this.id_bewegung_vektor.startsWith("SEQ"))) {
				this.id_bewegung_vektor=new MyLong(this.id_bewegung_vektor).get_cUF_LongString();
			}
		}

		
		if (		b_id_bewegung_vektor_ok 
				&&  new MyLong(id_adresse_beginn_der_buchung).get_bOK()
				&&  new MyLong(id_adresse_besitzer).get_bOK()
				&&  new MyLong(id_artikel_bez).get_bOK()
				&& 	S.isEmpty(e_preis_rein)||new MyBigDecimal(e_preis_rein).get_bOK()
				&&  S.isEmpty(menge_vorher) ||new MyBigDecimal(menge_vorher).get_bOK()
				&&  S.isEmpty(menge_nachher) ||new MyBigDecimal(menge_nachher).get_bOK()
				&&  vektorpos_typ!=null
				&&  new MyDate(this.leistungsdatum).get_bOK()
				&&  vektor_posnr_startwert != 0
				&&  atom_posnr_startwert != 0
				
				) {
			return true;
		}
		return false;
	}
	
}
