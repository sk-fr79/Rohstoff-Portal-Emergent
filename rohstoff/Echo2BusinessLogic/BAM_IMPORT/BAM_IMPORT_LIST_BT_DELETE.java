package rohstoff.Echo2BusinessLogic.BAM_IMPORT;


import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentMULTIDELETE;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BAM_IMPORT_INFO;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_EXPORT_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BAM_IMPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BAM_IMPORT_INFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EXPORT_LOG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_EXPORT_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_EXPORT_RG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.archive.ArchiverFileChecker;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.FIBU_EXPORT_LIST_ButtonDelete;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public class BAM_IMPORT_LIST_BT_DELETE extends MyE2_Button
{

	public BAM_IMPORT_LIST_BT_DELETE(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("LOESCHE_BAM_IMPORT"));
	}
	

	private class ownActionAgent extends ButtonActionAgentMULTIDELETE
	{
		public ownActionAgent(E2_NavigationList onavigationList,  MyE2_Button oownButton)
		{
			super(new MyE2_String("Loeschen von -Eintraegen"), onavigationList);
		}

		
		public Vector<String> get_vSQL_Before_DELETE(String cID_toDeleteUnformated)  {
			System.out.println(cID_toDeleteUnformated);
			return  new Vector<String>();
			
		}
		public Vector<String> get_vSQL_After_DELETE(String cID_toDeleteUnformated) 			{return  new Vector<String>();}
		public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) throws myException 	{return  new MyE2_MessageVector();}
		public void Execute_After_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {}
		public void Execute_Before_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {
			System.out.print("Deleting "+vIDs_toDeleteUnformated);
		
			Archiver arch = new Archiver.Builder("BAM",
					Archiver_CONST.MEDIENKENNER.IMPORT_ABZUG)
					.build();
			
			for (String id: vIDs_toDeleteUnformated) {
				RECORD_BAM_IMPORT rbi = new RECORD_BAM_IMPORT(id);

				RECLIST_BAM_IMPORT_INFO rbiil = rbi.get_DOWN_RECORD_LIST_BAM_IMPORT_INFO_id_bam_import();
				for (RECORD_BAM_IMPORT_INFO rbii : rbiil) {
					rbii.DELETE();
				}
				
				RECLIST_ARCHIVMEDIEN rla = new RECLIST_ARCHIVMEDIEN_ext (_DB.BAM_IMPORT, id, Archiver_CONST.MEDIENKENNER.IMPORT_ABZUG);

				// Delete "Archivmedien" associated with this entry if not linked to other files
				for (RECORD_ARCHIVMEDIEN ra : rla) {
					ArchiverFileChecker afc = new ArchiverFileChecker(ra.get_PFAD_cUF(), ra.get_DATEINAME_cUF());
					if (afc.canDelete()) {
						afc.getLastFile().delete();
						DEBUG.System_println("Deleting stale archive file: "+id);
					}
					
					// mp: den Datensatz der Record-Archivmedien löschen 
					ra.DELETE();
				}
				
				rbi.DELETE();
			}
		}
	}
}
