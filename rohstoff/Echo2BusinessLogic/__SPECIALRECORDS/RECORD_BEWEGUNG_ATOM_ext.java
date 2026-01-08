package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EINHEIT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;

public class RECORD_BEWEGUNG_ATOM_ext extends RECORD_BEWEGUNG_ATOM {

	private RECORD_BEWEGUNG_STATION[] arrStationStartEnde = null;
	
	public RECORD_BEWEGUNG_ATOM_ext(RECORD_BEWEGUNG_ATOM recordOrig) {
		super(recordOrig);
	}

	
	/**
	 * liefert array[2], pos[0]=start, pos[1]=ziel-station
	 * @return
	 * @throws myException
	 */
	public RECORD_BEWEGUNG_STATION[] get_StationStartEnde() throws myException {
		
		if (this.arrStationStartEnde==null) {
			
			this.arrStationStartEnde = new  RECORD_BEWEGUNG_STATION[2];
			
			RECLIST_BEWEGUNG_STATION rlBewegung_STATION = 
					this.get_DOWN_RECORD_LIST_BEWEGUNG_STATION_id_bewegung_atom("",_DB.BEWEGUNG_STATION$MENGENVORZEICHEN,true);
			
			if (rlBewegung_STATION.get_vKeyValues().size()!=2) {
				throw new myException(this,"Every RECORD_BEWEGUNG_ATOM must have exact 2 RECORD_BEWEGUNG_STATION - objects !");
			}
			
			this.arrStationStartEnde[0]=rlBewegung_STATION.get(0);
			this.arrStationStartEnde[1]=rlBewegung_STATION.get(1);
		}
		
		return this.arrStationStartEnde;
		
	}
	
	public RECORD_BEWEGUNG_STATION  get_StationStart() throws myException {
		return this.get_StationStartEnde()[0];
	}
	
	public RECORD_BEWEGUNG_STATION  get_StationZiel() throws myException {
		return this.get_StationStartEnde()[1];
	}
	
	
	/**
	 * martin: 2016-05-03: aktualisiert die Preise des atoms
	 * @return
	 * @throws myException
	 */
	public MyE2_MessageVector  schreibe_preise() throws myException {
		MyE2_MessageVector  mv = new MyE2_MessageVector();

		this.REBUILD();
		
		BigDecimal  bd_menge = this.bd(BEWEGUNG_ATOM.menge, BigDecimal.ZERO, 4);
		BigDecimal  bd_epreis = this.bd(BEWEGUNG_ATOM.e_preis, BigDecimal.ZERO, 2);
		
		long id_artikel_bez = this.l(BEWEGUNG_ATOM.id_artikel_bez, 0l).longValue();
		
		if (id_artikel_bez>0) {
			RECORD_ARTIKEL  ra = this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel();
			BigDecimal bd_multiplikator = ra.bd(ARTIKEL.mengendivisor, BigDecimal.ZERO, 0);
			BigDecimal bd_betrag = bd_menge.divide(bd_multiplikator, BigDecimal.ROUND_HALF_UP).multiply(bd_epreis, new MathContext(2,  RoundingMode.HALF_UP));
			this.nv(BEWEGUNG_ATOM.gesamtpreis,bd_betrag,mv);
		} else {
			this.nv(BEWEGUNG_ATOM.gesamtpreis,"",mv);
		}
		mv.add_MESSAGE(this.UPDATE(true));
		
		return mv;
	}
	
	
	/**
	 * @param sonderlager    zu pruefendes sonderlager, wenn null, wird gegen alle sonderlager geprueft
	 * @return true, wenn das atom von einem eigenen lager ins sortenwechsellager geht
	 * @throws myException
	 */
	public boolean is_atom_eigenlager_nach_sonderlager(ENUM_SONDERLAGER sonderlager) throws myException {
		boolean b_rueck = false;
		
		RECORD_BEWEGUNG_STATION[] stat = this.get_StationStartEnde();
		
		RECORD_BEWEGUNG_STATION start = stat[0];
		RECORD_BEWEGUNG_STATION ziel = 	stat[1];
		
		RECORD_ADRESSE_extend  a_start = new RECORD_ADRESSE_extend(start.get_UP_RECORD_ADRESSE_id_adresse());
		RECORD_ADRESSE_extend  a_ziel = new RECORD_ADRESSE_extend(ziel.get_UP_RECORD_ADRESSE_id_adresse());
		
		if (sonderlager == null) {
			if (a_start.is_adress_of_mandant() && a_ziel.is_adress_of_mandant()) {
				if (S.isEmpty(a_start.ufs(ADRESSE.sonderlager,"")) && S.isFull(a_ziel.ufs(ADRESSE.sonderlager,""))) {
					return b_rueck = true;
				}
			}
			
		} else {
			if (a_start.is_adress_of_mandant() && a_ziel.is_adress_of_mandant()) {
				if (S.isEmpty(a_start.ufs(ADRESSE.sonderlager,"")) && a_ziel.ufs(ADRESSE.sonderlager,"").equals(sonderlager.db_val())) {
					return b_rueck = true;
				}
			}
		}
		return b_rueck;
	}
	
	
	

	/**
	 * 
	 * @param sonderlager    zu pruefendes sonderlager, wenn null, wird gegen alle sonderlager geprueft
	 * @return true, wenn das atom von einem eigenen lager ins sortenwechsellager geht
	 * @throws myException
	 */
	public boolean is_atom_sonderlager_nach_eigenlager(ENUM_SONDERLAGER sonderlager) throws myException {
		boolean b_rueck = false;
		
		RECORD_BEWEGUNG_STATION[] stat = this.get_StationStartEnde();
		
		RECORD_BEWEGUNG_STATION start = stat[0];
		RECORD_BEWEGUNG_STATION ziel = 	stat[1];
		
		RECORD_ADRESSE_extend  a_start = new RECORD_ADRESSE_extend(start.get_UP_RECORD_ADRESSE_id_adresse());
		RECORD_ADRESSE_extend  a_ziel = new RECORD_ADRESSE_extend(ziel.get_UP_RECORD_ADRESSE_id_adresse());
		
		if (sonderlager == null) {
			if (a_start.is_adress_of_mandant() && a_ziel.is_adress_of_mandant()) {
				if (S.isFull(a_start.ufs(ADRESSE.sonderlager,"")) && S.isEmpty(a_ziel.ufs(ADRESSE.sonderlager,""))) {
					return b_rueck = true;
				}
			}
			
		} else {		
			if (a_start.is_adress_of_mandant() && a_ziel.is_adress_of_mandant()) {
				if (a_start.ufs(ADRESSE.sonderlager,"").equals(sonderlager.db_val()) && S.isEmpty(a_ziel.ufs(ADRESSE.sonderlager,""))) {
					return b_rueck = true;
				}
			}
		}
		
		return b_rueck;
	}

	
	
	
	
	/*
	 * pruefmethoden fuer verdeckte aufteilung: nur true, wenn das atom selbst als einziges in einem Vektor vom typ WE_MAIN enthalten ist und wenn alle anderen 
	 * vektorpos in dem zugehoerigen vektor vom typ WE_HIDDEN_SEP sind und 2 atome enthalten
	 */
	public boolean is_atom_WE_MAIN() throws myException {
		boolean b_rueck = true;
		
		RECORD_BEWEGUNG_VEKTOR_POS 	own_vp 	= this.get_UP_RECORD_BEWEGUNG_VEKTOR_POS_id_bewegung_vektor_pos();
		RECORD_BEWEGUNG_VEKTOR 		own_v 	= this.get_UP_RECORD_BEWEGUNG_VEKTOR_id_bewegung_vektor();
		
		//bedingung 1: die eigene vektorpos own_vp darf nur ein atom enthalten
		if (own_vp.get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_bewegung_vektor_pos().size()!=1 || (  !own_vp.ufs(BEWEGUNG_VEKTOR_POS.pos_typ).equals(ENUM_VEKTORPOS_TYP.WE_MAIN.db_val()))) {
			b_rueck = false;
		} else {
			
			//alle anderen bewegungs-vektor-pos eintraege muessen vom type WE_HIDDEN_SEP oder WE_MENGEN_DIFF_LIEFERANT_LAGER sein
			RECLIST_BEWEGUNG_VEKTOR_POS  rl_vp = own_v.get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_POS_id_bewegung_vektor();
			
			for (RECORD_BEWEGUNG_VEKTOR_POS vp: rl_vp) {
				
				if (!vp.ufs(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor_pos).equals(own_vp.ufs(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor_pos))) {
					if (  !(vp.ufs(BEWEGUNG_VEKTOR_POS.pos_typ).equals(ENUM_VEKTORPOS_TYP.WE_HIDDEN_SEP.db_val()) || vp.ufs(BEWEGUNG_VEKTOR_POS.pos_typ).equals(ENUM_VEKTORPOS_TYP.WE_MENGEN_DIFF_LIEFERANT_LAGER.db_val()) )) {
						b_rueck = false;
					} else {
						RECLIST_BEWEGUNG_ATOM  rla = vp.get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_bewegung_vektor_pos();
						if (rla.size()!=2) {
							b_rueck = false;
						} else {
							Vector<RECORD_BEWEGUNG_ATOM> v_a = rla.sort(true, BEWEGUNG_ATOM.posnr);
							if (!(new RECORD_BEWEGUNG_ATOM_ext(v_a.get(0)).is_atom_eigenlager_nach_sonderlager(null))) {
								b_rueck = false;
							} else if (!(new RECORD_BEWEGUNG_ATOM_ext(v_a.get(1)).is_atom_sonderlager_nach_eigenlager(null))) {
								b_rueck = false;
							}
						}
						
					}
				}
			}
		}
		
		return b_rueck;
	}
	
		

	/**
	 * sucht alle Vektor_pos in der umgebung, die als WE_HIDDEN_SEP definiert sind
	 * @return
	 * @throws myException
	 */
	public Vector<RECORD_BEWEGUNG_VEKTOR_POS> vektor_pos_HIDDENSEP() throws myException {
		Vector<RECORD_BEWEGUNG_VEKTOR_POS>  v = new Vector<>(); 
		
		RECORD_BEWEGUNG_VEKTOR_POS 	own_vp 	= this.get_UP_RECORD_BEWEGUNG_VEKTOR_POS_id_bewegung_vektor_pos();
		RECORD_BEWEGUNG_VEKTOR 		own_v 	= this.get_UP_RECORD_BEWEGUNG_VEKTOR_id_bewegung_vektor();
		
		if (this.is_atom_WE_MAIN()) {
		
			Vector<RECORD_BEWEGUNG_VEKTOR_POS>  rl_vp = own_v.get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_POS_id_bewegung_vektor().sort(true, BEWEGUNG_VEKTOR_POS.posnr);
			
			for (RECORD_BEWEGUNG_VEKTOR_POS vp: rl_vp) {
				
				if (!vp.ufs(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor_pos).equals(own_vp.ufs(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor_pos))) {
					if (  vp.ufs(BEWEGUNG_VEKTOR_POS.pos_typ).equals(ENUM_VEKTORPOS_TYP.WE_HIDDEN_SEP.db_val())) {
						v.add(vp);
					}
				}
			}
		}
		return v;
	}
	

	
	
	
	public String _get_anr1_anr2_artbez1() throws myException {
		RECORD_ARTIKEL_BEZ  ab 	= this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez();
		if (ab!=null) {
			RECORD_ARTIKEL      a	= ab.get_UP_RECORD_ARTIKEL_id_artikel();
			if (a!=null) {
				StringBuffer c_rueck = new StringBuffer();
				return c_rueck.append(a.fs(ARTIKEL.anr1,"")).append(" ").append(ab.fs(ARTIKEL_BEZ.anr2)).append(" ").append(a.fs(ARTIKEL.artbez1,"")).toString();
			}
		}
		return "<?>";
	}
	
	public String _get_einheitkurz() throws myException {
		RECORD_ARTIKEL_BEZ  ab 	= this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez();
		if (ab!=null) {
			RECORD_ARTIKEL      a	= ab.get_UP_RECORD_ARTIKEL_id_artikel();
			if (a!=null) {
				RECORD_EINHEIT      eh = a.get_UP_RECORD_EINHEIT_id_einheit();
				if (eh!=null) {
					return eh.fs(EINHEIT.einheitkurz,"");
				}
			}
		} 
		return "<?>";
	}
	

	
	
}
