package rohstoff.Echo2BusinessLogic.WAAGE.HANDLER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS.WAAGE_SETTINGS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAAGE_SETTINGS;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.Rec_WaageSettings;


public class WaageHandlerFactory {

	
	// erzeugt das richtige Waage-Objekt für die in der Tabelle WaageSettings angebenenn Werte
	public WaageHandlerFactory (){
	}
	
	public static WaageHandlerBase getWaageHandler(RECORD_WAAGE_SETTINGS oSettings) throws myException{
		WaageHandlerBase oHandler = null;
//		WaageHandlerSocket_Base oSocket_base = null;;

		String sParam = null;
		// 
		String sTyp = oSettings.get_TYP_WAAGE_cUF();
		if (sTyp.equalsIgnoreCase("SYSTEC_IP")){
			// IP_ADR: 	Ip-Adresse 
			// PORT: 	IP-Port 
			// PARAM1: 	Waage-NR
			
			//WaageHandlerSocket_SYSTEC oHandler = new WaageHandlerSocket_SYSTEC();
			oHandler = new WaageHandlerSocket_SYSTEC();

			sParam = oSettings.get_IP_ADR_cUF_NN("");
			oHandler.setParameter(WaageHandlerSocket_Base.ENUM_Parameter.IP_ADR.name(), sParam );
			sParam = oSettings.get_PORT_cUF_NN("");
			oHandler.setParameter(WaageHandlerSocket_Base.ENUM_Parameter.PORT.name(), sParam );
			sParam = oSettings.get_PARAM1_cUF_NN("");
			oHandler.setParameter(WaageHandlerSocket_SYSTEC.ENUM_Parameter_SYSTEC.WAAGENR.name(), sParam);
			
	
		} else if (sTyp.equalsIgnoreCase("SYSTEC_IP_READ")){
			
			oHandler = new WaageHandlerSocket_SYSTEC_read();
			
			sParam = oSettings.get_IP_ADR_cUF_NN("");
			oHandler.setParameter(WaageHandlerSocket_Base.ENUM_Parameter.IP_ADR.name(), sParam );
			sParam = oSettings.get_PORT_cUF_NN("");
			oHandler.setParameter(WaageHandlerSocket_Base.ENUM_Parameter.PORT.name(), sParam );
			sParam = oSettings.get_PARAM1_cUF_NN("");
			oHandler.setParameter(WaageHandlerSocket_SYSTEC.ENUM_Parameter_SYSTEC.WAAGENR.name(), sParam);

		} else if (sTyp.equals("SYSTEC_USB")){
			// 
			// noch nicht implementiert
			//
		}  else if (sTyp.equalsIgnoreCase("MINIPOND_IP")){
			
			oHandler = new WaageHandlerSocket_BuLMinipond();
			
			sParam = oSettings.get_IP_ADR_cUF_NN("");
			oHandler.setParameter(WaageHandlerSocket_Base.ENUM_Parameter.IP_ADR.name(), sParam );
			sParam = oSettings.get_PORT_cUF_NN("");
			oHandler.setParameter(WaageHandlerSocket_Base.ENUM_Parameter.PORT.name(), sParam );

		}
		else {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es gibt keine Waage vom Typ '" + sTyp + "'. Bitte prüfen Sie die Waage-Settings!")));
		}
		return oHandler;
	}
	

	public static WaageHandlerBase getWaageHandler(Rec_WaageSettings oSettings) throws myException{
		WaageHandlerBase oHandler = null;
//		WaageHandlerSocket_Base oSocket_base = null;;

		String sParam = null;
		// 
		String sTyp = oSettings.get_ufs_dbVal(WAAGE_SETTINGS.typ_waage) ;
		if (sTyp.equalsIgnoreCase("SYSTEC_IP")){
			// IP_ADR: 	Ip-Adresse 
			// PORT: 	IP-Port 
			// PARAM1: 	Waage-NR
			
			//WaageHandlerSocket_SYSTEC oHandler = new WaageHandlerSocket_SYSTEC();
			oHandler = new WaageHandlerSocket_SYSTEC();

			sParam = oSettings.get_ufs_dbVal(WAAGE_SETTINGS.ip_adr, "") ; //.get_IP_ADR_cUF_NN("");
			oHandler.setParameter(WaageHandlerSocket_Base.ENUM_Parameter.IP_ADR.name(), sParam );
			
			sParam = oSettings.get_ufs_dbVal(WAAGE_SETTINGS.port, "")   ;//get_PORT_cUF_NN("");
			oHandler.setParameter(WaageHandlerSocket_Base.ENUM_Parameter.PORT.name(), sParam );
			
			sParam = oSettings.get_ufs_dbVal(WAAGE_SETTINGS.param1, "")   ;//.get_PARAM1_cUF_NN("");
			oHandler.setParameter(WaageHandlerSocket_SYSTEC.ENUM_Parameter_SYSTEC.WAAGENR.name(), sParam);
			
	
		} else if (sTyp.equalsIgnoreCase("SYSTEC_IP_READ")){
			
			oHandler = new WaageHandlerSocket_SYSTEC_read();
			
			sParam = oSettings.get_ufs_dbVal(WAAGE_SETTINGS.ip_adr, "") ; //.get_IP_ADR_cUF_NN("");
			oHandler.setParameter(WaageHandlerSocket_Base.ENUM_Parameter.IP_ADR.name(), sParam );
			
			sParam = oSettings.get_ufs_dbVal(WAAGE_SETTINGS.port, "")   ;//get_PORT_cUF_NN("");
			oHandler.setParameter(WaageHandlerSocket_Base.ENUM_Parameter.PORT.name(), sParam );
			
			sParam = oSettings.get_ufs_dbVal(WAAGE_SETTINGS.param1, "")   ;//.get_PARAM1_cUF_NN("");
			oHandler.setParameter(WaageHandlerSocket_SYSTEC.ENUM_Parameter_SYSTEC.WAAGENR.name(), sParam);

		} else if (sTyp.equals("SYSTEC_USB")){
			// 
			// noch nicht implementiert
			//
		}  else if (sTyp.equalsIgnoreCase("MINIPOND_IP")){
			
			oHandler = new WaageHandlerSocket_BuLMinipond();
			
			sParam = oSettings.get_ufs_dbVal(WAAGE_SETTINGS.ip_adr, "") ; //.get_IP_ADR_cUF_NN("");
			oHandler.setParameter(WaageHandlerSocket_Base.ENUM_Parameter.IP_ADR.name(), sParam );
			
			sParam = oSettings.get_ufs_dbVal(WAAGE_SETTINGS.port, "")   ;//get_PORT_cUF_NN("");
			oHandler.setParameter(WaageHandlerSocket_Base.ENUM_Parameter.PORT.name(), sParam );

		}
		else {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es gibt keine Waage vom Typ '" + sTyp + "'. Bitte prüfen Sie die Waage-Settings!")));
		}
		return oHandler;
	}
	


	
	
}
