package panter.gmbh.Echo2.RB.BASICS;

import panter.gmbh.Echo2.RB.IF.IF_RB_Part;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class RB_ForcedDBValue  implements IF_RB_Part<RB_PreSettingsContainer> {

	private RB_PreSettingsContainer   	rb_SurfaceSettingsContainerThisBelongsTo = null;


	private IF_Field                        field = null;
	private String   						formated_value_forced_at_save = null;
	
	//schluessel unter dem das objekt bei der uebergeordneten collection registriert ist
	private RB_K  										my_key_in_collection = null;
	
	
	public RB_K  getMyKeyInCollection() throws myException {
		return this.my_key_in_collection;
	}
	
	public RB_K setMyKeyInCollection(RB_K key) throws myException {
		this.my_key_in_collection = key;
		return key;
	}


	
	public RB_ForcedDBValue() {
		super();
	
	}
	
	
	public IF_Field rb_get_field() {
		return field;
	}


	public void rb_set_field(IF_Field p_field) {
		this.field = p_field;
	}


	public String rb_get_formated_value_forced_at_save() {
		return formated_value_forced_at_save;
	}


	public void rb_set_formated_value_forced_at_save(String p_formated_value_forced_at_save) {
		this.formated_value_forced_at_save = p_formated_value_forced_at_save;
	}

	
	
	@Override
	public void rb_set_belongs_to(RB_PreSettingsContainer surfaceSettingsContainer) throws myException {
		this.rb_SurfaceSettingsContainerThisBelongsTo=surfaceSettingsContainer;
	}


	@Override
	public RB_PreSettingsContainer rb_get_belongs_to() throws myException {
		return this.rb_SurfaceSettingsContainerThisBelongsTo;
	}


	
	
}
