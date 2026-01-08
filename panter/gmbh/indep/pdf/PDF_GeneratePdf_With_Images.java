package panter.gmbh.indep.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;

import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;



public class PDF_GeneratePdf_With_Images
{
	
	private Vector<String> 	vNamesOfImageFiles = new Vector<String>();
	private String    		cFilenameWithoutEnding = null;
	
//	private myTempFile      oTempFile4PDF = null;
	
	private boolean         bSetBorder = false;
	private float   		fScaleImageSize = 200f;
	private int             iSizeGrid = 1;

	private myTempFile 		tf = null; 
	
	
	public PDF_GeneratePdf_With_Images(	Vector<String> 	NamesOfImageFiles, 
										String 			Filename_without_ending, 
										boolean 		setBorder,
										float   		ScaleImages) throws myException
	{
		super();
		this.vNamesOfImageFiles.addAll(NamesOfImageFiles);
		this.cFilenameWithoutEnding = Filename_without_ending;
		this.bSetBorder = setBorder;
		this.fScaleImageSize = ScaleImages;
		
		if (this.fScaleImageSize<150f)
		{
			this.iSizeGrid=3;
		}
		else if (this.fScaleImageSize<250f)
		{
			this.iSizeGrid=2;
		}
		else
		{
			this.iSizeGrid=1;
		}
			
	}

	
	
	public myTempFile generate_pdf_with_images() throws myException {
		myTempFile oTempFile4PDF = new myTempFile(this.cFilenameWithoutEnding, ".pdf", true, true);
		
		try 	{
			Document oDocument = new Document();
			FileOutputStream oFileOut = new FileOutputStream(oTempFile4PDF.getFile());
			PdfWriter.getInstance(oDocument, oFileOut);
			oDocument.open();
			
			float[] fBreite = new float[this.iSizeGrid];
			
			fBreite[0] = this.fScaleImageSize;
			
			if (this.iSizeGrid>1f)	{fBreite[1] = this.fScaleImageSize;}
			if (this.iSizeGrid>2f)	{fBreite[2] = this.fScaleImageSize;}
			
			
			PdfPTable pdfTable = new  PdfPTable(fBreite);
			
			pdfTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			
			int iCount = 0;
			for (String cFilename: this.vNamesOfImageFiles)
			{
				Image oImage = Image.getInstance(cFilename);
				oImage.setAlignment(Image.MIDDLE);
				
				oImage.scaleToFit(this.fScaleImageSize-4f, this.fScaleImageSize-4f);

				PdfPCell oCell = new PdfPCell(oImage,false);
				oCell.setHorizontalAlignment(Cell.ALIGN_MIDDLE);
				oCell.setPadding(2f);

				oCell.setBorder(Rectangle.NO_BORDER);
				
				pdfTable.addCell(oCell);
				
				iCount++;
			}
			
			// mindestens eine zeile fuellen
			if (iCount<this.iSizeGrid)	{
				for (int i=iCount;i<this.iSizeGrid;i++)	{
					pdfTable.addCell(" ");
				}
			}  else {
				int iRest = this.iSizeGrid-(iCount % this.iSizeGrid);
				for (int i=0;i<iRest;i++) {
					pdfTable.addCell(" ");
				}
			}
			
			pdfTable.setWidthPercentage(98f);
			
			oDocument.add(pdfTable);
			
			oDocument.close();
			
			oFileOut.close();
			
			oTempFile4PDF.close();
		
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			throw new myException("FileNotFoundException:"+e.getLocalizedMessage());
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
			throw new myException("DocumentException:"+e.getLocalizedMessage());
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
			throw new myException("MalformedURLException:"+e.getLocalizedMessage());
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new myException("IOException:"+e.getLocalizedMessage());
		}

		return oTempFile4PDF;
		
	}
	
	
	
	
	public void starte_DownLoad() throws myException {
		this.tf = this.generate_pdf_with_images();
		if (tf != null) {
			tf.starteDownLoad(this.cFilenameWithoutEnding+".pdf", "application/pdf");
		} else {
			throw new myException("Error generating file !!!");
		}
	}
	
	
	
}
