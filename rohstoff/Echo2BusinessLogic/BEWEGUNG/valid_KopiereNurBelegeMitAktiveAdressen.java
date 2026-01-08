package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;

public abstract class valid_KopiereNurBelegeMitAktiveAdressen extends  XX_ActionValidator 
{
	
	public abstract VectorSingle SammleAdressIDs(String cID_BelegToCopy) throws myException;
	
	
    // validierungsmethode
    public MyE2_MessageVector isValid(String cID_BelegToCopy) throws myException
    {
    	VectorSingle  vIDsAdresse = this.SammleAdressIDs(cID_BelegToCopy);
    	
    	MyE2_MessageVector  oMV = new MyE2_MessageVector();
    	
    	if (vIDsAdresse!=null)
    	{
    		for (int i=0;i<vIDsAdresse.size();i++)
    		{
    			if (!vIDsAdresse.get(i).trim().equals("0"))          //0 wird beim sammeln als dummy verwendet fuer adressfelder, die nicht besetzt sind
    			{
	    			RECORD_ADRESSE  recAdresse = new RECORD_ADRESSE(vIDsAdresse.get(i));
	    			if (recAdresse.is_AKTIV_NO())
	    			{
	    				MyE2_String  cAlarm = new MyE2_String("Die Adresse: ",true, recAdresse.get_NAME1_cF_NN("<name1>")+" "+recAdresse.get_ORT_cF_NN("<ort>"),false," ist INAKTIV !!",true);
	    				oMV.add_MESSAGE(new MyE2_Alarm_Message(cAlarm));
	    			}
    			}
    		}
    	}
    	else
    	{
    		oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Programm-Fehler: Die Adressvalidierung auf inaktive beteiligte Adressen kann nicht durchgeführt werden !")));
    	}
    	return oMV;
    }


}
