package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT._SETTING_DATUM;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class KORR_PosNummern {
	
	private Vector<String>  	vID_VKOPF_RG =  new Vector<String>();

	
	public KORR_PosNummern(Vector<String> vID_RechKopf) throws myException {
		super();
		this.vID_VKOPF_RG.addAll(vID_RechKopf);
		
	}
	
	
	public boolean MakeKorrection(MyE2_MessageVector  oMV) throws myException {
		
		boolean bRueck = false;
		
		if (this.vID_VKOPF_RG.size() > 0)
		{
			/*
			 * hier die Reihenfolge der positionen festelegen, damit keine sortierzufaelle moeglich sind 
			 */
			Vector<String> vSQLs = new Vector<String>();
			for (int i=0;i<this.vID_VKOPF_RG.size();i++)
			{
				RECLIST_VPOS_RG  recListVPOSRG = new RECLIST_VPOS_RG("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE NVL(DELETED,'N')='N' AND ID_VKOPF_RG="+this.vID_VKOPF_RG.get(i)+" ORDER BY POSITIONSNUMMER");
				
				for (int k=0;k<recListVPOSRG.get_vKeyValues().size();k++)
				{
					bibMSG.add_MESSAGE(recListVPOSRG.get(k).set_NEW_VALUE_POSITIONSNUMMER(""+(k+1)));
					vSQLs.add(recListVPOSRG.get(k).get_SQL_UPDATE_STATEMENT(null, true));
				}
			}
			MyE2_MessageVector ooMV = new MyE2_MessageVector();
			ooMV.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQLs, true));
			
			//DEBUG.System_print(vSQLs);
			
			if (ooMV.get_bHasAlarms())
			{
				bRueck = false;
			} else {
				bRueck = true;
			}
			
			oMV.add_MESSAGE(ooMV);
		}

		return bRueck;
	}
	
}
