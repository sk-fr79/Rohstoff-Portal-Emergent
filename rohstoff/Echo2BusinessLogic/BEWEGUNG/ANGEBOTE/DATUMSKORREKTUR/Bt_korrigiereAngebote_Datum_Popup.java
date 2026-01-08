package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE.DATUMSKORREKTUR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.XX_List_EXPANDER_4_ComponentMAP;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD_ANGEBOT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD_ANGEBOT;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.exceptions.myException;

public class Bt_korrigiereAngebote_Datum_Popup extends E2_Date_Selection_Von_Bis_Container {

	private Vector<String> vIdToUpdate = null;

	private XX_List_EXPANDER_4_ComponentMAP parent = null;

	public Bt_korrigiereAngebote_Datum_Popup(RECORD_VPOS_STD_ANGEBOT rec, XX_List_EXPANDER_4_ComponentMAP parent) throws myException {

		super(rec, VPOS_STD_ANGEBOT.gueltig_von, VPOS_STD_ANGEBOT.gueltig_bis);

		this.parent = parent;

		this.add_CloseActions(new closeAction(this));

		vIdToUpdate = new Vector<>();

		vIdToUpdate.add(rec.get_ID_VPOS_STD_cUF_NN(""));
	}

	public Bt_korrigiereAngebote_Datum_Popup(RECORD_VKOPF_STD rec, XX_List_EXPANDER_4_ComponentMAP parent) throws myException {
		super();

		this.parent = parent;

		this.add_CloseActions(new closeAction(this));

		RECLIST_VPOS_STD vposRecList = rec.get_DOWN_RECORD_LIST_VPOS_STD_id_vkopf_std();

		vIdToUpdate = new Vector<>();
		vIdToUpdate.addAll(vposRecList.get_vKeyValues());

		String[] datumRange = getMeinstenErscheinteDatum();

		this.setDatumRange(new myDateHelper(datumRange[0]), new myDateHelper(datumRange[1]));

	}

	public class closeAction extends XX_ActionAgentWhenCloseWindow{

		public closeAction(E2_BasicModuleContainer container) {
			super(container);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			parent.refreshDaughterComponent();
		}



	}


	@Override
	public void saveData() throws myException {
		for(String idTopUpdate: vIdToUpdate){
			RECORD_VPOS_STD_ANGEBOT recVposAngebot = new RECORD_VPOS_STD_ANGEBOT(new vgl(VPOS_STD_ANGEBOT.id_vpos_std, idTopUpdate).s());
			recVposAngebot.set_NEW_VALUE_GUELTIG_VON(this.getStartDate());
			recVposAngebot.set_NEW_VALUE_GUELTIG_BIS(this.getEndDate());
			recVposAngebot.UPDATE(true);
		}

	}


	private String[] getMeinstenErscheinteDatum() throws myException{
		ArrayList<String> datumListForCount =  new ArrayList<String>();

		for(String idToUpdate: vIdToUpdate){
			RECORD_VPOS_STD_ANGEBOT recAng = new RECORD_VPOS_STD_ANGEBOT(new vgl(VPOS_STD_ANGEBOT.id_vpos_std, idToUpdate).s());

			datumListForCount.add(
					myDateHelper.ChangeDBFormatStringToNormalString(recAng.get_GUELTIG_VON_cUF_NN(""))+"@"+
							myDateHelper.ChangeDBFormatStringToNormalString(recAng.get_GUELTIG_BIS_cUF_NN("")));

		}

		String result = mostCommon(datumListForCount);

		return result.split("@");
	}

	public static <T> T mostCommon(List<T> list) {
		Map<T, Integer> map = new HashMap<>();

		for (T t : list) {
			Integer val = map.get(t);
			map.put(t, val == null ? 1 : val + 1);
		}

		Entry<T, Integer> max = null;

		for (Entry<T, Integer> e : map.entrySet()) {
			if (max == null || e.getValue() > max.getValue())
				max = e;
		}

		return max.getKey();
	}

}
