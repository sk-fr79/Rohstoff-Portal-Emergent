package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE;

import java.util.ArrayList;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_MasterKey;


public abstract class FZ_dataObjectsCollector extends RB_DataobjectsCollector_V2 {

	
	private ArrayList<FZ_dataObjectsCollector_doBeforeSave>   	doBefore = new ArrayList<>();
	private ArrayList<FZ_dataObjectsCollector_doAfterSave>   	doAfter = new ArrayList<>();  

	//die individuelle funktionalitaet nach untern weiter gereicht, hier werden in den 
	public abstract void manipulate_filled_records_before_save_individual(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv) throws myException;
	public abstract void execute_final_statements_in_open_transaction_individual(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv) throws myException;
	
	
	/**
	 * 
	 */
	public FZ_dataObjectsCollector() {
		super();
		
		this.doBefore.add(new FZ_dataObjectsCollector_doBeforeSave_FieldSettingFromMasterkey());   //fuegt die fieldsetters, die im jeweilgen masterkey definiert wurden, in die records ein, before der erster save ausgefuehrt wird
		
		
		this.doAfter.add(new FZ_dataObjectsCollector_doAfterSave_FieldMappingsFromMasterKey());
		this.doAfter.add(new FZ_dataObjectsCollector_doAfterSave_writeAdresseBasis());
		this.doAfter.add(new FZ_dataObjectsCollector_doAfterSave_writeStatistikDaten());
		this.doAfter.add(new FZ_dataObjectsCollector_doAfterSave_writePrimanotaTable());
	}



	@Override
	public void database_to_dataobject(Object startPoint) throws myException {
	}


//	public abstract void execute_own_statements(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv) throws myException;
 
	public abstract IF_MasterKey get_master_key();
	
	
	@Override
	public void manipulate_filled_records_before_save(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv) throws myException {
		for (FZ_dataObjectsCollector_doBeforeSave doing: this.doBefore) {
			doing.manipulateRecordsBeforeSave((FZ_dataObjectsCollector)do_collector, mv);
		}
		this.manipulate_filled_records_before_save_individual(do_collector, mv);
	}

	
	
	@Override
	public void execute_final_statements_in_open_transaction(RB_DataobjectsCollector_V2 do_collector,MyE2_MessageVector mv) throws myException {
		for (FZ_dataObjectsCollector_doAfterSave doing: this.doAfter) {
			doing.executeInOpenTransactionAfterSave((FZ_dataObjectsCollector)do_collector, mv);
		}
		this.execute_final_statements_in_open_transaction_individual(do_collector, mv);
	}


	
	

	public ArrayList<FZ_dataObjectsCollector_doAfterSave> getDoingsAfterSave() {
		return doAfter;
	}
	
	public ArrayList<FZ_dataObjectsCollector_doBeforeSave> getDoingsBeforeSave() {
		return doBefore;
	}

	
}
