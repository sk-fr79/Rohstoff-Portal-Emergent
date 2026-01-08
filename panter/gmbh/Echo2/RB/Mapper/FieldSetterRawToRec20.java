package panter.gmbh.Echo2.RB.Mapper;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class FieldSetterRawToRec20 extends FieldMapperOrSetter {

	private String   			dbValueRaw = null;
	
	
	/**
	 * 
	 * @param p_dbValueRawFormat
	 * @param p_tableTargetKey
	 * @param p_targetField
	 * @param p_toSetInNewRecords
	 * @param p_toSetInExistingRecords
	 */
	public FieldSetterRawToRec20(	String 		p_dbValueRawFormat, 
								RB_KM 		p_tableTargetKey, 
								IF_Field 	p_targetField, 
								boolean 	p_toSetInNewRecords , 
								boolean 	p_toSetInExistingRecords) {
		super(p_tableTargetKey, p_targetField);
		this.dbValueRaw = p_dbValueRawFormat;
		this._setInNewRecords(p_toSetInNewRecords)._setInExistingRecords(p_toSetInExistingRecords);
	}

	
	public String getDbValueRawFormat() {
		return dbValueRaw;
	}

	
	@Override
	public void executeMapping(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv) throws myException {
		Rec20 recTarget =  do_collector.get(this.getTargetTableKey()).rec20();
		
		if ( (recTarget.is_newRecordSet()&& this.isToSetInNewRecords()) || (recTarget.is_ExistingRecord()&& this.isToSetInExistingRecords()))  { 
			if (this.getValidator().isMappingValid(do_collector, this, mv)) {
				recTarget.getRec20LastRead()._add_field_val_pair(this.getTargetField(), this.getDbValueRawFormat());
			}
		}
	}

	
}
