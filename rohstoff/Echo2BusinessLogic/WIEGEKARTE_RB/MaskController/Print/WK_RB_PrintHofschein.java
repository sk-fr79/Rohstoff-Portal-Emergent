package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print;

import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;

public class WK_RB_PrintHofschein extends WK_RB_PrintBase {

	public WK_RB_PrintHofschein(RecDOWiegekarte recWK) {
		super(recWK);
		
	}

	public WK_RB_PrintHofschein(String idWiegekarte) throws myException {
		super(idWiegekarte);
		
	}

	@Override
	public void Print() throws myException {

		// es gibt 2 Hofscheine: WE und WA
		String sIstLieferant 	= _recWK.get_ufs_dbVal(WIEGEKARTE.ist_lieferant, "N");
		boolean bIstLieferant 	= sIstLieferant.equalsIgnoreCase("Y");
		
		String sReportName = "waage_hofschein" + (bIstLieferant ? "_WE" : "_WA");
		
		// drucken der Wiegekarte(n)
		E2_JasperHASH oJasperHash = new E2_JasperHASH_STD(sReportName,new JasperFileDef_PDF());
		oJasperHash.set_bWarningOnEmptyPipelineResult(true);
		
			
		// alle Jasper-Parameter aus der Wiegekarte setzen 
		setJasper_BaseData(oJasperHash);
		
		// die Wiegekarte und die dazugehörigen Blätter
		String sFilename = "Hofschein_"  + (bIstLieferant ? "WE_" : "WA_") + getIDWiegekarte().toString();
		
		oJasperHash.set_cDownloadAndSendeNameStaticPart(sFilename);
			
		m_v_jasper.add(oJasperHash);
					
		// drucken...
		if (doPrint()) {
			bibMSG.MV()._addInfo(new MyString("Hofschein wurde gedruckt!"));
		}
			
	}

}
