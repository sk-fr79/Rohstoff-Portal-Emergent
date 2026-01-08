package panter.gmbh.Echo2.RB.BASICS;

import java.util.Vector;

import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.indep.exceptions.myException;

public class RB__TOOLS {
	
	
	
	/**
	 * suchfunktion innerhalb eines componentMapCollector oder der eigenen componentmap
	 * @param me
	 * @param key_of_neighbor
	 * @param key_of_mask_neighbor
	 * @return
	 * @throws myException
	 */
	public static IF_RB_Component find_neighbor(IF_RB_Component me, RB_KF key_of_neighbor, RB_KM key_of_mask_neighbor) throws myException {
		IF_RB_Component c_rueck = null;

		//fehler ausschliessen
		if (me==null || key_of_neighbor == null) {
			throw new myException( "RB__CONST.find_neighbor(): Own Component and Key of neighbor MUST no be null !");
		}
		
		//zuerst die eigene map
		RB_ComponentMap ownMap = me.rb_ComponentMap_this_belongsTo();
		
		//dann die suchmap
		RB_ComponentMap  mapToSearch = ownMap;
		
		if (key_of_mask_neighbor!=null) {
			mapToSearch = ownMap.rb_get_belongs_to().get(key_of_mask_neighbor);
			if (mapToSearch==null) {
				return null;              //fehler
			}
		}
		
		c_rueck = mapToSearch.getRbComponent(key_of_neighbor);
		return c_rueck;
	}

	
	/**
	 * suchfunktion innerhalb eines componentMapCollector oder der eigenen componentmap
	 * @param me
	 * @param key_of_neighbor
	 * @param key_of_mask_neighbor
	 * @return
	 * @throws myException
	 */
	public static IF_RB_Component find_comp(RB_ComponentMap  map, RB_KF key_of_comp) throws myException {
		IF_RB_Component c_rueck = null;

		Vector<IF_RB_Component> v_sammler = new Vector<>();
		
		//zuerst die eigene map
		RB_ComponentMapCollector ownCol = map.rb_get_belongs_to();
		
		for (RB_ComponentMap m: ownCol) {
			if (m.getRbComponent(key_of_comp)!=null) {
				v_sammler.add(m.getRbComponent(key_of_comp));
			}
		}
		
		if (v_sammler.size()==1) {
			c_rueck = v_sammler.get(0);
		}  else {
			if (v_sammler.size()==0) {
				throw new myException("Component not found !");
			} else {
				throw new myException("No singular component found !");
			}
		}
		
		return c_rueck;
	}

	
}
