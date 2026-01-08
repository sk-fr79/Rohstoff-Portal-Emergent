package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_ComponentMAP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FUO_MASK_ComponentMAP;

/**
 * zusatz-actionagent fuer die fuhrenmaske, um steuerdefinitionen zu holen
 * @author martin
 *
 */
public class _HD_ActionAgent_FindTaxDef__FUHREN_ORT_MASKE extends _HD_ActionAgent_FindTaxDef__ABSTRACT {

	@Override
	public HD_Stationen create_Stationen(ExecINFO oExecInfo) 	throws myException {
		HD_Stationen 		vHD_Stationen = 	new HD_Stationen(true);
		
		MyE2_Button 			oButtonSteuer  = 	(MyE2_Button) oExecInfo.get_MyActionEvent().getSource();
		
		FUO_MASK_ComponentMAP  	oMAP_FUO = 			(FUO_MASK_ComponentMAP)oButtonSteuer.EXT().get_oComponentMAP();
		FU_MASK_ComponentMAP    oMAP_FU =      		(FU_MASK_ComponentMAP)oMAP_FUO.get_oFU_FUO_DaughterComponent().EXT().get_oComponentMAP();
		
		boolean   				bOrtIstQuelle = oMAP_FUO.get_oSQLFieldMAP().get_hmRestrictionFieldValues().get(_DB.VPOS_TPA_FUHRE_ORT$DEF_QUELLE_ZIEL).equals("'EK'");
		
		if (bOrtIstQuelle) {
			vHD_Stationen.add(new _HD_Station_FUHREN_ORT_MASKE(oMAP_FUO,oMAP_FU));
			vHD_Stationen.add(new _HD_Station_FUHREN_MASKE(oMAP_FU, false, true));
		} else {
			vHD_Stationen.add(new _HD_Station_FUHREN_MASKE(oMAP_FU, true, true));
			vHD_Stationen.add(new _HD_Station_FUHREN_ORT_MASKE(oMAP_FUO,oMAP_FU));
		}
		
		return vHD_Stationen;
	}

	
	@Override
	public void _action_if_found_uniqueDefinition(HD_WarenBewegungEinstufung oHD_Fuhreneinstufung)	throws myException {
		
		//alle vectoren durchgehen und bei allen HD_Station_ - objekten den update ausfuehren
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		oMV.add_MESSAGE(oHD_Fuhreneinstufung.get_oHD_StationEK().applyResults(oHD_Fuhreneinstufung, oHD_Fuhreneinstufung.get_cID_TAX_IN_Korrekt_UF(), oHD_Fuhreneinstufung.get_cINTRASTAT_IN(), oHD_Fuhreneinstufung.get_c_TRANSIT_EK(), true));
		oMV.add_MESSAGE(oHD_Fuhreneinstufung.get_oHD_StationVK().applyResults(oHD_Fuhreneinstufung, oHD_Fuhreneinstufung.get_cID_TAX_OUT_Korrekt_UF(), oHD_Fuhreneinstufung.get_cINTRASTAT_OUT(), oHD_Fuhreneinstufung.get_c_TRANSIT_VK(), false));

		bibMSG.add_MESSAGE(oMV);

		if (bibMSG.get_bHasAlarms()) {
			return;
		}

		HD_WarenBewegungEinstufungen vFuhrenEinstufung_single = new HD_WarenBewegungEinstufungen();
		vFuhrenEinstufung_single.add(oHD_Fuhreneinstufung,false);
		
		new HD_PopupZeigeFuhreneinstufung(vFuhrenEinstufung_single,
						new MyE2_String("Es wurde der folgende Sachverhalt bei diesem Fuhrenort gefunden und geschrieben (NUR IM FUHRENORT! Hauptfuhre MUSS separat eingestellt werden !"),
						true);
	}

	

	@Override
	public MyE2_String get_Message_Eindeutige_Fuhreneinstufung_Kann_nicht_uebernommen_werden() {
		return new MyE2_String("Es wurden bereits abgeschlossene Preise an einigen Orten dieser Fuhre gefunden !");
	}
	
}
