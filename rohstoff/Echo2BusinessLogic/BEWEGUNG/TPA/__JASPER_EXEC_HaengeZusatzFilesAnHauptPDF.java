package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.apache.commons.io.FileUtils;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_CONST.EXECUTER_JUMPPOINTS;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_ROOT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperTempFile_and_pipePos;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperTempFile_and_pipePos_VECT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperTempFile;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.pdf.pdfConcat;

public class __JASPER_EXEC_HaengeZusatzFilesAnHauptPDF extends Jasper_Exe_ROOT {

	@Override
	public void EXECUTE(E2_JasperHASH oJasperHash, Object objZusatz, MyE2_MessageVector oMV_Rueck, Object oObjectRueckgabe) throws myException {
		
//		//hier wird die BST___JasperHashLIEFERSCHEIN_multi-Temp-PDF um die 
//		E2_JasperFileProcessor_PART_VECTOR  vJasperFileProcessor_Part = (E2_JasperFileProcessor_PART_VECTOR)objZusatz;
		
		//hier alle E2_JasperFileProcessor_PART_VECTOR - en, die in der jasperhash stehen, beruecksichtigen
		Vector<E2_JasperTempFile_and_pipePos_VECT> vAlleJasperPartVectoren = new Vector<E2_JasperTempFile_and_pipePos_VECT>();
		vAlleJasperPartVectoren.add(oJasperHash.get_jasperTempFile_and_pipePos_VECT_standard());
		vAlleJasperPartVectoren.add(oJasperHash.get_jasperTempFile_and_pipePos_VECT_DirektDruck());
		vAlleJasperPartVectoren.add(oJasperHash.get_jasperTempFile_and_pipePos_VECT_Archiv());
		vAlleJasperPartVectoren.add(oJasperHash.get_jasperTempFile_and_pipePos_VECT_KontrollMail());
		
		for (E2_JasperTempFile_and_pipePos_VECT vJasperFileProcessor_Part: vAlleJasperPartVectoren) {
		//jetzt den vector nach einem BST___JasperHashLIEFERSCHEIN_multi durchsuchen
			for (E2_JasperTempFile_and_pipePos oJasperPart: vJasperFileProcessor_Part) {
				if (oJasperPart.get_oJasperTempFile().get_oJasperHash() instanceof BST___JasperHashLIEFERSCHEIN_multi) {
					
					BST___JasperHashLIEFERSCHEIN_multi oJasperHashLieferscheinMulti = (BST___JasperHashLIEFERSCHEIN_multi)oJasperPart.get_oJasperTempFile().get_oJasperHash();
					Vector<BST___JasperHash_NUR_EU_BLAETTER> vEUBlaetter = oJasperHashLieferscheinMulti.get_vJasperHashAnhang7();
					
					Vector<E2_JasperTempFile>  vTempFilesWithAnhang7 = new Vector<E2_JasperTempFile>();
					
					//jetzt die anhang7-dokus bauen
					for (BST___JasperHash_NUR_EU_BLAETTER oJasperEU: vEUBlaetter) {
						vTempFilesWithAnhang7.add(new E2_JasperTempFile(oJasperEU, null, null, null, null, null));
					}
	  
					Vector<String>  vFilenamesPDF = new Vector<String>();
					vFilenamesPDF.add(oJasperPart.get_oJasperTempFile().get_NameOfCreatedFile());
					for (E2_JasperTempFile oTempFile: vTempFilesWithAnhang7) {
						vFilenamesPDF.add(oTempFile.get_NameOfCreatedFile());
					}
					
					pdfConcat  oConcater = new pdfConcat(vFilenamesPDF);
					myTempFile oTempfileNew = oConcater.baueZielDatei("lieferschein_multi");
					oTempfileNew.set_bDeleteAtFinalize(true);
	
					try {
						//jetzt die neue datei auf die alte kopieren (kuckucksei-prinzip)
						FileUtils.copyFile(new File(oTempfileNew.getFileName()),new File(oJasperPart.get_oJasperTempFile().get_NameOfCreatedFile()));
						
						DEBUG.System_println(" ");
						DEBUG.System_println("======================");
						DEBUG.System_println("Quelle: "+oTempfileNew.getFileName());
						DEBUG.System_println("Ziel: "+oJasperPart.get_oJasperTempFile().get_NameOfCreatedFile());
						DEBUG.System_println("======================");
						
					} catch (IOException e) {
						e.printStackTrace();
						throw new myException(this,"Error building output-file");
					}
					
					DEBUG.System_print(vFilenamesPDF);
				}
			}
			}
		
	}

	@Override
	public EXECUTER_JUMPPOINTS get_JUMPMarker() {
		return EXECUTER_JUMPPOINTS.JUMPPOINT_AFTER_PROCESS_ALL_TEMPFILES;
	}

}
