package panter.gmbh.indep.pdf;

import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;

import com.lowagie.text.pdf.BaseFont;

import nextapp.echo2.app.Color;


public class PDF_Overlay_Factory_4_FreeText extends PDF_Overlay_Factory {

	
	public PDF_Overlay_Factory_4_FreeText() throws myException {
		super();
	}

	
	 public myTempFile generateCopyFromOriginal(String PDFFilename, String OverlayText, Color color) throws myException {
	   	int iFontSize = bibALL.get_RECORD_MANDANT().get_WASSERZEICHEN_FONTSIZE_lValue(new Long(60)).intValue();
    	String cWasserzeichenFont = bibALL.get_RECORD_MANDANT().get_WASSERZEICHEN_FONTNAME_cUF_NN(BaseFont.HELVETICA_BOLD);
    	float f_WasserzeichenRotate = 45f;
    	
    	//jetzt validieren
    	if (iFontSize >100) {iFontSize=100;}
    	if (iFontSize <0) 	{iFontSize=60;}
    	
    	if (!this.get_FontNames().contains(cWasserzeichenFont))  {
    		cWasserzeichenFont=BaseFont.HELVETICA_BOLD;
    	}
    	if (f_WasserzeichenRotate >360 || f_WasserzeichenRotate<1) {f_WasserzeichenRotate=270f;}
    	this.setImprintFontSize(iFontSize);
    	this.setImprintFontName(cWasserzeichenFont);
    	
    
    	this.setImprintColor(color.getRed(),
    						color.getGreen(),
    						color.getBlue());

    	if (S.isEmpty(OverlayText)) {
    		OverlayText =  bibALL.get_RECORD_MANDANT().get_WASSERZEICHEN_TEXT_cF_NN("KOPIE");
    	}
    	
    	
		 return super.generatePDFwithOverlayText(PDFFilename,  OverlayText ,1,f_WasserzeichenRotate);
	 }
}
