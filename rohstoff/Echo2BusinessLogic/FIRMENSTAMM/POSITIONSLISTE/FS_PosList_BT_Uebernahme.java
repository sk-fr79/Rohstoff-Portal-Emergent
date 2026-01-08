package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.POSITIONSLISTE;

import java.math.BigDecimal;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FS_PosList_BT_Uebernahme extends MyE2_ButtonInLIST {

	public FS_PosList_BT_Uebernahme() {
		super(E2_ResourceIcon.get_RI("pfeil_rechts.png"), true);
		
		this.setToolTipText(new MyE2_String("Zahlbetrag übernehmen und speichern ...").CTrans());
		
		this.add_oActionAgent(new ownActionAgent());
	}

	
	private class ownActionAgent extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			String cID_VPOS_RG = ((FS_PosList_BT_Uebernahme)oExecInfo.get_MyActionEvent().getSource()).EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();

			
			RECORD_VPOS_RG  recRG = new RECORD_VPOS_RG(cID_VPOS_RG);

			boolean bFuellenErlaubt = false;
			
			if (S.isFull(recRG.get_ID_VKOPF_RG_cUF_NN(""))) {
			
				RECORD_VKOPF_RG  	recKOPF_RG = recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg();
				RECLIST_FIBU   		rlFIBU = recKOPF_RG.get_DOWN_RECORD_LIST_FIBU_id_vkopf_rg("NVL("+_DB.FIBU$STORNIERT+",'N')='N'","",true);		
				
				
				if (rlFIBU.get_vKeyValues().size()==0) {
					bFuellenErlaubt = true;
				} else if (rlFIBU.get_vKeyValues().size()==1) {
					if (rlFIBU.get(0).is_BUCHUNG_GESCHLOSSEN_NO()) {
						bFuellenErlaubt = true;
					} else {
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Diese Position ist laut FIBU bereits abgeschlossen, damit ist diese Eingabe nicht sinnvoll !")));
						return;
					}
				} else if (rlFIBU.get_vKeyValues().size()>1) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler! zu dieser Position werden mehrere FIBU-Einträge gefunden .. Bitte vom SYSADMIN prüfen lassen !")));
					return;
				}

			} else {
				bFuellenErlaubt = true;            //bei freien positionen immer erlaubt
			}
			
			if (bFuellenErlaubt) {
				
				BigDecimal oBD_Preis = recRG.get_GESAMTPREIS_FW_bdValue(BigDecimal.ZERO).subtract(recRG.get_GESAMTPREIS_ABZUG_FW_bdValue(BigDecimal.ZERO));
				
				String cNewValue = MyNumberFormater.formatDezWithRound(oBD_Preis, 2);
				
				recRG.set_NEW_VALUE_ZAHLUNG_KONTROLLE(cNewValue);
				//DEBUG.System_println(recRG.get_SQL_UPDATE_STD(), null);
				recRG.UPDATE(true);
				
				((FS_PosList_BT_Uebernahme)oExecInfo.get_MyActionEvent().getSource()).EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bei dieser Position ist dieser Eintrag nicht möglich !")));
			}
			
		}
		
	}
	

	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		return new FS_PosList_BT_Uebernahme();
		
	}
	
}
