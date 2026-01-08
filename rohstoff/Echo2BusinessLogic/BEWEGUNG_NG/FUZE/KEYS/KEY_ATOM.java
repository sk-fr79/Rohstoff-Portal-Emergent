package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS;

import java.util.Vector;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_KeyChain;


public class KEY_ATOM extends RB_KM implements IF_KeyChain, IF_containsPosNr<KEY_ATOM> {

	private KEY_VEKT_POS  father_vektor_pos = null;
	
	private Vector<IF_KeyChain>  v_direct_child_keys = new Vector<>();
	
	private Long f_posNr = null;

	
	public KEY_ATOM(KEY_VEKT_POS  p_father_vektor_pos) throws myException {
		super(_TAB.bewegung_atom,_TAB.bewegung_atom+"/"+p_father_vektor_pos.get_next_number());
		this.father_vektor_pos = p_father_vektor_pos;
		this.father_vektor_pos.get_direct_child_keys().add(this);
	}


	
	public Vector<KEY_STATION>  get_v_key_station() {
		Vector<KEY_STATION> v = new Vector<>();
		KEY_STATION  s1 = (KEY_STATION)this.v_direct_child_keys.get(0);
		KEY_STATION  s2 = (KEY_STATION)this.v_direct_child_keys.get(1);
		if (s1.is_startStation()) {
			v.add(s1);
			v.add(s2);
		} else {
			v.add(s2);
			v.add(s1);
		}
		return v;
	}
	

	

	@Override
	public int get_next_number() throws myException {
		return this.get_root_key().get_next_number();
	}


	@Override
	public IF_KeyChain get_father_key() {
		return this.father_vektor_pos;
	}

	public KEY_STATION  startstation() {
		Vector<IF_KeyChain>  v1 = this.v_direct_child_keys;
		
		for (IF_KeyChain k: v1) {
			if (( (KEY_STATION)k).is_startStation()) {
				return (KEY_STATION)k;
			}
		}
		return null;
	}

	public KEY_STATION  zielstation() {
		Vector<IF_KeyChain>  v1 = this.v_direct_child_keys;
		
		for (IF_KeyChain k: v1) {
			if ((!( (KEY_STATION)k).is_startStation())) {
				return (KEY_STATION)k;
			}
		}
		return null;
	}



	@Override
	public Vector<IF_KeyChain> get_direct_child_keys() {
		return this.v_direct_child_keys;
	}



	@Override
	public KEY_ATOM setPosNr(Long posNr) {
		this.f_posNr=posNr;
		return this;
	}



	@Override
	public Long getPosNr() {
		return this.f_posNr;
	}



	/**
	 * returns new, empty record of correct type 
	 */
	@Override
	public Rec20 getRec20() throws myException {
		return new Rec20(_TAB.bewegung_atom);
	}

	
}
