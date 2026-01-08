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

public class FZ_sortenwechsel {

//	private VectorNN<String>  	v_sql = new VectorNN<>();
	
	/**
	 * id_bewegung_vektor kann sein: ein bestehender ID-WERT oder ein SEQ_BEWEGUNG_VEKTOR.CURRVAL,
	 * nach der pruefung ist der wert im raw-modus nutzbar
	 */
	private String  		id_bewegung_vektor = null;
	
	private String   		id_adresse_beginn_der_buchung = null;
	private String   		id_adresse_besitzer = null;

	private String  		id_artikel_bez_vorher = null;
	private String   		id_artikel_bez_nachher = null;
	private String          e_preis = null;					//kann null sein
	private String          menge = null;					//kann null sein 
	
	private ENUM_VEKTORPOS_TYP  vektorpos_typ = null;
	private String 			leistungsdatum = null;
	private int    			atom_posnr_startwert = 0;
	private int    			vektor_posnr_startwert = 0;
	
	/**
	 * 
	 */
	public FZ_sortenwechsel() {
		super();
	}
	
	public FZ_sortenwechsel _01_vektorpos_typ(ENUM_VEKTORPOS_TYP typ) {
		this.vektorpos_typ = typ;
		return this;
	}

	public FZ_sortenwechsel _02_bewegung_vektor(String id) {
		this.id_bewegung_vektor = id;
		return this;
	}

	public FZ_sortenwechsel _03_vektor_posnr_startwert(int vektor_posnr_startwert) {
		this.vektor_posnr_startwert = vektor_posnr_startwert;
		return this;
	}
	public FZ_sortenwechsel _04_atom_posnr_startwert(int atom_posnr_startwert) {
		this.atom_posnr_startwert = atom_posnr_startwert;
		return this;
	}

	public FZ_sortenwechsel _05_leistungsdatum(String datum) {
		this.leistungsdatum = datum;
		return this;
	}

	
	public FZ_sortenwechsel _06_adresse_ausgang(String id) {
		this.id_adresse_beginn_der_buchung = id;
		return this;
	}
	public FZ_sortenwechsel _07_adresse_besitzer(String id) {
		this.id_adresse_besitzer = id;
		return this;
	}
	public FZ_sortenwechsel _08_artikel_bez_vorher(String id) {
		this.id_artikel_bez_vorher = id;
		return this;
	}
	public FZ_sortenwechsel _09_artikel_bez_nachher(String id) {
		this.id_artikel_bez_nachher = id;
		return this;
	}

		
	/**
	 * 
	 * @param p_e_preis (can be null)
	 * @return
	 */
	public FZ_sortenwechsel _00_e_preis(String p_e_preis) {
		this.e_preis = p_e_preis;
		return this;
	}
	
	/**
	 * 
	 * @param p_menge (can be null)
	 * @return
	 */
	public FZ_sortenwechsel _00_menge(String p_menge) {
		this.menge = p_menge;
		return this;
	}
	
	
	
	public VectorNN<String>  get_statements(boolean throwExceptionIfNotPossible, MyE2_MessageVector mv) throws myException {
		
		VectorNN<String>  v = new VectorNN<>();
		
		if (this.pruefeVollstaendig()) {
			
			//nachsehen, ob noetig
			if (this.id_artikel_bez_vorher.equals(id_artikel_bez_nachher)) {
				//nix passiert
				return v;
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

			//linke seite - rein ins SW-lager
			Rec20 					rec_artikelbez1 = new Rec20(_TAB.artikel_bez)._fill_id(new MyLong(this.id_artikel_bez_vorher).get_lValue());
			Rec20 					rec_artikel1 	=	rec_artikelbez1.get_up_rec20(ARTIKEL.id_artikel);
			RECORD_ADRESSE_extend 	rec_adresse   =   new RECORD_ADRESSE_extend(new MyLong(this.id_adresse_beginn_der_buchung).get_cUF_LongString());
			
			if (!rec_adresse.get_main_Adress().get_ID_ADRESSE_cUF().equals(bibALL.get_ID_ADRESS_MANDANT())) {
				throw new myException(this,"Sortenwechsel nur in Lagern, die zum Mandanten gehören !!");
			}
			
			atom_links	._nv(BEWEGUNG_ATOM.id_artikel, 							rec_artikel1.get_fs_dbVal(ARTIKEL.id_artikel), mv)
						._nv(BEWEGUNG_ATOM.id_artikel_bez, 						rec_artikelbez1.get_fs_dbVal(ARTIKEL_BEZ.id_artikel_bez), mv)
						._nv(BEWEGUNG_ATOM.e_preis, 							this.e_preis, mv)
						._nv(BEWEGUNG_ATOM.menge, 								this.menge, mv)
						._nv(BEWEGUNG_ATOM.posnr, 								""+(atom_posnr_startwert++), mv)
						._put_raw_value(BEWEGUNG_ATOM.id_bewegung_vektor, 		this.id_bewegung_vektor, false)
						._put_raw_value(BEWEGUNG_ATOM.id_bewegung_vektor_pos, 	_TAB.bewegung_vektor_pos.seq_currval(), false)
						
						;
			
			stat1_links ._nv(BEWEGUNG_STATION.id_adresse, 						rec_adresse.get_ID_ADRESSE_cF(), mv)
						._nv(BEWEGUNG_STATION.id_adresse_basis, 				bibALL.get_ID_ADRESS_MANDANT(), mv)
						._nv(BEWEGUNG_STATION.mengenvorzeichen, 				"-1", mv)
						._nv(BEWEGUNG_STATION.id_adresse_besitzer, 				this.id_adresse_besitzer, mv)
						._put_raw_value(BEWEGUNG_STATION.id_bewegung_atom,	 	_TAB.bewegung_atom.seq_currval(), false)
						;
			
			stat2_links ._nv(BEWEGUNG_STATION.id_adresse, 						bibSES.get_ID_ADRESSE_LAGER_SORTENWECHSEL(), mv)
						._nv(BEWEGUNG_STATION.id_adresse_basis, 				bibALL.get_ID_ADRESS_MANDANT(), mv)
						._nv(BEWEGUNG_STATION.id_adresse_besitzer, 				this.id_adresse_besitzer, mv)
						._nv(BEWEGUNG_STATION.mengenvorzeichen, 				"1", mv)
						._put_raw_value(BEWEGUNG_STATION.id_bewegung_atom, 		_TAB.bewegung_atom.seq_currval(), false)
						;
			
			//rechte seite - raus aus SW-lager
			Rec20 rec_artikelbez2 = 	new Rec20(_TAB.artikel_bez)._fill_id(new MyLong(this.id_artikel_bez_nachher).get_lValue());
			Rec20 rec_artikel2 	=		rec_artikelbez2.get_up_rec20(ARTIKEL.id_artikel);

			atom_rechts	._nv(BEWEGUNG_ATOM.id_artikel, 							rec_artikel2.get_fs_dbVal(ARTIKEL.id_artikel), mv)
						._nv(BEWEGUNG_ATOM.id_artikel_bez, 						rec_artikelbez2.get_fs_dbVal(ARTIKEL_BEZ.id_artikel_bez), mv)
						._nv(BEWEGUNG_ATOM.e_preis, 							this.e_preis, mv)
						._nv(BEWEGUNG_ATOM.menge, 								this.menge, mv)
						._nv(BEWEGUNG_ATOM.posnr, 								""+(atom_posnr_startwert++), mv)
						._put_raw_value(BEWEGUNG_ATOM.id_bewegung_vektor, 		this.id_bewegung_vektor, false)
						._put_raw_value(BEWEGUNG_ATOM.id_bewegung_vektor_pos, 	_TAB.bewegung_vektor_pos.seq_currval(), false)
						;

			stat1_rechts ._nv(BEWEGUNG_STATION.id_adresse, 						bibSES.get_ID_ADRESSE_LAGER_SORTENWECHSEL(), mv)
						._nv(BEWEGUNG_STATION.id_adresse_basis, 				bibALL.get_ID_ADRESS_MANDANT(), mv)
						._nv(BEWEGUNG_STATION.id_adresse_besitzer, 				this.id_adresse_besitzer, mv)
						._nv(BEWEGUNG_STATION.mengenvorzeichen,					 "-1", mv)
						._put_raw_value(BEWEGUNG_STATION.id_bewegung_atom, 		_TAB.bewegung_atom.seq_currval(), false)
						;
			
			stat2_rechts ._nv(BEWEGUNG_STATION.id_adresse, 						rec_adresse.get_ID_ADRESSE_cF(), mv)
						._nv(BEWEGUNG_STATION.id_adresse_basis, 				bibALL.get_ID_ADRESS_MANDANT(), mv)
						._nv(BEWEGUNG_STATION.id_adresse_besitzer, 				this.id_adresse_besitzer, mv)
						._nv(BEWEGUNG_STATION.mengenvorzeichen, 				"1", mv)
						._put_raw_value(BEWEGUNG_STATION.id_bewegung_atom, 		_TAB.bewegung_atom.seq_currval(), false)
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
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Automatische Umbuchungen <Sortenwechsel> nicht möglich !")));
			}
		}
		
		return v;
	}
	
	
	private boolean pruefeVollstaendig() {
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
				&&  new MyLong(id_artikel_bez_vorher).get_bOK()
				&&  new MyLong(id_artikel_bez_nachher).get_bOK()
				&& 	S.isEmpty(e_preis)||new MyBigDecimal(e_preis).get_bOK()
				&&  S.isEmpty(menge)||new MyBigDecimal(menge).get_bOK()
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
