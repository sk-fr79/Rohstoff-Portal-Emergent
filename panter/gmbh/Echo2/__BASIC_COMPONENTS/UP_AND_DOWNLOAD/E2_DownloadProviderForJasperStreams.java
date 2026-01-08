
package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import nextapp.echo2.app.filetransfer.ResourceDownloadProvider;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef;
import panter.gmbh.indep.bibALL;

/**
 *  Ableitung der vorhandenen filetransfer-lib-klasse, damit jasperprint-objekte
 *        direkt gestreamt werden können
 *
 *
 */
public class E2_DownloadProviderForJasperStreams extends ResourceDownloadProvider
{
	private JasperPrint oJasperPrint = null;
	private String  	cTargetMimeType = null;

	public E2_DownloadProviderForJasperStreams(JasperPrint ojasperPrint, String contenttype, String  ctargetMimeType,String cDownloadFileName)
	{
		super(E2_DownloadProviderForJasperStreams.makeFileName(cDownloadFileName,ctargetMimeType), contenttype);
		this.oJasperPrint = ojasperPrint;
		this.cTargetMimeType = ctargetMimeType;
		
		/*
		 * dafür sorgen, dass der name mitgeliefert wird
		 */
		this.setFileNameProvided(true);
		
	}

	public void writeFile(OutputStream out) throws IOException
	{
		try
		{
			if (this.cTargetMimeType.equals(JasperFileDef.MIMETYP_PDF))
			{
				JasperExportManager.exportReportToPdfStream(this.oJasperPrint, out);
			}
			else if (this.cTargetMimeType.equals(JasperFileDef.MIMETYP_XML))
			{
				JasperExportManager.exportReportToXmlStream(this.oJasperPrint, out);
			}
			else if (this.cTargetMimeType.equals(JasperFileDef.MIMETYP_XLS))
			{
				JRExporter exporter = new JRXlsExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, this.oJasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
				exporter.exportReport();
			}
			else if (this.cTargetMimeType.equals(JasperFileDef.MIMETYP_HTML))
			{
				JRExporter exporter = new JRHtmlExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, this.oJasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
				exporter.exportReport();
			}
			else if (this.cTargetMimeType.equals(JasperFileDef.MIMETYP_CSV))
			{
				JRExporter exporter = new JRCsvExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, this.oJasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
				exporter.exportReport();
			}

		}
		catch (Exception e)
		{
			throw new IOException("Fehler beim Download einer Jasper-Recource (2): ");

		}

	}
	
	
	
	/*
	 * hilfsmethode, generiert aus einem filebasename und einem Typ
	 * einen ganzen file-namen
	 * wird benutzt, um den super-construktor richtig zu füllen
	 */
	 public static String makeFileName(String cFileBaseName,String  ctargetMimeType)
	 {
	 	String cRueck = bibALL.null2leer(cFileBaseName);
	 	
	 	/*
	 	 * jetzt noch leerzeichen aus dem namen rausschmeissen
	 	 */
	 	cRueck = bibALL.ReplaceTeilString(cRueck," ","_");
	 	
	 	if (cRueck.equals(""))
	 	{
	 		cRueck = "name";
	 	}
	 	cRueck +=".";
		if (ctargetMimeType.equals(JasperFileDef.MIMETYP_PDF))
		{
			cRueck += "pdf";
		}
		else if (ctargetMimeType.equals(JasperFileDef.MIMETYP_XML))
		{
			cRueck += "xml";
		}
		else if (ctargetMimeType.equals(JasperFileDef.MIMETYP_XLS))
		{
			cRueck += "xls";		
		}
		else if (ctargetMimeType.equals(JasperFileDef.MIMETYP_HTML))
		{
			cRueck += "html";		
		}
		else if (ctargetMimeType.equals(JasperFileDef.MIMETYP_CSV))
		{
			cRueck += "csv";		
		}
	 	
	 	return cRueck;
	 }
	 
	
}
