/**
 * 
 */
package jasper;

import panter.gmbh.basics4project.DB_ENUMS.JASPERREPORT_KEY;
import panter.gmbh.basics4project.DB_ENUMS.JASPERREPORT_VALUE;
import panter.gmbh.basics4project.DB_ENUMS.SPRACHE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class Service {

	public static String read(String keycode, String laenderCode) throws myException {
		
		SEL  s_keyCode = new SEL("*").FROM(_TAB.jasperreport_key).WHERE(new vglParam(JASPERREPORT_KEY.keycode));
		SqlStringExtended  sql = new SqlStringExtended(s_keyCode.s())._addParameters(new VEK<ParamDataObject>()._a(new Param_String("",keycode)));
		Rec21 r_key = new Rec21(_TAB.jasperreport_key)._fill_sql(sql);

		SEL  s_sprache = new SEL("*").FROM(_TAB.sprache).WHERE(new vglParam(SPRACHE.laendercode));
		SqlStringExtended  sql_sprache = new SqlStringExtended(s_sprache.s())._addParameters(new VEK<ParamDataObject>()._a(new Param_String("",laenderCode)));
		Rec21 r_sprache = new Rec21(_TAB.sprache)._fill_sql(sql_sprache);
		
		
		if (r_key.is_newRecordSet()) {
			throw new MyExceptionJasperValueNotFound(S.NN(keycode,"<undef>"));
		}
		
		if (r_sprache.is_newRecordSet()) {
			throw new MyExceptionJasperValueNotFound(S.NN(laenderCode,"<undef>"));
		}
		
		SEL  s_value = new SEL("*").FROM(_TAB.jasperreport_value).WHERE(new vglParam(JASPERREPORT_VALUE.id_jasperreport_key)).AND(new vglParam(JASPERREPORT_VALUE.id_sprache));
		SqlStringExtended  sql_value = new SqlStringExtended(s_value.s())._addParameters(new VEK<ParamDataObject>()
																								._a(new Param_Long(r_key.getLongDbValue(JASPERREPORT_KEY.id_jasperreport_key)))
																								._a(new Param_Long(r_sprache.getLongDbValue(SPRACHE.id_sprache)))
																						);
		
		RecList21 rl_vals = new RecList21(_TAB.jasperreport_value)._fill(sql_value);
		
		if (rl_vals.size()==0 || rl_vals.size()>1) {
			//dann default
			return r_key.getUfs(JASPERREPORT_KEY.defaultvalue);
		} else {
			return rl_vals.get(0).getUfs(JASPERREPORT_VALUE.reportvalue);
		}
	}

	
	public static String getReportBasePath() {
		return bibSES.getServletContext().getRealPath("")+bibALL.get_REPORTBASEPATH();
	}

	public static String getReportPathALL() {
		return bibSES.getServletContext().getRealPath("")+bibALL.get_REPORTBASEPATH()+"/ALLE";
	}

	public static String getReportPathMandant() {
		return bibSES.getServletContext().getRealPath("")+bibALL.get_REPORTBASEPATH()+"/"+bibALL.get_ID_MANDANT();
	}


}
