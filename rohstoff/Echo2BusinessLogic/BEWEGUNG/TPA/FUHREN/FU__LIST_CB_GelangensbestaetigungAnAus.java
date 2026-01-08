package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.BasicInterfaces.Service.PdServiceCheckGelangensbestaetigungIsValid;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.EnumMessageType;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibServer;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_VPOS_TPA_FUHRE_EXT;

public class FU__LIST_CB_GelangensbestaetigungAnAus extends MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction
{

	public FU__LIST_CB_GelangensbestaetigungAnAus(SQLField osqlField) throws myException
	{
		super(osqlField);
		this.add_oActionAgent(this.get_ToggleAction());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(FU___CONST.VALIDKEY_GELANGENSBESTAETIGUNG_ERHALTEN));
		
		this.setToolTipText(bibServer.get_TooltipInfosFromDBDescription(_DB.VPOS_TPA_FUHRE, _DB.VPOS_TPA_FUHRE$GELANGENSBESTAETIGUNG_ERHALTEN));
		
	}

	@Override
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		FU__LIST_CB_GelangensbestaetigungAnAus oCBCopy = null;
		try {
			oCBCopy = new FU__LIST_CB_GelangensbestaetigungAnAus(this.EXT_DB().get_oSQLField());
		} catch (myException e) {
			e.printStackTrace();
		}
	
		return oCBCopy;
	}

	@Override
	public XX_ActionAgent get_ToggleAction() throws myException
	{
		
		return new XX_ActionAgent()
		{
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				FU__LIST_CB_GelangensbestaetigungAnAus oThis = FU__LIST_CB_GelangensbestaetigungAnAus.this;
				
				String cID_VPOS_TPA_FUHRE = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();		
				
				RECORD_VPOS_TPA_FUHRE_EXT recFuhren =  new RECORD_VPOS_TPA_FUHRE_EXT(new RECORD_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE));
				
				PdServiceCheckGelangensbestaetigungIsValid.EnumGelangensbestaetigungStatus statusGelangensBestaetigungAllowed = 
						new PdServiceCheckGelangensbestaetigungIsValid().getStatusValidierung(
								 recFuhren.get_ID_ADRESSE_LAGER_ZIEL_lValue(-1l)
								,recFuhren.get_bFremdwarenFuhre()
								,recFuhren.get_TP_VERANTWORTUNG_cUF_NN(""));
				
				boolean oldValue = recFuhren.is_GELANGENSBESTAETIGUNG_ERHALTEN_YES();
				boolean newValue = oThis.isSelected();
				
				if (statusGelangensBestaetigungAllowed.getMessageType()==EnumMessageType.ALARM) {
					//der alte status wiederherstellen, checkbox wie vorher
					oThis.setSelected(oldValue);
					bibMSG.MV()._addAlarm(statusGelangensBestaetigungAllowed.getMessage());
				} else {
					MyE2_MessageVector oMV_OK = bibDB.ExecMultiSQLVector(bibVECTOR.get_Vector("UPDATE "+bibE2.cTO()+"."+_DB.VPOS_TPA_FUHRE+" SET "+
							_DB.VPOS_TPA_FUHRE$GELANGENSBESTAETIGUNG_ERHALTEN+"="+
							(newValue?"'Y'":"'N'")+" WHERE "+_DB.VPOS_TPA_FUHRE$ID_VPOS_TPA_FUHRE+"="+cID_VPOS_TPA_FUHRE), 
							true);
					if (oMV_OK.isOK()) {
						oThis.setSelected(newValue);
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Erhalt der Gelangesbestätigung wurde auf "+(newValue?"<ERHALTEN>":"<FEHLT>")+" gesetzt!")));
						if (statusGelangensBestaetigungAllowed.getMessageType()==EnumMessageType.WARNING && newValue) {
							bibMSG.MV()._addWarn(statusGelangensBestaetigungAllowed.getMessage());
						}
					} else {
						oThis.setSelected(oldValue);
						bibMSG.add_MESSAGE(oMV_OK);
					}
				}
				
				
				
//				if (!recFuhren.get_bFremdwarenFuhre()) {
//				
//					//jetzt pruefen, ob der schalter sinnvoll ist
//					if (recFuhren.get_ID_ADRESSE_ZIEL_lValue(0l).intValue()!=bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(-1l).intValue()) {
//						
//						if (recFuhren.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel()!=null && 
//							recFuhren.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel().get_UP_RECORD_LAND_id_land()!=null &&
//							recFuhren.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel().get_UP_RECORD_LAND_id_land().is_INTRASTAT_JN_YES() &&
//							recFuhren.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel().get_UP_RECORD_LAND_id_land().get_ID_LAND_lValue(-1l).intValue()!=
//								bibALL.get_RECORD_MANDANT().get_ID_LAND_lValue(-1l).intValue()) {
//							
//							boolean bNew = !recFuhren.is_GELANGENSBESTAETIGUNG_ERHALTEN_YES();
//							
//							MyE2_MessageVector oMV_OK = bibDB.ExecMultiSQLVector(bibVECTOR.get_Vector("UPDATE "+bibE2.cTO()+"."+_DB.VPOS_TPA_FUHRE+" SET "+
//																			_DB.VPOS_TPA_FUHRE$GELANGENSBESTAETIGUNG_ERHALTEN+"="+
//																			(bNew?"'Y'":"'N'")+" WHERE "+_DB.VPOS_TPA_FUHRE$ID_VPOS_TPA_FUHRE+"="+cID_VPOS_TPA_FUHRE), 
//																			true);
//							
//							if (oMV_OK.get_bIsOK()) {
//								//FU__LIST_CB_GelangensbestaetigungAnAus.this.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
//								oThis.setSelected(bNew);
//								bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Erhalt der Gelangesbestätigung wurde auf "+(bNew?"<ERHALTEN>":"<FEHLT>")+" gesetzt!")));
//							} else {
//								bibMSG.add_MESSAGE(oMV_OK);
//								//im fehlerfall den alten zustand aus der datenbank lesen
//								FU__LIST_CB_GelangensbestaetigungAnAus.this.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
//							} 
//							
//						} else {
//							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Das Land der Abladestation ist kein EU-Ausland (laut Länderstamm)!")));
//							oThis.setSelected(!oThis.isSelected());
//						}
//						
//					} else {
//						bibMSG.add_MESSAGE(
//								new MyE2_Alarm_Message(
//										new MyE2_String("Die Abladestelle ist ein Lager von "+bibALL.get_RECORD_MANDANT().get___KETTE(
//												bibVECTOR.get_Vector(_DB.MANDANT$NAME1,_DB.MANDANT$NAME2,_DB.MANDANT$ORT)))));
//						oThis.setSelected(!oThis.isSelected());
//					} 
//					
//				
//				} else {
//					bibMSG.add_MESSAGE(
//							new MyE2_Alarm_Message(
//									new MyE2_String("Bei Fremdwarenfuhren kann es keine Gelangensbestätigung geben !")));
//					oThis.setSelected(!oThis.isSelected());
//				}
				
			}
				
		};
	}

}
