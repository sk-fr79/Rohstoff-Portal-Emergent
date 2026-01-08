package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES;

import java.util.Vector;

import panter.gmbh.indep.exceptions.myException;

public interface IF_KeyChain {

	public int get_next_number() 	throws myException;
	public IF_KeyChain 				get_father_key();
	
	public Vector<IF_KeyChain>   	get_direct_child_keys();
	
	
	//muss im root-key ueberschrieben werden
	public default boolean is_root() {
		return (this.get_father_key()==null);
	}
	
	//liefert alle keys, die innerhalb der key-chain angelegt wurden
	public default Vector<IF_KeyChain>  get_complete_key_chain() {
		return this.get_root_key().get_all_child_keys();
	}
	
	/**
	 * 
	 * @return den beginn der kette
	 */
	public default IF_KeyChain  get_root_key() {
		IF_KeyChain key = this;
		while (!key.is_root()) {
			key = key.get_father_key();
			if (key.is_root()) {
				break;
			}
		}
		return key;
	}
	
	
	

	
	/**
	 * alle child-keys ab hier 
	 * @return
	 */
	public default Vector<IF_KeyChain> get_all_child_keys() {
		Vector<IF_KeyChain> sammler = new Vector<>();
		
		Vector<IF_KeyChain> v = this.get_direct_child_keys();
		sammler.addAll(v);
		
		while (v.size()>0) {
			Vector<IF_KeyChain> v_innerer_collector = new Vector<>();
			
			for (IF_KeyChain k: v) {
				sammler.addAll(k.get_direct_child_keys());
				v_innerer_collector.addAll(k.get_direct_child_keys());
			}
			
			v = v_innerer_collector;
		}
		return sammler;
	}

	
	
	
	
}
