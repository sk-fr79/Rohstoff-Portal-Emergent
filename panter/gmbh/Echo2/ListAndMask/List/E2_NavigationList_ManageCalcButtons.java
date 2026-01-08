package panter.gmbh.Echo2.ListAndMask.List;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.calculation.LIST_TITEL_CalcButton;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_COLUMNS_TO_CALC;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;

public class E2_NavigationList_ManageCalcButtons {

	private E2_NavigationList  o_NAVILIST = null;
	private E2_ComponentMAP    o_ComponentMAP = null;
	
	public E2_NavigationList_ManageCalcButtons(E2_NavigationList oNAVILIST, E2_ComponentMAP oComponentMAP) {
		super();
		this.o_NAVILIST = oNAVILIST;
		this.o_ComponentMAP = oComponentMAP;
	}
	
	public void DO_02_RegisterNavigationListToComponentAddons() throws myException {
		for (MyE2IF__Component oComp: this.o_ComponentMAP.get_hmRealComponents().values()) {
			for (E2IF__BelongsToNavigationList oCompAddonInHeader: oComp.EXT().get_vZusatzKomponentenInListenTitel()) {
				oCompAddonInHeader._SET_NAVILIST_THIS_BELONGS_TO(this.o_NAVILIST);
			}
		}
	}
	

	public void DO_01_Search_and_Add_StandardCalcButtonDefs() throws myException {
		
		//nur moeglich,wenn es eine vorhandene this.cMODULE_IDENTIFIER_OF_CONTAINING_MODULE gibt
		if (S.isFull(o_NAVILIST.get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE())) { 
			HashMap<String, HashMap<String, Vector<RECORD_COLUMNS_TO_CALC>>> hmCalcDefs = bibSES.get_set_SUM_COLUMES_IN_LISTMODULES();

			HashMap<String, Vector<RECORD_COLUMNS_TO_CALC>>  hmCols4Module = hmCalcDefs.get(o_NAVILIST.get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE());
			if (hmCols4Module != null) {
				for (MyE2IF__Component oComp: this.o_ComponentMAP.get_hmRealComponents().values()) {
					String cHASH = oComp.EXT().get_C_HASHKEY();
					if (S.isFull(cHASH) && hmCols4Module.containsKey(cHASH)) {
						//DEBUG.System_println(cHASH);
						Vector<RECORD_COLUMNS_TO_CALC> vCalcFields = hmCols4Module.get(cHASH);
						for (RECORD_COLUMNS_TO_CALC recCalc: vCalcFields) {
							oComp.EXT().get_vZusatzKomponentenInListenTitel().add(new LIST_TITEL_CalcButton(oComp, recCalc));
						}
					}
				}
			}
		}
	}
	
	
}
