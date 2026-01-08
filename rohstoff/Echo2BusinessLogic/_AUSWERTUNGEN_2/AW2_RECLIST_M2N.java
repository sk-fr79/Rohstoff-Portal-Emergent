package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.M2N;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_M2N;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT_REITER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_M2N_ext;

public class AW2_RECLIST_M2N extends RECLIST_M2N_ext {

	private HashMap<String, AW2_RECORD_REPORT>  		hm_report = 		new HashMap<String, AW2_RECORD_REPORT>();
	private HashMap<String, RECORD_REPORT_REITER>  		hm_report_reiter = 	new HashMap<String, RECORD_REPORT_REITER>();
	
	
	public AW2_RECLIST_M2N() throws myException {
		super(_TAB.report,_TAB.report_reiter);
	}

	
	
	
	
	public AW2_RECLIST_M2N(String id_report, String id_report_reiter) throws myException {
		this();
		SEL sel = new SEL(_TAB.m2n)
							.FROM(_TAB.m2n)
							.WHERE(new vgl(M2N.table_base_1, _TAB.report.baseTableName()))
							.AND(new vgl(M2N.table_base_2, _TAB.report_reiter.baseTableName()))
							.AND(new vgl(M2N.table_id_1, id_report))
							.AND(new vgl(M2N.table_id_2, id_report_reiter));

		this.set_cQueryString(sel.s());
		this.REFRESH();
	
		
	}
	
	
	public AW2_RECLIST_M2N( String id_report_reiter) throws myException {
		this();
		SEL sel = new SEL(_TAB.m2n)
							.FROM(_TAB.m2n)
							.WHERE(new vgl(M2N.table_base_1, _TAB.report.baseTableName()))
							.AND(new vgl(M2N.table_base_2, _TAB.report_reiter.baseTableName()))
							.AND(new vgl(M2N.table_id_2, id_report_reiter));

		this.set_cQueryString(sel.s());
		this.REFRESH();
	}

	
	
	/**
	 * liefert die vorhandenen reiter, die etwas enthalten, sortiert nach dem m2n-sortierfeld
	 * @return
	 * @throws myException
	 */
	public Vector<AW2_RECORD_REPORT>  get_v_reports_from_reiter(RECORD_REPORT_REITER rr) throws myException {
		Vector<AW2_RECORD_REPORT> v_rueck = new Vector<AW2_RECORD_REPORT>();
		
		for (RECORD_M2N  m2n: this)	{
			if (m2n.get_TABLE_ID_2_cUF().equals(rr.get_ID_REPORT_REITER_cUF())) {
				v_rueck.add(new AW2_RECORD_REPORT(m2n.get_TABLE_ID_1_cUF(),m2n));
			}
		}
		
		v_rueck.sort(new Comparator<AW2_RECORD_REPORT>() {
			@Override
			public int compare(AW2_RECORD_REPORT o1, AW2_RECORD_REPORT o2) {
				try {
					return o1.get_m2n().get_SORT1_cUF_NN("0").compareTo(o2.get_m2n().get_SORT1_cUF_NN("0"));
				} catch (myException e) {
					return 0;
				}
			}
		});
		
		Vector<AW2_RECORD_REPORT> v_rg = new Vector<AW2_RECORD_REPORT>();
		v_rg.addAll(v_rueck);
		
		return v_rg;
	}
	
	
	public int countNumber(int id_report) throws myException {
		int i_rueck = 0;
		
		for (RECORD_M2N  m2n: this.values()) {
			if (m2n.get_TABLE_ID_1_lValue(0l).intValue()==id_report) {
				i_rueck ++;
			}
		}
		return i_rueck;
	}
	
	
	public String get_tooltip_info_verteilung(int id_report) throws myException {
		String c_rueck = "";
		
		for (RECORD_M2N  m2n: this.values()) {
			if (m2n.get_TABLE_ID_1_lValue(0l).intValue()==id_report) {
				RECORD_REPORT_REITER rr = new RECORD_REPORT_REITER(m2n.get_TABLE_ID_2_cUF_NN(""));
				c_rueck = c_rueck+rr.get_REITERNAME_cUF_NN("--")+"\n";
			}
		}
		return c_rueck;
	}

	
	
	
	public RECORD_REPORT get_record_report(RECORD_M2N rec_m2n) throws myException {
		if (this.hm_report.get(rec_m2n.get_ID_M2N_cUF())==null) {
			this.hm_report.put(rec_m2n.get_ID_M2N_cUF(),new AW2_RECORD_REPORT(rec_m2n.get_TABLE_ID_1_cUF(), rec_m2n));
		}
		return this.hm_report.get(rec_m2n.get_ID_M2N_cUF());
	}
	
	
	
	public RECORD_REPORT_REITER get_record_report_reiter(RECORD_M2N rec_m2n) throws myException {
		if (this.hm_report_reiter.get(rec_m2n.get_ID_M2N_cUF())==null) {
			this.hm_report_reiter.put(rec_m2n.get_ID_M2N_cUF(),new RECORD_REPORT_REITER(rec_m2n.get_TABLE_ID_2_cUF()));
		}
		return this.hm_report_reiter.get(rec_m2n.get_ID_M2N_cUF());
	}
	

	
}
