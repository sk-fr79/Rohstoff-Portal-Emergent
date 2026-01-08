package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFileAutoDel;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.REPORT_VERLAUF.REP_VER_ProtokollWriter;

public class E2_JasperTempFile extends myTempFileAutoDel
{
	
	private E2_JasperHASH  oJasperHash = null;
	
	

	private String   	  			 			cJasperFileWithPath = null;
	private Connection 	   						oConnection4Jasper = null;
//	private ownRECORD_REPORT_PIPELINE_POS 		oRecPipePos = null;

	private String    							cNameOfFile4UserWithoutEnding = null;
	private String    							cFileEnding4User = null;
	private String   							c_JasperBaseName = null;
	
	
	//2014-01-15: Dem jasper-Tempfile die Seitezahl mitgeben, damit spaeter 0-Seiten entfernt werden koennen
	private Integer  							intAnzahlSeitenNachGenerierung = null;


	/**
	 * 
	 * @return s after calling createTempOutputFile() number of pages (so 0=empty report)
	 */
	public Integer get_intAnzahlSeitenNachGenerierung() {
		return intAnzahlSeitenNachGenerierung;
	}


	/**
	 * 
	 * @param JasperHash
	 * @param NameOfFile4User_WITHOUT_Ending (can be null)
	 * @param cJasperBaseName       (wenn NULL, dann wird der standard-report-name aus der E2_JasperHash gezogen)
	 * Baut die temporaere ausgabedatei auf
	 * 
	 * @throws myException
	 */
	public E2_JasperTempFile(	E2_JasperHASH 					JasperHash,
								String     						cZusatzString1,
								String     						cZusatzString2,
								String     						cZusatzString3,
								String   						NameOfFile4User_WITHOUT_Ending,
								String    						cJasperBaseName
								) throws myException
	{
		super(bibALL.get_SESSIONID(null), JasperHash.get_oJasperFileDef().Endung, true);
		
		DEBUG.System_println("Betrete E2_JasperTempFile.E2_JasperTempFile", DEBUG.DEBUG_FLAG_DIVERS1);
		
		
		this.oJasperHash = JasperHash;
		
		this.cNameOfFile4UserWithoutEnding = NameOfFile4User_WITHOUT_Ending;
		
		//falls kein innerer name (im Fall eines zip-archivs) angegeben wird, dann wird der jasper-name benutzt
		if (S.isEmpty(this.cNameOfFile4UserWithoutEnding))
		{
			this.cNameOfFile4UserWithoutEnding= this.oJasperHash.get_cReportBaseName();
		}
		this.cFileEnding4User = this.oJasperHash.get_oJasperFileDef().Endung;
		
		if (S.isEmpty(cJasperBaseName))
		{
			this.cJasperFileWithPath = this.oJasperHash.get_cCompleteReportPathAndFileName();
			this.c_JasperBaseName = this.oJasperHash.get_cReportBaseName();
		}
		else
		{
			this.cJasperFileWithPath=new E2_JasperReportPathFinder(cJasperBaseName).get_cCompleteReportPathAndFileName();
			this.c_JasperBaseName = cJasperBaseName;
		}
		
		//jetzt die zusatztexte noch einfuegen
		this.oJasperHash.put("ZUSATZSTRING4JASPERHASH1", cZusatzString1);
		this.oJasperHash.put("ZUSATZSTRING4JASPERHASH2", cZusatzString2);
		this.oJasperHash.put("ZUSATZSTRING4JASPERHASH3", cZusatzString3);
			
			//um kompativel zu bleiben, wird der alte eindruck-Text-parameter ebenfalls mit in die jasper-datei uebernommen (der erste text)
		this.oJasperHash.put(E2_JasperHASH.HASHKEY_SYS_ACTUAL_COPY_TEXT,cZusatzString1);

		this.oConnection4Jasper = bibALL.get_oConnectionNormal().get_oConnection();

		//hier evtl. austausch der reports nach uebersetzer-tabelle
		String protokolleName = this.createTempOutputFile();
		try {
			File helpFile = new File(protokolleName);
			protokolleName = helpFile.getName();
			if (protokolleName.trim().toUpperCase().endsWith(".JASPER")) {
				protokolleName = protokolleName.trim().substring(0,protokolleName.length()-7);
			} else if (protokolleName.trim().toUpperCase().endsWith(".JRXML")) {
				protokolleName = protokolleName.trim().substring(0,protokolleName.length()-6);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		new REP_VER_ProtokollWriter()._writeInLogTable(this.oJasperHash, protokolleName);
		
		DEBUG.System_println("Verlasse E2_JasperTempFile.E2_JasperTempFile", DEBUG.DEBUG_FLAG_DIVERS1);
	}
	
	
	public String get_NameOfCreatedFile()
	{
		return this.getFileName();
	}
	
	
	protected void finalize() throws Throwable
	{
		//System.out.println("Betrete E2JasperTempFile.finalize()");
		super.finalize();
	}

	

	/**
	 * neue version mit uebersetzer-pruefung
	 * @author martin
	 * @date 06.02.2020
	 *
	 * @throws myException
	 */
	private String createTempOutputFile() throws myException
	{
		FileOutputStream oOut = null;
		String jasperFileNameAndPath = this.cJasperFileWithPath;
		
		try {
			try {
				jasperFileNameAndPath = new JasperFileExchangeService().getTranslatedFileNameAndPath(this.cJasperFileWithPath, this.oJasperHash);
			} catch (Exception e) {
				e.printStackTrace();
				jasperFileNameAndPath = this.cJasperFileWithPath;
			}
			
			JasperPrint oJasperPrint = JasperFillManager.fillReport(jasperFileNameAndPath, this.oJasperHash, this.oConnection4Jasper);

			//2014-01-15: Mitschreiben der Seitenzahl, um leere dokumente zu finden
			this.intAnzahlSeitenNachGenerierung = new Integer(oJasperPrint.getPages().size());
			DEBUG.System_println("Seiten: " +oJasperPrint.getPages().size(),DEBUG.DEBUG_FLAG_DIVERS1);
			
			oOut = new FileOutputStream(this.getFile());
	
			//JasperExportManager.exportReportToPdfStream(oJasperPrint,oOut);
			this.createFile(oJasperPrint, oOut);
	
			oOut.close();
			this.close();
			
			oOut = null;
			oJasperPrint = null;
			
			if (! bibALL.get_DISABLE_EXPLICIT_GARBAGE_COLLECTION()){
				System.gc();
			}

			
			DEBUG.System_println("Verlasse E2_JasperTempFile.createTempOutputFile", DEBUG.DEBUG_FLAG_DIVERS1);
			
		} catch (JRException e) {
			e.printStackTrace();
			throw new myException(myException.TYPE_ERROR_JASPER,"Fehler: JasperPrint-Objekt konnte nicht initialisiert werden -->"+ e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new myException(myException.TYPE_ERROR_JASPER,"Fehler bei der Ausgabe auf Datei -->"+ e.getLocalizedMessage());
		} finally {
			if (oOut != null) {
				try	{
					oOut.close();
					oOut = null;
				} catch (Exception e) {
				}
			}
		}
		
		return jasperFileNameAndPath;
	}

	
	
	private void createFile(JasperPrint oJasperPrint, FileOutputStream oOut) throws Exception
	{
		if (this.oJasperHash.get_oJasperFileDef().MimeType.equals(JasperFileDef.MIMETYP_PDF))
		{
			JasperExportManager.exportReportToPdfStream(oJasperPrint,oOut);
		}
		else if (this.oJasperHash.get_oJasperFileDef().MimeType.equals(JasperFileDef.MIMETYP_XML))
		{
			JasperExportManager.exportReportToXmlStream(oJasperPrint, oOut);
		}
		else if (this.oJasperHash.get_oJasperFileDef().MimeType.equals(JasperFileDef.MIMETYP_XLS))
		{
			JRExporter exporter = new JRXlsExporter();
			//parameter, damit zahlen nicht als text exportiert werden 2010-07-15
			exporter.setParameter(JExcelApiExporterParameter.IS_DETECT_CELL_TYPE,  Boolean.TRUE);
			// -------------------------------------------------------
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, oJasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oOut);
			exporter.exportReport();
		}
		else if (this.oJasperHash.get_oJasperFileDef().MimeType.equals(JasperFileDef.MIMETYP_HTML))
		{
			JRExporter exporter = new JRHtmlExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, oJasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oOut);
			exporter.exportReport();
		}
		else if (this.oJasperHash.get_oJasperFileDef().MimeType.equals(JasperFileDef.MIMETYP_CSV))
		{
			JRExporter exporter = new JRCsvExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, oJasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oOut);
			exporter.exportReport();
		}
	}

	
	public E2_JasperHASH get_oJasperHash() 
	{
		return oJasperHash;
	}

	
	public String get_cNameOfFile4UserWithoutEnding() 
	{
		return cNameOfFile4UserWithoutEnding;
	}


	public String get_cFileEnding4User() 
	{
		return cFileEnding4User;
	}
	
	public String get_cJasperBaseName() 
	{
		return c_JasperBaseName;
	}
	
}
