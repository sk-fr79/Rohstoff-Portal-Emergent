package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT.WF_ListSelektor_3_SETTING_MACRO.SELTYP;


public class WF_HEAD_LIST_SelectionComponentVector_Version3 extends E2_SelectionComponentsVector {
	
	//pseudeo-selektoren, die nur "haekchen richtig setzen"
	private WF_ListSelektor_3_SETTING_MACRO       sel_bt_select_all = 			null;
	private WF_ListSelektor_3_SETTING_MACRO       sel_bt_select_relevant = 		null;
	private WF_ListSelektor_3_super_user_selektor userIsPresent = 				null; 

	public WF_HEAD_LIST_SelectionComponentVector_Version3(	E2_NavigationList onavigationList,
															String module_kenner) throws myException {
		super(onavigationList);
		
		this.userIsPresent = 			new WF_ListSelektor_3_super_user_selektor();
		WF_ListSelektor_3_Zeitselektor  					sel_zeitbereich = 			new WF_ListSelektor_3_Zeitselektor();
		WF_ListSelektor_3_StatusOpenClosed  				statusOpenClosed = 			new WF_ListSelektor_3_StatusOpenClosed();
		WF_ListSelektor_3_StatusGeloescht_Ja_Nein  			statusDelYesNo = 			new WF_ListSelektor_3_StatusGeloescht_Ja_Nein();
		WF_ListSelektor_3_besitztAufgaben_JaNein	  		statusHatOderHatNicht =  	new WF_ListSelektor_3_besitztAufgaben_JaNein(null);
		WF_ListSelektor_3_multi_user_selektor 				userSetting6_user_roles = 	new WF_ListSelektor_3_multi_user_selektor(userIsPresent);
		WF_ListSelektor_3_Prioritaet                        sel_prio   = 				new WF_ListSelektor_3_Prioritaet();

		//benutzerdefininerte
		E2_ListSelector_DB_Defined 							db_basedSelektor =  		new E2_ListSelector_DB_Defined(module_kenner,250);

		
		//pseudeo-selektoren, die nur "haekchen richtig setzen"
		this.sel_bt_select_all = 			new WF_ListSelektor_3_SETTING_MACRO(SELTYP.ALL, this, userIsPresent.get_selField());
		this.sel_bt_select_relevant = 		new WF_ListSelektor_3_SETTING_MACRO(SELTYP.RELEVANT, this, userIsPresent.get_selField());

		//die macrobuttons jeweils nachkorrigieren
		userIsPresent.get_selField().add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				WF_HEAD_LIST_SelectionComponentVector_Version3.this.sel_bt_select_all.set_personalisation(WF_HEAD_LIST_SelectionComponentVector_Version3.this.userIsPresent.get_ActualWert());
				WF_HEAD_LIST_SelectionComponentVector_Version3.this.sel_bt_select_relevant.set_personalisation(WF_HEAD_LIST_SelectionComponentVector_Version3.this.userIsPresent.get_ActualWert());
			}
		});
		
		//die macrobuttons jeweils nachkorrigieren
		userIsPresent.get_bt_me().add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				WF_HEAD_LIST_SelectionComponentVector_Version3.this.sel_bt_select_all.set_personalisation(WF_HEAD_LIST_SelectionComponentVector_Version3.this.userIsPresent.get_ActualWert());
				WF_HEAD_LIST_SelectionComponentVector_Version3.this.sel_bt_select_relevant.set_personalisation(WF_HEAD_LIST_SelectionComponentVector_Version3.this.userIsPresent.get_ActualWert());
			}
		});
		
		
		this.add(statusOpenClosed);
		this.add(statusDelYesNo);
		this.add(statusHatOderHatNicht);
		this.add(userSetting6_user_roles);
		this.add(db_basedSelektor);
		this.add(userIsPresent);
		this.add(sel_prio);
		this.add(sel_zeitbereich);
		this.add(sel_bt_select_all);
		this.add(sel_bt_select_relevant);
		
		
		//den datenbank-definierten selektor nur anzeigen, wenn etwas drinsteht 
		E2_Grid block_diverse = new E2_Grid()._setSize(100)	._a(new RB_lab()._tr("Diverse")._fsa(-2)._i(), new RB_gld()._ins(E2_INSETS.I(0,0,0,1)));
		if (db_basedSelektor.get_bIsFilled()) {
			block_diverse._a(db_basedSelektor.get_oComponentForSelection());
		}
		

		
		//jede thematisch zusammengehoerende selektionseinheit ist in einem eigenen grid mit themenueberschrift
		E2_Grid  block_laufzettel_mit_beteiligung = new E2_Grid()._setSize(400)
																._a(userIsPresent.get_oComponentForSelection(), new RB_gld()._ins(1))
																._a(sel_bt_select_all.get_oComponentForSelection(), new RB_gld()._ins(1,5,1,1))
																._a(sel_bt_select_relevant.get_oComponentForSelection(), new RB_gld()._ins(1,5,1,1))
																;
		
		E2_Grid  block_benutzer_genauer_definieren = new E2_Grid()._setSize(400)._a(userSetting6_user_roles.get_oComponentForSelection(), 	new RB_gld()._ins(1)); 
		
		
		
		E2_Grid  block_status_des_laufzettels = new E2_Grid()._setSize(260)
													._a(new RB_lab()._tr("Status des Laufzettels")._fsa(-2)._i(), new RB_gld()._ins(2))
													._a(statusOpenClosed.get_oComponentForSelection())
													._a(statusDelYesNo.get_oComponentForSelection())
													;

		E2_Grid  block_eigenschaften_des_laufzettels = new E2_Grid()._setSize(260)
													._a(new RB_lab()._tr("Eigenschaften des Laufzettels")._fsa(-2)._i(), new RB_gld()._ins(2))
													._a(statusHatOderHatNicht.get_oComponentForSelection())
													;
		
		E2_Grid  block_offene_aufgaben_mit_faelligkeit = new E2_Grid()._setSize(250)._a(sel_zeitbereich.get_oComponentForSelection());
		

		E2_Grid block_prioritaet_der_aufgaben = 	  new E2_Grid()._setSize(520)._a(sel_prio.get_oComponentForSelection());



		//in der ersten spalte die 2 userbasierten selektoren
		E2_Grid g_help1 = new E2_Grid()._setSize(180,400)._a(block_laufzettel_mit_beteiligung, new RB_gld()._ins(2,0,2,5))._a(block_benutzer_genauer_definieren, new RB_gld()._ins(2,0,2,5));
		
		E2_Grid  gridContainer = 		new E2_Grid()._setSize(260,260,520,250)._bo_ddd()
														._a(g_help1, 										new RB_gld()._span(3))
														._a(block_offene_aufgaben_mit_faelligkeit, 			new RB_gld()._ins(2))
														._a(block_status_des_laufzettels, 					new RB_gld()._ins(2,2,10,2))
														._a(block_eigenschaften_des_laufzettels, 			new RB_gld()._ins(10,2,10,2))
														._a(block_prioritaet_der_aufgaben, 					new RB_gld()._ins(10,2,10,2))
														._a(block_diverse, 									new RB_gld()._ins(2))
														;
		
		this.set_oSelComponent(gridContainer);
		
	}

}
