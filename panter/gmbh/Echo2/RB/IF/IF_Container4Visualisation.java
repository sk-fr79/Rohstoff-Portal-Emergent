package panter.gmbh.Echo2.RB.IF;

import java.util.HashMap;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.indep.exceptions.myException;

/**
 * definition eines containers, der RB-definierte Maskenteile beinhaltet (z.b. RB_ModuleContainerMASK)
 * @author martin
 *
 */
public interface IF_Container4Visualisation {

	public E2_MODULNAME_ENUM.MODUL 	rb_get_OwnKENNER();
	public void 					rb_set_OwnKENNER(E2_MODULNAME_ENUM.MODUL kenner);
    
	public HashMap<RB_K, RB_ComponentMapCollector> 	rb_hm_component_map_collector();
	public RB_ComponentMapCollector 				rb_ComponentMapCollector(RB_K registrationKey);
	public RB_ComponentMapCollector 				rb_FirstAndOnlyComponentMapCollector() throws myException;
	public RB_ComponentMapCollector 				rb_FirstComponentMapCollector() throws myException;
	
}
