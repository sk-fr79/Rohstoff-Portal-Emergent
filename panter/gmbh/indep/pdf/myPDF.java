package panter.gmbh.indep.pdf;

import java.awt.Color;
import java.awt.Point;
import java.io.FileOutputStream;
import java.util.Vector;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;



public class myPDF
{
    public static final int ALIGN_LEFT = Element.ALIGN_LEFT;
    public static final int ALIGN_CENTER = Element.ALIGN_CENTER;
    public static final int ALIGN_RIGHT = Element.ALIGN_RIGHT;
    public static final int ALIGN_BOTTOM = Element.ALIGN_BOTTOM;
    public static final int ALIGN_BASELINE = Element.ALIGN_BASELINE;
    public static final int ALIGN_MIDDLE = Element.ALIGN_MIDDLE;
    public static final int ALIGN_TOP = Element.ALIGN_TOP;
    public static final int BORDER_LEFT = Rectangle.LEFT;
    public static final int BORDER_RIGHT = Rectangle.RIGHT;
    public static final int BORDER_TOP = Rectangle.TOP;
    public static final int BORDER_BOTTOM = Rectangle.BOTTOM;
    public static final int BORDER_NONE = Rectangle.NO_BORDER;
    public static final int BORDER_BOX = Rectangle.BOX;

    public static final Color COLOR_PDF_BLACK = Color.BLACK;
    public static final Color COLOR_PDF_WHITE = Color.WHITE;
    
    // definitionsflags für das format
    public static final Rectangle FORMAT_A4_HOCH = PageSize.A4;
    public static final Rectangle FORMAT_A4_QUER = PageSize.A4.rotate();
    public static final Rectangle FORMAT_A3_HOCH = PageSize.A3;
    public static final Rectangle FORMAT_A3_QUER = PageSize.A3.rotate();
    
    
    private Vector<myPDF.BenannteTabelle> Tabellen = new Vector<BenannteTabelle>(); // diesem vector können BenannteTabelle 
    
    private Rectangle pageSize; // entpricht in punkten gerechnet der groesse A4
    private Document oDocument; // 
    private boolean bEsIstWasSchiefGegangen = false;
    private String cFehlerCode = "";
    private String cFilename = "";
    private String cFileURL = "";
    private PdfWriter docWriter = null;

	FileOutputStream oFileOutput = null;

    // konstruktor für benannte dateiname
    public myPDF(String pFilename, Rectangle oRect, float left, float right, float top, float bottom, HeaderFooter pFooter)
    {
        this.cFilename = pFilename;
        this.cFileURL = ""; // url nur in der anderen variante
        this.pageSize = oRect;
        this.oDocument = new Document(pageSize, left, right, top, bottom);

        try
        {
			this.oFileOutput = new FileOutputStream(cFilename);
			
            docWriter = PdfWriter.getInstance(oDocument, this.oFileOutput);

            // HeaderFooter muss genau hier hin, damit der footer auf der ersten seite erscheint
            if (pFooter != null)
            {
                this.oDocument.setFooter(pFooter);
            }

            oDocument.open();
        }
        catch (Exception e)
        {
            this.bEsIstWasSchiefGegangen = true;
            this.cFehlerCode += ("1 ->" + e.getMessage());
        }
    }

    // konstruktor für temporäre dateinamen 
    // für web-zugriff
    public myPDF(Rectangle oRect, float left, float right, float top, float bottom, HeaderFooter pFooter)
    {
        this.pageSize = oRect;
        this.oDocument = new Document(pageSize, left, right, top, bottom);

        // erzeugt eine temporäre datei, die nach der session gelöscht wird 
        myTempFile oDatei = new myTempFile("webtemp", ".pdf", true);

        if (oDatei.istOK())
        {
            this.cFilename = oDatei.getFileName();
            this.cFileURL = oDatei.getFileURL();

            try
            {
				this.oFileOutput = new FileOutputStream(oDatei.getFile());
            	
                docWriter = PdfWriter.getInstance(oDocument, this.oFileOutput);
				

                // HeaderFooter muss genau hier hin, damit der footer auf der ersten seite erscheint
                if (pFooter != null)
                {
                    this.oDocument.setFooter(pFooter);
                }

                oDocument.open();
            }
            catch (Exception e)
            {
                this.bEsIstWasSchiefGegangen = true;
                this.cFehlerCode += ("2 ->" + e.getMessage());
            }
        }
        else
        {
            this.bEsIstWasSchiefGegangen = true;
            this.cFehlerCode += ("3 ->" + "Fehler beim erstellen von myTempFile()");
        }
    }

    // konstruktor für benannte ausgabedateien
    // für web-zugriff
    public myPDF(Rectangle oRect, String cNamensVorsatz, String cEndungOhnePunkt, boolean bLoeschenNachSession, float left, float right, float top, float bottom, HeaderFooter pFooter)
    {
        this.pageSize = oRect;
        this.oDocument = new Document(pageSize, left, right, top, bottom);

        if (cNamensVorsatz.equals("") || cEndungOhnePunkt.equals(""))
        {
            this.bEsIstWasSchiefGegangen = true;
            this.cFehlerCode += ("3a ->" + "Fehler beim erstellen von myTempFile()");
        }
        else
        {
            // erzeugt eine temporäre datei, die nach der session gelöscht wird 
            myTempFile oDatei = new myTempFile(cNamensVorsatz, "." + cEndungOhnePunkt, bLoeschenNachSession);

            if (oDatei.istOK())
            {
                this.cFilename = oDatei.getFileName();
                this.cFileURL = oDatei.getFileURL();

                try
                {
					this.oFileOutput = new FileOutputStream(oDatei.getFile());

                    docWriter = PdfWriter.getInstance(oDocument, this.oFileOutput);

                    // HeaderFooter muss genau hier hin, damit der footer auf der ersten seite erscheint
                    if (pFooter != null)
                    {
                        this.oDocument.setFooter(pFooter);
                    }

                    oDocument.open();
                }
                catch (Exception e)
                {
                    this.bEsIstWasSchiefGegangen = true;
                    this.cFehlerCode += ("2 ->" + e.getMessage());
                }
            }
            else
            {
                this.bEsIstWasSchiefGegangen = true;
                this.cFehlerCode += ("3 ->" + "Fehler beim erstellen von myTempFile()");
            }
        }
    }

    // definiert spaltenbreiten einer tabelle
    public void setWidths(String cTabelleName, float[] aWidths)
    {
        com.lowagie.text.Table tHelp = this.findTabelle(cTabelleName);

        if (tHelp != null)
        {
            try
            {
                tHelp.setWidths(aWidths);
            }
            catch (Exception e)
            {
                this.bEsIstWasSchiefGegangen = true;
                this.cFehlerCode += ("15 ->" + e.getMessage());
            }
        }
        else
        {
            this.bEsIstWasSchiefGegangen = true;
            this.cFehlerCode += ("14 ->" + "Die Tabelle " + cTabelleName + " wurde nicht gefunden !");
        }
    }

    // neue tabelle generieren
    public void addTabelle(int iSpalten, float fGesamtBreiteProzent, String cName, int iRahmenBreite)
    {
        this.Tabellen.add(new myPDF.BenannteTabelle(iSpalten, fGesamtBreiteProzent, cName, iRahmenBreite, 0, 0));
    }

    // neue tabelle generieren
    public void addTabelleToVector(int iSpalten, float fGesamtBreiteProzent, String cName, int iRahmenBreite, float fCellSpaceing, float fCellPadding)
    {
        this.Tabellen.add(new myPDF.BenannteTabelle(iSpalten, fGesamtBreiteProzent, cName, iRahmenBreite, fCellSpaceing, fCellPadding));
    }

    public void addTabelleToDocument(String cTabelleName, float fOffset)
    {
        com.lowagie.text.Table tHelp = this.findTabelle(cTabelleName);

        if (tHelp != null)
        {
            try
            {
                tHelp.setOffset(fOffset);
                this.oDocument.add(tHelp);
            }
            catch (Exception e)
            {
                this.bEsIstWasSchiefGegangen = true;
                this.cFehlerCode += ("4 ->" + e.getMessage());
                bibALL.WriteLogExceptionTrace(e);
            }
        }
        else
        {
            this.bEsIstWasSchiefGegangen = true;
            this.cFehlerCode += ("5 ->" + "Die Tabelle " + cTabelleName + " wurde nicht gefunden !");
        }
    }



	public void addTabelleToDocument(Table oTabelle, float fOffset)
	{

		if (oTabelle != null)
		{
			try
			{
				oTabelle.setOffset(fOffset);
				this.oDocument.add(oTabelle);
			}
			catch (Exception e)
			{
				this.bEsIstWasSchiefGegangen = true;
				this.cFehlerCode += ("24 ->" + e.getMessage());
				bibALL.WriteLogExceptionTrace(e);
			}
		}
	}




    public void addTextToTabellePOS(String cTabelleName, int iZeile, int iSpalte, String Text, Font oFont, int pAlignHorizontal, int pAlignVertical, float pBorderWidth, int pBorderTyp, float fZeilenAbstand)
    {
        com.lowagie.text.Table tHelp = this.findTabelle(cTabelleName);

        String cText = Text;
        
        if (cText == null)
        {
            cText = "";
        }

        if (tHelp != null)
        {
            try
            {
                Paragraph para = new Paragraph(cText, oFont);

                if (fZeilenAbstand > 0)
                { // sonst bleibts beim standard
                    para.setLeading(fZeilenAbstand);
                }

                com.lowagie.text.Cell Zelle = new com.lowagie.text.Cell(para);
                Zelle.setHorizontalAlignment(pAlignHorizontal);
                Zelle.setVerticalAlignment(pAlignVertical);
                Zelle.setBorderWidth(pBorderWidth);
                Zelle.setBorder(pBorderTyp);
                tHelp.addCell(Zelle, new Point(iZeile, iSpalte));
            }
            catch (Exception e)
            {
                this.bEsIstWasSchiefGegangen = true;
                this.cFehlerCode += ("6 ->" + e.getMessage());
            }
        }
        else
        {
            this.bEsIstWasSchiefGegangen = true;
            this.cFehlerCode += ("7 ->" + "Die Tabelle " + cTabelleName + " wurde nicht gefunden !");
        }
    }

    public void addTextToTabelle(String cTabelleName, String Text, Font oFont, int pAlignHorizontal, int pAlignVertical, float pBorderWidth, int pBorderTyp, int pColSpan, int pRowSpan, float fZeilenAbstand)
    {
        com.lowagie.text.Table tHelp = this.findTabelle(cTabelleName);
  
        String cText = Text;

        if (cText == null)
        {
            cText = "";
        }

        if (tHelp != null)
        {
            try
            {
                Paragraph para = new Paragraph(cText, oFont);

                if (fZeilenAbstand > 0)
                { // sonst bleibts beim standard
                    para.setLeading(fZeilenAbstand);
                }

                com.lowagie.text.Cell Zelle = new com.lowagie.text.Cell(para);
                Zelle.setHorizontalAlignment(pAlignHorizontal);
                Zelle.setVerticalAlignment(pAlignVertical);
                Zelle.setBorderWidth(pBorderWidth);
                Zelle.setBorder(pBorderTyp);

                if (pColSpan > 1)
                {
                    Zelle.setColspan(pColSpan);
                }

                if (pRowSpan > 1)
                {
                    Zelle.setRowspan(pRowSpan);
                }

                tHelp.addCell(Zelle);
                
            }
            catch (Exception e)
            {
                this.bEsIstWasSchiefGegangen = true;
                this.cFehlerCode += ("8 ->" + e.getMessage());
            }
        }
        else
        {
            this.bEsIstWasSchiefGegangen = true;
            this.cFehlerCode += ("9 ->" + "Die Tabelle " + cTabelleName + " wurde nicht gefunden !");
        }
    }



	public static void addTextToTabelle(Table oTabelle, String Text, Font oFont, int pAlignHorizontal, int pAlignVertical, float pBorderWidth, int pBorderTyp, int pColSpan, int pRowSpan, float fZeilenAbstand)
				throws myException
	 {

        String cText = Text;

		 if (cText == null)
		 {
			 cText = "";
		 }

		 if (oTabelle != null)
		 {
			 try
			 {
				 Paragraph para = new Paragraph(cText, oFont);

				 if (fZeilenAbstand > 0)
				 { // sonst bleibts beim standard
					 para.setLeading(fZeilenAbstand);
				 }

				 com.lowagie.text.Cell Zelle = new com.lowagie.text.Cell(para);
				 Zelle.setHorizontalAlignment(pAlignHorizontal);
				 Zelle.setVerticalAlignment(pAlignVertical);
				 Zelle.setBorderWidth(pBorderWidth);
				 Zelle.setBorder(pBorderTyp);

				 if (pColSpan > 1)
				 {
					 Zelle.setColspan(pColSpan);
				 }

				 if (pRowSpan > 1)
				 {
					 Zelle.setRowspan(pRowSpan);
				 }

				oTabelle.addCell(Zelle);
                
			 }
			 catch (Exception e)
			 {
				 throw new myException("myPDF:addTextToTabelle:"+e.getLocalizedMessage());
			 }
		 }
	 }







    // eine benannte tabelle in eine andere benannte tabelle einfügen
    public void addTabelleToTabellePOS(String cTabelleName, int iZeile, int iSpalte, String cInsertedTabelleName, int pAlignHorizontal, int pAlignVertical, float pBorderWidth, int pBorderTyp)
    {
        com.lowagie.text.Table tHelp = this.findTabelle(cTabelleName);
        com.lowagie.text.Table tInsert = this.findTabelle(cInsertedTabelleName);

        if ((cTabelleName == null) || (cInsertedTabelleName == null))
        {
            this.bEsIstWasSchiefGegangen = true;
            this.cFehlerCode += ("10 ->" + "Eine der  Tabellen " + cTabelleName + "/" + cInsertedTabelleName + " wurde nicht gefunden !");
        }
        else
        {
            try
            {
                tInsert.setTableFitsPage(true);

                com.lowagie.text.Cell Zelle = new com.lowagie.text.Cell(tInsert);
                Zelle.setHorizontalAlignment(pAlignHorizontal);
                Zelle.setVerticalAlignment(pAlignVertical);
                Zelle.setBorderWidth(pBorderWidth);
                Zelle.setBorder(pBorderTyp);

                tHelp.addCell(Zelle, new Point(iZeile, iSpalte));
            }
            catch (Exception e)
            {
                this.bEsIstWasSchiefGegangen = true;
                this.cFehlerCode += ("11 ->" + e.getMessage());
            }
        }
    }

    // sucht tabelle aus vector raus
    public com.lowagie.text.Table findTabelle(String cTabelleName)
    {
        com.lowagie.text.Table tRueck = null;
        BenannteTabelle tHelp = null;

        for (int i = 0; i < this.Tabellen.size(); i++)
        {
            if (((BenannteTabelle) this.Tabellen.get(i)).getNamen().equals(cTabelleName))
            {
                tHelp = (BenannteTabelle) this.Tabellen.get(i);
                tRueck = tHelp.getTable();

                break;
            }
        }

        return tRueck;
    }

    // wenn was schief geht, dann nach oben melden
    public boolean getIstWasSchiefGegangen()
    {
        return this.bEsIstWasSchiefGegangen;
    }

    public String getFehlerCode()
    {
        return this.cFehlerCode;
    }

    public String getFileName()
    {
        return this.cFilename;
    }

    public String getFileURL()
    {
        return this.cFileURL;
    }

    public void addParagraph(String cZeile, Font oFont, float fOffset)
    {
        if (cZeile != null)
        {
            try
            {
                Paragraph oPara = new Paragraph(fOffset, cZeile, oFont);

                this.oDocument.add(oPara);
            }
            catch (Exception e)
            {
                this.bEsIstWasSchiefGegangen = true;
                this.cFehlerCode += ("12 ->" + e.getMessage());
            }
        }
    }

    public void addParagraph(String cZeile, Font oFont, float fOffset, float fLeading)
    {
        if (cZeile != null)
        {
            try
            {
                Paragraph oPara = new Paragraph(fOffset, cZeile, oFont);
                oPara.setLeading(fLeading);
                this.oDocument.add(oPara);
            }
            catch (Exception e)
            {
                this.bEsIstWasSchiefGegangen = true;
                this.cFehlerCode += ("12 ->" + e.getMessage());
            }
        }
    }

    public void addParagraph(String cZeile, Font oFont, float fOffset, float fLeading, float SpaceOben)
    {
        if (cZeile != null)
        {
            try
            {
                Paragraph oSpace = new Paragraph(fOffset, " ", myPDF.getHELVETICA_NORMAL((int) SpaceOben));
                Paragraph oPara = new Paragraph(fOffset, cZeile, oFont);
                oSpace.setLeading(SpaceOben);
                oPara.setLeading(fLeading);

                this.oDocument.add(oSpace);
                this.oDocument.add(oPara);
            }
            catch (Exception e)
            {
                this.bEsIstWasSchiefGegangen = true;
                this.cFehlerCode += ("12 ->" + e.getMessage());
            }
        }
    }

    // baut ein fusszeilen-element, das der seite übergeben werden kann
    public static HeaderFooter makeFooter(String cFussText, Font oFont, float fLeading)
    {
        Phrase pFooter = new Phrase(fLeading, cFussText, oFont);
        HeaderFooter footer = new HeaderFooter(pFooter, false);
        footer.setBorder(Rectangle.NO_BORDER);

        return footer;
    }

    public void FormularSchliessen()
    {
        oDocument.close();
        docWriter.flush();
        docWriter.close();
        if (this.oFileOutput != null)
        {
        	try
        	{
        		this.oFileOutput.flush();
        	}
        	catch(Exception e)
        	{
        	}
			try
			{
				this.oFileOutput.close();
			}
			catch(Exception e)
			{
			}
        	
        }
        
    }

	protected void finalize()
	{
		 if (oDocument != null) 		oDocument.close();
		 if (docWriter != null) 	docWriter.flush();
		 if (docWriter != null) 	docWriter.close();
		 if (this.oFileOutput != null)
		 {
			 try
			 {
				 this.oFileOutput.flush();
			 }
			 catch(Exception e)
			 {
			 }
			 try
			 {
				 this.oFileOutput.close();
			 }
			 catch(Exception e)
			 {
			 }
        	
		 }
	}


	public static Table createNewTable(int pSpalten, float pBreiteInProzent, int pRahmenBreite, float pCellSpaceing, float pCellPadding) 
					throws myException
	{
		float 	fCellSpaceing 	= pCellSpaceing;
		float 	fCellPadding 	= pCellPadding;
		Table 	tabRueck 		= null;

		if (fCellSpaceing < 0) 	fCellSpaceing 	= 0;
		if (fCellPadding < 0) 	fCellPadding 	= 0;

		try
		{
			tabRueck = new com.lowagie.text.Table(pSpalten);
			tabRueck.setPadding(fCellSpaceing);
			tabRueck.setPadding(pCellPadding);
			float fBreiteInProzent = pBreiteInProzent;
			int iRahmenBreite = pRahmenBreite;

			if (fBreiteInProzent > 100)	fBreiteInProzent = 100; 

			tabRueck.setWidth(fBreiteInProzent);
			tabRueck.setAutoFillEmptyCells(true); // zellen automatisch füllen
			tabRueck.setBorderWidth(iRahmenBreite);
		}
		catch (Exception e)
		{
			tabRueck = null;
			throw new myException("myPDF:createNewTable:Error creating new table: "+e.getLocalizedMessage());
		}
		return tabRueck;
	}


	
	
	
	
	
	/**
	 * @param pSpalten
	 * @param pBreiteInProzent
	 * @param pRahmenBreite
	 * @param pCellPadding
	 * @param BorderColor
	 * @return
	 * @throws myException
	 */
	public static PdfPTable createNew_PdfPTable(int pSpalten, float pBreiteInProzent, int pRahmenBreite, float pCellPadding, java.awt.Color BorderColor) 
			throws myException
	{
		PdfPTable 	tabRueck 		= null;
		
		float 	fCellPadding 	= pCellPadding;
		float  fBorderWidth 	= pRahmenBreite;

		if (fCellPadding < 0) 	fCellPadding 	= 0;
		if (fBorderWidth < 0) 	fBorderWidth 	= 0;

		
		try
		{
			tabRueck = new PdfPTable(pSpalten);
			float fBreiteInProzent = pBreiteInProzent;
			
			if (fBreiteInProzent > 100)	fBreiteInProzent = 100; 
			
			tabRueck.setWidthPercentage(fBreiteInProzent);
			
			/*
			 * standard-cell-einstellungen
			 */
			tabRueck.getDefaultCell().setBorderWidth(fBorderWidth);
			tabRueck.getDefaultCell().setPadding(fCellPadding);
			
			if (BorderColor != null)
			{
			    tabRueck.getDefaultCell().setBorderColor(BorderColor);
			}
			
			
			
		}
		catch (Exception e)
		{
			tabRueck = null;
			throw new myException("myPDF:createNewTable:Error creating new table: "+e.getLocalizedMessage());
		}
		return tabRueck;
	}
	
	
	
	
	
	/**
	 * @param cContent
	 * @param oFont
	 * @param iBorderSize
	 * @return
	 */
	public static PdfPCell myPdfPCell(String cContent,Font oFont, int iBorderSize)
	{
	    Phrase pHelp = new Phrase(cContent);
	    
	    if (oFont != null)
	    {
	        pHelp= new Phrase(cContent,oFont);
	    }
        PdfPCell oCellHelp = new PdfPCell(pHelp);
        oCellHelp.setBorderWidth(0);
        
        if (iBorderSize >= 0)
            oCellHelp.setBorderWidth(iBorderSize);
        
        return oCellHelp;
	}
	
	public static PdfPCell myPdfPCell(String cContent,Font oFont, int iBorderSize, Color BorderColor)
	{
        PdfPCell oCellHelp = myPDF.myPdfPCell(cContent,oFont, iBorderSize);
        if (BorderColor != null)
            oCellHelp.setBorderColor(BorderColor);
        
        return oCellHelp;
	}
	
	public static PdfPCell myPdfPCell(String cContent,Font oFont, int iBorderSize, Color BorderColor, Color BackgroundColor)
	{
        PdfPCell oCellHelp = myPDF.myPdfPCell(cContent,oFont, iBorderSize,BorderColor);
        if (BackgroundColor != null)
            oCellHelp.setBackgroundColor(BackgroundColor);
        
        return oCellHelp;
	}
	
	public static PdfPCell myPdfPCell(String cContent,Font oFont, int iBorderSize, Color BorderColor, Color BackgroundColor, int iHorizontAlign)
	{
        PdfPCell oCellHelp = myPDF.myPdfPCell(cContent,oFont, iBorderSize,BorderColor,BackgroundColor);
        
        oCellHelp.setHorizontalAlignment(iHorizontAlign);
        
        return oCellHelp;
	}

	public static PdfPCell myPdfPCell(String cContent,Font oFont, int iBorderSize, Color BorderColor, Color BackgroundColor, int iHorizontAlign, float fCellPadding)
	{
        PdfPCell oCellHelp = myPDF.myPdfPCell(cContent,oFont, iBorderSize,BorderColor,BackgroundColor,iHorizontAlign);
        
        if (fCellPadding>=0)
            oCellHelp.setPadding(fCellPadding);
        
        return oCellHelp;
	}

	
	
	
	
	
	
	
	
	

    public static final float cm2pt(float fCm)
    {
        return (fCm * (float) 72.0) / (float) 2.54;
    }

    // ein paar brauchbare fonts
    public static final Font getHELVETICA_NORMAL(int iSize)
    {
        return FontFactory.getFont(FontFactory.HELVETICA, iSize, Font.NORMAL);
    }

    public static final Font getTIMES_NORMAL(int iSize)
    {
        return FontFactory.getFont(FontFactory.TIMES, iSize, Font.NORMAL);
    }

    public static final Font getCOURIER_NORMAL(int iSize)
    {
        return FontFactory.getFont(FontFactory.COURIER, iSize, Font.NORMAL);
    }

    public static final Font getHELVETICA_BOLD(int iSize)
    {
        return FontFactory.getFont(FontFactory.HELVETICA_BOLD, iSize, Font.NORMAL);
    }

    public static final Font getTIMES_BOLD(int iSize)
    {
        return FontFactory.getFont(FontFactory.TIMES_BOLD, iSize, Font.NORMAL);
    }

    public static final Font getCOURIER_BOLD(int iSize)
    {
        return FontFactory.getFont(FontFactory.COURIER_BOLD, iSize, Font.NORMAL);
    }

    public static final Font getHELVETICA_ITALIC(int iSize)
    {
        return FontFactory.getFont(FontFactory.HELVETICA, iSize, Font.ITALIC);
    }

    public static final Font getTIMES_ITALIC(int iSize)
    {
        return FontFactory.getFont(FontFactory.TIMES, iSize, Font.ITALIC);
    }

    public static final Font getCOURIER_ITALIC(int iSize)
    {
        return FontFactory.getFont(FontFactory.COURIER, iSize, Font.ITALIC);
    }

    public static final Font getHELVETICA_BOLD_ITALIC(int iSize)
    {
        return FontFactory.getFont(FontFactory.HELVETICA, iSize, Font.BOLDITALIC);
    }

    public static final Font getTIMES_BOLD_ITALIC(int iSize)
    {
        return FontFactory.getFont(FontFactory.TIMES, iSize, Font.BOLDITALIC);
    }

    public static final Font getCOURIER_BOLD_ITALIC(int iSize)
    {
        return FontFactory.getFont(FontFactory.COURIER, iSize, Font.BOLDITALIC);
    }


	public void flush()
	{
		if (this.oFileOutput != null)
		try
		{
			this.oFileOutput.flush();	
		}
		catch (Exception e)
		{
		}
	}

    public Document get_oDocument()
    {
        return this.oDocument;
    }

	
	
    public class BenannteTabelle
    {
        private com.lowagie.text.Table Tabelle;
        private String cNamen;
        private float fBreiteInProzent;
        private int iSpalten;
        private int iRahmenBreite;
        private String c_FehlerCode;
        private float fCellSpaceing = 0;
        private float fCellPadding = 0;

        public BenannteTabelle(int pSpalten, float pBreiteInProzent, String pName, int pRahmenBreite, float pCellSpaceing, float pCellPadding)
        {
            fCellSpaceing = pCellSpaceing;
            fCellPadding = pCellPadding;

            if (fCellSpaceing < 0)
            {
                fCellSpaceing = 0;
            }

            if (fCellPadding < 0)
            {
                fCellPadding = 0;
            }

            this.c_FehlerCode = "";

            try
            {
                Tabelle = new com.lowagie.text.Table(pSpalten);
                Tabelle.setPadding(fCellSpaceing);
                Tabelle.setPadding(pCellPadding);
            }
            catch (Exception e)
            {
                Tabelle = null;
                this.c_FehlerCode += ("13 -> Fehler beim Erzeugen der Tabelle " + pName);
            }

            iSpalten = pSpalten;
            cNamen = pName;
            fBreiteInProzent = pBreiteInProzent;
            iRahmenBreite = pRahmenBreite;

            if (fBreiteInProzent > 100)
            {
                fBreiteInProzent = 100; // mehr als 100 geht nicht
            }

            Tabelle.setWidth(fBreiteInProzent);
            Tabelle.setAutoFillEmptyCells(true); // zellen automatisch füllen
            Tabelle.setBorderWidth(iRahmenBreite);
        }

        public int getSpaltenZahl()
        {
            return iSpalten;
        }

        public int getRahmenBreite()
        {
            return iRahmenBreite;
        }

        public String getNamen()
        {
            return cNamen;
        }

        public float getBreiteInProzent()
        {
            return fBreiteInProzent;
        }

        public com.lowagie.text.Table getTable()
        {
            return Tabelle;
        }

        public String getFehlerCode()
        {
            return this.c_FehlerCode;
        }
    }
}
