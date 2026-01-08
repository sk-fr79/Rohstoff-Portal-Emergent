/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 10.02.2020
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PRAcroForm;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.SimpleBookmark;

import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 10.02.2020
 *
 */
public class PdServiceConcatenatePdfs {

	/**
	 * @author martin
	 * @date 10.02.2020
	 *
	 */
	public PdServiceConcatenatePdfs() {
	}

	
	@SuppressWarnings("rawtypes")
	public myTempFile getConcatenatedFile(VEK<String> pdfPaths, String baseName) {
			
		Document 			document = null;
        PdfCopy  			writer = null;
        myTempFile 			oTempFile = null;
        FileOutputStream 	oFileOut = null;

        try {
            int pageOffset = 0;
            ArrayList<List> master = new ArrayList<List>();
            int f = 0;
            
            oTempFile = new myTempFile(baseName,".pdf",true);
            
            while (f < pdfPaths.size())   {

            	PdfReader reader = new PdfReader((String)pdfPaths.get(f));
                reader.consolidateNamedDestinations();

                int n = reader.getNumberOfPages();
                List bookmarks = SimpleBookmark.getBookmark(reader);
                
                if (bookmarks != null)  {
                    if (pageOffset != 0) {
                        SimpleBookmark.shiftPageNumbers(bookmarks, pageOffset, null);
                    }
                    master.addAll(bookmarks);
                }
                pageOffset += n;
                
                if (f == 0) {

                    document = new Document(reader.getPageSizeWithRotation(1));

                    oFileOut = new FileOutputStream(oTempFile.getFile());
                    writer = new PdfCopy(document,oFileOut);

                    document.open();
                }
                
                PdfImportedPage page;
                for (int i = 0; i < n; )    {
                    ++i;
                    page = writer.getImportedPage(reader, i);
                    writer.addPage(page);

                }

                PRAcroForm form = reader.getAcroForm();
                if (form != null) {
                    writer.copyAcroForm(reader);
                }
                
                f++;
            }
            
            
            if (master.size() > 0)  {
                writer.setOutlines(master);
            }
            
            
            if (document != null) {
            	document.close();
            }
        } catch(Exception e) {
        	e.printStackTrace();
        	return null;
        } finally {
            if (writer != null)  writer.close();
            if (oFileOut != null) {
                try {
                    oFileOut.close();
                }
                catch (Exception ex) {
                	ex.printStackTrace();
                }
            }
            if (oTempFile != null) oTempFile.close();
        }
        
        return oTempFile;

	}

	
	public void startDownload(VEK<String> pdfPaths, String downloadNameWithoutEnding) throws myException {
		myTempFile tempFile = getConcatenatedFile(pdfPaths, downloadNameWithoutEnding);
		if (tempFile==null) {
			throw new myException("Error concatenate pdfs <7e94c9ae-4c00-11ea-b77f-2e728ce88125>");
		} else {
			tempFile.starteDownLoad(downloadNameWithoutEnding+".pdf", "application/pdf");
		}
	}
	
	
	
}
