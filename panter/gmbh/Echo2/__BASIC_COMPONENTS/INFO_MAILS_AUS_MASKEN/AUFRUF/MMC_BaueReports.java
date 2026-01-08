package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.AUFRUF;

import java.util.Vector;

import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MAIL_AUS_MASK_JASPER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MAIL_AUS_MASK;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MAIL_AUS_MASK_JASPER;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class MMC_BaueReports {

	private RECORD_MAIL_AUS_MASK  			recMailAusMask=	null;
	private MMC_ERSETZUNGS_HASH   			oErsetzungsHash = null;
	
	private Vector<E2_JasperHASH>           vJasperHash = new Vector<E2_JasperHASH>();

	public MMC_BaueReports(	RECORD_MAIL_AUS_MASK 			rec_MailAusMask,
							MMC_ERSETZUNGS_HASH   			o_ErsetzungsHash) throws myException {
		super();
		this.recMailAusMask = rec_MailAusMask;
		this.oErsetzungsHash = o_ErsetzungsHash;

		this.vJasperHash.removeAllElements();
		
		RECLIST_MAIL_AUS_MASK_JASPER  rlJASPERS = this.recMailAusMask.get_DOWN_RECORD_LIST_MAIL_AUS_MASK_JASPER_id_mail_aus_mask(
																					null,_DB.MAIL_AUS_MASK_JASPER$DOWNLOADNAME,true);
		
		if (rlJASPERS.get_vKeyValues().size()>0) {
			for (int i=0;i<rlJASPERS.get_vKeyValues().size();i++) {
				RECORD_MAIL_AUS_MASK_JASPER  recJasper = rlJASPERS.get(i);
				
				E2_JasperHASH_STD  oJasper = new E2_JasperHASH_STD(recJasper.get_REPORTNAME_cUF(), new JasperFileDef_PDF());
				oJasper.set_TYPE_MAIL();

				this.vJasperHash.add(oJasper);
				
				//jetzt die 20 moeglichen paare parameter/wert an den jasperhash uebergeben (der wert wird ueber den ersetzungsHash von #WERT# - eintraegen befreit)  
				for (int k=1;k<=20;k++) {
					String cHelp = ""+k;
					if (cHelp.length()==1) {cHelp = "0"+cHelp;}
					String cPARAM = "PARAMETER"+cHelp;
					String cVAL = "WERT"+cHelp;

					if (S.isFull(recJasper.get_UnFormatedValue(cPARAM)) && S.isFull(recJasper.get_UnFormatedValue(cVAL)) ) {
						oJasper.put(recJasper.get_UnFormatedValue(cPARAM), this.oErsetzungsHash.get_Ersetzungstext(recJasper.get_UnFormatedValue(cVAL)));
					}
				}

				oJasper.set_bShowErrorMessageWhenResultIsEmpty(true);
				oJasper.set_bEliminateEmptyJasperTempFiles(true);
				
				oJasper.Build_tempFile(false);      //temporaere datei mit mail-kenner erzeugen
			}
		}
	}

	public Vector<E2_JasperHASH> get_vJasperHash() {
		return vJasperHash;
	}

	
}
