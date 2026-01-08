/**
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.JASPER_COMPILECHAIN;

import java.io.File;

import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.basics4project.DB_ENUMS.JASPER_COMPILE_CHAIN;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.PairS;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class JCC_MASK_comp_selectBaseDir extends RB_selField {

	/**
	 * @throws myException 
	 * 
	 */
	public JCC_MASK_comp_selectBaseDir() throws myException {
		super();
		
		this._width(200);
		
		//zuerst alle in der datenbank vorhandene varianten einlesen
		RecList21 rl = new RecList21(_TAB.jasper_compile_chain)._fill(new SqlStringExtended(new SEL("*").FROM(_TAB.jasper_compile_chain).ORDERUP(JASPER_COMPILE_CHAIN.compilebasedir).s()));
		
		VEK<String> dirs = new VEK<>();
		
		for (Rec21 r: rl) {
			dirs._addValidated((s)->{ return !dirs.contains(s); }, r.getUfs(JASPER_COMPILE_CHAIN.compilebasedir));
		}
		
		//dann die theoretischen (ALLE und mandanten)
		dirs._a("ALLE");
		
		RecList21 mandanten = new RecList21(_TAB.mandant)._fillWithAll();
		
		for (Rec21 m: mandanten) {
			dirs._a(m.getUfs(MANDANT.id_mandant));
		}

		dirs.sort((s1,s2)->{return s1.compareTo(s2);});
		
		this._addPair(new PairS("-",""));
		for (String dir: dirs) {
			this._addPair(new PairS(dir, dir));
		}
	}

}
