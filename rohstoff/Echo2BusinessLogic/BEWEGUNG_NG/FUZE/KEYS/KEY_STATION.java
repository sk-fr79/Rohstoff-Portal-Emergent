package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS;

import java.util.Vector;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_KeyChain;


public class KEY_STATION extends RB_KM  implements IF_KeyChain {

	private KEY_ATOM  			father_atom = null;
	private boolean     		is_start = false;
	private Vector<IF_KeyChain>  v_direct_child_keys = new Vector<>();

	
	public KEY_STATION(KEY_ATOM p_father_atom, boolean b_start) throws myException {
		super(_TAB.bewegung_station, _TAB.bewegung_station.n()+"/"+p_father_atom.get_root_key().get_next_number());
		this.father_atom=p_father_atom;
		this.is_start = b_start;
		this.father_atom.get_direct_child_keys().add(this);
	}

	@Override
	public int get_next_number() throws myException {
		return this.get_root_key().get_next_number();
	}


	@Override
	public IF_KeyChain get_father_key() {
		return this.father_atom;
	}

	
	public boolean is_startStation() {
		return is_start;
	}


	@Override
	public Vector<IF_KeyChain> get_direct_child_keys() {
		return this.v_direct_child_keys;
	}


}
