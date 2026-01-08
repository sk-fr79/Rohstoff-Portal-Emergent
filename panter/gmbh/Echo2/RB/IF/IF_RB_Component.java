package panter.gmbh.Echo2.RB.IF;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Label;
import nextapp.echo2.app.TextArea;
import nextapp.echo2.app.TextField;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;


/**
 * interface zur erweiterung der MyE2IF_Components mit den methoden, 
 * die benoetigt werden, um im Datenbank-Ablauf der RECORD-Basierten Masken zu funktionieren
 * @author martin
 *
 */
public interface IF_RB_Component extends MyE2IF__Component, IF_Formatter, IF_RB_Part<RB_ComponentMap> {

	public void 	 						rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException;
	
	public void 	 						rb_set_db_value_manual(String valueFormated) throws myException;
	
	public RB_KF      						rb_KF() throws myException;
	public void      						set_rb_RB_K(RB_KF key) throws myException;
	
//	public RB_ComponentMap                  rb_ComponentMap_this_belongsTo() throws myException;
	
	
	public Vector<RB_Validator_Component>  	rb_VALIDATORS_4_INPUT() throws myException;
	
	
	//default-methoden zu suche der zugehoerigen container
	public default RB_ComponentMap  _find_componentMap_i_belong_to() throws myException {
		RB_ComponentMap  map = ((IF_RB_Component)this).rb_ComponentMap_this_belongsTo();
		if (map != null) {
			return map;
		}
		throw new myException("Error finding my own RB_ComponentMap");
	}

	public default RB_ComponentMapCollector  _find_componentMapCollector_i_belong_to() throws myException {
		if (this instanceof IF_RB_Component) {
			RB_ComponentMap  map = ((IF_RB_Component)this).rb_ComponentMap_this_belongsTo();
			if (map != null) {
				RB_ComponentMapCollector col = map.rb_get_belongs_to();
				if (col != null) {
					return col;
				}
			}
		}
		throw new myException("Error finding my own RB_ComponentMapCollector");
	}

	
	public default IF_Container4Visualisation  _find_container4visualisation_i_belong_to() throws myException {
		if (this instanceof IF_RB_Component) {
			RB_ComponentMap  map = ((IF_RB_Component)this).rb_ComponentMap_this_belongsTo();
			if (map != null) {
				RB_ComponentMapCollector col = map.rb_get_belongs_to();
				if (col != null) {
					IF_Container4Visualisation cont = col.rb_get_belongs_to();
					if (cont!=null) {
						return cont;
					}
				}
			}
		}
		throw new myException("Error finding my own IF_Container4Visualisation");
	}

	
	

	
	
	/**
	 * findet in der componentmap-struktur alle komponenten, die mit einem feldnamen registriert sind
	 * @param field_key
	 * @return
	 * @throws myException
	 */
	public default HashMap<RB_KM,IF_RB_Component>  _find_components_in_neighborhood(RB_KF field_key) throws myException {
		HashMap<RB_KM,IF_RB_Component> v_c = new HashMap<>();
		RB_ComponentMap map = this._find_componentMap_i_belong_to();

		if (map != null) {
			IF_RB_Component c = map.getIfRbComp(field_key);
			if (c!=null) {
				v_c.put(map.getOwnMaskKey(),c);
			}
			
			//jetzt die anderen componentmaps
			RB_ComponentMapCollector map_c = map.rb_get_belongs_to();
			
			for (RB_ComponentMap m: map_c) {
				IF_RB_Component c1 = m.getIfRbComp(field_key);
				if (c1!=null) {
					v_c.put(m.getOwnMaskKey(),c1);
				}
			}
		}
		return v_c;
	}
	
	/**
	 * findet in der componentmap-struktur alle komponenten, die mit einem feldnamen registriert sind
	 * @param field
	 * @return
	 * @throws myException
	 */
	public default HashMap<RB_KM,IF_RB_Component>  _find_components_in_neighborhood(IF_Field field) throws myException {
		return this._find_components_in_neighborhood(field.fk());
	}

	
	/**
	 * findet, falls es ein eindeutiges feld des namen im Umfeld gibt, dieses ansonsten null (auch bei mehrdeutigkeit)
	 * @param field
	 * @return
	 * @throws myException
	 */
	public default IF_RB_Component  _find_component_in_neighborhood(RB_KF field) throws myException {
		HashMap<RB_KM,IF_RB_Component> hm = this._find_components_in_neighborhood(field);
		
		if (hm.values().size()==1) {
			return new Vector<IF_RB_Component>(hm.values()).get(0);
		}
		
		return null;
	}

	/**
	 * findet, falls es ein eindeutiges feld des namen im Umfeld gibt, dieses ansonsten null (auch bei mehrdeutigkeit)
	 * @param field
	 * @return
	 * @throws myException
	 */
	public default IF_RB_Component  _find_component_in_neighborhood(IF_Field field) throws myException {
		return this._find_component_in_neighborhood(field.fk());
	}
	
	
	
	/**
	 * findet, feld aus der maskenCollection
	 * @param mask
	 * @param field
	 * @return
	 * @throws myException
	 */
	public default IF_RB_Component  _find_component_in_neighborhood(RB_KM mask, RB_KF field) throws myException {
		HashMap<RB_KM,IF_RB_Component> hm = this._find_components_in_neighborhood(field);
		
		return hm.get(mask);
	}



	public default void rb_set_belongs_to(RB_ComponentMap map) throws myException {
		((MyE2IF__Component)this).EXT().rb_set_belongs_to(map);
	}
	
	
	public default RB_ComponentMap rb_get_belongs_to() throws myException {
		return ((MyE2IF__Component)this).EXT().rb_get_belongs_to();
	}
	
	public default RB_ComponentMap rb_ComponentMap_this_belongsTo() throws myException {
		return (RB_ComponentMap)this.EXT().get_oComponentMAP();
	}


	
	/**
	 * 
	 * @return key, under which this component is registered
	 * @throws myException
	 */
	public default RB_K   getMyKeyInCollection() throws myException {
		return ((MyE2IF__Component)this).EXT().get_my_key_in_collection();
	}
	public default RB_K   setMyKeyInCollection(RB_K key) throws myException {
		((MyE2IF__Component)this).EXT().set_my_key_in_collection(key);
		return key;
	}

	
	
	/**
	 * eine default-methode, um den aktuellen maskenwert auch von nicht-db-componenten auszulesen
	 * falls es generisch nicht moeglich ist, dann muss die einzelne komponenten diesen wert ueberschreiben
	 */
	public default String  get__actual_maskstring_in_view() throws myException {
		String c_ret = null;
		if (this instanceof TextField) {
			c_ret = ((TextField)this).getText();
		} else if (this instanceof TextArea) {
			c_ret = ((TextArea)this).getText();
	    } else if (this instanceof Label) {
			c_ret = ((Label)this).getText();
		} else if (this instanceof IF_RB_Component_Savable) {
			c_ret = ((IF_RB_Component_Savable)this).rb_readValue_4_dataobject();
		} else  {
			throw new myException("Please implement the method ...(IF_RB_Component.get__actual_in_view()) on class ..."+this.getClass().getName()); 
		}
		return c_ret;
	}
	
	
	/**
	 * 2018-01-26: martin
	 * @param partOfMask
	 * @return
	 * @throws myException
	 */
	public default IF_RB_Component _setRegisteredToMask(boolean partOfMask) throws myException {
		this.EXT().setRegisteredToMask(partOfMask);
		return this;
	}
	
	/**
	 * 2018-01-26: martin
	 * @return
	 */
	public default boolean isRegisteredToMask() {
		return this.EXT().isRegisteredToMask();
	}
	
	
}
