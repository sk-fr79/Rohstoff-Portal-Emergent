
package panter.gmbh.indep.pdf;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PRAcroForm;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.SimpleBookmark;

/**
* uebernommen von www.lowagie.com / autor von iText
 * */
public class pdfConcat
{
    
    Vector<String> vPDFNames = null;
//    HttpSession oSES  = null;
    
//    public pdfConcat(Vector<String> vpDFNames,HttpSession oses)
//    {
//        this.vPDFNames = vpDFNames;
////        this.oSES = oses;
//    }
    
    public pdfConcat(Vector<String> vpDFNames)
    {
        this.vPDFNames = vpDFNames;
//        this.oSES = bibE2.get_CurrSession();
    }
    

    
    
    
    @SuppressWarnings("unchecked")
	public myTempFile baueZielDatei(String NamensBasis) throws myException
    {
//        String cRueck = "";
        
        Document document = null;
        PdfCopy  writer = null;
        myTempFile oTempFile = null;
        FileOutputStream oFileOut = null;

        try {
            int pageOffset = 0;
            ArrayList<List> master = new ArrayList<List>();
            int f = 0;
            
            oTempFile = new myTempFile(NamensBasis,".pdf",true);
            
            
            while (f < this.vPDFNames.size()) 
            {
                // we create a reader for a certain document
                PdfReader reader = new PdfReader((String)this.vPDFNames.get(f));
                reader.consolidateNamedDestinations();
                // we retrieve the total number of pages
                int n = reader.getNumberOfPages();
                List bookmarks = SimpleBookmark.getBookmark(reader);
                if (bookmarks != null) 
                {
                    if (pageOffset != 0)
                        SimpleBookmark.shiftPageNumbers(bookmarks, pageOffset, null);
                    master.addAll(bookmarks);
                }
                pageOffset += n;
                
                if (f == 0) 
                {
                    // step 1: creation of a document-object
                    document = new Document(reader.getPageSizeWithRotation(1));
                    
                    // step 2: we create a writer that listens to the document
                    oFileOut = new FileOutputStream(oTempFile.getFile());
                    writer = new PdfCopy(document,oFileOut);
                   
                    // step 3: we open the document
                    document.open();
                }
                
                // step 4: we add content
                PdfImportedPage page;
                for (int i = 0; i < n; ) 
                {
                    ++i;
                    page = writer.getImportedPage(reader, i);
                    writer.addPage(page);
                    // bibALL.System_println("Processed page " + i);
                }
                PRAcroForm form = reader.getAcroForm();
                if (form != null)
                {
                    writer.copyAcroForm(reader);
                }
                
                f++;
            }
            
            
            if (master.size() > 0)
            {
                writer.setOutlines(master);
            }
            
            /*
             * rueckgabename definieren
             */
           // cRueck = oTempFile.getFileName();
            
            // step 5: we close the document
            if (document != null) {
            	document.close();
            }
        }
        catch(Exception e) 
        {
        	e.printStackTrace();
            throw new myException("Fehler beim Verbinden der PDF-Dokumente ..");
        }
        finally
        {
            if (writer != null)  writer.close();
            if (oFileOut != null)
            {
                try 
                {
                    oFileOut.close();
                }
                catch (Exception ex) {}
            }
               
            if (oTempFile != null) oTempFile.close();
        }
        
        return oTempFile;
        
    }
}

