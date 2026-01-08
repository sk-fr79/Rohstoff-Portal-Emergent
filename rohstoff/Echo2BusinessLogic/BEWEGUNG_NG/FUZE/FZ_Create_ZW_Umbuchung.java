package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._RECS.Rec20_atom;




public abstract class FZ_Create_ZW_Umbuchung {


	private 		Vector<HashMap<ENUM_UMBUCHUNGSDEF, Rec20>>   	vAlleUmbuchungen = new Vector<>();

	public abstract String 											get_position_nummer_umbuchungsatom_links()	throws myException;
	public abstract String 											get_position_nummer_umbuchungsatom_recht() 	throws myException;
	public abstract ENUM_VEKTORPOS_TYP 								get_vektorPos_typ() throws myException;
	public abstract ENUM_SONDERLAGER 								get_umbunchung_sonderlager() throws myException;

	
	public FZ_Create_ZW_Umbuchung(Rec20 rec_vektor, Rec20 rec_vektor_pos, Rec20 rec_atom1, Rec20 rec_atom2, MyE2_MessageVector mv) throws myException{

		HashMap<ENUM_UMBUCHUNGSDEF, Rec20>  hmUmbuchung = new HashMap<>();

		Rec20_atom rec_atom1_ext = new Rec20_atom(rec_atom1);
		Rec20_atom rec_atom2_ext = new Rec20_atom(rec_atom2);

		hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.vector, 						rec_vektor);
		hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.vectorpos, 					rec_vektor_pos);
		hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.atomstart, 					rec_atom1);
		hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.atomziel, 					rec_atom2);
		hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.station_atomstart_links, 	rec_atom1_ext.__station_start());
		hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.station_atomstart_rechts, 	rec_atom1_ext.__station_ziel());
		hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.station_atomziel_links, 		rec_atom2_ext.__station_start());
		hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.station_atomziel_rechts, 	rec_atom2_ext.__station_ziel());

		vAlleUmbuchungen.add(hmUmbuchung);
	}


	public FZ_Create_ZW_Umbuchung(RB_DataobjectsCollector_V2 doCollector, MyE2_MessageVector mv) throws myException{
		ArrayList<UmbuchungInfoMap>  ubMaps = ((FZ_dataObjectsCollector)doCollector).get_master_key().getUmbuchungen();

		for (UmbuchungInfoMap ifMap : ubMaps) {
			HashMap<ENUM_UMBUCHUNGSDEF, Rec20>  hmUmbuchung = new HashMap<>();
			hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.vector, 						doCollector.get(ifMap.get(ENUM_UMBUCHUNGSDEF.vector)).rec20());
			hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.vectorpos, 					doCollector.get(ifMap.get(ENUM_UMBUCHUNGSDEF.vectorpos)).rec20());
			hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.atomstart, 					doCollector.get(ifMap.get(ENUM_UMBUCHUNGSDEF.atomstart)).rec20());
			hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.atomziel, 					doCollector.get(ifMap.get(ENUM_UMBUCHUNGSDEF.atomziel)).rec20());
			hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.station_atomstart_links, 	doCollector.get(ifMap.get(ENUM_UMBUCHUNGSDEF.station_atomstart_links)).rec20());
			hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.station_atomstart_rechts, 	doCollector.get(ifMap.get(ENUM_UMBUCHUNGSDEF.station_atomstart_rechts)).rec20());
			hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.station_atomziel_links, 		doCollector.get(ifMap.get(ENUM_UMBUCHUNGSDEF.station_atomziel_links)).rec20());
			hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.station_atomziel_rechts, 	doCollector.get(ifMap.get(ENUM_UMBUCHUNGSDEF.station_atomziel_rechts)).rec20());
			
			vAlleUmbuchungen.add(hmUmbuchung);
		}
	}


	
	public void SAVE(boolean b_commit, MyE2_MessageVector mv) throws myException{
		for(HashMap<ENUM_UMBUCHUNGSDEF, Rec20> umbuchung: vAlleUmbuchungen){
			for(ENUM_UMBUCHUNGSDEF ub_def:  ENUM_UMBUCHUNGSDEF.values()){
				if(ub_def.isToSave()){
					if(mv.get_bIsOK()){
						umbuchung.get(ub_def)._SAVE(true, mv);
						DEBUG.System_println("**** "+ umbuchung.get(ub_def).get_sql_4_save(true));
					}
				}
			}
		}
	}

	
	public FZ_Create_ZW_Umbuchung umbuchen(MyE2_MessageVector mv) throws myException{

		for(HashMap<ENUM_UMBUCHUNGSDEF, Rec20> hmUmbuchung: vAlleUmbuchungen){

			if(is_sorte_unterschiedlich( hmUmbuchung,  mv)){

				generate_umbuchungsAtom(			hmUmbuchung,  	mv);

				update_sorte_in_umbuchungs_atoms(	hmUmbuchung, 	mv);

				update_menge_in_umbuchungs_atoms(	hmUmbuchung, 	mv);

				update_preis_in_umbuchungs_atoms(	hmUmbuchung, 	mv);

				connect_records_atom(				hmUmbuchung, 	mv);
				
				generate_umbuchungsLager(			hmUmbuchung,  	mv);

				connect_records_station(			hmUmbuchung,  	mv);

//				update_vector_position(				hmUmbuchung, 	mv);
				
			}
		}
		return this;
		
	}

	private void update_vector_position(HashMap<ENUM_UMBUCHUNGSDEF, Rec20> hmUmbuchung, MyE2_MessageVector mv) throws myException{
		Rec20 vectorpos = hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.vectorpos);
		
		vectorpos._nv(BEWEGUNG_VEKTOR_POS.pos_typ, get_vektorPos_typ().db_val(), mv);
//		vectorpos._nv(BEWEGUNG_VEKTOR_POS.posnr, , mv)
	}
	
	private void connect_records_atom(HashMap<ENUM_UMBUCHUNGSDEF, Rec20> hmUmbuchung, MyE2_MessageVector mv) throws myException {
		String uf_id_bewegung 				= hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.vector).get_myLong_dbVal(BEWEGUNG_VEKTOR.id_bewegung).get_cUF_LongString();
		String uf_id_bewegung_vektor 		= hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.vector).get_myLong_dbVal(BEWEGUNG_VEKTOR.id_bewegung_vektor).get_cUF_LongString();
		String uf_id_bewegung_vektor_pos 	= hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.vectorpos).get_myLong_dbVal(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor_pos).get_cUF_LongString();
		String uf_id_station_start 			= hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.station_atomstart_links).get_ufs_dbVal(BEWEGUNG_STATION.id_bewegung_station);
		String uf_id_station_ziel			= hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.station_atomziel_rechts).get_ufs_dbVal(BEWEGUNG_STATION.id_bewegung_station);
		
		Rec20 atom_umb_links = hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atom_start_zu_umbuchung);
		Rec20 atom_umb_rechts = hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atom_umbuchung_zu_ziel);
		
		atom_umb_links.	_nv(BEWEGUNG_ATOM.id_bewegung, 							uf_id_bewegung, 				mv);
		atom_umb_rechts._nv(BEWEGUNG_ATOM.id_bewegung.fn(), 					uf_id_bewegung, 				mv);
		
		atom_umb_links.	_nv(BEWEGUNG_ATOM.posnr.fn(), 				get_position_nummer_umbuchungsatom_links(),	mv);
		atom_umb_rechts._nv(BEWEGUNG_ATOM.posnr.fn(), 				get_position_nummer_umbuchungsatom_recht(),	mv);

		atom_umb_links.	_nv(BEWEGUNG_ATOM.id_bewegung_vektor, 					uf_id_bewegung_vektor, 			mv);
		atom_umb_rechts._nv(BEWEGUNG_ATOM.id_bewegung_vektor.fn(), 				uf_id_bewegung_vektor, 			mv);

		atom_umb_links.	_nv(BEWEGUNG_ATOM.id_bewegung_vektor_pos.fn(),			uf_id_bewegung_vektor_pos,		mv);
		atom_umb_rechts._nv(BEWEGUNG_ATOM.id_bewegung_vektor_pos.fn(),			uf_id_bewegung_vektor_pos,		mv);
		
		atom_umb_links.	_nv(BEWEGUNG_ATOM.id_bewegungstation_logi_start.fn(),	uf_id_station_start, 			mv);
		atom_umb_rechts._nv(BEWEGUNG_ATOM.id_bewegungstation_logi_start.fn(),	uf_id_station_start, 			mv);
		atom_umb_links.	_nv(BEWEGUNG_ATOM.id_bewegungstation_logi_ziel.fn(),	uf_id_station_ziel, 			mv);
		atom_umb_rechts._nv(BEWEGUNG_ATOM.id_bewegungstation_logi_ziel.fn(),	uf_id_station_ziel, 			mv);
	}

	public Vector<HashMap<ENUM_UMBUCHUNGSDEF, Rec20>> get_alle_umbunchung_hm(){
		return vAlleUmbuchungen;
	}
	
	private boolean is_sorte_unterschiedlich(HashMap<ENUM_UMBUCHUNGSDEF, Rec20> hmUmbuchung, MyE2_MessageVector mv) throws myException{
		MyLong l_id_artikel_sorte1 = hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atomstart).get_myLong_dbVal(BEWEGUNG_ATOM.id_artikel);
		MyLong l_id_artikel_sorte2 = hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atomziel).get_myLong_dbVal(BEWEGUNG_ATOM.id_artikel);

		if(l_id_artikel_sorte1.get_iValue() == l_id_artikel_sorte2.get_iValue()){
			return false;
		}

		return true;
	}


	private void generate_umbuchungsAtom(HashMap<ENUM_UMBUCHUNGSDEF, Rec20> hmUmbuchung, MyE2_MessageVector mv) throws myException{
		Rec20_atom atom3 = new Rec20_atom();
		Rec20_atom atom4 = new Rec20_atom();

		hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.atom_start_zu_umbuchung, atom3);
		hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.atom_umbuchung_zu_ziel, atom4);
	}

	
	/**
	 * 
	 * @throws myException
	 */
	private void update_sorte_in_umbuchungs_atoms(HashMap<ENUM_UMBUCHUNGSDEF, Rec20> hmUmbuchung, MyE2_MessageVector mv) throws myException{
		String sorte_atom1 =	hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atomstart).get_ufs_dbVal(BEWEGUNG_ATOM.id_artikel);
		String sorte_atom2 = 	hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atomziel).get_ufs_dbVal(BEWEGUNG_ATOM.id_artikel);
		
		String id_artbez_atom1 =hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atomstart).get_ufs_dbVal(BEWEGUNG_ATOM.id_artikel_bez);
		String id_artbez_atom2 =hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atomziel).get_ufs_dbVal(BEWEGUNG_ATOM.id_artikel_bez);
		
		String artbez1_atom1 = 	hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atomstart).get_ufs_dbVal(BEWEGUNG_ATOM.artbez1);
		String artbez1_atom2 = 	hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atomziel).get_ufs_dbVal(BEWEGUNG_ATOM.artbez1);
		
		String artbez2_atom1 = 	hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atomstart).get_ufs_dbVal(BEWEGUNG_ATOM.artbez2);
		String artbez2_atom2 = 	hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atomziel).get_ufs_dbVal(BEWEGUNG_ATOM.artbez2);

		Rec20 rec_umb_links = 	hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atom_start_zu_umbuchung);
		Rec20 rec_umb_rechts = 	hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atom_umbuchung_zu_ziel);
	
		rec_umb_links._nv(	BEWEGUNG_ATOM.id_artikel.fn(), 		sorte_atom2,		mv);
		rec_umb_links._nv(	BEWEGUNG_ATOM.id_artikel_bez.fn(), 	id_artbez_atom2,	mv);
		rec_umb_links._nv(	BEWEGUNG_ATOM.artbez1.fn(), 		artbez1_atom2,		mv);
		rec_umb_links._nv(	BEWEGUNG_ATOM.artbez2.fn(), 		artbez2_atom2,		mv);
		
		rec_umb_rechts._nv(	BEWEGUNG_ATOM.id_artikel.fn(), 		sorte_atom1, 		mv);
		rec_umb_rechts._nv(	BEWEGUNG_ATOM.id_artikel_bez.fn(), 	id_artbez_atom1, 	mv);
		rec_umb_rechts._nv(	BEWEGUNG_ATOM.artbez1.fn(), 		artbez1_atom1,	 	mv);
		rec_umb_rechts._nv(	BEWEGUNG_ATOM.artbez2.fn(), 		artbez2_atom1, 		mv);
//		
//		rec_umb_links._nv(	BEWEGUNG_ATOM.id_artikel.fn(), sorte_atom2,	mv);
//		rec_umb_rechts._nv(	BEWEGUNG_ATOM.id_artikel.fn(), sorte_atom1, mv);

	}


	private void update_menge_in_umbuchungs_atoms(HashMap<ENUM_UMBUCHUNGSDEF, Rec20> hmUmbuchung, MyE2_MessageVector mv) throws myException{
		String menge_atom1 = hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atomstart).get_ufs_dbVal(BEWEGUNG_ATOM.menge,"");
		//		MyLong menge_atom2 = this.record_list.get(3).get_fs_dbVal(BEWEGUNG_ATOM.menge, "");

		Rec20 rec_umb_links = 	hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atom_start_zu_umbuchung);
		Rec20 rec_umb_rechts = 	hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atom_umbuchung_zu_ziel);

		rec_umb_links._nv(	BEWEGUNG_ATOM.menge.fn(), menge_atom1, mv);
		rec_umb_rechts._nv(	BEWEGUNG_ATOM.menge.fn(), menge_atom1, mv);
	}


	private void update_preis_in_umbuchungs_atoms(HashMap<ENUM_UMBUCHUNGSDEF, Rec20> hmUmbuchung, MyE2_MessageVector mv) throws myException{
		String epreis_atom1 = hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atomstart).get_ufs_dbVal(BEWEGUNG_ATOM.e_preis,"");
		//		MyLong menge_atom2 = this.record_list.get(3).get_fs_dbVal(BEWEGUNG_ATOM.menge, "");

		Rec20 rec_umb_links = 	hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atom_start_zu_umbuchung);
		Rec20 rec_umb_rechts = 	hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atom_umbuchung_zu_ziel);

		rec_umb_links._nv(	BEWEGUNG_ATOM.e_preis.fn(), epreis_atom1, mv);
		rec_umb_rechts._nv(	BEWEGUNG_ATOM.e_preis.fn(), epreis_atom1, mv);
	}

	
	private void generate_umbuchungsLager(HashMap<ENUM_UMBUCHUNGSDEF, Rec20> hmUmbuchung, MyE2_MessageVector mv) throws myException{
		Rec20 atom_links__start_lager 	= new Rec20(_TAB.bewegung_station);
		Rec20 atom_links__ziel_lager 	= new Rec20(_TAB.bewegung_station);

		hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.station_start_zu_umbuchung_links, atom_links__start_lager);
		hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.station_start_zu_umbuchung_rechts, atom_links__ziel_lager);

		Rec20 atom_recht__start_lager 	= new Rec20(_TAB.bewegung_station);
		Rec20 atom_recht__ziel_lager 	= new Rec20(_TAB.bewegung_station);

		hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.station_umbuchung_zu_ziel_links, atom_recht__start_lager);
		hmUmbuchung.put(ENUM_UMBUCHUNGSDEF.station_umbuchung_zu_ziel_rechts, atom_recht__ziel_lager);

	}

	
	private void connect_records_station(HashMap<ENUM_UMBUCHUNGSDEF, Rec20> hmUmbuchung, MyE2_MessageVector mv) throws myException{
		Rec20 atom_links__start_lager 	= hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.station_start_zu_umbuchung_links);
		Rec20 atom_links__ziel_lager 	= hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.station_start_zu_umbuchung_rechts);

		Rec20 atom_recht__start_lager 	= hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.station_umbuchung_zu_ziel_links);
		Rec20 atom_recht__ziel_lager 	= hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.station_umbuchung_zu_ziel_rechts);

		String 				sonderlager_id 			= get_sonderlager_id(hmUmbuchung);
		ENUM_SONDERLAGER 	umbunchung_sonderlager 	= get_umbunchung_sonderlager();

		String adress_mandant = bibALL.get_ID_ADRESS_MANDANT();
	
		atom_links__start_lager._nv(			BEWEGUNG_STATION.id_adresse, 			sonderlager_id, 				 	mv);
		atom_links__start_lager._nv(			BEWEGUNG_STATION.id_adresse_basis, 		adress_mandant, 					mv);
		atom_links__start_lager._nv(			BEWEGUNG_STATION.id_adresse_besitzer,	adress_mandant, 					mv);
		atom_links__start_lager._put_raw_value(	BEWEGUNG_STATION.id_bewegung_atom, 		_TAB.bewegung_atom.seq_currval(), 	false);
		atom_links__start_lager._nv(			BEWEGUNG_STATION.mengenvorzeichen, 		"-1", 								mv);
		
		atom_links__ziel_lager._nv(				BEWEGUNG_STATION.id_adresse, 			umbunchung_sonderlager.get_ID(),	mv);
		atom_links__ziel_lager._nv(				BEWEGUNG_STATION.id_adresse_basis, 		adress_mandant,						mv);
		atom_links__ziel_lager._nv(				BEWEGUNG_STATION.id_adresse_besitzer, 	adress_mandant, 					mv);
		atom_links__ziel_lager._put_raw_value(	BEWEGUNG_STATION.id_bewegung_atom, 		_TAB.bewegung_atom.seq_currval(), 	false);
		atom_links__ziel_lager._nv(				BEWEGUNG_STATION.mengenvorzeichen, 		"1", 								mv);
		
		atom_recht__start_lager._nv(			BEWEGUNG_STATION.id_adresse, 			umbunchung_sonderlager.get_ID(),	mv);
		atom_recht__start_lager._nv(			BEWEGUNG_STATION.id_adresse_basis, 		adress_mandant,						mv);
		atom_recht__start_lager._nv(			BEWEGUNG_STATION.id_adresse_besitzer,	adress_mandant, 					mv);
		atom_recht__start_lager._put_raw_value(	BEWEGUNG_STATION.id_bewegung_atom, 		_TAB.bewegung_atom.seq_currval(), 	false);
		atom_recht__start_lager._nv(			BEWEGUNG_STATION.mengenvorzeichen, 		"-1", 								mv);
		
		atom_recht__ziel_lager._nv(				BEWEGUNG_STATION.id_adresse, 			sonderlager_id,  					mv);
		atom_recht__ziel_lager._nv(				BEWEGUNG_STATION.id_adresse_basis, 		adress_mandant, 				 	mv);
		atom_recht__ziel_lager._nv(				BEWEGUNG_STATION.id_adresse_besitzer, 	adress_mandant					, 	mv);
		atom_recht__ziel_lager._put_raw_value(	BEWEGUNG_STATION.id_bewegung_atom, 		_TAB.bewegung_atom.seq_currval(), 	false);
		atom_recht__ziel_lager._nv(				BEWEGUNG_STATION.mengenvorzeichen, 		"1", 								mv);
	}

	private String get_sonderlager_id(HashMap<ENUM_UMBUCHUNGSDEF, Rec20> hmUmbuchung) throws myException{
		Rec20_atom atom_links = new Rec20_atom(hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atomstart));
		return atom_links.__station_ziel().get_ufs_dbVal(BEWEGUNG_STATION.id_adresse);
	}

	@SuppressWarnings("unused")
	private String get_adresse_besitzer_id(HashMap<ENUM_UMBUCHUNGSDEF, Rec20> hmUmbuchung, boolean true_links) throws myException{
		Rec20_atom atom = null;
		String return_id = "";
		if(true_links){
			atom = new Rec20_atom(hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atomstart));		
			return_id = atom.__station_start().get_ufs_dbVal(BEWEGUNG_STATION.id_adresse_besitzer);
		}
		else{
			atom = new Rec20_atom(hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atomziel));
			return_id = atom.__station_ziel().get_ufs_dbVal(BEWEGUNG_STATION.id_adresse_besitzer);
		}
		return return_id;
	}

	@SuppressWarnings("unused")
	private String get_adresse_basis_id(HashMap<ENUM_UMBUCHUNGSDEF, Rec20> hmUmbuchung, boolean true_links) throws myException{
		Rec20_atom atom = null;
		String return_id = "";
		if(true_links){
			atom = new Rec20_atom(hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atomstart));		
			return_id = atom.__station_start().get_ufs_dbVal(BEWEGUNG_STATION.id_adresse_basis);
		}
		else{
			atom = new Rec20_atom(hmUmbuchung.get(ENUM_UMBUCHUNGSDEF.atomziel));
			return_id = atom.__station_ziel().get_ufs_dbVal(BEWEGUNG_STATION.id_adresse_basis);
		}
		return return_id;
	}


	
}
