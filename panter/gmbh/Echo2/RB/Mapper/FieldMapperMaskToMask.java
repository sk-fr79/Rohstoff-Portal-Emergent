package panter.gmbh.Echo2.RB.Mapper;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class FieldMapperMaskToMask extends FieldMapperOrSetter {


	private RB_KM  				sourceTableKey = null;
	private IF_Field   			sourceField =  null;

	private boolean   			toSetInNewRecords = 		true;
	private boolean   			toSetInExistingRecords = 	true;

	//ein validierungsobject, kann belibige bedingungen fuer ein mapping hinterlegen
	private MapperValidator  validator = new MapperValidatorAlways();
	

	/**
	 * 
	 * @param p_tableSourceKey
	 * @param p_sourceField
	 * @param p_tableTargetKey
	 * @param p_targetField
	 */
	public FieldMapperMaskToMask(	RB_KM 		p_tableSourceKey, 	
										IF_Field 	p_sourceField, 
										RB_KM 		p_tableTargetKey, 
										IF_Field 	p_targetField) {
		super(p_tableTargetKey, p_targetField);
		this.sourceTableKey = 			p_tableSourceKey;
		this.sourceField = 				p_sourceField;
	}

	
	
	
	public boolean isToSetInNewRecords() {
		return toSetInNewRecords;
	}

	public boolean isToSetInExistingRecords() {
		return toSetInExistingRecords;
	}

	public RB_KM getSourceTableKey() {
		return sourceTableKey;
	}

	public IF_Field getSourceField() {
		return sourceField;
	}

	
	public FieldMapperMaskToMask _setValidator(MapperValidator p_validator) {
		this.validator = p_validator;
		return this;
	}



	public MapperValidator getValidator() {
		return validator;
	}



	@Override
	public void executeMapping(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv) throws myException {
		Rec20 rec =  do_collector.get(this.getTargetTableKey()).rec20();
		if ( (rec.is_newRecordSet()&& this.isToSetInNewRecords()) || (rec.is_ExistingRecord()&& this.isToSetInExistingRecords()))  { 
		
			if (this.getValidator().isMappingValid(do_collector, this, mv)) {
				RB_MaskController  controller = new RB_MaskController(do_collector.rb_ComponentMapCollector_ThisBelongsTo());
				String c_new_val_source = controller.get_liveVal(this.getSourceTableKey(), this.getSourceField());
				controller.set_maskVal(this.getTargetTableKey(), this.getTargetField(), c_new_val_source, mv);
			}
		}

		
	}
	
	
}
