package rohstoff.Echo2BusinessLogic._4_ALL;

import java.io.File;
import java.io.IOException;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_DRUCKER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;

public class Print_via_DirectPrinterDefinition {

	public Print_via_DirectPrinterDefinition(myTempFile  oFileToPrint, RECORD_DRUCKER  recDRUCKER) throws myException {
		super();
		
		if (S.isEmpty(recDRUCKER.get_DIRECT_DRUCK_BEFEHL_cUF_NN("")))
			throw new myException("Print_via_DirectPrinterDefinition: Printer has not printcommand !!!");

		String cDruckBefehl = recDRUCKER.get_DIRECT_DRUCK_BEFEHL_cUF();
		String cFileName = oFileToPrint.getFileName();
		
		//druckbefehl muss die sequenz "#datei#" enthalten
		//cDruckBefehl = " lp -d HP-LaserJet-2015 #datei#";
		

		this._print(cDruckBefehl, cFileName);
	}

	
	public Print_via_DirectPrinterDefinition(String  cFileToPrint, RECORD_DRUCKER  recDRUCKER) throws myException {
		super();
		
		if (S.isEmpty(recDRUCKER.get_DIRECT_DRUCK_BEFEHL_cUF_NN("")))
			throw new myException("Print_via_DirectPrinterDefinition: Printer has not printcommand !!!");

		String cDruckBefehl = recDRUCKER.get_DIRECT_DRUCK_BEFEHL_cUF();
		String cFileName = cFileToPrint;
		
		this._print(cDruckBefehl, cFileName);
	}

	
	
	
	private void _print(String cDruckBefehl, String cFileName) throws myException {
		
		if (!(new File(cFileName).exists())) {
			throw new myException("Print_via_DirectPrinterDefinition: Error printing:File is not existing: "+cFileName );
		}
		
		try
		{
			//2013-07-04: es werden zwei platzhalter ausgetauscht: #datei# und #name#
			String cDruckBefehlKorr = bibALL.ReplaceTeilString(cDruckBefehl, "#datei#", cFileName);
			Runtime.getRuntime().exec(cDruckBefehlKorr);
			
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Druckvorgang ausgeführt: ",true,cFileName,false)));
			
			
			//alt//  Runtime.getRuntime().exec(bibALL.ReplaceTeilString(cDruckBefehl, "#datei#", oTempfile.getFileName()));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			throw new myException("Print_via_DirectPrinterDefinition: Error printing:"+e.getLocalizedMessage());
		}

		
	}
	
	
}
