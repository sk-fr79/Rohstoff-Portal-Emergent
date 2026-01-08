package panter.gmbh.basics4project.specialViews;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.XXX_ViewBuilder;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class build_view_FuhrenKlassifizierung extends XXX_ViewBuilder
{
	
	
	
	@Override
	public boolean build_View_forAll_Mandants() throws myException
	{
		
		RECLIST_MANDANT  oRecList = new RECLIST_MANDANT("SELECT * FROM "+bibE2.cTO()+".JD_MANDANT");
		
		String cID_ADRESSE_MANDANT= bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1");
		
		
		String c_SQL =  	" CREATE OR REPLACE VIEW A#MANDANT#_FUHRENKLASSEN AS ("+	
		 			" SELECT ID_VPOS_TPA_FUHRE,STATUS_BUCHUNG, "+
					" CASE "+
					"   WHEN (ZAHL_EIGENE_LADEORTE=0 AND ZAHL_EIGENE_ABLADEORTE=0)  THEN  'STRECKE' "+
					"    WHEN (ZAHL_EIGENE_LADEORTE=1 AND ZAHL_FREMD_LADEORTE=0 AND ZAHL_EIGENE_ABLADEORTE=0 AND ZAHL_FREMD_ABLADEORTE>0)  THEN  'EX_LAGER' "+
					"    WHEN (ZAHL_EIGENE_LADEORTE=0 AND ZAHL_FREMD_LADEORTE>0 AND ZAHL_EIGENE_ABLADEORTE=1 AND ZAHL_FREMD_ABLADEORTE=0)  THEN  'IN_LAGER' "+
					" ELSE  'GEMISCHT' "+
					" END  AS TYPUS "+
					" FROM "+
					" (SELECT ID_VPOS_TPA_FUHRE,STATUS_BUCHUNG,"+
					" ((SELECT COUNT(*) FROM JT_VPOS_TPA_FUHRE H WHERE H.ID_VPOS_TPA_FUHRE=FU.ID_VPOS_TPA_FUHRE AND H.ID_ADRESSE_START="+cID_ADRESSE_MANDANT+")+"+
					" (SELECT COUNT(*) FROM JT_VPOS_TPA_FUHRE_ORT H WHERE H.ID_VPOS_TPA_FUHRE=FU.ID_VPOS_TPA_FUHRE AND H.ID_ADRESSE="+cID_ADRESSE_MANDANT+" AND DEF_QUELLE_ZIEL='EK')) AS ZAHL_EIGENE_LADEORTE,"+
					" ((SELECT COUNT(*) FROM JT_VPOS_TPA_FUHRE H WHERE H.ID_VPOS_TPA_FUHRE=FU.ID_VPOS_TPA_FUHRE AND H.ID_ADRESSE_ZIEL="+cID_ADRESSE_MANDANT+")+"+
					" (SELECT COUNT(*) FROM JT_VPOS_TPA_FUHRE_ORT H WHERE H.ID_VPOS_TPA_FUHRE=FU.ID_VPOS_TPA_FUHRE AND H.ID_ADRESSE="+cID_ADRESSE_MANDANT+" AND DEF_QUELLE_ZIEL='VK')) AS ZAHL_EIGENE_ABLADEORTE,"+
					" ((SELECT COUNT(*) FROM JT_VPOS_TPA_FUHRE H WHERE H.ID_VPOS_TPA_FUHRE=FU.ID_VPOS_TPA_FUHRE AND H.ID_ADRESSE_START<>"+cID_ADRESSE_MANDANT+")+"+
					" (SELECT COUNT(*) FROM JT_VPOS_TPA_FUHRE_ORT H WHERE H.ID_VPOS_TPA_FUHRE=FU.ID_VPOS_TPA_FUHRE AND H.ID_ADRESSE<>"+cID_ADRESSE_MANDANT+" AND DEF_QUELLE_ZIEL='EK')) AS ZAHL_FREMD_LADEORTE,"+
					" ((SELECT COUNT(*) FROM JT_VPOS_TPA_FUHRE H WHERE H.ID_VPOS_TPA_FUHRE=FU.ID_VPOS_TPA_FUHRE AND H.ID_ADRESSE_ZIEL<>"+cID_ADRESSE_MANDANT+")+"+
					" (SELECT COUNT(*) FROM JT_VPOS_TPA_FUHRE_ORT H WHERE H.ID_VPOS_TPA_FUHRE=FU.ID_VPOS_TPA_FUHRE AND H.ID_ADRESSE<>"+cID_ADRESSE_MANDANT+" AND DEF_QUELLE_ZIEL='VK')) AS ZAHL_FREMD_ABLADEORTE "+
					" FROM JT_VPOS_TPA_FUHRE FU "+
					" WHERE FU.ID_MANDANT=#MANDANT# )"+
					")";

									

		for (int i=0;i<oRecList.get_vKeyValues().size();i++)
		{
			
			String cSQL = bibALL.ReplaceTeilString(c_SQL,"#MANDANT#",oRecList.get(i).get_ID_MANDANT_cUF());
			
			
			if (bibDB.ExecSQL(cSQL, true))
			{
				MyE2_String cInfo = new MyE2_String("Fuhrenklassen-View fuer Mandant: <",true,oRecList.get(i).get_NAME1_cF_NN(""),false, "> erfolgreich erstellt !",true);
				bibMSG.add_MESSAGE(new MyE2_Info_Message(cInfo));
			}
			else
			{
				MyE2_String cInfo = new MyE2_String("Fuhrenklassen-View fuer Mandant: <",true,oRecList.get(i).get_NAME1_cF_NN(""),false, "> KONNTE NICHT ERSTELLT WERDEN !",true);
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(cInfo));
			}
		}
		
		
		return false;
	}

	
	
	
	
	
}
