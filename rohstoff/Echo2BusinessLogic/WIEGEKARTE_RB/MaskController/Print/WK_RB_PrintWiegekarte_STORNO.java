package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print;

import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS.BorderCrossingReminder;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.WK_RB_MC_ContainerHandling;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;

public class WK_RB_PrintWiegekarte_STORNO extends WK_RB_PrintBase {

	public WK_RB_PrintWiegekarte_STORNO(RecDOWiegekarte recWK) {
		super(recWK);
		
	}

	public WK_RB_PrintWiegekarte_STORNO(String idWiegekarte) throws myException {
		super(idWiegekarte);
		
	}

	@Override
	public void Print() throws myException {
		
		
		
		// drucken der Wiegekarte(n)
		E2_JasperHASH oJasperHash = new E2_JasperHASH_STD("wiegekarte",new JasperFileDef_PDF());
		oJasperHash.set_bWarningOnEmptyPipelineResult(true);
			
		// alle Jasper-Parameter aus der Wiegekarte setzen 
		setJasper_BaseData(oJasperHash);
		
		oJasperHash.put(ENUM_PRINT_PARAMETERS.STORNO.Name()					, "Y" );
		
		
		// die Wiegekarte und die dazugehörigen Blätter
		String sFilename = "WK_STORNO_" + getIDWiegekarte().toString();
		
		oJasperHash.set_cDownloadAndSendeNameStaticPart(sFilename);
			
		m_v_jasper.add(oJasperHash);
		
		
					
		// drucken...
		if (bibMSG.get_bIsOK()) {
			
			if (doPrint()) {
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Storno-Beleg wurde gedruckt."));
				
			}
		}
			
	}

}
