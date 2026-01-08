package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE;

import java.util.ArrayList;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.Mapper.FieldMapperOrSetter;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_MasterKey;

public class FZ_dataObjectsCollector_doBeforeSave_FieldSettingFromMasterkey implements FZ_dataObjectsCollector_doBeforeSave{

	@Override
	public void manipulateRecordsBeforeSave(FZ_dataObjectsCollector do_collector, MyE2_MessageVector mv) throws myException {

		//als erstes den Masterkey besorgen
		IF_MasterKey key = do_collector.get_master_key();

		ArrayList<FieldMapperOrSetter>  fieldSetters = key.getFieldMappersBeforeSaveDataObject();
		
		for (FieldMapperOrSetter fm: fieldSetters) {
			fm.executeMapping(do_collector, mv);
		}
	}

}
