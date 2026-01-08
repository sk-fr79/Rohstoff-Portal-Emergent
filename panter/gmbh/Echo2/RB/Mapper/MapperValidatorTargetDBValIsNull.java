package panter.gmbh.Echo2.RB.Mapper;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class MapperValidatorTargetDBValIsNull extends MapperValidator {

	
	@Override
	public boolean isMappingValid(RB_DataobjectsCollector_V2 collector, FieldMapperOrSetter mapper, MyE2_MessageVector mv) throws myException {
		RB_KM    targetTable = mapper.getTargetTableKey();
		IF_Field target = mapper.getTargetField();
		
		String rawVal = collector.get_v2(targetTable).get_rec20().getRawSqlFieldTerm4Save(target, mv);
		
		if (rawVal==null) {
			throw new myException(this, mapper.getTargetField().getDebugString()+" has no defined value !!");
		}
		
		if (rawVal.trim().toUpperCase().equals("NULL")) {
			return true;
		}
			
		return false;
	}

}
