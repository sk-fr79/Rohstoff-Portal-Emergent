package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT_REITER;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class AW2__BasicContainer_RohstoffAuswertungen extends Project_BasicModuleContainer {

	//die auswertungen, die programmiert sind, kommen auf tab 1, die einfachen abfragelisten auf tab2
	private MyE2_TabbedPane          oTabbed = null; 

	
	public AW2__BasicContainer_RohstoffAuswertungen() throws myException  {
		super(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_ROHSTOFFAUSWERTUNGEN.get_callKey());
	
		this.set_iVerticalOffsetForTabbedPane(140);
		
		this.rebuild_tab_container();
	}

	
	public void rebuild_tab_container() throws myException {
		
		this.RESET_Content();
		this.oTabbed = new  MyE2_TabbedPane(700); 
		this.oTabbed.set_bAutoHeight(true);
		
		AW2_RECLIST_REPORT_REITER  rr = new AW2_RECLIST_REPORT_REITER();
		
		this.add(this.oTabbed,E2_INSETS.I_2_2_2_2);
		
		if (bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES()||bibALL.get_RECORD_USER().is_DEVELOPER_YES()) {
			AW2__Tab_Reiter t = new AW2__Tab_Reiter(null,null,this);
			this.oTabbed.add_Tabb(new MyE2_String("Auswertungen (alle)"), t, new ownActionAgentRefresh(t));
		}
		
		//feststellen, wieviele eintrage verteilt auf die m2n-struktur existieren
		for (int i=0;i<rr.get_vKeyValues().size();i++) {
			RECORD_REPORT_REITER  rrr = rr.get(i);
			AW2__Tab_Reiter t = new AW2__Tab_Reiter(rrr, null,this);
			this.oTabbed.add_Tabb(new MyE2_String(rrr.get_REITERNAME_cUF_NN("<reportgruppe>")),
					t, new ownActionAgentRefresh(t));
		}
		
		//hier noch einen reiter mit dem tab-modul anhaengen
		this.oTabbed.add_Tabb(new MyE2_String("Schnell-Abfragen"), new AW2_Report_CONTAINER(null));

	}
	
	
	private class ownActionAgentRefresh extends XX_ActionAgent {
		private AW2__Tab_Reiter oTab = null;
		public ownActionAgentRefresh(AW2__Tab_Reiter p_tab) {
			super();
			this.oTab = p_tab;
		}
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			this.oTab._rebuild_ButtonListe();
			this.oTab._refresh_ButtonAnzeige();
		}
	}

	
}
