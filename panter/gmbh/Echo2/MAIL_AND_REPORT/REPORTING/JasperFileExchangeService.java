/**
 * panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING
 * @author martin
 * @date 06.02.2020
 * 
 */
package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import java.io.File;
import java.util.StringTokenizer;

import panter.gmbh.basics4project.DB_ENUMS.REP_VARIANTEN;
import panter.gmbh.basics4project.DB_ENUMS.REP_VARIANTEN_PARAM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.vgl_like;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

/**
 * nachsehen, ob ein report einen uebersetzungsauftrag besitzt 
 * @author martin
 * @date 06.02.2020
 *
 */
public class JasperFileExchangeService {

	
	
	public JasperFileExchangeService() {
	}
	
	
	public String getTranslatedFileNameAndPath(String reportFileNameAndPath, E2_JasperHASH hash) throws myException {
		
		//zuerst den report aus dem pfad rausloesen
		StringTokenizer oToken = new StringTokenizer(reportFileNameAndPath,File.separator);
		
		String nameOhnePfad = "";
		while (oToken.hasMoreTokens()) {
			nameOhnePfad = oToken.nextToken();
		}
		String baseName = getBaseName(nameOhnePfad);
		
		SEL s = new SEL(_TAB.rep_varianten).FROM(_TAB.rep_varianten).WHERE(new vgl_like(REP_VARIANTEN.rep_file_name,baseName)).AND(new vgl_YN(REP_VARIANTEN.aktiv, true));
		
		RecList21 rlTranslators = new RecList21(_TAB.rep_varianten)._fill(new SqlStringExtended(s.s()));
		
		int treffer = 0;
		
		String translatedReportName = "";
		
		for (Rec21 recRV: rlTranslators) {
			translatedReportName = recRV.getUfs(REP_VARIANTEN.rep_file_name_trans); 
			
			RecList21 rlRVP = recRV.get_down_reclist21(REP_VARIANTEN_PARAM.id_rep_varianten);
			
			boolean passt = true;
			for (Rec21 recRVP: rlRVP) {
				Object valueInMap = hash.get(recRVP.getUfs(REP_VARIANTEN_PARAM.param_name,""));
				String valueInDB = recRVP.getUfs(REP_VARIANTEN_PARAM.param_value,"");
				
				if  ( ! (valueInMap!=null && valueInMap instanceof String && ((String)valueInMap).equals(valueInDB)) ) {
					passt=false;
				}
			}
			
			if (passt) {
				treffer++;
			}
		}
		
		
		if (treffer==1) {
			E2_JasperReportPathFinder repPath = new E2_JasperReportPathFinder(getBaseName(translatedReportName));
			return repPath.get_cCompleteReportPathAndFileName();
		}
		
		return reportFileNameAndPath;
	}

	
	private String getBaseName(String nameWithOutPath ) {
		String baseName = nameWithOutPath;
		if (baseName.toUpperCase().contains(".JRXML")) {
			int i = baseName.toUpperCase().indexOf(".JRXML");
			baseName = baseName.substring(0,i);
		} else if (baseName.toUpperCase().contains(".JASPER")) {
			int i = baseName.toUpperCase().indexOf(".JASPER");
			baseName = baseName.substring(0,i);
		}
		return baseName;
	}
	
}
