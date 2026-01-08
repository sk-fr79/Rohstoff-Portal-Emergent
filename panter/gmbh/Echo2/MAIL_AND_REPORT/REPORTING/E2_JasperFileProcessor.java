package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName_Vector;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT_PIPELINE_POS;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.mail.MailException;
import panter.gmbh.indep.mail.SendMail_STD;

public class E2_JasperFileProcessor 
{

	private E2_JasperTempFile_and_pipePos_VECT  	vTempFilesToProcess = null;
	private E2_JasperHASH     						oJasperHASH = null;
	
	private myTempFile 								oResultingTempfile = null;
	
	
	public E2_JasperFileProcessor(	E2_JasperTempFile_and_pipePos_VECT vTempFilesToProcess, E2_JasperHASH oJasperHASH) 
	{
		super();
		this.vTempFilesToProcess = 	vTempFilesToProcess;
		this.oJasperHASH = 			oJasperHASH;
	}



	public MyE2_MessageVector   make_DirectPrinting() throws myException
	{

		MyE2_MessageVector    oMVRueck = new MyE2_MessageVector();
		
		
		Vector<String>  vFilesToDelete = new Vector<String>();
		
		for (E2_JasperTempFile_and_pipePos oFilePart : this.vTempFilesToProcess)
		{
			RECORD_REPORT_PIPELINE_POS  oPipelinePos =  oFilePart.get_oRecordPipelinePOS();
			E2_JasperTempFile           oTempFile = 	oFilePart.get_oJasperTempFile();	
			
			if (oPipelinePos==null)
			{
				throw new myException(this,"Error: Archiving without Pipeline is not possible !!");
			}

			
			if (oTempFile.get_oJasperHash().get_oJasperFileDef().MimeType.equals(JasperFileDef.MIMETYP_PDF))
			{
				E2_JasperFileProcessor.sendPdfFile_to_Printer(oTempFile,oPipelinePos,  this.erzeuge_lesbaren_filenamen());
				vFilesToDelete.add(oTempFile.get_NameOfCreatedFile());
			}
			else
			{
				oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Achtung! Direktdruck beim Dateityp: ",true,oTempFile.get_oJasperHash().get_oJasperFileDef().MimeType,false, " ist nicht möglich !",true)));
			}
		}
		
		oMVRueck.add(new MyE2_Info_Message(new MyE2_String("Es wurden ",true," "+this.vTempFilesToProcess.size(),false," Dokumente direkt gedruckt !",true)));
		
		//oMVRueck.add_MESSAGE(new E2_FileDeleter(vFilesToDelete).execute_DELETE());
		
		return oMVRueck;
	}

	
	
	
	/*
	 * 2011-06-27: optionaler direktdruck mit zusammengefassten belegen
	 */
	public MyE2_MessageVector   make_DirectPrinting_Zusammengefasst() throws myException
	{
		DEBUG.System_println("Betrete E2_JasperFileProcessor.make_DirectPrinting_Zusammengefasst()", DEBUG.DEBUG_FLAG_DIVERS1);
		
		MyE2_MessageVector    oMVRueck = new MyE2_MessageVector();
		
		
		
		
		//zuerst die druckbefehle als hash-keys in eine hashmap stellen, damit alle drucke, die zum gleichen drucker gesendet werden
		//zuerst verkettet werden koennen
		HashMap<String, E2_JasperTempFile_and_pipePos_VECT>  hmDruckSortierungNachZiel = new HashMap<String, E2_JasperTempFile_and_pipePos_VECT>();
		
		for (E2_JasperTempFile_and_pipePos oFilePart : this.vTempFilesToProcess)
		{
			if (	oFilePart.get_oRecordPipelinePOS()!=null && 
					oFilePart.get_oRecordPipelinePOS().get_UP_RECORD_DRUCKER_id_drucker()!=null && 
					S.isFull(oFilePart.get_oRecordPipelinePOS().get_UP_RECORD_DRUCKER_id_drucker().get_DIRECT_DRUCK_BEFEHL_cUF()))
			{
			
				String cDruckBefehl = oFilePart.get_oRecordPipelinePOS().get_UP_RECORD_DRUCKER_id_drucker().get_DIRECT_DRUCK_BEFEHL_cUF();
				if (!hmDruckSortierungNachZiel.containsKey(cDruckBefehl))
				{
					hmDruckSortierungNachZiel.put(cDruckBefehl, new E2_JasperTempFile_and_pipePos_VECT());
				}
				if (oFilePart.get_oJasperTempFile().get_oJasperHash().get_oJasperFileDef().MimeType.equals(JasperFileDef.MIMETYP_PDF))
				{
					hmDruckSortierungNachZiel.get(cDruckBefehl).add(oFilePart);
				}
				else
				{
					oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurde versucht, den Report ",true,oFilePart.get_oJasperTempFile().get_cJasperBaseName(),false," als nicht PDF zu drucken !",true)));
				}
			}
			else
			{
				oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Undefinierter Fehler beim Verketteten Mehrfachdruck!",true)));
			}
			
			if (oMVRueck.get_bHasAlarms())
			{
				return oMVRueck;
			}
		}		
		

		//jetzt die hashmap der verketteten drucke durchgehen
		Iterator<String> oIterDruckZiele = hmDruckSortierungNachZiel.keySet().iterator();
		while (oIterDruckZiele.hasNext())
		{
			String cDruckZiel = oIterDruckZiele.next();
			
			E2_JasperTempFile_and_pipePos_VECT oVJasper = hmDruckSortierungNachZiel.get(cDruckZiel);
			
			//hier werden die dateien zusammengefuegt und der jasperhash als file uebergeben
			E2_JasperTempFile_VECT  vJasperTempFiles = oVJasper.get_vJasperTempFile(); 
			
			oResultingTempfile = vJasperTempFiles.get_myTempFileAllConcated( vJasperTempFiles.get(0).get_cNameOfFile4UserWithoutEnding());
			oResultingTempfile.set_bDeleteAtFinalize(true); 				 //loeschen beim aufraeumen

			E2_JasperFileProcessor.sendPdfFile_to_Printer(oResultingTempfile,cDruckZiel,this.erzeuge_lesbaren_filenamen());

			oMVRueck.add(new MyE2_Info_Message(new MyE2_String("Es wurden ",true," "+oVJasper.size(),false," Dokumente zusammengefasst gedruckt !",true)));
		}
		
		DEBUG.System_println("Verlasse E2_JasperFileProcessor.make_DirectPrinting_Zusammengefasst()", DEBUG.DEBUG_FLAG_DIVERS1);
		
		return oMVRueck;
	}

	
	/**
	 * wird benutzt, um beim "MISBRAUCH" der direkt-kopie via scp einen neuen namen zu erzeugen, damit nicht der kryptische temp-filename 
	 * im zielordner landet
	 * @return
	 */
	private String erzeuge_lesbaren_filenamen() {
		String cNameLesbar = "druckdatei";
		
		//2013-07-04: weitere funktion fuer direkt-druck: die moeglichkeit, eine Datei auf einen zielort zu kopieren. dann ist es 
		//            sinnvoll, einen lesbaren namen zu vergeben (falls moeglich, den download-namen)
		if (this.oJasperHASH != null) {
			cNameLesbar = this.oJasperHASH.get_completeDownloadAndSendeName(true);
		}

		return cNameLesbar;
	}
	
	
	

	public MyE2_MessageVector   make_ControllMailing() throws myException
	{
		
		MyE2_MessageVector    oMVRueck = new MyE2_MessageVector();

		HashMap<String, FileWithSendName_Vector> hmMailAdressenSammler = new HashMap<String, FileWithSendName_Vector>();
		
		//zuerst die FileWithSendname aufbauen
		for (E2_JasperTempFile_and_pipePos oFilePart : this.vTempFilesToProcess)
		{

			FileWithSendName_Vector   vFileWithSendName = new FileWithSendName_Vector();
			
			RECORD_REPORT_PIPELINE_POS  oPipelinePos =  oFilePart.get_oRecordPipelinePOS();
			E2_JasperTempFile           oTempFile = 	oFilePart.get_oJasperTempFile();	
			
			if (oPipelinePos==null)
			{
				throw new myException(this,"Error: Controll-Email without Pipeline is not possible !!");
			}
			
			//archivname feststellen
			//variante 1: name der report-datei
			String cFileBaseName = oPipelinePos.get_REPORTFILEBASENAME_cF();

			
			//variante 2: wenn es kein folgereport in der pipeline ist, dann den namen via jasperHash
			//wenn die archivdatei keine angehaengte zusatz-report-datei war, dann den namen aus der JasperHash-Datei bauen lassen
			if (oFilePart.get_oJasperTempFile().get_cJasperBaseName().equals(oFilePart.get_oJasperTempFile().get_oJasperHash().get_cReportBaseName()))
			{
				cFileBaseName = oFilePart.get_oJasperTempFile().get_oJasperHash().get_completeDownloadAndSendeName(false);
			}
			
			//falls in der pipeline was eigenes veranstaltet wird, dann den nehmen
			if (S.isFull(oPipelinePos.get_SQL_ARCHIVFILENAME_cF()))
			{
				cFileBaseName = new Interpret_ARCHNAME(oPipelinePos.get_SQL_ARCHIVFILENAME_cF(), oTempFile.get_oJasperHash()).get_cArchName();
			}
			
			
			
			if (S.isEmpty(cFileBaseName))
			{
				cFileBaseName = "Controlmail";
			}

			vFileWithSendName.add(new FileWithSendName(		oFilePart.get_oJasperTempFile().get_NameOfCreatedFile(), 
															cFileBaseName+oTempFile.get_oJasperHash().get_oJasperFileDef().Endung, 
															oTempFile.get_oJasperHash().get_oJasperFileDef()));
			
			
			//jetzt die eMails initialisieren
			Vector<String> vMailTargets = new Vector<String>();

			if (S.isFull(oPipelinePos.get_MAIL_TARGETS_KOMMASEPARATED_cUF()))
			{
				String cMailTargets = bibALL.ReplaceTeilString(oPipelinePos.get_MAIL_TARGETS_KOMMASEPARATED_cUF(), "\n", ",");
				vMailTargets.addAll(bibALL.TrenneZeile(cMailTargets, ","));
			}
			else
			{
				oMVRueck.add_MESSAGE(new MyE2_Alarm_Message("Es gibt keine Ziel-Mail-Adressen für Kontroll-Mails !!"));
			}
			
			if (!oMVRueck.get_bIsOK())
			{
				break;
			}
			
			for (String cMailAdresse: vMailTargets)
			{
				if (!hmMailAdressenSammler.containsKey(cMailAdresse))
				{
					hmMailAdressenSammler.put(cMailAdresse, new FileWithSendName_Vector());
				}
				hmMailAdressenSammler.get(cMailAdresse).addAll(vFileWithSendName);
			}
			
		}
			
		
		Iterator<String>  oIter = hmMailAdressenSammler.keySet().iterator();
		
		while (oIter.hasNext())
		{
			String cEmail = 			oIter.next();
			FileWithSendName_Vector 	vVector = hmMailAdressenSammler.get(cEmail);
			
			
			try 
			{
				SendMail_STD oMail = new SendMail_STD(
						cEmail, 
						null,
						null,
						"Automatikmail aus Drucksystem", 
						"Automatikmail aus Drucksystem\n\n\n\n"+bibALL.get_RECORD_USER().get_MAIL_SIGNATUR_cUF_NN(""),
						vVector);
				
				oMail.sendViaStandardMail();
				
			} 
			catch (MailException e) 
			{
				e.printStackTrace();
				throw new myException(e.getErrorMessage());
			}
		}
		
		
		oMVRueck.add(new MyE2_Info_Message(new MyE2_String("Es wurden ",true," "+hmMailAdressenSammler.keySet().size(),false," Kontroll-Emails verschickt !",true)));

		
		return oMVRueck;
	}


	
	
	
	/**
	 * 2014-09-22: umstellung auf mehrfache archivierung, ein druck (z.B. mehrfachlieferschein) kann mehreren Druckprotokollzeilen zugeordnet werden
	 * @return
	 * @throws myException
	 */
	public MyE2_MessageVector   make_Archiving() throws myException
	{
		MyE2_MessageVector    oMVRueck = new MyE2_MessageVector();

		String cTableName4Archiv	= null;
		String cFileBaseName  		= null;
		//String cID_ARCHIV  			= null;
		
		
		
		for (E2_JasperTempFile_and_pipePos oFilePart : this.vTempFilesToProcess)
		{
			RECORD_REPORT_PIPELINE_POS  oPipelinePos =  oFilePart.get_oRecordPipelinePOS();
			E2_JasperTempFile           oTempFile = 	oFilePart.get_oJasperTempFile();	
			
			if (oPipelinePos==null)
			{
				throw new myException(this,"Error: Archiving without Pipeline is not possible !!");
			}
			
			//archivname feststellen
			//variante 1: name der report-datei
			cFileBaseName = oPipelinePos.get_REPORTFILEBASENAME_cF();

			
			//variante 2: wenn es kein folgereport in der pipeline ist, dann den namen via jasperHash
			//wenn die archivdatei keine angehaengte zusatz-report-datei war, dann den namen aus der JasperHash-Datei bauen lassen
			if (oFilePart.get_oJasperTempFile().get_cJasperBaseName().equals(oFilePart.get_oJasperTempFile().get_oJasperHash().get_cReportBaseName()))
			{
				cFileBaseName = oFilePart.get_oJasperTempFile().get_oJasperHash().get_completeDownloadAndSendeName(false);
			}
			
			//falls in der pipeline was eigenes veranstaltet wird, dann den nehmen
			if (S.isFull(oPipelinePos.get_SQL_ARCHIVFILENAME_cF()))
			{
				cFileBaseName = new Interpret_ARCHNAME(oPipelinePos.get_SQL_ARCHIVFILENAME_cF(), oTempFile.get_oJasperHash()).get_cArchName();
			}
					
			if (S.isEmpty(cFileBaseName))
			{
				oMVRueck.add_MESSAGE(new MyE2_Alarm_Message("Es gibt keinen Basis-Namen für die Archivdatei !!"));
			}
			
			
			Vector<Integer>  vIDsArchivTabelle=null;

			if (S.isFull(oPipelinePos.get_SQL_ARCHIV_ID_cUF()))
			{
				 vIDsArchivTabelle = new Interpret_ARCHID(oPipelinePos.get_SQL_ARCHIV_ID_cUF(), oTempFile.get_oJasperHash()).get_vID_for_Arch();
				
				if (vIDsArchivTabelle == null || vIDsArchivTabelle.size()==0)
				{
					oMVRueck.add_MESSAGE(new MyE2_Alarm_Message("Es gibt keine ID für die Archivdatei !!"));
				}
			}
			else
			{
				oMVRueck.add_MESSAGE(new MyE2_Alarm_Message("Es gibt keine Drucktabellen-ID's für die Archivdatei !!"));
			}
					
			if (oMVRueck.get_bIsOK()) {
		
				// !!! TABELLENNAME in Archiv-Table ohne JD_ oder JT_
				if (S.isFull(oPipelinePos.get_ARCHIV_TABLENAME_cUF())) 	{
					
					cTableName4Archiv = oPipelinePos.get_ARCHIV_TABLENAME_cUF();
					
					if (cTableName4Archiv.startsWith("JT_") || cTableName4Archiv.startsWith("JD_")) {
						cTableName4Archiv=cTableName4Archiv.substring(3);
					}
					
				} else 	{
					oMVRueck.add_MESSAGE(new MyE2_Alarm_Message("Es gibt keinen Tabellennamen für die Archivierung !!"));
				}
				
	
				if (oMVRueck.get_bIsOK()) {
					Archiver oArchiv = new Archiver("PRINTARCHIV_"+bibALL.get_cDateNOWInverse(""));
					
					String cNameOfArchivCopy = oArchiv.copyFilenameToNextFree(oTempFile.get_NameOfCreatedFile(), cFileBaseName+"_"+bibALL.get_cDateTimeNOWInverse()+this.oJasperHASH.get_oJasperFileDef().Endung);
					
					for (Integer iArchTableID: vIDsArchivTabelle) {
						String cID_ARCHIV = ""+iArchTableID.intValue();
						oArchiv.WriteFileInfoToArchiveTable(oArchiv.get_cSUB_DIR_IN_Archiv(),
																cNameOfArchivCopy,
																cFileBaseName+this.oJasperHASH.get_oJasperFileDef().Endung,
																"Archivierung aus Druckvorgang: "+cFileBaseName,
																 null,
																 null,
																 cTableName4Archiv,    //tabellenname ohne JT_
																 cID_ARCHIV,
																 this.oJasperHASH.get_oJasperFileDef().Endung,
																 this.oJasperHASH.get_IS_TYP_MAIL()?Archiver_CONST.MEDIENKENNER.EMAIL.get_DB_WERT():Archiver_CONST.MEDIENKENNER.PRINT.get_DB_WERT(),
																 null,
																 null,
																 null,null);
					}
					oMVRueck.add(new MyE2_Info_Message(new MyE2_String("Es wurden ",true," "+this.vTempFilesToProcess.size(),false," Dokumente archiviert !",true)));
				}
			}
		}
		
		
		return oMVRueck;
	}

	
	
	
	
	
	
	public MyE2_MessageVector   make_File4StandardProcessing() throws myException
	{
		DEBUG.System_println("Betrete E2_JasperFileProcessor.make_File4StandardProcessing()", DEBUG.DEBUG_FLAG_DIVERS1);

		MyE2_MessageVector    oMVRueck = new MyE2_MessageVector();
		
		if (this.vTempFilesToProcess.size()==0)
		{
			oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Ein Druckprozess enthält keine resultierende Datei zum Weiterverarbeiten !!")));
			return oMVRueck;
		}
		
		//hier werden die dateien zusammengefuegt und der jasperhash als file uebergeben
		E2_JasperTempFile_VECT  vJasperTempFiles = this.vTempFilesToProcess.get_vJasperTempFile(); 
		
		oResultingTempfile = vJasperTempFiles.get_myTempFileAllConcated( vJasperTempFiles.get(0).get_cNameOfFile4UserWithoutEnding());
		oResultingTempfile.set_bDeleteAtFinalize(true); 				 //loeschen beim aufraeumen
		this.oJasperHASH.get_vFileContainer().add(oResultingTempfile);   // ... aber erst beim aufraeumen der E2_JasperHash (!!Email-Versand!!) 
		
		this.oJasperHASH.set_HM_FILENAME_OF_TEMP_FILE(oResultingTempfile.getFileName());
		this.oJasperHASH.signDocument(oResultingTempfile.getFileName());
		this.oJasperHASH.set_oTempFileWithSendeName(new FileWithSendName(oResultingTempfile.getFileName(),this.oJasperHASH.get_completeDownloadAndSendeName(true),this.oJasperHASH.get_oJasperFileDef()));

		DEBUG.System_println("Verlasse E2_JasperFileProcessor.make_File4StandardProcessing()", DEBUG.DEBUG_FLAG_DIVERS1);
		
		return oMVRueck;
	}

	
	

	/**
	 * 
	 * @param oTempFile
	 * @param cShellCommand
	 * @param cNewFileName (wird uebergeben, wenn der "direktdruck" kein druck, sondern eine kopie ist
	 * @throws myException
	 */
	public static void sendPdfFile_to_Printer(myTempFile oTempFile, String cShellCommand, String cNewFileName) throws myException
	{
		String cDruckBefehl = null;
		if (S.isFull(cShellCommand))
		{
			cDruckBefehl = cShellCommand;
		}
		else
		{
			throw new myException("E2_JasperDirectPrinter:sendPdfFile_to_Printer: There is no print-Definition for this report !!!!");
		}

		
		//druckbefehl muss die sequenz "#datei#" enthalten
		//cDruckBefehl = " lp -d HP-LaserJet-2015 #datei#";
		
		try
		{
			//2013-07-04: es werden zwei platzhalter ausgetauscht: #datei# und #name#
			String cDruckBefehlKorr = bibALL.ReplaceTeilString(cDruckBefehl, "#datei#", oTempFile.getFileName());
			if (S.isEmpty(cNewFileName)) {
				cNewFileName = "druckdatei";
			}
			cDruckBefehlKorr = bibALL.ReplaceTeilString(cDruckBefehlKorr, "#name#", cNewFileName);
			Runtime.getRuntime().exec(cDruckBefehlKorr);
			
			//Runtime.getRuntime().exec(bibALL.ReplaceTeilString(cDruckBefehl, "#datei#", oTempFile.getFileName()));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			throw new myException("E2_JasperDirectPrinter:sendPdfFile_to_Printer: Error printing (2):"+e.getLocalizedMessage());
		}

		
	}


	
	
	/**
	 * 2011-06-27: fuer zusammengefasste drucke
	 * @throws myException
	 */
	public static void sendPdfFile_to_Printer(String cShellCommand, E2_JasperHASH  oJasperHash) throws myException
	{
		String cDruckBefehl = null;
		if (S.isFull(cShellCommand))
		{
			cDruckBefehl = cShellCommand;
		}
		else
		{
			throw new myException("E2_JasperDirectPrinter:sendPdfFile_to_Printer: There is no print-Definition for this report:"+ oJasperHash.get_cReportBaseName());
		}

		
		//druckbefehl muss die sequenz "#datei#" enthalten
		//cDruckBefehl = " lp -d HP-LaserJet-2015 #datei#";
		
		try
		{
			Runtime.getRuntime().exec(bibALL.ReplaceTeilString(cDruckBefehl, "#datei#", oJasperHash.get_HM_FILENAME_OF_TEMP_FILE()));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			throw new myException("E2_JasperDirectPrinter:sendPdfFile_to_Printer: Error printing:"+e.getLocalizedMessage());
		}

		
	}


	
	
	
	
	/**
	 * 
	 * @throws myException
	 */
	public static void sendPdfFile_to_Printer(E2_JasperTempFile  oTempfile, RECORD_REPORT_PIPELINE_POS  oPipeLinePos, String cNewFileName) throws myException
	{
		
		if (oPipeLinePos==null)
		{
			throw new myException("E2_JasperFileProcessor:sendPdfFile_to_Printer: Error: Without Pipeline not possible !!");
		}

		

		if (oPipeLinePos.get_UP_RECORD_DRUCKER_id_drucker()==null)
			throw new myException("E2_JasperFileProcessor:sendPdfFile_to_Printer: Tempfile: No Printer defined !!!");

		if (S.isEmpty(oPipeLinePos.get_UP_RECORD_DRUCKER_id_drucker().get_DIRECT_DRUCK_BEFEHL_cUF_NN("")))
			throw new myException("E2_JasperFileProcessor:sendPdfFile_to_Printer: Printer has not printcommand !!!");

		
		if (S.isEmpty(oPipeLinePos.get_UP_RECORD_DRUCKER_id_drucker().get_DIRECT_DRUCK_BEFEHL_cUF_NN("")))
			throw new myException("E2_JasperFileProcessor:sendPdfFile_to_Printer: Printer has not printcommand !!!");

		
		
		String cDruckBefehl = oPipeLinePos.get_UP_RECORD_DRUCKER_id_drucker().get_DIRECT_DRUCK_BEFEHL_cUF();
		
		
		//druckbefehl muss die sequenz "#datei#" enthalten
		//cDruckBefehl = " lp -d HP-LaserJet-2015 #datei#";
		
		try
		{
			//2013-07-04: es werden zwei platzhalter ausgetauscht: #datei# und #name#
			String cDruckBefehlKorr = bibALL.ReplaceTeilString(cDruckBefehl, "#datei#", oTempfile.getFileName());
			if (S.isEmpty(cNewFileName)) {
				cNewFileName = "druckdatei";
			}
			cDruckBefehlKorr = bibALL.ReplaceTeilString(cDruckBefehlKorr, "#name#", cNewFileName);
			Runtime.getRuntime().exec(cDruckBefehlKorr);

			
			//alt//  Runtime.getRuntime().exec(bibALL.ReplaceTeilString(cDruckBefehl, "#datei#", oTempfile.getFileName()));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			throw new myException("E2_JasperDirectPrinter:sendPdfFile_to_Printer: Error printing:"+e.getLocalizedMessage());
		}

		
	}

	
	
}
