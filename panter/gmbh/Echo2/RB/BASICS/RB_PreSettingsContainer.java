package panter.gmbh.Echo2.RB.BASICS;

import java.util.LinkedHashMap;
import java.util.Set;

import panter.gmbh.Echo2.RB.BASICS.RB__CONST.ALIGN_HORIZONTAL;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.ALIGN_VERTICAL;
import panter.gmbh.Echo2.RB.IF.IF_RB_Collector;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

/**
 * container-klasse fuer   LinkedHashMap<RB_KF,RB_SurfaceSettings> 
 * @author martin
 *
 */
public class RB_PreSettingsContainer implements IF_RB_Collector<RB_SurfaceSettings>{
	
	private LinkedHashMap<RB_KF,RB_SurfaceSettings> 		hmSurfaceSettings = new LinkedHashMap<RB_KF, RB_SurfaceSettings>();
	
	/*
	 *2015-11-26: definiert forcierte speicherwerte fuer einzelne spalten in der tabelle, wird am ende der übergabe mask-to-daten eingeschleust 
	 */
	private LinkedHashMap<IF_Field,RB_ForcedDBValue> 		hmForcedDBValue = new LinkedHashMap<IF_Field, RB_ForcedDBValue>();
	

	public RB_PreSettingsContainer() {
		super();
	}

	@Override
	public RB_SurfaceSettings registerComponent(RB_K keyfield, RB_SurfaceSettings settings) throws myException {
		if (keyfield instanceof RB_KF) {
			this.hmSurfaceSettings.put((RB_KF)keyfield, settings);
			settings.rb_set_belongs_to(this);
			
			//2016-07-19: einheitliche registrierung
			settings.setMyKeyInCollection(keyfield);

			
			return settings;
		} else {
			throw new myException(this, "Key MUST be of Type: RB_KF");
		}
	} 


	public RB_ForcedDBValue  rb_register(IF_Field field, RB_ForcedDBValue value) {
		this.hmForcedDBValue.put(field, value);
		return value;
	}
	
	
	
	 
	public RB_SurfaceSettings rb_get(RB_KF keyField) {
		return this.hmSurfaceSettings.get(keyField);
	}
	

	public RB_SurfaceSettings rb_get(IF_Field field) throws myException {
		return this.hmSurfaceSettings.get(field.fk());
	}
	

	public RB_ForcedDBValue  rb_get_forcedDB(IF_Field keyField) {
		return this.hmForcedDBValue.get(keyField);
	}


	public void rb_clearMap() {
		this.hmSurfaceSettings.clear();
	}
	
	
	public Set<RB_KF>  rb_get_keySet() {
		return this.hmSurfaceSettings.keySet();
	}

	/**
	 * 
	 * @return hashmap with all SufaceSettings
	 */
	public LinkedHashMap<RB_KF, RB_SurfaceSettings> rb_get_hmSurfaceSettings() {
		return hmSurfaceSettings;
	}
	
	
	
	/*
	 * 2016-04-11: neue vereinfachte definitionsmethoden
	 */
	
	
	/**
	 * 
	 * @param field
	 * @param maskValue
	 * @throws myException
	 */
	public void rb_set_defaultMaskValue(IF_Field field, String maskValue) throws myException {
		this.rb_get(field.fk()).set_rb_Default(maskValue);
	}
	

	/**
	 * 
	 * @param field
	 * @param enabled
	 * @throws myException
	 */
	public void rb_set_enabled(IF_Field field, boolean enabled) throws myException {
		this.rb_get(field.fk()).set_Enabled(enabled);
	}

	
	/**
	 * 
	 * @param field
	 * @param mustBeFilled
	 * @throws myException
	 */
	public void rb_set_mandatoryField(IF_Field field, boolean mustBeFilled) throws myException {
		this.rb_get(field.fk()).set_MustField(mustBeFilled);
	}

	
	/**
	 * 
	 * @param field
	 * @param alignHorizontal
	 * @throws myException
	 */
	public void rb_set_horizontalAlignment(IF_Field field, ALIGN_HORIZONTAL alignHorizontal) throws myException {
		this.rb_get(field.fk()).set_AlignHorizontal(alignHorizontal);
	}
	
	
	/**
	 * 
	 * @param field
	 * @param alignVertical
	 * @throws myException
	 */
	public void rb_set_verticalAlignment(IF_Field field, ALIGN_VERTICAL alignVertical) throws myException {
		this.rb_get(field.fk()).set_AlignVertical(alignVertical);
	}
	
	
	/**
	 * 
	 * @param field
	 * @param formatedDBValue
	 * @throws myException
	 */
	public void rb_set_forcedValueAtSave(IF_Field field, String formatedDBValue) throws myException {
		this.hmForcedDBValue.get(field).rb_set_formated_value_forced_at_save(formatedDBValue);
	}
	
}
