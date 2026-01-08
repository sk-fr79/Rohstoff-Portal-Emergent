package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.MASK;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.HAD_Searcher;
import panter.gmbh.Echo2.components.E2_MessageBoxYesNo;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.activeReport_NG.IF_AR_Component;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HILFETEXT;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public class HADM_bt_Delete extends MyE2_Button  implements  IF_AR_Component{
	private String 			id_hilfeText = null;
	private GridLayoutData 	gl = MyE2_Grid.LAYOUT_CENTER_TOP(E2_INSETS.I(0, 0, 0, 0));

	
	private MyE2_MessageVector mv = new MyE2_MessageVector();

	
	public HADM_bt_Delete(String p_id_hilfeText) {
		super(E2_ResourceIcon.get_RI("delete_mini.png"));
		this.id_hilfeText=p_id_hilfeText;
		this.gl.setColumnSpan(1);
		
		this.setLayoutData(this.gl);
		
		this.add_oActionAgent(new ownAction());
		
		this.add_GlobalValidator(new HADM_bt_validator(p_id_hilfeText));

		this.setToolTipText(new MyE2_String("Den Eintrag in dieser Zeil löschen").CTrans());
		
	}

	@Override
	public GridLayoutData get_layoutData() {
		return this.gl;
	}

	@Override
	public Component comp() {
		return this;
	}

	
	private class ownAction extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			new ownPopupYesNo();
		}
	}
	

	private class ownPopupYesNo extends E2_MessageBoxYesNo {

		public ownPopupYesNo()	throws myException {
			super(	new MyE2_String("Eintrag löschen"), new MyE2_String("JA"),new MyE2_String("Nein"),
					bibVECTOR.get_Vector(new MyE2_String("Sicherheitsabfrage:"),new MyE2_String("Diesen Eintrag löschen ?")), 
					new ownActionDelete());
			
		}
			
	}
	
	private class ownActionDelete extends XX_ActionAgent {

		public ownActionDelete() {
			super();
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			HADM_bt_Delete.this.mv.clear();
			RECORD_HILFETEXT  rec = new RECORD_HILFETEXT(HADM_bt_Delete.this.id_hilfeText);
			HADM_bt_Delete.this.mv.add_MESSAGE(rec.DELETE());
			if (HADM_bt_Delete.this.mv.get_bIsOK()) {
				RECLIST_ARCHIVMEDIEN_ext rl = new RECLIST_ARCHIVMEDIEN_ext(_TAB.hilfetext.baseTableName(), HADM_bt_Delete.this.id_hilfeText, null, null);
				for (RECORD_ARCHIVMEDIEN ra: rl) {
					HADM_bt_Delete.this.mv.add_MESSAGE(new RECORD_ARCHIVMEDIEN_ext(ra).DELETE_DatasetAndFilesWhenPossible());
				}
			}
			new HAD_Searcher()._rebuild();
			bibMSG.add_MESSAGE(HADM_bt_Delete.this.mv);

		}
		
	}

	
}
