/**
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import java.io.File;
import java.util.HashMap;

import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceCompileJasperFileBean;
import panter.gmbh.basics4project.DB_ENUMS.JASPER_COMPILE_CHAIN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.PairS;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibFILE;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class RecList21_jasperCompileChain extends RecList21 {

	
	private String reportName = null;
	
	/**
	 * @param p_tab
	 */
	public RecList21_jasperCompileChain() {
		super(_TAB.jasper_compile_chain);
	}

	
	/**
	 * 
	 * @param jasperXmlName (name of jasperfile without path, WITH ending, zB. "liste.jrxml")
	 * @return
	 * @throws myException
	 */
	public RecList21_jasperCompileChain fillWithReportName(String jasperXmlName) throws myException {
		
		this.reportName = jasperXmlName;
		
		if (!jasperXmlName.toUpperCase().endsWith(".JRXML")) {
			throw new myException("Error: Not a name of an jasper-report-file");
		}
		
		//2018-05-04: pruefen, ob in der datenbank noch eintraege sind fuer tochterreports
		SEL s = new SEL("*").FROM(_TAB.jasper_compile_chain).WHERE(new vglParam(JASPER_COMPILE_CHAIN.reportname));

		this._fill(new SqlStringExtended(s.s())._addParameters(new VEK<ParamDataObject>()._a(new Param_String("",jasperXmlName))));

		return this;
	}
	
	
	
	public RecList21_jasperCompileChain checkAndCompileIfNeeded(String pathToCheckedReport) throws myException {
		//alle zu kompilierenden reports, die zu dem aufgerufenen record gehoeren, sammeln
		HashMap<String,PairS>  vPathAndFile = new HashMap<String,PairS>();
		
		pathToCheckedReport = bibFILE.addFileSeparatorToPath(pathToCheckedReport);
		
		vPathAndFile.put(pathToCheckedReport+this.reportName, new PairS(pathToCheckedReport,this.reportName));  //start-report als erstes
		
		for (Rec21 r: this) {
			
			String target = bibALL.get_ReportPathInFileSystem()+r.getUfs(JASPER_COMPILE_CHAIN.compilebasedir)+File.separator+r.getUfs(JASPER_COMPILE_CHAIN.compiletarget);
			
			File fTarget = new File(target);
			if (!fTarget.exists()) {
				throw new myException("CompileTarget is not existing. Please correct the compile-chain-table");
			}
			if (fTarget.isDirectory()) {
				target = target+File.separator;
				File[] files = fTarget.listFiles();
				for (File f: files) {
					if (f.isFile() && f.getName().toUpperCase().endsWith(".JRXML")) {
						vPathAndFile.put(target+f.getName(),new PairS(target,f.getName()));
					}
				}
			} else if (fTarget.isFile()) {
				vPathAndFile.put(target,new PairS(bibALL.get_ReportPathInFileSystem()+r.getUfs(JASPER_COMPILE_CHAIN.compilebasedir)+File.separator,r.getUfs(JASPER_COMPILE_CHAIN.compiletarget)));
			}
		}

		//jetzt pruefen, und gegebenenfalls kompilieren
		boolean isToCompile = false;
		PdServiceCompileJasperFileBean compiler = new PdServiceCompileJasperFileBean();
		for (PairS p: vPathAndFile.values()) {
			isToCompile = isToCompile || compiler.isCompileNeeded(p.getVal1(), p.getVal2());
		}
		if (isToCompile) {
			for (PairS p: vPathAndFile.values()) {
				compiler.compileJRXMLtoJasper(p.getVal1(), p.getVal2());
			}
		}
		//ende der erweiterung der Kompilierung

		return this;
		
	}
	
}
