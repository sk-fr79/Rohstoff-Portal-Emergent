package panter.gmbh.Echo2.__BASIC_COMPONENTS.SCANNER;

import java.io.File;
import java.util.Collection;
import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.pdf.pdfConcat;

public abstract class SCAN_BUTTON_ABSTRACT0 extends MyE2_Button {
	
	
	public abstract XX_ActionAgent get_ActionAgent() throws myException;
	
	private RECORD_SCANNER_SETTINGS_SPECIAL  recScan4ThisButton = null;

	public SCAN_BUTTON_ABSTRACT0() throws myException {
		super(E2_ResourceIcon.get_RI("scanner.png"),E2_ResourceIcon.get_RI("scanner__.png"));
		this.add_oActionAgent(this.get_ActionAgent());
	}

	public SCAN_BUTTON_ABSTRACT0(MyString cButtonText) throws myException {
		super(cButtonText);
		this.add_oActionAgent(this.get_ActionAgent());
	}

	public SCAN_BUTTON_ABSTRACT0(RECORD_SCANNER_SETTINGS_SPECIAL  rec_Scan4ThisButton) throws myException {
		super(new MyE2_String(rec_Scan4ThisButton.get_SCANNER_NAME_cUF(),false));
		this.set_recScan4ThisButton(rec_Scan4ThisButton);
		this.add_oActionAgent(this.get_ActionAgent());
	}

	public void set_ToolTip(MyE2_String cToolTips) {
		this.setText(cToolTips.CTrans());
	}
	
	
	public RECORD_SCANNER_SETTINGS_SPECIAL get_recScan4ThisButton() {
		return recScan4ThisButton;
	}
	
	
	/**
	 * 
	 * @return ending of scanfile (when RECORD_SCANNER_SETTINGS_SPECIAL==null then pdf, else value from RECORD_SCANNER_SETTINGS_SPECIAL)
	 * @throws myException
	 */
	public String get_FileEnding() throws myException {
		if (this.get_recScan4ThisButton()!=null) {
			return "pdf";
		} else {
			return this.get_recScan4ThisButton().get_FILETYPE_cUF_NN("PDF").toLowerCase();  //ist ein NotNull-Feld, damit immer definiert
		}
	}

	public void set_recScan4ThisButton(RECORD_SCANNER_SETTINGS_SPECIAL recScan4ThisButton) {
		this.recScan4ThisButton = recScan4ThisButton;
	}

	
	
	/**
	 * hilfsmethode, kann zurueckgegeben mehrfache pfds concatenieren und zu einer bennannten datei zurueckgeben
	 * @param vResultsFileNamesWithoutPath
	 * @param cBaseNameOfTarget
	 * @return
	 * @throws myException
	 */
	public myTempFile  get_ConcatenatedTempFilePdf(Collection<String> vResultsFileNamesWithPath, String cBaseNameOfTarget) throws myException {
		//jetzt die moeglichen pdf-dokumente verknuepfen zu einem Temp-file
		Vector<String> vFileNamesWithPath = new Vector<String>(vResultsFileNamesWithPath);
		
		myTempFile  tfRueck = null;
		
		if (vFileNamesWithPath.size()>0) {
			pdfConcat  oConcat = new pdfConcat(vFileNamesWithPath);
			
			tfRueck = oConcat.baueZielDatei(cBaseNameOfTarget);
		}
		
		return tfRueck;
	}
	
	
	/**
	 * hilfsmethode, um die vom scan-frontend uebertragenen scanfiles zu loeschen
	 * @param vResultsFileNamesWithPath (im OUTPUT-ordner)
	 * @return
	 * @throws myException
	 */
	public void  cleanTempFiles(Collection<String> vResultsFileNamesWithPath) throws myException {

		//jetzt die moeglichen pdf-dokumente verknuepfen zu einem Temp-file
		for (String cNameWithPath: vResultsFileNamesWithPath){
			File file = new File(cNameWithPath);
			file.delete();
		}
	
	}
	

	
	
	
}
