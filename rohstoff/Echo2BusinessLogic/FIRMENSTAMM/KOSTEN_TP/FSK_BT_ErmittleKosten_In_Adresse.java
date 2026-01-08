package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KOSTEN_TP;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_CONST;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN._TP_KOSTEN_DIVERSE.TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis;

public class FSK_BT_ErmittleKosten_In_Adresse extends MyE2_Button {

	
	public FSK_BT_ErmittleKosten_In_Adresse() throws myException {
		super(new MyE2_String("Alle Kosten dieser Adresse ermitteln"), MyE2_Button.StyleImageButtonCenteredWithBlackBorder());
		
		this.__setImages(E2_ResourceIcon.get_RI("wizard.png"), true);
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalAUTHValidator_AUTO(FS_CONST.MASK_VALID_ERMITTLE_KOSTEN);
	}
 
	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (FSK_BT_ErmittleKosten_In_Adresse.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP() != null) {
				String cID_ADRESSE = FSK_BT_ErmittleKosten_In_Adresse.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				if (S.isFull(cID_ADRESSE)) {
					new ownKOSTEN_SUCHE_MittlererRealer_TP_Preis(cID_ADRESSE).SHOW_SettingsWindow();
				} else {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler: Ermittlung der Adressen-ID ist nicht möglich !")));
				}
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bei einer Neuerfassung ist die Funktion gesperrt !")));
			}
		}
	}
	 
	
	
	private class ownKOSTEN_SUCHE_MittlererRealer_TP_Preis extends TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis {

		public ownKOSTEN_SUCHE_MittlererRealer_TP_Preis(String cID_Adresse) throws myException {
			super(bibALL.get_cDateFirstDateActualMonth(), true, cID_Adresse, null);
		}

		@Override
		public MyE2_MessageVector do_After_UpdateStatements() throws myException {
			String cID_ADRESSE = FSK_BT_ErmittleKosten_In_Adresse.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			((FSK__FULL_MASK_DAUGHTER_TP_KOSTEN) FSK_BT_ErmittleKosten_In_Adresse.this.EXT().get_oComponentMAP().
					get__Comp(FS_CONST.MASK_FIELD_DAUGHTER_TRANSPORTKOSTEN_ADRESSE)).build_content_for_Mask(cID_ADRESSE, cID_ADRESSE, E2_ComponentMAP.STATUS_EDIT);			

			return new MyE2_MessageVector();
		}
		
	}

	
}
