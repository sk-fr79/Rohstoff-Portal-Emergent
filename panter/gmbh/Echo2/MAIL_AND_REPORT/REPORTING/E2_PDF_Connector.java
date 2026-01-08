package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.pdf.pdfConcat;

public class E2_PDF_Connector {


	private myTempFile  oTempFile = null;

	
	public E2_PDF_Connector(Vector<FileWithSendName> vConcatenateFiles, boolean bAutoDel, String cName4TargetFile) throws myException {
		super();
		
		Vector<String> vFileNames = new Vector<String>();
		for (FileWithSendName oFile: vConcatenateFiles) {
			vFileNames.add(oFile.get_cNameWithPath());
		}
		
		//wenn der targetname mit .pdf endet, dann wegmachen
		if (cName4TargetFile.toUpperCase().endsWith(".PDF") && cName4TargetFile.length()>4) {
			cName4TargetFile = cName4TargetFile.substring(0,cName4TargetFile.length()-4);
		}
		
	    pdfConcat oConcat = new pdfConcat(vFileNames);
	    this.oTempFile  = oConcat.baueZielDatei(cName4TargetFile);
	    this.oTempFile.set_bDeleteAtFinalize(bAutoDel);
		
	}



	public myTempFile get_oTempFileAutoDel() {
		return this.oTempFile;
	}
	
	
}
