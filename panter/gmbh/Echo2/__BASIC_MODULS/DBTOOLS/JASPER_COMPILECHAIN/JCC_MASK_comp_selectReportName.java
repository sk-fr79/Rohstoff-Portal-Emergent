/**
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.JASPER_COMPILECHAIN;

import java.io.File;

import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.PairS;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.RecList21_jasperCompileChain;

/**
 * @author martin
 *
 */
public class JCC_MASK_comp_selectReportName extends RB_selField {

	/**
	 * @throws myException 
	 * 
	 */
	public JCC_MASK_comp_selectReportName() throws myException {
		super();
		
		this._width(200);
		
		//jetzt alle reports aus den basisverzeichnissen ALLE, 1,2, ... (mandanten) auflisten
		String basePath = bibALL.get_ReportPathInFileSystem();

		VEK<String> v_dirs = new VEK<String>()._a("ALLE"+File.separator);
		
		RecList21 mandanten = new RecList21(_TAB.mandant)._fillWithAll();
		
		for (Rec21 m: mandanten) {
			v_dirs._a(m.getUfs(MANDANT.id_mandant)+File.separator);
		}
		
		VEK<String> jrxmls = new VEK<String>();
		for (String dir: v_dirs) {
			File ofileDir = new File(basePath+dir);
			if (ofileDir.exists() && ofileDir.isDirectory()) {
				String[] cJasperFiles = ofileDir.list();
				for (String s: cJasperFiles) {
					File fs = new File(basePath+dir+s);
					if (fs.exists() && fs.isFile() && s.toUpperCase().trim().endsWith(".JRXML")) {
						jrxmls._addValidated((jrxml)-> {return !jrxmls.contains(jrxml); }, s);
					}
				}
			}
		}
		
		//sortieren: 
		jrxmls.sort((s1,s2)->{return s1.compareTo(s2);});
		
		for (String rep: jrxmls) {
			this._addPair(new PairS(rep,rep));
		}
		
	}

}
