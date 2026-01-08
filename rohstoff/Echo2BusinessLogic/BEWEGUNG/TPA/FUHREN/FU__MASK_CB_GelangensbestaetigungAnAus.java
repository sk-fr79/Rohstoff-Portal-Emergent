package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.BasicInterfaces.Service.PdServiceCheckGelangensbestaetigungIsValid;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.EnumMessageType;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class FU__MASK_CB_GelangensbestaetigungAnAus extends MyE2_DB_CheckBox
{

	public FU__MASK_CB_GelangensbestaetigungAnAus(SQLField osqlField) throws myException
	{
		super(osqlField);
		this.add_oActionAgent(this.get_ToggleAction());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(FU___CONST.VALIDKEY_GELANGENSBESTAETIGUNG_ERHALTEN));
	}

	@Override
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		FU__MASK_CB_GelangensbestaetigungAnAus oCBCopy = null;
		try {
			oCBCopy = new FU__MASK_CB_GelangensbestaetigungAnAus(this.EXT_DB().get_oSQLField());
		} catch (myException e) {
			e.printStackTrace();
		}
	
		return oCBCopy;
	}

	public XX_ActionAgent get_ToggleAction() throws myException
	{
		
		return new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				FU__MASK_CB_GelangensbestaetigungAnAus oThis = FU__MASK_CB_GelangensbestaetigungAnAus.this;
				E2_ComponentMAP  oMap = oThis.EXT().get_oComponentMAP();
				
				//eine sinnpruefung erfolgt nur, wenn der schalter eingeschaltet wird
				if (oThis.isSelected()) {
				
					boolean bFremdWarenFuhre = 			oMap.get_bActualDBValue(_DB.VPOS_TPA_FUHRE$OHNE_ABRECHNUNG) || 
														S.isFull(oMap.get_cActualDBValueFormated(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_FREMDAUFTRAG));
					
					int     iID_ADRESSE_ZIEL = 			oMap.get_LActualDBValue(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_ZIEL, -1l, -1l).intValue();
					int     iID_ADRESSE_LAGER_ZIEL = 	oMap.get_LActualDBValue(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_LAGER_ZIEL, -1l, -1l).intValue();
					String transportVerantwortung =     oMap.get_cActualDBValueFormated(VPOS_TPA_FUHRE.tp_verantwortung.fn());

					
					Long   lIdZiel = iID_ADRESSE_LAGER_ZIEL<0?null:new Long(iID_ADRESSE_LAGER_ZIEL);
					
					PdServiceCheckGelangensbestaetigungIsValid.EnumGelangensbestaetigungStatus statusGelangensBestaetigungAllowed = 
							new PdServiceCheckGelangensbestaetigungIsValid().getStatusValidierung(
									lIdZiel
									,bFremdWarenFuhre
									,transportVerantwortung);
					
					boolean newValue = oThis.isSelected();
					
					if (statusGelangensBestaetigungAllowed.getMessageType()==EnumMessageType.ALARM) {
						//der alte status wiederherstellen, checkbox wie vorher
						oThis.setSelected(!newValue);
						bibMSG.MV()._addAlarm(statusGelangensBestaetigungAllowed.getMessage());
					} else {
						if (statusGelangensBestaetigungAllowed.getMessageType()==EnumMessageType.WARNING && newValue) {
							bibMSG.MV()._addWarn(statusGelangensBestaetigungAllowed.getMessage());
						}
					}

					
//					
//					//fehler1: Zieladresse noch undefiniert
//					if (iID_ADRESSE_ZIEL==-1 || iID_ADRESSE_LAGER_ZIEL==-1) {
//						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Schalter erst möglich, wenn die Zieladressen feststehen!")));
//						oThis.setSelected(false);
//						return;
//					}
//	
//					//fehler2: Zieladresse ist mandant
//					if (iID_ADRESSE_ZIEL==bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(-2l).intValue()) {
//						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Abladestelle ist ein Lager von "+bibALL.get_RECORD_MANDANT().get___KETTE(
//								bibVECTOR.get_Vector(_DB.MANDANT$NAME1,_DB.MANDANT$NAME2,_DB.MANDANT$ORT)))));
//						oThis.setSelected(false);
//						return;
//					}
//					
//					//fehler2: Fremdwarenfuhre
//					if (bFremdWarenFuhre) {
//						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bei Fremdwarenfuhren kann es keine Gelangensbestätigung geben !")));
//						oThis.setSelected(false);
//						return;
//					}
//					
//					RECORD_ADRESSE  recLAGER = new RECORD_ADRESSE(iID_ADRESSE_LAGER_ZIEL);
//					
//					if (recLAGER.get_UP_RECORD_LAND_id_land()!=null &&
//						recLAGER.get_UP_RECORD_LAND_id_land().is_INTRASTAT_JN_YES() &&
//						recLAGER.get_UP_RECORD_LAND_id_land().get_ID_LAND_lValue(-1l).intValue()!=bibALL.get_RECORD_MANDANT().get_ID_LAND_lValue(-1l).intValue()) {
//								
//						//alles ok		
//						
//					} else {
//						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Zieladresse muss im EU-Ausland liegen !")));
//						oThis.setSelected(false);
//					}
				}
			}
		};
	}

}
