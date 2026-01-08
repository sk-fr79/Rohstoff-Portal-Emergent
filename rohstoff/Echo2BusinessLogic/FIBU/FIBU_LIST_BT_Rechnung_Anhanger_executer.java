package rohstoff.Echo2BusinessLogic.FIBU;

import java.io.File;
import java.util.Collections;
import java.util.Vector;

import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_CONST.EXECUTER_JUMPPOINTS;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_ROOT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperTempFile_and_pipePos_VECT;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN.__RECORD_FIBU_SPECIAL;

public class FIBU_LIST_BT_Rechnung_Anhanger_executer extends Jasper_Exe_ROOT {


	private FIBU_LIST_BT_IF_Druck_Mail_Common parent = null;

	public FIBU_LIST_BT_Rechnung_Anhanger_executer(FIBU_LIST_BT_IF_Druck_Mail_Common parentContainer) {
		this.parent = parentContainer;
	}

	@Override
	public void EXECUTE(E2_JasperHASH oJasperHash, Object objZusatz, MyE2_MessageVector omvRueck, Object oSammlerRueckgaben)	throws myException {
		MyE2_MessageVector  oMV = new MyE2_MessageVector();

		if (this.parent.mussRechnungAnhaegen())
		{
			Vector<String> recList = this.parent.getSelectedFibuList();

			Vector<String> vFilenamesOfArchiveFiles = new Vector<String>();

			Collections.sort(recList);

			for (int i =0; i<recList.size();i++ )
			{
				RECORD_FIBU recFibu = new RECORD_FIBU(recList.get(i));
				if (recFibu.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_DRUCK_GUTSCHRIFT) ||
						recFibu.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_DRUCK_RECHNUNG))
				{
					String cPfadZumArchiv = new __RECORD_FIBU_SPECIAL(recFibu).get__Pfad_Zu_Archivdatei();

					if (S.isEmpty(cPfadZumArchiv))
					{
						//wenn die archivdateien aufgerufen werden, dann muessen fuer alle rechnungen eine archivdatei vorhanden sein,
						//sonst fehler
						oMV.add_MESSAGE(new MyE2_Alarm_Message("Für mindestens einen Belegt innerhalb der gemahnten Belege ist kein Eintrag im Archiv vorhanden !!"));
					}

					if (S.isFull(cPfadZumArchiv))
					{
						if (new File(cPfadZumArchiv).exists())
						{
							vFilenamesOfArchiveFiles.add(cPfadZumArchiv);
						}
					}
				}
			} 

			Vector<String> vFileNames = new Vector<String>();
			vFileNames.addAll(vFilenamesOfArchiveFiles); 

			E2_JasperTempFile_and_pipePos_VECT standardPipeVect 	= oJasperHash.get_jasperTempFile_and_pipePos_VECT_standard();
			E2_JasperTempFile_and_pipePos_VECT directPrintPipeVect 	= oJasperHash.get_jasperTempFile_and_pipePos_VECT_DirektDruck();
			E2_JasperTempFile_and_pipePos_VECT archivePipeVect 		= oJasperHash.get_jasperTempFile_and_pipePos_VECT_Archiv();
			E2_JasperTempFile_and_pipePos_VECT controlMailPipeVect 	= oJasperHash.get_jasperTempFile_and_pipePos_VECT_KontrollMail();

			if(standardPipeVect.size()>0){
				standardPipeVect.get(0).get_oJasperTempFile().append_pdf(vFilenamesOfArchiveFiles);
			}
			if(directPrintPipeVect.size()>0){
				directPrintPipeVect.get(0).get_oJasperTempFile().append_pdf(vFilenamesOfArchiveFiles);
			}
			if(archivePipeVect.size()>0){
				archivePipeVect.get(0).get_oJasperTempFile().append_pdf(vFilenamesOfArchiveFiles);
			}
			if(controlMailPipeVect.size()>0){
				controlMailPipeVect.get(0).get_oJasperTempFile().append_pdf(vFilenamesOfArchiveFiles);
			}
		}

		bibMSG.add_MESSAGE(oMV);

	}

	@Override
	public EXECUTER_JUMPPOINTS get_JUMPMarker() {
		return EXECUTER_JUMPPOINTS.JUMPPOINT_AFTER_PROCESS_ALL_TEMPFILES;
	}

}
