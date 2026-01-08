/**
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.O;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class PdServiceReadFromDBFieldMask {

	
	/**
	 * return s raw value from mask-value
	 * @param map
	 * @param field
	 * @return
	 * @throws myException
	 */
	public Object getActualRawMaskVal(E2_ComponentMAP map,IF_Field field) throws myException {
		if (O.isOneNull(map,field)) {
			throw new myException(this,"Null parameters not allowed !");
		}
		VEK<Component> v = this.identifyComponent(map, field);
		if (v.size()==0) {
			throw new myException(this,"Field: "+field.tnfn()+" was not found in mask !");
		} else if (v.size()>1) {
			throw new myException(this,"Field: "+field.tnfn()+" was found multiple times in mask !");
		} else {
			Component c = v.get(0);
			if (c instanceof MyE2IF__DB_Component) {
				MyMetaFieldDEF mfd = field.generate_MetaFieldDef();
				Object raw = mfd.getRaw(((MyE2IF__DB_Component)c).get_cActualDBValueFormated());
				return raw;
			}
		}
		return null;
	}


	
	
	private VEK<Component> identifyComponent(E2_ComponentMAP map,IF_Field field) throws myException {
		
		VEK<Component> v = new VEK<Component>();
		
		
		E2_vCombinedComponentMAPs mapCollection = map.get_E2_vCombinedComponentMAPs();
		
		if (mapCollection!=null && mapCollection.size()>0) {
			for (E2_ComponentMAP m: mapCollection) {
				if (m.get_oSQLFieldMAP().get_cMAIN_TABLE().toUpperCase().equals(field.fullTableName().toUpperCase())) {
					for (String c: m.get_hmRealComponents().keySet()) {
						if (field.fn().trim().toUpperCase().equals(c.trim().toUpperCase())) {
							v._a((Component)m.get_hmRealComponents().get(c));
						}
					}
				}
			}
		} else {
			if (map.get_oSQLFieldMAP().get_cMAIN_TABLE().toUpperCase().equals(field.fullTableName().toUpperCase())) {
				for (String c: map.get_hmRealComponents().keySet()) {
					if (field.fn().trim().toUpperCase().equals(c.trim().toUpperCase())) {
						v._a((Component)map.get_hmRealComponents().get(c));
					}
				}
			}
		}
		
		return v;
	}
	
	
}
