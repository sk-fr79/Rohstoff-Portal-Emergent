package panter.gmbh.Echo2.RB.Mapper;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.indep.exceptions.myException;

public abstract class MapperValidator {

	/**
	 * 
	 * @param collector 
	 * @param mapper
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public abstract boolean isMappingValid(RB_DataobjectsCollector_V2 collector, FieldMapperOrSetter mapper, MyE2_MessageVector mv) throws myException;
		
	
}
