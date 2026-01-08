package panter.gmbh.Echo2.ListAndMask.List.Export;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;

public class EXP_popup_menue_exporter extends MyE2_PopUpMenue {

	private E2_NavigationList navigationlist = null;

	private EXP_bt_export_CSV_pre_selections exp_csv = null; 
	
	public EXP_popup_menue_exporter(E2_NavigationList p_navigationlist) {
		this(p_navigationlist,null,null,null,null);
	}

	/**
	 * 
	 * @param p_navigationlist
	 * @param toolTips (MyE2_String)
	 * @param auth_modul_kenner
	 * @param auth_button_kenner_excel
	 * @param auth_button_kenner_csv
	 */
	public EXP_popup_menue_exporter(	E2_NavigationList 	p_navigationlist, 
										MyE2_String         toolTips,
										String 				auth_modul_kenner, 
										String 				auth_button_kenner_excel, 
										String 				auth_button_kenner_csv) {
		super(E2_ResourceIcon.get_RI("export_pop.png"),E2_ResourceIcon.get_RI("export_pop__.png"),false);
		this.navigationlist = p_navigationlist;
		
		this.addButton(new EXP_bt_export_excel(this.navigationlist,auth_modul_kenner,auth_button_kenner_excel), true);
		
		if (toolTips==null) {
			toolTips=new MyE2_String("Export der Liste ins XLS oder CSV-Format (momentane Auswahl)");
		}
		
		this.exp_csv = new EXP_bt_export_CSV_pre_selections(this.navigationlist,auth_modul_kenner,auth_button_kenner_csv);
		this.addButton(exp_csv, true);
		
	}

	
	

	public Vector<EXP_addOnRecords> get_v_append_recs_4_csv_export() {
		return exp_csv.get_v_append_recs();
	}

	
}
