package panter.gmbh.indep.batch;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/**
 * allgemeiner Batch-Handler fuer das ausfuehren von druckjobs
 * Dazu muessen folgende parameter gesetzt sein:
 *     REPORTNAME  -> name des jasperfiles ohne endung
 *     
 *     Fuer alle jasper-parameter, die an die hasperhash uebergeben werden sollen, muss ein
 *     ! vorausgestellt werden z.B.
 *     !DATUM_VON=2012-08-03 wird als parameter DATUM_VON uebergeben
 *     
 *     Ebenfalls wird ein jasperhash-Key BATCHDRUCK uebergeben, der hier den wert Y enhaelt
 * 
 * @author martin
 *
 */
public class DruckHandler implements ICallableTask
{

	
	
	private Vector<String>  			vTaskMessages = new Vector<String>();
	private HashMap<String, String>	    hmTaskParamters = new HashMap<String, String>();
	
	@Override
	public boolean runTask()
	{
		boolean bRueck = true;
		
		System.out.println("hier 1");
		
		try
		{
			if (this.hmTaskParamters.containsKey("REPORTNAME") && S.isFull(this.hmTaskParamters.get("REPORTNAME")))
			{
				E2_JasperHASH_STD  oJasperHash = new E2_JasperHASH_STD(this.hmTaskParamters.get("REPORTNAME"), new JasperFileDef_PDF());
			
				oJasperHash.put(E2_JasperHASH.HASHKEY_SYS_KENNSTRING_BATCHDRUCK, 	"Y");
				
				for (String cKEY: this.hmTaskParamters.keySet())
				{
					if (cKEY.startsWith("!"))
					{
						oJasperHash.put(cKEY.substring(1), this.hmTaskParamters.get(cKEY));
					}
				}
				
				oJasperHash.Build_tempFile(true);
				
			}
			else
			{
				this.vTaskMessages.add("Error in call-String: no Jasperfile defined !");
			}
			
		}
		catch (myException e)
		{
			e.printStackTrace();
			bRueck = false;
			
			this.vTaskMessages.add("Error loading Jasper-file: LIST_verladeplan_batch");
			
		}
		return bRueck;
	}

	@Override
	public void setTaskParameters(HashMap<String, String> hmParameters)
	{
		this.hmTaskParamters=hmParameters;
	}

	@Override
	public Vector<String> getTaskMessages()
	{
		return this.vTaskMessages;
	}

}
