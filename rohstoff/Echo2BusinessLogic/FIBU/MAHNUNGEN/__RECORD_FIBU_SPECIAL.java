package rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN;

import java.io.File;
import java.util.Vector;

import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_VKOPF_RG_ext;

public class __RECORD_FIBU_SPECIAL extends RECORD_FIBU
{

	public __RECORD_FIBU_SPECIAL(RECORD_FIBU recordOrig)
	{
		super(recordOrig);
	}

	
//	public String get__Pfad_Zu_Archivdatei() throws myException
//	{
//		
//		String cPfad = null;
//		
//		if (this.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_DRUCK_GUTSCHRIFT)||  this.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_DRUCK_RECHNUNG))
//		{
//			RECORD_VKOPF_RG  recVkopf = this.get_UP_RECORD_VKOPF_RG_id_vkopf_rg();
//			
//			RECLIST_VKOPF_RG_DRUCK  reclistDruck = recVkopf.get_DOWN_RECORD_LIST_VKOPF_RG_DRUCK_id_vkopf_rg("NVL("+_DB.VKOPF_RG_DRUCK$IST_ORIGINAL+",'N')='N'","ID_VKOPF_RG_DRUCK",true);
//			
//			if (reclistDruck.get_vKeyValues().size()>0)
//			{
//				RECORD_VKOPF_RG_DRUCK  recDruck = reclistDruck.get(reclistDruck.get_vKeyValues().size()-1);   //den letzten druck rausziehen
//				
//				//kann eingesetzt werden fuer: JT_VKOPF_RG_DRUCK/JT_VKOPF_STD_DRUCK/JT_VKOPF_TPA_DRUCK/JT_VKOPF_KON_DRUCK/JT_VPOS_TPA_FUHRE_DRUCK
//				String cTableREF_ARCH = "VKOPF_RG_DRUCK";
//				String cTableREF_ID   = recDruck.get_ID_VKOPF_RG_DRUCK_cUF();
//				
//				//2014-11-05: den original-schalter bei den archivmedien beruecksichtigen
//				RECLIST_ARCHIVMEDIEN  recArch = new RECLIST_ARCHIVMEDIEN("SELECT * FROM "+bibE2.cTO()+".JT_ARCHIVMEDIEN WHERE JT_ARCHIVMEDIEN.TABLENAME="+bibALL.MakeSql(cTableREF_ARCH)+" AND "+
//																		"JT_ARCHIVMEDIEN.ID_TABLE="+cTableREF_ID+" AND NVL("+_DB.ARCHIVMEDIEN$IST_ORIGINAL+",'N')='N'");
//				
//				
//				//jetzt 3 moeglichkeiten:  kein eintrag, genau ein eintrag oder mehrere
//				if (recArch.get_vKeyValues().size()>=1)       //download startet
//				{
//					RECORD_ARCHIVMEDIEN recordArchiv = recArch.get(recArch.get_vKeyValues().size()-1);
//					
//					String cBasePath	= 	Archiver.truncate_FileSeparatorsFromPath(new Archiver("").get_cArchiveBasePath());
//					String cDocPath		=  	Archiver.truncate_FileSeparatorsFromPath(recordArchiv.get_PFAD_cUF());
//					String cDocName		=  	Archiver.truncate_FileSeparatorsFromPath(recordArchiv.get_DATEINAME_cUF());
//					String cDownloadName	=  	Archiver.truncate_FileSeparatorsFromPath(recordArchiv.get_DOWNLOADNAME_cUF_NN(recordArchiv.get_DATEINAME_cUF()));
//					String cCompletePath	= File.separator+cBasePath+File.separator+cDocPath+File.separator+cDocName;
//
//					cPfad = cCompletePath;
//					
//				}
//			}
//		}
//		
//		return cPfad;
//
//	}
	
	
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
