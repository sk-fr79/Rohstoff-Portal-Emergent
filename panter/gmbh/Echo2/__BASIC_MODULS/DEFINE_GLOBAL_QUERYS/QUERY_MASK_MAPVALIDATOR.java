package panter.gmbh.Echo2.__BASIC_MODULS.DEFINE_GLOBAL_QUERYS;

import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;

public class QUERY_MASK_MAPVALIDATOR extends XX_MAP_ValidBeforeSAVE 
{

	public MyE2_MessageVector _doValidation( E2_ComponentMAP oMap,SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) 
	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		
		// es duerfen keine Anfuehrungszeichen in der query vorhanden sein
		try 
		{
			String cSQLFELDLISTE = (String)oInputMap.get_InputString("SQLFELDLISTE");
			String cSQLFROMBLOCK = (String)oInputMap.get_InputString("SQLFROMBLOCK");
			String cSQLWHEREBLOCK = (String)oInputMap.get_InputString("SQLWHEREBLOCK");
			String cSQLORDERBLOCK = (String)oInputMap.get_InputString("SQLORDERBLOCK");
			
			if (cSQLFELDLISTE.indexOf("\"")>-1)
				vRueck.add_MESSAGE(new MyE2_Alarm_Message("Feldliste enthält Anführungszeichen ! Das ist verboten !"), false);
			
			if (cSQLFROMBLOCK.indexOf("\"")>-1)
				vRueck.add_MESSAGE(new MyE2_Alarm_Message("FROM-Block enthält Anführungszeichen ! Das ist verboten !"), false);
			
			if (cSQLWHEREBLOCK.indexOf("\"")>-1)
				vRueck.add_MESSAGE(new MyE2_Alarm_Message("WHERE-Block enthält Anführungszeichen ! Das ist verboten !"), false);

			if (cSQLORDERBLOCK.indexOf("\"")>-1)
				vRueck.add_MESSAGE(new MyE2_Alarm_Message("ORDER-Block enthält Anführungszeichen ! Das ist verboten !"), false);
} 
		catch (myException e) 
		{
			vRueck.add_MESSAGE(new MyE2_Alarm_Message("Ein Feld wurde in der INPUT-MAP nicht gefunden !"), false);
			vRueck.add_MESSAGE(e.get_ErrorMessage(), false);
		}


		
		return vRueck;
	}

}
