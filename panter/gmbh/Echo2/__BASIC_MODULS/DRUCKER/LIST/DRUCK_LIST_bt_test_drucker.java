package panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER.LIST;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import com.itextpdf.text.DocumentException;

import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER.DRUCK_Test_process;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.DRUCKER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class DRUCK_LIST_bt_test_drucker extends MyE2_ButtonInLIST implements DS_IF_components4decision{

	private Vector<XX_ActionAgent> storage_vector_4_all_agents = new Vector<>();
	private Vector<XX_ActionAgent> storage_vector_4_standard_agents = new Vector<>();
	private Vector<DS_ActionAgent> storage_vector_4_decision_agents = new Vector<>();
	private HashMap<DS_ActionAgent, DS_PopupContainer4Decision> hm_descision_containers = new HashMap<>();

	public DRUCK_LIST_bt_test_drucker(){
		super("Test");
		this.setToolTipText("Testseite drucken");
		this._aaa(new confirm_druck_confirmation_agent(this));
		this._aaa(new Test_drucker__AA());
	}


	private class Test_drucker__AA extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			try {				
				E2_ComponentMAP oMAP = DRUCK_LIST_bt_test_drucker.this.EXT().get_oComponentMAP();
				SQLResultMAP  	oSqlResultMap = oMAP.get_oInternalSQLResultMAP();

				Rec20 drucker_definition_record = new Rec20(_TAB.drucker)._fill_id(oSqlResultMap.get_UnFormatedValue(DRUCKER.id_drucker.fieldName()));

				new DRUCK_Test_process(drucker_definition_record);

			} catch (InterruptedException | DocumentException | IOException e) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(e.getMessage()));
			}
		}

	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy{
		DRUCK_LIST_bt_test_drucker oDruckerTest_bt =  null;
		oDruckerTest_bt = new DRUCK_LIST_bt_test_drucker();
		return oDruckerTest_bt;
	}

	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_all_agents() throws myException {
		return storage_vector_4_all_agents;
	}

	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_standard_agents() throws myException {
		return storage_vector_4_standard_agents;
	}

	@Override
	public Vector<DS_ActionAgent> get_storage_vector_4_decision_agents() throws myException {
		return storage_vector_4_decision_agents;
	}

	@Override
	public HashMap<DS_ActionAgent, DS_PopupContainer4Decision> get_hm_descision_containers() throws myException {

		return hm_descision_containers;
	}

	private class confirm_druck_confirmation_agent extends DS_ActionAgent{

		MyE2_MessageVector omv;
		public confirm_druck_confirmation_agent(DS_IF_components4decision p_actionComponent) {
			super(p_actionComponent);

			omv= new MyE2_MessageVector();
		}

		@Override
		public Boolean make_decision_when_true_then_popup() throws myException {
			return true;
		}

		@Override
		public DS_PopupContainer4Decision generate_popupContainer(DS_IF_components4decision activeComponent)
				throws myException {
			return new ownPopupContainer(this.get_actionComponent());
		}

		@Override
		public void fill_popupContainer(DS_PopupContainer4Decision container) throws myException {
			E2_Grid4MaskSimple  gm = new E2_Grid4MaskSimple()
					._add(new MyE2_Label(new MyE2_String("Wollen sie eine Test Seite drucken?"),MyE2_Label.STYLE_TITEL_NORMAL()), new RB_gld()._span(2)._al(E2_ALIGN.CENTER_MID))
					._add(container.get_bt_OK()._t("Ja"), 	new RB_gld()._ins(E2_INSETS.I(2,10,5,2))._al(E2_ALIGN.CENTER_MID))
					._add(container.get_bt_NO()._t("Nein"), new RB_gld()._ins(E2_INSETS.I(2,10,5,2))._al(E2_ALIGN.CENTER_MID))
					._setSize(200,200);

			container.add(gm, E2_INSETS.I(2,3,2,3));

		}

		@Override
		public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {return null;}

	}
	
	private class ownPopupContainer extends DS_PopupContainer4Decision {

		public ownPopupContainer(DS_IF_components4decision p_motherComponent) {
			super(p_motherComponent);
		}

		@Override
		public int get_width_in_pixel() {
			return 400;
		}

		@Override
		public int get_height_in_pixel() {
			return 200;
		}

		@Override
		public MyE2_String get_titleText4PopUp() {
			return new MyE2_String("Test seite drucken");
		}
		
	}
}