package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorFortschrittsbalken;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName_Vector;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock_Vector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.E2_FortsschrittsBalken;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;

public class V_JasperHASH extends Vector<E2_JasperHASH>
{

	private Vector<myTempFile> vTempFileNamesToDelete = new Vector<myTempFile>();
	
	
	
	public V_JasperHASH()
	{
		super();
	}

	
	protected void finalize()
	{
		bibMSG.add_MESSAGE(new E2_FileDeleter(this.vTempFileNamesToDelete).execute_DELETE());
	}
	
	/**
	 * @param oGrid4FortschrittsInfo  (falls der fortschrittsanzeiger eingestellt ist)
	 * @param bSkip_existing_Files 
	 * @param bPrintOrMail (true = print, false = mail)
	 */
	public boolean CREATE_TEMP_FILES_FOR_ALL_HASHMAPS(E2_ServerPushMessageContainer oServerPushContainer, boolean bSkip_existing_Files, boolean bPrintOrMail) throws myException
	{

		E2_FortsschrittsBalken  oBalken = new E2_FortsschrittsBalken(this.size(),20,new E2_ColorFortschrittsbalken());
		boolean bOK = true;
		
		for (int i = 0; i < this.size(); i++)
		{
			E2_JasperHASH oJasperHash = (E2_JasperHASH) this.get(i);
			
			if (oServerPushContainer != null)
			{
				//fuer die info einen wert raussuchen
				MyString cINFO_Ganz = new MyE2_String("Aufbau PDF ("+(i+1)+")"); 
				MyString cINFO = new MyE2_String(oJasperHash.get_cReportBaseName(),false);

				if (oJasperHash.get_vID_ADRESSE_FOR_MailLoad().size()>0)
				{
					cINFO.addUnTranslated(" --> Adresse:"+oJasperHash.get_vID_ADRESSE_FOR_MailLoad().get(0));
				}
				cINFO_Ganz.addString(cINFO);
				
				oServerPushContainer.get_oGridBaseForMessages().removeAll();
				oBalken.set_Wert(i);
				oServerPushContainer.get_oGridBaseForMessages().add(oBalken);
				oServerPushContainer.get_oGridBaseForMessages().add(new MyE2_Label(cINFO));
			}
			
			if (bSkip_existing_Files && S.isFull(oJasperHash.get_HM_FILENAME_OF_TEMP_FILE()))     //falls gewuenscht werden volle tempfiles uebersprungen
			{
				continue;
			}
			oJasperHash.Build_tempFile(bPrintOrMail);
			
			if (oServerPushContainer!=null && oServerPushContainer.get_bIsInterupted())
			{
				bOK = false;     //wurde abgebrochen
				break;
			}
		} 
		
		
		return bOK;
	}

	
	
	
	/**
	 *
	 * @param oGrid4FortschrittsInfo
	 * @throws myException
	 * übernimmt einen vector aus HashMaps in denen der pdf-dateiname unter dem schluessel CONST_JASPER.HM_FILENAME_OF_TEMP_FILE
	 * vorhanden ist. diese werden verkettet und downgeloadet
	 * @deprecated  reason this method is deprecated <br/>
               use {@link #DownloadFiles()} instead like this: 

	 */
	public void DOWNLOAD_FILES(MyE2_Grid oGrid4FortschrittsInfo) throws myException
	{
		/*
		 * jetzt schauen, ob im namensvector mehrere dateien sind, und diese ausgeben 
		 * (falls nötig verkettet)
		 */
		if (this.size()==1)
		{
			if (this.get(0).get_oTempFileWithSendeName() != null)
			{
				this.get(0).get_oTempFileWithSendeName().Download_File();
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Warning_Message("Keine Datei zum Download vorhanden !!!"));
			}
		}
		else
		{
			//hier alle JasperFileDef - objekte checken, ob sie aus pdfs bestehen, sonst geht da nicht
			for (int i=0;i<this.size();i++)
			{
				if (!this.get(i).get_oJasperFileDef().MimeType.equals(JasperFileDef.MIMETYP_PDF))
				{
					throw new myException(this,"downloading multiple dokument only for pdfs allowed !!");
				}
			}
			
			
			FileWithSendName_Vector  vFileWithSendName = this.get_vFileWithSendNames();
			if (vFileWithSendName.size()>=1)
			{
				this.vTempFileNamesToDelete.add(vFileWithSendName.DOWNLOAD_CONCATENATED_PDFS(oGrid4FortschrittsInfo));
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Warning_Message("Keine Datei zum Download vorhanden !!!"));
			}
			
			//aenderung 2010-10-05: loeschen der vorhandenen einzelfiles
			//bibMSG.add_MESSAGE(new E2_FileDeleter(this.get_vCompleteFileNames()).execute_DELETE());
		}
	}

	
	
	
	
	/**
	 * @param vHashMaps
	 * @param DeleteSingleDokuments
	 * @throws myException
	 * übernimmt einen vector aus HashMaps in denen der pdf-dateiname unter dem schluessel CONST_JASPER.HM_FILENAME_OF_TEMP_FILE
	 * vorhanden ist. diese werden verkettet und downgeloadet
	 */
	public void DownloadFiles(MyE2_Grid oGrid4FortschrittsInfo) throws myException {
		/*
		 * jetzt schauen, ob im namensvector mehrere dateien sind, und diese ausgeben 
		 * (falls nötig verkettet)
		 */
		if (this.size()==1) {
			if (this.get(0).get_oTempFileWithSendeName() != null)	{
				this.get(0).get_oTempFileWithSendeName().Download_File();
			} else {
				if (!checkCorrectDownloadSuppressionInPipeline()) {
					//nur dann warnen, sonst wird der anwender verwirrt!
					bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Keine Datei zum Download vorhanden !!!"));
				}
			}
		}
		else
		{
			//hier alle JasperFileDef - objekte checken, ob sie aus pdfs bestehen, sonst geht da nicht
			for (int i=0;i<this.size();i++) {
				if (!this.get(i).get_oJasperFileDef().MimeType.equals(JasperFileDef.MIMETYP_PDF)) {
					throw new myException(this,"downloading multiple dokument only for pdfs allowed !!");
				}
			}
			
			FileWithSendName_Vector  vFileWithSendName = this.get_vFileWithSendNames();
			if (vFileWithSendName.size()>=1) {
				this.vTempFileNamesToDelete.add(vFileWithSendName.DOWNLOAD_CONCATENATED_PDFS(oGrid4FortschrittsInfo));
			} else {
				if (!checkCorrectDownloadSuppressionInPipeline()) {
					//nur dann warnen, sonst wird der anwender verwirrt!
					bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Keine Datei zum Download vorhanden !!!"));
				}
			}
		}
	}

	
	private boolean checkCorrectDownloadSuppressionInPipeline() throws myException {
		//nachsehen, ob alle evtl. vorhandenen downloads durch scripte unterdrueckt wurden
		boolean isAllCorrect = true;
		for (E2_JasperHASH hash: this) {
			if (hash.getPipelinePositions().size()>0) {
				for (RECORD_REPORT_PIPELINE_POS_EXT pp: hash.getPipelinePositions()) {
					if (pp.isForDownload()) {
						if (pp.isSuppressedByScript()) {
							continue;
						} else {
							isAllCorrect = false;
							break;
						}
					}
				}
			}
		}
		return isAllCorrect;
	}
	
	
	
	public FileWithSendName_Vector get_vFileWithSendNames()
	{
		FileWithSendName_Vector vRueck = new FileWithSendName_Vector();
		
		for (int i=0;i<this.size();i++)
		{
			if (this.get(i).get_oTempFileWithSendeName()!=null)
			{
				vRueck.add(this.get(i).get_oTempFileWithSendeName());
			}
		}
		
		return vRueck;
	}
	
	
	public Vector<String> get_vCompleteFileNames()
	{
		Vector<String> vRueck = new Vector<String>();
		
		for (int i=0;i<this.size();i++)
		{
			if (this.get(i).get_oTempFileWithSendeName()!=null)
			{
				vRueck.add(this.get(i).get_oTempFileWithSendeName().get_cNameWithPath());
			}
		}
		
		return vRueck;
	}

	
	
	/**
	 * prueft, ob die E2_JasperHashes fuer mailVersand definiert sind (fuer die vorauswahl, ob ueberhaupt ein 
	 * Mailbutton angeboten werden soll
	 * @return
	 */
	public boolean get_bAll_Have_MAILBLOCK_with_Minimum_One_MailAdresse() throws myException
	{
		for (E2_JasperHASH oJasperHash:this)
		{
			if (!oJasperHash.get_bHasMAILBLOCK_With_Minimum_One_MailAdresse())
			{
				return false;
			}
		}
		return true;
	}
	
	
	
	
	

	/**
	 * 
	 * @return s V_MailBlock aller jasperHash-Objekte
	 * @throws myException
	 */
	public MailBlock_Vector BUILD_AND_GET_V_MAIL_BLOCKS() throws myException
	{
		MailBlock_Vector vRueckMailBlock = new MailBlock_Vector();
		
		for (int i=0;i<this.size();i++)
		{
			E2_JasperHASH oJSPHash = this.get(i);
			
			vRueckMailBlock.add(oJSPHash.get_BUILD_AND_GET_MAILBLOCK());
			
		}
		return vRueckMailBlock;
	}
	
	
	
	
	/**
	 * hilfsmethode zum extrahieren aller in eine hashmap
	 * unter dem kenner CONST_JASPER:HM_SQL_STACK_EXECUTE_BEFORE_REPORT
	 * gespeicherten vectoren
	 */
	public Vector<String> get_vSQL_STACK_EXECUTE_BEFORE_FROM_ALL_HASHMAPS()
	{
		Vector<String> vRueck = null;

		vRueck = new Vector<String>();
			
		for (int i=0;i<this.size();i++)
		{
			vRueck.addAll(this.get(i).get_vSQL_STATEMENTS_BEFORE_REPORT());
		}
		return vRueck;
	}



	
	/**
	 * hilfsmethode zum extrahieren aller in eine hashmap
	 * unter dem kenner CONST_JASPER:HM_SQL_STACK_EXECUTE_BEFORE_REPORT
	 * gespeicherten vectoren
	 */
	public Vector<String> get_vSQL_STACK_EXECUTE_AFTER_FROM_ALL_HASHMAPS()
	{
		Vector<String> vRueck = null;

		vRueck = new Vector<String>();
			
		for (int i=0;i<this.size();i++)
		{
			vRueck.addAll(this.get(i).get_vSQL_STATEMENTS_AFTER_REPORT());
		}
		return vRueck;
	}


	
	
	public boolean get_bAreALLDesignedForMail() throws myException
	{
		for (int i=0;i<this.size();i++)
		{
			if (!this.get(i).get_bIsDesignedForMail())
			{
				return false;
			}
		}
		
		return true;
	}
	
	
	
	/*
	 * aenderung 2010-10-19: aenderung wegen pipeline
	 * Vectorweites setzen der verarbeitungsmerkmale
	 */
	public void set_TYPE_PRINT()
	{
		for (E2_JasperHASH oHash: this)
		{
			oHash.put(E2_JasperHASH.HASH_TYPE, E2_JasperHASH.VALUE_PRINT);
		}
	}
	public void set_TYPE_MAIL()
	{
		for (E2_JasperHASH oHash: this)
		{
			oHash.put(E2_JasperHASH.HASH_TYPE, E2_JasperHASH.VALUE_MAIL);
		}
	}
	public void set_TYPE_PREVIEW()
	{
		for (E2_JasperHASH oHash: this)
		{
			oHash.put(E2_JasperHASH.HASH_TYPE, E2_JasperHASH.VALUE_PREVIEW);
		}
	}


	
	
}
