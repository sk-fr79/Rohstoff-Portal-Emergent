package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE;

import java.util.ArrayList;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.Mapper.FieldMapperOrSetter;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_MasterKey;

public class FZ_dataObjectsCollector_doAfterSave_FieldMappingsFromMasterKey implements FZ_dataObjectsCollector_doAfterSave {

	@Override
	public void executeInOpenTransactionAfterSave(FZ_dataObjectsCollector do_collector, MyE2_MessageVector mv) 	throws myException {
		
		//als erstes den Masterkey besorgen
		IF_MasterKey key = do_collector.get_master_key();
		
		//die Rec20 - objekte sammeln, damit die verwendeten dann gespeichert werden koennen
		Vector<Rec20>   vRecsToSave = new Vector<>();
		
		ArrayList<FieldMapperOrSetter>  fieldMappers = key.getFieldMappersAfterSaveDataObject();
		
		for (FieldMapperOrSetter fm: fieldMappers) {
			fm.executeMapping(do_collector, mv);
			
			//nachsehen, ob im originalen record oder im recordnew etwas eingetragen wurde
			Rec20 recDirekt = do_collector.get(fm.getTargetTableKey()).rec20();
			Rec20 recAfterNew = do_collector.get(fm.getTargetTableKey()).rec20().get_rec_after_save_new();
			
			if (recDirekt.get_bHasSomething_to_save()) {
				if (!vRecsToSave.contains(recDirekt)) { 
					vRecsToSave.add(recDirekt);
				}
			}
			if (recAfterNew!=null && recAfterNew.get_bHasSomething_to_save()) {
				if (!vRecsToSave.contains(recAfterNew)) { 
					vRecsToSave.add(recAfterNew);
				}
			}
		}
		
		for (Rec20 r: vRecsToSave) {
			DEBUG.System_println(S.NN(r.get_sql_4_save(true)," -- nothing to save ----"));
			r._SaveAndClean(false, mv);
		}
		
		
	}

}
