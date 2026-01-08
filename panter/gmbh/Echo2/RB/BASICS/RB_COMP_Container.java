package panter.gmbh.Echo2.RB.BASICS;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;

/**
 * container-klasse, die innerhalb einer RB_Mask (ex-E2_Component-Map) noch zusaetzlich als Hashmap<RB_KF,MyE2IF_ComponentMap>
 * @author martin
 *
 */
public class RB_COMP_Container implements Iterable<MyE2IF__Component>{
	private HashMap<RB_KF, MyE2IF__Component>  hmComponents = new HashMap<RB_KF, MyE2IF__Component>();

	public RB_COMP_Container() {
		super();
	}
	
	public MyE2IF__Component put(RB_KF key, MyE2IF__Component comp) {
		return this.hmComponents.put(key, comp);
	}
	
	public MyE2IF__Component get(RB_KF key) {
		return this.hmComponents.get(key);
	}

	public IF_RB_Component get_(RB_KF key) {
		if (this.hmComponents.get(key)!=null) {
			if (this.hmComponents.get(key) instanceof IF_RB_Component) {
				return (IF_RB_Component)this.hmComponents.get(key);
			}
		}
		return null;
	}

	
	@Override
	public Iterator<MyE2IF__Component> iterator() {
		return this.hmComponents.values().iterator();
	}

	
	public Set<RB_KF> keySet() {
		return this.hmComponents.keySet();
	}
	
	public Collection<MyE2IF__Component> values() {
		return this.hmComponents.values();
	}

}
