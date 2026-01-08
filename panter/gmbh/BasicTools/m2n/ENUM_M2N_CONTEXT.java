package panter.gmbh.BasicTools.m2n;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.M2N;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public enum ENUM_M2N_CONTEXT {

	USER_WEBSEITE(_TAB.user, _TAB.internet,"Definition, welcher Benutzer bei einzelnen Adressen welche Internet-Adresse in der Liste sehen will")
	
	
	
	;
	
	private _TAB    m_tab = null;
	private _TAB    n_tab = null;
	private String  erklaerung = null; 
	
	
	private ENUM_M2N_CONTEXT(_TAB p_mtab, _TAB p_ntab, String c_erklaerung) {
		this.m_tab = p_mtab;
		this.n_tab = p_ntab;
		this.erklaerung = c_erklaerung;
	}

	
	
	public MyE2_MessageVector delete(String id_m_tab, String id_n_tab, boolean clear_not_referenced) throws myException {
		String c_sql = "DELETE FROM "+bibE2.cTO()+"."+_TAB.m2n.fullTableName()+" WHERE 1=2 ";
		c_sql = c_sql+" OR ("+this._bedingung(id_m_tab, id_n_tab).s()+")";
		VEK<String> v_sql = new VEK<String>();
		if (clear_not_referenced) {
			v_sql._a(this.get_clear_statments());
		}
		v_sql._a(c_sql);
		return bibDB.ExecMultiSQLVector(v_sql, true);
	}
	
	/**
	 * alle eintraege loeschen, deren jeweilige m oder n - referenz verschwunden ist
	 * @throws myException 
	 */
	private VEK<String> get_clear_statments() throws myException {
		String del1 = "DELETE FROM "+bibE2.cTO()+"."+M2N.fullTabName()+" WHERE NVL("+M2N.m2n_context_enum.tnfn()+",'@@@@@@@@@@@@@@')='"+this.name()+"'"
									+ " AND "+M2N.table_id_1.tnfn()+" NOT IN (#SEL1#)";
		
		String del2 = "DELETE FROM "+bibE2.cTO()+"."+M2N.fullTabName()+" WHERE NVL("+M2N.m2n_context_enum.tnfn()+",'@@@@@@@@@@@@@@')='"+this.name()+"'"
									+ " AND "+M2N.table_id_2.tnfn()+" NOT IN (#SEL2#)";
		
		SEL sel1 = new SEL().ADDFIELD(this.m_tab.key()).FROM(this.m_tab);
		SEL sel2 = new SEL().ADDFIELD(this.n_tab.key()).FROM(this.n_tab);
		
		VEK<String> v = new VEK<String>()	._a(bibALL.ReplaceTeilString(del1, "#SEL1#", sel1.s()))
											._a(bibALL.ReplaceTeilString(del2, "#SEL2#", sel2.s()));
		
		return v;
		
	}

	private And _bedingung(String id_m_tab, String id_n_tab) throws myException {
		return new 	And(new vgl(M2N.table_base_1, this.m_tab.baseTableName()))
				.and(new vgl(M2N.table_base_2,this.n_tab.baseTableName()))
				.and(new vgl(M2N.table_id_1, id_m_tab))
				.and(new vgl(M2N.table_id_2, id_n_tab))
				.and(new vgl(M2N.m2n_context_enum, this.name()));
	}
	
	public MyE2_MessageVector save(String id_m_tab, String id_n_tab) throws myException {
		return this.insert(id_m_tab, id_n_tab);
	}
	
	
	public MyE2_MessageVector insert(String id_m_tab, String id_n_tab) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		Rec20 rec = new Rec20(_TAB.m2n);
		rec	._nv(M2N.table_base_1,	this.m_tab.baseTableName(),mv)
			._nv(M2N.table_base_2,	this.n_tab.baseTableName(),mv)
			._nv(M2N.table_id_1,	id_m_tab,mv)
			._nv(M2N.table_id_2,	id_n_tab,mv)
			._nv(M2N.m2n_context_enum,	this.name(),mv)
			;

		if (mv.get_bIsOK()) {
			rec._SAVE(true, mv);
		}
		return mv;
	}
	

	/**
	 * 
	 * @param id_m_tab
	 * @param id_n_tab
	 * @return true, when key is in the database, false if not
	 * @throws myException
	 */
	public boolean checkValue(String id_m_tab, String id_n_tab) throws myException {
		RecList20 	rec = new RecList20(_TAB.m2n)._fill(this._bedingung(id_m_tab, id_n_tab).s(), null);
		if (rec.size()==0) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * liefert die result-reclist fuer die besagten m2n-records
	 * @param id_m_tab
	 * @param id_n_tab
	 * @return
	 * @throws myException
	 */
	public RecList20  get_rlM2N(String id_m_tab, String id_n_tab) throws myException {
		return new RecList20(_TAB.m2n)._fill(this._bedingung(id_m_tab, id_n_tab).s(), null);
	}
	
	

	public _TAB get_mtab() {
		return m_tab;
	}


	public _TAB get_ntab() {
		return n_tab;
	}



	public String get_erklaerung() {
		return erklaerung;
	}
	
}
