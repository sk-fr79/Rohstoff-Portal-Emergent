package panter.gmbh.Echo2.RB.Mapper;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class FieldMapperRecToRec extends FieldMapperOrSetter {


	private RB_KM  				sourceTableKey = null;
	private IF_Field   			sourceField =  null;


	//ein validierungsobject, kann belibige bedingungen fuer ein mapping hinterlegen
	private MapperValidator  validator = new MapperValidatorAlways();
	

	/**
	 * 
	 * @param p_tableSourceKey
	 * @param p_sourceField
	 * @param p_tableTargetKey
	 * @param p_targetField
	 */
	public FieldMapperRecToRec(	RB_KM 		p_tableSourceKey, 	
									IF_Field 	p_sourceField, 
									RB_KM 		p_tableTargetKey, 
									IF_Field 	p_targetField) {
		super(p_tableTargetKey, p_targetField);
		this.sourceTableKey = 			p_tableSourceKey;
		this.sourceField = 				p_sourceField;
	}

	
	
	
	/**
	 * 
	 * @param p_tableSourceKey
	 * @param p_sourceField
	 * @param p_tableTargetKey
	 * @param p_targetField
	 * @param p_toSetInNewRecords
	 * @param p_toSetInExistingRecords
	 */
	public FieldMapperRecToRec(	RB_KM 		p_tableSourceKey, 	
									IF_Field 	p_sourceField, 
									RB_KM 		p_tableTargetKey, 
									IF_Field 	p_targetField,  
									boolean 	p_toSetInNewRecords , 
									boolean 	p_toSetInExistingRecords) {
		super(p_tableTargetKey, p_targetField);
		this.sourceTableKey = 			p_tableSourceKey;
		this.sourceField = 				p_sourceField;
		this._setInNewRecords(p_toSetInNewRecords)._setInExistingRecords(p_toSetInExistingRecords);
	}

	
	

	public RB_KM getSourceTableKey() {
		return sourceTableKey;
	}

	public IF_Field getSourceField() {
		return sourceField;
	}

	
	public FieldMapperRecToRec _setValidator(MapperValidator p_validator) {
		this.validator = p_validator;
		return this;
	}



	public MapperValidator getValidator() {
		return validator;
	}



	@Override
	public void executeMapping(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv) throws myException {

		Rec20 recSource =  do_collector.get(this.getSourceTableKey()).rec20();
		Rec20 recTarget =  do_collector.get(this.getTargetTableKey()).rec20();
		
		if ( (recTarget.is_newRecordSet()&& this.isToSetInNewRecords()) || (recTarget.is_ExistingRecord()&& this.isToSetInExistingRecords()))  { 
		
			if (this.getValidator().isMappingValid(do_collector, this, mv)) {
				recTarget.getRec20LastRead()._nv(this.getTargetField(),recSource.getRec20LastRead().get_fs_lastVal(this.getSourceField()), mv);
			}
		}
	}
	
	
}
