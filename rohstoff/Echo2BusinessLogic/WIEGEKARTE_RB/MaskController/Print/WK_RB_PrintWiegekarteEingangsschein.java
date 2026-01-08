package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print;

import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS.BorderCrossingReminder;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.WK_RB_MC_ContainerHandling;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;

public class WK_RB_PrintWiegekarteEingangsschein extends WK_RB_PrintBase {

	public WK_RB_PrintWiegekarteEingangsschein(RecDOWiegekarte recWK) {
		super(recWK);
		
	}

	public WK_RB_PrintWiegekarteEingangsschein(String idWiegekarte) throws myException {
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
		E2_JasperHASH oJasperHash = new E2_JasperHASH_STD("wiegekarte",new JasperFileDef_PDF());
		oJasperHash.set_bWarningOnEmptyPipelineResult(true);
		
		setJasper_BaseData(oJasperHash);
		oJasperHash.put(ENUM_PRINT_PARAMETERS.DRUCKE_WIEGEKARTE.Name()					, "Y" );
		String sFilename = "WK_" + getIDWiegekarte().toString();
		oJasperHash.set_cDownloadAndSendeNameStaticPart(sFilename);
		m_v_jasper.add(oJasperHash);
		

		
		// Jasperhash des Eingangsscheins
		E2_JasperHASH oJasperHashES = new E2_JasperHASH_STD("waage_eingangsschein",new JasperFileDef_PDF());
		oJasperHashES.set_bWarningOnEmptyPipelineResult(true);
		
		setJasper_BaseData(oJasperHashES);
		oJasperHashES.put(ENUM_PRINT_PARAMETERS.DRUCKE_EINGANGSSCHEIN_LIEFERSCHEIN.Name()	, "Y" );
		sFilename = (bIstLieferant ? "ES_" : "LS_") + getIDWiegekarte().toString();
		oJasperHashES.set_cDownloadAndSendeNameStaticPart(sFilename);
		m_v_jasper.add(oJasperHashES);

		
		
					
		// drucken...
		if (bibMSG.get_bIsOK()) {
			
			if (doPrint()) {
				
				// druckdatum anpassen
				updateWK_SetDruckdatum();
				
				// druckzähler anpassen
				try {
					incWKDruckzaehler(true);
					
				} catch (myException e1) {
					e1.printStackTrace();
				}
				
				// reload wiegekarte Record
				_recWK.REBUILD();
				
				// Nach dem Erzeugen des Reports wird geschaut, ob ein Archiveintrag der Wiegekarte vorhanden ist.
				// Falls ja, wird der verlinkt zu den Adressen und Fuhren.
				connectWKArchivToAdditionalRecords();
				
				// BORDERCROSSING-MELDUNGEN prüfen
				try {
					bibMSG.add_MESSAGE(new BorderCrossingReminder().check_and_generate_necessary_reminders_from_wiegekarte( getIDWiegekarte().toString()) );
				} catch (Exception e) {
					// sollte nie passieren
				}
				
				// Container-Prüfungen
				WK_RB_MC_ContainerHandling mcContainer = new WK_RB_MC_ContainerHandling(_recWK.rb_get_belongs_to());
				mcContainer.sendMessage_IfContainerTaraChanged(bibMSG.MV());
				mcContainer.sendMessage_IfUVVInvalid(bibMSG.MV());
				
				try {
					mcContainer.handleBuchungContainerstation(bibMSG.MV());
					mcContainer.sendMessage_IfContainerBookedMultiple(bibMSG.MV());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		}
			
	}

}
