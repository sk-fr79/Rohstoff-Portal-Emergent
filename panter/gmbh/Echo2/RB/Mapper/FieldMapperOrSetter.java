package panter.gmbh.Echo2.RB.Mapper;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public abstract class FieldMapperOrSetter {
	
	private RB_KM  				targetTableKey = null;
	private IF_Field   			targetField =  null;

	private boolean   			toSetInNewRecords = 		true;
	private boolean   			toSetInExistingRecords = 	true;

	//ein validierungsobject, kann belibige bedingungen fuer ein mapping hinterlegen
	private MapperValidator  validator = new MapperValidatorAlways();
	
	
	public abstract void    	executeMapping(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv) throws myException;
	

	/**
	 * 
	 * @param p_tableSourceKey
	 * @param p_sourceField
	 * @param p_tableTargetKey
	 * @param p_targetField
	 */
	public FieldMapperOrSetter(	RB_KM 		p_tableTargetKey, 
								IF_Field 	p_targetField) {
		super();
		this.targetTableKey = 			p_tableTargetKey;
		this.targetField = 				p_targetField;
	}

	
	
	public RB_KM getTargetTableKey() {
		return targetTableKey;
	}

	public IF_Field getTargetField() {
		return targetField;
	}

	public boolean isToSetInNewRecords() {
		return toSetInNewRecords;
	}

	public boolean isToSetInExistingRecords() {
		return toSetInExistingRecords;
	}
	
	public FieldMapperOrSetter _setValidator(MapperValidator p_validator) {
		this.validator = p_validator;
		return this;
	}

	public MapperValidator getValidator() {
		return validator;
	}



	public FieldMapperOrSetter _setInNewRecords(boolean p_toSetInNewRecords) {
		this.toSetInNewRecords = p_toSetInNewRecords;
		return this;
	}


	public FieldMapperOrSetter _setInExistingRecords(boolean p_toSetInExistingRecords) {
		this.toSetInExistingRecords = p_toSetInExistingRecords;
		return this;
	}
	
	
}
