/**
 * 
 */
package panter.gmbh.Echo2.ListAndMask;

import panter.gmbh.indep.PairS;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public enum ENUM_LIST_CHECKBOXINFO {
	
	DELETE_BLOCK(new VEK<PairS>()._a(new PairS("DEL_GRUND","Löschgrund"))._a(new PairS("DEL_DATE","Löschdatum"))._a(new PairS("DEL_KUERZEL","Gelöscht von")), "DELETED")
	,STORNO_BLOCK(new VEK<PairS>()._a(new PairS("STORNO_GRUND","Stornogrund"))._a(new PairS("STORNO_KUERZEL","Storniert von")), "IST_STORNIERT")
	;
	
	private VEK<PairS> m_pairFieldNamesAndUserText = null;
	private String     m_nameOfYesNoField = null;
	
	private ENUM_LIST_CHECKBOXINFO(VEK<PairS> textAndFieldnames, String nameOfYesNoField) {
		this.m_pairFieldNamesAndUserText=textAndFieldnames;
		this.m_nameOfYesNoField = nameOfYesNoField;
	}
	
	/**
	 * 
	 * @param results (string, uebersetzt und am ende (kein \n am ende)
	 * @return
	 */
	public String getToolTipBlock(SQLResultMAP results) { 
		String ret = "";
		boolean ok = false;
		try {
			if (results.containsKey(this.m_nameOfYesNoField) && results.get_FormatedValue(this.m_nameOfYesNoField).equals("Y")) {
				ok = true;
			}
		} catch (myException e) {
			ok = false;
			if (bibALL.get_bDebugMode()) {	e.printStackTrace();}
		}
		if (ok) {
			for (PairS p: this.m_pairFieldNamesAndUserText) {
				ret += S.ms(p.getVal2()).CTrans()+": ";
				if (results.containsKey(p.getVal1())) {
					try {
						ret += results.get_FormatedValue(p.getVal1());
					} catch (myException e) {
						if (bibALL.get_bDebugMode()) {	e.printStackTrace();}
					}
				}
				ret+="\n";
			}
		}
		
		if (ret.endsWith("\n")) {
			ret = ret.substring(0,ret.lastIndexOf("\n"));
		}
		
		return ret;
	}
	
	
	
}
