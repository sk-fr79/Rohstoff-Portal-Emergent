package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print;

import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.WK_RB_MC_ContainerHandling;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;

public class WK_RB_PrintEingangLieferschein extends WK_RB_PrintBase {

	public WK_RB_PrintEingangLieferschein(RecDOWiegekarte recWK) {
		super(recWK);
		
	}

	public WK_RB_PrintEingangLieferschein(String idWiegekarte) throws myException {
		super(idWiegekarte);
		
	}

	@Override
	public void Print() throws myException {
		
		String sIstLieferant 	= _recWK.get_ufs_dbVal(WIEGEKARTE.ist_lieferant, "N");
		boolean bIstLieferant 	= sIstLieferant.equalsIgnoreCase("Y");
		
		// Eingangsschein-Nr erzeugen, wenn nötig
		updateWK_SetEingangsscheinNr();
		
		// Eingangsschein-Zähler setzen, bzw. erhöhen
		incWKESZaehler(true);

		// drucken der Wiegekarte(n)
		E2_JasperHASH oJasperHash = new E2_JasperHASH_STD("waage_eingangsschein",new JasperFileDef_PDF());
		oJasperHash.set_bWarningOnEmptyPipelineResult(true);
			
		// alle Jasper-Parameter aus der Wiegekarte setzen 
		setJasper_BaseData(oJasperHash);
		
		// Standard-JasperHash settings überschreiben
		oJasperHash.put(ENUM_PRINT_PARAMETERS.DRUCKE_EINGANGSSCHEIN_LIEFERSCHEIN.Name()	, "Y" );

		String sFilename = (bIstLieferant ? "ES_" : "LS_") + getIDWiegekarte().toString();
		
		oJasperHash.set_cDownloadAndSendeNameStaticPart(sFilename);
			
		m_v_jasper.add(oJasperHash);

		
		// wenn alles ok...
		if (bibMSG.get_bIsOK()) {
			// drucken...
			if (doPrint()) {
				// Nach dem Erzeugen des Reports wird geschaut, ob ein Archiveintrag der Wiegekarte vorhanden ist.
				// Falls ja, wird der verlinkt zu den Adressen und Fuhren.
				connectWKArchivToAdditionalRecords();
				
				// Container-Prüfungen
				WK_RB_MC_ContainerHandling mcContainer = new WK_RB_MC_ContainerHandling(_recWK.rb_get_belongs_to());
				mcContainer.sendMessage_IfContainerTaraChanged(bibMSG.MV());
				mcContainer.sendMessage_IfUVVInvalid(bibMSG.MV());
				
			}
		}
		
			
	}

}
