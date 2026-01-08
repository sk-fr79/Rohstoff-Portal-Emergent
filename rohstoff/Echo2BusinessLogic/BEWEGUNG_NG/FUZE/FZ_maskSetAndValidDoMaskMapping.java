package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE;

import java.util.ArrayList;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.Echo2.RB.Mapper.FieldMapperOrSetter;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_MasterKey;

public class FZ_maskSetAndValidDoMaskMapping extends RB_Mask_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, ExecINFO oExecInfo) throws myException {
		FZ_dataObjectsCollector  do_collector = (FZ_dataObjectsCollector) rbMASK.getRbDataObjectActual().rb_get_belongs_to();
		
		IF_MasterKey key = do_collector.get_master_key();
		
		ArrayList<FieldMapperOrSetter> maskMappers = key.getFieldMappersBeforeSaveMask();
		
		MyE2_MessageVector mv = new MyE2_MessageVector();
		for (FieldMapperOrSetter  mapper: maskMappers) {
			mapper.executeMapping((RB_DataobjectsCollector_V2)rbMASK.rb_get_belongs_to().rb_Actual_DataobjectCollector(), mv);
		}
		return mv;
	}

}
