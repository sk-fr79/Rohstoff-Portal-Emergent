package panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_Download;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.pdf.pdfConcat;

public class FileWithSendName_Vector extends Vector<FileWithSendName>
{
	

	
	/**
	 * 
	 * @param oGrid4FortschrittsInfo
	 * @return s Filename of Concatenated file
	 * @throws myException
	 */
	public myTempFile DOWNLOAD_CONCATENATED_PDFS( MyE2_Grid oGrid4FortschrittsInfo) throws myException
	{
		myTempFile oTempfileRueck = null;
		
		/*
		 * jetzt schauen, ob im namensvector mehrere dateien sind, und diese ausgeben 
		 * (falls nötig verkettet)
		 */
		
		String cDownloadName = "MultiDokument";
		
		if (this.size()==1)
		{
			this.get(0).Download_File();
//			cDownloadName = this.get(0).get_cNameFor_USER_EmailAttachment();
		}
		else
		{
			int iPosEndung = this.get(0).get_cNameFor_USER_EmailAttachment().toUpperCase().indexOf(this.get(0).get_oJasperFileDef().Endung.toUpperCase());
			
			if (iPosEndung>4)
			{
				cDownloadName = this.get(0).get_cNameFor_USER_EmailAttachment();
				cDownloadName=cDownloadName.substring(0, iPosEndung)+"_und_weitere"+this.get(0).get_oJasperFileDef().Endung;
			}
			
			
		    Vector<String> vHelpNames = new Vector<String>();
		    
		    for (int i=0;i<this.size();i++)
		    {
	    		vHelpNames.add(this.get(i).get_cNameWithPath());
		    }
		    
		    if (oGrid4FortschrittsInfo != null)
		    {
				oGrid4FortschrittsInfo.removeAll();
				oGrid4FortschrittsInfo.addAfterRemoveAll(new MyE2_Label(new MyE2_String("Ich verkette die Dokumente ...")));
		    }
		    
		    pdfConcat oConcat = new pdfConcat(vHelpNames);
		    myTempFile cAlleZusammen = oConcat.baueZielDatei("Multidokument");
		    
		    if (cAlleZusammen!=null)
		    {
		    	new E2_Download().starteFileDownload(cAlleZusammen.getFileName(),cDownloadName,JasperFileDef.MIMETYP_PDF);
		    	oTempfileRueck = cAlleZusammen;
		    }
		}
		
		return oTempfileRueck;
	}


}
