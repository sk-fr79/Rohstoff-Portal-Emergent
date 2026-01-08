package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.EU_VERTRAG;

import java.util.HashMap;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class FS_COMP_Veranlasser_Empfaenger_chkBox_grid implements FS_Report_konstant_EU_VERTRAG{

	private RB_cb mandant_veranlasser_box;
	private RB_cb mandant_empfaenger_box;

	private RB_cb kunde_veranlasser_box;
	private RB_cb kunde_empfaenger_box;

	private E2_NavigationList navilist;

	private E2_Grid mainGrid;
	
	public FS_COMP_Veranlasser_Empfaenger_chkBox_grid(E2_NavigationList p_navilist, E2_Grid c_mainGrid) throws myException {

		super();

		this.mainGrid = c_mainGrid;
		
		this.setNavilist(p_navilist);

		RECORD_ADRESSE recadresse = new RECORD_ADRESSE(p_navilist.get_vSelectedIDs_Unformated_Select_the_one_and_only().get(0));
		String kundeName = recadresse.get_NAME1_cUF() + " " + recadresse.get_NAME2_cUF_NN(""); 
		String mandantName =  bibALL.get_MANDANT_NAME(false, false, true, true, false, false, false, false, false, "");


		_init();

		mainGrid
		._a(new RB_lab()._t("Definition: Wer liefert und wer bekommt Ware")._fsa(2)._b(), new RB_gld()._ins(10,5,5,5)._col_back_d()._span(2))
		._a(new RB_lab()._t(kundeName)._bi(), 	new RB_gld()._ins(10,5,5,5)._col_back_d())
		._a(new RB_lab()._t(mandantName)._bi(), new RB_gld()._ins(10,5,5,5)._col_back_d())
		._a(kunde_veranlasser_box, new RB_gld()._ins(10,5,5,5))
		._a(mandant_veranlasser_box, new RB_gld()._ins(10,5,5,5))
		._a(kunde_empfaenger_box, new RB_gld()._ins(10,5,5,25))
		._a(mandant_empfaenger_box, new RB_gld()._ins(10,5,5,25));
	}

	private void _init(){

		mandant_veranlasser_box = new RB_cb("Veranlasser");
		mandant_veranlasser_box.add_oActionAgent(new ownActionAgent());
		mandant_veranlasser_box.EXT().set_C_MERKMAL("mandant_supplier_box");

		mandant_empfaenger_box = new RB_cb("Empfänger");
		mandant_empfaenger_box.add_oActionAgent(new ownActionAgent());
		mandant_empfaenger_box.EXT().set_C_MERKMAL("mandant_consignee_box");

		kunde_veranlasser_box = new RB_cb("Veranlasser");
		kunde_veranlasser_box.add_oActionAgent(new ownActionAgent());
		kunde_veranlasser_box.EXT().set_C_MERKMAL("kunde_supplier_box");

		kunde_empfaenger_box = new RB_cb("Empfänger");
		kunde_empfaenger_box.add_oActionAgent(new ownActionAgent());
		kunde_empfaenger_box.EXT().set_C_MERKMAL("kunde_consignee_box");
	}

	public HashMap<String, String> getSelection(){
		HashMap<String, String> ergebnisMap = new HashMap<>();
		
		ergebnisMap.put(MANDANT_VERANLASSER, 	convertBooleanToString(mandant_veranlasser_box.isSelected()));
		ergebnisMap.put(MANDANT_EMPFAENGER, 	convertBooleanToString(mandant_empfaenger_box.isSelected()));
		ergebnisMap.put(KUNDE_VERANLASSER, 		convertBooleanToString(kunde_veranlasser_box.isSelected()));
		ergebnisMap.put(KUNDE_EMPFAENGER,		convertBooleanToString(kunde_empfaenger_box.isSelected()));
		
		return ergebnisMap;
	}

	private class ownActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_cb oCB = (RB_cb)bibE2.get_LAST_ACTIONEVENT().getSource();
			String[] kat = oCB.EXT().get_C_MERKMAL().toUpperCase().split("_");
			if(kat[0].equals(KUNDE)){
				if(kat[1].equals(EMPFAENGER)){
					mandant_veranlasser_box.setSelected(oCB.isSelected());
				}else if(kat[1].equals(VERANLASSER)){
					mandant_empfaenger_box.setSelected(oCB.isSelected());
				}
			}else if(kat[0].equals(MANDANT)){
				if(kat[1].equals(EMPFAENGER)){
					kunde_veranlasser_box.setSelected(oCB.isSelected());
				}else if(kat[1].equals(VERANLASSER)){
					kunde_empfaenger_box.setSelected(oCB.isSelected());
				}
			}
		}
	}

	public E2_NavigationList getNavilist() {
		return navilist;
	}

	public void setNavilist(E2_NavigationList navilist) {
		this.navilist = navilist;
	}

	public static String convertBooleanToString(boolean b){
		return b?"Y":"N";
	}
	
}
