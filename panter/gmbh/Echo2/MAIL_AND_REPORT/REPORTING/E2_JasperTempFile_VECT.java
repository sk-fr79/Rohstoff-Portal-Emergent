package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName_Vector;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.ZIP.myZipCreator;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.pdf.pdfConcat;

public class E2_JasperTempFile_VECT extends Vector<E2_JasperTempFile> 
{

	public E2_JasperTempFile_VECT() {
		super();
	}

	public E2_JasperTempFile_VECT(Collection<? extends E2_JasperTempFile> c) 
	{
		super(c);
	}

	
	
	public Vector<String> get_vFilenames()
	{
		Vector<String> vRueck = new Vector<String>();
		
		Iterator<E2_JasperTempFile>  iTer = this.iterator();
		while (iTer.hasNext())
		{
			vRueck.add(iTer.next().get_NameOfCreatedFile());
		}
		return vRueck;
	}
	
	
	
	/**
	 * 
	 * @param cNameOfConcatenatedFileWithEnding ist der Name, der benutzt wird, wenn eine verkettung stattfindet
	 * @return s  new Tempfile  (old: ...      kompletter Dateiname incl. Endung der temporaeren datei) 
	 * @throws myException
	 */
	public myTempFile get_myTempFileAllConcated(String cNameOfConcatenatedFile_WITHOUT_Ending) throws myException
	{
		
		myTempFile cRueck = null;
		
		if (this.size() == 1)
		{
			cRueck = this.get(0);
		} 
		else
		{
			/**
			 * falls es pdf-dokumente waren, dann werden sie zusammengefasst,
			 * sonst gezippt
			 */
			boolean bAlleArePDFs = true;
			Iterator<E2_JasperTempFile>  iTer = this.iterator();
			while (iTer.hasNext())
			{
				if (!iTer.next().get_oJasperHash().get_oJasperFileDef().MimeType.equals(JasperFileDef.MIMETYP_PDF))
				{
					bAlleArePDFs=false;
					break;
				}
			}
			
			
			if (bAlleArePDFs)
			{
				pdfConcat oConcat = new pdfConcat(this.get_vFilenames());
				// wenn mehrere subdokumente vorliegen, dann zusammenfassen
				cRueck = oConcat.baueZielDatei(cNameOfConcatenatedFile_WITHOUT_Ending); 
			}
			else
			{
				/*
				 * falls es keine pdfs sind, wird gezippt
				 */
				FileWithSendName_Vector vFileWithSendNames = new FileWithSendName_Vector();
				
				Iterator<E2_JasperTempFile>  iTer2 = this.iterator();
				while (iTer.hasNext())
				{
					E2_JasperTempFile  oJasperTempFile = iTer2.next();
					vFileWithSendNames.add(new FileWithSendName(	oJasperTempFile.get_NameOfCreatedFile(),
																	oJasperTempFile.get_cNameOfFile4UserWithoutEnding()+oJasperTempFile.get_cFileEnding4User(),
																	oJasperTempFile.get_oJasperHash().get_oJasperFileDef()));
				}
				
				myZipCreator oZC;
				try 
				{
					oZC = new myZipCreator(vFileWithSendNames);
				} 
				catch (FileNotFoundException e) 
				{
					e.printStackTrace();
					throw new myException(e.getLocalizedMessage());
				}
				
				cRueck = oZC.get_oTempZipFile();
			}
			
		}

		return cRueck;
	}
	
	
}
