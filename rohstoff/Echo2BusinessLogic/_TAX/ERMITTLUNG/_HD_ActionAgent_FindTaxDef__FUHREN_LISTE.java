package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.exceptions.myException;

public class _HD_ActionAgent_FindTaxDef__FUHREN_LISTE extends _HD_ActionAgent_FindTaxDef__ABSTRACT {
	
	
	@Override
	public HD_Stationen create_Stationen(ExecINFO oExecInfo) throws myException
	{
		__FU_BT_SucheHandelsDef_Zu_Fuhre oListButton = (__FU_BT_SucheHandelsDef_Zu_Fuhre)oExecInfo.get_MyActionEvent().getSource();
		
		
		HD_Stationen vHD_Stationen = new HD_Stationen(true);
		
		HD_RECORD_VPOS_TPA_FUHRE recFuhre = new HD_RECORD_VPOS_TPA_FUHRE(
				new RECORD_VPOS_TPA_FUHRE(
						oListButton.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_LActualDBValue("ID_VPOS_TPA_FUHRE",false)),
						false); 

		RECLIST_VPOS_TPA_FUHRE_ORT  recListOrte = 
						recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N'", null, true);

		vHD_Stationen.add(recFuhre.get_HD_StationEK());
		vHD_Stationen.add(recFuhre.get_HD_StationVK());

		if (recListOrte.get_vKeyValues().size()>0) {
			bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Achtung! Es wird nur die Warenbewegung auf der Hauptfuhre analysiert! Die Fuhrenorte müssen aus der Maske ermittelt werden !")));
		}
		
		return vHD_Stationen;
	}

	@Override
	public void _action_if_found_uniqueDefinition(HD_WarenBewegungEinstufung oHD_Fuhreneinstufung) throws myException
	{
	}

	@Override
	public MyE2_String get_Message_Eindeutige_Fuhreneinstufung_Kann_nicht_uebernommen_werden() {
		return new MyE2_String("Einstufungsvorschlag für diese Fuhre ...");
	}

}
