package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KOSTEN_TP;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_CONST;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN._TP_KOSTEN_DIVERSE.TP_KOSTEN_SucheKombinationenQuelleZiel;

public class FSK_BT_Ermittle_Kosten_Relationen_In_Adresse extends MyE2_Button {

	
	public FSK_BT_Ermittle_Kosten_Relationen_In_Adresse() {
		super(new MyE2_String("Suche Kombinationen aus der Historie"), MyE2_Button.StyleImageButtonCenteredWithBlackBorder());
		
		this.__setImages(E2_ResourceIcon.get_RI("suche.png"), true);
		
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalAUTHValidator_AUTO(FS_CONST.MASK_VALID_ERMITTLE_KOSTENRELATIONEN);

	}
 
	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			
			String cID_ADRESSE_BASE = null; 
			if (FSK_BT_Ermittle_Kosten_Relationen_In_Adresse.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP() != null) {
				cID_ADRESSE_BASE = FSK_BT_Ermittle_Kosten_Relationen_In_Adresse.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				
				new TP_KOSTEN_SucheKombinationenQuelleZiel(cID_ADRESSE_BASE);

				//liste neu einlesen
				((FSK__FULL_MASK_DAUGHTER_TP_KOSTEN) FSK_BT_Ermittle_Kosten_Relationen_In_Adresse.this.EXT().get_oComponentMAP().get__Comp(
						FS_CONST.MASK_FIELD_DAUGHTER_TRANSPORTKOSTEN_ADRESSE)).set_cActual_Formated_DBContent_To_Mask(
								cID_ADRESSE_BASE, E2_ComponentMAP.STATUS_EDIT, null);
				
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bei einer Neuerfassung ist die Funktion gesperrt !")));
			}
			
		}
	}
	
}
