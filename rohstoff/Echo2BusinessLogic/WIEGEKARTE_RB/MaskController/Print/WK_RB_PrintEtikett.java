package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print;

import java.math.BigDecimal;
import java.math.RoundingMode;

import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_ABZUG_GEB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecList_WK_Gebinde;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;

public class WK_RB_PrintEtikett extends WK_RB_PrintBase {

	int _nCopies = -1;
	
	public WK_RB_PrintEtikett(RecDOWiegekarte recWK) {
		this(recWK,-1);
	}
	
	public WK_RB_PrintEtikett(RecDOWiegekarte recWK, int nCopies) {
		super(recWK);
		_nCopies = nCopies;
	}

	public WK_RB_PrintEtikett(String idWiegekarte, int nCopies) throws myException {
		super(idWiegekarte);
		_nCopies = nCopies;
		
	}

	@Override
	public void Print() throws myException {
		
		BigDecimal bdCount = BigDecimal.ZERO;
		RecList_WK_Gebinde recListGebinde = null;
		
		// prüfen, wieviele Etiketten gedruckt werden sollen....
		String  gueterkategorie  = _recWK.get_ufs_dbVal(WIEGEKARTE.gueterkategorie, "");
		
		
		if (S.isFull(gueterkategorie) && _nCopies < 0 ) {
			
			if (gueterkategorie.equals(WK_RB_CONST.ENUM_Gueterkategorie.SCHUETTGUT.db_val())) {
				_nCopies = 1;
				
				E2_JasperHASH oJasperHash = new E2_JasperHASH_STD("wiegekarte_etikett",new JasperFileDef_PDF());
				oJasperHash.set_bWarningOnEmptyPipelineResult(true);
				
				// alle Jasper-Parameter aus der Wiegekarte setzen 
				setJasper_BaseData(oJasperHash);

				oJasperHash.put(ENUM_PRINT_PARAMETERS.NUM_COPY.Name()			, "1" );
				oJasperHash.put(ENUM_PRINT_PARAMETERS.NUM_COPY_GESAMT.Name()	, "1" );
				m_v_jasper.add(oJasperHash);
								
			} else {
				
				// finden, wieviele Container es gibt...
				recListGebinde = new RecList_WK_Gebinde(_recWK.getActualID().toString());
				for (Rec22 wk_geb: recListGebinde) {
					// Zählen der Gebinde
					BigDecimal bdMenge = wk_geb.get_raw_resultValue_bigDecimal(WIEGEKARTE_ABZUG_GEB.menge,BigDecimal.ZERO);
					bdCount = bdCount.add(bdMenge);
				}
				bdCount = bdCount.setScale(0, RoundingMode.HALF_UP);
				_nCopies = bdCount.intValue();
				
				int nCount = 1;
				
				for (Rec22 wk_geb: recListGebinde) {
					// Zählen der Gebinde
					BigDecimal bdMenge = wk_geb.get_raw_resultValue_bigDecimal(WIEGEKARTE_ABZUG_GEB.menge,BigDecimal.ZERO);
					bdMenge = bdMenge.setScale(0, RoundingMode.HALF_UP);
					int nGebindeGesamt = bdMenge.intValue();
					

					for (int i=1; i<=nGebindeGesamt; i++) {
						// drucken der Wiegekarte(n)
						E2_JasperHASH oJasperHash = new E2_JasperHASH_STD("wiegekarte_etikett",new JasperFileDef_PDF());
						
						// alle Jasper-Parameter aus der Wiegekarte setzen 
						setJasper_BaseData(oJasperHash);
						oJasperHash.put(ENUM_PRINT_PARAMETERS.NUM_COPY_GEBINDE.Name()			, Integer.toString(i) );
						oJasperHash.put(ENUM_PRINT_PARAMETERS.NUM_COPY_GEBINDE_GESAMT.Name()	, Integer.toString(nGebindeGesamt) );
						oJasperHash.put(ENUM_PRINT_PARAMETERS.ID_ABZUG_GEBINDE.Name() 			, wk_geb.get_ufs_dbVal(WIEGEKARTE_ABZUG_GEB.id_wiegekarte_abzug_geb, ""));
						
						oJasperHash.put(ENUM_PRINT_PARAMETERS.NUM_COPY.Name()			, Integer.toString(nCount++) );
						oJasperHash.put(ENUM_PRINT_PARAMETERS.NUM_COPY_GESAMT.Name()	, Integer.toString(_nCopies) );
						
						m_v_jasper.add(oJasperHash);
					}
				}
				
			}
		} else {
			// es ist eine Zahl angegeben!! oder es gibt keine güterkategorie
			if (_nCopies > 0) {
				for (int i=1; i<=_nCopies; i++) {
					// drucken der Wiegekarte(n)
					E2_JasperHASH oJasperHash = new E2_JasperHASH_STD("wiegekarte_etikett",new JasperFileDef_PDF());
					
					// alle Jasper-Parameter aus der Wiegekarte setzen 
					setJasper_BaseData(oJasperHash);
					
					oJasperHash.put(ENUM_PRINT_PARAMETERS.NUM_COPY.Name()			, Integer.toString(i) );
					oJasperHash.put(ENUM_PRINT_PARAMETERS.NUM_COPY_GESAMT.Name()	, Integer.toString(_nCopies) );
					
					m_v_jasper.add(oJasperHash);
				}
			} else {
				bibMSG.MV()._addInfo("Es wurde keine Anzahl von Etikett-Kopien angegeben.");
			}
		}

		
		
					
		// drucken...
		if (doPrint()) {
			bibMSG.MV()._addInfo(String.format(gueterkategorie + ": Es wurden %s Etiketten gedruckt!",_nCopies));
		}
			
	}
	
	
	
	
	

}
