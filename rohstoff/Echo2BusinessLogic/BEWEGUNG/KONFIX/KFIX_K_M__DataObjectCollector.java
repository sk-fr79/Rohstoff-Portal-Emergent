package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VKOPF_KON;

public class KFIX_K_M__DataObjectCollector extends RB_DataobjectsCollector_V2 {
   
	private VORGANGSART belegTyp = null;
	
	public KFIX_K_M__DataObjectCollector(VORGANGSART belegtyp) throws myException {
        super();
        this.belegTyp=belegtyp;
        
        this.registerComponent(new RB_KM(_TAB.vkopf_kon), new KFIX_K_M_DataObject(_TAB.vkopf_kon));
    }
	
	public KFIX_K_M__DataObjectCollector(String id_vkopf_kon, MASK_STATUS status, VORGANGSART belegtyp) throws myException {
		super();
		this.belegTyp=belegtyp;
		
		Rec20_VKOPF_KON recVkopfKon = new Rec20_VKOPF_KON(_TAB.vkopf_kon)._fill_id(id_vkopf_kon);
		
		boolean isFix = recVkopfKon.is_fixierungsKontrakte();
		
		this.registerComponent(new RB_KM(_TAB.vkopf_kon), new KFIX_K_M_DataObject(recVkopfKon, status, belegtyp, isFix));
		
	}
	

	@Override
	public void manipulate_filled_records_before_save(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv)	throws myException {
		this.get_v2(new RB_KM(_TAB.vkopf_kon)).get_rec20()._put_raw_value(VKOPF_KON.vorgang_typ,"'"+this.belegTyp.get_DBValue()+"'" , false);
	}

	@Override
	public void database_to_dataobject(Object startPoint) throws myException {
		this.rb_ComponentMapCollector_ThisBelongsTo();
	}

	@Override
	public void execute_final_statements_in_open_transaction(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv) throws myException {	
	}

	@Override
	public RB_DataobjectsCollector_V2 get_copy() throws myException {
		return null;
	}

	public VORGANGSART getBelegTyp() {
		return belegTyp;
	}
   
}
 
