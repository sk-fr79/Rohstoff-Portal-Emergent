package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.SORTENWECHSEL;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibNUM;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;

public class SW_position {

	private MyE2_MessageVector    	mv = new MyE2_MessageVector();
	
	private MyLong  				l_id_artikel_bez_source = null;
	private MyLong  				l_id_artikel_bez_target = null;
	private MyBigDecimal 			bd_menge_formated = null;
	
	
	private boolean     			b_is_checked = false;
	
	public MyE2_MessageVector mv() {
		return mv;
	}




	public MyLong get_id_artikel_bez_source() {
		return l_id_artikel_bez_source;
	}


	public MyLong get_id_artikel_bez_target() {
		return l_id_artikel_bez_target;
	}


	public MyBigDecimal get_bd_menge_formated() {
		return bd_menge_formated;
	}


	public boolean is_checked() {
		return b_is_checked;
	}


	public SW_position() {
		super();
	}
	

	/**
	 * 
	 * @param id_artikel_bez_source
	 * @return
	 */
	public SW_position _set_id_artikel_bez_source(String id_artikel_bez_source) {
		this.l_id_artikel_bez_source = new MyLong(id_artikel_bez_source);
		
		if (this.l_id_artikel_bez_source.isNotOK()) {
			this.mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehlerhaft Source-Sortenbezeichnungs-ID: ",true,id_artikel_bez_source,false)));
			this.l_id_artikel_bez_source = null;
		}
		return this;
	}
	
	
	/**
	 * 
	 * @param id_artikel_bez_target_uf
	 * @return
	 */
	public SW_position _set_id_artikel_bez_target(String id_artikel_bez_target) {
		this.l_id_artikel_bez_target= new MyLong(id_artikel_bez_target);
		
		if (this.l_id_artikel_bez_target.isNotOK()) {
			this.mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehlerhaft Target-Sortenbezeichnungs-ID: ",true,id_artikel_bez_target,false)));
			this.l_id_artikel_bez_target = null;
		}
		return this;
	}
	
	/**
	 * 
	 * @param p_menge_formated
	 * @return
	 */
	public SW_position _set_menge_umbuchung(String p_menge_formated) {
		this.bd_menge_formated= new MyBigDecimal(p_menge_formated);
		
		if (this.bd_menge_formated.isNotOK()) {
			this.mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehlerhaft Mengenangabe",true,p_menge_formated,false)));
			this.bd_menge_formated = null;
		}
		return this;
	}
	

	
	
	public SW_position _check() throws myException {
		
		if (this.mv.get_bIsOK()) {    //sonst laeuft sowieso nix
			this.check(l_id_artikel_bez_source, "Quellartikel ist nicht definiert !");
			this.check(l_id_artikel_bez_target, "Zielartikel ist nicht definiert !");
			this.check(bd_menge_formated, "Die Umbuchungsmenge ist nicht definiert !");
		}
		this.b_is_checked=true;
		
		return this;
	}
	
	
	private void check(Object ob, String meldungstext) {
		if (ob == null) {
			this.mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(meldungstext)));
		}
	}
	
	
	public Vector<String> generate_sql_save_stack(	String 		posnummer_uf 
													,String 	id_adresse_lager_uf 
													,String 	id_adresse_besitzer_uf
													,String     id_bewegung_vektor_uf
													,String     e_preis_formated
													) throws myException {
		Vector<String> v_sql = new Vector<>();
		
		//alles muss vollstaendig sein
		if (!bibNUM.isAllLong(posnummer_uf,id_adresse_lager_uf,  id_adresse_besitzer_uf,id_bewegung_vektor_uf)) {
			throw new myException(this,"Error: parametes missing/false !");
		}
		
		if (!bibNUM.isAllNumber(e_preis_formated)) {
			throw new myException(this,"Error (2): parametes missing/false !");
		}
		
		if (!(this.b_is_checked && this.mv.get_bIsOK())) {
			throw new myException(this,"Error ! Something is wrong !");
		}
		
		
		long posnummer = new MyLong(posnummer_uf).get_lValue();
		
		RECORD_ARTIKEL_BEZ  ab_source = new RECORD_ARTIKEL_BEZ(this.l_id_artikel_bez_source.get_cUF_LongString());
		RECORD_ARTIKEL_BEZ  ab_target = new RECORD_ARTIKEL_BEZ(this.l_id_artikel_bez_target.get_cUF_LongString());
		
		
			
		//neue datensaetze
		RECORDNEW_BEWEGUNG_VEKTOR_POS  	new_pos = new RECORDNEW_BEWEGUNG_VEKTOR_POS();
		mv.add_MESSAGE(new_pos.nv(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor,	id_bewegung_vektor_uf));
		mv.add_MESSAGE(new_pos.nv(BEWEGUNG_VEKTOR_POS.pos_typ, 				ENUM_VEKTORPOS_TYP.WE_HIDDEN_SEP.db_val()));
		mv.add_MESSAGE(new_pos.nv(BEWEGUNG_VEKTOR_POS.posnr, 				""+posnummer));

		
		RECORDNEW_BEWEGUNG_ATOM        atom_raus = new RECORDNEW_BEWEGUNG_ATOM();
		mv.add_MESSAGE(atom_raus.nv(BEWEGUNG_ATOM.id_artikel, 		ab_source.fs(BEWEGUNG_ATOM.id_artikel)));
		mv.add_MESSAGE(atom_raus.nv(BEWEGUNG_ATOM.id_artikel_bez, 	ab_source.fs(BEWEGUNG_ATOM.id_artikel_bez)));
		mv.add_MESSAGE(atom_raus.nv(BEWEGUNG_ATOM.menge, 			this.bd_menge_formated.get_FormatedRoundedNumber(3)));
		mv.add_MESSAGE(atom_raus.nv(BEWEGUNG_ATOM.e_preis, 			e_preis_formated));
		mv.add_MESSAGE(atom_raus.nv(BEWEGUNG_ATOM.id_bewegung_vektor, id_bewegung_vektor_uf));
		mv.add_MESSAGE(atom_raus.nv(BEWEGUNG_ATOM.posnr, 			""+(posnummer*100)));
		atom_raus.add_raw_val(BEWEGUNG_ATOM.id_bewegung_vektor_pos, _TAB.bewegung_vektor_pos.seq_currval());

		
		//buchung lager ins sortenwechsel-lager
		RECORDNEW_BEWEGUNG_STATION     stat_l = new RECORDNEW_BEWEGUNG_STATION();
		mv.add_MESSAGE(stat_l.nv(BEWEGUNG_STATION.id_adresse, 			id_adresse_lager_uf));
		mv.add_MESSAGE(stat_l.nv(BEWEGUNG_STATION.id_adresse_basis, 	bibALL.get_ID_ADRESS_MANDANT()));
		mv.add_MESSAGE(stat_l.nv(BEWEGUNG_STATION.id_adresse_besitzer,  id_adresse_besitzer_uf));
		mv.add_MESSAGE(stat_l.nv(BEWEGUNG_STATION.mengenvorzeichen, 	"-1"));
		stat_l.add_raw_val(BEWEGUNG_STATION.id_bewegung_atom, _TAB.bewegung_atom.seq_currval());

		RECORDNEW_BEWEGUNG_STATION     stat_r = new RECORDNEW_BEWEGUNG_STATION();
		mv.add_MESSAGE(stat_r.nv(BEWEGUNG_STATION.id_adresse, 			bibSES.get_ID_ADRESSE_LAGER_SORTENWECHSEL()));
		mv.add_MESSAGE(stat_r.nv(BEWEGUNG_STATION.id_adresse_basis, 	bibALL.get_ID_ADRESS_MANDANT()));
		mv.add_MESSAGE(stat_r.nv(BEWEGUNG_STATION.id_adresse_besitzer,  id_adresse_besitzer_uf));
		mv.add_MESSAGE(stat_r.nv(BEWEGUNG_STATION.mengenvorzeichen, 	"1"));
		stat_r.add_raw_val(BEWEGUNG_STATION.id_bewegung_atom, _TAB.bewegung_atom.seq_currval());
		
		
		RECORDNEW_BEWEGUNG_ATOM        atom_rein = new RECORDNEW_BEWEGUNG_ATOM();
		mv.add_MESSAGE(atom_rein.nv(BEWEGUNG_ATOM.id_artikel, 			ab_target.fs(BEWEGUNG_ATOM.id_artikel)));
		mv.add_MESSAGE(atom_rein.nv(BEWEGUNG_ATOM.id_artikel_bez, 		ab_target.fs(BEWEGUNG_ATOM.id_artikel_bez)));
		mv.add_MESSAGE(atom_rein.nv(BEWEGUNG_ATOM.menge, 				this.bd_menge_formated.get_FormatedRoundedNumber(3)));
		mv.add_MESSAGE(atom_rein.nv(BEWEGUNG_ATOM.e_preis, 				e_preis_formated));
		mv.add_MESSAGE(atom_rein.nv(BEWEGUNG_ATOM.id_bewegung_vektor, 	id_bewegung_vektor_uf));
		mv.add_MESSAGE(atom_rein.nv(BEWEGUNG_ATOM.posnr, 			""+(posnummer*100+1)));
		atom_rein.add_raw_val(BEWEGUNG_ATOM.id_bewegung_vektor_pos, _TAB.bewegung_vektor_pos.seq_currval());
		

		
		//buchung sortenwechsel-lager  ins lager
		RECORDNEW_BEWEGUNG_STATION     stat_l2 = new RECORDNEW_BEWEGUNG_STATION();
		mv.add_MESSAGE(stat_l2.nv(BEWEGUNG_STATION.id_adresse, 			bibSES.get_ID_ADRESSE_LAGER_SORTENWECHSEL()));
		mv.add_MESSAGE(stat_l2.nv(BEWEGUNG_STATION.id_adresse_basis, 	bibALL.get_ID_ADRESS_MANDANT()));
		mv.add_MESSAGE(stat_l2.nv(BEWEGUNG_STATION.id_adresse_besitzer, id_adresse_besitzer_uf));
		mv.add_MESSAGE(stat_l2.nv(BEWEGUNG_STATION.mengenvorzeichen, 	"-1"));
		stat_l2.add_raw_val(BEWEGUNG_STATION.id_bewegung_atom, _TAB.bewegung_atom.seq_currval());
		

		RECORDNEW_BEWEGUNG_STATION     stat_r2 = new RECORDNEW_BEWEGUNG_STATION();
		mv.add_MESSAGE(stat_r2.nv(BEWEGUNG_STATION.id_adresse, 		  	id_adresse_lager_uf));
		mv.add_MESSAGE(stat_r2.nv(BEWEGUNG_STATION.id_adresse_basis, 	bibALL.get_ID_ADRESS_MANDANT()));
		mv.add_MESSAGE(stat_r2.nv(BEWEGUNG_STATION.id_adresse_besitzer, id_adresse_besitzer_uf));
		mv.add_MESSAGE(stat_r2.nv(BEWEGUNG_STATION.mengenvorzeichen, 	"1"));
		stat_r2.add_raw_val(BEWEGUNG_STATION.id_bewegung_atom, _TAB.bewegung_atom.seq_currval());

		
		v_sql.add(new_pos.get_InsertSQLStatementWith_Id_Field(true, true));
		v_sql.add(atom_raus.get_InsertSQLStatementWith_Id_Field(true, true));
		v_sql.add(stat_l.get_InsertSQLStatementWith_Id_Field(true, true));
		v_sql.add(stat_r.get_InsertSQLStatementWith_Id_Field(true, true));

		v_sql.add(atom_rein.get_InsertSQLStatementWith_Id_Field(true, true));
		v_sql.add(stat_l2.get_InsertSQLStatementWith_Id_Field(true, true));
		v_sql.add(stat_r2.get_InsertSQLStatementWith_Id_Field(true, true));
		
		
		return v_sql;
			
	}

	
	
}
