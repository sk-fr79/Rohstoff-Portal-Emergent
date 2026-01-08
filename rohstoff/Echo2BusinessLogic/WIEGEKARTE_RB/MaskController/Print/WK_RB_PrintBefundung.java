package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print;

import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarteBefund;

public class WK_RB_PrintBefundung extends WK_RB_PrintBase {

	
	RecDOWiegekarteBefund _DOBefund = null;
	
	public WK_RB_PrintBefundung(RecDOWiegekarte recWK) {
		super(recWK);
		_DOBefund = _recWK._get_Befundung();
	}

	public WK_RB_PrintBefundung(String idWiegekarte) throws myException {
		super(idWiegekarte);
		_DOBefund = _recWK._get_Befundung();
	}

	@Override
	public void Print() throws myException {

		if (_DOBefund == null) {
			bibMSG.MV()._addInfo("Es gibt keine Daten zur Befundung");
			return;
		}
		
		
		
		
		
		
		String sReportName = "waage_befundung";
		
		// drucken der Wiegekarte(n)
		E2_JasperHASH oJasperHash = new E2_JasperHASH_STD(sReportName,new JasperFileDef_PDF());
		oJasperHash.set_bWarningOnEmptyPipelineResult(true);
			
		// alle Jasper-Parameter aus der Wiegekarte setzen 
		setJasper_BaseData(oJasperHash);
		
		// die Wiegekarte und die dazugehörigen Blätter
		String sFilename = "Befundung_WK_" + getIDWiegekarte().toString() + "_BEF_"  + getIDWiegekarteBefund().toString();
		
		oJasperHash.set_cDownloadAndSendeNameStaticPart(sFilename);
			
		m_v_jasper.add(oJasperHash);
					
		// drucken...
		if (doPrint()) {
			
			// druckdatum anpassen
			updateWK_SetBefundungDruckdatum();
			
			// TODO: Erzeugter Befundungsauftrag an die Wiegekarte hängen
			// connectWKArchivToAdditionalRecords();
			//
			
			bibMSG.MV()._addInfo(new MyString("Befundung wurde gedruckt!"));
		}
			
	}

}
