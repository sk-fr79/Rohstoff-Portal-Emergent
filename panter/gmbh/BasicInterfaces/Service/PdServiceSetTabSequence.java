/**
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import java.util.HashMap;

import nextapp.echo2.app.Component;
import panter.gmbh.BasicInterfaces.IF_FocusTraversalIndexSetter;
import panter.gmbh.BasicInterfaces.IF__Reflections;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class PdServiceSetTabSequence implements IF__Reflections {

	private VEK<E2_ComponentMAP>  hmComponentMaps = new VEK<E2_ComponentMAP>();
	
	public PdServiceSetTabSequence() {
		super();
	}


	public PdServiceSetTabSequence _addMap(E2_ComponentMAP map) {
		this.hmComponentMaps._a(map);
		return this;
	}


	public PdServiceSetTabSequence _setSeq(Integer iNext, Object... fields) throws myException {
		
		for (Object o_field: fields) {
		
			VEK<Component> v = new VEK<>(); 
			String name = null;
			if (o_field instanceof String) { 
				name = (String) o_field;
				v._a(this.identifyComponent((String) o_field));
			} else if (o_field instanceof IF_Field) {
				name = ((IF_Field)o_field).fn();
				v._a(this.identifyComponent((IF_Field)o_field));
			} else {
				throw new myException("Types of paramterlist can only be IF_Field or String");
			}
			
			if (v.size()==0) {
				throw new myException(this, "Component with key: "+name+" was not found !");
			} else if (v.size()>1) {
				throw new myException(this, "Component with key: "+name+" was multiple found !");
			} else {
				Component c = v.get(0);

				c.setFocusTraversalParticipant(true);
				if (c instanceof IF_FocusTraversalIndexSetter) {
					iNext = ((IF_FocusTraversalIndexSetter)c).setFocusTraversalIndexInSubComponents(iNext);
				} else {
					c.setFocusTraversalIndex(iNext);
					iNext = iNext+1;
				}
			}
			
		}
		return this;
	}

	
	
	private VEK<Component> identifyComponent(String field) throws myException {
		
		VEK<Component> v = new VEK<Component>();
		
		for (HashMap<String,MyE2IF__Component> m: hmComponentMaps) {
			for (String c: m.keySet()) {
				if (field.trim().toUpperCase().equals(c.trim().toUpperCase())) {
					v._a((Component)m.get(c));
				}
			}
		}
		
		return v;
	}
	
	
	
	private VEK<Component> identifyComponent(IF_Field field) throws myException {
		VEK<Component> v = new VEK<Component>();
		
		for (E2_ComponentMAP m: this.hmComponentMaps) {
			if (m instanceof RB_ComponentMap) {
				RB_ComponentMap mr = (RB_ComponentMap)m;
				String tableName = mr.rb_TABLENAME(); 
				if (tableName.trim().toUpperCase().equals(field.fullTableName().trim().toUpperCase())) {
					if (mr.get__CompEcho(field)!=null) {
						v._a(mr.get__CompEcho(field));
					}
				}
			} else {
				if (m.get_oSQLFieldMAP()!=null && S.NN(m.get_oSQLFieldMAP().get_cMAIN_TABLE()).trim().toUpperCase().equals(field.fullTableName().trim().toUpperCase())) {
					if (m.get__CompEcho(field)!=null) {
						v._a(m.get__CompEcho(field));
					}
				}
			}
		}
		
		return v;
	}
	
	
}
