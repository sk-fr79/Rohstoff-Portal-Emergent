package rohstoff.Echo2BusinessLogic._4_ALL;

import java.util.Vector;

import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.TermLMR;
import panter.gmbh.indep.dataTools.TERM._TermCONST;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class SEL_Query4UserList extends SEL {

	public enum USERTYP{
		
		BUERO(new TermLMR(USER.ist_verwaltung.fn(),_TermCONST.COMP.EQ.s(),"'Y'"))
		,FAHRER(new TermLMR(USER.ist_fahrer.fn(),_TermCONST.COMP.EQ.s(),"'Y'"))
		,GESCHAEFTSFUEHER(new TermLMR(USER.geschaeftsfuehrer.fn(),_TermCONST.COMP.EQ.s(),"'Y'"))
		,TEXTCOLLECTOR(new TermLMR(USER.textcollector.fn(),_TermCONST.COMP.EQ.s(),"'Y'"))
		,SUPERVISOR(new TermLMR(USER.ist_supervisor.fn(),_TermCONST.COMP.EQ.s(),"'Y'"))
		;
		
		private Term queryBlock = null;
		
		private USERTYP(Term query_block) {
			this.queryBlock = query_block;
		}
		public Term get_queryBlock() {
			return queryBlock;
		}
		
	}
	
//	String cSelect1 = "SELECT NVL(NAME1,NVL(NAME,'-'))||', '||NVL(VORNAME,'')||' ('|| NVL(KUERZEL,'-')||')',ID_USER FROM "+bibE2.cTO()+".JD_USER " +
//			" WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND NVL(IST_VERWALTUNG,'N')='Y' AND  NVL(AKTIV,'N')='Y' ORDER BY 1 ";
//
	
	private Vector<Term>  v_user_typs = new Vector<Term>();
	
	public SEL_Query4UserList() throws myException {
		super();
		this.ADDFIELD("NVL("+USER.name1.fn()+",NVL("+USER.name.fn()+",'-'))||', '||NVL("+USER.vorname.fn()+",'')||' ('|| NVL("+USER.kuerzel.fn()+",'-')||')'", "NAMEBLOCK")
			.ADDFIELD(USER.id_user)
			.FROM(_TAB.user)
			.WHERE(new vgl(USER.id_mandant, bibALL.get_ID_MANDANT()))
			.AND(new vgl(USER.aktiv,"Y"))
			.ORDERUP("1");
	}

	
	public SEL_Query4UserList add_typ(USERTYP typ) {
		this.v_user_typs.add(typ.get_queryBlock());
		return this;
	}

	@Override
	public String s() throws myException {
		if (this.v_user_typs.size()>0) {
			this.AND(this.v_user_typs.get(0));
			for (int i=1;i<this.v_user_typs.size();i++) {
				this.OR(this.v_user_typs.get(i));
			}
		}
		
//		DEBUG.System_println(super.s());
		
		return super.s();
	}
	
	
}
