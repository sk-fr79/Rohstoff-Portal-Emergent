package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.MODEL_VIEW;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public abstract class BEW_ModelView_bt extends E2_Button {

	private String selectedId;


	public BEW_ModelView_bt(_TAB table) throws myException {
		super();
		this	._image(E2_ResourceIcon.get_RI("inforound.png"), true)
				._ttt(new MyE2_String("Anzeige der Datenstuktur des gewählten Bewegungssatzes ..."))
		;

		this.add_oActionAgent(new call_BewegungModel_ActionAgent(table));
	}

	public abstract String get_id_table() throws myException;

	private class call_BewegungModel_ActionAgent extends XX_ActionAgent{
		
		private _TAB table;

		
		public call_BewegungModel_ActionAgent(_TAB table) {
			this.table=table;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			selectedId = get_id_table();
			if (S.isFull(selectedId)) {
				BEW_ModelView_DisplayContainer modCont = new BEW_ModelView_DisplayContainer(selectedId, this.table);
				
				modCont.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1600), new Extent(1000), new MyE2_String("Anzeige der Datenstuktur des gewählten Bewegungssatzes ..."));
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Datensatz ist noch nicht geschrieben !")));
				
			}
		}
	}
}
