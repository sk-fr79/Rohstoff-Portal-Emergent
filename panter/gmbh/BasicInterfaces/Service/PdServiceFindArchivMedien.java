/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 19.12.2018
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martinis,
 * @date 19.12.2018
 *
 */
public class PdServiceFindArchivMedien {

	private VEK<PAIR<_TAB, Long>>  tabsAndIds = new VEK<>();
			
	
	public PdServiceFindArchivMedien() {
		super();
	}

	public VEK<Rec21>  getArchivMedien()  throws myException {
		
		VEK<Rec21> ret = new VEK<>();
		
		boolean allFull = true;
		
		if (tabsAndIds!=null && tabsAndIds.size()>0) {
			for (PAIR<_TAB, Long> p: tabsAndIds) {
				if (p==null || p.getVal1()==null || p.getVal2()==null) {
					allFull=false;
					break;
				}
			}
		} else {
			allFull=false;
		}
		
		if (!allFull) {
			throw new myException(this,"Please fill tabsAndIds with not null members !");
		}
		
		for (PAIR<_TAB, Long> p: tabsAndIds) {
			SEL s = new SEL("*").FROM(_TAB.archivmedien).WHERE(new vgl(ARCHIVMEDIEN.tablename, p.getVal1().baseTableName())).AND(new vgl(ARCHIVMEDIEN.id_table,p.getVal2().toString()));		
			RecList21 rlMedien = new RecList21(_TAB.archivmedien)._fill(s.s());
			
			ret._a(rlMedien.values());
		}
		
		return ret;
	}
	
	
	public PdServiceFindArchivMedien _add(_TAB t, Long id) {
		this.tabsAndIds._a(new PAIR<_TAB, Long>()._setVal1(t)._setVal2(id));
		return this;
	}
	         
	  
	
	
	
	
}
