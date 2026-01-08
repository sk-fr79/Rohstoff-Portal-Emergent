package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskControllerField;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskControllerMap;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.ZAHLUNGSBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_P_M_Controller extends RB_MaskControllerMap {

	public KFIX_P_M_Controller(RB_ComponentMapCollector componentMapCollector) throws myException {
		super(componentMapCollector);
	}

	@Override
	public MyE2_MessageVector do_mask_settings(IF_RB_Component compCalling, String fieldVal, boolean primaryCall)
			throws myException {
		MyE2_MessageVector omv = new  MyE2_MessageVector();

		RB_MaskControllerField  callingField = this.get_MaskControllerField(compCalling);

		if (callingField != null) {
			
			if(callingField.get_component() instanceof KFIX_P_M_Zahlungsbedingung_SelectField){
				fill_zahlungsbedingung_dateien(omv, fieldVal);
			}
			
		}
		return omv;
	}
	
	private void fill_zahlungsbedingung_dateien(MyE2_MessageVector omv, String zahlungsbedingung) throws myException{
		Rec20 recZahlungsB = new Rec20(_TAB.zahlungsbedingungen)._fill_id(zahlungsbedingung);

		RB_KM keymap = new RB_KM(_TAB.vpos_kon);
		this.set_maskVal(keymap, VPOS_KON.zahlungsbedingungen, recZahlungsB.get_fs_dbVal(ZAHLUNGSBEDINGUNGEN.bezeichnung), omv);
		this.set_maskVal(keymap, VPOS_KON.fixmonat, recZahlungsB.get_fs_dbVal(ZAHLUNGSBEDINGUNGEN.fixmonat), omv);
		this.set_maskVal(keymap, VPOS_KON.fixtag, recZahlungsB.get_fs_dbVal(ZAHLUNGSBEDINGUNGEN.fixtag), omv);
		this.set_maskVal(keymap, VPOS_KON.zahltage, recZahlungsB.get_fs_dbVal(ZAHLUNGSBEDINGUNGEN.zahltage), omv);
		this.set_maskVal(keymap, VPOS_KON.skonto_prozent, recZahlungsB.get_fs_dbVal(ZAHLUNGSBEDINGUNGEN.skonto_prozent), omv);
		this.set_maskVal(keymap, VPOS_KON.fixmonat, recZahlungsB.get_fs_dbVal(ZAHLUNGSBEDINGUNGEN.fixmonat), omv);
	}
	
	

}
