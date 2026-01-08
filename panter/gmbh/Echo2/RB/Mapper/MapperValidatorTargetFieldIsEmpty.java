package panter.gmbh.Echo2.RB.Mapper;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class MapperValidatorTargetFieldIsEmpty extends MapperValidator {


	public MapperValidatorTargetFieldIsEmpty() {
		super();
	}

	
	@Override
	public boolean isMappingValid(RB_DataobjectsCollector_V2 collector, FieldMapperOrSetter mapper, MyE2_MessageVector mv) throws myException {
		    
		RB_MaskController  controller = 			new RB_MaskController(collector.rb_ComponentMapCollector_ThisBelongsTo());
		
		IF_RB_Component    rb_comp = controller.getComponent(mapper.getTargetTableKey(), mapper.getTargetField());
		
		if (rb_comp instanceof IF_RB_Component_Savable) {
			String newValTarget = controller.get_liveVal(mapper.getTargetTableKey(), mapper.getTargetField());
			if (S.isEmpty(newValTarget)) {
				return true;
			}
		} else {
			throw new myException(this, "Only saveables can be mapped ...");
		}
		
		return false;
	}




}
