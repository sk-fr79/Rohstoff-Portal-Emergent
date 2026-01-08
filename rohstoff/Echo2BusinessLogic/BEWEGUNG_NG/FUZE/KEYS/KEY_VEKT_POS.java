package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS;

import java.util.Vector;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_KeyChain;

public class KEY_VEKT_POS  extends RB_KM  implements IF_KeyChain, IF_containsPosNr<KEY_VEKT_POS>   {

	public KEY_VEKT  father_vekt = null;
	private Vector<IF_KeyChain>  v_direct_child_keys = new Vector<>();
	private Long f_posNr = null;

	
	public KEY_VEKT_POS(KEY_VEKT p_father_vekt) throws myException {
		super(_TAB.bewegung_vektor_pos,_TAB.bewegung_vektor_pos.n()+"/"+p_father_vekt.get_root_key().get_next_number());
		this.father_vekt = p_father_vekt;
		this.father_vekt.get_direct_child_keys().add(this);
	}

	
	@Override
	public int get_next_number() throws myException {
		return this.get_root_key().get_next_number();
	}


	@Override
	public IF_KeyChain get_father_key() {
		return this.father_vekt;
	}


	public Vector<KEY_ATOM>  get_v_key_atom() {
		Vector<KEY_ATOM> v = new Vector<>();
		for (IF_KeyChain k: this.get_direct_child_keys()) {
			v.add((KEY_ATOM)k);
		}
		return v;
	}

	
	

	@Override
	public Vector<IF_KeyChain> get_direct_child_keys() {
		return this.v_direct_child_keys;
	}

	@Override
	public KEY_VEKT_POS setPosNr(Long posNr) {
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
		return new Rec20(_TAB.bewegung_vektor_pos);
	}

}
