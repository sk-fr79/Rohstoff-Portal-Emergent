package rohstoff.Echo2BusinessLogic.FIBU;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_VKOPF_RG_ext;

public class FIBU_LIST_BT_DownloadBeleg extends MyE2_ButtonInLIST
{

	public FIBU_LIST_BT_DownloadBeleg() 
	{
		super(E2_ResourceIcon.get_RI("down.png"), true);
		this.add_GlobalAUTHValidator_AUTO("DOWNLOAD_PRINTARCHIV");
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new ownValidator());
		this.setToolTipText(new MyE2_String("Originaldruck (wenn mehrere vorhanden, dann den neuesten) aus dem Archiv holen").CTrans());
		this.EXT().set_oColExtent(new Extent(20));
		
	}

	
	private class ownValidator  extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated)	throws myException 
		{
			//die aktuellen werte beschaffen
			E2_ComponentMAP oMAP = FIBU_LIST_BT_DownloadBeleg.this.EXT().get_oComponentMAP();
			SQLResultMAP     oResultMap = oMAP.get_oInternalSQLResultMAP();

			MyE2_MessageVector oMV = new MyE2_MessageVector();
			

			if (!(oResultMap.get_UnFormatedValue("BUCHUNGSTYP").equals("DRUCK_GUTSCHRIFT")||  oResultMap.get_UnFormatedValue("BUCHUNGSTYP").equals("DRUCK_RECHNUNG")))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Nur für Einträge vom Typ RECHNUNG/GUTSCHRIFT können Belege aus dem Archiv geholt werden!"));
			}
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated)		throws myException 
		{
			return null;
		}
		
	}

	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_ComponentMAP oMAP = FIBU_LIST_BT_DownloadBeleg.this.EXT().get_oComponentMAP();
			SQLResultMAP     oResultMap = oMAP.get_oInternalSQLResultMAP();
			
			if (oResultMap.get_UnFormatedValue("BUCHUNGSTYP").equals("DRUCK_GUTSCHRIFT")||  oResultMap.get_UnFormatedValue("BUCHUNGSTYP").equals("DRUCK_RECHNUNG"))
			{
//				RECORD_VKOPF_RG  recVkopf = new RECORD_VKOPF_RG(oResultMap.get_LActualDBValue("ID_VKOPF_RG", false));
//				
//				RECLIST_VKOPF_RG_DRUCK  reclistDruck = recVkopf.get_DOWN_RECORD_LIST_VKOPF_RG_DRUCK_id_vkopf_rg("NVL("+_DB.VKOPF_RG_DRUCK$IST_ORIGINAL+",'N')='N'","ID_VKOPF_RG_DRUCK",true);
//				
//				if (reclistDruck.get_vKeyValues().size()>0)
//				{
//					RECORD_VKOPF_RG_DRUCK  recDruck = reclistDruck.get(reclistDruck.get_vKeyValues().size()-1);   //den letzten druck rausziehen
//					
//					//kann eingesetzt werden fuer: JT_VKOPF_RG_DRUCK/JT_VKOPF_STD_DRUCK/JT_VKOPF_TPA_DRUCK/JT_VKOPF_KON_DRUCK/JT_VPOS_TPA_FUHRE_DRUCK
//					String cTableREF_ARCH = "VKOPF_RG_DRUCK";
//					String cTableREF_ID   = recDruck.get_ID_VKOPF_RG_DRUCK_cUF();
//					
//					
//					RECLIST_ARCHIVMEDIEN  recArch = new RECLIST_ARCHIVMEDIEN("SELECT * FROM "+bibE2.cTO()+".JT_ARCHIVMEDIEN WHERE JT_ARCHIVMEDIEN.TABLENAME="+bibALL.MakeSql(cTableREF_ARCH)+" AND "+
//																			"JT_ARCHIVMEDIEN.ID_TABLE="+cTableREF_ID);
//
//					
//					//jetzt 3 moeglichkeiten:  kein eintrag, genau ein eintrag oder mehrere
//					if (recArch.get_vKeyValues().size()==0)
//					{
//						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es existiert zu diesem Eintrag keine Archivdatei !"));
//					}
//					else if (recArch.get_vKeyValues().size()>=1)       //download startet
//					{
//						new DownLoader(recArch.get(recArch.get_vKeyValues().size()-1));   //den letzten holen
//					}
//				}
				
				//2015-06-11: beruecksichtigen, dass es auch archiveintraege mit kontenblatt gibt
				RECORD_VKOPF_RG_ext  recVkopf = new RECORD_VKOPF_RG_ext(oResultMap.get_LActualDBValue("ID_VKOPF_RG", false));

				Vector<RECORD_ARCHIVMEDIEN> v_rec_archiv_medien = recVkopf.get_RECORD_ARCHIVMEDIEN_NEWEST_NOT_ORIGINAL(false);
				
				if (v_rec_archiv_medien.size()==0l) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es existiert zu diesem Eintrag keine Archivdatei !"));
				} else 	{
					RECORD_ARCHIVMEDIEN_ext rec_arch_med = new RECORD_ARCHIVMEDIEN_ext(v_rec_archiv_medien.get(0));
					if (rec_arch_med.is_PDF()) {
						RECLIST_ARCHIVMEDIEN_ext rl_am_ext = new RECLIST_ARCHIVMEDIEN_ext();
						rl_am_ext.ADD(v_rec_archiv_medien);
						rl_am_ext.generate_pdf_tempFile_concatenated().starteDownLoad(
								v_rec_archiv_medien.get(0).get_DOWNLOADNAME_cUF_NN(v_rec_archiv_medien.get(0).get_DATEINAME_cUF_NN("download.pdf")), "application/pdf");
					} else {
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Archivdatei ist kein PDF ???!"));
					}
				}
			}
		}
	}
	

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		FIBU_LIST_BT_DownloadBeleg  oBT_Copy = null;
		oBT_Copy = new FIBU_LIST_BT_DownloadBeleg();
		return oBT_Copy;
	}
}
