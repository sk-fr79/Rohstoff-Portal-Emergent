package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS;

import java.util.Vector;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_KeyChain;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_MasterKey;


public class KEY_VEKT extends RB_KM  implements IF_KeyChain, IF_containsPosNr<KEY_VEKT>  {

	private IF_MasterKey   			father_bewegung = null;
	private Vector<IF_KeyChain>  	v_direct_child_keys = new Vector<>();
	private Long f_posNr = null;

	
	public KEY_VEKT(IF_MasterKey  p_father_bewegung) throws myException {
		super(_TAB.bewegung_vektor,_TAB.bewegung_vektor.n()+"/"+p_father_bewegung.get_next_number());
		this.father_bewegung = p_father_bewegung;
		this.father_bewegung.get_direct_child_keys().add(this);
	}

	
	@Override
	public int get_next_number() throws myException {
		return this.get_root_key().get_next_number();
	}


	@Override
	public IF_KeyChain get_father_key() {
		return this.father_bewegung;
	}


	public Vector<KEY_VEKT_POS>  get_v_key_vektorpos() {
		Vector<KEY_VEKT_POS> v = new Vector<>();
		for (IF_KeyChain k: this.get_direct_child_keys()) {
			v.add((KEY_VEKT_POS)k);
		}
		return v;
	}
	

	@Override
	public Vector<IF_KeyChain> get_direct_child_keys() {
		return this.v_direct_child_keys;
	}
	
	@Override
	public KEY_VEKT setPosNr(Long posNr) {
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
		return new Rec20(_TAB.bewegung_vektor);
	}

}
