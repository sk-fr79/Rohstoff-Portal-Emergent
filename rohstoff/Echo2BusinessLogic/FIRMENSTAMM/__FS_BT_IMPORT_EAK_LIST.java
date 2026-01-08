package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE_EAK_CODE;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ADRESSE_EAK_CODE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibTEXT;
import panter.gmbh.indep.exceptions.myException;

/**
 * button, um avv-code-listen zu exportieren (auf der haupt- und der Lieferadressen-Liste 
 * @author martin
 *
 */
public class __FS_BT_IMPORT_EAK_LIST extends MyE2_Button {
	private __FS_Component_MASK_DAUGHTER_EAK_CODES  oAVV_CODE_Daughter = null;

	public __FS_BT_IMPORT_EAK_LIST(__FS_Component_MASK_DAUGHTER_EAK_CODES oAVV_CODE_Daughter) {
		super(new MyE2_String("Import"));
		this.oAVV_CODE_Daughter = oAVV_CODE_Daughter;
		this.setToolTipText(new MyE2_String("Importieren der IDs der untenstehenden Liste").CTrans());
		this.add_oActionAgent(new ownActionShowImportPopup());
		this.add_GlobalAUTHValidator_AUTO(FS_CONST.MASK_FIELD_VALID_KEY_IMPORT_AVV_CODES);
		this.add_GlobalValidator(new ownValidator());
	}
	
	
	private class ownValidator extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated)		throws myException {
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			if (__FS_BT_IMPORT_EAK_LIST.this.EXT().get_oComponentMAP().get_bIs_Neueingabe()) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Diese Funktion steht bei Neuerfassung einer Adresse nicht zur Verfügung !")));
			}
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated)	throws myException {
			return null;
		}
		
	}
	
	
	private class ownActionShowImportPopup extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			__FS_BT_IMPORT_EAK_LIST oThis = __FS_BT_IMPORT_EAK_LIST.this;
			
			Vector<String> vIDs = new Vector<String>();
			
			vIDs.addAll(oThis.oAVV_CODE_Daughter.get_oNavigationList().get_vectorSegmentation());
			
			new ownContainerWithID_TextArea(vIDs);
		}
	}
	

	
	private class ownContainerWithID_TextArea extends E2_BasicModuleContainer
	{
		private MyE2_TextArea  		oTA = new MyE2_TextArea("",100,10000,30);
		
		public ownContainerWithID_TextArea(Vector<String> vIDs) throws myException {
			super();
			
			this.add(new MyE2_Label(new MyE2_String("Bitte AVV-Codes unten eintragen:"),true));
			this.add(oTA);
			this.add(new ownButtonImport());
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(200), new Extent(650), new MyE2_String("Import AVV-Codes"));
		}
		
		
		private class ownButtonImport extends MyE2_Button {

			public ownButtonImport() {
				super(new MyE2_String("Starte Import"));
				this.add_oActionAgent(new ownActionStartImport());
			}
		}

		
		private class ownActionStartImport extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				int iErfolg = 0;
				int iDoppelt = 0;
				int iFehler = 0;
				
				String cID_Adresse = __FS_BT_IMPORT_EAK_LIST.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				
				Vector<String> vIDs = bibTEXT.get_ZeilenAusTextblock(ownContainerWithID_TextArea.this.oTA.getText());
				
				RECORD_ADRESSE  recAdr = new RECORD_ADRESSE(cID_Adresse);
				RECLIST_ADRESSE_EAK_CODE rlEAK_Code = recAdr.get_DOWN_RECORD_LIST_ADRESSE_EAK_CODE_id_adresse();
				Vector<String> vIDsVorhanden = new Vector<String>();
				vIDsVorhanden.addAll(rlEAK_Code.get_ID_EAK_CODE_hmString_UnFormated("").values());
				
				
				for (String cID: vIDs) {
					MyLong lID = new MyLong(cID);
					if (lID.get_oLong() != null) {
						if (vIDsVorhanden.contains(""+lID.get_lValue())) {
							//System.out.println("Doppelt: "+cID);;
							iDoppelt++;
						} else {
							RECORDNEW_ADRESSE_EAK_CODE  recNewAVV = new RECORDNEW_ADRESSE_EAK_CODE();
							recNewAVV.set_NEW_VALUE_ID_ADRESSE(cID_Adresse);
							recNewAVV.set_NEW_VALUE_ID_EAK_CODE(lID.get_lValue());
							MyE2_MessageVector oMV_Err = new MyE2_MessageVector();
							recNewAVV.do_WRITE_NEW_ADRESSE_EAK_CODE(oMV_Err);
							if (oMV_Err.get_bHasAlarms()) {
								iFehler++;
								//System.out.println("Fehler: "+cID);;
							} else {
								iErfolg++;
								//System.out.println("Erfolg: "+cID);;
							}
						}
					} else {
						iFehler++;
						//System.out.println("Fehler(2): "+cID);;
					}
				}
				
				ownContainerWithID_TextArea.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				
				//liste neu bauen
				__FS_BT_IMPORT_EAK_LIST.this.oAVV_CODE_Daughter.get_oNavigationList()._REBUILD_COMPLETE_LIST("");
				
				bibMSG.add_MESSAGE(new MyE2_Message(new MyE2_String(
								"Importstatistik: Erfolgreich: ",true,""+iErfolg,false,
								",     Bereits vorhanden:",true,""+iDoppelt,false,
								",     Fehler beim Import: ",true,""+iFehler,false),MyE2_Message.TYP_INFO,false));
			}
		}

	}
	
	
	
	
	
	
	
	
}
