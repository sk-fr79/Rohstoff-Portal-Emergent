package panter.gmbh.indep.pdf;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;

import com.lowagie.text.pdf.BaseFont;

public class PDF_Overlay_Factory_4_OriginalDownload extends PDF_Overlay_Factory {

	
	public PDF_Overlay_Factory_4_OriginalDownload() throws myException {
		super();
	}

	
	 public myTempFile generateCopyFromOriginal(String PDFFilename) throws myException {
	   	int iFontSize = bibALL.get_RECORD_MANDANT().get_WASSERZEICHEN_FONTSIZE_lValue(new Long(60)).intValue();
    	String cWasserzeichenFont = bibALL.get_RECORD_MANDANT().get_WASSERZEICHEN_FONTNAME_cUF_NN(BaseFont.HELVETICA_BOLD);
    	float f_WasserzeichenRotate = bibALL.get_RECORD_MANDANT().get_WASSERZEICHEN_ROTATE_dValue(270d).floatValue();
    	
    	//jetzt validieren
    	if (iFontSize >100) {iFontSize=100;}
    	if (iFontSize <0) 	{iFontSize=60;}
    	
    	if (!this.get_FontNames().contains(cWasserzeichenFont))  {
    		cWasserzeichenFont=BaseFont.HELVETICA_BOLD;
    	}
    	if (f_WasserzeichenRotate >360 || f_WasserzeichenRotate<1) {f_WasserzeichenRotate=270f;}
    	this.setImprintFontSize(iFontSize);
    	this.setImprintFontName(cWasserzeichenFont);

		 return super.generatePDFwithOverlayText(PDFFilename,  bibALL.get_RECORD_MANDANT().get_WASSERZEICHEN_TEXT_cF_NN("KOPIE"),1,f_WasserzeichenRotate);
	 }
}
