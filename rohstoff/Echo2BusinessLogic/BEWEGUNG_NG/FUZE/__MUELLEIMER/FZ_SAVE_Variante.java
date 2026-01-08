package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.__MUELLEIMER;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_MasterKey;

public class FZ_SAVE_Variante {

	private RB_DataobjectsCollector_V2 	do_collector 	= null;
	private IF_MasterKey 				key 			= null;
	private MASK_STATUS 				status 			= null;


	public FZ_SAVE_Variante() {

	}

	public FZ_SAVE_Variante _set_collector(RB_DataobjectsCollector_V2 p_do_collector){
		this.do_collector = p_do_collector;
		return this;
	}

	public FZ_SAVE_Variante _set_master_key(IF_MasterKey p_key){
		this.key = p_key;
		return this;
	}

	public FZ_SAVE_Variante _set_mask_status(MASK_STATUS p_status){
		this.status = p_status;
		return this;
	}

	public FZ_SAVE_Variante _SAVE(MyE2_MessageVector mv) throws myException{
		if(this.do_collector == null){
			mv._addAlarm("FZ_SAVE_Variante: Dataobjectcollector is null, please set a valid dataobjectcollector");
		}else if(this.key == null){
			mv._addAlarm("FZ_SAVE_Variante: Master key is null, please set a valid master key");
		}else if(this.status == null){
			mv._addAlarm("FZ_SAVE_Variante: status is null, please set a valid status");
		}

		if(mv.get_bIsOK()){
			String id_vektor = null;



			if(this.status== MASK_STATUS.NEW){
				id_vektor = this.do_collector.get(this.key.k_vektor()).rec20().get_rec_after_save_new().get_ufs_dbVal(BEWEGUNG_VEKTOR.id_bewegung_vektor);
			} else {		
				id_vektor = this.do_collector.get(this.key.k_vektor()).rec20().get_ufs_dbVal(BEWEGUNG_VEKTOR.id_bewegung_vektor);
			}

			Rec20 rec_vektor = new Rec20(_TAB.bewegung_vektor)._fill_id(id_vektor)
					._nv(BEWEGUNG_VEKTOR.variante, this.key.getMaskType().DB_CONST(), mv);
			
			rec_vektor._SAVE(false, mv);

		} else {
			mv.add_MESSAGE(new MyE2_Alarm_Message(S.mt("Achtung! Systemfehler beim Speichern !!")));
		}
	
	return this;
}

}
