package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.__MUELLEIMER;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.FZ_dataObjectsCollector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.FZ_dataObjectsCollector_doAfterSave;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_KeyChain;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_MasterKey;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.IF_containsPosNr;

public class FZ_dataObjectsCollector_doAfterSave_fillPositionNumbers implements FZ_dataObjectsCollector_doAfterSave {



	@SuppressWarnings("rawtypes")
	@Override
	public void executeInOpenTransactionAfterSave(FZ_dataObjectsCollector do_collector, MyE2_MessageVector mv) throws myException {
		
		//generell alle dem masterkey anhaengenden keys durchsuchen und, falls posnummern im datensatz vorhanden sind, diese reinschreiben
		IF_MasterKey m_key = do_collector.get_master_key();
		
		for (IF_KeyChain key: m_key.get_all_child_keys()) {
			
			if (key instanceof IF_containsPosNr) {
				Long posnr = ((IF_containsPosNr)key).getPosNr();    //im masterkey hinterlegte posnummer
				
				Rec20 rec = ((IF_containsPosNr)key).getRec20()._fill_id(do_collector.get((RB_KM)key).rec20().getActualID());
				rec._add_field_val_pair("POSNR", ""+posnr);
				
				rec._SaveAndClean(false, mv);
			}
		}
	}

}
