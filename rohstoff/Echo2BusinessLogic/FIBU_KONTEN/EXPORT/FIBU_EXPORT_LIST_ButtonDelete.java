package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT;


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
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_EXPORT_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EXPORT_LOG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_EXPORT_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_EXPORT_RG;
import panter.gmbh.indep.exceptions.myException;

public class FIBU_EXPORT_LIST_ButtonDelete extends MyE2_Button
{

	private final E2_NavigationList  o_NaviList;
	public FIBU_EXPORT_LIST_Selector o_Selektor;
	
	public FIBU_EXPORT_LIST_ButtonDelete(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		
		this.o_NaviList = onavigationList;
		
		this.add_oActionAgent(new ownActionAgent(onavigationList,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("LOESCHE_FIBU_EXPORT"));
		this.add_GlobalValidator(new ownValidDontDeleteWhenMaterialized());
	}
	
	public void setSelektor(FIBU_EXPORT_LIST_Selector s) {
		o_Selektor = s;
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
		public void Execute_Before_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {}

	}
	

    private class ownValidDontDeleteWhenMaterialized extends XX_ActionValidator {
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			E2_NavigationList  oNaviList = FIBU_EXPORT_LIST_ButtonDelete.this.o_NaviList;
			
			Vector<String> vIDs_UF = oNaviList.get_vSelectedIDs_Unformated();

			// Default case; materialized is deemed to be true, so no records will be
			// deleted; the first check on a record sets this to false if deletion 
			// is allowed
			boolean isMaterialized = true;
			String deleteKopfId = "";
			for (String cID: vIDs_UF) {
				RECORD_VPOS_EXPORT_RG  recPOS;
				try {
					recPOS = new RECORD_VPOS_EXPORT_RG(cID);
					RECORD_EXPORT_LOG recLog = new RECORD_EXPORT_LOG(recPOS.get_ID_EXPORT_LOG_cUF());
					isMaterialized = recLog.is_MATERIALISIERT_YES();
					deleteKopfId = recPOS.get_ID_VKOPF_RG_cUF();
				} catch (myException e) {
					// This can fail because of multiple records to be deleted (the first vpos to be
					// deleted causes all vpos' belonging to the same vkopf to be deleted; hence,
					// the IDs in the vIDs_UF vector may already have been deleted.
					
				}
				
				
				if (isMaterialized) {
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Achtung Der Datensatz mit der ID ",true, ""+cID,false," ist bereits festgeschrieben ! Löschen verboten!",true)));
				} else if (!deleteKopfId.equals("")){
					// Delete all vpos with vkopf_id deleteKopfId
					RECLIST_VPOS_EXPORT_RG allPOSWithVKopf = new RECLIST_VPOS_EXPORT_RG("ID_VKOPF_RG = "+deleteKopfId+"", "");
					for (RECORD_VPOS_EXPORT_RG tmp : allPOSWithVKopf.values() ) {
						tmp.DELETE();
					}
					/*
					try {
						//System.out.println("Lösche Kopf selbst: "+deleteKopfId);
						RECORD_VKOPF_EXPORT_RG  recKopf = new RECORD_VKOPF_EXPORT_RG(deleteKopfId);
						recKopf.DELETE();
					} catch (myException e) {
						// Fail silently sind the vkopf may also already have been deleted
					}
					*/
				}
			}
			// Refreshe Selektor (Dropdown)
			if (FIBU_EXPORT_LIST_ButtonDelete.this.o_Selektor != null) {
				FIBU_EXPORT_LIST_ButtonDelete.this.o_Selektor.refreshSelectField(-1);
				oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Löschen erfolgt.",true)));
			}
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
			return null;
		}
    }
}