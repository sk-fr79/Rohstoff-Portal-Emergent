package rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN2;

import java.io.File;
import java.util.Vector;

import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_VKOPF_RG_ext;

public class __RECORD_FIBU_SPECIAL_ extends RECORD_FIBU
{

	public __RECORD_FIBU_SPECIAL_(RECORD_FIBU recordOrig)
	{
		super(recordOrig);
	}

	
	
	//2014-11-05: vereinheitlicht
	public String get__Pfad_Zu_Archivdatei() throws myException
	{
		
		String cPfad = null;
		
		if (this.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_DRUCK_GUTSCHRIFT)||  this.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_DRUCK_RECHNUNG))
		{
			RECORD_VKOPF_RG_ext  recVkopf = new RECORD_VKOPF_RG_ext(this.get_UP_RECORD_VKOPF_RG_id_vkopf_rg());
			
			//2015-06-12: umstellung wegen der separaten archivierung der kontenblaetter
			Vector<RECORD_ARCHIVMEDIEN> v_rec_arch = recVkopf.get_RECORD_ARCHIVMEDIEN_NEWEST_NOT_ORIGINAL(true);
				
			//jetzt 3 moeglichkeiten:  kein druckprotokoll, eines oder mehrere 
			if (v_rec_arch !=null && v_rec_arch.size()>0) {
				//hier wird das erste gefundene geholte (muss sichergestellt sein, dass das die rechnung ist)
				RECORD_ARCHIVMEDIEN ra = v_rec_arch.get(0);
				String cBasePath	= 	Archiver.truncate_FileSeparatorsFromPath(new Archiver("").get_cArchiveBasePath());
				String cDocPath		=  	Archiver.truncate_FileSeparatorsFromPath(ra.get_PFAD_cUF());
				String cDocName		=  	Archiver.truncate_FileSeparatorsFromPath(ra.get_DATEINAME_cUF());
				//String cDownloadName	=  	Archiver.truncate_FileSeparatorsFromPath(ra.get_DOWNLOADNAME_cUF_NN(ra.get_DATEINAME_cUF()));
				String cCompletePath	= File.separator+cBasePath+File.separator+cDocPath+File.separator+cDocName;

				cPfad = cCompletePath;
				
			}
		}
		
		return cPfad;

	}
	
}
